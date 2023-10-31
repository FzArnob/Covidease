package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class SafeParcelWriter {
    private SafeParcelWriter() {
    }

    private static void zzb(Parcel parcel, int i, int i2) {
        Parcel parcel2 = parcel;
        int i3 = i;
        int i4 = i2;
        if (i4 >= 65535) {
            parcel2.writeInt(-65536 | i3);
            parcel2.writeInt(i4);
            return;
        }
        parcel2.writeInt((i4 << 16) | i3);
    }

    private static int zza(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        parcel2.writeInt(-65536 | i);
        parcel2.writeInt(0);
        return parcel2.dataPosition();
    }

    private static void zzb(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int i2 = i;
        int dataPosition = parcel2.dataPosition();
        parcel2.setDataPosition(i2 - 4);
        parcel2.writeInt(dataPosition - i2);
        parcel2.setDataPosition(dataPosition);
    }

    public static int beginObjectHeader(Parcel parcel) {
        return zza(parcel, 20293);
    }

    public static void finishObjectHeader(Parcel parcel, int i) {
        zzb(parcel, i);
    }

    public static void writeBoolean(Parcel parcel, int i, boolean z) {
        Parcel parcel2 = parcel;
        zzb(parcel2, i, 4);
        parcel2.writeInt(z ? 1 : 0);
    }

    public static void writeBooleanObject(Parcel parcel, int i, Boolean bool, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        Boolean bool2 = bool;
        boolean z2 = z;
        if (bool2 != null) {
            zzb(parcel2, i2, 4);
            parcel2.writeInt(bool2.booleanValue() ? 1 : 0);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeByte(Parcel parcel, int i, byte b) {
        Parcel parcel2 = parcel;
        zzb(parcel2, i, 4);
        parcel2.writeInt(b);
    }

    public static void writeChar(Parcel parcel, int i, char c) {
        Parcel parcel2 = parcel;
        zzb(parcel2, i, 4);
        parcel2.writeInt(c);
    }

    public static void writeShort(Parcel parcel, int i, short s) {
        Parcel parcel2 = parcel;
        zzb(parcel2, i, 4);
        parcel2.writeInt(s);
    }

    public static void writeInt(Parcel parcel, int i, int i2) {
        Parcel parcel2 = parcel;
        zzb(parcel2, i, 4);
        parcel2.writeInt(i2);
    }

    public static void writeIntegerObject(Parcel parcel, int i, Integer num, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        Integer num2 = num;
        boolean z2 = z;
        if (num2 != null) {
            zzb(parcel2, i2, 4);
            parcel2.writeInt(num2.intValue());
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeLong(Parcel parcel, int i, long j) {
        Parcel parcel2 = parcel;
        zzb(parcel2, i, 8);
        parcel2.writeLong(j);
    }

    public static void writeLongObject(Parcel parcel, int i, Long l, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        Long l2 = l;
        boolean z2 = z;
        if (l2 != null) {
            zzb(parcel2, i2, 8);
            parcel2.writeLong(l2.longValue());
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeBigInteger(Parcel parcel, int i, BigInteger bigInteger, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        BigInteger bigInteger2 = bigInteger;
        boolean z2 = z;
        if (bigInteger2 != null) {
            int zza = zza(parcel2, i2);
            parcel2.writeByteArray(bigInteger2.toByteArray());
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeFloat(Parcel parcel, int i, float f) {
        Parcel parcel2 = parcel;
        zzb(parcel2, i, 4);
        parcel2.writeFloat(f);
    }

    public static void writeFloatObject(Parcel parcel, int i, Float f, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        Float f2 = f;
        boolean z2 = z;
        if (f2 != null) {
            zzb(parcel2, i2, 4);
            parcel2.writeFloat(f2.floatValue());
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeDouble(Parcel parcel, int i, double d) {
        Parcel parcel2 = parcel;
        zzb(parcel2, i, 8);
        parcel2.writeDouble(d);
    }

    public static void writeDoubleObject(Parcel parcel, int i, Double d, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        Double d2 = d;
        boolean z2 = z;
        if (d2 != null) {
            zzb(parcel2, i2, 8);
            parcel2.writeDouble(d2.doubleValue());
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeBigDecimal(Parcel parcel, int i, BigDecimal bigDecimal, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        BigDecimal bigDecimal2 = bigDecimal;
        boolean z2 = z;
        if (bigDecimal2 != null) {
            int zza = zza(parcel2, i2);
            parcel2.writeByteArray(bigDecimal2.unscaledValue().toByteArray());
            parcel2.writeInt(bigDecimal2.scale());
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeString(Parcel parcel, int i, String str, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        String str2 = str;
        boolean z2 = z;
        if (str2 != null) {
            int zza = zza(parcel2, i2);
            parcel2.writeString(str2);
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeIBinder(Parcel parcel, int i, IBinder iBinder, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        IBinder iBinder2 = iBinder;
        boolean z2 = z;
        if (iBinder2 != null) {
            int zza = zza(parcel2, i2);
            parcel2.writeStrongBinder(iBinder2);
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeParcelable(Parcel parcel, int i, Parcelable parcelable, int i2, boolean z) {
        Parcel parcel2 = parcel;
        int i3 = i;
        Parcelable parcelable2 = parcelable;
        int i4 = i2;
        boolean z2 = z;
        if (parcelable2 != null) {
            int zza = zza(parcel2, i3);
            parcelable2.writeToParcel(parcel2, i4);
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i3, 0);
        }
    }

    public static void writeBundle(Parcel parcel, int i, Bundle bundle, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        Bundle bundle2 = bundle;
        boolean z2 = z;
        if (bundle2 != null) {
            int zza = zza(parcel2, i2);
            parcel2.writeBundle(bundle2);
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeByteArray(Parcel parcel, int i, byte[] bArr, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        byte[] bArr2 = bArr;
        boolean z2 = z;
        if (bArr2 != null) {
            int zza = zza(parcel2, i2);
            parcel2.writeByteArray(bArr2);
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeByteArrayArray(Parcel parcel, int i, byte[][] bArr, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        byte[][] bArr2 = bArr;
        boolean z2 = z;
        if (bArr2 != null) {
            int zza = zza(parcel2, i2);
            int length = bArr2.length;
            parcel2.writeInt(length);
            for (int i3 = 0; i3 < length; i3++) {
                parcel2.writeByteArray(bArr2[i3]);
            }
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeBooleanArray(Parcel parcel, int i, boolean[] zArr, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        boolean[] zArr2 = zArr;
        boolean z2 = z;
        if (zArr2 != null) {
            int zza = zza(parcel2, i2);
            parcel2.writeBooleanArray(zArr2);
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeCharArray(Parcel parcel, int i, char[] cArr, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        char[] cArr2 = cArr;
        boolean z2 = z;
        if (cArr2 != null) {
            int zza = zza(parcel2, i2);
            parcel2.writeCharArray(cArr2);
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeIntArray(Parcel parcel, int i, int[] iArr, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        int[] iArr2 = iArr;
        boolean z2 = z;
        if (iArr2 != null) {
            int zza = zza(parcel2, i2);
            parcel2.writeIntArray(iArr2);
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeLongArray(Parcel parcel, int i, long[] jArr, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        long[] jArr2 = jArr;
        boolean z2 = z;
        if (jArr2 != null) {
            int zza = zza(parcel2, i2);
            parcel2.writeLongArray(jArr2);
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeBigIntegerArray(Parcel parcel, int i, BigInteger[] bigIntegerArr, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        BigInteger[] bigIntegerArr2 = bigIntegerArr;
        boolean z2 = z;
        if (bigIntegerArr2 != null) {
            int zza = zza(parcel2, i2);
            int length = bigIntegerArr2.length;
            parcel2.writeInt(length);
            for (int i3 = 0; i3 < length; i3++) {
                parcel2.writeByteArray(bigIntegerArr2[i3].toByteArray());
            }
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeFloatArray(Parcel parcel, int i, float[] fArr, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        float[] fArr2 = fArr;
        boolean z2 = z;
        if (fArr2 != null) {
            int zza = zza(parcel2, i2);
            parcel2.writeFloatArray(fArr2);
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeDoubleArray(Parcel parcel, int i, double[] dArr, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        double[] dArr2 = dArr;
        boolean z2 = z;
        if (dArr2 != null) {
            int zza = zza(parcel2, i2);
            parcel2.writeDoubleArray(dArr2);
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeBigDecimalArray(Parcel parcel, int i, BigDecimal[] bigDecimalArr, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        BigDecimal[] bigDecimalArr2 = bigDecimalArr;
        boolean z2 = z;
        if (bigDecimalArr2 != null) {
            int zza = zza(parcel2, i2);
            int length = bigDecimalArr2.length;
            parcel2.writeInt(length);
            for (int i3 = 0; i3 < length; i3++) {
                parcel2.writeByteArray(bigDecimalArr2[i3].unscaledValue().toByteArray());
                parcel2.writeInt(bigDecimalArr2[i3].scale());
            }
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeStringArray(Parcel parcel, int i, String[] strArr, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        String[] strArr2 = strArr;
        boolean z2 = z;
        if (strArr2 != null) {
            int zza = zza(parcel2, i2);
            parcel2.writeStringArray(strArr2);
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeIBinderArray(Parcel parcel, int i, IBinder[] iBinderArr, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        IBinder[] iBinderArr2 = iBinderArr;
        boolean z2 = z;
        if (iBinderArr2 != null) {
            int zza = zza(parcel2, i2);
            parcel2.writeBinderArray(iBinderArr2);
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeBooleanList(Parcel parcel, int i, List<Boolean> list, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        List<Boolean> list2 = list;
        boolean z2 = z;
        if (list2 != null) {
            int zza = zza(parcel2, i2);
            int size = list2.size();
            parcel2.writeInt(size);
            for (int i3 = 0; i3 < size; i3++) {
                parcel2.writeInt(list2.get(i3).booleanValue() ? 1 : 0);
            }
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeIntegerList(Parcel parcel, int i, List<Integer> list, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        List<Integer> list2 = list;
        boolean z2 = z;
        if (list2 != null) {
            int zza = zza(parcel2, i2);
            int size = list2.size();
            parcel2.writeInt(size);
            for (int i3 = 0; i3 < size; i3++) {
                parcel2.writeInt(list2.get(i3).intValue());
            }
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeLongList(Parcel parcel, int i, List<Long> list, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        List<Long> list2 = list;
        boolean z2 = z;
        if (list2 != null) {
            int zza = zza(parcel2, i2);
            int size = list2.size();
            parcel2.writeInt(size);
            for (int i3 = 0; i3 < size; i3++) {
                parcel2.writeLong(list2.get(i3).longValue());
            }
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeFloatList(Parcel parcel, int i, List<Float> list, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        List<Float> list2 = list;
        boolean z2 = z;
        if (list2 != null) {
            int zza = zza(parcel2, i2);
            int size = list2.size();
            parcel2.writeInt(size);
            for (int i3 = 0; i3 < size; i3++) {
                parcel2.writeFloat(list2.get(i3).floatValue());
            }
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeDoubleList(Parcel parcel, int i, List<Double> list, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        List<Double> list2 = list;
        boolean z2 = z;
        if (list2 != null) {
            int zza = zza(parcel2, i2);
            int size = list2.size();
            parcel2.writeInt(size);
            for (int i3 = 0; i3 < size; i3++) {
                parcel2.writeDouble(list2.get(i3).doubleValue());
            }
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeStringList(Parcel parcel, int i, List<String> list, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        List<String> list2 = list;
        boolean z2 = z;
        if (list2 != null) {
            int zza = zza(parcel2, i2);
            parcel2.writeStringList(list2);
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeIBinderList(Parcel parcel, int i, List<IBinder> list, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        List<IBinder> list2 = list;
        boolean z2 = z;
        if (list2 != null) {
            int zza = zza(parcel2, i2);
            parcel2.writeBinderList(list2);
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static <T extends Parcelable> void writeTypedArray(Parcel parcel, int i, T[] tArr, int i2, boolean z) {
        Parcel parcel2 = parcel;
        int i3 = i;
        T[] tArr2 = tArr;
        int i4 = i2;
        boolean z2 = z;
        if (tArr2 != null) {
            int zza = zza(parcel2, i3);
            int length = tArr2.length;
            parcel2.writeInt(length);
            for (int i5 = 0; i5 < length; i5++) {
                T t = tArr2[i5];
                T t2 = t;
                if (t == null) {
                    parcel2.writeInt(0);
                } else {
                    zza(parcel2, t2, i4);
                }
            }
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i3, 0);
        }
    }

    public static <T extends Parcelable> void writeTypedList(Parcel parcel, int i, List<T> list, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        List<T> list2 = list;
        boolean z2 = z;
        if (list2 != null) {
            int zza = zza(parcel2, i2);
            int size = list2.size();
            parcel2.writeInt(size);
            for (int i3 = 0; i3 < size; i3++) {
                Parcelable parcelable = (Parcelable) list2.get(i3);
                Parcelable parcelable2 = parcelable;
                if (parcelable == null) {
                    parcel2.writeInt(0);
                } else {
                    zza(parcel2, parcelable2, 0);
                }
            }
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    private static <T extends Parcelable> void zza(Parcel parcel, T t, int i) {
        Parcel parcel2 = parcel;
        int dataPosition = parcel2.dataPosition();
        parcel2.writeInt(1);
        int dataPosition2 = parcel2.dataPosition();
        t.writeToParcel(parcel2, i);
        int dataPosition3 = parcel2.dataPosition();
        parcel2.setDataPosition(dataPosition);
        parcel2.writeInt(dataPosition3 - dataPosition2);
        parcel2.setDataPosition(dataPosition3);
    }

    public static void writeParcel(Parcel parcel, int i, Parcel parcel2, boolean z) {
        Parcel parcel3 = parcel;
        int i2 = i;
        Parcel parcel4 = parcel2;
        boolean z2 = z;
        if (parcel4 != null) {
            int zza = zza(parcel3, i2);
            parcel3.appendFrom(parcel4, 0, parcel4.dataSize());
            zzb(parcel3, zza);
        } else if (z2) {
            zzb(parcel3, i2, 0);
        }
    }

    public static void writeParcelArray(Parcel parcel, int i, Parcel[] parcelArr, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        Parcel[] parcelArr2 = parcelArr;
        boolean z2 = z;
        if (parcelArr2 != null) {
            int zza = zza(parcel2, i2);
            int length = parcelArr2.length;
            parcel2.writeInt(length);
            for (int i3 = 0; i3 < length; i3++) {
                Parcel parcel3 = parcelArr2[i3];
                Parcel parcel4 = parcel3;
                if (parcel3 != null) {
                    parcel2.writeInt(parcel4.dataSize());
                    parcel2.appendFrom(parcel4, 0, parcel4.dataSize());
                } else {
                    parcel2.writeInt(0);
                }
            }
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeParcelList(Parcel parcel, int i, List<Parcel> list, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        List<Parcel> list2 = list;
        boolean z2 = z;
        if (list2 != null) {
            int zza = zza(parcel2, i2);
            int size = list2.size();
            parcel2.writeInt(size);
            for (int i3 = 0; i3 < size; i3++) {
                Parcel parcel3 = list2.get(i3);
                Parcel parcel4 = parcel3;
                if (parcel3 != null) {
                    parcel2.writeInt(parcel4.dataSize());
                    parcel2.appendFrom(parcel4, 0, parcel4.dataSize());
                } else {
                    parcel2.writeInt(0);
                }
            }
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeList(Parcel parcel, int i, List list, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        List list2 = list;
        boolean z2 = z;
        if (list2 != null) {
            int zza = zza(parcel2, i2);
            parcel2.writeList(list2);
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeSparseBooleanArray(Parcel parcel, int i, SparseBooleanArray sparseBooleanArray, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        SparseBooleanArray sparseBooleanArray2 = sparseBooleanArray;
        boolean z2 = z;
        if (sparseBooleanArray2 != null) {
            int zza = zza(parcel2, i2);
            parcel2.writeSparseBooleanArray(sparseBooleanArray2);
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeDoubleSparseArray(Parcel parcel, int i, SparseArray<Double> sparseArray, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        SparseArray<Double> sparseArray2 = sparseArray;
        boolean z2 = z;
        if (sparseArray2 != null) {
            int zza = zza(parcel2, i2);
            int size = sparseArray2.size();
            parcel2.writeInt(size);
            for (int i3 = 0; i3 < size; i3++) {
                parcel2.writeInt(sparseArray2.keyAt(i3));
                parcel2.writeDouble(sparseArray2.valueAt(i3).doubleValue());
            }
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeFloatSparseArray(Parcel parcel, int i, SparseArray<Float> sparseArray, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        SparseArray<Float> sparseArray2 = sparseArray;
        boolean z2 = z;
        if (sparseArray2 != null) {
            int zza = zza(parcel2, i2);
            int size = sparseArray2.size();
            parcel2.writeInt(size);
            for (int i3 = 0; i3 < size; i3++) {
                parcel2.writeInt(sparseArray2.keyAt(i3));
                parcel2.writeFloat(sparseArray2.valueAt(i3).floatValue());
            }
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeSparseIntArray(Parcel parcel, int i, SparseIntArray sparseIntArray, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        SparseIntArray sparseIntArray2 = sparseIntArray;
        boolean z2 = z;
        if (sparseIntArray2 != null) {
            int zza = zza(parcel2, i2);
            int size = sparseIntArray2.size();
            parcel2.writeInt(size);
            for (int i3 = 0; i3 < size; i3++) {
                parcel2.writeInt(sparseIntArray2.keyAt(i3));
                parcel2.writeInt(sparseIntArray2.valueAt(i3));
            }
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeSparseLongArray(Parcel parcel, int i, SparseLongArray sparseLongArray, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        SparseLongArray sparseLongArray2 = sparseLongArray;
        boolean z2 = z;
        if (sparseLongArray2 != null) {
            int zza = zza(parcel2, i2);
            int size = sparseLongArray2.size();
            parcel2.writeInt(size);
            for (int i3 = 0; i3 < size; i3++) {
                parcel2.writeInt(sparseLongArray2.keyAt(i3));
                parcel2.writeLong(sparseLongArray2.valueAt(i3));
            }
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeStringSparseArray(Parcel parcel, int i, SparseArray<String> sparseArray, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        SparseArray<String> sparseArray2 = sparseArray;
        boolean z2 = z;
        if (sparseArray2 != null) {
            int zza = zza(parcel2, i2);
            int size = sparseArray2.size();
            parcel2.writeInt(size);
            for (int i3 = 0; i3 < size; i3++) {
                parcel2.writeInt(sparseArray2.keyAt(i3));
                parcel2.writeString(sparseArray2.valueAt(i3));
            }
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeParcelSparseArray(Parcel parcel, int i, SparseArray<Parcel> sparseArray, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        SparseArray<Parcel> sparseArray2 = sparseArray;
        boolean z2 = z;
        if (sparseArray2 != null) {
            int zza = zza(parcel2, i2);
            int size = sparseArray2.size();
            parcel2.writeInt(size);
            for (int i3 = 0; i3 < size; i3++) {
                parcel2.writeInt(sparseArray2.keyAt(i3));
                Parcel valueAt = sparseArray2.valueAt(i3);
                Parcel parcel3 = valueAt;
                if (valueAt != null) {
                    parcel2.writeInt(parcel3.dataSize());
                    parcel2.appendFrom(parcel3, 0, parcel3.dataSize());
                } else {
                    parcel2.writeInt(0);
                }
            }
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static <T extends Parcelable> void writeTypedSparseArray(Parcel parcel, int i, SparseArray<T> sparseArray, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        SparseArray<T> sparseArray2 = sparseArray;
        boolean z2 = z;
        if (sparseArray2 != null) {
            int zza = zza(parcel2, i2);
            int size = sparseArray2.size();
            parcel2.writeInt(size);
            for (int i3 = 0; i3 < size; i3++) {
                parcel2.writeInt(sparseArray2.keyAt(i3));
                Parcelable parcelable = (Parcelable) sparseArray2.valueAt(i3);
                Parcelable parcelable2 = parcelable;
                if (parcelable == null) {
                    parcel2.writeInt(0);
                } else {
                    zza(parcel2, parcelable2, 0);
                }
            }
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeIBinderSparseArray(Parcel parcel, int i, SparseArray<IBinder> sparseArray, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        SparseArray<IBinder> sparseArray2 = sparseArray;
        boolean z2 = z;
        if (sparseArray2 != null) {
            int zza = zza(parcel2, i2);
            int size = sparseArray2.size();
            parcel2.writeInt(size);
            for (int i3 = 0; i3 < size; i3++) {
                parcel2.writeInt(sparseArray2.keyAt(i3));
                parcel2.writeStrongBinder(sparseArray2.valueAt(i3));
            }
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }

    public static void writeByteArraySparseArray(Parcel parcel, int i, SparseArray<byte[]> sparseArray, boolean z) {
        Parcel parcel2 = parcel;
        int i2 = i;
        SparseArray<byte[]> sparseArray2 = sparseArray;
        boolean z2 = z;
        if (sparseArray2 != null) {
            int zza = zza(parcel2, i2);
            int size = sparseArray2.size();
            parcel2.writeInt(size);
            for (int i3 = 0; i3 < size; i3++) {
                parcel2.writeInt(sparseArray2.keyAt(i3));
                parcel2.writeByteArray(sparseArray2.valueAt(i3));
            }
            zzb(parcel2, zza);
        } else if (z2) {
            zzb(parcel2, i2, 0);
        }
    }
}
