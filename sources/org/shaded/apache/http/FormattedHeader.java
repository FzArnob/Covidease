package org.shaded.apache.http;

import org.shaded.apache.http.util.CharArrayBuffer;

public interface FormattedHeader extends Header {
    CharArrayBuffer getBuffer();

    int getValuePos();
}
