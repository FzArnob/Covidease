package android.support.p003v7.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.RestrictTo;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.ref.WeakReference;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.widget.ViewStubCompat */
public final class ViewStubCompat extends View {
    private OnInflateListener mInflateListener;
    private int mInflatedId;
    private WeakReference<View> mInflatedViewRef;
    private LayoutInflater mInflater;
    private int mLayoutResource;

    /* renamed from: android.support.v7.widget.ViewStubCompat$OnInflateListener */
    public interface OnInflateListener {
        void onInflate(ViewStubCompat viewStubCompat, View view);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ViewStubCompat(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ViewStubCompat(android.content.Context r11, android.util.AttributeSet r12, int r13) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            r5 = r0
            r6 = r1
            r7 = r2
            r8 = r3
            r5.<init>(r6, r7, r8)
            r5 = r0
            r6 = 0
            r5.mLayoutResource = r6
            r5 = r1
            r6 = r2
            int[] r7 = android.support.p003v7.appcompat.C0425R.styleable.ViewStubCompat
            r8 = r3
            r9 = 0
            android.content.res.TypedArray r5 = r5.obtainStyledAttributes(r6, r7, r8, r9)
            r4 = r5
            r5 = r0
            r6 = r4
            int r7 = android.support.p003v7.appcompat.C0425R.styleable.ViewStubCompat_android_inflatedId
            r8 = -1
            int r6 = r6.getResourceId(r7, r8)
            r5.mInflatedId = r6
            r5 = r0
            r6 = r4
            int r7 = android.support.p003v7.appcompat.C0425R.styleable.ViewStubCompat_android_layout
            r8 = 0
            int r6 = r6.getResourceId(r7, r8)
            r5.mLayoutResource = r6
            r5 = r0
            r6 = r4
            int r7 = android.support.p003v7.appcompat.C0425R.styleable.ViewStubCompat_android_id
            r8 = -1
            int r6 = r6.getResourceId(r7, r8)
            r5.setId(r6)
            r5 = r4
            r5.recycle()
            r5 = r0
            r6 = 8
            r5.setVisibility(r6)
            r5 = r0
            r6 = 1
            r5.setWillNotDraw(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.ViewStubCompat.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public int getInflatedId() {
        return this.mInflatedId;
    }

    public void setInflatedId(int inflatedId) {
        int i = inflatedId;
        this.mInflatedId = i;
    }

    public int getLayoutResource() {
        return this.mLayoutResource;
    }

    public void setLayoutResource(int layoutResource) {
        int i = layoutResource;
        this.mLayoutResource = i;
    }

    public void setLayoutInflater(LayoutInflater inflater) {
        LayoutInflater layoutInflater = inflater;
        this.mInflater = layoutInflater;
    }

    public LayoutInflater getLayoutInflater() {
        return this.mInflater;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        setMeasuredDimension(0, 0);
    }

    @SuppressLint({"MissingSuperCall"})
    public void draw(Canvas canvas) {
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
    }

    public void setVisibility(int i) {
        Throwable th;
        int visibility = i;
        if (this.mInflatedViewRef != null) {
            View view = (View) this.mInflatedViewRef.get();
            if (view != null) {
                view.setVisibility(visibility);
                return;
            }
            Throwable th2 = th;
            new IllegalStateException("setVisibility called on un-referenced view");
            throw th2;
        }
        super.setVisibility(visibility);
        if (visibility == 0 || visibility == 4) {
            View inflate = inflate();
        }
    }

    public View inflate() {
        Throwable th;
        Throwable th2;
        LayoutInflater factory;
        WeakReference<View> weakReference;
        ViewParent viewParent = getParent();
        if (viewParent == null || !(viewParent instanceof ViewGroup)) {
            Throwable th3 = th;
            new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent");
            throw th3;
        } else if (this.mLayoutResource != 0) {
            ViewGroup parent = (ViewGroup) viewParent;
            if (this.mInflater != null) {
                factory = this.mInflater;
            } else {
                factory = LayoutInflater.from(getContext());
            }
            View view = factory.inflate(this.mLayoutResource, parent, false);
            if (this.mInflatedId != -1) {
                view.setId(this.mInflatedId);
            }
            int index = parent.indexOfChild(this);
            parent.removeViewInLayout(this);
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            if (layoutParams != null) {
                parent.addView(view, index, layoutParams);
            } else {
                parent.addView(view, index);
            }
            new WeakReference<>(view);
            this.mInflatedViewRef = weakReference;
            if (this.mInflateListener != null) {
                this.mInflateListener.onInflate(this, view);
            }
            return view;
        } else {
            Throwable th4 = th2;
            new IllegalArgumentException("ViewStub must have a valid layoutResource");
            throw th4;
        }
    }

    public void setOnInflateListener(OnInflateListener inflateListener) {
        OnInflateListener onInflateListener = inflateListener;
        this.mInflateListener = onInflateListener;
    }
}
