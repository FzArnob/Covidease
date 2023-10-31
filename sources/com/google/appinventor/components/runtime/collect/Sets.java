package com.google.appinventor.components.runtime.collect;

import java.util.Collections;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class Sets {
    public Sets() {
    }

    public static <K> HashSet<K> newHashSet() {
        HashSet<K> hashSet;
        HashSet<K> hashSet2 = hashSet;
        new HashSet<>();
        return hashSet2;
    }

    public static <E> HashSet<E> newHashSet(E... eArr) {
        HashSet hashSet;
        E[] eArr2 = eArr;
        new HashSet(((eArr2.length << 2) / 3) + 1);
        HashSet hashSet2 = hashSet;
        HashSet hashSet3 = hashSet2;
        boolean addAll = Collections.addAll(hashSet2, eArr2);
        return hashSet3;
    }

    public static <E> SortedSet<E> newSortedSet(E... eArr) {
        SortedSet<E> sortedSet;
        new TreeSet();
        SortedSet<E> sortedSet2 = sortedSet;
        SortedSet<E> sortedSet3 = sortedSet2;
        boolean addAll = Collections.addAll(sortedSet2, eArr);
        return sortedSet3;
    }
}
