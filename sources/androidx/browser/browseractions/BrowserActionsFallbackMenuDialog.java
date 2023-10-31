package androidx.browser.browseractions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.p000v4.view.animation.LinearOutSlowInInterpolator;
import android.view.MotionEvent;
import android.view.View;

class BrowserActionsFallbackMenuDialog extends Dialog {
    private static final long ENTER_ANIMATION_DURATION_MS = 250;
    private static final long EXIT_ANIMATION_DURATION_MS = 150;
    private final View mContentView;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BrowserActionsFallbackMenuDialog(Context context, View contentView) {
        super(context);
        this.mContentView = contentView;
    }

    public void show() {
        Drawable drawable;
        new ColorDrawable(0);
        getWindow().setBackgroundDrawable(drawable);
        startAnimation(true);
        super.show();
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != 0) {
            return false;
        }
        dismiss();
        return true;
    }

    public void dismiss() {
        startAnimation(false);
    }

    private void startAnimation(boolean z) {
        TimeInterpolator timeInterpolator;
        Animator.AnimatorListener animatorListener;
        boolean isEnterAnimation = z;
        float from = isEnterAnimation ? 0.0f : 1.0f;
        float to = isEnterAnimation ? 1.0f : 0.0f;
        long duration = isEnterAnimation ? ENTER_ANIMATION_DURATION_MS : 150;
        this.mContentView.setScaleX(from);
        this.mContentView.setScaleY(from);
        new LinearOutSlowInInterpolator();
        final boolean z2 = isEnterAnimation;
        new AnimatorListenerAdapter(this) {
            final /* synthetic */ BrowserActionsFallbackMenuDialog this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationEnd(Animator animator) {
                Animator animator2 = animator;
                if (!z2) {
                    BrowserActionsFallbackMenuDialog.super.dismiss();
                }
            }
        };
        this.mContentView.animate().scaleX(to).scaleY(to).setDuration(duration).setInterpolator(timeInterpolator).setListener(animatorListener).start();
    }
}
