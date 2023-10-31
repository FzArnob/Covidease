package gnu.kawa.servlet;

import com.firebase.client.FirebaseError;
import java.io.IOException;
import java.io.OutputStream;

/* compiled from: ServletPrinter */
class HttpOutputStream extends OutputStream {
    byte[] buffer;
    HttpRequestContext context;
    int count;
    OutputStream out;

    public HttpOutputStream(HttpRequestContext context2, int bufSize) {
        this.context = context2;
        this.buffer = new byte[bufSize];
    }

    public void write(int i) throws IOException {
        int b = i;
        if (this.count >= this.buffer.length) {
            flush();
        }
        byte[] bArr = this.buffer;
        int i2 = this.count;
        int i3 = i2 + 1;
        this.count = i3;
        bArr[i2] = (byte) b;
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        byte[] data = bArr;
        int offset = i;
        int length = i2;
        int length2 = this.buffer.length - this.count;
        while (true) {
            int avail = length2;
            if (length <= avail) {
                break;
            }
            System.arraycopy(data, offset, this.buffer, this.count, avail);
            this.count += avail;
            flush();
            offset += avail;
            length -= avail;
            length2 = this.buffer.length;
        }
        if (length > 0) {
            System.arraycopy(data, offset, this.buffer, this.count, length);
            this.count += length;
        }
    }

    public boolean reset() {
        this.count = 0;
        return this.out == null;
    }

    public void flush() throws IOException {
        if (this.out == null) {
            maybeSendResponseHeaders(-1);
            this.out = this.context.getResponseStream();
        }
        if (this.count > 0) {
            this.out.write(this.buffer, 0, this.count);
            this.count = 0;
        }
    }

    /* access modifiers changed from: package-private */
    public void maybeSendResponseHeaders(int i) throws IOException {
        int count2 = i;
        int statusCode = this.context.statusCode;
        if (statusCode != -999) {
            this.context.sendResponseHeaders(statusCode, this.context.statusReasonPhrase, (long) count2);
            this.context.statusCode = FirebaseError.UNKNOWN_ERROR;
        }
    }

    public void close() throws IOException {
        if (this.out == null) {
            maybeSendResponseHeaders(this.count);
            this.out = this.context.getResponseStream();
        }
        flush();
        this.out.close();
    }
}
