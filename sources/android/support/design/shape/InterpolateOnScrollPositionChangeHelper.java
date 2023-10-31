package android.support.design.shape;

import android.support.design.internal.Experimental;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

@Experimental("The shapes API is currently experimental and subject to change")
public class InterpolateOnScrollPositionChangeHelper {
    private final int[] containerLocation = new int[2];
    private ScrollView containingScrollView;
    private MaterialShapeDrawable materialShapeDrawable;
    private final ViewTreeObserver.OnScrollChangedListener scrollChangedListener;
    private final int[] scrollLocation = new int[2];
    private View shapedView;

    public InterpolateOnScrollPositionChangeHelper(View shapedView2, MaterialShapeDrawable materialShapeDrawable2, ScrollView containingScrollView2) {
        ViewTreeObserver.OnScrollChangedListener onScrollChangedListener;
        new ViewTreeObserver.OnScrollChangedListener(this) {
            final /* synthetic */ InterpolateOnScrollPositionChangeHelper this$0;

            {
                this.this$0 = this$0;
            }

            public void onScrollChanged() {
                this.this$0.updateInterpolationForScreenPosition();
            }
        };
        this.scrollChangedListener = onScrollChangedListener;
        this.shapedView = shapedView2;
        this.materialShapeDrawable = materialShapeDrawable2;
        this.containingScrollView = containingScrollView2;
    }

    public void setMaterialShapeDrawable(MaterialShapeDrawable materialShapeDrawable2) {
        MaterialShapeDrawable materialShapeDrawable3 = materialShapeDrawable2;
        this.materialShapeDrawable = materialShapeDrawable3;
    }

    public void setContainingScrollView(ScrollView containingScrollView2) {
        ScrollView scrollView = containingScrollView2;
        this.containingScrollView = scrollView;
    }

    public void startListeningForScrollChanges(ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.addOnScrollChangedListener(this.scrollChangedListener);
    }

    public void stopListeningForScrollChanges(ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.removeOnScrollChangedListener(this.scrollChangedListener);
    }

    public void updateInterpolationForScreenPosition() {
        Throwable th;
        if (this.containingScrollView != null) {
            if (this.containingScrollView.getChildCount() == 0) {
                Throwable th2 = th;
                new IllegalStateException("Scroll bar must contain a child to calculate interpolation.");
                throw th2;
            }
            this.containingScrollView.getLocationInWindow(this.scrollLocation);
            this.containingScrollView.getChildAt(0).getLocationInWindow(this.containerLocation);
            int y = (this.shapedView.getTop() - this.scrollLocation[1]) + this.containerLocation[1];
            int viewHeight = this.shapedView.getHeight();
            int windowHeight = this.containingScrollView.getHeight();
            if (y < 0) {
                this.materialShapeDrawable.setInterpolation(Math.max(0.0f, Math.min(1.0f, 1.0f + (((float) y) / ((float) viewHeight)))));
                this.shapedView.invalidate();
            } else if (y + viewHeight > windowHeight) {
                this.materialShapeDrawable.setInterpolation(Math.max(0.0f, Math.min(1.0f, 1.0f - (((float) ((y + viewHeight) - windowHeight)) / ((float) viewHeight)))));
                this.shapedView.invalidate();
            } else if (this.materialShapeDrawable.getInterpolation() != 1.0f) {
                this.materialShapeDrawable.setInterpolation(1.0f);
                this.shapedView.invalidate();
            }
        }
    }
}
