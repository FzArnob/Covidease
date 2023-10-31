package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public final class WebViewActivity extends Activity {
    public WebViewActivity() {
    }

    static /* synthetic */ boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(WebViewActivity webViewActivity, WebView webView, String str) {
        Intent intent;
        WebView webView2 = webView;
        String str2 = str;
        WebViewActivity webViewActivity2 = webViewActivity;
        int i = Log.i("WebView", "Handling url ".concat(String.valueOf(str2)));
        Uri parse = Uri.parse(str2);
        Uri uri = parse;
        if (parse.getScheme().equals(Form.APPINVENTOR_URL_SCHEME)) {
            new Intent();
            Intent intent2 = intent;
            Intent data = intent2.setData(uri);
            webViewActivity2.setResult(-1, intent2);
            webViewActivity2.finish();
        } else {
            webView2.loadUrl(str2);
        }
        return true;
    }

    public final void onCreate(Bundle bundle) {
        WebView webView;
        WebViewClient webViewClient;
        StringBuilder sb;
        WebViewClient webViewClient2;
        super.onCreate(bundle);
        new WebView(this);
        WebView webView2 = webView;
        WebView webView3 = webView2;
        webView2.getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= 24) {
            new WebViewClient(this) {
                private /* synthetic */ WebViewActivity hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                    return WebViewActivity.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, webView, webResourceRequest.getUrl().toString());
                }
            };
            webView3.setWebViewClient(webViewClient2);
        } else {
            new WebViewClient(this) {
                private /* synthetic */ WebViewActivity hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    return WebViewActivity.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, webView, str);
                }
            };
            webView3.setWebViewClient(webViewClient);
        }
        setContentView(webView3);
        Intent intent = getIntent();
        Intent intent2 = intent;
        if (intent != null && intent2.getData() != null) {
            Uri data = intent2.getData();
            Uri uri = data;
            String scheme = data.getScheme();
            new StringBuilder("Got intent with URI: ");
            int i = Log.i("WebView", sb.append(uri).append(", scheme=").append(scheme).append(", host=").append(uri.getHost()).toString());
            webView3.loadUrl(uri.toString());
        }
    }
}
