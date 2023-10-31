package android.support.p000v4.media.session;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.annotation.RestrictTo;
import android.support.p000v4.media.MediaBrowserCompat;
import android.support.p000v4.media.MediaBrowserServiceCompat;
import android.util.Log;
import android.view.KeyEvent;
import java.util.List;

/* renamed from: android.support.v4.media.session.MediaButtonReceiver */
public class MediaButtonReceiver extends BroadcastReceiver {
    private static final String TAG = "MediaButtonReceiver";

    public MediaButtonReceiver() {
    }

    public void onReceive(Context context, Intent intent) {
        StringBuilder sb;
        Throwable th;
        MediaButtonConnectionCallback mediaButtonConnectionCallback;
        MediaBrowserCompat mediaBrowserCompat;
        Context context2 = context;
        Intent intent2 = intent;
        if (intent2 == null || !"android.intent.action.MEDIA_BUTTON".equals(intent2.getAction()) || !intent2.hasExtra("android.intent.extra.KEY_EVENT")) {
            new StringBuilder();
            int d = Log.d(TAG, sb.append("Ignore unsupported intent: ").append(intent2).toString());
            return;
        }
        ComponentName mediaButtonServiceComponentName = getServiceComponentByAction(context2, "android.intent.action.MEDIA_BUTTON");
        if (mediaButtonServiceComponentName != null) {
            Intent component = intent2.setComponent(mediaButtonServiceComponentName);
            startForegroundService(context2, intent2);
            return;
        }
        ComponentName mediaBrowserServiceComponentName = getServiceComponentByAction(context2, MediaBrowserServiceCompat.SERVICE_INTERFACE);
        if (mediaBrowserServiceComponentName != null) {
            BroadcastReceiver.PendingResult pendingResult = goAsync();
            Context applicationContext = context2.getApplicationContext();
            new MediaButtonConnectionCallback(applicationContext, intent2, pendingResult);
            MediaButtonConnectionCallback connectionCallback = mediaButtonConnectionCallback;
            new MediaBrowserCompat(applicationContext, mediaBrowserServiceComponentName, connectionCallback, (Bundle) null);
            MediaBrowserCompat mediaBrowser = mediaBrowserCompat;
            connectionCallback.setMediaBrowser(mediaBrowser);
            mediaBrowser.connect();
            return;
        }
        Throwable th2 = th;
        new IllegalStateException("Could not find any Service that handles android.intent.action.MEDIA_BUTTON or implements a media browser service.");
        throw th2;
    }

    /* renamed from: android.support.v4.media.session.MediaButtonReceiver$MediaButtonConnectionCallback */
    private static class MediaButtonConnectionCallback extends MediaBrowserCompat.ConnectionCallback {
        private final Context mContext;
        private final Intent mIntent;
        private MediaBrowserCompat mMediaBrowser;
        private final BroadcastReceiver.PendingResult mPendingResult;

        MediaButtonConnectionCallback(Context context, Intent intent, BroadcastReceiver.PendingResult pendingResult) {
            this.mContext = context;
            this.mIntent = intent;
            this.mPendingResult = pendingResult;
        }

        /* access modifiers changed from: package-private */
        public void setMediaBrowser(MediaBrowserCompat mediaBrowser) {
            MediaBrowserCompat mediaBrowserCompat = mediaBrowser;
            this.mMediaBrowser = mediaBrowserCompat;
        }

        public void onConnected() {
            MediaControllerCompat mediaController;
            try {
                new MediaControllerCompat(this.mContext, this.mMediaBrowser.getSessionToken());
                boolean dispatchMediaButtonEvent = mediaController.dispatchMediaButtonEvent((KeyEvent) this.mIntent.getParcelableExtra("android.intent.extra.KEY_EVENT"));
            } catch (RemoteException e) {
                int e2 = Log.e(MediaButtonReceiver.TAG, "Failed to create a media controller", e);
            }
            finish();
        }

        public void onConnectionSuspended() {
            finish();
        }

        public void onConnectionFailed() {
            finish();
        }

        private void finish() {
            this.mMediaBrowser.disconnect();
            this.mPendingResult.finish();
        }
    }

    public static KeyEvent handleIntent(MediaSessionCompat mediaSessionCompat, Intent intent) {
        MediaSessionCompat mediaSessionCompat2 = mediaSessionCompat;
        Intent intent2 = intent;
        if (mediaSessionCompat2 == null || intent2 == null || !"android.intent.action.MEDIA_BUTTON".equals(intent2.getAction()) || !intent2.hasExtra("android.intent.extra.KEY_EVENT")) {
            return null;
        }
        KeyEvent ke = (KeyEvent) intent2.getParcelableExtra("android.intent.extra.KEY_EVENT");
        boolean dispatchMediaButtonEvent = mediaSessionCompat2.getController().dispatchMediaButtonEvent(ke);
        return ke;
    }

    public static PendingIntent buildMediaButtonPendingIntent(Context context, long j) {
        Context context2 = context;
        long action = j;
        ComponentName mbrComponent = getMediaButtonReceiverComponent(context2);
        if (mbrComponent != null) {
            return buildMediaButtonPendingIntent(context2, mbrComponent, action);
        }
        int w = Log.w(TAG, "A unique media button receiver could not be found in the given context, so couldn't build a pending intent.");
        return null;
    }

    public static PendingIntent buildMediaButtonPendingIntent(Context context, ComponentName componentName, long j) {
        Intent intent;
        Parcelable parcelable;
        StringBuilder sb;
        Context context2 = context;
        ComponentName mbrComponent = componentName;
        long action = j;
        if (mbrComponent == null) {
            int w = Log.w(TAG, "The component name of media button receiver should be provided.");
            return null;
        }
        int keyCode = PlaybackStateCompat.toKeyCode(action);
        if (keyCode == 0) {
            new StringBuilder();
            int w2 = Log.w(TAG, sb.append("Cannot build a media button pending intent with the given action: ").append(action).toString());
            return null;
        }
        new Intent("android.intent.action.MEDIA_BUTTON");
        Intent intent2 = intent;
        Intent component = intent2.setComponent(mbrComponent);
        new KeyEvent(0, keyCode);
        Intent putExtra = intent2.putExtra("android.intent.extra.KEY_EVENT", parcelable);
        return PendingIntent.getBroadcast(context2, keyCode, intent2, 0);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static ComponentName getMediaButtonReceiverComponent(Context context) {
        Intent intent;
        ComponentName componentName;
        Context context2 = context;
        new Intent("android.intent.action.MEDIA_BUTTON");
        Intent queryIntent = intent;
        Intent intent2 = queryIntent.setPackage(context2.getPackageName());
        List<ResolveInfo> resolveInfos = context2.getPackageManager().queryBroadcastReceivers(queryIntent, 0);
        if (resolveInfos.size() == 1) {
            ResolveInfo resolveInfo = resolveInfos.get(0);
            new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
            return componentName;
        }
        if (resolveInfos.size() > 1) {
            int w = Log.w(TAG, "More than one BroadcastReceiver that handles android.intent.action.MEDIA_BUTTON was found, returning null.");
        }
        return null;
    }

    private static void startForegroundService(Context context, Intent intent) {
        Context context2 = context;
        Intent intent2 = intent;
        if (Build.VERSION.SDK_INT >= 26) {
            ComponentName startForegroundService = context2.startForegroundService(intent2);
        } else {
            ComponentName startService = context2.startService(intent2);
        }
    }

    private static ComponentName getServiceComponentByAction(Context context, String str) {
        Intent intent;
        Throwable th;
        StringBuilder sb;
        ComponentName componentName;
        Context context2 = context;
        String action = str;
        PackageManager pm = context2.getPackageManager();
        new Intent(action);
        Intent queryIntent = intent;
        Intent intent2 = queryIntent.setPackage(context2.getPackageName());
        List<ResolveInfo> resolveInfos = pm.queryIntentServices(queryIntent, 0);
        if (resolveInfos.size() == 1) {
            ResolveInfo resolveInfo = resolveInfos.get(0);
            new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
            return componentName;
        } else if (resolveInfos.isEmpty()) {
            return null;
        } else {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Expected 1 service that handles ").append(action).append(", found ").append(resolveInfos.size()).toString());
            throw th2;
        }
    }
}
