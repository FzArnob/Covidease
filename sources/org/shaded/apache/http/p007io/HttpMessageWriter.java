package org.shaded.apache.http.p007io;

import java.io.IOException;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpMessage;

/* renamed from: org.shaded.apache.http.io.HttpMessageWriter */
public interface HttpMessageWriter {
    void write(HttpMessage httpMessage) throws IOException, HttpException;
}
