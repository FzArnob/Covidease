package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.NanoHTTPD;
import com.google.appinventor.components.runtime.util.WebViewerUtil;

@DesignerComponent(category = ComponentCategory.VIEWS, description = "Component for viewing Web pages. The Home URL can be specified in the Designer or in the Blocks Editor. The view can be set to follow links when they are tapped, and users can fill in Web forms. Warning: This is not a full browser. For example, pressing the phone's hardware Back key will exit the app, rather than move back in the browser history.<p />You can use the WebViewer.WebViewString property to communicate between your app and Javascript code running in the Webviewer page. In the app, you get and set WebViewString.  In the WebViewer, you include Javascript that references the window.Makeroid object, using the methoods <em>getWebViewString()</em> and <em>setWebViewString(text)</em>. <p />For example, if the WebViewer opens to a page that contains the Javascript command <br /><em>document.write(\"The answer is\" + window.Makeroid.getWebViewString());</em> <br />and if you set WebView.WebVewString to \"hello\", then the web page will show <br /><em>The answer is hello</em>. <br />And if the Web page contains Javascript that executes the command <br /><em>window.Makeroid.setWebViewString(\"hello from Javascript\")</em>, <br />then the value of the WebViewString property will be <br /><em>hello from Javascript</em>.", version = 13)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.READ_EXTERNAL_STORAGE, android.permission.WRITE_EXTERNAL_STORAGE")
public final class WebViewer extends AndroidViewComponent implements DownloadListener, ActivityResultListener, OnPauseListener, OnResumeListener {
    private static final String LOG_TAG = "WebViewer";
    /* access modifiers changed from: private */
    public static int REQUEST_CODE_FILE_PICKER;
    /* access modifiers changed from: private */
    public Activity activity;
    private final Handler androidUIHandler;
    private Context context;
    private CookieManager cookieMgr = CookieManager.getInstance();
    private boolean desktopMode = false;
    private boolean followLinks = true;
    /* access modifiers changed from: private */
    public Form form;
    private boolean haveLocationPermission = false;
    private boolean havePermission = false;
    private String homeUrl;
    private boolean ignoreSslErrors = false;
    private boolean jsEnabled = true;
    private boolean loadImages = true;
    /* access modifiers changed from: private */
    public ValueCallback<Uri[]> mFilePathCallback;
    private boolean prompt = true;
    private boolean scrollbar = true;
    /* access modifiers changed from: private */
    public boolean useExternalBrowser = false;
    /* access modifiers changed from: private */
    public final WebView webview;
    WebViewInterface wvInterface;
    private boolean zoomControl = true;
    private boolean zoomEnabled = true;
    private int zoomPercent = 100;

    static /* synthetic */ ValueCallback access$302(WebViewer webViewer, ValueCallback valueCallback) {
        ValueCallback valueCallback2 = valueCallback;
        ValueCallback valueCallback3 = valueCallback2;
        webViewer.mFilePathCallback = valueCallback3;
        return valueCallback2;
    }

    static /* synthetic */ boolean access$602(WebViewer webViewer, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        webViewer.havePermission = z3;
        return z2;
    }

    static /* synthetic */ boolean access$902(WebViewer webViewer, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        webViewer.haveLocationPermission = z3;
        return z2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @android.annotation.SuppressLint({"ClickableViewAccessibility", "AddJavascriptInterface"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WebViewer(com.google.appinventor.components.runtime.ComponentContainer r10) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r3 = r0
            r4 = r1
            r3.<init>(r4)
            r3 = r0
            r4 = 1
            r3.followLinks = r4
            r3 = r0
            r4 = 1
            r3.prompt = r4
            r3 = r0
            r4 = 1
            r3.scrollbar = r4
            r3 = r0
            r4 = 0
            r3.ignoreSslErrors = r4
            r3 = r0
            r4 = 1
            r3.zoomControl = r4
            r3 = r0
            r4 = 1
            r3.zoomEnabled = r4
            r3 = r0
            r4 = 100
            r3.zoomPercent = r4
            r3 = r0
            r4 = 1
            r3.loadImages = r4
            r3 = r0
            android.webkit.CookieManager r4 = android.webkit.CookieManager.getInstance()
            r3.cookieMgr = r4
            r3 = r0
            r4 = 0
            r3.useExternalBrowser = r4
            r3 = r0
            r4 = 1
            r3.jsEnabled = r4
            r3 = r0
            r4 = 0
            r3.desktopMode = r4
            r3 = r0
            r4 = 0
            r3.havePermission = r4
            r3 = r0
            r4 = 0
            r3.haveLocationPermission = r4
            r3 = r0
            android.os.Handler r4 = new android.os.Handler
            r8 = r4
            r4 = r8
            r5 = r8
            r5.<init>()
            r3.androidUIHandler = r4
            r3 = r0
            r4 = r1
            android.app.Activity r4 = r4.$context()
            r3.context = r4
            r3 = r0
            r4 = r1
            android.app.Activity r4 = r4.$context()
            r3.activity = r4
            r3 = r0
            r4 = r1
            com.google.appinventor.components.runtime.Form r4 = r4.$form()
            r3.form = r4
            r3 = r0
            com.google.appinventor.components.runtime.Form r3 = r3.form
            r4 = r0
            r3.registerForOnPause(r4)
            r3 = r0
            com.google.appinventor.components.runtime.Form r3 = r3.form
            r4 = r0
            r3.registerForOnResume(r4)
            r3 = r0
            com.google.appinventor.components.runtime.Form r3 = r3.form
            r4 = r0
            int r3 = r3.registerForActivityResult(r4)
            REQUEST_CODE_FILE_PICKER = r3
            r3 = r0
            android.webkit.WebView r4 = new android.webkit.WebView
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r0
            android.content.Context r6 = r6.context
            r5.<init>(r6)
            r3.webview = r4
            r3 = r0
            r3.resetWebViewClient()
            r3 = r0
            android.webkit.WebView r3 = r3.webview
            android.webkit.WebSettings r3 = r3.getSettings()
            r8 = r3
            r3 = r8
            r4 = r8
            r2 = r4
            r4 = 1
            r3.setDomStorageEnabled(r4)
            r3 = r2
            r4 = 1
            r3.setAllowFileAccessFromFileURLs(r4)
            r3 = r2
            r4 = 1
            r3.setAllowUniversalAccessFromFileURLs(r4)
            r3 = r2
            r4 = 1
            r3.setUseWideViewPort(r4)
            r3 = r2
            r4 = 1
            r3.setLoadWithOverviewMode(r4)
            r3 = r2
            android.webkit.WebSettings$LayoutAlgorithm r4 = android.webkit.WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
            r3.setLayoutAlgorithm(r4)
            r3 = r2
            r4 = r0
            boolean r4 = r4.zoomControl
            r3.setDisplayZoomControls(r4)
            r3 = r2
            r4 = r0
            boolean r4 = r4.zoomEnabled
            r3.setBuiltInZoomControls(r4)
            r3 = r2
            r4 = r0
            int r4 = r4.zoomPercent
            r3.setTextZoom(r4)
            r3 = r2
            r4 = r0
            boolean r4 = r4.loadImages
            r3.setLoadsImagesAutomatically(r4)
            r3 = r2
            r4 = r0
            boolean r4 = r4.loadImages
            if (r4 != 0) goto L_0x016d
            r4 = 1
        L_0x00dc:
            r3.setBlockNetworkImage(r4)
            r3 = r0
            android.webkit.WebView r3 = r3.webview
            r4 = 1
            r3.setFocusable(r4)
            r3 = r0
            com.google.appinventor.components.runtime.WebViewer$WebViewInterface r4 = new com.google.appinventor.components.runtime.WebViewer$WebViewInterface
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r0
            r8 = r6
            r6 = r8
            r7 = r8
            android.webkit.WebView r7 = r7.webview
            android.content.Context r7 = r7.getContext()
            r5.<init>(r6, r7)
            r3.wvInterface = r4
            r3 = r0
            android.webkit.WebView r3 = r3.webview
            r4 = r0
            com.google.appinventor.components.runtime.WebViewer$WebViewInterface r4 = r4.wvInterface
            java.lang.String r5 = "AppInventor"
            r3.addJavascriptInterface(r4, r5)
            r3 = r0
            android.webkit.WebView r3 = r3.webview
            r4 = r0
            com.google.appinventor.components.runtime.WebViewer$WebViewInterface r4 = r4.wvInterface
            java.lang.String r5 = "Makeroid"
            r3.addJavascriptInterface(r4, r5)
            r3 = r0
            android.webkit.WebView r3 = r3.webview
            r4 = r0
            com.google.appinventor.components.runtime.WebViewer$WebViewInterface r4 = r4.wvInterface
            java.lang.String r5 = "Kodular"
            r3.addJavascriptInterface(r4, r5)
            r3 = r0
            android.webkit.WebView r3 = r3.webview
            com.google.appinventor.components.runtime.WebViewer$1 r4 = new com.google.appinventor.components.runtime.WebViewer$1
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r0
            r5.<init>(r6)
            r3.setOnTouchListener(r4)
            r3 = r0
            android.webkit.WebView r3 = r3.webview
            com.google.appinventor.components.runtime.WebViewer$MyWebChromeClient r4 = new com.google.appinventor.components.runtime.WebViewer$MyWebChromeClient
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r0
            r5.<init>(r6)
            r3.setWebChromeClient(r4)
            r3 = r0
            android.webkit.WebView r3 = r3.webview
            r4 = r0
            r3.setDownloadListener(r4)
            r3 = r1
            r4 = r0
            r3.$add(r4)
            r3 = r0
            r8 = r3
            r3 = r8
            r4 = r8
            boolean r4 = r4.jsEnabled
            r3.EnableJavaScript(r4)
            r3 = r0
            r8 = r3
            r3 = r8
            r4 = r8
            boolean r4 = r4.desktopMode
            r3.DesktopMode(r4)
            r3 = r0
            java.lang.String r4 = ""
            r3.HomeUrl(r4)
            r3 = r0
            r4 = -2
            r3.Width(r4)
            r3 = r0
            r4 = -2
            r3.Height(r4)
            return
        L_0x016d:
            r4 = 0
            goto L_0x00dc
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.WebViewer.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public final View getView() {
        return this.webview;
    }

    public final void onPause() {
        if (this.webview != null) {
            this.webview.onPause();
        }
    }

    public final void onResume() {
        if (this.webview != null) {
            this.webview.onResume();
        }
    }

    public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        String str5 = str2;
        OnDownloadStart(str, str3, str4, j);
    }

    @SimpleEvent(description = "Event for listening download links.")
    public final void OnDownloadStart(String str, String str2, String str3, long j) {
        Object[] objArr = new Object[4];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        Object[] objArr3 = objArr2;
        objArr3[2] = str3;
        Object[] objArr4 = objArr3;
        objArr4[3] = Long.valueOf(j);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnDownloadStart", objArr4);
    }

    public class MyWebViewClient extends WebViewClient {
        private /* synthetic */ WebViewer hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        public MyWebViewClient(WebViewer webViewer) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = webViewer;
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.PageLoaded();
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            WebView webView2 = webView;
            return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.shouldOverrideUrlLoading(str);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            WebView webView2 = webView;
            WebResourceRequest webResourceRequest2 = webResourceRequest;
            if (Build.VERSION.SDK_INT >= 21) {
                return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.shouldOverrideUrlLoading(webResourceRequest2.getUrl().toString());
            }
            return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.useExternalBrowser;
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            WebView webView2 = webView;
            SslError sslError2 = sslError;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.handleSslError(sslErrorHandler);
        }
    }

    /* access modifiers changed from: private */
    public boolean shouldOverrideUrlLoading(String str) {
        Intent intent;
        String str2 = str;
        if ((this.useExternalBrowser && (str2.startsWith("http:") || str2.startsWith("https:"))) || str2.startsWith("tel:") || str2.startsWith("mailto:") || str2.startsWith("file:")) {
            try {
                new Intent("android.intent.action.VIEW", Uri.parse(str2));
                this.activity.startActivity(intent);
                return true;
            } catch (Exception e) {
                int e2 = Log.e(LOG_TAG, String.valueOf(e));
            }
        }
        return !this.followLinks;
    }

    /* access modifiers changed from: private */
    public void handleSslError(SslErrorHandler sslErrorHandler) {
        SslErrorHandler sslErrorHandler2 = sslErrorHandler;
        if (this.ignoreSslErrors) {
            sslErrorHandler2.proceed();
            return;
        }
        sslErrorHandler2.cancel();
        this.form.dispatchErrorOccurredEvent(this, "WebView", ErrorMessages.ERROR_WEBVIEW_SSL_ERROR, new Object[0]);
    }

    public class MyWebChromeClient extends WebChromeClient {
        private /* synthetic */ WebViewer hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        public MyWebChromeClient(WebViewer webViewer) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = webViewer;
        }

        public void onProgressChanged(WebView webView, int i) {
            WebView webView2 = webView;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.ProgressChanged(i);
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            ConsoleMessage consoleMessage2 = consoleMessage;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.OnConsoleMessage(consoleMessage2.message(), consoleMessage2.lineNumber(), consoleMessage2.sourceId());
            return true;
        }

        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            WebView webView2 = webView;
            ValueCallback<Uri[]> valueCallback2 = valueCallback;
            WebChromeClient.FileChooserParams fileChooserParams2 = fileChooserParams;
            if (Build.VERSION.SDK_INT < 21) {
                return false;
            }
            if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.mFilePathCallback != null) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.mFilePathCallback.onReceiveValue((Object) null);
                ValueCallback access$302 = WebViewer.access$302(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, (ValueCallback) null);
            }
            ValueCallback access$3022 = WebViewer.access$302(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, valueCallback2);
            Intent createIntent = fileChooserParams2.createIntent();
            Intent intent = createIntent;
            Intent putExtra = createIntent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
            Intent type = intent.setType("*/*");
            try {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.activity.startActivityForResult(Intent.createChooser(intent, "Choose file"), WebViewer.REQUEST_CODE_FILE_PICKER);
            } catch (Exception e) {
                int e2 = Log.e(WebViewer.LOG_TAG, "No activity found to handle file chooser intent.", e);
                valueCallback2.onReceiveValue((Object) null);
            }
            return true;
        }
    }

    @SimpleEvent(description = "Event to detect that the loading progress has changed.")
    public final void ProgressChanged(int i) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "ProgressChanged", Integer.valueOf(i));
    }

    @SimpleEvent(description = "Get webpage console output")
    public final void OnConsoleMessage(String str, int i, String str2) {
        Object[] objArr = new Object[3];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = Integer.valueOf(i);
        Object[] objArr3 = objArr2;
        objArr3[2] = str2;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnConsoleMessage", objArr3);
    }

    public final void resultReturned(int i, int i2, Intent intent) {
        int i3 = i2;
        Intent intent2 = intent;
        if (i == REQUEST_CODE_FILE_PICKER && Build.VERSION.SDK_INT >= 21) {
            if (i3 != -1) {
                this.mFilePathCallback.onReceiveValue((Object) null);
                this.mFilePathCallback = null;
                return;
            }
            Uri[] parseResult = WebChromeClient.FileChooserParams.parseResult(i3, intent2);
            Uri[] uriArr = parseResult;
            if (parseResult == null) {
                ClipData clipData = intent2.getClipData();
                ClipData clipData2 = clipData;
                int itemCount = clipData.getItemCount();
                int i4 = itemCount;
                uriArr = new Uri[itemCount];
                for (int i5 = 0; i5 < i4; i5++) {
                    uriArr[i5] = clipData2.getItemAt(i5).getUri();
                }
            }
            this.mFilePathCallback.onReceiveValue(uriArr);
            this.mFilePathCallback = null;
        }
    }

    @SimpleProperty
    public final void Width(int i) {
        int i2 = i;
        if (i2 == -1) {
            i2 = -2;
        }
        super.Width(i2);
    }

    @SimpleProperty
    public final void Height(int i) {
        int i2 = i;
        if (i2 == -1) {
            i2 = -2;
        }
        super.Height(i2);
    }

    @SimpleProperty(description = "URL of the page the WebViewer should initially open to. Setting this will load the page.")
    public final String HomeUrl() {
        return this.homeUrl;
    }

    @DesignerProperty(editorType = "string")
    @SimpleProperty
    public final void HomeUrl(String str) {
        this.homeUrl = str;
        this.webview.clearHistory();
        loadUrl("HomeUrl", this.homeUrl);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "URL of the page currently viewed. This could be different from the Home URL if new pages were visited by following links.")
    public final String CurrentUrl() {
        return this.webview.getUrl() == null ? "" : this.webview.getUrl();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Title of the page currently viewed")
    public final String CurrentPageTitle() {
        return this.webview.getTitle() == null ? "" : this.webview.getTitle();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Determines whether to follow links when they are tapped in the WebViewer.  If you follow links, you can use GoBack and GoForward to navigate the browser history. ")
    public final boolean FollowLinks() {
        return this.followLinks;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty
    public final void FollowLinks(boolean z) {
        this.followLinks = z;
        resetWebViewClient();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Determine whether or not to ignore SSL errors. Set to true to ignore errors. Use this to accept self signed certificates from websites.")
    public final boolean IgnoreSslErrors() {
        return this.ignoreSslErrors;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty
    public final void IgnoreSslErrors(boolean z) {
        this.ignoreSslErrors = z;
        resetWebViewClient();
    }

    @SimpleFunction(description = "Loads the home URL page. This happens automatically when the home URL is changed.")
    public final void GoHome() {
        loadUrl("GoHome", this.homeUrl);
    }

    @SimpleFunction(description = "Go back to the previous page in the history list. Does nothing if there is no previous page.")
    public final void GoBack() {
        if (this.webview.canGoBack()) {
            this.webview.goBack();
        }
    }

    @SimpleFunction(description = "Go forward to the next page in the history list. Does nothing if there is no next page.")
    public final void GoForward() {
        if (this.webview.canGoForward()) {
            this.webview.goForward();
        }
    }

    @SimpleFunction(description = "Go forward or backward a number of steps away from the current page. Steps is negative if backward and positive if forward.")
    public final void GoBackOrForward(int i) {
        this.webview.goBackOrForward(i);
    }

    @SimpleFunction(description = "Returns true if the WebViewer can go forward in the history list.")
    public final boolean CanGoForward() {
        return this.webview.canGoForward();
    }

    @SimpleFunction(description = "Returns true if the WebViewer can go back in the history list.")
    public final boolean CanGoBack() {
        return this.webview.canGoBack();
    }

    @SimpleFunction(description = "Returns true if the WebViewer can go back or forward the number of steps in the history list.")
    public final boolean CanGoBackOrForward(int i) {
        return this.webview.canGoBackOrForward(i);
    }

    @SimpleFunction(description = "Stops the current load.")
    public final void StopLoading() {
        this.webview.stopLoading();
    }

    @SimpleFunction(description = "Reloads the current page")
    public final void Reload() {
        this.webview.reload();
    }

    @SimpleFunction(description = "Load the page at the given URL.")
    public final void GoToUrl(String str) {
        String str2 = str;
        if (str2.startsWith("file:///mnt/sdcard/AppInventor/")) {
            str2 = str2.replaceFirst("AppInventor", "Makeroid");
        }
        loadUrl("GoToUrl", str2);
    }

    private void loadUrl(String str, String str2) {
        PermissionResultHandler permissionResultHandler;
        String str3 = str;
        String str4 = str2;
        if (str4.startsWith("file:///mnt/sdcard/AppInventor/")) {
            str4 = str4.replaceFirst("AppInventor", "Makeroid");
        }
        if (this.havePermission || !MediaUtil.isExternalFileUrl(str4)) {
            this.webview.loadUrl(str4);
            return;
        }
        final String str5 = str4;
        final String str6 = str3;
        new PermissionResultHandler(this) {
            private /* synthetic */ WebViewer hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
            }

            public final void HandlePermissionResponse(String str, boolean z) {
                String str2 = str;
                if (z) {
                    boolean access$602 = WebViewer.access$602(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, true);
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.webview.loadUrl(str5);
                    return;
                }
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str6, "android.permission.READ_EXTERNAL_STORAGE");
            }
        };
        this.form.askPermission("android.permission.READ_EXTERNAL_STORAGE", permissionResultHandler);
    }

    @SimpleFunction(description = "Load HTML content using Base64-encoded data URI scheme")
    public final void LoadHtml(String str) {
        String encodeToString = Base64.encodeToString(str.getBytes(), 1);
        this.webview.loadData(encodeToString, NanoHTTPD.MIME_HTML, "base64");
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "Whether or not to give the application permission to use the Javascript geolocation API. This property is available only in the designer.", userVisible = false)
    public final void UsesLocation(boolean z) {
        Runnable runnable;
        boolean z2 = z;
        if (z2 && !this.haveLocationPermission) {
            new Runnable(this) {
                final /* synthetic */ WebViewer hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void run() {
                    PermissionResultHandler permissionResultHandler;
                    new PermissionResultHandler(this) {
                        private /* synthetic */ C11003 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                        }

                        public final void HandlePermissionResponse(String str, boolean z) {
                            String str2 = str;
                            if (z) {
                                boolean access$902 = WebViewer.access$902(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, true);
                                WebViewer.setupWebViewGeoLoc(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.webview, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.activity);
                                int d = Log.d(WebViewer.LOG_TAG, "Permission Granted");
                                return;
                            }
                            boolean access$9022 = WebViewer.access$902(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, false);
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "UsesLocation", "android.permission.ACCESS_FINE_LOCATION");
                        }
                    };
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.askPermission("android.permission.ACCESS_FINE_LOCATION", permissionResultHandler);
                }
            };
            boolean post = this.androidUIHandler.post(runnable);
        } else if (z2 && this.haveLocationPermission) {
            setupWebViewGeoLoc(this, this.webview, this.activity);
        }
    }

    @SimpleProperty(description = "If True, then prompt the user of the WebView to give permission to access the geolocation API. If False, then assume permission is granted.")
    public final boolean PromptforPermission() {
        return this.prompt;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(userVisible = true)
    public final void PromptforPermission(boolean z) {
        boolean z2 = z;
        this.prompt = z2;
    }

    @SimpleFunction(description = "Clear stored location permissions.")
    public final void ClearLocations() {
        GeolocationPermissions.getInstance().clearAll();
    }

    @SimpleFunction(description = "Clear WebView caches.")
    public final void ClearCaches() {
        this.webview.clearCache(true);
    }

    @SimpleFunction(description = "Start to clear the WebView cookies.")
    public final void ClearCookies() {
        ValueCallback valueCallback;
        if (Build.VERSION.SDK_INT >= 22) {
            int d = Log.d(LOG_TAG, "Api is bigger than 21");
            new ValueCallback<Boolean>(this) {
                private /* synthetic */ WebViewer hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final /* synthetic */ void onReceiveValue(Object obj) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.CookiesRemoved(((Boolean) obj).booleanValue());
                }
            };
            this.cookieMgr.removeAllCookies(valueCallback);
            this.cookieMgr.flush();
            return;
        }
        try {
            int d2 = Log.d(LOG_TAG, "Api is smaller as 22 ");
            CookieSyncManager createInstance = CookieSyncManager.createInstance(this.context);
            CookieSyncManager cookieSyncManager = createInstance;
            createInstance.startSync();
            this.cookieMgr.removeAllCookie();
            this.cookieMgr.removeSessionCookie();
            cookieSyncManager.stopSync();
            cookieSyncManager.sync();
            CookiesRemoved(true);
            int d3 = Log.d(LOG_TAG, "Cookies successfully removed");
        } catch (Exception e) {
            CookiesRemoved(false);
            int e2 = Log.e(LOG_TAG, "Cookies NOT successfully removed");
        }
    }

    @SimpleEvent(description = "This event return true when the cookies have been successfully removed. If the cookies was successfully cleared then the next run returns false as status, if in this time no new cookies was set.")
    public final void CookiesRemoved(boolean z) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "CookiesRemoved", Boolean.valueOf(z));
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "Whether to display a scrollbar")
    public final void Scrollbar(boolean z) {
        boolean z2 = z;
        this.webview.setVerticalScrollBarEnabled(z2);
        this.webview.setHorizontalScrollBarEnabled(z2);
        this.scrollbar = z2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public final boolean Scrollbar() {
        return this.scrollbar;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Show or Hide the zoom display.")
    public final boolean ZoomDisplay() {
        return this.zoomControl;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    public final void ZoomDisplay(boolean z) {
        this.zoomControl = z;
        this.webview.getSettings().setDisplayZoomControls(this.zoomControl);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Enable or Disable pinch zooming. This effects both pinch zooming and the zoom controls.")
    public final boolean ZoomEnabled() {
        return this.zoomEnabled;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    public final void ZoomEnabled(boolean z) {
        boolean z2 = z;
        this.zoomEnabled = z2;
        this.webview.getSettings().setBuiltInZoomControls(z2);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The zoom of the page in percent %")
    public final int ZoomPercent() {
        return this.zoomPercent;
    }

    @DesignerProperty(defaultValue = "100", editorType = "integer")
    public final void ZoomPercent(int i) {
        int i2 = i;
        this.zoomPercent = i2;
        this.webview.getSettings().setTextZoom(i2);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether or not to automatically load images")
    public final boolean LoadImages() {
        return this.loadImages;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean", propertyType = "advanced")
    public final void LoadImages(boolean z) {
        boolean z2 = z;
        this.loadImages = z2;
        this.webview.getSettings().setLoadsImagesAutomatically(z2);
        this.webview.getSettings().setBlockNetworkImage(!z2);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Open a link in the webviewer page using the external browser. If true the page will be loaded in the external browser and not in the webviewer itself.")
    public final boolean UseExternalBrowser() {
        return this.useExternalBrowser;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "Open a link in the webviewer page using the external browser. If true the page will be loaded in the external browser and not in the webviewer itself.")
    public final void UseExternalBrowser(boolean z) {
        this.useExternalBrowser = z;
        resetWebViewClient();
    }

    @SimpleEvent(description = "Triggers when page finished loading")
    public final void PageLoaded() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "PageLoaded", new Object[0]);
    }

    @DesignerProperty(defaultValue = "", editorType = "string", propertyType = "advanced")
    @SimpleProperty
    public final void UserAgent(String str) {
        this.webview.getSettings().setUserAgentString(str);
    }

    @SimpleProperty(description = "Get User Agent")
    public final String UserAgent() {
        return this.webview.getSettings().getUserAgentString();
    }

    @DesignerProperty(defaultValue = "false", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty
    public final void DesktopMode(boolean z) {
        boolean z2 = z;
        this.desktopMode = z2;
        WebSettings settings = this.webview.getSettings();
        if (z2) {
            settings.setUserAgentString(settings.getUserAgentString().replace("Mobile", "eliboM").replace("Android", "diordnA"));
            return;
        }
        settings.setUserAgentString((String) null);
    }

    @SimpleProperty(description = "Get/Set Desktop mode by altering the user agent string.")
    public final boolean DesktopMode() {
        return this.desktopMode;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "Enable/Disable JavaScript. Enabled by default")
    public final void EnableJavaScript(boolean z) {
        boolean z2 = z;
        this.jsEnabled = z2;
        this.webview.getSettings().setJavaScriptEnabled(z2);
    }

    @SimpleProperty(description = "Evaluate Javascript in current page context")
    public final boolean EnableJavaScript() {
        return this.jsEnabled;
    }

    @SimpleFunction(description = "Evaluate JS in the context of the current page")
    public final void EvaluateJS(String str) {
        StringBuilder sb;
        ValueCallback valueCallback;
        String str2 = str;
        if (Build.VERSION.SDK_INT >= 19) {
            new ValueCallback<String>(this) {
                private /* synthetic */ WebViewer hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final /* synthetic */ void onReceiveValue(Object obj) {
                    String str = (String) obj;
                    if (str != null) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AfterJSEvaluated(str);
                    } else {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AfterJSEvaluated("null");
                    }
                }
            };
            this.webview.evaluateJavascript(str2, valueCallback);
            return;
        }
        WebView webView = this.webview;
        new StringBuilder("javascript:(function() {");
        webView.loadUrl(sb.append(str2).append("})()").toString());
        AfterJSEvaluated("");
    }

    @SimpleEvent(description = "Get the result of the evaluated JS")
    public final void AfterJSEvaluated(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AfterJSEvaluated", str);
    }

    private void resetWebViewClient() {
        WebViewClient webViewClient;
        new MyWebViewClient(this);
        this.webview.setWebViewClient(webViewClient);
    }

    public static void setupWebViewGeoLoc(WebViewer webViewer, WebView webView, Activity activity2) {
        WebChromeClient webChromeClient;
        WebView webView2 = webView;
        Activity activity3 = activity2;
        webView2.getSettings().setGeolocationDatabasePath(activity3.getFilesDir().getAbsolutePath());
        webView2.getSettings().setDatabaseEnabled(true);
        WebViewerUtil.initialize(activity3);
        final Activity activity4 = activity3;
        new WebChromeClient(webViewer) {

            /* renamed from: B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T  reason: collision with other field name */
            private /* synthetic */ WebViewer f548B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

            {
                this.f548B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r6;
            }

            public final void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
                AlertDialog.Builder builder;
                StringBuilder sb;
                DialogInterface.OnClickListener onClickListener;
                DialogInterface.OnClickListener onClickListener2;
                String str2 = str;
                GeolocationPermissions.Callback callback2 = callback;
                GeolocationPermissions.Callback callback3 = callback2;
                String str3 = str2;
                if (this.f548B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.PromptforPermission()) {
                    new AlertDialog.Builder(activity4);
                    AlertDialog create = builder.create();
                    AlertDialog alertDialog = create;
                    create.setTitle(WebViewerUtil.getPermissionTitleString());
                    if (str2.equals("file://")) {
                        str2 = WebViewerUtil.getPermissionApplicationString();
                    }
                    new StringBuilder();
                    alertDialog.setMessage(sb.append(str2).append(WebViewerUtil.getPermissionMessageString()).toString());
                    final GeolocationPermissions.Callback callback4 = callback3;
                    final String str4 = str3;
                    new DialogInterface.OnClickListener(this) {

                        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
                        private /* synthetic */ C11046 f549hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.f549hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
                        }

                        public final void onClick(DialogInterface dialogInterface, int i) {
                            DialogInterface dialogInterface2 = dialogInterface;
                            int i2 = i;
                            callback4.invoke(str4, true, true);
                        }
                    };
                    alertDialog.setButton(-1, WebViewerUtil.getPermissionAllowString(), onClickListener);
                    final GeolocationPermissions.Callback callback5 = callback3;
                    final String str5 = str3;
                    new DialogInterface.OnClickListener(this) {

                        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
                        private /* synthetic */ C11046 f550hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.f550hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
                        }

                        public final void onClick(DialogInterface dialogInterface, int i) {
                            DialogInterface dialogInterface2 = dialogInterface;
                            int i2 = i;
                            callback5.invoke(str5, false, true);
                        }
                    };
                    alertDialog.setButton(-2, WebViewerUtil.getPermissionDeniedString(), onClickListener2);
                    alertDialog.show();
                    return;
                }
                callback2.invoke(str2, true, true);
            }
        };
        webView2.setWebChromeClient(webChromeClient);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Gets the WebView's String, which is viewable through Javascript in the WebView as the window.AppInventor object")
    public final String WebViewString() {
        return this.wvInterface.getWebViewString();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public final void WebViewString(String str) {
        this.wvInterface.setWebViewStringFromBlocks(str);
    }

    @SimpleEvent(description = "When the JavaScript calls AppInventor.setWebViewString this event is run.")
    public final void WebViewStringChange(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "WebViewStringChange", str);
    }

    public class WebViewInterface {
        private String NKl0wOol24QsTInLTCDxIHyqeqvhGzhrtbHVkm7sQvkPq9BLF5nrPQlR8efylAFa = "";
        private Context hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
        final /* synthetic */ WebViewer f551hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        WebViewInterface(WebViewer webViewer, Context context) {
            this.f551hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = webViewer;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = context;
        }

        @JavascriptInterface
        public String getWebViewString() {
            return this.NKl0wOol24QsTInLTCDxIHyqeqvhGzhrtbHVkm7sQvkPq9BLF5nrPQlR8efylAFa;
        }

        @JavascriptInterface
        public void setWebViewString(String str) {
            Runnable runnable;
            String str2 = str;
            this.NKl0wOol24QsTInLTCDxIHyqeqvhGzhrtbHVkm7sQvkPq9BLF5nrPQlR8efylAFa = str2;
            final String str3 = str2;
            new Runnable(this) {
                private /* synthetic */ WebViewInterface hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final void run() {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.f551hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.WebViewStringChange(str3);
                }
            };
            this.f551hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.runOnUiThread(runnable);
        }

        public void setWebViewStringFromBlocks(String str) {
            String str2 = str;
            this.NKl0wOol24QsTInLTCDxIHyqeqvhGzhrtbHVkm7sQvkPq9BLF5nrPQlR8efylAFa = str2;
        }
    }
}
