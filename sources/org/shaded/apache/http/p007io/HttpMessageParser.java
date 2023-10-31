package org.shaded.apache.http.p007io;

import java.io.IOException;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpMessage;

/* renamed from: org.shaded.apache.http.io.HttpMessageParser */
public interface HttpMessageParser {
    HttpMessage parse() throws IOException, HttpException;
}
