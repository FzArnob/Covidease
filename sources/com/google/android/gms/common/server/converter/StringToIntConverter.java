package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import java.util.ArrayList;
import java.util.HashMap;

@KeepForSdk
@SafeParcelable.Class(creator = "StringToIntConverterCreator")
public final class StringToIntConverter extends AbstractSafeParcelable implements FastJsonResponse.FieldConverter<String, Integer> {
    public static final Parcelable.Creator<StringToIntConverter> CREATOR;
    @SafeParcelable.VersionField(mo25283id = 1)
    private final int zalf;
    private final HashMap<String, Integer> zapm;
    private final SparseArray<String> zapn;
    @SafeParcelable.Field(getter = "getSerializedMap", mo25277id = 2)
    private final ArrayList<zaa> zapo;

    @SafeParcelable.Constructor
    StringToIntConverter(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) ArrayList<zaa> arrayList) {
        HashMap<String, Integer> hashMap;
        SparseArray<String> sparseArray;
        this.zalf = i;
        new HashMap<>();
        this.zapm = hashMap;
        new SparseArray<>();
        this.zapn = sparseArray;
        this.zapo = null;
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = arrayList2;
        int size = arrayList2.size();
        int i2 = 0;
        while (i2 < size) {
            i2++;
            zaa zaa2 = (zaa) arrayList3.get(i2);
            StringToIntConverter add = add(zaa2.zapp, zaa2.zapq);
        }
    }

    @SafeParcelable.Class(creator = "StringToIntConverterEntryCreator")
    public static final class zaa extends AbstractSafeParcelable {
        public static final Parcelable.Creator<zaa> CREATOR;
        @SafeParcelable.VersionField(mo25283id = 1)
        private final int versionCode;
        @SafeParcelable.Field(mo25277id = 2)
        final String zapp;
        @SafeParcelable.Field(mo25277id = 3)
        final int zapq;

        @SafeParcelable.Constructor
        zaa(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) String str, @SafeParcelable.Param(mo25280id = 3) int i2) {
            this.versionCode = i;
            this.zapp = str;
            this.zapq = i2;
        }

        zaa(String str, int i) {
            this.versionCode = 1;
            this.zapp = str;
            this.zapq = i;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            int i2 = i;
            Parcel parcel2 = parcel;
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
            SafeParcelWriter.writeInt(parcel2, 1, this.versionCode);
            SafeParcelWriter.writeString(parcel2, 2, this.zapp, false);
            SafeParcelWriter.writeInt(parcel2, 3, this.zapq);
            SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
        }

        static {
            Parcelable.Creator<zaa> creator;
            new zad();
            CREATOR = creator;
        }
    }

    @KeepForSdk
    public StringToIntConverter() {
        HashMap<String, Integer> hashMap;
        SparseArray<String> sparseArray;
        this.zalf = 1;
        new HashMap<>();
        this.zapm = hashMap;
        new SparseArray<>();
        this.zapn = sparseArray;
        this.zapo = null;
    }

    @KeepForSdk
    public final StringToIntConverter add(String str, int i) {
        String str2 = str;
        int i2 = i;
        Integer put = this.zapm.put(str2, Integer.valueOf(i2));
        this.zapn.put(i2, str2);
        return this;
    }

    public final int zacj() {
        return 7;
    }

    public final int zack() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        ArrayList arrayList;
        Object obj;
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.zalf);
        Parcel parcel3 = parcel2;
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        for (String next : this.zapm.keySet()) {
            new zaa(next, this.zapm.get(next).intValue());
            boolean add = arrayList2.add(obj);
        }
        SafeParcelWriter.writeTypedList(parcel3, 2, arrayList2, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    public final /* synthetic */ Object convertBack(Object obj) {
        String str = this.zapn.get(((Integer) obj).intValue());
        String str2 = str;
        if (str != null || !this.zapm.containsKey("gms_unknown")) {
            return str2;
        }
        return "gms_unknown";
    }

    public final /* synthetic */ Object convert(Object obj) {
        Integer num = this.zapm.get((String) obj);
        Integer num2 = num;
        if (num == null) {
            num2 = this.zapm.get("gms_unknown");
        }
        return num2;
    }

    static {
        Parcelable.Creator<StringToIntConverter> creator;
        new zac();
        CREATOR = creator;
    }
}
