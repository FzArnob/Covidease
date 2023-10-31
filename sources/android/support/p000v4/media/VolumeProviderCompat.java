package android.support.p000v4.media;

import android.os.Build;
import android.support.annotation.RestrictTo;
import android.support.p000v4.media.VolumeProviderCompatApi21;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* renamed from: android.support.v4.media.VolumeProviderCompat */
public abstract class VolumeProviderCompat {
    public static final int VOLUME_CONTROL_ABSOLUTE = 2;
    public static final int VOLUME_CONTROL_FIXED = 0;
    public static final int VOLUME_CONTROL_RELATIVE = 1;
    private Callback mCallback;
    private final int mControlType;
    private int mCurrentVolume;
    private final int mMaxVolume;
    private Object mVolumeProviderObj;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.media.VolumeProviderCompat$ControlType */
    public @interface ControlType {
    }

    public VolumeProviderCompat(int volumeControl, int maxVolume, int currentVolume) {
        this.mControlType = volumeControl;
        this.mMaxVolume = maxVolume;
        this.mCurrentVolume = currentVolume;
    }

    public final int getCurrentVolume() {
        return this.mCurrentVolume;
    }

    public final int getVolumeControl() {
        return this.mControlType;
    }

    public final int getMaxVolume() {
        return this.mMaxVolume;
    }

    public final void setCurrentVolume(int i) {
        int currentVolume = i;
        this.mCurrentVolume = currentVolume;
        Object volumeProviderObj = getVolumeProvider();
        if (volumeProviderObj != null && Build.VERSION.SDK_INT >= 21) {
            VolumeProviderCompatApi21.setCurrentVolume(volumeProviderObj, currentVolume);
        }
        if (this.mCallback != null) {
            this.mCallback.onVolumeChanged(this);
        }
    }

    public void onSetVolumeTo(int volume) {
    }

    public void onAdjustVolume(int direction) {
    }

    public void setCallback(Callback callback) {
        Callback callback2 = callback;
        this.mCallback = callback2;
    }

    public Object getVolumeProvider() {
        VolumeProviderCompatApi21.Delegate delegate;
        if (this.mVolumeProviderObj == null && Build.VERSION.SDK_INT >= 21) {
            new VolumeProviderCompatApi21.Delegate(this) {
                final /* synthetic */ VolumeProviderCompat this$0;

                {
                    this.this$0 = this$0;
                }

                public void onSetVolumeTo(int volume) {
                    this.this$0.onSetVolumeTo(volume);
                }

                public void onAdjustVolume(int direction) {
                    this.this$0.onAdjustVolume(direction);
                }
            };
            this.mVolumeProviderObj = VolumeProviderCompatApi21.createVolumeProvider(this.mControlType, this.mMaxVolume, this.mCurrentVolume, delegate);
        }
        return this.mVolumeProviderObj;
    }

    /* renamed from: android.support.v4.media.VolumeProviderCompat$Callback */
    public static abstract class Callback {
        public abstract void onVolumeChanged(VolumeProviderCompat volumeProviderCompat);

        public Callback() {
        }
    }
}
