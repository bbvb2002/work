package com.example.bbpush;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyPushReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.d("debug","receiver");
		 try {
		      String action = intent.getAction();
		      String channel = intent.getExtras().getString("com.parse.Channel");
		      JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
		 
		      Log.d("debug", "got action " + action + " on channel " + channel + " with:");
		      Iterator itr = json.keys();
		      while (itr.hasNext()) {
		        String key = (String) itr.next();
		        Log.d("debug", "..." + key + " => " + json.getString(key));
		      }
		    } catch (JSONException e) {
		      Log.d("debug", "JSONException: " + e.getMessage());
		    }
		  }
	

}
