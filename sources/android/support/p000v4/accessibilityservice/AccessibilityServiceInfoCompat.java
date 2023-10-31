package android.support.p000v4.accessibilityservice;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* renamed from: android.support.v4.accessibilityservice.AccessibilityServiceInfoCompat */
public final class AccessibilityServiceInfoCompat {
    public static final int CAPABILITY_CAN_FILTER_KEY_EVENTS = 8;
    public static final int CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 4;
    public static final int CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION = 2;
    public static final int CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT = 1;
    public static final int FEEDBACK_ALL_MASK = -1;
    public static final int FEEDBACK_BRAILLE = 32;
    public static final int FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 2;
    public static final int FLAG_REPORT_VIEW_IDS = 16;
    public static final int FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 8;
    public static final int FLAG_REQUEST_FILTER_KEY_EVENTS = 32;
    public static final int FLAG_REQUEST_TOUCH_EXPLORATION_MODE = 4;

    private AccessibilityServiceInfoCompat() {
    }

    @Nullable
    public static String loadDescription(@NonNull AccessibilityServiceInfo accessibilityServiceInfo, @NonNull PackageManager packageManager) {
        AccessibilityServiceInfo info = accessibilityServiceInfo;
        PackageManager packageManager2 = packageManager;
        if (Build.VERSION.SDK_INT >= 16) {
            return info.loadDescription(packageManager2);
        }
        return info.getDescription();
    }

    @NonNull
    public static String feedbackTypeToString(int i) {
        StringBuilder sb;
        int feedbackType = i;
        new StringBuilder();
        StringBuilder builder = sb;
        StringBuilder append = builder.append("[");
        while (feedbackType > 0) {
            int feedbackTypeFlag = 1 << Integer.numberOfTrailingZeros(feedbackType);
            feedbackType &= feedbackTypeFlag ^ -1;
            if (builder.length() > 1) {
                StringBuilder append2 = builder.append(", ");
            }
            switch (feedbackTypeFlag) {
                case 1:
                    StringBuilder append3 = builder.append("FEEDBACK_SPOKEN");
                    break;
                case 2:
                    StringBuilder append4 = builder.append("FEEDBACK_HAPTIC");
                    break;
                case 4:
                    StringBuilder append5 = builder.append("FEEDBACK_AUDIBLE");
                    break;
                case 8:
                    StringBuilder append6 = builder.append("FEEDBACK_VISUAL");
                    break;
                case 16:
                    StringBuilder append7 = builder.append("FEEDBACK_GENERIC");
                    break;
            }
        }
        StringBuilder append8 = builder.append("]");
        return builder.toString();
    }

    @Nullable
    public static String flagToString(int flag) {
        switch (flag) {
            case 1:
                return "DEFAULT";
            case 2:
                return "FLAG_INCLUDE_NOT_IMPORTANT_VIEWS";
            case 4:
                return "FLAG_REQUEST_TOUCH_EXPLORATION_MODE";
            case 8:
                return "FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
            case 16:
                return "FLAG_REPORT_VIEW_IDS";
            case 32:
                return "FLAG_REQUEST_FILTER_KEY_EVENTS";
            default:
                return null;
        }
    }

    public static int getCapabilities(@NonNull AccessibilityServiceInfo accessibilityServiceInfo) {
        AccessibilityServiceInfo info = accessibilityServiceInfo;
        if (Build.VERSION.SDK_INT >= 18) {
            return info.getCapabilities();
        }
        if (info.getCanRetrieveWindowContent()) {
            return 1;
        }
        return 0;
    }

    @NonNull
    public static String capabilityToString(int capability) {
        switch (capability) {
            case 1:
                return "CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT";
            case 2:
                return "CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION";
            case 4:
                return "CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
            case 8:
                return "CAPABILITY_CAN_FILTER_KEY_EVENTS";
            default:
                return "UNKNOWN";
        }
    }
}
