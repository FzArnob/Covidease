package com.google.appinventor.components.runtime.repackaged.org.json;

public class JSONException extends RuntimeException {
    private static final long serialVersionUID = 0;
    private Throwable cause;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JSONException(String message) {
        super(message);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public JSONException(java.lang.Throwable r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            java.lang.String r3 = r3.getMessage()
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            r2.cause = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.repackaged.org.json.JSONException.<init>(java.lang.Throwable):void");
    }

    public Throwable getCause() {
        return this.cause;
    }
}
