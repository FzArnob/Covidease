package com.google.android.gms.common.internal;

import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class GmsLogger {
    private static final int zzef = 15;
    private static final String zzeg = null;
    private final String zzeh;
    private final String zzei;

    public GmsLogger(String str, String str2) {
        String str3 = str;
        String str4 = str2;
        Object checkNotNull = Preconditions.checkNotNull(str3, "log tag cannot be null");
        boolean z = str3.length() <= 23;
        Object[] objArr = new Object[2];
        objArr[0] = str3;
        Object[] objArr2 = objArr;
        objArr2[1] = 23;
        Preconditions.checkArgument(z, "tag \"%s\" is longer than the %d character maximum", objArr2);
        this.zzeh = str3;
        if (str4 == null || str4.length() <= 0) {
            this.zzei = null;
        } else {
            this.zzei = str4;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GmsLogger(String str) {
        this(str, (String) null);
    }

    @KeepForSdk
    public final boolean canLog(int i) {
        return Log.isLoggable(this.zzeh, i);
    }

    @KeepForSdk
    public final boolean canLogPii() {
        return false;
    }

    @KeepForSdk
    /* renamed from: d */
    public final void mo25212d(String str, String str2) {
        String str3 = str;
        String str4 = str2;
        if (canLog(3)) {
            int d = Log.d(str3, zzh(str4));
        }
    }

    @KeepForSdk
    /* renamed from: d */
    public final void mo25213d(String str, String str2, Throwable th) {
        String str3 = str;
        String str4 = str2;
        Throwable th2 = th;
        if (canLog(3)) {
            int d = Log.d(str3, zzh(str4), th2);
        }
    }

    @KeepForSdk
    /* renamed from: v */
    public final void mo25221v(String str, String str2) {
        String str3 = str;
        String str4 = str2;
        if (canLog(2)) {
            int v = Log.v(str3, zzh(str4));
        }
    }

    @KeepForSdk
    /* renamed from: v */
    public final void mo25222v(String str, String str2, Throwable th) {
        String str3 = str;
        String str4 = str2;
        Throwable th2 = th;
        if (canLog(2)) {
            int v = Log.v(str3, zzh(str4), th2);
        }
    }

    @KeepForSdk
    /* renamed from: i */
    public final void mo25217i(String str, String str2) {
        String str3 = str;
        String str4 = str2;
        if (canLog(4)) {
            int i = Log.i(str3, zzh(str4));
        }
    }

    @KeepForSdk
    /* renamed from: i */
    public final void mo25218i(String str, String str2, Throwable th) {
        String str3 = str;
        String str4 = str2;
        Throwable th2 = th;
        if (canLog(4)) {
            int i = Log.i(str3, zzh(str4), th2);
        }
    }

    @KeepForSdk
    /* renamed from: w */
    public final void mo25223w(String str, String str2) {
        String str3 = str;
        String str4 = str2;
        if (canLog(5)) {
            int w = Log.w(str3, zzh(str4));
        }
    }

    @KeepForSdk
    /* renamed from: w */
    public final void mo25224w(String str, String str2, Throwable th) {
        String str3 = str;
        String str4 = str2;
        Throwable th2 = th;
        if (canLog(5)) {
            int w = Log.w(str3, zzh(str4), th2);
        }
    }

    @KeepForSdk
    public final void wfmt(String str, String str2, Object... objArr) {
        String str3 = str;
        String str4 = str2;
        Object[] objArr2 = objArr;
        if (canLog(5)) {
            int w = Log.w(this.zzeh, zza(str4, objArr2));
        }
    }

    @KeepForSdk
    /* renamed from: e */
    public final void mo25214e(String str, String str2) {
        String str3 = str;
        String str4 = str2;
        if (canLog(6)) {
            int e = Log.e(str3, zzh(str4));
        }
    }

    @KeepForSdk
    /* renamed from: e */
    public final void mo25215e(String str, String str2, Throwable th) {
        String str3 = str;
        String str4 = str2;
        Throwable th2 = th;
        if (canLog(6)) {
            int e = Log.e(str3, zzh(str4), th2);
        }
    }

    @KeepForSdk
    public final void efmt(String str, String str2, Object... objArr) {
        String str3 = str;
        String str4 = str2;
        Object[] objArr2 = objArr;
        if (canLog(6)) {
            int e = Log.e(str3, zza(str4, objArr2));
        }
    }

    @KeepForSdk
    public final void wtf(String str, String str2, Throwable th) {
        String str3 = str;
        String str4 = str2;
        Throwable th2 = th;
        if (canLog(7)) {
            int e = Log.e(str3, zzh(str4), th2);
            int wtf = Log.wtf(str3, zzh(str4), th2);
        }
    }

    @KeepForSdk
    public final void pii(String str, String str2) {
        String str3;
        String str4;
        String str5 = str;
        String str6 = str2;
        if (canLogPii()) {
            String valueOf = String.valueOf(str5);
            String valueOf2 = String.valueOf(" PII_LOG");
            String str7 = valueOf2;
            if (valueOf2.length() != 0) {
                str4 = valueOf.concat(str7);
            } else {
                String str8 = valueOf;
                str4 = str3;
                new String(str8);
            }
            int i = Log.i(str4, zzh(str6));
        }
    }

    @KeepForSdk
    public final void pii(String str, String str2, Throwable th) {
        String str3;
        String str4;
        String str5 = str;
        String str6 = str2;
        Throwable th2 = th;
        if (canLogPii()) {
            String valueOf = String.valueOf(str5);
            String valueOf2 = String.valueOf(" PII_LOG");
            String str7 = valueOf2;
            if (valueOf2.length() != 0) {
                str4 = valueOf.concat(str7);
            } else {
                String str8 = valueOf;
                str4 = str3;
                new String(str8);
            }
            int i = Log.i(str4, zzh(str6), th2);
        }
    }

    private final String zzh(String str) {
        String str2 = str;
        if (this.zzei == null) {
            return str2;
        }
        return this.zzei.concat(str2);
    }

    private final String zza(String str, Object... objArr) {
        String format = String.format(str, objArr);
        if (this.zzei == null) {
            return format;
        }
        return this.zzei.concat(format);
    }
}
