package com.nabartejedor.earthquake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.location.Location;
import android.net.ParseException;
import android.util.Log;

public class JSONes {

	public static ArrayList<Quake> ObtenerJson(String urlStr) throws IOException,   JSONException {
		
		ArrayList<Quake> list = new ArrayList<Quake>();
		
		
		urlStr = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson";
		URL url = new URL(urlStr);
				

		URLConnection connection;
		connection = url.openConnection();

		HttpURLConnection httpConnection = (HttpURLConnection) connection;
		int responseCode = httpConnection.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_OK) {
			Log.d("tag","ok en response code");
			InputStream in = httpConnection.getInputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"), 8);
		    StringBuilder sb = new StringBuilder();

		    String line = null;
		    while ((line = reader.readLine()) != null)
		    {
		        sb.append(line + "\n");
		    }
		    
		    String result = sb.toString();
			
		    JSONObject jObject = new JSONObject(result);
		    JSONArray jArray = jObject.getJSONArray("features"); 
		
			Log.d("tag","xxxxxxxxx" + jArray);
			Quake q = new Quake();
			long aaa;
			for (int i=0; i < jArray.length(); i++)
			{
			    
			        JSONObject oneObject = jArray.getJSONObject(i);
			        
			        JSONObject r = oneObject.getJSONObject("id");
			        JSONObject p = oneObject.getJSONObject("properties");
					JSONArray l = oneObject.getJSONObject("geometry").getJSONArray(
							"coordinates");

					
				//	q.setId(r.getLong("id"));
					
					aaa =  q.getId();
					
					Log.d("tag","zzzzz" + aaa);
					
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
	
	
	

