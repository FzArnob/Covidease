package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@SafeParcelable.Class(creator = "WakeLockEventCreator")
public final class WakeLockEvent extends StatsEvent {
    public static final Parcelable.Creator<WakeLockEvent> CREATOR;
    private long durationMillis;
    @SafeParcelable.VersionField(mo25283id = 1)
    private final int versionCode;
    @SafeParcelable.Field(getter = "getTimeMillis", mo25277id = 2)
    private final long zzfo;
    @SafeParcelable.Field(getter = "getEventType", mo25277id = 11)
    private int zzfp;
    @SafeParcelable.Field(getter = "getWakeLockName", mo25277id = 4)
    private final String zzfq;
    @SafeParcelable.Field(getter = "getSecondaryWakeLockName", mo25277id = 10)
    private final String zzfr;
    @SafeParcelable.Field(getter = "getCodePackage", mo25277id = 17)
    private final String zzfs;
    @SafeParcelable.Field(getter = "getWakeLockType", mo25277id = 5)
    private final int zzft;
    @SafeParcelable.Field(getter = "getCallingPackages", mo25277id = 6)
    private final List<String> zzfu;
    @SafeParcelable.Field(getter = "getEventKey", mo25277id = 12)
    private final String zzfv;
    @SafeParcelable.Field(getter = "getElapsedRealtime", mo25277id = 8)
    private final long zzfw;
    @SafeParcelable.Field(getter = "getDeviceState", mo25277id = 14)
    private int zzfx;
    @SafeParcelable.Field(getter = "getHostPackage", mo25277id = 13)
    private final String zzfy;
    @SafeParcelable.Field(getter = "getBeginPowerPercentage", mo25277id = 15)
    private final float zzfz;
    @SafeParcelable.Field(getter = "getTimeout", mo25277id = 16)
    private final long zzga;
    @SafeParcelable.Field(getter = "getAcquiredWithTimeout", mo25277id = 18)
    private final boolean zzgb;

    @SafeParcelable.Constructor
    WakeLockEvent(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) long j, @SafeParcelable.Param(mo25280id = 11) int i2, @SafeParcelable.Param(mo25280id = 4) String str, @SafeParcelable.Param(mo25280id = 5) int i3, @SafeParcelable.Param(mo25280id = 6) List<String> list, @SafeParcelable.Param(mo25280id = 12) String str2, @SafeParcelable.Param(mo25280id = 8) long j2, @SafeParcelable.Param(mo25280id = 14) int i4, @SafeParcelable.Param(mo25280id = 10) String str3, @SafeParcelable.Param(mo25280id = 13) String str4, @SafeParcelable.Param(mo25280id = 15) float f, @SafeParcelable.Param(mo25280id = 16) long j3, @SafeParcelable.Param(mo25280id = 17) String str5, @SafeParcelable.Param(mo25280id = 18) boolean z) {
        this.versionCode = i;
        this.zzfo = j;
        this.zzfp = i2;
        this.zzfq = str;
        this.zzfr = str3;
        this.zzfs = str5;
        this.zzft = i3;
        this.durationMillis = -1;
        this.zzfu = list;
        this.zzfv = str2;
        this.zzfw = j2;
        this.zzfx = i4;
        this.zzfy = str4;
        this.zzfz = f;
        this.zzga = j3;
        this.zzgb = z;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public WakeLockEvent(long j, int i, String str, int i2, List<String> list, String str2, long j2, int i3, String str3, String str4, float f, long j3, String str5, boolean z) {
        this(2, j, i, str, i2, list, str2, j2, i3, str3, str4, f, j3, str5, z);
    }

    public final long getTimeMillis() {
        return this.zzfo;
    }

    public final int getEventType() {
        return this.zzfp;
    }

    public final long zzu() {
        return this.durationMillis;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.versionCode);
        SafeParcelWriter.writeLong(parcel2, 2, getTimeMillis());
        SafeParcelWriter.writeString(parcel2, 4, this.zzfq, false);
        SafeParcelWriter.writeInt(parcel2, 5, this.zzft);
        SafeParcelWriter.writeStringList(parcel2, 6, this.zzfu, false);
        SafeParcelWriter.writeLong(parcel2, 8, this.zzfw);
        SafeParcelWriter.writeString(parcel2, 10, this.zzfr, false);
        SafeParcelWriter.writeInt(parcel2, 11, getEventType());
        SafeParcelWriter.writeString(parcel2, 12, this.zzfv, false);
        SafeParcelWriter.writeString(parcel2, 13, this.zzfy, false);
        SafeParcelWriter.writeInt(parcel2, 14, this.zzfx);
        SafeParcelWriter.writeFloat(parcel2, 15, this.zzfz);
        SafeParcelWriter.writeLong(parcel2, 16, this.zzga);
        SafeParcelWriter.writeString(parcel2, 17, this.zzfs, false);
        SafeParcelWriter.writeBoolean(parcel2, 18, this.zzgb);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    public final String zzv() {
        String join;
        String str;
        String str2;
        String str3;
        StringBuilder sb;
        String str4 = this.zzfq;
        int i = this.zzft;
        if (this.zzfu == null) {
            join = "";
        } else {
            join = TextUtils.join(",", this.zzfu);
        }
        String str5 = join;
        int i2 = this.zzfx;
        if (this.zzfr == null) {
            str = "";
        } else {
            str = this.zzfr;
        }
        String str6 = str;
        if (this.zzfy == null) {
            str2 = "";
        } else {
            str2 = this.zzfy;
        }
        String str7 = str2;
        float f = this.zzfz;
        if (this.zzfs == null) {
            str3 = "";
        } else {
            str3 = this.zzfs;
        }
        String str8 = str3;
        boolean z = this.zzgb;
        new StringBuilder(51 + String.valueOf(str4).length() + String.valueOf(str5).length() + String.valueOf(str6).length() + String.valueOf(str7).length() + String.valueOf(str8).length());
        return sb.append("\t").append(str4).append("\t").append(i).append("\t").append(str5).append("\t").append(i2).append("\t").append(str6).append("\t").append(str7).append("\t").append(f).append("\t").append(str8).append("\t").append(z).toString();
    }

    static {
        Parcelable.Creator<WakeLockEvent> creator;
        new zza();
        CREATOR = creator;
    }
}
