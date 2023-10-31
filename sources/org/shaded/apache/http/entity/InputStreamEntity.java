package org.shaded.apache.http.entity;

import android.support.p000v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class InputStreamEntity extends AbstractHttpEntity {
    private static final int BUFFER_SIZE = 2048;
    private boolean consumed = false;
    private final InputStream content;
    private final long length;

    public InputStreamEntity(InputStream inputStream, long j) {
        Throwable th;
        InputStream instream = inputStream;
        long length2 = j;
        if (instream == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Source input stream may not be null");
            throw th2;
        }
        this.content = instream;
        this.length = length2;
    }

    public boolean isRepeatable() {
        return false;
    }

    public long getContentLength() {
        return this.length;
    }

    public InputStream getContent() throws IOException {
        return this.content;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        int l;
        Throwable th;
        OutputStream outstream = outputStream;
        if (outstream == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Output stream may not be null");
            throw th2;
        }
        InputStream instream = this.content;
        byte[] buffer = new byte[2048];
        if (this.length >= 0) {
            long j = this.length;
            while (true) {
                long remaining = j;
                if (remaining <= 0 || (l = instream.read(buffer, 0, (int) Math.min(PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH, remaining))) == -1) {
                    break;
                }
                outstream.write(buffer, 0, l);
                j = remaining - ((long) l);
            }
        } else {
            while (true) {
                int read = instream.read(buffer);
                int l2 = read;
                if (read == -1) {
                    break;
                }
                outstream.write(buffer, 0, l2);
            }
        }
        this.consumed = true;
    }

    public boolean isStreaming() {
        return !this.consumed;
    }

    public void consumeContent() throws IOException {
        this.consumed = true;
        this.content.close();
    }
}
