package android.support.p003v7.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.p003v7.appcompat.C0425R;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;

/* renamed from: android.support.v7.widget.AppCompatRatingBar */
public class AppCompatRatingBar extends RatingBar {
    private final AppCompatProgressBarHelper mAppCompatProgressBarHelper;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AppCompatRatingBar(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AppCompatRatingBar(Context context, AttributeSet attrs) {
        this(context, attrs, C0425R.attr.ratingBarStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AppCompatRatingBar(android.content.Context r10, android.util.AttributeSet r11, int r12) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r12
            r4 = r0
            r5 = r1
            r6 = r2
            r7 = r3
            r4.<init>(r5, r6, r7)
            r4 = r0
            android.support.v7.widget.AppCompatProgressBarHelper r5 = new android.support.v7.widget.AppCompatProgressBarHelper
            r8 = r5
            r5 = r8
            r6 = r8
            r7 = r0
            r6.<init>(r7)
            r4.mAppCompatProgressBarHelper = r5
            r4 = r0
            android.support.v7.widget.AppCompatProgressBarHelper r4 = r4.mAppCompatProgressBarHelper
            r5 = r2
            r6 = r3
            r4.loadFromAttributes(r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.AppCompatRatingBar.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    /* access modifiers changed from: protected */
    public synchronized void onMeasure(int i, int i2) {
        int widthMeasureSpec = i;
        int heightMeasureSpec = i2;
        synchronized (this) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            Bitmap sampleTile = this.mAppCompatProgressBarHelper.getSampleTime();
            if (sampleTile != null) {
                setMeasuredDimension(View.resolveSizeAndState(sampleTile.getWidth() * getNumStars(), widthMeasureSpec, 0), getMeasuredHeight());
            }
        }
    }
}
