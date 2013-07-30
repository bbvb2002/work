package com.example.barcodetest;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String BARCODE_SCANNER_ACTION="com.google.zxing.client.andriod.SCAN";
	private static final int BARCODE_SCANNER_CODE = 0;
	private Button scanbtn;
	private TextView textview_result;
	private TextView textview_format;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textview_result = (TextView) findViewById(R.id.textView_result);
		textview_format = (TextView) findViewById(R.id.textView_format);
		
		scanbtn = (Button) findViewById(R.id.scanner);
		scanbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				openscanner();
			}
		});
		
	}

	private boolean checkInstall(){
		Intent intent = new Intent();
		
		intent.setAction(BARCODE_SCANNER_ACTION);
		List<ResolveInfo> result = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
		return result.size() !=0;
		
	}
	
	protected void openscanner() {
		// TODO Auto-generated method stub
		if(checkInstall()==false){
			return ;
		}
		Intent intent = new Intent();
		intent.setAction(BARCODE_SCANNER_ACTION);
		intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
		startActivityForResult(intent,BARCODE_SCANNER_CODE);
	}

	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == BARCODE_SCANNER_CODE && resultCode == RESULT_OK) {
			String result = data.getStringExtra("SCAN_REUSLT");
			String format = data.getStringExtra("SCAN_RESULT_FORMAT");
			
			textview_result.setText(result);
			textview_format.setText(format);
			Log.d("debug", "done");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
