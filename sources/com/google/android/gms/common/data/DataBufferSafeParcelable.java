package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
public class DataBufferSafeParcelable<T extends SafeParcelable> extends AbstractDataBuffer<T> {
    private static final String[] zalo = {"data"};
    private final Parcelable.Creator<T> zalp;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @KeepForSdk
    public DataBufferSafeParcelable(DataHolder dataHolder, Parcelable.Creator<T> creator) {
        super(dataHolder);
        this.zalp = creator;
    }

    @KeepForSdk
    public static DataHolder.Builder buildDataHolder() {
        return DataHolder.builder(zalo);
    }

    @KeepForSdk
    public static <T extends SafeParcelable> void addValue(DataHolder.Builder builder, T t) {
        ContentValues contentValues;
        Parcel obtain = Parcel.obtain();
        t.writeToParcel(obtain, 0);
        new ContentValues();
        ContentValues contentValues2 = contentValues;
        contentValues2.put("data", obtain.marshall());
        DataHolder.Builder withRow = builder.withRow(contentValues2);
        obtain.recycle();
    }

    @KeepForSdk
    public T get(int i) {
        int i2 = i;
        byte[] byteArray = this.mDataHolder.getByteArray("data", i2, this.mDataHolder.getWindowIndex(i2));
        Parcel obtain = Parcel.obtain();
        Parcel parcel = obtain;
        obtain.unmarshall(byteArray, 0, byteArray.length);
        parcel.setDataPosition(0);
        T t = (SafeParcelable) this.zalp.createFromParcel(parcel);
        parcel.recycle();
        return t;
    }
}
