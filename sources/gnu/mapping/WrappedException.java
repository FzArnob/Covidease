package gnu.mapping;

public class WrappedException extends RuntimeException {
    public WrappedException() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WrappedException(String message) {
        super(message);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WrappedException(java.lang.Throwable r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            java.lang.String r3 = r3.toString()
            r4 = r1
            r2.<init>(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.WrappedException.<init>(java.lang.Throwable):void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WrappedException(String message, Throwable e) {
        super(message, e);
    }

    public Throwable getException() {
        return getCause();
    }

    public String toString() {
        return getMessage();
    }

    public static RuntimeException wrapIfNeeded(Throwable th) {
        RuntimeException runtimeException;
        Throwable ex = th;
        if (ex instanceof RuntimeException) {
            return (RuntimeException) ex;
        }
        new WrappedException(ex);
        return runtimeException;
    }
}
