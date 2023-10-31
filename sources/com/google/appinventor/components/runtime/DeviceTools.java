package com.google.appinventor.components.runtime;

import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.support.p000v4.media.session.PlaybackStateCompat;
import android.support.p000v4.p002os.EnvironmentCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.DeviceStorage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.UTILITIES, description = "", iconName = "images/devicetools.png", nonVisible = true, version = 5)
@UsesPermissions(permissionNames = "android.permission.READ_PHONE_STATE")
public class DeviceTools extends AndroidNonvisibleComponent implements Component {
    private boolean YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u = false;
    private Context context;
    private final long hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
    private int yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DeviceTools(com.google.appinventor.components.runtime.ComponentContainer r7) {
        /*
            r6 = this;
            r1 = r6
            r2 = r7
            r3 = r1
            r4 = r2
            com.google.appinventor.components.runtime.Form r4 = r4.$form()
            r3.<init>(r4)
            r3 = r1
            r4 = 0
            r3.YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u = r4
            r3 = r1
            r4 = 1048576(0x100000, double:5.180654E-318)
            r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r4
            r3 = r1
            r4 = r2
            android.app.Activity r4 = r4.$context()
            r3.context = r4
            java.lang.String r3 = "DeviceTools"
            java.lang.String r4 = "Device Tools Created"
            int r3 = android.util.Log.d(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.DeviceTools.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleProperty(description = "The result is the code of your country.")
    public String CountryCode() {
        return this.context.getResources().getConfiguration().locale.getCountry();
    }

    @SimpleProperty(description = "The result is the code of your device language.")
    public String LanguageCode() {
        return this.context.getResources().getConfiguration().locale.getLanguage();
    }

    @SimpleProperty(description = "Get the android version of device.")
    public String AndroidVersion() {
        return Build.VERSION.RELEASE;
    }

    @SimpleProperty(description = "The user-visible SDK version of the framework.")
    public int ApiVersion() {
        return Build.VERSION.SDK_INT;
    }

    @SimpleProperty(description = "The name of the underlying board, like \"goldfish\".")
    public String Board() {
        return Build.BOARD;
    }

    @SimpleProperty(description = "The system bootloader version number.")
    public String BootloaderVersion() {
        return Build.BOOTLOADER;
    }

    @SimpleProperty(description = "The consumer-visible brand with which the product/hardware will be associated, if any.")
    public String Brand() {
        return Build.BRAND;
    }

    @SimpleProperty(description = "The name of the industrial design.")
    public String DeviceName() {
        return Build.DEVICE;
    }

    @SimpleProperty(description = "Get the build number(Software) of the device.")
    public String BuildNumber() {
        return Build.DISPLAY;
    }

    @SimpleProperty(description = "A string that uniquely identifies this build.")
    public String Fingerprint() {
        return Build.FINGERPRINT;
    }

    @SimpleProperty(description = "The name of the hardware (from the kernel command line or /proc).")
    public String Hardware() {
        return Build.HARDWARE;
    }

    @SimpleProperty(description = "Either a changelist number, or a label like \"M4-rc20\".")
    /* renamed from: Id */
    public String mo10601Id() {
        return Build.ID;
    }

    @SimpleFunction(description = "Get the IMEI of the device. The result will be then at the 'Got IMEI' event.")
    public void GetIMEI() {
        Runnable runnable;
        new Runnable(this) {
            final /* synthetic */ DeviceTools hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void run() {
                PermissionResultHandler permissionResultHandler;
                new PermissionResultHandler(this) {
                    private /* synthetic */ C06281 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                    {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                    }

                    public final void HandlePermissionResponse(String str, boolean z) {
                        String str2 = str;
                        if (z) {
                            DeviceTools.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                        } else {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "GetIMEI", "android.permission.READ_PHONE_STATE");
                        }
                    }
                };
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.askPermission("android.permission.READ_PHONE_STATE", permissionResultHandler);
            }
        };
        this.form.runOnUiThread(runnable);
    }

    @SimpleEvent(description = "Event to get the IMEI after it was requested.")
    public void GotIMEI(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotIMEI", str);
    }

    @SimpleProperty(description = "The manufacturer of the product/hardware.")
    public String Manufacturer() {
        return Build.MANUFACTURER;
    }

    @SimpleProperty(description = "The end-user-visible name for the end product.")
    public String ModelName() {
        return Build.MODEL;
    }

    @SimpleProperty(description = "The name of the overall product.")
    public String Product() {
        return Build.PRODUCT;
    }

    @SimpleProperty(description = "Returns the version string for the radio firmware. May return null (if, for instance, the radio is not currently on).")
    public String RadioVersion() {
        return Build.getRadioVersion();
    }

    @Deprecated
    @SimpleProperty(description = "DEPRECATED. DO NOT USE THIS ANYMORE. USE 'Get Serial' INSTEAD!")
    public String Serial() {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                new StringBuilder();
                return sb4.append(Build.getSerial()).toString();
            }
            new StringBuilder();
            return sb3.append(Build.SERIAL).toString();
        } catch (PermissionException e) {
            PermissionException permissionException = e;
            this.form.dispatchPermissionDeniedEvent((Component) this, "Serial", permissionException);
            int e2 = Log.e("DeviceTools", String.valueOf(permissionException));
            new StringBuilder();
            return sb2.append(permissionException.getMessage()).toString();
        } catch (Exception e3) {
            Exception exc = e3;
            int e4 = Log.e("DeviceTools", String.valueOf(exc));
            new StringBuilder();
            return sb.append(exc.getMessage()).toString();
        }
    }

    @SimpleFunction(description = "A hardware serial number, if available. Alphanumeric only, case-insensitive. For apps targeting SDK higher than N_MR1 this field is set to UNKNOWN.")
    public void GetSerial() {
        Runnable runnable;
        new Runnable(this) {
            final /* synthetic */ DeviceTools hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void run() {
                PermissionResultHandler permissionResultHandler;
                new PermissionResultHandler(this) {
                    private /* synthetic */ C06302 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                    {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                    }

                    public final void HandlePermissionResponse(String str, boolean z) {
                        String str2 = str;
                        if (z) {
                            DeviceTools.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                        } else {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "GetSerial", "android.permission.READ_PHONE_STATE");
                        }
                    }
                };
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.askPermission("android.permission.READ_PHONE_STATE", permissionResultHandler);
            }
        };
        this.form.runOnUiThread(runnable);
    }

    @SimpleEvent(description = "Event to get the serial number after it was requested.")
    public void GotSerial(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotSerial", str);
    }

    @SimpleProperty(description = "Comma-separated tags describing the build, like \"unsigned,debug\".")
    public String Tags() {
        return Build.TAGS;
    }

    @SimpleProperty(description = "The type of build, like \"user\" or \"eng\".")
    public String Type() {
        return Build.TYPE;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void ShowSuccessToast(boolean z) {
        boolean z2 = z;
        this.YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns true if 'Show Success Toast' is enabled.")
    public boolean ShowSuccessToast() {
        return this.YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u;
    }

    @SimpleFunction(description = "Parse a text between two strings. Example: text = abcdef, start = a, end = d, result = bc. If there is a problem the 'if Text Not Found' will be returned.")
    public String Parse(String str, String str2, String str3, String str4) {
        String str5 = str;
        String str6 = str2;
        String str7 = str3;
        String str8 = str4;
        String str9 = str8.isEmpty() ? "text not found" : str8;
        String str10 = str5;
        try {
            String substring = str10.substring(str10.indexOf(str6) + str6.length(), str5.length());
            return substring.substring(0, substring.indexOf(str7));
        } catch (Exception e) {
            int e2 = Log.e("DeviceTools", String.valueOf(e));
            return str9;
        }
    }

    @SimpleFunction(description = "Copy text to clipboard. In case 'Show Success Toast' is true, the toast with your message will be shown after copying a text to the clipboard.")
    public void Copy(String str, String str2) {
        String str3 = str;
        String str4 = str2;
        try {
            ((ClipboardManager) this.context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Copied text", str3));
            int d = Log.d("DeviceTools", "Text copied: ".concat(String.valueOf(str3)));
            if (this.YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u) {
                Toast.makeText(this.context, str4, 0).show();
            }
        } catch (Exception e) {
            int e2 = Log.e("DeviceTools", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Paste text from clipboard. In case 'Show Success Toast' is true, the toast with your message will be shown after pasting a text from the clipboard.")
    public String Paste(String str) {
        String str2 = str;
        ClipboardManager clipboardManager = (ClipboardManager) this.context.getSystemService("clipboard");
        ClipData primaryClip = clipboardManager != null ? clipboardManager.getPrimaryClip() : null;
        ClipData clipData = primaryClip;
        if (primaryClip == null) {
            return "";
        }
        String charSequence = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(clipData.getItemAt(0)).toString();
        int d = Log.d("DeviceTools", "Text pasted: ".concat(String.valueOf(charSequence)));
        if (this.YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u) {
            Toast.makeText(this.context, str2, 0).show();
        }
        return charSequence;
    }

    private CharSequence hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(ClipData.Item item) {
        StringBuilder sb;
        StringBuilder sb2;
        InputStreamReader inputStreamReader;
        InputStreamReader inputStreamReader2;
        StringBuilder sb3;
        InputStreamReader inputStreamReader3;
        ClipData.Item item2 = item;
        CharSequence text = item2.getText();
        CharSequence charSequence = text;
        if (text != null) {
            return charSequence;
        }
        Uri uri = item2.getUri();
        Uri uri2 = uri;
        if (uri != null) {
            FileInputStream fileInputStream = null;
            try {
                AssetFileDescriptor openTypedAssetFileDescriptor = this.context.getContentResolver().openTypedAssetFileDescriptor(uri2, "text/*", (Bundle) null);
                AssetFileDescriptor assetFileDescriptor = openTypedAssetFileDescriptor;
                if (openTypedAssetFileDescriptor != null) {
                    fileInputStream = assetFileDescriptor.createInputStream();
                }
                if (Build.VERSION.SDK_INT >= 19) {
                    new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
                    inputStreamReader2 = inputStreamReader3;
                } else {
                    new InputStreamReader(fileInputStream, "UTF-8");
                    inputStreamReader2 = inputStreamReader;
                }
                new StringBuilder(128);
                StringBuilder sb4 = sb3;
                char[] cArr = new char[8192];
                while (true) {
                    int read = inputStreamReader2.read(cArr);
                    int i = read;
                    if (read <= 0) {
                        break;
                    }
                    StringBuilder append = sb4.append(cArr, 0, i);
                }
                String sb5 = sb4.toString();
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        int e2 = Log.e("DeviceTools", String.valueOf(e));
                    }
                }
                return sb5;
            } catch (FileNotFoundException e3) {
                FileNotFoundException fileNotFoundException = e3;
                new StringBuilder("Unable to open content URI as text, ignoring... ");
                int d = Log.d("DeviceTools", sb2.append(fileNotFoundException.getMessage()).toString(), fileNotFoundException);
                if (0 != 0) {
                    FileInputStream fileInputStream2 = null;
                    try {
                        fileInputStream2.close();
                    } catch (IOException e4) {
                        int e5 = Log.e("DeviceTools", String.valueOf(e4));
                    }
                }
                int d2 = Log.d("DeviceTools", "Couldn't open the URI as a stream, then the URI itself probably serves fairly well as a textual representation");
                return uri2.toString();
            } catch (IOException e6) {
                IOException iOException = e6;
                int w = Log.w("DeviceTools", "Failure loading text", iOException);
                new StringBuilder();
                String sb6 = sb.append(iOException.getMessage()).toString();
                if (0 != 0) {
                    FileInputStream fileInputStream3 = null;
                    try {
                        fileInputStream3.close();
                    } catch (IOException e7) {
                        int e8 = Log.e("DeviceTools", String.valueOf(e7));
                    }
                }
                return sb6;
            } catch (Throwable th) {
                Throwable th2 = th;
                if (0 != 0) {
                    FileInputStream fileInputStream4 = null;
                    try {
                        fileInputStream4.close();
                    } catch (IOException e9) {
                        int e10 = Log.e("DeviceTools", String.valueOf(e9));
                    }
                }
                throw th2;
            }
        } else {
            Intent intent = item2.getIntent();
            Intent intent2 = intent;
            if (intent == null) {
                return "";
            }
            int d3 = Log.d("DeviceTools", "all we have is an Intent, then we can just turn that into text");
            return intent2.toUri(1);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the unique device specific 'AndroidID'. Developed by Cian.")
    public String GetDeviceId() {
        return Settings.Secure.getString(this.context.getContentResolver(), "android_id");
    }

    @SimpleFunction(description = "This returns TRUE if ADB debugging is enabled, which could be a sign of hacking your app, or a compromised device. Developed by Cian.")
    public boolean isADBDebuggingEnabled() throws Exception {
        this.yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT = Settings.Secure.getInt(this.context.getContentResolver(), "adb_enabled");
        if (this.yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT > 0) {
            return true;
        }
        return false;
    }

    @SimpleFunction(description = "Returns TRUE if the device operating on an emulator. Developed by Cian.")
    public boolean isEmulator() {
        if (Build.BOARD.toLowerCase().contains("nox") || Build.BOOTLOADER.toLowerCase().contains("nox") || Build.BRAND.equalsIgnoreCase("generic") || Build.BRAND.equalsIgnoreCase("generic_x86") || Build.BRAND.equalsIgnoreCase("TTVM") || Build.BRAND.toLowerCase().contains("Andy") || Build.DEVICE.toLowerCase().contains("generic") || Build.DEVICE.toLowerCase().contains("generic_x86") || Build.DEVICE.toLowerCase().contains("Andy") || Build.DEVICE.toLowerCase().contains("ttVM_Hdragon") || Build.DEVICE.toLowerCase().contains("Droid4X") || Build.DEVICE.toLowerCase().contains("nox") || Build.DEVICE.toLowerCase().contains("generic_x86_64") || Build.DEVICE.toLowerCase().contains("vbox86p") || Build.FINGERPRINT.toLowerCase().contains("generic") || Build.FINGERPRINT.toLowerCase().contains("generic/sdk/generic") || Build.FINGERPRINT.toLowerCase().contains("generic_x86/sdk_x86/generic_x86") || Build.FINGERPRINT.toLowerCase().contains("Andy") || Build.FINGERPRINT.toLowerCase().contains("ttVM_Hdragon") || Build.FINGERPRINT.toLowerCase().contains("generic_x86_64") || Build.FINGERPRINT.toLowerCase().contains("generic/google_sdk/generic") || Build.FINGERPRINT.toLowerCase().contains("vbox86p") || Build.FINGERPRINT.toLowerCase().contains("generic/vbox86p/vbox86p") || Build.FINGERPRINT.startsWith(EnvironmentCompat.MEDIA_UNKNOWN) || Build.HARDWARE.equalsIgnoreCase("goldfish") || Build.HARDWARE.equalsIgnoreCase("vbox86") || Build.HARDWARE.toLowerCase().contains("nox") || Build.HARDWARE.toLowerCase().contains("ttVM_x86") || Build.MANUFACTURER.equalsIgnoreCase(EnvironmentCompat.MEDIA_UNKNOWN) || Build.MANUFACTURER.equalsIgnoreCase("Genymotion") || Build.MANUFACTURER.toLowerCase().contains("Andy") || Build.MANUFACTURER.toLowerCase().contains("MIT") || Build.MANUFACTURER.toLowerCase().contains("nox") || Build.MANUFACTURER.toLowerCase().contains("TiantianVM") || Build.MODEL.equalsIgnoreCase("sdk") || Build.MODEL.equalsIgnoreCase("google_sdk") || Build.MODEL.toLowerCase().contains("Droid4X") || Build.MODEL.toLowerCase().contains("TiantianVM") || Build.MODEL.toLowerCase().contains("Andy") || Build.MODEL.equalsIgnoreCase("Android SDK built for x86_64") || Build.MODEL.equalsIgnoreCase("Android SDK built for x86") || Build.MODEL.contains("Emulator") || Build.PRODUCT.toLowerCase().contains("sdk") || Build.PRODUCT.toLowerCase().contains("Andy") || Build.PRODUCT.toLowerCase().contains("ttVM_Hdragon") || Build.PRODUCT.toLowerCase().contains("google_sdk") || Build.PRODUCT.toLowerCase().contains("Droid4X") || Build.PRODUCT.toLowerCase().contains("nox") || Build.PRODUCT.toLowerCase().contains("sdk_x86") || Build.PRODUCT.toLowerCase().contains("sdk_google") || Build.PRODUCT.toLowerCase().contains("vbox86p") || Build.SERIAL.toLowerCase().contains("nox")) {
            return true;
        }
        return false;
    }

    @SimpleFunction(description = "Returns TRUE if the app was installed from Play Store. Developed by Cian.")
    public boolean isPlayStoreInstalled() {
        List list;
        List list2 = list;
        String[] strArr = new String[2];
        strArr[0] = "com.android.vending";
        String[] strArr2 = strArr;
        strArr2[1] = "com.google.android.feedback";
        new ArrayList(Arrays.asList(strArr2));
        List list3 = list2;
        String installerPackageName = this.context.getPackageManager().getInstallerPackageName(this.context.getPackageName());
        String str = installerPackageName;
        if (installerPackageName == null || !list3.contains(str)) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0019  */
    @com.google.appinventor.components.annotations.SimpleFunction(description = "Returns TRUE if one of 12 known patching or root emulating packages is installed. The name of the package is not returned, so the user does not know which package name to change. Developed by Cian.")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean CheckForDangerousAPK() {
        /*
            r6 = this;
            r0 = r6
            r3 = r0
            android.content.Context r3 = r3.context
            android.content.pm.PackageManager r3 = r3.getPackageManager()
            r4 = 0
            java.util.List r3 = r3.getInstalledApplications(r4)
            java.util.Iterator r3 = r3.iterator()
            r1 = r3
        L_0x0012:
            r3 = r1
            boolean r3 = r3.hasNext()
            if (r3 == 0) goto L_0x00b8
            r3 = r1
            java.lang.Object r3 = r3.next()
            android.content.pm.ApplicationInfo r3 = (android.content.pm.ApplicationInfo) r3
            r5 = r3
            r3 = r5
            r4 = r5
            r2 = r4
            java.lang.String r3 = r3.packageName
            java.lang.String r4 = "cc.madkite.freedom"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x00b3
            r3 = r2
            java.lang.String r3 = r3.packageName
            java.lang.String r4 = "devadvance.rootcloak"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x00b3
            r3 = r2
            java.lang.String r3 = r3.packageName
            java.lang.String r4 = ".robv.android.xposed.installer"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x00b3
            r3 = r2
            java.lang.String r3 = r3.packageName
            java.lang.String r4 = ".saurik.substrate"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x00b3
            r3 = r2
            java.lang.String r3 = r3.packageName
            java.lang.String r4 = ".devadvance.rootcloakplus"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x00b3
            r3 = r2
            java.lang.String r3 = r3.packageName
            java.lang.String r4 = ".zachspong.temprootremovejb"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x00b3
            r3 = r2
            java.lang.String r3 = r3.packageName
            java.lang.String r4 = ".amphoras.hidemyroot"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x00b3
            r3 = r2
            java.lang.String r3 = r3.packageName
            java.lang.String r4 = ".formyhm.hideroot"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x00b3
            r3 = r2
            java.lang.String r3 = r3.packageName
            java.lang.String r4 = ".koushikdutta.rommanager"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x00b3
            r3 = r2
            java.lang.String r3 = r3.packageName
            java.lang.String r4 = ".dimonvideo.luckypatcher"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x00b3
            r3 = r2
            java.lang.String r3 = r3.packageName
            java.lang.String r4 = ".chelpus.lackypatch"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x00b3
            r3 = r2
            java.lang.String r3 = r3.packageName
            java.lang.String r4 = ".ramdroid.appquarantine"
            boolean r3 = r3.contains(r4)
            if (r3 == 0) goto L_0x00b6
        L_0x00b3:
            r3 = 1
            r0 = r3
        L_0x00b5:
            return r0
        L_0x00b6:
            goto L_0x0012
        L_0x00b8:
            r3 = 0
            r0 = r3
            goto L_0x00b5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.DeviceTools.CheckForDangerousAPK():boolean");
    }

    @SimpleFunction(description = "Total RAM size in Gigabytes.")
    public float MemoryTotal() {
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(DeviceStorage.MEMORY_TOTAL);
    }

    @SimpleFunction(description = "Total free RAM size in Gigabytes.")
    public float MemoryFree() {
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(DeviceStorage.MEMORY_AVAILABLE);
    }

    @SimpleFunction(description = "Size of used-memory in Gigabytes.")
    public float MemoryUsed() {
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(DeviceStorage.MEMORY_USED);
    }

    @SimpleFunction(description = "Total external storage size in Gigabytes.")
    public float ExternalStorageTotal() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(DeviceStorage.EXTERNAL_STORAGE_TOTAL);
    }

    @SimpleFunction(description = "Available size of external storage in Gigabytes.")
    public float ExternalStorageAvailable() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(DeviceStorage.EXTERNAL_STORAGE_AVAILABLE);
    }

    @SimpleFunction(description = "Size of used-external-storage in Gigabytes.")
    public float ExternalStorageUsed() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(DeviceStorage.EXTERNAL_STORAGE_USED);
    }

    @SimpleFunction(description = "Total size of internal storage in Gigabytes.")
    public float InternalStorageTotal() {
        return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(DeviceStorage.INTERNAL_STORAGE_TOTAL);
    }

    @SimpleFunction(description = "Size of available internal storage in Gigabytes.")
    public float InternalStorageAvailable() {
        return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(DeviceStorage.INTERNAL_STORAGE_AVAILABLE);
    }

    @SimpleFunction(description = "Size of used-internal-storage in Gigabytes.")
    public float InternalStorageUsed() {
        return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(DeviceStorage.INTERNAL_STORAGE_USED);
    }

    private static float hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(DeviceStorage deviceStorage) {
        StatFs statFs;
        DeviceStorage deviceStorage2 = deviceStorage;
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return 0.0f;
        }
        new StatFs(Environment.getExternalStorageDirectory().getPath());
        StatFs statFs2 = statFs;
        StatFs statFs3 = statFs2;
        long blockSize = (long) statFs2.getBlockSize();
        long blockCount = (long) statFs3.getBlockCount();
        long availableBlocks = (long) statFs3.getAvailableBlocks();
        long j = (blockCount * blockSize) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        long j2 = (availableBlocks * blockSize) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        long j3 = j - j2;
        switch (deviceStorage2) {
            case EXTERNAL_STORAGE_TOTAL:
                return ((float) j) / 1000.0f;
            case EXTERNAL_STORAGE_AVAILABLE:
                return ((float) j2) / 1000.0f;
            case EXTERNAL_STORAGE_USED:
                return ((float) j3) / 1000.0f;
            default:
                return 0.0f;
        }
    }

    private static float B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(DeviceStorage deviceStorage) {
        StatFs statFs;
        new StatFs(Environment.getDataDirectory().getPath());
        StatFs statFs2 = statFs;
        StatFs statFs3 = statFs2;
        long blockSize = (long) statFs2.getBlockSize();
        long availableBlocks = (long) statFs3.getAvailableBlocks();
        long blockCount = (long) statFs3.getBlockCount();
        long j = (availableBlocks * blockSize) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        long j2 = (blockCount * blockSize) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        long j3 = j2;
        long j4 = j2 - j;
        switch (deviceStorage) {
            case INTERNAL_STORAGE_TOTAL:
                return ((float) j3) / 1000.0f;
            case INTERNAL_STORAGE_AVAILABLE:
                return ((float) j) / 1000.0f;
            case INTERNAL_STORAGE_USED:
                return ((float) j4) / 1000.0f;
            default:
                return 0.0f;
        }
    }

    private float wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(DeviceStorage deviceStorage) {
        ActivityManager.MemoryInfo memoryInfo;
        new ActivityManager.MemoryInfo();
        ActivityManager.MemoryInfo memoryInfo2 = memoryInfo;
        ((ActivityManager) this.context.getSystemService("activity")).getMemoryInfo(memoryInfo2);
        long j = memoryInfo2.availMem / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        long j2 = memoryInfo2.totalMem / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        long j3 = j2;
        long j4 = j2 - j;
        switch (deviceStorage) {
            case MEMORY_TOTAL:
                return ((float) j3) / 1000.0f;
            case MEMORY_AVAILABLE:
                return ((float) j) / 1000.0f;
            case MEMORY_USED:
                return ((float) j4) / 1000.0f;
            default:
                return 0.0f;
        }
    }

    static /* synthetic */ void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(DeviceTools deviceTools) {
        StringBuilder sb;
        DeviceTools deviceTools2 = deviceTools;
        DeviceTools deviceTools3 = deviceTools2;
        try {
            new StringBuilder();
            deviceTools3.GotIMEI(sb.append(((TelephonyManager) deviceTools2.context.getSystemService("phone")).getDeviceId()).toString());
        } catch (PermissionException e) {
            PermissionException permissionException = e;
            deviceTools2.form.dispatchPermissionDeniedEvent((Component) deviceTools2, "GetIMEI", permissionException);
            int e2 = Log.e("DeviceTools", String.valueOf(permissionException));
        } catch (Exception e3) {
            int e4 = Log.e("DeviceTools", String.valueOf(e3));
        }
    }

    static /* synthetic */ void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(DeviceTools deviceTools) {
        StringBuilder sb;
        StringBuilder sb2;
        DeviceTools deviceTools2 = deviceTools;
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                new StringBuilder();
                deviceTools2.GotSerial(sb2.append(Build.getSerial()).toString());
                return;
            }
            new StringBuilder();
            deviceTools2.GotSerial(sb.append(Build.SERIAL).toString());
        } catch (PermissionException e) {
            PermissionException permissionException = e;
            deviceTools2.form.dispatchPermissionDeniedEvent((Component) deviceTools2, "GetSerial", permissionException);
            int e2 = Log.e("DeviceTools", String.valueOf(permissionException));
        } catch (Exception e3) {
            int e4 = Log.e("DeviceTools", String.valueOf(e3));
        }
    }
}
