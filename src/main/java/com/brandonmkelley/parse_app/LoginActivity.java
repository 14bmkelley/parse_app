package com.brandonmkelley.parse_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends Activity {

	private Button submit = (Button) findViewById(R.id.login_submit);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setListeners();
	}

	private void setListeners() {

		submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(this, internal.class);
				startActivity(intent);

			}

		});

	}

}
