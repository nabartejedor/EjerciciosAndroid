package com.nabartejedor.earthquake;

import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;


public class EarthquakeListFragment extends ListFragment implements LoaderCallbacks<Cursor> {
	

    SimpleCursorAdapter adapter;

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    String[] fromColumnas = new String[3];
	
	fromColumnas[0] = SQLiteOpenHelperMain.TIME;
	fromColumnas[1] = SQLiteOpenHelperMain.PLACE;
	fromColumnas[2] = SQLiteOpenHelperMain.MAGNITUD;
	
	int[] toColumnas = new int[3];

	toColumnas[0] = R.id.textView1;
	toColumnas[1] = R.id.textView2;
	toColumnas[2] = R.id.textView3;
	
	String projection = null;
	String where = null;
	
    adapter = new SimpleCursorAdapter(getActivity(),
			R.layout.fragmentTerremotos, null, fromColumnas, toColumnas);
    getLoaderManager().initLoader(1, null, this);
  }

  public Loader<Cursor> onCreateLoader(int id, Bundle args) {
	CursorLoader loader = new CursorLoader(getActivity(),
    MyContentProvider.CONTENT_URI, projection, where, null, null);
    return loader;
  }

  public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) { 
	adapter.swapCursor(cursor);
  }


  public void onLoaderReset(Loader<Cursor> loader) {
	adapter.swapCursor(null);
  }

}