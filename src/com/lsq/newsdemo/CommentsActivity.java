package com.lsq.newsdemo;

import java.util.ArrayList;

import com.lsq.News.Comment;
import com.lsq.News.MyApplication;
import com.lsq.utils.CommentAdapter;
import com.lsq.utils.CommentUtils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

public class CommentsActivity extends Activity{
	private ListView lv_comments;
	private ArrayList<Comment> al;
	private Context mContext;
	MyApplication myApplication;
	private int flag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		mContext = getBaseContext();
		myApplication = (MyApplication) getApplication();
		flag = getIntent().getIntExtra("flag", 2);
		lv_comments = (ListView) findViewById(R.id.lv_news);
		new Thread(new Runnable(){
			
			@Override
			public void run() {
				if(flag == 1){
					al = CommentUtils.getComments();
				}else{
					al = CommentUtils.getMyComments(myApplication.getLoginedUser().getUname());
				}
				
				Message msg = new Message();
				msg.obj = al;
				mHandler.sendMessage(msg);
			}
		}).start(); 
//		lv_comments.setOnItemClickListener(this);
		
	}
	
	private Handler mHandler = new Handler(){
    	public void handleMessage(android.os.Message message) {
    		
    		 al = (ArrayList<Comment>) message.obj;
    		 CommentAdapter mAdapter = new CommentAdapter(al,mContext);
    		lv_comments.setAdapter(mAdapter);
    	};
    };
    
//	public void onItemClick(AdapterView<?> parent, View view, int position,
//			long id) {
//		News news = (News) parent.getItemAtPosition(position);
//		String url = news.getUrl();
//		Intent it = new Intent(mContext, com.lsq.newsdemo.ContentsActivity.class);
//		it.putExtra("url", url);
//		startActivity(it);
//		
//	}
}
