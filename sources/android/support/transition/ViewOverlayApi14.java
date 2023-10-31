package android.support.transition;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.p000v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

class ViewOverlayApi14 implements ViewOverlayImpl {
    protected OverlayViewGroup mOverlayViewGroup;

    ViewOverlayApi14(Context context, ViewGroup hostView, View requestingView) {
        OverlayViewGroup overlayViewGroup;
        new OverlayViewGroup(context, hostView, requestingView, this);
        this.mOverlayViewGroup = overlayViewGroup;
    }

    static ViewGroup getContentView(View view) {
        View parent = view;
        while (parent != null) {
            if (parent.getId() == 16908290 && (parent instanceof ViewGroup)) {
                return (ViewGroup) parent;
            }
            if (parent.getParent() instanceof ViewGroup) {
                parent = (ViewGroup) parent.getParent();
            }
        }
        return null;
    }

    static ViewOverlayApi14 createFrom(View view) {
        ViewOverlayApi14 viewOverlayApi14;
        View view2 = view;
        ViewGroup contentView = getContentView(view2);
        if (contentView == null) {
            return null;
        }
        int numChildren = contentView.getChildCount();
        for (int i = 0; i < numChildren; i++) {
            View child = contentView.getChildAt(i);
            if (child instanceof OverlayViewGroup) {
                return ((OverlayViewGroup) child).mViewOverlay;
            }
        }
        new ViewGroupOverlayApi14(contentView.getContext(), contentView, view2);
        return viewOverlayApi14;
    }

    /* access modifiers changed from: package-private */
    public ViewGroup getOverlayView() {
        return this.mOverlayViewGroup;
    }

    public void add(@NonNull Drawable drawable) {
        this.mOverlayViewGroup.add(drawable);
    }

    public void clear() {
        this.mOverlayViewGroup.clear();
    }

    public void remove(@NonNull Drawable drawable) {
        this.mOverlayViewGroup.remove(drawable);
    }

    /* access modifiers changed from: package-private */
    public boolean isEmpty() {
        return this.mOverlayViewGroup.isEmpty();
    }

    static class OverlayViewGroup extends ViewGroup {
        static Method sInvalidateChildInParentFastMethod;
        ArrayList<Drawable> mDrawables = null;
        ViewGroup mHostView;
        View mRequestingView;
        ViewOverlayApi14 mViewOverlay;

        static {
            Class<ViewGroup> cls = ViewGroup.class;
            try {
                Class[] clsArr = new Class[3];
                clsArr[0] = Integer.TYPE;
                Class[] clsArr2 = clsArr;
                clsArr2[1] = Integer.TYPE;
                Class[] clsArr3 = clsArr2;
                clsArr3[2] = Rect.class;
                sInvalidateChildInParentFastMethod = cls.getDeclaredMethod("invalidateChildInParentFast", clsArr3);
            } catch (NoSuchMethodException e) {
                NoSuchMethodException noSuchMethodException = e;
            }
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        OverlayViewGroup(Context context, ViewGroup viewGroup, View requestingView, ViewOverlayApi14 viewOverlay) {
            super(context);
            ViewGroup hostView = viewGroup;
            this.mHostView = hostView;
            this.mRequestingView = requestingView;
            setRight(hostView.getWidth());
            setBottom(hostView.getHeight());
            hostView.addView(this);
            this.mViewOverlay = viewOverlay;
        }

        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            MotionEvent motionEvent2 = motionEvent;
            return false;
        }

        public void add(Drawable drawable) {
            ArrayList<Drawable> arrayList;
            Drawable drawable2 = drawable;
            if (this.mDrawables == null) {
                new ArrayList<>();
                this.mDrawables = arrayList;
            }
            if (!this.mDrawables.contains(drawable2)) {
                boolean add = this.mDrawables.add(drawable2);
                invalidate(drawable2.getBounds());
                drawable2.setCallback(this);
            }
        }

        public void remove(Drawable drawable) {
            Drawable drawable2 = drawable;
            if (this.mDrawables != null) {
                boolean remove = this.mDrawables.remove(drawable2);
                invalidate(drawable2.getBounds());
                drawable2.setCallback((Drawable.Callback) null);
            }
        }

        /* access modifiers changed from: protected */
        public boolean verifyDrawable(@NonNull Drawable drawable) {
            Drawable who = drawable;
            return super.verifyDrawable(who) || (this.mDrawables != null && this.mDrawables.contains(who));
        }

        public void add(View view) {
            View child = view;
            if (child.getParent() instanceof ViewGroup) {
                ViewGroup parent = (ViewGroup) child.getParent();
                if (!(parent == this.mHostView || parent.getParent() == null || !ViewCompat.isAttachedToWindow(parent))) {
                    int[] parentLocation = new int[2];
                    int[] hostViewLocation = new int[2];
                    parent.getLocationOnScreen(parentLocation);
                    this.mHostView.getLocationOnScreen(hostViewLocation);
                    ViewCompat.offsetLeftAndRight(child, parentLocation[0] - hostViewLocation[0]);
                    ViewCompat.offsetTopAndBottom(child, parentLocation[1] - hostViewLocation[1]);
                }
                parent.removeView(child);
                if (child.getParent() != null) {
                    parent.removeView(child);
                }
            }
            super.addView(child, getChildCount() - 1);
        }

        public void remove(View view) {
            super.removeView(view);
            if (isEmpty()) {
                this.mHostView.removeView(this);
            }
        }

        public void clear() {
            removeAllViews();
            if (this.mDrawables != null) {
                this.mDrawables.clear();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean isEmpty() {
            return getChildCount() == 0 && (this.mDrawables == null || this.mDrawables.size() == 0);
        }

        public void invalidateDrawable(@NonNull Drawable drawable) {
            invalidate(drawable.getBounds());
        }

        /* access modifiers changed from: protected */
        public void dispatchDraw(Canvas canvas) {
            Rect rect;
            Canvas canvas2 = canvas;
            int[] contentViewLocation = new int[2];
            int[] hostViewLocation = new int[2];
            this.mHostView.getLocationOnScreen(contentViewLocation);
            this.mRequestingView.getLocationOnScreen(hostViewLocation);
            canvas2.translate((float) (hostViewLocation[0] - contentViewLocation[0]), (float) (hostViewLocation[1] - contentViewLocation[1]));
            new Rect(0, 0, this.mRequestingView.getWidth(), this.mRequestingView.getHeight());
            boolean clipRect = canvas2.clipRect(rect);
            super.dispatchDraw(canvas2);
            int numDrawables = this.mDrawables == null ? 0 : this.mDrawables.size();
            for (int i = 0; i < numDrawables; i++) {
                this.mDrawables.get(i).draw(canvas2);
            }
        }

        /* access modifiers changed from: protected */
        public void onLayout(boolean changed, int l, int t, int r, int b) {
        }

        private void getOffset(int[] iArr) {
            int[] offset = iArr;
            int[] contentViewLocation = new int[2];
            int[] hostViewLocation = new int[2];
            this.mHostView.getLocationOnScreen(contentViewLocation);
            this.mRequestingView.getLocationOnScreen(hostViewLocation);
            offset[0] = hostViewLocation[0] - contentViewLocation[0];
            offset[1] = hostViewLocation[1] - contentViewLocation[1];
        }

        public void invalidateChildFast(View view, Rect rect) {
            View child = view;
            Rect dirty = rect;
            if (this.mHostView != null) {
                int left = child.getLeft();
                int top = child.getTop();
                int[] offset = new int[2];
                getOffset(offset);
                dirty.offset(left + offset[0], top + offset[1]);
                this.mHostView.invalidate(dirty);
            }
        }

        /* access modifiers changed from: protected */
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public ViewParent invalidateChildInParentFast(int i, int i2, Rect rect) {
            int left = i;
            int top = i2;
            Rect dirty = rect;
            if ((this.mHostView instanceof ViewGroup) && sInvalidateChildInParentFastMethod != null) {
                try {
                    getOffset(new int[2]);
                    Method method = sInvalidateChildInParentFastMethod;
                    ViewGroup viewGroup = this.mHostView;
                    Object[] objArr = new Object[3];
                    objArr[0] = Integer.valueOf(left);
                    Object[] objArr2 = objArr;
                    objArr2[1] = Integer.valueOf(top);
                    Object[] objArr3 = objArr2;
                    objArr3[2] = dirty;
                    Object invoke = method.invoke(viewGroup, objArr3);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e2) {
                    e2.printStackTrace();
                }
            }
            return null;
        }

        public ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
            int[] location = iArr;
            Rect dirty = rect;
            if (this.mHostView != null) {
                dirty.offset(location[0], location[1]);
                if (this.mHostView instanceof ViewGroup) {
                    location[0] = 0;
                    location[1] = 0;
                    int[] offset = new int[2];
                    getOffset(offset);
                    dirty.offset(offset[0], offset[1]);
                    return super.invalidateChildInParent(location, dirty);
                }
                invalidate(dirty);
            }
            return null;
        }

        static class TouchInterceptor extends View {
            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            TouchInterceptor(Context context) {
                super(context);
            }
        }
    }

    private ViewOverlayApi14() {
    }
}
