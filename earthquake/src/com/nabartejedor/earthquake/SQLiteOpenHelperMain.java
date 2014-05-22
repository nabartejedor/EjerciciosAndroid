package com.nabartejedor.earthquake;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteOpenHelperMain extends SQLiteOpenHelper{
	
	public SQLiteOpenHelperMain(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}



	public	static	final	String	DATABASE_NAME	=	"earthquake.db";	
	public	static	final	String	DATABASE_TABLE	=	"terremotos";	
	public	static	final	int	DATABASE_VERSION	=	1;	

	public static final String ID = "_id";
	public static final String ID_X = "_idx";
	public static final String PLACE = "_place";
	public static final String TIME = "_time";
	public static final String DETAIL = "_detail";
	public static final String MAGNITUD = "_magnitud";
	public static final String LATITUD = "_lat";
	public static final String LONGITUD = "_long";
	public static final String URL = "_url";
	public static final String CREATEAT = "_create_at";
	public static final String UPDATEAT = "_update_at";
	
	
	
	
//	SQL	Statement	to	create	a	new	database.	
	private	static	final	String	DATABASE_CREATE	= "CREATE TABLE IF NOT EXISTS "  + DATABASE_TABLE +
			"(_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
			"_idx TEXT  UNIQUE," +
			"_place TEXT," +
			"_time INT," +
			"_detail TEXT," +
			"_magnitud REAL," +
			"_lat REAL," +
			"_long REAL," +
			"_url TEXT," +
			"_create_at INT," +
			"_update_at INT);";
	
	
//	Called	when	no	db	exists	in	disk	
	@Override	
	public	void	onCreate(SQLiteDatabase	db)	{	
			db.execSQL(DATABASE_CREATE);	
	}
	



//	Called	when	there	is	a	database	version	mismatch	
	@Override	
	public	void	onUpgrade(SQLiteDatabase	db,	int	oldVersion,	int	newVersion)	{	
			//	Simplest	case	is	to	drop	the	old	table	and	create	a	new	one.	
			db.execSQL("DROP	TABLE	IF	EXISTS	"	+	DATABASE_TABLE);	
			//	Create	a	new	one.	
			onCreate(db);	
	}
}
