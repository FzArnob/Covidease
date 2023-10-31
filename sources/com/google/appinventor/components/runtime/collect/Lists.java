package com.google.appinventor.components.runtime.collect;

import java.util.ArrayList;
import java.util.Collections;

public class Lists {
    public Lists() {
    }

    public static <E> ArrayList<E> newArrayList() {
        ArrayList<E> arrayList;
        ArrayList<E> arrayList2 = arrayList;
        new ArrayList<>();
        return arrayList2;
    }

    public static <E> ArrayList<E> newArrayList(E... eArr) {
        ArrayList arrayList;
        E[] eArr2 = eArr;
        new ArrayList(((eArr2.length * 110) / 100) + 5);
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = arrayList2;
        boolean addAll = Collections.addAll(arrayList2, eArr2);
        return arrayList3;
    }
}
