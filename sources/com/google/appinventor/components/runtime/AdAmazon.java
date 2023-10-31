package com.google.appinventor.components.runtime;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import com.amazon.device.ads.Ad;
import com.amazon.device.ads.AdError;
import com.amazon.device.ads.AdLayout;
import com.amazon.device.ads.AdListener;
import com.amazon.device.ads.AdProperties;
import com.amazon.device.ads.AdRegistration;
import com.amazon.device.ads.AdTargetingOptions;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.KodularContentProtection;

@DesignerComponent(category = ComponentCategory.ADVERTISING, description = "AdAmazon component allows you to monetize your app. You must have a valid publisher id that can be obtained from https://developer.amazon.com. If your publisher id is invalid, the AdAmazon banner will not display on the emulator or the device.", helpUrl = "https://docs.kodular.io/components/monetization/amazon-banner/", iconName = "images/amazon.png", version = 1)
@UsesLibraries(libraries = "amazon-ads.jar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.ACCESS_NETWORK_STATE, android.permission.ACCESS_FINE_LOCATION, android.permission.ACCESS_COARSE_LOCATION, android.permission.ACCESS_WIFI_STATE")
public class AdAmazon extends AndroidViewComponent implements AdListener, OnDestroyListener {
    private boolean B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private String KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = "AmazonPublisherId";
    private String TAG = "AdAmazon";
    private final Handler androidUIHandler;
    private Form form;
    private boolean havePermission;
    private AdLayout hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private boolean f332hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = true;
    private KodularContentProtection kodularContentProtection;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AdAmazon(com.google.appinventor.components.runtime.ComponentContainer r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            r3 = 1
            r2.f332hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            java.lang.String r3 = "AdAmazon"
            r2.TAG = r3
            r2 = r0
            java.lang.String r3 = "AmazonPublisherId"
            r2.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = r3
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
            r3 = 1
            r2.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r3
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.form = r3
            r2 = r0
            com.amazon.device.ads.AdLayout r3 = new com.amazon.device.ads.AdLayout
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r1
            android.app.Activity r5 = r5.$context()
            com.amazon.device.ads.AdSize r6 = com.amazon.device.ads.AdSize.SIZE_320x50
            r4.<init>(r5, r6)
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            com.amazon.device.ads.AdLayout r2 = r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            android.widget.LinearLayout$LayoutParams r3 = new android.widget.LinearLayout$LayoutParams
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = -1
            r6 = -2
            r4.<init>(r5, r6)
            r2.setLayoutParams(r3)
            r2 = r1
            r3 = r0
            r2.$add(r3)
            r2 = r0
            com.amazon.device.ads.AdLayout r2 = r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r3 = r0
            r2.setListener(r3)
            r2 = r0
            r3 = 1
            r2.TestMode(r3)
            r2 = r0
            r3 = 1
            r2.RefreshAd(r3)
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
            com.google.appinventor.components.runtime.AdAmazon$1 r3 = new com.google.appinventor.components.runtime.AdAmazon$1
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r4.<init>(r5)
            r2.setOnValidationResultListener(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.AdAmazon.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @DesignerProperty(defaultValue = "AmazonPublisherId", editorType = "string")
    @SimpleProperty(description = "Sets the Amazon Ad Publisher Id and refreshes the ad.")
    public void PublisherId(String str) {
        String trim = str.trim();
        this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = trim;
        AdRegistration.setAppKey(trim);
        RefreshAd(true);
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "Refreshes the ad.")
    public void RefreshAd(boolean z) {
        Runnable runnable;
        boolean z2 = z;
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = z2;
        if (!this.havePermission) {
            new Runnable(this) {
                final /* synthetic */ AdAmazon hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void run() {
                    PermissionResultHandler permissionResultHandler;
                    new PermissionResultHandler(this) {
                        private /* synthetic */ C05622 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                        }

                        public final void HandlePermissionResponse(String str, boolean z) {
                            String str2 = str;
                            if (z) {
                                boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = AdAmazon.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, true);
                                AdAmazon.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).startContentValidation(AdAmazon.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).getAppId());
                                return;
                            }
                            boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3 = AdAmazon.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, false);
                            AdAmazon.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "RefreshAd", "android.permission.ACCESS_FINE_LOCATION");
                        }
                    };
                    AdAmazon.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).askPermission("android.permission.ACCESS_FINE_LOCATION", permissionResultHandler);
                }
            };
            boolean post = this.androidUIHandler.post(runnable);
            return;
        }
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(z2);
    }

    private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(boolean z) {
        StringBuilder sb;
        AdTargetingOptions adTargetingOptions;
        if (z) {
            try {
                new AdTargetingOptions();
                boolean loadAd = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.loadAd(adTargetingOptions);
            } catch (PermissionException e) {
                this.form.dispatchPermissionDeniedEvent((Component) this, "RefreshAd", e);
                AdFailedToLoad("PERMISSION_ERROR", "Permission \"android.permission.ACCESS_FINE_LOCATION\" was denied but needed.");
            } catch (Exception e2) {
                new StringBuilder();
                int e3 = Log.e("AdAmazon", sb.append(e2.getMessage()).toString());
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String PublisherId() {
        return this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void TestMode(boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        this.f332hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = z3;
        AdRegistration.enableLogging(z2);
        AdRegistration.enableTesting(z2);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean TestMode() {
        return this.f332hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public View getView() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public void onDestroy() {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.destroy();
        }
    }

    public void onAdCollapsed(Ad ad) {
        Ad ad2 = ad;
        int i = Log.i(this.TAG, "Ad collapsed.");
        AdDismissed();
    }

    public void onAdDismissed(Ad ad) {
        Ad ad2 = ad;
        int i = Log.i(this.TAG, "Ad Dismissed.");
        AdDismissed();
    }

    public void onAdExpanded(Ad ad) {
        Ad ad2 = ad;
        int i = Log.i(this.TAG, "Ad expanded.");
        AdExpanded();
    }

    public void onAdFailedToLoad(Ad ad, AdError adError) {
        StringBuilder sb;
        Ad ad2 = ad;
        AdError adError2 = adError;
        new StringBuilder("Ad failed to load. Code: ");
        int w = Log.w("AmazonInterstitialAds", sb.append(adError2.getCode()).append(", Message: ").append(adError2.getMessage()).toString());
        AdFailedToLoad(adError2.getCode().toString(), adError2.getMessage());
    }

    public void onAdLoaded(Ad ad, AdProperties adProperties) {
        StringBuilder sb;
        Ad ad2 = ad;
        String str = this.TAG;
        new StringBuilder();
        int i = Log.i(str, sb.append(adProperties.getAdType().toString()).append(" ad loaded successfully.").toString());
        AdLoaded();
    }

    @SimpleEvent(description = "Event to detect that a ad was dismissed.")
    public void AdDismissed() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdDismissed", new Object[0]);
    }

    @SimpleEvent(description = "Event to detect that a ad was expanded.")
    public void AdExpanded() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdExpanded", new Object[0]);
    }

    @SimpleEvent(description = "Event to detect that the try to load a ad was not successful.")
    public void AdFailedToLoad(String str, String str2) {
        Object[] objArr = new Object[2];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdFailedToLoad", objArr2);
    }

    @SimpleEvent(description = "Event to detect that a ad was loaded.")
    public void AdLoaded() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdLoaded", new Object[0]);
    }

    public int Height() {
        return getView().getHeight();
    }

    public void Height(int i) {
        this.container.setChildHeight(this, i);
    }

    public int Width() {
        return getView().getWidth();
    }

    public void Width(int i) {
        this.container.setChildWidth(this, i);
    }

    public void HeightPercent(int i) {
    }

    public void WidthPercent(int i) {
    }
}
