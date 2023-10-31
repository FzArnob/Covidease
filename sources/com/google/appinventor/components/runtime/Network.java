package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.CONNECTIVITY, description = "Provides basic information about the network connectivity of the device", iconName = "images/network.png", nonVisible = true, version = 2)
@UsesPermissions(permissionNames = "android.permission.ACCESS_NETWORK_STATE, android.permission.ACCESS_WIFI_STATE")
public class Network extends AndroidNonvisibleComponent implements Component {
    /* access modifiers changed from: private */
    public NetworkInfo B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private boolean LqPkE8nw2txhYsROtdyVVn8eaaz2Snf5qPRDJl9Xd4fWH3FTDxRDNp0V8h7hgt8B = false;
    private final Activity activity;
    private Context context;
    private ConnectivityManager hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private String symWhrqAyHWXMObHLoQEIMlJvqdZFvcp7UyC2VmDxP3CgSs0pkdkxz6qiaDBzrEK = "NONE";
    private final BroadcastReceiver vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;

    static /* synthetic */ NetworkInfo hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Network network, NetworkInfo networkInfo) {
        NetworkInfo networkInfo2 = networkInfo;
        NetworkInfo networkInfo3 = networkInfo2;
        network.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = networkInfo3;
        return networkInfo2;
    }

    static /* synthetic */ String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Network network, String str) {
        String str2 = str;
        String str3 = str2;
        network.symWhrqAyHWXMObHLoQEIMlJvqdZFvcp7UyC2VmDxP3CgSs0pkdkxz6qiaDBzrEK = str3;
        return str2;
    }

    static /* synthetic */ boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Network network, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        network.LqPkE8nw2txhYsROtdyVVn8eaaz2Snf5qPRDJl9Xd4fWH3FTDxRDNp0V8h7hgt8B = z3;
        return z2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Network(com.google.appinventor.components.runtime.ComponentContainer r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = 0
            r2.LqPkE8nw2txhYsROtdyVVn8eaaz2Snf5qPRDJl9Xd4fWH3FTDxRDNp0V8h7hgt8B = r3
            r2 = r0
            java.lang.String r3 = "NONE"
            r2.symWhrqAyHWXMObHLoQEIMlJvqdZFvcp7UyC2VmDxP3CgSs0pkdkxz6qiaDBzrEK = r3
            r2 = r0
            com.google.appinventor.components.runtime.Network$1 r3 = new com.google.appinventor.components.runtime.Network$1
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r4.<init>(r5)
            r2.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.activity = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            r7 = r2
            r2 = r7
            r3 = r7
            android.content.Context r3 = r3.context
            java.lang.String r4 = "connectivity"
            java.lang.Object r3 = r3.getSystemService(r4)
            android.net.ConnectivityManager r3 = (android.net.ConnectivityManager) r3
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            android.app.Activity r2 = r2.activity
            r3 = r0
            android.content.BroadcastReceiver r3 = r3.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R
            android.content.IntentFilter r4 = new android.content.IntentFilter
            r7 = r4
            r4 = r7
            r5 = r7
            java.lang.String r6 = "android.net.conn.CONNECTIVITY_CHANGE"
            r5.<init>(r6)
            android.content.Intent r2 = r2.registerReceiver(r3, r4)
            java.lang.String r2 = "Network"
            java.lang.String r3 = "Network Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Network.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Returns \"True\" if the device is connected to a network, \"False\" otherwise.")
    public boolean IsConnected() {
        try {
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getActiveNetworkInfo();
            return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T != null && this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.isConnected();
        } catch (Exception e) {
            int e2 = Log.e("Network", String.valueOf(e));
            return false;
        }
    }

    @Deprecated
    @SimpleFunction(description = "Returns the type of network the device is connected to. e.g. \"wifi\" or \"mobile\". This block is deprecated, use the \"IsWiFiConnection\" and \"IsMobileConnection\" blocks instead")
    public String Type() {
        return this.symWhrqAyHWXMObHLoQEIMlJvqdZFvcp7UyC2VmDxP3CgSs0pkdkxz6qiaDBzrEK;
    }

    @SimpleEvent(description = "Called when the device connects to a network.")
    public void OnConnect() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnConnect", new Object[0]);
    }

    @SimpleEvent(description = "Called when the device disconnects from a network.")
    public void OnDisconnect() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnDisconnect", new Object[0]);
    }

    @SimpleFunction(description = "Returns true if connection is through WiFi")
    public boolean IsWiFiConnection() {
        NetworkInfo activeNetworkInfo = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getActiveNetworkInfo();
        NetworkInfo networkInfo = activeNetworkInfo;
        return activeNetworkInfo != null && networkInfo.isConnectedOrConnecting() && networkInfo.getType() == 1;
    }

    @SimpleFunction(description = "Returns true if connection is through Mobile")
    public boolean IsMobileConnection() {
        NetworkInfo activeNetworkInfo = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getActiveNetworkInfo();
        NetworkInfo networkInfo = activeNetworkInfo;
        return activeNetworkInfo != null && networkInfo.isConnectedOrConnecting() && networkInfo.getType() == 0;
    }

    @SimpleFunction(description = "Returns true if using a fast connection")
    public boolean IsFastConnection() {
        boolean z;
        NetworkInfo activeNetworkInfo = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getActiveNetworkInfo();
        NetworkInfo networkInfo = activeNetworkInfo;
        if (activeNetworkInfo != null && networkInfo.isConnectedOrConnecting()) {
            int type = networkInfo.getType();
            int subtype = networkInfo.getSubtype();
            int i = type;
            int i2 = i;
            if (i == 1) {
                z = true;
            } else {
                if (i2 == 0) {
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
                    }
                }
                z = false;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    @SimpleFunction(description = "Returns true if using roaming")
    public boolean IsRoaming() {
        NetworkInfo activeNetworkInfo = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getActiveNetworkInfo();
        NetworkInfo networkInfo = activeNetworkInfo;
        return activeNetworkInfo != null && networkInfo.isConnectedOrConnecting() && networkInfo.isRoaming();
    }
}
