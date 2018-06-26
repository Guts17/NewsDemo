package com.lsq.newsdemo;

import com.lsq.News.MyApplication;
import com.lsq.utils.CommentUtils;
import com.lsq.utils.HtmlContents;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ContentsActivity extends Activity {

	private TextView tv;
	private String url;
	private EditText et_comment;
	private Button bt_report;
	private Context mContext;
	private MyApplication myApplication;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contents);
		mContext = getBaseContext();
		myApplication = (MyApplication) getApplication();
		tv = (TextView) findViewById(R.id.contents);
		et_comment = (EditText) findViewById(R.id.comment);
		bt_report = (Button) findViewById(R.id.report);
		url = getIntent().getStringExtra("url");
		
		bt_report.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						String flag = CommentUtils.insertComment(myApplication.getLoginedUser().getUname(), et_comment.getText().toString());
						System.out.println("flagΪ:" + flag);
						if("true".equals(flag)){
							Looper.prepare();
							Toast.makeText(mContext, "评论成功", 1000).show();
							Looper.loop();
						}else{
							Looper.prepare();
							Toast.makeText(mContext, "评论失败", 1000).show();
							Looper.loop();
						}
					}
				}).start();
				
				Intent it = new Intent(mContext, com.lsq.newsdemo.CommentsActivity.class);
				//设置标记，用于判断是哪个页面启动的评论页面
				it.putExtra("flag", 1);
				startActivity(it);
			}
		});
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				Message msg = new Message();
				msg.obj = HtmlContents.getNewsContents(url);
				mHandler.sendMessage(msg); 
			}
		}).start();
		tv.setText("");
	}
	
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg) {
			tv.setText((String) msg.obj);
		};
	};
	
	
}
