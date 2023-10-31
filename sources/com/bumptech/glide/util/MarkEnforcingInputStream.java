package com.bumptech.glide.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MarkEnforcingInputStream extends FilterInputStream {
    private static final int END_OF_STREAM = -1;
    private static final int UNSET = Integer.MIN_VALUE;
    private int availableBytes = Integer.MIN_VALUE;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MarkEnforcingInputStream(InputStream in) {
        super(in);
    }

    public void mark(int i) {
        int readlimit = i;
        super.mark(readlimit);
        this.availableBytes = readlimit;
    }

    public int read() throws IOException {
        if (getBytesToRead(1) == -1) {
            return -1;
        }
        int result = super.read();
        updateAvailableBytesAfterRead(1);
        return result;
    }

    public int read(byte[] bArr, int i, int byteCount) throws IOException {
        byte[] buffer = bArr;
        int byteOffset = i;
        int toRead = (int) getBytesToRead((long) byteCount);
        if (toRead == -1) {
            return -1;
        }
        int read = super.read(buffer, byteOffset, toRead);
        updateAvailableBytesAfterRead((long) read);
        return read;
    }

    public void reset() throws IOException {
        super.reset();
        this.availableBytes = Integer.MIN_VALUE;
    }

    public long skip(long byteCount) throws IOException {
        long toSkip = getBytesToRead(byteCount);
        if (toSkip == -1) {
            return -1;
        }
        long read = super.skip(toSkip);
        updateAvailableBytesAfterRead(read);
        return read;
    }

    public int available() throws IOException {
        return this.availableBytes == Integer.MIN_VALUE ? super.available() : Math.min(this.availableBytes, super.available());
    }

    private long getBytesToRead(long j) {
        long targetByteCount = j;
        if (this.availableBytes == 0) {
            return -1;
        }
        if (this.availableBytes == Integer.MIN_VALUE || targetByteCount <= ((long) this.availableBytes)) {
            return targetByteCount;
        }
        return (long) this.availableBytes;
    }

    private void updateAvailableBytesAfterRead(long j) {
        long bytesRead = j;
        if (this.availableBytes != Integer.MIN_VALUE && bytesRead != -1) {
            this.availableBytes = (int) (((long) this.availableBytes) - bytesRead);
        }
    }
}
