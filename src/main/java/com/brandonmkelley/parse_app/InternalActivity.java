package com.brandonmkelley.parse_app;

import android.app.Activity;
import android.os.Bundle;

public class InternalActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internal);
	}

	@Override
	public void onBackPressed() {

		//TODO: change so that session times out
		super.onBackPressed();

	}

}
