package com.nabartejedor.earthquake;

import java.util.List;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.preference.PreferenceActivity;


public class MyFragmentPreferenceActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAlarm();
//		setContentView(R.layout.activity_my_fragment_preference);
//
//		if (savedInstanceState == null) {
//			getFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
	}

	public	void	onBuildHeaders(List<Header>	target)	{	
	 	 loadHeadersFromResource(R.xml.userpreferenceheaders,	target);	
	 }
	
	private	void	setAlarm()	{	
		//	Get	a	reference	to	the	Alarm	Manager	
		AlarmManager alarmManager =	(AlarmManager)getSystemService(MyService.ALARM_SERVICE);	
	
		//	Set	the	alarm	to	wake	the	device	if	sleeping.	
		int	alarmType	=	AlarmManager.ELAPSED_REALTIME_WAKEUP;	
	
		//	Trigger	the	device	in	10	seconds.	
		long	timeOrLengthofWait	=	1000;	
	
		//	Create	a	Pending	Intent	that	will	broadcast	and	action	
		String	ALARM_ACTION	=	"ALARM_ACTION";	
		Intent	intentToFire	=	new	Intent(ALARM_ACTION);	
		PendingIntent	alarmIntent	=	PendingIntent.getBroadcast(this,0,intentToFire,0);	
	
		//	Set	the	alarm	
		alarmManager.set(alarmType,	timeOrLengthofWait,	alarmIntent);	
		Log.d("tag","alarma activada");	
		alarmManager.cancel(alarmIntent);	
}
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.my_fragment_preference, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}

//	/**
//	 * A placeholder fragment containing a simple view.
//	 */
//	public static class PlaceholderFragment extends Fragment {
//
//		public PlaceholderFragment() {
//		}
//
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			View rootView = inflater.inflate(
//					R.layout.fragment_my_fragment_preference, container, false);
//			return rootView;
//		}
//	}
//
}


