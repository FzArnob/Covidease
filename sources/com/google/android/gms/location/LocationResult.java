package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@SafeParcelable.Class(creator = "LocationResultCreator")
@SafeParcelable.Reserved({1000})
public final class LocationResult extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<LocationResult> CREATOR;
    static final List<Location> zzbb = Collections.emptyList();
    @SafeParcelable.Field(defaultValueUnchecked = "LocationResult.DEFAULT_LOCATIONS", getter = "getLocations", mo25277id = 1)
    private final List<Location> zzbc;

    static {
        Parcelable.Creator<LocationResult> creator;
        new zzac();
        CREATOR = creator;
    }

    @SafeParcelable.Constructor
    LocationResult(@SafeParcelable.Param(mo25280id = 1) List<Location> list) {
        this.zzbc = list;
    }

    public static LocationResult create(List<Location> list) {
        LocationResult locationResult;
        List<Location> list2 = list;
        if (list2 == null) {
            list2 = zzbb;
        }
        new LocationResult(list2);
        return locationResult;
    }

    public static LocationResult extractResult(Intent intent) {
        Intent intent2 = intent;
        if (!hasResult(intent2)) {
            return null;
        }
        return (LocationResult) intent2.getExtras().getParcelable("com.google.android.gms.location.EXTRA_LOCATION_RESULT");
    }

    public static boolean hasResult(Intent intent) {
        Intent intent2 = intent;
        if (intent2 == null) {
            return false;
        }
        return intent2.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_RESULT");
    }

    public final boolean equals(Object obj) {
        Object obj2 = obj;
        if (!(obj2 instanceof LocationResult)) {
            return false;
        }
        LocationResult locationResult = (LocationResult) obj2;
        LocationResult locationResult2 = locationResult;
        if (locationResult.zzbc.size() != this.zzbc.size()) {
            return false;
        }
        Iterator<Location> it = this.zzbc.iterator();
        for (Location time : locationResult2.zzbc) {
            if (it.next().getTime() != time.getTime()) {
                return false;
            }
        }
        return true;
    }

    public final Location getLastLocation() {
        int size = this.zzbc.size();
        int i = size;
        if (size == 0) {
            return null;
        }
        return this.zzbc.get(i - 1);
    }

    @NonNull
    public final List<Location> getLocations() {
        return this.zzbc;
    }

    public final int hashCode() {
        int i = 17;
        for (Location time : this.zzbc) {
            long time2 = time.getTime();
            long j = time2;
            long j2 = time2;
            long j3 = j2;
            i = (i * 31) + ((int) (j ^ (j2 >>> 32)));
        }
        return i;
    }

    public final String toString() {
        StringBuilder sb;
        String valueOf = String.valueOf(this.zzbc);
        new StringBuilder(27 + String.valueOf(valueOf).length());
        return sb.append("LocationResult[locations: ").append(valueOf).append("]").toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeTypedList(parcel2, 1, getLocations(), false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }
}
