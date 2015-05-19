package com.brandonmkelley.parse_app;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.ArrayList;

public class InternalActivity extends Activity {

	private static final int USERNAME = 0;
	private static final int AGE = 1;
	private static final int CITY = 2;

	private ListView list;
	private ArrayList<String> rawList;
	private ArrayAdapter<String> adapter;
	private Button logout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internal);

		//update listview with user info
		list = (ListView) findViewById(R.id.internal_list);
		rawList = new ArrayList<String>();
		populateList();
		adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.activity_internal_list_element, rawList);
		list.setAdapter(adapter);

		//create listener for logout button
		logout = (Button) findViewById(R.id.internal_logout);
		setLogoutListener();

	}

	@Override
	public void onBackPressed() {

		moveTaskToBack(true);

	}

	private void populateList() {

		final Bundle b = getIntent().getExtras();

		rawList.add(USERNAME, "Username: " + b.getString("username"));
		rawList.add(AGE, "Age: " + b.getInt("age"));
		rawList.add(CITY, "City: " + b.getString("city"));

	}

	private void setLogoutListener() {

		logout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				ParseUser.logOutInBackground(new LogOutCallback() {

					@Override
					public void done(ParseException e) {

						finish();

					}

				});

			}

		});

	}

}
