package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
@SafeParcelable.Class(creator = "ClientIdentityCreator")
@SafeParcelable.Reserved({1000})
public class ClientIdentity extends AbstractSafeParcelable {
    @KeepForSdk
    public static final Parcelable.Creator<ClientIdentity> CREATOR;
    @Nullable
    @SafeParcelable.Field(defaultValueUnchecked = "null", mo25277id = 2)
    private final String packageName;
    @SafeParcelable.Field(defaultValueUnchecked = "0", mo25277id = 1)
    private final int uid;

    @SafeParcelable.Constructor
    public ClientIdentity(@SafeParcelable.Param(mo25280id = 1) int i, @Nullable @SafeParcelable.Param(mo25280id = 2) String str) {
        this.uid = i;
        this.packageName = str;
    }

    public int hashCode() {
        return this.uid;
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r3 = r1
            r4 = r0
            if (r3 != r4) goto L_0x0009
            r3 = 1
            r0 = r3
        L_0x0008:
            return r0
        L_0x0009:
            r3 = r1
            if (r3 == 0) goto L_0x0011
            r3 = r1
            boolean r3 = r3 instanceof com.google.android.gms.common.internal.ClientIdentity
            if (r3 != 0) goto L_0x0014
        L_0x0011:
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x0014:
            r3 = r1
            com.google.android.gms.common.internal.ClientIdentity r3 = (com.google.android.gms.common.internal.ClientIdentity) r3
            r5 = r3
            r3 = r5
            r4 = r5
            r2 = r4
            int r3 = r3.uid
            r4 = r0
            int r4 = r4.uid
            if (r3 != r4) goto L_0x0031
            r3 = r2
            java.lang.String r3 = r3.packageName
            r4 = r0
            java.lang.String r4 = r4.packageName
            boolean r3 = com.google.android.gms.common.internal.Objects.equal(r3, r4)
            if (r3 == 0) goto L_0x0031
            r3 = 1
            r0 = r3
            goto L_0x0008
        L_0x0031:
            r3 = 0
            r0 = r3
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.ClientIdentity.equals(java.lang.Object):boolean");
    }

    public String toString() {
        StringBuilder sb;
        int i = this.uid;
        String str = this.packageName;
        new StringBuilder(12 + String.valueOf(str).length());
        return sb.append(i).append(":").append(str).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.uid);
        SafeParcelWriter.writeString(parcel2, 2, this.packageName, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    static {
        Parcelable.Creator<ClientIdentity> creator;
        new zab();
        CREATOR = creator;
    }
}
