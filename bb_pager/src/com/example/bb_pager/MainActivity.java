package com.example.bb_pager;

import com.viewpagerindicator.CirclePageIndicator;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;


public class MainActivity extends FragmentActivity {

	private ViewPager viewpager;
	private CirclePageIndicator indicator;
	
	private FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
