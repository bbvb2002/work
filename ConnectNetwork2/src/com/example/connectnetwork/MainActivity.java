package com.example.connectnetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.connectnetwork.GeoModule.geometry.location;
import com.google.gson.Gson;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private  static String ROOT_URL = "http://graph.facebook.com/";
	private  static String EXAMPLE_URL;

	private ProgressDialog progress;
	private TextView resultTextView;
	private Button button1;
	private Button button2;
	private EditText edittext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		resultTextView = (TextView) findViewById(R.id.result);
		edittext = (EditText) findViewById(R.id.editText1);
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String address =edittext.getText().toString();
				try {
					address = URLEncoder.encode(address,"utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String pararm = String.format("%s",address);
				
				EXAMPLE_URL = ROOT_URL+pararm;
				UrlLoader urlLoader = new UrlLoader();
				urlLoader.execute(1);
			}
		});

		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String address = edittext.getText().toString();
				try {
					address = URLEncoder.encode(address,"utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String pararm = String.format("%s",address);
				EXAMPLE_URL = ROOT_URL+pararm;
				UrlLoader urlLoader = new UrlLoader();
				urlLoader.execute(2);
			}
		});

		progress = new ProgressDialog(this);
	}

	private String readStreamToString(InputStream in) {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
		try {
			String line;
			StringBuilder content = new StringBuilder();
			while ((line = buffer.readLine()) != null) {
				content.append(line);
			}
			return content.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String fetchMethod1() {
		try {
			URL url = new URL(EXAMPLE_URL);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			String content = readStreamToString(is);
			return content;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String fetchMethod2() {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(EXAMPLE_URL);
		try {
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String content = httpclient.execute(httpget, responseHandler);
			return content;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	private class UrlLoader extends AsyncTask<Integer, Integer, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progress.setTitle("Fetching");
			progress.show();
		}

		@Override
		protected String doInBackground(Integer... params) {
			int useMethod = params[0];
			switch (useMethod) {
			case 1:
				return fetchMethod1();
			case 2:
				return fetchMethod2();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
			if (result != null) {
				
					Gson gson = new Gson();
					FacebookInfoModule info = gson.fromJson(result, FacebookInfoModule.class);
					resultTextView.setText(String.format("id:%s", info.name));
				
			}
			progress.dismiss();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
