package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.dynamite.DynamiteModule;
import java.lang.reflect.Method;

public class ProviderInstaller {
    public static final String PROVIDER_NAME = "GmsCore_OpenSSL";
    private static final Object lock;
    /* access modifiers changed from: private */
    public static final GoogleApiAvailabilityLight zziv = GoogleApiAvailabilityLight.getInstance();
    private static Method zziw = null;

    public interface ProviderInstallListener {
        void onProviderInstallFailed(int i, Intent intent);

        void onProviderInstalled();
    }

    public ProviderInstaller() {
    }

    public static void installIfNeeded(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        Throwable th;
        String str;
        String str2;
        Throwable th2;
        Context context2 = context;
        Object checkNotNull = Preconditions.checkNotNull(context2, "Context must not be null");
        zziv.verifyGooglePlayServicesIsAvailable(context2, 11925000);
        Context zzk = zzk(context2);
        Context context3 = zzk;
        if (zzk == null) {
            context3 = zzl(context2);
        }
        if (context3 == null) {
            int e = Log.e("ProviderInstaller", "Failed to get remote context");
            Throwable th3 = th2;
            new GooglePlayServicesNotAvailableException(8);
            throw th3;
        }
        Object obj = lock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (zziw == null) {
                    zziw = context3.getClassLoader().loadClass("com.google.android.gms.common.security.ProviderInstallerImpl").getMethod("insertProvider", new Class[]{Context.class});
                }
                Object invoke = zziw.invoke((Object) null, new Object[]{context3});
            } catch (Exception e2) {
                Throwable th4 = e2;
                Throwable th5 = th4;
                Throwable cause = th4.getCause();
                if (Log.isLoggable("ProviderInstaller", 6)) {
                    String valueOf = String.valueOf(cause == null ? th5.getMessage() : cause.getMessage());
                    String str3 = valueOf;
                    if (valueOf.length() != 0) {
                        str2 = "Failed to install provider: ".concat(str3);
                    } else {
                        str2 = str;
                        new String("Failed to install provider: ");
                    }
                    int e3 = Log.e("ProviderInstaller", str2);
                }
                boolean addDynamiteErrorToDropBox = CrashUtils.addDynamiteErrorToDropBox(context2, cause == null ? th5 : cause);
                Throwable th6 = th;
                new GooglePlayServicesNotAvailableException(8);
                throw th6;
            } catch (Throwable th7) {
                Throwable th8 = th7;
                Object obj3 = obj2;
                throw th8;
            }
        }
    }

    public static void installIfNeededAsync(Context context, ProviderInstallListener providerInstallListener) {
        AsyncTask asyncTask;
        Context context2 = context;
        ProviderInstallListener providerInstallListener2 = providerInstallListener;
        Object checkNotNull = Preconditions.checkNotNull(context2, "Context must not be null");
        Object checkNotNull2 = Preconditions.checkNotNull(providerInstallListener2, "Listener must not be null");
        Preconditions.checkMainThread("Must be called on the UI thread");
        new zza(context2, providerInstallListener2);
        AsyncTask execute = asyncTask.execute(new Void[0]);
    }

    @Nullable
    private static Context zzk(Context context) {
        String str;
        String str2;
        try {
            return DynamiteModule.load(context, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING, "providerinstaller").getModuleContext();
        } catch (DynamiteModule.LoadingException e) {
            String valueOf = String.valueOf(e.getMessage());
            String str3 = valueOf;
            if (valueOf.length() != 0) {
                str2 = "Failed to load providerinstaller module: ".concat(str3);
            } else {
                str2 = str;
                new String("Failed to load providerinstaller module: ");
            }
            int w = Log.w("ProviderInstaller", str2);
            return null;
        }
    }

    @Nullable
    private static Context zzl(Context context) {
        String str;
        String str2;
        Context context2 = context;
        try {
            return GooglePlayServicesUtilLight.getRemoteContext(context2);
        } catch (Resources.NotFoundException e) {
            Resources.NotFoundException notFoundException = e;
            String valueOf = String.valueOf(notFoundException.getMessage());
            String str3 = valueOf;
            if (valueOf.length() != 0) {
                str2 = "Failed to load GMS Core context for providerinstaller: ".concat(str3);
            } else {
                str2 = str;
                new String("Failed to load GMS Core context for providerinstaller: ");
            }
            int w = Log.w("ProviderInstaller", str2);
            boolean addDynamiteErrorToDropBox = CrashUtils.addDynamiteErrorToDropBox(context2, notFoundException);
            return null;
        }
    }

    static {
        Object obj;
        new Object();
        lock = obj;
    }
}
