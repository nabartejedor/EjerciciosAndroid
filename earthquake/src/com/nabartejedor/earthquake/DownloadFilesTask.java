package com.nabartejedor.earthquake;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class DownloadFilesTask extends AsyncTask<String, Void, ArrayList<Quake>> {
	private Context contexto;
	public static interface IUpdateList {
		public void actualizarLista(ArrayList<Quake> result);
	}

	private IUpdateList fragment;

	public DownloadFilesTask(Context contexto, IUpdateList fragment) {
		this.contexto = contexto;
		this.fragment = fragment;
	}

	@Override
	protected ArrayList<Quake> doInBackground(String... urls) {
		Log.d("tag","do in background");
		Quake q;
		ArrayList<Quake> listaTerremotos = new ArrayList<Quake>();

	//	DataBaseOperations basedatos = new DataBaseOperations(ctx);

		ContentResolver cr = contexto.getContentResolver();
		ContentValues newValues = new ContentValues();
		
		
		String url = urls[0];
		try {
			listaTerremotos = JSONes.ObtenerJson(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < listaTerremotos.size() - 1; i++) {
			q = listaTerremotos.get(i);
			addNewEarthQuake(q);
		}

		return listaTerremotos;
	}

	private void addNewEarthQuake(Quake q) {
		Log.d("tag","add new earthquake");
		ContentResolver cr = contexto.getContentResolver();
		ContentValues newValues = new ContentValues();

		newValues.put(MyContentProvider.SQLiteOpenHelperMain.TIME, q.getTime());
		newValues.put(MyContentProvider.SQLiteOpenHelperMain.ID, q.getId());
		newValues.put(MyContentProvider.SQLiteOpenHelperMain.PLACE, q.getPlace());
		newValues.put(MyContentProvider.SQLiteOpenHelperMain.LATITUD, q.getLat());
		newValues.put(MyContentProvider.SQLiteOpenHelperMain.LONGITUD, q.getLng());
		newValues.put(MyContentProvider.SQLiteOpenHelperMain.MAGNITUD,q.getMag());
		newValues.put(MyContentProvider.SQLiteOpenHelperMain.URL, q.getUrl());

		cr.insert(MyContentProvider.CONTENT_URI, newValues);
	}
	
	
	
	protected void onPostExecute(ArrayList<Quake> result) {
		fragment.actualizarLista(result);
	}

}
