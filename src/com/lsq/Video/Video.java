package com.lsq.Video;

import android.graphics.Bitmap;

public class Video {
	private String title;
	private String videoId;
	private String videoUrl;
	private String posterUrl;
	private Bitmap poster;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getPosterUrl() {
		return posterUrl;
	}
	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}
	@Override
	public String toString() {
		return "Video [title=" + title + ", videoId=" + videoId + ", videoUrl="
				+ videoUrl + ", posterUrl=" + posterUrl + "]";
	}
	public Bitmap getPoster() {
		return poster;
	}
	public void setPoster(Bitmap poster) {
		this.poster = poster;
	}
	
	
}
