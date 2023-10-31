package com.google.android.gms.common.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import android.os.WorkSource;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.Component;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@KeepForSdk
public class WorkSourceUtil {
    private static final int zzhj = Process.myUid();
    private static final Method zzhk = zzx();
    private static final Method zzhl = zzy();
    private static final Method zzhm = zzz();
    private static final Method zzhn = zzaa();
    private static final Method zzho = zzab();
    private static final Method zzhp = zzac();
    private static final Method zzhq = zzad();

    private WorkSourceUtil() {
    }

    private static WorkSource zza(int i, String str) {
        WorkSource workSource;
        new WorkSource();
        WorkSource workSource2 = workSource;
        WorkSource workSource3 = workSource2;
        zza(workSource2, i, str);
        return workSource3;
    }

    @Nullable
    @KeepForSdk
    public static WorkSource fromPackage(Context context, @Nullable String str) {
        String str2;
        String str3;
        String str4;
        String str5;
        Context context2 = context;
        String str6 = str;
        if (context2 == null || context2.getPackageManager() == null || str6 == null) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = Wrappers.packageManager(context2).getApplicationInfo(str6, 0);
            if (applicationInfo != null) {
                return zza(applicationInfo.uid, str6);
            }
            String valueOf = String.valueOf(str6);
            String str7 = valueOf;
            if (valueOf.length() != 0) {
                str5 = "Could not get applicationInfo from package: ".concat(str7);
            } else {
                str5 = str4;
                new String("Could not get applicationInfo from package: ");
            }
            int e = Log.e("WorkSourceUtil", str5);
            return null;
        } catch (PackageManager.NameNotFoundException e2) {
            String valueOf2 = String.valueOf(str6);
            String str8 = valueOf2;
            if (valueOf2.length() != 0) {
                str3 = "Could not find package: ".concat(str8);
            } else {
                str3 = str2;
                new String("Could not find package: ");
            }
            int e3 = Log.e("WorkSourceUtil", str3);
            return null;
        }
    }

    private static void zza(WorkSource workSource, int i, @Nullable String str) {
        WorkSource workSource2 = workSource;
        int i2 = i;
        String str2 = str;
        if (zzhl != null) {
            if (str2 == null) {
                str2 = "";
            }
            try {
                Object[] objArr = new Object[2];
                objArr[0] = Integer.valueOf(i2);
                Object[] objArr2 = objArr;
                objArr2[1] = str2;
                Object invoke = zzhl.invoke(workSource2, objArr2);
            } catch (Exception e) {
                int wtf = Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
            }
        } else if (zzhk != null) {
            try {
                Object invoke2 = zzhk.invoke(workSource2, new Object[]{Integer.valueOf(i2)});
            } catch (Exception e2) {
                int wtf2 = Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e2);
            }
        }
    }

    @KeepForSdk
    public static WorkSource fromPackageAndModuleExperimentalPi(Context context, String str, String str2) {
        WorkSource workSource;
        Context context2 = context;
        String str3 = str;
        String str4 = str2;
        if (context2 == null || context2.getPackageManager() == null || str4 == null || str3 == null) {
            int w = Log.w("WorkSourceUtil", "Unexpected null arguments");
            return null;
        }
        int zzd = zzd(context2, str3);
        int i = zzd;
        if (zzd < 0) {
            return null;
        }
        new WorkSource();
        WorkSource workSource2 = workSource;
        WorkSource workSource3 = workSource2;
        String str5 = str4;
        String str6 = str3;
        int i2 = i;
        WorkSource workSource4 = workSource2;
        if (zzhp == null || zzhq == null) {
            zza(workSource4, i2, str6);
        } else {
            try {
                Object invoke = zzhp.invoke(workSource4, new Object[0]);
                if (i2 != zzhj) {
                    Object[] objArr = new Object[2];
                    objArr[0] = Integer.valueOf(i2);
                    Object[] objArr2 = objArr;
                    objArr2[1] = str6;
                    Object invoke2 = zzhq.invoke(invoke, objArr2);
                }
                Object[] objArr3 = new Object[2];
                objArr3[0] = Integer.valueOf(zzhj);
                Object[] objArr4 = objArr3;
                objArr4[1] = str5;
                Object invoke3 = zzhq.invoke(invoke, objArr4);
            } catch (Exception e) {
                int w2 = Log.w("WorkSourceUtil", "Unable to assign chained blame through WorkSource", e);
            }
        }
        return workSource3;
    }

    private static int zza(WorkSource workSource) {
        WorkSource workSource2 = workSource;
        if (zzhm != null) {
            try {
                return ((Integer) zzhm.invoke(workSource2, new Object[0])).intValue();
            } catch (Exception e) {
                int wtf = Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
            }
        }
        return 0;
    }

    @Nullable
    private static String zza(WorkSource workSource, int i) {
        WorkSource workSource2 = workSource;
        int i2 = i;
        if (zzho != null) {
            try {
                return (String) zzho.invoke(workSource2, new Object[]{Integer.valueOf(i2)});
            } catch (Exception e) {
                int wtf = Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
            }
        }
        return null;
    }

    @KeepForSdk
    public static List<String> getNames(@Nullable WorkSource workSource) {
        List<String> list;
        WorkSource workSource2 = workSource;
        int zza = workSource2 == null ? 0 : zza(workSource2);
        int i = zza;
        if (zza == 0) {
            return Collections.emptyList();
        }
        new ArrayList();
        List<String> list2 = list;
        for (int i2 = 0; i2 < i; i2++) {
            String zza2 = zza(workSource2, i2);
            String str = zza2;
            if (!Strings.isEmptyOrWhitespace(zza2)) {
                boolean add = list2.add(str);
            }
        }
        return list2;
    }

    @KeepForSdk
    public static boolean hasWorkSourcePermission(Context context) {
        Context context2 = context;
        if (context2 == null) {
            return false;
        }
        if (context2.getPackageManager() == null) {
            return false;
        }
        return Wrappers.packageManager(context2).checkPermission("android.permission.UPDATE_DEVICE_STATS", context2.getPackageName()) == 0;
    }

    private static int zzd(Context context, String str) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6 = str;
        try {
            ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(str6, 0);
            if (applicationInfo != null) {
                return applicationInfo.uid;
            }
            String valueOf = String.valueOf(str6);
            String str7 = valueOf;
            if (valueOf.length() != 0) {
                str5 = "Could not get applicationInfo from package: ".concat(str7);
            } else {
                str5 = str4;
                new String("Could not get applicationInfo from package: ");
            }
            int e = Log.e("WorkSourceUtil", str5);
            return -1;
        } catch (PackageManager.NameNotFoundException e2) {
            String valueOf2 = String.valueOf(str6);
            String str8 = valueOf2;
            if (valueOf2.length() != 0) {
                str3 = "Could not find package: ".concat(str8);
            } else {
                str3 = str2;
                new String("Could not find package: ");
            }
            int e3 = Log.e("WorkSourceUtil", str3);
            return -1;
        }
    }

    private static Method zzx() {
        Method method = null;
        try {
            method = WorkSource.class.getMethod(Component.DEFAULT_VALUE_FAB_ICON_NAME, new Class[]{Integer.TYPE});
        } catch (Exception e) {
        }
        return method;
    }

    private static Method zzy() {
        Method method = null;
        if (PlatformVersion.isAtLeastJellyBeanMR2()) {
            Class<WorkSource> cls = WorkSource.class;
            try {
                Class[] clsArr = new Class[2];
                clsArr[0] = Integer.TYPE;
                Class[] clsArr2 = clsArr;
                clsArr2[1] = String.class;
                method = cls.getMethod(Component.DEFAULT_VALUE_FAB_ICON_NAME, clsArr2);
            } catch (Exception e) {
            }
        }
        return method;
    }

    private static Method zzz() {
        Method method = null;
        try {
            method = WorkSource.class.getMethod(PropertyTypeConstants.PROPERTY_TYPE_FAB_SIZE, new Class[0]);
        } catch (Exception e) {
        }
        return method;
    }

    private static Method zzaa() {
        Method method = null;
        try {
            method = WorkSource.class.getMethod("get", new Class[]{Integer.TYPE});
        } catch (Exception e) {
        }
        return method;
    }

    private static Method zzab() {
        Method method = null;
        if (PlatformVersion.isAtLeastJellyBeanMR2()) {
            try {
                method = WorkSource.class.getMethod("getName", new Class[]{Integer.TYPE});
            } catch (Exception e) {
            }
        }
        return method;
    }

    private static final Method zzac() {
        Method method = null;
        if (PlatformVersion.isAtLeastP()) {
            try {
                method = WorkSource.class.getMethod("createWorkChain", new Class[0]);
            } catch (Exception e) {
                int w = Log.w("WorkSourceUtil", "Missing WorkChain API createWorkChain", e);
            }
        }
        return method;
    }

    @SuppressLint({"PrivateApi"})
    private static final Method zzad() {
        Method method = null;
        if (PlatformVersion.isAtLeastP()) {
            try {
                Class<?> cls = Class.forName("android.os.WorkSource$WorkChain");
                Class[] clsArr = new Class[2];
                clsArr[0] = Integer.TYPE;
                Class[] clsArr2 = clsArr;
                clsArr2[1] = String.class;
                method = cls.getMethod("addNode", clsArr2);
            } catch (Exception e) {
                int w = Log.w("WorkSourceUtil", "Missing WorkChain class", e);
            }
        }
        return method;
    }
}
