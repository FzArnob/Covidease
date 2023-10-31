package android.support.p003v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.p003v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/* renamed from: android.support.v7.widget.DividerItemDecoration */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = {16843284};
    public static final int HORIZONTAL = 0;
    private static final String TAG = "DividerItem";
    public static final int VERTICAL = 1;
    private final Rect mBounds;
    private Drawable mDivider;
    private int mOrientation;

    public DividerItemDecoration(Context context, int i) {
        Rect rect;
        int orientation = i;
        new Rect();
        this.mBounds = rect;
        TypedArray a = context.obtainStyledAttributes(ATTRS);
        this.mDivider = a.getDrawable(0);
        if (this.mDivider == null) {
            int w = Log.w(TAG, "@android:attr/listDivider was not set in the theme used for this DividerItemDecoration. Please set that attribute all call setDrawable()");
        }
        a.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int i) {
        Throwable th;
        int orientation = i;
        if (orientation == 0 || orientation == 1) {
            this.mOrientation = orientation;
            return;
        }
        Throwable th2 = th;
        new IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        throw th2;
    }

    public void setDrawable(@NonNull Drawable drawable) {
        Throwable th;
        Drawable drawable2 = drawable;
        if (drawable2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Drawable cannot be null.");
            throw th2;
        }
        this.mDivider = drawable2;
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        Canvas c = canvas;
        RecyclerView parent = recyclerView;
        RecyclerView.State state2 = state;
        if (parent.getLayoutManager() != null && this.mDivider != null) {
            if (this.mOrientation == 1) {
                drawVertical(c, parent);
            } else {
                drawHorizontal(c, parent);
            }
        }
    }

    private void drawVertical(Canvas canvas, RecyclerView recyclerView) {
        int left;
        int right;
        Canvas canvas2 = canvas;
        RecyclerView parent = recyclerView;
        int save = canvas2.save();
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            boolean clipRect = canvas2.clipRect(left, parent.getPaddingTop(), right, parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, this.mBounds);
            int bottom = this.mBounds.bottom + Math.round(child.getTranslationY());
            this.mDivider.setBounds(left, bottom - this.mDivider.getIntrinsicHeight(), right, bottom);
            this.mDivider.draw(canvas2);
        }
        canvas2.restore();
    }

    private void drawHorizontal(Canvas canvas, RecyclerView recyclerView) {
        int top;
        int bottom;
        Canvas canvas2 = canvas;
        RecyclerView parent = recyclerView;
        int save = canvas2.save();
        if (parent.getClipToPadding()) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            boolean clipRect = canvas2.clipRect(parent.getPaddingLeft(), top, parent.getWidth() - parent.getPaddingRight(), bottom);
        } else {
            top = 0;
            bottom = parent.getHeight();
        }
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, this.mBounds);
            int right = this.mBounds.right + Math.round(child.getTranslationX());
            this.mDivider.setBounds(right - this.mDivider.getIntrinsicWidth(), top, right, bottom);
            this.mDivider.draw(canvas2);
        }
        canvas2.restore();
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        Rect outRect = rect;
        View view2 = view;
        RecyclerView recyclerView2 = recyclerView;
        RecyclerView.State state2 = state;
        if (this.mDivider == null) {
            outRect.set(0, 0, 0, 0);
        } else if (this.mOrientation == 1) {
            outRect.set(0, 0, 0, this.mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, this.mDivider.getIntrinsicWidth(), 0);
        }
    }
}
