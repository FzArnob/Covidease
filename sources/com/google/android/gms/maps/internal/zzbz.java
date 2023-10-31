package com.google.android.gms.maps.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class zzbz {
    private static final String TAG = zzbz.class.getSimpleName();
    @Nullable
    @SuppressLint({"StaticFieldLeak"})
    private static Context zzck = null;
    private static zze zzcl;

    public zzbz() {
    }

    public static zze zza(Context context) throws GooglePlayServicesNotAvailableException {
        zze zze;
        zze zze2;
        Throwable th;
        Throwable th2;
        Context context2 = context;
        Object checkNotNull = Preconditions.checkNotNull(context2);
        if (zzcl != null) {
            return zzcl;
        }
        int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context2, 13400000);
        int i = isGooglePlayServicesAvailable;
        switch (isGooglePlayServicesAvailable) {
            case 0:
                int i2 = Log.i(TAG, "Making Creator dynamically");
                IBinder iBinder = (IBinder) zza(zzb(context2).getClassLoader(), "com.google.android.gms.maps.internal.CreatorImpl");
                IBinder iBinder2 = iBinder;
                if (iBinder == null) {
                    zze2 = null;
                } else {
                    IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.maps.internal.ICreator");
                    IInterface iInterface = queryLocalInterface;
                    if (queryLocalInterface instanceof zze) {
                        zze2 = (zze) iInterface;
                    } else {
                        zze2 = zze;
                        new zzf(iBinder2);
                    }
                }
                zzcl = zze2;
                try {
                    zzcl.zza(ObjectWrapper.wrap(zzb(context2).getResources()), GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE);
                    return zzcl;
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                    Throwable th3 = th;
                    new RuntimeRemoteException(remoteException);
                    throw th3;
                }
            default:
                Throwable th4 = th2;
                new GooglePlayServicesNotAvailableException(i);
                throw th4;
        }
    }

    @Nullable
    private static Context zzb(Context context) {
        Context context2 = context;
        if (zzck != null) {
            return zzck;
        }
        Context zzc = zzc(context2);
        zzck = zzc;
        return zzc;
    }

    @Nullable
    private static Context zzc(Context context) {
        Context remoteContext;
        Context context2 = context;
        try {
            remoteContext = DynamiteModule.load(context2, DynamiteModule.PREFER_REMOTE, "com.google.android.gms.maps_dynamite").getModuleContext();
        } catch (Exception e) {
            int e2 = Log.e(TAG, "Failed to load maps module, use legacy", e);
            remoteContext = GooglePlayServicesUtil.getRemoteContext(context2);
        }
        return remoteContext;
    }

    private static <T> T zza(ClassLoader classLoader, String str) {
        String str2;
        String str3;
        String str4 = str;
        try {
            return zza(((ClassLoader) Preconditions.checkNotNull(classLoader)).loadClass(str4));
        } catch (ClassNotFoundException e) {
            IllegalStateException illegalStateException = r7;
            String valueOf = String.valueOf(str4);
            String str5 = valueOf;
            if (valueOf.length() != 0) {
                str3 = "Unable to find dynamic class ".concat(str5);
            } else {
                str3 = str2;
                new String("Unable to find dynamic class ");
            }
            IllegalStateException illegalStateException2 = new IllegalStateException(str3);
            throw illegalStateException;
        }
    }

    private static <T> T zza(Class<?> cls) {
        String str;
        String str2;
        String str3;
        String str4;
        Class<?> cls2 = cls;
        try {
            return cls2.newInstance();
        } catch (InstantiationException e) {
            IllegalStateException illegalStateException = r6;
            String valueOf = String.valueOf(cls2.getName());
            String str5 = valueOf;
            if (valueOf.length() != 0) {
                str4 = "Unable to instantiate the dynamic class ".concat(str5);
            } else {
                str4 = str3;
                new String("Unable to instantiate the dynamic class ");
            }
            IllegalStateException illegalStateException2 = new IllegalStateException(str4);
            throw illegalStateException;
        } catch (IllegalAccessException e2) {
            IllegalStateException illegalStateException3 = r6;
            String valueOf2 = String.valueOf(cls2.getName());
            String str6 = valueOf2;
            if (valueOf2.length() != 0) {
                str2 = "Unable to call the default constructor of ".concat(str6);
            } else {
                str2 = str;
                new String("Unable to call the default constructor of ");
            }
            IllegalStateException illegalStateException4 = new IllegalStateException(str2);
            throw illegalStateException3;
        }
    }
}
