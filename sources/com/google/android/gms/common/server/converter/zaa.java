package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;

@SafeParcelable.Class(creator = "ConverterWrapperCreator")
public final class zaa extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zaa> CREATOR;
    @SafeParcelable.VersionField(mo25283id = 1)
    private final int zalf;
    @SafeParcelable.Field(getter = "getStringToIntConverter", mo25277id = 2)
    private final StringToIntConverter zapl;

    @SafeParcelable.Constructor
    zaa(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) StringToIntConverter stringToIntConverter) {
        this.zalf = i;
        this.zapl = stringToIntConverter;
    }

    private zaa(StringToIntConverter stringToIntConverter) {
        this.zalf = 1;
        this.zapl = stringToIntConverter;
    }

    public static zaa zaa(FastJsonResponse.FieldConverter<?, ?> fieldConverter) {
        Throwable th;
        zaa zaa;
        FastJsonResponse.FieldConverter<?, ?> fieldConverter2 = fieldConverter;
        if (fieldConverter2 instanceof StringToIntConverter) {
            new zaa((StringToIntConverter) fieldConverter2);
            return zaa;
        }
        Throwable th2 = th;
        new IllegalArgumentException("Unsupported safe parcelable field converter class.");
        throw th2;
    }

    public final FastJsonResponse.FieldConverter<?, ?> zaci() {
        Throwable th;
        if (this.zapl != null) {
            return this.zapl;
        }
        Throwable th2 = th;
        new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
        throw th2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.zalf);
        SafeParcelWriter.writeParcelable(parcel2, 2, this.zapl, i, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    static {
        Parcelable.Creator<zaa> creator;
        new zab();
        CREATOR = creator;
    }
}
