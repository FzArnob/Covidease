package android.support.p003v7.widget;

import android.support.p003v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/* renamed from: android.support.v7.widget.ChildHelper */
class ChildHelper {
    private static final boolean DEBUG = false;
    private static final String TAG = "ChildrenHelper";
    final Bucket mBucket;
    final Callback mCallback;
    final List<View> mHiddenViews;

    /* renamed from: android.support.v7.widget.ChildHelper$Callback */
    interface Callback {
        void addView(View view, int i);

        void attachViewToParent(View view, int i, ViewGroup.LayoutParams layoutParams);

        void detachViewFromParent(int i);

        View getChildAt(int i);

        int getChildCount();

        RecyclerView.ViewHolder getChildViewHolder(View view);

        int indexOfChild(View view);

        void onEnteredHiddenState(View view);

        void onLeftHiddenState(View view);

        void removeAllViews();

        void removeViewAt(int i);
    }

    ChildHelper(Callback callback) {
        Bucket bucket;
        List<View> list;
        this.mCallback = callback;
        new Bucket();
        this.mBucket = bucket;
        new ArrayList();
        this.mHiddenViews = list;
    }

    private void hideViewInternal(View view) {
        View child = view;
        boolean add = this.mHiddenViews.add(child);
        this.mCallback.onEnteredHiddenState(child);
    }

    private boolean unhideViewInternal(View view) {
        View child = view;
        if (!this.mHiddenViews.remove(child)) {
            return false;
        }
        this.mCallback.onLeftHiddenState(child);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void addView(View child, boolean hidden) {
        addView(child, -1, hidden);
    }

    /* access modifiers changed from: package-private */
    public void addView(View view, int i, boolean z) {
        int offset;
        View child = view;
        int index = i;
        boolean hidden = z;
        if (index < 0) {
            offset = this.mCallback.getChildCount();
        } else {
            offset = getOffset(index);
        }
        this.mBucket.insert(offset, hidden);
        if (hidden) {
            hideViewInternal(child);
        }
        this.mCallback.addView(child, offset);
    }

    private int getOffset(int i) {
        int index = i;
        if (index < 0) {
            return -1;
        }
        int limit = this.mCallback.getChildCount();
        int i2 = index;
        while (true) {
            int offset = i2;
            if (offset >= limit) {
                return -1;
            }
            int diff = index - (offset - this.mBucket.countOnesBefore(offset));
            if (diff == 0) {
                while (this.mBucket.get(offset)) {
                    offset++;
                }
                return offset;
            }
            i2 = offset + diff;
        }
    }

    /* access modifiers changed from: package-private */
    public void removeView(View view) {
        View view2 = view;
        int index = this.mCallback.indexOfChild(view2);
        if (index >= 0) {
            if (this.mBucket.remove(index)) {
                boolean unhideViewInternal = unhideViewInternal(view2);
            }
            this.mCallback.removeViewAt(index);
        }
    }

    /* access modifiers changed from: package-private */
    public void removeViewAt(int index) {
        int offset = getOffset(index);
        View view = this.mCallback.getChildAt(offset);
        if (view != null) {
            if (this.mBucket.remove(offset)) {
                boolean unhideViewInternal = unhideViewInternal(view);
            }
            this.mCallback.removeViewAt(offset);
        }
    }

    /* access modifiers changed from: package-private */
    public View getChildAt(int index) {
        return this.mCallback.getChildAt(getOffset(index));
    }

    /* access modifiers changed from: package-private */
    public void removeAllViewsUnfiltered() {
        this.mBucket.reset();
        for (int i = this.mHiddenViews.size() - 1; i >= 0; i--) {
            this.mCallback.onLeftHiddenState(this.mHiddenViews.get(i));
            View remove = this.mHiddenViews.remove(i);
        }
        this.mCallback.removeAllViews();
    }

    /* access modifiers changed from: package-private */
    public View findHiddenNonRemovedView(int i) {
        int position = i;
        int count = this.mHiddenViews.size();
        for (int i2 = 0; i2 < count; i2++) {
            View view = this.mHiddenViews.get(i2);
            RecyclerView.ViewHolder holder = this.mCallback.getChildViewHolder(view);
            if (holder.getLayoutPosition() == position && !holder.isInvalid() && !holder.isRemoved()) {
                return view;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void attachViewToParent(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        int offset;
        View child = view;
        int index = i;
        ViewGroup.LayoutParams layoutParams2 = layoutParams;
        boolean hidden = z;
        if (index < 0) {
            offset = this.mCallback.getChildCount();
        } else {
            offset = getOffset(index);
        }
        this.mBucket.insert(offset, hidden);
        if (hidden) {
            hideViewInternal(child);
        }
        this.mCallback.attachViewToParent(child, offset, layoutParams2);
    }

    /* access modifiers changed from: package-private */
    public int getChildCount() {
        return this.mCallback.getChildCount() - this.mHiddenViews.size();
    }

    /* access modifiers changed from: package-private */
    public int getUnfilteredChildCount() {
        return this.mCallback.getChildCount();
    }

    /* access modifiers changed from: package-private */
    public View getUnfilteredChildAt(int index) {
        return this.mCallback.getChildAt(index);
    }

    /* access modifiers changed from: package-private */
    public void detachViewFromParent(int index) {
        int offset = getOffset(index);
        boolean remove = this.mBucket.remove(offset);
        this.mCallback.detachViewFromParent(offset);
    }

    /* access modifiers changed from: package-private */
    public int indexOfChild(View child) {
        int index = this.mCallback.indexOfChild(child);
        if (index == -1) {
            return -1;
        }
        if (this.mBucket.get(index)) {
            return -1;
        }
        return index - this.mBucket.countOnesBefore(index);
    }

    /* access modifiers changed from: package-private */
    public boolean isHidden(View view) {
        return this.mHiddenViews.contains(view);
    }

    /* access modifiers changed from: package-private */
    public void hide(View view) {
        Throwable th;
        StringBuilder sb;
        View view2 = view;
        int offset = this.mCallback.indexOfChild(view2);
        if (offset < 0) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("view is not a child, cannot hide ").append(view2).toString());
            throw th2;
        }
        this.mBucket.set(offset);
        hideViewInternal(view2);
    }

    /* access modifiers changed from: package-private */
    public void unhide(View view) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        View view2 = view;
        int offset = this.mCallback.indexOfChild(view2);
        if (offset < 0) {
            Throwable th3 = th2;
            new StringBuilder();
            new IllegalArgumentException(sb2.append("view is not a child, cannot hide ").append(view2).toString());
            throw th3;
        } else if (!this.mBucket.get(offset)) {
            Throwable th4 = th;
            new StringBuilder();
            new RuntimeException(sb.append("trying to unhide a view that was not hidden").append(view2).toString());
            throw th4;
        } else {
            this.mBucket.clear(offset);
            boolean unhideViewInternal = unhideViewInternal(view2);
        }
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(this.mBucket.toString()).append(", hidden list:").append(this.mHiddenViews.size()).toString();
    }

    /* access modifiers changed from: package-private */
    public boolean removeViewIfHidden(View view) {
        View view2 = view;
        int index = this.mCallback.indexOfChild(view2);
        if (index == -1) {
            if (unhideViewInternal(view2)) {
            }
            return true;
        } else if (!this.mBucket.get(index)) {
            return false;
        } else {
            boolean remove = this.mBucket.remove(index);
            if (!unhideViewInternal(view2)) {
            }
            this.mCallback.removeViewAt(index);
            return true;
        }
    }

    /* renamed from: android.support.v7.widget.ChildHelper$Bucket */
    static class Bucket {
        static final int BITS_PER_WORD = 64;
        static final long LAST_BIT = Long.MIN_VALUE;
        long mData = 0;
        Bucket mNext;

        Bucket() {
        }

        /* access modifiers changed from: package-private */
        public void set(int i) {
            int index = i;
            if (index >= 64) {
                ensureNext();
                this.mNext.set(index - 64);
                return;
            }
            this.mData |= 1 << index;
        }

        private void ensureNext() {
            Bucket bucket;
            if (this.mNext == null) {
                new Bucket();
                this.mNext = bucket;
            }
        }

        /* access modifiers changed from: package-private */
        public void clear(int i) {
            int index = i;
            if (index < 64) {
                this.mData &= (1 << index) ^ -1;
            } else if (this.mNext != null) {
                this.mNext.clear(index - 64);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean get(int i) {
            int index = i;
            if (index >= 64) {
                ensureNext();
                return this.mNext.get(index - 64);
            }
            return (this.mData & (1 << index)) != 0;
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            this.mData = 0;
            if (this.mNext != null) {
                this.mNext.reset();
            }
        }

        /* access modifiers changed from: package-private */
        public void insert(int i, boolean z) {
            int index = i;
            boolean value = z;
            if (index >= 64) {
                ensureNext();
                this.mNext.insert(index - 64, value);
                return;
            }
            boolean lastBit = (this.mData & LAST_BIT) != 0;
            long mask = (1 << index) - 1;
            this.mData = (this.mData & mask) | ((this.mData & (mask ^ -1)) << 1);
            if (value) {
                set(index);
            } else {
                clear(index);
            }
            if (lastBit || this.mNext != null) {
                ensureNext();
                this.mNext.insert(0, lastBit);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean remove(int i) {
            int index = i;
            if (index >= 64) {
                ensureNext();
                return this.mNext.remove(index - 64);
            }
            long mask = 1 << index;
            boolean value = (this.mData & mask) != 0;
            this.mData &= mask ^ -1;
            long mask2 = mask - 1;
            this.mData = (this.mData & mask2) | Long.rotateRight(this.mData & (mask2 ^ -1), 1);
            if (this.mNext != null) {
                if (this.mNext.get(0)) {
                    set(63);
                }
                boolean remove = this.mNext.remove(0);
            }
            return value;
        }

        /* access modifiers changed from: package-private */
        public int countOnesBefore(int i) {
            int index = i;
            if (this.mNext == null) {
                if (index >= 64) {
                    return Long.bitCount(this.mData);
                }
                return Long.bitCount(this.mData & ((1 << index) - 1));
            } else if (index < 64) {
                return Long.bitCount(this.mData & ((1 << index) - 1));
            } else {
                return this.mNext.countOnesBefore(index - 64) + Long.bitCount(this.mData);
            }
        }

        public String toString() {
            StringBuilder sb;
            String sb2;
            if (this.mNext == null) {
                sb2 = Long.toBinaryString(this.mData);
            } else {
                new StringBuilder();
                sb2 = sb.append(this.mNext.toString()).append("xx").append(Long.toBinaryString(this.mData)).toString();
            }
            return sb2;
        }
    }
}
