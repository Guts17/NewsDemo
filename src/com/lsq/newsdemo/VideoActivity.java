package com.lsq.newsdemo;

import java.io.IOException;
import java.io.InputStream;
import com.lsq.utils.PlayerUtils;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class VideoActivity extends Activity {
	private WebView wv;
	private String title;
	private TextView tv_title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		String url = getIntent().getStringExtra("url");
		title = getIntent().getStringExtra("title");
		System.out.println("title::" + title);
		tv_title = (TextView) findViewById(R.id.title);
		tv_title.setText(title);
		wv = (WebView) findViewById(R.id.wv);
		WebSettings ws = wv.getSettings();
		ws.setJavaScriptEnabled(true);
		ws.setAllowFileAccess(true);
		ws.setDomStorageEnabled(true);
		ws.setBlockNetworkImage(true);
		ws.setBuiltInZoomControls(true);
		wv.setWebChromeClient(new WebChromeClient());
		wv.setWebViewClient(new WebViewClient());
		wv.setVisibility(View.VISIBLE);
		wv.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		InputStream in = null;
		try {
			in = getAssets().open("youkuplayer.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String videoID = PlayerUtils.getVideoID(url);
		String html = PlayerUtils.getVideoHTML(videoID, in);
		wv.loadData(html, "text/html", "utf-8");
		System.out.println(videoID);
	}
	@Override
	protected void onPause() {
		super.onPause();
		wv.loadUrl("javascript:pauseVideo()");
	}
}
