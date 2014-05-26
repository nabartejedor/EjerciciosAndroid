package com.nabartejedor.earthquake;

import java.util.ArrayList;

import android.app.ListFragment;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ListadoTerremotos extends ListFragment implements DownloadFilesTask.IUpdateList {
    private Cursor cursor; 
	private ArrayList<Quake> listado;
	private ArrayAdapter<Quake> adaptador;
	private SimpleCursorAdapter adapter; 
	
	DataBaseOperations bd;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Create the array list of to do items
	//	listado = new ArrayList<Quake>();

		// Create the array adapter to bind the array to the listview
	//	adaptador = new ArrayAdapter<Quake>(inflater.getContext(),
	//			android.R.layout.simple_list_item_1, listado);

		
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onActivityCreated(Bundle inState) {
		super.onActivityCreated(inState);
		
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
	public void actualizarLista(ArrayList<Quake> result) {
		for(Quake q : result) {
			listado.add(0, q);
		}
		
		adaptador.notifyDataSetChanged();
	}
	
}
