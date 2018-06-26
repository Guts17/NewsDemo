package com.lsq.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

import android.graphics.Bitmap;

import com.lsq.News.News;

public class HttpGetnews extends Thread {

	private static HttpURLConnection conn;
	public static ArrayList<News> getNews(){
		ArrayList<News> al = new ArrayList<News>();
		Bitmap img;
		String str;
				try {
					System.out.println("开始连接");
					URL url = new URL("http://v.juhe.cn/toutiao/index?type=top&key=bf8fd06c981382ef5868c646e50f0c32");
					conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(5000);
					if(conn.getResponseCode() == 200){
						System.out.println("连接成功");
						InputStream is = conn.getInputStream();
						byte[] data = Stream2Bytes.read(is);
						str = new String(data,"utf-8");
						JSONObject jb = new JSONObject(str);
						JSONObject jb_result = jb.getJSONObject("result");
						JSONArray ja = jb_result.getJSONArray("data");
						
						for(int i = 0;i < ja.length();i ++){
							JSONObject jb_news = ja.getJSONObject(i);
							News news = new News();
							img = HttpImage.getHttpImage(jb_news.getString("thumbnail_pic_s"));
							news.setTitle(jb_news.getString("title"));
							news.setAuthor_name(jb_news.getString("author_name"));
							news.setDate(jb_news.getString("date"));
							news.setThumbnail_pic_s(img);
							news.setUrl(jb_news.getString("url"));
							al.add(news);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					if(conn != null){
						conn.disconnect();
					}
				}
				return al;
	}
}
