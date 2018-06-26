package com.lsq.newsdemo;

import java.util.ArrayList;

import com.lsq.News.News;
import com.lsq.utils.HttpGetnews;
import com.lsq.utils.NewsAdapter;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainFragment extends Fragment implements OnItemClickListener {
	
	private ArrayList<News> al;
	private Context mContext;
	private ListView lv;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContext = getActivity().getBaseContext();
		View view = inflater.inflate(R.layout.fragment_main, container, false);
		lv = (ListView) view.findViewById(R.id.lv_news);
		new Thread(new Runnable(){
			
			@Override
			public void run() {
				al = HttpGetnews.getNews();
				Message msg = new Message();
				msg.obj = al;
				mHandler.sendMessage(msg);
			}
		}).start(); 
		lv.setOnItemClickListener(this);
		return view;
	}
	
	private Handler mHandler = new Handler(){
    	public void handleMessage(android.os.Message message) {
    		
    		al = (ArrayList<News>) message.obj;
    		NewsAdapter mAdaapter = new NewsAdapter(al, mContext);
    		lv.setAdapter(mAdaapter);
    	};
    };
    
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		News news = (News) parent.getItemAtPosition(position);
		String url = news.getUrl();
		Intent it = new Intent(mContext, com.lsq.newsdemo.ContentsActivity.class);
		it.putExtra("url", url);
		startActivity(it);
		
	}
}
