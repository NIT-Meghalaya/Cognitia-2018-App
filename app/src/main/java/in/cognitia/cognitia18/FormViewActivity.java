package in.cognitia.cognitia18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ActionMenuView;

public class FormViewActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_view);

        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new MyWebViewClient(this));
        webView.requestFocus(View.FOCUS_DOWN);
        webView.loadUrl(getResources().getString(R.string.form_long_url));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private class MyWebViewClient extends WebViewClient {

        private AppCompatActivity activity;

        public MyWebViewClient(AppCompatActivity activity) {
            this.activity = activity;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            String formLongUrl = getResources().getString(R.string.form_long_url);
            String formShortUrl = getResources().getString(R.string.form_short_url);

            view.loadUrl(url);

            return false;

            /*if(url.contains(formLongUrl) || url.contains(formShortUrl) ||
                    url.indexOf(formLongUrl) > -1 || url.indexOf(formShortUrl) > -1)
                return false;

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            activity.startActivity(intent);
            return true;*/
        }
    }
}
