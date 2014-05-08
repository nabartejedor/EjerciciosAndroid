package com.nabartejedor.calculadora;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Build;



public class MainActivity extends Activity {
	
	Calculadora c = null;
	private TextView textview;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		//outState.getstring("RESULTADO",resultado);
		//         getdouble....
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		c = new Calculadora();
		textview = (TextView) findViewById(R.id.textView1);
	}
	
	public void segue_numero(View v){
		Button boton = (Button) v;
		String numero =  boton.getText().toString();
		
		if(textview.getText().toString().equals("0")) {
			textview.setText(numero);
		} else {
			if(!c.ObtenerOperando().equals("")){
			  textview.append(numero);
			}
		}
		
		c.introducirDigito(textview.getText().toString());
	}
	
	public void segue_operacion(View v){
		Button boton = (Button) v;
		String operacion = boton.getText().toString();
		c.introducirOperando(operacion);
		textview.setText(String.valueOf(c.ObtenerResultado()));
	    
	}

}
