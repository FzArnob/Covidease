package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "SignInButtonConfigCreator")
public class SignInButtonConfig extends AbstractSafeParcelable {
    public static final Parcelable.Creator<SignInButtonConfig> CREATOR;
    @SafeParcelable.VersionField(mo25283id = 1)
    private final int zalf;
    @SafeParcelable.Field(getter = "getScopes", mo25277id = 4)
    @Deprecated
    private final Scope[] zany;
    @SafeParcelable.Field(getter = "getButtonSize", mo25277id = 2)
    private final int zapd;
    @SafeParcelable.Field(getter = "getColorScheme", mo25277id = 3)
    private final int zape;

    @SafeParcelable.Constructor
    SignInButtonConfig(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) int i2, @SafeParcelable.Param(mo25280id = 3) int i3, @SafeParcelable.Param(mo25280id = 4) Scope[] scopeArr) {
        this.zalf = i;
        this.zapd = i2;
        this.zape = i3;
        this.zany = scopeArr;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SignInButtonConfig(int i, int i2, Scope[] scopeArr) {
        this(1, i, i2, (Scope[]) null);
        Scope[] scopeArr2 = scopeArr;
    }

    public int getButtonSize() {
        return this.zapd;
    }

    public int getColorScheme() {
        return this.zape;
    }

    @Deprecated
    public Scope[] getScopes() {
        return this.zany;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.zalf);
        SafeParcelWriter.writeInt(parcel2, 2, getButtonSize());
        SafeParcelWriter.writeInt(parcel2, 3, getColorScheme());
        SafeParcelWriter.writeTypedArray(parcel2, 4, getScopes(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    static {
        Parcelable.Creator<SignInButtonConfig> creator;
        new zao();
        CREATOR = creator;
    }
}
