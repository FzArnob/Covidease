package android.support.design.circularreveal;

import android.animation.TypeEvaluator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.design.circularreveal.CircularRevealHelper;
import android.support.design.widget.MathUtils;
import android.util.Property;

public interface CircularRevealWidget extends CircularRevealHelper.Delegate {
    void buildCircularRevealCache();

    void destroyCircularRevealCache();

    void draw(Canvas canvas);

    @Nullable
    Drawable getCircularRevealOverlayDrawable();

    @ColorInt
    int getCircularRevealScrimColor();

    @Nullable
    RevealInfo getRevealInfo();

    boolean isOpaque();

    void setCircularRevealOverlayDrawable(@Nullable Drawable drawable);

    void setCircularRevealScrimColor(@ColorInt int i);

    void setRevealInfo(@Nullable RevealInfo revealInfo);

    public static class RevealInfo {
        public static final float INVALID_RADIUS = Float.MAX_VALUE;
        public float centerX;
        public float centerY;
        public float radius;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ RevealInfo(C00831 r4) {
            this();
            C00831 r1 = r4;
        }

        private RevealInfo() {
        }

        public RevealInfo(float centerX2, float centerY2, float radius2) {
            this.centerX = centerX2;
            this.centerY = centerY2;
            this.radius = radius2;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public RevealInfo(android.support.design.circularreveal.CircularRevealWidget.RevealInfo r7) {
            /*
                r6 = this;
                r0 = r6
                r1 = r7
                r2 = r0
                r3 = r1
                float r3 = r3.centerX
                r4 = r1
                float r4 = r4.centerY
                r5 = r1
                float r5 = r5.radius
                r2.<init>(r3, r4, r5)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.circularreveal.CircularRevealWidget.RevealInfo.<init>(android.support.design.circularreveal.CircularRevealWidget$RevealInfo):void");
        }

        public void set(float centerX2, float centerY2, float radius2) {
            this.centerX = centerX2;
            this.centerY = centerY2;
            this.radius = radius2;
        }

        public void set(RevealInfo revealInfo) {
            RevealInfo other = revealInfo;
            set(other.centerX, other.centerY, other.radius);
        }

        public boolean isInvalid() {
            return this.radius == Float.MAX_VALUE;
        }
    }

    public static class CircularRevealProperty extends Property<CircularRevealWidget, RevealInfo> {
        public static final Property<CircularRevealWidget, RevealInfo> CIRCULAR_REVEAL;

        static {
            Property<CircularRevealWidget, RevealInfo> property;
            new CircularRevealProperty("circularReveal");
            CIRCULAR_REVEAL = property;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        private CircularRevealProperty(String name) {
            super(RevealInfo.class, name);
        }

        public RevealInfo get(CircularRevealWidget object) {
            return object.getRevealInfo();
        }

        public void set(CircularRevealWidget object, RevealInfo value) {
            object.setRevealInfo(value);
        }
    }

    public static class CircularRevealEvaluator implements TypeEvaluator<RevealInfo> {
        public static final TypeEvaluator<RevealInfo> CIRCULAR_REVEAL;
        private final RevealInfo revealInfo;

        public CircularRevealEvaluator() {
            RevealInfo revealInfo2;
            new RevealInfo((C00831) null);
            this.revealInfo = revealInfo2;
        }

        static {
            TypeEvaluator<RevealInfo> typeEvaluator;
            new CircularRevealEvaluator();
            CIRCULAR_REVEAL = typeEvaluator;
        }

        public RevealInfo evaluate(float f, RevealInfo revealInfo2, RevealInfo revealInfo3) {
            float fraction = f;
            RevealInfo startValue = revealInfo2;
            RevealInfo endValue = revealInfo3;
            this.revealInfo.set(MathUtils.lerp(startValue.centerX, endValue.centerX, fraction), MathUtils.lerp(startValue.centerY, endValue.centerY, fraction), MathUtils.lerp(startValue.radius, endValue.radius, fraction));
            return this.revealInfo;
        }
    }

    public static class CircularRevealScrimColorProperty extends Property<CircularRevealWidget, Integer> {
        public static final Property<CircularRevealWidget, Integer> CIRCULAR_REVEAL_SCRIM_COLOR;

        static {
            Property<CircularRevealWidget, Integer> property;
            new CircularRevealScrimColorProperty("circularRevealScrimColor");
            CIRCULAR_REVEAL_SCRIM_COLOR = property;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        private CircularRevealScrimColorProperty(String name) {
            super(Integer.class, name);
        }

        public Integer get(CircularRevealWidget object) {
            return Integer.valueOf(object.getCircularRevealScrimColor());
        }

        public void set(CircularRevealWidget object, Integer value) {
            object.setCircularRevealScrimColor(value.intValue());
        }
    }
}
