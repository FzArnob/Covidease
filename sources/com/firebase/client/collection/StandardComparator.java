package com.firebase.client.collection;

import java.lang.Comparable;
import java.util.Comparator;

public class StandardComparator<A extends Comparable<A>> implements Comparator<A> {
    private static StandardComparator INSTANCE;

    static {
        StandardComparator standardComparator;
        new StandardComparator();
        INSTANCE = standardComparator;
    }

    private StandardComparator() {
    }

    public static <T extends Comparable<T>> StandardComparator<T> getComparator(Class<T> cls) {
        Class<T> cls2 = cls;
        return INSTANCE;
    }

    public int compare(A o1, A o2) {
        return o1.compareTo(o2);
    }
}
