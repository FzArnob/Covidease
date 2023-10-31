package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelImpl> CREATOR;
    private final VersionedParcelable mParcel;

    public ParcelImpl(VersionedParcelable parcel) {
        this.mParcel = parcel;
    }

    protected ParcelImpl(Parcel in) {
        VersionedParcelParcel versionedParcelParcel;
        new VersionedParcelParcel(in);
        this.mParcel = versionedParcelParcel.readVersionedParcelable();
    }

    public <T extends VersionedParcelable> T getVersionedParcel() {
        return this.mParcel;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int i) {
        VersionedParcelParcel parcel;
        int i2 = i;
        new VersionedParcelParcel(dest);
        parcel.writeVersionedParcelable(this.mParcel);
    }

    static {
        Parcelable.Creator<ParcelImpl> creator;
        new Parcelable.Creator<ParcelImpl>() {
            public ParcelImpl createFromParcel(Parcel in) {
                ParcelImpl parcelImpl;
                new ParcelImpl(in);
                return parcelImpl;
            }

            public ParcelImpl[] newArray(int size) {
                return new ParcelImpl[size];
            }
        };
        CREATOR = creator;
    }
}
