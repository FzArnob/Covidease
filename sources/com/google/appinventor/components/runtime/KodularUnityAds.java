package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
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
import com.google.appinventor.components.runtime.util.OnInitializeListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.mediation.IUnityAdsExtendedListener;
import com.unity3d.ads.metadata.MetaData;
import java.util.Arrays;
import java.util.Random;

@DesignerComponent(category = ComponentCategory.ADVERTISING, description = "...in ode messages file", helpUrl = "https://docs.kodular.io/components/monetization/unity-ads_ads/", iconName = "images/unityads.png", nonVisible = true, version = 2)
@UsesLibraries(libraries = "unity-ads.aar, unity-ads.jar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.ACCESS_NETWORK_STATE")
public class KodularUnityAds extends AndroidNonvisibleComponent implements OnInitializeListener {
    private Activity activity;
    private ComponentContainer container;
    private Context context;
    private boolean cxDeivMnEpGbjLe4A1R1VhwwbdnX12vGTzD2ggofq0XWzd0wEZ70Vx6p1IyPlwn3 = false;
    private String dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR = "";
    private String e1IHYfRNckEvpOWbFvMtuN7w9PEpZtVYShhIlzbQR8mwSxiOizA6OYtX7vMfGCUT = "";
    private Form form;
    private C0817a hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private KodularAdsCommission kodularAdsCommission;
    private KodularContentProtection kodularContentProtection;
    private String[] sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt;
    private boolean testMode = false;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KodularUnityAds(com.google.appinventor.components.runtime.ComponentContainer r9) {
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
            r2.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.e1IHYfRNckEvpOWbFvMtuN7w9PEpZtVYShhIlzbQR8mwSxiOizA6OYtX7vMfGCUT = r3
            r2 = r0
            r3 = 0
            r2.testMode = r3
            r2 = r0
            r3 = 0
            r2.cxDeivMnEpGbjLe4A1R1VhwwbdnX12vGTzD2ggofq0XWzd0wEZ70Vx6p1IyPlwn3 = r3
            r2 = r0
            r3 = 28
            java.lang.String[] r3 = new java.lang.String[r3]
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 0
            java.lang.String r6 = "at"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 1
            java.lang.String r6 = "be"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 2
            java.lang.String r6 = "bg"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 3
            java.lang.String r6 = "cy"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 4
            java.lang.String r6 = "cz"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 5
            java.lang.String r6 = "de"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 6
            java.lang.String r6 = "dk"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 7
            java.lang.String r6 = "ee"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 8
            java.lang.String r6 = "es"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 9
            java.lang.String r6 = "fi"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 10
            java.lang.String r6 = "fr"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 11
            java.lang.String r6 = "gb"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 12
            java.lang.String r6 = "gr"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 13
            java.lang.String r6 = "hr"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 14
            java.lang.String r6 = "hu"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 15
            java.lang.String r6 = "ie"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 16
            java.lang.String r6 = "it"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 17
            java.lang.String r6 = "lt"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 18
            java.lang.String r6 = "lu"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 19
            java.lang.String r6 = "lv"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 20
            java.lang.String r6 = "mt"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 21
            java.lang.String r6 = "nl"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 22
            java.lang.String r6 = "pl"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 23
            java.lang.String r6 = "pt"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 24
            java.lang.String r6 = "ro"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 25
            java.lang.String r6 = "se"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 26
            java.lang.String r6 = "si"
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 27
            java.lang.String r6 = "sk"
            r4[r5] = r6
            r2.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt = r3
            r2 = r0
            r3 = r1
            r2.container = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.activity = r3
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.form = r3
            r2 = r0
            com.google.appinventor.components.runtime.Form r2 = r2.form
            r3 = r0
            r2.registerForOnInitialize(r3)
            r2 = r0
            com.google.appinventor.components.runtime.KodularUnityAds$a r3 = new com.google.appinventor.components.runtime.KodularUnityAds$a
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r6 = 0
            r4.<init>(r5, r6)
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            java.lang.String r2 = "Unity Ads"
            java.lang.String r3 = "Kodular Unity Ads created"
            int r2 = android.util.Log.d(r2, r3)
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
            r5 = r0
            android.app.Activity r5 = r5.activity
            r4.<init>(r5)
            r2.kodularContentProtection = r3
            r2 = r0
            com.google.appinventor.components.runtime.util.KodularContentProtection r2 = r2.kodularContentProtection
            com.google.appinventor.components.runtime.KodularUnityAds$1 r3 = new com.google.appinventor.components.runtime.KodularUnityAds$1
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r4.<init>(r5)
            r2.setOnValidationResultListener(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularUnityAds.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    /* renamed from: com.google.appinventor.components.runtime.KodularUnityAds$a */
    class C0817a implements IUnityAdsExtendedListener {
        private /* synthetic */ KodularUnityAds hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        private C0817a(KodularUnityAds kodularUnityAds) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = kodularUnityAds;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C0817a(KodularUnityAds kodularUnityAds, byte b) {
            this(kodularUnityAds);
            byte b2 = b;
        }

        public final void onUnityAdsClick(String str) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AdOpened(str);
            int i = Log.i("Unity Ads", "Unity Ads: ad opened");
        }

        public final void onUnityAdsPlacementStateChanged(String str, UnityAds.PlacementState placementState, UnityAds.PlacementState placementState2) {
        }

        public final void onUnityAdsReady(String str) {
        }

        public final void onUnityAdsStart(String str) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AdStarted(str);
            int i = Log.i("Unity Ads", "Unity Ads: ad started");
        }

        public final void onUnityAdsFinish(String str, UnityAds.FinishState finishState) {
            UnityAds.FinishState finishState2 = finishState;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AdClosed(str, finishState2 == UnityAds.FinishState.SKIPPED, finishState2 == UnityAds.FinishState.COMPLETED);
            int i = Log.i("Unity Ads", "Unity Ads: ad closed");
        }

        public final void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String str) {
            UnityAds.UnityAdsError unityAdsError2 = unityAdsError;
            String str2 = str;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Error(String.valueOf(str2));
            int e = Log.e("Unity Ads", "Unity Ads: ".concat(String.valueOf(str2)));
        }
    }

    public void onInitialize() {
        MetaData metaData;
        boolean z;
        Random random;
        new MetaData(this.context);
        MetaData metaData2 = metaData;
        TelephonyManager telephonyManager = (TelephonyManager) this.container.$context().getSystemService("phone");
        TelephonyManager telephonyManager2 = telephonyManager;
        if (telephonyManager.getNetworkCountryIso() != null) {
            String networkCountryIso = telephonyManager2.getNetworkCountryIso();
            int i = Log.i("Unity Ads", "Current user country is: ".concat(String.valueOf(networkCountryIso)));
            boolean contains = Arrays.asList(this.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt).contains(networkCountryIso);
            int i2 = Log.i("Unity Ads", "Current user is in EUROPE: ".concat(String.valueOf(contains)));
            z = contains;
        } else {
            int e = Log.e("Unity Ads", "It was not possible to get the country code. We returned the value true as default to cover the EU privacy policy.");
            z = true;
        }
        if (z) {
            boolean z2 = metaData2.set("gdpr.consent", Boolean.FALSE);
        } else {
            boolean z3 = metaData2.set("gdpr.consent", Boolean.TRUE);
        }
        metaData2.commit();
        if (GameID() == null || GameID().isEmpty()) {
            Error("The game id can not be empty.");
            return;
        }
        new Random();
        if (random.nextFloat() <= this.kodularAdsCommission.getCommision("unity", "interstitial")) {
            this.cxDeivMnEpGbjLe4A1R1VhwwbdnX12vGTzD2ggofq0XWzd0wEZ70Vx6p1IyPlwn3 = true;
            UnityAds.initialize(this.container.$context(), KodularAdsUtil.UNITY_ADS_GAME_ID, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, TestMode());
            return;
        }
        this.cxDeivMnEpGbjLe4A1R1VhwwbdnX12vGTzD2ggofq0XWzd0wEZ70Vx6p1IyPlwn3 = false;
        UnityAds.initialize(this.container.$context(), GameID(), this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, TestMode());
    }

    @SimpleFunction(description = "Shows an ad to the user.")
    public void ShowAd() {
        this.kodularContentProtection.startContentValidation(this.form.getAppId());
    }

    @SimpleFunction(description = "Returns true if the ad is finished loading and can now be shown.")
    public boolean IsReady() {
        return UnityAds.isReady();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public String GameID() {
        return this.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR;
    }

    @DesignerProperty
    @SimpleProperty(userVisible = false)
    public void GameID(String str) {
        String str2 = str;
        this.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR = str2;
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

    @SimpleEvent(description = "Called when an ad request failed. The message will display the reason for why the ad failed.")
    public void Error(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Error", str);
    }

    @SimpleEvent(description = "Called when an ad was opened.")
    public void AdOpened(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdOpened", str);
    }

    @SimpleEvent(description = "Called when an ad was closed.")
    public void AdClosed(String str, boolean z, boolean z2) {
        Object[] objArr = new Object[3];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = Boolean.valueOf(z);
        Object[] objArr3 = objArr2;
        objArr3[2] = Boolean.valueOf(z2);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdClosed", objArr3);
    }

    @SimpleEvent(description = "Called when an ad was started.")
    public void AdStarted(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdStarted", str);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If you want to test the component then that this property to true. Then you will receive test ads.", userVisible = false)
    public void TestMode(boolean z) {
        boolean z2 = z;
        this.testMode = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean TestMode() {
        return this.testMode;
    }
}
