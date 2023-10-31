package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

@DesignerComponent(category = ComponentCategory.INTERNAL, description = "A component to create Short links with the Google API", iconName = "images/google.png", nonVisible = true, version = 1)
@UsesLibraries(libraries = "httpcore-4.3.2.jar, httpmime-4.3.4.jar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public final class GoogleShortlink extends AndroidNonvisibleComponent {
    private ConnectivityManager B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private final String HBxetIRrPp5wFSU0fYFDhqCxdFt0fBh3kY2YtziyusOgRcjzKRD45EhStHcUhuz0 = "https://www.googleapis.com/urlshortener/v1/url";
    private String KmWMzrb8pAMl652ZF8qeuTq7pG7ZDLw36UnzkrmNxV5DvooPwgtf6GDSAEiAO9jG = null;
    private String SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3 = null;
    private final Context context;
    private NetworkInfo hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GoogleShortlink(com.google.appinventor.components.runtime.ComponentContainer r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = 0
            r2.SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3 = r3
            r2 = r0
            java.lang.String r3 = "https://www.googleapis.com/urlshortener/v1/url"
            r2.HBxetIRrPp5wFSU0fYFDhqCxdFt0fBh3kY2YtziyusOgRcjzKRD45EhStHcUhuz0 = r3
            r2 = r0
            r3 = 0
            r2.KmWMzrb8pAMl652ZF8qeuTq7pG7ZDLw36UnzkrmNxV5DvooPwgtf6GDSAEiAO9jG = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            r5 = r2
            r2 = r5
            r3 = r5
            android.content.Context r3 = r3.context
            java.lang.String r4 = "connectivity"
            java.lang.Object r3 = r3.getSystemService(r4)
            android.net.ConnectivityManager r3 = (android.net.ConnectivityManager) r3
            r2.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r3
            java.lang.String r2 = "GoogleShortlink"
            java.lang.String r3 = "GoogleShortlink Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.GoogleShortlink.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Convert a link into a short link. Powerd by Google.")
    public final void UrlToShort(String str) {
        C0732b bVar;
        String str2 = str;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.getActiveNetworkInfo();
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null || !this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isConnectedOrConnecting()) {
            GotResponse(false, "There was a error. Please try again..");
            return;
        }
        new C0732b(this, (byte) 0);
        AsyncTask execute = bVar.execute(new String[]{str2});
    }

    @SimpleFunction(description = "Convert a short link from google into a normal link. Powerd by Google.")
    public final void ShortToUrl(String str) {
        C0731a aVar;
        String str2 = str;
        this.KmWMzrb8pAMl652ZF8qeuTq7pG7ZDLw36UnzkrmNxV5DvooPwgtf6GDSAEiAO9jG = str2;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.getActiveNetworkInfo();
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null || !this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isConnectedOrConnecting()) {
            GotResponse(false, "There was a error. Please try again..");
            return;
        }
        new C0731a(this, (byte) 0);
        AsyncTask execute = aVar.execute(new String[]{str2});
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "Set here your api key to use the service. How to get a api key? Read here more: \"https://goo.gl/AFks2R\"")
    public final void ApiKey(String str) {
        String str2 = str;
        this.SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3 = str2;
    }

    @SimpleEvent(description = "Event to detect when a link was converted.")
    public final void GotResponse(boolean z, String str) {
        Object[] objArr = new Object[2];
        objArr[0] = Boolean.valueOf(z);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotResponse", objArr2);
    }

    @SimpleProperty(description = "Don't use this default api key for your final app's. This default api key is only for development mode. Sponsored by NMD (Next Mobile Development).")
    public final String DefaultApiKey() {
        return "";
    }

    /* renamed from: com.google.appinventor.components.runtime.GoogleShortlink$b */
    class C0732b extends AsyncTask<String, Void, String> {
        private String AGGRF5jTQInc5pUFaFXws4RnGLG0yIcZMIkVuaCJtvs6ytQWDb3uUe5MrjxIRxbt;
        final /* synthetic */ GoogleShortlink hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        private final String xaqHe1zmXpFIxCQ2Rqyod5yEaSTeb7CYPqkTrZecvYFjGogfgX23htIKbmu7xARG;

        private C0732b(GoogleShortlink googleShortlink) {
            StringBuilder sb;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = googleShortlink;
            new StringBuilder("https://www.googleapis.com/urlshortener/v1/url?key=");
            this.xaqHe1zmXpFIxCQ2Rqyod5yEaSTeb7CYPqkTrZecvYFjGogfgX23htIKbmu7xARG = sb.append(GoogleShortlink.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)).toString();
            this.AGGRF5jTQInc5pUFaFXws4RnGLG0yIcZMIkVuaCJtvs6ytQWDb3uUe5MrjxIRxbt = null;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C0732b(GoogleShortlink googleShortlink, byte b) {
            this(googleShortlink);
            byte b2 = b;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            StringBuilder sb;
            JSONObject jSONObject;
            Runnable runnable;
            String str = (String) obj;
            if (str != null) {
                try {
                    new JSONObject(str);
                    JSONObject jSONObject2 = jSONObject;
                    String string = jSONObject2.getString("id");
                    if (jSONObject2.has("id")) {
                        final String str2 = string;
                        new Runnable(this) {
                            private /* synthetic */ C0732b hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                            {
                                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                            }

                            public final void run() {
                                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotResponse(true, str2);
                            }
                        };
                        ((Activity) GoogleShortlink.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)).runOnUiThread(runnable);
                    }
                } catch (JSONException e) {
                    new StringBuilder();
                    int d = Log.d("JSONException", sb.append(e.getMessage()).toString());
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotResponse(false, "There was a error. Please try again..");
                }
            }
        }

        /* JADX WARNING: type inference failed for: r4v32, types: [java.io.ByteArrayOutputStream] */
        /* JADX WARNING: type inference failed for: r4v33, types: [java.io.ByteArrayOutputStream] */
        /* access modifiers changed from: private */
        /* JADX WARNING: Multi-variable type inference failed */
        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String doInBackground(java.lang.String... r11) {
            /*
                r10 = this;
                r0 = r10
                r1 = r11
                r4 = r0
                r5 = r1
                r6 = 0
                r5 = r5[r6]
                r4.AGGRF5jTQInc5pUFaFXws4RnGLG0yIcZMIkVuaCJtvs6ytQWDb3uUe5MrjxIRxbt = r5
                org.apache.http.params.BasicHttpParams r4 = new org.apache.http.params.BasicHttpParams     // Catch:{ Exception -> 0x00ac }
                r9 = r4
                r4 = r9
                r5 = r9
                r5.<init>()     // Catch:{ Exception -> 0x00ac }
                r9 = r4
                r4 = r9
                r5 = r9
                r1 = r5
                r5 = 5000(0x1388, float:7.006E-42)
                org.apache.http.params.HttpConnectionParams.setConnectionTimeout(r4, r5)     // Catch:{ Exception -> 0x00ac }
                r4 = r1
                r5 = 10000(0x2710, float:1.4013E-41)
                org.apache.http.params.HttpConnectionParams.setSoTimeout(r4, r5)     // Catch:{ Exception -> 0x00ac }
                org.apache.http.impl.client.DefaultHttpClient r4 = new org.apache.http.impl.client.DefaultHttpClient     // Catch:{ Exception -> 0x00ac }
                r9 = r4
                r4 = r9
                r5 = r9
                r6 = r1
                r5.<init>(r6)     // Catch:{ Exception -> 0x00ac }
                r1 = r4
                org.apache.http.client.methods.HttpPost r4 = new org.apache.http.client.methods.HttpPost     // Catch:{ Exception -> 0x00ac }
                r9 = r4
                r4 = r9
                r5 = r9
                r6 = r0
                java.lang.String r6 = r6.xaqHe1zmXpFIxCQ2Rqyod5yEaSTeb7CYPqkTrZecvYFjGogfgX23htIKbmu7xARG     // Catch:{ Exception -> 0x00ac }
                r5.<init>(r6)     // Catch:{ Exception -> 0x00ac }
                r9 = r4
                r4 = r9
                r5 = r9
                r2 = r5
                java.lang.String r5 = "Content-type"
                java.lang.String r6 = "application/json"
                r4.setHeader(r5, r6)     // Catch:{ Exception -> 0x00ac }
                r4 = r2
                java.lang.String r5 = "Accept"
                java.lang.String r6 = "application/json"
                r4.setHeader(r5, r6)     // Catch:{ Exception -> 0x00ac }
                org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ Exception -> 0x00ac }
                r9 = r4
                r4 = r9
                r5 = r9
                r5.<init>()     // Catch:{ Exception -> 0x00ac }
                r9 = r4
                r4 = r9
                r5 = r9
                r3 = r5
                java.lang.String r5 = "longUrl"
                r6 = r0
                java.lang.String r6 = r6.AGGRF5jTQInc5pUFaFXws4RnGLG0yIcZMIkVuaCJtvs6ytQWDb3uUe5MrjxIRxbt     // Catch:{ Exception -> 0x00ac }
                org.json.JSONObject r4 = r4.put(r5, r6)     // Catch:{ Exception -> 0x00ac }
                r4 = r2
                org.apache.http.entity.StringEntity r5 = new org.apache.http.entity.StringEntity     // Catch:{ Exception -> 0x00ac }
                r9 = r5
                r5 = r9
                r6 = r9
                r7 = r3
                java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x00ac }
                java.lang.String r8 = "UTF-8"
                r6.<init>(r7, r8)     // Catch:{ Exception -> 0x00ac }
                r4.setEntity(r5)     // Catch:{ Exception -> 0x00ac }
                r4 = r1
                r5 = r2
                org.apache.http.HttpResponse r4 = r4.execute(r5)     // Catch:{ Exception -> 0x00ac }
                r9 = r4
                r4 = r9
                r5 = r9
                r1 = r5
                org.apache.http.StatusLine r4 = r4.getStatusLine()     // Catch:{ Exception -> 0x00ac }
                int r4 = r4.getStatusCode()     // Catch:{ Exception -> 0x00ac }
                r5 = 200(0xc8, float:2.8E-43)
                if (r4 != r5) goto L_0x00a9
                java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x00ac }
                r9 = r4
                r4 = r9
                r5 = r9
                r5.<init>()     // Catch:{ Exception -> 0x00ac }
                r2 = r4
                r4 = r1
                org.apache.http.HttpEntity r4 = r4.getEntity()     // Catch:{ Exception -> 0x00ac }
                r5 = r2
                r4.writeTo(r5)     // Catch:{ Exception -> 0x00ac }
                r4 = r2
                r4.close()     // Catch:{ Exception -> 0x00ac }
                r4 = r2
                java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x00ac }
                r0 = r4
            L_0x00a8:
                return r0
            L_0x00a9:
                r4 = 0
                r0 = r4
                goto L_0x00a8
            L_0x00ac:
                r4 = move-exception
                r1 = r4
                java.lang.String r4 = "Exception"
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r9 = r5
                r5 = r9
                r6 = r9
                r6.<init>()
                r6 = r1
                java.lang.String r6 = r6.getMessage()
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r5 = r5.toString()
                int r4 = android.util.Log.d(r4, r5)
                r4 = r0
                com.google.appinventor.components.runtime.GoogleShortlink r4 = r4.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                r5 = 0
                java.lang.String r6 = "There was a error. Please try again.."
                r4.GotResponse(r5, r6)
                r4 = 0
                r0 = r4
                goto L_0x00a8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.GoogleShortlink.C0732b.doInBackground(java.lang.String[]):java.lang.String");
        }
    }

    /* renamed from: com.google.appinventor.components.runtime.GoogleShortlink$a */
    class C0731a extends AsyncTask<String, Void, String> {
        private /* synthetic */ GoogleShortlink hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        private URL url;

        private C0731a(GoogleShortlink googleShortlink) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = googleShortlink;
            this.url = null;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C0731a(GoogleShortlink googleShortlink, byte b) {
            this(googleShortlink);
            byte b2 = b;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            StringBuilder sb;
            String str = (String) obj;
            if (str != null) {
                try {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotResponse(true, str);
                } catch (Exception e) {
                    new StringBuilder();
                    int d = Log.d("Exception", sb.append(e.getMessage()).toString());
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotResponse(false, "There was a error. Please try again..");
                }
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME */
        public String doInBackground(String... strArr) {
            StringBuilder sb;
            URL url2;
            try {
                new URL(strArr[0]);
                this.url = url2;
            } catch (MalformedURLException e) {
                new StringBuilder();
                int d = Log.d("MalformedURLException", sb.append(e.getMessage()).toString());
            }
            return GoogleShortlink.getFinalURL(this.url).toString();
        }
    }

    public static URL getFinalURL(URL url) {
        StringBuilder sb;
        URL url2;
        StringBuilder sb2;
        URL url3 = url;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url3.openConnection();
            HttpURLConnection httpURLConnection2 = httpURLConnection;
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection2.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            httpURLConnection2.connect();
            int responseCode = httpURLConnection2.getResponseCode();
            int i = responseCode;
            if (responseCode == 303 || i == 301 || i == 302) {
                String headerField = httpURLConnection2.getHeaderField("Location");
                String str = headerField;
                if (headerField.startsWith("/")) {
                    new StringBuilder();
                    str = sb2.append(url3.getProtocol()).append("://").append(url3.getHost()).append(str).toString();
                }
                new URL(str);
                return getFinalURL(url2);
            }
        } catch (Exception e) {
            new StringBuilder();
            int d = Log.d("Exception", sb.append(e.getMessage()).toString());
        }
        return url3;
    }
}
