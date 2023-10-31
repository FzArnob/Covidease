package com.google.android.gms.common.logging;

import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import java.util.Locale;

@KeepForSdk
public class Logger {
    private final String mTag;
    private final String zzei;
    private final GmsLogger zzew;
    private final int zzex;

    /* JADX WARNING: Illegal instructions before constructor call */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Logger(java.lang.String r15, java.lang.String... r16) {
        /*
            r14 = this;
            r0 = r14
            r1 = r15
            r2 = r16
            r9 = r0
            r10 = r1
            r11 = r2
            r13 = r11
            r11 = r13
            r12 = r13
            r3 = r12
            int r11 = r11.length
            if (r11 != 0) goto L_0x0015
            java.lang.String r11 = ""
        L_0x0011:
            r9.<init>((java.lang.String) r10, (java.lang.String) r11)
            return
        L_0x0015:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r13 = r11
            r11 = r13
            r12 = r13
            r12.<init>()
            r13 = r11
            r11 = r13
            r12 = r13
            r4 = r12
            r12 = 91
            java.lang.StringBuilder r11 = r11.append(r12)
            r11 = r3
            r13 = r11
            r11 = r13
            r12 = r13
            r5 = r12
            int r11 = r11.length
            r6 = r11
            r11 = 0
            r7 = r11
        L_0x0030:
            r11 = r7
            r12 = r6
            if (r11 >= r12) goto L_0x0052
            r11 = r5
            r12 = r7
            r11 = r11[r12]
            r8 = r11
            r11 = r4
            int r11 = r11.length()
            r12 = 1
            if (r11 <= r12) goto L_0x0049
            r11 = r4
            java.lang.String r12 = ","
            java.lang.StringBuilder r11 = r11.append(r12)
        L_0x0049:
            r11 = r4
            r12 = r8
            java.lang.StringBuilder r11 = r11.append(r12)
            int r7 = r7 + 1
            goto L_0x0030
        L_0x0052:
            r11 = r4
            r12 = 93
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = 32
            java.lang.StringBuilder r11 = r11.append(r12)
            r11 = r4
            java.lang.String r11 = r11.toString()
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.logging.Logger.<init>(java.lang.String, java.lang.String[]):void");
    }

    private Logger(String str, String str2) {
        GmsLogger gmsLogger;
        String str3 = str;
        this.zzei = str2;
        this.mTag = str3;
        new GmsLogger(str3);
        this.zzew = gmsLogger;
        int i = 2;
        while (7 >= i && !Log.isLoggable(this.mTag, i)) {
            i++;
        }
        this.zzex = i;
    }

    @KeepForSdk
    public boolean isLoggable(int i) {
        return this.zzex <= i;
    }

    @KeepForSdk
    /* renamed from: v */
    public void mo25332v(String str, @Nullable Object... objArr) {
        String str2 = str;
        Object[] objArr2 = objArr;
        if (isLoggable(2)) {
            int v = Log.v(this.mTag, format(str2, objArr2));
        }
    }

    @KeepForSdk
    /* renamed from: d */
    public void mo25327d(String str, @Nullable Object... objArr) {
        String str2 = str;
        Object[] objArr2 = objArr;
        if (isLoggable(3)) {
            int d = Log.d(this.mTag, format(str2, objArr2));
        }
    }

    @KeepForSdk
    /* renamed from: i */
    public void mo25330i(String str, @Nullable Object... objArr) {
        int i = Log.i(this.mTag, format(str, objArr));
    }

    @KeepForSdk
    /* renamed from: w */
    public void mo25333w(String str, @Nullable Object... objArr) {
        int w = Log.w(this.mTag, format(str, objArr));
    }

    @KeepForSdk
    /* renamed from: e */
    public void mo25329e(String str, @Nullable Object... objArr) {
        int e = Log.e(this.mTag, format(str, objArr));
    }

    @KeepForSdk
    /* renamed from: e */
    public void mo25328e(String str, Throwable th, @Nullable Object... objArr) {
        int e = Log.e(this.mTag, format(str, objArr), th);
    }

    @KeepForSdk
    public void wtf(String str, Throwable th, @Nullable Object... objArr) {
        int wtf = Log.wtf(this.mTag, format(str, objArr), th);
    }

    @KeepForSdk
    public void wtf(Throwable th) {
        int wtf = Log.wtf(this.mTag, th);
    }

    private final String format(String str, @Nullable Object... objArr) {
        String str2 = str;
        Object[] objArr2 = objArr;
        if (objArr2 != null && objArr2.length > 0) {
            str2 = String.format(Locale.US, str2, objArr2);
        }
        return this.zzei.concat(str2);
    }
}
