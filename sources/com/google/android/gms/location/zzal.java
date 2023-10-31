package com.google.android.gms.location;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class(creator = "RemoveGeofencingRequestCreator")
@SafeParcelable.Reserved({1000})
public final class zzal extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzal> CREATOR;
    @SafeParcelable.Field(defaultValue = "", getter = "getTag", mo25277id = 3)
    private final String tag;
    @SafeParcelable.Field(getter = "getGeofenceIds", mo25277id = 1)
    private final List<String> zzbu;
    @SafeParcelable.Field(getter = "getPendingIntent", mo25277id = 2)
    private final PendingIntent zzbv;

    static {
        Parcelable.Creator<zzal> creator;
        new zzam();
        CREATOR = creator;
    }

    @SafeParcelable.Constructor
    zzal(@SafeParcelable.Param(mo25280id = 1) @Nullable List<String> list, @SafeParcelable.Param(mo25280id = 2) @Nullable PendingIntent pendingIntent, @SafeParcelable.Param(mo25280id = 3) String str) {
        List<String> list2 = list;
        PendingIntent pendingIntent2 = pendingIntent;
        String str2 = str;
        this.zzbu = list2 == null ? Collections.emptyList() : Collections.unmodifiableList(list2);
        this.zzbv = pendingIntent2;
        this.tag = str2;
    }

    public static zzal zza(PendingIntent pendingIntent) {
        zzal zzal;
        PendingIntent pendingIntent2 = pendingIntent;
        Object checkNotNull = Preconditions.checkNotNull(pendingIntent2, "PendingIntent can not be null.");
        new zzal((List<String>) null, pendingIntent2, "");
        return zzal;
    }

    public static zzal zza(List<String> list) {
        zzal zzal;
        List<String> list2 = list;
        Object checkNotNull = Preconditions.checkNotNull(list2, "geofence can't be null.");
        Preconditions.checkArgument(!list2.isEmpty(), "Geofences must contains at least one id.");
        new zzal(list2, (PendingIntent) null, "");
        return zzal;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeStringList(parcel2, 1, this.zzbu, false);
        SafeParcelWriter.writeParcelable(parcel2, 2, this.zzbv, i, false);
        SafeParcelWriter.writeString(parcel2, 3, this.tag, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }
}
