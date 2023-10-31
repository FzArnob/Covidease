package android.support.p000v4.media;

import android.media.VolumeProvider;
import android.support.annotation.RequiresApi;

@RequiresApi(21)
/* renamed from: android.support.v4.media.VolumeProviderCompatApi21 */
class VolumeProviderCompatApi21 {

    /* renamed from: android.support.v4.media.VolumeProviderCompatApi21$Delegate */
    public interface Delegate {
        void onAdjustVolume(int i);

        void onSetVolumeTo(int i);
    }

    public static Object createVolumeProvider(int volumeControl, int maxVolume, int currentVolume, Delegate delegate) {
        Object obj;
        final Delegate delegate2 = delegate;
        new VolumeProvider(volumeControl, maxVolume, currentVolume) {
            public void onSetVolumeTo(int volume) {
                delegate2.onSetVolumeTo(volume);
            }

            public void onAdjustVolume(int direction) {
                delegate2.onAdjustVolume(direction);
            }
        };
        return obj;
    }

    public static void setCurrentVolume(Object volumeProviderObj, int currentVolume) {
        ((VolumeProvider) volumeProviderObj).setCurrentVolume(currentVolume);
    }

    private VolumeProviderCompatApi21() {
    }
}
