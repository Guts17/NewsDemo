package com.lsq.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
//»ñÈ¡ÍøÂçÍ¼Æ¬
public class HttpImage {
	private static HttpURLConnection conn;
	
	public static Bitmap getHttpImage(String path){
		Bitmap img = null;
		try {
			URL url = new URL(path);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			if(conn.getResponseCode() == 200){
				InputStream is = conn.getInputStream();
				img = BitmapFactory.decodeStream(is);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(conn != null){
				conn.disconnect();
			}
		}
		return img;
	}
}
