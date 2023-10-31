package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "GoogleSignInOptionsExtensionCreator")
public class GoogleSignInOptionsExtensionParcelable extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GoogleSignInOptionsExtensionParcelable> CREATOR;
    @SafeParcelable.Field(getter = "getBundle", mo25277id = 3)
    private Bundle mBundle;
    @SafeParcelable.Field(getter = "getType", mo25277id = 2)
    private int mType;
    @SafeParcelable.VersionField(mo25283id = 1)
    private final int versionCode;

    @SafeParcelable.Constructor
    GoogleSignInOptionsExtensionParcelable(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) int i2, @SafeParcelable.Param(mo25280id = 3) Bundle bundle) {
        this.versionCode = i;
        this.mType = i2;
        this.mBundle = bundle;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GoogleSignInOptionsExtensionParcelable(com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = 1
            r4 = r1
            int r4 = r4.getExtensionType()
            r5 = r1
            android.os.Bundle r5 = r5.toBundle()
            r2.<init>(r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable.<init>(com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension):void");
    }

    @KeepForSdk
    public int getType() {
        return this.mType;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.versionCode);
        SafeParcelWriter.writeInt(parcel2, 2, getType());
        SafeParcelWriter.writeBundle(parcel2, 3, this.mBundle, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    static {
        Parcelable.Creator<GoogleSignInOptionsExtensionParcelable> creator;
        new zaa();
        CREATOR = creator;
    }
}
