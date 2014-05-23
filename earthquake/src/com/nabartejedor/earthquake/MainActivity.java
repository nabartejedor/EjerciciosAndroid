package com.nabartejedor.earthquake;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity {

    private String whereArgs[];

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);   
        
        if (savedInstanceState == null) {
        	getFragmentManager().beginTransaction()
            .add(R.id.container, new ListadoTerremotos(),"list")
            .commit();
        }
        
        
        //SQLiteOpenHelperMain
        final DataBaseOperations basedatos = new DataBaseOperations(this);
        basedatos.open();
        
     // DELETE de la tabla entera
     //	basedatos.deleteAllEarthquakes();
        
        //*********
        
//     	Thread t = new Thread(new Runnable() {
//			public void run() {
//				// JSON
//		     	try {
//		   *****  		
//		     		
//		   *****  		
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
//		t.start();
     	
		//***************
		
        
//      DELETE        
//        String  whereArgs[] = null;
//        String where = "_id = " + 1;
//        basedatos.deleteEmptyHoards(where,whereArgs);        
        
        
        
//        INSERT
        
//        Quake q = new Quake();
//        q.setId(1);
//        q.setDetail("onddarbi center");
//        q.setLat(4342.32);
//        q.setLng(43242.55);
//        q.setMag(7);
//        q.setUrl("http://fdfvrfv.es");
//        basedatos.addNewEarthQuake(q);
        
//        DELETE        
       // whereArgs[] = null;
  //      where = "_id = " + 5;
  //      basedatos.deleteEmptyHoards(where,whereArgs);
        
        
//        UPDATE        
 //       basedatos.updateHoardValue(1, 2);
        
//       SELECT 
 //       basedatos.selectEarthQuakes(0);

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
        	Intent	i	=	new	Intent(this,MyFragmentPreferenceActivity.class);	
            startActivityForResult(i,1);
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}



