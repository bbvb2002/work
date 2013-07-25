package com.example.contact;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.provider.ContactsContract;

public class MainActivity extends Activity {

	private ListView listview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		listview = (ListView) findViewById(R.id.contactList);
		createContact();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void createContact(){
		Uri uri = ContactsContract.Contacts.CONTENT_URI;
		//selection "name=?" selectionArgs String[]{"tommy"}
		//getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
		Cursor cursor = getContentResolver().query(uri, null, null, null, null);
		
		String[]  from = new String[]{ContactsContract.Contacts.DISPLAY_NAME,ContactsContract.Contacts._ID};
		int[] to = new int[]{android.R.id.text1,android.R.id.text2};
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, from, to);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
			Cursor c = (Cursor) arg0.getItemAtPosition(arg2);	
			int index = c.getColumnIndex(ContactsContract.Contacts._ID);
			long _id = c.getLong(index);
			
			TextView idTextView = (TextView) arg1.findViewById(android.R.id.text2);
			long _id2 = Long.valueOf(idTextView.getText().toString());
			
			Intent intent =new Intent();
			intent.putExtra("id", _id);
			intent.setClass(MainActivity.this,PhoneActivity.class);
			startActivity(intent);
			
			}
		});
		
	}

}
