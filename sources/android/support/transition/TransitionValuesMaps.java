package android.support.transition;

import android.support.p000v4.util.C1642ArrayMap;
import android.support.p000v4.util.C1647LongSparseArray;
import android.util.SparseArray;
import android.view.View;

class TransitionValuesMaps {
    final SparseArray<View> mIdValues;
    final C1647LongSparseArray<View> mItemIdValues;
    final C1642ArrayMap<String, View> mNameValues;
    final C1642ArrayMap<View, TransitionValues> mViewValues;

    TransitionValuesMaps() {
        C1642ArrayMap<View, TransitionValues> arrayMap;
        SparseArray<View> sparseArray;
        C1647LongSparseArray<View> longSparseArray;
        C1642ArrayMap<String, View> arrayMap2;
        new C1642ArrayMap<>();
        this.mViewValues = arrayMap;
        new SparseArray<>();
        this.mIdValues = sparseArray;
        new C1647LongSparseArray<>();
        this.mItemIdValues = longSparseArray;
        new C1642ArrayMap<>();
        this.mNameValues = arrayMap2;
    }
}
