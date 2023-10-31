package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAppOptions;
import com.adcolony.sdk.AdColonyInterstitial;
import com.adcolony.sdk.AdColonyInterstitialListener;
import com.adcolony.sdk.AdColonyZone;
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
import com.google.appinventor.components.runtime.util.KodularGDPRUtil;
import com.google.appinventor.components.runtime.util.OnInitializeListener;
import java.util.Random;

@DesignerComponent(category = ComponentCategory.ADVERTISING, description = "...in ode messages file", helpUrl = "https://docs.kodular.io/components/monetization/adcolony-interstitial/", iconName = "images/adcolony.png", nonVisible = true, version = 1)
@UsesLibraries(libraries = "AdColony.jar,play-services-ads-identifier.jar, play-services-ads-identifier.aar,play-services-basement.jar, play-services-basement.aar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.ACCESS_NETWORK_STATE, android.permission.VIBRATE")
public class KodularAdColonyInterstitialAd extends AndroidNonvisibleComponent implements OnDestroyListener, OnInitializeListener {
    private String AsUIHfRHW1pScN9YQW0IsOeuFdHXhbXb53xXbDg8x2ZIBxv57XORnQnTS1wprCIt = "";
    private String appId = "";
    private ComponentContainer container;
    private Context context;
    private Form form;
    private AdColonyAppOptions hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private AdColonyInterstitial f446hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private AdColonyInterstitialListener f447hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private KodularAdsCommission kodularAdsCommission;
    private KodularContentProtection kodularContentProtection;
    private boolean pgq50Ta8BOwqZ1x44UiPoTcDoRPiNHTTIb3Jgmceok7eFp2gi0sO4JRUukOMKqHH = false;
    private String poblgm1P6mADk8QKAia8LTNTlp3hP9LK4vL2LyACRyn6OaTobhLhKjphCbjAETmg = "";

    static /* synthetic */ AdColonyInterstitial hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularAdColonyInterstitialAd kodularAdColonyInterstitialAd, AdColonyInterstitial adColonyInterstitial) {
        AdColonyInterstitial adColonyInterstitial2 = adColonyInterstitial;
        AdColonyInterstitial adColonyInterstitial3 = adColonyInterstitial2;
        kodularAdColonyInterstitialAd.f446hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = adColonyInterstitial3;
        return adColonyInterstitial2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KodularAdColonyInterstitialAd(com.google.appinventor.components.runtime.ComponentContainer r9) {
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
            r2.appId = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.AsUIHfRHW1pScN9YQW0IsOeuFdHXhbXb53xXbDg8x2ZIBxv57XORnQnTS1wprCIt = r3
            r2 = r0
            r3 = 0
            r2.pgq50Ta8BOwqZ1x44UiPoTcDoRPiNHTTIb3Jgmceok7eFp2gi0sO4JRUukOMKqHH = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.poblgm1P6mADk8QKAia8LTNTlp3hP9LK4vL2LyACRyn6OaTobhLhKjphCbjAETmg = r3
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
            com.google.appinventor.components.runtime.Form r2 = r2.form
            r3 = r0
            r2.registerForOnInitialize(r3)
            r2 = r0
            com.adcolony.sdk.AdColonyAppOptions r3 = new com.adcolony.sdk.AdColonyAppOptions
            r7 = r3
            r3 = r7
            r4 = r7
            r4.<init>()
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
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
            com.google.appinventor.components.runtime.KodularAdColonyInterstitialAd$1 r3 = new com.google.appinventor.components.runtime.KodularAdColonyInterstitialAd$1
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r4.<init>(r5)
            r2.setOnValidationResultListener(r3)
            java.lang.String r2 = "AdColony Interstitial Ad"
            java.lang.String r3 = "Kodular AdColony Interstitial Ad created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularAdColonyInterstitialAd.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public void onInitialize() {
        AdColonyInterstitialListener adColonyInterstitialListener;
        Random random;
        if (AppID().isEmpty() || ZoneID().isEmpty()) {
            boolean configure = AdColony.configure(this.container.$context(), this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, KodularAdsUtil.AD_COLONY_APP_ID, new String[]{KodularAdsUtil.AD_COLONY_ZONE_ID});
            this.poblgm1P6mADk8QKAia8LTNTlp3hP9LK4vL2LyACRyn6OaTobhLhKjphCbjAETmg = KodularAdsUtil.AD_COLONY_ZONE_ID;
        } else {
            new Random();
            if (random.nextFloat() <= this.kodularAdsCommission.getCommision("adcolony", "interstitial")) {
                boolean configure2 = AdColony.configure(this.container.$context(), this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, KodularAdsUtil.AD_COLONY_APP_ID, new String[]{KodularAdsUtil.AD_COLONY_ZONE_ID});
                this.poblgm1P6mADk8QKAia8LTNTlp3hP9LK4vL2LyACRyn6OaTobhLhKjphCbjAETmg = KodularAdsUtil.AD_COLONY_ZONE_ID;
            } else {
                boolean configure3 = AdColony.configure(this.container.$context(), this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, AppID(), new String[]{ZoneID()});
                this.poblgm1P6mADk8QKAia8LTNTlp3hP9LK4vL2LyACRyn6OaTobhLhKjphCbjAETmg = ZoneID();
            }
        }
        new AdColonyInterstitialListener(this) {
            private /* synthetic */ KodularAdColonyInterstitialAd hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onRequestFilled(AdColonyInterstitial adColonyInterstitial) {
                AdColonyInterstitial hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = KodularAdColonyInterstitialAd.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, adColonyInterstitial);
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AdLoaded();
                int i = Log.i("AdColony Interstitial Ad", "AdColony Interstitial: Interstitial ad loaded");
            }

            public final void onRequestNotFilled(AdColonyZone adColonyZone) {
                AdColonyZone adColonyZone2 = adColonyZone;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Error("Request not filled.");
                int e = Log.e("AdColony Interstitial Ad", "AdColony Interstitial: Interstitial ad failed to load: request not filled");
            }

            public final void onOpened(AdColonyInterstitial adColonyInterstitial) {
                AdColonyInterstitial adColonyInterstitial2 = adColonyInterstitial;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AdOpened();
                int i = Log.i("AdColony Interstitial Ad", "AdColony Interstitial: Interstitial ad opened");
            }

            public final void onExpiring(AdColonyInterstitial adColonyInterstitial) {
                AdColonyInterstitial adColonyInterstitial2 = adColonyInterstitial;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AdExpiring();
                int i = Log.i("AdColony Interstitial Ad", "AdColony Interstitial: Interstitial ad is expiring");
            }
        };
        this.f447hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = adColonyInterstitialListener;
    }

    @SimpleFunction(description = "Loads a new ad.")
    public void LoadAd() {
        this.kodularContentProtection.startContentValidation(this.form.getAppId());
    }

    @SimpleFunction(description = "Shows an ad to the user.")
    public void ShowAd() {
        if (this.f446hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            if (this.f446hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isExpired()) {
                AdExpiring();
            } else {
                boolean show = this.f446hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.show();
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public String AppID() {
        return this.appId;
    }

    @DesignerProperty
    @SimpleProperty(userVisible = false)
    public void AppID(String str) {
        String str2 = str;
        this.appId = str2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public String ZoneID() {
        return this.AsUIHfRHW1pScN9YQW0IsOeuFdHXhbXb53xXbDg8x2ZIBxv57XORnQnTS1wprCIt;
    }

    @DesignerProperty
    @SimpleProperty(userVisible = false)
    public void ZoneID(String str) {
        String str2 = str;
        this.AsUIHfRHW1pScN9YQW0IsOeuFdHXhbXb53xXbDg8x2ZIBxv57XORnQnTS1wprCIt = str2;
    }

    @SimpleEvent(description = "Called when an ad request failed. The message will display the reason for why the ad failed.")
    public void Error(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Error", str);
    }

    @SimpleEvent(description = "Called when the ad is expiring. You should load a new ad.")
    public void AdExpiring() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdExpiring", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad is received.")
    public void AdLoaded() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdLoaded", new Object[0]);
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

    @SimpleProperty(description = "If set to true the user allowed the ad network to show personalized ads. You only need to request the consent from european users.")
    public void UserConsent(boolean z) {
        boolean z2 = z;
        this.pgq50Ta8BOwqZ1x44UiPoTcDoRPiNHTTIb3Jgmceok7eFp2gi0sO4JRUukOMKqHH = z2;
        if (z2) {
            AdColonyAppOptions gDPRConsentString = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setGDPRConsentString("1");
        } else {
            AdColonyAppOptions gDPRConsentString2 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setGDPRConsentString("1");
        }
        AdColonyAppOptions gDPRRequired = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setGDPRRequired(z2);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean UserConsent() {
        return this.pgq50Ta8BOwqZ1x44UiPoTcDoRPiNHTTIb3Jgmceok7eFp2gi0sO4JRUukOMKqHH;
    }

    @SimpleFunction(description = "Returns true if the current app user is located in europe. If true you must ask the user as example in a dialog if he give his consent for personalized ads.")
    public boolean IsEuropeanUser() {
        return KodularGDPRUtil.isRequestLocationInEurope(this.container.$context());
    }

    public void onDestroy() {
        if (this.f446hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            boolean destroy = this.f446hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.destroy();
            this.f446hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
        }
    }
}
