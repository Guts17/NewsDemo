package com.lsq.utils;

import java.util.ArrayList;

import com.lsq.News.Comment;
import com.lsq.newsdemo.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CommentAdapter extends BaseAdapter {

	private ArrayList<Comment> al;
	private Context mContext;
	ViewHolder viewHolder;
	
	public CommentAdapter(ArrayList<Comment> al,Context mContext){
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.comment_item,parent,false);
			viewHolder = new ViewHolder();
//			viewHolder.img = (ImageView) convertView.findViewById(R.id.touxiang);
			viewHolder.txt_uname = (TextView) convertView.findViewById(R.id.uname);
			viewHolder.txt_comment = (TextView) convertView.findViewById(R.id.comment);
			convertView.setTag(viewHolder);
		}
		else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
//		viewHolder.img.setImageBitmap(al.get(position).getThumbnail_pic_s());
        viewHolder.txt_uname.setText(al.get(position).getUname());
        viewHolder.txt_comment.setText(al.get(position).getComment());
        return convertView;
	}
	
	static class ViewHolder{
//		ImageView img;
		TextView txt_uname;
		TextView txt_comment;
	}

}
