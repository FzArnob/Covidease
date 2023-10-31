package com.google.android.gms.common.data;

import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;

public final class DataBufferUtils {
    @KeepForSdk
    public static final String KEY_NEXT_PAGE_TOKEN = "next_page_token";
    @KeepForSdk
    public static final String KEY_PREV_PAGE_TOKEN = "prev_page_token";

    private DataBufferUtils() {
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T, E extends com.google.android.gms.common.data.Freezable<T>> java.util.ArrayList<T> freezeAndClose(com.google.android.gms.common.data.DataBuffer<E> r9) {
        /*
            r0 = r9
            java.util.ArrayList r5 = new java.util.ArrayList
            r8 = r5
            r5 = r8
            r6 = r8
            r7 = r0
            int r7 = r7.getCount()
            r6.<init>(r7)
            r1 = r5
            r5 = r0
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x0036 }
            r2 = r5
        L_0x0015:
            r5 = r2
            boolean r5 = r5.hasNext()     // Catch:{ all -> 0x0036 }
            if (r5 == 0) goto L_0x002f
            r5 = r2
            java.lang.Object r5 = r5.next()     // Catch:{ all -> 0x0036 }
            com.google.android.gms.common.data.Freezable r5 = (com.google.android.gms.common.data.Freezable) r5     // Catch:{ all -> 0x0036 }
            r3 = r5
            r5 = r1
            r6 = r3
            java.lang.Object r6 = r6.freeze()     // Catch:{ all -> 0x0036 }
            boolean r5 = r5.add(r6)     // Catch:{ all -> 0x0036 }
            goto L_0x0015
        L_0x002f:
            r5 = r0
            r5.close()
            r5 = r1
            r0 = r5
            return r0
        L_0x0036:
            r5 = move-exception
            r4 = r5
            r5 = r0
            r5.close()
            r5 = r4
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.data.DataBufferUtils.freezeAndClose(com.google.android.gms.common.data.DataBuffer):java.util.ArrayList");
    }

    public static boolean hasNextPage(DataBuffer<?> dataBuffer) {
        Bundle metadata = dataBuffer.getMetadata();
        return (metadata == null || metadata.getString(KEY_NEXT_PAGE_TOKEN) == null) ? false : true;
    }

    public static boolean hasPrevPage(DataBuffer<?> dataBuffer) {
        Bundle metadata = dataBuffer.getMetadata();
        return (metadata == null || metadata.getString(KEY_PREV_PAGE_TOKEN) == null) ? false : true;
    }

    public static boolean hasData(DataBuffer<?> dataBuffer) {
        DataBuffer<?> dataBuffer2 = dataBuffer;
        return dataBuffer2 != null && dataBuffer2.getCount() > 0;
    }
}
