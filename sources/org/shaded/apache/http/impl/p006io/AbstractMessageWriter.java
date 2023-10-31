package org.shaded.apache.http.impl.p006io;

import java.io.IOException;
import java.util.Iterator;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpMessage;
import org.shaded.apache.http.message.BasicLineFormatter;
import org.shaded.apache.http.message.LineFormatter;
import org.shaded.apache.http.p007io.HttpMessageWriter;
import org.shaded.apache.http.p007io.SessionOutputBuffer;
import org.shaded.apache.http.params.HttpParams;
import org.shaded.apache.http.util.CharArrayBuffer;

/* renamed from: org.shaded.apache.http.impl.io.AbstractMessageWriter */
public abstract class AbstractMessageWriter implements HttpMessageWriter {
    protected final CharArrayBuffer lineBuf;
    protected final LineFormatter lineFormatter;
    protected final SessionOutputBuffer sessionBuffer;

    /* access modifiers changed from: protected */
    public abstract void writeHeadLine(HttpMessage httpMessage) throws IOException;

    public AbstractMessageWriter(SessionOutputBuffer sessionOutputBuffer, LineFormatter lineFormatter2, HttpParams httpParams) {
        CharArrayBuffer charArrayBuffer;
        Throwable th;
        SessionOutputBuffer buffer = sessionOutputBuffer;
        LineFormatter formatter = lineFormatter2;
        HttpParams httpParams2 = httpParams;
        if (buffer == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Session input buffer may not be null");
            throw th2;
        }
        this.sessionBuffer = buffer;
        new CharArrayBuffer(128);
        this.lineBuf = charArrayBuffer;
        this.lineFormatter = formatter != null ? formatter : BasicLineFormatter.DEFAULT;
    }

    public void write(HttpMessage httpMessage) throws IOException, HttpException {
        Throwable th;
        HttpMessage message = httpMessage;
        if (message == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP message may not be null");
            throw th2;
        }
        writeHeadLine(message);
        Iterator it = message.headerIterator();
        while (it.hasNext()) {
            this.sessionBuffer.writeLine(this.lineFormatter.formatHeader(this.lineBuf, (Header) it.next()));
        }
        this.lineBuf.clear();
        this.sessionBuffer.writeLine(this.lineBuf);
    }
}
