package com.google.android.gms.common.server;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
@SafeParcelable.Class(creator = "FavaDiagnosticsEntityCreator")
public class FavaDiagnosticsEntity extends AbstractSafeParcelable implements ReflectedParcelable {
    @KeepForSdk
    public static final Parcelable.Creator<FavaDiagnosticsEntity> CREATOR;
    @SafeParcelable.VersionField(mo25283id = 1)
    private final int zalf;
    @SafeParcelable.Field(mo25277id = 2)
    private final String zapj;
    @SafeParcelable.Field(mo25277id = 3)
    private final int zapk;

    @SafeParcelable.Constructor
    public FavaDiagnosticsEntity(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) String str, @SafeParcelable.Param(mo25280id = 3) int i2) {
        this.zalf = i;
        this.zapj = str;
        this.zapk = i2;
    }

    @KeepForSdk
    public FavaDiagnosticsEntity(String str, int i) {
        this.zalf = 1;
        this.zapj = str;
        this.zapk = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.zalf);
        SafeParcelWriter.writeString(parcel2, 2, this.zapj, false);
        SafeParcelWriter.writeInt(parcel2, 3, this.zapk);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    static {
        Parcelable.Creator<FavaDiagnosticsEntity> creator;
        new zaa();
        CREATOR = creator;
    }
}
