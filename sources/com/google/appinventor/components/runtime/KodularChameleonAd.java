package com.google.appinventor.components.runtime;

import android.app.Activity;
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
import com.kodular.chameleon.ChameleonAds;

@DesignerComponent(category = ComponentCategory.INTERNAL, description = "write in ode", iconName = "images/chameleon.png", nonVisible = true, version = 1)
@UsesLibraries(libraries = "chameleon.aar, chameleon.jar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.ACCESS_NETWORK_STATE")
public class KodularChameleonAd extends AndroidNonvisibleComponent implements Component {
    private static final String LOG_TAG = "Kodular Chameleon Ad";
    private Activity activity;
    private ChameleonAds chameleonAds;
    private Form form;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KodularChameleonAd(com.google.appinventor.components.runtime.ComponentContainer r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.form = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.activity = r3
            r2 = r0
            com.kodular.chameleon.ChameleonAds r3 = new com.kodular.chameleon.ChameleonAds
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r0
            android.app.Activity r5 = r5.activity
            android.support.v7.app.AppCompatActivity r5 = (android.support.p003v7.app.AppCompatActivity) r5
            r4.<init>(r5)
            r2.chameleonAds = r3
            r2 = r0
            com.kodular.chameleon.ChameleonAds r2 = r2.chameleonAds
            com.google.appinventor.components.runtime.KodularChameleonAd$1 r3 = new com.google.appinventor.components.runtime.KodularChameleonAd$1
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r0
            r4.<init>(r5)
            r2.setOnAdLoadedListener(r3)
            r2 = r0
            com.kodular.chameleon.ChameleonAds r2 = r2.chameleonAds
            com.google.appinventor.components.runtime.KodularChameleonAd$2 r3 = new com.google.appinventor.components.runtime.KodularChameleonAd$2
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r0
            r4.<init>(r5)
            r2.setOnAdFailedToLoadListener(r3)
            r2 = r0
            com.kodular.chameleon.ChameleonAds r2 = r2.chameleonAds
            com.google.appinventor.components.runtime.KodularChameleonAd$3 r3 = new com.google.appinventor.components.runtime.KodularChameleonAd$3
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r0
            r4.<init>(r5)
            r2.setOnAdFailedToShowListener(r3)
            r2 = r0
            com.kodular.chameleon.ChameleonAds r2 = r2.chameleonAds
            com.google.appinventor.components.runtime.KodularChameleonAd$4 r3 = new com.google.appinventor.components.runtime.KodularChameleonAd$4
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r0
            r4.<init>(r5)
            r2.setOnAdClosed(r3)
            java.lang.String r2 = "Kodular Chameleon Ad"
            java.lang.String r3 = "Kodular Chameleon Ad Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularChameleonAd.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Load a new interstitial chameleon ad.")
    public void LoadAd() {
        this.chameleonAds.loadChameleonAd();
    }

    @SimpleFunction(description = "Show a chameleon interstitial ad after it was loaded.")
    public void ShowAd() {
        int i = Log.i(LOG_TAG, "ShowAd was called.");
        this.chameleonAds.showChameleonAd();
    }

    @SimpleEvent(description = "Event triggered when ads are loaded.")
    public void AdLoaded() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdLoaded", new Object[0]);
    }

    @SimpleEvent(description = "Event triggered when ads are closed.")
    public void AdClosed() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdClosed", new Object[0]);
    }

    @SimpleEvent(description = "Event triggered when ads failed to load. Here is the list with all possible error codes (int) and error messages (string): '1' = The before loaded ad was not finished. It is not allowed to load a new one. The first must be in finished loading process. '2' = Ad failed to load. No internet connection available. '3' = Please enter a valid chameleon 'App ID'. '4' = Please enter a valid chameleon 'User ID'. '5' = UNKNOWN_WEB_VIEW_ERROR. '6' = There was an error validating the SSL Certificate.")
    public void AdFailedToLoad(String str, int i) {
        Object[] objArr = new Object[2];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = Integer.valueOf(i);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdFailedToLoad", objArr2);
    }

    @SimpleEvent(description = "Event triggered when ads failed to show. Here is the list with all possible error codes (int) and error messages (string): '1' = UNKNOWN_ERROR_ON_SHOW_AD. '2' = The ad must first be loaded. After loading finished the ad can be shown.")
    public void AdFailedToShow(String str, int i) {
        Object[] objArr = new Object[2];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = Integer.valueOf(i);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdFailedToShow", objArr2);
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "Input here your own Chameleon Ad 'App ID' (request one at my.kodular.io).")
    public void AppId(String str) {
        this.chameleonAds.setAppId(str);
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "Input here your own Chameleon Ad 'User ID' (get yours at my.kodular.io).")
    public void UserId(String str) {
        this.chameleonAds.setUserId(str);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If set to true, the device will receive test ads. This option must be set before you load a new ad.")
    public void TestMode(boolean z) {
        this.chameleonAds.setTestMode(z);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean TestMode() {
        return this.chameleonAds.isTestMode();
    }
}
