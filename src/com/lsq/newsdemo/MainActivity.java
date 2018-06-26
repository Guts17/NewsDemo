package com.lsq.newsdemo;


import java.util.ArrayList;
import com.lsq.News.News;
import com.lsq.utils.NewsAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListView;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends ActionBarActivity{

	private Context mContext;
	private ArrayList<News> al;
	private ListView lv;
	
	private TextView tv_home;
	private TextView tv_hot;
	private TextView tv_zhibo;
	private TextView tv_video;
	private TextView tv_setting;
	private MainFragment fg_home,fg_zhibo;
	private SettingFragment fg_setting;
	private ImgFragment fg_hot;
	private VideoFragment fg_video;
	FragmentManager fm;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        fm = getFragmentManager();
        bindViews();
        tv_home.performClick();
    }

    private void bindViews(){
    	tv_home = (TextView) findViewById(R.id.tv_home);
    	tv_hot = (TextView) findViewById(R.id.tv_hot);
    	tv_zhibo = (TextView) findViewById(R.id.tv_zhibo);
    	tv_video = (TextView) findViewById(R.id.tv_video);
    	tv_setting = (TextView) findViewById(R.id.tv_setting);
    }
    
    
    private void setSelected(){
    	tv_home.setSelected(false);
    	tv_hot.setSelected(false);
    	tv_zhibo.setSelected(false);
    	tv_video.setSelected(false);
    	tv_setting.setSelected(false);
    }
    
    private void hideAllFragment(FragmentTransaction ft){
    	if(fg_home != null) ft.hide(fg_home);
    	if(fg_hot != null) ft.hide(fg_hot);
    	if(fg_zhibo != null) ft.hide(fg_zhibo);
    	if(fg_video != null) ft.hide(fg_video);
    	if(fg_setting != null) ft.hide(fg_setting);
    }
    
    public void onClick(View v){
    	FragmentTransaction ft = fm.beginTransaction();
    	hideAllFragment(ft);
    	switch (v.getId()) {
		case R.id.tv_home:
			setSelected();
			tv_home.setSelected(true);
			if(fg_home == null){
				fg_home = new MainFragment();
				ft.add(R.id.fl_news, fg_home);
			}else{
				ft.show(fg_home);
			}
			break;
		case R.id.tv_hot:
			setSelected();
			tv_hot.setSelected(true);
			if(fg_hot == null){
				fg_hot = new ImgFragment();
				ft.add(R.id.fl_news, fg_hot);
			}else{
				ft.show(fg_hot);
			}
			break;
		case R.id.tv_zhibo:
			setSelected();
			tv_zhibo.setSelected(true);
			if(fg_zhibo == null){
				fg_zhibo = new MainFragment();
				ft.add(R.id.fl_news, fg_zhibo);
			}else{
				ft.show(fg_zhibo);
			}
			break;
		case R.id.tv_video:
			setSelected();
			tv_video.setSelected(true);
			if(fg_video == null){
				fg_video = new VideoFragment();
				ft.add(R.id.fl_news, fg_video);
			}else{
				ft.show(fg_video);
			}
			break;
		case R.id.tv_setting:
			setSelected();
			tv_setting.setSelected(true);
			if(fg_setting == null){
				fg_setting = new SettingFragment();
				ft.add(R.id.fl_news, fg_setting);
			}else{
				ft.show(fg_setting);
			}
			break;
		}
    	ft.commit();
    }
    
//    private Handler mHandler = new Handler(){
//    	public void handleMessage(android.os.Message message) {
//    		
//    		 al = (ArrayList<News>) message.obj;
//    		NewsAdapter mAdaapter = new NewsAdapter(al, mContext);
//    		lv.setAdapter(mAdaapter);
//    	};
//    };
//	@Override
//	public void onItemClick(AdapterView<?> parent, View view, int position,
//			long id) {
//		News news = (News) parent.getItemAtPosition(position);
//		String url = news.getUrl();
//		Intent it = new Intent(MainActivity.this, com.lsq.newsdemo.ContentsActivity.class);
//		it.putExtra("url", url);
//		startActivity(it);
//		
//	}
   
	
    
}
    
    
   
	
	
	

