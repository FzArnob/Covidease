package android.support.p000v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/* renamed from: android.support.v4.util.LruCache  reason: case insensitive filesystem */
public class C1648LruCache<K, V> {
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final LinkedHashMap<K, V> map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    public C1648LruCache(int i) {
        LinkedHashMap<K, V> linkedHashMap;
        Throwable th;
        int maxSize2 = i;
        if (maxSize2 <= 0) {
            Throwable th2 = th;
            new IllegalArgumentException("maxSize <= 0");
            throw th2;
        }
        this.maxSize = maxSize2;
        new LinkedHashMap<>(0, 0.75f, true);
        this.map = linkedHashMap;
    }

    /* JADX INFO: finally extract failed */
    public void resize(int i) {
        Throwable th;
        int maxSize2 = i;
        if (maxSize2 <= 0) {
            Throwable th2 = th;
            new IllegalArgumentException("maxSize <= 0");
            throw th2;
        }
        synchronized (this) {
            try {
                this.maxSize = maxSize2;
                trimToSize(maxSize2);
            } catch (Throwable th3) {
                while (true) {
                    Throwable th4 = th3;
                    throw th4;
                }
            }
        }
    }

    /* JADX INFO: finally extract failed */
    @Nullable
    public final V get(@NonNull K k) {
        Throwable th;
        K key = k;
        if (key == null) {
            Throwable th2 = th;
            new NullPointerException("key == null");
            throw th2;
        }
        synchronized (this) {
            try {
                C1648LruCache<K, V> mapValue = this.map.get(key);
                if (mapValue != null) {
                    this.hitCount++;
                    C1648LruCache<K, V> lruCache = mapValue;
                    return lruCache;
                }
                this.missCount++;
                C1648LruCache<K, V> createdValue = create(key);
                if (createdValue == null) {
                    return null;
                }
                synchronized (this) {
                    try {
                        this.createCount++;
                        C1648LruCache<K, V> mapValue2 = this.map.put(key, createdValue);
                        if (mapValue2 != null) {
                            Object put = this.map.put(key, mapValue2);
                        } else {
                            this.size += safeSizeOf(key, createdValue);
                        }
                        if (mapValue2 != null) {
                            entryRemoved(false, key, createdValue, mapValue2);
                            return mapValue2;
                        }
                        trimToSize(this.maxSize);
                        return createdValue;
                    } catch (Throwable th3) {
                        Throwable th4 = th3;
                        throw th4;
                    }
                }
            } catch (Throwable th5) {
                while (true) {
                    Throwable th6 = th5;
                    throw th6;
                }
            }
        }
    }

    /* JADX INFO: finally extract failed */
    @Nullable
    public final V put(@NonNull K k, @NonNull V v) {
        Throwable th;
        K key = k;
        V value = v;
        if (key == null || value == null) {
            Throwable th2 = th;
            new NullPointerException("key == null || value == null");
            throw th2;
        }
        synchronized (this) {
            try {
                this.putCount++;
                this.size += safeSizeOf(key, value);
                C1648LruCache<K, V> previous = this.map.put(key, value);
                if (previous != null) {
                    this.size -= safeSizeOf(key, previous);
                }
                if (previous != null) {
                    entryRemoved(false, key, previous, value);
                }
                trimToSize(this.maxSize);
                return previous;
            } catch (Throwable th3) {
                while (true) {
                    Throwable th4 = th3;
                    throw th4;
                }
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005a, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void trimToSize(int r14) {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
        L_0x0002:
            r7 = r0
            r12 = r7
            r7 = r12
            r8 = r12
            r4 = r8
            monitor-enter(r7)
            r7 = r0
            int r7 = r7.size     // Catch:{ all -> 0x0044 }
            if (r7 < 0) goto L_0x001b
            r7 = r0
            java.util.LinkedHashMap<K, V> r7 = r7.map     // Catch:{ all -> 0x0044 }
            boolean r7 = r7.isEmpty()     // Catch:{ all -> 0x0044 }
            if (r7 == 0) goto L_0x004a
            r7 = r0
            int r7 = r7.size     // Catch:{ all -> 0x0044 }
            if (r7 == 0) goto L_0x004a
        L_0x001b:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0044 }
            r12 = r7
            r7 = r12
            r8 = r12
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0044 }
            r12 = r9
            r9 = r12
            r10 = r12
            r10.<init>()     // Catch:{ all -> 0x0044 }
            r10 = r0
            java.lang.Class r10 = r10.getClass()     // Catch:{ all -> 0x0044 }
            java.lang.String r10 = r10.getName()     // Catch:{ all -> 0x0044 }
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ all -> 0x0044 }
            java.lang.String r10 = ".sizeOf() is reporting inconsistent results!"
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ all -> 0x0044 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x0044 }
            r8.<init>(r9)     // Catch:{ all -> 0x0044 }
            throw r7     // Catch:{ all -> 0x0044 }
        L_0x0044:
            r7 = move-exception
            r6 = r7
            r7 = r4
            monitor-exit(r7)     // Catch:{ all -> 0x0044 }
            r7 = r6
            throw r7
        L_0x004a:
            r7 = r0
            int r7 = r7.size     // Catch:{ all -> 0x0044 }
            r8 = r1
            if (r7 <= r8) goto L_0x0059
            r7 = r0
            java.util.LinkedHashMap<K, V> r7 = r7.map     // Catch:{ all -> 0x0044 }
            boolean r7 = r7.isEmpty()     // Catch:{ all -> 0x0044 }
            if (r7 == 0) goto L_0x005c
        L_0x0059:
            r7 = r4
            monitor-exit(r7)     // Catch:{ all -> 0x0044 }
            return
        L_0x005c:
            r7 = r0
            java.util.LinkedHashMap<K, V> r7 = r7.map     // Catch:{ all -> 0x0044 }
            java.util.Set r7 = r7.entrySet()     // Catch:{ all -> 0x0044 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x0044 }
            java.lang.Object r7 = r7.next()     // Catch:{ all -> 0x0044 }
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7     // Catch:{ all -> 0x0044 }
            r5 = r7
            r7 = r5
            java.lang.Object r7 = r7.getKey()     // Catch:{ all -> 0x0044 }
            r2 = r7
            r7 = r5
            java.lang.Object r7 = r7.getValue()     // Catch:{ all -> 0x0044 }
            r3 = r7
            r7 = r0
            java.util.LinkedHashMap<K, V> r7 = r7.map     // Catch:{ all -> 0x0044 }
            r8 = r2
            java.lang.Object r7 = r7.remove(r8)     // Catch:{ all -> 0x0044 }
            r7 = r0
            r12 = r7
            r7 = r12
            r8 = r12
            int r8 = r8.size     // Catch:{ all -> 0x0044 }
            r9 = r0
            r10 = r2
            r11 = r3
            int r9 = r9.safeSizeOf(r10, r11)     // Catch:{ all -> 0x0044 }
            int r8 = r8 - r9
            r7.size = r8     // Catch:{ all -> 0x0044 }
            r7 = r0
            r12 = r7
            r7 = r12
            r8 = r12
            int r8 = r8.evictionCount     // Catch:{ all -> 0x0044 }
            r9 = 1
            int r8 = r8 + 1
            r7.evictionCount = r8     // Catch:{ all -> 0x0044 }
            r7 = r4
            monitor-exit(r7)     // Catch:{ all -> 0x0044 }
            r7 = r0
            r8 = 1
            r9 = r2
            r10 = r3
            r11 = 0
            r7.entryRemoved(r8, r9, r10, r11)
            goto L_0x0002
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.util.C1648LruCache.trimToSize(int):void");
    }

    /* JADX INFO: finally extract failed */
    @Nullable
    public final V remove(@NonNull K k) {
        Throwable th;
        K key = k;
        if (key == null) {
            Throwable th2 = th;
            new NullPointerException("key == null");
            throw th2;
        }
        synchronized (this) {
            try {
                C1648LruCache<K, V> previous = this.map.remove(key);
                if (previous != null) {
                    this.size -= safeSizeOf(key, previous);
                }
                if (previous != null) {
                    entryRemoved(false, key, previous, (C1648LruCache<K, V>) null);
                }
                return previous;
            } catch (Throwable th3) {
                while (true) {
                    Throwable th4 = th3;
                    throw th4;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void entryRemoved(boolean evicted, @NonNull K k, @NonNull V v, @Nullable V v2) {
    }

    /* access modifiers changed from: protected */
    @Nullable
    public V create(@NonNull K k) {
        K k2 = k;
        return null;
    }

    private int safeSizeOf(K k, V v) {
        Throwable th;
        StringBuilder sb;
        K key = k;
        V value = v;
        int result = sizeOf(key, value);
        if (result >= 0) {
            return result;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Negative size: ").append(key).append("=").append(value).toString());
        throw th2;
    }

    /* access modifiers changed from: protected */
    public int sizeOf(@NonNull K k, @NonNull V v) {
        K k2 = k;
        V v2 = v;
        return 1;
    }

    public final void evictAll() {
        trimToSize(-1);
    }

    public final synchronized int size() {
        int i;
        synchronized (this) {
            i = this.size;
        }
        return i;
    }

    public final synchronized int maxSize() {
        int i;
        synchronized (this) {
            i = this.maxSize;
        }
        return i;
    }

    public final synchronized int hitCount() {
        int i;
        synchronized (this) {
            i = this.hitCount;
        }
        return i;
    }

    public final synchronized int missCount() {
        int i;
        synchronized (this) {
            i = this.missCount;
        }
        return i;
    }

    public final synchronized int createCount() {
        int i;
        synchronized (this) {
            i = this.createCount;
        }
        return i;
    }

    public final synchronized int putCount() {
        int i;
        synchronized (this) {
            i = this.putCount;
        }
        return i;
    }

    public final synchronized int evictionCount() {
        int i;
        synchronized (this) {
            i = this.evictionCount;
        }
        return i;
    }

    public final synchronized Map<K, V> snapshot() {
        Map<K, V> map2;
        Map<K, V> map3;
        synchronized (this) {
            Map<K, V> map4 = map2;
            new LinkedHashMap(this.map);
            map3 = map4;
        }
        return map3;
    }

    public final synchronized String toString() {
        String format;
        synchronized (this) {
            int accesses = this.hitCount + this.missCount;
            int hitPercent = accesses != 0 ? (100 * this.hitCount) / accesses : 0;
            Locale locale = Locale.US;
            Object[] objArr = new Object[4];
            objArr[0] = Integer.valueOf(this.maxSize);
            Object[] objArr2 = objArr;
            objArr2[1] = Integer.valueOf(this.hitCount);
            Object[] objArr3 = objArr2;
            objArr3[2] = Integer.valueOf(this.missCount);
            Object[] objArr4 = objArr3;
            objArr4[3] = Integer.valueOf(hitPercent);
            format = String.format(locale, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", objArr4);
        }
        return format;
    }
}
