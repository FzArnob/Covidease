package com.google.appinventor.components.runtime;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularChromeCustomTabsHelper;

@SimpleObject
@DesignerComponent(category = ComponentCategory.EXPERIMENTAL, description = "Chrome Custom Tabs component", iconName = "images/chrome.png", nonVisible = true, version = 2)
public final class MakeroidChromeCustomTabs extends AndroidNonvisibleComponent {
    private boolean AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl = true;
    private boolean UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5 = false;
    private String WT81i4At6dHne14KPL5TcdNiShzKisif1ehRA81016Xex9QKtWws9m2RXtqd3wpA;
    private Context context;
    private CustomTabsIntent.Builder hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private boolean nKfZQ1laYcwrzNEumUwCbmi2kaHgg3eE1c9SDtYVLTkiuRTWxcP8Pqqx3AbL5ow = true;
    private int op7m9tjL9FfXtQ4MVagpcEhDmFIa6xyIVDUYWgTs5INAROZGTkftwWcRnCCOYoFx = Component.COLOR_INDIGO;
    private boolean showTitle = true;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MakeroidChromeCustomTabs(com.google.appinventor.components.runtime.ComponentContainer r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = 1
            r2.showTitle = r3
            r2 = r0
            r3 = -12627531(0xffffffffff3f51b5, float:-2.543068E38)
            r2.op7m9tjL9FfXtQ4MVagpcEhDmFIa6xyIVDUYWgTs5INAROZGTkftwWcRnCCOYoFx = r3
            r2 = r0
            r3 = 0
            r2.UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5 = r3
            r2 = r0
            r3 = 1
            r2.AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl = r3
            r2 = r0
            r3 = 1
            r2.nKfZQ1laYcwrzNEumUwCbmi2kaHgg3eE1c9SDtYVLTkiuRTWxcP8Pqqx3AbL5ow = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            android.support.customtabs.CustomTabsIntent$Builder r3 = new android.support.customtabs.CustomTabsIntent$Builder
            r5 = r3
            r3 = r5
            r4 = r5
            r4.<init>()
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            java.lang.String r2 = "Chrome Custom Tabs"
            java.lang.String r3 = "Custom Tabs Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidChromeCustomTabs.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The URL to load in the custom tab. The URL must start with 'http://' or 'https://'")
    public final void Url(String str) {
        String str2 = str;
        this.WT81i4At6dHne14KPL5TcdNiShzKisif1ehRA81016Xex9QKtWws9m2RXtqd3wpA = str2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the URL.")
    public final String Url() {
        return this.WT81i4At6dHne14KPL5TcdNiShzKisif1ehRA81016Xex9QKtWws9m2RXtqd3wpA;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether or not to show the title.", userVisible = true)
    public final void ShowTitle(boolean z) {
        boolean z2 = z;
        this.showTitle = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return whether the title is shown or not.")
    public final boolean ShowTitle() {
        return this.showTitle;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether or not to hide the url bar on scrolling.", userVisible = false)
    public final void UrlBarHidingOnScroll(boolean z) {
        boolean z2 = z;
        this.UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5 = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return whether the url bar is hide on scrolling or not.")
    public final boolean UrlBarHidingOnScroll() {
        return this.UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether or not to add the default share menu item into the menu.", userVisible = false)
    public final void DefaultShareMenuItem(boolean z) {
        boolean z2 = z;
        this.AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return whether the default share menu item is added or not.")
    public final boolean DefaultShareMenuItem() {
        return this.AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether or not to enable instant apps.", userVisible = true)
    public final void InstantAppsEnabled(boolean z) {
        boolean z2 = z;
        this.nKfZQ1laYcwrzNEumUwCbmi2kaHgg3eE1c9SDtYVLTkiuRTWxcP8Pqqx3AbL5ow = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return whether the instant apps support is enabled or not.")
    public final boolean InstantAppsEnabled() {
        return this.nKfZQ1laYcwrzNEumUwCbmi2kaHgg3eE1c9SDtYVLTkiuRTWxcP8Pqqx3AbL5ow;
    }

    @DesignerProperty(defaultValue = "&HFF3F51B5", editorType = "color")
    @SimpleProperty(description = "Specifies the color of the toolbar.")
    public final void ToolbarColor(int i) {
        int i2 = i;
        this.op7m9tjL9FfXtQ4MVagpcEhDmFIa6xyIVDUYWgTs5INAROZGTkftwWcRnCCOYoFx = i2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the toolbar color")
    public final int ToolbarColor() {
        return this.op7m9tjL9FfXtQ4MVagpcEhDmFIa6xyIVDUYWgTs5INAROZGTkftwWcRnCCOYoFx;
    }

    @SimpleFunction(description = "Use this block to add a menu item with the given title and page link. The page can be as example 'https://www.instagram.com/kodular/'. If the user have Instagram installed,the page will then be opened in the official Instagram app. Else in the default browser.")
    public final void AddMenuItemOpenPage(String str, String str2) {
        Intent intent;
        new Intent("android.intent.action.VIEW", Uri.parse(str2));
        CustomTabsIntent.Builder addMenuItem = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addMenuItem(str, PendingIntent.getActivity(this.context, 0, intent, 0));
    }

    @SimpleFunction(description = "Use this block to add a menu item with the given title to open any app you want with the package name. The package name can be as example 'com.instagram.android' to open Instagram on the menu item click. If the app is not installed the menu item will not be added.")
    public final void AddMenuItemOpenApp(String str, String str2) {
        String str3 = str;
        Intent launchIntentForPackage = this.context.getPackageManager().getLaunchIntentForPackage(str2);
        Intent intent = launchIntentForPackage;
        if (launchIntentForPackage != null) {
            CustomTabsIntent.Builder addMenuItem = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addMenuItem(str3, PendingIntent.getActivity(this.context, 0, intent, 0));
        }
    }

    @SimpleFunction(description = "Use this block to open the custom tab.")
    public final void OpenCustomTab() {
        try {
            CustomTabsIntent.Builder startAnimations = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStartAnimations(this.context, 17432578, 17432579);
            CustomTabsIntent.Builder showTitle2 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setShowTitle(this.showTitle);
            CustomTabsIntent.Builder toolbarColor = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setToolbarColor(this.op7m9tjL9FfXtQ4MVagpcEhDmFIa6xyIVDUYWgTs5INAROZGTkftwWcRnCCOYoFx);
            if (this.UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5) {
                CustomTabsIntent.Builder enableUrlBarHiding = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.enableUrlBarHiding();
            }
            if (this.AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl) {
                CustomTabsIntent.Builder addDefaultShareMenuItem = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addDefaultShareMenuItem();
            }
            CustomTabsIntent.Builder instantAppsEnabled = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setInstantAppsEnabled(this.nKfZQ1laYcwrzNEumUwCbmi2kaHgg3eE1c9SDtYVLTkiuRTWxcP8Pqqx3AbL5ow);
            CustomTabsIntent build = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.build();
            String packageNameToUse = KodularChromeCustomTabsHelper.getPackageNameToUse(this.context);
            String str = packageNameToUse;
            if (packageNameToUse != null) {
                Intent intent = build.intent.setPackage(str);
            }
            build.launchUrl(this.context, Uri.parse(this.WT81i4At6dHne14KPL5TcdNiShzKisif1ehRA81016Xex9QKtWws9m2RXtqd3wpA));
        } catch (Exception e) {
            int e2 = Log.e("Chrome Custom Tabs", String.valueOf(e));
        }
    }
}
