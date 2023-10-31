package com.google.android.gms.maps.model;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.IOUtils;
import java.io.IOException;

@SafeParcelable.Class(creator = "MapStyleOptionsCreator")
@SafeParcelable.Reserved({1})
public final class MapStyleOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<MapStyleOptions> CREATOR;
    private static final String TAG = MapStyleOptions.class.getSimpleName();
    @SafeParcelable.Field(getter = "getJson", mo25277id = 2)
    private String zzdl;

    @SafeParcelable.Constructor
    public MapStyleOptions(@SafeParcelable.Param(mo25280id = 2) String str) {
        this.zzdl = str;
    }

    public static MapStyleOptions loadRawResourceStyle(Context context, int i) throws Resources.NotFoundException {
        Throwable th;
        StringBuilder sb;
        String str;
        MapStyleOptions mapStyleOptions;
        int i2 = i;
        try {
            new String(IOUtils.readInputStreamFully(context.getResources().openRawResource(i2)), "UTF-8");
            MapStyleOptions mapStyleOptions2 = mapStyleOptions;
            new MapStyleOptions(str);
            return mapStyleOptions2;
        } catch (IOException e) {
            IOException iOException = e;
            Throwable th2 = th;
            String valueOf = String.valueOf(iOException);
            new StringBuilder(37 + String.valueOf(valueOf).length());
            new Resources.NotFoundException(sb.append("Failed to read resource ").append(i2).append(": ").append(valueOf).toString());
            throw th2;
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeString(parcel2, 2, this.zzdl, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    static {
        Parcelable.Creator<MapStyleOptions> creator;
        new zzg();
        CREATOR = creator;
    }
}
