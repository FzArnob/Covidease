package org.shaded.apache.http.impl.cookie;

import org.shaded.apache.http.annotation.Immutable;

@Immutable
public class DateParseException extends Exception {
    private static final long serialVersionUID = 4417696455000643370L;

    public DateParseException() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DateParseException(String message) {
        super(message);
    }
}
