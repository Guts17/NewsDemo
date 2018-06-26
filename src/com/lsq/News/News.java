package com.lsq.News;

import android.graphics.Bitmap;



public class News {

	private String uniquekey;
	private String title;
	private String date;
	private String category;
	private String author_name;
	private String url;
	private Bitmap thumbnail_pic_s;
	private String contents;
	public String getUniquekey() {
		return uniquekey;
	}
	public void setUniquekey(String uniquekey) {
		this.uniquekey = uniquekey;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Bitmap getThumbnail_pic_s() {
		return thumbnail_pic_s;
	}
	public void setThumbnail_pic_s(Bitmap thumbnail_pic_s) {
		this.thumbnail_pic_s = thumbnail_pic_s;
	}
	
	
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	@Override
	public String toString() {
		return "News [uniquekey=" + uniquekey + ", title=" + title + ", date="
				+ date + ", category=" + category + ", author_name="
				+ author_name + ", url=" + url + ", thumbnail_pic_s="
				+ thumbnail_pic_s + ", contents=" + contents + "]";
	}
	
}
