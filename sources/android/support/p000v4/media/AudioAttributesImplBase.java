package android.support.p000v4.media;

import android.os.Bundle;
import android.support.annotation.NonNull;
import java.util.Arrays;

/* renamed from: android.support.v4.media.AudioAttributesImplBase */
class AudioAttributesImplBase implements AudioAttributesImpl {
    int mContentType = 0;
    int mFlags = 0;
    int mLegacyStream = -1;
    int mUsage = 0;

    AudioAttributesImplBase() {
    }

    AudioAttributesImplBase(int contentType, int flags, int usage, int legacyStream) {
        this.mContentType = contentType;
        this.mFlags = flags;
        this.mUsage = usage;
        this.mLegacyStream = legacyStream;
    }

    public Object getAudioAttributes() {
        return null;
    }

    public int getVolumeControlStream() {
        return AudioAttributesCompat.toVolumeStreamType(true, this.mFlags, this.mUsage);
    }

    public int getLegacyStreamType() {
        if (this.mLegacyStream != -1) {
            return this.mLegacyStream;
        }
        return AudioAttributesCompat.toVolumeStreamType(false, this.mFlags, this.mUsage);
    }

    public int getRawLegacyStreamType() {
        return this.mLegacyStream;
    }

    public int getContentType() {
        return this.mContentType;
    }

    public int getUsage() {
        return this.mUsage;
    }

    public int getFlags() {
        int flags = this.mFlags;
        int legacyStream = getLegacyStreamType();
        if (legacyStream == 6) {
            flags |= 4;
        } else if (legacyStream == 7) {
            flags |= 1;
        }
        return flags & 273;
    }

    @NonNull
    public Bundle toBundle() {
        Bundle bundle;
        new Bundle();
        Bundle bundle2 = bundle;
        bundle2.putInt("android.support.v4.media.audio_attrs.USAGE", this.mUsage);
        bundle2.putInt("android.support.v4.media.audio_attrs.CONTENT_TYPE", this.mContentType);
        bundle2.putInt("android.support.v4.media.audio_attrs.FLAGS", this.mFlags);
        if (this.mLegacyStream != -1) {
            bundle2.putInt("android.support.v4.media.audio_attrs.LEGACY_STREAM_TYPE", this.mLegacyStream);
        }
        return bundle2;
    }

    public int hashCode() {
        Object[] objArr = new Object[4];
        objArr[0] = Integer.valueOf(this.mContentType);
        Object[] objArr2 = objArr;
        objArr2[1] = Integer.valueOf(this.mFlags);
        Object[] objArr3 = objArr2;
        objArr3[2] = Integer.valueOf(this.mUsage);
        Object[] objArr4 = objArr3;
        objArr4[3] = Integer.valueOf(this.mLegacyStream);
        return Arrays.hashCode(objArr4);
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (!(o instanceof AudioAttributesImplBase)) {
            return false;
        }
        AudioAttributesImplBase that = (AudioAttributesImplBase) o;
        return this.mContentType == that.getContentType() && this.mFlags == that.getFlags() && this.mUsage == that.getUsage() && this.mLegacyStream == that.mLegacyStream;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder("AudioAttributesCompat:");
        StringBuilder sb2 = sb;
        if (this.mLegacyStream != -1) {
            StringBuilder append = sb2.append(" stream=").append(this.mLegacyStream);
            StringBuilder append2 = sb2.append(" derived");
        }
        StringBuilder append3 = sb2.append(" usage=").append(AudioAttributesCompat.usageToString(this.mUsage)).append(" content=").append(this.mContentType).append(" flags=0x").append(Integer.toHexString(this.mFlags).toUpperCase());
        return sb2.toString();
    }

    public static AudioAttributesImpl fromBundle(Bundle bundle) {
        AudioAttributesImpl audioAttributesImpl;
        Bundle bundle2 = bundle;
        if (bundle2 == null) {
            return null;
        }
        int usage = bundle2.getInt("android.support.v4.media.audio_attrs.USAGE", 0);
        new AudioAttributesImplBase(bundle2.getInt("android.support.v4.media.audio_attrs.CONTENT_TYPE", 0), bundle2.getInt("android.support.v4.media.audio_attrs.FLAGS", 0), usage, bundle2.getInt("android.support.v4.media.audio_attrs.LEGACY_STREAM_TYPE", -1));
        return audioAttributesImpl;
    }
}
