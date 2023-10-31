package com.google.appinventor.components.runtime;

import android.content.pm.PackageManager;
import android.support.p000v4.content.ContextCompat;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularHelper;

@SimpleObject
@DesignerComponent(category = ComponentCategory.UTILITIES, description = "", iconName = "images/package.png", nonVisible = true, version = 3)
public class Package extends AndroidNonvisibleComponent implements Component {
    private ComponentContainer container;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Package(com.google.appinventor.components.runtime.ComponentContainer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            r2.container = r3
            java.lang.String r2 = "Package"
            java.lang.String r3 = "Package Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Package.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleProperty(description = "This block will returns the version name of the current running app.")
    public String VersionName() {
        try {
            return this.container.$form().getPackageManager().getPackageInfo(this.container.$form().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "0";
        }
    }

    @SimpleProperty(description = "This block will returns the version code of the current running app.")
    public int VersionCode() {
        try {
            return this.container.$form().getPackageManager().getPackageInfo(this.container.$form().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
    }

    @SimpleFunction(description = "This block will returns the version name of the package name. Returns 'Package not found' if the package is not installed.")
    public String VersionNameFrom(String str) {
        try {
            return this.container.$form().getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "Package not found";
        }
    }

    @SimpleFunction(description = "This block will returns the version code of the package name. Returns '-1' if the package is not installed.")
    public int VersionCodeFrom(String str) {
        try {
            return this.container.$form().getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return -1;
        }
    }

    @SimpleFunction(description = "Check whether a particular package has been granted a particular permission.")
    public boolean isPermissionGranted(String str, String str2) {
        String str3 = str;
        String str4 = str2;
        String str5 = str3;
        if (!str3.contains(".")) {
            str5 = "android.permission.".concat(String.valueOf(str3));
        }
        return ContextCompat.checkSelfPermission(this.container.$form(), str5) == 0;
    }

    @SimpleFunction(description = "Try to show the application icon of the given package name. If the application cannot be found, \"Package not found\" is the output.")
    public String GetPackageIcon(String str) {
        String str2 = str;
        try {
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularHelper.getPackageIcon(this.container.$form().getPackageManager().getApplicationIcon(str2)), AppNameFrom(str2));
        } catch (PackageManager.NameNotFoundException e) {
            return "Package not found";
        }
    }

    /* JADX WARNING: type inference failed for: r7v14, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(android.graphics.Bitmap r12, java.lang.String r13) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r6 = 0
            r3 = r6
            java.io.ByteArrayOutputStream r6 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x00cc }
            r10 = r6
            r6 = r10
            r7 = r10
            r7.<init>()     // Catch:{ Exception -> 0x00cc }
            r4 = r6
            r6 = r1
            android.graphics.Bitmap$CompressFormat r7 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ Exception -> 0x00cc }
            r8 = 0
            r9 = r4
            boolean r6 = r6.compress(r7, r8, r9)     // Catch:{ Exception -> 0x00cc }
            java.io.File r6 = new java.io.File     // Catch:{ Exception -> 0x00cc }
            r10 = r6
            r6 = r10
            r7 = r10
            r8 = r0
            com.google.appinventor.components.runtime.ComponentContainer r8 = r8.container     // Catch:{ Exception -> 0x00cc }
            com.google.appinventor.components.runtime.Form r8 = r8.$form()     // Catch:{ Exception -> 0x00cc }
            java.io.File r8 = r8.getFilesDir()     // Catch:{ Exception -> 0x00cc }
            java.lang.String r9 = "/Kodular-App-Icons"
            r7.<init>(r8, r9)     // Catch:{ Exception -> 0x00cc }
            r10 = r6
            r6 = r10
            r7 = r10
            r5 = r7
            boolean r6 = r6.exists()     // Catch:{ Exception -> 0x00cc }
            if (r6 != 0) goto L_0x003c
            r6 = r5
            boolean r6 = r6.mkdir()     // Catch:{ Exception -> 0x00cc }
        L_0x003c:
            java.io.File r6 = new java.io.File     // Catch:{ Exception -> 0x00cc }
            r10 = r6
            r6 = r10
            r7 = r10
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00cc }
            r10 = r8
            r8 = r10
            r9 = r10
            r9.<init>()     // Catch:{ Exception -> 0x00cc }
            r9 = r5
            java.lang.String r9 = r9.getPath()     // Catch:{ Exception -> 0x00cc }
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x00cc }
            java.lang.String r9 = java.io.File.separator     // Catch:{ Exception -> 0x00cc }
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x00cc }
            java.lang.String r9 = "."
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x00cc }
            r9 = r2
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x00cc }
            java.lang.String r9 = ".png"
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x00cc }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x00cc }
            r7.<init>(r8)     // Catch:{ Exception -> 0x00cc }
            r10 = r6
            r6 = r10
            r7 = r10
            r2 = r7
            boolean r6 = r6.exists()     // Catch:{ Exception -> 0x00cc }
            if (r6 != 0) goto L_0x0081
            r6 = r2
            boolean r6 = r6.createNewFile()     // Catch:{ Exception -> 0x00cc }
        L_0x0081:
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x00cc }
            r10 = r6
            r6 = r10
            r7 = r10
            r8 = r2
            r7.<init>(r8)     // Catch:{ Exception -> 0x00cc }
            r10 = r6
            r6 = r10
            r7 = r10
            r3 = r7
            r7 = r4
            byte[] r7 = r7.toByteArray()     // Catch:{ Exception -> 0x00cc }
            r6.write(r7)     // Catch:{ Exception -> 0x00cc }
            r6 = r3
            r6.flush()     // Catch:{ Exception -> 0x00cc }
            r6 = r3
            r6.close()     // Catch:{ Exception -> 0x00cc }
            r6 = r2
            java.lang.String r6 = r6.getAbsolutePath()     // Catch:{ Exception -> 0x00cc }
            r2 = r6
            r6 = r3
            r6.flush()     // Catch:{ Exception -> 0x00bd }
            r6 = r3
            r6.close()     // Catch:{ Exception -> 0x00bd }
        L_0x00ac:
            r6 = r1
            if (r6 == 0) goto L_0x00ba
            r6 = r1
            boolean r6 = r6.isRecycled()
            if (r6 != 0) goto L_0x00ba
            r6 = r1
            r6.recycle()
        L_0x00ba:
            r6 = r2
            r0 = r6
        L_0x00bc:
            return r0
        L_0x00bd:
            r6 = move-exception
            r3 = r6
            java.lang.String r6 = "Package"
            r7 = r3
            java.lang.String r7 = java.lang.String.valueOf(r7)
            int r6 = android.util.Log.e(r6, r7)
            goto L_0x00ac
        L_0x00cc:
            r6 = move-exception
            r4 = r6
            java.lang.String r6 = "Package"
            r7 = r4
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ all -> 0x010b }
            int r6 = android.util.Log.e(r6, r7)     // Catch:{ all -> 0x010b }
            r6 = r4
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x010b }
            r5 = r6
            r6 = r3
            if (r6 == 0) goto L_0x00eb
            r6 = r3
            r6.flush()     // Catch:{ Exception -> 0x00fc }
            r6 = r3
            r6.close()     // Catch:{ Exception -> 0x00fc }
        L_0x00eb:
            r6 = r1
            if (r6 == 0) goto L_0x00f9
            r6 = r1
            boolean r6 = r6.isRecycled()
            if (r6 != 0) goto L_0x00f9
            r6 = r1
            r6.recycle()
        L_0x00f9:
            r6 = r5
            r0 = r6
            goto L_0x00bc
        L_0x00fc:
            r6 = move-exception
            r2 = r6
            java.lang.String r6 = "Package"
            r7 = r2
            java.lang.String r7 = java.lang.String.valueOf(r7)
            int r6 = android.util.Log.e(r6, r7)
            goto L_0x00eb
        L_0x010b:
            r6 = move-exception
            r2 = r6
            r6 = r3
            if (r6 == 0) goto L_0x0118
            r6 = r3
            r6.flush()     // Catch:{ Exception -> 0x0128 }
            r6 = r3
            r6.close()     // Catch:{ Exception -> 0x0128 }
        L_0x0118:
            r6 = r1
            if (r6 == 0) goto L_0x0126
            r6 = r1
            boolean r6 = r6.isRecycled()
            if (r6 != 0) goto L_0x0126
            r6 = r1
            r6.recycle()
        L_0x0126:
            r6 = r2
            throw r6
        L_0x0128:
            r6 = move-exception
            r3 = r6
            java.lang.String r6 = "Package"
            r7 = r3
            java.lang.String r7 = java.lang.String.valueOf(r7)
            int r6 = android.util.Log.e(r6, r7)
            goto L_0x0118
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Package.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(android.graphics.Bitmap, java.lang.String):java.lang.String");
    }

    @SimpleProperty(description = "Returns the name from the current running app.")
    public String AppName() {
        PackageManager packageManager = this.container.$form().getPackageManager();
        try {
            return (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.container.$form().getPackageName(), 0));
        } catch (PackageManager.NameNotFoundException e) {
            return "not found";
        }
    }

    @SimpleFunction(description = "Returns the name from the given package name.")
    public String AppNameFrom(String str) {
        PackageManager packageManager = this.container.$form().getPackageManager();
        try {
            return (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 128));
        } catch (PackageManager.NameNotFoundException e) {
            return "Package not found";
        }
    }

    @SimpleProperty(description = "Returns the package name from the current running app.")
    public String PackageName() {
        return this.container.$form().getPackageName();
    }

    @SimpleFunction(description = "Returns true if a package (app) is installed and enabled.")
    public boolean IsPackageInstalled(String str) {
        try {
            return this.container.$form().getPackageManager().getApplicationInfo(str, 0).enabled;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
