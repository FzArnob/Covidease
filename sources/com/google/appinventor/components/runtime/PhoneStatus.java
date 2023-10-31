package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.p000v4.p002os.EnvironmentCompat;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesNativeLibraries;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.YaVersion;
import com.google.appinventor.components.runtime.util.AppInvHTTPD;
import com.google.appinventor.components.runtime.util.EclairUtil;
import com.google.appinventor.components.runtime.util.PackageInstaller;
import com.google.appinventor.components.runtime.util.SdkLevel;
import com.google.appinventor.components.runtime.util.WebRTCNativeMgr;
import java.security.MessageDigest;
import java.util.Formatter;

@UsesNativeLibraries(v7aLibraries = "libjingle_peerconnection_so.so", v8aLibraries = "libjingle_peerconnection_so.so", x86_64Libraries = "libjingle_peerconnection_so.so")
@DesignerComponent(category = ComponentCategory.INTERNAL, description = "Component that returns information about the phone.", iconName = "images/phoneip.png", nonVisible = true, version = 1)
@UsesLibraries(libraries = "webrtc.jar,google-http-client-beta.jar,google-http-client-android2-beta.jar,google-http-client-android3-beta.jar")
@SimpleObject
public class PhoneStatus extends AndroidNonvisibleComponent implements Component {
    private static Activity activity;
    private static boolean haiIuOdmUoc5XQFvR9GJKGOwB3ZezhsWV0MdG7MgpkerzHclvrTRoGLrsRSHUTL5 = true;
    private static PhoneStatus hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
    private final Form form;
    private String hibTQF3buaJTulLZvSVkxWzq69D3X99LEonIrTaR8DG6SkVpYpvjF3tGUybbhvWG = null;
    private String vvjaKRMN9H6lYkjQrf39wC72L2per26vSgUw9e5mZIMwW4gwULkxbiBNnCPVkUrj = null;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public PhoneStatus(com.google.appinventor.components.runtime.ComponentContainer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = 0
            r2.vvjaKRMN9H6lYkjQrf39wC72L2per26vSgUw9e5mZIMwW4gwULkxbiBNnCPVkUrj = r3
            r2 = r0
            r3 = 0
            r2.hibTQF3buaJTulLZvSVkxWzq69D3X99LEonIrTaR8DG6SkVpYpvjF3tGUybbhvWG = r3
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.form = r3
            r2 = r1
            android.app.Activity r2 = r2.$context()
            activity = r2
            com.google.appinventor.components.runtime.PhoneStatus r2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            if (r2 != 0) goto L_0x0029
            r2 = r0
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r2
        L_0x0029:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.PhoneStatus.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Returns the IP address of the phone in the form of a String")
    public static String GetWifiIpAddress() {
        String str;
        int i = ((WifiManager) activity.getSystemService("wifi")).getDhcpInfo().ipAddress;
        if (isConnected()) {
            str = intToIp(i);
        } else {
            str = "Error: No Wifi Connection";
        }
        return str;
    }

    @SimpleFunction(description = "Returns TRUE if the phone is on Wifi, FALSE otherwise")
    public static boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService("connectivity");
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getNetworkInfo(1);
        }
        if (networkInfo == null) {
            return false;
        }
        return networkInfo.isConnected();
    }

    @SimpleFunction(description = "Establish the secret seed for HOTP generation. Return the SHA1 of the provided seed, this will be used to contact the rendezvous server. Note: This code also starts the connection negotiation process if we are using WebRTC. This is a bit of a kludge...")
    public String setHmacSeedReturnCode(String str, String str2) {
        StringBuffer stringBuffer;
        Formatter formatter;
        StringBuilder sb;
        Runnable runnable;
        String str3 = str;
        String str4 = str2;
        if (str3.equals("")) {
            return "";
        }
        if (this.vvjaKRMN9H6lYkjQrf39wC72L2per26vSgUw9e5mZIMwW4gwULkxbiBNnCPVkUrj != null) {
            if (!this.vvjaKRMN9H6lYkjQrf39wC72L2per26vSgUw9e5mZIMwW4gwULkxbiBNnCPVkUrj.equals(str3)) {
                new Runnable(this) {
                    private /* synthetic */ PhoneStatus B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

                    {
                        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r5;
                    }

                    public final void run() {
                        Form.getActiveForm().finish();
                        System.exit(0);
                    }
                };
                Notifier.oneButtonAlert((Activity) Form.getActiveForm(), "You cannot use two codes with one start up of the Companion. You should restart the Companion and try again.", "Warning", "OK", runnable);
            }
            return this.hibTQF3buaJTulLZvSVkxWzq69D3X99LEonIrTaR8DG6SkVpYpvjF3tGUybbhvWG;
        }
        this.vvjaKRMN9H6lYkjQrf39wC72L2per26vSgUw9e5mZIMwW4gwULkxbiBNnCPVkUrj = str3;
        if (!haiIuOdmUoc5XQFvR9GJKGOwB3ZezhsWV0MdG7MgpkerzHclvrTRoGLrsRSHUTL5) {
            AppInvHTTPD.setHmacKey(str3);
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA1");
            instance.update(str3.getBytes());
            byte[] digest = instance.digest();
            new StringBuffer(digest.length << 1);
            StringBuffer stringBuffer2 = stringBuffer;
            new Formatter(stringBuffer2);
            Formatter formatter2 = formatter;
            int length = digest.length;
            for (int i = 0; i < length; i++) {
                Formatter format = formatter2.format("%02x", new Object[]{Byte.valueOf(digest[i])});
            }
            int d = Log.d("PhoneStatus", "Seed = ".concat(String.valueOf(str3)));
            new StringBuilder("Code = ");
            int d2 = Log.d("PhoneStatus", sb.append(stringBuffer2.toString()).toString());
            this.hibTQF3buaJTulLZvSVkxWzq69D3X99LEonIrTaR8DG6SkVpYpvjF3tGUybbhvWG = stringBuffer2.toString();
            return this.hibTQF3buaJTulLZvSVkxWzq69D3X99LEonIrTaR8DG6SkVpYpvjF3tGUybbhvWG;
        } catch (Exception e) {
            int e2 = Log.e("PhoneStatus", "Exception getting SHA1 Instance", e);
            return "";
        }
    }

    @SimpleFunction(description = "Returns true if we are running in the emulator or USB Connection")
    public boolean isDirect() {
        StringBuilder sb;
        StringBuilder sb2;
        new StringBuilder("android.os.Build.VERSION.RELEASE = ");
        int d = Log.d("PhoneStatus", sb.append(Build.VERSION.RELEASE).toString());
        new StringBuilder("android.os.Build.PRODUCT = ");
        int d2 = Log.d("PhoneStatus", sb2.append(Build.PRODUCT).toString());
        if (Build.PRODUCT.contains("google_sdk")) {
            return true;
        }
        if (this.form instanceof ReplForm) {
            return ((ReplForm) this.form).isDirect();
        }
        return false;
    }

    @SimpleFunction(description = "Start the WebRTC engine")
    public void startWebRTC(String str, String str2) {
        WebRTCNativeMgr webRTCNativeMgr;
        new WebRTCNativeMgr(str, str2);
        WebRTCNativeMgr webRTCNativeMgr2 = webRTCNativeMgr;
        webRTCNativeMgr2.initiate((ReplForm) this.form, activity, this.vvjaKRMN9H6lYkjQrf39wC72L2per26vSgUw9e5mZIMwW4gwULkxbiBNnCPVkUrj);
        ((ReplForm) this.form).setWebRTCMgr(webRTCNativeMgr2);
    }

    @SimpleFunction(description = "Start the internal AppInvHTTPD to listen for incoming forms. FOR REPL USE ONLY!")
    public void startHTTPD(boolean z) {
        ReplForm.topform.startHTTPD(z);
    }

    @SimpleFunction(description = "Declare that we have loaded our initial assets and other assets should come from the sdcard")
    public void setAssetsLoaded() {
        if (this.form instanceof ReplForm) {
            ((ReplForm) this.form).setAssetsLoaded();
        }
    }

    @SimpleFunction(description = "Causes an Exception, used to debug exception processing.")
    public static void doFault() throws Exception {
        Throwable th;
        Throwable th2 = th;
        new Exception("doFault called!");
        throw th2;
    }

    @SimpleFunction(description = "Downloads the URL and installs it as an Android Package")
    public void installURL(String str) {
        PackageInstaller.doPackageInstall(this.form, str);
    }

    @SimpleFunction(description = "Really Exit the Application")
    public void shutdown() {
        this.form.finish();
        System.exit(0);
    }

    @SimpleEvent
    public void OnSettings() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnSettings", new Object[0]);
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void WebRTC(boolean z) {
        haiIuOdmUoc5XQFvR9GJKGOwB3ZezhsWV0MdG7MgpkerzHclvrTRoGLrsRSHUTL5 = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If True we are using WebRTC to talk to the server.")
    public boolean WebRTC() {
        return haiIuOdmUoc5XQFvR9GJKGOwB3ZezhsWV0MdG7MgpkerzHclvrTRoGLrsRSHUTL5;
    }

    @SimpleFunction(description = "Get the current Android SDK Level")
    public int SdkLevel() {
        return SdkLevel.getLevel();
    }

    @SimpleFunction(description = "Return the our VersionName property")
    public String GetVersionName() {
        try {
            return this.form.getPackageManager().getPackageInfo(this.form.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            int e2 = Log.e("PhoneStatus", "Unable to get VersionName", e);
            return "UNKNOWN";
        }
    }

    @SimpleFunction(description = "Return the app that installed us")
    public String GetInstaller() {
        if (SdkLevel.getLevel() < 5) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        String installerPackageName = EclairUtil.getInstallerPackageName(YaVersion.ACCEPTABLE_COMPANION_PACKAGE, this.form);
        String str = installerPackageName;
        if (installerPackageName == null) {
            return "sideloaded";
        }
        return str;
    }

    public static boolean getUseWebRTC() {
        return haiIuOdmUoc5XQFvR9GJKGOwB3ZezhsWV0MdG7MgpkerzHclvrTRoGLrsRSHUTL5;
    }

    public static String intToIp(int i) {
        StringBuilder sb;
        int i2 = i;
        new StringBuilder();
        return sb.append(i2 & 255).append(".").append((i2 >> 8) & 255).append(".").append((i2 >> 16) & 255).append(".").append(i2 >>> 24).toString();
    }
}
