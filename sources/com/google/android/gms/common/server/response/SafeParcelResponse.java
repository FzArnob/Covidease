package com.google.android.gms.common.server.response;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.MapUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@KeepForSdk
@SafeParcelable.Class(creator = "SafeParcelResponseCreator")
@VisibleForTesting
public class SafeParcelResponse extends FastSafeParcelableJsonResponse {
    @KeepForSdk
    public static final Parcelable.Creator<SafeParcelResponse> CREATOR;
    private final String mClassName;
    @SafeParcelable.VersionField(getter = "getVersionCode", mo25283id = 1)
    private final int zalf;
    @SafeParcelable.Field(getter = "getFieldMappingDictionary", mo25277id = 3)
    private final zak zapz;
    @SafeParcelable.Field(getter = "getParcel", mo25277id = 2)
    private final Parcel zarb;
    private final int zarc;
    private int zard;
    private int zare;

    public SafeParcelResponse(zak zak, String str) {
        this.zalf = 1;
        this.zarb = Parcel.obtain();
        this.zarc = 0;
        this.zapz = (zak) Preconditions.checkNotNull(zak);
        this.mClassName = (String) Preconditions.checkNotNull(str);
        this.zard = 0;
    }

    private SafeParcelResponse(SafeParcelable safeParcelable, zak zak, String str) {
        this.zalf = 1;
        this.zarb = Parcel.obtain();
        safeParcelable.writeToParcel(this.zarb, 0);
        this.zarc = 1;
        this.zapz = (zak) Preconditions.checkNotNull(zak);
        this.mClassName = (String) Preconditions.checkNotNull(str);
        this.zard = 2;
    }

    @KeepForSdk
    public static <T extends FastJsonResponse & SafeParcelable> SafeParcelResponse from(T t) {
        zak zak;
        SafeParcelResponse safeParcelResponse;
        T t2 = t;
        String canonicalName = t2.getClass().getCanonicalName();
        T t3 = t2;
        new zak(t3.getClass());
        zak zak2 = zak;
        zak zak3 = zak2;
        zaa(zak2, t3);
        zak3.zacs();
        zak3.zacr();
        new SafeParcelResponse((SafeParcelable) t2, zak3, canonicalName);
        return safeParcelResponse;
    }

    private static void zaa(zak zak, FastJsonResponse fastJsonResponse) {
        String str;
        String str2;
        String str3;
        String str4;
        zak zak2 = zak;
        FastJsonResponse fastJsonResponse2 = fastJsonResponse;
        Class<?> cls = fastJsonResponse2.getClass();
        if (!zak2.zaa(cls)) {
            Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = fastJsonResponse2.getFieldMappings();
            zak2.zaa(cls, fieldMappings);
            for (String str5 : fieldMappings.keySet()) {
                FastJsonResponse.Field field = fieldMappings.get(str5);
                FastJsonResponse.Field field2 = field;
                Class<? extends FastJsonResponse> cls2 = field.zapx;
                Class<? extends FastJsonResponse> cls3 = cls2;
                if (cls2 != null) {
                    try {
                        zaa(zak2, (FastJsonResponse) cls3.newInstance());
                    } catch (InstantiationException e) {
                        InstantiationException instantiationException = e;
                        IllegalStateException illegalStateException = r14;
                        String valueOf = String.valueOf(field2.zapx.getCanonicalName());
                        String str6 = valueOf;
                        if (valueOf.length() != 0) {
                            str4 = "Could not instantiate an object of type ".concat(str6);
                        } else {
                            str4 = str3;
                            new String("Could not instantiate an object of type ");
                        }
                        IllegalStateException illegalStateException2 = new IllegalStateException(str4, instantiationException);
                        throw illegalStateException;
                    } catch (IllegalAccessException e2) {
                        IllegalAccessException illegalAccessException = e2;
                        IllegalStateException illegalStateException3 = r14;
                        String valueOf2 = String.valueOf(field2.zapx.getCanonicalName());
                        String str7 = valueOf2;
                        if (valueOf2.length() != 0) {
                            str2 = "Could not access object of type ".concat(str7);
                        } else {
                            str2 = str;
                            new String("Could not access object of type ");
                        }
                        IllegalStateException illegalStateException4 = new IllegalStateException(str2, illegalAccessException);
                        throw illegalStateException3;
                    }
                }
            }
        }
    }

    @SafeParcelable.Constructor
    SafeParcelResponse(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) Parcel parcel, @SafeParcelable.Param(mo25280id = 3) zak zak) {
        this.zalf = i;
        this.zarb = (Parcel) Preconditions.checkNotNull(parcel);
        this.zarc = 2;
        this.zapz = zak;
        if (this.zapz == null) {
            this.mClassName = null;
        } else {
            this.mClassName = this.zapz.zact();
        }
        this.zard = 2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zak zak;
        Throwable th;
        StringBuilder sb;
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.zalf);
        SafeParcelWriter.writeParcel(parcel2, 2, zacu(), false);
        Parcel parcel3 = parcel2;
        switch (this.zarc) {
            case 0:
                zak = null;
                break;
            case 1:
                zak = this.zapz;
                break;
            case 2:
                zak = this.zapz;
                break;
            default:
                Throwable th2 = th;
                int i3 = this.zarc;
                new StringBuilder(34);
                new IllegalStateException(sb.append("Invalid creation type: ").append(i3).toString());
                throw th2;
        }
        SafeParcelWriter.writeParcelable(parcel3, 3, zak, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    private final Parcel zacu() {
        switch (this.zard) {
            case 0:
                this.zare = SafeParcelWriter.beginObjectHeader(this.zarb);
                break;
            case 1:
                break;
        }
        SafeParcelWriter.finishObjectHeader(this.zarb, this.zare);
        this.zard = 2;
        return this.zarb;
    }

    public Map<String, FastJsonResponse.Field<?, ?>> getFieldMappings() {
        if (this.zapz == null) {
            return null;
        }
        return this.zapz.zai(this.mClassName);
    }

    public Object getValueObject(String str) {
        Throwable th;
        String str2 = str;
        Throwable th2 = th;
        new UnsupportedOperationException("Converting to JSON does not require this method.");
        throw th2;
    }

    public boolean isPrimitiveFieldSet(String str) {
        Throwable th;
        String str2 = str;
        Throwable th2 = th;
        new UnsupportedOperationException("Converting to JSON does not require this method.");
        throw th2;
    }

    private final void zab(FastJsonResponse.Field<?, ?> field) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        if (!(field.zapw != -1)) {
            Throwable th5 = th4;
            new IllegalStateException("Field does not have a valid safe parcelable field id.");
            throw th5;
        } else if (this.zarb == null) {
            Throwable th6 = th3;
            new IllegalStateException("Internal Parcel object is null.");
            throw th6;
        } else {
            switch (this.zard) {
                case 0:
                    this.zare = SafeParcelWriter.beginObjectHeader(this.zarb);
                    this.zard = 1;
                    return;
                case 1:
                    return;
                case 2:
                    Throwable th7 = th;
                    new IllegalStateException("Attempted to parse JSON with a SafeParcelResponse object that is already filled with data.");
                    throw th7;
                default:
                    Throwable th8 = th2;
                    new IllegalStateException("Unknown parse state in SafeParcelResponse.");
                    throw th8;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setIntegerInternal(FastJsonResponse.Field<?, ?> field, String str, int i) {
        FastJsonResponse.Field<?, ?> field2 = field;
        String str2 = str;
        zab(field2);
        SafeParcelWriter.writeInt(this.zarb, field2.getSafeParcelableFieldId(), i);
    }

    /* access modifiers changed from: protected */
    public final void zaa(FastJsonResponse.Field<?, ?> field, String str, ArrayList<Integer> arrayList) {
        FastJsonResponse.Field<?, ?> field2 = field;
        String str2 = str;
        ArrayList<Integer> arrayList2 = arrayList;
        zab(field2);
        int size = arrayList2.size();
        int i = size;
        int[] iArr = new int[size];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = arrayList2.get(i2).intValue();
        }
        SafeParcelWriter.writeIntArray(this.zarb, field2.getSafeParcelableFieldId(), iArr, true);
    }

    /* access modifiers changed from: protected */
    public final void zaa(FastJsonResponse.Field<?, ?> field, String str, BigInteger bigInteger) {
        FastJsonResponse.Field<?, ?> field2 = field;
        String str2 = str;
        zab(field2);
        SafeParcelWriter.writeBigInteger(this.zarb, field2.getSafeParcelableFieldId(), bigInteger, true);
    }

    /* access modifiers changed from: protected */
    public final void zab(FastJsonResponse.Field<?, ?> field, String str, ArrayList<BigInteger> arrayList) {
        FastJsonResponse.Field<?, ?> field2 = field;
        String str2 = str;
        ArrayList<BigInteger> arrayList2 = arrayList;
        zab(field2);
        int size = arrayList2.size();
        int i = size;
        BigInteger[] bigIntegerArr = new BigInteger[size];
        for (int i2 = 0; i2 < i; i2++) {
            bigIntegerArr[i2] = arrayList2.get(i2);
        }
        SafeParcelWriter.writeBigIntegerArray(this.zarb, field2.getSafeParcelableFieldId(), bigIntegerArr, true);
    }

    /* access modifiers changed from: protected */
    public void setLongInternal(FastJsonResponse.Field<?, ?> field, String str, long j) {
        FastJsonResponse.Field<?, ?> field2 = field;
        String str2 = str;
        zab(field2);
        SafeParcelWriter.writeLong(this.zarb, field2.getSafeParcelableFieldId(), j);
    }

    /* access modifiers changed from: protected */
    public final void zac(FastJsonResponse.Field<?, ?> field, String str, ArrayList<Long> arrayList) {
        FastJsonResponse.Field<?, ?> field2 = field;
        String str2 = str;
        ArrayList<Long> arrayList2 = arrayList;
        zab(field2);
        int size = arrayList2.size();
        int i = size;
        long[] jArr = new long[size];
        for (int i2 = 0; i2 < i; i2++) {
            jArr[i2] = arrayList2.get(i2).longValue();
        }
        SafeParcelWriter.writeLongArray(this.zarb, field2.getSafeParcelableFieldId(), jArr, true);
    }

    /* access modifiers changed from: protected */
    public final void zaa(FastJsonResponse.Field<?, ?> field, String str, float f) {
        FastJsonResponse.Field<?, ?> field2 = field;
        String str2 = str;
        zab(field2);
        SafeParcelWriter.writeFloat(this.zarb, field2.getSafeParcelableFieldId(), f);
    }

    /* access modifiers changed from: protected */
    public final void zad(FastJsonResponse.Field<?, ?> field, String str, ArrayList<Float> arrayList) {
        FastJsonResponse.Field<?, ?> field2 = field;
        String str2 = str;
        ArrayList<Float> arrayList2 = arrayList;
        zab(field2);
        int size = arrayList2.size();
        int i = size;
        float[] fArr = new float[size];
        for (int i2 = 0; i2 < i; i2++) {
            fArr[i2] = arrayList2.get(i2).floatValue();
        }
        SafeParcelWriter.writeFloatArray(this.zarb, field2.getSafeParcelableFieldId(), fArr, true);
    }

    /* access modifiers changed from: protected */
    public final void zaa(FastJsonResponse.Field<?, ?> field, String str, double d) {
        FastJsonResponse.Field<?, ?> field2 = field;
        String str2 = str;
        zab(field2);
        SafeParcelWriter.writeDouble(this.zarb, field2.getSafeParcelableFieldId(), d);
    }

    /* access modifiers changed from: protected */
    public final void zae(FastJsonResponse.Field<?, ?> field, String str, ArrayList<Double> arrayList) {
        FastJsonResponse.Field<?, ?> field2 = field;
        String str2 = str;
        ArrayList<Double> arrayList2 = arrayList;
        zab(field2);
        int size = arrayList2.size();
        int i = size;
        double[] dArr = new double[size];
        for (int i2 = 0; i2 < i; i2++) {
            dArr[i2] = arrayList2.get(i2).doubleValue();
        }
        SafeParcelWriter.writeDoubleArray(this.zarb, field2.getSafeParcelableFieldId(), dArr, true);
    }

    /* access modifiers changed from: protected */
    public final void zaa(FastJsonResponse.Field<?, ?> field, String str, BigDecimal bigDecimal) {
        FastJsonResponse.Field<?, ?> field2 = field;
        String str2 = str;
        zab(field2);
        SafeParcelWriter.writeBigDecimal(this.zarb, field2.getSafeParcelableFieldId(), bigDecimal, true);
    }

    /* access modifiers changed from: protected */
    public final void zaf(FastJsonResponse.Field<?, ?> field, String str, ArrayList<BigDecimal> arrayList) {
        FastJsonResponse.Field<?, ?> field2 = field;
        String str2 = str;
        ArrayList<BigDecimal> arrayList2 = arrayList;
        zab(field2);
        int size = arrayList2.size();
        int i = size;
        BigDecimal[] bigDecimalArr = new BigDecimal[size];
        for (int i2 = 0; i2 < i; i2++) {
            bigDecimalArr[i2] = arrayList2.get(i2);
        }
        SafeParcelWriter.writeBigDecimalArray(this.zarb, field2.getSafeParcelableFieldId(), bigDecimalArr, true);
    }

    /* access modifiers changed from: protected */
    public void setBooleanInternal(FastJsonResponse.Field<?, ?> field, String str, boolean z) {
        FastJsonResponse.Field<?, ?> field2 = field;
        String str2 = str;
        zab(field2);
        SafeParcelWriter.writeBoolean(this.zarb, field2.getSafeParcelableFieldId(), z);
    }

    /* access modifiers changed from: protected */
    public final void zag(FastJsonResponse.Field<?, ?> field, String str, ArrayList<Boolean> arrayList) {
        FastJsonResponse.Field<?, ?> field2 = field;
        String str2 = str;
        ArrayList<Boolean> arrayList2 = arrayList;
        zab(field2);
        int size = arrayList2.size();
        int i = size;
        boolean[] zArr = new boolean[size];
        for (int i2 = 0; i2 < i; i2++) {
            zArr[i2] = arrayList2.get(i2).booleanValue();
        }
        SafeParcelWriter.writeBooleanArray(this.zarb, field2.getSafeParcelableFieldId(), zArr, true);
    }

    /* access modifiers changed from: protected */
    public void setStringInternal(FastJsonResponse.Field<?, ?> field, String str, String str2) {
        FastJsonResponse.Field<?, ?> field2 = field;
        String str3 = str;
        zab(field2);
        SafeParcelWriter.writeString(this.zarb, field2.getSafeParcelableFieldId(), str2, true);
    }

    /* access modifiers changed from: protected */
    public void setStringsInternal(FastJsonResponse.Field<?, ?> field, String str, ArrayList<String> arrayList) {
        FastJsonResponse.Field<?, ?> field2 = field;
        String str2 = str;
        ArrayList<String> arrayList2 = arrayList;
        zab(field2);
        int size = arrayList2.size();
        int i = size;
        String[] strArr = new String[size];
        for (int i2 = 0; i2 < i; i2++) {
            strArr[i2] = arrayList2.get(i2);
        }
        SafeParcelWriter.writeStringArray(this.zarb, field2.getSafeParcelableFieldId(), strArr, true);
    }

    /* access modifiers changed from: protected */
    public void setDecodedBytesInternal(FastJsonResponse.Field<?, ?> field, String str, byte[] bArr) {
        FastJsonResponse.Field<?, ?> field2 = field;
        String str2 = str;
        zab(field2);
        SafeParcelWriter.writeByteArray(this.zarb, field2.getSafeParcelableFieldId(), bArr, true);
    }

    /* access modifiers changed from: protected */
    public final void zaa(FastJsonResponse.Field<?, ?> field, String str, Map<String, String> map) {
        Bundle bundle;
        FastJsonResponse.Field<?, ?> field2 = field;
        String str2 = str;
        Map<String, String> map2 = map;
        zab(field2);
        new Bundle();
        Bundle bundle2 = bundle;
        for (String next : map2.keySet()) {
            bundle2.putString(next, map2.get(next));
        }
        SafeParcelWriter.writeBundle(this.zarb, field2.getSafeParcelableFieldId(), bundle2, true);
    }

    public <T extends FastJsonResponse> void addConcreteTypeInternal(FastJsonResponse.Field<?, ?> field, String str, T t) {
        FastJsonResponse.Field<?, ?> field2 = field;
        String str2 = str;
        zab(field2);
        SafeParcelWriter.writeParcel(this.zarb, field2.getSafeParcelableFieldId(), ((SafeParcelResponse) t).zacu(), true);
    }

    public <T extends FastJsonResponse> void addConcreteTypeArrayInternal(FastJsonResponse.Field<?, ?> field, String str, ArrayList<T> arrayList) {
        ArrayList arrayList2;
        FastJsonResponse.Field<?, ?> field2 = field;
        String str2 = str;
        ArrayList<T> arrayList3 = arrayList;
        zab(field2);
        new ArrayList();
        ArrayList arrayList4 = arrayList2;
        int size = arrayList3.size();
        ArrayList arrayList5 = arrayList3;
        ArrayList arrayList6 = arrayList5;
        int size2 = arrayList5.size();
        int i = 0;
        while (i < size2) {
            i++;
            boolean add = arrayList4.add(((SafeParcelResponse) ((FastJsonResponse) arrayList6.get(i))).zacu());
        }
        SafeParcelWriter.writeParcelList(this.zarb, field2.getSafeParcelableFieldId(), arrayList4, true);
    }

    public String toString() {
        StringBuilder sb;
        Object checkNotNull = Preconditions.checkNotNull(this.zapz, "Cannot convert to JSON on client side.");
        Parcel zacu = zacu();
        zacu.setDataPosition(0);
        new StringBuilder(100);
        StringBuilder sb2 = sb;
        zaa(sb2, this.zapz.zai(this.mClassName), zacu);
        return sb2.toString();
    }

    private final void zaa(StringBuilder sb, Map<String, FastJsonResponse.Field<?, ?>> map, Parcel parcel) {
        SparseArray sparseArray;
        Throwable th;
        StringBuilder sb2;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        StringBuilder sb3;
        HashMap hashMap;
        StringBuilder sb4 = sb;
        Parcel parcel2 = parcel;
        new SparseArray();
        SparseArray sparseArray2 = sparseArray;
        for (Map.Entry next : map.entrySet()) {
            sparseArray2.put(((FastJsonResponse.Field) next.getValue()).getSafeParcelableFieldId(), next);
        }
        SparseArray sparseArray3 = sparseArray2;
        StringBuilder append = sb4.append('{');
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel2);
        boolean z = false;
        while (parcel2.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel2);
            int i = readHeader;
            Map.Entry entry = (Map.Entry) sparseArray3.get(SafeParcelReader.getFieldId(readHeader));
            Map.Entry entry2 = entry;
            if (entry != null) {
                if (z) {
                    StringBuilder append2 = sb4.append(",");
                }
                int i2 = i;
                Parcel parcel3 = parcel2;
                FastJsonResponse.Field field = (FastJsonResponse.Field) entry2.getValue();
                StringBuilder sb5 = sb4;
                StringBuilder append3 = sb5.append("\"").append((String) entry2.getKey()).append("\":");
                if (field.zacn()) {
                    int i3 = i2;
                    Parcel parcel4 = parcel3;
                    FastJsonResponse.Field field2 = field;
                    StringBuilder sb6 = sb5;
                    switch (field2.zapt) {
                        case 0:
                            zab(sb6, (FastJsonResponse.Field<?, ?>) field2, zab(field2, (Object) Integer.valueOf(SafeParcelReader.readInt(parcel4, i3))));
                            break;
                        case 1:
                            zab(sb6, (FastJsonResponse.Field<?, ?>) field2, zab(field2, (Object) SafeParcelReader.createBigInteger(parcel4, i3)));
                            break;
                        case 2:
                            zab(sb6, (FastJsonResponse.Field<?, ?>) field2, zab(field2, (Object) Long.valueOf(SafeParcelReader.readLong(parcel4, i3))));
                            break;
                        case 3:
                            zab(sb6, (FastJsonResponse.Field<?, ?>) field2, zab(field2, (Object) Float.valueOf(SafeParcelReader.readFloat(parcel4, i3))));
                            break;
                        case 4:
                            zab(sb6, (FastJsonResponse.Field<?, ?>) field2, zab(field2, (Object) Double.valueOf(SafeParcelReader.readDouble(parcel4, i3))));
                            break;
                        case 5:
                            zab(sb6, (FastJsonResponse.Field<?, ?>) field2, zab(field2, (Object) SafeParcelReader.createBigDecimal(parcel4, i3)));
                            break;
                        case 6:
                            zab(sb6, (FastJsonResponse.Field<?, ?>) field2, zab(field2, (Object) Boolean.valueOf(SafeParcelReader.readBoolean(parcel4, i3))));
                            break;
                        case 7:
                            zab(sb6, (FastJsonResponse.Field<?, ?>) field2, zab(field2, (Object) SafeParcelReader.createString(parcel4, i3)));
                            break;
                        case 8:
                        case 9:
                            zab(sb6, (FastJsonResponse.Field<?, ?>) field2, zab(field2, (Object) SafeParcelReader.createByteArray(parcel4, i3)));
                            break;
                        case 10:
                            Bundle createBundle = SafeParcelReader.createBundle(parcel4, i3);
                            new HashMap();
                            HashMap hashMap2 = hashMap;
                            for (String str : createBundle.keySet()) {
                                Object put = hashMap2.put(str, createBundle.getString(str));
                            }
                            zab(sb6, (FastJsonResponse.Field<?, ?>) field2, zab(field2, (Object) hashMap2));
                            break;
                        case 11:
                            Throwable th7 = th5;
                            new IllegalArgumentException("Method does not accept concrete type.");
                            throw th7;
                        default:
                            Throwable th8 = th6;
                            int i4 = field2.zapt;
                            new StringBuilder(36);
                            new IllegalArgumentException(sb3.append("Unknown field out type = ").append(i4).toString());
                            throw th8;
                    }
                } else {
                    int i5 = i2;
                    Parcel parcel5 = parcel3;
                    FastJsonResponse.Field field3 = field;
                    StringBuilder sb7 = sb5;
                    if (field3.zapu) {
                        StringBuilder append4 = sb7.append("[");
                        switch (field3.zapt) {
                            case 0:
                                ArrayUtils.writeArray(sb7, SafeParcelReader.createIntArray(parcel5, i5));
                                break;
                            case 1:
                                ArrayUtils.writeArray(sb7, (T[]) SafeParcelReader.createBigIntegerArray(parcel5, i5));
                                break;
                            case 2:
                                ArrayUtils.writeArray(sb7, SafeParcelReader.createLongArray(parcel5, i5));
                                break;
                            case 3:
                                ArrayUtils.writeArray(sb7, SafeParcelReader.createFloatArray(parcel5, i5));
                                break;
                            case 4:
                                ArrayUtils.writeArray(sb7, SafeParcelReader.createDoubleArray(parcel5, i5));
                                break;
                            case 5:
                                ArrayUtils.writeArray(sb7, (T[]) SafeParcelReader.createBigDecimalArray(parcel5, i5));
                                break;
                            case 6:
                                ArrayUtils.writeArray(sb7, SafeParcelReader.createBooleanArray(parcel5, i5));
                                break;
                            case 7:
                                ArrayUtils.writeStringArray(sb7, SafeParcelReader.createStringArray(parcel5, i5));
                                break;
                            case 8:
                            case 9:
                            case 10:
                                Throwable th9 = th3;
                                new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                                throw th9;
                            case 11:
                                Parcel[] createParcelArray = SafeParcelReader.createParcelArray(parcel5, i5);
                                Parcel[] parcelArr = createParcelArray;
                                int length = createParcelArray.length;
                                for (int i6 = 0; i6 < length; i6++) {
                                    if (i6 > 0) {
                                        StringBuilder append5 = sb7.append(",");
                                    }
                                    parcelArr[i6].setDataPosition(0);
                                    zaa(sb7, field3.zacq(), parcelArr[i6]);
                                }
                                break;
                            default:
                                Throwable th10 = th4;
                                new IllegalStateException("Unknown field type out.");
                                throw th10;
                        }
                        StringBuilder append6 = sb7.append("]");
                    } else {
                        switch (field3.zapt) {
                            case 0:
                                StringBuilder append7 = sb7.append(SafeParcelReader.readInt(parcel5, i5));
                                break;
                            case 1:
                                StringBuilder append8 = sb7.append(SafeParcelReader.createBigInteger(parcel5, i5));
                                break;
                            case 2:
                                StringBuilder append9 = sb7.append(SafeParcelReader.readLong(parcel5, i5));
                                break;
                            case 3:
                                StringBuilder append10 = sb7.append(SafeParcelReader.readFloat(parcel5, i5));
                                break;
                            case 4:
                                StringBuilder append11 = sb7.append(SafeParcelReader.readDouble(parcel5, i5));
                                break;
                            case 5:
                                StringBuilder append12 = sb7.append(SafeParcelReader.createBigDecimal(parcel5, i5));
                                break;
                            case 6:
                                StringBuilder append13 = sb7.append(SafeParcelReader.readBoolean(parcel5, i5));
                                break;
                            case 7:
                                StringBuilder append14 = sb7.append("\"").append(JsonUtils.escapeString(SafeParcelReader.createString(parcel5, i5))).append("\"");
                                break;
                            case 8:
                                StringBuilder append15 = sb7.append("\"").append(Base64Utils.encode(SafeParcelReader.createByteArray(parcel5, i5))).append("\"");
                                break;
                            case 9:
                                StringBuilder append16 = sb7.append("\"").append(Base64Utils.encodeUrlSafe(SafeParcelReader.createByteArray(parcel5, i5)));
                                StringBuilder append17 = sb7.append("\"");
                                break;
                            case 10:
                                Bundle createBundle2 = SafeParcelReader.createBundle(parcel5, i5);
                                Bundle bundle = createBundle2;
                                Set<String> keySet = createBundle2.keySet();
                                int size = keySet.size();
                                StringBuilder append18 = sb7.append("{");
                                boolean z2 = true;
                                for (String str2 : keySet) {
                                    if (!z2) {
                                        StringBuilder append19 = sb7.append(",");
                                    }
                                    z2 = false;
                                    StringBuilder append20 = sb7.append("\"").append(str2).append("\"");
                                    StringBuilder append21 = sb7.append(":");
                                    StringBuilder append22 = sb7.append("\"").append(JsonUtils.escapeString(bundle.getString(str2))).append("\"");
                                }
                                StringBuilder append23 = sb7.append("}");
                                break;
                            case 11:
                                Parcel createParcel = SafeParcelReader.createParcel(parcel5, i5);
                                createParcel.setDataPosition(0);
                                zaa(sb7, field3.zacq(), createParcel);
                                break;
                            default:
                                Throwable th11 = th2;
                                new IllegalStateException("Unknown field type out");
                                throw th11;
                        }
                    }
                }
                z = true;
            }
        }
        if (parcel2.dataPosition() != validateObjectHeader) {
            Throwable th12 = th;
            new StringBuilder(37);
            new SafeParcelReader.ParseException(sb2.append("Overread allowed size end=").append(validateObjectHeader).toString(), parcel2);
            throw th12;
        }
        StringBuilder append24 = sb4.append('}');
    }

    private final void zab(StringBuilder sb, FastJsonResponse.Field<?, ?> field, Object obj) {
        StringBuilder sb2 = sb;
        FastJsonResponse.Field<?, ?> field2 = field;
        Object obj2 = obj;
        if (field2.zaps) {
            ArrayList arrayList = (ArrayList) obj2;
            FastJsonResponse.Field<?, ?> field3 = field2;
            StringBuilder sb3 = sb2;
            StringBuilder sb4 = sb3;
            StringBuilder append = sb3.append("[");
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (i != 0) {
                    StringBuilder append2 = sb4.append(",");
                }
                zaa(sb4, field3.zapr, arrayList.get(i));
            }
            StringBuilder append3 = sb4.append("]");
            return;
        }
        zaa(sb2, field2.zapr, obj2);
    }

    private static void zaa(StringBuilder sb, int i, Object obj) {
        Throwable th;
        Throwable th2;
        StringBuilder sb2;
        StringBuilder sb3 = sb;
        int i2 = i;
        Object obj2 = obj;
        switch (i2) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                StringBuilder append = sb3.append(obj2);
                return;
            case 7:
                StringBuilder append2 = sb3.append("\"").append(JsonUtils.escapeString(obj2.toString())).append("\"");
                return;
            case 8:
                StringBuilder append3 = sb3.append("\"").append(Base64Utils.encode((byte[]) obj2)).append("\"");
                return;
            case 9:
                StringBuilder append4 = sb3.append("\"").append(Base64Utils.encodeUrlSafe((byte[]) obj2));
                StringBuilder append5 = sb3.append("\"");
                return;
            case 10:
                MapUtils.writeStringMapToJson(sb3, (HashMap) obj2);
                return;
            case 11:
                Throwable th3 = th;
                new IllegalArgumentException("Method does not accept concrete type.");
                throw th3;
            default:
                Throwable th4 = th2;
                new StringBuilder(26);
                new IllegalArgumentException(sb2.append("Unknown type = ").append(i2).toString());
                throw th4;
        }
    }

    static {
        Parcelable.Creator<SafeParcelResponse> creator;
        new zap();
        CREATOR = creator;
    }
}
