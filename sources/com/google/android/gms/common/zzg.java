package com.google.android.gms.common;

import java.lang.ref.WeakReference;

abstract class zzg extends zze {
    private static final WeakReference<byte[]> zzw;
    private WeakReference<byte[]> zzv = zzw;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzg(byte[] bArr) {
        super(bArr);
    }

    /* access modifiers changed from: protected */
    public abstract byte[] zzd();

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public final byte[] getBytes() {
        WeakReference<byte[]> weakReference;
        synchronized (this) {
            try {
                byte[] bArr = (byte[]) this.zzv.get();
                byte[] bArr2 = bArr;
                if (bArr == null) {
                    bArr2 = zzd();
                    new WeakReference(bArr2);
                    this.zzv = weakReference;
                }
                byte[] bArr3 = bArr2;
                return bArr3;
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    static {
        WeakReference<byte[]> weakReference;
        new WeakReference<>((Object) null);
        zzw = weakReference;
    }
}
