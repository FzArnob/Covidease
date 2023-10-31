package com.firebase.client;

import com.shaded.fasterxml.jackson.core.type.TypeReference;

public abstract class GenericTypeIndicator<T> extends TypeReference<T> {
    public GenericTypeIndicator() {
    }
}
