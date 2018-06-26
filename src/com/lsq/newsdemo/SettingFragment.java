package com.lsq.newsdemo;

import com.lsq.News.MyApplication;
import com.lsq.News.User;
import com.lsq.RegAndLogin.LoginToServer;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingFragment extends Fragment {
	private Context mContext;
	private EditText et_name,et_pass;
	private Button bt_reg,bt_login,bt_reset;
	private MyApplication myApplication;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContext = getActivity().getBaseContext();
		return inflater.inflate(R.layout.fragment_setting, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		bt_reg = (Button) getActivity().findViewById(R.id.register);
		bt_login = (Button) getActivity().findViewById(R.id.login);
		bt_reset = (Button) getActivity().findViewById(R.id.reset);
		et_name = (EditText) getActivity().findViewById(R.id.name);
		et_pass = (EditText) getActivity().findViewById(R.id.psd);
		bt_reg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent it = new Intent(mContext,com.lsq.newsdemo.RegActivity.class);
				startActivity(it);
			}
		});
		
		bt_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						System.out.println("登录线程执行......");
						boolean b = checkUser(et_name.getText().toString(),et_pass.getText().toString());						
						System.out.println("子线程中的b为:" + b);
						Message message = new Message();
						message.obj = b;
						mHandler.sendMessage(message);
					}
				}).start();
			}
		});
		
		bt_reset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				et_name.setText("");
				et_pass.setText("");
			}
		});
	}
	
	//验证用户名和密码
	public boolean checkUser(String name, String psd) {		
		System.out.println("checkUser中name和psd为:" + name + "  " + psd);
		LoginToServer loginToServer = new LoginToServer();
		String response = loginToServer.doGet(name, psd);		
		if ("true".equals(response)) {
			return true;
		} else {
			return false;
		}
	}
	
	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			myApplication = (MyApplication) getActivity().getApplication();
			System.out.println("mHandler执行,b");
			boolean b=(Boolean)msg.obj;
			System.out.println("b为:" + b);
			//登录成功后跳转到用户主页
			if(b){
				User user = new User();
				user.setUname(et_name.getText().toString());
				user.setUpass(et_pass.getText().toString());
				myApplication.userLogin(user);
				Intent intent=new Intent(getActivity(),com.lsq.newsdemo.HomeActivity.class);
				Bundle bundle=new Bundle();
				bundle.putString("name", et_name.getText().toString());
				bundle.putString("pass", et_pass.getText().toString());
				intent.putExtras(bundle);
				startActivity(intent);
//				getActivity().finish();
				Toast.makeText(getActivity(), "登录成功", 1000).show();
			}else{
				Toast.makeText(getActivity(), "登录失败", 1000).show();
			}
		};
	};
	
	

}
