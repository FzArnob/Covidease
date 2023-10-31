package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.widget.Button;
import com.google.android.gms.base.C0551R;
import com.google.android.gms.common.util.DeviceProperties;

public final class SignInButtonImpl extends Button {
    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SignInButtonImpl(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SignInButtonImpl(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 16842824);
    }

    public final void configure(Resources resources, SignInButtonConfig signInButtonConfig) {
        SignInButtonConfig signInButtonConfig2 = signInButtonConfig;
        configure(resources, signInButtonConfig2.getButtonSize(), signInButtonConfig2.getColorScheme());
    }

    public final void configure(Resources resources, int i, int i2) {
        int i3;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Resources resources2 = resources;
        int i4 = i;
        int i5 = i2;
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(14.0f);
        float f = resources2.getDisplayMetrics().density;
        setMinHeight((int) ((f * 48.0f) + 0.5f));
        setMinWidth((int) ((f * 48.0f) + 0.5f));
        Resources resources3 = resources2;
        int i6 = i4;
        int zaa = zaa(i5, C0551R.C0552drawable.common_google_signin_btn_icon_dark, C0551R.C0552drawable.common_google_signin_btn_icon_light, C0551R.C0552drawable.common_google_signin_btn_icon_light);
        int zaa2 = zaa(i5, C0551R.C0552drawable.common_google_signin_btn_text_dark, C0551R.C0552drawable.common_google_signin_btn_text_light, C0551R.C0552drawable.common_google_signin_btn_text_light);
        int i7 = zaa;
        switch (i6) {
            case 0:
            case 1:
                i3 = zaa2;
                break;
            case 2:
                i3 = i7;
                break;
            default:
                Throwable th3 = th2;
                new StringBuilder(32);
                new IllegalStateException(sb2.append("Unknown button size: ").append(i4).toString());
                throw th3;
        }
        Drawable wrap = DrawableCompat.wrap(resources3.getDrawable(i3));
        Drawable drawable = wrap;
        DrawableCompat.setTintList(wrap, resources3.getColorStateList(C0551R.color.common_google_signin_btn_tint));
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_ATOP);
        setBackgroundDrawable(drawable);
        Resources resources4 = resources2;
        setTextColor((ColorStateList) Preconditions.checkNotNull(resources4.getColorStateList(zaa(i5, C0551R.color.common_google_signin_btn_text_dark, C0551R.color.common_google_signin_btn_text_light, C0551R.color.common_google_signin_btn_text_light))));
        switch (i4) {
            case 0:
                setText(resources4.getString(C0551R.string.common_signin_button_text));
                break;
            case 1:
                setText(resources4.getString(C0551R.string.common_signin_button_text_long));
                break;
            case 2:
                setText((CharSequence) null);
                break;
            default:
                Throwable th4 = th;
                new StringBuilder(32);
                new IllegalStateException(sb.append("Unknown button size: ").append(i4).toString());
                throw th4;
        }
        setTransformationMethod((TransformationMethod) null);
        if (DeviceProperties.isWearable(getContext())) {
            setGravity(19);
        }
    }

    private static int zaa(int i, int i2, int i3, int i4) {
        Throwable th;
        StringBuilder sb;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        switch (i5) {
            case 0:
                return i6;
            case 1:
                return i7;
            case 2:
                return i8;
            default:
                Throwable th2 = th;
                new StringBuilder(33);
                new IllegalStateException(sb.append("Unknown color scheme: ").append(i5).toString());
                throw th2;
        }
    }
}
