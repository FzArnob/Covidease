package org.shaded.apache.http.entity;

import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpMessage;

public interface ContentLengthStrategy {
    public static final int CHUNKED = -2;
    public static final int IDENTITY = -1;

    long determineLength(HttpMessage httpMessage) throws HttpException;
}
