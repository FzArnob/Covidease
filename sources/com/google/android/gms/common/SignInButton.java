package com.google.android.gms.common;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.SignInButtonCreator;
import com.google.android.gms.common.internal.SignInButtonImpl;
import com.google.android.gms.dynamic.RemoteCreator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class SignInButton extends FrameLayout implements View.OnClickListener {
    public static final int COLOR_AUTO = 2;
    public static final int COLOR_DARK = 0;
    public static final int COLOR_LIGHT = 1;
    public static final int SIZE_ICON_ONLY = 2;
    public static final int SIZE_STANDARD = 0;
    public static final int SIZE_WIDE = 1;
    private int mColor;
    private int mSize;
    private View zaas;
    private View.OnClickListener zaat;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ButtonSize {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ColorScheme {
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SignInButton(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SignInButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SignInButton(android.content.Context r15, android.util.AttributeSet r16, int r17) {
        /*
            r14 = this;
            r0 = r14
            r1 = r15
            r2 = r16
            r3 = r17
            r9 = r0
            r10 = r1
            r11 = r2
            r12 = r3
            r9.<init>(r10, r11, r12)
            r9 = r0
            r10 = 0
            r9.zaat = r10
            r9 = r0
            r10 = r1
            r11 = r2
            r6 = r11
            r5 = r10
            r4 = r9
            r9 = r5
            android.content.res.Resources$Theme r9 = r9.getTheme()
            r10 = r6
            int[] r11 = com.google.android.gms.base.C0551R.styleable.SignInButton
            r12 = 0
            r13 = 0
            android.content.res.TypedArray r9 = r9.obtainStyledAttributes(r10, r11, r12, r13)
            r7 = r9
            r9 = r4
            r10 = r7
            int r11 = com.google.android.gms.base.C0551R.styleable.SignInButton_buttonSize     // Catch:{ all -> 0x004b }
            r12 = 0
            int r10 = r10.getInt(r11, r12)     // Catch:{ all -> 0x004b }
            r9.mSize = r10     // Catch:{ all -> 0x004b }
            r9 = r4
            r10 = r7
            int r11 = com.google.android.gms.base.C0551R.styleable.SignInButton_colorScheme     // Catch:{ all -> 0x004b }
            r12 = 2
            int r10 = r10.getInt(r11, r12)     // Catch:{ all -> 0x004b }
            r9.mColor = r10     // Catch:{ all -> 0x004b }
            r9 = r7
            r9.recycle()
            r9 = r0
            r10 = r0
            int r10 = r10.mSize
            r11 = r0
            int r11 = r11.mColor
            r9.setStyle(r10, r11)
            return
        L_0x004b:
            r9 = move-exception
            r8 = r9
            r9 = r7
            r9.recycle()
            r9 = r8
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.SignInButton.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public final void setSize(int i) {
        setStyle(i, this.mColor);
    }

    public final void setColorScheme(int i) {
        setStyle(this.mSize, i);
    }

    @Deprecated
    public final void setScopes(Scope[] scopeArr) {
        Scope[] scopeArr2 = scopeArr;
        setStyle(this.mSize, this.mColor);
    }

    public final void setStyle(int i, int i2) {
        SignInButtonImpl signInButtonImpl;
        this.mSize = i;
        this.mColor = i2;
        Context context = getContext();
        if (this.zaas != null) {
            removeView(this.zaas);
        }
        try {
            this.zaas = SignInButtonCreator.createView(context, this.mSize, this.mColor);
        } catch (RemoteCreator.RemoteCreatorException e) {
            int w = Log.w("SignInButton", "Sign in button not found, using placeholder instead");
            int i3 = this.mSize;
            int i4 = this.mColor;
            int i5 = i3;
            Context context2 = context;
            new SignInButtonImpl(context2);
            SignInButtonImpl signInButtonImpl2 = signInButtonImpl;
            signInButtonImpl2.configure(context2.getResources(), i5, i4);
            this.zaas = signInButtonImpl2;
        }
        addView(this.zaas);
        this.zaas.setEnabled(isEnabled());
        this.zaas.setOnClickListener(this);
    }

    @Deprecated
    public final void setStyle(int i, int i2, Scope[] scopeArr) {
        Scope[] scopeArr2 = scopeArr;
        setStyle(i, i2);
    }

    public final void setOnClickListener(View.OnClickListener onClickListener) {
        this.zaat = onClickListener;
        if (this.zaas != null) {
            this.zaas.setOnClickListener(this);
        }
    }

    public final void setEnabled(boolean z) {
        boolean z2 = z;
        super.setEnabled(z2);
        this.zaas.setEnabled(z2);
    }

    public final void onClick(View view) {
        View view2 = view;
        if (this.zaat != null && view2 == this.zaas) {
            this.zaat.onClick(this);
        }
    }
}
