package android.support.p000v4.media;

import android.media.AudioAttributes;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.versionedparcelable.VersionedParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* renamed from: android.support.v4.media.AudioAttributesCompat */
public class AudioAttributesCompat implements VersionedParcelable {
    static final String AUDIO_ATTRIBUTES_CONTENT_TYPE = "android.support.v4.media.audio_attrs.CONTENT_TYPE";
    static final String AUDIO_ATTRIBUTES_FLAGS = "android.support.v4.media.audio_attrs.FLAGS";
    static final String AUDIO_ATTRIBUTES_FRAMEWORKS = "android.support.v4.media.audio_attrs.FRAMEWORKS";
    static final String AUDIO_ATTRIBUTES_LEGACY_STREAM_TYPE = "android.support.v4.media.audio_attrs.LEGACY_STREAM_TYPE";
    static final String AUDIO_ATTRIBUTES_USAGE = "android.support.v4.media.audio_attrs.USAGE";
    public static final int CONTENT_TYPE_MOVIE = 3;
    public static final int CONTENT_TYPE_MUSIC = 2;
    public static final int CONTENT_TYPE_SONIFICATION = 4;
    public static final int CONTENT_TYPE_SPEECH = 1;
    public static final int CONTENT_TYPE_UNKNOWN = 0;
    static final int FLAG_ALL = 1023;
    static final int FLAG_ALL_PUBLIC = 273;
    public static final int FLAG_AUDIBILITY_ENFORCED = 1;
    static final int FLAG_BEACON = 8;
    static final int FLAG_BYPASS_INTERRUPTION_POLICY = 64;
    static final int FLAG_BYPASS_MUTE = 128;
    static final int FLAG_DEEP_BUFFER = 512;
    public static final int FLAG_HW_AV_SYNC = 16;
    static final int FLAG_HW_HOTWORD = 32;
    static final int FLAG_LOW_LATENCY = 256;
    static final int FLAG_SCO = 4;
    static final int FLAG_SECURE = 2;
    static final int INVALID_STREAM_TYPE = -1;
    private static final int[] SDK_USAGES = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16};
    private static final int SUPPRESSIBLE_CALL = 2;
    private static final int SUPPRESSIBLE_NOTIFICATION = 1;
    private static final SparseIntArray SUPPRESSIBLE_USAGES;
    private static final String TAG = "AudioAttributesCompat";
    public static final int USAGE_ALARM = 4;
    public static final int USAGE_ASSISTANCE_ACCESSIBILITY = 11;
    public static final int USAGE_ASSISTANCE_NAVIGATION_GUIDANCE = 12;
    public static final int USAGE_ASSISTANCE_SONIFICATION = 13;
    public static final int USAGE_ASSISTANT = 16;
    public static final int USAGE_GAME = 14;
    public static final int USAGE_MEDIA = 1;
    public static final int USAGE_NOTIFICATION = 5;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_DELAYED = 9;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_INSTANT = 8;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_REQUEST = 7;
    public static final int USAGE_NOTIFICATION_EVENT = 10;
    public static final int USAGE_NOTIFICATION_RINGTONE = 6;
    public static final int USAGE_UNKNOWN = 0;
    private static final int USAGE_VIRTUAL_SOURCE = 15;
    public static final int USAGE_VOICE_COMMUNICATION = 2;
    public static final int USAGE_VOICE_COMMUNICATION_SIGNALLING = 3;
    static boolean sForceLegacyBehavior;
    AudioAttributesImpl mImpl;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.media.AudioAttributesCompat$AttributeContentType */
    public @interface AttributeContentType {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.media.AudioAttributesCompat$AttributeUsage */
    public @interface AttributeUsage {
    }

    static {
        SparseIntArray sparseIntArray;
        new SparseIntArray();
        SUPPRESSIBLE_USAGES = sparseIntArray;
        SUPPRESSIBLE_USAGES.put(5, 1);
        SUPPRESSIBLE_USAGES.put(6, 2);
        SUPPRESSIBLE_USAGES.put(7, 2);
        SUPPRESSIBLE_USAGES.put(8, 1);
        SUPPRESSIBLE_USAGES.put(9, 1);
        SUPPRESSIBLE_USAGES.put(10, 1);
    }

    AudioAttributesCompat() {
    }

    AudioAttributesCompat(AudioAttributesImpl impl) {
        this.mImpl = impl;
    }

    public int getVolumeControlStream() {
        return this.mImpl.getVolumeControlStream();
    }

    @Nullable
    public Object unwrap() {
        return this.mImpl.getAudioAttributes();
    }

    public int getLegacyStreamType() {
        return this.mImpl.getLegacyStreamType();
    }

    @Nullable
    public static AudioAttributesCompat wrap(@NonNull Object obj) {
        AudioAttributesImpl impl;
        AudioAttributesCompat audioAttributesCompat;
        Object aa = obj;
        if (Build.VERSION.SDK_INT < 21 || sForceLegacyBehavior) {
            return null;
        }
        new AudioAttributesImplApi21((AudioAttributes) aa);
        new AudioAttributesCompat();
        AudioAttributesCompat aac = audioAttributesCompat;
        aac.mImpl = impl;
        return aac;
    }

    public int getContentType() {
        return this.mImpl.getContentType();
    }

    public int getUsage() {
        return this.mImpl.getUsage();
    }

    public int getFlags() {
        return this.mImpl.getFlags();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @NonNull
    public Bundle toBundle() {
        return this.mImpl.toBundle();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static AudioAttributesCompat fromBundle(Bundle bundle) {
        AudioAttributesImpl impl;
        AudioAttributesCompat audioAttributesCompat;
        AudioAttributesCompat audioAttributesCompat2;
        Bundle bundle2 = bundle;
        if (Build.VERSION.SDK_INT >= 21) {
            impl = AudioAttributesImplApi21.fromBundle(bundle2);
        } else {
            impl = AudioAttributesImplBase.fromBundle(bundle2);
        }
        if (impl == null) {
            audioAttributesCompat2 = null;
        } else {
            audioAttributesCompat2 = audioAttributesCompat;
            new AudioAttributesCompat(impl);
        }
        return audioAttributesCompat2;
    }

    /* renamed from: android.support.v4.media.AudioAttributesCompat$Builder */
    public static class Builder {
        private int mContentType = 0;
        private int mFlags = 0;
        private int mLegacyStream = -1;
        private int mUsage = 0;

        public Builder() {
        }

        public Builder(AudioAttributesCompat audioAttributesCompat) {
            AudioAttributesCompat aa = audioAttributesCompat;
            this.mUsage = aa.getUsage();
            this.mContentType = aa.getContentType();
            this.mFlags = aa.getFlags();
            this.mLegacyStream = aa.getRawLegacyStreamType();
        }

        public AudioAttributesCompat build() {
            AudioAttributesImpl audioAttributesImpl;
            AudioAttributesImpl impl;
            AudioAttributesCompat audioAttributesCompat;
            AudioAttributes.Builder builder;
            AudioAttributesImpl audioAttributesImpl2;
            if (AudioAttributesCompat.sForceLegacyBehavior || Build.VERSION.SDK_INT < 21) {
                new AudioAttributesImplBase(this.mContentType, this.mFlags, this.mUsage, this.mLegacyStream);
                impl = audioAttributesImpl;
            } else {
                new AudioAttributes.Builder();
                AudioAttributes.Builder api21Builder = builder.setContentType(this.mContentType).setFlags(this.mFlags).setUsage(this.mUsage);
                if (this.mLegacyStream != -1) {
                    AudioAttributes.Builder legacyStreamType = api21Builder.setLegacyStreamType(this.mLegacyStream);
                }
                new AudioAttributesImplApi21(api21Builder.build(), this.mLegacyStream);
                impl = audioAttributesImpl2;
            }
            new AudioAttributesCompat(impl);
            return audioAttributesCompat;
        }

        public Builder setUsage(int i) {
            int usage = i;
            switch (usage) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                    this.mUsage = usage;
                    break;
                case 16:
                    if (!AudioAttributesCompat.sForceLegacyBehavior && Build.VERSION.SDK_INT > 25) {
                        this.mUsage = usage;
                        break;
                    } else {
                        this.mUsage = 12;
                        break;
                    }
                    break;
                default:
                    this.mUsage = 0;
                    break;
            }
            return this;
        }

        public Builder setContentType(int i) {
            int contentType = i;
            switch (contentType) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                    this.mContentType = contentType;
                    break;
                default:
                    this.mUsage = 0;
                    break;
            }
            return this;
        }

        public Builder setFlags(int flags) {
            this.mFlags |= flags & AudioAttributesCompat.FLAG_ALL;
            return this;
        }

        public Builder setLegacyStreamType(int i) {
            Throwable th;
            int streamType = i;
            if (streamType == 10) {
                Throwable th2 = th;
                new IllegalArgumentException("STREAM_ACCESSIBILITY is not a legacy stream type that was used for audio playback");
                throw th2;
            }
            this.mLegacyStream = streamType;
            if (Build.VERSION.SDK_INT >= 21) {
                return setInternalLegacyStreamType(streamType);
            }
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setInternalLegacyStreamType(int i) {
            StringBuilder sb;
            int streamType = i;
            switch (streamType) {
                case 0:
                    this.mContentType = 1;
                    break;
                case 1:
                    break;
                case 2:
                    this.mContentType = 4;
                    break;
                case 3:
                    this.mContentType = 2;
                    break;
                case 4:
                    this.mContentType = 4;
                    break;
                case 5:
                    this.mContentType = 4;
                    break;
                case 6:
                    this.mContentType = 1;
                    this.mFlags |= 4;
                    break;
                case 7:
                    this.mFlags |= 1;
                    break;
                case 8:
                    this.mContentType = 4;
                    break;
                case 9:
                    this.mContentType = 4;
                    break;
                case 10:
                    this.mContentType = 1;
                    break;
                default:
                    new StringBuilder();
                    int e = Log.e(AudioAttributesCompat.TAG, sb.append("Invalid stream type ").append(streamType).append(" for AudioAttributesCompat").toString());
                    break;
            }
            this.mContentType = 4;
            this.mUsage = AudioAttributesCompat.usageForStreamType(streamType);
            return this;
        }
    }

    public int hashCode() {
        return this.mImpl.hashCode();
    }

    public String toString() {
        return this.mImpl.toString();
    }

    static String usageToString(int i) {
        StringBuilder sb;
        int usage = i;
        switch (usage) {
            case 0:
                return "USAGE_UNKNOWN";
            case 1:
                return "USAGE_MEDIA";
            case 2:
                return "USAGE_VOICE_COMMUNICATION";
            case 3:
                return "USAGE_VOICE_COMMUNICATION_SIGNALLING";
            case 4:
                return "USAGE_ALARM";
            case 5:
                return "USAGE_NOTIFICATION";
            case 6:
                return "USAGE_NOTIFICATION_RINGTONE";
            case 7:
                return "USAGE_NOTIFICATION_COMMUNICATION_REQUEST";
            case 8:
                return "USAGE_NOTIFICATION_COMMUNICATION_INSTANT";
            case 9:
                return "USAGE_NOTIFICATION_COMMUNICATION_DELAYED";
            case 10:
                return "USAGE_NOTIFICATION_EVENT";
            case 11:
                return "USAGE_ASSISTANCE_ACCESSIBILITY";
            case 12:
                return "USAGE_ASSISTANCE_NAVIGATION_GUIDANCE";
            case 13:
                return "USAGE_ASSISTANCE_SONIFICATION";
            case 14:
                return "USAGE_GAME";
            case 16:
                return "USAGE_ASSISTANT";
            default:
                new StringBuilder();
                return sb.append("unknown usage ").append(usage).toString();
        }
    }

    /* renamed from: android.support.v4.media.AudioAttributesCompat$AudioManagerHidden */
    static abstract class AudioManagerHidden {
        public static final int STREAM_ACCESSIBILITY = 10;
        public static final int STREAM_BLUETOOTH_SCO = 6;
        public static final int STREAM_SYSTEM_ENFORCED = 7;
        public static final int STREAM_TTS = 9;

        private AudioManagerHidden() {
        }
    }

    static int usageForStreamType(int streamType) {
        switch (streamType) {
            case 0:
                return 2;
            case 1:
            case 7:
                return 13;
            case 2:
                return 6;
            case 3:
                return 1;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 2;
            case 8:
                return 3;
            case 10:
                return 11;
            default:
                return 0;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void setForceLegacyBehavior(boolean force) {
        sForceLegacyBehavior = force;
    }

    static int toVolumeStreamType(boolean fromGetVolumeControlStream, AudioAttributesCompat audioAttributesCompat) {
        AudioAttributesCompat aa = audioAttributesCompat;
        return toVolumeStreamType(fromGetVolumeControlStream, aa.getFlags(), aa.getUsage());
    }

    /* access modifiers changed from: package-private */
    public int getRawLegacyStreamType() {
        return this.mImpl.getRawLegacyStreamType();
    }

    static int toVolumeStreamType(boolean z, int i, int i2) {
        Throwable th;
        StringBuilder sb;
        int i3;
        boolean fromGetVolumeControlStream = z;
        int flags = i;
        int usage = i2;
        if ((flags & 1) == 1) {
            if (fromGetVolumeControlStream) {
                i3 = 1;
            } else {
                i3 = 7;
            }
            return i3;
        } else if ((flags & 4) == 4) {
            return fromGetVolumeControlStream ? 0 : 6;
        } else {
            switch (usage) {
                case 0:
                    return fromGetVolumeControlStream ? Integer.MIN_VALUE : 3;
                case 1:
                case 12:
                case 14:
                case 16:
                    return 3;
                case 2:
                    return 0;
                case 3:
                    return fromGetVolumeControlStream ? 0 : 8;
                case 4:
                    return 4;
                case 5:
                case 7:
                case 8:
                case 9:
                case 10:
                    return 5;
                case 6:
                    return 2;
                case 11:
                    return 10;
                case 13:
                    return 1;
                default:
                    if (!fromGetVolumeControlStream) {
                        return 3;
                    }
                    Throwable th2 = th;
                    new StringBuilder();
                    new IllegalArgumentException(sb.append("Unknown usage value ").append(usage).append(" in audio attributes").toString());
                    throw th2;
            }
        }
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (!(o instanceof AudioAttributesCompat)) {
            return false;
        }
        AudioAttributesCompat that = (AudioAttributesCompat) o;
        if (this.mImpl != null) {
            return this.mImpl.equals(that.mImpl);
        }
        return that.mImpl == null;
    }
}
