package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.customtabs.CustomTabsService;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class KodularChromeCustomTabsHelper {
    private static String LOG_TAG = "KodularChromeCustomTabsHelper";
    private static String h8Png9oaVDfYGvgWdHcn1DMBnn2tbT5MRZoUvXvvhLrY5O6ybby2QmTOJ6PZJucW;

    private KodularChromeCustomTabsHelper() {
    }

    public static String getPackageNameToUse(Context context) {
        Intent intent;
        List list;
        Intent intent2;
        Context context2 = context;
        if (h8Png9oaVDfYGvgWdHcn1DMBnn2tbT5MRZoUvXvvhLrY5O6ybby2QmTOJ6PZJucW != null) {
            return h8Png9oaVDfYGvgWdHcn1DMBnn2tbT5MRZoUvXvvhLrY5O6ybby2QmTOJ6PZJucW;
        }
        PackageManager packageManager = context2.getPackageManager();
        new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
        Intent intent3 = intent;
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent3, 0);
        String str = null;
        if (resolveActivity != null) {
            str = resolveActivity.activityInfo.packageName;
        }
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent3, 0);
        new ArrayList();
        List list2 = list;
        for (ResolveInfo next : queryIntentActivities) {
            new Intent();
            Intent intent4 = intent2;
            Intent intent5 = intent4;
            Intent action = intent4.setAction(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION);
            Intent intent6 = intent5.setPackage(next.activityInfo.packageName);
            if (packageManager.resolveService(intent5, 0) != null) {
                boolean add = list2.add(next.activityInfo.packageName);
            }
        }
        if (list2.isEmpty()) {
            h8Png9oaVDfYGvgWdHcn1DMBnn2tbT5MRZoUvXvvhLrY5O6ybby2QmTOJ6PZJucW = null;
        } else if (list2.size() == 1) {
            h8Png9oaVDfYGvgWdHcn1DMBnn2tbT5MRZoUvXvvhLrY5O6ybby2QmTOJ6PZJucW = (String) list2.get(0);
        } else if (!TextUtils.isEmpty(str) && !hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(context2, intent3) && list2.contains(str)) {
            h8Png9oaVDfYGvgWdHcn1DMBnn2tbT5MRZoUvXvvhLrY5O6ybby2QmTOJ6PZJucW = str;
        } else if (list2.contains("com.android.chrome")) {
            h8Png9oaVDfYGvgWdHcn1DMBnn2tbT5MRZoUvXvvhLrY5O6ybby2QmTOJ6PZJucW = "com.android.chrome";
        } else if (list2.contains("com.chrome.beta")) {
            h8Png9oaVDfYGvgWdHcn1DMBnn2tbT5MRZoUvXvvhLrY5O6ybby2QmTOJ6PZJucW = "com.chrome.beta";
        } else if (list2.contains("com.chrome.dev")) {
            h8Png9oaVDfYGvgWdHcn1DMBnn2tbT5MRZoUvXvvhLrY5O6ybby2QmTOJ6PZJucW = "com.chrome.dev";
        } else if (list2.contains("com.google.android.apps.chrome")) {
            h8Png9oaVDfYGvgWdHcn1DMBnn2tbT5MRZoUvXvvhLrY5O6ybby2QmTOJ6PZJucW = "com.google.android.apps.chrome";
        }
        return h8Png9oaVDfYGvgWdHcn1DMBnn2tbT5MRZoUvXvvhLrY5O6ybby2QmTOJ6PZJucW;
    }

    private static boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Context context, Intent intent) {
        try {
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 64);
            List<ResolveInfo> list = queryIntentActivities;
            if (queryIntentActivities == null || list.size() == 0) {
                return false;
            }
            for (ResolveInfo next : list) {
                ResolveInfo resolveInfo = next;
                IntentFilter intentFilter = next.filter;
                IntentFilter intentFilter2 = intentFilter;
                if (intentFilter != null && intentFilter2.countDataAuthorities() != 0 && intentFilter2.countDataPaths() != 0 && resolveInfo.activityInfo != null) {
                    return true;
                }
            }
            return false;
        } catch (RuntimeException e) {
            int e2 = Log.e(LOG_TAG, "Runtime exception while getting specialized handlers");
        }
    }

    public static String[] getPackages() {
        String[] strArr = new String[5];
        strArr[0] = "";
        String[] strArr2 = strArr;
        strArr2[1] = "com.android.chrome";
        String[] strArr3 = strArr2;
        strArr3[2] = "com.chrome.beta";
        String[] strArr4 = strArr3;
        strArr4[3] = "com.chrome.dev";
        String[] strArr5 = strArr4;
        String[] strArr6 = strArr5;
        strArr5[4] = "com.google.android.apps.chrome";
        return strArr6;
    }
}
