package com.example.webviewcontent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.webkit.MimeTypeMap;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
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
			webViewSettings.setJavaScriptCanOpenWindowsAutomatically(true);
			webViewSettings.setPluginState(PluginState.ON);
			webView.getSettings().setAllowFileAccess(true);
			webView.setSoundEffectsEnabled(true);
			webView.setWebViewClient(new CustomWebViewClient());
			//webView.setWebChromeClient(new WebChromeClient());

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
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			String extension = url.substring(url.lastIndexOf(".") + 1);
			 if(extension.equals("mp4")||extension.equals("docx")||extension.equals("pdf")){//This takes the .mp4 player to be played outside the web view application
		          Intent i = new Intent(Intent.ACTION_VIEW);
		         
		          String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
		          i.setDataAndType(Uri.parse(url),mimeType);
		          String title = getResources().getString(R.string.choose_title);
		       // Create intent to show chooser
		          Intent chooser = Intent.createChooser(i, title);

		       // Verify the intent will resolve to at least one activity
		       if (i.resolveActivity(getPackageManager()) != null) {
		           startActivity(chooser);
		       }
		          //startActivity(i); //warning no error handling will cause force close if no media player on phone.
		          return true;
		     }
		     else return false; 
		}
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
