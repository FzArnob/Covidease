package com.google.appinventor.components.runtime;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.view.Window;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.UTILITIES, description = "", iconName = "images/keyguardManager.png", nonVisible = true, version = 1)
@UsesPermissions(permissionNames = "android.permission.DISABLE_KEYGUARD")
public class KodularKeyguardManager extends AndroidNonvisibleComponent implements ActivityResultListener, Component {
    private KeyguardManager B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private String BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS = "Confirm your screen lock.";
    private Activity activity;
    /* access modifiers changed from: private */
    public final Handler androidUIHandler;
    private Context context;
    private Form form;
    private String n86Bi3TT8lkq4OIAY6TQzz40yEQcROLeML3EOzGKfDOtlcMXWXcB7P2XOFzT0DDv = "Unlock";
    private int requestCode;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KodularKeyguardManager(com.google.appinventor.components.runtime.ComponentContainer r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            android.os.Handler r3 = new android.os.Handler
            r5 = r3
            r3 = r5
            r4 = r5
            r4.<init>()
            r2.androidUIHandler = r3
            r2 = r0
            java.lang.String r3 = "Unlock"
            r2.n86Bi3TT8lkq4OIAY6TQzz40yEQcROLeML3EOzGKfDOtlcMXWXcB7P2XOFzT0DDv = r3
            r2 = r0
            java.lang.String r3 = "Confirm your screen lock."
            r2.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.activity = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.form = r3
            r2 = r0
            r5 = r2
            r2 = r5
            r3 = r5
            android.content.Context r3 = r3.context
            java.lang.String r4 = "keyguard"
            java.lang.Object r3 = r3.getSystemService(r4)
            android.app.KeyguardManager r3 = (android.app.KeyguardManager) r3
            r2.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r3
            java.lang.String r2 = "Keyguard Manager"
            java.lang.String r3 = "Keyguard Manager Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularKeyguardManager.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @DesignerProperty(defaultValue = "Unlock", editorType = "string")
    @SimpleProperty(description = "")
    public void Title(String str) {
        String str2 = str;
        this.n86Bi3TT8lkq4OIAY6TQzz40yEQcROLeML3EOzGKfDOtlcMXWXcB7P2XOFzT0DDv = str2;
    }

    @SimpleProperty(description = "Returns the keyguard manager title text.")
    public String Title() {
        return this.n86Bi3TT8lkq4OIAY6TQzz40yEQcROLeML3EOzGKfDOtlcMXWXcB7P2XOFzT0DDv;
    }

    @DesignerProperty(defaultValue = "Confirm your screen lock.", editorType = "string")
    @SimpleProperty(description = "")
    public void Description(String str) {
        String str2 = str;
        this.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS = str2;
    }

    @SimpleProperty(description = "Returns the keyguard manager description text.")
    public String Description() {
        return this.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS;
    }

    @SimpleProperty(description = "Returns whether the device is currently locked and requires a PIN, pattern or password to unlock. Works only for devices with Android 5.1+")
    public boolean isDeviceLocked() {
        if (Build.VERSION.SDK_INT >= 22) {
            return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.isDeviceLocked();
        }
        return false;
    }

    @SimpleProperty(description = "Returns whether the device is secured with a PIN, pattern or password. Works only for devices with Android 6+")
    public boolean isDeviceSecure() {
        if (Build.VERSION.SDK_INT >= 23) {
            return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.isDeviceSecure();
        }
        return false;
    }

    @SuppressLint({"NewApi"})
    @SimpleProperty(description = "Return whether the keyguard is currently locked.")
    public boolean isKeyguardLocked() {
        return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.isKeyguardLocked();
    }

    @SuppressLint({"NewApi"})
    @SimpleProperty(description = "Return whether the keyguard is secured by a PIN, pattern or password or a SIM card is currently locked.")
    public boolean isKeyguardSecure() {
        return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.isKeyguardSecure();
    }

    @SimpleFunction(description = "Specifies whether an Activity should be shown on top of the lock screen whenever the lockscreen is up and the activity is resumed. Normally an activity will be transitioned to the stopped state if it is started while the lockscreen is up, but with this flag set the activity will remain in the resumed state visible on-top of the lock screen. ")
    public void ShowWhenLocked(boolean z) {
        boolean z2 = z;
        if (Build.VERSION.SDK_INT >= 27) {
            this.activity.setShowWhenLocked(z2);
            return;
        }
        Window window = this.activity.getWindow();
        if (z2) {
            window.addFlags(524288);
        } else {
            window.clearFlags(524288);
        }
    }

    @SimpleFunction(description = "Create the Confirm Credentials screen. You can customize the title and description. Or we will provide a generic one for you if you leave it empty. Works only for devices with Android 5+")
    public void ShowAuthenticationScreen() {
        Intent intent = null;
        if (Build.VERSION.SDK_INT >= 21) {
            intent = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.createConfirmDeviceCredentialIntent(Title().isEmpty() ? null : Title(), Description().isEmpty() ? null : Description());
        }
        if (intent != null) {
            this.requestCode = this.form.registerForActivityResult(this);
            this.form.startActivityForResult(intent, this.requestCode);
        }
    }

    public void resultReturned(int i, int i2, Intent intent) {
        Runnable runnable;
        Runnable runnable2;
        int i3 = i2;
        Intent intent2 = intent;
        if (i != this.requestCode) {
            return;
        }
        if (i3 == -1) {
            new Runnable(this) {
                private /* synthetic */ KodularKeyguardManager hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void run() {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.OnAuthenticationRequest(true);
                }
            };
            boolean post = this.androidUIHandler.post(runnable2);
            return;
        }
        new Runnable(this) {
            private /* synthetic */ KodularKeyguardManager hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void run() {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.OnAuthenticationRequest(false);
            }
        };
        boolean post2 = this.androidUIHandler.post(runnable);
    }

    @SimpleFunction(description = "If the device is currently locked, requests the Keyguard to be dismissed. Works only for devices with Android 8+")
    public void RequestDismissKeyguard() {
        KeyguardManager.KeyguardDismissCallback keyguardDismissCallback;
        if (Build.VERSION.SDK_INT >= 26) {
            new KeyguardManager.KeyguardDismissCallback(this) {
                final /* synthetic */ KodularKeyguardManager hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void onDismissError() {
                    Runnable runnable;
                    super.onDismissError();
                    new Runnable(this) {
                        private /* synthetic */ C08013 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                        }

                        public final void run() {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.OnDissmissKeyguardRequest(false, true);
                        }
                    };
                    boolean post = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.post(runnable);
                }

                public final void onDismissCancelled() {
                    Runnable runnable;
                    super.onDismissCancelled();
                    new Runnable(this) {
                        private /* synthetic */ C08013 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                        }

                        public final void run() {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.OnDissmissKeyguardRequest(false, true);
                        }
                    };
                    boolean post = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.post(runnable);
                }

                public final void onDismissSucceeded() {
                    Runnable runnable;
                    super.onDismissSucceeded();
                    new Runnable(this) {
                        private /* synthetic */ C08013 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                        }

                        public final void run() {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.OnDissmissKeyguardRequest(true, false);
                        }
                    };
                    boolean post = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.post(runnable);
                }
            };
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.requestDismissKeyguard(this.activity, keyguardDismissCallback);
        }
    }

    @SimpleEvent(description = "Event to detect a authentication request was called.")
    public void OnAuthenticationRequest(boolean z) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnAuthenticationRequest", Boolean.valueOf(z));
    }

    @SimpleEvent(description = "Event to detect a dissmiss request keyguard was called.")
    public void OnDissmissKeyguardRequest(boolean z, boolean z2) {
        Object[] objArr = new Object[2];
        objArr[0] = Boolean.valueOf(z);
        Object[] objArr2 = objArr;
        objArr2[1] = Boolean.valueOf(z2);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnDissmissKeyguardRequest", objArr2);
    }
}
