package com.google.appinventor.components.runtime.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toolbar;

public class KodularRtlSupportUtil {
    @SuppressLint({"StaticFieldLeak"})
    private static Context B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    private KodularRtlSupportUtil() {
    }

    public static void setContext(Context context) {
        B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = context;
    }

    public static void setSupportPadding(View view, int i, int i2, int i3, int i4) {
        View view2 = view;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        if (view2 != null) {
            if (Build.VERSION.SDK_INT < 17) {
                view2.setPadding(i5, i6, i7, i8);
            } else {
                view2.setPaddingRelative(i5, i6, i7, i8);
            }
            view2.invalidate();
        }
    }

    public static void setSupportMargin(ViewGroup.MarginLayoutParams marginLayoutParams, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams2 = marginLayoutParams;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        if (marginLayoutParams2 != null) {
            if (Build.VERSION.SDK_INT < 17) {
                marginLayoutParams2.setMargins(i5, i6, i7, i8);
                return;
            }
            marginLayoutParams2.setMargins(i5, i6, i7, i8);
            marginLayoutParams2.setMarginStart(i5);
            marginLayoutParams2.setMarginEnd(i7);
        }
    }

    public static void setSupportMargin(LinearLayout.LayoutParams layoutParams, int i, int i2, int i3, int i4) {
        LinearLayout.LayoutParams layoutParams2 = layoutParams;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        if (layoutParams2 != null) {
            if (Build.VERSION.SDK_INT < 17) {
                layoutParams2.setMargins(i5, i6, i7, i8);
                return;
            }
            layoutParams2.setMargins(i5, i6, i7, i8);
            layoutParams2.setMarginStart(i5);
            layoutParams2.setMarginEnd(i7);
        }
    }

    public static void setSupportMargin(FrameLayout.LayoutParams layoutParams, int i, int i2, int i3, int i4) {
        FrameLayout.LayoutParams layoutParams2 = layoutParams;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        if (layoutParams2 != null) {
            if (Build.VERSION.SDK_INT < 17) {
                layoutParams2.setMargins(i5, i6, i7, i8);
                return;
            }
            layoutParams2.setMargins(i5, i6, i7, i8);
            layoutParams2.setMarginStart(i5);
            layoutParams2.setMarginEnd(i7);
        }
    }

    public static void setSupportMargin(GridLayout.LayoutParams layoutParams, int i, int i2, int i3, int i4) {
        GridLayout.LayoutParams layoutParams2 = layoutParams;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        if (layoutParams2 != null) {
            if (Build.VERSION.SDK_INT < 17) {
                layoutParams2.setMargins(i5, i6, i7, i8);
                return;
            }
            layoutParams2.setMargins(i5, i6, i7, i8);
            layoutParams2.setMarginStart(i5);
            layoutParams2.setMarginEnd(i7);
        }
    }

    public static void setSupportMargin(RelativeLayout.LayoutParams layoutParams, int i, int i2, int i3, int i4) {
        RelativeLayout.LayoutParams layoutParams2 = layoutParams;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        if (layoutParams2 != null) {
            if (Build.VERSION.SDK_INT < 17) {
                layoutParams2.setMargins(i5, i6, i7, i8);
                return;
            }
            layoutParams2.setMargins(i5, i6, i7, i8);
            layoutParams2.setMarginStart(i5);
            layoutParams2.setMarginEnd(i7);
        }
    }

    public static void setSupportMargin(TableLayout.LayoutParams layoutParams, int i, int i2, int i3, int i4) {
        TableLayout.LayoutParams layoutParams2 = layoutParams;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        if (layoutParams2 != null) {
            if (Build.VERSION.SDK_INT < 17) {
                layoutParams2.setMargins(i5, i6, i7, i8);
                return;
            }
            layoutParams2.setMargins(i5, i6, i7, i8);
            layoutParams2.setMarginStart(i5);
            layoutParams2.setMarginEnd(i7);
        }
    }

    public static void setSupportMargin(TableRow.LayoutParams layoutParams, int i, int i2, int i3, int i4) {
        TableRow.LayoutParams layoutParams2 = layoutParams;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        if (layoutParams2 != null) {
            if (Build.VERSION.SDK_INT < 17) {
                layoutParams2.setMargins(i5, i6, i7, i8);
                return;
            }
            layoutParams2.setMargins(i5, i6, i7, i8);
            layoutParams2.setMarginStart(i5);
            layoutParams2.setMarginEnd(i7);
        }
    }

    public static void setSupportMargin(Toolbar.LayoutParams layoutParams, int i, int i2, int i3, int i4) {
        Toolbar.LayoutParams layoutParams2 = layoutParams;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        if (layoutParams2 != null) {
            if (Build.VERSION.SDK_INT < 17) {
                layoutParams2.setMargins(i5, i6, i7, i8);
                return;
            }
            layoutParams2.setMargins(i5, i6, i7, i8);
            layoutParams2.setMarginStart(i5);
            layoutParams2.setMarginEnd(i7);
        }
    }

    public static void setSupportTextDirection(TextView textView) {
        TextView textView2 = textView;
        if (textView2 != null && Build.VERSION.SDK_INT >= 17 && textView2.getTextDirection() != 2 && isLayoutDirectionRTL()) {
            textView2.setTextDirection(2);
            textView2.invalidate();
        }
    }

    public static void setSupportAutoMirrored(Drawable drawable) {
        Drawable drawable2 = drawable;
        if (drawable2 != null && Build.VERSION.SDK_INT >= 19) {
            drawable2.setAutoMirrored(true);
        }
    }

    public static boolean isLayoutDirectionRTL() {
        return Build.VERSION.SDK_INT >= 17 && B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T != null && B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.getResources().getConfiguration().getLayoutDirection() == 1;
    }
}
