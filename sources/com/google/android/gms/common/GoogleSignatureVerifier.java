package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.wrappers.Wrappers;
import javax.annotation.CheckReturnValue;

@ShowFirstParty
@CheckReturnValue
@KeepForSdk
public class GoogleSignatureVerifier {
    private static GoogleSignatureVerifier zzam;
    private final Context mContext;
    private volatile String zzan;

    private GoogleSignatureVerifier(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @KeepForSdk
    public static GoogleSignatureVerifier getInstance(Context context) {
        GoogleSignatureVerifier googleSignatureVerifier;
        Context context2 = context;
        Object checkNotNull = Preconditions.checkNotNull(context2);
        Class<GoogleSignatureVerifier> cls = GoogleSignatureVerifier.class;
        Class<GoogleSignatureVerifier> cls2 = cls;
        synchronized (cls) {
            try {
                if (zzam == null) {
                    zzc.zza(context2);
                    new GoogleSignatureVerifier(context2);
                    zzam = googleSignatureVerifier;
                }
                return zzam;
            } catch (Throwable th) {
                while (true) {
                    Throwable th2 = th;
                    Class<GoogleSignatureVerifier> cls3 = cls2;
                    throw th2;
                }
            }
        }
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isUidGoogleSigned(int i) {
        zzm zzm;
        int i2 = i;
        String[] packagesForUid = Wrappers.packageManager(this.mContext).getPackagesForUid(i2);
        String[] strArr = packagesForUid;
        if (packagesForUid == null || strArr.length == 0) {
            zzm = zzm.zzb("no pkgs");
        } else {
            zzm zzm2 = null;
            String[] strArr2 = strArr;
            String[] strArr3 = strArr2;
            int length = strArr2.length;
            for (int i3 = 0; i3 < length; i3++) {
                zzm zza = zza(strArr3[i3], i2);
                zzm2 = zza;
                if (zza.zzad) {
                    break;
                }
            }
            zzm = zzm2;
        }
        zzm zzm3 = zzm;
        zzm3.zzf();
        return zzm3.zzad;
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isPackageGoogleSigned(String str) {
        zzm zzc = zzc(str);
        zzc.zzf();
        return zzc.zzad;
    }

    public static boolean zza(PackageInfo packageInfo, boolean z) {
        zze zza;
        PackageInfo packageInfo2 = packageInfo;
        boolean z2 = z;
        if (!(packageInfo2 == null || packageInfo2.signatures == null)) {
            if (z2) {
                zza = zza(packageInfo2, zzh.zzx);
            } else {
                zza = zza(packageInfo2, zzh.zzx[0]);
            }
            if (zza != null) {
                return true;
            }
        }
        return false;
    }

    @KeepForSdk
    public boolean isGooglePublicSignedPackage(PackageInfo packageInfo) {
        PackageInfo packageInfo2 = packageInfo;
        if (packageInfo2 == null) {
            return false;
        }
        if (zza(packageInfo2, false)) {
            return true;
        }
        if (zza(packageInfo2, true)) {
            if (GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext)) {
                return true;
            }
            int w = Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
        }
        return false;
    }

    private final zzm zza(String str, int i) {
        String str2;
        String str3;
        zze zze;
        String str4 = str;
        try {
            PackageInfo zza = Wrappers.packageManager(this.mContext).zza(str4, 64, i);
            PackageInfo packageInfo = zza;
            boolean honorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext);
            PackageInfo packageInfo2 = zza;
            PackageInfo packageInfo3 = packageInfo2;
            if (packageInfo2 == null) {
                return zzm.zzb("null pkg");
            }
            if (packageInfo3.signatures.length != 1) {
                return zzm.zzb("single cert required");
            }
            new zzf(packageInfo3.signatures[0].toByteArray());
            zze zze2 = zze;
            String str5 = packageInfo3.packageName;
            String str6 = str5;
            zzm zza2 = zzc.zza(str5, zze2, honorsDebugCertificates, false);
            zzm zzm = zza2;
            if (!zza2.zzad || packageInfo3.applicationInfo == null || (packageInfo3.applicationInfo.flags & 2) == 0 || !zzc.zza(str6, zze2, false, true).zzad) {
                return zzm;
            }
            return zzm.zzb("debuggable release cert app rejected");
        } catch (PackageManager.NameNotFoundException e) {
            String valueOf = String.valueOf(str4);
            String str7 = valueOf;
            if (valueOf.length() != 0) {
                str3 = "no pkg ".concat(str7);
            } else {
                str3 = str2;
                new String("no pkg ");
            }
            return zzm.zzb(str3);
        }
    }

    private final zzm zzc(String str) {
        String str2;
        String str3;
        zze zze;
        zzm zzm;
        String str4 = str;
        if (str4 == null) {
            return zzm.zzb("null pkg");
        }
        if (str4.equals(this.zzan)) {
            return zzm.zze();
        }
        try {
            PackageInfo packageInfo = Wrappers.packageManager(this.mContext).getPackageInfo(str4, 64);
            PackageInfo packageInfo2 = packageInfo;
            boolean honorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext);
            PackageInfo packageInfo3 = packageInfo;
            PackageInfo packageInfo4 = packageInfo3;
            if (packageInfo3 == null) {
                zzm = zzm.zzb("null pkg");
            } else if (packageInfo4.signatures.length != 1) {
                zzm = zzm.zzb("single cert required");
            } else {
                new zzf(packageInfo4.signatures[0].toByteArray());
                zze zze2 = zze;
                String str5 = packageInfo4.packageName;
                String str6 = str5;
                zzm zza = zzc.zza(str5, zze2, honorsDebugCertificates, false);
                zzm zzm2 = zza;
                if (!zza.zzad || packageInfo4.applicationInfo == null || (packageInfo4.applicationInfo.flags & 2) == 0 || !zzc.zza(str6, zze2, false, true).zzad) {
                    zzm = zzm2;
                } else {
                    zzm = zzm.zzb("debuggable release cert app rejected");
                }
            }
            zzm zzm3 = zzm;
            zzm zzm4 = zzm3;
            if (zzm3.zzad) {
                this.zzan = str4;
            }
            return zzm4;
        } catch (PackageManager.NameNotFoundException e) {
            String valueOf = String.valueOf(str4);
            String str7 = valueOf;
            if (valueOf.length() != 0) {
                str3 = "no pkg ".concat(str7);
            } else {
                str3 = str2;
                new String("no pkg ");
            }
            return zzm.zzb(str3);
        }
    }

    private static zze zza(PackageInfo packageInfo, zze... zzeArr) {
        Object obj;
        PackageInfo packageInfo2 = packageInfo;
        zze[] zzeArr2 = zzeArr;
        if (packageInfo2.signatures == null) {
            return null;
        }
        if (packageInfo2.signatures.length != 1) {
            int w = Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        new zzf(packageInfo2.signatures[0].toByteArray());
        Object obj2 = obj;
        for (int i = 0; i < zzeArr2.length; i++) {
            if (zzeArr2[i].equals(obj2)) {
                return zzeArr2[i];
            }
        }
        return null;
    }
}
