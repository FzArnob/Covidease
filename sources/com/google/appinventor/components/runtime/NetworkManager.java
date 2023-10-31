package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.DEPRECATED, description = "", iconName = "images/network_mgr.png", nonVisible = true, version = 2)
@UsesPermissions(permissionNames = "android.permission.ACCESS_NETWORK_STATE")
public class NetworkManager extends AndroidNonvisibleComponent implements Component {
    private final Context context;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NetworkManager(com.google.appinventor.components.runtime.ComponentContainer r5) {
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
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.NetworkManager.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Returns true if connection is through WiFi")
    public boolean IsWiFiConnection() {
        NetworkInfo hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME();
        NetworkInfo networkInfo = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null && networkInfo.isConnectedOrConnecting() && networkInfo.getType() == 1;
    }

    @SimpleFunction(description = "Returns true if connection is through Mobile")
    public boolean IsMobileConnection() {
        NetworkInfo hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME();
        NetworkInfo networkInfo = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null && networkInfo.isConnectedOrConnecting() && networkInfo.getType() == 0;
    }

    @SimpleFunction(description = "Returns true if using a fast connection")
    public boolean IsFastConnection() {
        boolean z;
        NetworkInfo hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME();
        NetworkInfo networkInfo = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null && networkInfo.isConnectedOrConnecting()) {
            int type = networkInfo.getType();
            int subtype = networkInfo.getSubtype();
            int i = type;
            int i2 = i;
            if (i == 1) {
                z = true;
            } else if (i2 == 0) {
                switch (subtype) {
                    case 1:
                        z = false;
                        break;
                    case 2:
                        z = false;
                        break;
                    case 3:
                        z = true;
                        break;
                    case 4:
                        z = false;
                        break;
                    case 5:
                        z = true;
                        break;
                    case 6:
                        z = true;
                        break;
                    case 7:
                        z = false;
                        break;
                    case 8:
                        z = true;
                        break;
                    case 9:
                        z = true;
                        break;
                    case 10:
                        z = true;
                        break;
                    case 11:
                        z = false;
                        break;
                    case 12:
                        z = true;
                        break;
                    case 13:
                        z = true;
                        break;
                    case 14:
                        z = true;
                        break;
                    case 15:
                        z = true;
                        break;
                    default:
                        z = false;
                        break;
                }
            } else {
                z = false;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    @SimpleFunction(description = "Returns true if using using roaming")
    public boolean IsRoaming() {
        NetworkInfo hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME();
        NetworkInfo networkInfo = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null && networkInfo.isConnectedOrConnecting() && networkInfo.isRoaming();
    }

    @SimpleFunction(description = "Indicates whether network connectivity exists and it is possible to establish connections and pass data.")
    public boolean IsConnected() {
        NetworkInfo hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME();
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null && hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isConnectedOrConnecting();
    }

    @SimpleFunction(description = " describe the type of the network, for example WIFI or MOBILE")
    public String GetConnectionType() {
        NetworkInfo hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME();
        NetworkInfo networkInfo = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "UNABLE TO GET CONNECTION TYPE";
        }
        return networkInfo.getTypeName();
    }

    private NetworkInfo hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME() {
        return ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
    }

    @SimpleProperty(description = "Checks to see if device is GPS enabled")
    public boolean IsGPSEnabledDevice() {
        LocationManager locationManager = (LocationManager) this.form.getSystemService("location");
        LocationManager locationManager2 = locationManager;
        if (locationManager == null) {
            return false;
        }
        List<String> allProviders = locationManager2.getAllProviders();
        return allProviders != null && allProviders.contains("gps");
    }

    @SimpleProperty(description = "Checks to see if device is GPS enabled and if so, checks to see if GPS is started or not")
    public boolean IsGPSEnabled() {
        if (!IsGPSEnabledDevice()) {
            return false;
        }
        return ((LocationManager) this.form.getSystemService("location")).isProviderEnabled("gps");
    }

    @SimpleProperty(description = "Starts up the GPS configuration activity, giving user option to turn turn on the GPS")
    public boolean StartGPSOptions() {
        Intent intent;
        new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
        Intent intent2 = intent;
        this.form.startActivity(intent2);
        return true;
    }
}
