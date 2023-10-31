package android.support.p000v4.media;

import android.annotation.TargetApi;
import android.media.AudioAttributes;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@TargetApi(21)
/* renamed from: android.support.v4.media.AudioAttributesImplApi21 */
class AudioAttributesImplApi21 implements AudioAttributesImpl {
    private static final String TAG = "AudioAttributesCompat21";
    static Method sAudioAttributesToLegacyStreamType;
    AudioAttributes mAudioAttributes;
    int mLegacyStreamType;

    AudioAttributesImplApi21() {
        this.mLegacyStreamType = -1;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    AudioAttributesImplApi21(AudioAttributes audioAttributes) {
        this(audioAttributes, -1);
    }

    AudioAttributesImplApi21(AudioAttributes audioAttributes, int explicitLegacyStream) {
        this.mLegacyStreamType = -1;
        this.mAudioAttributes = audioAttributes;
        this.mLegacyStreamType = explicitLegacyStream;
    }

    static Method getAudioAttributesToLegacyStreamTypeMethod() {
        try {
            if (sAudioAttributesToLegacyStreamType == null) {
                sAudioAttributesToLegacyStreamType = AudioAttributes.class.getMethod("toLegacyStreamType", new Class[]{AudioAttributes.class});
            }
            return sAudioAttributesToLegacyStreamType;
        } catch (NoSuchMethodException e) {
            NoSuchMethodException noSuchMethodException = e;
            return null;
        }
    }

    public Object getAudioAttributes() {
        return this.mAudioAttributes;
    }

    public int getVolumeControlStream() {
        if (Build.VERSION.SDK_INT >= 26) {
            return this.mAudioAttributes.getVolumeControlStream();
        }
        return AudioAttributesCompat.toVolumeStreamType(true, getFlags(), getUsage());
    }

    public int getLegacyStreamType() {
        StringBuilder sb;
        StringBuilder sb2;
        if (this.mLegacyStreamType != -1) {
            return this.mLegacyStreamType;
        }
        Method frameworkMethod = getAudioAttributesToLegacyStreamTypeMethod();
        if (frameworkMethod == null) {
            new StringBuilder();
            int w = Log.w(TAG, sb2.append("No AudioAttributes#toLegacyStreamType() on API: ").append(Build.VERSION.SDK_INT).toString());
            return -1;
        }
        try {
            return ((Integer) frameworkMethod.invoke((Object) null, new Object[]{this.mAudioAttributes})).intValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            new StringBuilder();
            int w2 = Log.w(TAG, sb.append("getLegacyStreamType() failed on API: ").append(Build.VERSION.SDK_INT).toString(), e);
            return -1;
        }
    }

    public int getRawLegacyStreamType() {
        return this.mLegacyStreamType;
    }

    public int getContentType() {
        return this.mAudioAttributes.getContentType();
    }

    public int getUsage() {
        return this.mAudioAttributes.getUsage();
    }

    public int getFlags() {
        return this.mAudioAttributes.getFlags();
    }

    @NonNull
    public Bundle toBundle() {
        Bundle bundle;
        new Bundle();
        Bundle bundle2 = bundle;
        bundle2.putParcelable("android.support.v4.media.audio_attrs.FRAMEWORKS", this.mAudioAttributes);
        if (this.mLegacyStreamType != -1) {
            bundle2.putInt("android.support.v4.media.audio_attrs.LEGACY_STREAM_TYPE", this.mLegacyStreamType);
        }
        return bundle2;
    }

    public int hashCode() {
        return this.mAudioAttributes.hashCode();
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (!(o instanceof AudioAttributesImplApi21)) {
            return false;
        }
        return this.mAudioAttributes.equals(((AudioAttributesImplApi21) o).mAudioAttributes);
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("AudioAttributesCompat: audioattributes=").append(this.mAudioAttributes).toString();
    }

    public static AudioAttributesImpl fromBundle(Bundle bundle) {
        AudioAttributesImpl audioAttributesImpl;
        Bundle bundle2 = bundle;
        if (bundle2 == null) {
            return null;
        }
        AudioAttributes frameworkAttrs = (AudioAttributes) bundle2.getParcelable("android.support.v4.media.audio_attrs.FRAMEWORKS");
        if (frameworkAttrs == null) {
            return null;
        }
        new AudioAttributesImplApi21(frameworkAttrs, bundle2.getInt("android.support.v4.media.audio_attrs.LEGACY_STREAM_TYPE", -1));
        return audioAttributesImpl;
    }
}
