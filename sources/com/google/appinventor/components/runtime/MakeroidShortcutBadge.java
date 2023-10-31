package com.google.appinventor.components.runtime;

import android.content.Context;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import me.leolin.shortcutbadger.ShortcutBadger;

@DesignerComponent(category = ComponentCategory.EXPERIMENTAL, description = "Shortcut Badge component", iconName = "images/notification.png", nonVisible = true, version = 1)
@UsesLibraries(libraries = "ShortcutBadger.aar, ShortcutBadger.jar")
@SimpleObject
@UsesPermissions(permissionNames = "com.android.launcher.permission.READ_SETTINGS,com.android.launcher.permission.WRITE_SETTINGS,com.android.launcher.permission.INSTALL_SHORTCUT,com.android.launcher.permission.UNINSTALL_SHORTCUT,com.sec.android.provider.badge.permission.READ,com.sec.android.provider.badge.permission.WRITE,com.htc.launcher.permission.READ_SETTINGS,com.htc.launcher.permission.UPDATE_SHORTCUT,com.sonyericsson.home.permission.BROADCAST_BADGE,com.sonymobile.home.permission.PROVIDER_INSERT_BADGE,com.anddoes.launcher.permission.UPDATE_COUNT,com.majeur.launcher.permission.UPDATE_BADGE,com.huawei.android.launcher.permission.CHANGE_BADGE,com.huawei.android.launcher.permission.READ_SETTINGS,com.huawei.android.launcher.permission.WRITE_SETTINGS,android.permission.READ_APP_BADGE,com.oppo.launcher.permission.READ_SETTINGS,com.oppo.launcher.permission.WRITE_SETTINGS,me.everything.badger.permission.BADGE_COUNT_READ,me.everything.badger.permission.BADGE_COUNT_WRITE")
public final class MakeroidShortcutBadge extends AndroidNonvisibleComponent {
    private Context context;
    private int tOvM9eIUWszKzvISo4zKh7g8MyRaLHuNLQ5NdigAhriBDKCBmghUBEUlM2ZIvV1J = 0;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MakeroidShortcutBadge(com.google.appinventor.components.runtime.ComponentContainer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = 0
            r2.tOvM9eIUWszKzvISo4zKh7g8MyRaLHuNLQ5NdigAhriBDKCBmghUBEUlM2ZIvV1J = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidShortcutBadge.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Use this block to apply a notification badge count.")
    public final void ApplyCount(int i) {
        this.tOvM9eIUWszKzvISo4zKh7g8MyRaLHuNLQ5NdigAhriBDKCBmghUBEUlM2ZIvV1J = i;
        boolean applyCount = ShortcutBadger.applyCount(this.context, this.tOvM9eIUWszKzvISo4zKh7g8MyRaLHuNLQ5NdigAhriBDKCBmghUBEUlM2ZIvV1J);
    }

    @SimpleFunction(description = "Use this block to return the notification count.")
    public final int Count() {
        return this.tOvM9eIUWszKzvISo4zKh7g8MyRaLHuNLQ5NdigAhriBDKCBmghUBEUlM2ZIvV1J;
    }

    @SimpleFunction(description = "Use this block to remove the notification badge count.")
    public final void RemoveCount() {
        boolean removeCount = ShortcutBadger.removeCount(this.context);
    }
}
