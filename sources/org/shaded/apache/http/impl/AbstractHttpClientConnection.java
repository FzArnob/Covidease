package org.shaded.apache.http.impl;

import java.io.IOException;
import org.shaded.apache.http.HttpClientConnection;
import org.shaded.apache.http.HttpConnectionMetrics;
import org.shaded.apache.http.HttpEntityEnclosingRequest;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.HttpResponseFactory;
import org.shaded.apache.http.entity.ContentLengthStrategy;
import org.shaded.apache.http.impl.entity.EntityDeserializer;
import org.shaded.apache.http.impl.entity.EntitySerializer;
import org.shaded.apache.http.impl.entity.LaxContentLengthStrategy;
import org.shaded.apache.http.impl.entity.StrictContentLengthStrategy;
import org.shaded.apache.http.impl.p006io.HttpRequestWriter;
import org.shaded.apache.http.impl.p006io.HttpResponseParser;
import org.shaded.apache.http.message.LineFormatter;
import org.shaded.apache.http.message.LineParser;
import org.shaded.apache.http.p007io.EofSensor;
import org.shaded.apache.http.p007io.HttpMessageParser;
import org.shaded.apache.http.p007io.HttpMessageWriter;
import org.shaded.apache.http.p007io.SessionInputBuffer;
import org.shaded.apache.http.p007io.SessionOutputBuffer;
import org.shaded.apache.http.params.HttpParams;

public abstract class AbstractHttpClientConnection implements HttpClientConnection {
    private final EntityDeserializer entitydeserializer = createEntityDeserializer();
    private final EntitySerializer entityserializer = createEntitySerializer();
    private EofSensor eofSensor = null;
    private SessionInputBuffer inbuffer = null;
    private HttpConnectionMetricsImpl metrics = null;
    private SessionOutputBuffer outbuffer = null;
    private HttpMessageWriter requestWriter = null;
    private HttpMessageParser responseParser = null;

    /* access modifiers changed from: protected */
    public abstract void assertOpen() throws IllegalStateException;

    public AbstractHttpClientConnection() {
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
    public HttpResponseFactory createHttpResponseFactory() {
        HttpResponseFactory httpResponseFactory;
        new DefaultHttpResponseFactory();
        return httpResponseFactory;
    }

    /* access modifiers changed from: protected */
    public HttpMessageParser createResponseParser(SessionInputBuffer buffer, HttpResponseFactory responseFactory, HttpParams params) {
        HttpMessageParser httpMessageParser;
        new HttpResponseParser(buffer, (LineParser) null, responseFactory, params);
        return httpMessageParser;
    }

    /* access modifiers changed from: protected */
    public HttpMessageWriter createRequestWriter(SessionOutputBuffer buffer, HttpParams params) {
        HttpMessageWriter httpMessageWriter;
        new HttpRequestWriter(buffer, (LineFormatter) null, params);
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
            this.responseParser = createResponseParser(inbuffer2, createHttpResponseFactory(), params);
            this.requestWriter = createRequestWriter(outbuffer2, params);
            new HttpConnectionMetricsImpl(inbuffer2.getMetrics(), outbuffer2.getMetrics());
            this.metrics = httpConnectionMetricsImpl;
        }
    }

    public boolean isResponseAvailable(int timeout) throws IOException {
        assertOpen();
        return this.inbuffer.isDataAvailable(timeout);
    }

    public void sendRequestHeader(HttpRequest httpRequest) throws HttpException, IOException {
        Throwable th;
        HttpRequest request = httpRequest;
        if (request == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP request may not be null");
            throw th2;
        }
        assertOpen();
        this.requestWriter.write(request);
        this.metrics.incrementRequestCount();
    }

    public void sendRequestEntity(HttpEntityEnclosingRequest httpEntityEnclosingRequest) throws HttpException, IOException {
        Throwable th;
        HttpEntityEnclosingRequest request = httpEntityEnclosingRequest;
        if (request == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP request may not be null");
            throw th2;
        }
        assertOpen();
        if (request.getEntity() != null) {
            this.entityserializer.serialize(this.outbuffer, request, request.getEntity());
        }
    }

    /* access modifiers changed from: protected */
    public void doFlush() throws IOException {
        this.outbuffer.flush();
    }

    public void flush() throws IOException {
        assertOpen();
        doFlush();
    }

    public HttpResponse receiveResponseHeader() throws HttpException, IOException {
        assertOpen();
        HttpResponse response = (HttpResponse) this.responseParser.parse();
        if (response.getStatusLine().getStatusCode() >= 200) {
            this.metrics.incrementResponseCount();
        }
        return response;
    }

    public void receiveResponseEntity(HttpResponse httpResponse) throws HttpException, IOException {
        Throwable th;
        HttpResponse response = httpResponse;
        if (response == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP response may not be null");
            throw th2;
        }
        assertOpen();
        response.setEntity(this.entitydeserializer.deserialize(this.inbuffer, response));
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
