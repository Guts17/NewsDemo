package com.lsq.utils;

import java.util.ArrayList;

import com.lsq.News.Img;
import com.lsq.newsdemo.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImagesAdapter extends BaseAdapter {

	private ArrayList<Img> al;
	private Context mContext;
	ViewHolder viewHolder;
	
	public ImagesAdapter(ArrayList<Img> al,Context mContext){
		this.al = al;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		return al.size();
	}

	@Override
	public Object getItem(int position) {
		return al.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = LayoutInflater.from(mContext).inflate(R.layout.image_item,parent,false);
			viewHolder = new ViewHolder();
			viewHolder.img = (ImageView) convertView.findViewById(R.id.img);
			viewHolder.txt_title = (TextView) convertView.findViewById(R.id.img_title);
			convertView.setTag(viewHolder);
		}
		else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.img.setImageBitmap(al.get(position).getPic());
        viewHolder.txt_title.setText(al.get(position).getTitle());
        return convertView;
	}
	
	static class ViewHolder{
		ImageView img;
		TextView txt_title;
	} 

}
