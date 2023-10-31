package android.support.customtabs;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.customtabs.CustomTabsSessionToken;
import android.widget.RemoteViews;
import java.util.List;

public final class CustomTabsSession {
    private static final String TAG = "CustomTabsSession";
    private final ICustomTabsCallback mCallback;
    private final ComponentName mComponentName;
    private final Object mLock;
    private final ICustomTabsService mService;

    @VisibleForTesting
    @NonNull
    public static CustomTabsSession createMockSessionForTesting(@NonNull ComponentName componentName) {
        CustomTabsSession customTabsSession;
        ICustomTabsCallback iCustomTabsCallback;
        new CustomTabsSessionToken.MockCallback();
        new CustomTabsSession((ICustomTabsService) null, iCustomTabsCallback, componentName);
        return customTabsSession;
    }

    CustomTabsSession(ICustomTabsService service, ICustomTabsCallback callback, ComponentName componentName) {
        Object obj;
        new Object();
        this.mLock = obj;
        this.mService = service;
        this.mCallback = callback;
        this.mComponentName = componentName;
    }

    public boolean mayLaunchUrl(Uri url, Bundle extras, List<Bundle> otherLikelyBundles) {
        try {
            return this.mService.mayLaunchUrl(this.mCallback, url, extras, otherLikelyBundles);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            return false;
        }
    }

    public boolean setActionButton(@NonNull Bitmap icon, @NonNull String description) {
        Bundle bundle;
        Bundle bundle2;
        new Bundle();
        Bundle bundle3 = bundle;
        bundle3.putParcelable(CustomTabsIntent.KEY_ICON, icon);
        bundle3.putString(CustomTabsIntent.KEY_DESCRIPTION, description);
        new Bundle();
        Bundle metaBundle = bundle2;
        metaBundle.putBundle(CustomTabsIntent.EXTRA_ACTION_BUTTON_BUNDLE, bundle3);
        try {
            return this.mService.updateVisuals(this.mCallback, metaBundle);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            return false;
        }
    }

    public boolean setSecondaryToolbarViews(@Nullable RemoteViews remoteViews, @Nullable int[] clickableIDs, @Nullable PendingIntent pendingIntent) {
        Bundle bundle;
        new Bundle();
        Bundle bundle2 = bundle;
        bundle2.putParcelable(CustomTabsIntent.EXTRA_REMOTEVIEWS, remoteViews);
        bundle2.putIntArray(CustomTabsIntent.EXTRA_REMOTEVIEWS_VIEW_IDS, clickableIDs);
        bundle2.putParcelable(CustomTabsIntent.EXTRA_REMOTEVIEWS_PENDINGINTENT, pendingIntent);
        try {
            return this.mService.updateVisuals(this.mCallback, bundle2);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            return false;
        }
    }

    @Deprecated
    public boolean setToolbarItem(int id, @NonNull Bitmap icon, @NonNull String description) {
        Bundle bundle;
        Bundle bundle2;
        new Bundle();
        Bundle bundle3 = bundle;
        bundle3.putInt(CustomTabsIntent.KEY_ID, id);
        bundle3.putParcelable(CustomTabsIntent.KEY_ICON, icon);
        bundle3.putString(CustomTabsIntent.KEY_DESCRIPTION, description);
        new Bundle();
        Bundle metaBundle = bundle2;
        metaBundle.putBundle(CustomTabsIntent.EXTRA_ACTION_BUTTON_BUNDLE, bundle3);
        try {
            return this.mService.updateVisuals(this.mCallback, metaBundle);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            return false;
        }
    }

    public boolean requestPostMessageChannel(Uri postMessageOrigin) {
        try {
            return this.mService.requestPostMessageChannel(this.mCallback, postMessageOrigin);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            return false;
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int postMessage(java.lang.String r12, android.os.Bundle r13) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r6 = r0
            java.lang.Object r6 = r6.mLock
            r10 = r6
            r6 = r10
            r7 = r10
            r3 = r7
            monitor-enter(r6)
            r6 = r0
            android.support.customtabs.ICustomTabsService r6 = r6.mService     // Catch:{ RemoteException -> 0x001b }
            r7 = r0
            android.support.customtabs.ICustomTabsCallback r7 = r7.mCallback     // Catch:{ RemoteException -> 0x001b }
            r8 = r1
            r9 = r2
            int r6 = r6.postMessage(r7, r8, r9)     // Catch:{ RemoteException -> 0x001b }
            r7 = r3
            monitor-exit(r7)     // Catch:{ all -> 0x0022 }
            r0 = r6
        L_0x001a:
            return r0
        L_0x001b:
            r6 = move-exception
            r4 = r6
            r6 = -2
            r7 = r3
            monitor-exit(r7)     // Catch:{ all -> 0x0022 }
            r0 = r6
            goto L_0x001a
        L_0x0022:
            r6 = move-exception
            r5 = r6
            r6 = r3
            monitor-exit(r6)     // Catch:{ all -> 0x0022 }
            r6 = r5
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.customtabs.CustomTabsSession.postMessage(java.lang.String, android.os.Bundle):int");
    }

    public boolean validateRelationship(int i, @NonNull Uri uri, @Nullable Bundle bundle) {
        int relation = i;
        Uri origin = uri;
        Bundle extras = bundle;
        if (relation < 1 || relation > 2) {
            return false;
        }
        try {
            return this.mService.validateRelationship(this.mCallback, relation, origin, extras);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public IBinder getBinder() {
        return this.mCallback.asBinder();
    }

    /* access modifiers changed from: package-private */
    public ComponentName getComponentName() {
        return this.mComponentName;
    }
}
