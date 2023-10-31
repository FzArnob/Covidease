package com.google.android.gms.common.data;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.NoSuchElementException;

@KeepForSdk
public class SingleRefDataBufferIterator<T> extends DataBufferIterator<T> {
    private T zamg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SingleRefDataBufferIterator(DataBuffer<T> dataBuffer) {
        super(dataBuffer);
    }

    public T next() {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        if (!hasNext()) {
            Throwable th3 = th2;
            int i = this.zall;
            new StringBuilder(46);
            new NoSuchElementException(sb2.append("Cannot advance the iterator beyond ").append(i).toString());
            throw th3;
        }
        this.zall++;
        if (this.zall == 0) {
            this.zamg = this.zalk.get(0);
            if (!(this.zamg instanceof DataBufferRef)) {
                Throwable th4 = th;
                String valueOf = String.valueOf(this.zamg.getClass());
                new StringBuilder(44 + String.valueOf(valueOf).length());
                new IllegalStateException(sb.append("DataBuffer reference of type ").append(valueOf).append(" is not movable").toString());
                throw th4;
            }
        } else {
            ((DataBufferRef) this.zamg).zag(this.zall);
        }
        return this.zamg;
    }
}
