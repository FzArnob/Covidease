package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Environment;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;
import android.text.util.Linkify;
import android.util.Log;
import android.widget.TextView;
import com.google.appinventor.components.runtime.Form;

public class TextViewUtil {
    private static Context vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;

    private TextViewUtil() {
    }

    public static void setContext(Context context) {
        vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = context;
    }

    public static void setAlignment(TextView textView, int i, boolean z) {
        int i2;
        Throwable th;
        TextView textView2 = textView;
        boolean z2 = z;
        switch (i) {
            case 0:
                i2 = 8388611;
                break;
            case 1:
                i2 = 1;
                break;
            case 2:
                i2 = 8388613;
                break;
            default:
                Throwable th2 = th;
                new IllegalArgumentException();
                throw th2;
        }
        textView2.setGravity(i2 | (z2 ? 16 : 48));
        textView2.invalidate();
    }

    public static void setBackgroundColor(TextView textView, int i) {
        TextView textView2 = textView;
        textView2.setBackgroundColor(i);
        textView2.invalidate();
    }

    public static boolean isEnabled(TextView textView) {
        return textView.isEnabled();
    }

    public static void setEnabled(TextView textView, boolean z) {
        TextView textView2 = textView;
        textView2.setEnabled(z);
        textView2.invalidate();
    }

    public static float getFontSize(TextView textView, Context context) {
        return textView.getTextSize() / context.getResources().getDisplayMetrics().scaledDensity;
    }

    public static void setFontSize(TextView textView, float f) {
        TextView textView2 = textView;
        textView2.setTextSize(f);
        textView2.requestLayout();
    }

    public static void setFontTypeface(TextView textView, int i, boolean z, boolean z2) {
        StringBuilder sb;
        TextView textView2 = textView;
        try {
            textView2.setTypeface(Typeface.create(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(z, z2)));
            textView2.requestLayout();
        } catch (Exception e) {
            new StringBuilder();
            int d = Log.d("TextViewUtil", sb.append(e.getMessage()).toString());
        }
    }

    public static Typeface getTitleBarTypeFace(int i) {
        return Typeface.create(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(false, false));
    }

    public static Typeface getTitleBarCustomTypeFace(Form form, String str) {
        return Typeface.create(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form, str), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(false, false));
    }

    public static void setFontTypefaceCanvas(Paint paint, int i, boolean z, boolean z2) {
        StringBuilder sb;
        try {
            Typeface typeface = paint.setTypeface(Typeface.create(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(z, z2)));
        } catch (Exception e) {
            new StringBuilder();
            int d = Log.d("TextViewUtil", sb.append(e.getMessage()).toString());
        }
    }

    public static void setCustomFontTypeface(Form form, TextView textView, String str, boolean z, boolean z2) {
        StringBuilder sb;
        TextView textView2 = textView;
        try {
            textView2.setTypeface(Typeface.create(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form, str), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(z, z2)));
            textView2.requestLayout();
        } catch (Exception e) {
            new StringBuilder();
            int d = Log.d("TextViewUtil", sb.append(e.getMessage()).toString());
        }
    }

    public static void setCustomFontTypefaceCanvas(Form form, Paint paint, String str, boolean z, boolean z2) {
        StringBuilder sb;
        try {
            Typeface typeface = paint.setTypeface(Typeface.create(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form, str), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(z, z2)));
        } catch (Exception e) {
            new StringBuilder();
            int d = Log.d("TextViewUtil", sb.append(e.getMessage()).toString());
        }
    }

    public static String getText(TextView textView) {
        return textView.getText().toString();
    }

    public static void setTextHTML(TextView textView, String str) {
        TextView textView2 = textView;
        textView2.setText(fromHtml(str));
        textView2.requestLayout();
    }

    public static void setText(TextView textView, String str) {
        TextView textView2 = textView;
        textView2.setText(str);
        textView2.requestLayout();
    }

    public static void setPadding(TextView textView, int i) {
        TextView textView2 = textView;
        int i2 = i;
        textView2.setPadding(i2, i2, 0, 0);
        textView2.requestLayout();
    }

    public static void setTextColor(TextView textView, int i) {
        TextView textView2 = textView;
        textView2.setTextColor(i);
        textView2.invalidate();
    }

    public static void setTextColors(TextView textView, ColorStateList colorStateList) {
        textView.setTextColor(colorStateList);
    }

    public static void setHintColor(TextView textView, int i) {
        TextView textView2 = textView;
        textView2.setHintTextColor(i);
        textView2.invalidate();
    }

    public static void setHint(TextView textView, String str) {
        TextView textView2 = textView;
        textView2.setHint(str);
        textView2.invalidate();
    }

    private static Typeface hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(int i) {
        Typeface createFromAsset;
        Throwable th;
        switch (i) {
            case 0:
                createFromAsset = Typeface.DEFAULT;
                break;
            case 1:
                createFromAsset = Typeface.SANS_SERIF;
                break;
            case 2:
                createFromAsset = Typeface.SERIF;
                break;
            case 3:
                createFromAsset = Typeface.MONOSPACE;
                break;
            case 4:
                createFromAsset = Typeface.createFromAsset(vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.getAssets(), "Roboto-Thin.ttf");
                break;
            case 5:
                createFromAsset = Typeface.createFromAsset(vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.getAssets(), "Roboto-Regular.ttf");
                break;
            case 6:
                createFromAsset = Typeface.createFromAsset(vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.getAssets(), "fontawesome-webfont.ttf");
                break;
            case 7:
                createFromAsset = Typeface.createFromAsset(vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.getAssets(), "MaterialIcons-Regular.ttf");
                break;
            case 8:
                createFromAsset = Typeface.createFromAsset(vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.getAssets(), "fa-regular-400.ttf");
                break;
            case 9:
                createFromAsset = Typeface.createFromAsset(vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.getAssets(), "fa-solid-900.ttf");
                break;
            case 10:
                createFromAsset = Typeface.createFromAsset(vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.getAssets(), "fa-brands-400.ttf");
                break;
            default:
                Throwable th2 = th;
                new IllegalArgumentException();
                throw th2;
        }
        return createFromAsset;
    }

    private static Typeface hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Form form, String str) {
        StringBuilder sb;
        Typeface typeface;
        try {
            String assetFilePath = MediaUtil.getAssetFilePath(form, str);
            String str2 = assetFilePath;
            if (assetFilePath.startsWith(Environment.getExternalStorageDirectory().getAbsolutePath())) {
                typeface = Typeface.createFromFile(str2);
            } else {
                typeface = Typeface.createFromAsset(vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.getAssets(), str2);
            }
        } catch (Exception e) {
            new StringBuilder();
            int d = Log.d("TextViewUtil", sb.append(e.getMessage()).toString());
            typeface = null;
        }
        return typeface;
    }

    private static int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(boolean z, boolean z2) {
        boolean z3 = z2;
        int i = 0;
        if (z) {
            i = 1;
        }
        if (z3) {
            i |= 2;
        }
        return i;
    }

    public static Spanned fromHtml(String str) {
        String str2 = str;
        if (Build.VERSION.SDK_INT >= 24) {
            return Html.fromHtml(str2, 0);
        }
        return Html.fromHtml(str2);
    }

    public static SpannableString linkifyMessage(String str) {
        SpannableString spannableString;
        new SpannableString(str);
        SpannableString spannableString2 = spannableString;
        boolean addLinks = Linkify.addLinks(spannableString2, 15);
        return spannableString2;
    }

    public static class CustomTypefaceSpan extends TypefaceSpan {
        private final Typeface wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public CustomTypefaceSpan(String str, Typeface typeface) {
            super(str);
            this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = typeface;
        }

        public void updateDrawState(TextPaint textPaint) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(textPaint, this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou);
        }

        public void updateMeasureState(TextPaint textPaint) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(textPaint, this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou);
        }

        private static void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Paint paint, Typeface typeface) {
            int style;
            Paint paint2 = paint;
            Typeface typeface2 = typeface;
            Typeface typeface3 = paint2.getTypeface();
            Typeface typeface4 = typeface3;
            if (typeface3 == null) {
                style = 0;
            } else {
                style = typeface4.getStyle();
            }
            int style2 = style & (typeface2.getStyle() ^ -1);
            int i = style2;
            if ((style2 & 1) != 0) {
                paint2.setFakeBoldText(true);
            }
            if ((i & 2) != 0) {
                paint2.setTextSkewX(-0.25f);
            }
            Typeface typeface5 = paint2.setTypeface(typeface2);
        }
    }

    public static void setMinWidth(TextView textView, int i) {
        TextView textView2 = textView;
        int i2 = i;
        textView2.setMinWidth(i2);
        textView2.setMinimumWidth(i2);
    }

    public static void setMinHeight(TextView textView, int i) {
        TextView textView2 = textView;
        int i2 = i;
        textView2.setMinHeight(i2);
        textView2.setMinimumHeight(i2);
    }

    public static void setMinSize(TextView textView, int i, int i2) {
        TextView textView2 = textView;
        setMinWidth(textView2, i);
        setMinHeight(textView2, i2);
    }
}
