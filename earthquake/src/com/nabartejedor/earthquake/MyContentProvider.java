package com.nabartejedor.earthquake;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.net.Uri;
import android.util.Log;

public class MyContentProvider extends ContentProvider{
	
	public static Uri	CONTENT_URI	= Uri.parse("content://com.nabartejedor.provider.terremotosdatabaseprovider/elements");
	
    public static int ALLROWS = 1;	
    public static int SINGLE_ROW = 2;	
    public static UriMatcher uriMatcher;
    
    
    
    private SQLiteOpenHelper myOpenHelper;
    
    static{
    uriMatcher = new	UriMatcher(UriMatcher.NO_MATCH);	
    uriMatcher.addURI("com.nabartejedor.provider.MyContentProvider","elements",ALLROWS);	
    uriMatcher.addURI("com.nabartejedor.provider.MyContentProvider","elements/#",SINGLE_ROW);
    }
	
    
    
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		
		 if(uriMatcher.match(uri) == ALLROWS)
		 { 
             Log.d("TAG","entro en uri matcher all");
			 return "vnd.android.cursor.dir/vnd.com.nabartejedor.provider.elemental";
		 }
		 else{	
			 Log.d("TAG","entro en uri matcher 1");
             return "vnd.android.cursor.item/vnd.com.nabartejedor.provider.elemental";
		 }		
	    
	}


	

	@Override
	public boolean onCreate() {
		Log.d("tag","entro en oncreate del content provider" );
		 myOpenHelper = new SQLiteOpenHelperMain(getContext(),SQLiteOpenHelperMain.DATABASE_NAME,null,	
		 SQLiteOpenHelperMain.DATABASE_VERSION);
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
		  String[] selectionArgs, String sortOrder) {
		  SQLiteDatabase db;
		  Log.d("TAG","entro en cursor");
		  try
		 {	
          db =	myOpenHelper.getWritableDatabase();	
		 }
           catch
		  (SQLiteException ex)	
		  {
		  db = myOpenHelper.getReadableDatabase();	
		  }
		
		  SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		// TODO Auto-generated method stub
            if(uriMatcher.match(uri) == SINGLE_ROW)	
            {
              String rowID = uri.getPathSegments().get(1);
              queryBuilder.appendWhere(SQLiteOpenHelperMain.ID + "=" + rowID);
            }
		    queryBuilder.setTables(SQLiteOpenHelperMain.DATABASE_TABLE);
		    String groupBy = null;
		    String having = null;
			Cursor cursor	 = queryBuilder.query(db, projection,selection,
				  selectionArgs,groupBy ,having,sortOrder);
		    return cursor;
	}

	@Override	
	 public Uri insert(Uri uri,ContentValues values)
	{
      SQLiteDatabase db = myOpenHelper.getWritableDatabase();

      String nullColumnHack = null;

      long id = db.insert(SQLiteOpenHelperMain.DATABASE_TABLE,nullColumnHack,values);

      if(id>-1){
		 Uri insertedId = ContentUris.withAppendedId(CONTENT_URI,id);
         getContext().getContentResolver().notifyChange(insertedId,null);
	   return insertedId;	
      }	
	
	 else return null;	
	 }

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

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




//		SQL	Statement	to	create	a	new	database.	
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


//		Called	when	no	db	exists	in	disk	
		@Override	
		public	void	onCreate(SQLiteDatabase	db)	{	
				db.execSQL(DATABASE_CREATE);	
		}




//		Called	when	there	is	a	database	version	mismatch	
		@Override	
		public	void	onUpgrade(SQLiteDatabase	db,	int	oldVersion,	int	newVersion)	{	
				//	Simplest	case	is	to	drop	the	old	table	and	create	a	new	one.	
				db.execSQL("DROP	TABLE	IF	EXISTS	"	+	DATABASE_TABLE);	
				//	Create	a	new	one.	
				onCreate(db);	
		}



	}
	
}
