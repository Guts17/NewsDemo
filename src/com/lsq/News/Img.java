package com.lsq.News;

import android.graphics.Bitmap;

public class Img {

	private String title;
	private Bitmap pic;
	private String url;
	private int counts;
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Bitmap getPic() {
		return pic;
	}
	public void setPic(Bitmap pic) {
		this.pic = pic;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Img [title=" + title + ", pic=" + pic + ", url=" + url
				+ ", counts=" + counts + "]";
	}
	
	
	
}
