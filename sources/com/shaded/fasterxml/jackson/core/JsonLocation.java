package com.shaded.fasterxml.jackson.core;

import java.io.Serializable;

public class JsonLocation implements Serializable {

    /* renamed from: NA */
    public static final JsonLocation f285NA;
    private static final long serialVersionUID = 1;
    final int _columnNr;
    final int _lineNr;
    final transient Object _sourceRef;
    final long _totalBytes;
    final long _totalChars;

    static {
        JsonLocation jsonLocation;
        new JsonLocation("N/A", -1, -1, -1, -1);
        f285NA = jsonLocation;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public JsonLocation(Object obj, long j, int i, int i2) {
        this(obj, -1, j, i, i2);
    }

    public JsonLocation(Object obj, long j, long j2, int i, int i2) {
        this._sourceRef = obj;
        this._totalBytes = j;
        this._totalChars = j2;
        this._lineNr = i;
        this._columnNr = i2;
    }

    public Object getSourceRef() {
        return this._sourceRef;
    }

    public int getLineNr() {
        return this._lineNr;
    }

    public int getColumnNr() {
        return this._columnNr;
    }

    public long getCharOffset() {
        return this._totalChars;
    }

    public long getByteOffset() {
        return this._totalBytes;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder(80);
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append("[Source: ");
        if (this._sourceRef == null) {
            StringBuilder append2 = sb2.append("UNKNOWN");
        } else {
            StringBuilder append3 = sb2.append(this._sourceRef.toString());
        }
        StringBuilder append4 = sb2.append("; line: ");
        StringBuilder append5 = sb2.append(this._lineNr);
        StringBuilder append6 = sb2.append(", column: ");
        StringBuilder append7 = sb2.append(this._columnNr);
        StringBuilder append8 = sb2.append(']');
        return sb2.toString();
    }

    public int hashCode() {
        return ((((this._sourceRef == null ? 1 : this._sourceRef.hashCode()) ^ this._lineNr) + this._columnNr) ^ ((int) this._totalChars)) + ((int) this._totalBytes);
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r9) {
        /*
            r8 = this;
            r1 = r8
            r2 = r9
            r4 = r2
            r5 = r1
            if (r4 != r5) goto L_0x0009
            r4 = 1
            r1 = r4
        L_0x0008:
            return r1
        L_0x0009:
            r4 = r2
            if (r4 != 0) goto L_0x000f
            r4 = 0
            r1 = r4
            goto L_0x0008
        L_0x000f:
            r4 = r2
            boolean r4 = r4 instanceof com.shaded.fasterxml.jackson.core.JsonLocation
            if (r4 != 0) goto L_0x0017
            r4 = 0
            r1 = r4
            goto L_0x0008
        L_0x0017:
            r4 = r2
            com.shaded.fasterxml.jackson.core.JsonLocation r4 = (com.shaded.fasterxml.jackson.core.JsonLocation) r4
            r3 = r4
            r4 = r1
            java.lang.Object r4 = r4._sourceRef
            if (r4 != 0) goto L_0x0028
            r4 = r3
            java.lang.Object r4 = r4._sourceRef
            if (r4 == 0) goto L_0x0037
            r4 = 0
            r1 = r4
            goto L_0x0008
        L_0x0028:
            r4 = r1
            java.lang.Object r4 = r4._sourceRef
            r5 = r3
            java.lang.Object r5 = r5._sourceRef
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x0037
            r4 = 0
            r1 = r4
            goto L_0x0008
        L_0x0037:
            r4 = r1
            int r4 = r4._lineNr
            r5 = r3
            int r5 = r5._lineNr
            if (r4 != r5) goto L_0x0062
            r4 = r1
            int r4 = r4._columnNr
            r5 = r3
            int r5 = r5._columnNr
            if (r4 != r5) goto L_0x0062
            r4 = r1
            long r4 = r4._totalChars
            r6 = r3
            long r6 = r6._totalChars
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x0062
            r4 = r1
            long r4 = r4.getByteOffset()
            r6 = r3
            long r6 = r6.getByteOffset()
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x0062
            r4 = 1
        L_0x0060:
            r1 = r4
            goto L_0x0008
        L_0x0062:
            r4 = 0
            goto L_0x0060
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.JsonLocation.equals(java.lang.Object):boolean");
    }
}
