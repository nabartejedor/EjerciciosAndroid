package com.nabartejedor.intents;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import	android.content.BroadcastReceiver;	
import	android.content.Context;	


public class MainActivity extends Activity {
    public  String key = "cadena";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
      
		
//		Intent	intent	=	new	Intent(MainActivity.this,	SecondActivity.class);	
//		startActivity(intent);
		
        Button	formbutton	=	(Button)	findViewById(R.id.buttonform);	
        final EditText edittext = (EditText) findViewById(R.id.editText2);
        
        formbutton.setOnClickListener(new	Button.OnClickListener()	{	
          @Override
		public	void	onClick(View	view)	{	
        	
		    Intent	result	=  new	Intent(MainActivity.this,	FormActivity.class);	
		    result.putExtra("cadena",key);
		    startActivity(result);
		   //startActivityForResult(FORM_ACTION,result);
		    
		    Log.d("TAG","valor cadena: " + key);
		   // setResult(RESULT_OK,result);	
		   // finish();	
		
	      }
      });	
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
	
//	@Override	
//	public	void	onActivityResult(int requestCode,int	resultCode,	Intent	data)	{	
//			super.onActivityResult(requestCode,	resultCode,	data);	
//
////			switch(requestCode)	{	
////					case	(SELECT_HORSE):	
//							if	(resultCode	==	Activity.RESULT_OK){	
//								// String	cadena_enviada	=	data.getData().toString();	
//							//	textview.setText((CharSequence) data); 
//							}
//							//break;	
//
//					//default:	break;	
//		//	}	
//	}

}
