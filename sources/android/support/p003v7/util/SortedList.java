package android.support.p003v7.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/* renamed from: android.support.v7.util.SortedList */
public class SortedList<T> {
    private static final int CAPACITY_GROWTH = 10;
    private static final int DELETION = 2;
    private static final int INSERTION = 1;
    public static final int INVALID_POSITION = -1;
    private static final int LOOKUP = 4;
    private static final int MIN_CAPACITY = 10;
    private BatchedCallback mBatchedCallback;
    private Callback mCallback;
    T[] mData;
    private int mNewDataStart;
    private T[] mOldData;
    private int mOldDataSize;
    private int mOldDataStart;
    private int mSize;
    private final Class<T> mTClass;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SortedList(@NonNull Class<T> klass, @NonNull Callback<T> callback) {
        this(klass, callback, 10);
    }

    public SortedList(@NonNull Class<T> cls, @NonNull Callback<T> callback, int initialCapacity) {
        Class<T> klass = cls;
        this.mTClass = klass;
        this.mData = (Object[]) Array.newInstance(klass, initialCapacity);
        this.mCallback = callback;
        this.mSize = 0;
    }

    public int size() {
        return this.mSize;
    }

    public int add(T item) {
        throwIfInMutationOperation();
        return add(item, true);
    }

    public void addAll(@NonNull T[] tArr, boolean z) {
        T[] items = tArr;
        boolean mayModifyInput = z;
        throwIfInMutationOperation();
        if (items.length != 0) {
            if (mayModifyInput) {
                addAllInternal(items);
            } else {
                addAllInternal(copyArray(items));
            }
        }
    }

    public void addAll(@NonNull T... items) {
        addAll(items, false);
    }

    public void addAll(@NonNull Collection<T> collection) {
        Collection<T> items = collection;
        addAll(items.toArray((Object[]) Array.newInstance(this.mTClass, items.size())), true);
    }

    public void replaceAll(@NonNull T[] tArr, boolean mayModifyInput) {
        T[] items = tArr;
        throwIfInMutationOperation();
        if (mayModifyInput) {
            replaceAllInternal(items);
        } else {
            replaceAllInternal(copyArray(items));
        }
    }

    public void replaceAll(@NonNull T... items) {
        replaceAll(items, false);
    }

    public void replaceAll(@NonNull Collection<T> collection) {
        Collection<T> items = collection;
        replaceAll(items.toArray((Object[]) Array.newInstance(this.mTClass, items.size())), true);
    }

    private void addAllInternal(T[] tArr) {
        T[] newItems = tArr;
        if (newItems.length >= 1) {
            int newSize = sortAndDedup(newItems);
            if (this.mSize == 0) {
                this.mData = newItems;
                this.mSize = newSize;
                this.mCallback.onInserted(0, newSize);
                return;
            }
            merge(newItems, newSize);
        }
    }

    private void replaceAllInternal(@NonNull T[] tArr) {
        T[] newData = tArr;
        boolean forceBatchedUpdates = !(this.mCallback instanceof BatchedCallback);
        if (forceBatchedUpdates) {
            beginBatchedUpdates();
        }
        this.mOldDataStart = 0;
        this.mOldDataSize = this.mSize;
        this.mOldData = this.mData;
        this.mNewDataStart = 0;
        int newSize = sortAndDedup(newData);
        this.mData = (Object[]) Array.newInstance(this.mTClass, newSize);
        while (true) {
            if (this.mNewDataStart >= newSize && this.mOldDataStart >= this.mOldDataSize) {
                break;
            } else if (this.mOldDataStart >= this.mOldDataSize) {
                int insertIndex = this.mNewDataStart;
                int itemCount = newSize - this.mNewDataStart;
                System.arraycopy(newData, insertIndex, this.mData, insertIndex, itemCount);
                this.mNewDataStart += itemCount;
                this.mSize += itemCount;
                this.mCallback.onInserted(insertIndex, itemCount);
                break;
            } else if (this.mNewDataStart >= newSize) {
                int itemCount2 = this.mOldDataSize - this.mOldDataStart;
                this.mSize -= itemCount2;
                this.mCallback.onRemoved(this.mNewDataStart, itemCount2);
                break;
            } else {
                T oldItem = this.mOldData[this.mOldDataStart];
                T newItem = newData[this.mNewDataStart];
                int result = this.mCallback.compare(oldItem, newItem);
                if (result < 0) {
                    replaceAllRemove();
                } else if (result > 0) {
                    replaceAllInsert(newItem);
                } else if (!this.mCallback.areItemsTheSame(oldItem, newItem)) {
                    replaceAllRemove();
                    replaceAllInsert(newItem);
                } else {
                    this.mData[this.mNewDataStart] = newItem;
                    this.mOldDataStart++;
                    this.mNewDataStart++;
                    if (!this.mCallback.areContentsTheSame(oldItem, newItem)) {
                        this.mCallback.onChanged(this.mNewDataStart - 1, 1, this.mCallback.getChangePayload(oldItem, newItem));
                    }
                }
            }
        }
        this.mOldData = null;
        if (forceBatchedUpdates) {
            endBatchedUpdates();
        }
    }

    private void replaceAllInsert(T newItem) {
        this.mData[this.mNewDataStart] = newItem;
        this.mNewDataStart++;
        this.mSize++;
        this.mCallback.onInserted(this.mNewDataStart - 1, 1);
    }

    private void replaceAllRemove() {
        this.mSize--;
        this.mOldDataStart++;
        this.mCallback.onRemoved(this.mNewDataStart, 1);
    }

    private int sortAndDedup(@NonNull T[] tArr) {
        T[] items = tArr;
        if (items.length == 0) {
            return 0;
        }
        Arrays.sort(items, this.mCallback);
        int rangeStart = 0;
        int rangeEnd = 1;
        for (int i = 1; i < items.length; i++) {
            T currentItem = items[i];
            if (this.mCallback.compare(items[rangeStart], currentItem) == 0) {
                int sameItemPos = findSameItem(currentItem, items, rangeStart, rangeEnd);
                if (sameItemPos != -1) {
                    items[sameItemPos] = currentItem;
                } else {
                    if (rangeEnd != i) {
                        items[rangeEnd] = currentItem;
                    }
                    rangeEnd++;
                }
            } else {
                if (rangeEnd != i) {
                    items[rangeEnd] = currentItem;
                }
                int i2 = rangeEnd;
                rangeEnd++;
                rangeStart = i2;
            }
        }
        return rangeEnd;
    }

    private int findSameItem(T t, T[] tArr, int from, int i) {
        T item = t;
        T[] items = tArr;
        int to = i;
        for (int pos = from; pos < to; pos++) {
            if (this.mCallback.areItemsTheSame(items[pos], item)) {
                return pos;
            }
        }
        return -1;
    }

    private void merge(T[] tArr, int i) {
        T[] newData = tArr;
        int newDataSize = i;
        boolean forceBatchedUpdates = !(this.mCallback instanceof BatchedCallback);
        if (forceBatchedUpdates) {
            beginBatchedUpdates();
        }
        this.mOldData = this.mData;
        this.mOldDataStart = 0;
        this.mOldDataSize = this.mSize;
        this.mData = (Object[]) Array.newInstance(this.mTClass, this.mSize + newDataSize + 10);
        this.mNewDataStart = 0;
        int newDataStart = 0;
        while (true) {
            if (this.mOldDataStart >= this.mOldDataSize && newDataStart >= newDataSize) {
                break;
            } else if (this.mOldDataStart == this.mOldDataSize) {
                int itemCount = newDataSize - newDataStart;
                System.arraycopy(newData, newDataStart, this.mData, this.mNewDataStart, itemCount);
                this.mNewDataStart += itemCount;
                this.mSize += itemCount;
                this.mCallback.onInserted(this.mNewDataStart - itemCount, itemCount);
                break;
            } else if (newDataStart == newDataSize) {
                int itemCount2 = this.mOldDataSize - this.mOldDataStart;
                System.arraycopy(this.mOldData, this.mOldDataStart, this.mData, this.mNewDataStart, itemCount2);
                this.mNewDataStart += itemCount2;
                break;
            } else {
                T oldItem = this.mOldData[this.mOldDataStart];
                T newItem = newData[newDataStart];
                int compare = this.mCallback.compare(oldItem, newItem);
                if (compare > 0) {
                    T[] tArr2 = this.mData;
                    int i2 = this.mNewDataStart;
                    this.mNewDataStart = i2 + 1;
                    tArr2[i2] = newItem;
                    this.mSize++;
                    newDataStart++;
                    this.mCallback.onInserted(this.mNewDataStart - 1, 1);
                } else if (compare != 0 || !this.mCallback.areItemsTheSame(oldItem, newItem)) {
                    T[] tArr3 = this.mData;
                    int i3 = this.mNewDataStart;
                    this.mNewDataStart = i3 + 1;
                    tArr3[i3] = oldItem;
                    this.mOldDataStart++;
                } else {
                    T[] tArr4 = this.mData;
                    int i4 = this.mNewDataStart;
                    this.mNewDataStart = i4 + 1;
                    tArr4[i4] = newItem;
                    newDataStart++;
                    this.mOldDataStart++;
                    if (!this.mCallback.areContentsTheSame(oldItem, newItem)) {
                        this.mCallback.onChanged(this.mNewDataStart - 1, 1, this.mCallback.getChangePayload(oldItem, newItem));
                    }
                }
            }
        }
        this.mOldData = null;
        if (forceBatchedUpdates) {
            endBatchedUpdates();
        }
    }

    private void throwIfInMutationOperation() {
        Throwable th;
        if (this.mOldData != null) {
            Throwable th2 = th;
            new IllegalStateException("Data cannot be mutated in the middle of a batch update operation such as addAll or replaceAll.");
            throw th2;
        }
    }

    public void beginBatchedUpdates() {
        BatchedCallback batchedCallback;
        throwIfInMutationOperation();
        if (!(this.mCallback instanceof BatchedCallback)) {
            if (this.mBatchedCallback == null) {
                new BatchedCallback(this.mCallback);
                this.mBatchedCallback = batchedCallback;
            }
            this.mCallback = this.mBatchedCallback;
        }
    }

    public void endBatchedUpdates() {
        throwIfInMutationOperation();
        if (this.mCallback instanceof BatchedCallback) {
            ((BatchedCallback) this.mCallback).dispatchLastEvent();
        }
        if (this.mCallback == this.mBatchedCallback) {
            this.mCallback = this.mBatchedCallback.mWrappedCallback;
        }
    }

    private int add(T t, boolean z) {
        T item = t;
        boolean notify = z;
        int index = findIndexOf(item, this.mData, 0, this.mSize, 1);
        if (index == -1) {
            index = 0;
        } else if (index < this.mSize) {
            T existing = this.mData[index];
            if (this.mCallback.areItemsTheSame(existing, item)) {
                if (this.mCallback.areContentsTheSame(existing, item)) {
                    this.mData[index] = item;
                    return index;
                }
                this.mData[index] = item;
                this.mCallback.onChanged(index, 1, this.mCallback.getChangePayload(existing, item));
                return index;
            }
        }
        addToData(index, item);
        if (notify) {
            this.mCallback.onInserted(index, 1);
        }
        return index;
    }

    public boolean remove(T item) {
        throwIfInMutationOperation();
        return remove(item, true);
    }

    public T removeItemAt(int i) {
        int index = i;
        throwIfInMutationOperation();
        SortedList<T> item = get(index);
        removeItemAtIndex(index, true);
        return item;
    }

    private boolean remove(T item, boolean z) {
        boolean notify = z;
        int index = findIndexOf(item, this.mData, 0, this.mSize, 2);
        if (index == -1) {
            return false;
        }
        removeItemAtIndex(index, notify);
        return true;
    }

    private void removeItemAtIndex(int i, boolean notify) {
        int index = i;
        System.arraycopy(this.mData, index + 1, this.mData, index, (this.mSize - index) - 1);
        this.mSize--;
        this.mData[this.mSize] = null;
        if (notify) {
            this.mCallback.onRemoved(index, 1);
        }
    }

    public void updateItemAt(int i, T t) {
        int index = i;
        T item = t;
        throwIfInMutationOperation();
        T existing = get(index);
        boolean contentsChanged = existing == item || !this.mCallback.areContentsTheSame(existing, item);
        if (existing == item || this.mCallback.compare(existing, item) != 0) {
            if (contentsChanged) {
                this.mCallback.onChanged(index, 1, this.mCallback.getChangePayload(existing, item));
            }
            removeItemAtIndex(index, false);
            int newIndex = add(item, false);
            if (index != newIndex) {
                this.mCallback.onMoved(index, newIndex);
                return;
            }
            return;
        }
        this.mData[index] = item;
        if (contentsChanged) {
            this.mCallback.onChanged(index, 1, this.mCallback.getChangePayload(existing, item));
        }
    }

    public void recalculatePositionOfItemAt(int i) {
        int index = i;
        throwIfInMutationOperation();
        Object obj = get(index);
        removeItemAtIndex(index, false);
        int newIndex = add(obj, false);
        if (index != newIndex) {
            this.mCallback.onMoved(index, newIndex);
        }
    }

    public T get(int i) throws IndexOutOfBoundsException {
        Throwable th;
        StringBuilder sb;
        int index = i;
        if (index >= this.mSize || index < 0) {
            Throwable th2 = th;
            new StringBuilder();
            new IndexOutOfBoundsException(sb.append("Asked to get item at ").append(index).append(" but size is ").append(this.mSize).toString());
            throw th2;
        } else if (this.mOldData == null || index < this.mNewDataStart) {
            return this.mData[index];
        } else {
            return this.mOldData[(index - this.mNewDataStart) + this.mOldDataStart];
        }
    }

    public int indexOf(T t) {
        T item = t;
        if (this.mOldData == null) {
            return findIndexOf(item, this.mData, 0, this.mSize, 4);
        }
        int index = findIndexOf(item, this.mData, 0, this.mNewDataStart, 4);
        if (index != -1) {
            return index;
        }
        int index2 = findIndexOf(item, this.mOldData, this.mOldDataStart, this.mOldDataSize, 4);
        if (index2 != -1) {
            return (index2 - this.mOldDataStart) + this.mNewDataStart;
        }
        return -1;
    }

    private int findIndexOf(T t, T[] tArr, int i, int i2, int i3) {
        T item = t;
        T[] mData2 = tArr;
        int left = i;
        int right = i2;
        int reason = i3;
        while (left < right) {
            int middle = (left + right) / 2;
            T myItem = mData2[middle];
            int cmp = this.mCallback.compare(myItem, item);
            if (cmp < 0) {
                left = middle + 1;
            } else if (cmp != 0) {
                right = middle;
            } else if (this.mCallback.areItemsTheSame(myItem, item)) {
                return middle;
            } else {
                int exact = linearEqualitySearch(item, middle, left, right);
                if (reason != 1) {
                    return exact;
                }
                return exact == -1 ? middle : exact;
            }
        }
        return reason == 1 ? left : -1;
    }

    private int linearEqualitySearch(T t, int i, int i2, int i3) {
        T item = t;
        int middle = i;
        int left = i2;
        int right = i3;
        int next = middle - 1;
        while (next >= left) {
            T nextItem = this.mData[next];
            if (this.mCallback.compare(nextItem, item) != 0) {
                break;
            } else if (this.mCallback.areItemsTheSame(nextItem, item)) {
                return next;
            } else {
                next--;
            }
        }
        int next2 = middle + 1;
        while (next2 < right) {
            T nextItem2 = this.mData[next2];
            if (this.mCallback.compare(nextItem2, item) != 0) {
                break;
            } else if (this.mCallback.areItemsTheSame(nextItem2, item)) {
                return next2;
            } else {
                next2++;
            }
        }
        return -1;
    }

    private void addToData(int i, T t) {
        Throwable th;
        StringBuilder sb;
        int index = i;
        T item = t;
        if (index > this.mSize) {
            Throwable th2 = th;
            new StringBuilder();
            new IndexOutOfBoundsException(sb.append("cannot add item to ").append(index).append(" because size is ").append(this.mSize).toString());
            throw th2;
        }
        if (this.mSize == this.mData.length) {
            T[] newData = (Object[]) Array.newInstance(this.mTClass, this.mData.length + 10);
            System.arraycopy(this.mData, 0, newData, 0, index);
            newData[index] = item;
            System.arraycopy(this.mData, index, newData, index + 1, this.mSize - index);
            this.mData = newData;
        } else {
            System.arraycopy(this.mData, index, this.mData, index + 1, this.mSize - index);
            this.mData[index] = item;
        }
        this.mSize++;
    }

    private T[] copyArray(T[] tArr) {
        T[] items = tArr;
        T[] copy = (Object[]) Array.newInstance(this.mTClass, items.length);
        System.arraycopy(items, 0, copy, 0, items.length);
        return copy;
    }

    public void clear() {
        throwIfInMutationOperation();
        if (this.mSize != 0) {
            int prevSize = this.mSize;
            Arrays.fill(this.mData, 0, prevSize, (Object) null);
            this.mSize = 0;
            this.mCallback.onRemoved(0, prevSize);
        }
    }

    /* renamed from: android.support.v7.util.SortedList$Callback */
    public static abstract class Callback<T2> implements Comparator<T2>, ListUpdateCallback {
        public abstract boolean areContentsTheSame(T2 t2, T2 t22);

        public abstract boolean areItemsTheSame(T2 t2, T2 t22);

        public abstract int compare(T2 t2, T2 t22);

        public abstract void onChanged(int i, int i2);

        public Callback() {
        }

        public void onChanged(int position, int count, Object obj) {
            Object obj2 = obj;
            onChanged(position, count);
        }

        @Nullable
        public Object getChangePayload(T2 t2, T2 t22) {
            T2 t23 = t2;
            T2 t24 = t22;
            return null;
        }
    }

    /* renamed from: android.support.v7.util.SortedList$BatchedCallback */
    public static class BatchedCallback<T2> extends Callback<T2> {
        private final BatchingListUpdateCallback mBatchingListUpdateCallback;
        final Callback<T2> mWrappedCallback;

        public BatchedCallback(Callback<T2> wrappedCallback) {
            BatchingListUpdateCallback batchingListUpdateCallback;
            this.mWrappedCallback = wrappedCallback;
            new BatchingListUpdateCallback(this.mWrappedCallback);
            this.mBatchingListUpdateCallback = batchingListUpdateCallback;
        }

        public int compare(T2 o1, T2 o2) {
            return this.mWrappedCallback.compare(o1, o2);
        }

        public void onInserted(int position, int count) {
            this.mBatchingListUpdateCallback.onInserted(position, count);
        }

        public void onRemoved(int position, int count) {
            this.mBatchingListUpdateCallback.onRemoved(position, count);
        }

        public void onMoved(int fromPosition, int toPosition) {
            this.mBatchingListUpdateCallback.onMoved(fromPosition, toPosition);
        }

        public void onChanged(int position, int count) {
            this.mBatchingListUpdateCallback.onChanged(position, count, (Object) null);
        }

        public void onChanged(int position, int count, Object payload) {
            this.mBatchingListUpdateCallback.onChanged(position, count, payload);
        }

        public boolean areContentsTheSame(T2 oldItem, T2 newItem) {
            return this.mWrappedCallback.areContentsTheSame(oldItem, newItem);
        }

        public boolean areItemsTheSame(T2 item1, T2 item2) {
            return this.mWrappedCallback.areItemsTheSame(item1, item2);
        }

        @Nullable
        public Object getChangePayload(T2 item1, T2 item2) {
            return this.mWrappedCallback.getChangePayload(item1, item2);
        }

        public void dispatchLastEvent() {
            this.mBatchingListUpdateCallback.dispatchLastEvent();
        }
    }
}
