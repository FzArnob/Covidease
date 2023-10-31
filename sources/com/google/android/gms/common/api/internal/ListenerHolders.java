package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

@KeepForSdk
public class ListenerHolders {
    private final Set<ListenerHolder<?>> zajo;

    public ListenerHolders() {
        Map map;
        new WeakHashMap();
        this.zajo = Collections.newSetFromMap(map);
    }

    public final <L> ListenerHolder<L> zaa(@NonNull L l, @NonNull Looper looper, @NonNull String str) {
        ListenerHolder<L> createListenerHolder = createListenerHolder(l, looper, str);
        boolean add = this.zajo.add(createListenerHolder);
        return createListenerHolder;
    }

    public final void release() {
        for (ListenerHolder<?> clear : this.zajo) {
            clear.clear();
        }
        this.zajo.clear();
    }

    /* JADX WARNING: Multi-variable type inference failed */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <L> com.google.android.gms.common.api.internal.ListenerHolder<L> createListenerHolder(@android.support.annotation.NonNull L r9, @android.support.annotation.NonNull android.os.Looper r10, @android.support.annotation.NonNull java.lang.String r11) {
        /*
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r0
            java.lang.String r4 = "Listener must not be null"
            java.lang.Object r3 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r3, r4)
            r3 = r1
            java.lang.String r4 = "Looper must not be null"
            java.lang.Object r3 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r3, r4)
            r3 = r2
            java.lang.String r4 = "Listener type must not be null"
            java.lang.Object r3 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r3, r4)
            com.google.android.gms.common.api.internal.ListenerHolder r3 = new com.google.android.gms.common.api.internal.ListenerHolder
            r8 = r3
            r3 = r8
            r4 = r8
            r5 = r1
            r6 = r0
            r7 = r2
            r4.<init>(r5, r6, r7)
            r0 = r3
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.ListenerHolders.createListenerHolder(java.lang.Object, android.os.Looper, java.lang.String):com.google.android.gms.common.api.internal.ListenerHolder");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <L> com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey<L> createListenerKey(@android.support.annotation.NonNull L r7, @android.support.annotation.NonNull java.lang.String r8) {
        /*
            r0 = r7
            r1 = r8
            r2 = r0
            java.lang.String r3 = "Listener must not be null"
            java.lang.Object r2 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r2, r3)
            r2 = r1
            java.lang.String r3 = "Listener type must not be null"
            java.lang.Object r2 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r2, r3)
            r2 = r1
            java.lang.String r3 = "Listener type must not be empty"
            java.lang.String r2 = com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r2, r3)
            com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey r2 = new com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey
            r6 = r2
            r2 = r6
            r3 = r6
            r4 = r0
            r5 = r1
            r3.<init>(r4, r5)
            r0 = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.ListenerHolders.createListenerKey(java.lang.Object, java.lang.String):com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey");
    }
}
