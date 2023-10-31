package com.google.android.gms.common.data;

import com.google.android.gms.common.data.DataBufferObserver;
import java.util.HashSet;
import java.util.Iterator;

public final class DataBufferObserverSet implements DataBufferObserver, DataBufferObserver.Observable {
    private HashSet<DataBufferObserver> zalm;

    public DataBufferObserverSet() {
        HashSet<DataBufferObserver> hashSet;
        new HashSet<>();
        this.zalm = hashSet;
    }

    public final boolean hasObservers() {
        return !this.zalm.isEmpty();
    }

    public final void clear() {
        this.zalm.clear();
    }

    public final void addObserver(DataBufferObserver dataBufferObserver) {
        boolean add = this.zalm.add(dataBufferObserver);
    }

    public final void removeObserver(DataBufferObserver dataBufferObserver) {
        boolean remove = this.zalm.remove(dataBufferObserver);
    }

    public final void onDataChanged() {
        Iterator<DataBufferObserver> it = this.zalm.iterator();
        while (it.hasNext()) {
            it.next().onDataChanged();
        }
    }

    public final void onDataRangeChanged(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        Iterator<DataBufferObserver> it = this.zalm.iterator();
        while (it.hasNext()) {
            it.next().onDataRangeChanged(i3, i4);
        }
    }

    public final void onDataRangeInserted(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        Iterator<DataBufferObserver> it = this.zalm.iterator();
        while (it.hasNext()) {
            it.next().onDataRangeInserted(i3, i4);
        }
    }

    public final void onDataRangeRemoved(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        Iterator<DataBufferObserver> it = this.zalm.iterator();
        while (it.hasNext()) {
            it.next().onDataRangeRemoved(i3, i4);
        }
    }

    public final void onDataRangeMoved(int i, int i2, int i3) {
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        Iterator<DataBufferObserver> it = this.zalm.iterator();
        while (it.hasNext()) {
            it.next().onDataRangeMoved(i4, i5, i6);
        }
    }
}
