package com.lsq.News;

import android.app.Application;

public class MyApplication extends Application {

	private User user = new User();
	
	public User getLoginedUser(){
		return user;
	}
	public void userLogout(){
		user = new User();
	}
	public void userLogin(User usera){
		user.setUid(usera.getUid());
		user.setUname(usera.getUname());
		user.setUpass(usera.getUpass());
	}
}
