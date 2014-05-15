package com.example.internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.app.Activity;
import android.app.ActionBar;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;

public class MainActivity extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button	formbutton	=	(Button)	findViewById(R.id.button1);	
		Button  downbutton = (Button) findViewById(R.id.button2);
        
        formbutton.setOnClickListener(new	Button.OnClickListener()	{	    
          @Override
		  public	void	onClick(View	view)	{	 	
            Thread t = new Thread(new Runnable(){
              public void run(){
        	    obtenerJson();
              } 		
            });
            t.start();	  
          }
	    });
        
        
        
        
        downbutton.setOnClickListener(new	Button.OnClickListener()	{	    
          
        
        
	
            @Override
  		    public	void	onClick(View	view)	{ 
        	  String	serviceString	=	Context.DOWNLOAD_SERVICE;	
        	  DownloadManager	downloadManager;	
              downloadManager	=	(DownloadManager)getSystemService(serviceString);
        	  Uri	uri	=	Uri.parse("http://developer.android.com/shareables/icon_templates-v4.0.zip");
              DownloadManager.Request	request	=	new	Request(uri);	
              
              request.setDestinationInExternalFilesDir(MainActivity.this,Environment.DIRECTORY_DOWNLOADS,"Midescarga.zip");  
              
        	  final long	reference	=	downloadManager.enqueue(request);
        	  IntentFilter	filter	=	new	IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);	
      		
      	  	  BroadcastReceiver	receiver	=	new	BroadcastReceiver()	{	
      		  @Override	
      		  public	void	onReceive(Context	context,	Intent	intent)	{	
      						long	reference_new	=	intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,	-1);	
      						if	(reference_new	==	reference)	{									
      							Log.d("TAG",	"CONTENIDO DE REFERENCE: " + reference);
      						}	
      						
      		  }	
      		 
      		};	
      		
      		registerReceiver(receiver,filter);	
            }
  	    });
        
    }
	
	
	public void obtenerJson(){
		String	myFeed	= "http://www.arkaitzgarro.com/android/photos.json";	
 		try	{	
 				URL	url	=	new	URL(myFeed);	
 		
 				//	Create	a	new	HTTP	URL	connection	
 				URLConnection	connection	=	url.openConnection();	
 				HttpURLConnection	httpConnection	=	(HttpURLConnection)connection;	
 		
 				int	responseCode	=	httpConnection.getResponseCode();	
 				if	(responseCode	==	HttpURLConnection.HTTP_OK)	{	
 						InputStream	in	=	httpConnection.getInputStream();	
 						processStream(in);	
 				}	
 		}	
 		catch	(MalformedURLException	e)	{	
 				Log.d("TAG",	"Malformed	URL	Exception.",	e);	
 		}	
 		catch	(IOException	e)	{	
 				Log.d("TAG",	"IO	Exception.",	e);	
 		}
	}
       
	public void processStream(InputStream in){
		// HttpResponse response;
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder builder = new StringBuilder();
		try {
			for (String line = null; (line = reader.readLine()) != null;) {
			    builder.append(line).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONTokener tokener = new JSONTokener(builder.toString());
		try {
			JSONArray finalResult = new JSONArray(tokener);
			Log.d("TAG",	"JSON: " + finalResult);	
		} catch (JSONException e) {
			Log.d("TAG",	"se va por esta excepcion");
			e.printStackTrace();
		}
	}
        

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
