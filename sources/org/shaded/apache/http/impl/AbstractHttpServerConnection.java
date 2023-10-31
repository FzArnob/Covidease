package org.shaded.apache.http.impl;

import java.io.IOException;
import org.shaded.apache.http.HttpConnectionMetrics;
import org.shaded.apache.http.HttpEntityEnclosingRequest;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.HttpRequestFactory;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.HttpServerConnection;
import org.shaded.apache.http.entity.ContentLengthStrategy;
import org.shaded.apache.http.impl.entity.EntityDeserializer;
import org.shaded.apache.http.impl.entity.EntitySerializer;
import org.shaded.apache.http.impl.entity.LaxContentLengthStrategy;
import org.shaded.apache.http.impl.entity.StrictContentLengthStrategy;
import org.shaded.apache.http.impl.p006io.HttpRequestParser;
import org.shaded.apache.http.impl.p006io.HttpResponseWriter;
import org.shaded.apache.http.message.LineFormatter;
import org.shaded.apache.http.message.LineParser;
import org.shaded.apache.http.p007io.EofSensor;
import org.shaded.apache.http.p007io.HttpMessageParser;
import org.shaded.apache.http.p007io.HttpMessageWriter;
import org.shaded.apache.http.p007io.SessionInputBuffer;
import org.shaded.apache.http.p007io.SessionOutputBuffer;
import org.shaded.apache.http.params.HttpParams;

public abstract class AbstractHttpServerConnection implements HttpServerConnection {
    private final EntityDeserializer entitydeserializer = createEntityDeserializer();
    private final EntitySerializer entityserializer = createEntitySerializer();
    private EofSensor eofSensor = null;
    private SessionInputBuffer inbuffer = null;
    private HttpConnectionMetricsImpl metrics = null;
    private SessionOutputBuffer outbuffer = null;
    private HttpMessageParser requestParser = null;
    private HttpMessageWriter responseWriter = null;

    /* access modifiers changed from: protected */
    public abstract void assertOpen() throws IllegalStateException;

    public AbstractHttpServerConnection() {
    }

    /* access modifiers changed from: protected */
    public EntityDeserializer createEntityDeserializer() {
        EntityDeserializer entityDeserializer;
        ContentLengthStrategy contentLengthStrategy;
        new LaxContentLengthStrategy();
        new EntityDeserializer(contentLengthStrategy);
        return entityDeserializer;
    }

    /* access modifiers changed from: protected */
    public EntitySerializer createEntitySerializer() {
        EntitySerializer entitySerializer;
        ContentLengthStrategy contentLengthStrategy;
        new StrictContentLengthStrategy();
        new EntitySerializer(contentLengthStrategy);
        return entitySerializer;
    }

    /* access modifiers changed from: protected */
    public HttpRequestFactory createHttpRequestFactory() {
        HttpRequestFactory httpRequestFactory;
        new DefaultHttpRequestFactory();
        return httpRequestFactory;
    }

    /* access modifiers changed from: protected */
    public HttpMessageParser createRequestParser(SessionInputBuffer buffer, HttpRequestFactory requestFactory, HttpParams params) {
        HttpMessageParser httpMessageParser;
        new HttpRequestParser(buffer, (LineParser) null, requestFactory, params);
        return httpMessageParser;
    }

    /* access modifiers changed from: protected */
    public HttpMessageWriter createResponseWriter(SessionOutputBuffer buffer, HttpParams params) {
        HttpMessageWriter httpMessageWriter;
        new HttpResponseWriter(buffer, (LineFormatter) null, params);
        return httpMessageWriter;
    }

    /* access modifiers changed from: protected */
    public void init(SessionInputBuffer sessionInputBuffer, SessionOutputBuffer sessionOutputBuffer, HttpParams httpParams) {
        HttpConnectionMetricsImpl httpConnectionMetricsImpl;
        Throwable th;
        Throwable th2;
        SessionInputBuffer inbuffer2 = sessionInputBuffer;
        SessionOutputBuffer outbuffer2 = sessionOutputBuffer;
        HttpParams params = httpParams;
        if (inbuffer2 == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Input session buffer may not be null");
            throw th3;
        } else if (outbuffer2 == null) {
            Throwable th4 = th;
            new IllegalArgumentException("Output session buffer may not be null");
            throw th4;
        } else {
            this.inbuffer = inbuffer2;
            this.outbuffer = outbuffer2;
            if (inbuffer2 instanceof EofSensor) {
                this.eofSensor = (EofSensor) inbuffer2;
            }
            this.requestParser = createRequestParser(inbuffer2, createHttpRequestFactory(), params);
            this.responseWriter = createResponseWriter(outbuffer2, params);
            new HttpConnectionMetricsImpl(inbuffer2.getMetrics(), outbuffer2.getMetrics());
            this.metrics = httpConnectionMetricsImpl;
        }
    }

    public HttpRequest receiveRequestHeader() throws HttpException, IOException {
        assertOpen();
        HttpRequest request = (HttpRequest) this.requestParser.parse();
        this.metrics.incrementRequestCount();
        return request;
    }

    public void receiveRequestEntity(HttpEntityEnclosingRequest httpEntityEnclosingRequest) throws HttpException, IOException {
        Throwable th;
        HttpEntityEnclosingRequest request = httpEntityEnclosingRequest;
        if (request == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP request may not be null");
            throw th2;
        }
        assertOpen();
        request.setEntity(this.entitydeserializer.deserialize(this.inbuffer, request));
    }

    /* access modifiers changed from: protected */
    public void doFlush() throws IOException {
        this.outbuffer.flush();
    }

    public void flush() throws IOException {
        assertOpen();
        doFlush();
    }

    public void sendResponseHeader(HttpResponse httpResponse) throws HttpException, IOException {
        Throwable th;
        HttpResponse response = httpResponse;
        if (response == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP response may not be null");
            throw th2;
        }
        assertOpen();
        this.responseWriter.write(response);
        if (response.getStatusLine().getStatusCode() >= 200) {
            this.metrics.incrementResponseCount();
        }
    }

    public void sendResponseEntity(HttpResponse httpResponse) throws HttpException, IOException {
        HttpResponse response = httpResponse;
        if (response.getEntity() != null) {
            this.entityserializer.serialize(this.outbuffer, response, response.getEntity());
        }
    }

    /* access modifiers changed from: protected */
    public boolean isEof() {
        return this.eofSensor != null && this.eofSensor.isEof();
    }

    public boolean isStale() {
        if (!isOpen()) {
            return true;
        }
        if (isEof()) {
            return true;
        }
        try {
            boolean isDataAvailable = this.inbuffer.isDataAvailable(1);
            return isEof();
        } catch (IOException e) {
            IOException iOException = e;
            return true;
        }
    }

    public HttpConnectionMetrics getMetrics() {
        return this.metrics;
    }
}
