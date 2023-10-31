package com.bumptech.glide.load.engine.bitmap_recycle;

import java.util.Map;
import java.util.TreeMap;

class PrettyPrintTreeMap<K, V> extends TreeMap<K, V> {
    PrettyPrintTreeMap() {
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append("( ");
        for (Map.Entry<K, V> entry : entrySet()) {
            StringBuilder append2 = sb2.append('{').append(entry.getKey()).append(':').append(entry.getValue()).append("}, ");
        }
        if (!isEmpty()) {
            StringBuilder replace = sb2.replace(sb2.length() - 2, sb2.length(), "");
        }
        return sb2.append(" )").toString();
    }
}
