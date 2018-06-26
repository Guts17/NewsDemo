package com.lsq.newsdemo;


import java.util.ArrayList;

import com.lsq.Images.Images;
import com.lsq.utils.HtmlImages;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class ImagesActivity extends Activity {
	
	private TextView tv;
	private ArrayList<String> al = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Images.url = getIntent().getStringExtra("url");
		setContentView(R.layout.images);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Thread::::url:::" + Images.url);
				Message msg = new Message();
				msg.obj = HtmlImages.getImages(Images.url);
				mHandler.sendMessage(msg);
			}
		}).start();
//		tv = (TextView) findViewById(R.id.tv_urls);
		
	}
	
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg) {
//			tv.setText(msg.obj.toString());
			int i = 0;
			al = (ArrayList<String>) msg.obj;
			for(String a:al){
				Images.imageUrls[i] = a;
				System.out.println(Images.imageUrls[i]);
				i++;
			}
		};
	};
	
	
}
