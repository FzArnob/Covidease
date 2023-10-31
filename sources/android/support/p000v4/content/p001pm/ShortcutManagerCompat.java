package android.support.p000v4.content.p001pm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ShortcutManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

/* renamed from: android.support.v4.content.pm.ShortcutManagerCompat */
public class ShortcutManagerCompat {
    @VisibleForTesting
    static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
    @VisibleForTesting
    static final String INSTALL_SHORTCUT_PERMISSION = "com.android.launcher.permission.INSTALL_SHORTCUT";

    private ShortcutManagerCompat() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isRequestPinShortcutSupported(@android.support.annotation.NonNull android.content.Context r9) {
        /*
            r0 = r9
            int r4 = android.os.Build.VERSION.SDK_INT
            r5 = 26
            if (r4 < r5) goto L_0x0016
            r4 = r0
            java.lang.Class<android.content.pm.ShortcutManager> r5 = android.content.pm.ShortcutManager.class
            java.lang.Object r4 = r4.getSystemService(r5)
            android.content.pm.ShortcutManager r4 = (android.content.pm.ShortcutManager) r4
            boolean r4 = r4.isRequestPinShortcutSupported()
            r0 = r4
        L_0x0015:
            return r0
        L_0x0016:
            r4 = r0
            java.lang.String r5 = "com.android.launcher.permission.INSTALL_SHORTCUT"
            int r4 = android.support.p000v4.content.ContextCompat.checkSelfPermission(r4, r5)
            if (r4 == 0) goto L_0x0023
            r4 = 0
            r0 = r4
            goto L_0x0015
        L_0x0023:
            r4 = r0
            android.content.pm.PackageManager r4 = r4.getPackageManager()
            android.content.Intent r5 = new android.content.Intent
            r8 = r5
            r5 = r8
            r6 = r8
            java.lang.String r7 = "com.android.launcher.action.INSTALL_SHORTCUT"
            r6.<init>(r7)
            r6 = 0
            java.util.List r4 = r4.queryBroadcastReceivers(r5, r6)
            java.util.Iterator r4 = r4.iterator()
            r1 = r4
        L_0x003d:
            r4 = r1
            boolean r4 = r4.hasNext()
            if (r4 == 0) goto L_0x0067
            r4 = r1
            java.lang.Object r4 = r4.next()
            android.content.pm.ResolveInfo r4 = (android.content.pm.ResolveInfo) r4
            r2 = r4
            r4 = r2
            android.content.pm.ActivityInfo r4 = r4.activityInfo
            java.lang.String r4 = r4.permission
            r3 = r4
            r4 = r3
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L_0x0063
            java.lang.String r4 = "com.android.launcher.permission.INSTALL_SHORTCUT"
            r5 = r3
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0066
        L_0x0063:
            r4 = 1
            r0 = r4
            goto L_0x0015
        L_0x0066:
            goto L_0x003d
        L_0x0067:
            r4 = 0
            r0 = r4
            goto L_0x0015
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.content.p001pm.ShortcutManagerCompat.isRequestPinShortcutSupported(android.content.Context):boolean");
    }

    public static boolean requestPinShortcut(@NonNull Context context, @NonNull ShortcutInfoCompat shortcutInfoCompat, @Nullable IntentSender intentSender) {
        Intent intent;
        BroadcastReceiver broadcastReceiver;
        Context context2 = context;
        ShortcutInfoCompat shortcut = shortcutInfoCompat;
        IntentSender callback = intentSender;
        if (Build.VERSION.SDK_INT >= 26) {
            return ((ShortcutManager) context2.getSystemService(ShortcutManager.class)).requestPinShortcut(shortcut.toShortcutInfo(), callback);
        }
        if (!isRequestPinShortcutSupported(context2)) {
            return false;
        }
        new Intent(ACTION_INSTALL_SHORTCUT);
        Intent intent2 = shortcut.addToIntent(intent);
        if (callback == null) {
            context2.sendBroadcast(intent2);
            return true;
        }
        final IntentSender intentSender2 = callback;
        new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                Intent intent2 = intent;
                try {
                    intentSender2.sendIntent(context, 0, (Intent) null, (IntentSender.OnFinished) null, (Handler) null);
                } catch (IntentSender.SendIntentException e) {
                    IntentSender.SendIntentException sendIntentException = e;
                }
            }
        };
        context2.sendOrderedBroadcast(intent2, (String) null, broadcastReceiver, (Handler) null, -1, (String) null, (Bundle) null);
        return true;
    }

    @NonNull
    public static Intent createShortcutResultIntent(@NonNull Context context, @NonNull ShortcutInfoCompat shortcutInfoCompat) {
        Intent intent;
        Context context2 = context;
        ShortcutInfoCompat shortcut = shortcutInfoCompat;
        Intent result = null;
        if (Build.VERSION.SDK_INT >= 26) {
            result = ((ShortcutManager) context2.getSystemService(ShortcutManager.class)).createShortcutResultIntent(shortcut.toShortcutInfo());
        }
        if (result == null) {
            new Intent();
            result = intent;
        }
        return shortcut.addToIntent(result);
    }
}
