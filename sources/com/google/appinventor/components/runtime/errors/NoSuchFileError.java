package com.google.appinventor.components.runtime.errors;

import com.google.appinventor.components.annotations.SimpleObject;

@SimpleObject
public class NoSuchFileError extends RuntimeError {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NoSuchFileError(String str) {
        super(str);
    }
}
