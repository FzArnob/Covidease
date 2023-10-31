package com.google.appinventor.components.runtime;

import android.content.Context;
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
import com.white.mobi.sdk.WMManager;

@DesignerComponent(category = ComponentCategory.INTERNAL, description = "WhiteMobi component", helpUrl = "https://docs.kodular.io/components/monetization/whitemobi/", iconName = "images/whitemobi.png", nonVisible = true, version = 1)
@UsesLibraries(libraries = "gson.jar, whitemobisdk.jar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.ACCESS_NETWORK_STATE")
public class MakeroidWhiteMobi extends AndroidNonvisibleComponent implements OnPauseListener, OnResumeListener {
    private String RbYVQzNQ0N2UrAmoggkhMTlc54LPYDcOUMcpFAK7czpNLpbMtrjfrFIT6QATuiqT = "";
    private Context context;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MakeroidWhiteMobi(com.google.appinventor.components.runtime.ComponentContainer r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            java.lang.String r3 = ""
            r2.RbYVQzNQ0N2UrAmoggkhMTlc54LPYDcOUMcpFAK7czpNLpbMtrjfrFIT6QATuiqT = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            com.google.appinventor.components.runtime.Form r2 = r2.form
            r3 = r0
            r2.registerForOnPause(r3)
            r2 = r0
            com.google.appinventor.components.runtime.Form r2 = r2.form
            r3 = r0
            r2.registerForOnResume(r3)
            com.google.appinventor.components.runtime.MakeroidWhiteMobi$1 r2 = new com.google.appinventor.components.runtime.MakeroidWhiteMobi$1
            r5 = r2
            r2 = r5
            r3 = r5
            r4 = r0
            r3.<init>(r4)
            com.white.mobi.sdk.WMManager.setRewardListener(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidWhiteMobi.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public void ApplicationKey(String str) {
        String str2 = str;
        this.RbYVQzNQ0N2UrAmoggkhMTlc54LPYDcOUMcpFAK7czpNLpbMtrjfrFIT6QATuiqT = str2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the application key.")
    public String ApplicationKey() {
        return this.RbYVQzNQ0N2UrAmoggkhMTlc54LPYDcOUMcpFAK7czpNLpbMtrjfrFIT6QATuiqT;
    }

    @SimpleFunction(description = "Show WhiteMobi offerwall")
    public void ShowOfferWall() {
        WMManager.showOfferWall(this.RbYVQzNQ0N2UrAmoggkhMTlc54LPYDcOUMcpFAK7czpNLpbMtrjfrFIT6QATuiqT);
    }

    @SimpleEvent(description = "Event triggered when an offer has been completed")
    public void OfferCompleted(int i) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OfferCompleted", Integer.valueOf(i));
    }

    public void onPause() {
        WMManager.onPause();
    }

    public void onResume() {
        WMManager.onResume(this.context);
    }
}
