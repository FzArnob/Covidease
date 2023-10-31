package org.shaded.apache.commons.logging;

public class LogConfigurationException extends RuntimeException {
    protected Throwable cause;

    public LogConfigurationException() {
        this.cause = null;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LogConfigurationException(String message) {
        super(message);
        this.cause = null;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LogConfigurationException(java.lang.Throwable r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            if (r3 != 0) goto L_0x000c
            r3 = 0
        L_0x0007:
            r4 = r1
            r2.<init>(r3, r4)
            return
        L_0x000c:
            r3 = r1
            java.lang.String r3 = r3.toString()
            goto L_0x0007
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.commons.logging.LogConfigurationException.<init>(java.lang.Throwable):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LogConfigurationException(java.lang.String r8, java.lang.Throwable r9) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r0
            java.lang.StringBuffer r4 = new java.lang.StringBuffer
            r6 = r4
            r4 = r6
            r5 = r6
            r5.<init>()
            r5 = r1
            java.lang.StringBuffer r4 = r4.append(r5)
            java.lang.String r5 = " (Caused by "
            java.lang.StringBuffer r4 = r4.append(r5)
            r5 = r2
            java.lang.StringBuffer r4 = r4.append(r5)
            java.lang.String r5 = ")"
            java.lang.StringBuffer r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            r3 = r0
            r4 = 0
            r3.cause = r4
            r3 = r0
            r4 = r2
            r3.cause = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.commons.logging.LogConfigurationException.<init>(java.lang.String, java.lang.Throwable):void");
    }

    public Throwable getCause() {
        return this.cause;
    }
}
