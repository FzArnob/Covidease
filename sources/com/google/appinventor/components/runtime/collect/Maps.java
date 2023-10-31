package com.google.appinventor.components.runtime.collect;

import java.util.HashMap;
import java.util.TreeMap;

public class Maps {
    public Maps() {
    }

    public static <K, V> HashMap<K, V> newHashMap() {
        HashMap<K, V> hashMap;
        HashMap<K, V> hashMap2 = hashMap;
        new HashMap<>();
        return hashMap2;
    }

    public static <K, V> TreeMap<K, V> newTreeMap() {
        TreeMap<K, V> treeMap;
        TreeMap<K, V> treeMap2 = treeMap;
        new TreeMap<>();
        return treeMap2;
    }
}
