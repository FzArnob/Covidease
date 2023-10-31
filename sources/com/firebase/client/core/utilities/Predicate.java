package com.firebase.client.core.utilities;

public interface Predicate<T> {
    public static final Predicate<Object> TRUE;

    boolean evaluate(T t);

    static {
        Predicate<Object> predicate;
        new Predicate<Object>() {
            public boolean evaluate(Object obj) {
                Object obj2 = obj;
                return true;
            }
        };
        TRUE = predicate;
    }
}
