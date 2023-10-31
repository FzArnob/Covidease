package android.support.p003v7.widget;

import android.support.annotation.RestrictTo;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.ViewConfigurationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.widget.TooltipCompatHandler */
class TooltipCompatHandler implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {
    private static final long HOVER_HIDE_TIMEOUT_MS = 15000;
    private static final long HOVER_HIDE_TIMEOUT_SHORT_MS = 3000;
    private static final long LONG_CLICK_HIDE_TIMEOUT_MS = 2500;
    private static final String TAG = "TooltipCompatHandler";
    private static TooltipCompatHandler sActiveHandler;
    private static TooltipCompatHandler sPendingHandler;
    private final View mAnchor;
    private int mAnchorX;
    private int mAnchorY;
    private boolean mFromTouch;
    private final Runnable mHideRunnable;
    private final int mHoverSlop = ViewConfigurationCompat.getScaledHoverSlop(ViewConfiguration.get(this.mAnchor.getContext()));
    private TooltipPopup mPopup;
    private final Runnable mShowRunnable;
    private final CharSequence mTooltipText;

    public static void setTooltipText(View view, CharSequence charSequence) {
        Object obj;
        View view2 = view;
        CharSequence tooltipText = charSequence;
        if (sPendingHandler != null && sPendingHandler.mAnchor == view2) {
            setPendingHandler((TooltipCompatHandler) null);
        }
        if (TextUtils.isEmpty(tooltipText)) {
            if (sActiveHandler != null && sActiveHandler.mAnchor == view2) {
                sActiveHandler.hide();
            }
            view2.setOnLongClickListener((View.OnLongClickListener) null);
            view2.setLongClickable(false);
            view2.setOnHoverListener((View.OnHoverListener) null);
            return;
        }
        Object obj2 = obj;
        new TooltipCompatHandler(view2, tooltipText);
    }

    private TooltipCompatHandler(View anchor, CharSequence tooltipText) {
        Runnable runnable;
        Runnable runnable2;
        new Runnable(this) {
            final /* synthetic */ TooltipCompatHandler this$0;

            {
                this.this$0 = this$0;
            }

            public void run() {
                this.this$0.show(false);
            }
        };
        this.mShowRunnable = runnable;
        new Runnable(this) {
            final /* synthetic */ TooltipCompatHandler this$0;

            {
                this.this$0 = this$0;
            }

            public void run() {
                this.this$0.hide();
            }
        };
        this.mHideRunnable = runnable2;
        this.mAnchor = anchor;
        this.mTooltipText = tooltipText;
        clearAnchorPos();
        this.mAnchor.setOnLongClickListener(this);
        this.mAnchor.setOnHoverListener(this);
    }

    public boolean onLongClick(View view) {
        View v = view;
        this.mAnchorX = v.getWidth() / 2;
        this.mAnchorY = v.getHeight() / 2;
        show(true);
        return true;
    }

    public boolean onHover(View view, MotionEvent motionEvent) {
        View view2 = view;
        MotionEvent event = motionEvent;
        if (this.mPopup != null && this.mFromTouch) {
            return false;
        }
        AccessibilityManager manager = (AccessibilityManager) this.mAnchor.getContext().getSystemService("accessibility");
        if (manager.isEnabled() && manager.isTouchExplorationEnabled()) {
            return false;
        }
        switch (event.getAction()) {
            case 7:
                if (this.mAnchor.isEnabled() && this.mPopup == null && updateAnchorPos(event)) {
                    setPendingHandler(this);
                    break;
                }
            case 10:
                clearAnchorPos();
                hide();
                break;
        }
        return false;
    }

    public void onViewAttachedToWindow(View v) {
    }

    public void onViewDetachedFromWindow(View view) {
        View view2 = view;
        hide();
    }

    /* access modifiers changed from: package-private */
    public void show(boolean z) {
        TooltipPopup tooltipPopup;
        long timeout;
        boolean fromTouch = z;
        if (ViewCompat.isAttachedToWindow(this.mAnchor)) {
            setPendingHandler((TooltipCompatHandler) null);
            if (sActiveHandler != null) {
                sActiveHandler.hide();
            }
            sActiveHandler = this;
            this.mFromTouch = fromTouch;
            new TooltipPopup(this.mAnchor.getContext());
            this.mPopup = tooltipPopup;
            this.mPopup.show(this.mAnchor, this.mAnchorX, this.mAnchorY, this.mFromTouch, this.mTooltipText);
            this.mAnchor.addOnAttachStateChangeListener(this);
            if (this.mFromTouch) {
                timeout = 2500;
            } else if ((ViewCompat.getWindowSystemUiVisibility(this.mAnchor) & 1) == 1) {
                timeout = HOVER_HIDE_TIMEOUT_SHORT_MS - ((long) ViewConfiguration.getLongPressTimeout());
            } else {
                timeout = HOVER_HIDE_TIMEOUT_MS - ((long) ViewConfiguration.getLongPressTimeout());
            }
            boolean removeCallbacks = this.mAnchor.removeCallbacks(this.mHideRunnable);
            boolean postDelayed = this.mAnchor.postDelayed(this.mHideRunnable, timeout);
        }
    }

    /* access modifiers changed from: package-private */
    public void hide() {
        if (sActiveHandler == this) {
            sActiveHandler = null;
            if (this.mPopup != null) {
                this.mPopup.hide();
                this.mPopup = null;
                clearAnchorPos();
                this.mAnchor.removeOnAttachStateChangeListener(this);
            } else {
                int e = Log.e(TAG, "sActiveHandler.mPopup == null");
            }
        }
        if (sPendingHandler == this) {
            setPendingHandler((TooltipCompatHandler) null);
        }
        boolean removeCallbacks = this.mAnchor.removeCallbacks(this.mHideRunnable);
    }

    private static void setPendingHandler(TooltipCompatHandler tooltipCompatHandler) {
        TooltipCompatHandler handler = tooltipCompatHandler;
        if (sPendingHandler != null) {
            sPendingHandler.cancelPendingShow();
        }
        sPendingHandler = handler;
        if (sPendingHandler != null) {
            sPendingHandler.scheduleShow();
        }
    }

    private void scheduleShow() {
        boolean postDelayed = this.mAnchor.postDelayed(this.mShowRunnable, (long) ViewConfiguration.getLongPressTimeout());
    }

    private void cancelPendingShow() {
        boolean removeCallbacks = this.mAnchor.removeCallbacks(this.mShowRunnable);
    }

    private boolean updateAnchorPos(MotionEvent motionEvent) {
        MotionEvent event = motionEvent;
        int newAnchorX = (int) event.getX();
        int newAnchorY = (int) event.getY();
        if (Math.abs(newAnchorX - this.mAnchorX) <= this.mHoverSlop && Math.abs(newAnchorY - this.mAnchorY) <= this.mHoverSlop) {
            return false;
        }
        this.mAnchorX = newAnchorX;
        this.mAnchorY = newAnchorY;
        return true;
    }

    private void clearAnchorPos() {
        this.mAnchorX = Integer.MAX_VALUE;
        this.mAnchorY = Integer.MAX_VALUE;
    }
}
