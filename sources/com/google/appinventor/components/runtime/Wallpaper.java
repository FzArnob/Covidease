package com.google.appinventor.components.runtime;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.MediaUtil;

@DesignerComponent(category = ComponentCategory.UTILITIES, description = "", iconName = "images/wallpaper.png", nonVisible = true, version = 2)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.WRITE_SETTINGS, android.permission.SET_WALLPAPER, android.permission.READ_EXTERNAL_STORAGE, android.permission.WRITE_EXTERNAL_STORAGE")
public class Wallpaper extends AndroidNonvisibleComponent implements Component {
    private ComponentContainer container;
    private Context context;
    private Form form;
    private WallpaperManager hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private String xII67GZG6kyH0lnPetzrSf3qVoLN1hwzGNETaRx4syTWFg4cA6mAvJ4Fkw0XSWhv = "wallpaperPicture.png";

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Wallpaper(com.google.appinventor.components.runtime.ComponentContainer r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            java.lang.String r3 = "wallpaperPicture.png"
            r2.xII67GZG6kyH0lnPetzrSf3qVoLN1hwzGNETaRx4syTWFg4cA6mAvJ4Fkw0XSWhv = r3
            r2 = r0
            r3 = r1
            r2.container = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.form = r3
            r2 = r0
            r4 = r2
            r2 = r4
            r3 = r4
            android.content.Context r3 = r3.context
            android.app.WallpaperManager r3 = android.app.WallpaperManager.getInstance(r3)
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            java.lang.String r2 = "Wallpaper"
            java.lang.String r3 = "Wallpaper Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Wallpaper.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SuppressLint({"MissingPermission"})
    @SimpleFunction(description = "Remove any currently set system wallpaper, reverting to the system's built-in wallpaper.")
    public void Clear() {
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.clear();
            WallpaperCleared();
            int i = Log.i("Wallpaper", "Wallpaper cleared");
        } catch (Exception e) {
            int e2 = Log.e("Wallpaper", String.valueOf(e));
        }
    }

    @SimpleEvent(description = "Event to detect that the user has cleared/deleted the wallpaper.")
    public void WallpaperCleared() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "WallpaperCleared", new Object[0]);
    }

    @SimpleFunction(description = "Change the current system wallpaper.")
    public void SetWallpaper(String str) {
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, false);
    }

    @SimpleFunction(description = "Change the current lock screen wallpaper. This block works only on devices with Android 7+.")
    public void SetLockScreenWallpaper(String str) {
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, true);
    }

    private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, boolean z) {
        C1085b bVar;
        String str2 = str;
        boolean z2 = z;
        if (str2 != null && !str2.isEmpty()) {
            try {
                new C1085b(this, z2);
                AsyncTask execute = bVar.execute(new String[]{str2});
            } catch (Exception e) {
                int e2 = Log.e("Wallpaper", String.valueOf(e));
            }
        }
    }

    @SuppressLint({"StaticFieldLeak"})
    /* renamed from: com.google.appinventor.components.runtime.Wallpaper$b */
    class C1085b extends AsyncTask<String, String, String> {
        private boolean HD0pINq0k1DLtf3yXVbK8xQhU4iGPT4iryXVeTceizjENHUUCtIIAr11wB5gmj8l = false;
        private /* synthetic */ Wallpaper hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            if (((String) obj).equals("true")) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.WallpaperChanged(true);
            } else {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.WallpaperChanged(false);
            }
        }

        C1085b(Wallpaper wallpaper, boolean z) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = wallpaper;
            this.HD0pINq0k1DLtf3yXVbK8xQhU4iGPT4iryXVeTceizjENHUUCtIIAr11wB5gmj8l = z;
        }

        /* access modifiers changed from: private */
        @SuppressLint({"MissingPermission"})
        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME */
        public String doInBackground(String... strArr) {
            try {
                Bitmap bitmap = MediaUtil.getBitmapDrawable(Wallpaper.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME), strArr[0]).getBitmap();
                if (!this.HD0pINq0k1DLtf3yXVbK8xQhU4iGPT4iryXVeTceizjENHUUCtIIAr11wB5gmj8l) {
                    Wallpaper.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setBitmap(bitmap);
                } else if (Build.VERSION.SDK_INT < 24) {
                    return "false";
                } else {
                    int bitmap2 = Wallpaper.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setBitmap(bitmap, (Rect) null, false, 2);
                }
                int i = Log.i("Wallpaper", "Wallpaper changed successful");
                return "true";
            } catch (Exception e) {
                int e2 = Log.e("Wallpaper", String.valueOf(e));
                return "false";
            }
        }
    }

    @SimpleEvent(description = "Event to detect that the user has changed the wallpaper. This event will be invoked by the \"Set Wallpaper\" function.")
    public void WallpaperChanged(boolean z) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "WallpaperChanged", Boolean.valueOf(z));
    }

    @SimpleProperty(description = "Returns the desired minimum height for the wallpaper.")
    public int DesiredMinimumHeight() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getDesiredMinimumHeight();
    }

    @SimpleProperty(description = "Returns the desired minimum width for the wallpaper.")
    public int DesiredMinimumWidth() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getDesiredMinimumWidth();
    }

    @SimpleProperty(description = "Returns whether the calling package is allowed to set the wallpaper for the calling user. This block works only on devices with Android 7+.")
    public boolean IsSetWallpaperAllowed() {
        if (Build.VERSION.SDK_INT >= 24) {
            return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isSetWallpaperAllowed();
        }
        return false;
    }

    @SimpleProperty(description = "Returns whether wallpapers are supported for the calling user. This block works only on devices with Android 6+.")
    public boolean IsWallpaperSupported() {
        if (Build.VERSION.SDK_INT >= 23) {
            return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isWallpaperSupported();
        }
        return false;
    }

    @SuppressLint({"MissingPermission"})
    @SimpleFunction(description = "Reset all wallpaper to the factory default. This block works only on devices with Android 9+.")
    public void ClearWallpaper() {
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.clearWallpaper();
                WallpaperCleared();
                int i = Log.i("Wallpaper", "Wallpaper cleared");
            }
        } catch (Exception e) {
            int e2 = Log.e("Wallpaper", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Retrieve the current system wallpaper; if no wallpaper is set, the system built-in static wallpaper is returned.")
    public void GetWallpaper() {
        PermissionResultHandler permissionResultHandler;
        new PermissionResultHandler(this) {
            private /* synthetic */ Wallpaper hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void HandlePermissionResponse(String str, boolean z) {
                C1084a aVar;
                String str2 = str;
                if (z) {
                    new C1084a(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, (byte) 0);
                    AsyncTask execute = aVar.execute(new String[]{""});
                    return;
                }
                Wallpaper.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "GetWallpaper", str2);
            }
        };
        this.form.askPermission("android.permission.READ_EXTERNAL_STORAGE", permissionResultHandler);
    }

    @SimpleEvent(description = "Event to detect that the component got the current system wallpaper.")
    public void GotWallpaper(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotWallpaper", str);
    }

    @SuppressLint({"StaticFieldLeak"})
    /* renamed from: com.google.appinventor.components.runtime.Wallpaper$a */
    class C1084a extends AsyncTask<String, String, String> {
        private /* synthetic */ Wallpaper hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            Object[] objArr2 = objArr;
            return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.SaveUtil(Wallpaper.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).getDrawable());
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotWallpaper((String) obj);
        }

        private C1084a(Wallpaper wallpaper) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = wallpaper;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C1084a(Wallpaper wallpaper, byte b) {
            this(wallpaper);
            byte b2 = b;
        }
    }

    @DesignerProperty(defaultValue = "wallpaperPicture.png", editorType = "string")
    @SimpleProperty(description = "After the user clicked on \"Get Wallpaper\" this will be the name for the wallpaper picture.")
    public void SaveWallpaperAs(String str) {
        StringBuilder sb;
        String str2 = str;
        if (str2 == null || str2.isEmpty()) {
            this.xII67GZG6kyH0lnPetzrSf3qVoLN1hwzGNETaRx4syTWFg4cA6mAvJ4Fkw0XSWhv = "wallpaperPicture.png";
        } else {
            this.xII67GZG6kyH0lnPetzrSf3qVoLN1hwzGNETaRx4syTWFg4cA6mAvJ4Fkw0XSWhv = str2;
        }
        new StringBuilder("Wallpaper save name is: ");
        int i = Log.i("Wallpaper", sb.append(this.xII67GZG6kyH0lnPetzrSf3qVoLN1hwzGNETaRx4syTWFg4cA6mAvJ4Fkw0XSWhv).toString());
    }

    /* JADX WARNING: type inference failed for: r5v18, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String SaveUtil(android.graphics.drawable.Drawable r10) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r4 = 0
            r2 = r4
            r4 = r1
            android.graphics.drawable.BitmapDrawable r4 = (android.graphics.drawable.BitmapDrawable) r4     // Catch:{ PermissionException -> 0x0086, FileNotFoundException -> 0x00b2, Exception -> 0x00db }
            android.graphics.Bitmap r4 = r4.getBitmap()     // Catch:{ PermissionException -> 0x0086, FileNotFoundException -> 0x00b2, Exception -> 0x00db }
            r1 = r4
            java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream     // Catch:{ PermissionException -> 0x0086, FileNotFoundException -> 0x00b2, Exception -> 0x00db }
            r8 = r4
            r4 = r8
            r5 = r8
            r5.<init>()     // Catch:{ PermissionException -> 0x0086, FileNotFoundException -> 0x00b2, Exception -> 0x00db }
            r3 = r4
            r4 = r1
            android.graphics.Bitmap$CompressFormat r5 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ PermissionException -> 0x0086, FileNotFoundException -> 0x00b2, Exception -> 0x00db }
            r6 = 0
            r7 = r3
            boolean r4 = r4.compress(r5, r6, r7)     // Catch:{ PermissionException -> 0x0086, FileNotFoundException -> 0x00b2, Exception -> 0x00db }
            java.io.File r4 = new java.io.File     // Catch:{ PermissionException -> 0x0086, FileNotFoundException -> 0x00b2, Exception -> 0x00db }
            r8 = r4
            r4 = r8
            r5 = r8
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ PermissionException -> 0x0086, FileNotFoundException -> 0x00b2, Exception -> 0x00db }
            r8 = r6
            r6 = r8
            r7 = r8
            r7.<init>()     // Catch:{ PermissionException -> 0x0086, FileNotFoundException -> 0x00b2, Exception -> 0x00db }
            java.io.File r7 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ PermissionException -> 0x0086, FileNotFoundException -> 0x00b2, Exception -> 0x00db }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ PermissionException -> 0x0086, FileNotFoundException -> 0x00b2, Exception -> 0x00db }
            java.lang.String r7 = "/"
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ PermissionException -> 0x0086, FileNotFoundException -> 0x00b2, Exception -> 0x00db }
            r7 = r0
            java.lang.String r7 = r7.xII67GZG6kyH0lnPetzrSf3qVoLN1hwzGNETaRx4syTWFg4cA6mAvJ4Fkw0XSWhv     // Catch:{ PermissionException -> 0x0086, FileNotFoundException -> 0x00b2, Exception -> 0x00db }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ PermissionException -> 0x0086, FileNotFoundException -> 0x00b2, Exception -> 0x00db }
            java.lang.String r6 = r6.toString()     // Catch:{ PermissionException -> 0x0086, FileNotFoundException -> 0x00b2, Exception -> 0x00db }
            r5.<init>(r6)     // Catch:{ PermissionException -> 0x0086, FileNotFoundException -> 0x00b2, Exception -> 0x00db }
            r1 = r4
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ PermissionException -> 0x0086, FileNotFoundException -> 0x00b2, Exception -> 0x00db }
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r1
            r5.<init>(r6)     // Catch:{ PermissionException -> 0x0086, FileNotFoundException -> 0x00b2, Exception -> 0x00db }
            r8 = r4
            r4 = r8
            r5 = r8
            r2 = r5
            r5 = r3
            byte[] r5 = r5.toByteArray()     // Catch:{ PermissionException -> 0x0086, FileNotFoundException -> 0x00b2, Exception -> 0x00db }
            r4.write(r5)     // Catch:{ PermissionException -> 0x0086, FileNotFoundException -> 0x00b2, Exception -> 0x00db }
            r4 = r2
            r4.flush()     // Catch:{ PermissionException -> 0x0086, FileNotFoundException -> 0x00b2, Exception -> 0x00db }
            r4 = r2
            r4.close()     // Catch:{ PermissionException -> 0x0086, FileNotFoundException -> 0x00b2, Exception -> 0x00db }
            r4 = r1
            java.lang.String r4 = r4.getAbsolutePath()     // Catch:{ PermissionException -> 0x0086, FileNotFoundException -> 0x00b2, Exception -> 0x00db }
            r1 = r4
            r4 = r2
            r4.flush()     // Catch:{ Exception -> 0x0077 }
            r4 = r2
            r4.close()     // Catch:{ Exception -> 0x0077 }
        L_0x0074:
            r4 = r1
            r0 = r4
        L_0x0076:
            return r0
        L_0x0077:
            r4 = move-exception
            r2 = r4
            java.lang.String r4 = "Wallpaper"
            r5 = r2
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r4 = android.util.Log.e(r4, r5)
            goto L_0x0074
        L_0x0086:
            r4 = move-exception
            r1 = r4
            r4 = r0
            com.google.appinventor.components.runtime.Form r4 = r4.form     // Catch:{ all -> 0x0104 }
            r5 = r0
            java.lang.String r6 = "GetWallpaper"
            r7 = r1
            r4.dispatchPermissionDeniedEvent((com.google.appinventor.components.runtime.Component) r5, (java.lang.String) r6, (com.google.appinventor.components.runtime.errors.PermissionException) r7)     // Catch:{ all -> 0x0104 }
            r4 = r2
            if (r4 == 0) goto L_0x009e
            r4 = r2
            r4.flush()     // Catch:{ Exception -> 0x00a3 }
            r4 = r2
            r4.close()     // Catch:{ Exception -> 0x00a3 }
        L_0x009e:
            java.lang.String r4 = "ERROR"
            r0 = r4
            goto L_0x0076
        L_0x00a3:
            r4 = move-exception
            r1 = r4
            java.lang.String r4 = "Wallpaper"
            r5 = r1
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r4 = android.util.Log.e(r4, r5)
            goto L_0x009e
        L_0x00b2:
            r4 = move-exception
            r1 = r4
            java.lang.String r4 = "Wallpaper"
            r5 = r1
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x0104 }
            int r4 = android.util.Log.e(r4, r5)     // Catch:{ all -> 0x0104 }
            r4 = r2
            if (r4 == 0) goto L_0x009e
            r4 = r2
            r4.flush()     // Catch:{ Exception -> 0x00cc }
            r4 = r2
            r4.close()     // Catch:{ Exception -> 0x00cc }
            goto L_0x009e
        L_0x00cc:
            r4 = move-exception
            r1 = r4
            java.lang.String r4 = "Wallpaper"
            r5 = r1
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r4 = android.util.Log.e(r4, r5)
            goto L_0x009e
        L_0x00db:
            r4 = move-exception
            r1 = r4
            java.lang.String r4 = "Wallpaper"
            r5 = r1
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x0104 }
            int r4 = android.util.Log.e(r4, r5)     // Catch:{ all -> 0x0104 }
            r4 = r2
            if (r4 == 0) goto L_0x009e
            r4 = r2
            r4.flush()     // Catch:{ Exception -> 0x00f5 }
            r4 = r2
            r4.close()     // Catch:{ Exception -> 0x00f5 }
            goto L_0x009e
        L_0x00f5:
            r4 = move-exception
            r1 = r4
            java.lang.String r4 = "Wallpaper"
            r5 = r1
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r4 = android.util.Log.e(r4, r5)
            goto L_0x009e
        L_0x0104:
            r4 = move-exception
            r1 = r4
            r4 = r2
            if (r4 == 0) goto L_0x0111
            r4 = r2
            r4.flush()     // Catch:{ Exception -> 0x0113 }
            r4 = r2
            r4.close()     // Catch:{ Exception -> 0x0113 }
        L_0x0111:
            r4 = r1
            throw r4
        L_0x0113:
            r4 = move-exception
            r2 = r4
            java.lang.String r4 = "Wallpaper"
            r5 = r2
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r4 = android.util.Log.e(r4, r5)
            goto L_0x0111
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Wallpaper.SaveUtil(android.graphics.drawable.Drawable):java.lang.String");
    }
}
