package com.nabartejedor.intents;

import	android.content.BroadcastReceiver;	
import	android.content.Context;	
import	android.content.Intent;	
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.telephony.*;


public class Broadcast extends BroadcastReceiver {
	// private static final String CALL_PHONE = "CALL_PHONE";
	
	Bundle bundle;
    String state = "Ok";
    String inCall;
		@Override	
		public	void	onReceive(Context	context,	Intent	intent)	{	
			
			
			
			String action = intent.getAction();
			Uri	data	=	intent.getData();	
			
		//	String	type	=	intent.getStringExtra(CALL_PHONE);
			bundle = intent.getExtras();
			Log.d("TAG","recibiendo");
			Log.d("TAG","accion: " + action);
			if(action.equals("android.intent.action.PHONE_STATE"))
			{
				
				Log.d("TAG","ESTAN LLAMANDO");
				state = bundle.getString(TelephonyManager.EXTRA_STATE);
				if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                    inCall = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                    Log.d("TAG","Numero entrante: " + inCall);
				}    
			
			}
			if(action.equals("android.provider.Telephony.SMS_RECEIVED"))
			{
				Log.d("TAG","SMS RECIBIDO");
			
			}
			
			
		}
		
		
		// if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY.......
	
}
