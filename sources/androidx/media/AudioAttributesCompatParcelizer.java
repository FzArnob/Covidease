package androidx.media;

import android.support.annotation.RestrictTo;
import android.support.p000v4.media.AudioAttributesCompat;
import androidx.versionedparcelable.VersionedParcel;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public final class AudioAttributesCompatParcelizer {
    public AudioAttributesCompatParcelizer() {
    }

    public static AudioAttributesCompat read(VersionedParcel parcel) {
        AudioAttributesCompat audioAttributesCompat;
        new AudioAttributesCompat();
        AudioAttributesCompat obj = audioAttributesCompat;
        obj.mImpl = (AudioAttributesImpl) parcel.readVersionedParcelable(obj.mImpl, 1);
        return obj;
    }

    public static void write(AudioAttributesCompat obj, VersionedParcel versionedParcel) {
        VersionedParcel parcel = versionedParcel;
        parcel.setSerializationFlags(false, false);
        parcel.writeVersionedParcelable(obj.mImpl, 1);
    }
}
