package com.google.android.gms.common.images;

public final class Size {
    private final int zane;
    private final int zanf;

    public Size(int i, int i2) {
        this.zane = i;
        this.zanf = i2;
    }

    public final int getWidth() {
        return this.zane;
    }

    public final int getHeight() {
        return this.zanf;
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r3 = r1
            if (r3 != 0) goto L_0x0008
            r3 = 0
            r0 = r3
        L_0x0007:
            return r0
        L_0x0008:
            r3 = r0
            r4 = r1
            if (r3 != r4) goto L_0x000f
            r3 = 1
            r0 = r3
            goto L_0x0007
        L_0x000f:
            r3 = r1
            boolean r3 = r3 instanceof com.google.android.gms.common.images.Size
            if (r3 == 0) goto L_0x002e
            r3 = r1
            com.google.android.gms.common.images.Size r3 = (com.google.android.gms.common.images.Size) r3
            r2 = r3
            r3 = r0
            int r3 = r3.zane
            r4 = r2
            int r4 = r4.zane
            if (r3 != r4) goto L_0x002b
            r3 = r0
            int r3 = r3.zanf
            r4 = r2
            int r4 = r4.zanf
            if (r3 != r4) goto L_0x002b
            r3 = 1
            r0 = r3
            goto L_0x0007
        L_0x002b:
            r3 = 0
            r0 = r3
            goto L_0x0007
        L_0x002e:
            r3 = 0
            r0 = r3
            goto L_0x0007
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.Size.equals(java.lang.Object):boolean");
    }

    public final String toString() {
        StringBuilder sb;
        int i = this.zane;
        int i2 = this.zanf;
        new StringBuilder(23);
        return sb.append(i).append("x").append(i2).toString();
    }

    private static NumberFormatException zah(String str) {
        Throwable th;
        StringBuilder sb;
        String str2 = str;
        Throwable th2 = th;
        new StringBuilder(16 + String.valueOf(str2).length());
        new NumberFormatException(sb.append("Invalid Size: \"").append(str2).append("\"").toString());
        throw th2;
    }

    public static Size parseSize(String str) throws NumberFormatException {
        Size size;
        Throwable th;
        String str2 = str;
        if (str2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("string must not be null");
            throw th2;
        }
        int indexOf = str2.indexOf(42);
        int i = indexOf;
        if (indexOf < 0) {
            i = str2.indexOf(120);
        }
        if (i < 0) {
            throw zah(str2);
        }
        try {
            Size size2 = size;
            new Size(Integer.parseInt(str2.substring(0, i)), Integer.parseInt(str2.substring(i + 1)));
            return size2;
        } catch (NumberFormatException e) {
            throw zah(str2);
        }
    }

    public final int hashCode() {
        return this.zanf ^ ((this.zane << 16) | (this.zane >>> 16));
    }
}
