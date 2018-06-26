package com.lsq.newsdemo;

import java.util.ArrayList;

import com.lsq.News.Image;
import com.lsq.News.Img;
import com.lsq.utils.HttpGetImglist;
import com.lsq.utils.ImagesAdapter;

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

public class ImgFragment extends Fragment implements OnItemClickListener{

	private ArrayList<Img> al;
	private Context mContext;
	private ListView lv;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContext = getActivity().getBaseContext();
		View view = inflater.inflate(R.layout.fragment_img, container, false);
		lv = (ListView) view.findViewById(R.id.lv_imgs);
		new Thread(new Runnable(){
			
			@Override
			public void run() {
				al = HttpGetImglist.GetImgs();
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
    		
    		al = (ArrayList<Img>) message.obj;
    		ImagesAdapter mAdapter = new ImagesAdapter(al, mContext);
    		lv.setAdapter(mAdapter);
    	};
    };
    
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
//		Image image = (Image) parent.getItemAtPosition(position);
		Img image = (Img) parent.getItemAtPosition(position);
		String url = image.getUrl();
		Intent it = new Intent(mContext, com.lsq.newsdemo.ImagesActivity.class);
		it.putExtra("url", url);
		startActivity(it);
		
	}
}
