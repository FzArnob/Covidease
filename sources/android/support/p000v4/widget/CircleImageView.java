package android.support.p000v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.view.ViewCompat;
import android.view.animation.Animation;
import android.widget.ImageView;

/* renamed from: android.support.v4.widget.CircleImageView */
class CircleImageView extends ImageView {
    private static final int FILL_SHADOW_COLOR = 1023410176;
    private static final int KEY_SHADOW_COLOR = 503316480;
    private static final int SHADOW_ELEVATION = 4;
    private static final float SHADOW_RADIUS = 3.5f;
    private static final float X_OFFSET = 0.0f;
    private static final float Y_OFFSET = 1.75f;
    private Animation.AnimationListener mListener;
    int mShadowRadius;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CircleImageView(Context context, int i) {
        super(context);
        OvalShape oval;
        ShapeDrawable shapeDrawable;
        ShapeDrawable circle;
        ShapeDrawable shapeDrawable2;
        Shape shape;
        int color = i;
        float density = getContext().getResources().getDisplayMetrics().density;
        int shadowYOffset = (int) (density * Y_OFFSET);
        int shadowXOffset = (int) (density * 0.0f);
        this.mShadowRadius = (int) (density * SHADOW_RADIUS);
        if (elevationSupported()) {
            new OvalShape();
            new ShapeDrawable(shape);
            circle = shapeDrawable2;
            ViewCompat.setElevation(this, 4.0f * density);
        } else {
            new OvalShadow(this, this.mShadowRadius);
            new ShapeDrawable(oval);
            circle = shapeDrawable;
            setLayerType(1, circle.getPaint());
            circle.getPaint().setShadowLayer((float) this.mShadowRadius, (float) shadowXOffset, (float) shadowYOffset, KEY_SHADOW_COLOR);
            int padding = this.mShadowRadius;
            setPadding(padding, padding, padding, padding);
        }
        circle.getPaint().setColor(color);
        ViewCompat.setBackground(this, circle);
    }

    private boolean elevationSupported() {
        return Build.VERSION.SDK_INT >= 21;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!elevationSupported()) {
            setMeasuredDimension(getMeasuredWidth() + (this.mShadowRadius * 2), getMeasuredHeight() + (this.mShadowRadius * 2));
        }
    }

    public void setAnimationListener(Animation.AnimationListener listener) {
        Animation.AnimationListener animationListener = listener;
        this.mListener = animationListener;
    }

    public void onAnimationStart() {
        super.onAnimationStart();
        if (this.mListener != null) {
            this.mListener.onAnimationStart(getAnimation());
        }
    }

    public void onAnimationEnd() {
        super.onAnimationEnd();
        if (this.mListener != null) {
            this.mListener.onAnimationEnd(getAnimation());
        }
    }

    public void setBackgroundColorRes(int colorRes) {
        setBackgroundColor(ContextCompat.getColor(getContext(), colorRes));
    }

    public void setBackgroundColor(int i) {
        int color = i;
        if (getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable) getBackground()).getPaint().setColor(color);
        }
    }

    /* renamed from: android.support.v4.widget.CircleImageView$OvalShadow */
    private class OvalShadow extends OvalShape {
        private RadialGradient mRadialGradient;
        private Paint mShadowPaint;
        final /* synthetic */ CircleImageView this$0;

        OvalShadow(CircleImageView circleImageView, int shadowRadius) {
            Paint paint;
            CircleImageView circleImageView2 = circleImageView;
            this.this$0 = circleImageView2;
            new Paint();
            this.mShadowPaint = paint;
            circleImageView2.mShadowRadius = shadowRadius;
            updateRadialGradient((int) rect().width());
        }

        /* access modifiers changed from: protected */
        public void onResize(float f, float height) {
            float width = f;
            super.onResize(width, height);
            updateRadialGradient((int) width);
        }

        public void draw(Canvas canvas, Paint paint) {
            Canvas canvas2 = canvas;
            int viewWidth = this.this$0.getWidth();
            int viewHeight = this.this$0.getHeight();
            canvas2.drawCircle((float) (viewWidth / 2), (float) (viewHeight / 2), (float) (viewWidth / 2), this.mShadowPaint);
            canvas2.drawCircle((float) (viewWidth / 2), (float) (viewHeight / 2), (float) ((viewWidth / 2) - this.this$0.mShadowRadius), paint);
        }

        private void updateRadialGradient(int i) {
            RadialGradient radialGradient;
            int diameter = i;
            new RadialGradient((float) (diameter / 2), (float) (diameter / 2), (float) this.this$0.mShadowRadius, new int[]{CircleImageView.FILL_SHADOW_COLOR, 0}, (float[]) null, Shader.TileMode.CLAMP);
            this.mRadialGradient = radialGradient;
            Shader shader = this.mShadowPaint.setShader(this.mRadialGradient);
        }
    }
}
