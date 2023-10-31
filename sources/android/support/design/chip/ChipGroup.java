package android.support.design.chip;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.BoolRes;
import android.support.annotation.DimenRes;
import android.support.annotation.Dimension;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.C0064R;
import android.support.design.internal.FlowLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

public class ChipGroup extends FlowLayout {
    /* access modifiers changed from: private */
    @IdRes
    public int checkedId;
    /* access modifiers changed from: private */
    public final CheckedStateTracker checkedStateTracker;
    @Dimension
    private int chipSpacingHorizontal;
    @Dimension
    private int chipSpacingVertical;
    @Nullable
    private OnCheckedChangeListener onCheckedChangeListener;
    private PassThroughHierarchyChangeListener passThroughListener;
    /* access modifiers changed from: private */
    public boolean protectFromCheckedChange;
    /* access modifiers changed from: private */
    public boolean singleSelection;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(ChipGroup chipGroup, @IdRes int i);
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(int width, int height) {
            super(width, height);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ChipGroup(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ChipGroup(Context context, AttributeSet attrs) {
        this(context, attrs, C0064R.attr.chipGroupStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ChipGroup(android.content.Context r15, android.util.AttributeSet r16, int r17) {
        /*
            r14 = this;
            r0 = r14
            r1 = r15
            r2 = r16
            r3 = r17
            r7 = r0
            r8 = r1
            r9 = r2
            r10 = r3
            r7.<init>(r8, r9, r10)
            r7 = r0
            android.support.design.chip.ChipGroup$CheckedStateTracker r8 = new android.support.design.chip.ChipGroup$CheckedStateTracker
            r13 = r8
            r8 = r13
            r9 = r13
            r10 = r0
            r11 = 0
            r9.<init>(r10, r11)
            r7.checkedStateTracker = r8
            r7 = r0
            android.support.design.chip.ChipGroup$PassThroughHierarchyChangeListener r8 = new android.support.design.chip.ChipGroup$PassThroughHierarchyChangeListener
            r13 = r8
            r8 = r13
            r9 = r13
            r10 = r0
            r11 = 0
            r9.<init>(r10, r11)
            r7.passThroughListener = r8
            r7 = r0
            r8 = -1
            r7.checkedId = r8
            r7 = r0
            r8 = 0
            r7.protectFromCheckedChange = r8
            r7 = r1
            r8 = r2
            int[] r9 = android.support.design.C0064R.styleable.ChipGroup
            r10 = r3
            int r11 = android.support.design.C0064R.C0068style.Widget_MaterialComponents_ChipGroup
            r12 = 0
            int[] r12 = new int[r12]
            android.content.res.TypedArray r7 = android.support.design.internal.ThemeEnforcement.obtainStyledAttributes(r7, r8, r9, r10, r11, r12)
            r4 = r7
            r7 = r4
            int r8 = android.support.design.C0064R.styleable.ChipGroup_chipSpacing
            r9 = 0
            int r7 = r7.getDimensionPixelOffset(r8, r9)
            r5 = r7
            r7 = r0
            r8 = r4
            int r9 = android.support.design.C0064R.styleable.ChipGroup_chipSpacingHorizontal
            r10 = r5
            int r8 = r8.getDimensionPixelOffset(r9, r10)
            r7.setChipSpacingHorizontal(r8)
            r7 = r0
            r8 = r4
            int r9 = android.support.design.C0064R.styleable.ChipGroup_chipSpacingVertical
            r10 = r5
            int r8 = r8.getDimensionPixelOffset(r9, r10)
            r7.setChipSpacingVertical(r8)
            r7 = r0
            r8 = r4
            int r9 = android.support.design.C0064R.styleable.ChipGroup_singleLine
            r10 = 0
            boolean r8 = r8.getBoolean(r9, r10)
            r7.setSingleLine(r8)
            r7 = r0
            r8 = r4
            int r9 = android.support.design.C0064R.styleable.ChipGroup_singleSelection
            r10 = 0
            boolean r8 = r8.getBoolean(r9, r10)
            r7.setSingleSelection((boolean) r8)
            r7 = r4
            int r8 = android.support.design.C0064R.styleable.ChipGroup_checkedChip
            r9 = -1
            int r7 = r7.getResourceId(r8, r9)
            r6 = r7
            r7 = r6
            r8 = -1
            if (r7 == r8) goto L_0x0088
            r7 = r0
            r8 = r6
            r7.checkedId = r8
        L_0x0088:
            r7 = r4
            r7.recycle()
            r7 = r0
            r8 = r0
            android.support.design.chip.ChipGroup$PassThroughHierarchyChangeListener r8 = r8.passThroughListener
            super.setOnHierarchyChangeListener(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.chip.ChipGroup.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        ViewGroup.LayoutParams layoutParams;
        new LayoutParams(getContext(), attrs);
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        ViewGroup.LayoutParams layoutParams;
        new LayoutParams(lp);
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        ViewGroup.LayoutParams layoutParams;
        new LayoutParams(-2, -2);
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        ViewGroup.LayoutParams p = layoutParams;
        return super.checkLayoutParams(p) && (p instanceof LayoutParams);
    }

    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener listener) {
        ViewGroup.OnHierarchyChangeListener access$202 = PassThroughHierarchyChangeListener.access$202(this.passThroughListener, listener);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        if (this.checkedId != -1) {
            setCheckedStateForView(this.checkedId, true);
            setCheckedId(this.checkedId);
        }
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        View child = view;
        int index = i;
        ViewGroup.LayoutParams params = layoutParams;
        if (child instanceof Chip) {
            Chip chip = (Chip) child;
            if (chip.isChecked()) {
                if (this.checkedId != -1 && this.singleSelection) {
                    setCheckedStateForView(this.checkedId, false);
                }
                setCheckedId(chip.getId());
            }
        }
        super.addView(child, index, params);
    }

    @Deprecated
    public void setDividerDrawableHorizontal(Drawable drawable) {
        Throwable th;
        Drawable drawable2 = drawable;
        Throwable th2 = th;
        new UnsupportedOperationException("Changing divider drawables have no effect. ChipGroup do not use divider drawables as spacing.");
        throw th2;
    }

    @Deprecated
    public void setDividerDrawableVertical(@Nullable Drawable drawable) {
        Throwable th;
        Drawable drawable2 = drawable;
        Throwable th2 = th;
        new UnsupportedOperationException("Changing divider drawables have no effect. ChipGroup do not use divider drawables as spacing.");
        throw th2;
    }

    @Deprecated
    public void setShowDividerHorizontal(int i) {
        Throwable th;
        int i2 = i;
        Throwable th2 = th;
        new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
        throw th2;
    }

    @Deprecated
    public void setShowDividerVertical(int i) {
        Throwable th;
        int i2 = i;
        Throwable th2 = th;
        new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
        throw th2;
    }

    @Deprecated
    public void setFlexWrap(int i) {
        Throwable th;
        int i2 = i;
        Throwable th2 = th;
        new UnsupportedOperationException("Changing flex wrap not allowed. ChipGroup exposes a singleLine attribute instead.");
        throw th2;
    }

    public void check(@IdRes int i) {
        int id = i;
        if (id != this.checkedId) {
            if (this.checkedId != -1 && this.singleSelection) {
                setCheckedStateForView(this.checkedId, false);
            }
            if (id != -1) {
                setCheckedStateForView(id, true);
            }
            setCheckedId(id);
        }
    }

    @IdRes
    public int getCheckedChipId() {
        return this.singleSelection ? this.checkedId : -1;
    }

    public void clearCheck() {
        this.protectFromCheckedChange = true;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child instanceof Chip) {
                ((Chip) child).setChecked(false);
            }
        }
        this.protectFromCheckedChange = false;
        setCheckedId(-1);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        OnCheckedChangeListener onCheckedChangeListener2 = listener;
        this.onCheckedChangeListener = onCheckedChangeListener2;
    }

    /* access modifiers changed from: private */
    public void setCheckedId(int i) {
        int checkedId2 = i;
        this.checkedId = checkedId2;
        if (this.onCheckedChangeListener != null && this.singleSelection) {
            this.onCheckedChangeListener.onCheckedChanged(this, checkedId2);
        }
    }

    /* access modifiers changed from: private */
    public void setCheckedStateForView(@IdRes int viewId, boolean z) {
        boolean checked = z;
        View checkedView = findViewById(viewId);
        if (checkedView instanceof Chip) {
            this.protectFromCheckedChange = true;
            ((Chip) checkedView).setChecked(checked);
            this.protectFromCheckedChange = false;
        }
    }

    public void setChipSpacing(@Dimension int i) {
        int chipSpacing = i;
        setChipSpacingHorizontal(chipSpacing);
        setChipSpacingVertical(chipSpacing);
    }

    public void setChipSpacingResource(@DimenRes int id) {
        setChipSpacing(getResources().getDimensionPixelOffset(id));
    }

    @Dimension
    public int getChipSpacingHorizontal() {
        return this.chipSpacingHorizontal;
    }

    public void setChipSpacingHorizontal(@Dimension int i) {
        int chipSpacingHorizontal2 = i;
        if (this.chipSpacingHorizontal != chipSpacingHorizontal2) {
            this.chipSpacingHorizontal = chipSpacingHorizontal2;
            setItemSpacing(chipSpacingHorizontal2);
            requestLayout();
        }
    }

    public void setChipSpacingHorizontalResource(@DimenRes int id) {
        setChipSpacingHorizontal(getResources().getDimensionPixelOffset(id));
    }

    @Dimension
    public int getChipSpacingVertical() {
        return this.chipSpacingVertical;
    }

    public void setChipSpacingVertical(@Dimension int i) {
        int chipSpacingVertical2 = i;
        if (this.chipSpacingVertical != chipSpacingVertical2) {
            this.chipSpacingVertical = chipSpacingVertical2;
            setLineSpacing(chipSpacingVertical2);
            requestLayout();
        }
    }

    public void setChipSpacingVerticalResource(@DimenRes int id) {
        setChipSpacingVertical(getResources().getDimensionPixelOffset(id));
    }

    public void setSingleLine(@BoolRes int id) {
        setSingleLine(getResources().getBoolean(id));
    }

    public boolean isSingleSelection() {
        return this.singleSelection;
    }

    public void setSingleSelection(boolean z) {
        boolean singleSelection2 = z;
        if (this.singleSelection != singleSelection2) {
            this.singleSelection = singleSelection2;
            clearCheck();
        }
    }

    public void setSingleSelection(@BoolRes int id) {
        setSingleSelection(getResources().getBoolean(id));
    }

    private class CheckedStateTracker implements CompoundButton.OnCheckedChangeListener {
        final /* synthetic */ ChipGroup this$0;

        private CheckedStateTracker(ChipGroup chipGroup) {
            this.this$0 = chipGroup;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ CheckedStateTracker(ChipGroup x0, C00811 r7) {
            this(x0);
            C00811 r2 = r7;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            CompoundButton buttonView = compoundButton;
            boolean isChecked = z;
            if (!this.this$0.protectFromCheckedChange) {
                int id = buttonView.getId();
                if (isChecked) {
                    if (!(this.this$0.checkedId == -1 || this.this$0.checkedId == id || !this.this$0.singleSelection)) {
                        this.this$0.setCheckedStateForView(this.this$0.checkedId, false);
                    }
                    this.this$0.setCheckedId(id);
                } else if (this.this$0.checkedId == id) {
                    this.this$0.setCheckedId(-1);
                }
            }
        }
    }

    private class PassThroughHierarchyChangeListener implements ViewGroup.OnHierarchyChangeListener {
        private ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener;
        final /* synthetic */ ChipGroup this$0;

        private PassThroughHierarchyChangeListener(ChipGroup chipGroup) {
            this.this$0 = chipGroup;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ PassThroughHierarchyChangeListener(ChipGroup x0, C00811 r7) {
            this(x0);
            C00811 r2 = r7;
        }

        static /* synthetic */ ViewGroup.OnHierarchyChangeListener access$202(PassThroughHierarchyChangeListener x0, ViewGroup.OnHierarchyChangeListener x1) {
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener2 = x1;
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener3 = onHierarchyChangeListener2;
            x0.onHierarchyChangeListener = onHierarchyChangeListener3;
            return onHierarchyChangeListener2;
        }

        public void onChildViewAdded(View view, View view2) {
            int id;
            View parent = view;
            View child = view2;
            if (parent == this.this$0 && (child instanceof Chip)) {
                if (child.getId() == -1) {
                    if (Build.VERSION.SDK_INT >= 17) {
                        id = View.generateViewId();
                    } else {
                        id = child.hashCode();
                    }
                    child.setId(id);
                }
                ((Chip) child).setOnCheckedChangeListenerInternal(this.this$0.checkedStateTracker);
            }
            if (this.onHierarchyChangeListener != null) {
                this.onHierarchyChangeListener.onChildViewAdded(parent, child);
            }
        }

        public void onChildViewRemoved(View view, View view2) {
            View parent = view;
            View child = view2;
            if (parent == this.this$0 && (child instanceof Chip)) {
                ((Chip) child).setOnCheckedChangeListenerInternal((CompoundButton.OnCheckedChangeListener) null);
            }
            if (this.onHierarchyChangeListener != null) {
                this.onHierarchyChangeListener.onChildViewRemoved(parent, child);
            }
        }
    }
}
