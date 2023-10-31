package android.support.p000v4.view;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: android.support.v4.view.NestedScrollingParentHelper */
public class NestedScrollingParentHelper {
    private int mNestedScrollAxes;
    private final ViewGroup mViewGroup;

    public NestedScrollingParentHelper(@NonNull ViewGroup viewGroup) {
        this.mViewGroup = viewGroup;
    }

    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes) {
        onNestedScrollAccepted(child, target, axes, 0);
    }

    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int axes, int i) {
        View view3 = view;
        View view4 = view2;
        int i2 = i;
        int i3 = axes;
        this.mNestedScrollAxes = i3;
    }

    public int getNestedScrollAxes() {
        return this.mNestedScrollAxes;
    }

    public void onStopNestedScroll(@NonNull View target) {
        onStopNestedScroll(target, 0);
    }

    public void onStopNestedScroll(@NonNull View view, int i) {
        View view2 = view;
        int i2 = i;
        this.mNestedScrollAxes = 0;
    }
}
