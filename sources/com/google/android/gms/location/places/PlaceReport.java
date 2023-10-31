package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.p002os.EnvironmentCompat;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;

@SafeParcelable.Class(creator = "PlaceReportCreator")
public class PlaceReport extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<PlaceReport> CREATOR;
    @SafeParcelable.Field(getter = "getTag", mo25277id = 3)
    private final String tag;
    @SafeParcelable.VersionField(mo25283id = 1)
    private final int versionCode;
    @SafeParcelable.Field(getter = "getPlaceId", mo25277id = 2)
    private final String zza;
    @SafeParcelable.Field(getter = "getSource", mo25277id = 4)
    private final String zzb;

    static {
        Parcelable.Creator<PlaceReport> creator;
        new zza();
        CREATOR = creator;
    }

    @SafeParcelable.Constructor
    PlaceReport(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) String str, @SafeParcelable.Param(mo25280id = 3) String str2, @SafeParcelable.Param(mo25280id = 4) String str3) {
        this.versionCode = i;
        this.zza = str;
        this.tag = str2;
        this.zzb = str3;
    }

    @VisibleForTesting
    public static PlaceReport create(String str, String str2) {
        boolean z;
        PlaceReport placeReport;
        String str3 = EnvironmentCompat.MEDIA_UNKNOWN;
        String str4 = str2;
        String str5 = str;
        String str6 = str5;
        Object checkNotNull = Preconditions.checkNotNull(str5);
        String checkNotEmpty = Preconditions.checkNotEmpty(str4);
        String checkNotEmpty2 = Preconditions.checkNotEmpty(str3);
        String str7 = str3;
        boolean z2 = true;
        switch (str7.hashCode()) {
            case -1436706272:
                if (str7.equals("inferredGeofencing")) {
                    z2 = true;
                    break;
                }
                break;
            case -1194968642:
                if (str7.equals("userReported")) {
                    z2 = true;
                    break;
                }
                break;
            case -284840886:
                if (str7.equals(EnvironmentCompat.MEDIA_UNKNOWN)) {
                    z2 = false;
                    break;
                }
                break;
            case -262743844:
                if (str7.equals("inferredReverseGeocoding")) {
                    z2 = true;
                    break;
                }
                break;
            case 1164924125:
                if (str7.equals("inferredSnappedToRoad")) {
                    z2 = true;
                    break;
                }
                break;
            case 1287171955:
                if (str7.equals("inferredRadioSignals")) {
                    z2 = true;
                    break;
                }
                break;
        }
        switch (z2) {
            case false:
            case true:
            case true:
            case true:
            case true:
            case true:
                z = true;
                break;
            default:
                z = false;
                break;
        }
        Preconditions.checkArgument(z, "Invalid source");
        new PlaceReport(1, str6, str4, str3);
        return placeReport;
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        if (!(obj2 instanceof PlaceReport)) {
            return false;
        }
        PlaceReport placeReport = (PlaceReport) obj2;
        return Objects.equal(this.zza, placeReport.zza) && Objects.equal(this.tag, placeReport.tag) && Objects.equal(this.zzb, placeReport.zzb);
    }

    public String getPlaceId() {
        return this.zza;
    }

    public String getTag() {
        return this.tag;
    }

    public int hashCode() {
        Object[] objArr = new Object[3];
        objArr[0] = this.zza;
        Object[] objArr2 = objArr;
        objArr2[1] = this.tag;
        Object[] objArr3 = objArr2;
        objArr3[2] = this.zzb;
        return Objects.hashCode(objArr3);
    }

    public String toString() {
        Objects.ToStringHelper stringHelper = Objects.toStringHelper(this);
        Objects.ToStringHelper toStringHelper = stringHelper;
        Objects.ToStringHelper add = stringHelper.add("placeId", this.zza);
        Objects.ToStringHelper add2 = toStringHelper.add("tag", this.tag);
        if (!EnvironmentCompat.MEDIA_UNKNOWN.equals(this.zzb)) {
            Objects.ToStringHelper add3 = toStringHelper.add("source", this.zzb);
        }
        return toStringHelper.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel2, 2, getPlaceId(), false);
        SafeParcelWriter.writeString(parcel2, 3, getTag(), false);
        SafeParcelWriter.writeString(parcel2, 4, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }
}
