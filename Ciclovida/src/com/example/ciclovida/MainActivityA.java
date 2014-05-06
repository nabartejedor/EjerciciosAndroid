package com.example.ciclovida;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivityA extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_a);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity, menu);
		Log.d("TAG_cerate","creacion");
		return true;
	}

	
	@Override
	protected void onStart(){
		super.onStart();
		Log.d("TAG_start","comienzo");
		
	}
	@Override
	protected void onResume(){
		super.onResume();
		Log.d("TAG_resume","Reanudacion");
		
	}
	@Override
	protected void onPause(){
		super.onResume();
		Log.d("TAG_pause","Parada");
		
	}
	@Override
	protected void onStop(){
		super.onResume();
		Log.d("TAG_stop","Stop");
		
	}
	
	public void segue_a(){
		Log.d("TAG_segue","Segue_A");
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
			View rootView = inflater.inflate(R.layout.fragment_main_a,
					container, false);
			return rootView;
		}
	}

}
