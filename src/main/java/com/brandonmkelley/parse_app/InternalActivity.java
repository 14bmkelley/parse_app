package com.brandonmkelley.parse_app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class InternalActivity extends Activity {

	private static final int FIRSTNAME = 0;
	private static final int LASTNAME = 1;
	private static final int AGE = 2;
	private static final int CITY = 3;

	private ListView list;
	private ArrayList<String> rawList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internal);

		list = (ListView) findViewById(R.id.internal_list);
		rawList = new ArrayList<String>();
		displayList();
	}

	@Override
	public void onBackPressed() {

		moveTaskToBack(true);

	}

	private void displayList() {

		final Bundle b = getIntent().getExtras();

		rawList.add(FIRSTNAME, b.getString("firstName"));
		rawList.add(LASTNAME, b.getString("lastName"));
		rawList.add(AGE, b.getString("age"));
		rawList.add(CITY, b.getString("city"));



	}

}
