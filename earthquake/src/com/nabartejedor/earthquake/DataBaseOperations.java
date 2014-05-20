package com.nabartejedor.earthquake;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseOperations {
	
	//SQLiteOpenHelper  sqliteOpenHelper = new SQLiteOpenHelperMain(null, "", null, 1);
	private SQLiteOpenHelperMain sqliteOpenHelperMain;
	private SQLiteDatabase bd;
	
	public DataBaseOperations(Context context) {
		super();
		sqliteOpenHelperMain=new SQLiteOpenHelperMain(context, SQLiteOpenHelperMain.DATABASE_NAME, null, SQLiteOpenHelperMain.DATABASE_VERSION);
		
	}
	
	public void open(){
		bd=sqliteOpenHelperMain.getWritableDatabase();
	}
	
	public	void	addNewHoard(int	id,	String place, int time, String detail, float magnitud,	float latitud, float longitud, String url, int createat, int updateat)	{	
							
		//	Create	a	new	row	of	values	to	insert.	
		
							ContentValues	newValues	=	new	ContentValues();	
						
							//	Assign	values	for	each	row.	
							newValues.put(SQLiteOpenHelperMain.ID,	id);	
							newValues.put(SQLiteOpenHelperMain.PLACE,place);	
							newValues.put(SQLiteOpenHelperMain.MAGNITUD,magnitud);	
							newValues.put(SQLiteOpenHelperMain.LATITUD,	latitud);	
							newValues.put(SQLiteOpenHelperMain.LONGITUD,longitud);	
							newValues.put(SQLiteOpenHelperMain.URL,url);	
							newValues.put(SQLiteOpenHelperMain.CREATEAT,createat);	
							newValues.put(SQLiteOpenHelperMain.UPDATEAT,updateat);	
						

							//	Insert	the	row	into	your	table	
							bd.insert(SQLiteOpenHelperMain.DATABASE_TABLE,	null,newValues);		
			}
	
	

	public	void	deleteEmptyHoards(String where,String[] whereArgs)	{	

		
		bd.delete(SQLiteOpenHelperMain.DATABASE_TABLE,	where,	whereArgs);	
    }
	
	
}
