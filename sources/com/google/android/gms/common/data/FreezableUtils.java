package com.google.android.gms.common.data;

public final class FreezableUtils {
    public FreezableUtils() {
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T, E extends com.google.android.gms.common.data.Freezable<T>> java.util.ArrayList<T> freeze(java.util.ArrayList<E> r8) {
        /*
            r0 = r8
            java.util.ArrayList r4 = new java.util.ArrayList
            r7 = r4
            r4 = r7
            r5 = r7
            r6 = r0
            int r6 = r6.size()
            r5.<init>(r6)
            r1 = r4
            r4 = 0
            r2 = r4
            r4 = r0
            int r4 = r4.size()
            r3 = r4
        L_0x0017:
            r4 = r2
            r5 = r3
            if (r4 >= r5) goto L_0x002f
            r4 = r1
            r5 = r0
            r6 = r2
            java.lang.Object r5 = r5.get(r6)
            com.google.android.gms.common.data.Freezable r5 = (com.google.android.gms.common.data.Freezable) r5
            java.lang.Object r5 = r5.freeze()
            boolean r4 = r4.add(r5)
            int r2 = r2 + 1
            goto L_0x0017
        L_0x002f:
            r4 = r1
            r0 = r4
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.data.FreezableUtils.freeze(java.util.ArrayList):java.util.ArrayList");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T, E extends com.google.android.gms.common.data.Freezable<T>> java.util.ArrayList<T> freeze(E[] r7) {
        /*
            r0 = r7
            java.util.ArrayList r3 = new java.util.ArrayList
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r0
            int r5 = r5.length
            r4.<init>(r5)
            r1 = r3
            r3 = 0
            r2 = r3
        L_0x000e:
            r3 = r2
            r4 = r0
            int r4 = r4.length
            if (r3 >= r4) goto L_0x0023
            r3 = r1
            r4 = r0
            r5 = r2
            r4 = r4[r5]
            java.lang.Object r4 = r4.freeze()
            boolean r3 = r3.add(r4)
            int r2 = r2 + 1
            goto L_0x000e
        L_0x0023:
            r3 = r1
            r0 = r3
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.data.FreezableUtils.freeze(com.google.android.gms.common.data.Freezable[]):java.util.ArrayList");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T, E extends com.google.android.gms.common.data.Freezable<T>> java.util.ArrayList<T> freezeIterable(java.lang.Iterable<E> r7) {
        /*
            r0 = r7
            java.util.ArrayList r4 = new java.util.ArrayList
            r6 = r4
            r4 = r6
            r5 = r6
            r5.<init>()
            r1 = r4
            r4 = r0
            java.util.Iterator r4 = r4.iterator()
            r2 = r4
        L_0x0010:
            r4 = r2
            boolean r4 = r4.hasNext()
            if (r4 == 0) goto L_0x002a
            r4 = r2
            java.lang.Object r4 = r4.next()
            com.google.android.gms.common.data.Freezable r4 = (com.google.android.gms.common.data.Freezable) r4
            r3 = r4
            r4 = r1
            r5 = r3
            java.lang.Object r5 = r5.freeze()
            boolean r4 = r4.add(r5)
            goto L_0x0010
        L_0x002a:
            r4 = r1
            r0 = r4
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.data.FreezableUtils.freezeIterable(java.lang.Iterable):java.util.ArrayList");
    }
}
