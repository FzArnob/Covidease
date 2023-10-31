package android.support.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.p000v4.view.GravityCompat;
import android.support.p000v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Slide extends Visibility {
    private static final String PROPNAME_SCREEN_POSITION = "android:slide:screenPosition";
    private static final TimeInterpolator sAccelerate;
    private static final CalculateSlide sCalculateBottom;
    private static final CalculateSlide sCalculateEnd;
    private static final CalculateSlide sCalculateLeft;
    private static final CalculateSlide sCalculateRight;
    private static final CalculateSlide sCalculateStart;
    private static final CalculateSlide sCalculateTop;
    private static final TimeInterpolator sDecelerate;
    private CalculateSlide mSlideCalculator = sCalculateBottom;
    private int mSlideEdge = 80;

    private interface CalculateSlide {
        float getGoneX(ViewGroup viewGroup, View view);

        float getGoneY(ViewGroup viewGroup, View view);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface GravityFlag {
    }

    static {
        TimeInterpolator timeInterpolator;
        TimeInterpolator timeInterpolator2;
        CalculateSlide calculateSlide;
        CalculateSlide calculateSlide2;
        CalculateSlide calculateSlide3;
        CalculateSlide calculateSlide4;
        CalculateSlide calculateSlide5;
        CalculateSlide calculateSlide6;
        new DecelerateInterpolator();
        sDecelerate = timeInterpolator;
        new AccelerateInterpolator();
        sAccelerate = timeInterpolator2;
        new CalculateSlideHorizontal() {
            public float getGoneX(ViewGroup sceneRoot, View view) {
                return view.getTranslationX() - ((float) sceneRoot.getWidth());
            }
        };
        sCalculateLeft = calculateSlide;
        new CalculateSlideHorizontal() {
            public float getGoneX(ViewGroup viewGroup, View view) {
                float x;
                ViewGroup sceneRoot = viewGroup;
                View view2 = view;
                if (ViewCompat.getLayoutDirection(sceneRoot) == 1) {
                    x = view2.getTranslationX() + ((float) sceneRoot.getWidth());
                } else {
                    x = view2.getTranslationX() - ((float) sceneRoot.getWidth());
                }
                return x;
            }
        };
        sCalculateStart = calculateSlide2;
        new CalculateSlideVertical() {
            public float getGoneY(ViewGroup sceneRoot, View view) {
                return view.getTranslationY() - ((float) sceneRoot.getHeight());
            }
        };
        sCalculateTop = calculateSlide3;
        new CalculateSlideHorizontal() {
            public float getGoneX(ViewGroup sceneRoot, View view) {
                return view.getTranslationX() + ((float) sceneRoot.getWidth());
            }
        };
        sCalculateRight = calculateSlide4;
        new CalculateSlideHorizontal() {
            public float getGoneX(ViewGroup viewGroup, View view) {
                float x;
                ViewGroup sceneRoot = viewGroup;
                View view2 = view;
                if (ViewCompat.getLayoutDirection(sceneRoot) == 1) {
                    x = view2.getTranslationX() - ((float) sceneRoot.getWidth());
                } else {
                    x = view2.getTranslationX() + ((float) sceneRoot.getWidth());
                }
                return x;
            }
        };
        sCalculateEnd = calculateSlide5;
        new CalculateSlideVertical() {
            public float getGoneY(ViewGroup sceneRoot, View view) {
                return view.getTranslationY() + ((float) sceneRoot.getHeight());
            }
        };
        sCalculateBottom = calculateSlide6;
    }

    private static abstract class CalculateSlideHorizontal implements CalculateSlide {
        private CalculateSlideHorizontal() {
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ CalculateSlideHorizontal(C02151 r4) {
            this();
            C02151 r1 = r4;
        }

        public float getGoneY(ViewGroup viewGroup, View view) {
            ViewGroup viewGroup2 = viewGroup;
            return view.getTranslationY();
        }
    }

    private static abstract class CalculateSlideVertical implements CalculateSlide {
        private CalculateSlideVertical() {
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ CalculateSlideVertical(C02151 r4) {
            this();
            C02151 r1 = r4;
        }

        public float getGoneX(ViewGroup viewGroup, View view) {
            ViewGroup viewGroup2 = viewGroup;
            return view.getTranslationX();
        }
    }

    public Slide() {
        setSlideEdge(80);
    }

    public Slide(int slideEdge) {
        setSlideEdge(slideEdge);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Slide(android.content.Context r11, android.util.AttributeSet r12) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r5 = r0
            r6 = r1
            r7 = r2
            r5.<init>(r6, r7)
            r5 = r0
            android.support.transition.Slide$CalculateSlide r6 = sCalculateBottom
            r5.mSlideCalculator = r6
            r5 = r0
            r6 = 80
            r5.mSlideEdge = r6
            r5 = r1
            r6 = r2
            int[] r7 = android.support.transition.Styleable.SLIDE
            android.content.res.TypedArray r5 = r5.obtainStyledAttributes(r6, r7)
            r3 = r5
            r5 = r3
            r6 = r2
            org.xmlpull.v1.XmlPullParser r6 = (org.xmlpull.v1.XmlPullParser) r6
            java.lang.String r7 = "slideEdge"
            r8 = 0
            r9 = 80
            int r5 = android.support.p000v4.content.res.TypedArrayUtils.getNamedInt(r5, r6, r7, r8, r9)
            r4 = r5
            r5 = r3
            r5.recycle()
            r5 = r0
            r6 = r4
            r5.setSlideEdge(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.Slide.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    private void captureValues(TransitionValues transitionValues) {
        TransitionValues transitionValues2 = transitionValues;
        int[] position = new int[2];
        transitionValues2.view.getLocationOnScreen(position);
        Object put = transitionValues2.values.put(PROPNAME_SCREEN_POSITION, position);
    }

    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        TransitionValues transitionValues2 = transitionValues;
        super.captureStartValues(transitionValues2);
        captureValues(transitionValues2);
    }

    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        TransitionValues transitionValues2 = transitionValues;
        super.captureEndValues(transitionValues2);
        captureValues(transitionValues2);
    }

    public void setSlideEdge(int i) {
        SidePropagation sidePropagation;
        Throwable th;
        int slideEdge = i;
        switch (slideEdge) {
            case 3:
                this.mSlideCalculator = sCalculateLeft;
                break;
            case 5:
                this.mSlideCalculator = sCalculateRight;
                break;
            case 48:
                this.mSlideCalculator = sCalculateTop;
                break;
            case 80:
                this.mSlideCalculator = sCalculateBottom;
                break;
            case GravityCompat.START /*8388611*/:
                this.mSlideCalculator = sCalculateStart;
                break;
            case GravityCompat.END /*8388613*/:
                this.mSlideCalculator = sCalculateEnd;
                break;
            default:
                Throwable th2 = th;
                new IllegalArgumentException("Invalid slide direction");
                throw th2;
        }
        this.mSlideEdge = slideEdge;
        new SidePropagation();
        SidePropagation propagation = sidePropagation;
        propagation.setSide(slideEdge);
        setPropagation(propagation);
    }

    public int getSlideEdge() {
        return this.mSlideEdge;
    }

    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        ViewGroup sceneRoot = viewGroup;
        View view2 = view;
        TransitionValues transitionValues3 = transitionValues;
        TransitionValues endValues = transitionValues2;
        if (endValues == null) {
            return null;
        }
        int[] position = (int[]) endValues.values.get(PROPNAME_SCREEN_POSITION);
        float endX = view2.getTranslationX();
        float endY = view2.getTranslationY();
        return TranslationAnimationCreator.createAnimation(view2, endValues, position[0], position[1], this.mSlideCalculator.getGoneX(sceneRoot, view2), this.mSlideCalculator.getGoneY(sceneRoot, view2), endX, endY, sDecelerate);
    }

    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        ViewGroup sceneRoot = viewGroup;
        View view2 = view;
        TransitionValues startValues = transitionValues;
        TransitionValues transitionValues3 = transitionValues2;
        if (startValues == null) {
            return null;
        }
        int[] position = (int[]) startValues.values.get(PROPNAME_SCREEN_POSITION);
        return TranslationAnimationCreator.createAnimation(view2, startValues, position[0], position[1], view2.getTranslationX(), view2.getTranslationY(), this.mSlideCalculator.getGoneX(sceneRoot, view2), this.mSlideCalculator.getGoneY(sceneRoot, view2), sAccelerate);
    }
}
