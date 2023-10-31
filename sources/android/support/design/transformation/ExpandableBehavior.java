package android.support.design.transformation;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.design.expandable.ExpandableWidget;
import android.support.design.widget.CoordinatorLayout;
import android.support.p000v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.util.List;

public abstract class ExpandableBehavior extends CoordinatorLayout.Behavior<View> {
    private static final int STATE_COLLAPSED = 2;
    private static final int STATE_EXPANDED = 1;
    private static final int STATE_UNINITIALIZED = 0;
    /* access modifiers changed from: private */
    public int currentState = 0;

    public abstract boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2);

    /* access modifiers changed from: protected */
    public abstract boolean onExpandedStateChange(View view, View view2, boolean z, boolean z2);

    public ExpandableBehavior() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExpandableBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @CallSuper
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        ViewTreeObserver.OnPreDrawListener onPreDrawListener;
        CoordinatorLayout parent = coordinatorLayout;
        View child = view;
        int i2 = i;
        if (!ViewCompat.isLaidOut(child)) {
            ExpandableWidget dep = findExpandableWidget(parent, child);
            if (dep != null && didStateChange(dep.isExpanded())) {
                this.currentState = dep.isExpanded() ? 1 : 2;
                final View view2 = child;
                final int i3 = this.currentState;
                final ExpandableWidget expandableWidget = dep;
                new ViewTreeObserver.OnPreDrawListener(this) {
                    final /* synthetic */ ExpandableBehavior this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public boolean onPreDraw() {
                        view2.getViewTreeObserver().removeOnPreDrawListener(this);
                        if (this.this$0.currentState == i3) {
                            boolean onExpandedStateChange = this.this$0.onExpandedStateChange((View) expandableWidget, view2, expandableWidget.isExpanded(), false);
                        }
                        return false;
                    }
                };
                child.getViewTreeObserver().addOnPreDrawListener(onPreDrawListener);
            }
        }
        return false;
    }

    @CallSuper
    public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View dependency) {
        CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
        View child = view;
        ExpandableWidget dep = (ExpandableWidget) dependency;
        if (!didStateChange(dep.isExpanded())) {
            return false;
        }
        this.currentState = dep.isExpanded() ? 1 : 2;
        return onExpandedStateChange((View) dep, child, dep.isExpanded(), true);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public ExpandableWidget findExpandableWidget(CoordinatorLayout coordinatorLayout, View view) {
        CoordinatorLayout parent = coordinatorLayout;
        View child = view;
        List<View> dependencies = parent.getDependencies(child);
        int size = dependencies.size();
        for (int i = 0; i < size; i++) {
            View dependency = dependencies.get(i);
            if (layoutDependsOn(parent, child, dependency)) {
                return (ExpandableWidget) dependency;
            }
        }
        return null;
    }

    private boolean didStateChange(boolean expanded) {
        if (expanded) {
            return this.currentState == 0 || this.currentState == 2;
        }
        return this.currentState == 1;
    }

    public static <T extends ExpandableBehavior> T from(View view, Class<T> cls) {
        Throwable th;
        Throwable th2;
        Class<T> klass = cls;
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (!(params instanceof CoordinatorLayout.LayoutParams)) {
            Throwable th3 = th2;
            new IllegalArgumentException("The view is not a child of CoordinatorLayout");
            throw th3;
        }
        CoordinatorLayout.Behavior<?> behavior = ((CoordinatorLayout.LayoutParams) params).getBehavior();
        if (behavior instanceof ExpandableBehavior) {
            return (ExpandableBehavior) klass.cast(behavior);
        }
        Throwable th4 = th;
        new IllegalArgumentException("The view is not associated with ExpandableBehavior");
        throw th4;
    }
}
