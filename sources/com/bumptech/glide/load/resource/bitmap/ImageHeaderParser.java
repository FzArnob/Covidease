package com.bumptech.glide.load.resource.bitmap;

import android.support.p000v4.internal.view.SupportMenu;
import android.support.p000v4.view.MotionEventCompat;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ImageHeaderParser {
    private static final int[] BYTES_PER_FORMAT = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};
    private static final int EXIF_MAGIC_NUMBER = 65496;
    private static final int EXIF_SEGMENT_TYPE = 225;
    private static final int GIF_HEADER = 4671814;
    private static final int INTEL_TIFF_MAGIC_NUMBER = 18761;
    private static final String JPEG_EXIF_SEGMENT_PREAMBLE = "Exif\u0000\u0000";
    private static final byte[] JPEG_EXIF_SEGMENT_PREAMBLE_BYTES;
    private static final int MARKER_EOI = 217;
    private static final int MOTOROLA_TIFF_MAGIC_NUMBER = 19789;
    private static final int ORIENTATION_TAG_TYPE = 274;
    private static final int PNG_HEADER = -1991225785;
    private static final int SEGMENT_SOS = 218;
    private static final int SEGMENT_START_ID = 255;
    private static final String TAG = "ImageHeaderParser";
    private final StreamReader streamReader;

    public enum ImageType {
        ;
        
        private final boolean hasAlpha;

        private ImageType(boolean hasAlpha2) {
            String str = r8;
            int i = r9;
            this.hasAlpha = hasAlpha2;
        }

        public boolean hasAlpha() {
            return this.hasAlpha;
        }
    }

    static {
        byte[] bytes = new byte[0];
        try {
            bytes = JPEG_EXIF_SEGMENT_PREAMBLE.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            UnsupportedEncodingException unsupportedEncodingException = e;
        }
        JPEG_EXIF_SEGMENT_PREAMBLE_BYTES = bytes;
    }

    public ImageHeaderParser(InputStream is) {
        StreamReader streamReader2;
        new StreamReader(is);
        this.streamReader = streamReader2;
    }

    public boolean hasAlpha() throws IOException {
        return getType().hasAlpha();
    }

    public ImageType getType() throws IOException {
        int firstTwoBytes = this.streamReader.getUInt16();
        if (firstTwoBytes == EXIF_MAGIC_NUMBER) {
            return ImageType.JPEG;
        }
        int firstFourBytes = ((firstTwoBytes << 16) & SupportMenu.CATEGORY_MASK) | (this.streamReader.getUInt16() & 65535);
        if (firstFourBytes == PNG_HEADER) {
            long skip = this.streamReader.skip(21);
            return this.streamReader.getByte() >= 3 ? ImageType.PNG_A : ImageType.PNG;
        } else if ((firstFourBytes >> 8) == GIF_HEADER) {
            return ImageType.GIF;
        } else {
            return ImageType.UNKNOWN;
        }
    }

    public int getOrientation() throws IOException {
        RandomAccessReader randomAccessReader;
        if (!handles(this.streamReader.getUInt16())) {
            return -1;
        }
        byte[] exifData = getExifSegment();
        boolean hasJpegExifPreamble = exifData != null && exifData.length > JPEG_EXIF_SEGMENT_PREAMBLE_BYTES.length;
        if (hasJpegExifPreamble) {
            int i = 0;
            while (true) {
                if (i >= JPEG_EXIF_SEGMENT_PREAMBLE_BYTES.length) {
                    break;
                } else if (exifData[i] != JPEG_EXIF_SEGMENT_PREAMBLE_BYTES[i]) {
                    hasJpegExifPreamble = false;
                    break;
                } else {
                    i++;
                }
            }
        }
        if (!hasJpegExifPreamble) {
            return -1;
        }
        new RandomAccessReader(exifData);
        return parseExifSegment(randomAccessReader);
    }

    private byte[] getExifSegment() throws IOException {
        StringBuilder sb;
        short segmentType;
        int segmentLength;
        StringBuilder sb2;
        long skipped;
        StringBuilder sb3;
        do {
            short segmentId = this.streamReader.getUInt8();
            if (segmentId != 255) {
                if (Log.isLoggable(TAG, 3)) {
                    new StringBuilder();
                    int d = Log.d(TAG, sb.append("Unknown segmentId=").append(segmentId).toString());
                }
                return null;
            }
            segmentType = this.streamReader.getUInt8();
            if (segmentType == SEGMENT_SOS) {
                return null;
            }
            if (segmentType == MARKER_EOI) {
                if (Log.isLoggable(TAG, 3)) {
                    int d2 = Log.d(TAG, "Found MARKER_EOI in exif segment");
                }
                return null;
            }
            segmentLength = this.streamReader.getUInt16() - 2;
            if (segmentType != EXIF_SEGMENT_TYPE) {
                skipped = this.streamReader.skip((long) segmentLength);
            } else {
                byte[] segmentData = new byte[segmentLength];
                int read = this.streamReader.read(segmentData);
                if (read == segmentLength) {
                    return segmentData;
                }
                if (Log.isLoggable(TAG, 3)) {
                    new StringBuilder();
                    int d3 = Log.d(TAG, sb2.append("Unable to read segment data, type: ").append(segmentType).append(", length: ").append(segmentLength).append(", actually read: ").append(read).toString());
                }
                return null;
            }
        } while (skipped == ((long) segmentLength));
        if (Log.isLoggable(TAG, 3)) {
            new StringBuilder();
            int d4 = Log.d(TAG, sb3.append("Unable to skip enough data, type: ").append(segmentType).append(", wanted to skip: ").append(segmentLength).append(", but actually skipped: ").append(skipped).toString());
        }
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v19, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v14, resolved type: short} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int parseExifSegment(com.bumptech.glide.load.resource.bitmap.ImageHeaderParser.RandomAccessReader r17) {
        /*
            r0 = r17
            java.lang.String r13 = "Exif\u0000\u0000"
            int r13 = r13.length()
            r1 = r13
            r13 = r0
            r14 = r1
            short r13 = r13.getInt16(r14)
            r2 = r13
            r13 = r2
            r14 = 19789(0x4d4d, float:2.773E-41)
            if (r13 != r14) goto L_0x004d
            java.nio.ByteOrder r13 = java.nio.ByteOrder.BIG_ENDIAN
            r3 = r13
        L_0x0019:
            r13 = r0
            r14 = r3
            r13.order(r14)
            r13 = r0
            r14 = r1
            r15 = 4
            int r14 = r14 + 4
            int r13 = r13.getInt32(r14)
            r14 = r1
            int r13 = r13 + r14
            r4 = r13
            r13 = r0
            r14 = r4
            short r13 = r13.getInt16(r14)
            r5 = r13
            r13 = 0
            r10 = r13
        L_0x0033:
            r13 = r10
            r14 = r5
            if (r13 >= r14) goto L_0x0201
            r13 = r4
            r14 = r10
            int r13 = calcTagOffset(r13, r14)
            r6 = r13
            r13 = r0
            r14 = r6
            short r13 = r13.getInt16(r14)
            r7 = r13
            r13 = r7
            r14 = 274(0x112, float:3.84E-43)
            if (r13 == r14) goto L_0x0086
        L_0x004a:
            int r10 = r10 + 1
            goto L_0x0033
        L_0x004d:
            r13 = r2
            r14 = 18761(0x4949, float:2.629E-41)
            if (r13 != r14) goto L_0x0056
            java.nio.ByteOrder r13 = java.nio.ByteOrder.LITTLE_ENDIAN
            r3 = r13
            goto L_0x0019
        L_0x0056:
            java.lang.String r13 = "ImageHeaderParser"
            r14 = 3
            boolean r13 = android.util.Log.isLoggable(r13, r14)
            if (r13 == 0) goto L_0x0082
            java.lang.String r13 = "ImageHeaderParser"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r16 = r14
            r14 = r16
            r15 = r16
            r15.<init>()
            java.lang.String r15 = "Unknown endianness = "
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r2
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r14 = r14.toString()
            int r13 = android.util.Log.d(r13, r14)
        L_0x0082:
            java.nio.ByteOrder r13 = java.nio.ByteOrder.BIG_ENDIAN
            r3 = r13
            goto L_0x0019
        L_0x0086:
            r13 = r0
            r14 = r6
            r15 = 2
            int r14 = r14 + 2
            short r13 = r13.getInt16(r14)
            r8 = r13
            r13 = r8
            r14 = 1
            if (r13 < r14) goto L_0x0099
            r13 = r8
            r14 = 12
            if (r13 <= r14) goto L_0x00c6
        L_0x0099:
            java.lang.String r13 = "ImageHeaderParser"
            r14 = 3
            boolean r13 = android.util.Log.isLoggable(r13, r14)
            if (r13 == 0) goto L_0x004a
            java.lang.String r13 = "ImageHeaderParser"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r16 = r14
            r14 = r16
            r15 = r16
            r15.<init>()
            java.lang.String r15 = "Got invalid format code="
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r8
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r14 = r14.toString()
            int r13 = android.util.Log.d(r13, r14)
            goto L_0x004a
        L_0x00c6:
            r13 = r0
            r14 = r6
            r15 = 4
            int r14 = r14 + 4
            int r13 = r13.getInt32(r14)
            r9 = r13
            r13 = r9
            if (r13 >= 0) goto L_0x00e9
            java.lang.String r13 = "ImageHeaderParser"
            r14 = 3
            boolean r13 = android.util.Log.isLoggable(r13, r14)
            if (r13 == 0) goto L_0x004a
            java.lang.String r13 = "ImageHeaderParser"
            java.lang.String r14 = "Negative tiff component count"
            int r13 = android.util.Log.d(r13, r14)
            goto L_0x004a
        L_0x00e9:
            java.lang.String r13 = "ImageHeaderParser"
            r14 = 3
            boolean r13 = android.util.Log.isLoggable(r13, r14)
            if (r13 == 0) goto L_0x0139
            java.lang.String r13 = "ImageHeaderParser"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r16 = r14
            r14 = r16
            r15 = r16
            r15.<init>()
            java.lang.String r15 = "Got tagIndex="
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r10
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r15 = " tagType="
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r7
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r15 = " formatCode="
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r8
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r15 = " componentCount="
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r9
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r14 = r14.toString()
            int r13 = android.util.Log.d(r13, r14)
        L_0x0139:
            r13 = r9
            int[] r14 = BYTES_PER_FORMAT
            r15 = r8
            r14 = r14[r15]
            int r13 = r13 + r14
            r11 = r13
            r13 = r11
            r14 = 4
            if (r13 <= r14) goto L_0x0173
            java.lang.String r13 = "ImageHeaderParser"
            r14 = 3
            boolean r13 = android.util.Log.isLoggable(r13, r14)
            if (r13 == 0) goto L_0x004a
            java.lang.String r13 = "ImageHeaderParser"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r16 = r14
            r14 = r16
            r15 = r16
            r15.<init>()
            java.lang.String r15 = "Got byte count > 4, not orientation, continuing, formatCode="
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r8
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r14 = r14.toString()
            int r13 = android.util.Log.d(r13, r14)
            goto L_0x004a
        L_0x0173:
            r13 = r6
            r14 = 8
            int r13 = r13 + 8
            r12 = r13
            r13 = r12
            if (r13 < 0) goto L_0x0184
            r13 = r12
            r14 = r0
            int r14 = r14.length()
            if (r13 <= r14) goto L_0x01be
        L_0x0184:
            java.lang.String r13 = "ImageHeaderParser"
            r14 = 3
            boolean r13 = android.util.Log.isLoggable(r13, r14)
            if (r13 == 0) goto L_0x004a
            java.lang.String r13 = "ImageHeaderParser"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r16 = r14
            r14 = r16
            r15 = r16
            r15.<init>()
            java.lang.String r15 = "Illegal tagValueOffset="
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r12
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r15 = " tagType="
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r7
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r14 = r14.toString()
            int r13 = android.util.Log.d(r13, r14)
            goto L_0x004a
        L_0x01be:
            r13 = r11
            if (r13 < 0) goto L_0x01cb
            r13 = r12
            r14 = r11
            int r13 = r13 + r14
            r14 = r0
            int r14 = r14.length()
            if (r13 <= r14) goto L_0x01f9
        L_0x01cb:
            java.lang.String r13 = "ImageHeaderParser"
            r14 = 3
            boolean r13 = android.util.Log.isLoggable(r13, r14)
            if (r13 == 0) goto L_0x004a
            java.lang.String r13 = "ImageHeaderParser"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r16 = r14
            r14 = r16
            r15 = r16
            r15.<init>()
            java.lang.String r15 = "Illegal number of bytes for TI tag data tagType="
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r7
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r14 = r14.toString()
            int r13 = android.util.Log.d(r13, r14)
            goto L_0x004a
        L_0x01f9:
            r13 = r0
            r14 = r12
            short r13 = r13.getInt16(r14)
            r0 = r13
        L_0x0200:
            return r0
        L_0x0201:
            r13 = -1
            r0 = r13
            goto L_0x0200
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.ImageHeaderParser.parseExifSegment(com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$RandomAccessReader):int");
    }

    private static int calcTagOffset(int ifdOffset, int tagIndex) {
        return ifdOffset + 2 + (12 * tagIndex);
    }

    private static boolean handles(int i) {
        int imageMagicNumber = i;
        return (imageMagicNumber & EXIF_MAGIC_NUMBER) == EXIF_MAGIC_NUMBER || imageMagicNumber == MOTOROLA_TIFF_MAGIC_NUMBER || imageMagicNumber == INTEL_TIFF_MAGIC_NUMBER;
    }

    private static class RandomAccessReader {
        private final ByteBuffer data;

        public RandomAccessReader(byte[] data2) {
            this.data = ByteBuffer.wrap(data2);
            ByteBuffer order = this.data.order(ByteOrder.BIG_ENDIAN);
        }

        public void order(ByteOrder byteOrder) {
            ByteBuffer order = this.data.order(byteOrder);
        }

        public int length() {
            return this.data.array().length;
        }

        public int getInt32(int offset) {
            return this.data.getInt(offset);
        }

        public short getInt16(int offset) {
            return this.data.getShort(offset);
        }
    }

    private static class StreamReader {

        /* renamed from: is */
        private final InputStream f313is;

        public StreamReader(InputStream is) {
            this.f313is = is;
        }

        public int getUInt16() throws IOException {
            return ((this.f313is.read() << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (this.f313is.read() & 255);
        }

        public short getUInt8() throws IOException {
            return (short) (this.f313is.read() & 255);
        }

        public long skip(long j) throws IOException {
            long toSkip;
            long total = j;
            if (total < 0) {
                return 0;
            }
            long j2 = total;
            while (true) {
                toSkip = j2;
                if (toSkip <= 0) {
                    break;
                }
                long skipped = this.f313is.skip(toSkip);
                if (skipped > 0) {
                    j2 = toSkip - skipped;
                } else if (this.f313is.read() == -1) {
                    break;
                } else {
                    j2 = toSkip - 1;
                }
            }
            return total - toSkip;
        }

        public int read(byte[] bArr) throws IOException {
            int toRead;
            byte[] buffer = bArr;
            int length = buffer.length;
            while (true) {
                toRead = length;
                if (toRead <= 0) {
                    break;
                }
                int read = this.f313is.read(buffer, buffer.length - toRead, toRead);
                int read2 = read;
                if (read == -1) {
                    break;
                }
                length = toRead - read2;
            }
            return buffer.length - toRead;
        }

        public int getByte() throws IOException {
            return this.f313is.read();
        }
    }
}
