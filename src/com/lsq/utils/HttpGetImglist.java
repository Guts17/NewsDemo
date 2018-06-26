package com.lsq.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.graphics.Bitmap;
import com.lsq.News.Image;
import com.lsq.News.Img;

public class HttpGetImglist {
	private static HttpURLConnection conn;

	public static ArrayList<Img> GetImgs(){
		ArrayList<Img> al = new ArrayList<Img>();
		Bitmap image;
		String str;
		try {
			System.out.println("图片请求开始连接");
			
			Document doc = Jsoup.connect("http://news.qq.com/photon/photostory/eyhz.htm").get();
			Elements pic_lists = doc.getElementsByAttributeValue("class", "nrList");
			for(Element e : pic_lists){
				Img img = new Img();
				img.setTitle(e.getElementsByTag("p").first().text());
				img.setUrl(e.attr("href"));
				img.setPic(HttpImage.getHttpImage(e.getElementsByTag("img").first().attr("src")));
				al.add(img);
				
			}
			
//			Document doc = Jsoup.connect("http://desk.zol.com.cn/fengjing/").get();
//			Elements pic_lists = doc.getElementsByAttributeValue("class", "pic");
//			for(Element e : pic_lists){
//				System.out.println("http://desk.zol.com.cn" + e.attr("href"));
//				Img img = new Img();
//				img.setTitle(e.getElementsByTag("img").first().attr("alt"));
//				img.setUrl("http://desk.zol.com.cn" + e.attr("href"));
//				image = HttpImage.getHttpImage(e.getElementsByTag("img").first().attr("src"));
//				img.setPic(image);
//				al.add(img);
//				
//			}
			
//			URL url = new URL("https://api.tianapi.com/meinv/?key=ab83f861e1893e3b6b8ff0eb70553939&num=10");
//			conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
//			conn.setConnectTimeout(5000);
//			if(conn.getResponseCode() == 200){
//				System.out.println("图片请求连接成功");
//				InputStream is = conn.getInputStream();
//				byte[] data = Stream2Bytes.read(is);
//				str = new String(data,"utf-8");
//				JSONObject jb = new JSONObject(str);
//				JSONArray ja = jb.getJSONArray("newslist");
//				
//				for(int i = 0;i < ja.length();i ++){
//					JSONObject jb_imgs = ja.getJSONObject(i);
//					Image image = new Image();
//					img = HttpImage.getHttpImage(jb_imgs.getString("picUrl"));
//					image.setCtime(jb_imgs.getString("ctime"));
//					image.setTitle(jb_imgs.getString("title"));
//					image.setDescription(jb_imgs.getString("description"));
//					image.setPicUrl(jb_imgs.getString("picUrl"));
//					image.setUrl(jb_imgs.getString("url"));
//					image.setPic(img);
//					al.add(image);
//				}
//			}
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
