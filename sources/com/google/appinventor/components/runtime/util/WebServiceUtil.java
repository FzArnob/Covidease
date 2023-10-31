package com.google.appinventor.components.runtime.util;

import android.util.Log;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.shaded.apache.http.HttpHost;

public class WebServiceUtil {
    private static HttpClient B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = null;
    private static final WebServiceUtil hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private static Object oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS;

    static {
        WebServiceUtil webServiceUtil;
        Object obj;
        new WebServiceUtil();
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = webServiceUtil;
        new Object();
        oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS = obj;
    }

    private WebServiceUtil() {
    }

    /* JADX INFO: finally extract failed */
    public static WebServiceUtil getInstance() {
        SchemeRegistry schemeRegistry;
        Scheme scheme;
        Scheme scheme2;
        HttpParams httpParams;
        ClientConnectionManager clientConnectionManager;
        HttpClient httpClient;
        Object obj = oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T == null) {
                    new SchemeRegistry();
                    SchemeRegistry schemeRegistry2 = schemeRegistry;
                    SchemeRegistry schemeRegistry3 = schemeRegistry2;
                    new Scheme(HttpHost.DEFAULT_SCHEME_NAME, PlainSocketFactory.getSocketFactory(), 80);
                    Scheme register = schemeRegistry2.register(scheme);
                    new Scheme("https", SSLSocketFactory.getSocketFactory(), 443);
                    Scheme register2 = schemeRegistry3.register(scheme2);
                    new BasicHttpParams();
                    HttpParams httpParams2 = httpParams;
                    HttpParams httpParams3 = httpParams2;
                    HttpConnectionParams.setConnectionTimeout(httpParams2, 20000);
                    HttpConnectionParams.setSoTimeout(httpParams3, 20000);
                    ConnManagerParams.setMaxTotalConnections(httpParams3, 20);
                    new ThreadSafeClientConnManager(httpParams3, schemeRegistry3);
                    new DefaultHttpClient(clientConnectionManager, httpParams3);
                    B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = httpClient;
                }
                return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            } catch (Throwable th) {
                while (true) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            }
        }
    }

    public void postCommandReturningArray(String str, String str2, List<NameValuePair> list, AsyncCallbackPair<JSONArray> asyncCallbackPair) {
        AsyncCallbackPair asyncCallbackPair2;
        final AsyncCallbackPair<JSONArray> asyncCallbackPair3 = asyncCallbackPair;
        new AsyncCallbackPair<String>(this) {

            /* renamed from: B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T  reason: collision with other field name */
            private /* synthetic */ WebServiceUtil f575B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

            {
                this.f575B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r6;
            }

            public final /* synthetic */ void onSuccess(Object obj) {
                StringBuilder sb;
                Object obj2;
                try {
                    new JSONArray((String) obj);
                    asyncCallbackPair3.onSuccess(obj2);
                } catch (JSONException e) {
                    JSONException jSONException = e;
                    AsyncCallbackPair asyncCallbackPair = asyncCallbackPair3;
                    new StringBuilder();
                    asyncCallbackPair.onFailure(sb.append(jSONException.getMessage()).toString());
                }
            }

            public final void onFailure(String str) {
                asyncCallbackPair3.onFailure(str);
            }
        };
        postCommand(str, str2, list, asyncCallbackPair2);
    }

    public void postCommandReturningObject(String str, String str2, List<NameValuePair> list, AsyncCallbackPair<JSONObject> asyncCallbackPair) {
        AsyncCallbackPair asyncCallbackPair2;
        final AsyncCallbackPair<JSONObject> asyncCallbackPair3 = asyncCallbackPair;
        new AsyncCallbackPair<String>(this) {

            /* renamed from: B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T  reason: collision with other field name */
            private /* synthetic */ WebServiceUtil f576B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

            {
                this.f576B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r6;
            }

            public final /* synthetic */ void onSuccess(Object obj) {
                StringBuilder sb;
                Object obj2;
                try {
                    new JSONObject((String) obj);
                    asyncCallbackPair3.onSuccess(obj2);
                } catch (JSONException e) {
                    JSONException jSONException = e;
                    AsyncCallbackPair asyncCallbackPair = asyncCallbackPair3;
                    new StringBuilder();
                    asyncCallbackPair.onFailure(sb.append(jSONException.getMessage()).toString());
                }
            }

            public final void onFailure(String str) {
                asyncCallbackPair3.onFailure(str);
            }
        };
        postCommand(str, str2, list, asyncCallbackPair2);
    }

    public void postCommand(String str, String str2, List<NameValuePair> list, AsyncCallbackPair<String> asyncCallbackPair) {
        StringBuilder sb;
        HttpPost httpPost;
        StringBuilder sb2;
        ResponseHandler responseHandler;
        HttpEntity httpEntity;
        List<NameValuePair> list2;
        String str3 = str;
        String str4 = str2;
        List<NameValuePair> list3 = list;
        AsyncCallbackPair<String> asyncCallbackPair2 = asyncCallbackPair;
        new StringBuilder("Posting ");
        int d = Log.d("WebServiceUtil", sb.append(str4).append(" to ").append(str3).append(" with arguments ").append(list3).toString());
        if (str3 == null || str3.equals("")) {
            asyncCallbackPair2.onFailure("No service url to post command to.");
        }
        new StringBuilder();
        new HttpPost(sb2.append(str3).append("/").append(str4).toString());
        HttpPost httpPost2 = httpPost;
        if (list3 == null) {
            new ArrayList();
            list3 = list2;
        }
        try {
            new BasicResponseHandler();
            new UrlEncodedFormEntity(list3, "UTF-8");
            httpPost2.setEntity(httpEntity);
            httpPost2.setHeader("Accept", "application/json");
            asyncCallbackPair2.onSuccess((String) B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.execute(httpPost2, responseHandler));
        } catch (UnsupportedEncodingException e) {
            int w = Log.w("WebServiceUtil", e);
            asyncCallbackPair2.onFailure("Failed to encode params for web service call.");
        } catch (ClientProtocolException e2) {
            int w2 = Log.w("WebServiceUtil", e2);
            asyncCallbackPair2.onFailure("Communication with the web service encountered a protocol exception.");
        } catch (IOException e3) {
            int w3 = Log.w("WebServiceUtil", e3);
            asyncCallbackPair2.onFailure("Communication with the web service timed out.");
        }
    }
}
