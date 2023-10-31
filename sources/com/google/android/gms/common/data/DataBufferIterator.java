package com.google.android.gms.common.data;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.NoSuchElementException;

@KeepForSdk
public class DataBufferIterator<T> implements Iterator<T> {
    protected final DataBuffer<T> zalk;
    protected int zall = -1;

    public DataBufferIterator(DataBuffer<T> dataBuffer) {
        this.zalk = (DataBuffer) Preconditions.checkNotNull(dataBuffer);
    }

    public boolean hasNext() {
        return this.zall < this.zalk.getCount() + -1;
    }

    public T next() {
        Throwable th;
        StringBuilder sb;
        if (!hasNext()) {
            Throwable th2 = th;
            int i = this.zall;
            new StringBuilder(46);
            new NoSuchElementException(sb.append("Cannot advance the iterator beyond ").append(i).toString());
            throw th2;
        }
        DataBuffer<T> dataBuffer = this.zalk;
        int i2 = this.zall + 1;
        int i3 = i2;
        this.zall = i3;
        return dataBuffer.get(i2);
    }

    public void remove() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
        throw th2;
    }
}
