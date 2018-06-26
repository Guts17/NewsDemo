package com.lsq.utils;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HtmlImages {
	public static ArrayList<String> getImages(String url){
		ArrayList<String> al = new ArrayList<String>();
		try {
			
			Document doc = Jsoup.connect(url).get();
			Element imgcontent = doc.getElementsByAttributeValue("class", "tmpcontent").first();
			String json = imgcontent.getElementsByTag("script").first().toString();
			json = json.replace("<script>", "{");
			json = json.replace("<script value=\"varimgData\">", "{");
			json = json.replace("var imgData =", "\"imgData\":");
			json = json.replace("</script>", "]}");
			json = json.replace("];", "");
			
			JSONObject jb = new JSONObject(json);
			JSONArray ja = jb.getJSONArray("imgData");
			for(int i = 0;i < ja.length();i++){
				JSONObject jb_img = ja.getJSONObject(i);
				al.add(jb_img.getString("url"));
				System.out.println("ÃèÊö" + jb_img.getString("imgcontent"));
				System.out.println("Í¼Æ¬µØÖ·" + jb_img.getString("url"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
		
	}
}
