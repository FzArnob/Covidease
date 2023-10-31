package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class ViewUtils {
    private ViewUtils() {
    }

    @KeepForSdk
    public static String getXmlAttributeString(String str, String str2, Context context, AttributeSet attributeSet, boolean z, boolean z2, String str3) {
        StringBuilder sb;
        TypedValue typedValue;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        String str4 = str2;
        Context context2 = context;
        AttributeSet attributeSet2 = attributeSet;
        boolean z3 = z;
        boolean z4 = z2;
        String str5 = str3;
        String attributeValue = attributeSet2 == null ? null : attributeSet2.getAttributeValue(str, str4);
        String str6 = attributeValue;
        if (attributeValue != null && str6.startsWith("@string/") && z3) {
            String substring = str6.substring(8);
            String packageName = context2.getPackageName();
            new TypedValue();
            TypedValue typedValue2 = typedValue;
            try {
                Resources resources = context2.getResources();
                new StringBuilder(8 + String.valueOf(packageName).length() + String.valueOf(substring).length());
                resources.getValue(sb4.append(packageName).append(":string/").append(substring).toString(), typedValue2, true);
            } catch (Resources.NotFoundException e) {
                String str7 = str6;
                new StringBuilder(30 + String.valueOf(str4).length() + String.valueOf(str7).length());
                int w = Log.w(str5, sb2.append("Could not find resource for ").append(str4).append(": ").append(str7).toString());
            }
            if (typedValue2.string != null) {
                str6 = typedValue2.string.toString();
            } else {
                String valueOf = String.valueOf(typedValue2);
                new StringBuilder(28 + String.valueOf(str4).length() + String.valueOf(valueOf).length());
                int w2 = Log.w(str5, sb3.append("Resource ").append(str4).append(" was not a string: ").append(valueOf).toString());
            }
        }
        if (z4 && str6 == null) {
            new StringBuilder(33 + String.valueOf(str4).length());
            int w3 = Log.w(str5, sb.append("Required XML attribute \"").append(str4).append("\" missing").toString());
        }
        return str6;
    }
}
