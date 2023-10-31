package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.support.annotation.RestrictTo;
import android.support.p000v4.graphics.drawable.IconCompat;
import androidx.versionedparcelable.VersionedParcel;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public class IconCompatParcelizer {
    public IconCompatParcelizer() {
    }

    public static IconCompat read(VersionedParcel versionedParcel) {
        IconCompat iconCompat;
        VersionedParcel parcel = versionedParcel;
        new IconCompat();
        IconCompat obj = iconCompat;
        obj.mType = parcel.readInt(obj.mType, 1);
        obj.mData = parcel.readByteArray(obj.mData, 2);
        obj.mParcelable = parcel.readParcelable(obj.mParcelable, 3);
        obj.mInt1 = parcel.readInt(obj.mInt1, 4);
        obj.mInt2 = parcel.readInt(obj.mInt2, 5);
        obj.mTintList = (ColorStateList) parcel.readParcelable(obj.mTintList, 6);
        obj.mTintModeStr = parcel.readString(obj.mTintModeStr, 7);
        obj.onPostParceling();
        return obj;
    }

    public static void write(IconCompat iconCompat, VersionedParcel versionedParcel) {
        IconCompat obj = iconCompat;
        VersionedParcel parcel = versionedParcel;
        parcel.setSerializationFlags(true, true);
        obj.onPreParceling(parcel.isStream());
        parcel.writeInt(obj.mType, 1);
        parcel.writeByteArray(obj.mData, 2);
        parcel.writeParcelable(obj.mParcelable, 3);
        parcel.writeInt(obj.mInt1, 4);
        parcel.writeInt(obj.mInt2, 5);
        parcel.writeParcelable(obj.mTintList, 6);
        parcel.writeString(obj.mTintModeStr, 7);
    }
}
