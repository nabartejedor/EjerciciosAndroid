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
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class MyContentProvider extends ContentProvider{
	public static final Uri CONTENT_URI = Uri.parse("content://com.nabartejedor.provider.terremotosdatabaseprovider/elements");

	SQLiteOpenHelper myEarthquakesDatabase;
	
	
    static final String ID = "_id";
    static final String ID_X = "_idx";
    static final String PLACE = "_place";
    static final String TIME = "_time";
    static final String DETAIL = "_detail";
    static final String MAGNITUD = "_magnitud";
    static final String LATITUD = "_lat";
    static final String LONGITUD = "_long";
    static final String URL = "_url";
    static final String CREATEAT = "_create_at";
    static final String UPDATEAT = "_update_at";

	//Create the constants used to differentiate between the different URI
	//requests.
	private static final int ALLROWS = 1;
	private static final int SINGLE_ROW = 2;
	private static final UriMatcher uriMatcher;
	//Populate the UriMatcher object, where a URI ending in
	//'elements' will correspond to a request for all items,
	//and 'elements/[rowID]' represents a single row.
	static {
	   uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	   uriMatcher.addURI("com.nabartejedor.provider.terremotosdatabaseprovider","elements", ALLROWS);
	   uriMatcher.addURI("com.nabartejedor.provider.terremotosdatabaseprovider","elements/#", SINGLE_ROW);
	}   
	
	@Override
	public String getType(Uri uri) {
		if(uriMatcher.match(uri) == SINGLE_ROW) { 
	         return "vnd.android.cursor.item/vnd.com.nabartejedor.provider.elemental";
	    }
		
		// if(uriMatcher.match(uri) == ALLROWS) { 
		else{
			return "vnd.android.cursor.dir/vnd.com.nabartejedor.provider.elemental";
		}
		
	}
	
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// Open a read / write database to support the transaction.
	    SQLiteDatabase db = myEarthquakesDatabase.getWritableDatabase();
	    // To add empty rows to your database by passing in an empty
	    // Content Values object you must use the null column hack
	    // parameter to specify the name of the column that can be
	    // set to null.
	    String nullColumnHack = null;
	    // Insert the values into the table
	    long id = db.insert(SQLiteOpenHelperMain.DATABASE_TABLE,
	        nullColumnHack, values);
	    // Construct and return the URI of the newly inserted row.
	    if (id > -1) {
	      // Construct and return the URI of the newly inserted row.
	      Uri insertedId = ContentUris.withAppendedId(CONTENT_URI, id);
	      // Notify any observers of the change in the data set.
	      getContext().getContentResolver().notifyChange(insertedId, null);
	      return insertedId;
	    }
	    else
	      return null;
	}

	@Override
	public boolean onCreate() {
		// Creo una instancia de la clase myEarthquakes para poder acceder a
				// ella
				myEarthquakesDatabase = new SQLiteOpenHelperMain(getContext(),
				SQLiteOpenHelperMain.DATABASE_NAME, null,
				SQLiteOpenHelperMain.DATABASE_VERSION);
				return true;

	}


	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
	    String[] selectionArgs, String sortOrder) {

	SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
	
	queryBuilder.setTables(SQLiteOpenHelperMain.DATABASE_TABLE);
	SQLiteDatabase db;
	try {
	  db = myEarthquakesDatabase.getWritableDatabase();
	} catch (SQLiteException ex) {
	  db = myEarthquakesDatabase.getReadableDatabase();
	}
	// If this is a row query, limit the result set to the passed in row.
	switch (uriMatcher.match(uri)) {
	  case SINGLE_ROW :
	    String rowID = uri.getPathSegments().get(1);
	    queryBuilder.appendWhere(ID + "=" + rowID);
	  default: break;
	}
	String groupBy = null;
	String having = null;
	// Execute the query.
	Cursor cursor = queryBuilder.query(db, projection, selection,
	selectionArgs, groupBy , having , sortOrder);
	
	  return cursor;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return 0;
	}

  

	
	//***************   SQLITE OPEN HELPER   ****************************
	private class SQLiteOpenHelperMain extends SQLiteOpenHelper{
		
		public static	final	String	DATABASE_NAME	=	"earthquake.db";	
		public static	final	String	DATABASE_TABLE	=	"terremotos";	
		public static	final	int	DATABASE_VERSION	=	1;	

		
		public SQLiteOpenHelperMain(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		
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


	//************** FIN OPENHELPER *********************




	
	
	
	
}


