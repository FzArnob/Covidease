package com.google.appinventor.components.runtime.errors;

import com.google.appinventor.components.annotations.SimpleObject;

@SimpleObject
public class IllegalArgumentError extends RuntimeError {
    public IllegalArgumentError() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public IllegalArgumentError(String str) {
        super(str);
    }
}
