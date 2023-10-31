package com.google.appinventor.components.runtime;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.MediaUtil;
import java.util.HashMap;
import java.util.Map;
import org.shaded.apache.http.HttpStatus;

@DesignerComponent(category = ComponentCategory.MEDIA, description = "<p>A multimedia component that plays sound files and optionally vibrates for the number of milliseconds (thousandths of a second) specified in the Blocks Editor.  The name of the sound file to play can be specified either in the Designer or in the Blocks Editor.</p> <p>For supported sound file formats, see <a href=\"http://developer.android.com/guide/appendix/media-formats.html\" target=\"_blank\">Android Supported Media Formats</a>.</p><p>This <code>Sound</code> component is best for short sound files, such as sound effects, while the <code>Player</code> component is more efficient for longer sounds, such as songs.</p><p>You might get an error if you attempt to play a sound immediately after setting the source.</p>", iconName = "images/soundEffect.png", nonVisible = true, version = 4)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.VIBRATE, android.permission.INTERNET")
public class Sound extends AndroidNonvisibleComponent implements Component, Deleteable, OnDestroyListener, OnResumeListener, OnStopListener {
    private static final int LOOP_MODE_NO_LOOP = 0;
    private static final int MAX_PLAY_DELAY_RETRIES = 10;
    private static final int MAX_STREAMS = 10;
    private static final float PLAYBACK_RATE_NORMAL = 1.0f;
    private static final int PLAY_DELAY_LENGTH = 50;
    private static final float VOLUME_FULL = 1.0f;
    /* access modifiers changed from: private */
    public int delayRetries;
    /* access modifiers changed from: private */
    public boolean loadComplete;
    private AudioManager mAudioManager;
    private int minimumInterval;
    private final Handler playWaitHandler;
    private int soundId;
    private final Map<String, Integer> soundMap;
    private SoundPool soundPool;
    /* access modifiers changed from: private */
    public String sourcePath;
    private int streamId;
    /* access modifiers changed from: private */
    public final Component thisComponent;
    private long timeLastPlayed;
    private final Vibrator vibe;
    private final boolean waitForLoadToComplete;

    static /* synthetic */ boolean access$002(Sound sound, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        sound.loadComplete = z3;
        return z2;
    }

    static /* synthetic */ int access$310(Sound sound) {
        Sound sound2 = sound;
        int i = sound2.delayRetries;
        sound2.delayRetries = i - 1;
        return i;
    }

    /* renamed from: com.google.appinventor.components.runtime.Sound$a */
    class C1018a {
        final /* synthetic */ Sound hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        private C1018a(Sound sound) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = sound;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C1018a(Sound sound, byte b) {
            this(sound);
            byte b2 = b;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Sound(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        Handler handler;
        SoundPool soundPool2;
        Map<String, Integer> map;
        C1018a aVar;
        SoundPool.OnLoadCompleteListener onLoadCompleteListener;
        this.waitForLoadToComplete = Build.VERSION.SDK_INT >= 8;
        new Handler();
        this.playWaitHandler = handler;
        this.thisComponent = this;
        new SoundPool(10, 3, 0);
        this.soundPool = soundPool2;
        new HashMap();
        this.soundMap = map;
        this.vibe = (Vibrator) this.form.getSystemService("vibrator");
        this.sourcePath = "";
        this.loadComplete = true;
        this.form.registerForOnResume(this);
        this.form.registerForOnStop(this);
        this.form.registerForOnDestroy(this);
        this.form.setVolumeControlStream(3);
        MinimumInterval(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        if (this.waitForLoadToComplete) {
            new C1018a(this, (byte) 0);
            new SoundPool.OnLoadCompleteListener(aVar) {
                private /* synthetic */ C1018a hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void onLoadComplete(SoundPool soundPool, int i, int i2) {
                    SoundPool soundPool2 = soundPool;
                    int i3 = i;
                    int i4 = i2;
                    boolean access$002 = Sound.access$002(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, true);
                }
            };
            this.soundPool.setOnLoadCompleteListener(onLoadCompleteListener);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The name of the sound file.  Only certain formats are supported.  See http://developer.android.com/guide/appendix/media-formats.html.")
    public String Source() {
        return this.sourcePath;
    }

    @DesignerProperty(defaultValue = "", editorType = "audio_asset")
    @SimpleProperty
    public void Source(String str) {
        StringBuilder sb;
        StringBuilder sb2;
        String str2 = str;
        this.sourcePath = str2 == null ? "" : str2;
        if (this.streamId != 0) {
            this.soundPool.stop(this.streamId);
            this.streamId = 0;
        }
        this.soundId = 0;
        if (this.sourcePath.length() != 0) {
            Integer num = this.soundMap.get(this.sourcePath);
            Integer num2 = num;
            if (num != null) {
                this.soundId = num2.intValue();
                return;
            }
            new StringBuilder("No existing sound with path ");
            int i = Log.i("Sound", sb.append(this.sourcePath).append(".").toString());
            try {
                int loadSoundPool = MediaUtil.loadSoundPool(this.soundPool, this.form, this.sourcePath);
                int i2 = loadSoundPool;
                if (loadSoundPool != 0) {
                    Integer put = this.soundMap.put(this.sourcePath, Integer.valueOf(i2));
                    new StringBuilder("Successfully began loading sound: setting soundId to ");
                    int i3 = Log.i("Sound", sb2.append(i2).append(".").toString());
                    this.soundId = i2;
                    this.loadComplete = false;
                    return;
                }
                this.form.dispatchErrorOccurredEvent(this, "Source", ErrorMessages.ERROR_UNABLE_TO_LOAD_MEDIA, this.sourcePath);
            } catch (PermissionException e) {
                this.form.dispatchPermissionDeniedEvent((Component) this, "Source", e);
            } catch (Exception e2) {
                this.form.dispatchErrorOccurredEvent(this, "Source", ErrorMessages.ERROR_UNABLE_TO_LOAD_MEDIA, this.sourcePath);
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The minimum interval, in milliseconds, between sounds.  If you play a sound, all further Play() calls will be ignored until the interval has elapsed.")
    public int MinimumInterval() {
        return this.minimumInterval;
    }

    @DesignerProperty(defaultValue = "500", editorType = "non_negative_integer")
    @SimpleProperty
    public void MinimumInterval(int i) {
        int i2 = i;
        this.minimumInterval = i2;
    }

    @SimpleFunction(description = "Plays the sound specified by the Source property.")
    public void Play() {
        if (this.soundId != 0) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.timeLastPlayed == 0 || currentTimeMillis >= this.timeLastPlayed + ((long) this.minimumInterval)) {
                this.timeLastPlayed = currentTimeMillis;
                this.delayRetries = 10;
                playWhenLoadComplete();
                return;
            }
            int i = Log.i("Sound", "Unable to play because MinimumInterval has not elapsed since last play.");
            return;
        }
        int i2 = Log.i("Sound", "Sound Id was 0. Did you remember to set the Source property?");
        this.form.dispatchErrorOccurredEvent(this, "Play", ErrorMessages.ERROR_UNABLE_TO_PLAY_MEDIA, this.sourcePath);
    }

    /* access modifiers changed from: private */
    public void playWhenLoadComplete() {
        StringBuilder sb;
        Runnable runnable;
        if (this.loadComplete || !this.waitForLoadToComplete) {
            playAndCheckResult();
            return;
        }
        new StringBuilder("Sound not ready:  retrying.  Remaining retries = ");
        int i = Log.i("Sound", sb.append(this.delayRetries).toString());
        new Runnable(this) {
            private /* synthetic */ Sound hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void run() {
                if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.loadComplete) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.playAndCheckResult();
                } else if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.delayRetries > 0) {
                    int access$310 = Sound.access$310(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.playWhenLoadComplete();
                } else {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchErrorOccurredEvent(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.thisComponent, "Play", ErrorMessages.ERROR_SOUND_NOT_READY, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.sourcePath);
                }
            }
        };
        boolean postDelayed = this.playWaitHandler.postDelayed(runnable, 50);
    }

    /* access modifiers changed from: private */
    public void playAndCheckResult() {
        StringBuilder sb;
        if (this.soundPool != null) {
            this.streamId = this.soundPool.play(this.soundId, 1.0f, 1.0f, 0, 0, 1.0f);
            new StringBuilder("SoundPool.play returned stream id ");
            int i = Log.i("Sound", sb.append(this.streamId).toString());
            if (this.streamId == 0) {
                this.form.dispatchErrorOccurredEvent(this, "Play", ErrorMessages.ERROR_UNABLE_TO_PLAY_MEDIA, this.sourcePath);
            }
        }
    }

    @SimpleFunction(description = "Pauses playing the sound if it is being played.")
    public void Pause() {
        if (this.streamId != 0) {
            this.soundPool.pause(this.streamId);
        } else {
            int i = Log.i("Sound", "Unable to pause. Did you remember to call the Play function?");
        }
    }

    @SimpleFunction(description = "Resumes playing the sound after a pause.")
    public void Resume() {
        if (this.streamId != 0) {
            this.soundPool.resume(this.streamId);
        } else {
            int i = Log.i("Sound", "Unable to resume. Did you remember to call the Play function?");
        }
    }

    @SimpleFunction(description = "Stops playing the sound if it is being played.")
    public void Stop() {
        if (this.streamId != 0) {
            this.soundPool.stop(this.streamId);
            this.streamId = 0;
            return;
        }
        int i = Log.i("Sound", "Unable to stop. Did you remember to call the Play function?");
    }

    @SimpleFunction(description = "Vibrates for the specified number of milliseconds.")
    public void Vibrate(int i) {
        this.vibe.vibrate((long) i);
    }

    @SimpleFunction(description = "Vibrate with a given pattern")
    public void VibratePattern(int i, int i2, boolean z) {
        int i3;
        int i4 = i;
        int i5 = i2;
        if (z) {
            i3 = 0;
        } else {
            i3 = -1;
        }
        long[] jArr = new long[3];
        jArr[0] = 0;
        long[] jArr2 = jArr;
        jArr2[1] = (long) i4;
        long[] jArr3 = jArr2;
        jArr3[2] = (long) i5;
        this.vibe.vibrate(jArr3, i3);
    }

    @SimpleFunction(description = "Ringer mode that may be audible and may vibrate.")
    public void SoundNormal() {
        this.mAudioManager.setRingerMode(2);
    }

    @SimpleFunction(description = "Ringer mode that will be silent and will not vibrate.")
    public void SoundSilent() {
        this.mAudioManager.setRingerMode(0);
    }

    @SimpleFunction(description = "Ringer mode that will be silent and will vibrate.")
    public void SoundVibrate() {
        this.mAudioManager.setRingerMode(1);
    }

    public void onStop() {
        int i = Log.i("Sound", "Got onStop");
        if (this.streamId != 0 && this.soundPool != null) {
            this.soundPool.pause(this.streamId);
        }
    }

    public void onResume() {
        int i = Log.i("Sound", "Got onResume");
        if (this.streamId != 0 && this.soundPool != null) {
            this.soundPool.resume(this.streamId);
        }
    }

    public void onDestroy() {
        prepareToDie();
    }

    public void onDelete() {
        prepareToDie();
    }

    private void prepareToDie() {
        if (this.streamId != 0) {
            this.soundPool.stop(this.streamId);
            boolean unload = this.soundPool.unload(this.streamId);
        }
        this.soundPool.release();
        this.vibe.cancel();
        this.soundPool = null;
    }
}
