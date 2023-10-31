package android.support.design.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.util.SparseArray;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ParcelableSparseArray extends SparseArray<Parcelable> implements Parcelable {
    public static final Parcelable.Creator<ParcelableSparseArray> CREATOR;

    public ParcelableSparseArray() {
    }

    public ParcelableSparseArray(Parcel parcel, ClassLoader loader) {
        Parcel source = parcel;
        int size = source.readInt();
        int[] keys = new int[size];
        source.readIntArray(keys);
        Parcelable[] values = source.readParcelableArray(loader);
        for (int i = 0; i < size; i++) {
            put(keys[i], values[i]);
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int flags = i;
        int size = size();
        int[] keys = new int[size];
        Parcelable[] values = new Parcelable[size];
        for (int i2 = 0; i2 < size; i2++) {
            keys[i2] = keyAt(i2);
            values[i2] = (Parcelable) valueAt(i2);
        }
        parcel2.writeInt(size);
        parcel2.writeIntArray(keys);
        parcel2.writeParcelableArray(values, flags);
    }

    static {
        Parcelable.Creator<ParcelableSparseArray> creator;
        new Parcelable.ClassLoaderCreator<ParcelableSparseArray>() {
            public ParcelableSparseArray createFromParcel(Parcel source, ClassLoader loader) {
                ParcelableSparseArray parcelableSparseArray;
                new ParcelableSparseArray(source, loader);
                return parcelableSparseArray;
            }

            public ParcelableSparseArray createFromParcel(Parcel source) {
                ParcelableSparseArray parcelableSparseArray;
                new ParcelableSparseArray(source, (ClassLoader) null);
                return parcelableSparseArray;
            }

            public ParcelableSparseArray[] newArray(int size) {
                return new ParcelableSparseArray[size];
            }
        };
        CREATOR = creator;
    }
}
