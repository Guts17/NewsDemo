package com.lsq.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlContents {
	public static String getNewsContents(String url){
		String temp = "";
		try {
			Document doc = Jsoup.connect(url).get();
			Elements contents = doc.getElementsByAttributeValue("class", "section txt");
			for(Element e : contents){
				temp += e.text();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}
}
