package com.bumptech.glide.request.target;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.bumptech.glide.request.Request;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public abstract class ViewTarget<T extends View, Z> extends BaseTarget<Z> {
    private static final String TAG = "ViewTarget";
    private static boolean isTagUsedAtLeastOnce = false;
    private static Integer tagId = null;
    private final SizeDeterminer sizeDeterminer;
    protected final T view;

    public static void setTagId(int i) {
        Throwable th;
        int tagId2 = i;
        if (tagId != null || isTagUsedAtLeastOnce) {
            Throwable th2 = th;
            new IllegalArgumentException("You cannot set the tag id more than once or change the tag id after the first request has been made");
            throw th2;
        }
        tagId = Integer.valueOf(tagId2);
    }

    public ViewTarget(T t) {
        SizeDeterminer sizeDeterminer2;
        Throwable th;
        T view2 = t;
        if (view2 == null) {
            Throwable th2 = th;
            new NullPointerException("View must not be null!");
            throw th2;
        }
        this.view = view2;
        new SizeDeterminer(view2);
        this.sizeDeterminer = sizeDeterminer2;
    }

    public T getView() {
        return this.view;
    }

    public void getSize(SizeReadyCallback cb) {
        this.sizeDeterminer.getSize(cb);
    }

    public void setRequest(Request request) {
        setTag(request);
    }

    public Request getRequest() {
        Throwable th;
        Object tag = getTag();
        Request request = null;
        if (tag != null) {
            if (tag instanceof Request) {
                request = (Request) tag;
            } else {
                Throwable th2 = th;
                new IllegalArgumentException("You must not call setTag() on a view Glide is targeting");
                throw th2;
            }
        }
        return request;
    }

    private void setTag(Object obj) {
        Object tag = obj;
        if (tagId == null) {
            isTagUsedAtLeastOnce = true;
            this.view.setTag(tag);
            return;
        }
        this.view.setTag(tagId.intValue(), tag);
    }

    private Object getTag() {
        if (tagId == null) {
            return this.view.getTag();
        }
        return this.view.getTag(tagId.intValue());
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("Target for: ").append(this.view).toString();
    }

    private static class SizeDeterminer {
        private static final int PENDING_SIZE = 0;
        private final List<SizeReadyCallback> cbs;
        private Point displayDimens;
        private SizeDeterminerLayoutListener layoutListener;
        private final View view;

        public SizeDeterminer(View view2) {
            List<SizeReadyCallback> list;
            new ArrayList();
            this.cbs = list;
            this.view = view2;
        }

        private void notifyCbs(int i, int i2) {
            int width = i;
            int height = i2;
            for (SizeReadyCallback cb : this.cbs) {
                cb.onSizeReady(width, height);
            }
            this.cbs.clear();
        }

        /* access modifiers changed from: private */
        public void checkCurrentDimens() {
            if (!this.cbs.isEmpty()) {
                int currentWidth = getViewWidthOrParam();
                int currentHeight = getViewHeightOrParam();
                if (isSizeValid(currentWidth) && isSizeValid(currentHeight)) {
                    notifyCbs(currentWidth, currentHeight);
                    ViewTreeObserver observer = this.view.getViewTreeObserver();
                    if (observer.isAlive()) {
                        observer.removeOnPreDrawListener(this.layoutListener);
                    }
                    this.layoutListener = null;
                }
            }
        }

        public void getSize(SizeReadyCallback sizeReadyCallback) {
            SizeDeterminerLayoutListener sizeDeterminerLayoutListener;
            SizeReadyCallback cb = sizeReadyCallback;
            int currentWidth = getViewWidthOrParam();
            int currentHeight = getViewHeightOrParam();
            if (!isSizeValid(currentWidth) || !isSizeValid(currentHeight)) {
                if (!this.cbs.contains(cb)) {
                    boolean add = this.cbs.add(cb);
                }
                if (this.layoutListener == null) {
                    ViewTreeObserver observer = this.view.getViewTreeObserver();
                    new SizeDeterminerLayoutListener(this);
                    this.layoutListener = sizeDeterminerLayoutListener;
                    observer.addOnPreDrawListener(this.layoutListener);
                    return;
                }
                return;
            }
            cb.onSizeReady(currentWidth, currentHeight);
        }

        private int getViewHeightOrParam() {
            ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
            if (isSizeValid(this.view.getHeight())) {
                return this.view.getHeight();
            }
            if (layoutParams != null) {
                return getSizeForParam(layoutParams.height, true);
            }
            return 0;
        }

        private int getViewWidthOrParam() {
            ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
            if (isSizeValid(this.view.getWidth())) {
                return this.view.getWidth();
            }
            if (layoutParams != null) {
                return getSizeForParam(layoutParams.width, false);
            }
            return 0;
        }

        private int getSizeForParam(int i, boolean z) {
            int param = i;
            boolean isHeight = z;
            if (param != -2) {
                return param;
            }
            Point displayDimens2 = getDisplayDimens();
            return isHeight ? displayDimens2.y : displayDimens2.x;
        }

        @TargetApi(13)
        private Point getDisplayDimens() {
            Point point;
            Point point2;
            if (this.displayDimens != null) {
                return this.displayDimens;
            }
            Display display = ((WindowManager) this.view.getContext().getSystemService("window")).getDefaultDisplay();
            if (Build.VERSION.SDK_INT >= 13) {
                new Point();
                this.displayDimens = point2;
                display.getSize(this.displayDimens);
            } else {
                new Point(display.getWidth(), display.getHeight());
                this.displayDimens = point;
            }
            return this.displayDimens;
        }

        private boolean isSizeValid(int i) {
            int size = i;
            return size > 0 || size == -2;
        }

        private static class SizeDeterminerLayoutListener implements ViewTreeObserver.OnPreDrawListener {
            private final WeakReference<SizeDeterminer> sizeDeterminerRef;

            public SizeDeterminerLayoutListener(SizeDeterminer sizeDeterminer) {
                WeakReference<SizeDeterminer> weakReference;
                new WeakReference<>(sizeDeterminer);
                this.sizeDeterminerRef = weakReference;
            }

            public boolean onPreDraw() {
                StringBuilder sb;
                if (Log.isLoggable(ViewTarget.TAG, 2)) {
                    new StringBuilder();
                    int v = Log.v(ViewTarget.TAG, sb.append("OnGlobalLayoutListener called listener=").append(this).toString());
                }
                SizeDeterminer sizeDeterminer = (SizeDeterminer) this.sizeDeterminerRef.get();
                if (sizeDeterminer != null) {
                    sizeDeterminer.checkCurrentDimens();
                }
                return true;
            }
        }
    }
}
