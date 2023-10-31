package com.google.android.gms.common;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zzi;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

abstract class zze extends zzj {
    private int zzt;

    protected zze(byte[] bArr) {
        byte[] bArr2 = bArr;
        Preconditions.checkArgument(bArr2.length == 25);
        this.zzt = Arrays.hashCode(bArr2);
    }

    /* access modifiers changed from: package-private */
    public abstract byte[] getBytes();

    public int hashCode() {
        return this.zzt;
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        if (obj2 == null || !(obj2 instanceof zzi)) {
            return false;
        }
        try {
            zzi zzi = (zzi) obj2;
            zzi zzi2 = zzi;
            if (zzi.zzc() != hashCode()) {
                return false;
            }
            IObjectWrapper zzb = zzi2.zzb();
            IObjectWrapper iObjectWrapper = zzb;
            if (zzb == null) {
                return false;
            }
            return Arrays.equals(getBytes(), (byte[]) ObjectWrapper.unwrap(iObjectWrapper));
        } catch (RemoteException e) {
            int e2 = Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
            return false;
        }
    }

    public final IObjectWrapper zzb() {
        return ObjectWrapper.wrap(getBytes());
    }

    public final int zzc() {
        return hashCode();
    }

    protected static byte[] zza(String str) {
        Throwable th;
        try {
            return str.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            UnsupportedEncodingException unsupportedEncodingException = e;
            Throwable th2 = th;
            new AssertionError(unsupportedEncodingException);
            throw th2;
        }
    }
}
