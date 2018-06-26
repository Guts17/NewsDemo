package com.lsq.newsdemo;


import com.lsq.RegAndLogin.RegisterToServer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegActivity extends Activity {
	private EditText et_name,et_pass,et_repass;
	private Button bt_reg,bt_reset;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		et_name = (EditText) findViewById(R.id.name);
		et_pass = (EditText) findViewById(R.id.psd);
		et_repass = (EditText) findViewById(R.id.repsd);
		bt_reg = (Button) findViewById(R.id.register);
		bt_reset = (Button) findViewById(R.id.reset);
		
		bt_reg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String checkResult = checkInfo();
				if (checkResult != null) {
					Builder builder = new AlertDialog.Builder(
							RegActivity.this);
					builder.setTitle("出错提示");
					builder.setMessage(checkResult);
					builder.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									et_pass.setText("");
									et_repass.setText("");
								}
							});
					builder.create().show();
				} else {
					new Thread() {
						public void run() {
							boolean b = false;
							RegisterToServer registerToServer = new RegisterToServer();
							String response = registerToServer.doGet(et_name.getText().toString(), et_pass.getText().toString());
							if ("true".equals(response)) {
								b = true;
							} else {
								b = false;
							}
							Message message = new Message();
							message.obj = b;
							mHandler.sendMessage(message);
						}
					}.start();
				}
			}
		});
		
		bt_reset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				et_name.setText("");
				et_pass.setText("");
				et_repass.setText("");
			}
		});
	}
	
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg) {
			boolean b = (Boolean) msg.obj;
			if (b) {
//				Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
//				startActivity(intent);
				Toast.makeText(RegActivity.this, "注册成功",1000).show();
				finish();
			} else {
				Toast.makeText(RegActivity.this, "注册失败",1000).show();
			}
		};
	};
	
	public String checkInfo() {
		if (et_name.getText().toString() == null
				|| et_name.getText().toString().equals("")) {
			return "用户名不能为空";
		}
		if (et_pass.getText().toString().trim().length() < 3
				|| et_pass.getText().toString().trim().length() > 15) {
			return "密码位数应该3~15之间";
		}
		if (!et_repass.getText().toString().equals(et_repass.getText().toString())) {
			return "两次输入的密码不一致";
		}
		return null;
	}
}
