package org.shaded.apache.http.protocol;

import com.shaded.fasterxml.jackson.core.util.BufferRecycler;
import java.io.IOException;
import java.net.ProtocolException;
import org.shaded.apache.http.HttpClientConnection;
import org.shaded.apache.http.HttpEntityEnclosingRequest;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.HttpVersion;
import org.shaded.apache.http.ProtocolVersion;
import org.shaded.apache.http.client.methods.HttpHead;
import org.shaded.apache.http.params.CoreProtocolPNames;

public class HttpRequestExecutor {
    public HttpRequestExecutor() {
    }

    /* access modifiers changed from: protected */
    public boolean canResponseHaveBody(HttpRequest request, HttpResponse httpResponse) {
        HttpResponse response = httpResponse;
        if (HttpHead.METHOD_NAME.equalsIgnoreCase(request.getRequestLine().getMethod())) {
            return false;
        }
        int status = response.getStatusLine().getStatusCode();
        return (status < 200 || status == 204 || status == 304 || status == 205) ? false : true;
    }

    public HttpResponse execute(HttpRequest httpRequest, HttpClientConnection httpClientConnection, HttpContext httpContext) throws IOException, HttpException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        HttpRequest request = httpRequest;
        HttpClientConnection conn = httpClientConnection;
        HttpContext context = httpContext;
        if (request == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("HTTP request may not be null");
            throw th4;
        } else if (conn == null) {
            Throwable th5 = th2;
            new IllegalArgumentException("Client connection may not be null");
            throw th5;
        } else if (context == null) {
            Throwable th6 = th;
            new IllegalArgumentException("HTTP context may not be null");
            throw th6;
        } else {
            try {
                HttpResponse response = doSendRequest(request, conn, context);
                if (response == null) {
                    response = doReceiveResponse(request, conn, context);
                }
                return response;
            } catch (IOException e) {
                IOException ex = e;
                conn.close();
                throw ex;
            } catch (HttpException e2) {
                HttpException ex2 = e2;
                conn.close();
                throw ex2;
            } catch (RuntimeException e3) {
                RuntimeException ex3 = e3;
                conn.close();
                throw ex3;
            }
        }
    }

    public void preProcess(HttpRequest httpRequest, HttpProcessor httpProcessor, HttpContext httpContext) throws HttpException, IOException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        HttpRequest request = httpRequest;
        HttpProcessor processor = httpProcessor;
        HttpContext context = httpContext;
        if (request == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("HTTP request may not be null");
            throw th4;
        } else if (processor == null) {
            Throwable th5 = th2;
            new IllegalArgumentException("HTTP processor may not be null");
            throw th5;
        } else if (context == null) {
            Throwable th6 = th;
            new IllegalArgumentException("HTTP context may not be null");
            throw th6;
        } else {
            context.setAttribute(ExecutionContext.HTTP_REQUEST, request);
            processor.process(request, context);
        }
    }

    /* access modifiers changed from: protected */
    public HttpResponse doSendRequest(HttpRequest httpRequest, HttpClientConnection httpClientConnection, HttpContext httpContext) throws IOException, HttpException {
        Throwable th;
        StringBuffer stringBuffer;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        HttpRequest request = httpRequest;
        HttpClientConnection conn = httpClientConnection;
        HttpContext context = httpContext;
        if (request == null) {
            Throwable th5 = th4;
            new IllegalArgumentException("HTTP request may not be null");
            throw th5;
        } else if (conn == null) {
            Throwable th6 = th3;
            new IllegalArgumentException("HTTP connection may not be null");
            throw th6;
        } else if (context == null) {
            Throwable th7 = th2;
            new IllegalArgumentException("HTTP context may not be null");
            throw th7;
        } else {
            HttpResponse response = null;
            context.setAttribute(ExecutionContext.HTTP_CONNECTION, conn);
            context.setAttribute(ExecutionContext.HTTP_REQ_SENT, Boolean.FALSE);
            conn.sendRequestHeader(request);
            if (request instanceof HttpEntityEnclosingRequest) {
                boolean sendentity = true;
                ProtocolVersion ver = request.getRequestLine().getProtocolVersion();
                if (((HttpEntityEnclosingRequest) request).expectContinue() && !ver.lessEquals(HttpVersion.HTTP_1_0)) {
                    conn.flush();
                    if (conn.isResponseAvailable(request.getParams().getIntParameter(CoreProtocolPNames.WAIT_FOR_CONTINUE, BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN))) {
                        response = conn.receiveResponseHeader();
                        if (canResponseHaveBody(request, response)) {
                            conn.receiveResponseEntity(response);
                        }
                        int status = response.getStatusLine().getStatusCode();
                        if (status >= 200) {
                            sendentity = false;
                        } else if (status != 100) {
                            Throwable th8 = th;
                            new StringBuffer();
                            new ProtocolException(stringBuffer.append("Unexpected response: ").append(response.getStatusLine()).toString());
                            throw th8;
                        } else {
                            response = null;
                        }
                    }
                }
                if (sendentity) {
                    conn.sendRequestEntity((HttpEntityEnclosingRequest) request);
                }
            }
            conn.flush();
            context.setAttribute(ExecutionContext.HTTP_REQ_SENT, Boolean.TRUE);
            return response;
        }
    }

    /* access modifiers changed from: protected */
    public HttpResponse doReceiveResponse(HttpRequest httpRequest, HttpClientConnection httpClientConnection, HttpContext httpContext) throws HttpException, IOException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        HttpRequest request = httpRequest;
        HttpClientConnection conn = httpClientConnection;
        HttpContext context = httpContext;
        if (request == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("HTTP request may not be null");
            throw th4;
        } else if (conn == null) {
            Throwable th5 = th2;
            new IllegalArgumentException("HTTP connection may not be null");
            throw th5;
        } else if (context == null) {
            Throwable th6 = th;
            new IllegalArgumentException("HTTP context may not be null");
            throw th6;
        } else {
            HttpResponse response = null;
            int i = 0;
            while (true) {
                int statuscode = i;
                if (response != null && statuscode >= 200) {
                    return response;
                }
                response = conn.receiveResponseHeader();
                if (canResponseHaveBody(request, response)) {
                    conn.receiveResponseEntity(response);
                }
                i = response.getStatusLine().getStatusCode();
            }
        }
    }

    public void postProcess(HttpResponse httpResponse, HttpProcessor httpProcessor, HttpContext httpContext) throws HttpException, IOException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        HttpResponse response = httpResponse;
        HttpProcessor processor = httpProcessor;
        HttpContext context = httpContext;
        if (response == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("HTTP response may not be null");
            throw th4;
        } else if (processor == null) {
            Throwable th5 = th2;
            new IllegalArgumentException("HTTP processor may not be null");
            throw th5;
        } else if (context == null) {
            Throwable th6 = th;
            new IllegalArgumentException("HTTP context may not be null");
            throw th6;
        } else {
            context.setAttribute(ExecutionContext.HTTP_RESPONSE, response);
            processor.process(response, context);
        }
    }
}
