package com.lsq.utils;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.lsq.Video.Video;

public class HtmlGetVideo {
	public static ArrayList<Video> getVideoLists(String url){
		ArrayList<Video> al = new ArrayList<Video>();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements video_lists = doc.getElementsByAttributeValue("class", "p-thumb");
			for(Element e:video_lists){
				Video video = new Video();
				video.setTitle(e.getElementsByTag("a").first().attr("title"));
				video.setVideoUrl(e.getElementsByTag("a").first().attr("href"));
				video.setPosterUrl(e.getElementsByTag("img").first().attr("quic") + ".jpg");
				video.setPoster(HttpImage.getHttpImage(e.getElementsByTag("img").first().attr("quic") + ".jpg"));
				al.add(video);
				System.out.println(video.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}
	
	
}
