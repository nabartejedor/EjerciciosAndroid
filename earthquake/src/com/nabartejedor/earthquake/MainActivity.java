package com.nabartejedor.earthquake;

 

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.nabartejedor.earthquake.R;
import com.nabartejedor.earthquake.ListadoTerremotos;


public class MainActivity extends Activity {


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);   
        
        if (savedInstanceState == null) {
        	getFragmentManager().beginTransaction()
            .add(R.id.container, new ListadoTerremotos(),"list")
            .commit();
        }
        
        
   //     SQLiteOpenHelperMain
  //      final DataBaseOperations basedatos = new DataBaseOperations(this);
    //    basedatos.open();
//        
//        
// //     Get the Content Resolver.
//        ContentResolver cr = getContentResolver();
//// 
////        // Specify the result column projection. Return the minimum set
////        // of columns required to satisfy your requirements.
//        String[] result_columns = new String[] {
//            
//            MyContentProvider.SQLiteOpenHelperMain.PLACE,
//            MyContentProvider.SQLiteOpenHelperMain.LATITUD,
//            MyContentProvider.SQLiteOpenHelperMain.MAGNITUD,
//            MyContentProvider.SQLiteOpenHelperMain.ID
//            };
////        
////        // Append a row ID to the URI to address a specific row.
////        // ESTO ES PARA EL DETALLE
////     //   Uri rowAddress =
////    //    ContentUris.withAppendedId(MyContentProvider.CONTENT_URI,0);
////        
////        
////        // Replace these with valid SQL statements as necessary.
//        String where = null;
//        String whereArgs[] = null;
//        String order = null;
////        // Return the specified rows.
//        Cursor resultCursor = cr.query(MyContentProvider.CONTENT_URI, result_columns,
//                                       where, whereArgs, order);
////        
//        Quake q = new Quake();
////        
//        while(resultCursor.moveToNext()) {
////        	
//        	Log.d("tag","entro en while cursor" + MyContentProvider.SQLiteOpenHelperMain.ID);
////
//			int idIdx = resultCursor.getColumnIndex(MyContentProvider.SQLiteOpenHelperMain.ID);
//			int placeIdx = resultCursor.getColumnIndex(MyContentProvider.SQLiteOpenHelperMain.PLACE);
//			int latIdx = resultCursor.getColumnIndex(MyContentProvider.SQLiteOpenHelperMain.LATITUD);
//			int magIdx = resultCursor.getColumnIndex(MyContentProvider.SQLiteOpenHelperMain.MAGNITUD);
////
//			String place = resultCursor.getString(placeIdx);
//			String id = resultCursor.getString(idIdx);
//			double latitud = resultCursor.getDouble(latIdx);
//			double magnitud = resultCursor.getDouble(magIdx);
////
//			q.setIdx(id);
//			q.setPlace(place);
//			q.setLat(latitud);
//        	q.setMag(magnitud);
////        	
//			Log.d("tag","set ID " + q.getIdx());
//			Log.d("tag","set LAT " + q.getLat());
//			Log.d("tag","set PLACE " + q.getPlace());
//			Log.d("tag","set MAGNITUD " + q.getMag());
////
//			addNewHoard(id,place,latitud,magnitud);
//		} 
////        
////        
////        
//        resultCursor.close();
//        
//        
//        
//        
//     // DELETE de la tabla entera
//     //	basedatos.deleteAllEarthquakes();
//        
//        //*********
//        
////     	Thread t = new Thread(new Runnable() {
////			public void run() {
////				// JSON
////		     	try {
////		   *****  		
////		     		
////		   *****  		
////				} catch (IOException e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				} catch (JSONException e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
////			}
////		});
////		t.start();
//     	
//		//***************
//
//        
////      DELETE        
////        String  whereArgs[] = null;
////        String where = "_id = " + 1;
////        basedatos.deleteEmptyHoards(where,whereArgs);        
//        
//        
//        
////        INSERT
//        
////        Quake q = new Quake();
////        q.setId(1);
////        q.setDetail("onddarbi center");
////        q.setLat(4342.32);
////        q.setLng(43242.55);
////        q.setMag(7);
////        q.setUrl("http://fdfvrfv.es");
////        basedatos.addNewEarthQuake(q);
//        
////        DELETE        
//       // whereArgs[] = null;
//  //      where = "_id = " + 5;
//  //      basedatos.deleteEmptyHoards(where,whereArgs);
//        
//        
////        UPDATE        
// //       basedatos.updateHoardValue(1, 2);
//        
////       SELECT 
// //       basedatos.selectEarthQuakes(0);

	}

	private Uri addNewHoard(String hoardId, String hoardPlace, double hoardLati, double hoardMag) {
   
           ContentValues newValues = new ContentValues();
    
           newValues.put(MyContentProvider.SQLiteOpenHelperMain.ID, hoardId);
           newValues.put(MyContentProvider.SQLiteOpenHelperMain.PLACE, hoardPlace);
           newValues.put(MyContentProvider.SQLiteOpenHelperMain.LATITUD,hoardLati);
           newValues.put(MyContentProvider.SQLiteOpenHelperMain.MAGNITUD,hoardMag);
           
           ContentResolver cr = getContentResolver();
    
           Uri myRowUri = cr.insert(MyContentProvider.CONTENT_URI, newValues);
   
           return myRowUri;
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
        if (id == R.id.refresh_button) {
        	//
        	 Log.d("tag","clickamos el refresh");
        	 Intent	i	=	new	Intent(this,MyService.class);
        	 startService(i);
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



