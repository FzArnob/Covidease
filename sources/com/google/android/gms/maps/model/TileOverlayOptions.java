package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.maps.zzaf;
import com.google.android.gms.internal.maps.zzag;

@SafeParcelable.Class(creator = "TileOverlayOptionsCreator")
@SafeParcelable.Reserved({1})
public final class TileOverlayOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<TileOverlayOptions> CREATOR;
    @SafeParcelable.Field(getter = "getZIndex", mo25277id = 4)
    private float zzcs;
    @SafeParcelable.Field(getter = "isVisible", mo25277id = 3)
    private boolean zzct = true;
    @SafeParcelable.Field(getter = "getTransparency", mo25277id = 6)
    private float zzda = 0.0f;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "getTileProviderDelegate", mo25277id = 2, type = "android.os.IBinder")
    public zzaf zzei;
    private TileProvider zzej;
    @SafeParcelable.Field(defaultValue = "true", getter = "getFadeIn", mo25277id = 5)
    private boolean zzek = true;

    public TileOverlayOptions() {
    }

    @SafeParcelable.Constructor
    TileOverlayOptions(@SafeParcelable.Param(mo25280id = 2) IBinder iBinder, @SafeParcelable.Param(mo25280id = 3) boolean z, @SafeParcelable.Param(mo25280id = 4) float f, @SafeParcelable.Param(mo25280id = 5) boolean z2, @SafeParcelable.Param(mo25280id = 6) float f2) {
        TileProvider tileProvider;
        TileProvider tileProvider2;
        boolean z3 = z;
        float f3 = f;
        boolean z4 = z2;
        float f4 = f2;
        this.zzei = zzag.zzk(iBinder);
        if (this.zzei == null) {
            tileProvider2 = null;
        } else {
            tileProvider2 = tileProvider;
            new zzs(this);
        }
        this.zzej = tileProvider2;
        this.zzct = z3;
        this.zzcs = f3;
        this.zzek = z4;
        this.zzda = f4;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeIBinder(parcel2, 2, this.zzei.asBinder(), false);
        SafeParcelWriter.writeBoolean(parcel2, 3, isVisible());
        SafeParcelWriter.writeFloat(parcel2, 4, getZIndex());
        SafeParcelWriter.writeBoolean(parcel2, 5, getFadeIn());
        SafeParcelWriter.writeFloat(parcel2, 6, getTransparency());
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    public final TileOverlayOptions tileProvider(TileProvider tileProvider) {
        zzaf zzaf;
        zzaf zzaf2;
        TileProvider tileProvider2 = tileProvider;
        this.zzej = tileProvider2;
        if (this.zzej == null) {
            zzaf2 = null;
        } else {
            zzaf2 = zzaf;
            new zzt(this, tileProvider2);
        }
        this.zzei = zzaf2;
        return this;
    }

    public final TileOverlayOptions zIndex(float f) {
        this.zzcs = f;
        return this;
    }

    public final TileOverlayOptions visible(boolean z) {
        this.zzct = z;
        return this;
    }

    public final TileOverlayOptions fadeIn(boolean z) {
        this.zzek = z;
        return this;
    }

    public final TileOverlayOptions transparency(float f) {
        float f2 = f;
        Preconditions.checkArgument(f2 >= 0.0f && f2 <= 1.0f, "Transparency must be in the range [0..1]");
        this.zzda = f2;
        return this;
    }

    public final TileProvider getTileProvider() {
        return this.zzej;
    }

    public final float getZIndex() {
        return this.zzcs;
    }

    public final boolean isVisible() {
        return this.zzct;
    }

    public final boolean getFadeIn() {
        return this.zzek;
    }

    public final float getTransparency() {
        return this.zzda;
    }

    static {
        Parcelable.Creator<TileOverlayOptions> creator;
        new zzu();
        CREATOR = creator;
    }
}
