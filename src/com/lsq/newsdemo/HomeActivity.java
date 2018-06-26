package com.lsq.newsdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends Activity {
	private TextView tv_state;
	private Button bt_collects,bt_comments;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		tv_state = (TextView) findViewById(R.id.state);
		bt_comments = (Button) findViewById(R.id.comments);
		Bundle bundle = getIntent().getExtras();
		String name = bundle.getString("name");
//		String pass = bundle.getString("pass");
		tv_state.setText("ÓÃ»§" + name + "ÒÑµÇÂ¼");
		
		bt_comments.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent it = new Intent(getBaseContext(),com.lsq.newsdemo.CommentsActivity.class);
				it.putExtra("flag", 2);
				startActivity(it);
			}
		});
	}
}
