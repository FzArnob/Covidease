package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.transition.AnimatorUtils;
import android.support.transition.Transition;
import android.view.View;
import android.view.ViewGroup;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class Visibility extends Transition {
    public static final int MODE_IN = 1;
    public static final int MODE_OUT = 2;
    private static final String PROPNAME_PARENT = "android:visibility:parent";
    private static final String PROPNAME_SCREEN_LOCATION = "android:visibility:screenLocation";
    static final String PROPNAME_VISIBILITY = "android:visibility:visibility";
    private static final String[] sTransitionProperties;
    private int mMode = 3;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    static {
        String[] strArr = new String[2];
        strArr[0] = PROPNAME_VISIBILITY;
        String[] strArr2 = strArr;
        strArr2[1] = PROPNAME_PARENT;
        sTransitionProperties = strArr2;
    }

    private static class VisibilityInfo {
        ViewGroup mEndParent;
        int mEndVisibility;
        boolean mFadeIn;
        ViewGroup mStartParent;
        int mStartVisibility;
        boolean mVisibilityChange;

        VisibilityInfo() {
        }
    }

    public Visibility() {
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Visibility(android.content.Context r11, android.util.AttributeSet r12) {
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
            r6 = 3
            r5.mMode = r6
            r5 = r1
            r6 = r2
            int[] r7 = android.support.transition.Styleable.VISIBILITY_TRANSITION
            android.content.res.TypedArray r5 = r5.obtainStyledAttributes(r6, r7)
            r3 = r5
            r5 = r3
            r6 = r2
            android.content.res.XmlResourceParser r6 = (android.content.res.XmlResourceParser) r6
            java.lang.String r7 = "transitionVisibilityMode"
            r8 = 0
            r9 = 0
            int r5 = android.support.p000v4.content.res.TypedArrayUtils.getNamedInt(r5, r6, r7, r8, r9)
            r4 = r5
            r5 = r3
            r5.recycle()
            r5 = r4
            if (r5 == 0) goto L_0x0030
            r5 = r0
            r6 = r4
            r5.setMode(r6)
        L_0x0030:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.Visibility.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public void setMode(int i) {
        Throwable th;
        int mode = i;
        if ((mode & -4) != 0) {
            Throwable th2 = th;
            new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
            throw th2;
        }
        this.mMode = mode;
    }

    public int getMode() {
        return this.mMode;
    }

    @Nullable
    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    private void captureValues(TransitionValues transitionValues) {
        TransitionValues transitionValues2 = transitionValues;
        Object put = transitionValues2.values.put(PROPNAME_VISIBILITY, Integer.valueOf(transitionValues2.view.getVisibility()));
        Object put2 = transitionValues2.values.put(PROPNAME_PARENT, transitionValues2.view.getParent());
        int[] loc = new int[2];
        transitionValues2.view.getLocationOnScreen(loc);
        Object put3 = transitionValues2.values.put(PROPNAME_SCREEN_LOCATION, loc);
    }

    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public boolean isVisible(TransitionValues transitionValues) {
        TransitionValues values = transitionValues;
        if (values == null) {
            return false;
        }
        return ((Integer) values.values.get(PROPNAME_VISIBILITY)).intValue() == 0 && ((View) values.values.get(PROPNAME_PARENT)) != null;
    }

    private VisibilityInfo getVisibilityChangeInfo(TransitionValues transitionValues, TransitionValues transitionValues2) {
        VisibilityInfo visibilityInfo;
        TransitionValues startValues = transitionValues;
        TransitionValues endValues = transitionValues2;
        new VisibilityInfo();
        VisibilityInfo visInfo = visibilityInfo;
        visInfo.mVisibilityChange = false;
        visInfo.mFadeIn = false;
        if (startValues == null || !startValues.values.containsKey(PROPNAME_VISIBILITY)) {
            visInfo.mStartVisibility = -1;
            visInfo.mStartParent = null;
        } else {
            visInfo.mStartVisibility = ((Integer) startValues.values.get(PROPNAME_VISIBILITY)).intValue();
            visInfo.mStartParent = (ViewGroup) startValues.values.get(PROPNAME_PARENT);
        }
        if (endValues == null || !endValues.values.containsKey(PROPNAME_VISIBILITY)) {
            visInfo.mEndVisibility = -1;
            visInfo.mEndParent = null;
        } else {
            visInfo.mEndVisibility = ((Integer) endValues.values.get(PROPNAME_VISIBILITY)).intValue();
            visInfo.mEndParent = (ViewGroup) endValues.values.get(PROPNAME_PARENT);
        }
        if (startValues == null || endValues == null) {
            if (startValues == null && visInfo.mEndVisibility == 0) {
                visInfo.mFadeIn = true;
                visInfo.mVisibilityChange = true;
            } else if (endValues == null && visInfo.mStartVisibility == 0) {
                visInfo.mFadeIn = false;
                visInfo.mVisibilityChange = true;
            }
        } else if (visInfo.mStartVisibility == visInfo.mEndVisibility && visInfo.mStartParent == visInfo.mEndParent) {
            return visInfo;
        } else {
            if (visInfo.mStartVisibility != visInfo.mEndVisibility) {
                if (visInfo.mStartVisibility == 0) {
                    visInfo.mFadeIn = false;
                    visInfo.mVisibilityChange = true;
                } else if (visInfo.mEndVisibility == 0) {
                    visInfo.mFadeIn = true;
                    visInfo.mVisibilityChange = true;
                }
            } else if (visInfo.mEndParent == null) {
                visInfo.mFadeIn = false;
                visInfo.mVisibilityChange = true;
            } else if (visInfo.mStartParent == null) {
                visInfo.mFadeIn = true;
                visInfo.mVisibilityChange = true;
            }
        }
        return visInfo;
    }

    @Nullable
    public Animator createAnimator(@NonNull ViewGroup viewGroup, @Nullable TransitionValues transitionValues, @Nullable TransitionValues transitionValues2) {
        ViewGroup sceneRoot = viewGroup;
        TransitionValues startValues = transitionValues;
        TransitionValues endValues = transitionValues2;
        VisibilityInfo visInfo = getVisibilityChangeInfo(startValues, endValues);
        if (!visInfo.mVisibilityChange || (visInfo.mStartParent == null && visInfo.mEndParent == null)) {
            return null;
        }
        if (visInfo.mFadeIn) {
            return onAppear(sceneRoot, startValues, visInfo.mStartVisibility, endValues, visInfo.mEndVisibility);
        }
        return onDisappear(sceneRoot, startValues, visInfo.mStartVisibility, endValues, visInfo.mEndVisibility);
    }

    public Animator onAppear(ViewGroup viewGroup, TransitionValues transitionValues, int i, TransitionValues transitionValues2, int i2) {
        ViewGroup sceneRoot = viewGroup;
        TransitionValues startValues = transitionValues;
        int i3 = i;
        TransitionValues endValues = transitionValues2;
        int i4 = i2;
        if ((this.mMode & 1) != 1 || endValues == null) {
            return null;
        }
        if (startValues == null) {
            View endParent = (View) endValues.view.getParent();
            if (getVisibilityChangeInfo(getMatchedTransitionValues(endParent, false), getTransitionValues(endParent, false)).mVisibilityChange) {
                return null;
            }
        }
        return onAppear(sceneRoot, endValues.view, startValues, endValues);
    }

    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        ViewGroup viewGroup2 = viewGroup;
        View view2 = view;
        TransitionValues transitionValues3 = transitionValues;
        TransitionValues transitionValues4 = transitionValues2;
        return null;
    }

    /* JADX WARNING: type inference failed for: r21v8, types: [android.support.transition.Transition$TransitionListener] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.animation.Animator onDisappear(android.view.ViewGroup r28, android.support.transition.TransitionValues r29, int r30, android.support.transition.TransitionValues r31, int r32) {
        /*
            r27 = this;
            r2 = r27
            r3 = r28
            r4 = r29
            r5 = r30
            r6 = r31
            r7 = r32
            r20 = r2
            r0 = r20
            int r0 = r0.mMode
            r20 = r0
            r21 = 2
            r20 = r20 & 2
            r21 = 2
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x0025
            r20 = 0
            r2 = r20
        L_0x0024:
            return r2
        L_0x0025:
            r20 = r4
            if (r20 == 0) goto L_0x00fd
            r20 = r4
            r0 = r20
            android.view.View r0 = r0.view
            r20 = r0
        L_0x0031:
            r8 = r20
            r20 = r6
            if (r20 == 0) goto L_0x0101
            r20 = r6
            r0 = r20
            android.view.View r0 = r0.view
            r20 = r0
        L_0x003f:
            r9 = r20
            r20 = 0
            r10 = r20
            r20 = 0
            r11 = r20
            r20 = r9
            if (r20 == 0) goto L_0x0055
            r20 = r9
            android.view.ViewParent r20 = r20.getParent()
            if (r20 != 0) goto L_0x019e
        L_0x0055:
            r20 = r9
            if (r20 == 0) goto L_0x0105
            r20 = r9
            r10 = r20
        L_0x005d:
            r20 = r7
            r12 = r20
            r20 = r10
            if (r20 == 0) goto L_0x01fe
            r20 = r4
            if (r20 == 0) goto L_0x01fe
            r20 = r4
            r0 = r20
            java.util.Map<java.lang.String, java.lang.Object> r0 = r0.values
            r20 = r0
            java.lang.String r21 = "android:visibility:screenLocation"
            java.lang.Object r20 = r20.get(r21)
            int[] r20 = (int[]) r20
            int[] r20 = (int[]) r20
            r13 = r20
            r20 = r13
            r21 = 0
            r20 = r20[r21]
            r14 = r20
            r20 = r13
            r21 = 1
            r20 = r20[r21]
            r15 = r20
            r20 = 2
            r0 = r20
            int[] r0 = new int[r0]
            r20 = r0
            r16 = r20
            r20 = r3
            r21 = r16
            r20.getLocationOnScreen(r21)
            r20 = r10
            r21 = r14
            r22 = r16
            r23 = 0
            r22 = r22[r23]
            int r21 = r21 - r22
            r22 = r10
            int r22 = r22.getLeft()
            int r21 = r21 - r22
            r20.offsetLeftAndRight(r21)
            r20 = r10
            r21 = r15
            r22 = r16
            r23 = 1
            r22 = r22[r23]
            int r21 = r21 - r22
            r22 = r10
            int r22 = r22.getTop()
            int r21 = r21 - r22
            r20.offsetTopAndBottom(r21)
            r20 = r3
            android.support.transition.ViewGroupOverlayImpl r20 = android.support.transition.ViewGroupUtils.getOverlay(r20)
            r17 = r20
            r20 = r17
            r21 = r10
            r20.add(r21)
            r20 = r2
            r21 = r3
            r22 = r10
            r23 = r4
            r24 = r6
            android.animation.Animator r20 = r20.onDisappear(r21, r22, r23, r24)
            r18 = r20
            r20 = r18
            if (r20 != 0) goto L_0x01e2
            r20 = r17
            r21 = r10
            r20.remove(r21)
        L_0x00f7:
            r20 = r18
            r2 = r20
            goto L_0x0024
        L_0x00fd:
            r20 = 0
            goto L_0x0031
        L_0x0101:
            r20 = 0
            goto L_0x003f
        L_0x0105:
            r20 = r8
            if (r20 == 0) goto L_0x005d
            r20 = r8
            android.view.ViewParent r20 = r20.getParent()
            if (r20 != 0) goto L_0x0117
            r20 = r8
            r10 = r20
            goto L_0x005d
        L_0x0117:
            r20 = r8
            android.view.ViewParent r20 = r20.getParent()
            r0 = r20
            boolean r0 = r0 instanceof android.view.View
            r20 = r0
            if (r20 == 0) goto L_0x005d
            r20 = r8
            android.view.ViewParent r20 = r20.getParent()
            android.view.View r20 = (android.view.View) r20
            r12 = r20
            r20 = r2
            r21 = r12
            r22 = 1
            android.support.transition.TransitionValues r20 = r20.getTransitionValues(r21, r22)
            r13 = r20
            r20 = r2
            r21 = r12
            r22 = 1
            android.support.transition.TransitionValues r20 = r20.getMatchedTransitionValues(r21, r22)
            r14 = r20
            r20 = r2
            r21 = r13
            r22 = r14
            android.support.transition.Visibility$VisibilityInfo r20 = r20.getVisibilityChangeInfo(r21, r22)
            r15 = r20
            r20 = r15
            r0 = r20
            boolean r0 = r0.mVisibilityChange
            r20 = r0
            if (r20 != 0) goto L_0x016b
            r20 = r3
            r21 = r8
            r22 = r12
            android.view.View r20 = android.support.transition.TransitionUtils.copyViewImage(r20, r21, r22)
            r10 = r20
        L_0x0169:
            goto L_0x005d
        L_0x016b:
            r20 = r12
            android.view.ViewParent r20 = r20.getParent()
            if (r20 != 0) goto L_0x0169
            r20 = r12
            int r20 = r20.getId()
            r16 = r20
            r20 = r16
            r21 = -1
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x0169
            r20 = r3
            r21 = r16
            android.view.View r20 = r20.findViewById(r21)
            if (r20 == 0) goto L_0x0169
            r20 = r2
            r0 = r20
            boolean r0 = r0.mCanRemoveViews
            r20 = r0
            if (r20 == 0) goto L_0x0169
            r20 = r8
            r10 = r20
            goto L_0x0169
        L_0x019e:
            r20 = r7
            r21 = 4
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x01ae
            r20 = r9
            r11 = r20
            goto L_0x005d
        L_0x01ae:
            r20 = r8
            r21 = r9
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x01be
            r20 = r9
            r11 = r20
            goto L_0x005d
        L_0x01be:
            r20 = r2
            r0 = r20
            boolean r0 = r0.mCanRemoveViews
            r20 = r0
            if (r20 == 0) goto L_0x01ce
            r20 = r8
            r10 = r20
            goto L_0x005d
        L_0x01ce:
            r20 = r3
            r21 = r8
            r22 = r8
            android.view.ViewParent r22 = r22.getParent()
            android.view.View r22 = (android.view.View) r22
            android.view.View r20 = android.support.transition.TransitionUtils.copyViewImage(r20, r21, r22)
            r10 = r20
            goto L_0x005d
        L_0x01e2:
            r20 = r10
            r19 = r20
            r20 = r18
            android.support.transition.Visibility$1 r21 = new android.support.transition.Visibility$1
            r26 = r21
            r21 = r26
            r22 = r26
            r23 = r2
            r24 = r17
            r25 = r19
            r22.<init>(r23, r24, r25)
            r20.addListener(r21)
            goto L_0x00f7
        L_0x01fe:
            r20 = r11
            if (r20 == 0) goto L_0x025c
            r20 = r11
            int r20 = r20.getVisibility()
            r13 = r20
            r20 = r11
            r21 = 0
            android.support.transition.ViewUtils.setTransitionVisibility(r20, r21)
            r20 = r2
            r21 = r3
            r22 = r11
            r23 = r4
            r24 = r6
            android.animation.Animator r20 = r20.onDisappear(r21, r22, r23, r24)
            r14 = r20
            r20 = r14
            if (r20 == 0) goto L_0x0254
            android.support.transition.Visibility$DisappearListener r20 = new android.support.transition.Visibility$DisappearListener
            r26 = r20
            r20 = r26
            r21 = r26
            r22 = r11
            r23 = r12
            r24 = 1
            r21.<init>(r22, r23, r24)
            r15 = r20
            r20 = r14
            r21 = r15
            r20.addListener(r21)
            r20 = r14
            r21 = r15
            android.support.transition.AnimatorUtils.addPauseListener(r20, r21)
            r20 = r2
            r21 = r15
            android.support.transition.Transition r20 = r20.addListener(r21)
        L_0x024e:
            r20 = r14
            r2 = r20
            goto L_0x0024
        L_0x0254:
            r20 = r11
            r21 = r13
            android.support.transition.ViewUtils.setTransitionVisibility(r20, r21)
            goto L_0x024e
        L_0x025c:
            r20 = 0
            r2 = r20
            goto L_0x0024
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.Visibility.onDisappear(android.view.ViewGroup, android.support.transition.TransitionValues, int, android.support.transition.TransitionValues, int):android.animation.Animator");
    }

    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        ViewGroup viewGroup2 = viewGroup;
        View view2 = view;
        TransitionValues transitionValues3 = transitionValues;
        TransitionValues transitionValues4 = transitionValues2;
        return null;
    }

    public boolean isTransitionRequired(TransitionValues transitionValues, TransitionValues transitionValues2) {
        TransitionValues startValues = transitionValues;
        TransitionValues newValues = transitionValues2;
        if (startValues == null && newValues == null) {
            return false;
        }
        if (startValues != null && newValues != null && newValues.values.containsKey(PROPNAME_VISIBILITY) != startValues.values.containsKey(PROPNAME_VISIBILITY)) {
            return false;
        }
        VisibilityInfo changeInfo = getVisibilityChangeInfo(startValues, newValues);
        return changeInfo.mVisibilityChange && (changeInfo.mStartVisibility == 0 || changeInfo.mEndVisibility == 0);
    }

    private static class DisappearListener extends AnimatorListenerAdapter implements Transition.TransitionListener, AnimatorUtils.AnimatorPauseListenerCompat {
        boolean mCanceled = false;
        private final int mFinalVisibility;
        private boolean mLayoutSuppressed;
        private final ViewGroup mParent;
        private final boolean mSuppressLayout;
        private final View mView;

        DisappearListener(View view, int finalVisibility, boolean suppressLayout) {
            View view2 = view;
            this.mView = view2;
            this.mFinalVisibility = finalVisibility;
            this.mParent = (ViewGroup) view2.getParent();
            this.mSuppressLayout = suppressLayout;
            suppressLayout(true);
        }

        public void onAnimationPause(Animator animator) {
            Animator animator2 = animator;
            if (!this.mCanceled) {
                ViewUtils.setTransitionVisibility(this.mView, this.mFinalVisibility);
            }
        }

        public void onAnimationResume(Animator animator) {
            Animator animator2 = animator;
            if (!this.mCanceled) {
                ViewUtils.setTransitionVisibility(this.mView, 0);
            }
        }

        public void onAnimationCancel(Animator animator) {
            Animator animator2 = animator;
            this.mCanceled = true;
        }

        public void onAnimationRepeat(Animator animation) {
        }

        public void onAnimationStart(Animator animation) {
        }

        public void onAnimationEnd(Animator animator) {
            Animator animator2 = animator;
            hideViewWhenNotCanceled();
        }

        public void onTransitionStart(@NonNull Transition transition) {
        }

        public void onTransitionEnd(@NonNull Transition transition) {
            hideViewWhenNotCanceled();
            Transition removeListener = transition.removeListener(this);
        }

        public void onTransitionCancel(@NonNull Transition transition) {
        }

        public void onTransitionPause(@NonNull Transition transition) {
            Transition transition2 = transition;
            suppressLayout(false);
        }

        public void onTransitionResume(@NonNull Transition transition) {
            Transition transition2 = transition;
            suppressLayout(true);
        }

        private void hideViewWhenNotCanceled() {
            if (!this.mCanceled) {
                ViewUtils.setTransitionVisibility(this.mView, this.mFinalVisibility);
                if (this.mParent != null) {
                    this.mParent.invalidate();
                }
            }
            suppressLayout(false);
        }

        private void suppressLayout(boolean z) {
            boolean suppress = z;
            if (this.mSuppressLayout && this.mLayoutSuppressed != suppress && this.mParent != null) {
                this.mLayoutSuppressed = suppress;
                ViewGroupUtils.suppressLayout(this.mParent, suppress);
            }
        }
    }
}
