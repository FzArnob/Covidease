package com.shaded.fasterxml.jackson.databind.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public final class ArrayBuilders {
    private BooleanBuilder _booleanBuilder = null;
    private ByteBuilder _byteBuilder = null;
    private DoubleBuilder _doubleBuilder = null;
    private FloatBuilder _floatBuilder = null;
    private IntBuilder _intBuilder = null;
    private LongBuilder _longBuilder = null;
    private ShortBuilder _shortBuilder = null;

    public ArrayBuilders() {
    }

    public BooleanBuilder getBooleanBuilder() {
        BooleanBuilder booleanBuilder;
        if (this._booleanBuilder == null) {
            new BooleanBuilder();
            this._booleanBuilder = booleanBuilder;
        }
        return this._booleanBuilder;
    }

    public ByteBuilder getByteBuilder() {
        ByteBuilder byteBuilder;
        if (this._byteBuilder == null) {
            new ByteBuilder();
            this._byteBuilder = byteBuilder;
        }
        return this._byteBuilder;
    }

    public ShortBuilder getShortBuilder() {
        ShortBuilder shortBuilder;
        if (this._shortBuilder == null) {
            new ShortBuilder();
            this._shortBuilder = shortBuilder;
        }
        return this._shortBuilder;
    }

    public IntBuilder getIntBuilder() {
        IntBuilder intBuilder;
        if (this._intBuilder == null) {
            new IntBuilder();
            this._intBuilder = intBuilder;
        }
        return this._intBuilder;
    }

    public LongBuilder getLongBuilder() {
        LongBuilder longBuilder;
        if (this._longBuilder == null) {
            new LongBuilder();
            this._longBuilder = longBuilder;
        }
        return this._longBuilder;
    }

    public FloatBuilder getFloatBuilder() {
        FloatBuilder floatBuilder;
        if (this._floatBuilder == null) {
            new FloatBuilder();
            this._floatBuilder = floatBuilder;
        }
        return this._floatBuilder;
    }

    public DoubleBuilder getDoubleBuilder() {
        DoubleBuilder doubleBuilder;
        if (this._doubleBuilder == null) {
            new DoubleBuilder();
            this._doubleBuilder = doubleBuilder;
        }
        return this._doubleBuilder;
    }

    public static final class BooleanBuilder extends PrimitiveArrayBuilder<boolean[]> {
        public BooleanBuilder() {
        }

        public final boolean[] _constructArray(int i) {
            return new boolean[i];
        }
    }

    public static final class ByteBuilder extends PrimitiveArrayBuilder<byte[]> {
        public ByteBuilder() {
        }

        public final byte[] _constructArray(int i) {
            return new byte[i];
        }
    }

    public static final class ShortBuilder extends PrimitiveArrayBuilder<short[]> {
        public ShortBuilder() {
        }

        public final short[] _constructArray(int i) {
            return new short[i];
        }
    }

    public static final class IntBuilder extends PrimitiveArrayBuilder<int[]> {
        public IntBuilder() {
        }

        public final int[] _constructArray(int i) {
            return new int[i];
        }
    }

    public static final class LongBuilder extends PrimitiveArrayBuilder<long[]> {
        public LongBuilder() {
        }

        public final long[] _constructArray(int i) {
            return new long[i];
        }
    }

    public static final class FloatBuilder extends PrimitiveArrayBuilder<float[]> {
        public FloatBuilder() {
        }

        public final float[] _constructArray(int i) {
            return new float[i];
        }
    }

    public static final class DoubleBuilder extends PrimitiveArrayBuilder<double[]> {
        public DoubleBuilder() {
        }

        public final double[] _constructArray(int i) {
            return new double[i];
        }
    }

    public static Object getArrayComparator(Object obj) {
        Object obj2;
        Object obj3 = obj;
        int length = Array.getLength(obj3);
        final Class<?> cls = obj3.getClass();
        final int i = length;
        final Object obj4 = obj3;
        new Object() {
            /* JADX WARNING: type inference failed for: r8v0, types: [java.lang.Object] */
            /* JADX WARNING: Unknown variable types count: 1 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public boolean equals(java.lang.Object r8) {
                /*
                    r7 = this;
                    r0 = r7
                    r1 = r8
                    r5 = r1
                    r6 = r0
                    if (r5 != r6) goto L_0x0009
                    r5 = 1
                    r0 = r5
                L_0x0008:
                    return r0
                L_0x0009:
                    r5 = r1
                    if (r5 == 0) goto L_0x0016
                    r5 = r1
                    java.lang.Class r5 = r5.getClass()
                    r6 = r0
                    java.lang.Class r6 = r5
                    if (r5 == r6) goto L_0x0019
                L_0x0016:
                    r5 = 0
                    r0 = r5
                    goto L_0x0008
                L_0x0019:
                    r5 = r1
                    int r5 = java.lang.reflect.Array.getLength(r5)
                    r6 = r0
                    int r6 = r6
                    if (r5 == r6) goto L_0x0026
                    r5 = 0
                    r0 = r5
                    goto L_0x0008
                L_0x0026:
                    r5 = 0
                    r2 = r5
                L_0x0028:
                    r5 = r2
                    r6 = r0
                    int r6 = r6
                    if (r5 >= r6) goto L_0x0053
                    r5 = r0
                    java.lang.Object r5 = r7
                    r6 = r2
                    java.lang.Object r5 = java.lang.reflect.Array.get(r5, r6)
                    r3 = r5
                    r5 = r1
                    r6 = r2
                    java.lang.Object r5 = java.lang.reflect.Array.get(r5, r6)
                    r4 = r5
                    r5 = r3
                    r6 = r4
                    if (r5 != r6) goto L_0x0045
                L_0x0042:
                    int r2 = r2 + 1
                    goto L_0x0028
                L_0x0045:
                    r5 = r3
                    if (r5 == 0) goto L_0x0042
                    r5 = r3
                    r6 = r4
                    boolean r5 = r5.equals(r6)
                    if (r5 != 0) goto L_0x0042
                    r5 = 0
                    r0 = r5
                    goto L_0x0008
                L_0x0053:
                    r5 = 1
                    r0 = r5
                    goto L_0x0008
                */
                throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.util.ArrayBuilders.C14781.equals(java.lang.Object):boolean");
            }
        };
        return obj2;
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> java.util.HashSet<T> arrayToSet(T[] r9) {
        /*
            r0 = r9
            java.util.HashSet r6 = new java.util.HashSet
            r8 = r6
            r6 = r8
            r7 = r8
            r7.<init>()
            r1 = r6
            r6 = r0
            if (r6 == 0) goto L_0x0026
            r6 = r0
            r2 = r6
            r6 = r2
            int r6 = r6.length
            r3 = r6
            r6 = 0
            r4 = r6
        L_0x0014:
            r6 = r4
            r7 = r3
            if (r6 >= r7) goto L_0x0026
            r6 = r2
            r7 = r4
            r6 = r6[r7]
            r5 = r6
            r6 = r1
            r7 = r5
            boolean r6 = r6.add(r7)
            int r4 = r4 + 1
            goto L_0x0014
        L_0x0026:
            r6 = r1
            r0 = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.util.ArrayBuilders.arrayToSet(java.lang.Object[]):java.util.HashSet");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> java.util.ArrayList<T> arrayToList(T[] r9) {
        /*
            r0 = r9
            java.util.ArrayList r6 = new java.util.ArrayList
            r8 = r6
            r6 = r8
            r7 = r8
            r7.<init>()
            r1 = r6
            r6 = r0
            if (r6 == 0) goto L_0x0026
            r6 = r0
            r2 = r6
            r6 = r2
            int r6 = r6.length
            r3 = r6
            r6 = 0
            r4 = r6
        L_0x0014:
            r6 = r4
            r7 = r3
            if (r6 >= r7) goto L_0x0026
            r6 = r2
            r7 = r4
            r6 = r6[r7]
            r5 = r6
            r6 = r1
            r7 = r5
            boolean r6 = r6.add(r7)
            int r4 = r4 + 1
            goto L_0x0014
        L_0x0026:
            r6 = r1
            r0 = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.util.ArrayBuilders.arrayToList(java.lang.Object[]):java.util.ArrayList");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> java.util.HashSet<T> setAndArray(java.util.Set<T> r10, T[] r11) {
        /*
            r0 = r10
            r1 = r11
            java.util.HashSet r7 = new java.util.HashSet
            r9 = r7
            r7 = r9
            r8 = r9
            r8.<init>()
            r2 = r7
            r7 = r0
            if (r7 == 0) goto L_0x0014
            r7 = r2
            r8 = r0
            boolean r7 = r7.addAll(r8)
        L_0x0014:
            r7 = r1
            if (r7 == 0) goto L_0x0030
            r7 = r1
            r3 = r7
            r7 = r3
            int r7 = r7.length
            r4 = r7
            r7 = 0
            r5 = r7
        L_0x001e:
            r7 = r5
            r8 = r4
            if (r7 >= r8) goto L_0x0030
            r7 = r3
            r8 = r5
            r7 = r7[r8]
            r6 = r7
            r7 = r2
            r8 = r6
            boolean r7 = r7.add(r8)
            int r5 = r5 + 1
            goto L_0x001e
        L_0x0030:
            r7 = r2
            r0 = r7
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.util.ArrayBuilders.setAndArray(java.util.Set, java.lang.Object[]):java.util.HashSet");
    }

    public static <T> List<T> addToList(List<T> list, T t) {
        List<T> list2;
        List<T> list3 = list;
        T t2 = t;
        if (list3 == null) {
            new ArrayList();
            list3 = list2;
        }
        boolean add = list3.add(t2);
        return list3;
    }

    public static <T> T[] insertInList(T[] tArr, T t) {
        T[] tArr2 = tArr;
        T t2 = t;
        int length = tArr2.length;
        T[] tArr3 = (Object[]) Array.newInstance(tArr2.getClass().getComponentType(), length + 1);
        if (length > 0) {
            System.arraycopy(tArr2, 0, tArr3, 1, length);
        }
        tArr3[0] = t2;
        return tArr3;
    }

    public static <T> T[] insertInListNoDup(T[] tArr, T t) {
        T[] tArr2 = tArr;
        T t2 = t;
        int length = tArr2.length;
        int i = 0;
        while (i < length) {
            if (tArr2[i] != t2) {
                i++;
            } else if (i == 0) {
                return tArr2;
            } else {
                T[] tArr3 = (Object[]) Array.newInstance(tArr2.getClass().getComponentType(), length);
                System.arraycopy(tArr2, 0, tArr3, 1, i);
                tArr3[0] = t2;
                int i2 = i + 1;
                int i3 = length - i2;
                if (i3 > 0) {
                    System.arraycopy(tArr2, i2, tArr3, i2, i3);
                }
                return tArr3;
            }
        }
        T[] tArr4 = (Object[]) Array.newInstance(tArr2.getClass().getComponentType(), length + 1);
        if (length > 0) {
            System.arraycopy(tArr2, 0, tArr4, 1, length);
        }
        tArr4[0] = t2;
        return tArr4;
    }

    public static <T> Iterator<T> arrayAsIterator(T[] tArr) {
        Iterator<T> it;
        new ArrayIterator(tArr);
        return it;
    }

    public static <T> Iterable<T> arrayAsIterable(T[] tArr) {
        Iterable<T> iterable;
        new ArrayIterator(tArr);
        return iterable;
    }

    private static final class ArrayIterator<T> implements Iterator<T>, Iterable<T> {
        private final T[] _array;
        private int _index = 0;

        public ArrayIterator(T[] tArr) {
            this._array = tArr;
        }

        public boolean hasNext() {
            return this._index < this._array.length;
        }

        public T next() {
            Throwable th;
            if (this._index >= this._array.length) {
                Throwable th2 = th;
                new NoSuchElementException();
                throw th2;
            }
            T[] tArr = this._array;
            int i = this._index;
            int i2 = i + 1;
            this._index = i2;
            return tArr[i];
        }

        public void remove() {
            Throwable th;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }

        public Iterator<T> iterator() {
            return this;
        }
    }
}
