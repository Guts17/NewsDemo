package com.lsq.News;

public class Comment {
	private String uname;
	private String comment;
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "Comment [uname=" + uname + ", comment=" + comment + "]";
	}
	
	
}
