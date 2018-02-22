package com.example.pebbler10.pebble;


import static com.getpebble.android.kit.Constants.APP_UUID;
import static com.getpebble.android.kit.Constants.MSG_DATA;
import static com.getpebble.android.kit.Constants.TRANSACTION_ID;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;


import com.getpebble.android.kit.PebbleKit;
import com.getpebble.android.kit.util.PebbleDictionary;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class PebbleReceiver extends BroadcastReceiver{
	
	//MainActivity mActivity = new MainActivity();
	public SharedPreferences pref;
	static boolean ServerVercion= false;
	

        @Override
    	public void onReceive(Context context, Intent intent) {
        	
        	//mActivity.isAppInstalled("com.Relmtech.RemotePaid");
        	//Log.isLoggable("Instalado?", Configuration.inst);
        	//Configuration.installed = mActivity.pref.getBoolean(Configuration.Inst, false);
        	
        	    PackageManager pm = context.getPackageManager();
        	    try {
        	        pm.getPackageInfo("com.Relmtech.RemotePaid", PackageManager.GET_ACTIVITIES);
        	        Configuration.installed = true;
        	    } catch (PackageManager.NameNotFoundException e) {
        	    	Configuration.installed = false;
        	    }
        	   
        	
        	pref = context.getSharedPreferences("Myprefs", Context.MODE_PRIVATE);
        	

        	/*
    		if(pref.contains(Configuration.Inst)){
    			Configuration.inst = pref.getInt(B, 0);
    		}
    		*/
        	
    		if(pref.contains(Configuration.Server1)){
    			Configuration.s1 = pref.getString(Configuration.Server1, "");
    		}
    		if(pref.contains(Configuration.Server2)){
    			Configuration.s2 = pref.getString(Configuration.Server2, "");
    		}
    		if(pref.contains(Configuration.Server3)){
    			Configuration.s3 = pref.getString(Configuration.Server3, "");
    		}
   
    		ServerVercion=pref.getBoolean(Configuration.Checkbox, false);
    			
    			if(ServerVercion) { 
    				Log.d("check tiene:","SS");
    			}else { Log.d("check no tiene:","NN");} 
        	 
        	
    
        	final UUID receivedUuid = (UUID) intent.getSerializableExtra(APP_UUID);
        	
        	if(!Configuration.myUUID.equals(receivedUuid)){
        	
        		Log.d(receivedUuid.toString(), "not my uuid");
        		return;
        	}
        	
        	final int transactionId = intent.getIntExtra(TRANSACTION_ID, -1);
        	final String jsonData = intent.getStringExtra(MSG_DATA);
        	if(jsonData == null || jsonData.isEmpty()){
        		
        		//Log.d(jsonData, "is empty");
        		return;
        	}
        	
        	try {
        		final PebbleDictionary data = PebbleDictionary.fromJson(jsonData);
        		
        		int buttonPressed = data.getUnsignedInteger(Configuration.DATA_KEY).intValue();
        		 //Log.d("V3", "Vercion 3"); 
        		     
	        		if(Configuration.installed ){
	        			
	        			JSONObject button=null;
	        			String jsonUriButton = Integer.toHexString(buttonPressed);
	        			JSONObject json = new JSONObject(Configuration.readFileFromAssets("uris.json", context) );
	        			
	        			if(!jsonUriButton.equals("0") && !jsonUriButton.equals("7") && !jsonUriButton.equals("8") && !jsonUriButton.equals("9")) { 
	        				Log.d("log", "entro a crear G");
	        				button = json.getJSONObject(  jsonUriButton ); 
	        			}
	        			Log.d("bt", String.valueOf(buttonPressed) );
	        			Log.d("s1key", String.valueOf(Configuration.SERVER1_KEY)) ;
	
	        			if (buttonPressed == Configuration.SERVER1_KEY ) {
	        				
	        					localIntentUri(context, Configuration.s1.toString() );
	        				
						} else if (buttonPressed == (Configuration.SERVER2_KEY)) {
							
								localIntentUri(context, Configuration.s2.toString() );
					
						} else if (buttonPressed == (Configuration.SERVER3_KEY)) {
							
								localIntentUri(context, Configuration.s3.toString() );
							
						} else if (buttonPressed == (Configuration.DATA_KEY)) {
								//Log.d("log", "entro a data key");
								sendStringToPebble(context,"installed");
						} else if (button!= null){
							String uni = "";
	    					if(!ServerVercion) { uni = button.getString("uriV1"); } else {  uni = button.getString("uriV2");  }
	            			Intent localIntent = new Intent("android.intent.action.VIEW");
	            			localIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
	            			localIntent.setData(Uri.parse(uni));
	            		    context.startActivity(localIntent);
						}else{
				             	Toast.makeText(context, "DEFAULT" + buttonPressed, Toast.LENGTH_SHORT).show();	
				        }
	
				        		//PebbleKit.sendAckToPebble(context, transactionId);
	        			
		        		PebbleKit.sendAckToPebble(context, transactionId);
		        	}else{
		        		Toast.makeText(context, "YOU DONT HAVE INSTALLED FULL VERSION" + "", Toast.LENGTH_SHORT).show();
		        		
		        	}
        		
	        	}
	        	catch (JSONException e){
	        		
	        		Log.d("data", "failed received -> dict" + e);
	        		return;
	        	}
        	
 	
        }
        
      
        private void sendStringToPebble(Context context,String message) {
        		if(message.length() < Configuration.BUFFER_LENGTH) {

        			PebbleDictionary dictionary = new PebbleDictionary();	

        			dictionary.addInt8(0,(byte) 42);
        			dictionary.addString(Configuration.DATA_KEY, message);	
        			
        			PebbleKit.sendDataToPebble(context, Configuration.myUUID, dictionary);
        			//Log.d("data", "terminar enviar dic");
        			
        		} else {
        			Log.i("sendStringToPebble()", "String too long!");
        		}
        	}
       private void localIntentUri(Context context,String server){
    	   
    	    Intent localIntent = new Intent("android.intent.action.VIEW");
			localIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
			localIntent.setData(Uri.parse("ur://server/"+server));
			context.startActivity(localIntent);
    	
       }
};

