package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.converter.zaa;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.MapUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ShowFirstParty
@KeepForSdk
public abstract class FastJsonResponse {

    @ShowFirstParty
    public interface FieldConverter<I, O> {
        O convert(I i);

        I convertBack(O o);

        int zacj();

        int zack();
    }

    public FastJsonResponse() {
    }

    @KeepForSdk
    public abstract Map<String, Field<?, ?>> getFieldMappings();

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract Object getValueObject(String str);

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract boolean isPrimitiveFieldSet(String str);

    /* access modifiers changed from: protected */
    @KeepForSdk
    public boolean isFieldSet(Field field) {
        Throwable th;
        Throwable th2;
        Field field2 = field;
        if (field2.zapt != 11) {
            return isPrimitiveFieldSet(field2.zapv);
        }
        if (field2.zapu) {
            String str = field2.zapv;
            Throwable th3 = th2;
            new UnsupportedOperationException("Concrete type arrays not supported");
            throw th3;
        }
        String str2 = field2.zapv;
        Throwable th4 = th;
        new UnsupportedOperationException("Concrete types not supported");
        throw th4;
    }

    @ShowFirstParty
    @SafeParcelable.Class(creator = "FieldCreator")
    @VisibleForTesting
    @KeepForSdk
    public static class Field<I, O> extends AbstractSafeParcelable {
        public static final zai CREATOR;
        @SafeParcelable.VersionField(getter = "getVersionCode", mo25283id = 1)
        private final int zalf;
        @SafeParcelable.Field(getter = "getTypeIn", mo25277id = 2)
        protected final int zapr;
        @SafeParcelable.Field(getter = "isTypeInArray", mo25277id = 3)
        protected final boolean zaps;
        @SafeParcelable.Field(getter = "getTypeOut", mo25277id = 4)
        protected final int zapt;
        @SafeParcelable.Field(getter = "isTypeOutArray", mo25277id = 5)
        protected final boolean zapu;
        @SafeParcelable.Field(getter = "getOutputFieldName", mo25277id = 6)
        protected final String zapv;
        @SafeParcelable.Field(getter = "getSafeParcelableFieldId", mo25277id = 7)
        protected final int zapw;
        protected final Class<? extends FastJsonResponse> zapx;
        @SafeParcelable.Field(getter = "getConcreteTypeName", mo25277id = 8)
        private final String zapy;
        private zak zapz;
        /* access modifiers changed from: private */
        @SafeParcelable.Field(getter = "getWrappedConverter", mo25277id = 9, type = "com.google.android.gms.common.server.converter.ConverterWrapper")
        public FieldConverter<I, O> zaqa;

        @SafeParcelable.Constructor
        Field(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) int i2, @SafeParcelable.Param(mo25280id = 3) boolean z, @SafeParcelable.Param(mo25280id = 4) int i3, @SafeParcelable.Param(mo25280id = 5) boolean z2, @SafeParcelable.Param(mo25280id = 6) String str, @SafeParcelable.Param(mo25280id = 7) int i4, @SafeParcelable.Param(mo25280id = 8) String str2, @SafeParcelable.Param(mo25280id = 9) zaa zaa) {
            String str3 = str2;
            zaa zaa2 = zaa;
            this.zalf = i;
            this.zapr = i2;
            this.zaps = z;
            this.zapt = i3;
            this.zapu = z2;
            this.zapv = str;
            this.zapw = i4;
            if (str3 == null) {
                this.zapx = null;
                this.zapy = null;
            } else {
                this.zapx = SafeParcelResponse.class;
                this.zapy = str3;
            }
            if (zaa2 == null) {
                this.zaqa = null;
                return;
            }
            this.zaqa = zaa2.zaci();
        }

        private Field(int i, boolean z, int i2, boolean z2, String str, int i3, Class<? extends FastJsonResponse> cls, FieldConverter<I, O> fieldConverter) {
            Class<? extends FastJsonResponse> cls2 = cls;
            FieldConverter<I, O> fieldConverter2 = fieldConverter;
            this.zalf = 1;
            this.zapr = i;
            this.zaps = z;
            this.zapt = i2;
            this.zapu = z2;
            this.zapv = str;
            this.zapw = i3;
            this.zapx = cls2;
            if (cls2 == null) {
                this.zapy = null;
            } else {
                this.zapy = cls2.getCanonicalName();
            }
            this.zaqa = fieldConverter2;
        }

        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.google.android.gms.common.server.response.FastJsonResponse.Field<I, O> zacl() {
            /*
                r13 = this;
                r0 = r13
                com.google.android.gms.common.server.response.FastJsonResponse$Field r1 = new com.google.android.gms.common.server.response.FastJsonResponse$Field
                r12 = r1
                r1 = r12
                r2 = r12
                r3 = r0
                int r3 = r3.zalf
                r4 = r0
                int r4 = r4.zapr
                r5 = r0
                boolean r5 = r5.zaps
                r6 = r0
                int r6 = r6.zapt
                r7 = r0
                boolean r7 = r7.zapu
                r8 = r0
                java.lang.String r8 = r8.zapv
                r9 = r0
                int r9 = r9.zapw
                r10 = r0
                java.lang.String r10 = r10.zapy
                r11 = r0
                com.google.android.gms.common.server.converter.zaa r11 = r11.zaco()
                r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11)
                r0 = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.FastJsonResponse.Field.zacl():com.google.android.gms.common.server.response.FastJsonResponse$Field");
        }

        @KeepForSdk
        public int getSafeParcelableFieldId() {
            return this.zapw;
        }

        private final String zacm() {
            if (this.zapy == null) {
                return null;
            }
            return this.zapy;
        }

        public final boolean zacn() {
            return this.zaqa != null;
        }

        public final void zaa(zak zak) {
            zak zak2 = zak;
            this.zapz = zak2;
        }

        private final zaa zaco() {
            if (this.zaqa == null) {
                return null;
            }
            return zaa.zaa(this.zaqa);
        }

        public final FastJsonResponse zacp() throws InstantiationException, IllegalAccessException {
            FastJsonResponse fastJsonResponse;
            if (this.zapx != SafeParcelResponse.class) {
                return (FastJsonResponse) this.zapx.newInstance();
            }
            Object checkNotNull = Preconditions.checkNotNull(this.zapz, "The field mapping dictionary must be set if the concrete type is a SafeParcelResponse object.");
            new SafeParcelResponse(this.zapz, this.zapy);
            return fastJsonResponse;
        }

        public final Map<String, Field<?, ?>> zacq() {
            Object checkNotNull = Preconditions.checkNotNull(this.zapy);
            Object checkNotNull2 = Preconditions.checkNotNull(this.zapz);
            return this.zapz.zai(this.zapy);
        }

        public final O convert(I i) {
            return this.zaqa.convert(i);
        }

        public final I convertBack(O o) {
            return this.zaqa.convertBack(o);
        }

        /* JADX WARNING: Multi-variable type inference failed */
        @com.google.android.gms.common.annotation.KeepForSdk
        @com.google.android.gms.common.util.VisibleForTesting
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static com.google.android.gms.common.server.response.FastJsonResponse.Field<java.lang.Integer, java.lang.Integer> forInteger(java.lang.String r13, int r14) {
            /*
                r0 = r13
                r1 = r14
                com.google.android.gms.common.server.response.FastJsonResponse$Field r2 = new com.google.android.gms.common.server.response.FastJsonResponse$Field
                r12 = r2
                r2 = r12
                r3 = r12
                r4 = 0
                r5 = 0
                r6 = 0
                r7 = 0
                r8 = r0
                r9 = r1
                r10 = 0
                r11 = 0
                r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
                r0 = r2
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.FastJsonResponse.Field.forInteger(java.lang.String, int):com.google.android.gms.common.server.response.FastJsonResponse$Field");
        }

        /* JADX WARNING: Multi-variable type inference failed */
        @com.google.android.gms.common.annotation.KeepForSdk
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static com.google.android.gms.common.server.response.FastJsonResponse.Field<java.lang.Long, java.lang.Long> forLong(java.lang.String r13, int r14) {
            /*
                r0 = r13
                r1 = r14
                com.google.android.gms.common.server.response.FastJsonResponse$Field r2 = new com.google.android.gms.common.server.response.FastJsonResponse$Field
                r12 = r2
                r2 = r12
                r3 = r12
                r4 = 2
                r5 = 0
                r6 = 2
                r7 = 0
                r8 = r0
                r9 = r1
                r10 = 0
                r11 = 0
                r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
                r0 = r2
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.FastJsonResponse.Field.forLong(java.lang.String, int):com.google.android.gms.common.server.response.FastJsonResponse$Field");
        }

        /* JADX WARNING: Multi-variable type inference failed */
        @com.google.android.gms.common.annotation.KeepForSdk
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static com.google.android.gms.common.server.response.FastJsonResponse.Field<java.lang.Float, java.lang.Float> forFloat(java.lang.String r13, int r14) {
            /*
                r0 = r13
                r1 = r14
                com.google.android.gms.common.server.response.FastJsonResponse$Field r2 = new com.google.android.gms.common.server.response.FastJsonResponse$Field
                r12 = r2
                r2 = r12
                r3 = r12
                r4 = 3
                r5 = 0
                r6 = 3
                r7 = 0
                r8 = r0
                r9 = r1
                r10 = 0
                r11 = 0
                r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
                r0 = r2
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.FastJsonResponse.Field.forFloat(java.lang.String, int):com.google.android.gms.common.server.response.FastJsonResponse$Field");
        }

        /* JADX WARNING: Multi-variable type inference failed */
        @com.google.android.gms.common.annotation.KeepForSdk
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static com.google.android.gms.common.server.response.FastJsonResponse.Field<java.lang.Double, java.lang.Double> forDouble(java.lang.String r13, int r14) {
            /*
                r0 = r13
                r1 = r14
                com.google.android.gms.common.server.response.FastJsonResponse$Field r2 = new com.google.android.gms.common.server.response.FastJsonResponse$Field
                r12 = r2
                r2 = r12
                r3 = r12
                r4 = 4
                r5 = 0
                r6 = 4
                r7 = 0
                r8 = r0
                r9 = r1
                r10 = 0
                r11 = 0
                r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
                r0 = r2
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.FastJsonResponse.Field.forDouble(java.lang.String, int):com.google.android.gms.common.server.response.FastJsonResponse$Field");
        }

        /* JADX WARNING: Multi-variable type inference failed */
        @com.google.android.gms.common.annotation.KeepForSdk
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static com.google.android.gms.common.server.response.FastJsonResponse.Field<java.lang.Boolean, java.lang.Boolean> forBoolean(java.lang.String r13, int r14) {
            /*
                r0 = r13
                r1 = r14
                com.google.android.gms.common.server.response.FastJsonResponse$Field r2 = new com.google.android.gms.common.server.response.FastJsonResponse$Field
                r12 = r2
                r2 = r12
                r3 = r12
                r4 = 6
                r5 = 0
                r6 = 6
                r7 = 0
                r8 = r0
                r9 = r1
                r10 = 0
                r11 = 0
                r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
                r0 = r2
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.FastJsonResponse.Field.forBoolean(java.lang.String, int):com.google.android.gms.common.server.response.FastJsonResponse$Field");
        }

        /* JADX WARNING: Multi-variable type inference failed */
        @com.google.android.gms.common.annotation.KeepForSdk
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static com.google.android.gms.common.server.response.FastJsonResponse.Field<java.lang.String, java.lang.String> forString(java.lang.String r13, int r14) {
            /*
                r0 = r13
                r1 = r14
                com.google.android.gms.common.server.response.FastJsonResponse$Field r2 = new com.google.android.gms.common.server.response.FastJsonResponse$Field
                r12 = r2
                r2 = r12
                r3 = r12
                r4 = 7
                r5 = 0
                r6 = 7
                r7 = 0
                r8 = r0
                r9 = r1
                r10 = 0
                r11 = 0
                r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
                r0 = r2
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.FastJsonResponse.Field.forString(java.lang.String, int):com.google.android.gms.common.server.response.FastJsonResponse$Field");
        }

        /* JADX WARNING: Multi-variable type inference failed */
        @com.google.android.gms.common.annotation.KeepForSdk
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static com.google.android.gms.common.server.response.FastJsonResponse.Field<java.util.ArrayList<java.lang.String>, java.util.ArrayList<java.lang.String>> forStrings(java.lang.String r13, int r14) {
            /*
                r0 = r13
                r1 = r14
                com.google.android.gms.common.server.response.FastJsonResponse$Field r2 = new com.google.android.gms.common.server.response.FastJsonResponse$Field
                r12 = r2
                r2 = r12
                r3 = r12
                r4 = 7
                r5 = 1
                r6 = 7
                r7 = 1
                r8 = r0
                r9 = r1
                r10 = 0
                r11 = 0
                r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
                r0 = r2
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.FastJsonResponse.Field.forStrings(java.lang.String, int):com.google.android.gms.common.server.response.FastJsonResponse$Field");
        }

        /* JADX WARNING: Multi-variable type inference failed */
        @com.google.android.gms.common.annotation.KeepForSdk
        @com.google.android.gms.common.util.VisibleForTesting
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static com.google.android.gms.common.server.response.FastJsonResponse.Field<byte[], byte[]> forBase64(java.lang.String r13, int r14) {
            /*
                r0 = r13
                r1 = r14
                com.google.android.gms.common.server.response.FastJsonResponse$Field r2 = new com.google.android.gms.common.server.response.FastJsonResponse$Field
                r12 = r2
                r2 = r12
                r3 = r12
                r4 = 8
                r5 = 0
                r6 = 8
                r7 = 0
                r8 = r0
                r9 = r1
                r10 = 0
                r11 = 0
                r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
                r0 = r2
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.FastJsonResponse.Field.forBase64(java.lang.String, int):com.google.android.gms.common.server.response.FastJsonResponse$Field");
        }

        /* JADX WARNING: Multi-variable type inference failed */
        @com.google.android.gms.common.annotation.KeepForSdk
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static <T extends com.google.android.gms.common.server.response.FastJsonResponse> com.google.android.gms.common.server.response.FastJsonResponse.Field<T, T> forConcreteType(java.lang.String r14, int r15, java.lang.Class<T> r16) {
            /*
                r0 = r14
                r1 = r15
                r2 = r16
                com.google.android.gms.common.server.response.FastJsonResponse$Field r3 = new com.google.android.gms.common.server.response.FastJsonResponse$Field
                r13 = r3
                r3 = r13
                r4 = r13
                r5 = 11
                r6 = 0
                r7 = 11
                r8 = 0
                r9 = r0
                r10 = r1
                r11 = r2
                r12 = 0
                r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12)
                r0 = r3
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.FastJsonResponse.Field.forConcreteType(java.lang.String, int, java.lang.Class):com.google.android.gms.common.server.response.FastJsonResponse$Field");
        }

        /* JADX WARNING: Multi-variable type inference failed */
        @com.google.android.gms.common.annotation.KeepForSdk
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static <T extends com.google.android.gms.common.server.response.FastJsonResponse> com.google.android.gms.common.server.response.FastJsonResponse.Field<java.util.ArrayList<T>, java.util.ArrayList<T>> forConcreteTypeArray(java.lang.String r14, int r15, java.lang.Class<T> r16) {
            /*
                r0 = r14
                r1 = r15
                r2 = r16
                com.google.android.gms.common.server.response.FastJsonResponse$Field r3 = new com.google.android.gms.common.server.response.FastJsonResponse$Field
                r13 = r3
                r3 = r13
                r4 = r13
                r5 = 11
                r6 = 1
                r7 = 11
                r8 = 1
                r9 = r0
                r10 = r1
                r11 = r2
                r12 = 0
                r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12)
                r0 = r3
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.FastJsonResponse.Field.forConcreteTypeArray(java.lang.String, int, java.lang.Class):com.google.android.gms.common.server.response.FastJsonResponse$Field");
        }

        @KeepForSdk
        public static Field withConverter(String str, int i, FieldConverter<?, ?> fieldConverter, boolean z) {
            Field field;
            FieldConverter<?, ?> fieldConverter2 = fieldConverter;
            new Field(fieldConverter2.zacj(), z, fieldConverter2.zack(), false, str, i, (Class<? extends FastJsonResponse>) null, fieldConverter2);
            return field;
        }

        public void writeToParcel(Parcel parcel, int i) {
            Parcel parcel2 = parcel;
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
            SafeParcelWriter.writeInt(parcel2, 1, this.zalf);
            SafeParcelWriter.writeInt(parcel2, 2, this.zapr);
            SafeParcelWriter.writeBoolean(parcel2, 3, this.zaps);
            SafeParcelWriter.writeInt(parcel2, 4, this.zapt);
            SafeParcelWriter.writeBoolean(parcel2, 5, this.zapu);
            SafeParcelWriter.writeString(parcel2, 6, this.zapv, false);
            SafeParcelWriter.writeInt(parcel2, 7, getSafeParcelableFieldId());
            SafeParcelWriter.writeString(parcel2, 8, zacm(), false);
            SafeParcelWriter.writeParcelable(parcel2, 9, zaco(), i, false);
            SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
        }

        public String toString() {
            Objects.ToStringHelper add = Objects.toStringHelper(this).add("versionCode", Integer.valueOf(this.zalf)).add("typeIn", Integer.valueOf(this.zapr)).add("typeInArray", Boolean.valueOf(this.zaps)).add("typeOut", Integer.valueOf(this.zapt)).add("typeOutArray", Boolean.valueOf(this.zapu)).add("outputFieldName", this.zapv).add("safeParcelFieldId", Integer.valueOf(this.zapw)).add("concreteTypeName", zacm());
            Class<? extends FastJsonResponse> cls = this.zapx;
            Class<? extends FastJsonResponse> cls2 = cls;
            if (cls != null) {
                Objects.ToStringHelper add2 = add.add("concreteType.class", cls2.getCanonicalName());
            }
            if (this.zaqa != null) {
                Objects.ToStringHelper add3 = add.add("converterName", this.zaqa.getClass().getCanonicalName());
            }
            return add.toString();
        }

        static {
            zai zai;
            new zai();
            CREATOR = zai;
        }
    }

    private final <I, O> void zaa(Field<I, O> field, I i) {
        Throwable th;
        StringBuilder sb;
        Field<I, O> field2 = field;
        String str = field2.zapv;
        O convert = field2.convert(i);
        switch (field2.zapt) {
            case 0:
                if (zaa(str, convert)) {
                    setIntegerInternal(field2, str, ((Integer) convert).intValue());
                    return;
                }
                return;
            case 1:
                zaa((Field<?, ?>) field2, str, (BigInteger) convert);
                return;
            case 2:
                if (zaa(str, convert)) {
                    setLongInternal(field2, str, ((Long) convert).longValue());
                    return;
                }
                return;
            case 4:
                if (zaa(str, convert)) {
                    zaa((Field<?, ?>) field2, str, ((Double) convert).doubleValue());
                    return;
                }
                return;
            case 5:
                zaa((Field<?, ?>) field2, str, (BigDecimal) convert);
                return;
            case 6:
                if (zaa(str, convert)) {
                    setBooleanInternal(field2, str, ((Boolean) convert).booleanValue());
                    return;
                }
                return;
            case 7:
                setStringInternal(field2, str, (String) convert);
                return;
            case 8:
            case 9:
                if (zaa(str, convert)) {
                    setDecodedBytesInternal(field2, str, (byte[]) convert);
                    return;
                }
                return;
            default:
                Throwable th2 = th;
                int i2 = field2.zapt;
                new StringBuilder(44);
                new IllegalStateException(sb.append("Unsupported type for conversion: ").append(i2).toString());
                throw th2;
        }
    }

    protected static <O, I> I zab(Field<I, O> field, Object obj) {
        Field<I, O> field2 = field;
        Object obj2 = obj;
        if (field2.zaqa != null) {
            return field2.convertBack(obj2);
        }
        return obj2;
    }

    public final <O> void zaa(Field<Integer, O> field, int i) {
        Field<Integer, O> field2 = field;
        int i2 = i;
        if (field2.zaqa != null) {
            zaa(field2, Integer.valueOf(i2));
        } else {
            setIntegerInternal(field2, field2.zapv, i2);
        }
    }

    public final <O> void zaa(Field<ArrayList<Integer>, O> field, ArrayList<Integer> arrayList) {
        Field<ArrayList<Integer>, O> field2 = field;
        ArrayList<Integer> arrayList2 = arrayList;
        if (field2.zaqa != null) {
            zaa(field2, arrayList2);
        } else {
            zaa((Field<?, ?>) field2, field2.zapv, arrayList2);
        }
    }

    public final <O> void zaa(Field<BigInteger, O> field, BigInteger bigInteger) {
        Field<BigInteger, O> field2 = field;
        BigInteger bigInteger2 = bigInteger;
        if (field2.zaqa != null) {
            zaa(field2, bigInteger2);
        } else {
            zaa((Field<?, ?>) field2, field2.zapv, bigInteger2);
        }
    }

    public final <O> void zab(Field<ArrayList<BigInteger>, O> field, ArrayList<BigInteger> arrayList) {
        Field<ArrayList<BigInteger>, O> field2 = field;
        ArrayList<BigInteger> arrayList2 = arrayList;
        if (field2.zaqa != null) {
            zaa(field2, arrayList2);
        } else {
            zab(field2, field2.zapv, arrayList2);
        }
    }

    public final <O> void zaa(Field<Long, O> field, long j) {
        Field<Long, O> field2 = field;
        long j2 = j;
        if (field2.zaqa != null) {
            zaa(field2, Long.valueOf(j2));
        } else {
            setLongInternal(field2, field2.zapv, j2);
        }
    }

    public final <O> void zac(Field<ArrayList<Long>, O> field, ArrayList<Long> arrayList) {
        Field<ArrayList<Long>, O> field2 = field;
        ArrayList<Long> arrayList2 = arrayList;
        if (field2.zaqa != null) {
            zaa(field2, arrayList2);
        } else {
            zac(field2, field2.zapv, arrayList2);
        }
    }

    public final <O> void zaa(Field<Float, O> field, float f) {
        Field<Float, O> field2 = field;
        float f2 = f;
        if (field2.zaqa != null) {
            zaa(field2, Float.valueOf(f2));
        } else {
            zaa((Field<?, ?>) field2, field2.zapv, f2);
        }
    }

    public final <O> void zad(Field<ArrayList<Float>, O> field, ArrayList<Float> arrayList) {
        Field<ArrayList<Float>, O> field2 = field;
        ArrayList<Float> arrayList2 = arrayList;
        if (field2.zaqa != null) {
            zaa(field2, arrayList2);
        } else {
            zad(field2, field2.zapv, arrayList2);
        }
    }

    public final <O> void zaa(Field<Double, O> field, double d) {
        Field<Double, O> field2 = field;
        double d2 = d;
        if (field2.zaqa != null) {
            zaa(field2, Double.valueOf(d2));
        } else {
            zaa((Field<?, ?>) field2, field2.zapv, d2);
        }
    }

    public final <O> void zae(Field<ArrayList<Double>, O> field, ArrayList<Double> arrayList) {
        Field<ArrayList<Double>, O> field2 = field;
        ArrayList<Double> arrayList2 = arrayList;
        if (field2.zaqa != null) {
            zaa(field2, arrayList2);
        } else {
            zae(field2, field2.zapv, arrayList2);
        }
    }

    public final <O> void zaa(Field<BigDecimal, O> field, BigDecimal bigDecimal) {
        Field<BigDecimal, O> field2 = field;
        BigDecimal bigDecimal2 = bigDecimal;
        if (field2.zaqa != null) {
            zaa(field2, bigDecimal2);
        } else {
            zaa((Field<?, ?>) field2, field2.zapv, bigDecimal2);
        }
    }

    public final <O> void zaf(Field<ArrayList<BigDecimal>, O> field, ArrayList<BigDecimal> arrayList) {
        Field<ArrayList<BigDecimal>, O> field2 = field;
        ArrayList<BigDecimal> arrayList2 = arrayList;
        if (field2.zaqa != null) {
            zaa(field2, arrayList2);
        } else {
            zaf(field2, field2.zapv, arrayList2);
        }
    }

    public final <O> void zaa(Field<Boolean, O> field, boolean z) {
        Field<Boolean, O> field2 = field;
        boolean z2 = z;
        if (field2.zaqa != null) {
            zaa(field2, Boolean.valueOf(z2));
        } else {
            setBooleanInternal(field2, field2.zapv, z2);
        }
    }

    public final <O> void zag(Field<ArrayList<Boolean>, O> field, ArrayList<Boolean> arrayList) {
        Field<ArrayList<Boolean>, O> field2 = field;
        ArrayList<Boolean> arrayList2 = arrayList;
        if (field2.zaqa != null) {
            zaa(field2, arrayList2);
        } else {
            zag(field2, field2.zapv, arrayList2);
        }
    }

    public final <O> void zaa(Field<String, O> field, String str) {
        Field<String, O> field2 = field;
        String str2 = str;
        if (field2.zaqa != null) {
            zaa(field2, str2);
        } else {
            setStringInternal(field2, field2.zapv, str2);
        }
    }

    public final <O> void zah(Field<ArrayList<String>, O> field, ArrayList<String> arrayList) {
        Field<ArrayList<String>, O> field2 = field;
        ArrayList<String> arrayList2 = arrayList;
        if (field2.zaqa != null) {
            zaa(field2, arrayList2);
        } else {
            setStringsInternal(field2, field2.zapv, arrayList2);
        }
    }

    public final <O> void zaa(Field<byte[], O> field, byte[] bArr) {
        Field<byte[], O> field2 = field;
        byte[] bArr2 = bArr;
        if (field2.zaqa != null) {
            zaa(field2, bArr2);
        } else {
            setDecodedBytesInternal(field2, field2.zapv, bArr2);
        }
    }

    public final <O> void zaa(Field<Map<String, String>, O> field, Map<String, String> map) {
        Field<Map<String, String>, O> field2 = field;
        Map<String, String> map2 = map;
        if (field2.zaqa != null) {
            zaa(field2, map2);
        } else {
            zaa((Field<?, ?>) field2, field2.zapv, map2);
        }
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void setIntegerInternal(Field<?, ?> field, String str, int i) {
        Throwable th;
        Field<?, ?> field2 = field;
        String str2 = str;
        int i2 = i;
        Throwable th2 = th;
        new UnsupportedOperationException("Integer not supported");
        throw th2;
    }

    /* access modifiers changed from: protected */
    public void zaa(Field<?, ?> field, String str, ArrayList<Integer> arrayList) {
        Throwable th;
        Field<?, ?> field2 = field;
        String str2 = str;
        ArrayList<Integer> arrayList2 = arrayList;
        Throwable th2 = th;
        new UnsupportedOperationException("Integer list not supported");
        throw th2;
    }

    /* access modifiers changed from: protected */
    public void zaa(Field<?, ?> field, String str, BigInteger bigInteger) {
        Throwable th;
        Field<?, ?> field2 = field;
        String str2 = str;
        BigInteger bigInteger2 = bigInteger;
        Throwable th2 = th;
        new UnsupportedOperationException("BigInteger not supported");
        throw th2;
    }

    /* access modifiers changed from: protected */
    public void zab(Field<?, ?> field, String str, ArrayList<BigInteger> arrayList) {
        Throwable th;
        Field<?, ?> field2 = field;
        String str2 = str;
        ArrayList<BigInteger> arrayList2 = arrayList;
        Throwable th2 = th;
        new UnsupportedOperationException("BigInteger list not supported");
        throw th2;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void setLongInternal(Field<?, ?> field, String str, long j) {
        Throwable th;
        Field<?, ?> field2 = field;
        String str2 = str;
        long j2 = j;
        Throwable th2 = th;
        new UnsupportedOperationException("Long not supported");
        throw th2;
    }

    /* access modifiers changed from: protected */
    public void zac(Field<?, ?> field, String str, ArrayList<Long> arrayList) {
        Throwable th;
        Field<?, ?> field2 = field;
        String str2 = str;
        ArrayList<Long> arrayList2 = arrayList;
        Throwable th2 = th;
        new UnsupportedOperationException("Long list not supported");
        throw th2;
    }

    /* access modifiers changed from: protected */
    public void zaa(Field<?, ?> field, String str, float f) {
        Throwable th;
        Field<?, ?> field2 = field;
        String str2 = str;
        float f2 = f;
        Throwable th2 = th;
        new UnsupportedOperationException("Float not supported");
        throw th2;
    }

    /* access modifiers changed from: protected */
    public void zad(Field<?, ?> field, String str, ArrayList<Float> arrayList) {
        Throwable th;
        Field<?, ?> field2 = field;
        String str2 = str;
        ArrayList<Float> arrayList2 = arrayList;
        Throwable th2 = th;
        new UnsupportedOperationException("Float list not supported");
        throw th2;
    }

    /* access modifiers changed from: protected */
    public void zaa(Field<?, ?> field, String str, double d) {
        Throwable th;
        Field<?, ?> field2 = field;
        String str2 = str;
        double d2 = d;
        Throwable th2 = th;
        new UnsupportedOperationException("Double not supported");
        throw th2;
    }

    /* access modifiers changed from: protected */
    public void zae(Field<?, ?> field, String str, ArrayList<Double> arrayList) {
        Throwable th;
        Field<?, ?> field2 = field;
        String str2 = str;
        ArrayList<Double> arrayList2 = arrayList;
        Throwable th2 = th;
        new UnsupportedOperationException("Double list not supported");
        throw th2;
    }

    /* access modifiers changed from: protected */
    public void zaa(Field<?, ?> field, String str, BigDecimal bigDecimal) {
        Throwable th;
        Field<?, ?> field2 = field;
        String str2 = str;
        BigDecimal bigDecimal2 = bigDecimal;
        Throwable th2 = th;
        new UnsupportedOperationException("BigDecimal not supported");
        throw th2;
    }

    /* access modifiers changed from: protected */
    public void zaf(Field<?, ?> field, String str, ArrayList<BigDecimal> arrayList) {
        Throwable th;
        Field<?, ?> field2 = field;
        String str2 = str;
        ArrayList<BigDecimal> arrayList2 = arrayList;
        Throwable th2 = th;
        new UnsupportedOperationException("BigDecimal list not supported");
        throw th2;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void setBooleanInternal(Field<?, ?> field, String str, boolean z) {
        Throwable th;
        Field<?, ?> field2 = field;
        String str2 = str;
        boolean z2 = z;
        Throwable th2 = th;
        new UnsupportedOperationException("Boolean not supported");
        throw th2;
    }

    /* access modifiers changed from: protected */
    public void zag(Field<?, ?> field, String str, ArrayList<Boolean> arrayList) {
        Throwable th;
        Field<?, ?> field2 = field;
        String str2 = str;
        ArrayList<Boolean> arrayList2 = arrayList;
        Throwable th2 = th;
        new UnsupportedOperationException("Boolean list not supported");
        throw th2;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void setStringInternal(Field<?, ?> field, String str, String str2) {
        Throwable th;
        Field<?, ?> field2 = field;
        String str3 = str;
        String str4 = str2;
        Throwable th2 = th;
        new UnsupportedOperationException("String not supported");
        throw th2;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void setStringsInternal(Field<?, ?> field, String str, ArrayList<String> arrayList) {
        Throwable th;
        Field<?, ?> field2 = field;
        String str2 = str;
        ArrayList<String> arrayList2 = arrayList;
        Throwable th2 = th;
        new UnsupportedOperationException("String list not supported");
        throw th2;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void setDecodedBytesInternal(Field<?, ?> field, String str, byte[] bArr) {
        Throwable th;
        Field<?, ?> field2 = field;
        String str2 = str;
        byte[] bArr2 = bArr;
        Throwable th2 = th;
        new UnsupportedOperationException("byte[] not supported");
        throw th2;
    }

    /* access modifiers changed from: protected */
    public void zaa(Field<?, ?> field, String str, Map<String, String> map) {
        Throwable th;
        Field<?, ?> field2 = field;
        String str2 = str;
        Map<String, String> map2 = map;
        Throwable th2 = th;
        new UnsupportedOperationException("String map not supported");
        throw th2;
    }

    private static <O> boolean zaa(String str, O o) {
        StringBuilder sb;
        String str2 = str;
        if (o != null) {
            return true;
        }
        if (Log.isLoggable("FastJsonResponse", 6)) {
            new StringBuilder(58 + String.valueOf(str2).length());
            int e = Log.e("FastJsonResponse", sb.append("Output field (").append(str2).append(") has a null value, but expected a primitive").toString());
        }
        return false;
    }

    @KeepForSdk
    public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String str, T t) {
        Throwable th;
        Field<?, ?> field2 = field;
        String str2 = str;
        T t2 = t;
        Throwable th2 = th;
        new UnsupportedOperationException("Concrete type not supported");
        throw th2;
    }

    @KeepForSdk
    public <T extends FastJsonResponse> void addConcreteTypeArrayInternal(Field<?, ?> field, String str, ArrayList<T> arrayList) {
        Throwable th;
        Field<?, ?> field2 = field;
        String str2 = str;
        ArrayList<T> arrayList2 = arrayList;
        Throwable th2 = th;
        new UnsupportedOperationException("Concrete type array not supported");
        throw th2;
    }

    @KeepForSdk
    public String toString() {
        StringBuilder sb;
        Map<String, Field<?, ?>> fieldMappings = getFieldMappings();
        new StringBuilder(100);
        StringBuilder sb2 = sb;
        for (String next : fieldMappings.keySet()) {
            Field field = fieldMappings.get(next);
            if (isFieldSet(field)) {
                Object zab = zab(field, getFieldValue(field));
                if (sb2.length() == 0) {
                    StringBuilder append = sb2.append("{");
                } else {
                    StringBuilder append2 = sb2.append(",");
                }
                StringBuilder append3 = sb2.append("\"").append(next).append("\":");
                if (zab != null) {
                    switch (field.zapt) {
                        case 8:
                            StringBuilder append4 = sb2.append("\"").append(Base64Utils.encode((byte[]) zab)).append("\"");
                            break;
                        case 9:
                            StringBuilder append5 = sb2.append("\"").append(Base64Utils.encodeUrlSafe((byte[]) zab)).append("\"");
                            break;
                        case 10:
                            MapUtils.writeStringMapToJson(sb2, (HashMap) zab);
                            break;
                        default:
                            if (!field.zaps) {
                                zaa(sb2, field, zab);
                                break;
                            } else {
                                ArrayList arrayList = (ArrayList) zab;
                                Field field2 = field;
                                StringBuilder sb3 = sb2;
                                StringBuilder sb4 = sb3;
                                StringBuilder append6 = sb3.append("[");
                                int size = arrayList.size();
                                for (int i = 0; i < size; i++) {
                                    if (i > 0) {
                                        StringBuilder append7 = sb4.append(",");
                                    }
                                    Object obj = arrayList.get(i);
                                    Object obj2 = obj;
                                    if (obj != null) {
                                        zaa(sb4, field2, obj2);
                                    }
                                }
                                StringBuilder append8 = sb4.append("]");
                                break;
                            }
                    }
                } else {
                    StringBuilder append9 = sb2.append("null");
                }
            }
        }
        if (sb2.length() > 0) {
            StringBuilder append10 = sb2.append("}");
        } else {
            StringBuilder append11 = sb2.append("{}");
        }
        return sb2.toString();
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public Object getFieldValue(Field field) {
        Throwable th;
        StringBuilder sb;
        Field field2 = field;
        String str = field2.zapv;
        if (field2.zapx == null) {
            return getValueObject(field2.zapv);
        }
        Preconditions.checkState(getValueObject(field2.zapv) == null, "Concrete field shouldn't be value object: %s", field2.zapv);
        boolean z = field2.zapu;
        try {
            char upperCase = Character.toUpperCase(str.charAt(0));
            String substring = str.substring(1);
            new StringBuilder(4 + String.valueOf(substring).length());
            return getClass().getMethod(sb.append("get").append(upperCase).append(substring).toString(), new Class[0]).invoke(this, new Object[0]);
        } catch (Exception e) {
            Exception exc = e;
            Throwable th2 = th;
            new RuntimeException(exc);
            throw th2;
        }
    }

    private static void zaa(StringBuilder sb, Field field, Object obj) {
        StringBuilder sb2 = sb;
        Field field2 = field;
        Object obj2 = obj;
        if (field2.zapr == 11) {
            StringBuilder append = sb2.append(((FastJsonResponse) field2.zapx.cast(obj2)).toString());
        } else if (field2.zapr == 7) {
            StringBuilder append2 = sb2.append("\"");
            StringBuilder append3 = sb2.append(JsonUtils.escapeString((String) obj2));
            StringBuilder append4 = sb2.append("\"");
        } else {
            StringBuilder append5 = sb2.append(obj2);
        }
    }
}
