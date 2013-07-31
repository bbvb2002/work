package com.example.screncapture;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadCast extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		// /system/bin/screencap -p screen.png;
		File file = new File(context.getFilesDir(),(new Date()).getTime()+"_screen.png");
		execCommand(new String[]{"/system/bin/screencap","-p",file.toString()});
		Toast.makeText(context, "saveto:"+file.toString(), Toast.LENGTH_LONG);
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


