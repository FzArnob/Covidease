package org.shaded.apache.http.util;

public final class LangUtils {
    public static final int HASH_OFFSET = 37;
    public static final int HASH_SEED = 17;

    private LangUtils() {
    }

    public static int hashCode(int seed, int hashcode) {
        return (seed * 37) + hashcode;
    }

    public static int hashCode(int seed, boolean b) {
        return hashCode(seed, b ? 1 : 0);
    }

    public static int hashCode(int seed, Object obj) {
        Object obj2 = obj;
        return hashCode(seed, obj2 != null ? obj2.hashCode() : 0);
    }

    public static boolean equals(Object obj, Object obj2) {
        Object obj1 = obj;
        Object obj22 = obj2;
        return obj1 == null ? obj22 == null : obj1.equals(obj22);
    }

    public static boolean equals(Object[] objArr, Object[] objArr2) {
        Object[] a1 = objArr;
        Object[] a2 = objArr2;
        if (a1 == null) {
            if (a2 == null) {
                return true;
            }
            return false;
        } else if (a2 == null || a1.length != a2.length) {
            return false;
        } else {
            for (int i = 0; i < a1.length; i++) {
                if (!equals(a1[i], a2[i])) {
                    return false;
                }
            }
            return true;
        }
    }
}
