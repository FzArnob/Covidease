package org.shaded.apache.http.impl;

import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.HttpRequestFactory;
import org.shaded.apache.http.MethodNotSupportedException;
import org.shaded.apache.http.RequestLine;
import org.shaded.apache.http.client.methods.HttpDelete;
import org.shaded.apache.http.client.methods.HttpGet;
import org.shaded.apache.http.client.methods.HttpHead;
import org.shaded.apache.http.client.methods.HttpOptions;
import org.shaded.apache.http.client.methods.HttpPost;
import org.shaded.apache.http.client.methods.HttpPut;
import org.shaded.apache.http.client.methods.HttpTrace;
import org.shaded.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.shaded.apache.http.message.BasicHttpRequest;

public class DefaultHttpRequestFactory implements HttpRequestFactory {
    private static final String[] RFC2616_COMMON_METHODS = {HttpGet.METHOD_NAME};
    private static final String[] RFC2616_ENTITY_ENC_METHODS;
    private static final String[] RFC2616_SPECIAL_METHODS;

    static {
        String[] strArr = new String[2];
        strArr[0] = HttpPost.METHOD_NAME;
        String[] strArr2 = strArr;
        strArr2[1] = HttpPut.METHOD_NAME;
        RFC2616_ENTITY_ENC_METHODS = strArr2;
        String[] strArr3 = new String[4];
        strArr3[0] = HttpHead.METHOD_NAME;
        String[] strArr4 = strArr3;
        strArr4[1] = HttpOptions.METHOD_NAME;
        String[] strArr5 = strArr4;
        strArr5[2] = HttpDelete.METHOD_NAME;
        String[] strArr6 = strArr5;
        strArr6[3] = HttpTrace.METHOD_NAME;
        RFC2616_SPECIAL_METHODS = strArr6;
    }

    public DefaultHttpRequestFactory() {
    }

    private static boolean isOneOf(String[] strArr, String str) {
        String[] methods = strArr;
        String method = str;
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].equalsIgnoreCase(method)) {
                return true;
            }
        }
        return false;
    }

    public HttpRequest newHttpRequest(RequestLine requestLine) throws MethodNotSupportedException {
        Throwable th;
        StringBuffer stringBuffer;
        HttpRequest httpRequest;
        HttpRequest httpRequest2;
        HttpRequest httpRequest3;
        Throwable th2;
        RequestLine requestline = requestLine;
        if (requestline == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Request line may not be null");
            throw th3;
        }
        String method = requestline.getMethod();
        if (isOneOf(RFC2616_COMMON_METHODS, method)) {
            new BasicHttpRequest(requestline);
            return httpRequest3;
        } else if (isOneOf(RFC2616_ENTITY_ENC_METHODS, method)) {
            new BasicHttpEntityEnclosingRequest(requestline);
            return httpRequest2;
        } else if (isOneOf(RFC2616_SPECIAL_METHODS, method)) {
            new BasicHttpRequest(requestline);
            return httpRequest;
        } else {
            Throwable th4 = th;
            new StringBuffer();
            new MethodNotSupportedException(stringBuffer.append(method).append(" method not supported").toString());
            throw th4;
        }
    }

    public HttpRequest newHttpRequest(String str, String str2) throws MethodNotSupportedException {
        Throwable th;
        StringBuffer stringBuffer;
        HttpRequest httpRequest;
        HttpRequest httpRequest2;
        HttpRequest httpRequest3;
        String method = str;
        String uri = str2;
        if (isOneOf(RFC2616_COMMON_METHODS, method)) {
            new BasicHttpRequest(method, uri);
            return httpRequest3;
        } else if (isOneOf(RFC2616_ENTITY_ENC_METHODS, method)) {
            new BasicHttpEntityEnclosingRequest(method, uri);
            return httpRequest2;
        } else if (isOneOf(RFC2616_SPECIAL_METHODS, method)) {
            new BasicHttpRequest(method, uri);
            return httpRequest;
        } else {
            Throwable th2 = th;
            new StringBuffer();
            new MethodNotSupportedException(stringBuffer.append(method).append(" method not supported").toString());
            throw th2;
        }
    }
}
