package com.bumptech.glide.util;

import android.text.TextUtils;
import android.util.Log;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class ContentLengthInputStream extends FilterInputStream {
    private static final String TAG = "ContentLengthStream";
    private static final int UNKNOWN = -1;
    private final long contentLength;
    private int readSoFar;

    public static InputStream obtain(InputStream other, String contentLengthHeader) {
        return obtain(other, (long) parseContentLength(contentLengthHeader));
    }

    public static InputStream obtain(InputStream other, long contentLength2) {
        InputStream other2;
        new ContentLengthInputStream(other, contentLength2);
        return other2;
    }

    private static int parseContentLength(String str) {
        StringBuilder sb;
        String contentLengthHeader = str;
        int result = -1;
        if (!TextUtils.isEmpty(contentLengthHeader)) {
            try {
                result = Integer.parseInt(contentLengthHeader);
            } catch (NumberFormatException e) {
                NumberFormatException e2 = e;
                if (Log.isLoggable(TAG, 3)) {
                    new StringBuilder();
                    int d = Log.d(TAG, sb.append("failed to parse content length header: ").append(contentLengthHeader).toString(), e2);
                }
            }
        }
        return result;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ContentLengthInputStream(InputStream in, long contentLength2) {
        super(in);
        this.contentLength = contentLength2;
    }

    public synchronized int available() throws IOException {
        int max;
        synchronized (this) {
            max = (int) Math.max(this.contentLength - ((long) this.readSoFar), (long) this.in.available());
        }
        return max;
    }

    public synchronized int read() throws IOException {
        int checkReadSoFarOrThrow;
        synchronized (this) {
            checkReadSoFarOrThrow = checkReadSoFarOrThrow(super.read());
        }
        return checkReadSoFarOrThrow;
    }

    public int read(byte[] bArr) throws IOException {
        byte[] buffer = bArr;
        return read(buffer, 0, buffer.length);
    }

    public synchronized int read(byte[] bArr, int i, int i2) throws IOException {
        int checkReadSoFarOrThrow;
        byte[] buffer = bArr;
        int byteOffset = i;
        int byteCount = i2;
        synchronized (this) {
            checkReadSoFarOrThrow = checkReadSoFarOrThrow(super.read(buffer, byteOffset, byteCount));
        }
        return checkReadSoFarOrThrow;
    }

    private int checkReadSoFarOrThrow(int i) throws IOException {
        Throwable th;
        StringBuilder sb;
        int read = i;
        if (read >= 0) {
            this.readSoFar += read;
        } else if (this.contentLength - ((long) this.readSoFar) > 0) {
            Throwable th2 = th;
            new StringBuilder();
            new IOException(sb.append("Failed to read all expected data, expected: ").append(this.contentLength).append(", but read: ").append(this.readSoFar).toString());
            throw th2;
        }
        return read;
    }
}
