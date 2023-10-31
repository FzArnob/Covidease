package com.google.android.gms.common.util;

import android.support.annotation.Nullable;
import android.support.p000v4.util.C1642ArrayMap;
import android.support.p000v4.util.C1643ArraySet;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@KeepForSdk
public final class CollectionUtils {
    private CollectionUtils() {
    }

    @KeepForSdk
    public static boolean isEmpty(@Nullable Collection<?> collection) {
        Collection<?> collection2 = collection;
        if (collection2 == null) {
            return true;
        }
        return collection2.isEmpty();
    }

    @KeepForSdk
    @Deprecated
    public static <T> List<T> listOf() {
        return Collections.emptyList();
    }

    @KeepForSdk
    @Deprecated
    public static <T> List<T> listOf(T t) {
        return Collections.singletonList(t);
    }

    @KeepForSdk
    @Deprecated
    public static <T> List<T> listOf(T... tArr) {
        T[] tArr2 = tArr;
        switch (tArr2.length) {
            case 0:
                return listOf();
            case 1:
                return listOf(tArr2[0]);
            default:
                return Collections.unmodifiableList(Arrays.asList(tArr2));
        }
    }

    private static <T> Set<T> zza(int i, boolean z) {
        Set<T> set;
        Set<T> set2;
        int i2 = i;
        boolean z2 = z;
        float f = z2 ? 0.75f : 1.0f;
        if (i2 <= (z2 ? 128 : 256)) {
            new C1643ArraySet(i2);
            return set2;
        }
        new HashSet(i2, f);
        return set;
    }

    @KeepForSdk
    @Deprecated
    public static <T> Set<T> setOf(T t, T t2, T t3) {
        Set zza = zza(3, false);
        Set set = zza;
        boolean add = zza.add(t);
        boolean add2 = set.add(t2);
        boolean add3 = set.add(t3);
        return Collections.unmodifiableSet(set);
    }

    @KeepForSdk
    @Deprecated
    public static <T> Set<T> setOf(T... tArr) {
        T[] tArr2 = tArr;
        switch (tArr2.length) {
            case 0:
                return Collections.emptySet();
            case 1:
                return Collections.singleton(tArr2[0]);
            case 2:
                T t = tArr2[0];
                T t2 = tArr2[1];
                Set zza = zza(2, false);
                Set set = zza;
                boolean add = zza.add(t);
                boolean add2 = set.add(t2);
                return Collections.unmodifiableSet(set);
            case 3:
                return setOf(tArr2[0], tArr2[1], tArr2[2]);
            case 4:
                T t3 = tArr2[0];
                T t4 = tArr2[1];
                T t5 = tArr2[2];
                Set zza2 = zza(4, false);
                Set set2 = zza2;
                boolean add3 = zza2.add(t3);
                boolean add4 = set2.add(t4);
                boolean add5 = set2.add(t5);
                boolean add6 = set2.add(tArr2[3]);
                return Collections.unmodifiableSet(set2);
            default:
                Set zza3 = zza(tArr2.length, false);
                Set set3 = zza3;
                boolean addAll = Collections.addAll(zza3, tArr2);
                return Collections.unmodifiableSet(set3);
        }
    }

    @KeepForSdk
    public static <T> Set<T> mutableSetOfWithSize(int i) {
        Set<T> set;
        int i2 = i;
        if (i2 != 0) {
            return zza(i2, true);
        }
        new C1643ArraySet();
        return set;
    }

    private static <K, V> Map<K, V> zzb(int i, boolean z) {
        Map<K, V> map;
        Map<K, V> map2;
        int i2 = i;
        boolean z2 = z;
        if (i2 <= 256) {
            new C1642ArrayMap(i2);
            return map2;
        }
        new HashMap(i2, 1.0f);
        return map;
    }

    @KeepForSdk
    public static <K, V> Map<K, V> mapOf(K k, V v, K k2, V v2, K k3, V v3) {
        Map zzb = zzb(3, false);
        Map map = zzb;
        Object put = zzb.put(k, v);
        Object put2 = map.put(k2, v2);
        Object put3 = map.put(k3, v3);
        return Collections.unmodifiableMap(map);
    }

    @KeepForSdk
    public static <K, V> Map<K, V> mapOf(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6) {
        Map zzb = zzb(6, false);
        Map map = zzb;
        Object put = zzb.put(k, v);
        Object put2 = map.put(k2, v2);
        Object put3 = map.put(k3, v3);
        Object put4 = map.put(k4, v4);
        Object put5 = map.put(k5, v5);
        Object put6 = map.put(k6, v6);
        return Collections.unmodifiableMap(map);
    }

    @KeepForSdk
    public static <K, V> Map<K, V> mapOfKeyValueArrays(K[] kArr, V[] vArr) {
        Throwable th;
        StringBuilder sb;
        K[] kArr2 = kArr;
        V[] vArr2 = vArr;
        V[] vArr3 = vArr2;
        K[] kArr3 = kArr2;
        K[] kArr4 = kArr3;
        if (kArr3.length != vArr3.length) {
            Throwable th2 = th;
            int length = kArr4.length;
            int length2 = vArr3.length;
            new StringBuilder(66);
            new IllegalArgumentException(sb.append("Key and values array lengths not equal: ").append(length).append(" != ").append(length2).toString());
            throw th2;
        }
        switch (kArr2.length) {
            case 0:
                return Collections.emptyMap();
            case 1:
                K k = kArr2[0];
                V v = vArr2[0];
                V v2 = v;
                return Collections.singletonMap(k, v);
            default:
                Map zzb = zzb(kArr2.length, false);
                Map map = zzb;
                V[] vArr4 = vArr2;
                K[] kArr5 = kArr2;
                Map map2 = zzb;
                for (int i = 0; i < kArr5.length; i++) {
                    Object put = map2.put(kArr5[i], vArr4[i]);
                }
                return Collections.unmodifiableMap(map);
        }
    }
}
