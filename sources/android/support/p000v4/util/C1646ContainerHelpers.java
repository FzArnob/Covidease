package android.support.p000v4.util;

/* renamed from: android.support.v4.util.ContainerHelpers  reason: case insensitive filesystem */
class C1646ContainerHelpers {
    static final int[] EMPTY_INTS = new int[0];
    static final long[] EMPTY_LONGS = new long[0];
    static final Object[] EMPTY_OBJECTS = new Object[0];

    public static int idealIntArraySize(int need) {
        return idealByteArraySize(need * 4) / 4;
    }

    public static int idealLongArraySize(int need) {
        return idealByteArraySize(need * 8) / 8;
    }

    public static int idealByteArraySize(int i) {
        int need = i;
        for (int i2 = 4; i2 < 32; i2++) {
            if (need <= (1 << i2) - 12) {
                return (1 << i2) - 12;
            }
        }
        return need;
    }

    public static boolean equal(Object obj, Object obj2) {
        Object a = obj;
        Object b = obj2;
        return a == b || (a != null && a.equals(b));
    }

    static int binarySearch(int[] iArr, int size, int i) {
        int[] array = iArr;
        int value = i;
        int lo = 0;
        int hi = size - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            int midVal = array[mid];
            if (midVal < value) {
                lo = mid + 1;
            } else if (midVal <= value) {
                return mid;
            } else {
                hi = mid - 1;
            }
        }
        return lo ^ -1;
    }

    static int binarySearch(long[] jArr, int size, long j) {
        long[] array = jArr;
        long value = j;
        int lo = 0;
        int hi = size - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            long midVal = array[mid];
            if (midVal < value) {
                lo = mid + 1;
            } else if (midVal <= value) {
                return mid;
            } else {
                hi = mid - 1;
            }
        }
        return lo ^ -1;
    }

    private C1646ContainerHelpers() {
    }
}
