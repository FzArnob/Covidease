package org.shaded.apache.http.protocol;

import java.io.IOException;
import org.shaded.apache.http.ConnectionReuseStrategy;
import org.shaded.apache.http.HttpEntity;
import org.shaded.apache.http.HttpEntityEnclosingRequest;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.HttpResponseFactory;
import org.shaded.apache.http.HttpServerConnection;
import org.shaded.apache.http.HttpStatus;
import org.shaded.apache.http.HttpVersion;
import org.shaded.apache.http.MethodNotSupportedException;
import org.shaded.apache.http.ProtocolException;
import org.shaded.apache.http.ProtocolVersion;
import org.shaded.apache.http.UnsupportedHttpVersionException;
import org.shaded.apache.http.entity.ByteArrayEntity;
import org.shaded.apache.http.params.DefaultedHttpParams;
import org.shaded.apache.http.params.HttpParams;
import org.shaded.apache.http.util.EncodingUtils;

public class HttpService {
    private ConnectionReuseStrategy connStrategy = null;
    private HttpExpectationVerifier expectationVerifier = null;
    private HttpRequestHandlerResolver handlerResolver = null;
    private HttpParams params = null;
    private HttpProcessor processor = null;
    private HttpResponseFactory responseFactory = null;

    public HttpService(HttpProcessor proc, ConnectionReuseStrategy connStrategy2, HttpResponseFactory responseFactory2) {
        setHttpProcessor(proc);
        setConnReuseStrategy(connStrategy2);
        setResponseFactory(responseFactory2);
    }

    public void setHttpProcessor(HttpProcessor httpProcessor) {
        Throwable th;
        HttpProcessor processor2 = httpProcessor;
        if (processor2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP processor may not be null");
            throw th2;
        }
        this.processor = processor2;
    }

    public void setConnReuseStrategy(ConnectionReuseStrategy connectionReuseStrategy) {
        Throwable th;
        ConnectionReuseStrategy connStrategy2 = connectionReuseStrategy;
        if (connStrategy2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Connection reuse strategy may not be null");
            throw th2;
        }
        this.connStrategy = connStrategy2;
    }

    public void setResponseFactory(HttpResponseFactory httpResponseFactory) {
        Throwable th;
        HttpResponseFactory responseFactory2 = httpResponseFactory;
        if (responseFactory2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Response factory may not be null");
            throw th2;
        }
        this.responseFactory = responseFactory2;
    }

    public void setHandlerResolver(HttpRequestHandlerResolver handlerResolver2) {
        HttpRequestHandlerResolver httpRequestHandlerResolver = handlerResolver2;
        this.handlerResolver = httpRequestHandlerResolver;
    }

    public void setExpectationVerifier(HttpExpectationVerifier expectationVerifier2) {
        HttpExpectationVerifier httpExpectationVerifier = expectationVerifier2;
        this.expectationVerifier = httpExpectationVerifier;
    }

    public HttpParams getParams() {
        return this.params;
    }

    public void setParams(HttpParams params2) {
        HttpParams httpParams = params2;
        this.params = httpParams;
    }

    public void handleRequest(HttpServerConnection httpServerConnection, HttpContext httpContext) throws IOException, HttpException {
        HttpParams httpParams;
        HttpParams httpParams2;
        HttpEntity entity;
        HttpParams httpParams3;
        HttpParams httpParams4;
        HttpParams httpParams5;
        HttpServerConnection conn = httpServerConnection;
        HttpContext context = httpContext;
        context.setAttribute(ExecutionContext.HTTP_CONNECTION, conn);
        HttpResponse response = null;
        try {
            HttpRequest request = conn.receiveRequestHeader();
            new DefaultedHttpParams(request.getParams(), this.params);
            request.setParams(httpParams2);
            ProtocolVersion ver = request.getRequestLine().getProtocolVersion();
            if (!ver.lessEquals(HttpVersion.HTTP_1_1)) {
                ver = HttpVersion.HTTP_1_1;
            }
            if (request instanceof HttpEntityEnclosingRequest) {
                if (((HttpEntityEnclosingRequest) request).expectContinue()) {
                    response = this.responseFactory.newHttpResponse(ver, 100, context);
                    new DefaultedHttpParams(response.getParams(), this.params);
                    response.setParams(httpParams4);
                    if (this.expectationVerifier != null) {
                        try {
                            this.expectationVerifier.verify(request, response, context);
                        } catch (HttpException e) {
                            HttpException ex = e;
                            response = this.responseFactory.newHttpResponse(HttpVersion.HTTP_1_0, HttpStatus.SC_INTERNAL_SERVER_ERROR, context);
                            new DefaultedHttpParams(response.getParams(), this.params);
                            response.setParams(httpParams5);
                            handleException(ex, response);
                        }
                    }
                    if (response.getStatusLine().getStatusCode() < 200) {
                        conn.sendResponseHeader(response);
                        conn.flush();
                        response = null;
                        conn.receiveRequestEntity((HttpEntityEnclosingRequest) request);
                    }
                } else {
                    conn.receiveRequestEntity((HttpEntityEnclosingRequest) request);
                }
            }
            if (response == null) {
                response = this.responseFactory.newHttpResponse(ver, 200, context);
                new DefaultedHttpParams(response.getParams(), this.params);
                response.setParams(httpParams3);
                context.setAttribute(ExecutionContext.HTTP_REQUEST, request);
                context.setAttribute(ExecutionContext.HTTP_RESPONSE, response);
                this.processor.process(request, context);
                doService(request, response, context);
            }
            if ((request instanceof HttpEntityEnclosingRequest) && (entity = ((HttpEntityEnclosingRequest) request).getEntity()) != null) {
                entity.consumeContent();
            }
        } catch (HttpException e2) {
            response = this.responseFactory.newHttpResponse(HttpVersion.HTTP_1_0, HttpStatus.SC_INTERNAL_SERVER_ERROR, context);
            new DefaultedHttpParams(response.getParams(), this.params);
            response.setParams(httpParams);
            handleException(e2, response);
        }
        this.processor.process(response, context);
        conn.sendResponseHeader(response);
        conn.sendResponseEntity(response);
        conn.flush();
        if (!this.connStrategy.keepAlive(response, context)) {
            conn.close();
        }
    }

    /* access modifiers changed from: protected */
    public void handleException(HttpException httpException, HttpResponse httpResponse) {
        ByteArrayEntity byteArrayEntity;
        HttpException ex = httpException;
        HttpResponse response = httpResponse;
        if (ex instanceof MethodNotSupportedException) {
            response.setStatusCode(501);
        } else if (ex instanceof UnsupportedHttpVersionException) {
            response.setStatusCode(505);
        } else if (ex instanceof ProtocolException) {
            response.setStatusCode(HttpStatus.SC_BAD_REQUEST);
        } else {
            response.setStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
        new ByteArrayEntity(EncodingUtils.getAsciiBytes(ex.getMessage()));
        ByteArrayEntity entity = byteArrayEntity;
        entity.setContentType("text/plain; charset=US-ASCII");
        response.setEntity(entity);
    }

    /* access modifiers changed from: protected */
    public void doService(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        HttpRequest request = httpRequest;
        HttpResponse response = httpResponse;
        HttpContext context = httpContext;
        HttpRequestHandler handler = null;
        if (this.handlerResolver != null) {
            handler = this.handlerResolver.lookup(request.getRequestLine().getUri());
        }
        if (handler != null) {
            handler.handle(request, response, context);
        } else {
            response.setStatusCode(501);
        }
    }
}
