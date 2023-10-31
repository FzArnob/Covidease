package org.shaded.apache.http.message;

import java.util.NoSuchElementException;
import org.shaded.apache.http.FormattedHeader;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HeaderElement;
import org.shaded.apache.http.HeaderElementIterator;
import org.shaded.apache.http.HeaderIterator;
import org.shaded.apache.http.util.CharArrayBuffer;

public class BasicHeaderElementIterator implements HeaderElementIterator {
    private CharArrayBuffer buffer;
    private HeaderElement currentElement;
    private ParserCursor cursor;
    private final HeaderIterator headerIt;
    private final HeaderValueParser parser;

    public BasicHeaderElementIterator(HeaderIterator headerIterator, HeaderValueParser headerValueParser) {
        Throwable th;
        Throwable th2;
        HeaderIterator headerIterator2 = headerIterator;
        HeaderValueParser parser2 = headerValueParser;
        this.currentElement = null;
        this.buffer = null;
        this.cursor = null;
        if (headerIterator2 == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Header iterator may not be null");
            throw th3;
        } else if (parser2 == null) {
            Throwable th4 = th;
            new IllegalArgumentException("Parser may not be null");
            throw th4;
        } else {
            this.headerIt = headerIterator2;
            this.parser = parser2;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BasicHeaderElementIterator(HeaderIterator headerIterator) {
        this(headerIterator, BasicHeaderValueParser.DEFAULT);
    }

    private void bufferHeaderValue() {
        ParserCursor parserCursor;
        CharArrayBuffer charArrayBuffer;
        ParserCursor parserCursor2;
        this.cursor = null;
        this.buffer = null;
        while (this.headerIt.hasNext()) {
            Header h = this.headerIt.nextHeader();
            if (h instanceof FormattedHeader) {
                this.buffer = ((FormattedHeader) h).getBuffer();
                new ParserCursor(0, this.buffer.length());
                this.cursor = parserCursor;
                this.cursor.updatePos(((FormattedHeader) h).getValuePos());
                return;
            }
            String value = h.getValue();
            if (value != null) {
                new CharArrayBuffer(value.length());
                this.buffer = charArrayBuffer;
                this.buffer.append(value);
                new ParserCursor(0, this.buffer.length());
                this.cursor = parserCursor2;
                return;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x002f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void parseNextElement() {
        /*
            r5 = this;
            r0 = r5
        L_0x0001:
            r2 = r0
            org.shaded.apache.http.HeaderIterator r2 = r2.headerIt
            boolean r2 = r2.hasNext()
            if (r2 != 0) goto L_0x000f
            r2 = r0
            org.shaded.apache.http.message.ParserCursor r2 = r2.cursor
            if (r2 == 0) goto L_0x0067
        L_0x000f:
            r2 = r0
            org.shaded.apache.http.message.ParserCursor r2 = r2.cursor
            if (r2 == 0) goto L_0x001d
            r2 = r0
            org.shaded.apache.http.message.ParserCursor r2 = r2.cursor
            boolean r2 = r2.atEnd()
            if (r2 == 0) goto L_0x0021
        L_0x001d:
            r2 = r0
            r2.bufferHeaderValue()
        L_0x0021:
            r2 = r0
            org.shaded.apache.http.message.ParserCursor r2 = r2.cursor
            if (r2 == 0) goto L_0x0001
        L_0x0026:
            r2 = r0
            org.shaded.apache.http.message.ParserCursor r2 = r2.cursor
            boolean r2 = r2.atEnd()
            if (r2 != 0) goto L_0x0055
            r2 = r0
            org.shaded.apache.http.message.HeaderValueParser r2 = r2.parser
            r3 = r0
            org.shaded.apache.http.util.CharArrayBuffer r3 = r3.buffer
            r4 = r0
            org.shaded.apache.http.message.ParserCursor r4 = r4.cursor
            org.shaded.apache.http.HeaderElement r2 = r2.parseHeaderElement(r3, r4)
            r1 = r2
            r2 = r1
            java.lang.String r2 = r2.getName()
            int r2 = r2.length()
            if (r2 != 0) goto L_0x004f
            r2 = r1
            java.lang.String r2 = r2.getValue()
            if (r2 == 0) goto L_0x0054
        L_0x004f:
            r2 = r0
            r3 = r1
            r2.currentElement = r3
        L_0x0053:
            return
        L_0x0054:
            goto L_0x0026
        L_0x0055:
            r2 = r0
            org.shaded.apache.http.message.ParserCursor r2 = r2.cursor
            boolean r2 = r2.atEnd()
            if (r2 == 0) goto L_0x0001
            r2 = r0
            r3 = 0
            r2.cursor = r3
            r2 = r0
            r3 = 0
            r2.buffer = r3
            goto L_0x0001
        L_0x0067:
            goto L_0x0053
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.message.BasicHeaderElementIterator.parseNextElement():void");
    }

    public boolean hasNext() {
        if (this.currentElement == null) {
            parseNextElement();
        }
        return this.currentElement != null;
    }

    public HeaderElement nextElement() throws NoSuchElementException {
        Throwable th;
        if (this.currentElement == null) {
            parseNextElement();
        }
        if (this.currentElement == null) {
            Throwable th2 = th;
            new NoSuchElementException("No more header elements available");
            throw th2;
        }
        HeaderElement element = this.currentElement;
        this.currentElement = null;
        return element;
    }

    public final Object next() throws NoSuchElementException {
        return nextElement();
    }

    public void remove() throws UnsupportedOperationException {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException("Remove not supported");
        throw th2;
    }
}
