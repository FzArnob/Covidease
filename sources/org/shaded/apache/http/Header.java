package org.shaded.apache.http;

public interface Header {
    HeaderElement[] getElements() throws ParseException;

    String getName();

    String getValue();
}
