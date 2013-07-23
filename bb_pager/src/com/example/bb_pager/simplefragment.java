package com.example.bb_pager;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class simplefragment extends Fragment {

	private String content;
	public simplefragment newInstance(String content){
		simplefragment fragment = new simplefragment();
		fragment.content = content;
		return fragment;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View main = inflater.inflate(R.layout.fragment,null);
		TextView text = (TextView) main.findViewById(R.id.textView1);
		text.setText(content);
		// TODO Auto-generated method stub
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	

}
