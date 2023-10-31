package android.arch.core.internal;

import android.arch.core.internal.SafeIterableMap;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class FastSafeIterableMap<K, V> extends SafeIterableMap<K, V> {
    private HashMap<K, SafeIterableMap.Entry<K, V>> mHashMap;

    public FastSafeIterableMap() {
        HashMap<K, SafeIterableMap.Entry<K, V>> hashMap;
        new HashMap<>();
        this.mHashMap = hashMap;
    }

    /* access modifiers changed from: protected */
    public SafeIterableMap.Entry<K, V> get(K k) {
        return this.mHashMap.get(k);
    }

    public V putIfAbsent(@NonNull K k, @NonNull V v) {
        K key = k;
        V v2 = v;
        SafeIterableMap.Entry<K, V> current = get(key);
        if (current != null) {
            return current.mValue;
        }
        SafeIterableMap.Entry<K, V> put = this.mHashMap.put(key, put(key, v2));
        return null;
    }

    public V remove(@NonNull K k) {
        K key = k;
        FastSafeIterableMap<K, V> removed = super.remove(key);
        SafeIterableMap.Entry<K, V> remove = this.mHashMap.remove(key);
        return removed;
    }

    public boolean contains(K key) {
        return this.mHashMap.containsKey(key);
    }

    public Map.Entry<K, V> ceil(K k) {
        K k2 = k;
        if (contains(k2)) {
            return this.mHashMap.get(k2).mPrevious;
        }
        return null;
    }
}
