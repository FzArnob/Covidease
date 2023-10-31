package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
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
import com.google.appinventor.components.runtime.util.KodularAdsUtil;
import com.google.appinventor.components.runtime.util.KodularContentProtection;
import com.google.appinventor.components.runtime.util.MakeroidDataProtection;

@DesignerComponent(category = ComponentCategory.ADVERTISING, description = "add description in OdeMessage ", iconName = "images/admob.png", nonVisible = true, version = 3)
@UsesLibraries(libraries = "play-services-ads.jar, play-services-ads.aar,customtabs.jar, customtabs.aar,play-services-ads-base.jar, play-services-ads-base.aar,play-services-ads-identifier.jar, play-services-ads-identifier.aar,play-services-ads-lite.jar, play-services-ads-lite.aar,play-services-basement.jar, play-services-basement.aar,play-services-gass.jar, play-services-gass.aar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.ACCESS_NETWORK_STATE, android.permission.ACCESS_COARSE_LOCATION, android.permission.ACCESS_FINE_LOCATION")
public class AdmobRewardedVideo extends AndroidNonvisibleComponent implements RewardedVideoAdListener, Component, OnDestroyListener, OnPauseListener, OnResumeListener {
    private boolean Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = true;
    private Activity activity;
    public String adUnitId;
    protected final ComponentContainer container;
    private Context context;
    private Form form;
    private RewardedVideoAd hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private MakeroidDataProtection f338hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private boolean isCompanion = false;
    private KodularAdsCommission kodularAdsCommission;
    private KodularContentProtection kodularContentProtection;
    private boolean testMode = false;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AdmobRewardedVideo(com.google.appinventor.components.runtime.ComponentContainer r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = 1
            r2.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = r3
            r2 = r0
            r3 = 0
            r2.isCompanion = r3
            r2 = r0
            r3 = 0
            r2.testMode = r3
            r2 = r0
            r3 = r1
            r2.container = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.activity = r3
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
            com.google.appinventor.components.runtime.Form r2 = r2.form
            r3 = r0
            r2.registerForOnPause(r3)
            r2 = r0
            com.google.appinventor.components.runtime.Form r2 = r2.form
            r3 = r0
            r2.registerForOnResume(r3)
            r2 = r0
            r7 = r2
            r2 = r7
            r3 = r7
            android.app.Activity r3 = r3.activity
            com.google.android.gms.ads.reward.RewardedVideoAd r3 = com.google.android.gms.ads.MobileAds.getRewardedVideoAdInstance(r3)
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            com.google.android.gms.ads.reward.RewardedVideoAd r2 = r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r3 = r0
            r2.setRewardedVideoAdListener(r3)
            r2 = r0
            java.lang.String r3 = "ca-app-pub-3940256099942544/5224354917"
            r2.adUnitId = r3
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
            com.google.appinventor.components.runtime.util.MakeroidDataProtection r3 = new com.google.appinventor.components.runtime.util.MakeroidDataProtection
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            android.content.Context r5 = r5.context
            java.lang.String r6 = com.google.appinventor.components.runtime.util.MakeroidDataProtection.TYPE_AD_MOB_REWARDED_VIDEO
            r4.<init>(r5, r6)
            r2.f338hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            com.google.appinventor.components.runtime.util.MakeroidDataProtection r2 = r2.f338hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r3 = r0
            com.google.android.gms.ads.reward.RewardedVideoAd r3 = r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r2.setRewardedVideoAd(r3)
            r2 = r0
            com.google.appinventor.components.runtime.util.MakeroidDataProtection r2 = r2.f338hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.AdmobRewardedVideo$1 r3 = new com.google.appinventor.components.runtime.AdmobRewardedVideo$1
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r4.<init>(r5)
            r2.setOnConsentListener(r3)
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
            com.google.appinventor.components.runtime.AdmobRewardedVideo$2 r3 = new com.google.appinventor.components.runtime.AdmobRewardedVideo$2
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r4.<init>(r5)
            r2.setOnValidationResultListener(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.AdmobRewardedVideo.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public String AdUnitId() {
        return this.adUnitId;
    }

    @DesignerProperty(defaultValue = "ca-app-pub-3940256099942544/5224354917", editorType = "string")
    @SimpleProperty(userVisible = false)
    public void AdUnitId(String str) {
        String str2 = str;
        this.adUnitId = str2;
    }

    @SimpleFunction(description = "Load a new AdMob Rewarded Video ad.")
    public void LoadAd() {
        AdRequest.Builder builder;
        if (!this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB) {
            AdFailedToLoad(404, "Make sure you enabled the ad in the component properties.");
        } else if (this.testMode) {
            new AdRequest.Builder();
            AdRequest.Builder builder2 = builder;
            AdRequest.Builder builder3 = builder2;
            AdRequest.Builder addTestDevice = builder2.addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB");
            AdRequest.Builder addTestDevice2 = builder3.addTestDevice(KodularAdsUtil.guessSelfDeviceId(this.context));
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.loadAd(KodularAdsUtil.ADMOB_REWARDED_VIDEO_TEST_ID, builder3.build());
        } else {
            this.kodularContentProtection.startContentValidation(this.form.getAppId());
        }
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "This property must be set to true to receive ads.")
    public void AdEnabled(boolean z) {
        boolean z2 = z;
        this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean AdEnabled() {
        return this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If you want to test the component, that this property to true. After it you will receive test ads.", userVisible = false)
    public void TestMode(boolean z) {
        boolean z2 = z;
        this.testMode = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean TestMode() {
        return this.testMode;
    }

    @SimpleFunction(description = "It will show the Video")
    public void ShowAd() {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isLoaded()) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.show();
        } else {
            LoadAd();
        }
    }

    public void onRewarded(RewardItem rewardItem) {
        StringBuilder sb;
        RewardItem rewardItem2 = rewardItem;
        Rewarded(rewardItem2.getType(), rewardItem2.getAmount());
        new StringBuilder("Admob Rewarded Video: Rewarded type: ");
        int i = Log.i("AdmobRewardedVideo", sb.append(rewardItem2.getType()).append(", Rewarded amount: ").append(rewardItem2.getAmount()).toString());
    }

    public void onRewardedVideoAdLeftApplication() {
        AdLeftApplication();
        int i = Log.i("AdmobRewardedVideo", "Admob Rewarded Video: LeftApplication");
    }

    public void onRewardedVideoAdClosed() {
        AdClosed();
        int i = Log.i("AdmobRewardedVideo", "Admob Rewarded Video: Video ad closed");
    }

    public void onRewardedVideoAdFailedToLoad(int i) {
        String str;
        StringBuilder sb;
        int i2 = i;
        if (i2 == 0) {
            str = "Something happened internally; for instance, an invalid response was received from the ad server.";
        } else if (i2 == 1) {
            str = "The ad request was invalid; for instance, the ad unit ID was incorrect.";
        } else if (i2 == 2) {
            str = "The ad request was unsuccessful due to network connectivity.";
        } else if (i2 == 3) {
            str = "The ad request was successful, but no ad was returned due to lack of ad inventory.";
        } else {
            str = "No error message found.";
        }
        String str2 = str;
        AdFailedToLoad(i2, str2);
        new StringBuilder("Admob Rewarded Video: Video failed to load: Error code: ");
        int w = Log.w("AdmobRewardedVideo", sb.append(i2).append(" ,Error message: ").append(str2).toString());
    }

    public void onRewardedVideoAdLoaded() {
        AdLoaded();
        int i = Log.i("AdmobRewardedVideo", "Admob Rewarded Video: Video ad loaded");
    }

    public void onRewardedVideoAdOpened() {
        AdOpened();
        int i = Log.i("AdmobRewardedVideo", "Admob Rewarded Video: Video ad opened");
    }

    public void onRewardedVideoStarted() {
        AdVideoStarted();
        int i = Log.i("AdmobRewardedVideo", "Admob Rewarded Video: Video started");
    }

    public void onRewardedVideoCompleted() {
        AdVideoCompleted();
        int i = Log.i("AdmobRewardedVideo", "Admob Rewarded Video: Video completed");
    }

    @SimpleEvent(description = "User watched video and should be rewarded.")
    public void Rewarded(String str, int i) {
        Object[] objArr = new Object[2];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = Integer.valueOf(i);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Rewarded", objArr2);
    }

    @SimpleEvent(description = "Called when an ad request failed to load. The message will display the error code and error message.")
    public void AdFailedToLoad(int i, String str) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdFailedToLoad", objArr2);
    }

    @SimpleEvent(description = "Called when an ad was closed.")
    public void AdClosed() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdClosed", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad was opened.")
    public void AdOpened() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdOpened", new Object[0]);
    }

    @SimpleEvent(description = "Called when an video ad started to show content.")
    public void AdVideoStarted() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdVideoStarted", new Object[0]);
    }

    @SimpleEvent(description = "Called when an video ad was completed.")
    public void AdVideoCompleted() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdVideoCompleted", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad leaves the application (e.g., to go to the browser).")
    public void AdLeftApplication() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdLeftApplication", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad request was loaded.")
    public void AdLoaded() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdLoaded", new Object[0]);
    }

    public void onResume() {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.resume();
        }
    }

    public void onPause() {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.pause();
        }
    }

    public void onDestroy() {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.destroy();
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
        }
    }

    @DesignerProperty(defaultValue = "Data Protection", editorType = "string")
    @SimpleProperty(description = "The title for the consent dialog.")
    public void ConsentTitle(String str) {
        this.f338hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTitle(str);
    }

    @SimpleProperty
    public String ConsentTitle() {
        return this.f338hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getTitle();
    }

    @DesignerProperty(defaultValue = "Can we continue to use your data to tailor ads for you?", editorType = "string")
    @SimpleProperty(description = "The message for the consent dialog.")
    public void ConsentMessage(String str) {
        this.f338hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMessage(str);
    }

    @SimpleProperty
    public String ConsentMessage() {
        return this.f338hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMessage();
    }

    @SimpleFunction(description = "Deletes the user's consent. Useful if you want to test the consent dialog in development.")
    public void RevokeConsent() {
        this.f338hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.RevokeConsent();
    }

    @SimpleEvent(description = "Event triggered when the consent was changed.")
    public void OnConsentChanged(boolean z) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnConsentChanged", Boolean.valueOf(z));
    }

    @SimpleProperty(description = "Returns the current personalized consent. If true user has consent to personalized ads.")
    public boolean PersonalizedResult() {
        return this.f338hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getPersonalizedResult();
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If enabled you will see the consent dialog no matter if you are located in Europe or not. Please use this option only in development.  If this setting is enabled ALL taken consents will not be saved.")
    public void ConsentDevelopmentMode(boolean z) {
        this.f338hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setDevelopmentMode(z);
    }

    @SimpleProperty
    public boolean ConsentDevelopmentMode() {
        return this.f338hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getDevelopmentMode();
    }
}
