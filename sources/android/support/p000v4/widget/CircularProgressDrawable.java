package android.support.p000v4.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.p000v4.util.Preconditions;
import android.support.p000v4.view.animation.FastOutSlowInInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* renamed from: android.support.v4.widget.CircularProgressDrawable */
public class CircularProgressDrawable extends Drawable implements Animatable {
    private static final int ANIMATION_DURATION = 1332;
    private static final int ARROW_HEIGHT = 5;
    private static final int ARROW_HEIGHT_LARGE = 6;
    private static final int ARROW_WIDTH = 10;
    private static final int ARROW_WIDTH_LARGE = 12;
    private static final float CENTER_RADIUS = 7.5f;
    private static final float CENTER_RADIUS_LARGE = 11.0f;
    private static final int[] COLORS = {-16777216};
    private static final float COLOR_CHANGE_OFFSET = 0.75f;
    public static final int DEFAULT = 1;
    private static final float GROUP_FULL_ROTATION = 216.0f;
    public static final int LARGE = 0;
    private static final Interpolator LINEAR_INTERPOLATOR;
    private static final Interpolator MATERIAL_INTERPOLATOR;
    private static final float MAX_PROGRESS_ARC = 0.8f;
    private static final float MIN_PROGRESS_ARC = 0.01f;
    private static final float RING_ROTATION = 0.20999998f;
    private static final float SHRINK_OFFSET = 0.5f;
    private static final float STROKE_WIDTH = 2.5f;
    private static final float STROKE_WIDTH_LARGE = 3.0f;
    private Animator mAnimator;
    boolean mFinishing;
    private Resources mResources;
    private final Ring mRing;
    private float mRotation;
    float mRotationCount;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.widget.CircularProgressDrawable$ProgressDrawableSize */
    public @interface ProgressDrawableSize {
    }

    static {
        Interpolator interpolator;
        Interpolator interpolator2;
        new LinearInterpolator();
        LINEAR_INTERPOLATOR = interpolator;
        new FastOutSlowInInterpolator();
        MATERIAL_INTERPOLATOR = interpolator2;
    }

    public CircularProgressDrawable(@NonNull Context context) {
        Ring ring;
        this.mResources = ((Context) Preconditions.checkNotNull(context)).getResources();
        new Ring();
        this.mRing = ring;
        this.mRing.setColors(COLORS);
        setStrokeWidth(STROKE_WIDTH);
        setupAnimators();
    }

    private void setSizeParameters(float centerRadius, float strokeWidth, float arrowWidth, float arrowHeight) {
        Ring ring = this.mRing;
        float screenDensity = this.mResources.getDisplayMetrics().density;
        ring.setStrokeWidth(strokeWidth * screenDensity);
        ring.setCenterRadius(centerRadius * screenDensity);
        ring.setColorIndex(0);
        ring.setArrowDimensions(arrowWidth * screenDensity, arrowHeight * screenDensity);
    }

    public void setStyle(int size) {
        if (size == 0) {
            setSizeParameters(CENTER_RADIUS_LARGE, STROKE_WIDTH_LARGE, 12.0f, 6.0f);
        } else {
            setSizeParameters(CENTER_RADIUS, STROKE_WIDTH, 10.0f, 5.0f);
        }
        invalidateSelf();
    }

    public float getStrokeWidth() {
        return this.mRing.getStrokeWidth();
    }

    public void setStrokeWidth(float strokeWidth) {
        this.mRing.setStrokeWidth(strokeWidth);
        invalidateSelf();
    }

    public float getCenterRadius() {
        return this.mRing.getCenterRadius();
    }

    public void setCenterRadius(float centerRadius) {
        this.mRing.setCenterRadius(centerRadius);
        invalidateSelf();
    }

    public void setStrokeCap(@NonNull Paint.Cap strokeCap) {
        this.mRing.setStrokeCap(strokeCap);
        invalidateSelf();
    }

    @NonNull
    public Paint.Cap getStrokeCap() {
        return this.mRing.getStrokeCap();
    }

    public float getArrowWidth() {
        return this.mRing.getArrowWidth();
    }

    public float getArrowHeight() {
        return this.mRing.getArrowHeight();
    }

    public void setArrowDimensions(float width, float height) {
        this.mRing.setArrowDimensions(width, height);
        invalidateSelf();
    }

    public boolean getArrowEnabled() {
        return this.mRing.getShowArrow();
    }

    public void setArrowEnabled(boolean show) {
        this.mRing.setShowArrow(show);
        invalidateSelf();
    }

    public float getArrowScale() {
        return this.mRing.getArrowScale();
    }

    public void setArrowScale(float scale) {
        this.mRing.setArrowScale(scale);
        invalidateSelf();
    }

    public float getStartTrim() {
        return this.mRing.getStartTrim();
    }

    public float getEndTrim() {
        return this.mRing.getEndTrim();
    }

    public void setStartEndTrim(float start, float end) {
        this.mRing.setStartTrim(start);
        this.mRing.setEndTrim(end);
        invalidateSelf();
    }

    public float getProgressRotation() {
        return this.mRing.getRotation();
    }

    public void setProgressRotation(float rotation) {
        this.mRing.setRotation(rotation);
        invalidateSelf();
    }

    public int getBackgroundColor() {
        return this.mRing.getBackgroundColor();
    }

    public void setBackgroundColor(int color) {
        this.mRing.setBackgroundColor(color);
        invalidateSelf();
    }

    @NonNull
    public int[] getColorSchemeColors() {
        return this.mRing.getColors();
    }

    public void setColorSchemeColors(@NonNull int... colors) {
        this.mRing.setColors(colors);
        this.mRing.setColorIndex(0);
        invalidateSelf();
    }

    public void draw(Canvas canvas) {
        Canvas canvas2 = canvas;
        Rect bounds = getBounds();
        int save = canvas2.save();
        canvas2.rotate(this.mRotation, bounds.exactCenterX(), bounds.exactCenterY());
        this.mRing.draw(canvas2, bounds);
        canvas2.restore();
    }

    public void setAlpha(int alpha) {
        this.mRing.setAlpha(alpha);
        invalidateSelf();
    }

    public int getAlpha() {
        return this.mRing.getAlpha();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mRing.setColorFilter(colorFilter);
        invalidateSelf();
    }

    private void setRotation(float rotation) {
        float f = rotation;
        this.mRotation = f;
    }

    private float getRotation() {
        return this.mRotation;
    }

    public int getOpacity() {
        return -3;
    }

    public boolean isRunning() {
        return this.mAnimator.isRunning();
    }

    public void start() {
        this.mAnimator.cancel();
        this.mRing.storeOriginals();
        if (this.mRing.getEndTrim() != this.mRing.getStartTrim()) {
            this.mFinishing = true;
            Animator duration = this.mAnimator.setDuration(666);
            this.mAnimator.start();
            return;
        }
        this.mRing.setColorIndex(0);
        this.mRing.resetOriginals();
        Animator duration2 = this.mAnimator.setDuration(1332);
        this.mAnimator.start();
    }

    public void stop() {
        this.mAnimator.cancel();
        setRotation(0.0f);
        this.mRing.setShowArrow(false);
        this.mRing.setColorIndex(0);
        this.mRing.resetOriginals();
        invalidateSelf();
    }

    private int evaluateColorChange(float f, int i, int i2) {
        float fraction = f;
        int startValue = i;
        int endValue = i2;
        int startA = (startValue >> 24) & 255;
        int startR = (startValue >> 16) & 255;
        int startG = (startValue >> 8) & 255;
        int startB = startValue & 255;
        return ((startA + ((int) (fraction * ((float) (((endValue >> 24) & 255) - startA))))) << 24) | ((startR + ((int) (fraction * ((float) (((endValue >> 16) & 255) - startR))))) << 16) | ((startG + ((int) (fraction * ((float) (((endValue >> 8) & 255) - startG))))) << 8) | (startB + ((int) (fraction * ((float) ((endValue & 255) - startB)))));
    }

    /* access modifiers changed from: package-private */
    public void updateRingColor(float f, Ring ring) {
        float interpolatedTime = f;
        Ring ring2 = ring;
        if (interpolatedTime > COLOR_CHANGE_OFFSET) {
            ring2.setColor(evaluateColorChange((interpolatedTime - COLOR_CHANGE_OFFSET) / 0.25f, ring2.getStartingColor(), ring2.getNextColor()));
        } else {
            ring2.setColor(ring2.getStartingColor());
        }
    }

    private void applyFinishTranslation(float f, Ring ring) {
        float interpolatedTime = f;
        Ring ring2 = ring;
        updateRingColor(interpolatedTime, ring2);
        float targetRotation = (float) (Math.floor((double) (ring2.getStartingRotation() / MAX_PROGRESS_ARC)) + 1.0d);
        ring2.setStartTrim(ring2.getStartingStartTrim() + (((ring2.getStartingEndTrim() - MIN_PROGRESS_ARC) - ring2.getStartingStartTrim()) * interpolatedTime));
        ring2.setEndTrim(ring2.getStartingEndTrim());
        ring2.setRotation(ring2.getStartingRotation() + ((targetRotation - ring2.getStartingRotation()) * interpolatedTime));
    }

    /* access modifiers changed from: package-private */
    public void applyTransformation(float f, Ring ring, boolean z) {
        float endTrim;
        float startTrim;
        float interpolatedTime = f;
        Ring ring2 = ring;
        boolean lastFrame = z;
        if (this.mFinishing) {
            applyFinishTranslation(interpolatedTime, ring2);
        } else if (interpolatedTime != 1.0f || lastFrame) {
            float startingRotation = ring2.getStartingRotation();
            if (interpolatedTime < SHRINK_OFFSET) {
                float scaledTime = interpolatedTime / SHRINK_OFFSET;
                startTrim = ring2.getStartingStartTrim();
                endTrim = startTrim + (0.79f * MATERIAL_INTERPOLATOR.getInterpolation(scaledTime)) + MIN_PROGRESS_ARC;
            } else {
                float scaledTime2 = (interpolatedTime - SHRINK_OFFSET) / SHRINK_OFFSET;
                endTrim = ring2.getStartingStartTrim() + 0.79f;
                startTrim = endTrim - ((0.79f * (1.0f - MATERIAL_INTERPOLATOR.getInterpolation(scaledTime2))) + MIN_PROGRESS_ARC);
            }
            float scaledTime3 = startingRotation + (RING_ROTATION * interpolatedTime);
            float groupRotation = GROUP_FULL_ROTATION * (interpolatedTime + this.mRotationCount);
            ring2.setStartTrim(startTrim);
            ring2.setEndTrim(endTrim);
            ring2.setRotation(scaledTime3);
            setRotation(groupRotation);
        }
    }

    private void setupAnimators() {
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener;
        Animator.AnimatorListener animatorListener;
        Ring ring = this.mRing;
        ValueAnimator animator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        final Ring ring2 = ring;
        new ValueAnimator.AnimatorUpdateListener(this) {
            final /* synthetic */ CircularProgressDrawable this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationUpdate(ValueAnimator animation) {
                float interpolatedTime = ((Float) animation.getAnimatedValue()).floatValue();
                this.this$0.updateRingColor(interpolatedTime, ring2);
                this.this$0.applyTransformation(interpolatedTime, ring2, false);
                this.this$0.invalidateSelf();
            }
        };
        animator.addUpdateListener(animatorUpdateListener);
        animator.setRepeatCount(-1);
        animator.setRepeatMode(1);
        animator.setInterpolator(LINEAR_INTERPOLATOR);
        final Ring ring3 = ring;
        new Animator.AnimatorListener(this) {
            final /* synthetic */ CircularProgressDrawable this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationStart(Animator animator) {
                Animator animator2 = animator;
                this.this$0.mRotationCount = 0.0f;
            }

            public void onAnimationEnd(Animator animator) {
            }

            public void onAnimationCancel(Animator animation) {
            }

            public void onAnimationRepeat(Animator animator) {
                Animator animator2 = animator;
                this.this$0.applyTransformation(1.0f, ring3, true);
                ring3.storeOriginals();
                ring3.goToNextColor();
                if (this.this$0.mFinishing) {
                    this.this$0.mFinishing = false;
                    animator2.cancel();
                    Animator duration = animator2.setDuration(1332);
                    animator2.start();
                    ring3.setShowArrow(false);
                    return;
                }
                this.this$0.mRotationCount += 1.0f;
            }
        };
        animator.addListener(animatorListener);
        this.mAnimator = animator;
    }

    /* renamed from: android.support.v4.widget.CircularProgressDrawable$Ring */
    private static class Ring {
        int mAlpha = 255;
        Path mArrow;
        int mArrowHeight;
        final Paint mArrowPaint;
        float mArrowScale = 1.0f;
        int mArrowWidth;
        final Paint mCirclePaint;
        int mColorIndex;
        int[] mColors;
        int mCurrentColor;
        float mEndTrim = 0.0f;
        final Paint mPaint;
        float mRingCenterRadius;
        float mRotation = 0.0f;
        boolean mShowArrow;
        float mStartTrim = 0.0f;
        float mStartingEndTrim;
        float mStartingRotation;
        float mStartingStartTrim;
        float mStrokeWidth = 5.0f;
        final RectF mTempBounds;

        Ring() {
            RectF rectF;
            Paint paint;
            Paint paint2;
            Paint paint3;
            new RectF();
            this.mTempBounds = rectF;
            new Paint();
            this.mPaint = paint;
            new Paint();
            this.mArrowPaint = paint2;
            new Paint();
            this.mCirclePaint = paint3;
            this.mPaint.setStrokeCap(Paint.Cap.SQUARE);
            this.mPaint.setAntiAlias(true);
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mArrowPaint.setStyle(Paint.Style.FILL);
            this.mArrowPaint.setAntiAlias(true);
            this.mCirclePaint.setColor(0);
        }

        /* access modifiers changed from: package-private */
        public void setArrowDimensions(float width, float height) {
            this.mArrowWidth = (int) width;
            this.mArrowHeight = (int) height;
        }

        /* access modifiers changed from: package-private */
        public void setStrokeCap(Paint.Cap strokeCap) {
            this.mPaint.setStrokeCap(strokeCap);
        }

        /* access modifiers changed from: package-private */
        public Paint.Cap getStrokeCap() {
            return this.mPaint.getStrokeCap();
        }

        /* access modifiers changed from: package-private */
        public float getArrowWidth() {
            return (float) this.mArrowWidth;
        }

        /* access modifiers changed from: package-private */
        public float getArrowHeight() {
            return (float) this.mArrowHeight;
        }

        /* access modifiers changed from: package-private */
        public void draw(Canvas canvas, Rect rect) {
            Canvas c = canvas;
            Rect bounds = rect;
            RectF arcBounds = this.mTempBounds;
            float arcRadius = this.mRingCenterRadius + (this.mStrokeWidth / 2.0f);
            if (this.mRingCenterRadius <= 0.0f) {
                arcRadius = (((float) Math.min(bounds.width(), bounds.height())) / 2.0f) - Math.max((((float) this.mArrowWidth) * this.mArrowScale) / 2.0f, this.mStrokeWidth / 2.0f);
            }
            arcBounds.set(((float) bounds.centerX()) - arcRadius, ((float) bounds.centerY()) - arcRadius, ((float) bounds.centerX()) + arcRadius, ((float) bounds.centerY()) + arcRadius);
            float startAngle = (this.mStartTrim + this.mRotation) * 360.0f;
            float sweepAngle = ((this.mEndTrim + this.mRotation) * 360.0f) - startAngle;
            this.mPaint.setColor(this.mCurrentColor);
            this.mPaint.setAlpha(this.mAlpha);
            float inset = this.mStrokeWidth / 2.0f;
            arcBounds.inset(inset, inset);
            c.drawCircle(arcBounds.centerX(), arcBounds.centerY(), arcBounds.width() / 2.0f, this.mCirclePaint);
            arcBounds.inset(-inset, -inset);
            c.drawArc(arcBounds, startAngle, sweepAngle, false, this.mPaint);
            drawTriangle(c, startAngle, sweepAngle, arcBounds);
        }

        /* access modifiers changed from: package-private */
        public void drawTriangle(Canvas canvas, float f, float f2, RectF rectF) {
            Path path;
            Canvas c = canvas;
            float startAngle = f;
            float sweepAngle = f2;
            RectF bounds = rectF;
            if (this.mShowArrow) {
                if (this.mArrow == null) {
                    new Path();
                    this.mArrow = path;
                    this.mArrow.setFillType(Path.FillType.EVEN_ODD);
                } else {
                    this.mArrow.reset();
                }
                float centerRadius = Math.min(bounds.width(), bounds.height()) / 2.0f;
                float inset = (((float) this.mArrowWidth) * this.mArrowScale) / 2.0f;
                this.mArrow.moveTo(0.0f, 0.0f);
                this.mArrow.lineTo(((float) this.mArrowWidth) * this.mArrowScale, 0.0f);
                this.mArrow.lineTo((((float) this.mArrowWidth) * this.mArrowScale) / 2.0f, ((float) this.mArrowHeight) * this.mArrowScale);
                this.mArrow.offset((centerRadius + bounds.centerX()) - inset, bounds.centerY() + (this.mStrokeWidth / 2.0f));
                this.mArrow.close();
                this.mArrowPaint.setColor(this.mCurrentColor);
                this.mArrowPaint.setAlpha(this.mAlpha);
                int save = c.save();
                c.rotate(startAngle + sweepAngle, bounds.centerX(), bounds.centerY());
                c.drawPath(this.mArrow, this.mArrowPaint);
                c.restore();
            }
        }

        /* access modifiers changed from: package-private */
        public void setColors(@NonNull int[] colors) {
            this.mColors = colors;
            setColorIndex(0);
        }

        /* access modifiers changed from: package-private */
        public int[] getColors() {
            return this.mColors;
        }

        /* access modifiers changed from: package-private */
        public void setColor(int color) {
            int i = color;
            this.mCurrentColor = i;
        }

        /* access modifiers changed from: package-private */
        public void setBackgroundColor(int color) {
            this.mCirclePaint.setColor(color);
        }

        /* access modifiers changed from: package-private */
        public int getBackgroundColor() {
            return this.mCirclePaint.getColor();
        }

        /* access modifiers changed from: package-private */
        public void setColorIndex(int index) {
            this.mColorIndex = index;
            this.mCurrentColor = this.mColors[this.mColorIndex];
        }

        /* access modifiers changed from: package-private */
        public int getNextColor() {
            return this.mColors[getNextColorIndex()];
        }

        /* access modifiers changed from: package-private */
        public int getNextColorIndex() {
            return (this.mColorIndex + 1) % this.mColors.length;
        }

        /* access modifiers changed from: package-private */
        public void goToNextColor() {
            setColorIndex(getNextColorIndex());
        }

        /* access modifiers changed from: package-private */
        public void setColorFilter(ColorFilter filter) {
            ColorFilter colorFilter = this.mPaint.setColorFilter(filter);
        }

        /* access modifiers changed from: package-private */
        public void setAlpha(int alpha) {
            int i = alpha;
            this.mAlpha = i;
        }

        /* access modifiers changed from: package-private */
        public int getAlpha() {
            return this.mAlpha;
        }

        /* access modifiers changed from: package-private */
        public void setStrokeWidth(float f) {
            float strokeWidth = f;
            this.mStrokeWidth = strokeWidth;
            this.mPaint.setStrokeWidth(strokeWidth);
        }

        /* access modifiers changed from: package-private */
        public float getStrokeWidth() {
            return this.mStrokeWidth;
        }

        /* access modifiers changed from: package-private */
        public void setStartTrim(float startTrim) {
            float f = startTrim;
            this.mStartTrim = f;
        }

        /* access modifiers changed from: package-private */
        public float getStartTrim() {
            return this.mStartTrim;
        }

        /* access modifiers changed from: package-private */
        public float getStartingStartTrim() {
            return this.mStartingStartTrim;
        }

        /* access modifiers changed from: package-private */
        public float getStartingEndTrim() {
            return this.mStartingEndTrim;
        }

        /* access modifiers changed from: package-private */
        public int getStartingColor() {
            return this.mColors[this.mColorIndex];
        }

        /* access modifiers changed from: package-private */
        public void setEndTrim(float endTrim) {
            float f = endTrim;
            this.mEndTrim = f;
        }

        /* access modifiers changed from: package-private */
        public float getEndTrim() {
            return this.mEndTrim;
        }

        /* access modifiers changed from: package-private */
        public void setRotation(float rotation) {
            float f = rotation;
            this.mRotation = f;
        }

        /* access modifiers changed from: package-private */
        public float getRotation() {
            return this.mRotation;
        }

        /* access modifiers changed from: package-private */
        public void setCenterRadius(float centerRadius) {
            float f = centerRadius;
            this.mRingCenterRadius = f;
        }

        /* access modifiers changed from: package-private */
        public float getCenterRadius() {
            return this.mRingCenterRadius;
        }

        /* access modifiers changed from: package-private */
        public void setShowArrow(boolean z) {
            boolean show = z;
            if (this.mShowArrow != show) {
                this.mShowArrow = show;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean getShowArrow() {
            return this.mShowArrow;
        }

        /* access modifiers changed from: package-private */
        public void setArrowScale(float f) {
            float scale = f;
            if (scale != this.mArrowScale) {
                this.mArrowScale = scale;
            }
        }

        /* access modifiers changed from: package-private */
        public float getArrowScale() {
            return this.mArrowScale;
        }

        /* access modifiers changed from: package-private */
        public float getStartingRotation() {
            return this.mStartingRotation;
        }

        /* access modifiers changed from: package-private */
        public void storeOriginals() {
            this.mStartingStartTrim = this.mStartTrim;
            this.mStartingEndTrim = this.mEndTrim;
            this.mStartingRotation = this.mRotation;
        }

        /* access modifiers changed from: package-private */
        public void resetOriginals() {
            this.mStartingStartTrim = 0.0f;
            this.mStartingEndTrim = 0.0f;
            this.mStartingRotation = 0.0f;
            setStartTrim(0.0f);
            setEndTrim(0.0f);
            setRotation(0.0f);
        }
    }
}
