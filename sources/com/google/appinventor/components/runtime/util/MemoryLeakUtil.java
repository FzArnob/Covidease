package com.google.appinventor.components.runtime.util;

import android.util.Log;
import com.google.appinventor.components.runtime.collect.Maps;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryLeakUtil {
    private static final AtomicInteger hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
    private static final Map<String, WeakReference<Object>> oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS = Maps.newTreeMap();

    static {
        AtomicInteger atomicInteger;
        new AtomicInteger(0);
        hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = atomicInteger;
    }

    private MemoryLeakUtil() {
    }

    public static String trackObject(String str, Object obj) {
        StringBuilder sb;
        String sb2;
        Object obj2;
        StringBuilder sb3;
        String str2 = str;
        Object obj3 = obj;
        if (str2 == null) {
            new StringBuilder();
            sb2 = sb3.append(hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.incrementAndGet()).append("_").toString();
        } else {
            new StringBuilder();
            sb2 = sb.append(hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.incrementAndGet()).append("_").append(str2).toString();
        }
        String str3 = sb2;
        new WeakReference(obj3);
        WeakReference<Object> put = oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS.put(str3, obj2);
        return str3;
    }

    public static boolean isTrackedObjectCollected(String str, boolean z) {
        Throwable th;
        StringBuilder sb;
        String str2 = str;
        boolean z2 = z;
        System.gc();
        WeakReference weakReference = oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS.get(str2);
        WeakReference weakReference2 = weakReference;
        if (weakReference != null) {
            Object obj = weakReference2.get();
            String str3 = str2;
            new StringBuilder("Object with tag ");
            int i = Log.i("MemoryLeakUtil", sb.append(str3.substring(str3.indexOf("_") + 1)).append(" has ").append(obj != null ? "not " : "").append("been garbage collected.").toString());
            if (z2 && obj == null) {
                WeakReference<Object> remove = oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS.remove(str2);
            }
            if (obj == null) {
                return true;
            }
            return false;
        }
        Throwable th2 = th;
        new IllegalArgumentException("key not found");
        throw th2;
    }

    public static void checkAllTrackedObjects(boolean z, boolean z2) {
        StringBuilder sb;
        boolean z3 = z;
        boolean z4 = z2;
        int i = Log.i("MemoryLeakUtil", "Checking Tracked Objects ----------------------------------------");
        System.gc();
        int i2 = 0;
        int i3 = 0;
        Iterator<Map.Entry<String, WeakReference<Object>>> it = oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            String str = (String) next.getKey();
            Object obj = ((WeakReference) next.getValue()).get();
            Object obj2 = obj;
            if (obj != null) {
                i2++;
            } else {
                i3++;
                if (z4) {
                    it.remove();
                }
            }
            if (z3) {
                String str2 = str;
                new StringBuilder("Object with tag ");
                int i4 = Log.i("MemoryLeakUtil", sb.append(str2.substring(str2.indexOf("_") + 1)).append(" has ").append(obj2 != null ? "not " : "").append("been garbage collected.").toString());
            }
        }
        int i5 = Log.i("MemoryLeakUtil", "summary: collected ".concat(String.valueOf(i3)));
        int i6 = Log.i("MemoryLeakUtil", "summary: remaining ".concat(String.valueOf(i2)));
        int i7 = Log.i("MemoryLeakUtil", "-----------------------------------------------------------------");
    }
}
