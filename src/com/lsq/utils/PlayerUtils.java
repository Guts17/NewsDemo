package com.lsq.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PlayerUtils {

	public static String getVideoID(String videoUrl){
		int startIndex = videoUrl.indexOf("id_");
	       if(startIndex != -1){
	           startIndex = startIndex + "id_".length();
	           int endIndex = videoUrl.indexOf(".html");
	           if(endIndex == -1){
	               endIndex = videoUrl.length();
	           }
	           if(startIndex < endIndex){
	        	   videoUrl = videoUrl.substring(startIndex,endIndex);
	           }
	       }
		return videoUrl;
	}
	
	public static String getVideoHTML(String videoId,InputStream in) {
        try {
//            InputStream in = getResources().openRawResource(R.raw.players);
        	
            if (in != null) {
                InputStreamReader stream = new InputStreamReader(in, "utf-8");
                BufferedReader buffer = new BufferedReader(stream);
                String read;
                StringBuilder sb = new StringBuilder("");
 
                while ((read = buffer.readLine()) != null) {
                    sb.append(read + "\n");
                }
 
                in.close();
 
                String html = sb.toString().replace("videoID", videoId);
//                html = html.replace("[AUTO_PLAY]", String.valueOf(params.getAutoplay())).replace("[AUTO_HIDE]", String.valueOf(params.getAutohide())).replace("[REL]", String.valueOf(params.getRel())).replace("[SHOW_INFO]", String.valueOf(params.getShowinfo())).replace("[ENABLE_JS_API]", String.valueOf(params.getEnablejsapi())).replace("[DISABLE_KB]", String.valueOf(params.getDisablekb())).replace("[CC_LANG_PREF]", String.valueOf(params.getCc_lang_pref())).replace("[CONTROLS]", String.valueOf(params.getControls())).replace("[FS]", String.valueOf(params.getFs()));
                return html;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
