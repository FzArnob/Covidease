package com.google.appinventor.components.runtime.errors;

import com.google.appinventor.components.annotations.SimpleObject;

@SimpleObject
public class AssertionFailure extends RuntimeError {
    public AssertionFailure() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AssertionFailure(String str) {
        super(str);
    }
}
