package com.google.android.gms.common.internal.safeparcel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;

@KeepForSdk
@VisibleForTesting
public final class SafeParcelableSerializer {
    public SafeParcelableSerializer() {
    }

    @KeepForSdk
    public static <T extends SafeParcelable> byte[] serializeToBytes(T t) {
        Parcel obtain = Parcel.obtain();
        t.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }

    @KeepForSdk
    public static <T extends SafeParcelable> T deserializeFromBytes(byte[] bArr, Parcelable.Creator<T> creator) {
        byte[] bArr2 = bArr;
        Parcelable.Creator<T> creator2 = creator;
        T checkNotNull = Preconditions.checkNotNull(creator2);
        Parcel obtain = Parcel.obtain();
        Parcel parcel = obtain;
        obtain.unmarshall(bArr2, 0, bArr2.length);
        parcel.setDataPosition(0);
        T t = (SafeParcelable) creator2.createFromParcel(parcel);
        parcel.recycle();
        return t;
    }

    public static <T extends SafeParcelable> void serializeIterableToBundle(Iterable<T> iterable, Bundle bundle, String str) {
        ArrayList arrayList;
        Bundle bundle2 = bundle;
        String str2 = str;
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        for (T serializeToBytes : iterable) {
            boolean add = arrayList2.add(serializeToBytes(serializeToBytes));
        }
        bundle2.putSerializable(str2, arrayList2);
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T extends com.google.android.gms.common.internal.safeparcel.SafeParcelable> java.util.ArrayList<T> deserializeIterableFromBundle(android.os.Bundle r13, java.lang.String r14, android.os.Parcelable.Creator<T> r15) {
        /*
            r0 = r13
            r1 = r14
            r2 = r15
            r9 = r0
            r10 = r1
            java.io.Serializable r9 = r9.getSerializable(r10)
            java.util.ArrayList r9 = (java.util.ArrayList) r9
            r12 = r9
            r9 = r12
            r10 = r12
            r3 = r10
            if (r9 != 0) goto L_0x0014
            r9 = 0
            r0 = r9
        L_0x0013:
            return r0
        L_0x0014:
            java.util.ArrayList r9 = new java.util.ArrayList
            r12 = r9
            r9 = r12
            r10 = r12
            r11 = r3
            int r11 = r11.size()
            r10.<init>(r11)
            r4 = r9
            r9 = r3
            java.util.ArrayList r9 = (java.util.ArrayList) r9
            r12 = r9
            r9 = r12
            r10 = r12
            r6 = r10
            int r9 = r9.size()
            r7 = r9
            r9 = 0
            r8 = r9
        L_0x0030:
            r9 = r8
            r10 = r7
            if (r9 >= r10) goto L_0x004b
            r9 = r6
            r10 = r8
            java.lang.Object r9 = r9.get(r10)
            int r8 = r8 + 1
            byte[] r9 = (byte[]) r9
            r5 = r9
            r9 = r4
            r10 = r5
            r11 = r2
            com.google.android.gms.common.internal.safeparcel.SafeParcelable r10 = deserializeFromBytes(r10, r11)
            boolean r9 = r9.add(r10)
            goto L_0x0030
        L_0x004b:
            r9 = r4
            r0 = r9
            goto L_0x0013
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer.deserializeIterableFromBundle(android.os.Bundle, java.lang.String, android.os.Parcelable$Creator):java.util.ArrayList");
    }

    @KeepForSdk
    public static <T extends SafeParcelable> String serializeToString(T t) {
        return Base64Utils.encodeUrlSafe(serializeToBytes(t));
    }

    @KeepForSdk
    public static <T extends SafeParcelable> T deserializeFromString(String str, Parcelable.Creator<T> creator) {
        return deserializeFromBytes(Base64Utils.decodeUrlSafe(str), creator);
    }

    @KeepForSdk
    public static <T extends SafeParcelable> void serializeToIntentExtra(T t, Intent intent, String str) {
        Intent putExtra = intent.putExtra(str, serializeToBytes(t));
    }

    @KeepForSdk
    public static <T extends SafeParcelable> T deserializeFromIntentExtra(Intent intent, String str, Parcelable.Creator<T> creator) {
        Parcelable.Creator<T> creator2 = creator;
        byte[] byteArrayExtra = intent.getByteArrayExtra(str);
        byte[] bArr = byteArrayExtra;
        if (byteArrayExtra == null) {
            return null;
        }
        return deserializeFromBytes(bArr, creator2);
    }

    @KeepForSdk
    public static <T extends SafeParcelable> void serializeIterableToIntentExtra(Iterable<T> iterable, Intent intent, String str) {
        ArrayList arrayList;
        Intent intent2 = intent;
        String str2 = str;
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        for (T serializeToBytes : iterable) {
            boolean add = arrayList2.add(serializeToBytes(serializeToBytes));
        }
        Intent putExtra = intent2.putExtra(str2, arrayList2);
    }

    /* JADX WARNING: Multi-variable type inference failed */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T extends com.google.android.gms.common.internal.safeparcel.SafeParcelable> java.util.ArrayList<T> deserializeIterableFromIntentExtra(android.content.Intent r13, java.lang.String r14, android.os.Parcelable.Creator<T> r15) {
        /*
            r0 = r13
            r1 = r14
            r2 = r15
            r9 = r0
            r10 = r1
            java.io.Serializable r9 = r9.getSerializableExtra(r10)
            java.util.ArrayList r9 = (java.util.ArrayList) r9
            r12 = r9
            r9 = r12
            r10 = r12
            r3 = r10
            if (r9 != 0) goto L_0x0014
            r9 = 0
            r0 = r9
        L_0x0013:
            return r0
        L_0x0014:
            java.util.ArrayList r9 = new java.util.ArrayList
            r12 = r9
            r9 = r12
            r10 = r12
            r11 = r3
            int r11 = r11.size()
            r10.<init>(r11)
            r4 = r9
            r9 = r3
            java.util.ArrayList r9 = (java.util.ArrayList) r9
            r12 = r9
            r9 = r12
            r10 = r12
            r6 = r10
            int r9 = r9.size()
            r7 = r9
            r9 = 0
            r8 = r9
        L_0x0030:
            r9 = r8
            r10 = r7
            if (r9 >= r10) goto L_0x004b
            r9 = r6
            r10 = r8
            java.lang.Object r9 = r9.get(r10)
            int r8 = r8 + 1
            byte[] r9 = (byte[]) r9
            r5 = r9
            r9 = r4
            r10 = r5
            r11 = r2
            com.google.android.gms.common.internal.safeparcel.SafeParcelable r10 = deserializeFromBytes(r10, r11)
            boolean r9 = r9.add(r10)
            goto L_0x0030
        L_0x004b:
            r9 = r4
            r0 = r9
            goto L_0x0013
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer.deserializeIterableFromIntentExtra(android.content.Intent, java.lang.String, android.os.Parcelable$Creator):java.util.ArrayList");
    }
}
