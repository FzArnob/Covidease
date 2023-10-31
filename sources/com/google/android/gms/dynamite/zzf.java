package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

final class zzf implements DynamiteModule.VersionPolicy {
    zzf() {
    }

    public final DynamiteModule.VersionPolicy.zzb zza(Context context, String str, DynamiteModule.VersionPolicy.zza zza) throws DynamiteModule.LoadingException {
        DynamiteModule.VersionPolicy.zzb zzb;
        Context context2 = context;
        String str2 = str;
        DynamiteModule.VersionPolicy.zza zza2 = zza;
        new DynamiteModule.VersionPolicy.zzb();
        DynamiteModule.VersionPolicy.zzb zzb2 = zzb;
        DynamiteModule.VersionPolicy.zzb zzb3 = zzb2;
        zzb2.zzir = zza2.getLocalVersion(context2, str2);
        zzb3.zzis = zza2.zza(context2, str2, true);
        if (zzb3.zzir == 0 && zzb3.zzis == 0) {
            zzb3.zzit = 0;
        } else if (zzb3.zzis >= zzb3.zzir) {
            zzb3.zzit = 1;
        } else {
            zzb3.zzit = -1;
        }
        return zzb3;
    }
}
