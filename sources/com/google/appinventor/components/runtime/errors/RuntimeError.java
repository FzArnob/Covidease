package com.google.appinventor.components.runtime.errors;

import com.google.appinventor.components.annotations.SimpleObject;

@SimpleObject
public abstract class RuntimeError extends RuntimeException {
    protected RuntimeError() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected RuntimeError(String str) {
        super(str);
    }

    public static RuntimeError convertToRuntimeError(Throwable th) {
        Throwable th2;
        RuntimeError runtimeError;
        RuntimeError runtimeError2;
        RuntimeError runtimeError3;
        Throwable th3 = th;
        if (th3 instanceof RuntimeError) {
            return (RuntimeError) th3;
        }
        if (th3 instanceof ArrayIndexOutOfBoundsException) {
            new ArrayIndexOutOfBoundsError();
            return runtimeError3;
        } else if (th3 instanceof IllegalArgumentException) {
            new IllegalArgumentError();
            return runtimeError2;
        } else if (th3 instanceof NullPointerException) {
            new UninitializedInstanceError();
            return runtimeError;
        } else {
            Throwable th4 = th2;
            new UnsupportedOperationException(th3);
            throw th4;
        }
    }
}
