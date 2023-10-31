package com.google.appinventor.components.runtime;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.p000v4.app.NotificationCompat;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.UTILITIES, description = "", iconName = "images/batterymanager.png", nonVisible = true, version = 1)
public class BatteryManager extends AndroidNonvisibleComponent implements Component, OnDestroyListener, OnResumeListener, OnStopListener {
    private String ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud = "";
    private float B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = 0.0f;
    private boolean LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = false;
    private String PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC;
    private String TAG = "BatteryManager";
    private final ComponentContainer container;
    private float hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = 0.0f;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private BroadcastReceiver f340hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private String moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0 = "";
    private String tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE;
    private boolean vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE;
    /* access modifiers changed from: private */
    public int vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = 0;
    private int wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = 0;

    static /* synthetic */ float B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(BatteryManager batteryManager, float f) {
        float f2 = f;
        float f3 = f2;
        batteryManager.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = f3;
        return f2;
    }

    static /* synthetic */ int B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(BatteryManager batteryManager, int i) {
        int i2 = i;
        int i3 = i2;
        batteryManager.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = i3;
        return i2;
    }

    static /* synthetic */ String B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(BatteryManager batteryManager, String str) {
        String str2 = str;
        String str3 = str2;
        batteryManager.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE = str3;
        return str2;
    }

    static /* synthetic */ float hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BatteryManager batteryManager, float f) {
        float f2 = f;
        float f3 = f2;
        batteryManager.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = f3;
        return f2;
    }

    static /* synthetic */ int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BatteryManager batteryManager, int i) {
        int i2 = i;
        int i3 = i2;
        batteryManager.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = i3;
        return i2;
    }

    static /* synthetic */ String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BatteryManager batteryManager, String str) {
        String str2 = str;
        String str3 = str2;
        batteryManager.moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0 = str3;
        return str2;
    }

    static /* synthetic */ boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BatteryManager batteryManager, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        batteryManager.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = z3;
        return z2;
    }

    static /* synthetic */ String vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(BatteryManager batteryManager, String str) {
        String str2 = str;
        String str3 = str2;
        batteryManager.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud = str3;
        return str2;
    }

    static /* synthetic */ String wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(int i) {
        switch (i) {
            case 1:
                return "UNKNOWN";
            case 2:
                return "GOOD";
            case 3:
                return "OVERHEAT";
            case 4:
                return "DEAD";
            case 5:
                return "OVER_VOLTAGE";
            case 6:
                return "UNSPECIFIED_FAILURE";
            case 7:
                return "COLD";
            default:
                return "UNKNOWN";
        }
    }

    static /* synthetic */ String wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(BatteryManager batteryManager, String str) {
        String str2 = str;
        String str3 = str2;
        batteryManager.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = str3;
        return str2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BatteryManager(com.google.appinventor.components.runtime.ComponentContainer r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            java.lang.String r3 = "BatteryManager"
            r2.TAG = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0 = r3
            r2 = r0
            r3 = 0
            r2.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = r3
            r2 = r0
            r3 = 0
            r2.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = r3
            r2 = r0
            r3 = 0
            r2.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud = r3
            r2 = r0
            r3 = 0
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            r3 = 0
            r2.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r3
            r2 = r0
            com.google.appinventor.components.runtime.BatteryManager$1 r3 = new com.google.appinventor.components.runtime.BatteryManager$1
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r0
            r4.<init>(r5)
            r2.f340hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            com.google.appinventor.components.runtime.Form r2 = r2.form
            r3 = r0
            r2.registerForOnStop(r3)
            r2 = r0
            com.google.appinventor.components.runtime.Form r2 = r2.form
            r3 = r0
            r2.registerForOnResume(r3)
            r2 = r0
            com.google.appinventor.components.runtime.Form r2 = r2.form
            r3 = r0
            r2.registerForOnDestroy(r3)
            r2 = r0
            r3 = r1
            r2.container = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.BatteryManager.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Returns a list showing battery info for the specified key. Key can be: HEALTH, LEVEL, PLUGGED, PRESENT, STATUS, CHARGE_STATUS, TECHNOLOGY, TEMPERATURE, VOLTAGE. If key is empty, then all battery info is returned.")
    public YailList GetBatteryInfo(String str) {
        List list;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        StringBuilder sb5;
        StringBuilder sb6;
        StringBuilder sb7;
        StringBuilder sb8;
        StringBuilder sb9;
        String trim = str.toLowerCase().trim();
        String str2 = trim.equals("") ? "all" : trim;
        new ArrayList();
        List list2 = list;
        if (str2.equals("all") || str2.equals("health")) {
            new StringBuilder("HEALTH=");
            boolean add = list2.add(sb9.append(this.moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0).toString());
        }
        if (str2.equals("all") || str2.equals("level")) {
            new StringBuilder("LEVEL=");
            boolean add2 = list2.add(sb8.append(this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou).toString());
        }
        if (str2.equals("all") || str2.equals("plugged")) {
            new StringBuilder("PLUGGED=");
            boolean add3 = list2.add(sb7.append(this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE).toString());
        }
        if (str2.equals("all") || str2.equals("present")) {
            new StringBuilder("PRESENT=");
            boolean add4 = list2.add(sb6.append(this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn).toString());
        }
        if (str2.equals("all") || str2.equals(NotificationCompat.CATEGORY_STATUS)) {
            new StringBuilder("STATUS=");
            boolean add5 = list2.add(sb5.append(this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq).toString());
        }
        if (str2.equals("all") || str2.equals("charge_status")) {
            new StringBuilder("CHARGE_STATUS=");
            boolean add6 = list2.add(sb4.append(this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC).toString());
        }
        if (str2.equals("all") || str2.equals("technology")) {
            new StringBuilder("TECHNOLOGY=");
            boolean add7 = list2.add(sb3.append(this.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud).toString());
        }
        if (str2.equals("all") || str2.equals("temperature")) {
            new StringBuilder("TEMPERATURE=");
            boolean add8 = list2.add(sb2.append(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).toString());
        }
        if (str2.equals("all") || str2.equals("voltage")) {
            new StringBuilder("VOLTAGE=");
            boolean add9 = list2.add(sb.append(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T).toString());
        }
        Collections.sort(list2);
        return YailList.makeList(list2);
    }

    @SimpleProperty(description = "Indicating battery temperature in Centigrade")
    public float BatteryTemperature() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    @SimpleProperty(description = "Indicating battery voltage in Volts")
    public float BatteryVoltage() {
        return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    }

    @SimpleProperty(description = "Indicating whether a battery is present")
    public boolean BatteryPresent() {
        return this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn;
    }

    @SimpleProperty(description = "Indicating whether the device is plugged in to a power source. Can be USB, AC or UNKNOWN")
    public String BatteryPlugged() {
        return this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE;
    }

    @SimpleProperty(description = "Returns battery percentage level")
    public int BatteryLevel() {
        return this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
    }

    @SimpleProperty(description = "Returns battery health. It can be: COLD, DEAD, GOOD, OVERHEAT, OVER_VOLTAGE, UNKNOWN")
    public String BatteryHealth() {
        return this.moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Starts or stops monitoring battery data. StartMonitoring only when you need to get information.")
    public void StartMonitoring(boolean z) {
        IntentFilter intentFilter;
        this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE = z;
        if (this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE) {
            new IntentFilter("android.intent.action.BATTERY_CHANGED");
            Intent registerReceiver = this.container.$context().registerReceiver(this.f340hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, intentFilter);
            return;
        }
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME();
    }

    public void onDestroy() {
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME();
    }

    public void onResume() {
        StartMonitoring(this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE);
    }

    public void onStop() {
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME();
    }

    private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME() {
        this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE = false;
        if (this.f340hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            try {
                this.container.$context().unregisterReceiver(this.f340hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
            } catch (Exception e) {
            }
        }
    }
}
