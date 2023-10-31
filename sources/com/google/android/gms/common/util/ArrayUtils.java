package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@KeepForSdk
@VisibleForTesting
public final class ArrayUtils {
    @KeepForSdk
    public static <T> boolean contains(T[] tArr, T t) {
        int i;
        T t2 = t;
        T[] tArr2 = tArr;
        T[] tArr3 = tArr2;
        int length = tArr2 != null ? tArr3.length : 0;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                i = -1;
                break;
            } else if (Objects.equal(tArr3[i2], t2)) {
                i = i2;
                break;
            } else {
                i2++;
            }
        }
        if (i >= 0) {
            return true;
        }
        return false;
    }

    @KeepForSdk
    public static boolean contains(int[] iArr, int i) {
        int[] iArr2 = iArr;
        int i2 = i;
        if (iArr2 == null) {
            return false;
        }
        int[] iArr3 = iArr2;
        int[] iArr4 = iArr3;
        int length = iArr3.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (iArr4[i3] == i2) {
                return true;
            }
        }
        return false;
    }

    @KeepForSdk
    public static Integer[] toWrapperArray(int[] iArr) {
        int[] iArr2 = iArr;
        if (iArr2 == null) {
            return null;
        }
        int length = iArr2.length;
        int i = length;
        Integer[] numArr = new Integer[length];
        for (int i2 = 0; i2 < i; i2++) {
            numArr[i2] = Integer.valueOf(iArr2[i2]);
        }
        return numArr;
    }

    private ArrayUtils() {
    }

    @KeepForSdk
    public static <T> void writeArray(StringBuilder sb, T[] tArr) {
        StringBuilder sb2 = sb;
        T[] tArr2 = tArr;
        int length = tArr2.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                StringBuilder append = sb2.append(",");
            }
            StringBuilder append2 = sb2.append(tArr2[i].toString());
        }
    }

    @KeepForSdk
    public static void writeArray(StringBuilder sb, int[] iArr) {
        StringBuilder sb2 = sb;
        int[] iArr2 = iArr;
        int length = iArr2.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                StringBuilder append = sb2.append(",");
            }
            StringBuilder append2 = sb2.append(Integer.toString(iArr2[i]));
        }
    }

    @KeepForSdk
    public static void writeArray(StringBuilder sb, long[] jArr) {
        StringBuilder sb2 = sb;
        long[] jArr2 = jArr;
        int length = jArr2.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                StringBuilder append = sb2.append(",");
            }
            StringBuilder append2 = sb2.append(Long.toString(jArr2[i]));
        }
    }

    @KeepForSdk
    public static void writeArray(StringBuilder sb, float[] fArr) {
        StringBuilder sb2 = sb;
        float[] fArr2 = fArr;
        int length = fArr2.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                StringBuilder append = sb2.append(",");
            }
            StringBuilder append2 = sb2.append(Float.toString(fArr2[i]));
        }
    }

    @KeepForSdk
    public static void writeArray(StringBuilder sb, double[] dArr) {
        StringBuilder sb2 = sb;
        double[] dArr2 = dArr;
        int length = dArr2.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                StringBuilder append = sb2.append(",");
            }
            StringBuilder append2 = sb2.append(Double.toString(dArr2[i]));
        }
    }

    @KeepForSdk
    public static void writeArray(StringBuilder sb, boolean[] zArr) {
        StringBuilder sb2 = sb;
        boolean[] zArr2 = zArr;
        int length = zArr2.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                StringBuilder append = sb2.append(",");
            }
            StringBuilder append2 = sb2.append(Boolean.toString(zArr2[i]));
        }
    }

    @KeepForSdk
    public static void writeStringArray(StringBuilder sb, String[] strArr) {
        StringBuilder sb2 = sb;
        String[] strArr2 = strArr;
        int length = strArr2.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                StringBuilder append = sb2.append(",");
            }
            StringBuilder append2 = sb2.append("\"").append(strArr2[i]).append("\"");
        }
    }

    @KeepForSdk
    public static <T> T[] concat(T[]... tArr) {
        T[][] tArr2 = tArr;
        if (tArr2.length == 0) {
            return (Object[]) Array.newInstance(tArr2.getClass(), 0);
        }
        int i = 0;
        for (int i2 = 0; i2 < tArr2.length; i2++) {
            i += tArr2[i2].length;
        }
        T[] copyOf = Arrays.copyOf(tArr2[0], i);
        int length = tArr2[0].length;
        for (int i3 = 1; i3 < tArr2.length; i3++) {
            T[] tArr3 = tArr2[i3];
            T[] tArr4 = tArr3;
            System.arraycopy(tArr3, 0, copyOf, length, tArr4.length);
            length += tArr4.length;
        }
        return copyOf;
    }

    @KeepForSdk
    public static byte[] concatByteArrays(byte[]... bArr) {
        byte[][] bArr2 = bArr;
        if (bArr2.length == 0) {
            return new byte[0];
        }
        int i = 0;
        for (int i2 = 0; i2 < bArr2.length; i2++) {
            i += bArr2[i2].length;
        }
        byte[] copyOf = Arrays.copyOf(bArr2[0], i);
        int length = bArr2[0].length;
        for (int i3 = 1; i3 < bArr2.length; i3++) {
            byte[] bArr3 = bArr2[i3];
            byte[] bArr4 = bArr3;
            System.arraycopy(bArr3, 0, copyOf, length, bArr4.length);
            length += bArr4.length;
        }
        return copyOf;
    }

    @KeepForSdk
    public static <T> T[] appendToArray(T[] tArr, T t) {
        T[] copyOf;
        Throwable th;
        T[] tArr2 = tArr;
        T t2 = t;
        if (tArr2 == null && t2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Cannot generate array of generic type w/o class info");
            throw th2;
        }
        if (tArr2 == null) {
            copyOf = (Object[]) Array.newInstance(t2.getClass(), 1);
        } else {
            copyOf = Arrays.copyOf(tArr2, tArr2.length + 1);
        }
        copyOf[copyOf.length - 1] = t2;
        return copyOf;
    }

    @KeepForSdk
    public static <T> T[] removeAll(T[] tArr, T... tArr2) {
        T[] tArr3 = tArr;
        T[] tArr4 = tArr2;
        if (tArr3 == null) {
            return null;
        }
        if (tArr4 == null || tArr4.length == 0) {
            return Arrays.copyOf(tArr3, tArr3.length);
        }
        T[] tArr5 = (Object[]) Array.newInstance(tArr4.getClass().getComponentType(), tArr3.length);
        int i = 0;
        if (tArr4.length == 1) {
            T[] tArr6 = tArr3;
            T[] tArr7 = tArr6;
            int length = tArr6.length;
            for (int i2 = 0; i2 < length; i2++) {
                T t = tArr7[i2];
                if (!Objects.equal(tArr4[0], t)) {
                    int i3 = i;
                    i++;
                    tArr5[i3] = t;
                }
            }
        } else {
            T[] tArr8 = tArr3;
            T[] tArr9 = tArr8;
            int length2 = tArr8.length;
            for (int i4 = 0; i4 < length2; i4++) {
                T t2 = tArr9[i4];
                if (!contains(tArr4, t2)) {
                    int i5 = i;
                    i++;
                    tArr5[i5] = t2;
                }
            }
        }
        int i6 = i;
        T[] tArr10 = tArr5;
        T[] tArr11 = tArr10;
        if (tArr10 == null) {
            return null;
        }
        T[] tArr12 = tArr11;
        if (i6 != tArr11.length) {
            tArr12 = Arrays.copyOf(tArr11, i6);
        }
        return tArr12;
    }

    @KeepForSdk
    public static <T> ArrayList<T> newArrayList() {
        ArrayList<T> arrayList;
        ArrayList<T> arrayList2 = arrayList;
        new ArrayList<>();
        return arrayList2;
    }

    /* JADX WARNING: Multi-variable type inference failed */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> java.util.ArrayList<T> toArrayList(T[] r8) {
        /*
            r0 = r8
            r4 = r0
            int r4 = r4.length
            r1 = r4
            java.util.ArrayList r4 = new java.util.ArrayList
            r7 = r4
            r4 = r7
            r5 = r7
            r6 = r1
            r5.<init>(r6)
            r2 = r4
            r4 = 0
            r3 = r4
        L_0x0010:
            r4 = r3
            r5 = r1
            if (r4 >= r5) goto L_0x0020
            r4 = r2
            r5 = r0
            r6 = r3
            r5 = r5[r6]
            boolean r4 = r4.add(r5)
            int r3 = r3 + 1
            goto L_0x0010
        L_0x0020:
            r4 = r2
            r0 = r4
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.util.ArrayUtils.toArrayList(java.lang.Object[]):java.util.ArrayList");
    }

    @KeepForSdk
    public static int[] toPrimitiveArray(Collection<Integer> collection) {
        Collection<Integer> collection2 = collection;
        if (collection2 == null || collection2.size() == 0) {
            return new int[0];
        }
        int i = 0;
        int[] iArr = new int[collection2.size()];
        for (Integer intValue : collection2) {
            int i2 = i;
            i++;
            iArr[i2] = intValue.intValue();
        }
        return iArr;
    }
}
