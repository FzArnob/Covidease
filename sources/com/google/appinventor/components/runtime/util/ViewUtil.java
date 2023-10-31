package com.google.appinventor.components.runtime.util;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;

public final class ViewUtil {
    private ViewUtil() {
    }

    public static int calculatePixels(View view, int i) {
        return (int) (view.getContext().getResources().getDisplayMetrics().density * ((float) i));
    }

    public static void setChildWidthForHorizontalLayout(View view, int i) {
        View view2 = view;
        int i2 = i;
        ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
        ViewGroup.LayoutParams layoutParams2 = layoutParams;
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) layoutParams2;
            switch (i2) {
                case -2:
                    layoutParams3.width = 0;
                    layoutParams3.weight = 1.0f;
                    break;
                case -1:
                    layoutParams3.width = -2;
                    layoutParams3.weight = 0.0f;
                    break;
                default:
                    layoutParams3.width = calculatePixels(view2, i2);
                    layoutParams3.weight = 0.0f;
                    break;
            }
            view2.requestLayout();
            return;
        }
        int e = Log.e("ViewUtil", "The view does not have linear layout parameters");
    }

    public static void setChildHeightForHorizontalLayout(View view, int i) {
        View view2 = view;
        int i2 = i;
        ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
        ViewGroup.LayoutParams layoutParams2 = layoutParams;
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) layoutParams2;
            switch (i2) {
                case -2:
                    layoutParams3.height = -1;
                    break;
                case -1:
                    layoutParams3.height = -2;
                    break;
                default:
                    layoutParams3.height = calculatePixels(view2, i2);
                    break;
            }
            view2.requestLayout();
            return;
        }
        int e = Log.e("ViewUtil", "The view does not have linear layout parameters");
    }

    public static void setChildWidthForVerticalLayout(View view, int i) {
        View view2 = view;
        int i2 = i;
        ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
        ViewGroup.LayoutParams layoutParams2 = layoutParams;
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) layoutParams2;
            switch (i2) {
                case -2:
                    layoutParams3.width = -1;
                    break;
                case -1:
                    layoutParams3.width = -2;
                    break;
                default:
                    layoutParams3.width = calculatePixels(view2, i2);
                    break;
            }
            view2.requestLayout();
            return;
        }
        int e = Log.e("ViewUtil", "The view does not have linear layout parameters");
    }

    public static void setChildHeightForVerticalLayout(View view, int i) {
        View view2 = view;
        int i2 = i;
        ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
        ViewGroup.LayoutParams layoutParams2 = layoutParams;
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) layoutParams2;
            switch (i2) {
                case -2:
                    layoutParams3.height = 0;
                    layoutParams3.weight = 1.0f;
                    break;
                case -1:
                    layoutParams3.height = -2;
                    layoutParams3.weight = 0.0f;
                    break;
                default:
                    layoutParams3.height = calculatePixels(view2, i2);
                    layoutParams3.weight = 0.0f;
                    break;
            }
            view2.requestLayout();
            return;
        }
        int e = Log.e("ViewUtil", "The view does not have linear layout parameters");
    }

    public static void setChildWidthForTableLayout(View view, int i) {
        View view2 = view;
        int i2 = i;
        ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
        ViewGroup.LayoutParams layoutParams2 = layoutParams;
        if (layoutParams instanceof TableRow.LayoutParams) {
            TableRow.LayoutParams layoutParams3 = (TableRow.LayoutParams) layoutParams2;
            switch (i2) {
                case -2:
                    layoutParams3.width = -1;
                    break;
                case -1:
                    layoutParams3.width = -2;
                    break;
                default:
                    layoutParams3.width = calculatePixels(view2, i2);
                    break;
            }
            view2.requestLayout();
            return;
        }
        int e = Log.e("ViewUtil", "The view does not have table layout parameters");
    }

    public static void setChildHeightForTableLayout(View view, int i) {
        View view2 = view;
        int i2 = i;
        ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
        ViewGroup.LayoutParams layoutParams2 = layoutParams;
        if (layoutParams instanceof TableRow.LayoutParams) {
            TableRow.LayoutParams layoutParams3 = (TableRow.LayoutParams) layoutParams2;
            switch (i2) {
                case -2:
                    layoutParams3.height = -1;
                    break;
                case -1:
                    layoutParams3.height = -2;
                    break;
                default:
                    layoutParams3.height = calculatePixels(view2, i2);
                    break;
            }
            view2.requestLayout();
            return;
        }
        int e = Log.e("ViewUtil", "The view does not have table layout parameters");
    }

    public static void setBackgroundImage(View view, Drawable drawable) {
        setBackgroundDrawable(view, drawable);
    }

    public static void setImage(ImageView imageView, Drawable drawable) {
        ImageView imageView2 = imageView;
        Drawable drawable2 = drawable;
        imageView2.setImageDrawable(drawable2);
        if (drawable2 != null) {
            imageView2.setAdjustViewBounds(true);
        }
        imageView2.requestLayout();
    }

    public static void setBackgroundDrawable(View view, Drawable drawable) {
        View view2 = view;
        view2.setBackground(drawable);
        view2.invalidate();
    }

    public static void setShape(View view, int i, int i2, boolean z) {
        GradientDrawable gradientDrawable;
        View view2 = view;
        int i3 = i;
        int i4 = i2;
        new GradientDrawable();
        GradientDrawable gradientDrawable2 = gradientDrawable;
        GradientDrawable gradientDrawable3 = gradientDrawable2;
        gradientDrawable2.setShape(0);
        gradientDrawable3.setCornerRadius(z ? 25.0f : 0.0f);
        gradientDrawable3.setColor(i3);
        gradientDrawable3.setStroke(3, i4);
        view2.setBackground(gradientDrawable3);
    }
}
