package com.bumptech.glide.load.data;

import android.text.TextUtils;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.util.ContentLengthInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

public class HttpUrlFetcher implements DataFetcher<InputStream> {
    private static final HttpUrlConnectionFactory DEFAULT_CONNECTION_FACTORY;
    private static final int MAXIMUM_REDIRECTS = 5;
    private static final String TAG = "HttpUrlFetcher";
    private final HttpUrlConnectionFactory connectionFactory;
    private final GlideUrl glideUrl;
    private volatile boolean isCancelled;
    private InputStream stream;
    private HttpURLConnection urlConnection;

    interface HttpUrlConnectionFactory {
        HttpURLConnection build(URL url) throws IOException;
    }

    static {
        HttpUrlConnectionFactory httpUrlConnectionFactory;
        new DefaultHttpUrlConnectionFactory((C15101) null);
        DEFAULT_CONNECTION_FACTORY = httpUrlConnectionFactory;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public HttpUrlFetcher(GlideUrl glideUrl2) {
        this(glideUrl2, DEFAULT_CONNECTION_FACTORY);
    }

    HttpUrlFetcher(GlideUrl glideUrl2, HttpUrlConnectionFactory connectionFactory2) {
        this.glideUrl = glideUrl2;
        this.connectionFactory = connectionFactory2;
    }

    public InputStream loadData(Priority priority) throws Exception {
        Priority priority2 = priority;
        return loadDataWithRedirects(this.glideUrl.toURL(), 0, (URL) null, this.glideUrl.getHeaders());
    }

    private InputStream loadDataWithRedirects(URL url, int i, URL url2, Map<String, String> map) throws IOException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        URL redirectUrl;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        URL url3 = url;
        int redirects = i;
        URL lastUrl = url2;
        Map<String, String> headers = map;
        if (redirects >= 5) {
            Throwable th6 = th5;
            new IOException("Too many (> 5) redirects!");
            throw th6;
        }
        if (lastUrl != null) {
            try {
                if (url3.toURI().equals(lastUrl.toURI())) {
                    Throwable th7 = th4;
                    new IOException("In re-direct loop");
                    throw th7;
                }
            } catch (URISyntaxException e) {
                URISyntaxException uRISyntaxException = e;
            }
        }
        this.urlConnection = this.connectionFactory.build(url3);
        for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
            this.urlConnection.addRequestProperty(headerEntry.getKey(), headerEntry.getValue());
        }
        this.urlConnection.setConnectTimeout(2500);
        this.urlConnection.setReadTimeout(2500);
        this.urlConnection.setUseCaches(false);
        this.urlConnection.setDoInput(true);
        this.urlConnection.connect();
        if (this.isCancelled) {
            return null;
        }
        int statusCode = this.urlConnection.getResponseCode();
        if (statusCode / 100 == 2) {
            return getStreamForSuccessfulRequest(this.urlConnection);
        }
        if (statusCode / 100 == 3) {
            String redirectUrlString = this.urlConnection.getHeaderField("Location");
            if (TextUtils.isEmpty(redirectUrlString)) {
                Throwable th8 = th3;
                new IOException("Received empty or null redirect url");
                throw th8;
            }
            new URL(url3, redirectUrlString);
            return loadDataWithRedirects(redirectUrl, redirects + 1, url3, headers);
        } else if (statusCode == -1) {
            Throwable th9 = th2;
            new IOException("Unable to retrieve response code from HttpUrlConnection.");
            throw th9;
        } else {
            Throwable th10 = th;
            new StringBuilder();
            new IOException(sb.append("Request failed ").append(statusCode).append(": ").append(this.urlConnection.getResponseMessage()).toString());
            throw th10;
        }
    }

    private InputStream getStreamForSuccessfulRequest(HttpURLConnection httpURLConnection) throws IOException {
        StringBuilder sb;
        HttpURLConnection urlConnection2 = httpURLConnection;
        if (TextUtils.isEmpty(urlConnection2.getContentEncoding())) {
            this.stream = ContentLengthInputStream.obtain(urlConnection2.getInputStream(), (long) urlConnection2.getContentLength());
        } else {
            if (Log.isLoggable(TAG, 3)) {
                new StringBuilder();
                int d = Log.d(TAG, sb.append("Got non empty content encoding: ").append(urlConnection2.getContentEncoding()).toString());
            }
            this.stream = urlConnection2.getInputStream();
        }
        return this.stream;
    }

    public void cleanup() {
        if (this.stream != null) {
            try {
                this.stream.close();
            } catch (IOException e) {
                IOException iOException = e;
            }
        }
        if (this.urlConnection != null) {
            this.urlConnection.disconnect();
        }
    }

    public String getId() {
        return this.glideUrl.getCacheKey();
    }

    public void cancel() {
        this.isCancelled = true;
    }

    private static class DefaultHttpUrlConnectionFactory implements HttpUrlConnectionFactory {
        private DefaultHttpUrlConnectionFactory() {
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ DefaultHttpUrlConnectionFactory(C15101 r4) {
            this();
            C15101 r1 = r4;
        }

        public HttpURLConnection build(URL url) throws IOException {
            return (HttpURLConnection) url.openConnection();
        }
    }
}
