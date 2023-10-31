package com.bumptech.glide.signature;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.bumptech.glide.load.Key;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class ApplicationVersionSignature {
    private static final ConcurrentHashMap<String, Key> PACKAGE_NAME_TO_KEY;

    static {
        ConcurrentHashMap<String, Key> concurrentHashMap;
        new ConcurrentHashMap<>();
        PACKAGE_NAME_TO_KEY = concurrentHashMap;
    }

    public static Key obtain(Context context) {
        Context context2 = context;
        String packageName = context2.getPackageName();
        Key result = PACKAGE_NAME_TO_KEY.get(packageName);
        if (result == null) {
            Key toAdd = obtainVersionSignature(context2);
            result = PACKAGE_NAME_TO_KEY.putIfAbsent(packageName, toAdd);
            if (result == null) {
                result = toAdd;
            }
        }
        return result;
    }

    static void reset() {
        PACKAGE_NAME_TO_KEY.clear();
    }

    private static Key obtainVersionSignature(Context context) {
        String versionCode;
        Key key;
        Context context2 = context;
        PackageInfo pInfo = null;
        try {
            pInfo = context2.getPackageManager().getPackageInfo(context2.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (pInfo != null) {
            versionCode = String.valueOf(pInfo.versionCode);
        } else {
            versionCode = UUID.randomUUID().toString();
        }
        new StringSignature(versionCode);
        return key;
    }

    private ApplicationVersionSignature() {
    }
}
