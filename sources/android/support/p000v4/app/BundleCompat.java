package android.support.p000v4.app;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: android.support.v4.app.BundleCompat */
public final class BundleCompat {

    /* renamed from: android.support.v4.app.BundleCompat$BundleCompatBaseImpl */
    static class BundleCompatBaseImpl {
        private static final String TAG = "BundleCompatBaseImpl";
        private static Method sGetIBinderMethod;
        private static boolean sGetIBinderMethodFetched;
        private static Method sPutIBinderMethod;
        private static boolean sPutIBinderMethodFetched;

        private BundleCompatBaseImpl() {
        }

        public static IBinder getBinder(Bundle bundle, String str) {
            Bundle bundle2 = bundle;
            String key = str;
            if (!sGetIBinderMethodFetched) {
                try {
                    sGetIBinderMethod = Bundle.class.getMethod("getIBinder", new Class[]{String.class});
                    sGetIBinderMethod.setAccessible(true);
                } catch (NoSuchMethodException e) {
                    int i = Log.i(TAG, "Failed to retrieve getIBinder method", e);
                }
                sGetIBinderMethodFetched = true;
            }
            if (sGetIBinderMethod != null) {
                try {
                    return (IBinder) sGetIBinderMethod.invoke(bundle2, new Object[]{key});
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e2) {
                    int i2 = Log.i(TAG, "Failed to invoke getIBinder via reflection", e2);
                    sGetIBinderMethod = null;
                }
            }
            return null;
        }

        public static void putBinder(Bundle bundle, String str, IBinder iBinder) {
            Bundle bundle2 = bundle;
            String key = str;
            IBinder binder = iBinder;
            if (!sPutIBinderMethodFetched) {
                Class<Bundle> cls = Bundle.class;
                try {
                    Class[] clsArr = new Class[2];
                    clsArr[0] = String.class;
                    Class[] clsArr2 = clsArr;
                    clsArr2[1] = IBinder.class;
                    sPutIBinderMethod = cls.getMethod("putIBinder", clsArr2);
                    sPutIBinderMethod.setAccessible(true);
                } catch (NoSuchMethodException e) {
                    int i = Log.i(TAG, "Failed to retrieve putIBinder method", e);
                }
                sPutIBinderMethodFetched = true;
            }
            if (sPutIBinderMethod != null) {
                try {
                    Object[] objArr = new Object[2];
                    objArr[0] = key;
                    Object[] objArr2 = objArr;
                    objArr2[1] = binder;
                    Object invoke = sPutIBinderMethod.invoke(bundle2, objArr2);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e2) {
                    int i2 = Log.i(TAG, "Failed to invoke putIBinder via reflection", e2);
                    sPutIBinderMethod = null;
                }
            }
        }
    }

    private BundleCompat() {
    }

    @Nullable
    public static IBinder getBinder(@NonNull Bundle bundle, @Nullable String str) {
        Bundle bundle2 = bundle;
        String key = str;
        if (Build.VERSION.SDK_INT >= 18) {
            return bundle2.getBinder(key);
        }
        return BundleCompatBaseImpl.getBinder(bundle2, key);
    }

    public static void putBinder(@NonNull Bundle bundle, @Nullable String str, @Nullable IBinder iBinder) {
        Bundle bundle2 = bundle;
        String key = str;
        IBinder binder = iBinder;
        if (Build.VERSION.SDK_INT >= 18) {
            bundle2.putBinder(key, binder);
        } else {
            BundleCompatBaseImpl.putBinder(bundle2, key, binder);
        }
    }
}
