package com.google.appinventor.components.runtime;

import android.annotation.SuppressLint;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Collections;

@SimpleObject
@DesignerComponent(category = ComponentCategory.CONNECTIVITY, description = "Control the WiFi of the Device", helpUrl = "https://docs.kodular.io/components/connectivity/wifi/", iconName = "images/wifi.png", nonVisible = true, version = 2)
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.CHANGE_WIFI_STATE, android.permission.ACCESS_WIFI_STATE, android.permission.ACCESS_FINE_LOCATION")
public class WiFiAdmin extends AndroidNonvisibleComponent implements Component {
    private WifiManager hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WiFiAdmin(com.google.appinventor.components.runtime.ComponentContainer r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            java.lang.String r4 = "wifi"
            java.lang.Object r3 = r3.getSystemService(r4)
            android.net.wifi.WifiManager r3 = (android.net.wifi.WifiManager) r3
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            java.lang.String r2 = "WiFiAdmin"
            java.lang.String r3 = "WiFiAdmin Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.WiFiAdmin.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SuppressLint({"MissingPermission"})
    @SimpleFunction(description = "Enable the Wi-Fi")
    public void Enable() {
        boolean wifiEnabled = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setWifiEnabled(true);
    }

    @SuppressLint({"MissingPermission"})
    @SimpleFunction(description = "Disable the Wi-Fi")
    public void Disable() {
        boolean wifiEnabled = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setWifiEnabled(false);
    }

    @SuppressLint({"MissingPermission"})
    @SimpleFunction(description = "Toggle the Wi-Fi")
    public void Toggle() {
        boolean wifiEnabled = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setWifiEnabled(!this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isWifiEnabled());
    }

    @SuppressLint({"MissingPermission"})
    @SimpleFunction(description = "Return whether Wi-Fi is enabled or disabled")
    public boolean IsEnabled() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isWifiEnabled();
    }

    @SuppressLint({"MissingPermission"})
    @SimpleFunction(description = "Return whether this adapter supports 5 GHz band")
    public boolean Is5GHzSupported() {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.is5GHzBandSupported();
        }
        return false;
    }

    @SuppressLint({"MissingPermission"})
    @SimpleFunction(description = "Return whether this adapter supports Wi-Fi Direct")
    public boolean IsWiFiDirectSupported() {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isP2pSupported();
        }
        return false;
    }

    @SimpleFunction(description = "Return the current Local IP")
    public String LocalIP() {
        if (!IsConnected()) {
            return "";
        }
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "";
        }
    }

    @SuppressLint({"MissingPermission"})
    @SimpleFunction(description = "Returns the service set identifier (SSID) of the current 802.11 network")
    public String SSID() {
        return IsConnected() ? this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getConnectionInfo().getSSID() : "";
    }

    @SuppressLint({"MissingPermission"})
    @SimpleFunction(description = "Return the basic service set identifier (BSSID) of the current access point")
    public String BSSID() {
        return IsConnected() ? this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getConnectionInfo().getBSSID() : "";
    }

    @SuppressLint({"MissingPermission"})
    @SimpleFunction(description = "Returns the received signal strength indicator of the current 802.11 network, in dBm")
    public int SignalStrength() {
        if (IsConnected()) {
            return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getConnectionInfo().getRssi();
        }
        return 0;
    }

    @SuppressLint({"MissingPermission"})
    @SimpleFunction(description = "Returns the current link speed in Mbps")
    public int LinkSpeed() {
        if (IsConnected()) {
            return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getConnectionInfo().getLinkSpeed();
        }
        return 0;
    }

    @SimpleFunction(description = "Returns the wlan mac address.")
    public String MacAddress() {
        StringBuilder sb;
        try {
            for (T t : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                NetworkInterface networkInterface = t;
                if (t.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    byte[] bArr = hardwareAddress;
                    if (hardwareAddress == null) {
                        return "";
                    }
                    new StringBuilder();
                    StringBuilder sb2 = sb;
                    int length = bArr.length;
                    for (int i = 0; i < length; i++) {
                        StringBuilder append = sb2.append(String.format("%02X:", new Object[]{Byte.valueOf(bArr[i])}));
                    }
                    if (sb2.length() > 0) {
                        StringBuilder sb3 = sb2;
                        StringBuilder deleteCharAt = sb3.deleteCharAt(sb3.length() - 1);
                    }
                    return sb2.toString().toLowerCase();
                }
            }
        } catch (Exception e) {
            int e2 = Log.e("WiFiAdmin", String.valueOf(e));
        }
        return "02:00:00:00:00:00";
    }

    @SuppressLint({"MissingPermission"})
    public boolean IsConnected() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getConnectionInfo().getNetworkId() != -1;
    }
}
