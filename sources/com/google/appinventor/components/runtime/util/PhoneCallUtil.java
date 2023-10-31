package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class PhoneCallUtil {
    private PhoneCallUtil() {
    }

    public static void makePhoneCall(Context context, String str) {
        Intent intent;
        Context context2 = context;
        String str2 = str;
        if (str2 != null && str2.length() > 0) {
            new Intent("android.intent.action.CALL", Uri.parse("tel:".concat(String.valueOf(str2))));
            context2.startActivity(intent);
        }
    }
}
