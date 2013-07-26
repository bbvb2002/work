package com.example.bbpush;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.provider.Settings.Secure;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.PushService;

public class MainActivity extends Activity {

	private EditText edittext;
	private Button button;
	private TextView textview;
	private Spinner spinner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 Parse.initialize(this, "CjKQjvDIE1xWp2McrLxCXMc01QSzUEApr458ZV8G", "IwHbXSgzYbeFmy2S78aSxEQnQehoMp6L9UHC3goE"); 
		 PushService.setDefaultPushCallback(this, MainActivity.class);
		 ParseInstallation.getCurrentInstallation().saveInBackground();
		 ParseAnalytics.trackAppOpened(getIntent());
		
		 register();
			
		 PushService.subscribe(this, "all", MainActivity.class);
		 PushService.subscribe(this, "deviceid_"+getdeviceId(), MainActivity.class);
		 
		 button = (Button) findViewById(R.id.button1);
		 edittext = (EditText) findViewById(R.id.editText1);
		 textview = (TextView) findViewById(R.id.textView1);
		 textview.setText(getdeviceId());
		 spinner = (Spinner) findViewById(R.id.spinner1);
		 setDeviceId();
		 button.setOnClickListener(new OnClickListener() {
		 
		 
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParsePush push = new ParsePush();
				//push.setChannel("all");
				push.setChannel("deviceid_"+spinner.getSelectedItem().toString());
				Toast.makeText(MainActivity.this,spinner.getSelectedItem().toString() , Toast.LENGTH_LONG).show();
				push.setMessage(edittext.getText().toString());
				push.sendInBackground();
				
			}
		});
	}

	private String getdeviceId(){
		return Secure.getString(getContentResolver(),Secure.ANDROID_ID);
	}
	
	private void register(){
		ParseObject object = new ParseObject("deviceId");
		object.put("id", getdeviceId());
		try {
			object.save();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void setDeviceId(){
		
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("deviceId");
		query.findInBackground(new FindCallback<ParseObject>() {
			
			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				// TODO Auto-generated method stub
				
				List<String> ids = new ArrayList<String>();
				for(ParseObject obj : objects){
					ids.add(obj.getString("id"));
				}
				
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,ids);
				spinner.setAdapter(adapter);
				
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
