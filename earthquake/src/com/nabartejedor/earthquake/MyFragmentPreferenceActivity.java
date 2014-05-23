package com.nabartejedor.earthquake;

import java.util.List;

import android.preference.PreferenceActivity;


public class MyFragmentPreferenceActivity extends PreferenceActivity {



	public	void	onBuildHeaders(List<Header>	target)	{	
	 	 loadHeadersFromResource(R.xml.userpreferenceheaders,	target);	
	 }

}


