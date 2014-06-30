package com.example.webviewcontent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity
{
	public String TAG = "WebViewActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		try {
			setContentView(R.layout.activity_web_view);

			// get the intent

			Intent intent = getIntent();
			Uri webUri = intent.getData();
			WebView webView = (WebView) findViewById(R.id.webview);
			WebSettings webViewSettings = webView.getSettings();
			webViewSettings.setJavaScriptEnabled(true);
			webViewSettings.setUseWideViewPort(true);
			webViewSettings.setBuiltInZoomControls(true);
			webViewSettings.setDomStorageEnabled(true);
			webViewSettings.setJavaScriptCanOpenWindowsAutomatically(true);
			webViewSettings.setPluginState(PluginState.ON);
			webView.getSettings().setAllowFileAccess(true);
			webView.setSoundEffectsEnabled(true);
			webView.setWebViewClient(new CustomWebViewClient());

			Log.i(TAG, "User Agent used in the web view " + webViewSettings.getUserAgentString());
			Log.i(TAG, "URL to be loaded " + webUri.toString());
			webView.loadUrl(webUri.toString());
		}
		catch (Exception e) {
			Log.e(TAG, "error while loading the url " + e.getMessage());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.web_view, menu);
		return true;
	}

	class CustomWebViewClient extends WebViewClient
	{
		@Override
		public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
		{
			// TODO Auto-generated method stub
			super.onReceivedError(view, errorCode, description, failingUrl);
			Log.e(TAG, "error while loading the url " + description);
		}

		@Override
		public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error)
		{
			// TODO Auto-generated method stub
			super.onReceivedSslError(view, handler, error);
			Log.e(TAG, "error while loading the url " + error.toString());
		}

	}
}
