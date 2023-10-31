package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.util.Property;
import android.view.View;

class TranslationAnimationCreator {
    static Animator createAnimation(View view, TransitionValues transitionValues, int i, int i2, float f, float f2, float f3, float f4, TimeInterpolator timeInterpolator) {
        TransitionPositionListener transitionPositionListener;
        View view2 = view;
        TransitionValues values = transitionValues;
        int viewPosX = i;
        int viewPosY = i2;
        float startX = f;
        float startY = f2;
        float endX = f3;
        float endY = f4;
        TimeInterpolator interpolator = timeInterpolator;
        float terminalX = view2.getTranslationX();
        float terminalY = view2.getTranslationY();
        int[] startPosition = (int[]) values.view.getTag(C0211R.C0213id.transition_position);
        if (startPosition != null) {
            startX = ((float) (startPosition[0] - viewPosX)) + terminalX;
            startY = ((float) (startPosition[1] - viewPosY)) + terminalY;
        }
        int startPosX = viewPosX + Math.round(startX - terminalX);
        int startPosY = viewPosY + Math.round(startY - terminalY);
        view2.setTranslationX(startX);
        view2.setTranslationY(startY);
        if (startX == endX && startY == endY) {
            return null;
        }
        PropertyValuesHolder[] propertyValuesHolderArr = new PropertyValuesHolder[2];
        PropertyValuesHolder[] propertyValuesHolderArr2 = propertyValuesHolderArr;
        PropertyValuesHolder[] propertyValuesHolderArr3 = propertyValuesHolderArr;
        Property property = View.TRANSLATION_X;
        float[] fArr = new float[2];
        fArr[0] = startX;
        float[] fArr2 = fArr;
        fArr2[1] = endX;
        propertyValuesHolderArr3[0] = PropertyValuesHolder.ofFloat(property, fArr2);
        PropertyValuesHolder[] propertyValuesHolderArr4 = propertyValuesHolderArr2;
        PropertyValuesHolder[] propertyValuesHolderArr5 = propertyValuesHolderArr4;
        PropertyValuesHolder[] propertyValuesHolderArr6 = propertyValuesHolderArr4;
        Property property2 = View.TRANSLATION_Y;
        float[] fArr3 = new float[2];
        fArr3[0] = startY;
        float[] fArr4 = fArr3;
        fArr4[1] = endY;
        propertyValuesHolderArr6[1] = PropertyValuesHolder.ofFloat(property2, fArr4);
        ObjectAnimator anim = ObjectAnimator.ofPropertyValuesHolder(view2, propertyValuesHolderArr5);
        new TransitionPositionListener(view2, values.view, startPosX, startPosY, terminalX, terminalY);
        TransitionPositionListener listener = transitionPositionListener;
        anim.addListener(listener);
        AnimatorUtils.addPauseListener(anim, listener);
        anim.setInterpolator(interpolator);
        return anim;
    }

    private static class TransitionPositionListener extends AnimatorListenerAdapter {
        private final View mMovingView;
        private float mPausedX;
        private float mPausedY;
        private final int mStartX;
        private final int mStartY;
        private final float mTerminalX;
        private final float mTerminalY;
        private int[] mTransitionPosition = ((int[]) this.mViewInHierarchy.getTag(C0211R.C0213id.transition_position));
        private final View mViewInHierarchy;

        TransitionPositionListener(View movingView, View viewInHierarchy, int startX, int startY, float terminalX, float terminalY) {
            this.mMovingView = movingView;
            this.mViewInHierarchy = viewInHierarchy;
            this.mStartX = startX - Math.round(this.mMovingView.getTranslationX());
            this.mStartY = startY - Math.round(this.mMovingView.getTranslationY());
            this.mTerminalX = terminalX;
            this.mTerminalY = terminalY;
            if (this.mTransitionPosition != null) {
                this.mViewInHierarchy.setTag(C0211R.C0213id.transition_position, (Object) null);
            }
        }

        public void onAnimationCancel(Animator animator) {
            Animator animator2 = animator;
            if (this.mTransitionPosition == null) {
                this.mTransitionPosition = new int[2];
            }
            this.mTransitionPosition[0] = Math.round(((float) this.mStartX) + this.mMovingView.getTranslationX());
            this.mTransitionPosition[1] = Math.round(((float) this.mStartY) + this.mMovingView.getTranslationY());
            this.mViewInHierarchy.setTag(C0211R.C0213id.transition_position, this.mTransitionPosition);
        }

        public void onAnimationEnd(Animator animator) {
            Animator animator2 = animator;
            this.mMovingView.setTranslationX(this.mTerminalX);
            this.mMovingView.setTranslationY(this.mTerminalY);
        }

        public void onAnimationPause(Animator animator) {
            Animator animator2 = animator;
            this.mPausedX = this.mMovingView.getTranslationX();
            this.mPausedY = this.mMovingView.getTranslationY();
            this.mMovingView.setTranslationX(this.mTerminalX);
            this.mMovingView.setTranslationY(this.mTerminalY);
        }

        public void onAnimationResume(Animator animator) {
            Animator animator2 = animator;
            this.mMovingView.setTranslationX(this.mPausedX);
            this.mMovingView.setTranslationY(this.mPausedY);
        }
    }

    private TranslationAnimationCreator() {
    }
}
