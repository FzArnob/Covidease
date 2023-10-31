package com.bumptech.glide.disklrucache;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

class StrictLineReader implements Closeable {

    /* renamed from: CR */
    private static final byte f299CR = 13;

    /* renamed from: LF */
    private static final byte f300LF = 10;
    private byte[] buf;
    /* access modifiers changed from: private */
    public final Charset charset;
    private int end;

    /* renamed from: in */
    private final InputStream f301in;
    private int pos;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public StrictLineReader(InputStream in, Charset charset2) {
        this(in, 8192, charset2);
    }

    public StrictLineReader(InputStream inputStream, int i, Charset charset2) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        InputStream in = inputStream;
        int capacity = i;
        Charset charset3 = charset2;
        if (in == null || charset3 == null) {
            Throwable th4 = th;
            new NullPointerException();
            throw th4;
        } else if (capacity < 0) {
            Throwable th5 = th3;
            new IllegalArgumentException("capacity <= 0");
            throw th5;
        } else if (!charset3.equals(Util.US_ASCII)) {
            Throwable th6 = th2;
            new IllegalArgumentException("Unsupported encoding");
            throw th6;
        } else {
            this.f301in = in;
            this.charset = charset3;
            this.buf = new byte[capacity];
        }
    }

    public void close() throws IOException {
        InputStream inputStream = this.f301in;
        InputStream inputStream2 = inputStream;
        synchronized (inputStream) {
            try {
                if (this.buf != null) {
                    this.buf = null;
                    this.f301in.close();
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                InputStream inputStream3 = inputStream2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public String readLine() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        int i;
        String res;
        Throwable th;
        InputStream inputStream = this.f301in;
        InputStream inputStream2 = inputStream;
        synchronized (inputStream) {
            try {
                if (this.buf == null) {
                    Throwable th2 = th;
                    new IOException("LineReader is closed");
                    throw th2;
                }
                if (this.pos >= this.end) {
                    fillBuf();
                }
                int i2 = this.pos;
                while (i2 != this.end) {
                    if (this.buf[i2] == 10) {
                        new String(this.buf, this.pos, ((i2 == this.pos || this.buf[i2 + -1] != 13) ? i2 : i2 - 1) - this.pos, this.charset.name());
                        this.pos = i2 + 1;
                        String str = res;
                        return str;
                    }
                    i2++;
                }
                new ByteArrayOutputStream(this, (this.end - this.pos) + 80) {
                    final /* synthetic */ StrictLineReader this$0;

                    {
                        this.this$0 = r6;
                    }

                    public String toString() {
                        Throwable th;
                        String str;
                        try {
                            String str2 = str;
                            new String(this.buf, 0, (this.count <= 0 || this.buf[this.count + -1] != 13) ? this.count : this.count - 1, this.this$0.charset.name());
                            return str2;
                        } catch (UnsupportedEncodingException e) {
                            UnsupportedEncodingException e2 = e;
                            Throwable th2 = th;
                            new AssertionError(e2);
                            throw th2;
                        }
                    }
                };
                ByteArrayOutputStream out = byteArrayOutputStream;
                loop1:
                while (true) {
                    out.write(this.buf, this.pos, this.end - this.pos);
                    this.end = -1;
                    fillBuf();
                    i = this.pos;
                    while (true) {
                        if (i != this.end) {
                            if (this.buf[i] == 10) {
                                break loop1;
                            }
                            i++;
                        }
                    }
                }
                if (i != this.pos) {
                    out.write(this.buf, this.pos, i - this.pos);
                }
                this.pos = i + 1;
                String byteArrayOutputStream2 = out.toString();
                return byteArrayOutputStream2;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                InputStream inputStream3 = inputStream2;
                throw th4;
            }
        }
    }

    public boolean hasUnterminatedLine() {
        return this.end == -1;
    }

    private void fillBuf() throws IOException {
        Throwable th;
        int result = this.f301in.read(this.buf, 0, this.buf.length);
        if (result == -1) {
            Throwable th2 = th;
            new EOFException();
            throw th2;
        }
        this.pos = 0;
        this.end = result;
    }
}
