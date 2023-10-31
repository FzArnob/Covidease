package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.wooplr.spotlight.SpotlightView;
import com.wooplr.spotlight.prefs.PreferencesManager;
import com.wooplr.spotlight.utils.SpotlightListener;

@SimpleObject
@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "Spotlight component", iconName = "images/spotlight.png", nonVisible = true, version = 2)
@UsesLibraries(libraries = "Spotlight.aar, Spotlight.jar")
public final class MakeroidSpotlight extends AndroidNonvisibleComponent implements OnOrientationChangeListener {
    private int AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl = 32;
    private boolean AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd = true;
    private Typeface B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    /* renamed from: B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T  reason: collision with other field name */
    private AndroidViewComponent f489B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private String G7Lxwqvaa0zcPMEYmgjECqcap18lY2TYRMrZOi1cD4z0oU809LnjVuckWEr78wl;
    private boolean JTEvmldvMjbbtPPlVS4hmZghOoRNnXC0kZOUUAZdwkVNl1VM5pL0vCTYv5HQZ7AX = true;
    private int LVnP8NaPYXRgwZHgDK7PHMVEgcKwsNvZv2AHicCDg6yGIfguFikZkwwgr0dhWzJE = 0;
    private String Qo6hTp4u7pOnq5WtKfNdQM86FuI5n4weAQCyrgAeimDlOrP2dP7U8KFwjrDIHyb;
    private boolean RC7PBJGdnqEffr8752ypFkbK8qZYkmQ3ci6maWfntRZXmeHa8bLhdKUgkXcpRo6T = true;
    /* access modifiers changed from: private */
    public boolean Ta6bjdQXoFeEb44hWQ7kTTTXw2rT1LHw6UX7lms7WU7H0AkpETH9D9EhELUfywQi = false;
    private int UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5 = -1107296256;
    private int YgDmThmc2Ht6J8LKfKFuGtMLp2AoFjdDlIONA2izriJdICsKCPKMX3dYEj8K1z4k = -1;
    private Activity activity;
    private int cOmDbOC978RubVhXjun4VkHg9OxeO5ZzRCTQEv8rZa8E7YdcVv7aSE4TjAXwfuwN = 0;
    private Context context;
    private Form form;
    private long hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = 400;
    private Typeface hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private SpotlightView f490hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private PreferencesManager f491hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int iA1fsPSZHTCVXA414XY2edBmEFVpo23ZLLJ3ntPGDoZ3Lc1O8L7tucf7fjSxdGWm = 16;
    private boolean jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH = true;
    private int nKfZQ1laYcwrzNEumUwCbmi2kaHgg3eE1c9SDtYVLTkiuRTWxcP8Pqqx3AbL5ow = Component.COLOR_INDIGO;
    private boolean pFeTJgO2w7vELkZyStZDj0uZpMYRqdjcmMjC2zcPDquoynj4tIsgJjD3RDJtFf88 = true;
    private int q2q4oDuUajVwr2T7b6DILrrBhrCqmElgSd3ODKsAFi8uEX2COWatdRT7gov0FlS5 = 20;
    private long qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = 400;
    private int seAn8f9TucJq5ZQrZ6xvA6wzyVfqYrHR0kVGH9g6Rg5gdXevmQRBQv2iJqrzi5Rz = Component.COLOR_INDIGO;
    private long vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = 400;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MakeroidSpotlight(com.google.appinventor.components.runtime.ComponentContainer r9) {
        /*
            r8 = this;
            r1 = r8
            r2 = r9
            r3 = r1
            r4 = r2
            com.google.appinventor.components.runtime.Form r4 = r4.$form()
            r3.<init>(r4)
            r3 = r1
            r4 = -1107296256(0xffffffffbe000000, float:-0.125)
            r3.UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5 = r4
            r3 = r1
            r4 = 32
            r3.AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl = r4
            r3 = r1
            r4 = -12627531(0xffffffffff3f51b5, float:-2.543068E38)
            r3.nKfZQ1laYcwrzNEumUwCbmi2kaHgg3eE1c9SDtYVLTkiuRTWxcP8Pqqx3AbL5ow = r4
            r3 = r1
            r4 = 16
            r3.iA1fsPSZHTCVXA414XY2edBmEFVpo23ZLLJ3ntPGDoZ3Lc1O8L7tucf7fjSxdGWm = r4
            r3 = r1
            r4 = -1
            r3.YgDmThmc2Ht6J8LKfKFuGtMLp2AoFjdDlIONA2izriJdICsKCPKMX3dYEj8K1z4k = r4
            r3 = r1
            r4 = -12627531(0xffffffffff3f51b5, float:-2.543068E38)
            r3.seAn8f9TucJq5ZQrZ6xvA6wzyVfqYrHR0kVGH9g6Rg5gdXevmQRBQv2iJqrzi5Rz = r4
            r3 = r1
            r4 = 1
            r3.RC7PBJGdnqEffr8752ypFkbK8qZYkmQ3ci6maWfntRZXmeHa8bLhdKUgkXcpRo6T = r4
            r3 = r1
            r4 = 1
            r3.JTEvmldvMjbbtPPlVS4hmZghOoRNnXC0kZOUUAZdwkVNl1VM5pL0vCTYv5HQZ7AX = r4
            r3 = r1
            r4 = 1
            r3.pFeTJgO2w7vELkZyStZDj0uZpMYRqdjcmMjC2zcPDquoynj4tIsgJjD3RDJtFf88 = r4
            r3 = r1
            r4 = 1
            r3.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH = r4
            r3 = r1
            r4 = 400(0x190, double:1.976E-321)
            r3.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = r4
            r3 = r1
            r4 = 400(0x190, double:1.976E-321)
            r3.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = r4
            r3 = r1
            r4 = 400(0x190, double:1.976E-321)
            r3.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = r4
            r3 = r1
            r4 = 20
            r3.q2q4oDuUajVwr2T7b6DILrrBhrCqmElgSd3ODKsAFi8uEX2COWatdRT7gov0FlS5 = r4
            r3 = r1
            r4 = 0
            r3.LVnP8NaPYXRgwZHgDK7PHMVEgcKwsNvZv2AHicCDg6yGIfguFikZkwwgr0dhWzJE = r4
            r3 = r1
            r4 = 0
            r3.cOmDbOC978RubVhXjun4VkHg9OxeO5ZzRCTQEv8rZa8E7YdcVv7aSE4TjAXwfuwN = r4
            r3 = r1
            r4 = 1
            r3.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd = r4
            r3 = r1
            r4 = 0
            r3.Ta6bjdQXoFeEb44hWQ7kTTTXw2rT1LHw6UX7lms7WU7H0AkpETH9D9EhELUfywQi = r4
            r3 = r1
            r4 = r2
            com.google.appinventor.components.runtime.Form r4 = r4.$form()
            r3.form = r4
            r3 = r1
            r4 = r2
            android.app.Activity r4 = r4.$context()
            r3.activity = r4
            r3 = r1
            r4 = r2
            android.app.Activity r4 = r4.$context()
            r3.context = r4
            r3 = r1
            com.wooplr.spotlight.prefs.PreferencesManager r4 = new com.wooplr.spotlight.prefs.PreferencesManager
            r7 = r4
            r4 = r7
            r5 = r7
            r6 = r1
            android.content.Context r6 = r6.context
            r5.<init>(r6)
            r3.f491hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r4
            r3 = r1
            android.content.Context r3 = r3.context
            com.google.appinventor.components.runtime.util.TextViewUtil.setContext(r3)
            r3 = r2
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r4 = r1
            r3.registerForOnOrientationChangeListener(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidSpotlight.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public final void onOrientationChange() {
        if (this.Ta6bjdQXoFeEb44hWQ7kTTTXw2rT1LHw6UX7lms7WU7H0AkpETH9D9EhELUfywQi && this.f490hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.f490hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.removeSpotlightView(false);
            ShowSpotlight();
        }
    }

    @DesignerProperty(defaultValue = "", editorType = "component")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The component to show in the spotlight.")
    public final void Component(AndroidViewComponent androidViewComponent) {
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        this.f489B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = androidViewComponent2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the component.")
    public final AndroidViewComponent Component() {
        return this.f489B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    }

    @DesignerProperty(defaultValue = "&Hbe000000", editorType = "color")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Spotlight mask color")
    public final void MaskColor(int i) {
        int i2 = i;
        this.UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5 = i2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the Spotlight mask color.")
    public final int MaskColor() {
        return this.UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Spotlight heading text.")
    public final void HeadingText(String str) {
        String str2 = str;
        this.G7Lxwqvaa0zcPMEYmgjECqcap18lY2TYRMrZOi1cD4z0oU809LnjVuckWEr78wl = str2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the Spotlight heading text.")
    public final String HeadingText() {
        return this.G7Lxwqvaa0zcPMEYmgjECqcap18lY2TYRMrZOi1cD4z0oU809LnjVuckWEr78wl;
    }

    @DesignerProperty(defaultValue = "32", editorType = "integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Spotlight heading size.")
    public final void HeadingTextSize(int i) {
        int i2 = i;
        this.AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl = i2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the Spotlight heading text size.")
    public final int HeadingTextSize() {
        return this.AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl;
    }

    @DesignerProperty(defaultValue = "&HFF3F51B5", editorType = "color")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Spotlight heading text color.")
    public final void HeadingTextColor(int i) {
        int i2 = i;
        this.nKfZQ1laYcwrzNEumUwCbmi2kaHgg3eE1c9SDtYVLTkiuRTWxcP8Pqqx3AbL5ow = i2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the Spotlight heading text color.")
    public final int HeadingTextColor() {
        return this.nKfZQ1laYcwrzNEumUwCbmi2kaHgg3eE1c9SDtYVLTkiuRTWxcP8Pqqx3AbL5ow;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Spotlight subheading text.")
    public final void SubheadingText(String str) {
        String str2 = str;
        this.Qo6hTp4u7pOnq5WtKfNdQM86FuI5n4weAQCyrgAeimDlOrP2dP7U8KFwjrDIHyb = str2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the Spotlight subheading text.")
    public final String SubheadingText() {
        return this.Qo6hTp4u7pOnq5WtKfNdQM86FuI5n4weAQCyrgAeimDlOrP2dP7U8KFwjrDIHyb;
    }

    @DesignerProperty(defaultValue = "16", editorType = "integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Spotlight subheading text size.")
    public final void SubheadingTextSize(int i) {
        int i2 = i;
        this.iA1fsPSZHTCVXA414XY2edBmEFVpo23ZLLJ3ntPGDoZ3Lc1O8L7tucf7fjSxdGWm = i2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the Spotlight subheading text size.")
    public final int SubheadingTextSize() {
        return this.iA1fsPSZHTCVXA414XY2edBmEFVpo23ZLLJ3ntPGDoZ3Lc1O8L7tucf7fjSxdGWm;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Spotlight subheading text color.")
    public final void SubheadingTextColor(int i) {
        int i2 = i;
        this.YgDmThmc2Ht6J8LKfKFuGtMLp2AoFjdDlIONA2izriJdICsKCPKMX3dYEj8K1z4k = i2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the Spotlight subheading text color.")
    public final int SubheadingTextColor() {
        return this.YgDmThmc2Ht6J8LKfKFuGtMLp2AoFjdDlIONA2izriJdICsKCPKMX3dYEj8K1z4k;
    }

    @DesignerProperty(defaultValue = "&HFF3F51B5", editorType = "color")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Spotlight line and arc color.")
    public final void LineAndArcColor(int i) {
        int i2 = i;
        this.seAn8f9TucJq5ZQrZ6xvA6wzyVfqYrHR0kVGH9g6Rg5gdXevmQRBQv2iJqrzi5Rz = i2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the Spotlight line and arc color.")
    public final int LineAndArcColor() {
        return this.seAn8f9TucJq5ZQrZ6xvA6wzyVfqYrHR0kVGH9g6Rg5gdXevmQRBQv2iJqrzi5Rz;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Dismiss the spotlight on back pressed.")
    public final void DismissOnBackPress(boolean z) {
        boolean z2 = z;
        this.RC7PBJGdnqEffr8752ypFkbK8qZYkmQ3ci6maWfntRZXmeHa8bLhdKUgkXcpRo6T = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return whether or not DismissOnBackPress is enabled.")
    public final boolean DismissOnBackPress() {
        return this.RC7PBJGdnqEffr8752ypFkbK8qZYkmQ3ci6maWfntRZXmeHa8bLhdKUgkXcpRo6T;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Dismiss the spotlight on touch")
    public final void DismissOnTouch(boolean z) {
        boolean z2 = z;
        this.JTEvmldvMjbbtPPlVS4hmZghOoRNnXC0kZOUUAZdwkVNl1VM5pL0vCTYv5HQZ7AX = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return whether or not DismissOnTouch is enabled.")
    public final boolean DismissOnTouch() {
        return this.JTEvmldvMjbbtPPlVS4hmZghOoRNnXC0kZOUUAZdwkVNl1VM5pL0vCTYv5HQZ7AX;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Dismiss spotlight on touch after spotlight is completely visible.")
    public final void EnableDismissAfterShown(boolean z) {
        boolean z2 = z;
        this.pFeTJgO2w7vELkZyStZDj0uZpMYRqdjcmMjC2zcPDquoynj4tIsgJjD3RDJtFf88 = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return whether or not EnableDismissAfterShown is enabled.")
    public final boolean EnableDismissAfterShown() {
        return this.pFeTJgO2w7vELkZyStZDj0uZpMYRqdjcmMjC2zcPDquoynj4tIsgJjD3RDJtFf88;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Enable reveal animation (Only for Lollipop and above).")
    public final void EnableRevealAnimation(boolean z) {
        boolean z2 = z;
        this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return whether or not EnableRevealAnimation is enabled.")
    public final boolean EnableRevealAnimation() {
        return this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH;
    }

    @DesignerProperty(defaultValue = "400", editorType = "integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Intro animation duration (For Reveal and Fadein).")
    public final void IntroAnimationDuration(long j) {
        long j2 = j;
        this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = j2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the IntroAnimationDuration.")
    public final long IntroAnimationDuration() {
        return this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
    }

    @DesignerProperty(defaultValue = "400", editorType = "integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Fade in animation duration for spotlight text (Heading and Sub-heading).")
    public final void FadeinTextDuration(long j) {
        long j2 = j;
        this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = j2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the FadeinTextDuration.")
    public final long FadeinTextDuration() {
        return this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;
    }

    @DesignerProperty(defaultValue = "400", editorType = "integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Line animation duration")
    public final void LineAnimationDuration(long j) {
        long j2 = j;
        this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = j2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the LineAnimationDuration.")
    public final long LineAnimationDuration() {
        return this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE;
    }

    @DesignerProperty(defaultValue = "20", editorType = "integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The padding for the circle spotlight. Default is '20'.")
    public final void CirclePadding(int i) {
        int i2 = i;
        this.q2q4oDuUajVwr2T7b6DILrrBhrCqmElgSd3ODKsAFi8uEX2COWatdRT7gov0FlS5 = i2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public final int CirclePadding() {
        return this.q2q4oDuUajVwr2T7b6DILrrBhrCqmElgSd3ODKsAFi8uEX2COWatdRT7gov0FlS5;
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public final void FontHeadingTypeface(int i) {
        int i2 = i;
        this.LVnP8NaPYXRgwZHgDK7PHMVEgcKwsNvZv2AHicCDg6yGIfguFikZkwwgr0dhWzJE = i2;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = TextViewUtil.getTitleBarTypeFace(i2);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public final int FontHeadingTypeface() {
        return this.LVnP8NaPYXRgwZHgDK7PHMVEgcKwsNvZv2AHicCDg6yGIfguFikZkwwgr0dhWzJE;
    }

    @DesignerProperty(defaultValue = "", editorType = "asset", propertyType = "advanced")
    @SimpleProperty(description = "Set a custom font.")
    public final void FontHeadingTypefaceImport(String str) {
        String str2 = str;
        if (str2 != null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = TextViewUtil.getTitleBarCustomTypeFace(this.form, str2);
        }
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public final void FontSubheadingTypeface(int i) {
        int i2 = i;
        this.cOmDbOC978RubVhXjun4VkHg9OxeO5ZzRCTQEv8rZa8E7YdcVv7aSE4TjAXwfuwN = i2;
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = TextViewUtil.getTitleBarTypeFace(i2);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public final int FontSubheadingTypeface() {
        return this.cOmDbOC978RubVhXjun4VkHg9OxeO5ZzRCTQEv8rZa8E7YdcVv7aSE4TjAXwfuwN;
    }

    @DesignerProperty(defaultValue = "", editorType = "asset", propertyType = "advanced")
    @SimpleProperty(description = "Set a custom font.")
    public final void FontSubheadingTypefaceImport(String str) {
        String str2 = str;
        if (str2 != null) {
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = TextViewUtil.getTitleBarCustomTypeFace(this.form, str2);
        }
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public final void ShowTargetArc(boolean z) {
        boolean z2 = z;
        this.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If set to true you will see a half round circle below the spotlight circle.")
    public final boolean ShowTargetArc() {
        return this.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd;
    }

    @SimpleFunction(description = "Use this block to show the spotlight.")
    public final void ShowSpotlight() {
        SpotlightView.Builder builder;
        SpotlightListener spotlightListener;
        this.f491hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.resetAll();
        new SpotlightView.Builder(this.activity);
        new SpotlightListener(this) {
            private /* synthetic */ MakeroidSpotlight hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onUserClicked(SpotlightView spotlightView, String str) {
                SpotlightView spotlightView2 = spotlightView;
                String str2 = str;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Clicked();
                boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Ta6bjdQXoFeEb44hWQ7kTTTXw2rT1LHw6UX7lms7WU7H0AkpETH9D9EhELUfywQi = false;
            }
        };
        this.f490hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = builder.maskColor(this.UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5).headingTvText(this.G7Lxwqvaa0zcPMEYmgjECqcap18lY2TYRMrZOi1cD4z0oU809LnjVuckWEr78wl).headingTvColor(this.nKfZQ1laYcwrzNEumUwCbmi2kaHgg3eE1c9SDtYVLTkiuRTWxcP8Pqqx3AbL5ow).headingTvSize(this.AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl).subHeadingTvText(this.Qo6hTp4u7pOnq5WtKfNdQM86FuI5n4weAQCyrgAeimDlOrP2dP7U8KFwjrDIHyb).subHeadingTvColor(this.YgDmThmc2Ht6J8LKfKFuGtMLp2AoFjdDlIONA2izriJdICsKCPKMX3dYEj8K1z4k).subHeadingTvSize(this.iA1fsPSZHTCVXA414XY2edBmEFVpo23ZLLJ3ntPGDoZ3Lc1O8L7tucf7fjSxdGWm).lineAndArcColor(this.seAn8f9TucJq5ZQrZ6xvA6wzyVfqYrHR0kVGH9g6Rg5gdXevmQRBQv2iJqrzi5Rz).dismissOnBackPress(this.RC7PBJGdnqEffr8752ypFkbK8qZYkmQ3ci6maWfntRZXmeHa8bLhdKUgkXcpRo6T).dismissOnTouch(this.JTEvmldvMjbbtPPlVS4hmZghOoRNnXC0kZOUUAZdwkVNl1VM5pL0vCTYv5HQZ7AX).enableDismissAfterShown(this.pFeTJgO2w7vELkZyStZDj0uZpMYRqdjcmMjC2zcPDquoynj4tIsgJjD3RDJtFf88).enableRevealAnimation(this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH).introAnimationDuration(this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO).fadeinTextDuration(this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R).lineAnimDuration(this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE).target(this.f489B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.getView()).targetPadding(this.q2q4oDuUajVwr2T7b6DILrrBhrCqmElgSd3ODKsAFi8uEX2COWatdRT7gov0FlS5).extraPaddingForArc(20).setHeadingTypeface(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setSubHeadingTypeface(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T).showTargetArc(this.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd).setListener(spotlightListener).show();
        this.Ta6bjdQXoFeEb44hWQ7kTTTXw2rT1LHw6UX7lms7WU7H0AkpETH9D9EhELUfywQi = true;
    }

    @SimpleFunction(description = "Use this block to show the spotlight on a floating action button.")
    public final void ShowSpotlightOnFAB(MakeroidFab makeroidFab) {
        SpotlightView.Builder builder;
        SpotlightListener spotlightListener;
        this.f491hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.resetAll();
        new SpotlightView.Builder(this.activity);
        new SpotlightListener(this) {
            private /* synthetic */ MakeroidSpotlight hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onUserClicked(SpotlightView spotlightView, String str) {
                SpotlightView spotlightView2 = spotlightView;
                String str2 = str;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Clicked();
                boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Ta6bjdQXoFeEb44hWQ7kTTTXw2rT1LHw6UX7lms7WU7H0AkpETH9D9EhELUfywQi = false;
            }
        };
        this.f490hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = builder.maskColor(this.UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5).headingTvText(this.G7Lxwqvaa0zcPMEYmgjECqcap18lY2TYRMrZOi1cD4z0oU809LnjVuckWEr78wl).headingTvColor(this.nKfZQ1laYcwrzNEumUwCbmi2kaHgg3eE1c9SDtYVLTkiuRTWxcP8Pqqx3AbL5ow).headingTvSize(this.AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl).subHeadingTvText(this.Qo6hTp4u7pOnq5WtKfNdQM86FuI5n4weAQCyrgAeimDlOrP2dP7U8KFwjrDIHyb).subHeadingTvColor(this.YgDmThmc2Ht6J8LKfKFuGtMLp2AoFjdDlIONA2izriJdICsKCPKMX3dYEj8K1z4k).subHeadingTvSize(this.iA1fsPSZHTCVXA414XY2edBmEFVpo23ZLLJ3ntPGDoZ3Lc1O8L7tucf7fjSxdGWm).lineAndArcColor(this.seAn8f9TucJq5ZQrZ6xvA6wzyVfqYrHR0kVGH9g6Rg5gdXevmQRBQv2iJqrzi5Rz).dismissOnBackPress(this.RC7PBJGdnqEffr8752ypFkbK8qZYkmQ3ci6maWfntRZXmeHa8bLhdKUgkXcpRo6T).dismissOnTouch(this.JTEvmldvMjbbtPPlVS4hmZghOoRNnXC0kZOUUAZdwkVNl1VM5pL0vCTYv5HQZ7AX).enableDismissAfterShown(this.pFeTJgO2w7vELkZyStZDj0uZpMYRqdjcmMjC2zcPDquoynj4tIsgJjD3RDJtFf88).enableRevealAnimation(this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH).introAnimationDuration(this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO).fadeinTextDuration(this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R).lineAnimDuration(this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE).target(makeroidFab.getView()).targetPadding(this.q2q4oDuUajVwr2T7b6DILrrBhrCqmElgSd3ODKsAFi8uEX2COWatdRT7gov0FlS5).extraPaddingForArc(20).setHeadingTypeface(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setSubHeadingTypeface(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T).showTargetArc(this.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd).setListener(spotlightListener).show();
        this.Ta6bjdQXoFeEb44hWQ7kTTTXw2rT1LHw6UX7lms7WU7H0AkpETH9D9EhELUfywQi = true;
    }

    @SimpleEvent(description = "Event triggered when the spotlight is clicked.")
    public final void Clicked() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Clicked", new Object[0]);
    }
}
