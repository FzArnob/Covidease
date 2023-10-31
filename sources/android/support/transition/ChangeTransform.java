package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.p000v4.view.ViewCompat;
import android.support.transition.Transition;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;

public class ChangeTransform extends Transition {
    private static final Property<PathAnimatorMatrix, float[]> NON_TRANSLATIONS_PROPERTY;
    private static final String PROPNAME_INTERMEDIATE_MATRIX = "android:changeTransform:intermediateMatrix";
    private static final String PROPNAME_INTERMEDIATE_PARENT_MATRIX = "android:changeTransform:intermediateParentMatrix";
    private static final String PROPNAME_MATRIX = "android:changeTransform:matrix";
    private static final String PROPNAME_PARENT = "android:changeTransform:parent";
    private static final String PROPNAME_PARENT_MATRIX = "android:changeTransform:parentMatrix";
    private static final String PROPNAME_TRANSFORMS = "android:changeTransform:transforms";
    private static final boolean SUPPORTS_VIEW_REMOVAL_SUPPRESSION = (Build.VERSION.SDK_INT >= 21);
    private static final Property<PathAnimatorMatrix, PointF> TRANSLATIONS_PROPERTY;
    private static final String[] sTransitionProperties;
    private boolean mReparent = true;
    private Matrix mTempMatrix;
    boolean mUseOverlay = true;

    static {
        Property<PathAnimatorMatrix, float[]> property;
        Property<PathAnimatorMatrix, PointF> property2;
        String[] strArr = new String[3];
        strArr[0] = PROPNAME_MATRIX;
        String[] strArr2 = strArr;
        strArr2[1] = PROPNAME_TRANSFORMS;
        String[] strArr3 = strArr2;
        strArr3[2] = PROPNAME_PARENT_MATRIX;
        sTransitionProperties = strArr3;
        new Property<PathAnimatorMatrix, float[]>(float[].class, "nonTranslations") {
            public float[] get(PathAnimatorMatrix pathAnimatorMatrix) {
                PathAnimatorMatrix pathAnimatorMatrix2 = pathAnimatorMatrix;
                return null;
            }

            public void set(PathAnimatorMatrix object, float[] value) {
                object.setValues(value);
            }
        };
        NON_TRANSLATIONS_PROPERTY = property;
        new Property<PathAnimatorMatrix, PointF>(PointF.class, "translations") {
            public PointF get(PathAnimatorMatrix pathAnimatorMatrix) {
                PathAnimatorMatrix pathAnimatorMatrix2 = pathAnimatorMatrix;
                return null;
            }

            public void set(PathAnimatorMatrix object, PointF value) {
                object.setTranslation(value);
            }
        };
        TRANSLATIONS_PROPERTY = property2;
    }

    public ChangeTransform() {
        Matrix matrix;
        new Matrix();
        this.mTempMatrix = matrix;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ChangeTransform(android.content.Context r12, android.util.AttributeSet r13) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r4 = r0
            r5 = r1
            r6 = r2
            r4.<init>(r5, r6)
            r4 = r0
            r5 = 1
            r4.mUseOverlay = r5
            r4 = r0
            r5 = 1
            r4.mReparent = r5
            r4 = r0
            android.graphics.Matrix r5 = new android.graphics.Matrix
            r10 = r5
            r5 = r10
            r6 = r10
            r6.<init>()
            r4.mTempMatrix = r5
            r4 = r1
            r5 = r2
            int[] r6 = android.support.transition.Styleable.CHANGE_TRANSFORM
            android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r5, r6)
            r3 = r4
            r4 = r0
            r5 = r3
            r6 = r2
            org.xmlpull.v1.XmlPullParser r6 = (org.xmlpull.v1.XmlPullParser) r6
            java.lang.String r7 = "reparentWithOverlay"
            r8 = 1
            r9 = 1
            boolean r5 = android.support.p000v4.content.res.TypedArrayUtils.getNamedBoolean(r5, r6, r7, r8, r9)
            r4.mUseOverlay = r5
            r4 = r0
            r5 = r3
            r6 = r2
            org.xmlpull.v1.XmlPullParser r6 = (org.xmlpull.v1.XmlPullParser) r6
            java.lang.String r7 = "reparent"
            r8 = 0
            r9 = 1
            boolean r5 = android.support.p000v4.content.res.TypedArrayUtils.getNamedBoolean(r5, r6, r7, r8, r9)
            r4.mReparent = r5
            r4 = r3
            r4.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.ChangeTransform.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public boolean getReparentWithOverlay() {
        return this.mUseOverlay;
    }

    public void setReparentWithOverlay(boolean reparentWithOverlay) {
        boolean z = reparentWithOverlay;
        this.mUseOverlay = z;
    }

    public boolean getReparent() {
        return this.mReparent;
    }

    public void setReparent(boolean reparent) {
        boolean z = reparent;
        this.mReparent = z;
    }

    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    private void captureValues(TransitionValues transitionValues) {
        Object obj;
        Matrix matrix;
        Matrix matrix2;
        Matrix matrix3;
        TransitionValues transitionValues2 = transitionValues;
        View view = transitionValues2.view;
        if (view.getVisibility() != 8) {
            Object put = transitionValues2.values.put(PROPNAME_PARENT, view.getParent());
            new Transforms(view);
            Object put2 = transitionValues2.values.put(PROPNAME_TRANSFORMS, obj);
            Matrix matrix4 = view.getMatrix();
            if (matrix4 == null || matrix4.isIdentity()) {
                matrix = null;
            } else {
                new Matrix(matrix4);
                matrix = matrix3;
            }
            Object put3 = transitionValues2.values.put(PROPNAME_MATRIX, matrix);
            if (this.mReparent) {
                new Matrix();
                Matrix parentMatrix = matrix2;
                ViewGroup parent = (ViewGroup) view.getParent();
                ViewUtils.transformMatrixToGlobal(parent, parentMatrix);
                boolean preTranslate = parentMatrix.preTranslate((float) (-parent.getScrollX()), (float) (-parent.getScrollY()));
                Object put4 = transitionValues2.values.put(PROPNAME_PARENT_MATRIX, parentMatrix);
                Object put5 = transitionValues2.values.put(PROPNAME_INTERMEDIATE_MATRIX, view.getTag(C0211R.C0213id.transition_transform));
                Object put6 = transitionValues2.values.put(PROPNAME_INTERMEDIATE_PARENT_MATRIX, view.getTag(C0211R.C0213id.parent_matrix));
            }
        }
    }

    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        TransitionValues transitionValues2 = transitionValues;
        captureValues(transitionValues2);
        if (!SUPPORTS_VIEW_REMOVAL_SUPPRESSION) {
            ((ViewGroup) transitionValues2.view.getParent()).startViewTransition(transitionValues2.view);
        }
    }

    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public Animator createAnimator(@NonNull ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        ViewGroup sceneRoot = viewGroup;
        TransitionValues startValues = transitionValues;
        TransitionValues endValues = transitionValues2;
        if (startValues == null || endValues == null || !startValues.values.containsKey(PROPNAME_PARENT) || !endValues.values.containsKey(PROPNAME_PARENT)) {
            return null;
        }
        ViewGroup startParent = (ViewGroup) startValues.values.get(PROPNAME_PARENT);
        boolean handleParentChange = this.mReparent && !parentsMatch(startParent, (ViewGroup) endValues.values.get(PROPNAME_PARENT));
        Matrix startMatrix = (Matrix) startValues.values.get(PROPNAME_INTERMEDIATE_MATRIX);
        if (startMatrix != null) {
            Object put = startValues.values.put(PROPNAME_MATRIX, startMatrix);
        }
        Matrix startParentMatrix = (Matrix) startValues.values.get(PROPNAME_INTERMEDIATE_PARENT_MATRIX);
        if (startParentMatrix != null) {
            Object put2 = startValues.values.put(PROPNAME_PARENT_MATRIX, startParentMatrix);
        }
        if (handleParentChange) {
            setMatricesForParent(startValues, endValues);
        }
        ObjectAnimator transformAnimator = createTransformAnimator(startValues, endValues, handleParentChange);
        if (handleParentChange && transformAnimator != null && this.mUseOverlay) {
            createGhostView(sceneRoot, startValues, endValues);
        } else if (!SUPPORTS_VIEW_REMOVAL_SUPPRESSION) {
            startParent.endViewTransition(startValues.view);
        }
        return transformAnimator;
    }

    private ObjectAnimator createTransformAnimator(TransitionValues startValues, TransitionValues transitionValues, boolean z) {
        PathAnimatorMatrix pathAnimatorMatrix;
        TypeEvaluator typeEvaluator;
        AnimatorListenerAdapter animatorListenerAdapter;
        TransitionValues endValues = transitionValues;
        boolean handleParentChange = z;
        Matrix startMatrix = (Matrix) startValues.values.get(PROPNAME_MATRIX);
        Matrix endMatrix = (Matrix) endValues.values.get(PROPNAME_MATRIX);
        if (startMatrix == null) {
            startMatrix = MatrixUtils.IDENTITY_MATRIX;
        }
        if (endMatrix == null) {
            endMatrix = MatrixUtils.IDENTITY_MATRIX;
        }
        if (startMatrix.equals(endMatrix)) {
            return null;
        }
        Transforms transforms = (Transforms) endValues.values.get(PROPNAME_TRANSFORMS);
        View view = endValues.view;
        setIdentityTransforms(view);
        float[] startMatrixValues = new float[9];
        startMatrix.getValues(startMatrixValues);
        float[] endMatrixValues = new float[9];
        endMatrix.getValues(endMatrixValues);
        new PathAnimatorMatrix(view, startMatrixValues);
        PathAnimatorMatrix pathAnimatorMatrix2 = pathAnimatorMatrix;
        Property<PathAnimatorMatrix, float[]> property = NON_TRANSLATIONS_PROPERTY;
        TypeEvaluator typeEvaluator2 = typeEvaluator;
        new FloatArrayEvaluator(new float[9]);
        float[][] fArr = new float[2][];
        fArr[0] = startMatrixValues;
        float[][] fArr2 = fArr;
        fArr2[1] = endMatrixValues;
        PropertyValuesHolder valuesProperty = PropertyValuesHolder.ofObject(property, typeEvaluator2, fArr2);
        PropertyValuesHolder translationProperty = PropertyValuesHolderUtils.ofPointF(TRANSLATIONS_PROPERTY, getPathMotion().getPath(startMatrixValues[2], startMatrixValues[5], endMatrixValues[2], endMatrixValues[5]));
        PropertyValuesHolder[] propertyValuesHolderArr = new PropertyValuesHolder[2];
        propertyValuesHolderArr[0] = valuesProperty;
        PropertyValuesHolder[] propertyValuesHolderArr2 = propertyValuesHolderArr;
        propertyValuesHolderArr2[1] = translationProperty;
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(pathAnimatorMatrix2, propertyValuesHolderArr2);
        final boolean z2 = handleParentChange;
        final Matrix matrix = endMatrix;
        final View view2 = view;
        final Transforms transforms2 = transforms;
        final PathAnimatorMatrix pathAnimatorMatrix3 = pathAnimatorMatrix2;
        new AnimatorListenerAdapter(this) {
            private boolean mIsCanceled;
            private Matrix mTempMatrix;
            final /* synthetic */ ChangeTransform this$0;

            {
                Matrix matrix;
                this.this$0 = this$0;
                new Matrix();
                this.mTempMatrix = matrix;
            }

            public void onAnimationCancel(Animator animator) {
                Animator animator2 = animator;
                this.mIsCanceled = true;
            }

            public void onAnimationEnd(Animator animator) {
                Animator animator2 = animator;
                if (!this.mIsCanceled) {
                    if (!z2 || !this.this$0.mUseOverlay) {
                        view2.setTag(C0211R.C0213id.transition_transform, (Object) null);
                        view2.setTag(C0211R.C0213id.parent_matrix, (Object) null);
                    } else {
                        setCurrentMatrix(matrix);
                    }
                }
                ViewUtils.setAnimationMatrix(view2, (Matrix) null);
                transforms2.restore(view2);
            }

            public void onAnimationPause(Animator animator) {
                Animator animator2 = animator;
                setCurrentMatrix(pathAnimatorMatrix3.getMatrix());
            }

            public void onAnimationResume(Animator animator) {
                Animator animator2 = animator;
                ChangeTransform.setIdentityTransforms(view2);
            }

            private void setCurrentMatrix(Matrix currentMatrix) {
                this.mTempMatrix.set(currentMatrix);
                view2.setTag(C0211R.C0213id.transition_transform, this.mTempMatrix);
                transforms2.restore(view2);
            }
        };
        AnimatorListenerAdapter listener = animatorListenerAdapter;
        animator.addListener(listener);
        AnimatorUtils.addPauseListener(animator, listener);
        return animator;
    }

    private boolean parentsMatch(ViewGroup viewGroup, ViewGroup viewGroup2) {
        ViewGroup startParent = viewGroup;
        ViewGroup endParent = viewGroup2;
        boolean parentsMatch = false;
        if (!isValidTarget(startParent) || !isValidTarget(endParent)) {
            parentsMatch = startParent == endParent;
        } else {
            TransitionValues endValues = getMatchedTransitionValues(startParent, true);
            if (endValues != null) {
                parentsMatch = endParent == endValues.view;
            }
        }
        return parentsMatch;
    }

    private void createGhostView(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        Matrix matrix;
        Transition outerTransition;
        Transition.TransitionListener transitionListener;
        ViewGroup sceneRoot = viewGroup;
        TransitionValues startValues = transitionValues;
        TransitionValues endValues = transitionValues2;
        View view = endValues.view;
        new Matrix((Matrix) endValues.values.get(PROPNAME_PARENT_MATRIX));
        Matrix localEndMatrix = matrix;
        ViewUtils.transformMatrixToLocal(sceneRoot, localEndMatrix);
        GhostViewImpl ghostView = GhostViewUtils.addGhost(view, sceneRoot, localEndMatrix);
        if (ghostView != null) {
            ghostView.reserveEndViewTransition((ViewGroup) startValues.values.get(PROPNAME_PARENT), startValues.view);
            Transition transition = this;
            while (true) {
                outerTransition = transition;
                if (outerTransition.mParent == null) {
                    break;
                }
                transition = outerTransition.mParent;
            }
            new GhostListener(view, ghostView);
            Transition addListener = outerTransition.addListener(transitionListener);
            if (SUPPORTS_VIEW_REMOVAL_SUPPRESSION) {
                if (startValues.view != endValues.view) {
                    ViewUtils.setTransitionAlpha(startValues.view, 0.0f);
                }
                ViewUtils.setTransitionAlpha(view, 1.0f);
            }
        }
    }

    private void setMatricesForParent(TransitionValues transitionValues, TransitionValues transitionValues2) {
        Matrix matrix;
        TransitionValues startValues = transitionValues;
        TransitionValues endValues = transitionValues2;
        Matrix endParentMatrix = (Matrix) endValues.values.get(PROPNAME_PARENT_MATRIX);
        endValues.view.setTag(C0211R.C0213id.parent_matrix, endParentMatrix);
        Matrix toLocal = this.mTempMatrix;
        toLocal.reset();
        boolean invert = endParentMatrix.invert(toLocal);
        Matrix startLocal = (Matrix) startValues.values.get(PROPNAME_MATRIX);
        if (startLocal == null) {
            new Matrix();
            startLocal = matrix;
            Object put = startValues.values.put(PROPNAME_MATRIX, startLocal);
        }
        boolean postConcat = startLocal.postConcat((Matrix) startValues.values.get(PROPNAME_PARENT_MATRIX));
        boolean postConcat2 = startLocal.postConcat(toLocal);
    }

    static void setIdentityTransforms(View view) {
        setTransforms(view, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f);
    }

    static void setTransforms(View view, float translationX, float translationY, float translationZ, float scaleX, float scaleY, float rotationX, float rotationY, float rotationZ) {
        View view2 = view;
        view2.setTranslationX(translationX);
        view2.setTranslationY(translationY);
        ViewCompat.setTranslationZ(view2, translationZ);
        view2.setScaleX(scaleX);
        view2.setScaleY(scaleY);
        view2.setRotationX(rotationX);
        view2.setRotationY(rotationY);
        view2.setRotation(rotationZ);
    }

    private static class Transforms {
        final float mRotationX;
        final float mRotationY;
        final float mRotationZ;
        final float mScaleX;
        final float mScaleY;
        final float mTranslationX;
        final float mTranslationY;
        final float mTranslationZ;

        Transforms(View view) {
            View view2 = view;
            this.mTranslationX = view2.getTranslationX();
            this.mTranslationY = view2.getTranslationY();
            this.mTranslationZ = ViewCompat.getTranslationZ(view2);
            this.mScaleX = view2.getScaleX();
            this.mScaleY = view2.getScaleY();
            this.mRotationX = view2.getRotationX();
            this.mRotationY = view2.getRotationY();
            this.mRotationZ = view2.getRotation();
        }

        public void restore(View view) {
            ChangeTransform.setTransforms(view, this.mTranslationX, this.mTranslationY, this.mTranslationZ, this.mScaleX, this.mScaleY, this.mRotationX, this.mRotationY, this.mRotationZ);
        }

        public boolean equals(Object obj) {
            Object that = obj;
            if (!(that instanceof Transforms)) {
                return false;
            }
            Transforms thatTransform = (Transforms) that;
            return thatTransform.mTranslationX == this.mTranslationX && thatTransform.mTranslationY == this.mTranslationY && thatTransform.mTranslationZ == this.mTranslationZ && thatTransform.mScaleX == this.mScaleX && thatTransform.mScaleY == this.mScaleY && thatTransform.mRotationX == this.mRotationX && thatTransform.mRotationY == this.mRotationY && thatTransform.mRotationZ == this.mRotationZ;
        }

        public int hashCode() {
            return (31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * (this.mTranslationX != 0.0f ? Float.floatToIntBits(this.mTranslationX) : 0)) + (this.mTranslationY != 0.0f ? Float.floatToIntBits(this.mTranslationY) : 0))) + (this.mTranslationZ != 0.0f ? Float.floatToIntBits(this.mTranslationZ) : 0))) + (this.mScaleX != 0.0f ? Float.floatToIntBits(this.mScaleX) : 0))) + (this.mScaleY != 0.0f ? Float.floatToIntBits(this.mScaleY) : 0))) + (this.mRotationX != 0.0f ? Float.floatToIntBits(this.mRotationX) : 0))) + (this.mRotationY != 0.0f ? Float.floatToIntBits(this.mRotationY) : 0))) + (this.mRotationZ != 0.0f ? Float.floatToIntBits(this.mRotationZ) : 0);
        }
    }

    private static class GhostListener extends TransitionListenerAdapter {
        private GhostViewImpl mGhostView;
        private View mView;

        GhostListener(View view, GhostViewImpl ghostView) {
            this.mView = view;
            this.mGhostView = ghostView;
        }

        public void onTransitionEnd(@NonNull Transition transition) {
            Transition removeListener = transition.removeListener(this);
            GhostViewUtils.removeGhost(this.mView);
            this.mView.setTag(C0211R.C0213id.transition_transform, (Object) null);
            this.mView.setTag(C0211R.C0213id.parent_matrix, (Object) null);
        }

        public void onTransitionPause(@NonNull Transition transition) {
            Transition transition2 = transition;
            this.mGhostView.setVisibility(4);
        }

        public void onTransitionResume(@NonNull Transition transition) {
            Transition transition2 = transition;
            this.mGhostView.setVisibility(0);
        }
    }

    private static class PathAnimatorMatrix {
        private final Matrix mMatrix;
        private float mTranslationX = this.mValues[2];
        private float mTranslationY = this.mValues[5];
        private final float[] mValues;
        private final View mView;

        PathAnimatorMatrix(View view, float[] values) {
            Matrix matrix;
            new Matrix();
            this.mMatrix = matrix;
            this.mView = view;
            this.mValues = (float[]) values.clone();
            setAnimationMatrix();
        }

        /* access modifiers changed from: package-private */
        public void setValues(float[] fArr) {
            float[] values = fArr;
            System.arraycopy(values, 0, this.mValues, 0, values.length);
            setAnimationMatrix();
        }

        /* access modifiers changed from: package-private */
        public void setTranslation(PointF pointF) {
            PointF translation = pointF;
            this.mTranslationX = translation.x;
            this.mTranslationY = translation.y;
            setAnimationMatrix();
        }

        private void setAnimationMatrix() {
            this.mValues[2] = this.mTranslationX;
            this.mValues[5] = this.mTranslationY;
            this.mMatrix.setValues(this.mValues);
            ViewUtils.setAnimationMatrix(this.mView, this.mMatrix);
        }

        /* access modifiers changed from: package-private */
        public Matrix getMatrix() {
            return this.mMatrix;
        }
    }
}
