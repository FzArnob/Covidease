package com.google.android.gms.common.config;

import com.google.android.gms.common.config.GservicesValue;

final class zzb extends GservicesValue<Long> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzb(String str, Long l) {
        super(str, l);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object zzd(String str) {
        String str2 = str;
        GservicesValue.zza zza = null;
        return zza.getLong(this.mKey, (Long) this.zzbq);
    }
}
