package android.support.constraint;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

public class Constraints extends ViewGroup {
    public static final String TAG = "Constraints";
    ConstraintSet myConstraintSet;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Constraints(Context context) {
        super(context);
        super.setVisibility(8);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Constraints(android.content.Context r7, android.util.AttributeSet r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r1
            r5 = r2
            r3.<init>(r4, r5)
            r3 = r0
            r4 = r2
            r3.init(r4)
            r3 = r0
            r4 = 8
            super.setVisibility(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.Constraints.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Constraints(android.content.Context r9, android.util.AttributeSet r10, int r11) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r11
            r4 = r0
            r5 = r1
            r6 = r2
            r7 = r3
            r4.<init>(r5, r6, r7)
            r4 = r0
            r5 = r2
            r4.init(r5)
            r4 = r0
            r5 = 8
            super.setVisibility(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.Constraints.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        LayoutParams layoutParams;
        new LayoutParams(getContext(), attrs);
        return layoutParams;
    }

    public static class LayoutParams extends ConstraintLayout.LayoutParams {
        public float alpha = 1.0f;
        public boolean applyElevation = false;
        public float elevation = 0.0f;
        public float rotation = 0.0f;
        public float rotationX = 0.0f;
        public float rotationY = 0.0f;
        public float scaleX = 1.0f;
        public float scaleY = 1.0f;
        public float transformPivotX = 0.0f;
        public float transformPivotY = 0.0f;
        public float translationX = 0.0f;
        public float translationY = 0.0f;
        public float translationZ = 0.0f;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(int width, int height) {
            super(width, height);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(LayoutParams source) {
            super((ConstraintLayout.LayoutParams) source);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public LayoutParams(android.content.Context r12, android.util.AttributeSet r13) {
            /*
                r11 = this;
                r0 = r11
                r1 = r12
                r2 = r13
                r7 = r0
                r8 = r1
                r9 = r2
                r7.<init>((android.content.Context) r8, (android.util.AttributeSet) r9)
                r7 = r0
                r8 = 1065353216(0x3f800000, float:1.0)
                r7.alpha = r8
                r7 = r0
                r8 = 0
                r7.applyElevation = r8
                r7 = r0
                r8 = 0
                r7.elevation = r8
                r7 = r0
                r8 = 0
                r7.rotation = r8
                r7 = r0
                r8 = 0
                r7.rotationX = r8
                r7 = r0
                r8 = 0
                r7.rotationY = r8
                r7 = r0
                r8 = 1065353216(0x3f800000, float:1.0)
                r7.scaleX = r8
                r7 = r0
                r8 = 1065353216(0x3f800000, float:1.0)
                r7.scaleY = r8
                r7 = r0
                r8 = 0
                r7.transformPivotX = r8
                r7 = r0
                r8 = 0
                r7.transformPivotY = r8
                r7 = r0
                r8 = 0
                r7.translationX = r8
                r7 = r0
                r8 = 0
                r7.translationY = r8
                r7 = r0
                r8 = 0
                r7.translationZ = r8
                r7 = r1
                r8 = r2
                int[] r9 = android.support.constraint.C0025R.styleable.ConstraintSet
                android.content.res.TypedArray r7 = r7.obtainStyledAttributes(r8, r9)
                r3 = r7
                r7 = r3
                int r7 = r7.getIndexCount()
                r4 = r7
                r7 = 0
                r5 = r7
            L_0x0051:
                r7 = r5
                r8 = r4
                if (r7 >= r8) goto L_0x013f
                r7 = r3
                r8 = r5
                int r7 = r7.getIndex(r8)
                r6 = r7
                r7 = r6
                int r8 = android.support.constraint.C0025R.styleable.ConstraintSet_android_alpha
                if (r7 != r8) goto L_0x0070
                r7 = r0
                r8 = r3
                r9 = r6
                r10 = r0
                float r10 = r10.alpha
                float r8 = r8.getFloat(r9, r10)
                r7.alpha = r8
            L_0x006d:
                int r5 = r5 + 1
                goto L_0x0051
            L_0x0070:
                r7 = r6
                int r8 = android.support.constraint.C0025R.styleable.ConstraintSet_android_elevation
                if (r7 != r8) goto L_0x0086
                r7 = r0
                r8 = r3
                r9 = r6
                r10 = r0
                float r10 = r10.elevation
                float r8 = r8.getFloat(r9, r10)
                r7.elevation = r8
                r7 = r0
                r8 = 1
                r7.applyElevation = r8
                goto L_0x006d
            L_0x0086:
                r7 = r6
                int r8 = android.support.constraint.C0025R.styleable.ConstraintSet_android_rotationX
                if (r7 != r8) goto L_0x0098
                r7 = r0
                r8 = r3
                r9 = r6
                r10 = r0
                float r10 = r10.rotationX
                float r8 = r8.getFloat(r9, r10)
                r7.rotationX = r8
                goto L_0x006d
            L_0x0098:
                r7 = r6
                int r8 = android.support.constraint.C0025R.styleable.ConstraintSet_android_rotationY
                if (r7 != r8) goto L_0x00aa
                r7 = r0
                r8 = r3
                r9 = r6
                r10 = r0
                float r10 = r10.rotationY
                float r8 = r8.getFloat(r9, r10)
                r7.rotationY = r8
                goto L_0x006d
            L_0x00aa:
                r7 = r6
                int r8 = android.support.constraint.C0025R.styleable.ConstraintSet_android_rotation
                if (r7 != r8) goto L_0x00bc
                r7 = r0
                r8 = r3
                r9 = r6
                r10 = r0
                float r10 = r10.rotation
                float r8 = r8.getFloat(r9, r10)
                r7.rotation = r8
                goto L_0x006d
            L_0x00bc:
                r7 = r6
                int r8 = android.support.constraint.C0025R.styleable.ConstraintSet_android_scaleX
                if (r7 != r8) goto L_0x00ce
                r7 = r0
                r8 = r3
                r9 = r6
                r10 = r0
                float r10 = r10.scaleX
                float r8 = r8.getFloat(r9, r10)
                r7.scaleX = r8
                goto L_0x006d
            L_0x00ce:
                r7 = r6
                int r8 = android.support.constraint.C0025R.styleable.ConstraintSet_android_scaleY
                if (r7 != r8) goto L_0x00e0
                r7 = r0
                r8 = r3
                r9 = r6
                r10 = r0
                float r10 = r10.scaleY
                float r8 = r8.getFloat(r9, r10)
                r7.scaleY = r8
                goto L_0x006d
            L_0x00e0:
                r7 = r6
                int r8 = android.support.constraint.C0025R.styleable.ConstraintSet_android_transformPivotX
                if (r7 != r8) goto L_0x00f3
                r7 = r0
                r8 = r3
                r9 = r6
                r10 = r0
                float r10 = r10.transformPivotX
                float r8 = r8.getFloat(r9, r10)
                r7.transformPivotX = r8
                goto L_0x006d
            L_0x00f3:
                r7 = r6
                int r8 = android.support.constraint.C0025R.styleable.ConstraintSet_android_transformPivotY
                if (r7 != r8) goto L_0x0106
                r7 = r0
                r8 = r3
                r9 = r6
                r10 = r0
                float r10 = r10.transformPivotY
                float r8 = r8.getFloat(r9, r10)
                r7.transformPivotY = r8
                goto L_0x006d
            L_0x0106:
                r7 = r6
                int r8 = android.support.constraint.C0025R.styleable.ConstraintSet_android_translationX
                if (r7 != r8) goto L_0x0119
                r7 = r0
                r8 = r3
                r9 = r6
                r10 = r0
                float r10 = r10.translationX
                float r8 = r8.getFloat(r9, r10)
                r7.translationX = r8
                goto L_0x006d
            L_0x0119:
                r7 = r6
                int r8 = android.support.constraint.C0025R.styleable.ConstraintSet_android_translationY
                if (r7 != r8) goto L_0x012c
                r7 = r0
                r8 = r3
                r9 = r6
                r10 = r0
                float r10 = r10.translationY
                float r8 = r8.getFloat(r9, r10)
                r7.translationY = r8
                goto L_0x006d
            L_0x012c:
                r7 = r6
                int r8 = android.support.constraint.C0025R.styleable.ConstraintSet_android_translationZ
                if (r7 != r8) goto L_0x006d
                r7 = r0
                r8 = r3
                r9 = r6
                r10 = r0
                float r10 = r10.translationZ
                float r8 = r8.getFloat(r9, r10)
                r7.translationX = r8
                goto L_0x006d
            L_0x013f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.Constraints.LayoutParams.<init>(android.content.Context, android.util.AttributeSet):void");
        }
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        LayoutParams layoutParams;
        new LayoutParams(-2, -2);
        return layoutParams;
    }

    private void init(AttributeSet attributeSet) {
        AttributeSet attributeSet2 = attributeSet;
        int v = Log.v(TAG, " ################# init");
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        ViewGroup.LayoutParams layoutParams;
        new ConstraintLayout.LayoutParams(p);
        return layoutParams;
    }

    public ConstraintSet getConstraintSet() {
        ConstraintSet constraintSet;
        if (this.myConstraintSet == null) {
            new ConstraintSet();
            this.myConstraintSet = constraintSet;
        }
        this.myConstraintSet.clone(this);
        return this.myConstraintSet;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int l, int t, int r, int b) {
    }
}
