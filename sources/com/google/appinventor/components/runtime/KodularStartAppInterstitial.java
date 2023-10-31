package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularAdsCommission;
import com.google.appinventor.components.runtime.util.KodularContentProtection;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;

@DesignerComponent(category = ComponentCategory.ADVERTISING, description = "The component for showing Interstial Ads from StartApp", iconName = "images/startAppInterstitial.png", nonVisible = true, version = 1)
@UsesLibraries(libraries = "startapp-ads.jar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.ACCESS_NETWORK_STATE, android.permission.ACCESS_WIFI_STATE")
public final class KodularStartAppInterstitial extends AndroidNonvisibleComponent {
    private Activity activity;
    private String appId;
    private ComponentContainer container;
    private boolean jfz4esSp2gG8GcabwIhRTwYj9V6OBMxneQTmg596S0YedndAxvJdoaPHqPTMCjiO = false;
    private KodularAdsCommission kodularAdsCommission;
    private KodularContentProtection kodularContentProtection;
    private StartAppAd startAppAd;

    static /* synthetic */ boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularStartAppInterstitial kodularStartAppInterstitial, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        kodularStartAppInterstitial.jfz4esSp2gG8GcabwIhRTwYj9V6OBMxneQTmg596S0YedndAxvJdoaPHqPTMCjiO = z3;
        return z2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KodularStartAppInterstitial(com.google.appinventor.components.runtime.ComponentContainer r9) {
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
            r2.jfz4esSp2gG8GcabwIhRTwYj9V6OBMxneQTmg596S0YedndAxvJdoaPHqPTMCjiO = r3
            r2 = r0
            r3 = r1
            r2.container = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.activity = r3
            r2 = r0
            com.google.appinventor.components.runtime.util.KodularAdsCommission r3 = new com.google.appinventor.components.runtime.util.KodularAdsCommission
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r1
            android.app.Activity r5 = r5.$context()
            r6 = r1
            com.google.appinventor.components.runtime.Form r6 = r6.$form()
            r4.<init>(r5, r6)
            r2.kodularAdsCommission = r3
            r2 = r0
            com.startapp.android.publish.adsCommon.StartAppAd r3 = new com.startapp.android.publish.adsCommon.StartAppAd
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            android.app.Activity r5 = r5.activity
            r4.<init>(r5)
            r2.startAppAd = r3
            r2 = r0
            com.google.appinventor.components.runtime.util.KodularContentProtection r3 = new com.google.appinventor.components.runtime.util.KodularContentProtection
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            android.app.Activity r5 = r5.activity
            r4.<init>(r5)
            r2.kodularContentProtection = r3
            r2 = r0
            com.google.appinventor.components.runtime.util.KodularContentProtection r2 = r2.kodularContentProtection
            com.google.appinventor.components.runtime.KodularStartAppInterstitial$1 r3 = new com.google.appinventor.components.runtime.KodularStartAppInterstitial$1
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r4.<init>(r5)
            r2.setOnValidationResultListener(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularStartAppInterstitial.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Load a new StartApp Interstitial ad.")
    public final void LoadAd() {
        this.kodularContentProtection.startContentValidation(this.form.getAppId());
    }

    @DesignerProperty(editorType = "string")
    @SimpleProperty(userVisible = false)
    public final void AppId(String str) {
        String str2 = str;
        this.appId = str2;
    }

    @SimpleEvent(description = "Called when an ad request failed to load.")
    public final void OnFailedToReceiveAd(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnFailedToReceiveAd", str);
    }

    @SimpleEvent(description = "Called when an ad request failed to load. The message will display the error code and error message.")
    public final void OnReceiveAd() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnReceiveAd", new Object[0]);
    }

    @SimpleEvent(description = "Called when an an attempt was made to display the ad, but the ad was not ready to display.")
    public final void AdFailedToShow(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdFailedToShow", str);
    }

    @SimpleEvent(description = "Indicates that the user has clicked on the interstial ad")
    public final void AdClicked() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdClicked", new Object[0]);
    }

    @SimpleEvent(description = "Indicates that an Ad is shown to the user.")
    public final void AdDisplayed() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdDisplayed", new Object[0]);
    }

    @SimpleEvent(description = "Indicates that an Ad which was shown to the user is now hidden.")
    public final void AdHidden() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdHidden", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad request failed to load. The message will display the error code and error message.")
    public final void AdFailedToLoad(int i, String str) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdFailedToLoad", objArr2);
    }

    @SimpleFunction(description = "It will show the Interstitial Ad")
    public final void ShowInterstitialAd() {
        AdDisplayListener adDisplayListener;
        if (!this.jfz4esSp2gG8GcabwIhRTwYj9V6OBMxneQTmg596S0YedndAxvJdoaPHqPTMCjiO) {
            AdFailedToShow("An Ad should be loaded initially before it can be displayed.");
            return;
        }
        this.jfz4esSp2gG8GcabwIhRTwYj9V6OBMxneQTmg596S0YedndAxvJdoaPHqPTMCjiO = false;
        new AdDisplayListener(this) {
            private /* synthetic */ KodularStartAppInterstitial hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void adHidden(Ad ad) {
                Ad ad2 = ad;
                int i = Log.i("StartApp Interstitial", "StartApp Ad hidden");
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AdHidden();
            }

            public final void adDisplayed(Ad ad) {
                Ad ad2 = ad;
                int i = Log.i("StartApp Interstitial", "Ad displayed");
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AdDisplayed();
            }

            public final void adClicked(Ad ad) {
                Ad ad2 = ad;
                int i = Log.i("StartApp Interstitial", "Ad clicked");
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AdClicked();
            }

            public final void adNotDisplayed(Ad ad) {
                int i = Log.i("StartApp Interstitial", "Ad not displayed");
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AdFailedToShow(ad.getErrorMessage());
            }
        };
        boolean showAd = this.startAppAd.showAd(adDisplayListener);
    }
}
