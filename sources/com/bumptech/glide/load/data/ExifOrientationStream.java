package com.bumptech.glide.load.data;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExifOrientationStream extends FilterInputStream {
    private static final byte[] EXIF_SEGMENT = {-1, -31, 0, 28, Ev3Constants.Opcode.CP_LT16, Ev3Constants.Opcode.JR_GTEQ8, Ev3Constants.Opcode.JR_GT16, Ev3Constants.Opcode.JR_LT32, 0, 0, Ev3Constants.Opcode.CP_EQ16, Ev3Constants.Opcode.CP_EQ16, 0, 0, 0, 0, 0, 8, 0, 1, 1, 18, 0, 2, 0, 0, 0, 1, 0};
    private static final int ORIENTATION_POSITION = (SEGMENT_LENGTH + 2);
    private static final int SEGMENT_LENGTH = EXIF_SEGMENT.length;
    private static final int SEGMENT_START_POSITION = 2;
    private final byte orientation;
    private int position;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExifOrientationStream(InputStream in, int i) {
        super(in);
        Throwable th;
        StringBuilder sb;
        int orientation2 = i;
        if (orientation2 < -1 || orientation2 > 8) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Cannot add invalid orientation: ").append(orientation2).toString());
            throw th2;
        }
        this.orientation = (byte) orientation2;
    }

    public boolean markSupported() {
        return false;
    }

    public void mark(int i) {
        Throwable th;
        int i2 = i;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int read() throws java.io.IOException {
        /*
            r6 = this;
            r0 = r6
            r2 = r0
            int r2 = r2.position
            r3 = 2
            if (r2 < r3) goto L_0x000e
            r2 = r0
            int r2 = r2.position
            int r3 = ORIENTATION_POSITION
            if (r2 <= r3) goto L_0x0026
        L_0x000e:
            r2 = r0
            int r2 = super.read()
            r1 = r2
        L_0x0014:
            r2 = r1
            r3 = -1
            if (r2 == r3) goto L_0x0023
            r2 = r0
            r5 = r2
            r2 = r5
            r3 = r5
            int r3 = r3.position
            r4 = 1
            int r3 = r3 + 1
            r2.position = r3
        L_0x0023:
            r2 = r1
            r0 = r2
            return r0
        L_0x0026:
            r2 = r0
            int r2 = r2.position
            int r3 = ORIENTATION_POSITION
            if (r2 != r3) goto L_0x0032
            r2 = r0
            byte r2 = r2.orientation
            r1 = r2
            goto L_0x0014
        L_0x0032:
            byte[] r2 = EXIF_SEGMENT
            r3 = r0
            int r3 = r3.position
            r4 = 2
            int r3 = r3 + -2
            byte r2 = r2[r3]
            r3 = 255(0xff, float:3.57E-43)
            r2 = r2 & 255(0xff, float:3.57E-43)
            r1 = r2
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.data.ExifOrientationStream.read():int");
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read;
        byte[] buffer = bArr;
        int byteOffset = i;
        int byteCount = i2;
        if (this.position > ORIENTATION_POSITION) {
            read = super.read(buffer, byteOffset, byteCount);
        } else if (this.position == ORIENTATION_POSITION) {
            buffer[byteOffset] = this.orientation;
            read = 1;
        } else if (this.position < 2) {
            read = super.read(buffer, byteOffset, 2 - this.position);
        } else {
            read = Math.min(ORIENTATION_POSITION - this.position, byteCount);
            System.arraycopy(EXIF_SEGMENT, this.position - 2, buffer, byteOffset, read);
        }
        if (read > 0) {
            this.position += read;
        }
        return read;
    }

    public long skip(long byteCount) throws IOException {
        long skipped = super.skip(byteCount);
        if (skipped > 0) {
            this.position = (int) (((long) this.position) + skipped);
        }
        return skipped;
    }

    public void reset() throws IOException {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }
}
