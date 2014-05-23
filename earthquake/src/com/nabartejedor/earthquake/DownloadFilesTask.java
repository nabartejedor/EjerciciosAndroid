package com.nabartejedor.earthquake;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import android.content.Context;
import android.os.AsyncTask;

public class DownloadFilesTask extends AsyncTask<String, Void, ArrayList<Quake>> {
	
	public static interface IUpdateList {
		public void actualizarLista(ArrayList<Quake> result);
	}
	
	private Context ctx;
	private IUpdateList fragment;
	
	public DownloadFilesTask(Context ctx, IUpdateList fragment) {
		this.ctx = ctx;
		this.fragment = fragment;
	}

	@Override
	protected ArrayList<Quake> doInBackground(String... urls) {
		Quake q;
		ArrayList<Quake> listaTerremotos = new ArrayList<Quake>();
		
		DataBaseOperations basedatos = new DataBaseOperations(ctx);
		
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
			basedatos.addNewEarthQuake(q);
		}

		return listaTerremotos;
	}

	protected void onPostExecute(ArrayList<Quake> result) {
		fragment.actualizarLista(result);
	}

}
