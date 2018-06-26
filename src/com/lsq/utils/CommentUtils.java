package com.lsq.utils;

import java.io.InputStream;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.lsq.News.Comment;

public class CommentUtils {
	private static ArrayList<Comment> al = new ArrayList<Comment>();
	String urlAddress = "http://192.168.43.44:8280/InfoSearch/LoginServlet";//�������˵�ַ
	
	public static String insertComment(String uname,String comment){
		String str = "";
		String url = "http://192.168.43.44:8280/InfoSearch/InsertCommentServlet";
		HttpGet httpGet = new HttpGet(url + "?uname=" + uname + "&comment=" + comment);
		HttpClient hc = new DefaultHttpClient();
		try {
			HttpResponse hr = hc.execute(httpGet);
			HttpEntity he = hr.getEntity();
			InputStream is = he.getContent();
			byte[] data = Stream2Bytes.read(is);
			str = new String(data,"utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("insertComment中str:" + str);
		return str;
	}
	
	public static ArrayList<Comment> getComments(){
		String url = "http://192.168.43.44:8280/InfoSearch/GetCommentsServlet";
		String str;
		HttpGet httpGet = new HttpGet(url);
		HttpClient hc = new DefaultHttpClient();
		try {
			HttpResponse httpResponse = hc.execute(httpGet);
			HttpEntity he = httpResponse.getEntity();
			InputStream is = he.getContent();
			byte[] data = Stream2Bytes.read(is);
			str = new String(data,"utf-8");
			System.out.println("输入流:" + str);
			JSONObject jb = new JSONObject(str);
			JSONArray ja = jb.getJSONArray("comments");
			
			for(int i = 0;i < ja.length();i ++){
				JSONObject jb_comment = ja.getJSONObject(i);
				Comment comment = new Comment();
				comment.setUname(jb_comment.getString("uname"));
				comment.setComment(jb_comment.getString("comment"));
				System.out.println("用户名和评论为:" + jb_comment.getString("uname") + "  " + jb_comment.getString("comment"));
				al.add(comment);
			}
//			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
//			String response = "";
//			String readLine = null;
//			while((readLine = br.readLine()) != null){
//				response = response + readLine;
//			}
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}

	public static ArrayList<Comment> getMyComments(String uname) {
		String url = "http://192.168.43.44:8280/InfoSearch/GetMyCommentsServlet";
		String str;
		HttpGet httpGet = new HttpGet(url + "?uname=" + uname);
		HttpClient hc = new DefaultHttpClient();
		try {
			HttpResponse httpResponse = hc.execute(httpGet);
			HttpEntity he = httpResponse.getEntity();
			InputStream is = he.getContent();
			byte[] data = Stream2Bytes.read(is);
			str = new String(data,"utf-8");
			System.out.println("输入流:" + str);
			JSONObject jb = new JSONObject(str);
			JSONArray ja = jb.getJSONArray("comments");
			
			for(int i = 0;i < ja.length();i ++){
				JSONObject jb_comment = ja.getJSONObject(i);
				Comment comment = new Comment();
				comment.setUname(jb_comment.getString("uname"));
				comment.setComment(jb_comment.getString("comment"));
				System.out.println("用户名和评论为:" + jb_comment.getString("uname") + "  " + jb_comment.getString("comment"));
				al.add(comment);
			}
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}
	
	
}
