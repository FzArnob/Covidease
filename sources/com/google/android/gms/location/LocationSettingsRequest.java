package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class(creator = "LocationSettingsRequestCreator")
@SafeParcelable.Reserved({1000})
public final class LocationSettingsRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<LocationSettingsRequest> CREATOR;
    @SafeParcelable.Field(getter = "getLocationRequests", mo25277id = 1)
    private final List<LocationRequest> zzbg;
    @SafeParcelable.Field(defaultValue = "false", getter = "alwaysShow", mo25277id = 2)
    private final boolean zzbh;
    @SafeParcelable.Field(getter = "needBle", mo25277id = 3)
    private final boolean zzbi;
    @SafeParcelable.Field(getter = "getConfiguration", mo25277id = 5)
    private zzae zzbj;

    public static final class Builder {
        private boolean zzbh = false;
        private boolean zzbi = false;
        private zzae zzbj = null;
        private final ArrayList<LocationRequest> zzbk;

        public Builder() {
            ArrayList<LocationRequest> arrayList;
            new ArrayList<>();
            this.zzbk = arrayList;
        }

        public final Builder addAllLocationRequests(Collection<LocationRequest> collection) {
            for (LocationRequest next : collection) {
                LocationRequest locationRequest = next;
                if (next != null) {
                    boolean add = this.zzbk.add(locationRequest);
                }
            }
            return this;
        }

        public final Builder addLocationRequest(@NonNull LocationRequest locationRequest) {
            LocationRequest locationRequest2 = locationRequest;
            if (locationRequest2 != null) {
                boolean add = this.zzbk.add(locationRequest2);
            }
            return this;
        }

        public final LocationSettingsRequest build() {
            LocationSettingsRequest locationSettingsRequest;
            new LocationSettingsRequest(this.zzbk, this.zzbh, this.zzbi, (zzae) null);
            return locationSettingsRequest;
        }

        public final Builder setAlwaysShow(boolean z) {
            this.zzbh = z;
            return this;
        }

        public final Builder setNeedBle(boolean z) {
            this.zzbi = z;
            return this;
        }
    }

    static {
        Parcelable.Creator<LocationSettingsRequest> creator;
        new zzag();
        CREATOR = creator;
    }

    @SafeParcelable.Constructor
    LocationSettingsRequest(@SafeParcelable.Param(mo25280id = 1) List<LocationRequest> list, @SafeParcelable.Param(mo25280id = 2) boolean z, @SafeParcelable.Param(mo25280id = 3) boolean z2, @SafeParcelable.Param(mo25280id = 5) zzae zzae) {
        this.zzbg = list;
        this.zzbh = z;
        this.zzbi = z2;
        this.zzbj = zzae;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeTypedList(parcel2, 1, Collections.unmodifiableList(this.zzbg), false);
        SafeParcelWriter.writeBoolean(parcel2, 2, this.zzbh);
        SafeParcelWriter.writeBoolean(parcel2, 3, this.zzbi);
        SafeParcelWriter.writeParcelable(parcel2, 5, this.zzbj, i, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }
}
