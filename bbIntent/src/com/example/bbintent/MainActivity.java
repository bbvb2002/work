package com.example.bbintent;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.util.ByteArrayBuffer;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText edit_phone;
	private EditText edit_web;
	private EditText edit_email;
	private EditText edit_sms;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		edit_phone = (EditText) findViewById(R.id.edit_phone);
		edit_web = (EditText) findViewById(R.id.edit_web);
		edit_email = (EditText) findViewById(R.id.edit_mail);
		edit_sms = (EditText) findViewById(R.id.edit_sms);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void clickPhoneButton(View view){
		
		String phone = edit_phone.getText().toString();
		
		String[] command = new String[]{
				"am", "start",
				"--user", "0",
				"-a", "android.intent.action.CALL",
				"-d", "tel:"+ phone};
		
		String returnstr = execCommand(command);
		Toast.makeText(this, returnstr, Toast.LENGTH_LONG).show();
		
//		Uri uri = Uri.parse("tel:"+phone);
//		Intent intent = new Intent(Intent.ACTION_CALL,uri);
//		
//		startActivity(intent);
	}
	
	public void clickWebButton(View view){
		
		String url = edit_web.getText().toString();
		Uri uri = Uri.parse("http://"+url);
		Intent intent = new Intent(Intent.ACTION_VIEW,uri);
		startActivity(intent);
	}

	public void clickEmailButton(View view){
		String mailaddress = edit_email.getText().toString();
		Uri uri = Uri.parse("mailto:"+mailaddress);
		Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
		startActivity(intent);
	}
	
	public void clicksmsButton(View view){
		String sms = edit_sms.getText().toString();
		Uri uri = Uri.parse("smsto:"+sms);
		Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
		intent.putExtra("sms_body", "hello world");
		startActivity(intent);
	}
	public void clickContactButton(View view){
		
		Intent intent = new Intent(Intent.ACTION_VIEW,ContactsContract.Contacts.CONTENT_URI);
		
		startActivity(intent);
	}
	public void clickBroadCastButton(View view){
		
		Intent intent = new Intent();
		
		intent.setAction("com.example.intentex.broadcast");
		intent.putExtra("text", "hello bb");
		sendBroadcast(intent);
	}
	
	private String execCommand(String[] commands){
		try {
			Process ps= Runtime.getRuntime().exec(commands);
			InputStream is= ps.getInputStream();
			byte[] buffer = new byte[1024];
			is.read(buffer);
			return new String(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
