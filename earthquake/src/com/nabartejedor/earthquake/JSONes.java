package com.nabartejedor.earthquake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONes {

	public static ArrayList<Quake> ObtenerJson(String urlStr)
			throws IOException, JSONException {

		ArrayList<Quake> list = new ArrayList<Quake>();

		URL url = new URL(urlStr);

		URLConnection connection;
		connection = url.openConnection();

		HttpURLConnection httpConnection = (HttpURLConnection) connection;
		int responseCode = httpConnection.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_OK) {
			Log.d("tag", "ok en response code");
			InputStream in = httpConnection.getInputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					in, "UTF-8"), 8);
			StringBuilder sb = new StringBuilder();

			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}

			String result = sb.toString();

			JSONObject jObject = new JSONObject(result);
			JSONArray jArray = jObject.getJSONArray("features");

			Log.d("tag", "xxxxxxxxx" + jArray);

			for (int i = 0; i < jArray.length(); i++) {
				Quake q = new Quake();
				JSONObject oneObject = jArray.getJSONObject(i);

				String r = oneObject.getString("id");
				JSONObject p = oneObject.getJSONObject("properties");
				JSONArray l = oneObject.getJSONObject("geometry").getJSONArray(
						"coordinates");

				long time = (long) (System.currentTimeMillis());

				Log.d("tag", "ciclo en JSONes");


				q.setId(time);
				q.setIdx(r);
				q.setPlace(p.getString("place"));
				q.setMag(p.getDouble("mag"));
				q.setTime(p.getLong("time"));
				q.setUrl(p.getString("url"));
				q.setLng(l.getDouble(0));
				q.setLat(l.getDouble(1));

				list.add(q);

			}

		}
		return list;
	}

}
