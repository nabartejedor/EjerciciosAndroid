package com.nabartejedor.earthquake;

import java.util.List;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.preference.PreferenceActivity;


public class MyFragmentPreferenceActivity extends PreferenceActivity {



	public	void	onBuildHeaders(List<Header>	target)	{	
	 	 loadHeadersFromResource(R.xml.userpreferenceheaders,	target);	
	 }

}


