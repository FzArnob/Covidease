package org.shaded.apache.http.impl.p006io;

import java.io.IOException;
import java.io.InputStream;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.MalformedChunkCodingException;
import org.shaded.apache.http.message.LineParser;
import org.shaded.apache.http.p007io.SessionInputBuffer;
import org.shaded.apache.http.util.CharArrayBuffer;
import org.shaded.apache.http.util.ExceptionUtils;

/* renamed from: org.shaded.apache.http.impl.io.ChunkedInputStream */
public class ChunkedInputStream extends InputStream {
    private boolean bof = true;
    private final CharArrayBuffer buffer;
    private int chunkSize;
    private boolean closed = false;
    private boolean eof = false;
    private Header[] footers = new Header[0];

    /* renamed from: in */
    private SessionInputBuffer f292in;
    private int pos;

    public ChunkedInputStream(SessionInputBuffer sessionInputBuffer) {
        CharArrayBuffer charArrayBuffer;
        Throwable th;
        SessionInputBuffer in = sessionInputBuffer;
        if (in == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Session input buffer may not be null");
            throw th2;
        }
        this.f292in = in;
        this.pos = 0;
        new CharArrayBuffer(16);
        this.buffer = charArrayBuffer;
    }

    public int read() throws IOException {
        Throwable th;
        if (this.closed) {
            Throwable th2 = th;
            new IOException("Attempted read from closed stream.");
            throw th2;
        } else if (this.eof) {
            return -1;
        } else {
            if (this.pos >= this.chunkSize) {
                nextChunk();
                if (this.eof) {
                    return -1;
                }
            }
            int b = this.f292in.read();
            if (b != -1) {
                this.pos++;
            }
            return b;
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        Throwable th;
        Throwable th2;
        byte[] b = bArr;
        int off = i;
        int len = i2;
        if (this.closed) {
            Throwable th3 = th2;
            new IOException("Attempted read from closed stream.");
            throw th3;
        } else if (this.eof) {
            return -1;
        } else {
            if (this.pos >= this.chunkSize) {
                nextChunk();
                if (this.eof) {
                    return -1;
                }
            }
            int bytesRead = this.f292in.read(b, off, Math.min(len, this.chunkSize - this.pos));
            if (bytesRead != -1) {
                this.pos += bytesRead;
                return bytesRead;
            }
            Throwable th4 = th;
            new MalformedChunkCodingException("Truncated chunk");
            throw th4;
        }
    }

    public int read(byte[] bArr) throws IOException {
        byte[] b = bArr;
        return read(b, 0, b.length);
    }

    private void nextChunk() throws IOException {
        Throwable th;
        this.chunkSize = getChunkSize();
        if (this.chunkSize < 0) {
            Throwable th2 = th;
            new MalformedChunkCodingException("Negative chunk size");
            throw th2;
        }
        this.bof = false;
        this.pos = 0;
        if (this.chunkSize == 0) {
            this.eof = true;
            parseTrailerHeaders();
        }
    }

    private int getChunkSize() throws IOException {
        Throwable th;
        Throwable th2;
        if (!this.bof) {
            int cr = this.f292in.read();
            int lf = this.f292in.read();
            if (!(cr == 13 && lf == 10)) {
                Throwable th3 = th2;
                new MalformedChunkCodingException("CRLF expected at end of chunk");
                throw th3;
            }
        }
        this.buffer.clear();
        if (this.f292in.readLine(this.buffer) == -1) {
            return 0;
        }
        int separator = this.buffer.indexOf(59);
        if (separator < 0) {
            separator = this.buffer.length();
        }
        try {
            return Integer.parseInt(this.buffer.substringTrimmed(0, separator), 16);
        } catch (NumberFormatException e) {
            NumberFormatException numberFormatException = e;
            Throwable th4 = th;
            new MalformedChunkCodingException("Bad chunk header");
            throw th4;
        }
    }

    private void parseTrailerHeaders() throws IOException {
        IOException iOException;
        StringBuffer stringBuffer;
        try {
            this.footers = AbstractMessageParser.parseHeaders(this.f292in, -1, -1, (LineParser) null);
        } catch (HttpException e) {
            HttpException e2 = e;
            new StringBuffer();
            new MalformedChunkCodingException(stringBuffer.append("Invalid footer: ").append(e2.getMessage()).toString());
            IOException ioe = iOException;
            ExceptionUtils.initCause(ioe, e2);
            throw ioe;
        }
    }

    public void close() throws IOException {
        if (!this.closed) {
            try {
                if (!this.eof) {
                    exhaustInputStream(this);
                }
                this.eof = true;
                this.closed = true;
            } catch (Throwable th) {
                Throwable th2 = th;
                this.eof = true;
                this.closed = true;
                throw th2;
            }
        }
    }

    public Header[] getFooters() {
        return (Header[]) this.footers.clone();
    }

    static void exhaustInputStream(InputStream inStream) throws IOException {
        do {
        } while (inStream.read(new byte[1024]) >= 0);
    }
}
