package com.google.appinventor.components.runtime.multidex;

import android.app.Application;
import android.content.Context;

public class MultiDexApplication extends Application {
    public static boolean installed = false;

    public MultiDexApplication() {
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        boolean install = MultiDex.install(this, true);
    }
}
