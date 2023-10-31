package com.google.appinventor.components.runtime.util;

import android.support.annotation.NonNull;
import java.util.Iterator;

public interface YailObject<T> extends Iterable<T> {
    Object getObject(int i);

    boolean isEmpty();

    @NonNull
    Iterator<T> iterator();

    int size();
}
