package com.nabartejedor.intents;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity{
	
	private String cadenados = "";
	private String recibido;
	TextView textview = (TextView) findViewById(R.id.textView2);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

//		 Intent	intent	= new Intent(SecondActivity.this, MainActivity.class);	
//		
////		PackageManager	pm	=	getPackageManager();	
////		ComponentName	cn	=	intent.resolveActivity(pm);	
//		
//	//    Log.d("TAG","valor cn: " + cn);
//
//		
//		//if	(cn	==	null)	{	
//			startActivity(intent);
//			intent	=	getIntent();	
//			String	action	=	intent.getAction();	
//			
//			
//			String message = intent.getStringExtra(recibido);
//			
//			String	data	=	intent.getDataString();		
//			 Log.d("TAG","String enviado: " + data);
//			intent.getStringExtra(cadena);
//	
//		   
//		    textview.setText(message); 
		    
	//	}
//		
//			 Uri	marketUri	=	Uri.parse(“market://search?q=pname:com.myapp.packagename”);	
//			 Intent	marketIntent	=	new	Intent(Intent.ACTION_VIEW).setData(marketUri);		
//		
//			
//		}
		
//		
		
	}
	
	

}
