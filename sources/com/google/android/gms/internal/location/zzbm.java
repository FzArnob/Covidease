package com.google.android.gms.internal.location;

import android.os.Looper;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;

public final class zzbm {
    public static Looper zza(@Nullable Looper looper) {
        Looper looper2 = looper;
        return looper2 != null ? looper2 : zzc();
    }

    public static Looper zzc() {
        Preconditions.checkState(Looper.myLooper() != null, "Can't create handler inside thread that has not called Looper.prepare()");
        return Looper.myLooper();
    }
}
