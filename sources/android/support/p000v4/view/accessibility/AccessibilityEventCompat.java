package android.support.p000v4.view.accessibility;

import android.os.Build;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityRecord;

/* renamed from: android.support.v4.view.accessibility.AccessibilityEventCompat */
public final class AccessibilityEventCompat {
    public static final int CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION = 4;
    public static final int CONTENT_CHANGE_TYPE_SUBTREE = 1;
    public static final int CONTENT_CHANGE_TYPE_TEXT = 2;
    public static final int CONTENT_CHANGE_TYPE_UNDEFINED = 0;
    public static final int TYPES_ALL_MASK = -1;
    public static final int TYPE_ANNOUNCEMENT = 16384;
    public static final int TYPE_ASSIST_READING_CONTEXT = 16777216;
    public static final int TYPE_GESTURE_DETECTION_END = 524288;
    public static final int TYPE_GESTURE_DETECTION_START = 262144;
    @Deprecated
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_END = 1024;
    @Deprecated
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_START = 512;
    public static final int TYPE_TOUCH_INTERACTION_END = 2097152;
    public static final int TYPE_TOUCH_INTERACTION_START = 1048576;
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUSED = 32768;
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED = 65536;
    public static final int TYPE_VIEW_CONTEXT_CLICKED = 8388608;
    @Deprecated
    public static final int TYPE_VIEW_HOVER_ENTER = 128;
    @Deprecated
    public static final int TYPE_VIEW_HOVER_EXIT = 256;
    @Deprecated
    public static final int TYPE_VIEW_SCROLLED = 4096;
    @Deprecated
    public static final int TYPE_VIEW_TEXT_SELECTION_CHANGED = 8192;
    public static final int TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY = 131072;
    public static final int TYPE_WINDOWS_CHANGED = 4194304;
    @Deprecated
    public static final int TYPE_WINDOW_CONTENT_CHANGED = 2048;

    private AccessibilityEventCompat() {
    }

    @Deprecated
    public static int getRecordCount(AccessibilityEvent event) {
        return event.getRecordCount();
    }

    @Deprecated
    public static void appendRecord(AccessibilityEvent event, AccessibilityRecordCompat record) {
        event.appendRecord((AccessibilityRecord) record.getImpl());
    }

    @Deprecated
    public static AccessibilityRecordCompat getRecord(AccessibilityEvent event, int index) {
        AccessibilityRecordCompat accessibilityRecordCompat;
        new AccessibilityRecordCompat(event.getRecord(index));
        return accessibilityRecordCompat;
    }

    @Deprecated
    public static AccessibilityRecordCompat asRecord(AccessibilityEvent event) {
        AccessibilityRecordCompat accessibilityRecordCompat;
        new AccessibilityRecordCompat(event);
        return accessibilityRecordCompat;
    }

    public static void setContentChangeTypes(AccessibilityEvent accessibilityEvent, int i) {
        AccessibilityEvent event = accessibilityEvent;
        int changeTypes = i;
        if (Build.VERSION.SDK_INT >= 19) {
            event.setContentChangeTypes(changeTypes);
        }
    }

    public static int getContentChangeTypes(AccessibilityEvent accessibilityEvent) {
        AccessibilityEvent event = accessibilityEvent;
        if (Build.VERSION.SDK_INT >= 19) {
            return event.getContentChangeTypes();
        }
        return 0;
    }

    public static void setMovementGranularity(AccessibilityEvent accessibilityEvent, int i) {
        AccessibilityEvent event = accessibilityEvent;
        int granularity = i;
        if (Build.VERSION.SDK_INT >= 16) {
            event.setMovementGranularity(granularity);
        }
    }

    public static int getMovementGranularity(AccessibilityEvent accessibilityEvent) {
        AccessibilityEvent event = accessibilityEvent;
        if (Build.VERSION.SDK_INT >= 16) {
            return event.getMovementGranularity();
        }
        return 0;
    }

    public static void setAction(AccessibilityEvent accessibilityEvent, int i) {
        AccessibilityEvent event = accessibilityEvent;
        int action = i;
        if (Build.VERSION.SDK_INT >= 16) {
            event.setAction(action);
        }
    }

    public static int getAction(AccessibilityEvent accessibilityEvent) {
        AccessibilityEvent event = accessibilityEvent;
        if (Build.VERSION.SDK_INT >= 16) {
            return event.getAction();
        }
        return 0;
    }
}
