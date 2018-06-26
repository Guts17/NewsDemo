package com.lsq.newsdemo;

import java.util.ArrayList;

import com.lsq.News.News;
import com.lsq.Video.Video;
import com.lsq.Video.VideosAdapter;
import com.lsq.utils.HtmlGetVideo;
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

public class VideoFragment extends Fragment implements OnItemClickListener{
	private ArrayList<Video> al;
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
				al = HtmlGetVideo.getVideoLists("http://list.youku.com/category/show/c_95.html?spm=a2h1n.8251845.filterPanel.5~1~3!6~A");
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
    		
    		al = (ArrayList<Video>) message.obj;
    		VideosAdapter mAdapter = new VideosAdapter(al, mContext);
    		lv.setAdapter(mAdapter);
    	};
    };
    
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Video video = (Video) parent.getItemAtPosition(position);
		String url = video.getVideoUrl();
		Intent it = new Intent(mContext, com.lsq.newsdemo.VideoActivity.class);
		it.putExtra("url", url);
		System.out.println("title..." + video.getTitle());
		it.putExtra("title", video.getTitle());
		startActivity(it);
		
	}
}
