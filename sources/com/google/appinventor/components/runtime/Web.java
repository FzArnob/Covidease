package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.HtmlEntities;
import com.google.appinventor.components.runtime.collect.Lists;
import com.google.appinventor.components.runtime.collect.Maps;
import com.google.appinventor.components.runtime.errors.IllegalArgumentError;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.errors.RequestTimeoutException;
import com.google.appinventor.components.runtime.repackaged.org.json.XML;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.GingerbreadUtil;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.NanoHTTPD;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.shaded.apache.http.client.methods.HttpDelete;
import org.shaded.apache.http.client.methods.HttpGet;
import org.shaded.apache.http.client.methods.HttpPost;
import org.shaded.apache.http.client.methods.HttpPut;

@DesignerComponent(category = ComponentCategory.CONNECTIVITY, description = "Non-visible component that provides functions for HTTP GET, POST, PUT, and DELETE requests.", iconName = "images/web.png", nonVisible = true, version = 6)
@UsesLibraries(libraries = "json.jar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET,android.permission.WRITE_EXTERNAL_STORAGE,android.permission.READ_EXTERNAL_STORAGE")
public class Web extends AndroidNonvisibleComponent implements Component {
    private static final String LOG_TAG = "Web";
    private static final Map<String, String> mimeTypeToExtension;
    private final Activity activity;
    /* access modifiers changed from: private */
    public boolean allowCookies;
    /* access modifiers changed from: private */
    public final CookieHandler cookieHandler;
    /* access modifiers changed from: private */
    public YailList requestHeaders;
    /* access modifiers changed from: private */
    public String responseFileName;
    /* access modifiers changed from: private */
    public boolean saveResponse;
    /* access modifiers changed from: private */
    public int timeout;
    /* access modifiers changed from: private */
    public String urlString = "";

    /* renamed from: com.google.appinventor.components.runtime.Web$b */
    static class C1095b extends Exception {
        final int fAlnFrzRyM2sCUVmNXgAumx383He1buRJXamXMT9T0hNFh1WxfF7xQU1M3W9TIGM;
        final int jsy7Kz77rNyAY3JWsGLNrUoUOR8HajCK4Ef1UopjrJy4hdX7ElFJvGzBTGAF3HB;

        C1095b(int i, int i2) {
            this.fAlnFrzRyM2sCUVmNXgAumx383He1buRJXamXMT9T0hNFh1WxfF7xQU1M3W9TIGM = i;
            this.jsy7Kz77rNyAY3JWsGLNrUoUOR8HajCK4Ef1UopjrJy4hdX7ElFJvGzBTGAF3HB = i2;
        }
    }

    /* renamed from: com.google.appinventor.components.runtime.Web$a */
    static class C1094a extends Exception {
        final int fAlnFrzRyM2sCUVmNXgAumx383He1buRJXamXMT9T0hNFh1WxfF7xQU1M3W9TIGM;
        final int jsy7Kz77rNyAY3JWsGLNrUoUOR8HajCK4Ef1UopjrJy4hdX7ElFJvGzBTGAF3HB;

        C1094a(int i, int i2) {
            this.fAlnFrzRyM2sCUVmNXgAumx383He1buRJXamXMT9T0hNFh1WxfF7xQU1M3W9TIGM = i;
            this.jsy7Kz77rNyAY3JWsGLNrUoUOR8HajCK4Ef1UopjrJy4hdX7ElFJvGzBTGAF3HB = i2;
        }
    }

    static class CapturedProperties {
        final boolean allowCookies;
        final Map<String, List<String>> cookies;
        final Map<String, List<String>> requestHeaders;
        final String responseFileName;
        final boolean saveResponse;
        final int timeout;
        final URL url;
        final String urlString;

        CapturedProperties(Web web) throws MalformedURLException, C1095b {
            URL url2;
            Web web2 = web;
            this.urlString = web2.urlString;
            new URL(this.urlString);
            this.url = url2;
            this.allowCookies = web2.allowCookies;
            this.saveResponse = web2.saveResponse;
            this.responseFileName = web2.responseFileName;
            this.timeout = web2.timeout;
            this.requestHeaders = Web.processRequestHeaders(web2.requestHeaders);
            Map<String, List<String>> map = null;
            if (this.allowCookies && web2.cookieHandler != null) {
                try {
                    map = web2.cookieHandler.get(this.url.toURI(), this.requestHeaders);
                } catch (Exception | URISyntaxException e) {
                }
            }
            this.cookies = map;
        }
    }

    static {
        HashMap newHashMap = Maps.newHashMap();
        mimeTypeToExtension = newHashMap;
        Object put = newHashMap.put("application/pdf", "pdf");
        String put2 = mimeTypeToExtension.put("application/zip", "zip");
        String put3 = mimeTypeToExtension.put("audio/mpeg", "mpeg");
        String put4 = mimeTypeToExtension.put("audio/mp3", "mp3");
        String put5 = mimeTypeToExtension.put("audio/mp4", "mp4");
        String put6 = mimeTypeToExtension.put("image/gif", "gif");
        String put7 = mimeTypeToExtension.put("image/jpeg", "jpg");
        String put8 = mimeTypeToExtension.put("image/png", "png");
        String put9 = mimeTypeToExtension.put("image/tiff", "tiff");
        String put10 = mimeTypeToExtension.put("text/plain", "txt");
        String put11 = mimeTypeToExtension.put(NanoHTTPD.MIME_HTML, "html");
        String put12 = mimeTypeToExtension.put(NanoHTTPD.MIME_XML, "xml");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Web(com.google.appinventor.components.runtime.ComponentContainer r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            java.lang.String r3 = ""
            r2.urlString = r3
            r2 = r0
            com.google.appinventor.components.runtime.util.YailList r3 = new com.google.appinventor.components.runtime.util.YailList
            r5 = r3
            r3 = r5
            r4 = r5
            r4.<init>()
            r2.requestHeaders = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.responseFileName = r3
            r2 = r0
            r3 = 0
            r2.timeout = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.activity = r3
            r2 = r0
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 9
            if (r3 < r4) goto L_0x003c
            java.net.CookieHandler r3 = com.google.appinventor.components.runtime.util.GingerbreadUtil.newCookieManager()
        L_0x0039:
            r2.cookieHandler = r3
            return
        L_0x003c:
            r3 = 0
            goto L_0x0039
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Web.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected Web() {
        super((Form) null);
        YailList yailList;
        new YailList();
        this.requestHeaders = yailList;
        this.responseFileName = "";
        this.timeout = 0;
        this.activity = null;
        this.cookieHandler = null;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The URL for the web request.")
    public String Url() {
        return this.urlString;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public void Url(String str) {
        String str2 = str;
        this.urlString = str2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The request headers, as a list of two-element sublists. The first element of each sublist represents the request header field name. The second element of each sublist represents the request header field values, either a single value or a list containing multiple values.")
    public YailList RequestHeaders() {
        return this.requestHeaders;
    }

    @SimpleProperty
    public void RequestHeaders(YailList yailList) {
        YailList yailList2 = yailList;
        try {
            Map<String, List<String>> processRequestHeaders = processRequestHeaders(yailList2);
            this.requestHeaders = yailList2;
        } catch (C1095b e) {
            C1095b bVar = e;
            this.form.dispatchErrorOccurredEvent(this, "RequestHeaders", bVar.fAlnFrzRyM2sCUVmNXgAumx383He1buRJXamXMT9T0hNFh1WxfF7xQU1M3W9TIGM, Integer.valueOf(bVar.jsy7Kz77rNyAY3JWsGLNrUoUOR8HajCK4Ef1UopjrJy4hdX7ElFJvGzBTGAF3HB));
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the cookies from a response should be saved and used in subsequent requests. Cookies are only supported on Android version 2.3 or greater.")
    public boolean AllowCookies() {
        return this.allowCookies;
    }

    @DesignerProperty(defaultValue = "false", editorType = "boolean")
    @SimpleProperty
    public void AllowCookies(boolean z) {
        boolean z2 = z;
        this.allowCookies = z2;
        if (z2 && this.cookieHandler == null) {
            this.form.dispatchErrorOccurredEvent(this, "AllowCookies", 4, new Object[0]);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the response should be saved in a file.")
    public boolean SaveResponse() {
        return this.saveResponse;
    }

    @DesignerProperty(defaultValue = "false", editorType = "boolean")
    @SimpleProperty
    public void SaveResponse(boolean z) {
        boolean z2 = z;
        this.saveResponse = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The name of the file where the response should be saved. If SaveResponse is true and ResponseFileName is empty, then a new file name will be generated.")
    public String ResponseFileName() {
        return this.responseFileName;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public void ResponseFileName(String str) {
        String str2 = str;
        this.responseFileName = str2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The number of milliseconds that a web request will wait for a response before giving up. If set to 0, then there is no time limit on how long the request will wait.")
    public int Timeout() {
        return this.timeout;
    }

    @DesignerProperty(defaultValue = "0", editorType = "non_negative_integer")
    @SimpleProperty
    public void Timeout(int i) {
        Throwable th;
        int i2 = i;
        if (i2 < 0) {
            Throwable th2 = th;
            new IllegalArgumentError("Web Timeout must be a non-negative integer.");
            throw th2;
        }
        this.timeout = i2;
    }

    @SimpleFunction(description = "Clears all cookies for this Web component.")
    public void ClearCookies() {
        if (this.cookieHandler != null) {
            boolean clearCookies = GingerbreadUtil.clearCookies(this.cookieHandler);
        } else {
            this.form.dispatchErrorOccurredEvent(this, "ClearCookies", 4, new Object[0]);
        }
    }

    @SimpleFunction
    public void Get() {
        Runnable runnable;
        CapturedProperties capturePropertyValues = capturePropertyValues("Get");
        CapturedProperties capturedProperties = capturePropertyValues;
        if (capturePropertyValues != null) {
            final CapturedProperties capturedProperties2 = capturedProperties;
            new Runnable(this) {

                /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
                private /* synthetic */ Web f539hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.f539hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final void run() {
                    try {
                        this.f539hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.performRequest(capturedProperties2, (byte[]) null, (String) null, HttpGet.METHOD_NAME);
                    } catch (PermissionException e) {
                        this.f539hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.f539hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Get", e);
                    } catch (FileUtil.FileException e2) {
                        this.f539hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchErrorOccurredEvent(this.f539hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Get", e2.getErrorMessageNumber(), new Object[0]);
                    } catch (RequestTimeoutException e3) {
                        this.f539hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchErrorOccurredEvent(this.f539hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Get", ErrorMessages.ERROR_WEB_REQUEST_TIMED_OUT, capturedProperties2.urlString);
                    } catch (Exception e4) {
                        int e5 = Log.e(Web.LOG_TAG, "ERROR_UNABLE_TO_GET", e4);
                        this.f539hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchErrorOccurredEvent(this.f539hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Get", ErrorMessages.ERROR_WEB_UNABLE_TO_GET, capturedProperties2.urlString);
                    }
                }
            };
            AsynchUtil.runAsynchronously(runnable);
        }
    }

    @SimpleFunction(description = "Performs an HTTP POST request using the Url property and the specified text.<br>The characters of the text are encoded using UTF-8 encoding.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The responseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
    public void PostText(String str) {
        requestTextImpl(str, "UTF-8", "PostText", HttpPost.METHOD_NAME);
    }

    @SimpleFunction(description = "Performs an HTTP POST request using the Url property and the specified text.<br>The characters of the text are encoded using the given encoding.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The ResponseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
    public void PostTextWithEncoding(String str, String str2) {
        requestTextImpl(str, str2, "PostTextWithEncoding", HttpPost.METHOD_NAME);
    }

    @SimpleFunction(description = "Performs an HTTP POST request using the Url property and data from the specified file.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The ResponseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
    public void PostFile(String str) {
        Runnable runnable;
        String str2 = str;
        CapturedProperties capturePropertyValues = capturePropertyValues("PostFile");
        CapturedProperties capturedProperties = capturePropertyValues;
        if (capturePropertyValues != null) {
            final CapturedProperties capturedProperties2 = capturedProperties;
            final String str3 = str2;
            new Runnable(this) {

                /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
                private /* synthetic */ Web f540hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.f540hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
                }

                public final void run() {
                    try {
                        this.f540hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.performRequest(capturedProperties2, (byte[]) null, str3, HttpPost.METHOD_NAME);
                    } catch (FileUtil.FileException e) {
                        this.f540hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchErrorOccurredEvent(this.f540hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "PostFile", e.getErrorMessageNumber(), new Object[0]);
                    } catch (RequestTimeoutException e2) {
                        this.f540hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchErrorOccurredEvent(this.f540hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "PostFile", ErrorMessages.ERROR_WEB_REQUEST_TIMED_OUT, capturedProperties2.urlString);
                    } catch (Exception e3) {
                        int e4 = Log.e(Web.LOG_TAG, String.valueOf(e3));
                        Form form = this.f540hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form;
                        Web web = this.f540hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                        Object[] objArr = new Object[2];
                        objArr[0] = str3;
                        Object[] objArr2 = objArr;
                        objArr2[1] = capturedProperties2.urlString;
                        form.dispatchErrorOccurredEvent(web, "PostFile", ErrorMessages.ERROR_WEB_UNABLE_TO_POST_OR_PUT_FILE, objArr2);
                    }
                }
            };
            AsynchUtil.runAsynchronously(runnable);
        }
    }

    @SimpleFunction(description = "Performs an HTTP PUT request using the Url property and the specified text.<br>The characters of the text are encoded using UTF-8 encoding.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The responseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
    public void PutText(String str) {
        requestTextImpl(str, "UTF-8", "PutText", HttpPut.METHOD_NAME);
    }

    @SimpleFunction(description = "Performs an HTTP PUT request using the Url property and the specified text.<br>The characters of the text are encoded using the given encoding.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The ResponseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
    public void PutTextWithEncoding(String str, String str2) {
        requestTextImpl(str, str2, "PutTextWithEncoding", HttpPut.METHOD_NAME);
    }

    @SimpleFunction(description = "Performs an HTTP PUT request using the Url property and data from the specified file.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The ResponseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
    public void PutFile(String str) {
        Runnable runnable;
        String str2 = str;
        CapturedProperties capturePropertyValues = capturePropertyValues("PutFile");
        CapturedProperties capturedProperties = capturePropertyValues;
        if (capturePropertyValues != null) {
            final CapturedProperties capturedProperties2 = capturedProperties;
            final String str3 = str2;
            new Runnable(this) {

                /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
                private /* synthetic */ Web f541hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.f541hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
                }

                public final void run() {
                    try {
                        this.f541hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.performRequest(capturedProperties2, (byte[]) null, str3, HttpPut.METHOD_NAME);
                    } catch (FileUtil.FileException e) {
                        this.f541hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchErrorOccurredEvent(this.f541hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "PutFile", e.getErrorMessageNumber(), new Object[0]);
                    } catch (RequestTimeoutException e2) {
                        this.f541hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchErrorOccurredEvent(this.f541hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "PutFile", ErrorMessages.ERROR_WEB_REQUEST_TIMED_OUT, capturedProperties2.urlString);
                    } catch (Exception e3) {
                        int e4 = Log.e(Web.LOG_TAG, String.valueOf(e3));
                        Form form = this.f541hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form;
                        Web web = this.f541hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                        Object[] objArr = new Object[2];
                        objArr[0] = str3;
                        Object[] objArr2 = objArr;
                        objArr2[1] = capturedProperties2.urlString;
                        form.dispatchErrorOccurredEvent(web, "PutFile", ErrorMessages.ERROR_WEB_UNABLE_TO_POST_OR_PUT_FILE, objArr2);
                    }
                }
            };
            AsynchUtil.runAsynchronously(runnable);
        }
    }

    @SimpleFunction
    public void Delete() {
        Runnable runnable;
        CapturedProperties capturePropertyValues = capturePropertyValues("Delete");
        CapturedProperties capturedProperties = capturePropertyValues;
        if (capturePropertyValues != null) {
            final CapturedProperties capturedProperties2 = capturedProperties;
            new Runnable(this) {

                /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
                private /* synthetic */ Web f542hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.f542hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final void run() {
                    try {
                        this.f542hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.performRequest(capturedProperties2, (byte[]) null, (String) null, HttpDelete.METHOD_NAME);
                    } catch (FileUtil.FileException e) {
                        this.f542hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchErrorOccurredEvent(this.f542hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Delete", e.getErrorMessageNumber(), new Object[0]);
                    } catch (RequestTimeoutException e2) {
                        this.f542hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchErrorOccurredEvent(this.f542hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Delete", ErrorMessages.ERROR_WEB_REQUEST_TIMED_OUT, capturedProperties2.urlString);
                    } catch (Exception e3) {
                        this.f542hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchErrorOccurredEvent(this.f542hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Delete", ErrorMessages.ERROR_WEB_UNABLE_TO_DELETE, capturedProperties2.urlString);
                    }
                }
            };
            AsynchUtil.runAsynchronously(runnable);
        }
    }

    private void requestTextImpl(String str, String str2, String str3, String str4) {
        Runnable runnable;
        String str5 = str;
        String str6 = str2;
        String str7 = str3;
        String str8 = str4;
        CapturedProperties capturePropertyValues = capturePropertyValues(str7);
        CapturedProperties capturedProperties = capturePropertyValues;
        if (capturePropertyValues != null) {
            final String str9 = str6;
            final String str10 = str5;
            final String str11 = str7;
            final CapturedProperties capturedProperties2 = capturedProperties;
            final String str12 = str8;
            new Runnable(this) {

                /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
                private /* synthetic */ Web f543hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.f543hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r10;
                }

                public final void run() {
                    byte[] bArr;
                    try {
                        if (str9 == null || str9.length() == 0) {
                            bArr = str10.getBytes("UTF-8");
                        } else {
                            bArr = str10.getBytes(str9);
                        }
                        try {
                            this.f543hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.performRequest(capturedProperties2, bArr, (String) null, str12);
                        } catch (PermissionException e) {
                            this.f543hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.f543hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str11, e);
                        } catch (FileUtil.FileException e2) {
                            this.f543hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchErrorOccurredEvent(this.f543hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str11, e2.getErrorMessageNumber(), new Object[0]);
                        } catch (RequestTimeoutException e3) {
                            this.f543hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchErrorOccurredEvent(this.f543hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str11, ErrorMessages.ERROR_WEB_REQUEST_TIMED_OUT, capturedProperties2.urlString);
                        } catch (Exception e4) {
                            Form form = this.f543hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form;
                            Web web = this.f543hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                            String str = str11;
                            Object[] objArr = new Object[2];
                            objArr[0] = str10;
                            Object[] objArr2 = objArr;
                            objArr2[1] = capturedProperties2.urlString;
                            form.dispatchErrorOccurredEvent(web, str, ErrorMessages.ERROR_WEB_UNABLE_TO_POST_OR_PUT, objArr2);
                        }
                    } catch (UnsupportedEncodingException e5) {
                        this.f543hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchErrorOccurredEvent(this.f543hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str11, ErrorMessages.ERROR_WEB_UNSUPPORTED_ENCODING, str9);
                    }
                }
            };
            AsynchUtil.runAsynchronously(runnable);
        }
    }

    @SimpleEvent
    public void GotText(String str, int i, String str2, String str3) {
        Object[] objArr = new Object[4];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = Integer.valueOf(i);
        Object[] objArr3 = objArr2;
        objArr3[2] = str2;
        Object[] objArr4 = objArr3;
        objArr4[3] = str3;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotText", objArr4);
    }

    @SimpleEvent
    public void GotFile(String str, int i, String str2, String str3) {
        Object[] objArr = new Object[4];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = Integer.valueOf(i);
        Object[] objArr3 = objArr2;
        objArr3[2] = str2;
        Object[] objArr4 = objArr3;
        objArr4[3] = str3;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotFile", objArr4);
    }

    @SimpleEvent
    public void TimedOut(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "TimedOut", str);
    }

    @SimpleFunction
    public String BuildRequestData(YailList yailList) {
        try {
            return buildRequestData(yailList);
        } catch (C1094a e) {
            C1094a aVar = e;
            this.form.dispatchErrorOccurredEvent(this, "BuildRequestData", aVar.fAlnFrzRyM2sCUVmNXgAumx383He1buRJXamXMT9T0hNFh1WxfF7xQU1M3W9TIGM, Integer.valueOf(aVar.jsy7Kz77rNyAY3JWsGLNrUoUOR8HajCK4Ef1UopjrJy4hdX7ElFJvGzBTGAF3HB));
            return "";
        }
    }

    /* access modifiers changed from: package-private */
    public String buildRequestData(YailList yailList) throws C1094a {
        StringBuilder sb;
        Throwable th;
        Throwable th2;
        YailList yailList2 = yailList;
        new StringBuilder();
        StringBuilder sb2 = sb;
        String str = "";
        int i = 0;
        while (i < yailList2.size()) {
            Object object = yailList2.getObject(i);
            Object obj = object;
            if (object instanceof YailList) {
                YailList yailList3 = (YailList) obj;
                YailList yailList4 = yailList3;
                if (yailList3.size() == 2) {
                    StringBuilder append = sb2.append(str).append(UriEncode(yailList4.getObject(0).toString())).append('=').append(UriEncode(yailList4.getObject(1).toString()));
                    str = "&";
                    i++;
                } else {
                    Throwable th3 = th2;
                    new C1094a(ErrorMessages.ERROR_WEB_BUILD_REQUEST_DATA_NOT_TWO_ELEMENTS, i + 1);
                    throw th3;
                }
            } else {
                Throwable th4 = th;
                new C1094a(ErrorMessages.ERROR_WEB_BUILD_REQUEST_DATA_NOT_LIST, i + 1);
                throw th4;
            }
        }
        return sb2.toString();
    }

    @SimpleFunction
    public String UriEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            int e2 = Log.e(LOG_TAG, "UTF-8 is unsupported?", e);
            return "";
        }
    }

    @SimpleFunction
    public String UriDecode(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            int e2 = Log.e(LOG_TAG, "UTF-8 is unsupported?", e);
            return "";
        }
    }

    @SimpleFunction
    public Object JsonTextDecode(String str) {
        String str2 = str;
        try {
            return decodeJsonText(str2);
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, "JsonTextDecode", ErrorMessages.ERROR_WEB_JSON_TEXT_DECODE_FAILED, str2);
            return "";
        }
    }

    static Object decodeJsonText(String str) throws IllegalArgumentException {
        Throwable th;
        try {
            return JsonUtil.getObjectFromJson(str);
        } catch (JSONException e) {
            Throwable th2 = th;
            new IllegalArgumentException("jsonText is not a legal JSON value");
            throw th2;
        }
    }

    @SimpleFunction(description = "Decodes the given XML string to produce a list structure.  See the App Inventor documentation on \"Other topics, notes, and details\" for information.")
    public Object XMLTextDecode(String str) {
        StringBuilder sb;
        StringBuilder sb2;
        try {
            return JsonTextDecode(XML.toJSONObject(str).toString());
        } catch (com.google.appinventor.components.runtime.repackaged.org.json.JSONException e) {
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException jSONException = e;
            new StringBuilder();
            int e2 = Log.e("Exception in XMLTextDecode", sb.append(jSONException.getMessage()).toString());
            new StringBuilder();
            this.form.dispatchErrorOccurredEvent(this, "XMLTextDecode", ErrorMessages.ERROR_WEB_JSON_TEXT_DECODE_FAILED, sb2.append(jSONException.getMessage()).toString());
            return YailList.makeEmptyList();
        }
    }

    @SimpleFunction(description = "Decodes the given HTML text value. HTML character entities such as &amp;amp;, &amp;lt;, &amp;gt;, &amp;apos;, and &amp;quot; are changed to &amp;, &lt;, &gt;, &#39;, and &quot;. Entities such as &amp;#xhhhh, and &amp;#nnnn are changed to the appropriate characters.")
    public String HtmlTextDecode(String str) {
        String str2 = str;
        try {
            return HtmlEntities.decodeHtmlText(str2);
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, "HtmlTextDecode", ErrorMessages.ERROR_WEB_HTML_TEXT_DECODE_FAILED, str2);
            return "";
        }
    }

    /* access modifiers changed from: private */
    public void performRequest(CapturedProperties capturedProperties, byte[] bArr, String str, String str2) throws RequestTimeoutException, IOException {
        Runnable runnable;
        Throwable th;
        StringBuilder sb;
        String sb2;
        Runnable runnable2;
        Runnable runnable3;
        CapturedProperties capturedProperties2 = capturedProperties;
        byte[] bArr2 = bArr;
        String str3 = str;
        HttpURLConnection openConnection = openConnection(capturedProperties2, str2);
        HttpURLConnection httpURLConnection = openConnection;
        if (openConnection != null) {
            if (bArr2 != null) {
                try {
                    writeRequestData(httpURLConnection, bArr2);
                } catch (SocketTimeoutException e) {
                    final CapturedProperties capturedProperties3 = capturedProperties2;
                    new Runnable(this) {

                        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
                        private /* synthetic */ Web f547hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.f547hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                        }

                        public final void run() {
                            this.f547hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.TimedOut(capturedProperties3.urlString);
                        }
                    };
                    this.activity.runOnUiThread(runnable);
                    Throwable th2 = th;
                    new RequestTimeoutException();
                    throw th2;
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    httpURLConnection.disconnect();
                    throw th4;
                }
            } else if (str3 != null) {
                writeRequestFile(httpURLConnection, str3);
            }
            int responseCode = httpURLConnection.getResponseCode();
            String responseType = getResponseType(httpURLConnection);
            processResponseCookies(httpURLConnection);
            if (this.saveResponse) {
                final CapturedProperties capturedProperties4 = capturedProperties2;
                final int i = responseCode;
                final String str4 = responseType;
                final String saveResponseContent = saveResponseContent(httpURLConnection, capturedProperties2.responseFileName, responseType);
                new Runnable(this) {

                    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
                    private /* synthetic */ Web f545hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                    {
                        this.f545hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r9;
                    }

                    public final void run() {
                        this.f545hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotFile(capturedProperties4.urlString, i, str4, saveResponseContent);
                    }
                };
                this.activity.runOnUiThread(runnable3);
            } else {
                try {
                    sb2 = getResponseContent(httpURLConnection);
                } catch (Exception e2) {
                    Exception exc = e2;
                    new StringBuilder();
                    sb2 = sb.append(exc.getMessage()).toString();
                }
                final CapturedProperties capturedProperties5 = capturedProperties2;
                final int i2 = responseCode;
                final String str5 = responseType;
                final String str6 = sb2;
                new Runnable(this) {

                    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
                    private /* synthetic */ Web f546hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                    {
                        this.f546hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r9;
                    }

                    public final void run() {
                        this.f546hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotText(capturedProperties5.urlString, i2, str5, str6);
                    }
                };
                this.activity.runOnUiThread(runnable2);
            }
            httpURLConnection.disconnect();
        }
    }

    private static HttpURLConnection openConnection(CapturedProperties capturedProperties, String str) throws IOException, ClassCastException, ProtocolException {
        CapturedProperties capturedProperties2 = capturedProperties;
        String str2 = str;
        HttpURLConnection httpURLConnection = (HttpURLConnection) capturedProperties2.url.openConnection();
        HttpURLConnection httpURLConnection2 = httpURLConnection;
        httpURLConnection.setConnectTimeout(capturedProperties2.timeout);
        httpURLConnection2.setReadTimeout(capturedProperties2.timeout);
        if (str2.equals(HttpPut.METHOD_NAME) || str2.equals(HttpDelete.METHOD_NAME)) {
            httpURLConnection2.setRequestMethod(str2);
        }
        for (Map.Entry next : capturedProperties2.requestHeaders.entrySet()) {
            String str3 = (String) next.getKey();
            for (String addRequestProperty : (List) next.getValue()) {
                httpURLConnection2.addRequestProperty(str3, addRequestProperty);
            }
        }
        if (capturedProperties2.cookies != null) {
            for (Map.Entry next2 : capturedProperties2.cookies.entrySet()) {
                String str4 = (String) next2.getKey();
                for (String addRequestProperty2 : (List) next2.getValue()) {
                    httpURLConnection2.addRequestProperty(str4, addRequestProperty2);
                }
            }
        }
        return httpURLConnection2;
    }

    private static void writeRequestData(HttpURLConnection httpURLConnection, byte[] bArr) throws IOException {
        BufferedOutputStream bufferedOutputStream;
        HttpURLConnection httpURLConnection2 = httpURLConnection;
        byte[] bArr2 = bArr;
        httpURLConnection2.setDoOutput(true);
        httpURLConnection2.setFixedLengthStreamingMode(bArr2.length);
        new BufferedOutputStream(httpURLConnection2.getOutputStream());
        BufferedOutputStream bufferedOutputStream2 = bufferedOutputStream;
        try {
            bufferedOutputStream2.write(bArr2, 0, bArr2.length);
            bufferedOutputStream2.flush();
            bufferedOutputStream2.close();
        } catch (Throwable th) {
            Throwable th2 = th;
            bufferedOutputStream2.close();
            throw th2;
        }
    }

    /* JADX INFO: finally extract failed */
    private void writeRequestFile(HttpURLConnection httpURLConnection, String str) throws IOException {
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;
        BufferedOutputStream bufferedOutputStream2;
        HttpURLConnection httpURLConnection2 = httpURLConnection;
        new BufferedInputStream(MediaUtil.openMedia(this.form, str));
        BufferedInputStream bufferedInputStream2 = bufferedInputStream;
        try {
            httpURLConnection2.setDoOutput(true);
            httpURLConnection2.setChunkedStreamingMode(0);
            BufferedOutputStream bufferedOutputStream3 = bufferedOutputStream;
            new BufferedOutputStream(httpURLConnection2.getOutputStream());
            bufferedOutputStream2 = bufferedOutputStream3;
            while (true) {
                int read = bufferedInputStream2.read();
                int i = read;
                if (read != -1) {
                    bufferedOutputStream2.write(i);
                } else {
                    bufferedOutputStream2.flush();
                    bufferedOutputStream2.close();
                    bufferedInputStream2.close();
                    return;
                }
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            bufferedInputStream2.close();
            throw th2;
        }
    }

    private static String getResponseType(HttpURLConnection httpURLConnection) {
        String contentType = httpURLConnection.getContentType();
        return contentType != null ? contentType : "";
    }

    private void processResponseCookies(HttpURLConnection httpURLConnection) {
        HttpURLConnection httpURLConnection2 = httpURLConnection;
        if (this.allowCookies && this.cookieHandler != null) {
            try {
                this.cookieHandler.put(httpURLConnection2.getURL().toURI(), httpURLConnection2.getHeaderFields());
            } catch (Exception | URISyntaxException e) {
            }
        }
    }

    /* JADX INFO: finally extract failed */
    private static String getResponseContent(HttpURLConnection httpURLConnection) throws IOException {
        InputStreamReader inputStreamReader;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        HttpURLConnection httpURLConnection2 = httpURLConnection;
        String contentEncoding = httpURLConnection2.getContentEncoding();
        String str = contentEncoding;
        if (contentEncoding == null) {
            str = "UTF-8";
        }
        new InputStreamReader(getConnectionStream(httpURLConnection2), str);
        InputStreamReader inputStreamReader2 = inputStreamReader;
        try {
            int contentLength = httpURLConnection2.getContentLength();
            int i = contentLength;
            if (contentLength != -1) {
                sb2 = sb3;
                new StringBuilder(i);
            } else {
                sb2 = sb;
                new StringBuilder();
            }
            StringBuilder sb4 = sb2;
            char[] cArr = new char[1024];
            while (true) {
                int read = inputStreamReader2.read(cArr);
                int i2 = read;
                if (read != -1) {
                    StringBuilder append = sb4.append(cArr, 0, i2);
                } else {
                    String sb5 = sb4.toString();
                    inputStreamReader2.close();
                    return sb5;
                }
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            inputStreamReader2.close();
            throw th2;
        }
    }

    /* JADX INFO: finally extract failed */
    private static String saveResponseContent(HttpURLConnection httpURLConnection, String str, String str2) throws IOException {
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;
        OutputStream outputStream;
        BufferedOutputStream bufferedOutputStream2;
        File createFile = createFile(str, str2);
        new BufferedInputStream(getConnectionStream(httpURLConnection), 4096);
        BufferedInputStream bufferedInputStream2 = bufferedInputStream;
        try {
            BufferedOutputStream bufferedOutputStream3 = bufferedOutputStream;
            new FileOutputStream(createFile);
            new BufferedOutputStream(outputStream, 4096);
            bufferedOutputStream2 = bufferedOutputStream3;
            while (true) {
                int read = bufferedInputStream2.read();
                int i = read;
                if (read != -1) {
                    bufferedOutputStream2.write(i);
                } else {
                    bufferedOutputStream2.close();
                    bufferedInputStream2.close();
                    return createFile.getAbsolutePath();
                }
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            bufferedInputStream2.close();
            throw th2;
        }
    }

    private static InputStream getConnectionStream(HttpURLConnection httpURLConnection) throws SocketTimeoutException {
        HttpURLConnection httpURLConnection2 = httpURLConnection;
        try {
            return httpURLConnection2.getInputStream();
        } catch (SocketTimeoutException e) {
            throw e;
        } catch (IOException e2) {
            return httpURLConnection2.getErrorStream();
        }
    }

    private static File createFile(String str, String str2) throws IOException, FileUtil.FileException {
        String str3 = str;
        String str4 = str2;
        if (!TextUtils.isEmpty(str3)) {
            return FileUtil.getExternalFile(str3);
        }
        int indexOf = str4.indexOf(59);
        int i = indexOf;
        if (indexOf != -1) {
            str4 = str4.substring(0, i);
        }
        String str5 = mimeTypeToExtension.get(str4);
        String str6 = str5;
        if (str5 == null) {
            str6 = "tmp";
        }
        return FileUtil.getDownloadFile(str6);
    }

    /* access modifiers changed from: private */
    public static Map<String, List<String>> processRequestHeaders(YailList yailList) throws C1095b {
        Throwable th;
        Throwable th2;
        YailList yailList2 = yailList;
        HashMap newHashMap = Maps.newHashMap();
        int i = 0;
        while (i < yailList2.size()) {
            Object object = yailList2.getObject(i);
            Object obj = object;
            if (object instanceof YailList) {
                YailList yailList3 = (YailList) obj;
                YailList yailList4 = yailList3;
                if (yailList3.size() == 2) {
                    String obj2 = yailList4.getObject(0).toString();
                    Object object2 = yailList4.getObject(1);
                    ArrayList newArrayList = Lists.newArrayList();
                    if (object2 instanceof YailList) {
                        YailList yailList5 = (YailList) object2;
                        for (int i2 = 0; i2 < yailList5.size(); i2++) {
                            boolean add = newArrayList.add(yailList5.getObject(i2).toString());
                        }
                    } else {
                        boolean add2 = newArrayList.add(object2.toString());
                    }
                    Object put = newHashMap.put(obj2, newArrayList);
                    i++;
                } else {
                    Throwable th3 = th2;
                    new C1095b(ErrorMessages.ERROR_WEB_REQUEST_HEADER_NOT_TWO_ELEMENTS, i + 1);
                    throw th3;
                }
            } else {
                Throwable th4 = th;
                new C1095b(ErrorMessages.ERROR_WEB_REQUEST_HEADER_NOT_LIST, i + 1);
                throw th4;
            }
        }
        return newHashMap;
    }

    private CapturedProperties capturePropertyValues(String str) {
        CapturedProperties capturedProperties;
        String str2 = str;
        try {
            CapturedProperties capturedProperties2 = capturedProperties;
            new CapturedProperties(this);
            return capturedProperties2;
        } catch (MalformedURLException e) {
            this.form.dispatchErrorOccurredEvent(this, str2, ErrorMessages.ERROR_WEB_MALFORMED_URL, this.urlString);
            return null;
        } catch (C1095b e2) {
            C1095b bVar = e2;
            this.form.dispatchErrorOccurredEvent(this, str2, bVar.fAlnFrzRyM2sCUVmNXgAumx383He1buRJXamXMT9T0hNFh1WxfF7xQU1M3W9TIGM, Integer.valueOf(bVar.jsy7Kz77rNyAY3JWsGLNrUoUOR8HajCK4Ef1UopjrJy4hdX7ElFJvGzBTGAF3HB));
            return null;
        }
    }
}
