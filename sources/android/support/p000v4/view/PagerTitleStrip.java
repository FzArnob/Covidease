package android.support.p000v4.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.p000v4.view.ViewPager;
import android.text.method.SingleLineTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Locale;

@ViewPager.DecorView
/* renamed from: android.support.v4.view.PagerTitleStrip */
public class PagerTitleStrip extends ViewGroup {
    private static final int[] ATTRS = {16842804, 16842901, 16842904, 16842927};
    private static final float SIDE_ALPHA = 0.6f;
    private static final int[] TEXT_ATTRS = {16843660};
    private static final int TEXT_SPACING = 16;
    TextView mCurrText;
    private int mGravity;
    private int mLastKnownCurrentPage;
    float mLastKnownPositionOffset;
    TextView mNextText;
    private int mNonPrimaryAlpha;
    private final PageListener mPageListener;
    ViewPager mPager;
    TextView mPrevText;
    private int mScaledTextSpacing;
    int mTextColor;
    private boolean mUpdatingPositions;
    private boolean mUpdatingText;
    private WeakReference<PagerAdapter> mWatchingAdapter;

    /* renamed from: android.support.v4.view.PagerTitleStrip$SingleLineAllCapsTransform */
    private static class SingleLineAllCapsTransform extends SingleLineTransformationMethod {
        private Locale mLocale;

        SingleLineAllCapsTransform(Context context) {
            this.mLocale = context.getResources().getConfiguration().locale;
        }

        public CharSequence getTransformation(CharSequence source, View view) {
            CharSequence source2 = super.getTransformation(source, view);
            return source2 != null ? source2.toString().toUpperCase(this.mLocale) : null;
        }
    }

    private static void setSingleLineAllCaps(TextView textView) {
        TransformationMethod transformationMethod;
        TextView text = textView;
        new SingleLineAllCapsTransform(text.getContext());
        text.setTransformationMethod(transformationMethod);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PagerTitleStrip(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public PagerTitleStrip(@android.support.annotation.NonNull android.content.Context r16, @android.support.annotation.Nullable android.util.AttributeSet r17) {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r2 = r17
            r8 = r0
            r9 = r1
            r10 = r2
            r8.<init>(r9, r10)
            r8 = r0
            r9 = -1
            r8.mLastKnownCurrentPage = r9
            r8 = r0
            r9 = -1082130432(0xffffffffbf800000, float:-1.0)
            r8.mLastKnownPositionOffset = r9
            r8 = r0
            android.support.v4.view.PagerTitleStrip$PageListener r9 = new android.support.v4.view.PagerTitleStrip$PageListener
            r13 = r9
            r9 = r13
            r10 = r13
            r11 = r0
            r10.<init>(r11)
            r8.mPageListener = r9
            r8 = r0
            r9 = r0
            android.widget.TextView r10 = new android.widget.TextView
            r13 = r10
            r10 = r13
            r11 = r13
            r12 = r1
            r11.<init>(r12)
            r13 = r9
            r14 = r10
            r9 = r14
            r10 = r13
            r11 = r14
            r10.mPrevText = r11
            r8.addView(r9)
            r8 = r0
            r9 = r0
            android.widget.TextView r10 = new android.widget.TextView
            r13 = r10
            r10 = r13
            r11 = r13
            r12 = r1
            r11.<init>(r12)
            r13 = r9
            r14 = r10
            r9 = r14
            r10 = r13
            r11 = r14
            r10.mCurrText = r11
            r8.addView(r9)
            r8 = r0
            r9 = r0
            android.widget.TextView r10 = new android.widget.TextView
            r13 = r10
            r10 = r13
            r11 = r13
            r12 = r1
            r11.<init>(r12)
            r13 = r9
            r14 = r10
            r9 = r14
            r10 = r13
            r11 = r14
            r10.mNextText = r11
            r8.addView(r9)
            r8 = r1
            r9 = r2
            int[] r10 = ATTRS
            android.content.res.TypedArray r8 = r8.obtainStyledAttributes(r9, r10)
            r3 = r8
            r8 = r3
            r9 = 0
            r10 = 0
            int r8 = r8.getResourceId(r9, r10)
            r4 = r8
            r8 = r4
            if (r8 == 0) goto L_0x0088
            r8 = r0
            android.widget.TextView r8 = r8.mPrevText
            r9 = r4
            android.support.p000v4.widget.TextViewCompat.setTextAppearance(r8, r9)
            r8 = r0
            android.widget.TextView r8 = r8.mCurrText
            r9 = r4
            android.support.p000v4.widget.TextViewCompat.setTextAppearance(r8, r9)
            r8 = r0
            android.widget.TextView r8 = r8.mNextText
            r9 = r4
            android.support.p000v4.widget.TextViewCompat.setTextAppearance(r8, r9)
        L_0x0088:
            r8 = r3
            r9 = 1
            r10 = 0
            int r8 = r8.getDimensionPixelSize(r9, r10)
            r5 = r8
            r8 = r5
            if (r8 == 0) goto L_0x009a
            r8 = r0
            r9 = 0
            r10 = r5
            float r10 = (float) r10
            r8.setTextSize(r9, r10)
        L_0x009a:
            r8 = r3
            r9 = 2
            boolean r8 = r8.hasValue(r9)
            if (r8 == 0) goto L_0x00bf
            r8 = r3
            r9 = 2
            r10 = 0
            int r8 = r8.getColor(r9, r10)
            r6 = r8
            r8 = r0
            android.widget.TextView r8 = r8.mPrevText
            r9 = r6
            r8.setTextColor(r9)
            r8 = r0
            android.widget.TextView r8 = r8.mCurrText
            r9 = r6
            r8.setTextColor(r9)
            r8 = r0
            android.widget.TextView r8 = r8.mNextText
            r9 = r6
            r8.setTextColor(r9)
        L_0x00bf:
            r8 = r0
            r9 = r3
            r10 = 3
            r11 = 80
            int r9 = r9.getInteger(r10, r11)
            r8.mGravity = r9
            r8 = r3
            r8.recycle()
            r8 = r0
            r9 = r0
            android.widget.TextView r9 = r9.mCurrText
            android.content.res.ColorStateList r9 = r9.getTextColors()
            int r9 = r9.getDefaultColor()
            r8.mTextColor = r9
            r8 = r0
            r9 = 1058642330(0x3f19999a, float:0.6)
            r8.setNonPrimaryAlpha(r9)
            r8 = r0
            android.widget.TextView r8 = r8.mPrevText
            android.text.TextUtils$TruncateAt r9 = android.text.TextUtils.TruncateAt.END
            r8.setEllipsize(r9)
            r8 = r0
            android.widget.TextView r8 = r8.mCurrText
            android.text.TextUtils$TruncateAt r9 = android.text.TextUtils.TruncateAt.END
            r8.setEllipsize(r9)
            r8 = r0
            android.widget.TextView r8 = r8.mNextText
            android.text.TextUtils$TruncateAt r9 = android.text.TextUtils.TruncateAt.END
            r8.setEllipsize(r9)
            r8 = 0
            r6 = r8
            r8 = r4
            if (r8 == 0) goto L_0x0115
            r8 = r1
            r9 = r4
            int[] r10 = TEXT_ATTRS
            android.content.res.TypedArray r8 = r8.obtainStyledAttributes(r9, r10)
            r7 = r8
            r8 = r7
            r9 = 0
            r10 = 0
            boolean r8 = r8.getBoolean(r9, r10)
            r6 = r8
            r8 = r7
            r8.recycle()
        L_0x0115:
            r8 = r6
            if (r8 == 0) goto L_0x013f
            r8 = r0
            android.widget.TextView r8 = r8.mPrevText
            setSingleLineAllCaps(r8)
            r8 = r0
            android.widget.TextView r8 = r8.mCurrText
            setSingleLineAllCaps(r8)
            r8 = r0
            android.widget.TextView r8 = r8.mNextText
            setSingleLineAllCaps(r8)
        L_0x012a:
            r8 = r1
            android.content.res.Resources r8 = r8.getResources()
            android.util.DisplayMetrics r8 = r8.getDisplayMetrics()
            float r8 = r8.density
            r7 = r8
            r8 = r0
            r9 = 1098907648(0x41800000, float:16.0)
            r10 = r7
            float r9 = r9 * r10
            int r9 = (int) r9
            r8.mScaledTextSpacing = r9
            return
        L_0x013f:
            r8 = r0
            android.widget.TextView r8 = r8.mPrevText
            r8.setSingleLine()
            r8 = r0
            android.widget.TextView r8 = r8.mCurrText
            r8.setSingleLine()
            r8 = r0
            android.widget.TextView r8 = r8.mNextText
            r8.setSingleLine()
            goto L_0x012a
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.view.PagerTitleStrip.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public void setTextSpacing(int spacingPixels) {
        this.mScaledTextSpacing = spacingPixels;
        requestLayout();
    }

    public int getTextSpacing() {
        return this.mScaledTextSpacing;
    }

    public void setNonPrimaryAlpha(@FloatRange(from = 0.0d, mo103to = 1.0d) float alpha) {
        this.mNonPrimaryAlpha = ((int) (alpha * 255.0f)) & 255;
        int transparentColor = (this.mNonPrimaryAlpha << 24) | (this.mTextColor & 16777215);
        this.mPrevText.setTextColor(transparentColor);
        this.mNextText.setTextColor(transparentColor);
    }

    public void setTextColor(@ColorInt int i) {
        int color = i;
        this.mTextColor = color;
        this.mCurrText.setTextColor(color);
        int transparentColor = (this.mNonPrimaryAlpha << 24) | (this.mTextColor & 16777215);
        this.mPrevText.setTextColor(transparentColor);
        this.mNextText.setTextColor(transparentColor);
    }

    public void setTextSize(int i, float f) {
        int unit = i;
        float size = f;
        this.mPrevText.setTextSize(unit, size);
        this.mCurrText.setTextSize(unit, size);
        this.mNextText.setTextSize(unit, size);
    }

    public void setGravity(int gravity) {
        this.mGravity = gravity;
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        Throwable th;
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (!(parent instanceof ViewPager)) {
            Throwable th2 = th;
            new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
            throw th2;
        }
        ViewPager pager = (ViewPager) parent;
        PagerAdapter adapter = pager.getAdapter();
        ViewPager.OnPageChangeListener internalPageChangeListener = pager.setInternalPageChangeListener(this.mPageListener);
        pager.addOnAdapterChangeListener(this.mPageListener);
        this.mPager = pager;
        updateAdapter(this.mWatchingAdapter != null ? (PagerAdapter) this.mWatchingAdapter.get() : null, adapter);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mPager != null) {
            updateAdapter(this.mPager.getAdapter(), (PagerAdapter) null);
            ViewPager.OnPageChangeListener internalPageChangeListener = this.mPager.setInternalPageChangeListener((ViewPager.OnPageChangeListener) null);
            this.mPager.removeOnAdapterChangeListener(this.mPageListener);
            this.mPager = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void updateText(int i, PagerAdapter pagerAdapter) {
        int currentItem = i;
        PagerAdapter adapter = pagerAdapter;
        int itemCount = adapter != null ? adapter.getCount() : 0;
        this.mUpdatingText = true;
        CharSequence text = null;
        if (currentItem >= 1 && adapter != null) {
            text = adapter.getPageTitle(currentItem - 1);
        }
        this.mPrevText.setText(text);
        this.mCurrText.setText((adapter == null || currentItem >= itemCount) ? null : adapter.getPageTitle(currentItem));
        CharSequence text2 = null;
        if (currentItem + 1 < itemCount && adapter != null) {
            text2 = adapter.getPageTitle(currentItem + 1);
        }
        this.mNextText.setText(text2);
        int childWidthSpec = View.MeasureSpec.makeMeasureSpec(Math.max(0, (int) (((float) ((getWidth() - getPaddingLeft()) - getPaddingRight())) * 0.8f)), Integer.MIN_VALUE);
        int childHeightSpec = View.MeasureSpec.makeMeasureSpec(Math.max(0, (getHeight() - getPaddingTop()) - getPaddingBottom()), Integer.MIN_VALUE);
        this.mPrevText.measure(childWidthSpec, childHeightSpec);
        this.mCurrText.measure(childWidthSpec, childHeightSpec);
        this.mNextText.measure(childWidthSpec, childHeightSpec);
        this.mLastKnownCurrentPage = currentItem;
        if (!this.mUpdatingPositions) {
            updateTextPositions(currentItem, this.mLastKnownPositionOffset, false);
        }
        this.mUpdatingText = false;
    }

    public void requestLayout() {
        if (!this.mUpdatingText) {
            super.requestLayout();
        }
    }

    /* access modifiers changed from: package-private */
    public void updateAdapter(PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2) {
        WeakReference<PagerAdapter> weakReference;
        PagerAdapter oldAdapter = pagerAdapter;
        PagerAdapter newAdapter = pagerAdapter2;
        if (oldAdapter != null) {
            oldAdapter.unregisterDataSetObserver(this.mPageListener);
            this.mWatchingAdapter = null;
        }
        if (newAdapter != null) {
            newAdapter.registerDataSetObserver(this.mPageListener);
            new WeakReference<>(newAdapter);
            this.mWatchingAdapter = weakReference;
        }
        if (this.mPager != null) {
            this.mLastKnownCurrentPage = -1;
            this.mLastKnownPositionOffset = -1.0f;
            updateText(this.mPager.getCurrentItem(), newAdapter);
            requestLayout();
        }
    }

    /* access modifiers changed from: package-private */
    public void updateTextPositions(int i, float f, boolean z) {
        int prevTop;
        int currTop;
        int nextTop;
        int position = i;
        float positionOffset = f;
        boolean force = z;
        if (position != this.mLastKnownCurrentPage) {
            updateText(position, this.mPager.getAdapter());
        } else if (!force) {
            if (positionOffset == this.mLastKnownPositionOffset) {
                return;
            }
        }
        this.mUpdatingPositions = true;
        int prevWidth = this.mPrevText.getMeasuredWidth();
        int currWidth = this.mCurrText.getMeasuredWidth();
        int nextWidth = this.mNextText.getMeasuredWidth();
        int halfCurrWidth = currWidth / 2;
        int stripWidth = getWidth();
        int stripHeight = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int textPaddedRight = paddingRight + halfCurrWidth;
        int contentWidth = (stripWidth - (paddingLeft + halfCurrWidth)) - textPaddedRight;
        float currOffset = positionOffset + 0.5f;
        if (currOffset > 1.0f) {
            currOffset -= 1.0f;
        }
        int currLeft = ((stripWidth - textPaddedRight) - ((int) (((float) contentWidth) * currOffset))) - (currWidth / 2);
        int currRight = currLeft + currWidth;
        int prevBaseline = this.mPrevText.getBaseline();
        int currBaseline = this.mCurrText.getBaseline();
        int nextBaseline = this.mNextText.getBaseline();
        int maxBaseline = Math.max(Math.max(prevBaseline, currBaseline), nextBaseline);
        int prevTopOffset = maxBaseline - prevBaseline;
        int currTopOffset = maxBaseline - currBaseline;
        int nextTopOffset = maxBaseline - nextBaseline;
        int alignedPrevHeight = prevTopOffset + this.mPrevText.getMeasuredHeight();
        int alignedCurrHeight = currTopOffset + this.mCurrText.getMeasuredHeight();
        int maxTextHeight = Math.max(Math.max(alignedPrevHeight, alignedCurrHeight), nextTopOffset + this.mNextText.getMeasuredHeight());
        switch (this.mGravity & 112) {
            case 16:
                int centeredTop = (((stripHeight - paddingTop) - paddingBottom) - maxTextHeight) / 2;
                prevTop = centeredTop + prevTopOffset;
                currTop = centeredTop + currTopOffset;
                nextTop = centeredTop + nextTopOffset;
                break;
            case 80:
                int bottomGravTop = (stripHeight - paddingBottom) - maxTextHeight;
                prevTop = bottomGravTop + prevTopOffset;
                currTop = bottomGravTop + currTopOffset;
                nextTop = bottomGravTop + nextTopOffset;
                break;
            default:
                prevTop = paddingTop + prevTopOffset;
                currTop = paddingTop + currTopOffset;
                nextTop = paddingTop + nextTopOffset;
                break;
        }
        this.mCurrText.layout(currLeft, currTop, currRight, currTop + this.mCurrText.getMeasuredHeight());
        int prevLeft = Math.min(paddingLeft, (currLeft - this.mScaledTextSpacing) - prevWidth);
        this.mPrevText.layout(prevLeft, prevTop, prevLeft + prevWidth, prevTop + this.mPrevText.getMeasuredHeight());
        int nextLeft = Math.max((stripWidth - paddingRight) - nextWidth, currRight + this.mScaledTextSpacing);
        this.mNextText.layout(nextLeft, nextTop, nextLeft + nextWidth, nextTop + this.mNextText.getMeasuredHeight());
        this.mLastKnownPositionOffset = positionOffset;
        this.mUpdatingPositions = false;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int height;
        Throwable th;
        int widthMeasureSpec = i;
        int heightMeasureSpec = i2;
        if (View.MeasureSpec.getMode(widthMeasureSpec) != 1073741824) {
            Throwable th2 = th;
            new IllegalStateException("Must measure with an exact width");
            throw th2;
        }
        int heightPadding = getPaddingTop() + getPaddingBottom();
        int childHeightSpec = getChildMeasureSpec(heightMeasureSpec, heightPadding, -2);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int childWidthSpec = getChildMeasureSpec(widthMeasureSpec, (int) (((float) widthSize) * 0.2f), -2);
        this.mPrevText.measure(childWidthSpec, childHeightSpec);
        this.mCurrText.measure(childWidthSpec, childHeightSpec);
        this.mNextText.measure(childWidthSpec, childHeightSpec);
        if (View.MeasureSpec.getMode(heightMeasureSpec) == 1073741824) {
            height = View.MeasureSpec.getSize(heightMeasureSpec);
        } else {
            height = Math.max(getMinHeight(), this.mCurrText.getMeasuredHeight() + heightPadding);
        }
        setMeasuredDimension(widthSize, View.resolveSizeAndState(height, heightMeasureSpec, this.mCurrText.getMeasuredState() << 16));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2 = z;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        if (this.mPager != null) {
            updateTextPositions(this.mLastKnownCurrentPage, this.mLastKnownPositionOffset >= 0.0f ? this.mLastKnownPositionOffset : 0.0f, true);
        }
    }

    /* access modifiers changed from: package-private */
    public int getMinHeight() {
        int minHeight = 0;
        Drawable bg = getBackground();
        if (bg != null) {
            minHeight = bg.getIntrinsicHeight();
        }
        return minHeight;
    }

    /* renamed from: android.support.v4.view.PagerTitleStrip$PageListener */
    private class PageListener extends DataSetObserver implements ViewPager.OnPageChangeListener, ViewPager.OnAdapterChangeListener {
        private int mScrollState;
        final /* synthetic */ PagerTitleStrip this$0;

        PageListener(PagerTitleStrip pagerTitleStrip) {
            this.this$0 = pagerTitleStrip;
        }

        public void onPageScrolled(int i, float f, int i2) {
            int position = i;
            float positionOffset = f;
            int i3 = i2;
            if (positionOffset > 0.5f) {
                position++;
            }
            this.this$0.updateTextPositions(position, positionOffset, false);
        }

        public void onPageSelected(int i) {
            int i2 = i;
            if (this.mScrollState == 0) {
                this.this$0.updateText(this.this$0.mPager.getCurrentItem(), this.this$0.mPager.getAdapter());
                this.this$0.updateTextPositions(this.this$0.mPager.getCurrentItem(), this.this$0.mLastKnownPositionOffset >= 0.0f ? this.this$0.mLastKnownPositionOffset : 0.0f, true);
            }
        }

        public void onPageScrollStateChanged(int state) {
            int i = state;
            this.mScrollState = i;
        }

        public void onAdapterChanged(ViewPager viewPager, PagerAdapter oldAdapter, PagerAdapter newAdapter) {
            ViewPager viewPager2 = viewPager;
            this.this$0.updateAdapter(oldAdapter, newAdapter);
        }

        public void onChanged() {
            this.this$0.updateText(this.this$0.mPager.getCurrentItem(), this.this$0.mPager.getAdapter());
            this.this$0.updateTextPositions(this.this$0.mPager.getCurrentItem(), this.this$0.mLastKnownPositionOffset >= 0.0f ? this.this$0.mLastKnownPositionOffset : 0.0f, true);
        }
    }
}
