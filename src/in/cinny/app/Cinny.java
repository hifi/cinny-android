package in.cinny.app;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.content.Intent;

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

    // doesn't currently work in landscape mode
    private boolean isKeyboardVisible() {
        ViewGroup rootLayout = (ViewGroup) findViewById(R.id.rootLayout);
        int heightDiff = rootLayout.getRootView().getHeight() - rootLayout.getHeight();
        int contentViewTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();

        return heightDiff <= contentViewTop;
    }

    @Override
    public void onBackPressed() {
        // if soft keyboard is visible, ignore back button to allow closing it
        if (isKeyboardVisible()) {
            return;
        }

        // go back in history if we have any
        if (web.canGoBack()) {
            web.goBack();
        } else {
            // don't kill this activity but go to home screen
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
