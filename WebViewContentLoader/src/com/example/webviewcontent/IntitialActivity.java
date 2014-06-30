package com.example.webviewcontent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class IntitialActivity extends Activity
{

	private EditText editText;
	public static final String WEB_URI = "weburi";
	public static final String URI_SCHEME = "http://";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intitial);
		editText = (EditText) findViewById(R.id.EditURLEditText);
		editText.setText(URI_SCHEME);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.intitial, menu);
		return true;
	}

	/**
	 * Control comes here when submit button is clicked
	 * 
	 */
	public void submitClicked(View view)
	{
		Intent intent = new Intent(this, WebViewActivity.class);
		Uri webURI = Uri.parse(editText.getText().toString());
		intent.setData(webURI);
		startActivity(intent);
	}
}
