package com.google.android.gms.maps.model;

public final class Dash extends PatternItem {
    public final float length;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Dash(float r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = 0
            r4 = r1
            r5 = 0
            float r4 = java.lang.Math.max(r4, r5)
            java.lang.Float r4 = java.lang.Float.valueOf(r4)
            r2.<init>(r3, r4)
            r2 = r0
            r3 = r1
            r4 = 0
            float r3 = java.lang.Math.max(r3, r4)
            r2.length = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.Dash.<init>(float):void");
    }

    public final String toString() {
        StringBuilder sb;
        float f = this.length;
        new StringBuilder(30);
        return sb.append("[Dash: length=").append(f).append("]").toString();
    }
}
