package androidx.media;

import android.support.annotation.RestrictTo;
import androidx.versionedparcelable.VersionedParcel;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public final class AudioAttributesImplBaseParcelizer {
    public AudioAttributesImplBaseParcelizer() {
    }

    public static AudioAttributesImplBase read(VersionedParcel versionedParcel) {
        AudioAttributesImplBase audioAttributesImplBase;
        VersionedParcel parcel = versionedParcel;
        new AudioAttributesImplBase();
        AudioAttributesImplBase obj = audioAttributesImplBase;
        obj.mUsage = parcel.readInt(obj.mUsage, 1);
        obj.mContentType = parcel.readInt(obj.mContentType, 2);
        obj.mFlags = parcel.readInt(obj.mFlags, 3);
        obj.mLegacyStream = parcel.readInt(obj.mLegacyStream, 4);
        return obj;
    }

    public static void write(AudioAttributesImplBase audioAttributesImplBase, VersionedParcel versionedParcel) {
        AudioAttributesImplBase obj = audioAttributesImplBase;
        VersionedParcel parcel = versionedParcel;
        parcel.setSerializationFlags(false, false);
        parcel.writeInt(obj.mUsage, 1);
        parcel.writeInt(obj.mContentType, 2);
        parcel.writeInt(obj.mFlags, 3);
        parcel.writeInt(obj.mLegacyStream, 4);
    }
}
