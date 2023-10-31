package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.location.zzbh;
import java.util.ArrayList;
import java.util.List;

@SafeParcelable.Class(creator = "GeofencingRequestCreator")
@SafeParcelable.Reserved({1000})
public class GeofencingRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GeofencingRequest> CREATOR;
    public static final int INITIAL_TRIGGER_DWELL = 4;
    public static final int INITIAL_TRIGGER_ENTER = 1;
    public static final int INITIAL_TRIGGER_EXIT = 2;
    @SafeParcelable.Field(defaultValue = "", getter = "getTag", mo25277id = 3)
    private final String tag;
    @SafeParcelable.Field(getter = "getParcelableGeofences", mo25277id = 1)
    private final List<zzbh> zzap;
    @SafeParcelable.Field(getter = "getInitialTrigger", mo25277id = 2)
    private final int zzaq;

    public static final class Builder {
        private String tag = "";
        private final List<zzbh> zzap;
        private int zzaq = 5;

        public Builder() {
            List<zzbh> list;
            new ArrayList();
            this.zzap = list;
        }

        public final Builder addGeofence(Geofence geofence) {
            Geofence geofence2 = geofence;
            Object checkNotNull = Preconditions.checkNotNull(geofence2, "geofence can't be null.");
            Preconditions.checkArgument(geofence2 instanceof zzbh, "Geofence must be created using Geofence.Builder.");
            boolean add = this.zzap.add((zzbh) geofence2);
            return this;
        }

        public final Builder addGeofences(List<Geofence> list) {
            List<Geofence> list2 = list;
            if (list2 == null || list2.isEmpty()) {
                return this;
            }
            for (Geofence next : list2) {
                Geofence geofence = next;
                if (next != null) {
                    Builder addGeofence = addGeofence(geofence);
                }
            }
            return this;
        }

        public final GeofencingRequest build() {
            GeofencingRequest geofencingRequest;
            Preconditions.checkArgument(!this.zzap.isEmpty(), "No geofence has been added to this request.");
            new GeofencingRequest(this.zzap, this.zzaq, this.tag);
            return geofencingRequest;
        }

        public final Builder setInitialTrigger(int i) {
            this.zzaq = i & 7;
            return this;
        }
    }

    static {
        Parcelable.Creator<GeofencingRequest> creator;
        new zzq();
        CREATOR = creator;
    }

    @SafeParcelable.Constructor
    GeofencingRequest(@SafeParcelable.Param(mo25280id = 1) List<zzbh> list, @SafeParcelable.Param(mo25280id = 2) int i, @SafeParcelable.Param(mo25280id = 3) String str) {
        this.zzap = list;
        this.zzaq = i;
        this.tag = str;
    }

    public List<Geofence> getGeofences() {
        List<Geofence> list;
        new ArrayList();
        List<Geofence> list2 = list;
        List<Geofence> list3 = list2;
        boolean addAll = list2.addAll(this.zzap);
        return list3;
    }

    public int getInitialTrigger() {
        return this.zzaq;
    }

    public String toString() {
        StringBuilder sb;
        StringBuilder sb2;
        String str;
        String str2;
        new StringBuilder();
        StringBuilder sb3 = sb;
        StringBuilder sb4 = sb3;
        StringBuilder append = sb3.append("GeofencingRequest[");
        StringBuilder append2 = sb4.append("geofences=");
        StringBuilder append3 = sb4.append(this.zzap);
        int i = this.zzaq;
        new StringBuilder(30);
        StringBuilder append4 = sb4.append(sb2.append(", initialTrigger=").append(i).append(", ").toString());
        StringBuilder sb5 = sb4;
        String valueOf = String.valueOf(this.tag);
        String str3 = valueOf;
        if (valueOf.length() != 0) {
            str2 = "tag=".concat(str3);
        } else {
            str2 = str;
            new String("tag=");
        }
        StringBuilder append5 = sb5.append(str2);
        StringBuilder append6 = sb4.append("]");
        return sb4.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeTypedList(parcel2, 1, this.zzap, false);
        SafeParcelWriter.writeInt(parcel2, 2, getInitialTrigger());
        SafeParcelWriter.writeString(parcel2, 3, this.tag, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }
}
