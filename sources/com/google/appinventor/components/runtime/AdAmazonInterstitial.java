package com.google.appinventor.components.runtime;

import android.os.Handler;
import android.util.Log;
import com.amazon.device.ads.Ad;
import com.amazon.device.ads.AdError;
import com.amazon.device.ads.AdProperties;
import com.amazon.device.ads.DefaultAdListener;
import com.amazon.device.ads.InterstitialAd;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularAdsCommission;
import com.google.appinventor.components.runtime.util.KodularContentProtection;
import com.google.appinventor.components.runtime.util.ads.KodularInterstitial;

@DesignerComponent(category = ComponentCategory.ADVERTISING, description = "An interstitial ad is a full-page ad. AdAmazonInterstitial component allows you to monetize your app. You must have a valid Amazon Application Key. If your application key is invalid, the ad will not display on the emulator or the device. Warning: Make sure you're in test mode during development to avoid being disabled for clicking your own ads. ", helpUrl = "https://docs.kodular.io/components/monetization/amazon-interstitial/", iconName = "images/amazon.png", nonVisible = true, version = 2)
@UsesLibraries(libraries = "amazon-ads.jar,unity-ads.aar, unity-ads.jar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET,android.permission.ACCESS_COARSE_LOCATION,android.permission.ACCESS_FINE_LOCATION,android.permission.ACCESS_NETWORK_STATE,android.permission.ACCESS_WIFI_STATE")
public class AdAmazonInterstitial extends AndroidNonvisibleComponent implements Component {
    private String F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho = "";
    private final Handler androidUIHandler;
    private ComponentContainer container;
    private Form form;
    private boolean hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = false;
    /* access modifiers changed from: private */
    public boolean havePermission;
    private InterstitialAd hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private KodularInterstitial f333hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private KodularAdsCommission kodularAdsCommission;
    private KodularContentProtection kodularContentProtection;
    private boolean qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE;
    private int targetAge;
    /* access modifiers changed from: private */
    public boolean vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = true;
    /* access modifiers changed from: private */
    public boolean vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;
    private boolean wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = true;

    static /* synthetic */ boolean B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(AdAmazonInterstitial adAmazonInterstitial, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        adAmazonInterstitial.havePermission = z3;
        return z2;
    }

    static /* synthetic */ boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(AdAmazonInterstitial adAmazonInterstitial, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        adAmazonInterstitial.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = z3;
        return z2;
    }

    static /* synthetic */ boolean wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(AdAmazonInterstitial adAmazonInterstitial, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        adAmazonInterstitial.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = z3;
        return z2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AdAmazonInterstitial(com.google.appinventor.components.runtime.ComponentContainer r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            java.lang.String r3 = ""
            r2.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho = r3
            r2 = r0
            r3 = 1
            r2.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = r3
            r2 = r0
            r3 = 1
            r2.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = r3
            r2 = r0
            r3 = 0
            r2.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = r3
            r2 = r0
            android.os.Handler r3 = new android.os.Handler
            r7 = r3
            r3 = r7
            r4 = r7
            r4.<init>()
            r2.androidUIHandler = r3
            r2 = r0
            r3 = 0
            r2.havePermission = r3
            r2 = r0
            r3 = 0
            r2.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = r3
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.form = r3
            r2 = r0
            r3 = r1
            r2.container = r3
            r2 = r0
            com.google.appinventor.components.runtime.util.KodularAdsCommission r3 = new com.google.appinventor.components.runtime.util.KodularAdsCommission
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r1
            android.app.Activity r5 = r5.$context()
            r6 = r0
            com.google.appinventor.components.runtime.Form r6 = r6.form
            r4.<init>(r5, r6)
            r2.kodularAdsCommission = r3
            r2 = r0
            com.amazon.device.ads.InterstitialAd r3 = new com.amazon.device.ads.InterstitialAd
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r1
            android.app.Activity r5 = r5.$context()
            r4.<init>(r5)
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            com.amazon.device.ads.InterstitialAd r2 = r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.AdAmazonInterstitial$a r3 = new com.google.appinventor.components.runtime.AdAmazonInterstitial$a
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r4.<init>(r5)
            r2.setListener(r3)
            r2 = r0
            com.google.appinventor.components.runtime.util.ads.KodularInterstitial r3 = new com.google.appinventor.components.runtime.util.ads.KodularInterstitial
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r1
            android.app.Activity r5 = r5.$context()
            r4.<init>(r5)
            r2.f333hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            com.google.appinventor.components.runtime.util.ads.KodularInterstitial r2 = r2.f333hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.AdAmazonInterstitial$1 r3 = new com.google.appinventor.components.runtime.AdAmazonInterstitial$1
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r4.<init>(r5)
            r2.setOnAdsSwitcherListener(r3)
            r2 = r0
            com.google.appinventor.components.runtime.util.KodularContentProtection r3 = new com.google.appinventor.components.runtime.util.KodularContentProtection
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r1
            android.app.Activity r5 = r5.$context()
            r4.<init>(r5)
            r2.kodularContentProtection = r3
            r2 = r0
            com.google.appinventor.components.runtime.util.KodularContentProtection r2 = r2.kodularContentProtection
            com.google.appinventor.components.runtime.AdAmazonInterstitial$2 r3 = new com.google.appinventor.components.runtime.AdAmazonInterstitial$2
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r4.<init>(r5)
            r2.setOnValidationResultListener(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.AdAmazonInterstitial.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleEvent(description = "After a user clicks on the close ad button on an expanded ad, this callback is called immediately after collapsing the ad. This callback can be used to do things like resume your app or restart audio.")
    public void AdCollapsed() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdCollapsed", new Object[0]);
    }

    @SimpleEvent(description = "This callback is called each time an ad is successfully loaded. You can use this to log metrics on ad views and assist with initial integration. Detailed information about the ad that loaded can be obtained from the AdProperties object.")
    public void AdExpanded() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdExpanded", new Object[0]);
    }

    @SimpleEvent(description = "Whenever an ad fails to be retrieved, the event is called, returning the error message.")
    public void AdFailedToLoad(String str, String str2) {
        Object[] objArr = new Object[2];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdFailedToLoad", objArr2);
    }

    @SimpleEvent(description = "Called when an an attempt was made to display the ad, but the ad was not ready to display")
    public void AdFailedToShow(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdFailedToShow", str);
    }

    @SimpleEvent(description = "Triggered when the close button of the interstitial ad is clicked. It's important to remember only one interstitial ad can be shown at a time. The previous ad has to be dismissed before a new ad can be shown.")
    public void AdClosed() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdClosed", new Object[0]);
    }

    @SimpleEvent(description = "Triggered each time an ad is successfully loaded. But you don't have to display the ad right after it's loaded. For example, set a flag to true and then at a transition point, if flag=true, then display the ad.")
    public void AdLoaded() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdLoaded", new Object[0]);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public String ApplicationKey() {
        return this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho;
    }

    @DesignerProperty(defaultValue = "ApplicationKey", editorType = "string")
    @SimpleProperty(description = "Enter Application Key. Go to Amazon Developer Portal and sign-in for your ApplicationKey", userVisible = false)
    public void ApplicationKey(String str) {
        String str2 = str;
        this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho = str2;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(userVisible = true)
    public void EnableDebug(boolean z) {
        boolean z2 = z;
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean EnableDebug() {
        return this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "For debugging / development purposes flag all ad requests as tests, but set to false for production builds", userVisible = true)
    public void EnableTesting(boolean z) {
        boolean z2 = z;
        this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean EnableTesting() {
        return this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "If set to true, uses latitude and longitude coordinates as part of an ad request", userVisible = true)
    public void EnableGeoLocationTargeting(boolean z) {
        boolean z2 = z;
        this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean EnableGeoLocationTargeting() {
        return this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public int TargetAge() {
        return this.targetAge;
    }

    @DesignerProperty(defaultValue = "0", editorType = "integer")
    @SimpleProperty(description = "You can pass age information to the Amazon Mobile Ad Network to target specific age groups. If set as 0, Age Targetting will not be used")
    public void TargetAge(int i) {
        int i2 = i;
        this.targetAge = i2;
    }

    @DesignerProperty(defaultValue = "unity", editorType = "commission_interstitial_ads_network")
    @Deprecated
    @SimpleProperty(description = "Sets the ad network used to take the commission.")
    public void CommissionInterstitialAdsNetwork(String str) {
        this.f333hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.updateNetwork(str);
    }

    @SimpleFunction(description = "Loads a new ad.")
    public void LoadAd() {
        this.kodularContentProtection.startContentValidation(this.form.getAppId());
    }

    @SimpleFunction(description = "It will show the Interstitial Ad")
    public void ShowInterstitialAd() {
        if (!this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE) {
            if (this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO) {
                this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = false;
                boolean showAd = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.showAd();
                return;
            }
        } else if (this.f333hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.showAd()) {
            this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = false;
            return;
        }
        String str = "Interstitial ad was not ready to be shown. Make sure you have set ad AppKey and you invoke this after LoadAd";
        int d = Log.d("AdAmazonInterstitial", str);
        AdFailedToShow(str);
    }

    /* renamed from: com.google.appinventor.components.runtime.AdAmazonInterstitial$a */
    class C0568a extends DefaultAdListener {
        private /* synthetic */ AdAmazonInterstitial hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        C0568a(AdAmazonInterstitial adAmazonInterstitial) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = adAmazonInterstitial;
        }

        public final void onAdCollapsed(Ad ad) {
            Ad ad2 = ad;
            int i = Log.i("AdAmazonInterstitial", "Ad collapsed.");
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AdCollapsed();
        }

        public final void onAdExpanded(Ad ad) {
            Ad ad2 = ad;
            int i = Log.i("AdAmazonInterstitial", "Ad expanded.");
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AdExpanded();
        }

        public final void onAdFailedToLoad(Ad ad, AdError adError) {
            StringBuilder sb;
            Ad ad2 = ad;
            AdError adError2 = adError;
            new StringBuilder("Ad failed to load. Code: ");
            int w = Log.w("AdAmazonInterstitial", sb.append(adError2.getCode()).append(", Message: ").append(adError2.getMessage()).toString());
            boolean wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = AdAmazonInterstitial.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, false);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AdFailedToLoad(adError2.getCode().toString(), adError2.getMessage());
        }

        public final void onAdLoaded(Ad ad, AdProperties adProperties) {
            StringBuilder sb;
            Ad ad2 = ad;
            new StringBuilder();
            int i = Log.i("AdAmazonInterstitial", sb.append(adProperties.getAdType().toString()).append(" ad loaded successfully.").toString());
            boolean wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = AdAmazonInterstitial.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, true);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AdLoaded();
        }

        public final void onAdDismissed(Ad ad) {
            Ad ad2 = ad;
            int i = Log.i("AdAmazonInterstitial", "Ad onAdDismissed finished");
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AdClosed();
        }
    }
}
