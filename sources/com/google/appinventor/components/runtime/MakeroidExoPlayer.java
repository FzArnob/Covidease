package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Environment;
import android.support.p000v4.content.FileProvider;
import android.util.Log;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import java.io.File;
import org.shaded.apache.http.HttpHost;

@DesignerComponent(category = ComponentCategory.MEDIA, description = "", iconName = "images/exoplayer.png", nonVisible = true, version = 4)
@UsesLibraries(libraries = "exoplayer.jar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.READ_EXTERNAL_STORAGE")
public class MakeroidExoPlayer extends AndroidNonvisibleComponent implements AudioManager.OnAudioFocusChangeListener, Component, OnDestroyListener {
    private int DQyzcUACK1E7EOZ6u3x9ArvpeLM1Qtq6g7Sbq12R8AspY4PE6CpW0lfACU69Eund = 50;
    private String LqPkE8nw2txhYsROtdyVVn8eaaz2Snf5qPRDJl9Xd4fWH3FTDxRDNp0V8h7hgt8B;
    /* access modifiers changed from: private */
    public boolean YgDmThmc2Ht6J8LKfKFuGtMLp2AoFjdDlIONA2izriJdICsKCPKMX3dYEj8K1z4k = false;
    private ComponentContainer container;
    private Context context;
    private PackageManager hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private SimpleExoPlayer f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    /* access modifiers changed from: private */
    public boolean iA1fsPSZHTCVXA414XY2edBmEFVpo23ZLLJ3ntPGDoZ3Lc1O8L7tucf7fjSxdGWm = false;
    private boolean isCompanion = false;
    /* access modifiers changed from: private */
    public boolean isPause = false;
    /* access modifiers changed from: private */
    public boolean isPlaying = false;
    private String opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR = "";

    static /* synthetic */ String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidExoPlayer makeroidExoPlayer, String str) {
        String str2 = str;
        String str3 = str2;
        makeroidExoPlayer.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR = str3;
        return str2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MakeroidExoPlayer(com.google.appinventor.components.runtime.ComponentContainer r12) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = 0
            r2.isPlaying = r3
            r2 = r0
            r3 = 0
            r2.isPause = r3
            r2 = r0
            r3 = 0
            r2.iA1fsPSZHTCVXA414XY2edBmEFVpo23ZLLJ3ntPGDoZ3Lc1O8L7tucf7fjSxdGWm = r3
            r2 = r0
            r3 = 0
            r2.YgDmThmc2Ht6J8LKfKFuGtMLp2AoFjdDlIONA2izriJdICsKCPKMX3dYEj8K1z4k = r3
            r2 = r0
            r3 = 0
            r2.isCompanion = r3
            r2 = r0
            r3 = 50
            r2.DQyzcUACK1E7EOZ6u3x9ArvpeLM1Qtq6g7Sbq12R8AspY4PE6CpW0lfACU69Eund = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR = r3
            r2 = r0
            r3 = r1
            r2.container = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            r10 = r2
            r2 = r10
            r3 = r10
            android.content.Context r3 = r3.context
            android.content.pm.PackageManager r3 = r3.getPackageManager()
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            r10 = r2
            r2 = r10
            r3 = r10
            java.lang.String r3 = r3.AppName()
            r2.LqPkE8nw2txhYsROtdyVVn8eaaz2Snf5qPRDJl9Xd4fWH3FTDxRDNp0V8h7hgt8B = r3
            r2 = r1
            com.google.appinventor.components.runtime.Form r2 = r2.$form()
            boolean r2 = r2 instanceof com.google.appinventor.components.runtime.ReplForm
            if (r2 == 0) goto L_0x0059
            r2 = r0
            r3 = 1
            r2.isCompanion = r3
        L_0x0059:
            r2 = r1
            com.google.appinventor.components.runtime.Form r2 = r2.$form()
            r3 = r0
            r2.registerForOnDestroy(r3)
            r2 = r1
            com.google.appinventor.components.runtime.Form r2 = r2.$form()
            r3 = 3
            r2.setVolumeControlStream(r3)
            r2 = r0
            r10 = r2
            r2 = r10
            r3 = r10
            android.content.Context r3 = r3.context
            com.google.android.exoplayer2.trackselection.DefaultTrackSelector r4 = new com.google.android.exoplayer2.trackselection.DefaultTrackSelector
            r10 = r4
            r4 = r10
            r5 = r10
            com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection$Factory r6 = new com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection$Factory
            r10 = r6
            r6 = r10
            r7 = r10
            com.google.android.exoplayer2.upstream.DefaultBandwidthMeter r8 = new com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
            r10 = r8
            r8 = r10
            r9 = r10
            r9.<init>()
            r7.<init>(r8)
            r5.<init>(r6)
            com.google.android.exoplayer2.SimpleExoPlayer r3 = com.google.android.exoplayer2.ExoPlayerFactory.newSimpleInstance(r3, r4)
            r2.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            r10 = r2
            r2 = r10
            r3 = r10
            r1 = r3
            com.google.android.exoplayer2.SimpleExoPlayer r2 = r2.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.MakeroidExoPlayer$1 r3 = new com.google.appinventor.components.runtime.MakeroidExoPlayer$1
            r10 = r3
            r3 = r10
            r4 = r10
            r5 = r1
            r4.<init>(r5)
            r2.addListener(r3)
            r2 = r0
            com.google.android.exoplayer2.SimpleExoPlayer r2 = r2.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            if (r2 == 0) goto L_0x00ae
            r2 = r0
            com.google.android.exoplayer2.SimpleExoPlayer r2 = r2.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r3 = 0
            r2.setPlayWhenReady(r3)
        L_0x00ae:
            java.lang.String r2 = "ExoPlayer"
            java.lang.String r3 = "ExoPlayer Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidExoPlayer.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public void onAudioFocusChange(int i) {
        switch (i) {
            case -3:
            case -2:
                Pause();
                return;
            case -1:
                OtherPlayerStarted();
                return;
            case 1:
                if (this.isPlaying || this.isPause) {
                    OtherPlayerStopped();
                    return;
                }
                return;
            default:
                return;
        }
    }

    @SimpleEvent(description = "This event is signaled when another player has started (and the current player is playing or paused, but not stopped).")
    public void OtherPlayerStarted() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OtherPlayerStarted", new Object[0]);
    }

    @SimpleEvent(description = "This event is signaled when another player has stopped (and the current player is playing or paused, but not stopped).")
    public void OtherPlayerStopped() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OtherPlayerStopped", new Object[0]);
    }

    public void onDestroy() {
        try {
            if (this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
                this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.stop();
                this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.release();
                this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
            }
        } catch (Exception e) {
            int e2 = Log.e("ExoPlayer", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Stop the player.")
    public void Stop() {
        try {
            if (this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
                return;
            }
            if (this.isPlaying || this.isPause) {
                this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setPlayWhenReady(false);
                this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.seekTo(0);
                this.iA1fsPSZHTCVXA414XY2edBmEFVpo23ZLLJ3ntPGDoZ3Lc1O8L7tucf7fjSxdGWm = true;
                this.isPlaying = false;
                this.isPause = false;
                StatusChanged(false, false, true, false);
            }
        } catch (Exception e) {
            int e2 = Log.e("ExoPlayer", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Pause the player.")
    public void Pause() {
        try {
            if (this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null && this.isPlaying) {
                this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setPlayWhenReady(false);
                this.isPause = true;
                this.isPlaying = false;
                this.iA1fsPSZHTCVXA414XY2edBmEFVpo23ZLLJ3ntPGDoZ3Lc1O8L7tucf7fjSxdGWm = false;
                StatusChanged(false, true, false, this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isLoading());
            }
        } catch (Exception e) {
            int e2 = Log.e("ExoPlayer", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Start the player.")
    public void Start() {
        try {
            if (this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
                if (this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getPlaybackState() == 4) {
                    this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.seekTo(0);
                }
                this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setPlayWhenReady(true);
                this.isPlaying = true;
                this.isPause = false;
                this.iA1fsPSZHTCVXA414XY2edBmEFVpo23ZLLJ3ntPGDoZ3Lc1O8L7tucf7fjSxdGWm = false;
                StatusChanged(true, false, false, this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isLoading());
                Volume(this.DQyzcUACK1E7EOZ6u3x9ArvpeLM1Qtq6g7Sbq12R8AspY4PE6CpW0lfACU69Eund);
                Loop(this.YgDmThmc2Ht6J8LKfKFuGtMLp2AoFjdDlIONA2izriJdICsKCPKMX3dYEj8K1z4k);
            }
        } catch (Exception e) {
            int e2 = Log.e("ExoPlayer", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Resume the player.")
    public void Resume() {
        try {
            if (this.isPlaying) {
                Pause();
            } else {
                Start();
            }
        } catch (Exception e) {
            int e2 = Log.e("ExoPlayer", String.valueOf(e));
        }
    }

    @SimpleEvent(description = "This event returns true or false for the respective simpleExoPlayer statuses.")
    public void StatusChanged(boolean z, boolean z2, boolean z3, boolean z4) {
        Object[] objArr = new Object[4];
        objArr[0] = Boolean.valueOf(z);
        Object[] objArr2 = objArr;
        objArr2[1] = Boolean.valueOf(z2);
        Object[] objArr3 = objArr2;
        objArr3[2] = Boolean.valueOf(z3);
        Object[] objArr4 = objArr3;
        objArr4[3] = Boolean.valueOf(z4);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "StatusChanged", objArr4);
    }

    @SimpleEvent(description = "This event returns meta data from the audio stream. Works for files but not for streams as example radio streams.")
    public void GotMetaData(String str, String str2, String str3, String str4, String str5) {
        Object[] objArr = new Object[5];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        Object[] objArr3 = objArr2;
        objArr3[2] = str3;
        Object[] objArr4 = objArr3;
        objArr4[3] = str4;
        Object[] objArr5 = objArr4;
        objArr5[4] = str5;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotMetaData", objArr5);
    }

    @SimpleEvent(description = "This event returns the error reason for any problems.")
    public void OnPlayerError(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnPlayerError", str);
    }

    @SimpleEvent(description = "This event is invoked if the player state is completed.")
    public void Completed() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Completed", new Object[0]);
    }

    @DesignerProperty(defaultValue = "50", editorType = "non_negative_float")
    @SimpleProperty(description = "Sets the volume to a number between 0 and 100")
    public void Volume(int i) {
        int i2 = i;
        if (i2 > 100 || i2 < 0) {
            this.container.$form().dispatchErrorOccurredEvent(this, "Volume", ErrorMessages.ERROR_PLAYER_INVALID_VOLUME, Integer.valueOf(i2));
            return;
        }
        if (this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVolume((float) (1.0d - (Math.log((double) (100 - i2)) / Math.log(100.0d))));
        }
        this.DQyzcUACK1E7EOZ6u3x9ArvpeLM1Qtq6g7Sbq12R8AspY4PE6CpW0lfACU69Eund = i2;
    }

    @SimpleProperty(description = "Return the player volume.")
    public float Volume() {
        return (float) this.DQyzcUACK1E7EOZ6u3x9ArvpeLM1Qtq6g7Sbq12R8AspY4PE6CpW0lfACU69Eund;
    }

    @SimpleFunction(description = "Returns true if the player is current playing.")
    public boolean isPlaying() {
        return this.isPlaying;
    }

    @SimpleFunction(description = "Returns true if the player is current in pause mode.")
    public boolean isPause() {
        return this.isPause;
    }

    @SimpleFunction(description = "Returns true if the player is current stopped.")
    public boolean isStopped() {
        return this.iA1fsPSZHTCVXA414XY2edBmEFVpo23ZLLJ3ntPGDoZ3Lc1O8L7tucf7fjSxdGWm;
    }

    @SimpleFunction(description = "Returns true if the player is current loading.")
    public boolean isLoading() {
        return this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isLoading();
    }

    @SimpleProperty(description = "Returns the duration of the source file.")
    public int Duration() {
        if (this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            return (int) this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getDuration();
        }
        return 0;
    }

    @SimpleProperty(description = "Returns the current position of the source file that is playing in milliseconds.")
    public int CurrentPosition() {
        if (this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            return (int) this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCurrentPosition();
        }
        return 0;
    }

    @SimpleFunction(description = "Set a position where the source file should start playing in milliseconds.")
    public void SeekTo(int i) {
        int i2 = i;
        if (this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            int intValue = Long.valueOf(this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCurrentPosition()).intValue();
            if (intValue + i2 <= Long.valueOf(this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getDuration()).intValue()) {
                this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.seekTo((long) (intValue + i2));
            } else {
                this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.seekTo((long) intValue);
            }
        }
    }

    @DesignerProperty(defaultValue = "", editorType = "asset")
    @SimpleProperty(description = "Set the path to the audio source. Can be a asset file, from external card, or from a online stream.")
    public void Source(String str) {
        boolean z;
        StringBuilder sb;
        Runnable runnable;
        File file;
        ExtractorMediaSource.Factory factory;
        DataSource.Factory factory2;
        TransferListener transferListener;
        ExtractorsFactory extractorsFactory;
        String str2;
        String str3 = str;
        this.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR = str3;
        if (str3.isEmpty()) {
            this.form.dispatchErrorOccurredEvent(this, "Source", ErrorMessages.ERROR_UNABLE_TO_LOAD_MEDIA, str3);
        } else if (this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            String str4 = this.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR;
            String str5 = str4;
            if (str4.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                z = true;
            } else {
                z = !str5.startsWith("/");
            }
            if (z) {
                SimpleExoPlayer simpleExoPlayer = this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                new DefaultBandwidthMeter();
                new DefaultDataSourceFactory(this.context, Util.getUserAgent(this.context, this.LqPkE8nw2txhYsROtdyVVn8eaaz2Snf5qPRDJl9Xd4fWH3FTDxRDNp0V8h7hgt8B), transferListener);
                new ExtractorMediaSource.Factory(factory2);
                new DefaultExtractorsFactory();
                ExtractorMediaSource.Factory extractorsFactory2 = factory.setExtractorsFactory(extractorsFactory);
                String str6 = this.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR;
                String str7 = str6;
                if (str6.startsWith(HttpHost.DEFAULT_SCHEME_NAME) || str7.startsWith("/")) {
                    str2 = str7;
                } else if (this.isCompanion) {
                    str2 = "file:///mnt/sdcard/Makeroid/assets/".concat(String.valueOf(str7));
                } else {
                    str2 = Form.ASSETS_PREFIX.concat(String.valueOf(str7));
                }
                simpleExoPlayer.prepare(extractorsFactory2.createMediaSource(Uri.parse(str2)));
                return;
            }
            String str8 = this.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR;
            new StringBuilder();
            if (str8.contains(sb.append(this.context.getExternalFilesDir("")).toString())) {
                try {
                    new File(this.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR);
                    hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(file);
                } catch (Exception e) {
                    int e2 = Log.e("ExoPlayer", String.valueOf(e));
                }
            } else {
                new Runnable(this) {
                    final /* synthetic */ MakeroidExoPlayer hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                    {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                    }

                    public final void run() {
                        PermissionResultHandler permissionResultHandler;
                        new PermissionResultHandler(this) {
                            private /* synthetic */ C08672 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                            {
                                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                            }

                            public final void HandlePermissionResponse(String str, boolean z) {
                                File file;
                                StringBuilder sb;
                                String str2 = str;
                                if (z) {
                                    try {
                                        if (MakeroidExoPlayer.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).startsWith("file:///")) {
                                            String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = MakeroidExoPlayer.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, MakeroidExoPlayer.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).substring(7));
                                        }
                                        if (MakeroidExoPlayer.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).startsWith("/storage/emulated/0/") || MakeroidExoPlayer.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).startsWith("/storage/emulated/1/")) {
                                            String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3 = MakeroidExoPlayer.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, MakeroidExoPlayer.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).substring(19));
                                        }
                                        new StringBuilder();
                                        new File(sb.append(Environment.getExternalStorageDirectory().getPath()).append(MakeroidExoPlayer.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)).toString());
                                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(file);
                                    } catch (Exception e) {
                                        int e2 = Log.e("ExoPlayer", String.valueOf(e));
                                    }
                                } else {
                                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Source", "android.permission.READ_EXTERNAL_STORAGE");
                                }
                            }
                        };
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.askPermission("android.permission.READ_EXTERNAL_STORAGE", permissionResultHandler);
                    }
                };
                this.form.runOnUiThread(runnable);
            }
        }
    }

    @SimpleProperty
    public String Source() {
        return this.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR;
    }

    /* access modifiers changed from: private */
    public void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(File file) {
        StringBuilder sb;
        StringBuilder sb2;
        ExtractorMediaSource.Factory factory;
        DataSource.Factory factory2;
        TransferListener transferListener;
        ExtractorsFactory extractorsFactory;
        File file2 = file;
        new StringBuilder("ExoPlayer source path: ");
        int i = Log.i("ExoPlayer", sb.append(this.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR).toString());
        if (file2.exists()) {
            Context context2 = this.context;
            new StringBuilder();
            Uri uriForFile = FileProvider.getUriForFile(context2, sb2.append(this.context.getPackageName()).append(".provider").toString(), file2);
            SimpleExoPlayer simpleExoPlayer = this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            new DefaultBandwidthMeter();
            new DefaultDataSourceFactory(this.context, Util.getUserAgent(this.context, this.LqPkE8nw2txhYsROtdyVVn8eaaz2Snf5qPRDJl9Xd4fWH3FTDxRDNp0V8h7hgt8B), transferListener);
            new ExtractorMediaSource.Factory(factory2);
            new DefaultExtractorsFactory();
            simpleExoPlayer.prepare(factory.setExtractorsFactory(extractorsFactory).createMediaSource(uriForFile));
            return;
        }
        this.form.dispatchErrorOccurredEvent(this, "Source", ErrorMessages.ERROR_CANNOT_FIND_FILE, this.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void Loop(boolean z) {
        boolean z2 = z;
        if (this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            if (z2) {
                this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRepeatMode(1);
            } else {
                this.f478hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRepeatMode(0);
            }
        }
        this.YgDmThmc2Ht6J8LKfKFuGtMLp2AoFjdDlIONA2izriJdICsKCPKMX3dYEj8K1z4k = z2;
    }

    @SimpleProperty(description = "If true, the player will loop when it plays.")
    public boolean Loop() {
        return this.YgDmThmc2Ht6J8LKfKFuGtMLp2AoFjdDlIONA2izriJdICsKCPKMX3dYEj8K1z4k;
    }

    private String AppName() {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getApplicationInfo(this.context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        return (String) (applicationInfo != null ? this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getApplicationLabel(applicationInfo) : "not found");
    }
}
