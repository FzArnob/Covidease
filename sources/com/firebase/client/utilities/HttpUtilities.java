package com.firebase.client.utilities;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.shaded.apache.http.HttpEntity;
import org.shaded.apache.http.NameValuePair;
import org.shaded.apache.http.client.entity.UrlEncodedFormEntity;
import org.shaded.apache.http.client.methods.HttpDelete;
import org.shaded.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.shaded.apache.http.client.methods.HttpGet;
import org.shaded.apache.http.client.methods.HttpPost;
import org.shaded.apache.http.client.methods.HttpPut;
import org.shaded.apache.http.client.methods.HttpUriRequest;
import org.shaded.apache.http.message.BasicNameValuePair;

public class HttpUtilities {

    public enum HttpRequestType {
    }

    public HttpUtilities() {
    }

    public static URI buildUrl(String server, String str, Map<String, String> map) {
        Throwable th;
        Throwable th2;
        URI uri;
        URI uri2;
        URI uri3;
        StringBuilder sb;
        StringBuilder sb2;
        String path = str;
        Map<String, String> params = map;
        try {
            new URI(server);
            URI serverURI = uri;
            new URI(serverURI.getScheme(), serverURI.getAuthority(), path, (String) null, (String) null);
            URI uri4 = uri2;
            String query = null;
            if (params != null) {
                new StringBuilder();
                StringBuilder queryBuilder = sb2;
                boolean first = true;
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if (!first) {
                        StringBuilder append = queryBuilder.append("&");
                    }
                    first = false;
                    StringBuilder append2 = queryBuilder.append(URLEncoder.encode(entry.getKey(), "utf-8"));
                    StringBuilder append3 = queryBuilder.append("=");
                    StringBuilder append4 = queryBuilder.append(URLEncoder.encode(entry.getValue(), "utf-8"));
                }
                query = queryBuilder.toString();
            }
            if (query == null) {
                return uri4;
            }
            URI uri5 = uri3;
            new StringBuilder();
            new URI(sb.append(uri4.toASCIIString()).append("?").append(query).toString());
            return uri5;
        } catch (UnsupportedEncodingException e) {
            UnsupportedEncodingException e2 = e;
            Throwable th3 = th2;
            new RuntimeException("Couldn't build valid auth URI.", e2);
            throw th3;
        } catch (URISyntaxException e3) {
            URISyntaxException e4 = e3;
            Throwable th4 = th;
            new RuntimeException("Couldn't build valid auth URI.", e4);
            throw th4;
        }
    }

    private static void addMethodParams(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, Map<String, String> map) {
        List<NameValuePair> list;
        Throwable th;
        HttpEntity httpEntity;
        Object obj;
        HttpEntityEnclosingRequestBase request = httpEntityEnclosingRequestBase;
        Map<String, String> params = map;
        if (params != null) {
            new ArrayList<>();
            List<NameValuePair> postParams = list;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                new BasicNameValuePair(entry.getKey(), entry.getValue());
                boolean add = postParams.add(obj);
            }
            HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase2 = request;
            try {
                new UrlEncodedFormEntity(postParams, "utf-8");
                httpEntityEnclosingRequestBase2.setEntity(httpEntity);
            } catch (UnsupportedEncodingException e) {
                UnsupportedEncodingException e2 = e;
                Throwable th2 = th;
                new RuntimeException("Didn't find utf-8 encoding", e2);
                throw th2;
            }
        }
    }

    public static HttpUriRequest requestWithType(String str, String str2, HttpRequestType httpRequestType, Map<String, String> map, Map<String, String> map2) {
        Map<String, String> map3;
        HttpPut httpPut;
        HttpUriRequest httpUriRequest;
        HttpPost httpPost;
        HttpUriRequest httpUriRequest2;
        Throwable th;
        String server = str;
        String path = str2;
        HttpRequestType type = httpRequestType;
        Map<String, String> urlParams = map;
        Map<String, String> requestParams = map2;
        switch (type) {
            case GET:
            case DELETE:
                new HashMap(urlParams);
                urlParams = map3;
                urlParams.putAll(requestParams);
                break;
        }
        if (type == HttpRequestType.DELETE) {
            String put = urlParams.put("_method", "delete");
        }
        URI url = buildUrl(server, path, urlParams);
        switch (type) {
            case GET:
                new HttpGet(url);
                return httpUriRequest2;
            case DELETE:
                new HttpDelete(url);
                return httpUriRequest;
            case POST:
                new HttpPost(url);
                HttpPost post = httpPost;
                if (requestParams != null) {
                    addMethodParams(post, requestParams);
                }
                return post;
            case PUT:
                new HttpPut(url);
                HttpPut put2 = httpPut;
                if (requestParams != null) {
                    addMethodParams(put2, requestParams);
                }
                return put2;
            default:
                Throwable th2 = th;
                new IllegalStateException("Shouldn't reach here!");
                throw th2;
        }
    }
}
