//package com.nabartejedor.earthquake;
//
//import java.util.ArrayList;
//import java.util.Date;
//
//import com.nabartejedor.earthquake.MyContentProvider.SQLiteOpenHelperMain;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.util.Log;
//
//public class DataBaseOperations {
//
//	// SQLiteOpenHelper sqliteOpenHelper = new SQLiteOpenHelperMain(null, "",
//	// null, 1);
//	public SQLiteOpenHelperMain sqliteOpenHelperMain;
//	private SQLiteDatabase bd;
//
//	public DataBaseOperations(Context context) {
//		super();
//		sqliteOpenHelperMain = MyContentProvider.SQLiteOpenHelperMain(context,
//				MyContentProvider.SQLiteOpenHelperMain.DATABASE_NAME, null,
//				MyContentProvider.SQLiteOpenHelperMain.DATABASE_VERSION);
//		open();
//
//	}
//
//	public void open() {
//		bd = sqliteOpenHelperMain.getWritableDatabase();
//	}
//
//	public void addNewEarthQuake(Quake q) {
//
//		// Create a new row of values to insert.
//
//		ContentValues newValues = new ContentValues();
//
//		// Assign values for each row.
//	//	newValues.put(SQLiteOpenHelperMain.ID, q.getId());
//		newValues.put(MyContentProvider.SQLiteOpenHelperMain.ID_X, q.getIdx());
//		newValues.put(MyContentProvider.SQLiteOpenHelperMain.PLACE, q.getPlace());
//		newValues.put(MyContentProvider.SQLiteOpenHelperMain.TIME, q.getTime());
//		newValues.put(MyContentProvider.SQLiteOpenHelperMain.DETAIL, q.getDetail());
//		newValues.put(MyContentProvider.SQLiteOpenHelperMain.MAGNITUD, q.getMag());
//		newValues.put(MyContentProvider.SQLiteOpenHelperMain.LATITUD, q.getLat());
//		newValues.put(MyContentProvider.SQLiteOpenHelperMain.LONGITUD, q.getLng());
//		newValues.put(MyContentProvider.SQLiteOpenHelperMain.URL, q.getUrl());
//
//		newValues.put(MyContentProvider.SQLiteOpenHelperMain.CREATEAT, (new Date()).getTime());
//		newValues.put(MyContentProvider.SQLiteOpenHelperMain.UPDATEAT, (new Date()).getTime());
//
//		// Insert the row into your table
//		bd.insert(MyContentProvider.SQLiteOpenHelperMain.DATABASE_TABLE, null, newValues);
//	}
//
//	public void deleteEmptyHoards(String where, String[] whereArgs) {
//
//		bd.delete(MyContentProvider.SQLiteOpenHelperMain.DATABASE_TABLE, where, whereArgs);
//	}
//
//	public void deleteAllEarthquakes() {
//		String where = MyContentProvider.SQLiteOpenHelperMain.ID + "!=0";
//		String whereArgs[] = null;
//		bd.delete(MyContentProvider.SQLiteOpenHelperMain.DATABASE_TABLE, where, whereArgs);
//	}
//
//	public void updateHoardValue(int hoardId, float newHoardValue) {
////		// Create the updated row Content Values.
////		ContentValues updatedValues = new ContentValues();
////
////		// Assign values for each row.
////		updatedValues.put(MyContentProvider.SQLiteOpenHelperMain.ID, newHoardValue);
////		// [ ... Repeat for each column to update ... ]
////
////		// Specify a where clause the defines which rows should be
////		// updated. Specify where arguments as necessary.
////
////		String where = MyContentProvider.SQLiteOpenHelperMain.ID + "=" + hoardId;
////		String whereArgs[] = null;
////
////		// Update the row with the specified index with the new values.
////		SQLiteDatabase bd = MyContentProvider.SQLiteOpenHelperMain.getWritableDatabase();
////		bd.update(MyContentProvider.SQLiteOpenHelperMain.DATABASE_TABLE, updatedValues, where,
////				whereArgs);
//	}
//
//	public ArrayList<Quake> selectEarthQuakes(double mag) {
//		ArrayList<Quake> resultado = new ArrayList<Quake>();
//
//		String groupBy = null;
//		String having = null;
//		String[] result_columns = new String[]
//
//		{ MyContentProvider.SQLiteOpenHelperMain.ID, MyContentProvider.SQLiteOpenHelperMain.PLACE,
//				MyContentProvider.SQLiteOpenHelperMain.TIME, MyContentProvider.SQLiteOpenHelperMain.DETAIL,
//				MyContentProvider.SQLiteOpenHelperMain.MAGNITUD, MyContentProvider.SQLiteOpenHelperMain.LATITUD,
//				MyContentProvider.SQLiteOpenHelperMain.LONGITUD, MyContentProvider.SQLiteOpenHelperMain.URL,
//				MyContentProvider.SQLiteOpenHelperMain.CREATEAT, MyContentProvider.SQLiteOpenHelperMain.UPDATEAT };
//		// Specify the where clause that will limit our results.
//
//		String where = MyContentProvider.SQLiteOpenHelperMain.MAGNITUD + ">=?";
//		String whereArgs[] = {String.valueOf(mag)};
//
//		Cursor cursor = bd.query(MyContentProvider.SQLiteOpenHelperMain.DATABASE_TABLE,
//				result_columns, where, whereArgs, groupBy, having, MyContentProvider.SQLiteOpenHelperMain.TIME + " DESC");
//
//		int idIdx = cursor.getColumnIndex(MyContentProvider.SQLiteOpenHelperMain.ID);
//		int placeIdx = cursor.getColumnIndex(MyContentProvider.SQLiteOpenHelperMain.PLACE);
//		int latIdx = cursor.getColumnIndex(MyContentProvider.SQLiteOpenHelperMain.LATITUD);
//		int magIdx = cursor.getColumnIndex(MyContentProvider.SQLiteOpenHelperMain.MAGNITUD);
//
//		while(cursor.moveToNext()) {
//			Quake q = new Quake();
//
//			q.setIdx(cursor.getString(idIdx));
//			q.setPlace(cursor.getString(placeIdx));
//			q.setLat(cursor.getDouble(latIdx));
//			q.setMag(cursor.getDouble(magIdx));
//			Log.d("tag","entra en set ID " + q.getIdx());
//			Log.d("tag","entra en set LAT " + q.getLat());
//			Log.d("tag","entra en place PLACE " + q.getPlace());
//			Log.d("tag","entra en set MAGNITUD " + q.getMag());
//			Log.d("tag","entra en set DETAIL " + q.getDetail());
//
//			resultado.add(q);
//		}
//
//		cursor.close();
//
//		return resultado;
//	}
//
//}