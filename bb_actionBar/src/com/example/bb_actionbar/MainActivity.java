package com.example.bb_actionbar;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

import android.os.Bundle;

public class MainActivity extends SherlockActivity {

	private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        actionBar = getSupportActionBar();
        actionBar.setTitle("new title");
        actionBar.setHomeButtonEnabled(true);
        
       
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.subitem1:
			break;
		case R.id.subitem2:
			break;
		case R.id.startitem1:
			break;
		case R.id.startitem2:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
//		MenuInflater menuInflater = getSupportMenuInflater();
//		menuInflater.inflate(R.menu.main, menu);
		
		SubMenu submenu1 = menu.addSubMenu("start");
		submenu1.add("subItemStart1");
		submenu1.add("subItemStart2");
		submenu1.add("subItemStart3");
		
		MenuItem subMenuItem = submenu1.getItem();
		
		subMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		
		
		SubMenu submenu2 = menu.addSubMenu("settings");
		submenu2.add("subItemStart1");
		submenu2.add("subItemStart2");
		submenu2.add("subItemStart3");
		
		MenuItem subMenuItem2 = submenu2.getItem();
		
		subMenuItem2.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
		
		
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}


   
    
}
