package com.google.appinventor.components.runtime;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import gnu.expr.Declaration;

@DesignerComponent(category = ComponentCategory.MEDIA, description = "A multimedia component capable of playing videos. When the application is run, the VideoPlayer will be displayed as a rectangle on-screen.  If the user touches the rectangle, controls will appear to play/pause, skip ahead, and skip backward within the video.  The application can also control behavior by calling the <code>Start</code>, <code>Pause</code>, and <code>SeekTo</code> methods.  <p>Video files should be in 3GPP (.3gp) or MPEG-4 (.mp4) formats.  For more details about legal formats, see <a href=\"http://developer.android.com/guide/appendix/media-formats.html\" target=\"_blank\">Android Supported Media Formats</a>.</p><p>App Inventor for Android only permits video files under 1 MB and limits the total size of an application to 5 MB, not all of which is available for media (video, audio, and sound) files.  If your media files are too large, you may get errors when packaging or installing your application, in which case you should reduce the number of media files or their sizes.  Most video editing software, such as Windows Movie Maker and Apple iMovie, can help you decrease the size of videos by shortening them or re-encoding the video into a more compact format.</p><p>You can also set the media source to a URL that points to a streaming video, but the URL must point to the video file itself, not to a program that plays the video.", version = 8)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public final class VideoPlayer extends AndroidViewComponent implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, Deleteable, OnDestroyListener {
    private MediaPlayer B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private boolean IQizCoownq3QMuqjiN3SEtpvqAzYOMGAuQhX0ytYpmBBjgggyq4HRoSxolTbNj90 = false;
    /* access modifiers changed from: private */
    public final Handler androidUIHandler;
    private int hOhK0kjjpreklHpzajOh4zpZ0hDhUAnmvbGmmElshoJmuxQNkJo9K2Sh2YQvTJN;
    private MediaController hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final C1080a f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private String sourcePath;
    private boolean tFNQ38WX0EWx1sBmwkfGOavwj0ohdRyv5RfL5KQ0qa5Pnr6qc7YsZTQu1aP3NtmA = false;
    private boolean vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ = false;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public VideoPlayer(com.google.appinventor.components.runtime.ComponentContainer r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            r3 = 0
            r2.IQizCoownq3QMuqjiN3SEtpvqAzYOMGAuQhX0ytYpmBBjgggyq4HRoSxolTbNj90 = r3
            r2 = r0
            r3 = 0
            r2.vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ = r3
            r2 = r0
            r3 = 0
            r2.tFNQ38WX0EWx1sBmwkfGOavwj0ohdRyv5RfL5KQ0qa5Pnr6qc7YsZTQu1aP3NtmA = r3
            r2 = r0
            android.os.Handler r3 = new android.os.Handler
            r7 = r3
            r3 = r7
            r4 = r7
            r4.<init>()
            r2.androidUIHandler = r3
            r2 = r0
            r3 = 50
            r2.hOhK0kjjpreklHpzajOh4zpZ0hDhUAnmvbGmmElshoJmuxQNkJo9K2Sh2YQvTJN = r3
            r2 = r1
            com.google.appinventor.components.runtime.Form r2 = r2.$form()
            r3 = r0
            r2.registerForOnDestroy(r3)
            r2 = r0
            com.google.appinventor.components.runtime.VideoPlayer$a r3 = new com.google.appinventor.components.runtime.VideoPlayer$a
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r6 = r1
            android.app.Activity r6 = r6.$context()
            r4.<init>(r5, r6)
            r2.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            android.widget.MediaController r3 = new android.widget.MediaController
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r1
            android.app.Activity r5 = r5.$context()
            r4.<init>(r5)
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            com.google.appinventor.components.runtime.VideoPlayer$a r2 = r2.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r3 = r0
            android.widget.MediaController r3 = r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r2.setMediaController(r3)
            r2 = r0
            com.google.appinventor.components.runtime.VideoPlayer$a r2 = r2.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r3 = r0
            r2.setOnCompletionListener(r3)
            r2 = r0
            com.google.appinventor.components.runtime.VideoPlayer$a r2 = r2.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r3 = r0
            r2.setOnErrorListener(r3)
            r2 = r0
            com.google.appinventor.components.runtime.VideoPlayer$a r2 = r2.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r3 = r0
            r2.setOnPreparedListener(r3)
            r2 = r1
            r3 = r0
            r2.$add(r3)
            r2 = r1
            r3 = r0
            r4 = 176(0xb0, float:2.47E-43)
            r2.setChildWidth(r3, r4)
            r2 = r1
            r3 = r0
            r4 = 144(0x90, float:2.02E-43)
            r2.setChildHeight(r3, r4)
            r2 = r1
            com.google.appinventor.components.runtime.Form r2 = r2.$form()
            r3 = 3
            r2.setVolumeControlStream(r3)
            r2 = r0
            java.lang.String r3 = ""
            r2.sourcePath = r3
            android.os.StrictMode$ThreadPolicy$Builder r2 = new android.os.StrictMode$ThreadPolicy$Builder
            r7 = r2
            r2 = r7
            r3 = r7
            r3.<init>()
            android.os.StrictMode$ThreadPolicy$Builder r2 = r2.permitAll()
            android.os.StrictMode$ThreadPolicy r2 = r2.build()
            android.os.StrictMode.setThreadPolicy(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.VideoPlayer.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public final View getView() {
        return this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    @DesignerProperty(defaultValue = "", editorType = "video_asset")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The \"path\" to the video.  Usually, this will be the name of the video file, which should be added in the Designer.")
    public final void Source(String str) {
        StringBuilder sb;
        String str2 = str;
        if (this.IQizCoownq3QMuqjiN3SEtpvqAzYOMGAuQhX0ytYpmBBjgggyq4HRoSxolTbNj90) {
            Bundle fullScreenVideoAction = this.container.$form().fullScreenVideoAction(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SOURCE, this, str2);
            return;
        }
        this.sourcePath = str2 == null ? "" : str2;
        this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb();
        if (this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isPlaying()) {
            this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.stopPlayback();
        }
        this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVideoURI((Uri) null);
        this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.clearAnimation();
        if (this.sourcePath.length() > 0) {
            new StringBuilder("Source path is ");
            int i = Log.i("VideoPlayer", sb.append(this.sourcePath).toString());
            try {
                this.vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ = false;
                MediaUtil.loadVideoView(this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.container.$form(), this.sourcePath);
                int i2 = Log.i("VideoPlayer", "loading video succeeded");
            } catch (PermissionException e) {
                this.container.$form().dispatchPermissionDeniedEvent((Component) this, "Source", e);
            } catch (Exception e2) {
                this.container.$form().dispatchErrorOccurredEvent(this, "Source", ErrorMessages.ERROR_UNABLE_TO_LOAD_MEDIA, this.sourcePath);
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Reports whether the media is playing.")
    public final boolean IsPlaying() {
        return this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isPlaying();
    }

    @SimpleProperty(description = "Returns the current position of the source file that is playing.")
    public final int CurrentPosition() {
        return this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCurrentPosition();
    }

    @SimpleFunction(description = "Starts playback of the video.")
    public final void Start() {
        int i = Log.i("VideoPlayer", "Calling Start");
        if (this.IQizCoownq3QMuqjiN3SEtpvqAzYOMGAuQhX0ytYpmBBjgggyq4HRoSxolTbNj90) {
            Bundle fullScreenVideoAction = this.container.$form().fullScreenVideoAction(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PLAY, this, (Object) null);
        } else if (this.vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ) {
            this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.start();
        } else {
            this.tFNQ38WX0EWx1sBmwkfGOavwj0ohdRyv5RfL5KQ0qa5Pnr6qc7YsZTQu1aP3NtmA = true;
        }
    }

    @DesignerProperty(defaultValue = "50", editorType = "non_negative_float")
    @SimpleProperty(description = "Sets the volume to a number between 0 and 100. Values less than 0 will be treated as 0, and values greater than 100 will be treated as 100.")
    public final void Volume(int i) {
        int min = Math.min(Math.max(i, 0), 100);
        if (this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T != null) {
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setVolume(((float) min) / 100.0f, ((float) min) / 100.0f);
            this.hOhK0kjjpreklHpzajOh4zpZ0hDhUAnmvbGmmElshoJmuxQNkJo9K2Sh2YQvTJN = min;
        }
    }

    @SimpleProperty(description = "Return the volume.")
    public final int Volume() {
        return this.hOhK0kjjpreklHpzajOh4zpZ0hDhUAnmvbGmmElshoJmuxQNkJo9K2Sh2YQvTJN;
    }

    public final void delayedStart() {
        this.tFNQ38WX0EWx1sBmwkfGOavwj0ohdRyv5RfL5KQ0qa5Pnr6qc7YsZTQu1aP3NtmA = true;
        Start();
    }

    @SimpleFunction(description = "Pauses playback of the video.  Playback can be resumed at the same location by calling the <code>Start</code> method.")
    public final void Pause() {
        int i = Log.i("VideoPlayer", "Calling Pause");
        if (this.IQizCoownq3QMuqjiN3SEtpvqAzYOMGAuQhX0ytYpmBBjgggyq4HRoSxolTbNj90) {
            Bundle fullScreenVideoAction = this.container.$form().fullScreenVideoAction(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE, this, (Object) null);
            this.tFNQ38WX0EWx1sBmwkfGOavwj0ohdRyv5RfL5KQ0qa5Pnr6qc7YsZTQu1aP3NtmA = false;
            return;
        }
        this.tFNQ38WX0EWx1sBmwkfGOavwj0ohdRyv5RfL5KQ0qa5Pnr6qc7YsZTQu1aP3NtmA = false;
        this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.pause();
    }

    @SimpleFunction(description = "Resets to start of video and pauses it if video was playing.")
    public final void Stop() {
        int i = Log.i("VideoPlayer", "Calling Stop");
        Start();
        SeekTo(0);
        Pause();
    }

    @SimpleFunction(description = "Seeks to the requested time (specified in milliseconds) in the video. If the video is paused, the frame shown will not be updated by the seek. The player can jump only to key frames in the video, so seeking to times that differ by short intervals may not actually move to different frames.")
    public final void SeekTo(int i) {
        int i2 = i;
        int i3 = Log.i("VideoPlayer", "Calling SeekTo");
        if (i2 < 0) {
            i2 = 0;
        }
        if (this.IQizCoownq3QMuqjiN3SEtpvqAzYOMGAuQhX0ytYpmBBjgggyq4HRoSxolTbNj90) {
            Bundle fullScreenVideoAction = this.container.$form().fullScreenVideoAction(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SEEK, this, Integer.valueOf(i2));
        } else {
            this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.seekTo(i2);
        }
    }

    @SimpleFunction(description = "Returns duration of the video in milliseconds.")
    public final int GetDuration() {
        int i = Log.i("VideoPlayer", "Calling GetDuration");
        if (!this.IQizCoownq3QMuqjiN3SEtpvqAzYOMGAuQhX0ytYpmBBjgggyq4HRoSxolTbNj90) {
            return this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getDuration();
        }
        Bundle fullScreenVideoAction = this.container.$form().fullScreenVideoAction(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_DURATION, this, (Object) null);
        Bundle bundle = fullScreenVideoAction;
        if (fullScreenVideoAction.getBoolean(FullScreenVideoUtil.ACTION_SUCESS)) {
            return bundle.getInt(FullScreenVideoUtil.ACTION_DATA);
        }
        return 0;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        MediaPlayer mediaPlayer2 = mediaPlayer;
        Completed();
    }

    @SimpleEvent
    public final void Completed() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Completed", new Object[0]);
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        StringBuilder sb;
        MediaPlayer mediaPlayer2 = mediaPlayer;
        int i3 = i;
        int i4 = i2;
        this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb();
        this.tFNQ38WX0EWx1sBmwkfGOavwj0ohdRyv5RfL5KQ0qa5Pnr6qc7YsZTQu1aP3NtmA = false;
        this.vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ = false;
        new StringBuilder("onError: what is ");
        int e = Log.e("VideoPlayer", sb.append(i3).append(" 0x").append(Integer.toHexString(i3)).append(", extra is ").append(i4).append(" 0x").append(Integer.toHexString(i4)).toString());
        this.container.$form().dispatchErrorOccurredEvent(this, "Source", ErrorMessages.ERROR_UNABLE_TO_LOAD_MEDIA, this.sourcePath);
        return true;
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        this.vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ = true;
        this.tFNQ38WX0EWx1sBmwkfGOavwj0ohdRyv5RfL5KQ0qa5Pnr6qc7YsZTQu1aP3NtmA = false;
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = mediaPlayer;
        C1080a aVar = this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        C1080a aVar2 = aVar;
        C1080a aVar3 = aVar2;
        aVar2.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
        aVar3.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = Boolean.TRUE;
        aVar3.forceLayout();
        aVar3.invalidate();
        if (this.tFNQ38WX0EWx1sBmwkfGOavwj0ohdRyv5RfL5KQ0qa5Pnr6qc7YsZTQu1aP3NtmA) {
            Start();
        }
    }

    @SimpleEvent(description = "The VideoPlayerError event is no longer used. Please use the Screen.ErrorOccurred event instead.", userVisible = false)
    public final void VideoPlayerError(String str) {
    }

    public final void onDestroy() {
        prepareToDie();
    }

    public final void onDelete() {
        prepareToDie();
    }

    private void prepareToDie() {
        Bundle bundle;
        if (this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isPlaying()) {
            this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.stopPlayback();
        }
        this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVideoURI((Uri) null);
        this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.clearAnimation();
        this.tFNQ38WX0EWx1sBmwkfGOavwj0ohdRyv5RfL5KQ0qa5Pnr6qc7YsZTQu1aP3NtmA = false;
        this.vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ = false;
        if (this.IQizCoownq3QMuqjiN3SEtpvqAzYOMGAuQhX0ytYpmBBjgggyq4HRoSxolTbNj90) {
            new Bundle();
            Bundle bundle2 = bundle;
            bundle2.putBoolean(FullScreenVideoUtil.VIDEOPLAYER_FULLSCREEN, false);
            Bundle fullScreenVideoAction = this.container.$form().fullScreenVideoAction(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_FULLSCREEN, this, bundle2);
        }
    }

    @SimpleProperty
    public final int Width() {
        return super.Width();
    }

    @SimpleProperty(userVisible = true)
    public final void Width(int i) {
        int i2 = i;
        super.Width(i2);
        C1080a aVar = this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        aVar.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(i2, aVar.iz2AUXs4pv4EMA73duiR1R3OfWItF7gDqk3oMKKRO3MIyuqvZdoefifHvTvEAhn);
    }

    @SimpleProperty
    public final int Height() {
        return super.Height();
    }

    @SimpleProperty(userVisible = true)
    public final void Height(int i) {
        int i2 = i;
        super.Height(i2);
        this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.iMWvjaqDlqi8shqdETWDeLkDbaCwtdVfJFSzyvUX79cgwtU4Twvc8XyMVbnGcmik, i2);
    }

    @SimpleProperty
    public final boolean FullScreen() {
        return this.IQizCoownq3QMuqjiN3SEtpvqAzYOMGAuQhX0ytYpmBBjgggyq4HRoSxolTbNj90;
    }

    @SimpleProperty(userVisible = true)
    public final void FullScreen(boolean z) {
        Bundle bundle;
        Bundle bundle2;
        boolean z2 = z;
        if (z2 && Build.VERSION.SDK_INT <= 4) {
            this.container.$form().dispatchErrorOccurredEvent(this, "FullScreen(true)", ErrorMessages.ERROR_VIDEOPLAYER_FULLSCREEN_UNSUPPORTED, new Object[0]);
        } else if (z2 == this.IQizCoownq3QMuqjiN3SEtpvqAzYOMGAuQhX0ytYpmBBjgggyq4HRoSxolTbNj90) {
        } else {
            if (z2) {
                new Bundle();
                Bundle bundle3 = bundle2;
                Bundle bundle4 = bundle3;
                bundle3.putInt(FullScreenVideoUtil.VIDEOPLAYER_POSITION, this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCurrentPosition());
                bundle4.putBoolean(FullScreenVideoUtil.VIDEOPLAYER_PLAYING, this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isPlaying());
                this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.pause();
                bundle4.putBoolean(FullScreenVideoUtil.VIDEOPLAYER_FULLSCREEN, true);
                bundle4.putString(FullScreenVideoUtil.VIDEOPLAYER_SOURCE, this.sourcePath);
                if (this.container.$form().fullScreenVideoAction(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_FULLSCREEN, this, bundle4).getBoolean(FullScreenVideoUtil.ACTION_SUCESS)) {
                    this.IQizCoownq3QMuqjiN3SEtpvqAzYOMGAuQhX0ytYpmBBjgggyq4HRoSxolTbNj90 = true;
                    return;
                }
                this.IQizCoownq3QMuqjiN3SEtpvqAzYOMGAuQhX0ytYpmBBjgggyq4HRoSxolTbNj90 = false;
                this.container.$form().dispatchErrorOccurredEvent(this, "FullScreen", ErrorMessages.ERROR_VIDEOPLAYER_FULLSCREEN_UNAVAILBLE, "");
                return;
            }
            new Bundle();
            Bundle bundle5 = bundle;
            bundle5.putBoolean(FullScreenVideoUtil.VIDEOPLAYER_FULLSCREEN, false);
            Bundle fullScreenVideoAction = this.container.$form().fullScreenVideoAction(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_FULLSCREEN, this, bundle5);
            Bundle bundle6 = fullScreenVideoAction;
            if (fullScreenVideoAction.getBoolean(FullScreenVideoUtil.ACTION_SUCESS)) {
                fullScreenKilled(bundle6);
                return;
            }
            this.IQizCoownq3QMuqjiN3SEtpvqAzYOMGAuQhX0ytYpmBBjgggyq4HRoSxolTbNj90 = true;
            this.container.$form().dispatchErrorOccurredEvent(this, "FullScreen", ErrorMessages.ERROR_VIDEOPLAYER_FULLSCREEN_CANT_EXIT, "");
        }
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "If enabled the user will see the control buttons.")
    public final void ShowControls(boolean z) {
        if (z) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVisibility(0);
        } else {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVisibility(8);
        }
    }

    public final void fullScreenKilled(Bundle bundle) {
        Bundle bundle2 = bundle;
        this.IQizCoownq3QMuqjiN3SEtpvqAzYOMGAuQhX0ytYpmBBjgggyq4HRoSxolTbNj90 = false;
        String string = bundle2.getString(FullScreenVideoUtil.VIDEOPLAYER_SOURCE);
        String str = string;
        if (string != null && !str.equals(this.sourcePath)) {
            Source(str);
        }
        this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVisibility(0);
        this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.requestLayout();
        SeekTo(bundle2.getInt(FullScreenVideoUtil.VIDEOPLAYER_POSITION));
        if (bundle2.getBoolean(FullScreenVideoUtil.VIDEOPLAYER_PLAYING)) {
            Start();
        }
    }

    public final int getPassedWidth() {
        return this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.iMWvjaqDlqi8shqdETWDeLkDbaCwtdVfJFSzyvUX79cgwtU4Twvc8XyMVbnGcmik;
    }

    public final int getPassedHeight() {
        return this.f538hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.iz2AUXs4pv4EMA73duiR1R3OfWItF7gDqk3oMKKRO3MIyuqvZdoefifHvTvEAhn;
    }

    /* renamed from: com.google.appinventor.components.runtime.VideoPlayer$a */
    class C1080a extends VideoView {
        private /* synthetic */ VideoPlayer hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        public int iMWvjaqDlqi8shqdETWDeLkDbaCwtdVfJFSzyvUX79cgwtU4Twvc8XyMVbnGcmik = -1;
        public int iz2AUXs4pv4EMA73duiR1R3OfWItF7gDqk3oMKKRO3MIyuqvZdoefifHvTvEAhn = -1;
        Boolean vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = Boolean.FALSE;
        MediaPlayer wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C1080a(VideoPlayer videoPlayer, Context context) {
            super(context);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = videoPlayer;
        }

        public final void onMeasure(int i, int i2) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i, i2, 0);
        }

        /* access modifiers changed from: private */
        public void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(int i, int i2, int i3) {
            StringBuilder sb;
            StringBuilder sb2;
            StringBuilder sb3;
            StringBuilder sb4;
            StringBuilder sb5;
            StringBuilder sb6;
            Runnable runnable;
            StringBuilder sb7;
            Runnable runnable2;
            int i4 = i;
            int i5 = i2;
            int i6 = i3;
            boolean z = false;
            boolean z2 = false;
            float deviceDensity = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.container.$form().deviceDensity();
            int i7 = Log.i("VideoPlayer..onMeasure", "Device Density = ".concat(String.valueOf(deviceDensity)));
            new StringBuilder("AI setting dimensions as:");
            int i8 = Log.i("VideoPlayer..onMeasure", sb.append(this.iMWvjaqDlqi8shqdETWDeLkDbaCwtdVfJFSzyvUX79cgwtU4Twvc8XyMVbnGcmik).append(":").append(this.iz2AUXs4pv4EMA73duiR1R3OfWItF7gDqk3oMKKRO3MIyuqvZdoefifHvTvEAhn).toString());
            new StringBuilder("Dimenions from super>>");
            int i9 = Log.i("VideoPlayer..onMeasure", sb2.append(View.MeasureSpec.getSize(i4)).append(":").append(View.MeasureSpec.getSize(i5)).toString());
            int i10 = 176;
            int i11 = 144;
            switch (this.iMWvjaqDlqi8shqdETWDeLkDbaCwtdVfJFSzyvUX79cgwtU4Twvc8XyMVbnGcmik) {
                case -2:
                    switch (View.MeasureSpec.getMode(i4)) {
                        case Integer.MIN_VALUE:
                        case Declaration.MODULE_REFERENCE /*1073741824*/:
                            i10 = View.MeasureSpec.getSize(i4);
                            break;
                        case 0:
                            try {
                                i10 = ((View) getParent()).getMeasuredWidth();
                                break;
                            } catch (ClassCastException e) {
                                i10 = 176;
                                break;
                            } catch (NullPointerException e2) {
                                i10 = 176;
                                break;
                            }
                    }
                case -1:
                    if (this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.booleanValue()) {
                        try {
                            i10 = this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.getVideoWidth();
                            int i12 = Log.i("VideoPlayer.onMeasure", "Got width from MediaPlayer>".concat(String.valueOf(i10)));
                            break;
                        } catch (NullPointerException e3) {
                            new StringBuilder("Failed to get MediaPlayer for width:\n");
                            int e4 = Log.e("VideoPlayer..onMeasure", sb3.append(e3.getMessage()).toString());
                            i10 = 176;
                            break;
                        }
                    }
                    break;
                default:
                    z2 = true;
                    i10 = this.iMWvjaqDlqi8shqdETWDeLkDbaCwtdVfJFSzyvUX79cgwtU4Twvc8XyMVbnGcmik;
                    break;
            }
            if (this.iMWvjaqDlqi8shqdETWDeLkDbaCwtdVfJFSzyvUX79cgwtU4Twvc8XyMVbnGcmik <= -1000) {
                int Width = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.container.$form().Width();
                int i13 = Width;
                if (Width != 0 || i6 >= 2) {
                    i10 = (int) (((float) ((i13 * (-(i10 + 1000))) / 100)) * deviceDensity);
                } else {
                    new StringBuilder("Width not stable... trying again (onMeasure ");
                    int d = Log.d("VideoPlayer...onMeasure", sb7.append(i6).append(")").toString());
                    final int i14 = i4;
                    final int i15 = i5;
                    final int i16 = i6;
                    new Runnable(this) {
                        private /* synthetic */ C1080a B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

                        {
                            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r8;
                        }

                        public final void run() {
                            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i14, i15, i16 + 1);
                        }
                    };
                    boolean postDelayed = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.postDelayed(runnable2, 100);
                    setMeasuredDimension(100, 100);
                    return;
                }
            } else if (z2) {
                i10 = (int) (((float) i10) * deviceDensity);
            }
            switch (this.iz2AUXs4pv4EMA73duiR1R3OfWItF7gDqk3oMKKRO3MIyuqvZdoefifHvTvEAhn) {
                case -2:
                    switch (View.MeasureSpec.getMode(i5)) {
                        case Integer.MIN_VALUE:
                        case Declaration.MODULE_REFERENCE /*1073741824*/:
                            i11 = View.MeasureSpec.getSize(i5);
                            break;
                    }
                case -1:
                    if (this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.booleanValue()) {
                        try {
                            i11 = this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.getVideoHeight();
                            int i17 = Log.i("VideoPlayer.onMeasure", "Got height from MediaPlayer>".concat(String.valueOf(i11)));
                            break;
                        } catch (NullPointerException e5) {
                            new StringBuilder("Failed to get MediaPlayer for height:\n");
                            int e6 = Log.e("VideoPlayer..onMeasure", sb4.append(e5.getMessage()).toString());
                            i11 = 144;
                            break;
                        }
                    }
                    break;
                default:
                    z = true;
                    i11 = this.iz2AUXs4pv4EMA73duiR1R3OfWItF7gDqk3oMKKRO3MIyuqvZdoefifHvTvEAhn;
                    break;
            }
            if (this.iz2AUXs4pv4EMA73duiR1R3OfWItF7gDqk3oMKKRO3MIyuqvZdoefifHvTvEAhn <= -1000) {
                int Height = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.container.$form().Height();
                int i18 = Height;
                if (Height != 0 || i6 >= 2) {
                    i11 = (int) (((float) ((i18 * (-(i11 + 1000))) / 100)) * deviceDensity);
                } else {
                    new StringBuilder("Height not stable... trying again (onMeasure ");
                    int d2 = Log.d("VideoPlayer...onMeasure", sb6.append(i6).append(")").toString());
                    final int i19 = i4;
                    final int i20 = i5;
                    final int i21 = i6;
                    new Runnable(this) {
                        private /* synthetic */ C1080a B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

                        {
                            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r8;
                        }

                        public final void run() {
                            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i19, i20, i21 + 1);
                        }
                    };
                    boolean postDelayed2 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.postDelayed(runnable, 100);
                    setMeasuredDimension(100, 100);
                    return;
                }
            } else if (z) {
                i11 = (int) (((float) i11) * deviceDensity);
            }
            new StringBuilder("Setting dimensions to:");
            int i22 = Log.i("VideoPlayer.onMeasure", sb5.append(i10).append("x").append(i11).toString());
            getHolder().setFixedSize(i10, i11);
            setMeasuredDimension(i10, i11);
        }

        public final void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(int i, int i2) {
            this.iMWvjaqDlqi8shqdETWDeLkDbaCwtdVfJFSzyvUX79cgwtU4Twvc8XyMVbnGcmik = i;
            this.iz2AUXs4pv4EMA73duiR1R3OfWItF7gDqk3oMKKRO3MIyuqvZdoefifHvTvEAhn = i2;
            forceLayout();
            invalidate();
        }

        public final void sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb() {
            this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = Boolean.FALSE;
            this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = null;
            forceLayout();
            invalidate();
        }
    }
}
