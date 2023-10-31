package org.shaded.apache.http;

public class ParseException extends RuntimeException {
    private static final long serialVersionUID = -7288819855864183578L;

    public ParseException() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ParseException(String message) {
        super(message);
    }
}
