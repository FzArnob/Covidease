package android.support.p000v4.widget;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* renamed from: android.support.v4.widget.FocusStrategy */
class FocusStrategy {

    /* renamed from: android.support.v4.widget.FocusStrategy$BoundsAdapter */
    public interface BoundsAdapter<T> {
        void obtainBounds(T t, Rect rect);
    }

    /* renamed from: android.support.v4.widget.FocusStrategy$CollectionAdapter */
    public interface CollectionAdapter<T, V> {
        V get(T t, int i);

        int size(T t);
    }

    public static <L, T> T findNextFocusInRelativeDirection(@NonNull L l, @NonNull CollectionAdapter<L, T> collectionAdapter, @NonNull BoundsAdapter<T> boundsAdapter, @Nullable T t, int i, boolean z, boolean z2) {
        ArrayList arrayList;
        Comparator comparator;
        Throwable th;
        L focusables = l;
        CollectionAdapter<L, T> collectionAdapter2 = collectionAdapter;
        BoundsAdapter<T> adapter = boundsAdapter;
        T focused = t;
        int direction = i;
        boolean isLayoutRtl = z;
        boolean wrap = z2;
        int count = collectionAdapter2.size(focusables);
        new ArrayList(count);
        ArrayList arrayList2 = arrayList;
        for (int i2 = 0; i2 < count; i2++) {
            boolean add = arrayList2.add(collectionAdapter2.get(focusables, i2));
        }
        new SequentialComparator(isLayoutRtl, adapter);
        Collections.sort(arrayList2, comparator);
        switch (direction) {
            case 1:
                return getPreviousFocusable(focused, arrayList2, wrap);
            case 2:
                return getNextFocusable(focused, arrayList2, wrap);
            default:
                Throwable th2 = th;
                new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD}.");
                throw th2;
        }
    }

    private static <T> T getNextFocusable(T t, ArrayList<T> arrayList, boolean z) {
        T focused = t;
        ArrayList<T> focusables = arrayList;
        boolean wrap = z;
        int count = focusables.size();
        int position = (focused == null ? -1 : focusables.lastIndexOf(focused)) + 1;
        if (position < count) {
            return focusables.get(position);
        }
        if (!wrap || count <= 0) {
            return null;
        }
        return focusables.get(0);
    }

    private static <T> T getPreviousFocusable(T t, ArrayList<T> arrayList, boolean z) {
        T focused = t;
        ArrayList<T> focusables = arrayList;
        boolean wrap = z;
        int count = focusables.size();
        int position = (focused == null ? count : focusables.indexOf(focused)) - 1;
        if (position >= 0) {
            return focusables.get(position);
        }
        if (!wrap || count <= 0) {
            return null;
        }
        return focusables.get(count - 1);
    }

    /* renamed from: android.support.v4.widget.FocusStrategy$SequentialComparator */
    private static class SequentialComparator<T> implements Comparator<T> {
        private final BoundsAdapter<T> mAdapter;
        private final boolean mIsLayoutRtl;
        private final Rect mTemp1;
        private final Rect mTemp2;

        SequentialComparator(boolean isLayoutRtl, BoundsAdapter<T> adapter) {
            Rect rect;
            Rect rect2;
            new Rect();
            this.mTemp1 = rect;
            new Rect();
            this.mTemp2 = rect2;
            this.mIsLayoutRtl = isLayoutRtl;
            this.mAdapter = adapter;
        }

        public int compare(T first, T second) {
            Rect firstRect = this.mTemp1;
            Rect secondRect = this.mTemp2;
            this.mAdapter.obtainBounds(first, firstRect);
            this.mAdapter.obtainBounds(second, secondRect);
            if (firstRect.top < secondRect.top) {
                return -1;
            }
            if (firstRect.top > secondRect.top) {
                return 1;
            }
            if (firstRect.left < secondRect.left) {
                return this.mIsLayoutRtl ? 1 : -1;
            } else if (firstRect.left > secondRect.left) {
                return this.mIsLayoutRtl ? -1 : 1;
            } else if (firstRect.bottom < secondRect.bottom) {
                return -1;
            } else {
                if (firstRect.bottom > secondRect.bottom) {
                    return 1;
                }
                if (firstRect.right < secondRect.right) {
                    return this.mIsLayoutRtl ? 1 : -1;
                } else if (firstRect.right <= secondRect.right) {
                    return 0;
                } else {
                    return this.mIsLayoutRtl ? -1 : 1;
                }
            }
        }
    }

    public static <L, T> T findNextFocusInAbsoluteDirection(@NonNull L l, @NonNull CollectionAdapter<L, T> collectionAdapter, @NonNull BoundsAdapter<T> boundsAdapter, @Nullable T t, @NonNull Rect rect, int i) {
        Rect rect2;
        Rect rect3;
        Throwable th;
        L focusables = l;
        CollectionAdapter<L, T> collectionAdapter2 = collectionAdapter;
        BoundsAdapter<T> adapter = boundsAdapter;
        T focused = t;
        Rect focusedRect = rect;
        int direction = i;
        new Rect(focusedRect);
        Rect bestCandidateRect = rect2;
        switch (direction) {
            case 17:
                bestCandidateRect.offset(focusedRect.width() + 1, 0);
                break;
            case 33:
                bestCandidateRect.offset(0, focusedRect.height() + 1);
                break;
            case 66:
                bestCandidateRect.offset(-(focusedRect.width() + 1), 0);
                break;
            case 130:
                bestCandidateRect.offset(0, -(focusedRect.height() + 1));
                break;
            default:
                Throwable th2 = th;
                new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                throw th2;
        }
        T closest = null;
        int count = collectionAdapter2.size(focusables);
        new Rect();
        Rect focusableRect = rect3;
        for (int i2 = 0; i2 < count; i2++) {
            T focusable = collectionAdapter2.get(focusables, i2);
            if (focusable != focused) {
                adapter.obtainBounds(focusable, focusableRect);
                if (isBetterCandidate(direction, focusedRect, focusableRect, bestCandidateRect)) {
                    bestCandidateRect.set(focusableRect);
                    closest = focusable;
                }
            }
        }
        return closest;
    }

    private static boolean isBetterCandidate(int i, @NonNull Rect rect, @NonNull Rect rect2, @NonNull Rect rect3) {
        int direction = i;
        Rect source = rect;
        Rect candidate = rect2;
        Rect currentBest = rect3;
        if (!isCandidate(source, candidate, direction)) {
            return false;
        }
        if (!isCandidate(source, currentBest, direction)) {
            return true;
        }
        if (beamBeats(direction, source, candidate, currentBest)) {
            return true;
        }
        if (beamBeats(direction, source, currentBest, candidate)) {
            return false;
        }
        return getWeightedDistanceFor(majorAxisDistance(direction, source, candidate), minorAxisDistance(direction, source, candidate)) < getWeightedDistanceFor(majorAxisDistance(direction, source, currentBest), minorAxisDistance(direction, source, currentBest));
    }

    private static boolean beamBeats(int i, @NonNull Rect rect, @NonNull Rect rect2, @NonNull Rect rect3) {
        int direction = i;
        Rect source = rect;
        Rect rect1 = rect2;
        Rect rect22 = rect3;
        boolean rect1InSrcBeam = beamsOverlap(direction, source, rect1);
        if (beamsOverlap(direction, source, rect22) || !rect1InSrcBeam) {
            return false;
        }
        if (!isToDirectionOf(direction, source, rect22)) {
            return true;
        }
        if (direction == 17 || direction == 66) {
            return true;
        }
        return majorAxisDistance(direction, source, rect1) < majorAxisDistanceToFarEdge(direction, source, rect22);
    }

    private static int getWeightedDistanceFor(int i, int i2) {
        int majorAxisDistance = i;
        int minorAxisDistance = i2;
        return (13 * majorAxisDistance * majorAxisDistance) + (minorAxisDistance * minorAxisDistance);
    }

    private static boolean isCandidate(@NonNull Rect rect, @NonNull Rect rect2, int direction) {
        Throwable th;
        Rect srcRect = rect;
        Rect destRect = rect2;
        switch (direction) {
            case 17:
                return (srcRect.right > destRect.right || srcRect.left >= destRect.right) && srcRect.left > destRect.left;
            case 33:
                return (srcRect.bottom > destRect.bottom || srcRect.top >= destRect.bottom) && srcRect.top > destRect.top;
            case 66:
                return (srcRect.left < destRect.left || srcRect.right <= destRect.left) && srcRect.right < destRect.right;
            case 130:
                return (srcRect.top < destRect.top || srcRect.bottom <= destRect.top) && srcRect.bottom < destRect.bottom;
            default:
                Throwable th2 = th;
                new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                throw th2;
        }
    }

    private static boolean beamsOverlap(int direction, @NonNull Rect rect, @NonNull Rect rect2) {
        Throwable th;
        Rect rect1 = rect;
        Rect rect22 = rect2;
        switch (direction) {
            case 17:
            case 66:
                return rect22.bottom >= rect1.top && rect22.top <= rect1.bottom;
            case 33:
            case 130:
                return rect22.right >= rect1.left && rect22.left <= rect1.right;
            default:
                Throwable th2 = th;
                new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                throw th2;
        }
    }

    private static boolean isToDirectionOf(int direction, @NonNull Rect rect, @NonNull Rect rect2) {
        Throwable th;
        Rect src = rect;
        Rect dest = rect2;
        switch (direction) {
            case 17:
                return src.left >= dest.right;
            case 33:
                return src.top >= dest.bottom;
            case 66:
                return src.right <= dest.left;
            case 130:
                return src.bottom <= dest.top;
            default:
                Throwable th2 = th;
                new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                throw th2;
        }
    }

    private static int majorAxisDistance(int direction, @NonNull Rect source, @NonNull Rect dest) {
        return Math.max(0, majorAxisDistanceRaw(direction, source, dest));
    }

    private static int majorAxisDistanceRaw(int direction, @NonNull Rect rect, @NonNull Rect rect2) {
        Throwable th;
        Rect source = rect;
        Rect dest = rect2;
        switch (direction) {
            case 17:
                return source.left - dest.right;
            case 33:
                return source.top - dest.bottom;
            case 66:
                return dest.left - source.right;
            case 130:
                return dest.top - source.bottom;
            default:
                Throwable th2 = th;
                new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                throw th2;
        }
    }

    private static int majorAxisDistanceToFarEdge(int direction, @NonNull Rect source, @NonNull Rect dest) {
        return Math.max(1, majorAxisDistanceToFarEdgeRaw(direction, source, dest));
    }

    private static int majorAxisDistanceToFarEdgeRaw(int direction, @NonNull Rect rect, @NonNull Rect rect2) {
        Throwable th;
        Rect source = rect;
        Rect dest = rect2;
        switch (direction) {
            case 17:
                return source.left - dest.left;
            case 33:
                return source.top - dest.top;
            case 66:
                return dest.right - source.right;
            case 130:
                return dest.bottom - source.bottom;
            default:
                Throwable th2 = th;
                new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                throw th2;
        }
    }

    private static int minorAxisDistance(int direction, @NonNull Rect rect, @NonNull Rect rect2) {
        Throwable th;
        Rect source = rect;
        Rect dest = rect2;
        switch (direction) {
            case 17:
            case 66:
                return Math.abs((source.top + (source.height() / 2)) - (dest.top + (dest.height() / 2)));
            case 33:
            case 130:
                return Math.abs((source.left + (source.width() / 2)) - (dest.left + (dest.width() / 2)));
            default:
                Throwable th2 = th;
                new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                throw th2;
        }
    }

    private FocusStrategy() {
    }
}
