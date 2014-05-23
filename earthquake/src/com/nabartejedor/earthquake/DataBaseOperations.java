package com.nabartejedor.earthquake;

import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataBaseOperations {

	// SQLiteOpenHelper sqliteOpenHelper = new SQLiteOpenHelperMain(null, "",
	// null, 1);
	public SQLiteOpenHelperMain sqliteOpenHelperMain;
	private SQLiteDatabase bd;

	public DataBaseOperations(Context context) {
		super();
		sqliteOpenHelperMain = new SQLiteOpenHelperMain(context,
				SQLiteOpenHelperMain.DATABASE_NAME, null,
				SQLiteOpenHelperMain.DATABASE_VERSION);
		open();

	}

	public void open() {
		bd = sqliteOpenHelperMain.getWritableDatabase();
	}

	public void addNewEarthQuake(Quake q) {

		// Create a new row of values to insert.

		ContentValues newValues = new ContentValues();

		// Assign values for each row.
	//	newValues.put(SQLiteOpenHelperMain.ID, q.getId());
		newValues.put(SQLiteOpenHelperMain.ID_X, q.getIdx());
		newValues.put(SQLiteOpenHelperMain.PLACE, q.getPlace());
		newValues.put(SQLiteOpenHelperMain.TIME, q.getTime());
		newValues.put(SQLiteOpenHelperMain.DETAIL, q.getDetail());
		newValues.put(SQLiteOpenHelperMain.MAGNITUD, q.getMag());
		newValues.put(SQLiteOpenHelperMain.LATITUD, q.getLat());
		newValues.put(SQLiteOpenHelperMain.LONGITUD, q.getLng());
		newValues.put(SQLiteOpenHelperMain.URL, q.getUrl());

		newValues.put(SQLiteOpenHelperMain.CREATEAT, (new Date()).getTime());
		newValues.put(SQLiteOpenHelperMain.UPDATEAT, (new Date()).getTime());

		// Insert the row into your table
		bd.insert(SQLiteOpenHelperMain.DATABASE_TABLE, null, newValues);
	}

	public void deleteEmptyHoards(String where, String[] whereArgs) {

		bd.delete(SQLiteOpenHelperMain.DATABASE_TABLE, where, whereArgs);
	}
	
	public void deleteAllEarthquakes() {
		String where = SQLiteOpenHelperMain.ID + "!=0";
		String whereArgs[] = null;
		bd.delete(SQLiteOpenHelperMain.DATABASE_TABLE, where, whereArgs);
	}

	public void updateHoardValue(int hoardId, float newHoardValue) {
		// Create the updated row Content Values.
		ContentValues updatedValues = new ContentValues();

		// Assign values for each row.
		updatedValues.put(SQLiteOpenHelperMain.ID, newHoardValue);
		// [ ... Repeat for each column to update ... ]

		// Specify a where clause the defines which rows should be
		// updated. Specify where arguments as necessary.

		String where = SQLiteOpenHelperMain.ID + "=" + hoardId;
		String whereArgs[] = null;

		// Update the row with the specified index with the new values.
		SQLiteDatabase bd = sqliteOpenHelperMain.getWritableDatabase();
		bd.update(SQLiteOpenHelperMain.DATABASE_TABLE, updatedValues, where,
				whereArgs);
	}

	public ArrayList<Quake> selectEarthQuakes(double mag) {
		ArrayList<Quake> resultado = new ArrayList<Quake>();
		
		String groupBy = null;
		String having = null;
		String order = null;
		String[] result_columns = new String[]

		{ SQLiteOpenHelperMain.ID, SQLiteOpenHelperMain.PLACE,
				SQLiteOpenHelperMain.TIME, SQLiteOpenHelperMain.DETAIL,
				SQLiteOpenHelperMain.MAGNITUD, SQLiteOpenHelperMain.LATITUD,
				SQLiteOpenHelperMain.LONGITUD, SQLiteOpenHelperMain.URL,
				SQLiteOpenHelperMain.CREATEAT, SQLiteOpenHelperMain.UPDATEAT };
		// Specify the where clause that will limit our results.
		
		String where = SQLiteOpenHelperMain.MAGNITUD + ">=?";
		String whereArgs[] = {String.valueOf(mag)};
		
		Cursor cursor = bd.query(SQLiteOpenHelperMain.DATABASE_TABLE,
				result_columns, where, whereArgs, groupBy, having, SQLiteOpenHelperMain.TIME + " DESC");
		
		int idIdx = cursor.getColumnIndex(SQLiteOpenHelperMain.ID);
		int placeIdx = cursor.getColumnIndex(SQLiteOpenHelperMain.PLACE);
		int detailIdx = cursor.getColumnIndex(SQLiteOpenHelperMain.DETAIL);
		int latIdx = cursor.getColumnIndex(SQLiteOpenHelperMain.LATITUD);
		int magIdx = cursor.getColumnIndex(SQLiteOpenHelperMain.MAGNITUD);
		
		while(cursor.moveToNext()) {
			Quake q = new Quake();
			
			q.setIdx(cursor.getString(idIdx));
			q.setPlace(cursor.getString(placeIdx));
			q.setLat(cursor.getDouble(latIdx));
			q.setMag(cursor.getDouble(magIdx));
			Log.d("tag","entra en set ID " + q.getIdx());
			Log.d("tag","entra en set LAT " + q.getLat());
			Log.d("tag","entra en place PLACE " + q.getPlace());
			Log.d("tag","entra en set MAGNITUD " + q.getMag());
			Log.d("tag","entra en set DETAIL " + q.getDetail());
			// q.setDetail(cursor.getDetail()
			resultado.add(q);
		}

		cursor.close();
		
		return resultado;
	}

}
