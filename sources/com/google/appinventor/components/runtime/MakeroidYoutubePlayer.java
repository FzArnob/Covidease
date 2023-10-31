package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.ui.PlayerUIController;
import com.pierfrancescosoffritti.androidyoutubeplayer.utils.YouTubePlayerTracker;

@DesignerComponent(androidMinSdk = 17, category = ComponentCategory.GOOGLE, description = "", iconName = "images/youtubePlayer.png", version = 2)
@UsesLibraries(libraries = "youtube-player.jar, youtube-player.aar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public class MakeroidYoutubePlayer extends AndroidViewComponent {
    private int DQyzcUACK1E7EOZ6u3x9ArvpeLM1Qtq6g7Sbq12R8AspY4PE6CpW0lfACU69Eund = 50;
    private boolean IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt = false;
    private float YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u = 0.0f;
    private ComponentContainer container;
    private Context context;
    private String gmm7XQFeiJoCy8yWhIBdyNlesqTvuP4UyGLNuJ8TYBJTJ249gDjP9OHTkIprzI7;
    private YouTubePlayer hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private YouTubePlayerView f498hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private PlayerUIController f499hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private YouTubePlayerTracker f500hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    static /* synthetic */ YouTubePlayer hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidYoutubePlayer makeroidYoutubePlayer, YouTubePlayer youTubePlayer) {
        YouTubePlayer youTubePlayer2 = youTubePlayer;
        YouTubePlayer youTubePlayer3 = youTubePlayer2;
        makeroidYoutubePlayer.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = youTubePlayer3;
        return youTubePlayer2;
    }

    static /* synthetic */ PlayerUIController hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidYoutubePlayer makeroidYoutubePlayer, PlayerUIController playerUIController) {
        PlayerUIController playerUIController2 = playerUIController;
        PlayerUIController playerUIController3 = playerUIController2;
        makeroidYoutubePlayer.f499hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = playerUIController3;
        return playerUIController2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MakeroidYoutubePlayer(com.google.appinventor.components.runtime.ComponentContainer r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = 50
            r2.DQyzcUACK1E7EOZ6u3x9ArvpeLM1Qtq6g7Sbq12R8AspY4PE6CpW0lfACU69Eund = r3
            r2 = r0
            r3 = 0
            r2.YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u = r3
            r2 = r0
            r3 = 0
            r2.IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt = r3
            r2 = r0
            r3 = r1
            r2.container = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView r3 = new com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r0
            android.content.Context r5 = r5.context
            r4.<init>(r5)
            r2.f498hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            com.pierfrancescosoffritti.androidyoutubeplayer.utils.YouTubePlayerTracker r3 = new com.pierfrancescosoffritti.androidyoutubeplayer.utils.YouTubePlayerTracker
            r6 = r3
            r3 = r6
            r4 = r6
            r4.<init>()
            r2.f500hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView r2 = r2.f498hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.MakeroidYoutubePlayer$1 r3 = new com.google.appinventor.components.runtime.MakeroidYoutubePlayer$1
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r0
            r4.<init>(r5)
            r4 = 1
            r2.initialize(r3, r4)
            r2 = r1
            r3 = r0
            r2.$add(r3)
            r2 = r0
            r3 = 0
            r2.StartSecond(r3)
            r2 = r0
            r3 = 50
            r2.Volume(r3)
            r2 = r0
            r3 = 0
            r2.EnableLiveVideoUI(r3)
            java.lang.String r2 = "Makeroid Youtube Player"
            java.lang.String r3 = "Makeroid Youtube Player Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidYoutubePlayer.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public YouTubePlayerView getView() {
        return this.f498hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    @SimpleProperty
    public void Width(int i) {
        int i2 = i;
        if (i2 == -1) {
            i2 = -2;
        }
        super.Width(i2);
    }

    @SimpleEvent(description = "Event to get notified when the player enters or exits fullscreen. The variable 'fullscreen' returns true or false.")
    public void Fullscreen(boolean z) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Fullscreen", Boolean.valueOf(z));
    }

    @SimpleEvent(description = "Use this event to start the playing of a normal or instant youtube video.")
    public void Initialized() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Initialized", new Object[0]);
    }

    @SimpleEvent(description = "Use this event to detect that the state changes. Return values: 'UNKNOWN', 'UNSTARTED', 'ENDED', 'PLAYING', 'PAUSED', 'BUFFERING' or 'VIDEO_CUED'.")
    public void StateChanged(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "StateChanged", str);
    }

    @SimpleEvent(description = "Use this event to detect that the playback quality was changed. Return values: 'UNKNOWN', 'SMALL', 'MEDIUM', 'LARGE', 'HD720', 'HD1080', 'HIGH_RES' or 'DEFAULT'.")
    public void PlaybackQualityChanged(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "PlaybackQualityChanged", str);
    }

    @SimpleEvent(description = "Use this event to detect that the playback rate was changed. Return values: 'UNKNOWN', 'RATE_0_25', 'RATE_0_5', 'RATE_1', 'RATE_1_5' or 'RATE_2'.")
    public void PlaybackRateChanged(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "PlaybackRateChanged", str);
    }

    @SimpleEvent(description = "Use this event to detect that there was any error with the player. Return values: 'UNKNOWN', 'INVALID_PARAMETER_IN_REQUEST', 'HTML_5_PLAYER', 'VIDEO_NOT_FOUND', 'VIDEO_NOT_PLAYABLE_IN_EMBEDDED_PLAYER', 'INVALID_VOLUME' or 'INVALID_SEEK_TO'.")
    public void Error(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Error", str);
    }

    @SimpleFunction(description = "Use this block together with the 'Youtube Player' Initialized event. Loads and automatically plays the specified youtube video. Use only as example '_bZj-LOXdH8' from a youtube video.")
    public void InstantLoad(String str) {
        String str2 = str;
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.loadVideo(vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(str2), this.YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u);
        }
    }

    @SimpleFunction(description = "Loads the specified video's thumbnail and prepares the player to play the video. Does not automatically play the video. Use only as example '_bZj-LOXdH8' from a youtube video.")
    public void Load(String str) {
        String str2 = str;
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.cueVideo(vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(str2), this.YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u);
        }
    }

    @SimpleFunction(description = "Plays the youtube video.")
    public void Play() {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.play();
        }
    }

    @SimpleFunction(description = "Pause the youtube video.")
    public void Pause() {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.pause();
        }
    }

    @DesignerProperty(defaultValue = "0", editorType = "non_negative_float")
    @SimpleProperty(description = "The time from which the video should start playing.")
    public void StartSecond(float f) {
        float f2 = f;
        this.YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u = f2;
        this.gmm7XQFeiJoCy8yWhIBdyNlesqTvuP4UyGLNuJ8TYBJTJ249gDjP9OHTkIprzI7 = String.valueOf(f2);
    }

    @SimpleProperty
    public float StartSecond() {
        return this.YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u;
    }

    @SimpleFunction(description = "Set a position where the youtube video should start playing in seconds.")
    public void SeekTo(float f) {
        float f2 = f;
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            try {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.seekTo(f2);
            } catch (Exception e) {
                int e2 = Log.e("Makeroid Youtube Player", String.valueOf(e));
                Error("INVALID_SEEK_TO");
            }
        }
    }

    @DesignerProperty(defaultValue = "50", editorType = "integer")
    @SimpleProperty(description = "Set the volume to a number between 0 and 100.Use only integer numbers.")
    public void Volume(int i) {
        StringBuilder sb;
        int i2 = i;
        if (i2 > 100 || i2 < 0) {
            Error("INVALID_VOLUME");
        } else if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            new StringBuilder();
            this.gmm7XQFeiJoCy8yWhIBdyNlesqTvuP4UyGLNuJ8TYBJTJ249gDjP9OHTkIprzI7 = sb.append(this.YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u).toString();
            this.DQyzcUACK1E7EOZ6u3x9ArvpeLM1Qtq6g7Sbq12R8AspY4PE6CpW0lfACU69Eund = i2;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVolume(i2);
        }
    }

    @SimpleProperty(description = "Return the player volume.")
    public int Volume() {
        return this.DQyzcUACK1E7EOZ6u3x9ArvpeLM1Qtq6g7Sbq12R8AspY4PE6CpW0lfACU69Eund;
    }

    @SimpleProperty(description = "Returns true if the player is in fullscreen mode.")
    public boolean IsFullscreen() {
        return this.f498hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isFullScreen();
    }

    @SimpleFunction(description = "Toggle the state of the video player.")
    public void ToggleFullscreen() {
        this.f498hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.toggleFullScreen();
    }

    @SimpleFunction(description = "Enter the video in fullscreen mode.")
    public void EnterFullscreen() {
        this.f498hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.enterFullScreen();
    }

    @SimpleFunction(description = "Exit the video from fullscreen mode.")
    public void ExitFullscreen() {
        this.f498hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.exitFullScreen();
    }

    @SimpleProperty(description = "Returns the current second.")
    public float GetCurrentSecond() {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            return this.f500hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCurrentSecond();
        }
        return 0.0f;
    }

    @SimpleProperty(description = "Returns the video duration in seconds.")
    public float GetVideoDuration() {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            return this.f500hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getVideoDuration();
        }
        return 0.0f;
    }

    @SimpleProperty(description = "Use this block to test the youtube player. Powered by Kodular.io")
    public String TestVideoId() {
        return "_bZj-LOXdH8";
    }

    @SimpleFunction(description = "This block will return the thumbnail image path from a video id. Use only as example '_bZj-LOXdH8' from a youtube video.")
    public String GetThumbnailFromVideoId(String str) {
        StringBuilder sb;
        new StringBuilder("https://img.youtube.com/vi/");
        return sb.append(vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(str)).append("/0.jpg").toString();
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public void EnableLiveVideoUI(boolean z) {
        boolean z2 = z;
        this.IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt = z2;
        if (this.f499hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.f499hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.enableLiveVideoUI(z2);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If you want to play live videos you must setup the UI accordingly, by calling this method. If enabled, the user can not select a second on the video progress bar.")
    public boolean EnableLiveVideoUI() {
        return this.IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt;
    }

    private static String vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(String str) {
        String str2 = str;
        CharSequence charSequence = "https://www.youtube.com/watch?v=";
        CharSequence charSequence2 = "www.youtube.com/watch?v=";
        if (str2.contains(charSequence)) {
            return str2.replace(charSequence, "");
        }
        if (str2.contains(charSequence2)) {
            return str2.replace(charSequence2, "");
        }
        return str2;
    }
}
