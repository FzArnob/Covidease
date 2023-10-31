package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
public class DataBufferRef {
    @KeepForSdk
    protected final DataHolder mDataHolder;
    @KeepForSdk
    protected int mDataRow;
    private int zaln;

    @KeepForSdk
    public DataBufferRef(DataHolder dataHolder, int i) {
        this.mDataHolder = (DataHolder) Preconditions.checkNotNull(dataHolder);
        zag(i);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public int getDataRow() {
        return this.mDataRow;
    }

    /* access modifiers changed from: protected */
    public final void zag(int i) {
        int i2 = i;
        Preconditions.checkState(i2 >= 0 && i2 < this.mDataHolder.getCount());
        this.mDataRow = i2;
        this.zaln = this.mDataHolder.getWindowIndex(this.mDataRow);
    }

    @KeepForSdk
    public boolean isDataValid() {
        return !this.mDataHolder.isClosed();
    }

    @KeepForSdk
    public boolean hasColumn(String str) {
        return this.mDataHolder.hasColumn(str);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public long getLong(String str) {
        return this.mDataHolder.getLong(str, this.mDataRow, this.zaln);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public int getInteger(String str) {
        return this.mDataHolder.getInteger(str, this.mDataRow, this.zaln);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public boolean getBoolean(String str) {
        return this.mDataHolder.getBoolean(str, this.mDataRow, this.zaln);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public String getString(String str) {
        return this.mDataHolder.getString(str, this.mDataRow, this.zaln);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public float getFloat(String str) {
        return this.mDataHolder.zaa(str, this.mDataRow, this.zaln);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public double getDouble(String str) {
        return this.mDataHolder.zab(str, this.mDataRow, this.zaln);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public byte[] getByteArray(String str) {
        return this.mDataHolder.getByteArray(str, this.mDataRow, this.zaln);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public Uri parseUri(String str) {
        String str2 = str;
        String str3 = str2;
        String string = this.mDataHolder.getString(str2, this.mDataRow, this.zaln);
        String str4 = string;
        if (string == null) {
            return null;
        }
        return Uri.parse(str4);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void copyToBuffer(String str, CharArrayBuffer charArrayBuffer) {
        this.mDataHolder.zaa(str, this.mDataRow, this.zaln, charArrayBuffer);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public boolean hasNull(String str) {
        return this.mDataHolder.hasNull(str, this.mDataRow, this.zaln);
    }

    public int hashCode() {
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(this.mDataRow);
        Object[] objArr2 = objArr;
        objArr2[1] = Integer.valueOf(this.zaln);
        Object[] objArr3 = objArr2;
        objArr3[2] = this.mDataHolder;
        return Objects.hashCode(objArr3);
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        if (!(obj2 instanceof DataBufferRef)) {
            return false;
        }
        DataBufferRef dataBufferRef = (DataBufferRef) obj2;
        DataBufferRef dataBufferRef2 = dataBufferRef;
        if (!Objects.equal(Integer.valueOf(dataBufferRef.mDataRow), Integer.valueOf(this.mDataRow)) || !Objects.equal(Integer.valueOf(dataBufferRef2.zaln), Integer.valueOf(this.zaln)) || dataBufferRef2.mDataHolder != this.mDataHolder) {
            return false;
        }
        return true;
    }
}
