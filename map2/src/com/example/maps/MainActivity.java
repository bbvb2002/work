package com.example.maps;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class MainActivity extends FragmentActivity {
	
	private SupportMapFragment mapfragment;
	private GoogleMap map;
	private LocationManager locMgr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mapfragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		map = mapfragment.getMap();
		locMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);  //get location
	
		
		LatLng mylatlng = getLastKnowLocation();   
		MarkerOptions options = new MarkerOptions();  
		options.position(mylatlng).title("my location");  
		
		map.moveCamera(CameraUpdateFactory.newLatLng(mylatlng));
		map.addMarker(options);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private LatLng getLastKnowLocation(){
		Criteria criteria = new Criteria();  //set 標準
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		
		String provider = locMgr.getBestProvider(criteria, true);
		Location myLocation = locMgr.getLastKnownLocation(provider);
		return new LatLng(myLocation.getLatitude(),myLocation.getLongitude());
		
	}

}
