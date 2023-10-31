package android.support.p000v4.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/* renamed from: android.support.v4.widget.ContentLoadingProgressBar */
public class ContentLoadingProgressBar extends ProgressBar {
    private static final int MIN_DELAY = 500;
    private static final int MIN_SHOW_TIME = 500;
    private final Runnable mDelayedHide;
    private final Runnable mDelayedShow;
    boolean mDismissed;
    boolean mPostedHide;
    boolean mPostedShow;
    long mStartTime;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ContentLoadingProgressBar(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ContentLoadingProgressBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        Runnable runnable;
        Runnable runnable2;
        this.mStartTime = -1;
        this.mPostedHide = false;
        this.mPostedShow = false;
        this.mDismissed = false;
        new Runnable(this) {
            final /* synthetic */ ContentLoadingProgressBar this$0;

            {
                this.this$0 = this$0;
            }

            public void run() {
                this.this$0.mPostedHide = false;
                this.this$0.mStartTime = -1;
                this.this$0.setVisibility(8);
            }
        };
        this.mDelayedHide = runnable;
        new Runnable(this) {
            final /* synthetic */ ContentLoadingProgressBar this$0;

            {
                this.this$0 = this$0;
            }

            public void run() {
                this.this$0.mPostedShow = false;
                if (!this.this$0.mDismissed) {
                    this.this$0.mStartTime = System.currentTimeMillis();
                    this.this$0.setVisibility(0);
                }
            }
        };
        this.mDelayedShow = runnable2;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        removeCallbacks();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks();
    }

    private void removeCallbacks() {
        boolean removeCallbacks = removeCallbacks(this.mDelayedHide);
        boolean removeCallbacks2 = removeCallbacks(this.mDelayedShow);
    }

    public synchronized void hide() {
        synchronized (this) {
            this.mDismissed = true;
            boolean removeCallbacks = removeCallbacks(this.mDelayedShow);
            this.mPostedShow = false;
            long diff = System.currentTimeMillis() - this.mStartTime;
            if (diff >= 500 || this.mStartTime == -1) {
                setVisibility(8);
            } else if (!this.mPostedHide) {
                boolean postDelayed = postDelayed(this.mDelayedHide, 500 - diff);
                this.mPostedHide = true;
            }
        }
    }

    public synchronized void show() {
        synchronized (this) {
            this.mStartTime = -1;
            this.mDismissed = false;
            boolean removeCallbacks = removeCallbacks(this.mDelayedHide);
            this.mPostedHide = false;
            if (!this.mPostedShow) {
                boolean postDelayed = postDelayed(this.mDelayedShow, 500);
                this.mPostedShow = true;
            }
        }
    }
}
