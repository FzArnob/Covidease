package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.media.AudioManager;
import android.net.http.SslError;
import android.view.Display;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.Player;

public class FroyoUtil {
    private FroyoUtil() {
    }

    public static int getRotation(Display display) {
        return display.getRotation();
    }

    public static AudioManager setAudioManager(Activity activity) {
        return (AudioManager) activity.getSystemService("audio");
    }

    public static Object setAudioFocusChangeListener(Player player) {
        Object obj;
        final Player player2 = player;
        new AudioManager.OnAudioFocusChangeListener() {
            private boolean HThm2UFiN8mNIb2OEGwVoJpkwNLFwrt8FHi6kSBOC6T1EOtocK0hkK9xDo2LJOJZ = false;

            public final void onAudioFocusChange(int i) {
                switch (i) {
                    case -3:
                    case -2:
                        if (player2 != null && player2.playerState == Player.State.PLAYING) {
                            player2.pause();
                            this.HThm2UFiN8mNIb2OEGwVoJpkwNLFwrt8FHi6kSBOC6T1EOtocK0hkK9xDo2LJOJZ = true;
                            return;
                        }
                        return;
                    case -1:
                        this.HThm2UFiN8mNIb2OEGwVoJpkwNLFwrt8FHi6kSBOC6T1EOtocK0hkK9xDo2LJOJZ = false;
                        player2.OtherPlayerStarted();
                        return;
                    case 1:
                        if (player2 != null && this.HThm2UFiN8mNIb2OEGwVoJpkwNLFwrt8FHi6kSBOC6T1EOtocK0hkK9xDo2LJOJZ && player2.playerState == Player.State.PAUSED_BY_EVENT) {
                            player2.Start();
                            this.HThm2UFiN8mNIb2OEGwVoJpkwNLFwrt8FHi6kSBOC6T1EOtocK0hkK9xDo2LJOJZ = false;
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
        return obj;
    }

    public static boolean focusRequestGranted(AudioManager audioManager, Object obj) {
        return audioManager.requestAudioFocus((AudioManager.OnAudioFocusChangeListener) obj, 3, 1) == 1;
    }

    public static void abandonFocus(AudioManager audioManager, Object obj) {
        int abandonAudioFocus = audioManager.abandonAudioFocus((AudioManager.OnAudioFocusChangeListener) obj);
    }

    public static WebViewClient getWebViewClient(boolean z, boolean z2, Form form, Component component) {
        WebViewClient webViewClient;
        final boolean z3 = z2;
        final boolean z4 = z;
        final Form form2 = form;
        final Component component2 = component;
        new WebViewClient() {
            public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
                WebView webView2 = webView;
                String str2 = str;
                return !z3;
            }

            public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                WebView webView2 = webView;
                SslErrorHandler sslErrorHandler2 = sslErrorHandler;
                SslError sslError2 = sslError;
                if (z4) {
                    sslErrorHandler2.proceed();
                    return;
                }
                sslErrorHandler2.cancel();
                form2.dispatchErrorOccurredEvent(component2, "WebView", ErrorMessages.ERROR_WEBVIEW_SSL_ERROR, new Object[0]);
            }
        };
        return webViewClient;
    }
}
