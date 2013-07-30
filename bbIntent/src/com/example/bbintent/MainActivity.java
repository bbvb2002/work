package com.example.bbintent;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
		Uri uri = Uri.parse("tel:"+phone);
		Intent intent = new Intent(Intent.ACTION_CALL,uri);
		
		startActivity(intent);
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

}
