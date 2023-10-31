package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.internal.view.SupportMenu;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SafeParcelReader {

    public static class ParseException extends RuntimeException {
        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public ParseException(java.lang.String r12, android.os.Parcel r13) {
            /*
                r11 = this;
                r0 = r11
                r1 = r12
                r2 = r13
                r5 = r0
                r6 = r2
                int r6 = r6.dataPosition()
                r3 = r6
                r6 = r2
                int r6 = r6.dataSize()
                r4 = r6
                r6 = 41
                r7 = r1
                java.lang.String r7 = java.lang.String.valueOf(r7)
                int r7 = r7.length()
                int r6 = r6 + r7
                java.lang.StringBuilder r7 = new java.lang.StringBuilder
                r9 = r6
                r10 = r7
                r6 = r10
                r7 = r9
                r8 = r10
                r9 = r7
                r10 = r8
                r7 = r10
                r8 = r9
                r7.<init>(r8)
                r7 = r1
                java.lang.StringBuilder r6 = r6.append(r7)
                java.lang.String r7 = " Parcel: pos="
                java.lang.StringBuilder r6 = r6.append(r7)
                r7 = r3
                java.lang.StringBuilder r6 = r6.append(r7)
                java.lang.String r7 = " size="
                java.lang.StringBuilder r6 = r6.append(r7)
                r7 = r4
                java.lang.StringBuilder r6 = r6.append(r7)
                java.lang.String r6 = r6.toString()
                r5.<init>(r6)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException.<init>(java.lang.String, android.os.Parcel):void");
        }
    }

    private SafeParcelReader() {
    }

    public static int readHeader(Parcel parcel) {
        return parcel.readInt();
    }

    public static int getFieldId(int i) {
        return i & 65535;
    }

    public static int readSize(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int i2 = i;
        if ((i2 & SupportMenu.CATEGORY_MASK) != -65536) {
            return (i2 >> 16) & 65535;
        }
        return parcel2.readInt();
    }

    public static void skipUnknownField(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        parcel2.setDataPosition(parcel2.dataPosition() + readSize(parcel2, i));
    }

    private static void zza(Parcel parcel, int i, int i2) {
        Throwable th;
        StringBuilder sb;
        Parcel parcel2 = parcel;
        int i3 = i2;
        int readSize = readSize(parcel2, i);
        int i4 = readSize;
        if (readSize != i3) {
            Throwable th2 = th;
            String hexString = Integer.toHexString(i4);
            new StringBuilder(46 + String.valueOf(hexString).length());
            new ParseException(sb.append("Expected size ").append(i3).append(" got ").append(i4).append(" (0x").append(hexString).append(")").toString(), parcel2);
            throw th2;
        }
    }

    private static void zza(Parcel parcel, int i, int i2, int i3) {
        Throwable th;
        StringBuilder sb;
        Parcel parcel2 = parcel;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        if (i5 != i6) {
            Throwable th2 = th;
            String hexString = Integer.toHexString(i5);
            new StringBuilder(46 + String.valueOf(hexString).length());
            new ParseException(sb.append("Expected size ").append(i6).append(" got ").append(i5).append(" (0x").append(hexString).append(")").toString(), parcel2);
            throw th2;
        }
    }

    public static int validateObjectHeader(Parcel parcel) {
        Throwable th;
        StringBuilder sb;
        String str;
        String str2;
        Parcel parcel2 = parcel;
        int readHeader = readHeader(parcel2);
        int readSize = readSize(parcel2, readHeader);
        int dataPosition = parcel2.dataPosition();
        if (getFieldId(readHeader) != 20293) {
            ParseException parseException = r10;
            String valueOf = String.valueOf(Integer.toHexString(readHeader));
            String str3 = valueOf;
            if (valueOf.length() != 0) {
                str2 = "Expected object header. Got 0x".concat(str3);
            } else {
                str2 = str;
                new String("Expected object header. Got 0x");
            }
            ParseException parseException2 = new ParseException(str2, parcel2);
            throw parseException;
        }
        int i = dataPosition + readSize;
        int i2 = i;
        if (i >= dataPosition && i2 <= parcel2.dataSize()) {
            return i2;
        }
        Throwable th2 = th;
        new StringBuilder(54);
        new ParseException(sb.append("Size read is invalid start=").append(dataPosition).append(" end=").append(i2).toString(), parcel2);
        throw th2;
    }

    public static boolean readBoolean(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        zza(parcel2, i, 4);
        return parcel2.readInt() != 0;
    }

    public static Boolean readBooleanObject(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int i2 = i;
        int readSize = readSize(parcel2, i2);
        int i3 = readSize;
        if (readSize == 0) {
            return null;
        }
        zza(parcel2, i2, i3, 4);
        return Boolean.valueOf(parcel2.readInt() != 0);
    }

    public static byte readByte(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        zza(parcel2, i, 4);
        return (byte) parcel2.readInt();
    }

    public static char readChar(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        zza(parcel2, i, 4);
        return (char) parcel2.readInt();
    }

    public static short readShort(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        zza(parcel2, i, 4);
        return (short) parcel2.readInt();
    }

    public static int readInt(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        zza(parcel2, i, 4);
        return parcel2.readInt();
    }

    public static Integer readIntegerObject(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int i2 = i;
        int readSize = readSize(parcel2, i2);
        int i3 = readSize;
        if (readSize == 0) {
            return null;
        }
        zza(parcel2, i2, i3, 4);
        return Integer.valueOf(parcel2.readInt());
    }

    public static long readLong(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        zza(parcel2, i, 8);
        return parcel2.readLong();
    }

    public static Long readLongObject(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int i2 = i;
        int readSize = readSize(parcel2, i2);
        int i3 = readSize;
        if (readSize == 0) {
            return null;
        }
        zza(parcel2, i2, i3, 8);
        return Long.valueOf(parcel2.readLong());
    }

    public static BigInteger createBigInteger(Parcel parcel, int i) {
        BigInteger bigInteger;
        Parcel parcel2 = parcel;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        byte[] createByteArray = parcel2.createByteArray();
        parcel2.setDataPosition(dataPosition + readSize);
        new BigInteger(createByteArray);
        return bigInteger;
    }

    public static float readFloat(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        zza(parcel2, i, 4);
        return parcel2.readFloat();
    }

    public static Float readFloatObject(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int i2 = i;
        int readSize = readSize(parcel2, i2);
        int i3 = readSize;
        if (readSize == 0) {
            return null;
        }
        zza(parcel2, i2, i3, 4);
        return Float.valueOf(parcel2.readFloat());
    }

    public static double readDouble(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        zza(parcel2, i, 8);
        return parcel2.readDouble();
    }

    public static Double readDoubleObject(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int i2 = i;
        int readSize = readSize(parcel2, i2);
        int i3 = readSize;
        if (readSize == 0) {
            return null;
        }
        zza(parcel2, i2, i3, 8);
        return Double.valueOf(parcel2.readDouble());
    }

    public static BigDecimal createBigDecimal(Parcel parcel, int i) {
        BigDecimal bigDecimal;
        BigInteger bigInteger;
        Parcel parcel2 = parcel;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        byte[] createByteArray = parcel2.createByteArray();
        int readInt = parcel2.readInt();
        parcel2.setDataPosition(dataPosition + readSize);
        new BigInteger(createByteArray);
        new BigDecimal(bigInteger, readInt);
        return bigDecimal;
    }

    public static String createString(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        String readString = parcel2.readString();
        parcel2.setDataPosition(dataPosition + readSize);
        return readString;
    }

    public static IBinder readIBinder(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        IBinder readStrongBinder = parcel2.readStrongBinder();
        parcel2.setDataPosition(dataPosition + readSize);
        return readStrongBinder;
    }

    public static <T extends Parcelable> T createParcelable(Parcel parcel, int i, Parcelable.Creator<T> creator) {
        Parcel parcel2 = parcel;
        Parcelable.Creator<T> creator2 = creator;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        T t = (Parcelable) creator2.createFromParcel(parcel2);
        parcel2.setDataPosition(dataPosition + readSize);
        return t;
    }

    public static Bundle createBundle(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        Bundle readBundle = parcel2.readBundle();
        parcel2.setDataPosition(dataPosition + readSize);
        return readBundle;
    }

    public static byte[] createByteArray(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        byte[] createByteArray = parcel2.createByteArray();
        parcel2.setDataPosition(dataPosition + readSize);
        return createByteArray;
    }

    public static byte[][] createByteArrayArray(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        int readInt = parcel2.readInt();
        int i2 = readInt;
        byte[][] bArr = new byte[readInt][];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = parcel2.createByteArray();
        }
        parcel2.setDataPosition(dataPosition + readSize);
        return bArr;
    }

    public static boolean[] createBooleanArray(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        boolean[] createBooleanArray = parcel2.createBooleanArray();
        parcel2.setDataPosition(dataPosition + readSize);
        return createBooleanArray;
    }

    public static char[] createCharArray(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        char[] createCharArray = parcel2.createCharArray();
        parcel2.setDataPosition(dataPosition + readSize);
        return createCharArray;
    }

    public static int[] createIntArray(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        int[] createIntArray = parcel2.createIntArray();
        parcel2.setDataPosition(dataPosition + readSize);
        return createIntArray;
    }

    public static long[] createLongArray(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        long[] createLongArray = parcel2.createLongArray();
        parcel2.setDataPosition(dataPosition + readSize);
        return createLongArray;
    }

    public static BigInteger[] createBigIntegerArray(Parcel parcel, int i) {
        BigInteger bigInteger;
        Parcel parcel2 = parcel;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        int readInt = parcel2.readInt();
        int i2 = readInt;
        BigInteger[] bigIntegerArr = new BigInteger[readInt];
        for (int i3 = 0; i3 < i2; i3++) {
            new BigInteger(parcel2.createByteArray());
            bigIntegerArr[i3] = bigInteger;
        }
        parcel2.setDataPosition(dataPosition + readSize);
        return bigIntegerArr;
    }

    public static float[] createFloatArray(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        float[] createFloatArray = parcel2.createFloatArray();
        parcel2.setDataPosition(dataPosition + readSize);
        return createFloatArray;
    }

    public static double[] createDoubleArray(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        double[] createDoubleArray = parcel2.createDoubleArray();
        parcel2.setDataPosition(dataPosition + readSize);
        return createDoubleArray;
    }

    public static BigDecimal[] createBigDecimalArray(Parcel parcel, int i) {
        BigDecimal bigDecimal;
        BigInteger bigInteger;
        Parcel parcel2 = parcel;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        int readInt = parcel2.readInt();
        int i2 = readInt;
        BigDecimal[] bigDecimalArr = new BigDecimal[readInt];
        for (int i3 = 0; i3 < i2; i3++) {
            byte[] createByteArray = parcel2.createByteArray();
            int readInt2 = parcel2.readInt();
            new BigInteger(createByteArray);
            new BigDecimal(bigInteger, readInt2);
            bigDecimalArr[i3] = bigDecimal;
        }
        parcel2.setDataPosition(dataPosition + readSize);
        return bigDecimalArr;
    }

    public static String[] createStringArray(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        String[] createStringArray = parcel2.createStringArray();
        parcel2.setDataPosition(dataPosition + readSize);
        return createStringArray;
    }

    public static IBinder[] createIBinderArray(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        IBinder[] createBinderArray = parcel2.createBinderArray();
        parcel2.setDataPosition(dataPosition + readSize);
        return createBinderArray;
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<java.lang.Boolean> createBooleanList(android.os.Parcel r11, int r12) {
        /*
            r0 = r11
            r1 = r12
            r7 = r0
            r8 = r1
            int r7 = readSize(r7, r8)
            r2 = r7
            r7 = r0
            int r7 = r7.dataPosition()
            r3 = r7
            r7 = r2
            if (r7 != 0) goto L_0x0015
            r7 = 0
            r0 = r7
        L_0x0014:
            return r0
        L_0x0015:
            java.util.ArrayList r7 = new java.util.ArrayList
            r10 = r7
            r7 = r10
            r8 = r10
            r8.<init>()
            r4 = r7
            r7 = r0
            int r7 = r7.readInt()
            r5 = r7
            r7 = 0
            r6 = r7
        L_0x0026:
            r7 = r6
            r8 = r5
            if (r7 >= r8) goto L_0x0040
            r7 = r4
            r8 = r0
            int r8 = r8.readInt()
            if (r8 == 0) goto L_0x003e
            r8 = 1
        L_0x0033:
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)
            boolean r7 = r7.add(r8)
            int r6 = r6 + 1
            goto L_0x0026
        L_0x003e:
            r8 = 0
            goto L_0x0033
        L_0x0040:
            r7 = r0
            r8 = r3
            r9 = r2
            int r8 = r8 + r9
            r7.setDataPosition(r8)
            r7 = r4
            r0 = r7
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBooleanList(android.os.Parcel, int):java.util.ArrayList");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<java.lang.Integer> createIntegerList(android.os.Parcel r11, int r12) {
        /*
            r0 = r11
            r1 = r12
            r7 = r0
            r8 = r1
            int r7 = readSize(r7, r8)
            r2 = r7
            r7 = r0
            int r7 = r7.dataPosition()
            r3 = r7
            r7 = r2
            if (r7 != 0) goto L_0x0015
            r7 = 0
            r0 = r7
        L_0x0014:
            return r0
        L_0x0015:
            java.util.ArrayList r7 = new java.util.ArrayList
            r10 = r7
            r7 = r10
            r8 = r10
            r8.<init>()
            r4 = r7
            r7 = r0
            int r7 = r7.readInt()
            r5 = r7
            r7 = 0
            r6 = r7
        L_0x0026:
            r7 = r6
            r8 = r5
            if (r7 >= r8) goto L_0x003b
            r7 = r4
            r8 = r0
            int r8 = r8.readInt()
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            boolean r7 = r7.add(r8)
            int r6 = r6 + 1
            goto L_0x0026
        L_0x003b:
            r7 = r0
            r8 = r3
            r9 = r2
            int r8 = r8 + r9
            r7.setDataPosition(r8)
            r7 = r4
            r0 = r7
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createIntegerList(android.os.Parcel, int):java.util.ArrayList");
    }

    public static SparseBooleanArray createSparseBooleanArray(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        SparseBooleanArray readSparseBooleanArray = parcel2.readSparseBooleanArray();
        parcel2.setDataPosition(dataPosition + readSize);
        return readSparseBooleanArray;
    }

    public static SparseIntArray createSparseIntArray(Parcel parcel, int i) {
        SparseIntArray sparseIntArray;
        Parcel parcel2 = parcel;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        new SparseIntArray();
        SparseIntArray sparseIntArray2 = sparseIntArray;
        int readInt = parcel2.readInt();
        for (int i2 = 0; i2 < readInt; i2++) {
            sparseIntArray2.append(parcel2.readInt(), parcel2.readInt());
        }
        parcel2.setDataPosition(dataPosition + readSize);
        return sparseIntArray2;
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.util.SparseArray<java.lang.Float> createFloatSparseArray(android.os.Parcel r13, int r14) {
        /*
            r0 = r13
            r1 = r14
            r9 = r0
            r10 = r1
            int r9 = readSize(r9, r10)
            r2 = r9
            r9 = r0
            int r9 = r9.dataPosition()
            r3 = r9
            r9 = r2
            if (r9 != 0) goto L_0x0015
            r9 = 0
            r0 = r9
        L_0x0014:
            return r0
        L_0x0015:
            android.util.SparseArray r9 = new android.util.SparseArray
            r12 = r9
            r9 = r12
            r10 = r12
            r10.<init>()
            r4 = r9
            r9 = r0
            int r9 = r9.readInt()
            r5 = r9
            r9 = 0
            r6 = r9
        L_0x0026:
            r9 = r6
            r10 = r5
            if (r9 >= r10) goto L_0x0043
            r9 = r0
            int r9 = r9.readInt()
            r7 = r9
            r9 = r0
            float r9 = r9.readFloat()
            r8 = r9
            r9 = r4
            r10 = r7
            r11 = r8
            java.lang.Float r11 = java.lang.Float.valueOf(r11)
            r9.append(r10, r11)
            int r6 = r6 + 1
            goto L_0x0026
        L_0x0043:
            r9 = r0
            r10 = r3
            r11 = r2
            int r10 = r10 + r11
            r9.setDataPosition(r10)
            r9 = r4
            r0 = r9
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createFloatSparseArray(android.os.Parcel, int):android.util.SparseArray");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.util.SparseArray<java.lang.Double> createDoubleSparseArray(android.os.Parcel r15, int r16) {
        /*
            r0 = r15
            r1 = r16
            r10 = r0
            r11 = r1
            int r10 = readSize(r10, r11)
            r2 = r10
            r10 = r0
            int r10 = r10.dataPosition()
            r3 = r10
            r10 = r2
            if (r10 != 0) goto L_0x0016
            r10 = 0
            r0 = r10
        L_0x0015:
            return r0
        L_0x0016:
            android.util.SparseArray r10 = new android.util.SparseArray
            r14 = r10
            r10 = r14
            r11 = r14
            r11.<init>()
            r4 = r10
            r10 = r0
            int r10 = r10.readInt()
            r5 = r10
            r10 = 0
            r6 = r10
        L_0x0027:
            r10 = r6
            r11 = r5
            if (r10 >= r11) goto L_0x0044
            r10 = r0
            int r10 = r10.readInt()
            r7 = r10
            r10 = r0
            double r10 = r10.readDouble()
            r8 = r10
            r10 = r4
            r11 = r7
            r12 = r8
            java.lang.Double r12 = java.lang.Double.valueOf(r12)
            r10.append(r11, r12)
            int r6 = r6 + 1
            goto L_0x0027
        L_0x0044:
            r10 = r0
            r11 = r3
            r12 = r2
            int r11 = r11 + r12
            r10.setDataPosition(r11)
            r10 = r4
            r0 = r10
            goto L_0x0015
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createDoubleSparseArray(android.os.Parcel, int):android.util.SparseArray");
    }

    public static SparseLongArray createSparseLongArray(Parcel parcel, int i) {
        SparseLongArray sparseLongArray;
        Parcel parcel2 = parcel;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        new SparseLongArray();
        SparseLongArray sparseLongArray2 = sparseLongArray;
        int readInt = parcel2.readInt();
        for (int i2 = 0; i2 < readInt; i2++) {
            sparseLongArray2.append(parcel2.readInt(), parcel2.readLong());
        }
        parcel2.setDataPosition(dataPosition + readSize);
        return sparseLongArray2;
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.util.SparseArray<java.lang.String> createStringSparseArray(android.os.Parcel r13, int r14) {
        /*
            r0 = r13
            r1 = r14
            r9 = r0
            r10 = r1
            int r9 = readSize(r9, r10)
            r2 = r9
            r9 = r0
            int r9 = r9.dataPosition()
            r3 = r9
            r9 = r2
            if (r9 != 0) goto L_0x0015
            r9 = 0
            r0 = r9
        L_0x0014:
            return r0
        L_0x0015:
            android.util.SparseArray r9 = new android.util.SparseArray
            r12 = r9
            r9 = r12
            r10 = r12
            r10.<init>()
            r4 = r9
            r9 = r0
            int r9 = r9.readInt()
            r5 = r9
            r9 = 0
            r6 = r9
        L_0x0026:
            r9 = r6
            r10 = r5
            if (r9 >= r10) goto L_0x003f
            r9 = r0
            int r9 = r9.readInt()
            r7 = r9
            r9 = r0
            java.lang.String r9 = r9.readString()
            r8 = r9
            r9 = r4
            r10 = r7
            r11 = r8
            r9.append(r10, r11)
            int r6 = r6 + 1
            goto L_0x0026
        L_0x003f:
            r9 = r0
            r10 = r3
            r11 = r2
            int r10 = r10 + r11
            r9.setDataPosition(r10)
            r9 = r4
            r0 = r9
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringSparseArray(android.os.Parcel, int):android.util.SparseArray");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.util.SparseArray<android.os.Parcel> createParcelSparseArray(android.os.Parcel r16, int r17) {
        /*
            r0 = r16
            r1 = r17
            r11 = r0
            r12 = r1
            int r11 = readSize(r11, r12)
            r2 = r11
            r11 = r0
            int r11 = r11.dataPosition()
            r3 = r11
            r11 = r2
            if (r11 != 0) goto L_0x0017
            r11 = 0
            r0 = r11
        L_0x0016:
            return r0
        L_0x0017:
            r11 = r0
            int r11 = r11.readInt()
            r4 = r11
            android.util.SparseArray r11 = new android.util.SparseArray
            r15 = r11
            r11 = r15
            r12 = r15
            r12.<init>()
            r5 = r11
            r11 = 0
            r6 = r11
        L_0x0028:
            r11 = r6
            r12 = r4
            if (r11 >= r12) goto L_0x0068
            r11 = r0
            int r11 = r11.readInt()
            r7 = r11
            r11 = r0
            int r11 = r11.readInt()
            r15 = r11
            r11 = r15
            r12 = r15
            r8 = r12
            if (r11 == 0) goto L_0x0061
            r11 = r0
            int r11 = r11.dataPosition()
            r9 = r11
            android.os.Parcel r11 = android.os.Parcel.obtain()
            r15 = r11
            r11 = r15
            r12 = r15
            r10 = r12
            r12 = r0
            r13 = r9
            r14 = r8
            r11.appendFrom(r12, r13, r14)
            r11 = r5
            r12 = r7
            r13 = r10
            r11.append(r12, r13)
            r11 = r0
            r12 = r9
            r13 = r8
            int r12 = r12 + r13
            r11.setDataPosition(r12)
        L_0x005e:
            int r6 = r6 + 1
            goto L_0x0028
        L_0x0061:
            r11 = r5
            r12 = r7
            r13 = 0
            r11.append(r12, r13)
            goto L_0x005e
        L_0x0068:
            r11 = r0
            r12 = r3
            r13 = r2
            int r12 = r12 + r13
            r11.setDataPosition(r12)
            r11 = r5
            r0 = r11
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelSparseArray(android.os.Parcel, int):android.util.SparseArray");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> android.util.SparseArray<T> createTypedSparseArray(android.os.Parcel r14, int r15, android.os.Parcelable.Creator<T> r16) {
        /*
            r0 = r14
            r1 = r15
            r2 = r16
            r10 = r0
            r11 = r1
            int r10 = readSize(r10, r11)
            r3 = r10
            r10 = r0
            int r10 = r10.dataPosition()
            r4 = r10
            r10 = r3
            if (r10 != 0) goto L_0x0017
            r10 = 0
            r0 = r10
        L_0x0016:
            return r0
        L_0x0017:
            r10 = r0
            int r10 = r10.readInt()
            r5 = r10
            android.util.SparseArray r10 = new android.util.SparseArray
            r13 = r10
            r10 = r13
            r11 = r13
            r11.<init>()
            r6 = r10
            r10 = 0
            r7 = r10
        L_0x0028:
            r10 = r7
            r11 = r5
            if (r10 >= r11) goto L_0x004c
            r10 = r0
            int r10 = r10.readInt()
            r8 = r10
            r10 = r0
            int r10 = r10.readInt()
            if (r10 == 0) goto L_0x0049
            r10 = r2
            r11 = r0
            java.lang.Object r10 = r10.createFromParcel(r11)
            r9 = r10
        L_0x0040:
            r10 = r6
            r11 = r8
            r12 = r9
            r10.append(r11, r12)
            int r7 = r7 + 1
            goto L_0x0028
        L_0x0049:
            r10 = 0
            r9 = r10
            goto L_0x0040
        L_0x004c:
            r10 = r0
            r11 = r4
            r12 = r3
            int r11 = r11 + r12
            r10.setDataPosition(r11)
            r10 = r6
            r0 = r10
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedSparseArray(android.os.Parcel, int, android.os.Parcelable$Creator):android.util.SparseArray");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.util.SparseArray<android.os.IBinder> createIBinderSparseArray(android.os.Parcel r13, int r14) {
        /*
            r0 = r13
            r1 = r14
            r9 = r0
            r10 = r1
            int r9 = readSize(r9, r10)
            r2 = r9
            r9 = r0
            int r9 = r9.dataPosition()
            r3 = r9
            r9 = r2
            if (r9 != 0) goto L_0x0015
            r9 = 0
            r0 = r9
        L_0x0014:
            return r0
        L_0x0015:
            r9 = r0
            int r9 = r9.readInt()
            r4 = r9
            android.util.SparseArray r9 = new android.util.SparseArray
            r12 = r9
            r9 = r12
            r10 = r12
            r11 = r4
            r10.<init>(r11)
            r5 = r9
            r9 = 0
            r6 = r9
        L_0x0027:
            r9 = r6
            r10 = r4
            if (r9 >= r10) goto L_0x0040
            r9 = r0
            int r9 = r9.readInt()
            r7 = r9
            r9 = r0
            android.os.IBinder r9 = r9.readStrongBinder()
            r8 = r9
            r9 = r5
            r10 = r7
            r11 = r8
            r9.append(r10, r11)
            int r6 = r6 + 1
            goto L_0x0027
        L_0x0040:
            r9 = r0
            r10 = r3
            r11 = r2
            int r10 = r10 + r11
            r9.setDataPosition(r10)
            r9 = r5
            r0 = r9
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createIBinderSparseArray(android.os.Parcel, int):android.util.SparseArray");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.util.SparseArray<byte[]> createByteArraySparseArray(android.os.Parcel r13, int r14) {
        /*
            r0 = r13
            r1 = r14
            r9 = r0
            r10 = r1
            int r9 = readSize(r9, r10)
            r2 = r9
            r9 = r0
            int r9 = r9.dataPosition()
            r3 = r9
            r9 = r2
            if (r9 != 0) goto L_0x0015
            r9 = 0
            r0 = r9
        L_0x0014:
            return r0
        L_0x0015:
            r9 = r0
            int r9 = r9.readInt()
            r4 = r9
            android.util.SparseArray r9 = new android.util.SparseArray
            r12 = r9
            r9 = r12
            r10 = r12
            r11 = r4
            r10.<init>(r11)
            r5 = r9
            r9 = 0
            r6 = r9
        L_0x0027:
            r9 = r6
            r10 = r4
            if (r9 >= r10) goto L_0x0040
            r9 = r0
            int r9 = r9.readInt()
            r7 = r9
            r9 = r0
            byte[] r9 = r9.createByteArray()
            r8 = r9
            r9 = r5
            r10 = r7
            r11 = r8
            r9.append(r10, r11)
            int r6 = r6 + 1
            goto L_0x0027
        L_0x0040:
            r9 = r0
            r10 = r3
            r11 = r2
            int r10 = r10 + r11
            r9.setDataPosition(r10)
            r9 = r5
            r0 = r9
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createByteArraySparseArray(android.os.Parcel, int):android.util.SparseArray");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<java.lang.Long> createLongList(android.os.Parcel r11, int r12) {
        /*
            r0 = r11
            r1 = r12
            r7 = r0
            r8 = r1
            int r7 = readSize(r7, r8)
            r2 = r7
            r7 = r0
            int r7 = r7.dataPosition()
            r3 = r7
            r7 = r2
            if (r7 != 0) goto L_0x0015
            r7 = 0
            r0 = r7
        L_0x0014:
            return r0
        L_0x0015:
            java.util.ArrayList r7 = new java.util.ArrayList
            r10 = r7
            r7 = r10
            r8 = r10
            r8.<init>()
            r4 = r7
            r7 = r0
            int r7 = r7.readInt()
            r5 = r7
            r7 = 0
            r6 = r7
        L_0x0026:
            r7 = r6
            r8 = r5
            if (r7 >= r8) goto L_0x003b
            r7 = r4
            r8 = r0
            long r8 = r8.readLong()
            java.lang.Long r8 = java.lang.Long.valueOf(r8)
            boolean r7 = r7.add(r8)
            int r6 = r6 + 1
            goto L_0x0026
        L_0x003b:
            r7 = r0
            r8 = r3
            r9 = r2
            int r8 = r8 + r9
            r7.setDataPosition(r8)
            r7 = r4
            r0 = r7
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createLongList(android.os.Parcel, int):java.util.ArrayList");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<java.lang.Float> createFloatList(android.os.Parcel r11, int r12) {
        /*
            r0 = r11
            r1 = r12
            r7 = r0
            r8 = r1
            int r7 = readSize(r7, r8)
            r2 = r7
            r7 = r0
            int r7 = r7.dataPosition()
            r3 = r7
            r7 = r2
            if (r7 != 0) goto L_0x0015
            r7 = 0
            r0 = r7
        L_0x0014:
            return r0
        L_0x0015:
            java.util.ArrayList r7 = new java.util.ArrayList
            r10 = r7
            r7 = r10
            r8 = r10
            r8.<init>()
            r4 = r7
            r7 = r0
            int r7 = r7.readInt()
            r5 = r7
            r7 = 0
            r6 = r7
        L_0x0026:
            r7 = r6
            r8 = r5
            if (r7 >= r8) goto L_0x003b
            r7 = r4
            r8 = r0
            float r8 = r8.readFloat()
            java.lang.Float r8 = java.lang.Float.valueOf(r8)
            boolean r7 = r7.add(r8)
            int r6 = r6 + 1
            goto L_0x0026
        L_0x003b:
            r7 = r0
            r8 = r3
            r9 = r2
            int r8 = r8 + r9
            r7.setDataPosition(r8)
            r7 = r4
            r0 = r7
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createFloatList(android.os.Parcel, int):java.util.ArrayList");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<java.lang.Double> createDoubleList(android.os.Parcel r11, int r12) {
        /*
            r0 = r11
            r1 = r12
            r7 = r0
            r8 = r1
            int r7 = readSize(r7, r8)
            r2 = r7
            r7 = r0
            int r7 = r7.dataPosition()
            r3 = r7
            r7 = r2
            if (r7 != 0) goto L_0x0015
            r7 = 0
            r0 = r7
        L_0x0014:
            return r0
        L_0x0015:
            java.util.ArrayList r7 = new java.util.ArrayList
            r10 = r7
            r7 = r10
            r8 = r10
            r8.<init>()
            r4 = r7
            r7 = r0
            int r7 = r7.readInt()
            r5 = r7
            r7 = 0
            r6 = r7
        L_0x0026:
            r7 = r6
            r8 = r5
            if (r7 >= r8) goto L_0x003b
            r7 = r4
            r8 = r0
            double r8 = r8.readDouble()
            java.lang.Double r8 = java.lang.Double.valueOf(r8)
            boolean r7 = r7.add(r8)
            int r6 = r6 + 1
            goto L_0x0026
        L_0x003b:
            r7 = r0
            r8 = r3
            r9 = r2
            int r8 = r8 + r9
            r7.setDataPosition(r8)
            r7 = r4
            r0 = r7
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createDoubleList(android.os.Parcel, int):java.util.ArrayList");
    }

    public static ArrayList<String> createStringList(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        ArrayList<String> createStringArrayList = parcel2.createStringArrayList();
        parcel2.setDataPosition(dataPosition + readSize);
        return createStringArrayList;
    }

    public static ArrayList<IBinder> createIBinderList(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        ArrayList<IBinder> createBinderArrayList = parcel2.createBinderArrayList();
        parcel2.setDataPosition(dataPosition + readSize);
        return createBinderArrayList;
    }

    public static <T> T[] createTypedArray(Parcel parcel, int i, Parcelable.Creator<T> creator) {
        Parcel parcel2 = parcel;
        Parcelable.Creator<T> creator2 = creator;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        T[] createTypedArray = parcel2.createTypedArray(creator2);
        parcel2.setDataPosition(dataPosition + readSize);
        return createTypedArray;
    }

    public static <T> ArrayList<T> createTypedList(Parcel parcel, int i, Parcelable.Creator<T> creator) {
        Parcel parcel2 = parcel;
        Parcelable.Creator<T> creator2 = creator;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        ArrayList<T> createTypedArrayList = parcel2.createTypedArrayList(creator2);
        parcel2.setDataPosition(dataPosition + readSize);
        return createTypedArrayList;
    }

    public static Parcel createParcel(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        Parcel parcel3 = obtain;
        obtain.appendFrom(parcel2, dataPosition, readSize);
        parcel2.setDataPosition(dataPosition + readSize);
        return parcel3;
    }

    public static Parcel[] createParcelArray(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize == 0) {
            return null;
        }
        int readInt = parcel2.readInt();
        int i2 = readInt;
        Parcel[] parcelArr = new Parcel[readInt];
        for (int i3 = 0; i3 < i2; i3++) {
            int readInt2 = parcel2.readInt();
            int i4 = readInt2;
            if (readInt2 != 0) {
                int dataPosition2 = parcel2.dataPosition();
                Parcel obtain = Parcel.obtain();
                obtain.appendFrom(parcel2, dataPosition2, i4);
                parcelArr[i3] = obtain;
                parcel2.setDataPosition(dataPosition2 + i4);
            } else {
                parcelArr[i3] = null;
            }
        }
        parcel2.setDataPosition(dataPosition + readSize);
        return parcelArr;
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<android.os.Parcel> createParcelList(android.os.Parcel r15, int r16) {
        /*
            r0 = r15
            r1 = r16
            r10 = r0
            r11 = r1
            int r10 = readSize(r10, r11)
            r2 = r10
            r10 = r0
            int r10 = r10.dataPosition()
            r3 = r10
            r10 = r2
            if (r10 != 0) goto L_0x0016
            r10 = 0
            r0 = r10
        L_0x0015:
            return r0
        L_0x0016:
            r10 = r0
            int r10 = r10.readInt()
            r4 = r10
            java.util.ArrayList r10 = new java.util.ArrayList
            r14 = r10
            r10 = r14
            r11 = r14
            r11.<init>()
            r5 = r10
            r10 = 0
            r6 = r10
        L_0x0027:
            r10 = r6
            r11 = r4
            if (r10 >= r11) goto L_0x0061
            r10 = r0
            int r10 = r10.readInt()
            r14 = r10
            r10 = r14
            r11 = r14
            r7 = r11
            if (r10 == 0) goto L_0x005a
            r10 = r0
            int r10 = r10.dataPosition()
            r8 = r10
            android.os.Parcel r10 = android.os.Parcel.obtain()
            r14 = r10
            r10 = r14
            r11 = r14
            r9 = r11
            r11 = r0
            r12 = r8
            r13 = r7
            r10.appendFrom(r11, r12, r13)
            r10 = r5
            r11 = r9
            boolean r10 = r10.add(r11)
            r10 = r0
            r11 = r8
            r12 = r7
            int r11 = r11 + r12
            r10.setDataPosition(r11)
        L_0x0057:
            int r6 = r6 + 1
            goto L_0x0027
        L_0x005a:
            r10 = r5
            r11 = 0
            boolean r10 = r10.add(r11)
            goto L_0x0057
        L_0x0061:
            r10 = r0
            r11 = r3
            r12 = r2
            int r11 = r11 + r12
            r10.setDataPosition(r11)
            r10 = r5
            r0 = r10
            goto L_0x0015
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelList(android.os.Parcel, int):java.util.ArrayList");
    }

    public static void readList(Parcel parcel, int i, List list, ClassLoader classLoader) {
        Parcel parcel2 = parcel;
        List list2 = list;
        ClassLoader classLoader2 = classLoader;
        int readSize = readSize(parcel2, i);
        int dataPosition = parcel2.dataPosition();
        if (readSize != 0) {
            parcel2.readList(list2, classLoader2);
            parcel2.setDataPosition(dataPosition + readSize);
        }
    }

    public static void ensureAtEnd(Parcel parcel, int i) {
        Throwable th;
        StringBuilder sb;
        Parcel parcel2 = parcel;
        int i2 = i;
        if (parcel2.dataPosition() != i2) {
            Throwable th2 = th;
            new StringBuilder(37);
            new ParseException(sb.append("Overread allowed size end=").append(i2).toString(), parcel2);
            throw th2;
        }
    }
}
