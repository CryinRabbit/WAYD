package com.settingpage;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends Activity {
	
	private Button notifibutton;
//	private Button bgbutton;
	
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification_activity);
		notifibutton = (Button)findViewById(R.id.notifibutton);
		notifibutton.setOnClickListener(listener);
	}
	
	private Button.OnClick(View notfi) {
		
		Intent intent = new Intent();
		intent.setClass(SettingActivity.this,)
	}
	
	
}
