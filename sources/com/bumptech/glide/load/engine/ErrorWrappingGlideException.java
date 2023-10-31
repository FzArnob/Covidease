package com.bumptech.glide.load.engine;

public class ErrorWrappingGlideException extends Exception {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ErrorWrappingGlideException(java.lang.Error r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r1
            if (r2 != 0) goto L_0x0016
            java.lang.NullPointerException r2 = new java.lang.NullPointerException
            r5 = r2
            r2 = r5
            r3 = r5
            java.lang.String r4 = "The causing error must not be null"
            r3.<init>(r4)
            throw r2
        L_0x0016:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.ErrorWrappingGlideException.<init>(java.lang.Error):void");
    }

    public Error getCause() {
        return (Error) super.getCause();
    }
}
