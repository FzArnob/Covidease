package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.location.zzj;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class(creator = "DeviceOrientationRequestInternalCreator")
public final class zzm extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzm> CREATOR;
    @VisibleForTesting
    static final List<ClientIdentity> zzcd = Collections.emptyList();
    static final zzj zzce;
    @SafeParcelable.Field(defaultValueUnchecked = "null", mo25277id = 3)
    @Nullable
    private String tag;
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequestInternal.DEFAULT_DEVICE_ORIENTATION_REQUEST", mo25277id = 1)
    private zzj zzcf;
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequestInternal.DEFAULT_CLIENTS", mo25277id = 2)
    private List<ClientIdentity> zzm;

    static {
        zzj zzj;
        Parcelable.Creator<zzm> creator;
        new zzj();
        zzce = zzj;
        new zzn();
        CREATOR = creator;
    }

    @SafeParcelable.Constructor
    zzm(@SafeParcelable.Param(mo25280id = 1) zzj zzj, @SafeParcelable.Param(mo25280id = 2) List<ClientIdentity> list, @SafeParcelable.Param(mo25280id = 3) String str) {
        this.zzcf = zzj;
        this.zzm = list;
        this.tag = str;
    }

    public final boolean equals(Object obj) {
        Object obj2 = obj;
        if (!(obj2 instanceof zzm)) {
            return false;
        }
        zzm zzm2 = (zzm) obj2;
        return Objects.equal(this.zzcf, zzm2.zzcf) && Objects.equal(this.zzm, zzm2.zzm) && Objects.equal(this.tag, zzm2.tag);
    }

    public final int hashCode() {
        return this.zzcf.hashCode();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeParcelable(parcel2, 1, this.zzcf, i, false);
        SafeParcelWriter.writeTypedList(parcel2, 2, this.zzm, false);
        SafeParcelWriter.writeString(parcel2, 3, this.tag, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }
}
