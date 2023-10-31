package com.google.android.gms.common.data;

import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Iterator;

public abstract class AbstractDataBuffer<T> implements DataBuffer<T> {
    protected final DataHolder mDataHolder;

    @KeepForSdk
    protected AbstractDataBuffer(DataHolder dataHolder) {
        this.mDataHolder = dataHolder;
    }

    public abstract T get(int i);

    public int getCount() {
        if (this.mDataHolder == null) {
            return 0;
        }
        return this.mDataHolder.getCount();
    }

    @Deprecated
    public final void close() {
        release();
    }

    @Deprecated
    public boolean isClosed() {
        return this.mDataHolder == null || this.mDataHolder.isClosed();
    }

    public Bundle getMetadata() {
        return this.mDataHolder.getMetadata();
    }

    public Iterator<T> iterator() {
        Iterator<T> it;
        new DataBufferIterator(this);
        return it;
    }

    public Iterator<T> singleRefIterator() {
        Iterator<T> it;
        new SingleRefDataBufferIterator(this);
        return it;
    }

    public void release() {
        if (this.mDataHolder != null) {
            this.mDataHolder.close();
        }
    }
}
