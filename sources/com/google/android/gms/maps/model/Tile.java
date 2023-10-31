package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "TileCreator")
@SafeParcelable.Reserved({1})
public final class Tile extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Tile> CREATOR;
    @SafeParcelable.Field(mo25277id = 4)
    public final byte[] data;
    @SafeParcelable.Field(mo25277id = 3)
    public final int height;
    @SafeParcelable.Field(mo25277id = 2)
    public final int width;

    @SafeParcelable.Constructor
    public Tile(@SafeParcelable.Param(mo25280id = 2) int i, @SafeParcelable.Param(mo25280id = 3) int i2, @SafeParcelable.Param(mo25280id = 4) byte[] bArr) {
        this.width = i;
        this.height = i2;
        this.data = bArr;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 2, this.width);
        SafeParcelWriter.writeInt(parcel2, 3, this.height);
        SafeParcelWriter.writeByteArray(parcel2, 4, this.data, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    static {
        Parcelable.Creator<Tile> creator;
        new zzr();
        CREATOR = creator;
    }
}
