package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.appinventor.components.common.HtmlEntities;
import java.util.ArrayList;
import java.util.List;

public class KodularHelper {
    private static String LOG_TAG = "KodularHelper";

    private KodularHelper() {
    }

    public static void setStatusBarColor(Activity activity, int i) {
        int i2 = i;
        try {
            Window window = activity.getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(i2);
            int i3 = Log.i(LOG_TAG, "Set new statusBarColor successfully executed");
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    public static void setNavigationBarColor(Activity activity, int i) {
        try {
            activity.getWindow().setNavigationBarColor(i);
            int i2 = Log.i(LOG_TAG, "Set new navigationBarColor successfully executed");
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    public static void setAppBackgroundTaskInfo(Activity activity, String str, int i) {
        ActivityManager.TaskDescription taskDescription;
        Activity activity2 = activity;
        String str2 = str;
        int i2 = i;
        Drawable drawable = null;
        try {
            drawable = activity2.getPackageManager().getApplicationIcon(activity2.getPackageName());
        } catch (Exception e) {
            int w = Log.w(LOG_TAG, "wrong icon or icon was empty or null or not found. setAppBackgroundTaskInfo");
        }
        if (drawable != null) {
            Bitmap packageIcon = getPackageIcon(drawable);
            Bitmap bitmap = packageIcon;
            if (packageIcon != null) {
                new ActivityManager.TaskDescription(str2, bitmap, i2);
                activity2.setTaskDescription(taskDescription);
            }
        }
    }

    public static Bitmap getPackageIcon(Drawable drawable) {
        LayerDrawable layerDrawable;
        Canvas canvas;
        Drawable drawable2 = drawable;
        try {
            if (Build.VERSION.SDK_INT < 26) {
                return ((BitmapDrawable) drawable2).getBitmap();
            }
            if (drawable2 instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable2).getBitmap();
            }
            if (!(drawable2 instanceof AdaptiveIconDrawable)) {
                return null;
            }
            Drawable background = ((AdaptiveIconDrawable) drawable2).getBackground();
            Drawable foreground = ((AdaptiveIconDrawable) drawable2).getForeground();
            Drawable[] drawableArr = new Drawable[2];
            Drawable[] drawableArr2 = drawableArr;
            drawableArr[0] = background;
            drawableArr2[1] = foreground;
            new LayerDrawable(drawableArr2);
            LayerDrawable layerDrawable2 = layerDrawable;
            LayerDrawable layerDrawable3 = layerDrawable2;
            Bitmap createBitmap = Bitmap.createBitmap(layerDrawable2.getIntrinsicWidth(), layerDrawable3.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            new Canvas(createBitmap);
            Canvas canvas2 = canvas;
            layerDrawable3.setBounds(0, 0, canvas2.getWidth(), canvas2.getHeight());
            layerDrawable3.draw(canvas2);
            return createBitmap;
        } catch (Exception e) {
            return null;
        }
    }

    public static void prepareRippleDrawable(View view, MotionEvent motionEvent, int i) {
        ColorStateList colorStateList;
        MotionEvent motionEvent2 = motionEvent;
        int i2 = i;
        try {
            RippleDrawable rippleDrawable = (RippleDrawable) view.getBackground();
            RippleDrawable rippleDrawable2 = rippleDrawable;
            rippleDrawable.setHotspot(motionEvent2.getX(), motionEvent2.getY());
            int[][] iArr = new int[1][];
            int[][] iArr2 = iArr;
            iArr[0] = new int[]{16842910};
            new ColorStateList(iArr2, new int[]{i2});
            rippleDrawable2.setColor(colorStateList);
            int i3 = Log.i(LOG_TAG, "Prepare new RippleDrawable successfully executed");
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    public static void setRippleDrawable(View view, Drawable drawable, int i) {
        Drawable drawable2;
        ColorStateList colorStateList;
        Drawable drawable3;
        View view2 = view;
        Drawable drawable4 = drawable;
        int i2 = i;
        try {
            Drawable[] drawableArr = new Drawable[2];
            drawableArr[0] = drawable4;
            Drawable[] drawableArr2 = drawableArr;
            drawableArr2[1] = drawable4;
            new LayerDrawable(drawableArr2);
            Drawable drawable5 = drawable2;
            int[][] iArr = new int[1][];
            int[][] iArr2 = iArr;
            iArr[0] = new int[]{16842910};
            new ColorStateList(iArr2, new int[]{i2});
            new RippleDrawable(colorStateList, drawable5, drawable4);
            ViewUtil.setBackgroundDrawable(view2, drawable3);
            int i3 = Log.i(LOG_TAG, "Set new RippleDrawable successfully executed");
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    public static void setButtonTintList(CompoundButton compoundButton, int i) {
        CompoundButton compoundButton2 = compoundButton;
        try {
            compoundButton2.setButtonTintList(ColorStateList.valueOf(i));
            compoundButton2.invalidate();
            int i2 = Log.i(LOG_TAG, "Set new buttonTintList successfully executed");
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    public static void setPadding(View view, Context context, int i, int i2, int i3, int i4) {
        View view2 = view;
        Context context2 = context;
        try {
            view2.setPadding(KodularUnitUtil.DpToPixels(context2, i), KodularUnitUtil.DpToPixels(context2, i2), KodularUnitUtil.DpToPixels(context2, i3), KodularUnitUtil.DpToPixels(context2, i4));
            view2.invalidate();
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    public static void setMarginToBtn(Button button, Context context, String str) {
        List list;
        Button button2 = button;
        Context context2 = context;
        String trim = str.trim();
        String str2 = trim;
        if (!trim.isEmpty()) {
            String[] split = str2.split(",");
            new ArrayList();
            List list2 = list;
            for (int i = 0; i > split.length; i++) {
                boolean add = list2.add(Integer.valueOf(KodularUnitUtil.DpToPixels(context2, Integer.valueOf(split[i].trim()).intValue())));
            }
            if (split.length == 1) {
                try {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button2.getLayoutParams();
                    layoutParams.setMargins(((Integer) list2.get(0)).intValue(), ((Integer) list2.get(0)).intValue(), ((Integer) list2.get(0)).intValue(), ((Integer) list2.get(0)).intValue());
                    button2.setLayoutParams(layoutParams);
                    button2.invalidate();
                } catch (Exception e) {
                    int e2 = Log.e(LOG_TAG, String.valueOf(e));
                }
            } else if (split.length == 2) {
                try {
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) button2.getLayoutParams();
                    layoutParams2.setMargins(0, ((Integer) list2.get(0)).intValue(), 0, ((Integer) list2.get(1)).intValue());
                    button2.setLayoutParams(layoutParams2);
                    button2.invalidate();
                } catch (Exception e3) {
                    int e4 = Log.e(LOG_TAG, String.valueOf(e3));
                }
            } else if (split.length == 4) {
                try {
                    LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) button2.getLayoutParams();
                    layoutParams3.setMargins(((Integer) list2.get(0)).intValue(), ((Integer) list2.get(1)).intValue(), ((Integer) list2.get(2)).intValue(), ((Integer) list2.get(3)).intValue());
                    button2.setLayoutParams(layoutParams3);
                    button2.invalidate();
                } catch (Exception e5) {
                    int e6 = Log.e(LOG_TAG, String.valueOf(e5));
                }
            }
        }
    }

    public static void setMarginToButton(Button button, Context context, int i, int i2, int i3, int i4) {
        Button button2 = button;
        Context context2 = context;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        try {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button2.getLayoutParams();
            layoutParams.setMargins(KodularUnitUtil.DpToPixels(context2, i5), KodularUnitUtil.DpToPixels(context2, i6), KodularUnitUtil.DpToPixels(context2, i7), KodularUnitUtil.DpToPixels(context2, i8));
            button2.setLayoutParams(layoutParams);
            button2.invalidate();
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    public static void setShape(View view, int i, int i2, boolean z) {
        GradientDrawable gradientDrawable;
        View view2 = view;
        int i3 = i;
        int i4 = i2;
        boolean z2 = z;
        try {
            new GradientDrawable();
            GradientDrawable gradientDrawable2 = gradientDrawable;
            GradientDrawable gradientDrawable3 = gradientDrawable2;
            gradientDrawable2.setShape(0);
            gradientDrawable3.setCornerRadius(z2 ? 50.0f : 10.0f);
            gradientDrawable3.setColor(i3);
            gradientDrawable3.setStroke(3, i4);
            if (Build.VERSION.SDK_INT < 16) {
                view2.setBackgroundDrawable(gradientDrawable3);
            } else {
                view2.setBackground(gradientDrawable3);
            }
            view2.invalidate();
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    public static void setShadow(TextView textView, float f, float f2, float f3, int i) {
        TextView textView2 = textView;
        try {
            textView2.setShadowLayer(f3, f, f2, i);
            textView2.invalidate();
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    public static Bitmap textToBitmap(Context context, String str, String str2, int i, float f) {
        Paint paint;
        Canvas canvas;
        Context context2 = context;
        String str3 = str;
        String str4 = str2;
        int i2 = i;
        float f2 = f;
        try {
            new Paint(1);
            Paint paint2 = paint;
            Paint paint3 = paint2;
            paint2.setTextSize(f2);
            paint3.setColor(i2);
            paint3.setTextAlign(Paint.Align.CENTER);
            TextViewUtil.setContext(context2);
            if (str3.equalsIgnoreCase("material")) {
                TextViewUtil.setFontTypefaceCanvas(paint3, 7, false, false);
            } else if (str3.equalsIgnoreCase("font_awesome")) {
                TextViewUtil.setFontTypefaceCanvas(paint3, 6, false, false);
            }
            int i3 = (int) f2;
            Bitmap createBitmap = Bitmap.createBitmap(i3, i3, Bitmap.Config.ARGB_8888);
            new Canvas(createBitmap);
            Canvas canvas2 = canvas;
            Canvas canvas3 = canvas2;
            canvas3.drawText(HtmlEntities.decodeHtmlText(str4), (float) (canvas2.getWidth() / 2), (float) ((int) (((float) (canvas3.getHeight() / 2)) - ((paint3.descent() + paint3.ascent()) / 2.0f))), paint3);
            return createBitmap;
        } catch (Exception e) {
            return null;
        }
    }
}
