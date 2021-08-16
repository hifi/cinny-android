package in.cinny.app;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.view.View;

public class Cinny extends Activity {
    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            return;
        }

        web = (WebView)findViewById(R.id.webview);

        // FIXME: need a custom class to handle external URIs
        web.setWebViewClient(new WebViewClient());

        // disable overscroll rubber bands on edges
        web.setOverScrollMode(WebView.OVER_SCROLL_NEVER);

        // enable all goodies the app may need
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);

        // FIXME: page cache disabled to force loading new (live) version when the app restarts
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        // FIXME: use local resources for the app
        web.loadUrl("https://app.cinny.in/");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
	super.onSaveInstanceState(outState);
	web.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
	super.onRestoreInstanceState(savedInstanceState);
	web.restoreState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        // FIXME: detect if no keyboard is visible and pass the back to webview?
    }
}
