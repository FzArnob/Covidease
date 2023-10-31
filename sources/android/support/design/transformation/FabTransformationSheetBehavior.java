package android.support.design.transformation;

import android.content.Context;
import android.os.Build;
import android.support.annotation.CallSuper;
import android.support.design.C0064R;
import android.support.design.animation.MotionSpec;
import android.support.design.animation.Positioning;
import android.support.design.transformation.FabTransformationBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.p000v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import java.util.HashMap;
import java.util.Map;

public class FabTransformationSheetBehavior extends FabTransformationBehavior {
    private Map<View, Integer> importantForAccessibilityMap;

    public FabTransformationSheetBehavior() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FabTransformationSheetBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /* access modifiers changed from: protected */
    public FabTransformationBehavior.FabTransformationSpec onCreateMotionSpec(Context context, boolean expanded) {
        int specRes;
        FabTransformationBehavior.FabTransformationSpec fabTransformationSpec;
        Positioning positioning;
        Context context2 = context;
        if (expanded) {
            specRes = C0064R.animator.mtrl_fab_transformation_sheet_expand_spec;
        } else {
            specRes = C0064R.animator.mtrl_fab_transformation_sheet_collapse_spec;
        }
        new FabTransformationBehavior.FabTransformationSpec();
        FabTransformationBehavior.FabTransformationSpec spec = fabTransformationSpec;
        spec.timings = MotionSpec.createFromResource(context2, specRes);
        new Positioning(17, 0.0f, 0.0f);
        spec.positioning = positioning;
        return spec;
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public boolean onExpandedStateChange(View dependency, View view, boolean z, boolean animated) {
        View child = view;
        boolean expanded = z;
        updateImportantForAccessibility(child, expanded);
        return super.onExpandedStateChange(dependency, child, expanded, animated);
    }

    private void updateImportantForAccessibility(View view, boolean z) {
        Map<View, Integer> map;
        View sheet = view;
        boolean expanded = z;
        ViewParent viewParent = sheet.getParent();
        if (viewParent instanceof CoordinatorLayout) {
            CoordinatorLayout parent = (CoordinatorLayout) viewParent;
            int childCount = parent.getChildCount();
            if (Build.VERSION.SDK_INT >= 16 && expanded) {
                new HashMap(childCount);
                this.importantForAccessibilityMap = map;
            }
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);
                boolean hasScrimBehavior = (child.getLayoutParams() instanceof CoordinatorLayout.LayoutParams) && (((CoordinatorLayout.LayoutParams) child.getLayoutParams()).getBehavior() instanceof FabTransformationScrimBehavior);
                if (child != sheet && !hasScrimBehavior) {
                    if (expanded) {
                        if (Build.VERSION.SDK_INT >= 16) {
                            Integer put = this.importantForAccessibilityMap.put(child, Integer.valueOf(child.getImportantForAccessibility()));
                        }
                        ViewCompat.setImportantForAccessibility(child, 4);
                    } else if (this.importantForAccessibilityMap != null && this.importantForAccessibilityMap.containsKey(child)) {
                        ViewCompat.setImportantForAccessibility(child, this.importantForAccessibilityMap.get(child).intValue());
                    }
                }
            }
            if (!expanded) {
                this.importantForAccessibilityMap = null;
            }
        }
    }
}
