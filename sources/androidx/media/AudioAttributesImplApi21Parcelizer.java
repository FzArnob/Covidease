package androidx.media;

import android.media.AudioAttributes;
import android.support.annotation.RestrictTo;
import androidx.versionedparcelable.VersionedParcel;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public final class AudioAttributesImplApi21Parcelizer {
    public AudioAttributesImplApi21Parcelizer() {
    }

    public static AudioAttributesImplApi21 read(VersionedParcel versionedParcel) {
        AudioAttributesImplApi21 audioAttributesImplApi21;
        VersionedParcel parcel = versionedParcel;
        new AudioAttributesImplApi21();
        AudioAttributesImplApi21 obj = audioAttributesImplApi21;
        obj.mAudioAttributes = (AudioAttributes) parcel.readParcelable(obj.mAudioAttributes, 1);
        obj.mLegacyStreamType = parcel.readInt(obj.mLegacyStreamType, 2);
        return obj;
    }

    public static void write(AudioAttributesImplApi21 audioAttributesImplApi21, VersionedParcel versionedParcel) {
        AudioAttributesImplApi21 obj = audioAttributesImplApi21;
        VersionedParcel parcel = versionedParcel;
        parcel.setSerializationFlags(false, false);
        parcel.writeParcelable(obj.mAudioAttributes, 1);
        parcel.writeInt(obj.mLegacyStreamType, 2);
    }
}
