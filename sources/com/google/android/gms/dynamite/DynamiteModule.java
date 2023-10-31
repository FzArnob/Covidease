package com.google.android.gms.dynamite;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public final class DynamiteModule {
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION;
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING;
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION;
    @KeepForSdk
    public static final VersionPolicy PREFER_LOCAL;
    @KeepForSdk
    public static final VersionPolicy PREFER_REMOTE;
    @GuardedBy("DynamiteModule.class")
    private static Boolean zzif;
    @GuardedBy("DynamiteModule.class")
    private static zzi zzig;
    @GuardedBy("DynamiteModule.class")
    private static zzk zzih;
    @GuardedBy("DynamiteModule.class")
    private static String zzii;
    @GuardedBy("DynamiteModule.class")
    private static int zzij = -1;
    private static final ThreadLocal<zza> zzik;
    private static final VersionPolicy.zza zzil;
    private static final VersionPolicy zzim;
    private final Context zzin;

    @DynamiteApi
    public static class DynamiteLoaderClassLoader {
        @GuardedBy("DynamiteLoaderClassLoader.class")
        public static ClassLoader sClassLoader;

        public DynamiteLoaderClassLoader() {
        }
    }

    public interface VersionPolicy {

        public interface zza {
            int getLocalVersion(Context context, String str);

            int zza(Context context, String str, boolean z) throws LoadingException;
        }

        public static class zzb {
            public int zzir = 0;
            public int zzis = 0;
            public int zzit = 0;

            public zzb() {
            }
        }

        zzb zza(Context context, String str, zza zza2) throws LoadingException;
    }

    private static class zza {
        public Cursor zzio;

        private zza() {
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ zza(zza zza) {
            this();
            zza zza2 = zza;
        }
    }

    @KeepForSdk
    public static DynamiteModule load(Context context, VersionPolicy versionPolicy, String str) throws LoadingException {
        zza zza2;
        VersionPolicy.zzb zza3;
        StringBuilder sb;
        Throwable th;
        StringBuilder sb2;
        Throwable th2;
        StringBuilder sb3;
        String str2;
        String str3;
        Throwable th3;
        VersionPolicy.zza zza4;
        Context context2 = context;
        VersionPolicy versionPolicy2 = versionPolicy;
        String str4 = str;
        zza zza5 = zzik.get();
        new zza((zza) null);
        zza zza6 = zza2;
        zzik.set(zza6);
        try {
            zza3 = versionPolicy2.zza(context2, str4, zzil);
            int i = zza3.zzir;
            int i2 = zza3.zzis;
            new StringBuilder(68 + String.valueOf(str4).length() + String.valueOf(str4).length());
            int i3 = Log.i("DynamiteModule", sb.append("Considering local module ").append(str4).append(":").append(i).append(" and remote module ").append(str4).append(":").append(i2).toString());
            if (zza3.zzit == 0 || ((zza3.zzit == -1 && zza3.zzir == 0) || (zza3.zzit == 1 && zza3.zzis == 0))) {
                Throwable th4 = th;
                int i4 = zza3.zzir;
                int i5 = zza3.zzis;
                new StringBuilder(91);
                new LoadingException(sb2.append("No acceptable module found. Local version is ").append(i4).append(" and remote version is ").append(i5).append(".").toString(), (zza) null);
                throw th4;
            } else if (zza3.zzit == -1) {
                DynamiteModule zze = zze(context2, str4);
                if (zza6.zzio != null) {
                    zza6.zzio.close();
                }
                zzik.set(zza5);
                return zze;
            } else if (zza3.zzit == 1) {
                DynamiteModule zza7 = zza(context2, str4, zza3.zzis);
                if (zza6.zzio != null) {
                    zza6.zzio.close();
                }
                zzik.set(zza5);
                return zza7;
            } else {
                Throwable th5 = th2;
                int i6 = zza3.zzit;
                new StringBuilder(47);
                new LoadingException(sb3.append("VersionPolicy returned invalid code:").append(i6).toString(), (zza) null);
                throw th5;
            }
        } catch (LoadingException e) {
            LoadingException loadingException = e;
            String valueOf = String.valueOf(loadingException.getMessage());
            String str5 = valueOf;
            if (valueOf.length() != 0) {
                str3 = "Failed to load remote module: ".concat(str5);
            } else {
                str3 = str2;
                new String("Failed to load remote module: ");
            }
            int w = Log.w("DynamiteModule", str3);
            if (zza3.zzir != 0) {
                new zzb(zza3.zzir, 0);
                if (versionPolicy2.zza(context2, str4, zza4).zzit == -1) {
                    DynamiteModule zze2 = zze(context2, str4);
                    if (zza6.zzio != null) {
                        zza6.zzio.close();
                    }
                    zzik.set(zza5);
                    return zze2;
                }
            }
            Throwable th6 = th3;
            new LoadingException("Remote load failed. No local fallback found.", loadingException, (zza) null);
            throw th6;
        } catch (Throwable th7) {
            Throwable th8 = th7;
            if (zza6.zzio != null) {
                zza6.zzio.close();
            }
            zzik.set(zza5);
            throw th8;
        }
    }

    @KeepForSdk
    public static class LoadingException extends Exception {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        private LoadingException(String str) {
            super(str);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        private LoadingException(String str, Throwable th) {
            super(str, th);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ LoadingException(String str, zza zza) {
            this(str);
            zza zza2 = zza;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ LoadingException(String str, Throwable th, zza zza) {
            this(str, th);
            zza zza2 = zza;
        }
    }

    private static class zzb implements VersionPolicy.zza {
        private final int zzip;
        private final int zziq = 0;

        public zzb(int i, int i2) {
            int i3 = i2;
            this.zzip = i;
        }

        public final int zza(Context context, String str, boolean z) {
            Context context2 = context;
            String str2 = str;
            boolean z2 = z;
            return 0;
        }

        public final int getLocalVersion(Context context, String str) {
            Context context2 = context;
            String str2 = str;
            return this.zzip;
        }
    }

    @KeepForSdk
    public static int getLocalVersion(Context context, String str) {
        String str2;
        String str3;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        String str4 = str;
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            new StringBuilder(61 + String.valueOf(str4).length());
            Class<?> loadClass = classLoader.loadClass(sb2.append("com.google.android.gms.dynamite.descriptors.").append(str4).append(".ModuleDescriptor").toString());
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get((Object) null).equals(str4)) {
                return declaredField2.getInt((Object) null);
            }
            String valueOf = String.valueOf(declaredField.get((Object) null));
            new StringBuilder(51 + String.valueOf(valueOf).length() + String.valueOf(str4).length());
            int e = Log.e("DynamiteModule", sb3.append("Module descriptor id '").append(valueOf).append("' didn't match expected id '").append(str4).append("'").toString());
            return 0;
        } catch (ClassNotFoundException e2) {
            new StringBuilder(45 + String.valueOf(str4).length());
            int w = Log.w("DynamiteModule", sb.append("Local module descriptor class for ").append(str4).append(" not found.").toString());
            return 0;
        } catch (Exception e3) {
            String valueOf2 = String.valueOf(e3.getMessage());
            String str5 = valueOf2;
            if (valueOf2.length() != 0) {
                str3 = "Failed to load module descriptor class: ".concat(str5);
            } else {
                str3 = str2;
                new String("Failed to load module descriptor class: ");
            }
            int e4 = Log.e("DynamiteModule", str3);
            return 0;
        }
    }

    /* JADX INFO: finally extract failed */
    public static int zza(Context context, String str, boolean z) {
        String str2;
        String str3;
        StringBuilder sb;
        ClassLoader classLoader;
        Context context2 = context;
        String str4 = str;
        boolean z2 = z;
        Class<DynamiteModule> cls = DynamiteModule.class;
        Class<DynamiteModule> cls2 = cls;
        try {
            synchronized (cls) {
                Boolean bool = zzif;
                Boolean bool2 = bool;
                if (bool == null) {
                    try {
                        Class<?> loadClass = context2.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName());
                        Field declaredField = loadClass.getDeclaredField("sClassLoader");
                        Class<?> cls3 = loadClass;
                        Class<?> cls4 = cls3;
                        synchronized (cls3) {
                            try {
                                ClassLoader classLoader2 = (ClassLoader) declaredField.get((Object) null);
                                ClassLoader classLoader3 = classLoader2;
                                if (classLoader2 != null) {
                                    if (classLoader3 == ClassLoader.getSystemClassLoader()) {
                                        bool2 = Boolean.FALSE;
                                    } else {
                                        zza(classLoader3);
                                        bool2 = Boolean.TRUE;
                                    }
                                } else if ("com.google.android.gms".equals(context2.getApplicationContext().getPackageName())) {
                                    declaredField.set((Object) null, ClassLoader.getSystemClassLoader());
                                    bool2 = Boolean.FALSE;
                                } else {
                                    try {
                                        int zzc = zzc(context2, str4, z2);
                                        if (zzii == null || zzii.isEmpty()) {
                                            int i = zzc;
                                            return i;
                                        }
                                        new zzh(zzii, ClassLoader.getSystemClassLoader());
                                        ClassLoader classLoader4 = classLoader;
                                        zza(classLoader4);
                                        declaredField.set((Object) null, classLoader4);
                                        zzif = Boolean.TRUE;
                                        int i2 = zzc;
                                        return i2;
                                    } catch (LoadingException e) {
                                        declaredField.set((Object) null, ClassLoader.getSystemClassLoader());
                                        bool2 = Boolean.FALSE;
                                    }
                                }
                            } catch (LoadingException e2) {
                            } catch (Throwable th) {
                                Throwable th2 = th;
                                Class<?> cls5 = cls4;
                                throw th2;
                            }
                            zzif = bool2;
                        }
                    } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e3) {
                        String valueOf = String.valueOf(e3);
                        new StringBuilder(30 + String.valueOf(valueOf).length());
                        int w = Log.w("DynamiteModule", sb.append("Failed to load module via V2: ").append(valueOf).toString());
                        bool2 = Boolean.FALSE;
                    }
                }
                if (!bool2.booleanValue()) {
                    return zzb(context2, str4, z2);
                }
                try {
                    return zzc(context2, str4, z2);
                } catch (LoadingException e4) {
                    String valueOf2 = String.valueOf(e4.getMessage());
                    String str5 = valueOf2;
                    if (valueOf2.length() != 0) {
                        str3 = "Failed to retrieve remote module version: ".concat(str5);
                    } else {
                        str3 = str2;
                        new String("Failed to retrieve remote module version: ");
                    }
                    int w2 = Log.w("DynamiteModule", str3);
                    return 0;
                }
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            boolean addDynamiteErrorToDropBox = CrashUtils.addDynamiteErrorToDropBox(context2, th4);
            throw th4;
        }
    }

    private static int zzb(Context context, String str, boolean z) {
        String str2;
        String str3;
        Context context2 = context;
        String str4 = str;
        boolean z2 = z;
        zzi zzj = zzj(context2);
        zzi zzi = zzj;
        if (zzj == null) {
            return 0;
        }
        try {
            if (zzi.zzak() >= 2) {
                return zzi.zzb(ObjectWrapper.wrap(context2), str4, z2);
            }
            int w = Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
            return zzi.zza(ObjectWrapper.wrap(context2), str4, z2);
        } catch (RemoteException e) {
            String valueOf = String.valueOf(e.getMessage());
            String str5 = valueOf;
            if (valueOf.length() != 0) {
                str3 = "Failed to retrieve remote module version: ".concat(str5);
            } else {
                str3 = str2;
                new String("Failed to retrieve remote module version: ");
            }
            int w2 = Log.w("DynamiteModule", str3);
            return 0;
        }
    }

    private static int zzc(Context context, String str, boolean z) throws LoadingException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        Class<DynamiteModule> cls;
        Throwable th3;
        Cursor cursor = null;
        boolean z2 = z;
        String str2 = str;
        try {
            ContentResolver contentResolver = context.getContentResolver();
            String str3 = str2;
            String str4 = z2 ? "api_force_staging" : "api";
            new StringBuilder(42 + String.valueOf(str4).length() + String.valueOf(str3).length());
            Cursor query = contentResolver.query(Uri.parse(sb.append("content://com.google.android.gms.chimera/").append(str4).append("/").append(str3).toString()), (String[]) null, (String) null, (String[]) null, (String) null);
            cursor = query;
            if (query == null || !cursor.moveToFirst()) {
                int w = Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                Throwable th4 = th2;
                new LoadingException("Failed to connect to dynamite module ContentResolver.", (zza) null);
                throw th4;
            }
            int i = cursor.getInt(0);
            int i2 = i;
            if (i > 0) {
                Class<DynamiteModule> cls2 = DynamiteModule.class;
                cls = cls2;
                synchronized (cls2) {
                    zzii = cursor.getString(2);
                    int columnIndex = cursor.getColumnIndex("loaderVersion");
                    int i3 = columnIndex;
                    if (columnIndex >= 0) {
                        zzij = cursor.getInt(i3);
                    }
                    zza zza2 = zzik.get();
                    zza zza3 = zza2;
                    if (zza2 != null && zza3.zzio == null) {
                        zza3.zzio = cursor;
                        cursor = null;
                    }
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return i2;
        } catch (Exception e) {
            Exception exc = e;
            Exception exc2 = exc;
            if (exc instanceof LoadingException) {
                throw exc2;
            }
            Throwable th5 = th;
            new LoadingException("V2 version check failed", exc2, (zza) null);
            throw th5;
        } catch (Throwable th6) {
            Throwable th7 = th6;
            if (cursor != null) {
                cursor.close();
            }
            throw th7;
        }
    }

    @KeepForSdk
    public static int getRemoteVersion(Context context, String str) {
        return zza(context, str, false);
    }

    private static DynamiteModule zze(Context context, String str) {
        String str2;
        String str3;
        DynamiteModule dynamiteModule;
        Context context2 = context;
        String valueOf = String.valueOf(str);
        String str4 = valueOf;
        if (valueOf.length() != 0) {
            str3 = "Selected local version of ".concat(str4);
        } else {
            str3 = str2;
            new String("Selected local version of ");
        }
        int i = Log.i("DynamiteModule", str3);
        new DynamiteModule(context2.getApplicationContext());
        return dynamiteModule;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.dynamite.DynamiteModule zza(android.content.Context r18, java.lang.String r19, int r20) throws com.google.android.gms.dynamite.DynamiteModule.LoadingException {
        /*
            r0 = r18
            r1 = r19
            r2 = r20
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r11 = com.google.android.gms.dynamite.DynamiteModule.class
            r16 = r11
            r11 = r16
            r12 = r16
            r4 = r12
            monitor-enter(r11)     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            java.lang.Boolean r11 = zzif     // Catch:{ all -> 0x003b }
            r3 = r11
            r11 = r4
            monitor-exit(r11)     // Catch:{ all -> 0x003b }
            r11 = r3
            if (r11 != 0) goto L_0x0043
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r11 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            r16 = r11
            r11 = r16
            r12 = r16
            java.lang.String r13 = "Failed to determine which loading route to use."
            r14 = 0
            r12.<init>((java.lang.String) r13, (com.google.android.gms.dynamite.zza) r14)     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            throw r11     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
        L_0x0028:
            r11 = move-exception
            r3 = r11
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r11 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException
            r16 = r11
            r11 = r16
            r12 = r16
            java.lang.String r13 = "Failed to load remote module."
            r14 = r3
            r15 = 0
            r12.<init>(r13, r14, r15)
            throw r11
        L_0x003b:
            r11 = move-exception
            r5 = r11
            r11 = r4
            monitor-exit(r11)     // Catch:{ all -> 0x003b }
            r11 = r5
            throw r11     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
        L_0x0041:
            r11 = move-exception
            throw r11
        L_0x0043:
            r11 = r3
            boolean r11 = r11.booleanValue()     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            if (r11 == 0) goto L_0x0053
            r11 = r0
            r12 = r1
            r13 = r2
            com.google.android.gms.dynamite.DynamiteModule r11 = zzb((android.content.Context) r11, (java.lang.String) r12, (int) r13)     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            r0 = r11
        L_0x0052:
            return r0
        L_0x0053:
            r11 = r0
            r12 = r1
            r13 = r2
            r8 = r13
            r7 = r12
            r6 = r11
            java.lang.String r11 = "DynamiteModule"
            r12 = 51
            r13 = r7
            java.lang.String r13 = java.lang.String.valueOf(r13)     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            int r13 = r13.length()     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            int r12 = r12 + r13
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            r16 = r12
            r17 = r13
            r12 = r17
            r13 = r16
            r14 = r17
            r16 = r13
            r17 = r14
            r13 = r17
            r14 = r16
            r13.<init>(r14)     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            java.lang.String r13 = "Selected remote version of "
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            r13 = r7
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            java.lang.String r13 = ", version >= "
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            r13 = r8
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            java.lang.String r12 = r12.toString()     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            int r11 = android.util.Log.i(r11, r12)     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            r11 = r6
            com.google.android.gms.dynamite.zzi r11 = zzj(r11)     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            r16 = r11
            r11 = r16
            r12 = r16
            r9 = r12
            if (r11 != 0) goto L_0x00d6
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r11 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            r16 = r11
            r11 = r16
            r12 = r16
            java.lang.String r13 = "Failed to create IDynamiteLoader."
            r14 = 0
            r12.<init>((java.lang.String) r13, (com.google.android.gms.dynamite.zza) r14)     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            throw r11     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
        L_0x00bd:
            r11 = move-exception
            r3 = r11
            r11 = r0
            r12 = r3
            boolean r11 = com.google.android.gms.common.util.CrashUtils.addDynamiteErrorToDropBox(r11, r12)
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r11 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException
            r16 = r11
            r11 = r16
            r12 = r16
            java.lang.String r13 = "Failed to load remote module."
            r14 = r3
            r15 = 0
            r12.<init>(r13, r14, r15)
            throw r11
        L_0x00d6:
            r11 = r9
            int r11 = r11.zzak()     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            r12 = 2
            if (r11 < r12) goto L_0x0102
            r11 = r9
            r12 = r6
            com.google.android.gms.dynamic.IObjectWrapper r12 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r12)     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            r13 = r7
            r14 = r8
            com.google.android.gms.dynamic.IObjectWrapper r11 = r11.zzb((com.google.android.gms.dynamic.IObjectWrapper) r12, (java.lang.String) r13, (int) r14)     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            r10 = r11
        L_0x00eb:
            r11 = r10
            java.lang.Object r11 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r11)     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            if (r11 != 0) goto L_0x011a
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r11 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            r16 = r11
            r11 = r16
            r12 = r16
            java.lang.String r13 = "Failed to load remote module."
            r14 = 0
            r12.<init>((java.lang.String) r13, (com.google.android.gms.dynamite.zza) r14)     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            throw r11     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
        L_0x0102:
            java.lang.String r11 = "DynamiteModule"
            java.lang.String r12 = "Dynamite loader version < 2, falling back to createModuleContext"
            int r11 = android.util.Log.w(r11, r12)     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            r11 = r9
            r12 = r6
            com.google.android.gms.dynamic.IObjectWrapper r12 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r12)     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            r13 = r7
            r14 = r8
            com.google.android.gms.dynamic.IObjectWrapper r11 = r11.zza((com.google.android.gms.dynamic.IObjectWrapper) r12, (java.lang.String) r13, (int) r14)     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            r10 = r11
            goto L_0x00eb
        L_0x011a:
            com.google.android.gms.dynamite.DynamiteModule r11 = new com.google.android.gms.dynamite.DynamiteModule     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            r16 = r11
            r11 = r16
            r12 = r16
            r13 = r10
            java.lang.Object r13 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r13)     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            android.content.Context r13 = (android.content.Context) r13     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            r12.<init>(r13)     // Catch:{ RemoteException -> 0x0028, LoadingException -> 0x0041, Throwable -> 0x00bd }
            r0 = r11
            goto L_0x0052
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zza(android.content.Context, java.lang.String, int):com.google.android.gms.dynamite.DynamiteModule");
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.dynamite.zzi zzj(android.content.Context r13) {
        /*
            r0 = r13
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r7 = com.google.android.gms.dynamite.DynamiteModule.class
            r11 = r7
            r7 = r11
            r8 = r11
            r1 = r8
            monitor-enter(r7)
            com.google.android.gms.dynamite.zzi r7 = zzig     // Catch:{ all -> 0x00a7 }
            if (r7 == 0) goto L_0x0012
            com.google.android.gms.dynamite.zzi r7 = zzig     // Catch:{ all -> 0x00a7 }
            r8 = r1
            monitor-exit(r8)     // Catch:{ all -> 0x00a7 }
            r0 = r7
        L_0x0011:
            return r0
        L_0x0012:
            com.google.android.gms.common.GoogleApiAvailabilityLight r7 = com.google.android.gms.common.GoogleApiAvailabilityLight.getInstance()     // Catch:{ all -> 0x00a7 }
            r8 = r0
            int r7 = r7.isGooglePlayServicesAvailable(r8)     // Catch:{ all -> 0x00a7 }
            if (r7 == 0) goto L_0x0022
            r7 = 0
            r8 = r1
            monitor-exit(r8)     // Catch:{ all -> 0x00a7 }
            r0 = r7
            goto L_0x0011
        L_0x0022:
            r7 = r0
            java.lang.String r8 = "com.google.android.gms"
            r9 = 3
            android.content.Context r7 = r7.createPackageContext(r8, r9)     // Catch:{ Exception -> 0x006f }
            java.lang.ClassLoader r7 = r7.getClassLoader()     // Catch:{ Exception -> 0x006f }
            java.lang.String r8 = "com.google.android.gms.chimera.container.DynamiteLoaderImpl"
            java.lang.Class r7 = r7.loadClass(r8)     // Catch:{ Exception -> 0x006f }
            java.lang.Object r7 = r7.newInstance()     // Catch:{ Exception -> 0x006f }
            android.os.IBinder r7 = (android.os.IBinder) r7     // Catch:{ Exception -> 0x006f }
            r11 = r7
            r7 = r11
            r8 = r11
            r5 = r8
            if (r7 != 0) goto L_0x0051
            r7 = 0
        L_0x0043:
            r11 = r7
            r7 = r11
            r8 = r11
            r3 = r8
            if (r7 == 0) goto L_0x0097
            r7 = r3
            zzig = r7     // Catch:{ Exception -> 0x006f }
            r7 = r3
            r8 = r1
            monitor-exit(r8)     // Catch:{ all -> 0x00a7 }
            r0 = r7
            goto L_0x0011
        L_0x0051:
            r7 = r5
            java.lang.String r8 = "com.google.android.gms.dynamite.IDynamiteLoader"
            android.os.IInterface r7 = r7.queryLocalInterface(r8)     // Catch:{ Exception -> 0x006f }
            r11 = r7
            r7 = r11
            r8 = r11
            r6 = r8
            boolean r7 = r7 instanceof com.google.android.gms.dynamite.zzi     // Catch:{ Exception -> 0x006f }
            if (r7 == 0) goto L_0x0065
            r7 = r6
            com.google.android.gms.dynamite.zzi r7 = (com.google.android.gms.dynamite.zzi) r7     // Catch:{ Exception -> 0x006f }
            goto L_0x0043
        L_0x0065:
            com.google.android.gms.dynamite.zzj r7 = new com.google.android.gms.dynamite.zzj     // Catch:{ Exception -> 0x006f }
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = r5
            r8.<init>(r9)     // Catch:{ Exception -> 0x006f }
            goto L_0x0043
        L_0x006f:
            r7 = move-exception
            r2 = r7
            java.lang.String r7 = "DynamiteModule"
            java.lang.String r8 = "Failed to load IDynamiteLoader from GmsCore: "
            r9 = r2
            java.lang.String r9 = r9.getMessage()     // Catch:{ all -> 0x00a7 }
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ all -> 0x00a7 }
            r11 = r9
            r9 = r11
            r10 = r11
            int r10 = r10.length()     // Catch:{ all -> 0x00a7 }
            if (r10 == 0) goto L_0x0098
            java.lang.String r8 = r8.concat(r9)     // Catch:{ all -> 0x00a7 }
        L_0x008d:
            int r7 = android.util.Log.e(r7, r8)     // Catch:{ all -> 0x00a7 }
        L_0x0091:
            r7 = r1
            monitor-exit(r7)     // Catch:{ all -> 0x00a7 }
            r7 = 0
            r0 = r7
            goto L_0x0011
        L_0x0097:
            goto L_0x0091
        L_0x0098:
            java.lang.String r9 = new java.lang.String     // Catch:{ all -> 0x00a7 }
            r11 = r8
            r12 = r9
            r8 = r12
            r9 = r11
            r10 = r12
            r11 = r9
            r12 = r10
            r9 = r12
            r10 = r11
            r9.<init>(r10)     // Catch:{ all -> 0x00a7 }
            goto L_0x008d
        L_0x00a7:
            r7 = move-exception
            r4 = r7
            r7 = r1
            monitor-exit(r7)     // Catch:{ all -> 0x00a7 }
            r7 = r4
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzj(android.content.Context):com.google.android.gms.dynamite.zzi");
    }

    @KeepForSdk
    public final Context getModuleContext() {
        return this.zzin;
    }

    /* JADX INFO: finally extract failed */
    private static DynamiteModule zzb(Context context, String str, int i) throws LoadingException, RemoteException {
        StringBuilder sb;
        Throwable th;
        IObjectWrapper zza2;
        DynamiteModule dynamiteModule;
        Throwable th2;
        Throwable th3;
        Context context2 = context;
        String str2 = str;
        int i2 = i;
        new StringBuilder(51 + String.valueOf(str2).length());
        int i3 = Log.i("DynamiteModule", sb.append("Selected remote version of ").append(str2).append(", version >= ").append(i2).toString());
        Class<DynamiteModule> cls = DynamiteModule.class;
        Class<DynamiteModule> cls2 = cls;
        synchronized (cls) {
            try {
                zzk zzk = zzih;
                if (zzk == null) {
                    Throwable th4 = th3;
                    new LoadingException("DynamiteLoaderV2 was not cached.", (zza) null);
                    throw th4;
                }
                zza zza3 = zzik.get();
                zza zza4 = zza3;
                if (zza3 == null || zza4.zzio == null) {
                    Throwable th5 = th;
                    new LoadingException("No result cursor", (zza) null);
                    throw th5;
                }
                Context applicationContext = context2.getApplicationContext();
                zzk zzk2 = zzk;
                Cursor cursor = zza4.zzio;
                int i4 = i2;
                String str3 = str2;
                Context context3 = applicationContext;
                IObjectWrapper wrap = ObjectWrapper.wrap(null);
                if (zzaj().booleanValue()) {
                    int v = Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");
                    zza2 = zzk2.zzb(ObjectWrapper.wrap(context3), str3, i4, ObjectWrapper.wrap(cursor));
                } else {
                    int w = Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
                    zza2 = zzk2.zza(ObjectWrapper.wrap(context3), str3, i4, ObjectWrapper.wrap(cursor));
                }
                Context context4 = (Context) ObjectWrapper.unwrap(zza2);
                Context context5 = context4;
                if (context4 == null) {
                    Throwable th6 = th2;
                    new LoadingException("Failed to get module context", (zza) null);
                    throw th6;
                }
                new DynamiteModule(context5);
                return dynamiteModule;
            } catch (Throwable th7) {
                while (true) {
                    Throwable th8 = th7;
                    Class<DynamiteModule> cls3 = cls2;
                    throw th8;
                }
            }
        }
    }

    private static Boolean zzaj() {
        Class<DynamiteModule> cls = DynamiteModule.class;
        Class<DynamiteModule> cls2 = cls;
        synchronized (cls) {
            try {
                Boolean valueOf = Boolean.valueOf(zzij >= 2);
                return valueOf;
            } catch (Throwable th) {
                Throwable th2 = th;
                Class<DynamiteModule> cls3 = cls2;
                throw th2;
            }
        }
    }

    @GuardedBy("DynamiteModule.class")
    private static void zza(ClassLoader classLoader) throws LoadingException {
        Throwable th;
        zzk zzk;
        zzk zzk2;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
            IBinder iBinder2 = iBinder;
            if (iBinder == null) {
                zzk2 = null;
            } else {
                IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                IInterface iInterface = queryLocalInterface;
                if (queryLocalInterface instanceof zzk) {
                    zzk2 = (zzk) iInterface;
                } else {
                    zzk2 = zzk;
                    new zzl(iBinder2);
                }
            }
            zzih = zzk2;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            Throwable th2 = e;
            Throwable th3 = th;
            new LoadingException("Failed to instantiate dynamite loader", th2, (zza) null);
            throw th3;
        }
    }

    @KeepForSdk
    public final IBinder instantiate(String str) throws LoadingException {
        String str2;
        String str3;
        String str4 = str;
        try {
            return (IBinder) this.zzin.getClassLoader().loadClass(str4).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            Throwable th = e;
            LoadingException loadingException = r8;
            String valueOf = String.valueOf(str4);
            String str5 = valueOf;
            if (valueOf.length() != 0) {
                str3 = "Failed to instantiate module class: ".concat(str5);
            } else {
                str3 = str2;
                new String("Failed to instantiate module class: ");
            }
            LoadingException loadingException2 = new LoadingException(str3, th, (zza) null);
            throw loadingException;
        }
    }

    private DynamiteModule(Context context) {
        this.zzin = (Context) Preconditions.checkNotNull(context);
    }

    static {
        ThreadLocal<zza> threadLocal;
        VersionPolicy.zza zza2;
        VersionPolicy versionPolicy;
        VersionPolicy versionPolicy2;
        VersionPolicy versionPolicy3;
        VersionPolicy versionPolicy4;
        VersionPolicy versionPolicy5;
        VersionPolicy versionPolicy6;
        new ThreadLocal<>();
        zzik = threadLocal;
        new zza();
        zzil = zza2;
        new zzb();
        PREFER_REMOTE = versionPolicy;
        new zzc();
        PREFER_LOCAL = versionPolicy2;
        new zzd();
        PREFER_HIGHEST_OR_LOCAL_VERSION = versionPolicy3;
        new zze();
        PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = versionPolicy4;
        new zzf();
        PREFER_HIGHEST_OR_REMOTE_VERSION = versionPolicy5;
        new zzg();
        zzim = versionPolicy6;
    }
}
