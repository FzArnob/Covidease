package com.github.ybq.android.spinkit.sprite;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.Property;
import com.github.ybq.android.spinkit.animation.AnimationUtils;
import com.github.ybq.android.spinkit.animation.FloatProperty;
import com.github.ybq.android.spinkit.animation.IntProperty;

public abstract class Sprite extends Drawable implements ValueAnimator.AnimatorUpdateListener, Animatable, Drawable.Callback {
    public static final Property<Sprite, Integer> ALPHA;
    public static final Property<Sprite, Integer> ROTATE;
    public static final Property<Sprite, Integer> ROTATE_X;
    public static final Property<Sprite, Integer> ROTATE_Y;
    public static final Property<Sprite, Float> SCALE;
    public static final Property<Sprite, Float> SCALE_X;
    public static final Property<Sprite, Float> SCALE_Y;
    public static final Property<Sprite, Integer> TRANSLATE_X;
    public static final Property<Sprite, Float> TRANSLATE_X_PERCENTAGE;
    public static final Property<Sprite, Integer> TRANSLATE_Y;
    public static final Property<Sprite, Float> TRANSLATE_Y_PERCENTAGE;
    private static final Rect ZERO_BOUNDS_RECT;
    private int alpha = 255;
    private int animationDelay;
    private ValueAnimator animator;
    protected Rect drawBounds = ZERO_BOUNDS_RECT;
    private Camera mCamera;
    private Matrix mMatrix;
    private float pivotX;
    private float pivotY;
    private int rotate;
    private int rotateX;
    private int rotateY;
    private float scale = 1.0f;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private int translateX;
    private float translateXPercentage;
    private int translateY;
    private float translateYPercentage;

    /* access modifiers changed from: protected */
    public abstract void drawSelf(Canvas canvas);

    public abstract int getColor();

    public abstract ValueAnimator onCreateAnimation();

    public abstract void setColor(int i);

    static {
        Rect rect;
        Property<Sprite, Integer> property;
        Property<Sprite, Integer> property2;
        Property<Sprite, Integer> property3;
        Property<Sprite, Integer> property4;
        Property<Sprite, Integer> property5;
        Property<Sprite, Float> property6;
        Property<Sprite, Float> property7;
        Property<Sprite, Float> property8;
        Property<Sprite, Float> property9;
        Property<Sprite, Float> property10;
        Property<Sprite, Integer> property11;
        new Rect();
        ZERO_BOUNDS_RECT = rect;
        new IntProperty<Sprite>("rotateX") {
            public void setValue(Sprite object, int value) {
                object.setRotateX(value);
            }

            public Integer get(Sprite object) {
                return Integer.valueOf(object.getRotateX());
            }
        };
        ROTATE_X = property;
        new IntProperty<Sprite>("rotate") {
            public void setValue(Sprite object, int value) {
                object.setRotate(value);
            }

            public Integer get(Sprite object) {
                return Integer.valueOf(object.getRotate());
            }
        };
        ROTATE = property2;
        new IntProperty<Sprite>("rotateY") {
            public void setValue(Sprite object, int value) {
                object.setRotateY(value);
            }

            public Integer get(Sprite object) {
                return Integer.valueOf(object.getRotateY());
            }
        };
        ROTATE_Y = property3;
        new IntProperty<Sprite>("translateX") {
            public void setValue(Sprite object, int value) {
                object.setTranslateX(value);
            }

            public Integer get(Sprite object) {
                return Integer.valueOf(object.getTranslateX());
            }
        };
        TRANSLATE_X = property4;
        new IntProperty<Sprite>("translateY") {
            public void setValue(Sprite object, int value) {
                object.setTranslateY(value);
            }

            public Integer get(Sprite object) {
                return Integer.valueOf(object.getTranslateY());
            }
        };
        TRANSLATE_Y = property5;
        new FloatProperty<Sprite>("translateXPercentage") {
            public void setValue(Sprite object, float value) {
                object.setTranslateXPercentage(value);
            }

            public Float get(Sprite object) {
                return Float.valueOf(object.getTranslateXPercentage());
            }
        };
        TRANSLATE_X_PERCENTAGE = property6;
        new FloatProperty<Sprite>("translateYPercentage") {
            public void setValue(Sprite object, float value) {
                object.setTranslateYPercentage(value);
            }

            public Float get(Sprite object) {
                return Float.valueOf(object.getTranslateYPercentage());
            }
        };
        TRANSLATE_Y_PERCENTAGE = property7;
        new FloatProperty<Sprite>("scaleX") {
            public void setValue(Sprite object, float value) {
                object.setScaleX(value);
            }

            public Float get(Sprite object) {
                return Float.valueOf(object.getScaleX());
            }
        };
        SCALE_X = property8;
        new FloatProperty<Sprite>("scaleY") {
            public void setValue(Sprite object, float value) {
                object.setScaleY(value);
            }

            public Float get(Sprite object) {
                return Float.valueOf(object.getScaleY());
            }
        };
        SCALE_Y = property9;
        new FloatProperty<Sprite>("scale") {
            public void setValue(Sprite object, float value) {
                object.setScale(value);
            }

            public Float get(Sprite object) {
                return Float.valueOf(object.getScale());
            }
        };
        SCALE = property10;
        new IntProperty<Sprite>("alpha") {
            public void setValue(Sprite object, int value) {
                object.setAlpha(value);
            }

            public Integer get(Sprite object) {
                return Integer.valueOf(object.getAlpha());
            }
        };
        ALPHA = property11;
    }

    public Sprite() {
        Camera camera;
        Matrix matrix;
        new Camera();
        this.mCamera = camera;
        new Matrix();
        this.mMatrix = matrix;
    }

    public void setAlpha(int alpha2) {
        int i = alpha2;
        this.alpha = i;
    }

    public int getAlpha() {
        return this.alpha;
    }

    public int getOpacity() {
        return -3;
    }

    public float getTranslateXPercentage() {
        return this.translateXPercentage;
    }

    public void setTranslateXPercentage(float translateXPercentage2) {
        float f = translateXPercentage2;
        this.translateXPercentage = f;
    }

    public float getTranslateYPercentage() {
        return this.translateYPercentage;
    }

    public void setTranslateYPercentage(float translateYPercentage2) {
        float f = translateYPercentage2;
        this.translateYPercentage = f;
    }

    public int getTranslateX() {
        return this.translateX;
    }

    public void setTranslateX(int translateX2) {
        int i = translateX2;
        this.translateX = i;
    }

    public int getTranslateY() {
        return this.translateY;
    }

    public void setTranslateY(int translateY2) {
        int i = translateY2;
        this.translateY = i;
    }

    public int getRotate() {
        return this.rotate;
    }

    public void setRotate(int rotate2) {
        int i = rotate2;
        this.rotate = i;
    }

    public float getScale() {
        return this.scale;
    }

    public void setScale(float f) {
        float scale2 = f;
        this.scale = scale2;
        setScaleX(scale2);
        setScaleY(scale2);
    }

    public float getScaleX() {
        return this.scaleX;
    }

    public void setScaleX(float scaleX2) {
        float f = scaleX2;
        this.scaleX = f;
    }

    public float getScaleY() {
        return this.scaleY;
    }

    public void setScaleY(float scaleY2) {
        float f = scaleY2;
        this.scaleY = f;
    }

    public int getRotateX() {
        return this.rotateX;
    }

    public void setRotateX(int rotateX2) {
        int i = rotateX2;
        this.rotateX = i;
    }

    public int getRotateY() {
        return this.rotateY;
    }

    public void setRotateY(int rotateY2) {
        int i = rotateY2;
        this.rotateY = i;
    }

    public float getPivotX() {
        return this.pivotX;
    }

    public void setPivotX(float pivotX2) {
        float f = pivotX2;
        this.pivotX = f;
    }

    public float getPivotY() {
        return this.pivotY;
    }

    public void setPivotY(float pivotY2) {
        float f = pivotY2;
        this.pivotY = f;
    }

    public int getAnimationDelay() {
        return this.animationDelay;
    }

    public Sprite setAnimationDelay(int animationDelay2) {
        this.animationDelay = animationDelay2;
        return this;
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void start() {
        if (!AnimationUtils.isStarted(this.animator)) {
            this.animator = obtainAnimation();
            if (this.animator != null) {
                AnimationUtils.start((Animator) this.animator);
                invalidateSelf();
            }
        }
    }

    public ValueAnimator obtainAnimation() {
        if (this.animator == null) {
            this.animator = onCreateAnimation();
        }
        if (this.animator != null) {
            this.animator.addUpdateListener(this);
            this.animator.setStartDelay((long) this.animationDelay);
        }
        return this.animator;
    }

    public void stop() {
        if (AnimationUtils.isStarted(this.animator)) {
            this.animator.removeAllUpdateListeners();
            this.animator.end();
            reset();
        }
    }

    public void reset() {
        this.scale = 1.0f;
        this.rotateX = 0;
        this.rotateY = 0;
        this.translateX = 0;
        this.translateY = 0;
        this.rotate = 0;
        this.translateXPercentage = 0.0f;
        this.translateYPercentage = 0.0f;
    }

    public boolean isRunning() {
        return AnimationUtils.isRunning(this.animator);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Rect bounds = rect;
        super.onBoundsChange(bounds);
        setDrawBounds(bounds);
    }

    public void setDrawBounds(Rect rect) {
        Rect drawBounds2 = rect;
        setDrawBounds(drawBounds2.left, drawBounds2.top, drawBounds2.right, drawBounds2.bottom);
    }

    public void setDrawBounds(int left, int top, int right, int bottom) {
        Rect rect;
        new Rect(left, top, right, bottom);
        this.drawBounds = rect;
        setPivotX((float) getDrawBounds().centerX());
        setPivotY((float) getDrawBounds().centerY());
    }

    public void invalidateDrawable(Drawable drawable) {
        Drawable drawable2 = drawable;
        invalidateSelf();
    }

    public void scheduleDrawable(Drawable who, Runnable what, long when) {
    }

    public void unscheduleDrawable(Drawable who, Runnable what) {
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        ValueAnimator valueAnimator2 = valueAnimator;
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public Rect getDrawBounds() {
        return this.drawBounds;
    }

    public void draw(Canvas canvas) {
        Canvas canvas2 = canvas;
        int tx = getTranslateX();
        int tx2 = tx == 0 ? (int) (((float) getBounds().width()) * getTranslateXPercentage()) : tx;
        int ty = getTranslateY();
        canvas2.translate((float) tx2, (float) (ty == 0 ? (int) (((float) getBounds().height()) * getTranslateYPercentage()) : ty));
        canvas2.scale(getScaleX(), getScaleY(), getPivotX(), getPivotY());
        canvas2.rotate((float) getRotate(), getPivotX(), getPivotY());
        if (!(getRotateX() == 0 && getRotateY() == 0)) {
            this.mCamera.save();
            this.mCamera.rotateX((float) getRotateX());
            this.mCamera.rotateY((float) getRotateY());
            this.mCamera.getMatrix(this.mMatrix);
            boolean preTranslate = this.mMatrix.preTranslate(-getPivotX(), -getPivotY());
            boolean postTranslate = this.mMatrix.postTranslate(getPivotX(), getPivotY());
            this.mCamera.restore();
            canvas2.concat(this.mMatrix);
        }
        drawSelf(canvas2);
    }

    public Rect clipSquare(Rect rect) {
        Rect rect2;
        Rect rect3 = rect;
        int min = Math.min(rect3.width(), rect3.height());
        int cx = rect3.centerX();
        int cy = rect3.centerY();
        int r = min / 2;
        new Rect(cx - r, cy - r, cx + r, cy + r);
        return rect2;
    }
}
