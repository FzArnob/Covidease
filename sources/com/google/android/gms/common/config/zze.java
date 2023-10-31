package com.google.android.gms.common.config;

import com.google.android.gms.common.config.GservicesValue;

final class zze extends GservicesValue<String> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zze(String str, String str2) {
        super(str, str2);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object zzd(String str) {
        String str2 = str;
        GservicesValue.zza zza = null;
        return zza.getString(this.mKey, (String) this.zzbq);
    }
}
