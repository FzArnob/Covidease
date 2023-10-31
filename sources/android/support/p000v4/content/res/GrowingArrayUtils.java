package android.support.p000v4.content.res;

import java.lang.reflect.Array;

/* renamed from: android.support.v4.content.res.GrowingArrayUtils */
final class GrowingArrayUtils {
    static final /* synthetic */ boolean $assertionsDisabled = (!GrowingArrayUtils.class.desiredAssertionStatus());

    public static <T> T[] append(T[] tArr, int i, T t) {
        Throwable th;
        T[] array = tArr;
        int currentSize = i;
        T element = t;
        if ($assertionsDisabled || currentSize <= array.length) {
            if (currentSize + 1 > array.length) {
                T[] newArray = (Object[]) Array.newInstance(array.getClass().getComponentType(), growSize(currentSize));
                System.arraycopy(array, 0, newArray, 0, currentSize);
                array = newArray;
            }
            array[currentSize] = element;
            return array;
        }
        Throwable th2 = th;
        new AssertionError();
        throw th2;
    }

    public static int[] append(int[] iArr, int i, int i2) {
        Throwable th;
        int[] array = iArr;
        int currentSize = i;
        int element = i2;
        if ($assertionsDisabled || currentSize <= array.length) {
            if (currentSize + 1 > array.length) {
                int[] newArray = new int[growSize(currentSize)];
                System.arraycopy(array, 0, newArray, 0, currentSize);
                array = newArray;
            }
            array[currentSize] = element;
            return array;
        }
        Throwable th2 = th;
        new AssertionError();
        throw th2;
    }

    public static long[] append(long[] jArr, int i, long j) {
        Throwable th;
        long[] array = jArr;
        int currentSize = i;
        long element = j;
        if ($assertionsDisabled || currentSize <= array.length) {
            if (currentSize + 1 > array.length) {
                long[] newArray = new long[growSize(currentSize)];
                System.arraycopy(array, 0, newArray, 0, currentSize);
                array = newArray;
            }
            array[currentSize] = element;
            return array;
        }
        Throwable th2 = th;
        new AssertionError();
        throw th2;
    }

    public static boolean[] append(boolean[] zArr, int i, boolean z) {
        Throwable th;
        boolean[] array = zArr;
        int currentSize = i;
        boolean element = z;
        if ($assertionsDisabled || currentSize <= array.length) {
            if (currentSize + 1 > array.length) {
                boolean[] newArray = new boolean[growSize(currentSize)];
                System.arraycopy(array, 0, newArray, 0, currentSize);
                array = newArray;
            }
            array[currentSize] = element;
            return array;
        }
        Throwable th2 = th;
        new AssertionError();
        throw th2;
    }

    public static <T> T[] insert(T[] tArr, int i, int i2, T t) {
        Throwable th;
        T[] array = tArr;
        int currentSize = i;
        int index = i2;
        T element = t;
        if (!$assertionsDisabled && currentSize > array.length) {
            Throwable th2 = th;
            new AssertionError();
            throw th2;
        } else if (currentSize + 1 <= array.length) {
            System.arraycopy(array, index, array, index + 1, currentSize - index);
            array[index] = element;
            return array;
        } else {
            T[] newArray = (Object[]) Array.newInstance(array.getClass().getComponentType(), growSize(currentSize));
            System.arraycopy(array, 0, newArray, 0, index);
            newArray[index] = element;
            System.arraycopy(array, index, newArray, index + 1, array.length - index);
            return newArray;
        }
    }

    public static int[] insert(int[] iArr, int i, int i2, int i3) {
        Throwable th;
        int[] array = iArr;
        int currentSize = i;
        int index = i2;
        int element = i3;
        if (!$assertionsDisabled && currentSize > array.length) {
            Throwable th2 = th;
            new AssertionError();
            throw th2;
        } else if (currentSize + 1 <= array.length) {
            System.arraycopy(array, index, array, index + 1, currentSize - index);
            array[index] = element;
            return array;
        } else {
            int[] newArray = new int[growSize(currentSize)];
            System.arraycopy(array, 0, newArray, 0, index);
            newArray[index] = element;
            System.arraycopy(array, index, newArray, index + 1, array.length - index);
            return newArray;
        }
    }

    public static long[] insert(long[] jArr, int i, int i2, long j) {
        Throwable th;
        long[] array = jArr;
        int currentSize = i;
        int index = i2;
        long element = j;
        if (!$assertionsDisabled && currentSize > array.length) {
            Throwable th2 = th;
            new AssertionError();
            throw th2;
        } else if (currentSize + 1 <= array.length) {
            System.arraycopy(array, index, array, index + 1, currentSize - index);
            array[index] = element;
            return array;
        } else {
            long[] newArray = new long[growSize(currentSize)];
            System.arraycopy(array, 0, newArray, 0, index);
            newArray[index] = element;
            System.arraycopy(array, index, newArray, index + 1, array.length - index);
            return newArray;
        }
    }

    public static boolean[] insert(boolean[] zArr, int i, int i2, boolean z) {
        Throwable th;
        boolean[] array = zArr;
        int currentSize = i;
        int index = i2;
        boolean element = z;
        if (!$assertionsDisabled && currentSize > array.length) {
            Throwable th2 = th;
            new AssertionError();
            throw th2;
        } else if (currentSize + 1 <= array.length) {
            System.arraycopy(array, index, array, index + 1, currentSize - index);
            array[index] = element;
            return array;
        } else {
            boolean[] newArray = new boolean[growSize(currentSize)];
            System.arraycopy(array, 0, newArray, 0, index);
            newArray[index] = element;
            System.arraycopy(array, index, newArray, index + 1, array.length - index);
            return newArray;
        }
    }

    public static int growSize(int i) {
        int currentSize = i;
        return currentSize <= 4 ? 8 : currentSize * 2;
    }

    private GrowingArrayUtils() {
    }
}
