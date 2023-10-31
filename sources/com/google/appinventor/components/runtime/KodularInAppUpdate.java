package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;

@DesignerComponent(androidMinSdk = 21, category = ComponentCategory.EXPERIMENTAL, description = "...in ode messages file", helpUrl = "https://docs.kodular.io/components/monetization/in-app-update/", iconName = "images/inappUpdate.png", nonVisible = true, version = 1)
@UsesLibraries(libraries = "play-core.aar, play-core.jar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public class KodularInAppUpdate extends AndroidNonvisibleComponent implements OnResumeListener {
    private ComponentContainer container;
    private Form form;
    private AppUpdateManager hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private InstallStateUpdatedListener f456hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private Task<AppUpdateInfo> f457hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getAppUpdateInfo();

    static /* synthetic */ int B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T() {
        return 0;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KodularInAppUpdate(com.google.appinventor.components.runtime.ComponentContainer r8) {
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
            r2.container = r3
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.form = r3
            r2 = r0
            com.google.appinventor.components.runtime.Form r2 = r2.form
            r3 = r0
            r2.registerForOnResume(r3)
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            com.google.android.play.core.appupdate.AppUpdateManager r3 = com.google.android.play.core.appupdate.AppUpdateManagerFactory.create(r3)
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            r6 = r2
            r2 = r6
            r3 = r6
            com.google.android.play.core.appupdate.AppUpdateManager r3 = r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.android.play.core.tasks.Task r3 = r3.getAppUpdateInfo()
            r2.f457hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            com.google.android.play.core.tasks.Task<com.google.android.play.core.appupdate.AppUpdateInfo> r2 = r2.f457hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.KodularInAppUpdate$1 r3 = new com.google.appinventor.components.runtime.KodularInAppUpdate$1
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r0
            r4.<init>(r5)
            com.google.android.play.core.tasks.Task r2 = r2.addOnCompleteListener(r3)
            java.lang.String r2 = "InApp Update"
            java.lang.String r3 = "Kodular InApp Update created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularInAppUpdate.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleEvent(description = "Use this event to check if updates from Google Play Store are available for your app.")
    public void Initialized() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Initialized", new Object[0]);
    }

    @SimpleEvent(description = "Event will be invoked when there was a update fail.")
    public void UpdateFailed() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "UpdateFailed", new Object[0]);
    }

    @SimpleEvent(description = "Event will be invoked when the update was canceled.")
    public void UpdateCanceled() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "UpdateCanceled", new Object[0]);
    }

    @SimpleEvent(description = "Event will be invoked when the update is downloaded.")
    public void UpdateDownloaded() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "UpdateDownloaded", new Object[0]);
    }

    @SimpleEvent(description = "Event will be invoked when the update is still in downloading progress.")
    public void UpdateDownloading() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "UpdateDownloading", new Object[0]);
    }

    @SimpleEvent(description = "Event will be invoked when the update is installed.")
    public void UpdateInstalled() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "UpdateInstalled", new Object[0]);
    }

    @SimpleEvent(description = "Event will be invoked when the update is still in installing progress.")
    public void UpdateInstalling() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "UpdateInstalling", new Object[0]);
    }

    @SimpleProperty(description = "Returns true if a update is available for your app.")
    public boolean IsUpdateAvailable() {
        try {
            return ((AppUpdateInfo) this.f457hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getResult()).updateAvailability() == 2;
        } catch (Exception e) {
            int e2 = Log.e("InApp Update", String.valueOf(e));
            return false;
        }
    }

    @SimpleFunction(description = "Start the process for immediate in-app updates. In this time your app user can NOT use your app. After the update download is done your app will be restarted automatically.")
    public void StartImmediateUpdate() {
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.registerListener(this.f456hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
            boolean startUpdateFlowForResult = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.startUpdateFlowForResult((AppUpdateInfo) this.f457hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getResult(), 1, this.container.$context(), 0);
        } catch (Exception e) {
            int e2 = Log.e("InApp Update", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Start the process for flexible in-app updates. The update will be done in the background. Your users can still use your app in this time. After the update download is done you must restart your app.")
    public void StartFlexibleUpdate() {
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.registerListener(this.f456hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
            boolean startUpdateFlowForResult = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.startUpdateFlowForResult((AppUpdateInfo) this.f457hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getResult(), 0, this.container.$context(), 0);
        } catch (Exception e) {
            int e2 = Log.e("InApp Update", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Use this block to install a flexible update after it was downloaded.")
    public void InstallFlexibleUpdateNow() {
        try {
            Task completeUpdate = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.completeUpdate();
        } catch (Exception e) {
            int e2 = Log.e("InApp Update", String.valueOf(e));
        }
    }

    public void onResume() {
        OnSuccessListener onSuccessListener;
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null && this.f457hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            new OnSuccessListener<AppUpdateInfo>(this) {
                private /* synthetic */ KodularInAppUpdate hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final /* synthetic */ void onSuccess(Object obj) {
                    AppUpdateInfo appUpdateInfo = (AppUpdateInfo) obj;
                    if (appUpdateInfo.updateAvailability() == 3) {
                        try {
                            boolean startUpdateFlowForResult = KodularInAppUpdate.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).startUpdateFlowForResult(appUpdateInfo, 1, KodularInAppUpdate.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).$context(), KodularInAppUpdate.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T());
                        } catch (Exception e) {
                            int e2 = Log.e("InApp Update", String.valueOf(e));
                        }
                    } else if (appUpdateInfo.installStatus() == 11) {
                        Task completeUpdate = KodularInAppUpdate.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).completeUpdate();
                    }
                }
            };
            Task addOnSuccessListener = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getAppUpdateInfo().addOnSuccessListener(onSuccessListener);
        }
    }
}
