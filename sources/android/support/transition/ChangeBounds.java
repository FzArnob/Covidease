package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.view.ViewCompat;
import android.support.transition.Transition;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import java.util.Map;

public class ChangeBounds extends Transition {
    private static final Property<View, PointF> BOTTOM_RIGHT_ONLY_PROPERTY;
    private static final Property<ViewBounds, PointF> BOTTOM_RIGHT_PROPERTY;
    private static final Property<Drawable, PointF> DRAWABLE_ORIGIN_PROPERTY;
    private static final Property<View, PointF> POSITION_PROPERTY;
    private static final String PROPNAME_BOUNDS = "android:changeBounds:bounds";
    private static final String PROPNAME_CLIP = "android:changeBounds:clip";
    private static final String PROPNAME_PARENT = "android:changeBounds:parent";
    private static final String PROPNAME_WINDOW_X = "android:changeBounds:windowX";
    private static final String PROPNAME_WINDOW_Y = "android:changeBounds:windowY";
    private static final Property<View, PointF> TOP_LEFT_ONLY_PROPERTY;
    private static final Property<ViewBounds, PointF> TOP_LEFT_PROPERTY;
    private static RectEvaluator sRectEvaluator;
    private static final String[] sTransitionProperties;
    private boolean mReparent = false;
    private boolean mResizeClip = false;
    private int[] mTempLocation = new int[2];

    static {
        Property<Drawable, PointF> property;
        Property<ViewBounds, PointF> property2;
        Property<ViewBounds, PointF> property3;
        Property<View, PointF> property4;
        Property<View, PointF> property5;
        Property<View, PointF> property6;
        RectEvaluator rectEvaluator;
        String[] strArr = new String[5];
        strArr[0] = PROPNAME_BOUNDS;
        String[] strArr2 = strArr;
        strArr2[1] = PROPNAME_CLIP;
        String[] strArr3 = strArr2;
        strArr3[2] = PROPNAME_PARENT;
        String[] strArr4 = strArr3;
        strArr4[3] = PROPNAME_WINDOW_X;
        String[] strArr5 = strArr4;
        strArr5[4] = PROPNAME_WINDOW_Y;
        sTransitionProperties = strArr5;
        new Property<Drawable, PointF>(PointF.class, "boundsOrigin") {
            private Rect mBounds;

            {
                Rect rect;
                new Rect();
                this.mBounds = rect;
            }

            public void set(Drawable drawable, PointF pointF) {
                Drawable object = drawable;
                PointF value = pointF;
                object.copyBounds(this.mBounds);
                this.mBounds.offsetTo(Math.round(value.x), Math.round(value.y));
                object.setBounds(this.mBounds);
            }

            public PointF get(Drawable object) {
                PointF pointF;
                object.copyBounds(this.mBounds);
                new PointF((float) this.mBounds.left, (float) this.mBounds.top);
                return pointF;
            }
        };
        DRAWABLE_ORIGIN_PROPERTY = property;
        new Property<ViewBounds, PointF>(PointF.class, "topLeft") {
            public void set(ViewBounds viewBounds, PointF topLeft) {
                viewBounds.setTopLeft(topLeft);
            }

            public PointF get(ViewBounds viewBounds) {
                ViewBounds viewBounds2 = viewBounds;
                return null;
            }
        };
        TOP_LEFT_PROPERTY = property2;
        new Property<ViewBounds, PointF>(PointF.class, "bottomRight") {
            public void set(ViewBounds viewBounds, PointF bottomRight) {
                viewBounds.setBottomRight(bottomRight);
            }

            public PointF get(ViewBounds viewBounds) {
                ViewBounds viewBounds2 = viewBounds;
                return null;
            }
        };
        BOTTOM_RIGHT_PROPERTY = property3;
        new Property<View, PointF>(PointF.class, "bottomRight") {
            public void set(View view, PointF pointF) {
                View view2 = view;
                PointF bottomRight = pointF;
                ViewUtils.setLeftTopRightBottom(view2, view2.getLeft(), view2.getTop(), Math.round(bottomRight.x), Math.round(bottomRight.y));
            }

            public PointF get(View view) {
                View view2 = view;
                return null;
            }
        };
        BOTTOM_RIGHT_ONLY_PROPERTY = property4;
        new Property<View, PointF>(PointF.class, "topLeft") {
            public void set(View view, PointF pointF) {
                View view2 = view;
                PointF topLeft = pointF;
                ViewUtils.setLeftTopRightBottom(view2, Math.round(topLeft.x), Math.round(topLeft.y), view2.getRight(), view2.getBottom());
            }

            public PointF get(View view) {
                View view2 = view;
                return null;
            }
        };
        TOP_LEFT_ONLY_PROPERTY = property5;
        new Property<View, PointF>(PointF.class, "position") {
            public void set(View view, PointF pointF) {
                View view2 = view;
                PointF topLeft = pointF;
                int left = Math.round(topLeft.x);
                int top = Math.round(topLeft.y);
                ViewUtils.setLeftTopRightBottom(view2, left, top, left + view2.getWidth(), top + view2.getHeight());
            }

            public PointF get(View view) {
                View view2 = view;
                return null;
            }
        };
        POSITION_PROPERTY = property6;
        new RectEvaluator();
        sRectEvaluator = rectEvaluator;
    }

    public ChangeBounds() {
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ChangeBounds(android.content.Context r11, android.util.AttributeSet r12) {
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
            r6 = 2
            int[] r6 = new int[r6]
            r5.mTempLocation = r6
            r5 = r0
            r6 = 0
            r5.mResizeClip = r6
            r5 = r0
            r6 = 0
            r5.mReparent = r6
            r5 = r1
            r6 = r2
            int[] r7 = android.support.transition.Styleable.CHANGE_BOUNDS
            android.content.res.TypedArray r5 = r5.obtainStyledAttributes(r6, r7)
            r3 = r5
            r5 = r3
            r6 = r2
            android.content.res.XmlResourceParser r6 = (android.content.res.XmlResourceParser) r6
            java.lang.String r7 = "resizeClip"
            r8 = 0
            r9 = 0
            boolean r5 = android.support.p000v4.content.res.TypedArrayUtils.getNamedBoolean(r5, r6, r7, r8, r9)
            r4 = r5
            r5 = r3
            r5.recycle()
            r5 = r0
            r6 = r4
            r5.setResizeClip(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.ChangeBounds.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    @Nullable
    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    public void setResizeClip(boolean resizeClip) {
        boolean z = resizeClip;
        this.mResizeClip = z;
    }

    public boolean getResizeClip() {
        return this.mResizeClip;
    }

    private void captureValues(TransitionValues transitionValues) {
        Object obj;
        TransitionValues values = transitionValues;
        View view = values.view;
        if (ViewCompat.isLaidOut(view) || view.getWidth() != 0 || view.getHeight() != 0) {
            new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            Object put = values.values.put(PROPNAME_BOUNDS, obj);
            Object put2 = values.values.put(PROPNAME_PARENT, values.view.getParent());
            if (this.mReparent) {
                values.view.getLocationInWindow(this.mTempLocation);
                Object put3 = values.values.put(PROPNAME_WINDOW_X, Integer.valueOf(this.mTempLocation[0]));
                Object put4 = values.values.put(PROPNAME_WINDOW_Y, Integer.valueOf(this.mTempLocation[1]));
            }
            if (this.mResizeClip) {
                Object put5 = values.values.put(PROPNAME_CLIP, ViewCompat.getClipBounds(view));
            }
        }
    }

    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    private boolean parentMatches(View view, View view2) {
        View startParent = view;
        View endParent = view2;
        boolean parentMatches = true;
        if (this.mReparent) {
            TransitionValues endValues = getMatchedTransitionValues(startParent, true);
            if (endValues == null) {
                parentMatches = startParent == endParent;
            } else {
                parentMatches = endParent == endValues.view;
            }
        }
        return parentMatches;
    }

    @Nullable
    public Animator createAnimator(@NonNull ViewGroup viewGroup, @Nullable TransitionValues transitionValues, @Nullable TransitionValues transitionValues2) {
        Canvas canvas;
        BitmapDrawable bitmapDrawable;
        Animator.AnimatorListener animatorListener;
        Animator anim;
        Animator.AnimatorListener animatorListener2;
        Rect rect;
        Rect rect2;
        Transition.TransitionListener transitionListener;
        ViewBounds viewBounds;
        Animator animator;
        Animator.AnimatorListener animatorListener3;
        ViewGroup sceneRoot = viewGroup;
        TransitionValues startValues = transitionValues;
        TransitionValues endValues = transitionValues2;
        if (startValues == null || endValues == null) {
            return null;
        }
        Map<String, Object> startParentVals = startValues.values;
        Map<String, Object> endParentVals = endValues.values;
        ViewGroup startParent = (ViewGroup) startParentVals.get(PROPNAME_PARENT);
        ViewGroup endParent = (ViewGroup) endParentVals.get(PROPNAME_PARENT);
        if (startParent == null || endParent == null) {
            return null;
        }
        View view = endValues.view;
        if (parentMatches(startParent, endParent)) {
            Rect startBounds = (Rect) startValues.values.get(PROPNAME_BOUNDS);
            Rect endBounds = (Rect) endValues.values.get(PROPNAME_BOUNDS);
            int startLeft = startBounds.left;
            int endLeft = endBounds.left;
            int startTop = startBounds.top;
            int endTop = endBounds.top;
            int startRight = startBounds.right;
            int endRight = endBounds.right;
            int startBottom = startBounds.bottom;
            int endBottom = endBounds.bottom;
            int startWidth = startRight - startLeft;
            int startHeight = startBottom - startTop;
            int endWidth = endRight - endLeft;
            int endHeight = endBottom - endTop;
            Rect startClip = (Rect) startValues.values.get(PROPNAME_CLIP);
            Rect endClip = (Rect) endValues.values.get(PROPNAME_CLIP);
            int numChanges = 0;
            if (!((startWidth == 0 || startHeight == 0) && (endWidth == 0 || endHeight == 0))) {
                if (!(startLeft == endLeft && startTop == endTop)) {
                    numChanges = 0 + 1;
                }
                if (!(startRight == endRight && startBottom == endBottom)) {
                    numChanges++;
                }
            }
            if ((startClip != null && !startClip.equals(endClip)) || (startClip == null && endClip != null)) {
                numChanges++;
            }
            if (numChanges > 0) {
                if (!this.mResizeClip) {
                    ViewUtils.setLeftTopRightBottom(view, startLeft, startTop, startRight, startBottom);
                    if (numChanges == 2) {
                        if (startWidth == endWidth && startHeight == endHeight) {
                            anim = ObjectAnimatorUtils.ofPointF(view, POSITION_PROPERTY, getPathMotion().getPath((float) startLeft, (float) startTop, (float) endLeft, (float) endTop));
                        } else {
                            new ViewBounds(view);
                            ViewBounds viewBounds2 = viewBounds;
                            ObjectAnimator topLeftAnimator = ObjectAnimatorUtils.ofPointF(viewBounds2, TOP_LEFT_PROPERTY, getPathMotion().getPath((float) startLeft, (float) startTop, (float) endLeft, (float) endTop));
                            ObjectAnimator bottomRightAnimator = ObjectAnimatorUtils.ofPointF(viewBounds2, BOTTOM_RIGHT_PROPERTY, getPathMotion().getPath((float) startRight, (float) startBottom, (float) endRight, (float) endBottom));
                            new AnimatorSet();
                            Animator set = animator;
                            Animator[] animatorArr = new Animator[2];
                            animatorArr[0] = topLeftAnimator;
                            Animator[] animatorArr2 = animatorArr;
                            animatorArr2[1] = bottomRightAnimator;
                            set.playTogether(animatorArr2);
                            anim = set;
                            final ViewBounds viewBounds3 = viewBounds2;
                            new AnimatorListenerAdapter(this) {
                                private ViewBounds mViewBounds = viewBounds3;
                                final /* synthetic */ ChangeBounds this$0;

                                {
                                    this.this$0 = this$0;
                                }
                            };
                            set.addListener(animatorListener3);
                        }
                    } else if (startLeft == endLeft && startTop == endTop) {
                        anim = ObjectAnimatorUtils.ofPointF(view, BOTTOM_RIGHT_ONLY_PROPERTY, getPathMotion().getPath((float) startRight, (float) startBottom, (float) endRight, (float) endBottom));
                    } else {
                        anim = ObjectAnimatorUtils.ofPointF(view, TOP_LEFT_ONLY_PROPERTY, getPathMotion().getPath((float) startLeft, (float) startTop, (float) endLeft, (float) endTop));
                    }
                } else {
                    ViewUtils.setLeftTopRightBottom(view, startLeft, startTop, startLeft + Math.max(startWidth, endWidth), startTop + Math.max(startHeight, endHeight));
                    ObjectAnimator positionAnimator = null;
                    if (!(startLeft == endLeft && startTop == endTop)) {
                        positionAnimator = ObjectAnimatorUtils.ofPointF(view, POSITION_PROPERTY, getPathMotion().getPath((float) startLeft, (float) startTop, (float) endLeft, (float) endTop));
                    }
                    Rect finalClip = endClip;
                    if (startClip == null) {
                        new Rect(0, 0, startWidth, startHeight);
                        startClip = rect2;
                    }
                    if (endClip == null) {
                        new Rect(0, 0, endWidth, endHeight);
                        endClip = rect;
                    }
                    ObjectAnimator clipAnimator = null;
                    if (!startClip.equals(endClip)) {
                        ViewCompat.setClipBounds(view, startClip);
                        RectEvaluator rectEvaluator = sRectEvaluator;
                        Object[] objArr = new Object[2];
                        objArr[0] = startClip;
                        Object[] objArr2 = objArr;
                        objArr2[1] = endClip;
                        clipAnimator = ObjectAnimator.ofObject(view, "clipBounds", rectEvaluator, objArr2);
                        final View view2 = view;
                        final Rect rect3 = finalClip;
                        final int i = endLeft;
                        final int i2 = endTop;
                        final int i3 = endRight;
                        final int i4 = endBottom;
                        new AnimatorListenerAdapter(this) {
                            private boolean mIsCanceled;
                            final /* synthetic */ ChangeBounds this$0;

                            {
                                this.this$0 = this$0;
                            }

                            public void onAnimationCancel(Animator animator) {
                                Animator animator2 = animator;
                                this.mIsCanceled = true;
                            }

                            public void onAnimationEnd(Animator animator) {
                                Animator animator2 = animator;
                                if (!this.mIsCanceled) {
                                    ViewCompat.setClipBounds(view2, rect3);
                                    ViewUtils.setLeftTopRightBottom(view2, i, i2, i3, i4);
                                }
                            }
                        };
                        clipAnimator.addListener(animatorListener2);
                    }
                    anim = TransitionUtils.mergeAnimators(positionAnimator, clipAnimator);
                }
                if (view.getParent() instanceof ViewGroup) {
                    ViewGroup parent = (ViewGroup) view.getParent();
                    ViewGroupUtils.suppressLayout(parent, true);
                    final ViewGroup viewGroup2 = parent;
                    new TransitionListenerAdapter(this) {
                        boolean mCanceled = false;
                        final /* synthetic */ ChangeBounds this$0;

                        {
                            this.this$0 = this$0;
                        }

                        public void onTransitionCancel(@NonNull Transition transition) {
                            Transition transition2 = transition;
                            ViewGroupUtils.suppressLayout(viewGroup2, false);
                            this.mCanceled = true;
                        }

                        public void onTransitionEnd(@NonNull Transition transition) {
                            Transition transition2 = transition;
                            if (!this.mCanceled) {
                                ViewGroupUtils.suppressLayout(viewGroup2, false);
                            }
                            Transition removeListener = transition2.removeListener(this);
                        }

                        public void onTransitionPause(@NonNull Transition transition) {
                            Transition transition2 = transition;
                            ViewGroupUtils.suppressLayout(viewGroup2, false);
                        }

                        public void onTransitionResume(@NonNull Transition transition) {
                            Transition transition2 = transition;
                            ViewGroupUtils.suppressLayout(viewGroup2, true);
                        }
                    };
                    Transition addListener = addListener(transitionListener);
                }
                return anim;
            }
        } else {
            int startX = ((Integer) startValues.values.get(PROPNAME_WINDOW_X)).intValue();
            int startY = ((Integer) startValues.values.get(PROPNAME_WINDOW_Y)).intValue();
            int endX = ((Integer) endValues.values.get(PROPNAME_WINDOW_X)).intValue();
            int endY = ((Integer) endValues.values.get(PROPNAME_WINDOW_Y)).intValue();
            if (!(startX == endX && startY == endY)) {
                sceneRoot.getLocationInWindow(this.mTempLocation);
                Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
                new Canvas(bitmap);
                view.draw(canvas);
                new BitmapDrawable(bitmap);
                BitmapDrawable drawable = bitmapDrawable;
                float transitionAlpha = ViewUtils.getTransitionAlpha(view);
                ViewUtils.setTransitionAlpha(view, 0.0f);
                ViewUtils.getOverlay(sceneRoot).add(drawable);
                ObjectAnimator anim2 = ObjectAnimator.ofPropertyValuesHolder(drawable, new PropertyValuesHolder[]{PropertyValuesHolderUtils.ofPointF(DRAWABLE_ORIGIN_PROPERTY, getPathMotion().getPath((float) (startX - this.mTempLocation[0]), (float) (startY - this.mTempLocation[1]), (float) (endX - this.mTempLocation[0]), (float) (endY - this.mTempLocation[1])))});
                final ViewGroup viewGroup3 = sceneRoot;
                final BitmapDrawable bitmapDrawable2 = drawable;
                final View view3 = view;
                final float f = transitionAlpha;
                new AnimatorListenerAdapter(this) {
                    final /* synthetic */ ChangeBounds this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void onAnimationEnd(Animator animator) {
                        Animator animator2 = animator;
                        ViewUtils.getOverlay(viewGroup3).remove(bitmapDrawable2);
                        ViewUtils.setTransitionAlpha(view3, f);
                    }
                };
                anim2.addListener(animatorListener);
                return anim2;
            }
        }
        return null;
    }

    private static class ViewBounds {
        private int mBottom;
        private int mBottomRightCalls;
        private int mLeft;
        private int mRight;
        private int mTop;
        private int mTopLeftCalls;
        private View mView;

        ViewBounds(View view) {
            this.mView = view;
        }

        /* access modifiers changed from: package-private */
        public void setTopLeft(PointF pointF) {
            PointF topLeft = pointF;
            this.mLeft = Math.round(topLeft.x);
            this.mTop = Math.round(topLeft.y);
            this.mTopLeftCalls++;
            if (this.mTopLeftCalls == this.mBottomRightCalls) {
                setLeftTopRightBottom();
            }
        }

        /* access modifiers changed from: package-private */
        public void setBottomRight(PointF pointF) {
            PointF bottomRight = pointF;
            this.mRight = Math.round(bottomRight.x);
            this.mBottom = Math.round(bottomRight.y);
            this.mBottomRightCalls++;
            if (this.mTopLeftCalls == this.mBottomRightCalls) {
                setLeftTopRightBottom();
            }
        }

        private void setLeftTopRightBottom() {
            ViewUtils.setLeftTopRightBottom(this.mView, this.mLeft, this.mTop, this.mRight, this.mBottom);
            this.mTopLeftCalls = 0;
            this.mBottomRightCalls = 0;
        }
    }
}
