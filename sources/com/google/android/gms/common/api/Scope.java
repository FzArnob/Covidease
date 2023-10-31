package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
@SafeParcelable.Class(creator = "ScopeCreator")
public final class Scope extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<Scope> CREATOR;
    @SafeParcelable.Field(getter = "getScopeUri", mo25277id = 2)
    private final String zzaq;
    @SafeParcelable.VersionField(mo25283id = 1)
    private final int zzg;

    @SafeParcelable.Constructor
    Scope(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) String str) {
        String str2 = str;
        String checkNotEmpty = Preconditions.checkNotEmpty(str2, "scopeUri must not be null or empty");
        this.zzg = i;
        this.zzaq = str2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Scope(String str) {
        this(1, str);
    }

    @KeepForSdk
    public final String getScopeUri() {
        return this.zzaq;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            if (r2 != r3) goto L_0x0009
            r2 = 1
            r0 = r2
        L_0x0008:
            return r0
        L_0x0009:
            r2 = r1
            boolean r2 = r2 instanceof com.google.android.gms.common.api.Scope
            if (r2 != 0) goto L_0x0011
            r2 = 0
            r0 = r2
            goto L_0x0008
        L_0x0011:
            r2 = r0
            java.lang.String r2 = r2.zzaq
            r3 = r1
            com.google.android.gms.common.api.Scope r3 = (com.google.android.gms.common.api.Scope) r3
            java.lang.String r3 = r3.zzaq
            boolean r2 = r2.equals(r3)
            r0 = r2
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.Scope.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        return this.zzaq.hashCode();
    }

    public final String toString() {
        return this.zzaq;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.zzg);
        SafeParcelWriter.writeString(parcel2, 2, getScopeUri(), false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    static {
        Parcelable.Creator<Scope> creator;
        new zza();
        CREATOR = creator;
    }
}
