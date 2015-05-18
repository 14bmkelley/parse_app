package com.brandonmkelley.parse_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends Activity {

	private Button submit;
	private boolean clicked;
	private Animation blink;
	private TextView blinkMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		//create listener for submit button
		submit = (Button) findViewById(R.id.login_submit);
		setSubmitListener();

		//keep track of clicking
		clicked = false;

		//set up blink animation
		blink = new AlphaAnimation(0.0f, 1.0f);
		blink.setDuration(500);
		blink.setStartOffset(0);
		blink.setRepeatMode(Animation.REVERSE);
		blink.setRepeatCount(Animation.INFINITE);
		blinkMessage = (TextView) findViewById(R.id.login_blink);

		//set up Parse
		Parse.enableLocalDatastore(this);
		Parse.initialize(this, "8DnPzMRHvMamLLizP0GIRyIRMVOFqRKpMzmp1G5d", "ZlK7n1x7Y6jnkxMRllVsWeukWk9W0CIDxAZ0ma8q");

	}

	private void setSubmitListener() {

		submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (clicked) {
					return;
				}

				//disable clicking
				clicked = true;

				//start animation
				blinkMessage.setText("Logging you in, please wait.");
				blinkMessage.startAnimation(blink);

				//get input fields
				EditText username_view = (EditText) findViewById(R.id.login_username);
				EditText password_view = (EditText) findViewById(R.id.login_password);

				InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				manager.hideSoftInputFromWindow(password_view.getWindowToken(), 0);

				//save content as strings
				String username = username_view.getText().toString().trim();
				String password = password_view.getText().toString().trim();

				ParseUser.logInInBackground(username, password, new LogInCallback() {

					@Override
					public void done(ParseUser parseUser, ParseException e) {

						if (parseUser != null) {

							//save strings to bundle for next activity
							Bundle b = new Bundle();
							b.putString("firstname", parseUser.getString("firstname"));
							b.putString("lastname", parseUser.getString("lastname"));
							b.putString("username", parseUser.getUsername());
							b.putString("email", parseUser.getEmail());

							Log.d("app", parseUser.getUsername());

							//start new activity with bundle
							Intent intent = new Intent(getBaseContext(), InternalActivity.class);
							intent.putExtras(b);

							startActivity(intent);
							blinkMessage.clearAnimation();
							blinkMessage.setText("");
							clicked = false;

						} else {

							//incorrect username or password handled
							CharSequence message = "Sorry, that username/password combination was incorrect.";
							int length = Toast.LENGTH_SHORT;
							Toast.makeText(getApplicationContext(), message, length).show();

						}

					}

				});

			}

		});

	}

}
