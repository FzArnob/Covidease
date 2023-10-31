package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;

@ShowFirstParty
@SafeParcelable.Class(creator = "FieldMapPairCreator")
public final class zam extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zam> CREATOR;
    @SafeParcelable.VersionField(mo25283id = 1)
    private final int versionCode;
    @SafeParcelable.Field(mo25277id = 2)
    final String zaqz;
    @SafeParcelable.Field(mo25277id = 3)
    final FastJsonResponse.Field<?, ?> zara;

    @SafeParcelable.Constructor
    zam(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) String str, @SafeParcelable.Param(mo25280id = 3) FastJsonResponse.Field<?, ?> field) {
        this.versionCode = i;
        this.zaqz = str;
        this.zara = field;
    }

    zam(String str, FastJsonResponse.Field<?, ?> field) {
        this.versionCode = 1;
        this.zaqz = str;
        this.zara = field;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel2, 2, this.zaqz, false);
        SafeParcelWriter.writeParcelable(parcel2, 3, this.zara, i, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    static {
        Parcelable.Creator<zam> creator;
        new zaj();
        CREATOR = creator;
    }
}
