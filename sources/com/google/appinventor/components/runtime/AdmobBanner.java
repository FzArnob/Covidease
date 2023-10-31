package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
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
import com.google.appinventor.components.runtime.util.ads.KodularBanner;
import java.util.Random;

@DesignerComponent(category = ComponentCategory.ADVERTISING, description = "AdMob Banner component", iconName = "images/admob.png", nonVisible = false, version = 3)
@UsesLibraries(libraries = "play-services-ads.jar, play-services-ads.aar,customtabs.jar, customtabs.aar,play-services-ads-base.jar, play-services-ads-base.aar,play-services-ads-identifier.jar, play-services-ads-identifier.aar,play-services-ads-lite.jar, play-services-ads-lite.aar,play-services-basement.jar, play-services-basement.aar,play-services-gass.jar, play-services-gass.aar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.ACCESS_NETWORK_STATE, android.permission.ACCESS_COARSE_LOCATION, android.permission.ACCESS_FINE_LOCATION")
public final class AdmobBanner extends AndroidViewComponent implements OnDestroyListener, OnPauseListener, OnResumeListener {
    private boolean Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = true;
    private Activity activity;
    private String adUnitId;
    private ComponentContainer container;
    private Context context;
    private Form form;
    private AdView hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    /* access modifiers changed from: private */

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    public MakeroidDataProtection f336hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private KodularBanner f337hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private boolean isCompanion = false;
    private KodularAdsCommission kodularAdsCommission;
    private KodularContentProtection kodularContentProtection;
    private boolean l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j = false;
    private LinearLayout linearLayout;
    private boolean mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT = false;
    private boolean qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = false;
    private boolean testMode = false;

    static /* synthetic */ String B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(int i) {
        int i2 = i;
        if (i2 == 0) {
            return "Something happened internally; for instance, an invalid response was received from the ad server.";
        }
        if (i2 == 1) {
            return "The ad request was invalid; for instance, the ad unit ID was incorrect.";
        }
        if (i2 == 2) {
            return "The ad request was unsuccessful due to network connectivity.";
        }
        if (i2 == 3) {
            return "The ad request was successful, but no ad was returned due to lack of ad inventory.";
        }
        return "No error message found.";
    }

    static /* synthetic */ boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME() {
        return false;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AdmobBanner(com.google.appinventor.components.runtime.ComponentContainer r10) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r3 = r0
            r4 = r1
            r3.<init>(r4)
            r3 = r0
            r4 = 0
            r3.isCompanion = r4
            r3 = r0
            r4 = 0
            r3.testMode = r4
            r3 = r0
            r4 = 1
            r3.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = r4
            r3 = r0
            r4 = 0
            r3.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT = r4
            r3 = r0
            r4 = 0
            r3.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j = r4
            r3 = r0
            r4 = 0
            r3.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = r4
            r3 = r0
            r4 = r1
            r3.container = r4
            r3 = r0
            r4 = r1
            android.app.Activity r4 = r4.$context()
            r3.context = r4
            r3 = r0
            r4 = r1
            android.app.Activity r4 = r4.$context()
            r3.activity = r4
            r3 = r0
            r4 = r1
            com.google.appinventor.components.runtime.Form r4 = r4.$form()
            r3.form = r4
            r3 = r0
            com.google.appinventor.components.runtime.Form r3 = r3.form
            r4 = r0
            r3.registerForOnDestroy(r4)
            r3 = r0
            com.google.appinventor.components.runtime.Form r3 = r3.form
            r4 = r0
            r3.registerForOnPause(r4)
            r3 = r0
            com.google.appinventor.components.runtime.Form r3 = r3.form
            r4 = r0
            r3.registerForOnResume(r4)
            r3 = r0
            java.lang.String r4 = "ca-app-pub-3940256099942544/6300978111"
            r3.adUnitId = r4
            r3 = r0
            com.google.appinventor.components.runtime.util.KodularAdsCommission r4 = new com.google.appinventor.components.runtime.util.KodularAdsCommission
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r0
            android.content.Context r6 = r6.context
            r7 = r0
            com.google.appinventor.components.runtime.Form r7 = r7.form
            r5.<init>(r6, r7)
            r3.kodularAdsCommission = r4
            r3 = r0
            com.google.appinventor.components.runtime.util.ads.KodularBanner r4 = new com.google.appinventor.components.runtime.util.ads.KodularBanner
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r0
            android.content.Context r6 = r6.context
            r5.<init>(r6)
            r3.f337hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r4
            r3 = r0
            com.google.appinventor.components.runtime.util.ads.KodularBanner r3 = r3.f337hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.AdmobBanner$1 r4 = new com.google.appinventor.components.runtime.AdmobBanner$1
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r0
            r5.<init>(r6)
            r3.setOnAdsSwitcherListener(r4)
            r3 = r0
            com.google.appinventor.components.runtime.util.MakeroidDataProtection r4 = new com.google.appinventor.components.runtime.util.MakeroidDataProtection
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r0
            android.content.Context r6 = r6.context
            java.lang.String r7 = com.google.appinventor.components.runtime.util.MakeroidDataProtection.TYPE_AD_MOB_BANNER
            r5.<init>(r6, r7)
            r3.f336hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r4
            r3 = r0
            com.google.appinventor.components.runtime.util.MakeroidDataProtection r3 = r3.f336hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.AdmobBanner$2 r4 = new com.google.appinventor.components.runtime.AdmobBanner$2
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r0
            r5.<init>(r6)
            r3.setOnConsentListener(r4)
            r3 = r0
            com.google.appinventor.components.runtime.util.KodularContentProtection r4 = new com.google.appinventor.components.runtime.util.KodularContentProtection
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r0
            android.app.Activity r6 = r6.activity
            r5.<init>(r6)
            r3.kodularContentProtection = r4
            r3 = r0
            com.google.appinventor.components.runtime.util.KodularContentProtection r3 = r3.kodularContentProtection
            com.google.appinventor.components.runtime.AdmobBanner$3 r4 = new com.google.appinventor.components.runtime.AdmobBanner$3
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r0
            r5.<init>(r6)
            r3.setOnValidationResultListener(r4)
            r3 = r0
            com.google.appinventor.components.runtime.Form r3 = r3.form
            boolean r3 = r3 instanceof com.google.appinventor.components.runtime.ReplForm
            if (r3 == 0) goto L_0x00cb
            r3 = r0
            r4 = 1
            r3.isCompanion = r4
        L_0x00cb:
            r3 = r0
            r8 = r3
            r3 = r8
            r4 = r8
            r1 = r4
            android.widget.LinearLayout r4 = new android.widget.LinearLayout
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r1
            android.content.Context r6 = r6.context
            r5.<init>(r6)
            r3.linearLayout = r4
            r3 = r1
            android.widget.LinearLayout r3 = r3.linearLayout
            r4 = 0
            r3.setOrientation(r4)
            r3 = r1
            android.widget.LinearLayout r3 = r3.linearLayout
            android.widget.LinearLayout$LayoutParams r4 = new android.widget.LinearLayout$LayoutParams
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = -1
            r7 = -2
            r5.<init>(r6, r7)
            r3.setLayoutParams(r4)
            r3 = r1
            com.google.android.gms.ads.AdView r4 = new com.google.android.gms.ads.AdView
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r1
            android.content.Context r6 = r6.context
            r5.<init>(r6)
            r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r4
            r3 = r1
            com.google.android.gms.ads.AdView r3 = r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.android.gms.ads.AdSize r4 = com.google.android.gms.ads.AdSize.SMART_BANNER
            r3.setAdSize(r4)
            android.widget.RelativeLayout$LayoutParams r3 = new android.widget.RelativeLayout$LayoutParams
            r8 = r3
            r3 = r8
            r4 = r8
            r5 = -1
            r6 = -2
            r4.<init>(r5, r6)
            r8 = r3
            r3 = r8
            r4 = r8
            r2 = r4
            r4 = 12
            r3.addRule(r4)
            r3 = r1
            com.google.android.gms.ads.AdView r3 = r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r4 = r2
            r3.setLayoutParams(r4)
            r3 = r1
            com.google.android.gms.ads.AdView r3 = r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.AdmobBanner$4 r4 = new com.google.appinventor.components.runtime.AdmobBanner$4
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r1
            r5.<init>(r6)
            r3.setAdListener(r4)
            r3 = r1
            com.google.appinventor.components.runtime.util.MakeroidDataProtection r3 = r3.f336hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r4 = r1
            com.google.android.gms.ads.AdView r4 = r4.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r3.setAdView(r4)
            r3 = r1
            android.widget.LinearLayout r3 = r3.linearLayout
            r4 = r1
            com.google.android.gms.ads.AdView r4 = r4.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r3.addView(r4)
            r3 = r1
            com.google.appinventor.components.runtime.ComponentContainer r3 = r3.container
            r4 = r1
            r3.$add(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.AdmobBanner.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public final View getView() {
        return this.linearLayout;
    }

    @SimpleFunction(description = "Load a new AdMob Banner ad.")
    public final void LoadAd() {
        Random random;
        AdRequest.Builder builder;
        if (!this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB) {
            AdFailedToLoad(404, "Make sure you enabled the ad in the component properties.");
        } else if (this.testMode) {
            new AdRequest.Builder();
            AdRequest.Builder builder2 = builder;
            AdRequest.Builder builder3 = builder2;
            AdRequest.Builder addTestDevice = builder2.addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB");
            AdRequest.Builder addTestDevice2 = builder3.addTestDevice(KodularAdsUtil.guessSelfDeviceId(this.context));
            if (!this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setAdUnitId(KodularAdsUtil.ADMOB_BANNER_TEST_ID);
                this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j = true;
            }
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.loadAd(builder3.build());
        } else {
            if (!this.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setAdUnitId(this.adUnitId);
                this.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT = true;
            }
            new Random();
            float nextFloat = random.nextFloat();
            float commision = this.kodularAdsCommission.getCommision("admob", "banner");
            this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = false;
            this.kodularContentProtection.startContentValidation(this.form.getAppId());
        }
    }

    @DesignerProperty(defaultValue = "ca-app-pub-3940256099942544/6300978111", editorType = "string")
    @SimpleProperty(userVisible = false)
    public final void AdUnitId(String str) {
        String str2 = str;
        this.adUnitId = str2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Set Ad Unit ID")
    public final String AdUnitId() {
        return this.adUnitId;
    }

    @SimpleEvent(description = "Called when an ad request was loaded.")
    public final void AdLoaded() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdLoaded", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad request failed to load. The message will display the error code and error message.")
    public final void AdFailedToLoad(int i, String str) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdFailedToLoad", objArr2);
    }

    @SimpleEvent(description = "Called when an ad was opened.")
    public final void AdOpened() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdOpened", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad was closed.")
    public final void AdClosed() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdClosed", new Object[0]);
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "This property must be set to true to receive ads.")
    public final void AdEnabled(boolean z) {
        boolean z2 = z;
        this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public final boolean AdEnabled() {
        return this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If you want to test the component then that this property to true. Then you will receive test ads.", userVisible = false)
    public final void TestMode(boolean z) {
        boolean z2 = z;
        this.testMode = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public final boolean TestMode() {
        return this.testMode;
    }

    public final void onPause() {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.pause();
        }
    }

    public final void onResume() {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.resume();
        }
    }

    public final void onDestroy() {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.destroy();
        }
    }

    public final int Height() {
        return getView().getHeight();
    }

    public final void Height(int i) {
        this.container.setChildHeight(this, i);
    }

    public final int Width() {
        return getView().getWidth();
    }

    public final void Width(int i) {
        this.container.setChildWidth(this, i);
    }

    public final void HeightPercent(int i) {
    }

    public final void WidthPercent(int i) {
    }

    @DesignerProperty(defaultValue = "Data Protection", editorType = "string")
    @SimpleProperty(description = "The title for the consent dialog.")
    public final void ConsentTitle(String str) {
        this.f336hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTitle(str);
    }

    @SimpleProperty
    public final String ConsentTitle() {
        return this.f336hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getTitle();
    }

    @DesignerProperty(defaultValue = "Can we continue to use your data to tailor ads for you?", editorType = "string")
    @SimpleProperty(description = "The message for the consent dialog.")
    public final void ConsentMessage(String str) {
        this.f336hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMessage(str);
    }

    @SimpleProperty
    public final String ConsentMessage() {
        return this.f336hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMessage();
    }

    @SimpleFunction(description = "Deletes the user's consent. Useful if you want to test the consent dialog in development.")
    public final void RevokeConsent() {
        this.f336hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.RevokeConsent();
    }

    @SimpleEvent(description = "Event triggered when the consent was changed.")
    public final void OnConsentChanged(boolean z) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnConsentChanged", Boolean.valueOf(z));
    }

    @SimpleProperty(description = "Returns the current personalized consent. If true user has consent to personalized ads.")
    public final boolean PersonalizedResult() {
        return this.f336hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getPersonalizedResult();
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If enabled you will see the consent dialog no matter if you are located in Europe or not. Please use this option only in development.  If this setting is enabled ALL taken consents will not be saved.")
    public final void ConsentDevelopmentMode(boolean z) {
        this.f336hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setDevelopmentMode(z);
    }

    @SimpleProperty
    public final boolean ConsentDevelopmentMode() {
        return this.f336hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getDevelopmentMode();
    }
}
