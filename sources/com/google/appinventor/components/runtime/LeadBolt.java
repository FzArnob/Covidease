package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import com.apptracker.android.listener.AppModuleListener;
import com.apptracker.android.track.AppTracker;
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
import com.google.appinventor.components.runtime.util.ads.KodularInterstitial;
import com.google.appinventor.components.runtime.util.ads.KodularRewardedVideo;
import java.util.Random;

@DesignerComponent(category = ComponentCategory.ADVERTISING, description = "LeadBolt is a non-visible component allowing you to show Network and Rewarded ads.", helpUrl = "https://docs.kodular.io/components/monetization/leadbolt/", iconName = "images/leadbolt.png", nonVisible = true, version = 2)
@UsesLibraries(libraries = "AppTracker.jar, AdMobAppTracker.jar, unity-ads.aar, unity-ads.jar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.ACCESS_NETWORK_STATE")
public class LeadBolt extends AndroidNonvisibleComponent implements Component {
    /* access modifiers changed from: private */
    public boolean DQyzcUACK1E7EOZ6u3x9ArvpeLM1Qtq6g7Sbq12R8AspY4PE6CpW0lfACU69Eund = false;
    private boolean YvCSL3NGR2QTRYJR2TwKp0VDudhjPNaOEVNDS5yB8p5C86x6HrXaW1BAbVOWs3Le = false;
    private ComponentContainer container;
    private Context context;
    private AppModuleListener hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private KodularInterstitial f458hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private KodularRewardedVideo f459hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private KodularAdsCommission kodularAdsCommission;
    /* access modifiers changed from: private */
    public boolean nvMxrgxLbjkSMxKVTHnXYUSqg15Nn9sVGKlCuWXpKupRQzyTNqJO1nkpaVIaCsw = false;
    private boolean qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = false;
    private String rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A = "";
    private boolean uylNXPbffZHriucQ1SAwUkLE9dP8WxwaWe5GnEXLipwyDpy1sV8NkWFXe3foYSTl = false;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LeadBolt(com.google.appinventor.components.runtime.ComponentContainer r9) {
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
            r2.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A = r3
            r2 = r0
            r3 = 0
            r2.uylNXPbffZHriucQ1SAwUkLE9dP8WxwaWe5GnEXLipwyDpy1sV8NkWFXe3foYSTl = r3
            r2 = r0
            r3 = 0
            r2.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = r3
            r2 = r0
            r3 = 0
            r2.DQyzcUACK1E7EOZ6u3x9ArvpeLM1Qtq6g7Sbq12R8AspY4PE6CpW0lfACU69Eund = r3
            r2 = r0
            r3 = 0
            r2.YvCSL3NGR2QTRYJR2TwKp0VDudhjPNaOEVNDS5yB8p5C86x6HrXaW1BAbVOWs3Le = r3
            r2 = r0
            r3 = 0
            r2.nvMxrgxLbjkSMxKVTHnXYUSqg15Nn9sVGKlCuWXpKupRQzyTNqJO1nkpaVIaCsw = r3
            r2 = r0
            com.google.appinventor.components.runtime.LeadBolt$3 r3 = new com.google.appinventor.components.runtime.LeadBolt$3
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r4.<init>(r5)
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            r3 = r1
            r2.container = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            com.google.appinventor.components.runtime.util.KodularAdsCommission r3 = new com.google.appinventor.components.runtime.util.KodularAdsCommission
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            android.content.Context r5 = r5.context
            r6 = r1
            com.google.appinventor.components.runtime.Form r6 = r6.$form()
            r4.<init>(r5, r6)
            r2.kodularAdsCommission = r3
            r2 = r0
            com.google.appinventor.components.runtime.util.ads.KodularInterstitial r3 = new com.google.appinventor.components.runtime.util.ads.KodularInterstitial
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            android.content.Context r5 = r5.context
            r4.<init>(r5)
            r2.f458hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            com.google.appinventor.components.runtime.util.ads.KodularInterstitial r2 = r2.f458hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.LeadBolt$1 r3 = new com.google.appinventor.components.runtime.LeadBolt$1
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r4.<init>(r5)
            r2.setOnAdsSwitcherListener(r3)
            r2 = r0
            com.google.appinventor.components.runtime.util.ads.KodularRewardedVideo r3 = new com.google.appinventor.components.runtime.util.ads.KodularRewardedVideo
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            android.content.Context r5 = r5.context
            r4.<init>(r5)
            r2.f459hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            com.google.appinventor.components.runtime.util.ads.KodularRewardedVideo r2 = r2.f459hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.LeadBolt$2 r3 = new com.google.appinventor.components.runtime.LeadBolt$2
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r4.<init>(r5)
            r2.setOnAdsSwitcherListener(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.LeadBolt.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    private void vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(String str) {
        String str2 = str;
        if (!this.uylNXPbffZHriucQ1SAwUkLE9dP8WxwaWe5GnEXLipwyDpy1sV8NkWFXe3foYSTl) {
            AppTracker.setModuleListener(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
            AppTracker.startSession(this.context, this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A, true);
            this.uylNXPbffZHriucQ1SAwUkLE9dP8WxwaWe5GnEXLipwyDpy1sV8NkWFXe3foYSTl = true;
            if (str2.contains("NetworkAd")) {
                LoadNetworkAd();
            } else if (str2.contains("RewardedAd")) {
                LoadRewardedAd();
            }
        }
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public void APIKey(String str) {
        String str2 = str;
        this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A = str2;
    }

    @DesignerProperty(defaultValue = "unity", editorType = "commission_interstitial_ads_network")
    @Deprecated
    @SimpleProperty(description = "Sets the ad network used to take the commission.")
    public void CommissionInterstitialAdsNetwork(String str) {
        this.f458hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.updateNetwork(str);
    }

    @DesignerProperty(defaultValue = "unity", editorType = "commission_rewarded_ads_network")
    @Deprecated
    @SimpleProperty(description = "Sets the ad network used to take the commission.")
    public void CommissionRewardedAdsNetwork(String str) {
        this.f459hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.updateNetwork(str);
    }

    @SimpleFunction(description = "Use this block to load a Network ad.")
    public void LoadNetworkAd() {
        Random random;
        new Random();
        if (random.nextFloat() <= this.kodularAdsCommission.getCommision("leadbolt", "interstitial") || this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE) {
            this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = true;
            this.f458hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.loadAd(false);
            return;
        }
        vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq("NetworkAd");
        if (AppTracker.isAdReady("inapp")) {
            AppTracker.loadModuleToCache(this.context, "inapp");
            return;
        }
        this.uylNXPbffZHriucQ1SAwUkLE9dP8WxwaWe5GnEXLipwyDpy1sV8NkWFXe3foYSTl = false;
        int i = Log.i("LeadBolt", "No ads ready");
    }

    @SimpleFunction(description = "Use this block after LoadNetworkAd to show the loaded ad.")
    public void ShowNetworkAd() {
        if (this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE) {
            if (!this.f458hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.showAd()) {
                LBFailed("", "Ad failed to show.", false);
                return;
            }
            this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = false;
            this.DQyzcUACK1E7EOZ6u3x9ArvpeLM1Qtq6g7Sbq12R8AspY4PE6CpW0lfACU69Eund = false;
        } else if (AppTracker.isAdReady("inapp")) {
            AppTracker.loadModule(this.context, "inapp");
        } else {
            this.uylNXPbffZHriucQ1SAwUkLE9dP8WxwaWe5GnEXLipwyDpy1sV8NkWFXe3foYSTl = false;
            int i = Log.i("LeadBolt", "No ads ready");
        }
    }

    @SimpleFunction(description = "Use this block to load a Rewarded Video ad.")
    public void LoadRewardedAd() {
        Random random;
        new Random();
        if (random.nextFloat() <= this.kodularAdsCommission.getCommision("leadbolt", "video")) {
            this.YvCSL3NGR2QTRYJR2TwKp0VDudhjPNaOEVNDS5yB8p5C86x6HrXaW1BAbVOWs3Le = true;
            this.f459hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.loadAd(false);
            return;
        }
        vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq("RewardedAd");
        if (AppTracker.isAdReady("reward")) {
            AppTracker.loadModuleToCache(this.context, "reward");
            return;
        }
        this.uylNXPbffZHriucQ1SAwUkLE9dP8WxwaWe5GnEXLipwyDpy1sV8NkWFXe3foYSTl = false;
        int i = Log.i("LeadBolt", "No ads ready");
    }

    @SimpleFunction(description = "Use this block after LoadRewardedAd to show the loaded ad.")
    public void ShowRewardedAd() {
        if (this.YvCSL3NGR2QTRYJR2TwKp0VDudhjPNaOEVNDS5yB8p5C86x6HrXaW1BAbVOWs3Le) {
            if (!this.f459hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.showAd()) {
                LBFailed("", "Ad failed to show.", false);
            } else {
                this.nvMxrgxLbjkSMxKVTHnXYUSqg15Nn9sVGKlCuWXpKupRQzyTNqJO1nkpaVIaCsw = false;
            }
        } else if (AppTracker.isAdReady("reward")) {
            AppTracker.loadModule(this.context, "reward");
        } else {
            this.uylNXPbffZHriucQ1SAwUkLE9dP8WxwaWe5GnEXLipwyDpy1sV8NkWFXe3foYSTl = false;
            int i = Log.i("LeadBolt", "No ads ready");
        }
    }

    @SimpleFunction(description = "Use this block after loading an ad to check whether it's ready to show.")
    public boolean IsNetworkAdReady() {
        return this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE ? this.DQyzcUACK1E7EOZ6u3x9ArvpeLM1Qtq6g7Sbq12R8AspY4PE6CpW0lfACU69Eund : AppTracker.isAdReady("inapp");
    }

    @SimpleFunction(description = "Use this block after loading an ad to check whether it's ready to show.")
    public boolean IsRewardedAdReady() {
        return this.YvCSL3NGR2QTRYJR2TwKp0VDudhjPNaOEVNDS5yB8p5C86x6HrXaW1BAbVOWs3Le ? this.nvMxrgxLbjkSMxKVTHnXYUSqg15Nn9sVGKlCuWXpKupRQzyTNqJO1nkpaVIaCsw : AppTracker.isAdReady("reward");
    }

    @SimpleEvent(description = "Event triggered when ads are shown")
    public void LBLoaded(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "LBLoaded", str);
    }

    @SimpleEvent(description = "Event triggered when ads are failed to load")
    public void LBFailed(String str, String str2, boolean z) {
        Object[] objArr = new Object[3];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        Object[] objArr3 = objArr2;
        objArr3[2] = Boolean.valueOf(z);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "LBFailed", objArr3);
    }

    @SimpleEvent(description = "Event triggered when ads are closed")
    public void LBClosed(String str, boolean z) {
        Object[] objArr = new Object[2];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = Boolean.valueOf(z);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "LBClosed", objArr2);
    }

    @SimpleEvent(description = "Event triggered when ads are clicked")
    public void LBClicked(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "LBClicked", str);
    }

    @SimpleEvent(description = "Event triggered when ads are loaded")
    public void LBCached(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "LBCached", str);
    }
}
