package org.shaded.apache.http;

public interface HttpRequest extends HttpMessage {
    RequestLine getRequestLine();
}
