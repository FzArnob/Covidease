package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinPrivacySettings;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkSettings;
import com.firebase.client.FirebaseError;
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
import com.google.appinventor.components.runtime.util.KodularGDPRUtil;
import org.shaded.apache.http.HttpStatus;

@DesignerComponent(category = ComponentCategory.ADVERTISING, description = "...in ode messages file", helpUrl = "https://docs.kodular.io/components/monetization/app-lovin/", iconName = "images/applovin.png", nonVisible = true, version = 1)
@UsesLibraries(libraries = "app-lovin.aar, app-lovin.jar,customtabs.jar, customtabs.aar,play-services-ads-base.jar, play-services-ads-base.aar,play-services-ads-identifier.jar, play-services-ads-identifier.aar,play-services-ads-lite.jar, play-services-ads-lite.aar,play-services-basement.jar, play-services-basement.aar,play-services-gass.jar, play-services-gass.aar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.ACCESS_NETWORK_STATE")
public class KodularAppLovin extends AndroidNonvisibleComponent {
    private String N4rx6qe3Dkxm9iGosdnZviEGwwAyjrMopVTdmRoB5smsVn2ef0JNliQjJW08w5OT = "";
    private ComponentContainer container;
    private Context context;
    private Form form;
    private AppLovinInterstitialAdDialog hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private AppLovinAd f448hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private AppLovinSdk f449hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private AppLovinSdkSettings f450hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private KodularAdsCommission kodularAdsCommission;
    private KodularContentProtection kodularContentProtection;
    private boolean pgq50Ta8BOwqZ1x44UiPoTcDoRPiNHTTIb3Jgmceok7eFp2gi0sO4JRUukOMKqHH = false;

    static /* synthetic */ AppLovinAd hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularAppLovin kodularAppLovin, AppLovinAd appLovinAd) {
        AppLovinAd appLovinAd2 = appLovinAd;
        AppLovinAd appLovinAd3 = appLovinAd2;
        kodularAppLovin.f448hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = appLovinAd3;
        return appLovinAd2;
    }

    static /* synthetic */ AppLovinSdk hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularAppLovin kodularAppLovin, AppLovinSdk appLovinSdk) {
        AppLovinSdk appLovinSdk2 = appLovinSdk;
        AppLovinSdk appLovinSdk3 = appLovinSdk2;
        kodularAppLovin.f449hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = appLovinSdk3;
        return appLovinSdk2;
    }

    static /* synthetic */ String vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R(int i) {
        switch (i) {
            case -900:
                return "Indicates that a postback URL you attempted to dispatch was empty or nil.";
            case -800:
                return "Indicates that a AppLovin servers have returned an invalid response";
            case -702:
                return "Indicates that the impression has already been tracked.";
            case -700:
                return "Indicates that there was an error while attempting to render a native ad.";
            case -600:
                return "Indicates that the user exited out of the ad early. You may or may not wish to grant a reward depending on your preference. Note: This code is only possible when working with rewarded ads.";
            case -500:
                return "Indicates that a reward validation requested timed out (usually due to poor connectivity). Note: This code is only possible when working with rewarded videos.";
            case -400:
                return "Indicates that an unknown server-side error occurred. Note: This code is only possible when working with rewarded videos.";
            case -300:
                return "Indicates that the developer called for a rewarded video before one was available. Note: This code is only possible when working with rewarded videos.";
            case -202:
                return "Indicates that an attempt to cache a video resource to the filesystem failed; the device may be out of space.";
            case -201:
                return "Indicates that an attempt to cache an image resource to the filesystem failed; the device may be out of space.";
            case -200:
                return "Indicates that an attempt to cache a resource to the filesystem failed; the device may be out of space.";
            case -103:
                return "Indicates that the device had no network connectivity at the time of an ad request, either due to airplane mode or no service.";
            case -102:
                return "Indicates that the network conditions prevented the SDK from receiving an ad.";
            case FirebaseError.PROVIDER_ERROR /*-22*/:
                return "Indicates that the SDK is currently disabled.";
            case -8:
                return "Indicates that the provided ad token is invalid; ad token must be returned from AppLovin S2S integration.";
            case -7:
                return "Indicates that the zone provided is invalid; the zone needs to be added to your AppLovin account or may still be propagating to our servers.";
            case -6:
                return "Indicates that there has been a failure to render an ad on screen.";
            case -1:
                return "Indicates that the system is in unexpected state.";
            case HttpStatus.SC_NO_CONTENT /*204*/:
                return "Indicates that no ads are currently eligible for your device.";
            default:
                return "No error message available";
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KodularAppLovin(com.google.appinventor.components.runtime.ComponentContainer r9) {
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
            r2.N4rx6qe3Dkxm9iGosdnZviEGwwAyjrMopVTdmRoB5smsVn2ef0JNliQjJW08w5OT = r3
            r2 = r0
            r3 = 0
            r2.pgq50Ta8BOwqZ1x44UiPoTcDoRPiNHTTIb3Jgmceok7eFp2gi0sO4JRUukOMKqHH = r3
            r2 = r0
            r3 = r1
            r2.container = r3
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.form = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            com.applovin.sdk.AppLovinSdkSettings r3 = new com.applovin.sdk.AppLovinSdkSettings
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r1
            android.app.Activity r5 = r5.$context()
            r4.<init>(r5)
            r2.f450hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
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
            com.google.appinventor.components.runtime.KodularAppLovin$1 r3 = new com.google.appinventor.components.runtime.KodularAppLovin$1
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r4.<init>(r5)
            r2.setOnValidationResultListener(r3)
            java.lang.String r2 = "AppLovin"
            java.lang.String r3 = "Kodular AppLovin created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularAppLovin.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Loads a new ad.")
    public void LoadAd() {
        this.kodularContentProtection.startContentValidation(this.form.getAppId());
    }

    @SimpleFunction(description = "Shows an ad to the user.")
    public void ShowAd() {
        AppLovinAdClickListener appLovinAdClickListener;
        AppLovinAdDisplayListener appLovinAdDisplayListener;
        if (this.f449hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = AppLovinInterstitialAd.create(this.f449hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.container.$context());
            new AppLovinAdClickListener(this) {
                private /* synthetic */ KodularAppLovin hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void adClicked(AppLovinAd appLovinAd) {
                    AppLovinAd appLovinAd2 = appLovinAd;
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AdOpened();
                    int i = Log.i("AppLovin", "AppLovin: ad opened");
                }
            };
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setAdClickListener(appLovinAdClickListener);
            new AppLovinAdDisplayListener(this) {
                private /* synthetic */ KodularAppLovin hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void adDisplayed(AppLovinAd appLovinAd) {
                    AppLovinAd appLovinAd2 = appLovinAd;
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AdStarted();
                    int i = Log.i("AppLovin", "Unity Ads: ad started");
                }

                public final void adHidden(AppLovinAd appLovinAd) {
                    AppLovinAd appLovinAd2 = appLovinAd;
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AdClosed();
                    int i = Log.i("AppLovin", "Unity Ads: ad closed");
                }
            };
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setAdDisplayListener(appLovinAdDisplayListener);
            if (this.f448hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.showAndRender(this.f448hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public String SdkKey() {
        return this.N4rx6qe3Dkxm9iGosdnZviEGwwAyjrMopVTdmRoB5smsVn2ef0JNliQjJW08w5OT;
    }

    @DesignerProperty
    @SimpleProperty(userVisible = false)
    public void SdkKey(String str) {
        String str2 = str;
        this.N4rx6qe3Dkxm9iGosdnZviEGwwAyjrMopVTdmRoB5smsVn2ef0JNliQjJW08w5OT = str2;
    }

    @SimpleEvent(description = "Called when an ad request failed. The message will display the reason for why the ad failed.")
    public void Error(int i, String str) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Error", objArr2);
    }

    @SimpleEvent(description = "Called when an ad is received.")
    public void AdLoaded() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdLoaded", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad was opened.")
    public void AdOpened() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdOpened", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad was closed.")
    public void AdClosed() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdClosed", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad was started.")
    public void AdStarted() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdStarted", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad request failed to load. The message will display the error code and error message.")
    public void AdFailedToLoad(int i, String str) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdFailedToLoad", objArr2);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If you want to test the component then that this property to true. Then you will receive test ads.")
    public void TestMode(boolean z) {
        boolean z2 = z;
        if (this.f450hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.f450hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTestAdsEnabled(z2);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean TestMode() {
        if (this.f450hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            return this.f450hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isTestAdsEnabled();
        }
        return false;
    }

    @SimpleProperty(description = "If set to true the user allowed the ad network to show personalized ads. You only need to request the consent from european users.")
    public void UserConsent(boolean z) {
        boolean z2 = z;
        this.pgq50Ta8BOwqZ1x44UiPoTcDoRPiNHTTIb3Jgmceok7eFp2gi0sO4JRUukOMKqHH = z2;
        AppLovinPrivacySettings.setHasUserConsent(z2, this.container.$context());
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean UserConsent() {
        return this.pgq50Ta8BOwqZ1x44UiPoTcDoRPiNHTTIb3Jgmceok7eFp2gi0sO4JRUukOMKqHH;
    }

    @SimpleFunction(description = "Returns true if the current app user is located in europe. If true you must ask the user as example in a dialog if he give his consent for personalized ads.")
    public boolean IsEuropeanUser() {
        return KodularGDPRUtil.isRequestLocationInEurope(this.container.$context());
    }
}
