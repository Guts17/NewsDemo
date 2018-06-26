package com.lsq.utils;

import java.util.ArrayList;

import com.lsq.News.News;
import com.lsq.newsdemo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsAdapter extends BaseAdapter {
	private ArrayList<News> al;
	private Context mContext;
	ViewHolder viewHolder;
	
	public NewsAdapter(ArrayList<News> al,Context mContext){
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.news_item,parent,false);
			viewHolder = new ViewHolder();
			viewHolder.img = (ImageView) convertView.findViewById(R.id.touxiang);
			viewHolder.txt_title = (TextView) convertView.findViewById(R.id.title);
			viewHolder.txt_says = (TextView) convertView.findViewById(R.id.says);
			viewHolder.txt_date = (TextView) convertView.findViewById(R.id.date);
			convertView.setTag(viewHolder);
		}
		else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.img.setImageBitmap(al.get(position).getThumbnail_pic_s());
        viewHolder.txt_title.setText(al.get(position).getTitle());
        viewHolder.txt_says.setText(al.get(position).getAuthor_name());
        viewHolder.txt_date.setText(al.get(position).getDate());
        return convertView;
	}
	
	static class ViewHolder{
		ImageView img;
		TextView txt_title;
		TextView txt_says;
		TextView txt_date;
	}

}
