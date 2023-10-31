package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class(creator = "LocationRequestInternalCreator")
@SafeParcelable.Reserved({1000, 2, 3, 4})
public final class zzbd extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbd> CREATOR;
    static final List<ClientIdentity> zzcd = Collections.emptyList();
    @SafeParcelable.Field(defaultValueUnchecked = "null", mo25277id = 10)
    @Nullable
    private String moduleId;
    @SafeParcelable.Field(defaultValueUnchecked = "null", mo25277id = 6)
    @Nullable
    private String tag;
    @SafeParcelable.Field(defaultValueUnchecked = "null", mo25277id = 1)
    private LocationRequest zzdg;
    @SafeParcelable.Field(defaultValueUnchecked = "LocationRequestInternal.DEFAULT_HIDE_FROM_APP_OPS", mo25277id = 7)
    private boolean zzdh;
    @SafeParcelable.Field(defaultValueUnchecked = "LocationRequestInternal.DEFAULT_FORCE_COARSE_LOCATION", mo25277id = 8)
    private boolean zzdi;
    @SafeParcelable.Field(defaultValueUnchecked = "LocationRequestInternal.DEFAULT_EXEMPT_FROM_THROTTLE", mo25277id = 9)
    private boolean zzdj;
    private boolean zzdk = true;
    @SafeParcelable.Field(defaultValueUnchecked = "LocationRequestInternal.DEFAULT_CLIENTS", mo25277id = 5)
    private List<ClientIdentity> zzm;

    static {
        Parcelable.Creator<zzbd> creator;
        new zzbe();
        CREATOR = creator;
    }

    @SafeParcelable.Constructor
    zzbd(@SafeParcelable.Param(mo25280id = 1) LocationRequest locationRequest, @SafeParcelable.Param(mo25280id = 5) List<ClientIdentity> list, @SafeParcelable.Param(mo25280id = 6) @Nullable String str, @SafeParcelable.Param(mo25280id = 7) boolean z, @SafeParcelable.Param(mo25280id = 8) boolean z2, @SafeParcelable.Param(mo25280id = 9) boolean z3, @SafeParcelable.Param(mo25280id = 10) String str2) {
        this.zzdg = locationRequest;
        this.zzm = list;
        this.tag = str;
        this.zzdh = z;
        this.zzdi = z2;
        this.zzdj = z3;
        this.moduleId = str2;
    }

    @Deprecated
    public static zzbd zza(LocationRequest locationRequest) {
        zzbd zzbd;
        new zzbd(locationRequest, zzcd, (String) null, false, false, false, (String) null);
        return zzbd;
    }

    public final boolean equals(Object obj) {
        Object obj2 = obj;
        if (!(obj2 instanceof zzbd)) {
            return false;
        }
        zzbd zzbd = (zzbd) obj2;
        return Objects.equal(this.zzdg, zzbd.zzdg) && Objects.equal(this.zzm, zzbd.zzm) && Objects.equal(this.tag, zzbd.tag) && this.zzdh == zzbd.zzdh && this.zzdi == zzbd.zzdi && this.zzdj == zzbd.zzdj && Objects.equal(this.moduleId, zzbd.moduleId);
    }

    public final int hashCode() {
        return this.zzdg.hashCode();
    }

    public final String toString() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder sb2 = sb;
        StringBuilder sb3 = sb2;
        StringBuilder append = sb2.append(this.zzdg);
        if (this.tag != null) {
            StringBuilder append2 = sb3.append(" tag=").append(this.tag);
        }
        if (this.moduleId != null) {
            StringBuilder append3 = sb3.append(" moduleId=").append(this.moduleId);
        }
        StringBuilder append4 = sb3.append(" hideAppOps=").append(this.zzdh);
        StringBuilder append5 = sb3.append(" clients=").append(this.zzm);
        StringBuilder append6 = sb3.append(" forceCoarseLocation=").append(this.zzdi);
        if (this.zzdj) {
            StringBuilder append7 = sb3.append(" exemptFromBackgroundThrottle");
        }
        return sb3.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeParcelable(parcel2, 1, this.zzdg, i, false);
        SafeParcelWriter.writeTypedList(parcel2, 5, this.zzm, false);
        SafeParcelWriter.writeString(parcel2, 6, this.tag, false);
        SafeParcelWriter.writeBoolean(parcel2, 7, this.zzdh);
        SafeParcelWriter.writeBoolean(parcel2, 8, this.zzdi);
        SafeParcelWriter.writeBoolean(parcel2, 9, this.zzdj);
        SafeParcelWriter.writeString(parcel2, 10, this.moduleId, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }
}
