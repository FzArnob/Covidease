package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import org.shaded.apache.http.cookie.ClientCookie;

@KeepForSdk
@SafeParcelable.Class(creator = "FeatureCreator")
public class Feature extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Feature> CREATOR;
    @SafeParcelable.Field(getter = "getName", mo25277id = 1)
    private final String name;
    @SafeParcelable.Field(getter = "getOldVersion", mo25277id = 2)
    @Deprecated
    private final int zzk;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getVersion", mo25277id = 3)
    private final long zzl;

    @KeepForSdk
    public Feature(String str, long j) {
        this.name = str;
        this.zzl = j;
        this.zzk = -1;
    }

    @SafeParcelable.Constructor
    public Feature(@SafeParcelable.Param(mo25280id = 1) String str, @SafeParcelable.Param(mo25280id = 2) int i, @SafeParcelable.Param(mo25280id = 3) long j) {
        this.name = str;
        this.zzk = i;
        this.zzl = j;
    }

    @KeepForSdk
    public String getName() {
        return this.name;
    }

    @KeepForSdk
    public long getVersion() {
        return this.zzl == -1 ? (long) this.zzk : this.zzl;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeString(parcel2, 1, getName(), false);
        SafeParcelWriter.writeInt(parcel2, 2, this.zzk);
        SafeParcelWriter.writeLong(parcel2, 3, getVersion());
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    public boolean equals(@Nullable Object obj) {
        Object obj2 = obj;
        if (!(obj2 instanceof Feature)) {
            return false;
        }
        Feature feature = (Feature) obj2;
        if (((getName() == null || !getName().equals(feature.getName())) && (getName() != null || feature.getName() != null)) || getVersion() != feature.getVersion()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        Object[] objArr = new Object[2];
        objArr[0] = getName();
        Object[] objArr2 = objArr;
        objArr2[1] = Long.valueOf(getVersion());
        return Objects.hashCode(objArr2);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("name", getName()).add(ClientCookie.VERSION_ATTR, Long.valueOf(getVersion())).toString();
    }

    static {
        Parcelable.Creator<Feature> creator;
        new zzb();
        CREATOR = creator;
    }
}
