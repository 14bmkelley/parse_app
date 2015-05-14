package com.brandonmkelley.parse_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends Activity {

	private Button submit = (Button) findViewById(R.id.login_submit);

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		//create listener for submit button
		setListeners();

		//set up Parse
		Parse.enableLocalDatastore(this);
		Parse.initialize(this, "8DnPzMRHvMamLLizP0GIRyIRMVOFqRKpMzmp1G5d", "ZlK7n1x7Y6jnkxMRllVsWeukWk9W0CIDxAZ0ma8q");

	}

	private void setListeners() {

		submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				//get input fields
				EditText username_view = (EditText) findViewById(R.id.login_username);
				EditText password_view = (EditText) findViewById(R.id.login_password);

				//save content as strings
				String username = username_view.getText().toString();
				String password = password_view.getText().toString();

				ParseUser.logInInBackground(username, password, new LogInCallback() {

					@Override
					public void done(ParseUser parseUser, ParseException e) {

						if (e != null) {

							e.printStackTrace();

						} else if (parseUser == null) {

							//wrong credentials

						} else {

							//success!

						}

					}

				});

				/*
				//save strings to bundle for next activity
				Bundle b = new Bundle();
				b.putString("username", username);
				b.putString("password", password);

				//start new activity with bundle
				Intent intent = new Intent(getBaseContext(), InternalActivity.class);
				intent.putExtras(b);
				startActivity(intent);
				*/

			}

		});

	}

}
