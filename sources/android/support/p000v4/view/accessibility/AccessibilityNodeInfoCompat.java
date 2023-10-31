package android.support.p000v4.view.accessibility;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: android.support.v4.view.accessibility.AccessibilityNodeInfoCompat */
public class AccessibilityNodeInfoCompat {
    public static final int ACTION_ACCESSIBILITY_FOCUS = 64;
    public static final String ACTION_ARGUMENT_COLUMN_INT = "android.view.accessibility.action.ARGUMENT_COLUMN_INT";
    public static final String ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN = "ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN";
    public static final String ACTION_ARGUMENT_HTML_ELEMENT_STRING = "ACTION_ARGUMENT_HTML_ELEMENT_STRING";
    public static final String ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT = "ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT";
    public static final String ACTION_ARGUMENT_MOVE_WINDOW_X = "ACTION_ARGUMENT_MOVE_WINDOW_X";
    public static final String ACTION_ARGUMENT_MOVE_WINDOW_Y = "ACTION_ARGUMENT_MOVE_WINDOW_Y";
    public static final String ACTION_ARGUMENT_PROGRESS_VALUE = "android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE";
    public static final String ACTION_ARGUMENT_ROW_INT = "android.view.accessibility.action.ARGUMENT_ROW_INT";
    public static final String ACTION_ARGUMENT_SELECTION_END_INT = "ACTION_ARGUMENT_SELECTION_END_INT";
    public static final String ACTION_ARGUMENT_SELECTION_START_INT = "ACTION_ARGUMENT_SELECTION_START_INT";
    public static final String ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE = "ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE";
    public static final int ACTION_CLEAR_ACCESSIBILITY_FOCUS = 128;
    public static final int ACTION_CLEAR_FOCUS = 2;
    public static final int ACTION_CLEAR_SELECTION = 8;
    public static final int ACTION_CLICK = 16;
    public static final int ACTION_COLLAPSE = 524288;
    public static final int ACTION_COPY = 16384;
    public static final int ACTION_CUT = 65536;
    public static final int ACTION_DISMISS = 1048576;
    public static final int ACTION_EXPAND = 262144;
    public static final int ACTION_FOCUS = 1;
    public static final int ACTION_LONG_CLICK = 32;
    public static final int ACTION_NEXT_AT_MOVEMENT_GRANULARITY = 256;
    public static final int ACTION_NEXT_HTML_ELEMENT = 1024;
    public static final int ACTION_PASTE = 32768;
    public static final int ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = 512;
    public static final int ACTION_PREVIOUS_HTML_ELEMENT = 2048;
    public static final int ACTION_SCROLL_BACKWARD = 8192;
    public static final int ACTION_SCROLL_FORWARD = 4096;
    public static final int ACTION_SELECT = 4;
    public static final int ACTION_SET_SELECTION = 131072;
    public static final int ACTION_SET_TEXT = 2097152;
    private static final int BOOLEAN_PROPERTY_IS_HEADING = 2;
    private static final int BOOLEAN_PROPERTY_IS_SHOWING_HINT = 4;
    private static final String BOOLEAN_PROPERTY_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY";
    private static final int BOOLEAN_PROPERTY_SCREEN_READER_FOCUSABLE = 1;
    public static final int FOCUS_ACCESSIBILITY = 2;
    public static final int FOCUS_INPUT = 1;
    private static final String HINT_TEXT_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.HINT_TEXT_KEY";
    public static final int MOVEMENT_GRANULARITY_CHARACTER = 1;
    public static final int MOVEMENT_GRANULARITY_LINE = 4;
    public static final int MOVEMENT_GRANULARITY_PAGE = 16;
    public static final int MOVEMENT_GRANULARITY_PARAGRAPH = 8;
    public static final int MOVEMENT_GRANULARITY_WORD = 2;
    private static final String PANE_TITLE_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY";
    private static final String ROLE_DESCRIPTION_KEY = "AccessibilityNodeInfo.roleDescription";
    private static final String TOOLTIP_TEXT_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.TOOLTIP_TEXT_KEY";
    private final AccessibilityNodeInfo mInfo;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int mParentVirtualDescendantId = -1;

    /* renamed from: android.support.v4.view.accessibility.AccessibilityNodeInfoCompat$AccessibilityActionCompat */
    public static class AccessibilityActionCompat {
        public static final AccessibilityActionCompat ACTION_ACCESSIBILITY_FOCUS;
        public static final AccessibilityActionCompat ACTION_CLEAR_ACCESSIBILITY_FOCUS;
        public static final AccessibilityActionCompat ACTION_CLEAR_FOCUS;
        public static final AccessibilityActionCompat ACTION_CLEAR_SELECTION;
        public static final AccessibilityActionCompat ACTION_CLICK;
        public static final AccessibilityActionCompat ACTION_COLLAPSE;
        public static final AccessibilityActionCompat ACTION_CONTEXT_CLICK;
        public static final AccessibilityActionCompat ACTION_COPY;
        public static final AccessibilityActionCompat ACTION_CUT;
        public static final AccessibilityActionCompat ACTION_DISMISS;
        public static final AccessibilityActionCompat ACTION_EXPAND;
        public static final AccessibilityActionCompat ACTION_FOCUS;
        public static final AccessibilityActionCompat ACTION_HIDE_TOOLTIP;
        public static final AccessibilityActionCompat ACTION_LONG_CLICK;
        public static final AccessibilityActionCompat ACTION_MOVE_WINDOW;
        public static final AccessibilityActionCompat ACTION_NEXT_AT_MOVEMENT_GRANULARITY;
        public static final AccessibilityActionCompat ACTION_NEXT_HTML_ELEMENT;
        public static final AccessibilityActionCompat ACTION_PASTE;
        public static final AccessibilityActionCompat ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY;
        public static final AccessibilityActionCompat ACTION_PREVIOUS_HTML_ELEMENT;
        public static final AccessibilityActionCompat ACTION_SCROLL_BACKWARD;
        public static final AccessibilityActionCompat ACTION_SCROLL_DOWN;
        public static final AccessibilityActionCompat ACTION_SCROLL_FORWARD;
        public static final AccessibilityActionCompat ACTION_SCROLL_LEFT;
        public static final AccessibilityActionCompat ACTION_SCROLL_RIGHT;
        public static final AccessibilityActionCompat ACTION_SCROLL_TO_POSITION;
        public static final AccessibilityActionCompat ACTION_SCROLL_UP;
        public static final AccessibilityActionCompat ACTION_SELECT;
        public static final AccessibilityActionCompat ACTION_SET_PROGRESS;
        public static final AccessibilityActionCompat ACTION_SET_SELECTION;
        public static final AccessibilityActionCompat ACTION_SET_TEXT;
        public static final AccessibilityActionCompat ACTION_SHOW_ON_SCREEN;
        public static final AccessibilityActionCompat ACTION_SHOW_TOOLTIP;
        final Object mAction;

        static {
            AccessibilityActionCompat accessibilityActionCompat;
            AccessibilityActionCompat accessibilityActionCompat2;
            AccessibilityActionCompat accessibilityActionCompat3;
            AccessibilityActionCompat accessibilityActionCompat4;
            AccessibilityActionCompat accessibilityActionCompat5;
            AccessibilityActionCompat accessibilityActionCompat6;
            AccessibilityActionCompat accessibilityActionCompat7;
            AccessibilityActionCompat accessibilityActionCompat8;
            AccessibilityActionCompat accessibilityActionCompat9;
            AccessibilityActionCompat accessibilityActionCompat10;
            AccessibilityActionCompat accessibilityActionCompat11;
            AccessibilityActionCompat accessibilityActionCompat12;
            AccessibilityActionCompat accessibilityActionCompat13;
            AccessibilityActionCompat accessibilityActionCompat14;
            AccessibilityActionCompat accessibilityActionCompat15;
            AccessibilityActionCompat accessibilityActionCompat16;
            AccessibilityActionCompat accessibilityActionCompat17;
            AccessibilityActionCompat accessibilityActionCompat18;
            AccessibilityActionCompat accessibilityActionCompat19;
            AccessibilityActionCompat accessibilityActionCompat20;
            AccessibilityActionCompat accessibilityActionCompat21;
            AccessibilityActionCompat accessibilityActionCompat22;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction2;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction3;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction4;
            new AccessibilityActionCompat(1, (CharSequence) null);
            ACTION_FOCUS = accessibilityActionCompat;
            new AccessibilityActionCompat(2, (CharSequence) null);
            ACTION_CLEAR_FOCUS = accessibilityActionCompat2;
            new AccessibilityActionCompat(4, (CharSequence) null);
            ACTION_SELECT = accessibilityActionCompat3;
            new AccessibilityActionCompat(8, (CharSequence) null);
            ACTION_CLEAR_SELECTION = accessibilityActionCompat4;
            new AccessibilityActionCompat(16, (CharSequence) null);
            ACTION_CLICK = accessibilityActionCompat5;
            new AccessibilityActionCompat(32, (CharSequence) null);
            ACTION_LONG_CLICK = accessibilityActionCompat6;
            new AccessibilityActionCompat(64, (CharSequence) null);
            ACTION_ACCESSIBILITY_FOCUS = accessibilityActionCompat7;
            new AccessibilityActionCompat(128, (CharSequence) null);
            ACTION_CLEAR_ACCESSIBILITY_FOCUS = accessibilityActionCompat8;
            new AccessibilityActionCompat(256, (CharSequence) null);
            ACTION_NEXT_AT_MOVEMENT_GRANULARITY = accessibilityActionCompat9;
            new AccessibilityActionCompat(512, (CharSequence) null);
            ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = accessibilityActionCompat10;
            new AccessibilityActionCompat(1024, (CharSequence) null);
            ACTION_NEXT_HTML_ELEMENT = accessibilityActionCompat11;
            new AccessibilityActionCompat(2048, (CharSequence) null);
            ACTION_PREVIOUS_HTML_ELEMENT = accessibilityActionCompat12;
            new AccessibilityActionCompat(4096, (CharSequence) null);
            ACTION_SCROLL_FORWARD = accessibilityActionCompat13;
            new AccessibilityActionCompat(8192, (CharSequence) null);
            ACTION_SCROLL_BACKWARD = accessibilityActionCompat14;
            new AccessibilityActionCompat(16384, (CharSequence) null);
            ACTION_COPY = accessibilityActionCompat15;
            new AccessibilityActionCompat(32768, (CharSequence) null);
            ACTION_PASTE = accessibilityActionCompat16;
            new AccessibilityActionCompat(65536, (CharSequence) null);
            ACTION_CUT = accessibilityActionCompat17;
            new AccessibilityActionCompat(131072, (CharSequence) null);
            ACTION_SET_SELECTION = accessibilityActionCompat18;
            new AccessibilityActionCompat(262144, (CharSequence) null);
            ACTION_EXPAND = accessibilityActionCompat19;
            new AccessibilityActionCompat(524288, (CharSequence) null);
            ACTION_COLLAPSE = accessibilityActionCompat20;
            new AccessibilityActionCompat(1048576, (CharSequence) null);
            ACTION_DISMISS = accessibilityActionCompat21;
            new AccessibilityActionCompat(2097152, (CharSequence) null);
            ACTION_SET_TEXT = accessibilityActionCompat22;
            AccessibilityActionCompat accessibilityActionCompat23 = r4;
            if (Build.VERSION.SDK_INT >= 23) {
                accessibilityAction = AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN;
            } else {
                accessibilityAction = null;
            }
            AccessibilityActionCompat accessibilityActionCompat24 = new AccessibilityActionCompat(accessibilityAction);
            ACTION_SHOW_ON_SCREEN = accessibilityActionCompat23;
            AccessibilityActionCompat accessibilityActionCompat25 = r4;
            AccessibilityActionCompat accessibilityActionCompat26 = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION : null);
            ACTION_SCROLL_TO_POSITION = accessibilityActionCompat25;
            AccessibilityActionCompat accessibilityActionCompat27 = r4;
            AccessibilityActionCompat accessibilityActionCompat28 = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP : null);
            ACTION_SCROLL_UP = accessibilityActionCompat27;
            AccessibilityActionCompat accessibilityActionCompat29 = r4;
            AccessibilityActionCompat accessibilityActionCompat30 = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT : null);
            ACTION_SCROLL_LEFT = accessibilityActionCompat29;
            AccessibilityActionCompat accessibilityActionCompat31 = r4;
            AccessibilityActionCompat accessibilityActionCompat32 = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN : null);
            ACTION_SCROLL_DOWN = accessibilityActionCompat31;
            AccessibilityActionCompat accessibilityActionCompat33 = r4;
            AccessibilityActionCompat accessibilityActionCompat34 = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT : null);
            ACTION_SCROLL_RIGHT = accessibilityActionCompat33;
            AccessibilityActionCompat accessibilityActionCompat35 = r4;
            if (Build.VERSION.SDK_INT >= 23) {
                accessibilityAction2 = AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK;
            } else {
                accessibilityAction2 = null;
            }
            AccessibilityActionCompat accessibilityActionCompat36 = new AccessibilityActionCompat(accessibilityAction2);
            ACTION_CONTEXT_CLICK = accessibilityActionCompat35;
            AccessibilityActionCompat accessibilityActionCompat37 = r4;
            if (Build.VERSION.SDK_INT >= 24) {
                accessibilityAction3 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS;
            } else {
                accessibilityAction3 = null;
            }
            AccessibilityActionCompat accessibilityActionCompat38 = new AccessibilityActionCompat(accessibilityAction3);
            ACTION_SET_PROGRESS = accessibilityActionCompat37;
            AccessibilityActionCompat accessibilityActionCompat39 = r4;
            AccessibilityActionCompat accessibilityActionCompat40 = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 26 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW : null);
            ACTION_MOVE_WINDOW = accessibilityActionCompat39;
            AccessibilityActionCompat accessibilityActionCompat41 = r4;
            AccessibilityActionCompat accessibilityActionCompat42 = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP : null);
            ACTION_SHOW_TOOLTIP = accessibilityActionCompat41;
            AccessibilityActionCompat accessibilityActionCompat43 = r4;
            if (Build.VERSION.SDK_INT >= 28) {
                accessibilityAction4 = AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP;
            } else {
                accessibilityAction4 = null;
            }
            AccessibilityActionCompat accessibilityActionCompat44 = new AccessibilityActionCompat(accessibilityAction4);
            ACTION_HIDE_TOOLTIP = accessibilityActionCompat43;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public AccessibilityActionCompat(int r10, java.lang.CharSequence r11) {
            /*
                r9 = this;
                r0 = r9
                r1 = r10
                r2 = r11
                r3 = r0
                int r4 = android.os.Build.VERSION.SDK_INT
                r5 = 21
                if (r4 < r5) goto L_0x0018
                android.view.accessibility.AccessibilityNodeInfo$AccessibilityAction r4 = new android.view.accessibility.AccessibilityNodeInfo$AccessibilityAction
                r8 = r4
                r4 = r8
                r5 = r8
                r6 = r1
                r7 = r2
                r5.<init>(r6, r7)
            L_0x0014:
                r3.<init>(r4)
                return
            L_0x0018:
                r4 = 0
                goto L_0x0014
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.<init>(int, java.lang.CharSequence):void");
        }

        AccessibilityActionCompat(Object action) {
            this.mAction = action;
        }

        public int getId() {
            if (Build.VERSION.SDK_INT >= 21) {
                return ((AccessibilityNodeInfo.AccessibilityAction) this.mAction).getId();
            }
            return 0;
        }

        public CharSequence getLabel() {
            if (Build.VERSION.SDK_INT >= 21) {
                return ((AccessibilityNodeInfo.AccessibilityAction) this.mAction).getLabel();
            }
            return null;
        }
    }

    /* renamed from: android.support.v4.view.accessibility.AccessibilityNodeInfoCompat$CollectionInfoCompat */
    public static class CollectionInfoCompat {
        public static final int SELECTION_MODE_MULTIPLE = 2;
        public static final int SELECTION_MODE_NONE = 0;
        public static final int SELECTION_MODE_SINGLE = 1;
        final Object mInfo;

        public static CollectionInfoCompat obtain(int i, int i2, boolean z, int i3) {
            CollectionInfoCompat collectionInfoCompat;
            CollectionInfoCompat collectionInfoCompat2;
            CollectionInfoCompat collectionInfoCompat3;
            int rowCount = i;
            int columnCount = i2;
            boolean hierarchical = z;
            int selectionMode = i3;
            if (Build.VERSION.SDK_INT >= 21) {
                new CollectionInfoCompat(AccessibilityNodeInfo.CollectionInfo.obtain(rowCount, columnCount, hierarchical, selectionMode));
                return collectionInfoCompat3;
            } else if (Build.VERSION.SDK_INT >= 19) {
                new CollectionInfoCompat(AccessibilityNodeInfo.CollectionInfo.obtain(rowCount, columnCount, hierarchical));
                return collectionInfoCompat2;
            } else {
                new CollectionInfoCompat((Object) null);
                return collectionInfoCompat;
            }
        }

        public static CollectionInfoCompat obtain(int i, int i2, boolean z) {
            CollectionInfoCompat collectionInfoCompat;
            CollectionInfoCompat collectionInfoCompat2;
            int rowCount = i;
            int columnCount = i2;
            boolean hierarchical = z;
            if (Build.VERSION.SDK_INT >= 19) {
                new CollectionInfoCompat(AccessibilityNodeInfo.CollectionInfo.obtain(rowCount, columnCount, hierarchical));
                return collectionInfoCompat2;
            }
            new CollectionInfoCompat((Object) null);
            return collectionInfoCompat;
        }

        CollectionInfoCompat(Object info) {
            this.mInfo = info;
        }

        public int getColumnCount() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.CollectionInfo) this.mInfo).getColumnCount();
            }
            return 0;
        }

        public int getRowCount() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.CollectionInfo) this.mInfo).getRowCount();
            }
            return 0;
        }

        public boolean isHierarchical() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.CollectionInfo) this.mInfo).isHierarchical();
            }
            return false;
        }

        public int getSelectionMode() {
            if (Build.VERSION.SDK_INT >= 21) {
                return ((AccessibilityNodeInfo.CollectionInfo) this.mInfo).getSelectionMode();
            }
            return 0;
        }
    }

    /* renamed from: android.support.v4.view.accessibility.AccessibilityNodeInfoCompat$CollectionItemInfoCompat */
    public static class CollectionItemInfoCompat {
        final Object mInfo;

        public static CollectionItemInfoCompat obtain(int i, int i2, int i3, int i4, boolean z, boolean z2) {
            CollectionItemInfoCompat collectionItemInfoCompat;
            CollectionItemInfoCompat collectionItemInfoCompat2;
            CollectionItemInfoCompat collectionItemInfoCompat3;
            int rowIndex = i;
            int rowSpan = i2;
            int columnIndex = i3;
            int columnSpan = i4;
            boolean heading = z;
            boolean selected = z2;
            if (Build.VERSION.SDK_INT >= 21) {
                new CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionItemInfo.obtain(rowIndex, rowSpan, columnIndex, columnSpan, heading, selected));
                return collectionItemInfoCompat3;
            } else if (Build.VERSION.SDK_INT >= 19) {
                new CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionItemInfo.obtain(rowIndex, rowSpan, columnIndex, columnSpan, heading));
                return collectionItemInfoCompat2;
            } else {
                new CollectionItemInfoCompat((Object) null);
                return collectionItemInfoCompat;
            }
        }

        public static CollectionItemInfoCompat obtain(int i, int i2, int i3, int i4, boolean z) {
            CollectionItemInfoCompat collectionItemInfoCompat;
            CollectionItemInfoCompat collectionItemInfoCompat2;
            int rowIndex = i;
            int rowSpan = i2;
            int columnIndex = i3;
            int columnSpan = i4;
            boolean heading = z;
            if (Build.VERSION.SDK_INT >= 19) {
                new CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionItemInfo.obtain(rowIndex, rowSpan, columnIndex, columnSpan, heading));
                return collectionItemInfoCompat2;
            }
            new CollectionItemInfoCompat((Object) null);
            return collectionItemInfoCompat;
        }

        CollectionItemInfoCompat(Object info) {
            this.mInfo = info;
        }

        public int getColumnIndex() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.CollectionItemInfo) this.mInfo).getColumnIndex();
            }
            return 0;
        }

        public int getColumnSpan() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.CollectionItemInfo) this.mInfo).getColumnSpan();
            }
            return 0;
        }

        public int getRowIndex() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.CollectionItemInfo) this.mInfo).getRowIndex();
            }
            return 0;
        }

        public int getRowSpan() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.CollectionItemInfo) this.mInfo).getRowSpan();
            }
            return 0;
        }

        public boolean isHeading() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.CollectionItemInfo) this.mInfo).isHeading();
            }
            return false;
        }

        public boolean isSelected() {
            if (Build.VERSION.SDK_INT >= 21) {
                return ((AccessibilityNodeInfo.CollectionItemInfo) this.mInfo).isSelected();
            }
            return false;
        }
    }

    /* renamed from: android.support.v4.view.accessibility.AccessibilityNodeInfoCompat$RangeInfoCompat */
    public static class RangeInfoCompat {
        public static final int RANGE_TYPE_FLOAT = 1;
        public static final int RANGE_TYPE_INT = 0;
        public static final int RANGE_TYPE_PERCENT = 2;
        final Object mInfo;

        public static RangeInfoCompat obtain(int i, float f, float f2, float f3) {
            RangeInfoCompat rangeInfoCompat;
            RangeInfoCompat rangeInfoCompat2;
            int type = i;
            float min = f;
            float max = f2;
            float current = f3;
            if (Build.VERSION.SDK_INT >= 19) {
                new RangeInfoCompat(AccessibilityNodeInfo.RangeInfo.obtain(type, min, max, current));
                return rangeInfoCompat2;
            }
            new RangeInfoCompat((Object) null);
            return rangeInfoCompat;
        }

        RangeInfoCompat(Object info) {
            this.mInfo = info;
        }

        public float getCurrent() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.RangeInfo) this.mInfo).getCurrent();
            }
            return 0.0f;
        }

        public float getMax() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.RangeInfo) this.mInfo).getMax();
            }
            return 0.0f;
        }

        public float getMin() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.RangeInfo) this.mInfo).getMin();
            }
            return 0.0f;
        }

        public int getType() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.RangeInfo) this.mInfo).getType();
            }
            return 0;
        }
    }

    static AccessibilityNodeInfoCompat wrapNonNullInstance(Object obj) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat;
        Object object = obj;
        if (object == null) {
            return null;
        }
        new AccessibilityNodeInfoCompat(object);
        return accessibilityNodeInfoCompat;
    }

    @Deprecated
    public AccessibilityNodeInfoCompat(Object info) {
        this.mInfo = (AccessibilityNodeInfo) info;
    }

    private AccessibilityNodeInfoCompat(AccessibilityNodeInfo info) {
        this.mInfo = info;
    }

    public static AccessibilityNodeInfoCompat wrap(@NonNull AccessibilityNodeInfo info) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat;
        new AccessibilityNodeInfoCompat(info);
        return accessibilityNodeInfoCompat;
    }

    public AccessibilityNodeInfo unwrap() {
        return this.mInfo;
    }

    @Deprecated
    public Object getInfo() {
        return this.mInfo;
    }

    public static AccessibilityNodeInfoCompat obtain(View source) {
        return wrap(AccessibilityNodeInfo.obtain(source));
    }

    public static AccessibilityNodeInfoCompat obtain(View view, int i) {
        View root = view;
        int virtualDescendantId = i;
        if (Build.VERSION.SDK_INT >= 16) {
            return wrapNonNullInstance(AccessibilityNodeInfo.obtain(root, virtualDescendantId));
        }
        return null;
    }

    public static AccessibilityNodeInfoCompat obtain() {
        return wrap(AccessibilityNodeInfo.obtain());
    }

    public static AccessibilityNodeInfoCompat obtain(AccessibilityNodeInfoCompat info) {
        return wrap(AccessibilityNodeInfo.obtain(info.mInfo));
    }

    public void setSource(View source) {
        this.mInfo.setSource(source);
    }

    public void setSource(View view, int i) {
        View root = view;
        int virtualDescendantId = i;
        if (Build.VERSION.SDK_INT >= 16) {
            this.mInfo.setSource(root, virtualDescendantId);
        }
    }

    public AccessibilityNodeInfoCompat findFocus(int i) {
        int focus = i;
        if (Build.VERSION.SDK_INT >= 16) {
            return wrapNonNullInstance(this.mInfo.findFocus(focus));
        }
        return null;
    }

    public AccessibilityNodeInfoCompat focusSearch(int i) {
        int direction = i;
        if (Build.VERSION.SDK_INT >= 16) {
            return wrapNonNullInstance(this.mInfo.focusSearch(direction));
        }
        return null;
    }

    public int getWindowId() {
        return this.mInfo.getWindowId();
    }

    public int getChildCount() {
        return this.mInfo.getChildCount();
    }

    public AccessibilityNodeInfoCompat getChild(int index) {
        return wrapNonNullInstance(this.mInfo.getChild(index));
    }

    public void addChild(View child) {
        this.mInfo.addChild(child);
    }

    public void addChild(View view, int i) {
        View root = view;
        int virtualDescendantId = i;
        if (Build.VERSION.SDK_INT >= 16) {
            this.mInfo.addChild(root, virtualDescendantId);
        }
    }

    public boolean removeChild(View view) {
        View child = view;
        if (Build.VERSION.SDK_INT >= 21) {
            return this.mInfo.removeChild(child);
        }
        return false;
    }

    public boolean removeChild(View view, int i) {
        View root = view;
        int virtualDescendantId = i;
        if (Build.VERSION.SDK_INT >= 21) {
            return this.mInfo.removeChild(root, virtualDescendantId);
        }
        return false;
    }

    public int getActions() {
        return this.mInfo.getActions();
    }

    public void addAction(int action) {
        this.mInfo.addAction(action);
    }

    public void addAction(AccessibilityActionCompat accessibilityActionCompat) {
        AccessibilityActionCompat action = accessibilityActionCompat;
        if (Build.VERSION.SDK_INT >= 21) {
            this.mInfo.addAction((AccessibilityNodeInfo.AccessibilityAction) action.mAction);
        }
    }

    public boolean removeAction(AccessibilityActionCompat accessibilityActionCompat) {
        AccessibilityActionCompat action = accessibilityActionCompat;
        if (Build.VERSION.SDK_INT >= 21) {
            return this.mInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction) action.mAction);
        }
        return false;
    }

    public boolean performAction(int action) {
        return this.mInfo.performAction(action);
    }

    public boolean performAction(int i, Bundle bundle) {
        int action = i;
        Bundle arguments = bundle;
        if (Build.VERSION.SDK_INT >= 16) {
            return this.mInfo.performAction(action, arguments);
        }
        return false;
    }

    public void setMovementGranularities(int i) {
        int granularities = i;
        if (Build.VERSION.SDK_INT >= 16) {
            this.mInfo.setMovementGranularities(granularities);
        }
    }

    public int getMovementGranularities() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.mInfo.getMovementGranularities();
        }
        return 0;
    }

    public List<AccessibilityNodeInfoCompat> findAccessibilityNodeInfosByText(String text) {
        List<AccessibilityNodeInfoCompat> list;
        new ArrayList();
        List<AccessibilityNodeInfoCompat> result = list;
        List<AccessibilityNodeInfo> infos = this.mInfo.findAccessibilityNodeInfosByText(text);
        int infoCount = infos.size();
        for (int i = 0; i < infoCount; i++) {
            boolean add = result.add(wrap(infos.get(i)));
        }
        return result;
    }

    public AccessibilityNodeInfoCompat getParent() {
        return wrapNonNullInstance(this.mInfo.getParent());
    }

    public void setParent(View parent) {
        this.mInfo.setParent(parent);
    }

    public void setParent(View view, int i) {
        View root = view;
        int virtualDescendantId = i;
        this.mParentVirtualDescendantId = virtualDescendantId;
        if (Build.VERSION.SDK_INT >= 16) {
            this.mInfo.setParent(root, virtualDescendantId);
        }
    }

    public void getBoundsInParent(Rect outBounds) {
        this.mInfo.getBoundsInParent(outBounds);
    }

    public void setBoundsInParent(Rect bounds) {
        this.mInfo.setBoundsInParent(bounds);
    }

    public void getBoundsInScreen(Rect outBounds) {
        this.mInfo.getBoundsInScreen(outBounds);
    }

    public void setBoundsInScreen(Rect bounds) {
        this.mInfo.setBoundsInScreen(bounds);
    }

    public boolean isCheckable() {
        return this.mInfo.isCheckable();
    }

    public void setCheckable(boolean checkable) {
        this.mInfo.setCheckable(checkable);
    }

    public boolean isChecked() {
        return this.mInfo.isChecked();
    }

    public void setChecked(boolean checked) {
        this.mInfo.setChecked(checked);
    }

    public boolean isFocusable() {
        return this.mInfo.isFocusable();
    }

    public void setFocusable(boolean focusable) {
        this.mInfo.setFocusable(focusable);
    }

    public boolean isFocused() {
        return this.mInfo.isFocused();
    }

    public void setFocused(boolean focused) {
        this.mInfo.setFocused(focused);
    }

    public boolean isVisibleToUser() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.mInfo.isVisibleToUser();
        }
        return false;
    }

    public void setVisibleToUser(boolean z) {
        boolean visibleToUser = z;
        if (Build.VERSION.SDK_INT >= 16) {
            this.mInfo.setVisibleToUser(visibleToUser);
        }
    }

    public boolean isAccessibilityFocused() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.mInfo.isAccessibilityFocused();
        }
        return false;
    }

    public void setAccessibilityFocused(boolean z) {
        boolean focused = z;
        if (Build.VERSION.SDK_INT >= 16) {
            this.mInfo.setAccessibilityFocused(focused);
        }
    }

    public boolean isSelected() {
        return this.mInfo.isSelected();
    }

    public void setSelected(boolean selected) {
        this.mInfo.setSelected(selected);
    }

    public boolean isClickable() {
        return this.mInfo.isClickable();
    }

    public void setClickable(boolean clickable) {
        this.mInfo.setClickable(clickable);
    }

    public boolean isLongClickable() {
        return this.mInfo.isLongClickable();
    }

    public void setLongClickable(boolean longClickable) {
        this.mInfo.setLongClickable(longClickable);
    }

    public boolean isEnabled() {
        return this.mInfo.isEnabled();
    }

    public void setEnabled(boolean enabled) {
        this.mInfo.setEnabled(enabled);
    }

    public boolean isPassword() {
        return this.mInfo.isPassword();
    }

    public void setPassword(boolean password) {
        this.mInfo.setPassword(password);
    }

    public boolean isScrollable() {
        return this.mInfo.isScrollable();
    }

    public void setScrollable(boolean scrollable) {
        this.mInfo.setScrollable(scrollable);
    }

    public boolean isImportantForAccessibility() {
        if (Build.VERSION.SDK_INT >= 24) {
            return this.mInfo.isImportantForAccessibility();
        }
        return true;
    }

    public void setImportantForAccessibility(boolean z) {
        boolean important = z;
        if (Build.VERSION.SDK_INT >= 24) {
            this.mInfo.setImportantForAccessibility(important);
        }
    }

    public CharSequence getPackageName() {
        return this.mInfo.getPackageName();
    }

    public void setPackageName(CharSequence packageName) {
        this.mInfo.setPackageName(packageName);
    }

    public CharSequence getClassName() {
        return this.mInfo.getClassName();
    }

    public void setClassName(CharSequence className) {
        this.mInfo.setClassName(className);
    }

    public CharSequence getText() {
        return this.mInfo.getText();
    }

    public void setText(CharSequence text) {
        this.mInfo.setText(text);
    }

    public CharSequence getContentDescription() {
        return this.mInfo.getContentDescription();
    }

    public void setContentDescription(CharSequence contentDescription) {
        this.mInfo.setContentDescription(contentDescription);
    }

    public void recycle() {
        this.mInfo.recycle();
    }

    public void setViewIdResourceName(String str) {
        String viewId = str;
        if (Build.VERSION.SDK_INT >= 18) {
            this.mInfo.setViewIdResourceName(viewId);
        }
    }

    public String getViewIdResourceName() {
        if (Build.VERSION.SDK_INT >= 18) {
            return this.mInfo.getViewIdResourceName();
        }
        return null;
    }

    public int getLiveRegion() {
        if (Build.VERSION.SDK_INT >= 19) {
            return this.mInfo.getLiveRegion();
        }
        return 0;
    }

    public void setLiveRegion(int i) {
        int mode = i;
        if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setLiveRegion(mode);
        }
    }

    public int getDrawingOrder() {
        if (Build.VERSION.SDK_INT >= 24) {
            return this.mInfo.getDrawingOrder();
        }
        return 0;
    }

    public void setDrawingOrder(int i) {
        int drawingOrderInParent = i;
        if (Build.VERSION.SDK_INT >= 24) {
            this.mInfo.setDrawingOrder(drawingOrderInParent);
        }
    }

    public CollectionInfoCompat getCollectionInfo() {
        AccessibilityNodeInfo.CollectionInfo info;
        CollectionInfoCompat collectionInfoCompat;
        if (Build.VERSION.SDK_INT < 19 || (info = this.mInfo.getCollectionInfo()) == null) {
            return null;
        }
        new CollectionInfoCompat(info);
        return collectionInfoCompat;
    }

    public void setCollectionInfo(Object obj) {
        Object collectionInfo = obj;
        if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setCollectionInfo(collectionInfo == null ? null : (AccessibilityNodeInfo.CollectionInfo) ((CollectionInfoCompat) collectionInfo).mInfo);
        }
    }

    public void setCollectionItemInfo(Object obj) {
        Object collectionItemInfo = obj;
        if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setCollectionItemInfo(collectionItemInfo == null ? null : (AccessibilityNodeInfo.CollectionItemInfo) ((CollectionItemInfoCompat) collectionItemInfo).mInfo);
        }
    }

    public CollectionItemInfoCompat getCollectionItemInfo() {
        AccessibilityNodeInfo.CollectionItemInfo info;
        CollectionItemInfoCompat collectionItemInfoCompat;
        if (Build.VERSION.SDK_INT < 19 || (info = this.mInfo.getCollectionItemInfo()) == null) {
            return null;
        }
        new CollectionItemInfoCompat(info);
        return collectionItemInfoCompat;
    }

    public RangeInfoCompat getRangeInfo() {
        AccessibilityNodeInfo.RangeInfo info;
        RangeInfoCompat rangeInfoCompat;
        if (Build.VERSION.SDK_INT < 19 || (info = this.mInfo.getRangeInfo()) == null) {
            return null;
        }
        new RangeInfoCompat(info);
        return rangeInfoCompat;
    }

    public void setRangeInfo(RangeInfoCompat rangeInfoCompat) {
        RangeInfoCompat rangeInfo = rangeInfoCompat;
        if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setRangeInfo((AccessibilityNodeInfo.RangeInfo) rangeInfo.mInfo);
        }
    }

    public List<AccessibilityActionCompat> getActionList() {
        List<AccessibilityActionCompat> list;
        Object obj;
        List<AccessibilityNodeInfo.AccessibilityAction> list2 = null;
        if (Build.VERSION.SDK_INT >= 21) {
            list2 = this.mInfo.getActionList();
        }
        if (list2 == null) {
            return Collections.emptyList();
        }
        new ArrayList();
        List<AccessibilityActionCompat> result = list;
        int actionCount = list2.size();
        for (int i = 0; i < actionCount; i++) {
            new AccessibilityActionCompat(list2.get(i));
            boolean add = result.add(obj);
        }
        return result;
    }

    public void setContentInvalid(boolean z) {
        boolean contentInvalid = z;
        if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setContentInvalid(contentInvalid);
        }
    }

    public boolean isContentInvalid() {
        if (Build.VERSION.SDK_INT >= 19) {
            return this.mInfo.isContentInvalid();
        }
        return false;
    }

    public boolean isContextClickable() {
        if (Build.VERSION.SDK_INT >= 23) {
            return this.mInfo.isContextClickable();
        }
        return false;
    }

    public void setContextClickable(boolean z) {
        boolean contextClickable = z;
        if (Build.VERSION.SDK_INT >= 23) {
            this.mInfo.setContextClickable(contextClickable);
        }
    }

    @Nullable
    public CharSequence getHintText() {
        if (Build.VERSION.SDK_INT >= 26) {
            return this.mInfo.getHintText();
        }
        if (Build.VERSION.SDK_INT >= 19) {
            return this.mInfo.getExtras().getCharSequence(HINT_TEXT_KEY);
        }
        return null;
    }

    public void setHintText(@Nullable CharSequence charSequence) {
        CharSequence hintText = charSequence;
        if (Build.VERSION.SDK_INT >= 26) {
            this.mInfo.setHintText(hintText);
        } else if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.getExtras().putCharSequence(HINT_TEXT_KEY, hintText);
        }
    }

    public void setError(CharSequence charSequence) {
        CharSequence error = charSequence;
        if (Build.VERSION.SDK_INT >= 21) {
            this.mInfo.setError(error);
        }
    }

    public CharSequence getError() {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.mInfo.getError();
        }
        return null;
    }

    public void setLabelFor(View view) {
        View labeled = view;
        if (Build.VERSION.SDK_INT >= 17) {
            this.mInfo.setLabelFor(labeled);
        }
    }

    public void setLabelFor(View view, int i) {
        View root = view;
        int virtualDescendantId = i;
        if (Build.VERSION.SDK_INT >= 17) {
            this.mInfo.setLabelFor(root, virtualDescendantId);
        }
    }

    public AccessibilityNodeInfoCompat getLabelFor() {
        if (Build.VERSION.SDK_INT >= 17) {
            return wrapNonNullInstance(this.mInfo.getLabelFor());
        }
        return null;
    }

    public void setLabeledBy(View view) {
        View label = view;
        if (Build.VERSION.SDK_INT >= 17) {
            this.mInfo.setLabeledBy(label);
        }
    }

    public void setLabeledBy(View view, int i) {
        View root = view;
        int virtualDescendantId = i;
        if (Build.VERSION.SDK_INT >= 17) {
            this.mInfo.setLabeledBy(root, virtualDescendantId);
        }
    }

    public AccessibilityNodeInfoCompat getLabeledBy() {
        if (Build.VERSION.SDK_INT >= 17) {
            return wrapNonNullInstance(this.mInfo.getLabeledBy());
        }
        return null;
    }

    public boolean canOpenPopup() {
        if (Build.VERSION.SDK_INT >= 19) {
            return this.mInfo.canOpenPopup();
        }
        return false;
    }

    public void setCanOpenPopup(boolean z) {
        boolean opensPopup = z;
        if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setCanOpenPopup(opensPopup);
        }
    }

    public List<AccessibilityNodeInfoCompat> findAccessibilityNodeInfosByViewId(String str) {
        List<AccessibilityNodeInfoCompat> list;
        String viewId = str;
        if (Build.VERSION.SDK_INT < 18) {
            return Collections.emptyList();
        }
        List<AccessibilityNodeInfo> nodes = this.mInfo.findAccessibilityNodeInfosByViewId(viewId);
        new ArrayList();
        List<AccessibilityNodeInfoCompat> result = list;
        for (AccessibilityNodeInfo node : nodes) {
            boolean add = result.add(wrap(node));
        }
        return result;
    }

    public Bundle getExtras() {
        Bundle bundle;
        if (Build.VERSION.SDK_INT >= 19) {
            return this.mInfo.getExtras();
        }
        new Bundle();
        return bundle;
    }

    public int getInputType() {
        if (Build.VERSION.SDK_INT >= 19) {
            return this.mInfo.getInputType();
        }
        return 0;
    }

    public void setInputType(int i) {
        int inputType = i;
        if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setInputType(inputType);
        }
    }

    public void setMaxTextLength(int i) {
        int max = i;
        if (Build.VERSION.SDK_INT >= 21) {
            this.mInfo.setMaxTextLength(max);
        }
    }

    public int getMaxTextLength() {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.mInfo.getMaxTextLength();
        }
        return -1;
    }

    public void setTextSelection(int i, int i2) {
        int start = i;
        int end = i2;
        if (Build.VERSION.SDK_INT >= 18) {
            this.mInfo.setTextSelection(start, end);
        }
    }

    public int getTextSelectionStart() {
        if (Build.VERSION.SDK_INT >= 18) {
            return this.mInfo.getTextSelectionStart();
        }
        return -1;
    }

    public int getTextSelectionEnd() {
        if (Build.VERSION.SDK_INT >= 18) {
            return this.mInfo.getTextSelectionEnd();
        }
        return -1;
    }

    public AccessibilityNodeInfoCompat getTraversalBefore() {
        if (Build.VERSION.SDK_INT >= 22) {
            return wrapNonNullInstance(this.mInfo.getTraversalBefore());
        }
        return null;
    }

    public void setTraversalBefore(View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 22) {
            this.mInfo.setTraversalBefore(view2);
        }
    }

    public void setTraversalBefore(View view, int i) {
        View root = view;
        int virtualDescendantId = i;
        if (Build.VERSION.SDK_INT >= 22) {
            this.mInfo.setTraversalBefore(root, virtualDescendantId);
        }
    }

    public AccessibilityNodeInfoCompat getTraversalAfter() {
        if (Build.VERSION.SDK_INT >= 22) {
            return wrapNonNullInstance(this.mInfo.getTraversalAfter());
        }
        return null;
    }

    public void setTraversalAfter(View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 22) {
            this.mInfo.setTraversalAfter(view2);
        }
    }

    public void setTraversalAfter(View view, int i) {
        View root = view;
        int virtualDescendantId = i;
        if (Build.VERSION.SDK_INT >= 22) {
            this.mInfo.setTraversalAfter(root, virtualDescendantId);
        }
    }

    public AccessibilityWindowInfoCompat getWindow() {
        if (Build.VERSION.SDK_INT >= 21) {
            return AccessibilityWindowInfoCompat.wrapNonNullInstance(this.mInfo.getWindow());
        }
        return null;
    }

    public boolean isDismissable() {
        if (Build.VERSION.SDK_INT >= 19) {
            return this.mInfo.isDismissable();
        }
        return false;
    }

    public void setDismissable(boolean z) {
        boolean dismissable = z;
        if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setDismissable(dismissable);
        }
    }

    public boolean isEditable() {
        if (Build.VERSION.SDK_INT >= 18) {
            return this.mInfo.isEditable();
        }
        return false;
    }

    public void setEditable(boolean z) {
        boolean editable = z;
        if (Build.VERSION.SDK_INT >= 18) {
            this.mInfo.setEditable(editable);
        }
    }

    public boolean isMultiLine() {
        if (Build.VERSION.SDK_INT >= 19) {
            return this.mInfo.isMultiLine();
        }
        return false;
    }

    public void setMultiLine(boolean z) {
        boolean multiLine = z;
        if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setMultiLine(multiLine);
        }
    }

    @Nullable
    public CharSequence getTooltipText() {
        if (Build.VERSION.SDK_INT >= 28) {
            return this.mInfo.getTooltipText();
        }
        if (Build.VERSION.SDK_INT >= 19) {
            return this.mInfo.getExtras().getCharSequence(TOOLTIP_TEXT_KEY);
        }
        return null;
    }

    public void setTooltipText(@Nullable CharSequence charSequence) {
        CharSequence tooltipText = charSequence;
        if (Build.VERSION.SDK_INT >= 28) {
            this.mInfo.setTooltipText(tooltipText);
        } else if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.getExtras().putCharSequence(TOOLTIP_TEXT_KEY, tooltipText);
        }
    }

    public void setPaneTitle(@Nullable CharSequence charSequence) {
        CharSequence paneTitle = charSequence;
        if (Build.VERSION.SDK_INT >= 28) {
            this.mInfo.setPaneTitle(paneTitle);
        } else if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.getExtras().putCharSequence(PANE_TITLE_KEY, paneTitle);
        }
    }

    @Nullable
    public CharSequence getPaneTitle() {
        if (Build.VERSION.SDK_INT >= 28) {
            return this.mInfo.getPaneTitle();
        }
        if (Build.VERSION.SDK_INT >= 19) {
            return this.mInfo.getExtras().getCharSequence(PANE_TITLE_KEY);
        }
        return null;
    }

    public boolean isScreenReaderFocusable() {
        if (Build.VERSION.SDK_INT >= 28) {
            return this.mInfo.isScreenReaderFocusable();
        }
        return getBooleanProperty(1);
    }

    public void setScreenReaderFocusable(boolean z) {
        boolean screenReaderFocusable = z;
        if (Build.VERSION.SDK_INT >= 28) {
            this.mInfo.setScreenReaderFocusable(screenReaderFocusable);
        } else {
            setBooleanProperty(1, screenReaderFocusable);
        }
    }

    public boolean isShowingHintText() {
        if (Build.VERSION.SDK_INT >= 26) {
            return this.mInfo.isShowingHintText();
        }
        return getBooleanProperty(4);
    }

    public void setShowingHintText(boolean z) {
        boolean showingHintText = z;
        if (Build.VERSION.SDK_INT >= 26) {
            this.mInfo.setShowingHintText(showingHintText);
        } else {
            setBooleanProperty(4, showingHintText);
        }
    }

    public boolean isHeading() {
        if (Build.VERSION.SDK_INT >= 28) {
            return this.mInfo.isHeading();
        }
        if (getBooleanProperty(2)) {
            return true;
        }
        CollectionItemInfoCompat collectionItemInfo = getCollectionItemInfo();
        return collectionItemInfo != null && collectionItemInfo.isHeading();
    }

    public void setHeading(boolean z) {
        boolean isHeading = z;
        if (Build.VERSION.SDK_INT >= 28) {
            this.mInfo.setHeading(isHeading);
        } else {
            setBooleanProperty(2, isHeading);
        }
    }

    public boolean refresh() {
        if (Build.VERSION.SDK_INT >= 18) {
            return this.mInfo.refresh();
        }
        return false;
    }

    @Nullable
    public CharSequence getRoleDescription() {
        if (Build.VERSION.SDK_INT >= 19) {
            return this.mInfo.getExtras().getCharSequence(ROLE_DESCRIPTION_KEY);
        }
        return null;
    }

    public void setRoleDescription(@Nullable CharSequence charSequence) {
        CharSequence roleDescription = charSequence;
        if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.getExtras().putCharSequence(ROLE_DESCRIPTION_KEY, roleDescription);
        }
    }

    public int hashCode() {
        return this.mInfo == null ? 0 : this.mInfo.hashCode();
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        if (this == obj2) {
            return true;
        }
        if (obj2 == null) {
            return false;
        }
        if (getClass() != obj2.getClass()) {
            return false;
        }
        AccessibilityNodeInfoCompat other = (AccessibilityNodeInfoCompat) obj2;
        if (this.mInfo == null) {
            if (other.mInfo != null) {
                return false;
            }
        } else if (!this.mInfo.equals(other.mInfo)) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb;
        Rect rect;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        new StringBuilder();
        StringBuilder builder = sb;
        StringBuilder append = builder.append(super.toString());
        new Rect();
        Rect bounds = rect;
        getBoundsInParent(bounds);
        new StringBuilder();
        StringBuilder append2 = builder.append(sb2.append("; boundsInParent: ").append(bounds).toString());
        getBoundsInScreen(bounds);
        new StringBuilder();
        StringBuilder append3 = builder.append(sb3.append("; boundsInScreen: ").append(bounds).toString());
        StringBuilder append4 = builder.append("; packageName: ").append(getPackageName());
        StringBuilder append5 = builder.append("; className: ").append(getClassName());
        StringBuilder append6 = builder.append("; text: ").append(getText());
        StringBuilder append7 = builder.append("; contentDescription: ").append(getContentDescription());
        StringBuilder append8 = builder.append("; viewId: ").append(getViewIdResourceName());
        StringBuilder append9 = builder.append("; checkable: ").append(isCheckable());
        StringBuilder append10 = builder.append("; checked: ").append(isChecked());
        StringBuilder append11 = builder.append("; focusable: ").append(isFocusable());
        StringBuilder append12 = builder.append("; focused: ").append(isFocused());
        StringBuilder append13 = builder.append("; selected: ").append(isSelected());
        StringBuilder append14 = builder.append("; clickable: ").append(isClickable());
        StringBuilder append15 = builder.append("; longClickable: ").append(isLongClickable());
        StringBuilder append16 = builder.append("; enabled: ").append(isEnabled());
        StringBuilder append17 = builder.append("; password: ").append(isPassword());
        new StringBuilder();
        StringBuilder append18 = builder.append(sb4.append("; scrollable: ").append(isScrollable()).toString());
        StringBuilder append19 = builder.append("; [");
        int actionBits = getActions();
        while (actionBits != 0) {
            int action = 1 << Integer.numberOfTrailingZeros(actionBits);
            actionBits &= action ^ -1;
            StringBuilder append20 = builder.append(getActionSymbolicName(action));
            if (actionBits != 0) {
                StringBuilder append21 = builder.append(", ");
            }
        }
        StringBuilder append22 = builder.append("]");
        return builder.toString();
    }

    private void setBooleanProperty(int i, boolean z) {
        int property = i;
        boolean value = z;
        Bundle extras = getExtras();
        if (extras != null) {
            extras.putInt(BOOLEAN_PROPERTY_KEY, (extras.getInt(BOOLEAN_PROPERTY_KEY, 0) & (property ^ -1)) | (value ? property : 0));
        }
    }

    private boolean getBooleanProperty(int i) {
        int property = i;
        Bundle extras = getExtras();
        if (extras == null) {
            return false;
        }
        return (extras.getInt(BOOLEAN_PROPERTY_KEY, 0) & property) == property;
    }

    private static String getActionSymbolicName(int action) {
        switch (action) {
            case 1:
                return "ACTION_FOCUS";
            case 2:
                return "ACTION_CLEAR_FOCUS";
            case 4:
                return "ACTION_SELECT";
            case 8:
                return "ACTION_CLEAR_SELECTION";
            case 16:
                return "ACTION_CLICK";
            case 32:
                return "ACTION_LONG_CLICK";
            case 64:
                return "ACTION_ACCESSIBILITY_FOCUS";
            case 128:
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            case 256:
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            case 512:
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            case 1024:
                return "ACTION_NEXT_HTML_ELEMENT";
            case 2048:
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            case 4096:
                return "ACTION_SCROLL_FORWARD";
            case 8192:
                return "ACTION_SCROLL_BACKWARD";
            case 16384:
                return "ACTION_COPY";
            case 32768:
                return "ACTION_PASTE";
            case 65536:
                return "ACTION_CUT";
            case 131072:
                return "ACTION_SET_SELECTION";
            default:
                return "ACTION_UNKNOWN";
        }
    }
}
