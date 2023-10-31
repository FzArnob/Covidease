package com.shaded.fasterxml.jackson.databind.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EmptyIterator<T> implements Iterator<T> {
    private static final EmptyIterator<?> instance;

    static {
        EmptyIterator<?> emptyIterator;
        new EmptyIterator<>();
        instance = emptyIterator;
    }

    private EmptyIterator() {
    }

    public static <T> Iterator<T> instance() {
        return instance;
    }

    public boolean hasNext() {
        return false;
    }

    public T next() {
        Throwable th;
        Throwable th2 = th;
        new NoSuchElementException();
        throw th2;
    }

    public void remove() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }
}
