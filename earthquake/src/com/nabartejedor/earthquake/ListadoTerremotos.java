package com.nabartejedor.earthquake;

import java.util.ArrayList;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.nabartejedor.earthquake.R;

public class ListadoTerremotos extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {
	public final static String ITEMS_ARRAY = "ITEMS_ARRAY";
	public final static String ID = "_id";

	private static final String TAG = "EARTHQUAKE";

	// The loader's unique id. Loader ids are specific to the Activity or
	// Fragment in which they reside.
	private static final int LOADER_ID = 1;

	// The callbacks through which we will interact with the LoaderManager.
	private LoaderManager.LoaderCallbacks<Cursor> mCallbacks;

	private String[] from = { MyContentProvider.SQLiteOpenHelperMain.TIME,
			MyContentProvider.SQLiteOpenHelperMain.MAGNITUD,
			MyContentProvider.SQLiteOpenHelperMain.PLACE,
			MyContentProvider.SQLiteOpenHelperMain.ID };
	
	private int[] to = { R.id.time, R.id.magnitude, R.id.place };
	private SimpleCursorAdapter adapter;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d("tag","entro en oncreateview" );
		adapter = new SimpleCursorAdapter(getActivity(),
				R.layout.earthquake_row_layaout , null, from, to, 0);
		Log.d("tag","valor to" + to );
		adapter.setViewBinder(new EarthQuakeBinder());  
		setListAdapter(adapter);

		mCallbacks = this;

		LoaderManager lm = getLoaderManager();
	    lm.initLoader(LOADER_ID, null, mCallbacks);
	    Log.d("tag","valor magnitud: " + MyContentProvider.SQLiteOpenHelperMain.MAGNITUD );
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle inState) {
		super.onActivityCreated(inState);
		Log.d("tag","entro en activity create" );
//		bd = new DataBaseOperations(getActivity());
//		
//		//*Obtener shared preferences
//		// SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(null);
//		// String valor = prefs.getString("magnitud", "valor por defecto");
//		//Log.d("tag","XXXXX magnitud de shared preferences: " + valor);
//		
//		
//		listado.addAll(bd.selectEarthQuakes(0));
//		
//		Log.d("tag","***listado: " + listado);
//        
//		setListAdapter(adaptador);
//		adaptador.notifyDataSetChanged();
//		
//		DownloadFilesTask d = new DownloadFilesTask(getActivity(), this);
//		d.execute("http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson");

//		if (inState != null) {
//			// AÐadir a la lista
//			
//		}
	}
	@Override
	public void onResume() {
		super.onResume();

		getLoaderManager().restartLoader(LOADER_ID, null, mCallbacks);
	}
//	@Override
//	public void actualizarLista(ArrayList<Quake> result) {
//		for(Quake q : result) {
//			listado.add(0, q);
//		}
//
//		adaptador.notifyDataSetChanged();
//	}

	
	
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getActivity());
		String minMag = prefs.getString(
				getResources().getString(R.string.magnitud), "0");

		String where = MyContentProvider.SQLiteOpenHelperMain.MAGNITUD + " >= ? ";
		String[] whereArgs = { minMag };
		String order = MyContentProvider.SQLiteOpenHelperMain.TIME + " DESC";

		CursorLoader loader = new CursorLoader(getActivity(),
				MyContentProvider.CONTENT_URI, from, where, whereArgs, order);
		Log.d("tag","entro en on create loader" );
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		adapter.swapCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		adapter.swapCursor(null);
	}
}