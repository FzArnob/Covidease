package com.google.android.gms.common;

import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import java.util.concurrent.Callable;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

@CheckReturnValue
class zzm {
    private static final zzm zzac;
    private final Throwable cause;
    final boolean zzad;
    private final String zzae;

    zzm(boolean z, @Nullable String str, @Nullable Throwable th) {
        this.zzad = z;
        this.zzae = str;
        this.cause = th;
    }

    static zzm zze() {
        return zzac;
    }

    static zzm zza(Callable<String> callable) {
        zzm zzm;
        new zzo(callable, (zzn) null);
        return zzm;
    }

    static zzm zzb(@NonNull String str) {
        zzm zzm;
        new zzm(false, str, (Throwable) null);
        return zzm;
    }

    static zzm zza(@NonNull String str, @NonNull Throwable th) {
        zzm zzm;
        new zzm(false, str, th);
        return zzm;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public String getErrorMessage() {
        return this.zzae;
    }

    /* access modifiers changed from: package-private */
    public final void zzf() {
        if (!this.zzad && Log.isLoggable("GoogleCertificatesRslt", 3)) {
            if (this.cause != null) {
                int d = Log.d("GoogleCertificatesRslt", getErrorMessage(), this.cause);
            } else {
                int d2 = Log.d("GoogleCertificatesRslt", getErrorMessage());
            }
        }
    }

    static String zzc(String str, zze zze, boolean z, boolean z2) {
        String str2 = str;
        zze zze2 = zze;
        boolean z3 = z;
        Object[] objArr = new Object[5];
        objArr[0] = z2 ? "debug cert rejected" : "not whitelisted";
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        Object[] objArr3 = objArr2;
        objArr3[2] = Hex.bytesToStringLowercase(AndroidUtilsLight.zzj("SHA-1").digest(zze2.getBytes()));
        Object[] objArr4 = objArr3;
        objArr4[3] = Boolean.valueOf(z3);
        Object[] objArr5 = objArr4;
        objArr5[4] = "12451009.false";
        return String.format("%s: pkg=%s, sha1=%s, atk=%s, ver=%s", objArr5);
    }

    static {
        zzm zzm;
        new zzm(true, (String) null, (Throwable) null);
        zzac = zzm;
    }
}
