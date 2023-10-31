package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

@DesignerComponent(category = ComponentCategory.GOOGLE, description = "Firebase Remote Config", iconName = "images/firebaseDB.png", nonVisible = true, version = 2)
@UsesLibraries(libraries = "firebase-abt.jar, firebase-abt.aar,firebase-analytics.jar, firebase-analytics.aar,firebase-analytics-impl.jar, firebase-analytics-impl.aar,firebase-common.jar, firebase-common.aar,firebase-config.jar, firebase-config.aar,firebase-core.jar, firebase-core.aar,firebase-iid.jar, firebase-iid.aar,firebase-iid-interop.jar, firebase-iid-interop.aar,firebase-measurement-connector.jar, firebase-measurement-connector.aar,firebase-measurement-connector-impl.jar, firebase-measurement-connector-impl.aar,play-services-ads-identifier.jar, play-services-ads-identifier.aar,play-services-base.jar, play-services-base.aar,play-services-basement.jar, play-services-basement.aar,play-services-measurement.jar, play-services-measurement.aar,play-services-measurement-api.jar, play-services-measurement-api.aar,play-services-measurement-base.jar, play-services-measurement-base.aar,play-services-measurement-impl.jar, play-services-measurement-impl.aar,play-services-measurement-sdk.jar, play-services-measurement-sdk.aar,play-services-measurement-sdk-api.jar, play-services-measurement-sdk-api.aar,play-services-phenotype.jar, play-services-phenotype.aar,play-services-stats.jar, play-services-stats.aar,play-services-tasks.jar, play-services-tasks.aar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET,android.permission.ACCESS_NETWORK_STATE,android.permission.WAKE_LOCK,com.google.android.c2dm.permission.RECEIVE,com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE")
public class KodularFirebaseRemoteConfig extends AndroidNonvisibleComponent {
    private Activity activity;
    private Context context;
    private FirebaseRemoteConfig hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private long vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = 43200;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KodularFirebaseRemoteConfig(com.google.appinventor.components.runtime.ComponentContainer r7) {
        /*
            r6 = this;
            r1 = r6
            r2 = r7
            r3 = r1
            r4 = r2
            com.google.appinventor.components.runtime.Form r4 = r4.$form()
            r3.<init>(r4)
            r3 = r1
            r4 = 43200(0xa8c0, double:2.13436E-319)
            r3.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = r4
            r3 = r1
            r4 = r2
            android.app.Activity r4 = r4.$context()
            r3.context = r4
            r3 = r1
            r4 = r2
            android.app.Activity r4 = r4.$context()
            r3.activity = r4
            r3 = r1
            com.google.firebase.remoteconfig.FirebaseRemoteConfig r4 = com.google.firebase.remoteconfig.FirebaseRemoteConfig.getInstance()
            r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularFirebaseRemoteConfig.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Fetches parameter values for your app")
    public void Fetch() {
        OnCompleteListener onCompleteListener;
        new OnCompleteListener<Boolean>(this) {
            private /* synthetic */ KodularFirebaseRemoteConfig hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onComplete(@NonNull Task<Boolean> task) {
                if (task.isSuccessful()) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.FetchSuccess();
                } else {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.FetchFailed();
                }
            }
        };
        Task addOnCompleteListener = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.fetchAndActivate().addOnCompleteListener(this.activity, onCompleteListener);
    }

    @SimpleEvent(description = "Triggers when the fetch was successful")
    public void FetchSuccess() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "FetchSuccess", new Object[0]);
    }

    @SimpleEvent(description = "Triggers when the fetch failed")
    public void FetchFailed() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "FetchFailed", new Object[0]);
    }

    @SimpleFunction(description = "Gets a text value corresponding to the specified key")
    public String GetText(String str) {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getString(str);
    }

    @SimpleFunction(description = "Gets a number value corresponding to the specified key")
    public double GetNumber(String str) {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getDouble(str);
    }

    @SimpleFunction(description = "Gets a boolean value corresponding to the specified key")
    public boolean GetBoolean(String str) {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getBoolean(str);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Time how long the data keeps on the device in ms")
    public long CacheExpiration() {
        return this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;
    }

    @DesignerProperty(defaultValue = "43200", editorType = "non_negative_integer")
    @SimpleProperty
    public void CacheExpiration(long j) {
        FirebaseRemoteConfigSettings.Builder builder;
        long j2 = j;
        if (j2 < 3600) {
            j2 = 3600;
        }
        this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = j2;
        new FirebaseRemoteConfigSettings.Builder();
        Task configSettingsAsync = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setConfigSettingsAsync(builder.setMinimumFetchIntervalInSeconds(this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq).build());
    }
}
