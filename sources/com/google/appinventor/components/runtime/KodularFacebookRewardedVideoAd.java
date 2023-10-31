package com.google.appinventor.components.runtime;

import android.content.Context;
import com.facebook.ads.RewardedVideoAd;
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
import com.google.appinventor.components.runtime.util.ads.KodularRewardedVideo;
import java.util.Random;

@DesignerComponent(category = ComponentCategory.ADVERTISING, description = "...in ode messages file", helpUrl = "https://docs.kodular.io/components/monetization/facebook-rewarded/", iconName = "images/facebook.png", nonVisible = true, version = 2)
@UsesLibraries(libraries = "AudienceNetwork.jar, AudienceNetwork.aar, exoplayer.jar,play-services-basement.jar, play-services-basement.aar,unity-ads.jar, unity-ads.aar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.ACCESS_NETWORK_STATE")
public class KodularFacebookRewardedVideoAd extends AndroidNonvisibleComponent implements OnDestroyListener {
    private ComponentContainer container;
    private Context context;
    private String e1IHYfRNckEvpOWbFvMtuN7w9PEpZtVYShhIlzbQR8mwSxiOizA6OYtX7vMfGCUT = "";
    private Form form;
    private RewardedVideoAd hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private KodularRewardedVideo f453hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private KodularAdsCommission kodularAdsCommission;
    private KodularContentProtection kodularContentProtection;
    private boolean qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = false;

    static /* synthetic */ RewardedVideoAd hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularFacebookRewardedVideoAd kodularFacebookRewardedVideoAd, RewardedVideoAd rewardedVideoAd) {
        RewardedVideoAd rewardedVideoAd2 = rewardedVideoAd;
        RewardedVideoAd rewardedVideoAd3 = rewardedVideoAd2;
        kodularFacebookRewardedVideoAd.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = rewardedVideoAd3;
        return rewardedVideoAd2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KodularFacebookRewardedVideoAd(com.google.appinventor.components.runtime.ComponentContainer r9) {
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
            r2.e1IHYfRNckEvpOWbFvMtuN7w9PEpZtVYShhIlzbQR8mwSxiOizA6OYtX7vMfGCUT = r3
            r2 = r0
            r3 = 0
            r2.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = r3
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
            com.google.appinventor.components.runtime.Form r2 = r2.form
            r3 = r0
            r2.registerForOnDestroy(r3)
            r2 = r0
            com.google.appinventor.components.runtime.util.KodularAdsCommission r3 = new com.google.appinventor.components.runtime.util.KodularAdsCommission
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            android.content.Context r5 = r5.context
            r6 = r0
            com.google.appinventor.components.runtime.Form r6 = r6.form
            r4.<init>(r5, r6)
            r2.kodularAdsCommission = r3
            r2 = r0
            com.google.appinventor.components.runtime.util.ads.KodularRewardedVideo r3 = new com.google.appinventor.components.runtime.util.ads.KodularRewardedVideo
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r1
            android.app.Activity r5 = r5.$context()
            r4.<init>(r5)
            r2.f453hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            com.google.appinventor.components.runtime.util.ads.KodularRewardedVideo r2 = r2.f453hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.KodularFacebookRewardedVideoAd$1 r3 = new com.google.appinventor.components.runtime.KodularFacebookRewardedVideoAd$1
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
            com.google.appinventor.components.runtime.KodularFacebookRewardedVideoAd$2 r3 = new com.google.appinventor.components.runtime.KodularFacebookRewardedVideoAd$2
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r4.<init>(r5)
            r2.setOnValidationResultListener(r3)
            r2 = r0
            android.content.Context r2 = r2.context
            com.facebook.ads.AudienceNetworkAds.initialize(r2)
            java.lang.String r2 = "Facebook Rewarded Video Ad"
            java.lang.String r3 = "Kodular Facebook Rewarded Video Ad created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularFacebookRewardedVideoAd.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Loads a new ad.")
    public void LoadAd() {
        Random random;
        new Random();
        if (random.nextFloat() <= this.kodularAdsCommission.getCommision("facebook", "video")) {
            this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = true;
        } else {
            this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = false;
        }
        this.kodularContentProtection.startContentValidation(this.form.getAppId());
    }

    @SimpleFunction(description = "Shows an ad to the user.")
    public void ShowAd() {
        if (this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE) {
            if (!this.f453hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.showAd()) {
                Error("Ad failed to show.");
            }
        } else if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null && this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isAdLoaded()) {
            boolean show = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.show();
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public String PlacementID() {
        return this.e1IHYfRNckEvpOWbFvMtuN7w9PEpZtVYShhIlzbQR8mwSxiOizA6OYtX7vMfGCUT;
    }

    @DesignerProperty
    @SimpleProperty(userVisible = false)
    public void PlacementID(String str) {
        String str2 = str;
        this.e1IHYfRNckEvpOWbFvMtuN7w9PEpZtVYShhIlzbQR8mwSxiOizA6OYtX7vMfGCUT = str2;
    }

    @DesignerProperty(defaultValue = "unity", editorType = "commission_rewarded_ads_network")
    @Deprecated
    @SimpleProperty(description = "Sets the ad network used to take the commission.")
    public void CommissionRewardedAdsNetwork(String str) {
        this.f453hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.updateNetwork(str);
    }

    @SimpleEvent(description = "Called when an ad request failed. message will display the reason for why the ad failed.")
    public void Error(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Error", str);
    }

    @SimpleEvent(description = "Called when the user is about to return to the application after clicking on an ad.")
    public void AdClosed() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdClosed", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad is received.")
    public void AdLoaded() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdLoaded", new Object[0]);
    }

    @SimpleEvent(description = "Called when an video ad was completed.")
    public void AdVideoCompleted() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdVideoCompleted", new Object[0]);
    }

    @SimpleEvent(description = "Called when an video ad started to show content.")
    public void AdVideoStarted() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdVideoStarted", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad was opened.")
    public void AdOpened() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdOpened", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad request failed to load. The message will display the error code and error message.")
    public void AdFailedToLoad(int i, String str) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdFailedToLoad", objArr2);
    }

    public void onDestroy() {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.destroy();
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
        }
    }
}
