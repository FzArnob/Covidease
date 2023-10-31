package android.support.p003v7.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.widget.TintContextWrapper */
public class TintContextWrapper extends ContextWrapper {
    private static final Object CACHE_LOCK;
    private static ArrayList<WeakReference<TintContextWrapper>> sCache;
    private final Resources mResources;
    private final Resources.Theme mTheme;

    static {
        Object obj;
        new Object();
        CACHE_LOCK = obj;
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [android.content.Context] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.Context wrap(@android.support.annotation.NonNull android.content.Context r11) {
        /*
            r0 = r11
            r6 = r0
            boolean r6 = shouldWrap(r6)
            if (r6 == 0) goto L_0x00a3
            java.lang.Object r6 = CACHE_LOCK
            r10 = r6
            r6 = r10
            r7 = r10
            r1 = r7
            monitor-enter(r6)
            java.util.ArrayList<java.lang.ref.WeakReference<android.support.v7.widget.TintContextWrapper>> r6 = sCache     // Catch:{ all -> 0x009d }
            if (r6 != 0) goto L_0x003b
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x009d }
            r10 = r6
            r6 = r10
            r7 = r10
            r7.<init>()     // Catch:{ all -> 0x009d }
            sCache = r6     // Catch:{ all -> 0x009d }
        L_0x001d:
            android.support.v7.widget.TintContextWrapper r6 = new android.support.v7.widget.TintContextWrapper     // Catch:{ all -> 0x009d }
            r10 = r6
            r6 = r10
            r7 = r10
            r8 = r0
            r7.<init>(r8)     // Catch:{ all -> 0x009d }
            r2 = r6
            java.util.ArrayList<java.lang.ref.WeakReference<android.support.v7.widget.TintContextWrapper>> r6 = sCache     // Catch:{ all -> 0x009d }
            java.lang.ref.WeakReference r7 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x009d }
            r10 = r7
            r7 = r10
            r8 = r10
            r9 = r2
            r8.<init>(r9)     // Catch:{ all -> 0x009d }
            boolean r6 = r6.add(r7)     // Catch:{ all -> 0x009d }
            r6 = r2
            r7 = r1
            monitor-exit(r7)     // Catch:{ all -> 0x009d }
            r0 = r6
        L_0x003a:
            return r0
        L_0x003b:
            java.util.ArrayList<java.lang.ref.WeakReference<android.support.v7.widget.TintContextWrapper>> r6 = sCache     // Catch:{ all -> 0x009d }
            int r6 = r6.size()     // Catch:{ all -> 0x009d }
            r7 = 1
            int r6 = r6 + -1
            r2 = r6
        L_0x0045:
            r6 = r2
            if (r6 < 0) goto L_0x0066
            java.util.ArrayList<java.lang.ref.WeakReference<android.support.v7.widget.TintContextWrapper>> r6 = sCache     // Catch:{ all -> 0x009d }
            r7 = r2
            java.lang.Object r6 = r6.get(r7)     // Catch:{ all -> 0x009d }
            java.lang.ref.WeakReference r6 = (java.lang.ref.WeakReference) r6     // Catch:{ all -> 0x009d }
            r3 = r6
            r6 = r3
            if (r6 == 0) goto L_0x005c
            r6 = r3
            java.lang.Object r6 = r6.get()     // Catch:{ all -> 0x009d }
            if (r6 != 0) goto L_0x0063
        L_0x005c:
            java.util.ArrayList<java.lang.ref.WeakReference<android.support.v7.widget.TintContextWrapper>> r6 = sCache     // Catch:{ all -> 0x009d }
            r7 = r2
            java.lang.Object r6 = r6.remove(r7)     // Catch:{ all -> 0x009d }
        L_0x0063:
            int r2 = r2 + -1
            goto L_0x0045
        L_0x0066:
            java.util.ArrayList<java.lang.ref.WeakReference<android.support.v7.widget.TintContextWrapper>> r6 = sCache     // Catch:{ all -> 0x009d }
            int r6 = r6.size()     // Catch:{ all -> 0x009d }
            r7 = 1
            int r6 = r6 + -1
            r2 = r6
        L_0x0070:
            r6 = r2
            if (r6 < 0) goto L_0x001d
            java.util.ArrayList<java.lang.ref.WeakReference<android.support.v7.widget.TintContextWrapper>> r6 = sCache     // Catch:{ all -> 0x009d }
            r7 = r2
            java.lang.Object r6 = r6.get(r7)     // Catch:{ all -> 0x009d }
            java.lang.ref.WeakReference r6 = (java.lang.ref.WeakReference) r6     // Catch:{ all -> 0x009d }
            r3 = r6
            r6 = r3
            if (r6 == 0) goto L_0x0098
            r6 = r3
            java.lang.Object r6 = r6.get()     // Catch:{ all -> 0x009d }
            android.support.v7.widget.TintContextWrapper r6 = (android.support.p003v7.widget.TintContextWrapper) r6     // Catch:{ all -> 0x009d }
        L_0x0087:
            r4 = r6
            r6 = r4
            if (r6 == 0) goto L_0x009a
            r6 = r4
            android.content.Context r6 = r6.getBaseContext()     // Catch:{ all -> 0x009d }
            r7 = r0
            if (r6 != r7) goto L_0x009a
            r6 = r4
            r7 = r1
            monitor-exit(r7)     // Catch:{ all -> 0x009d }
            r0 = r6
            goto L_0x003a
        L_0x0098:
            r6 = 0
            goto L_0x0087
        L_0x009a:
            int r2 = r2 + -1
            goto L_0x0070
        L_0x009d:
            r6 = move-exception
            r5 = r6
            r6 = r1
            monitor-exit(r6)     // Catch:{ all -> 0x009d }
            r6 = r5
            throw r6
        L_0x00a3:
            r6 = r0
            r0 = r6
            goto L_0x003a
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.TintContextWrapper.wrap(android.content.Context):android.content.Context");
    }

    private static boolean shouldWrap(@NonNull Context context) {
        Context context2 = context;
        if ((context2 instanceof TintContextWrapper) || (context2.getResources() instanceof TintResources) || (context2.getResources() instanceof VectorEnabledTintResources)) {
            return false;
        }
        return Build.VERSION.SDK_INT < 21 || VectorEnabledTintResources.shouldBeUsed();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private TintContextWrapper(@android.support.annotation.NonNull android.content.Context r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            boolean r2 = android.support.p003v7.widget.VectorEnabledTintResources.shouldBeUsed()
            if (r2 == 0) goto L_0x0034
            r2 = r0
            android.support.v7.widget.VectorEnabledTintResources r3 = new android.support.v7.widget.VectorEnabledTintResources
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r6 = r1
            android.content.res.Resources r6 = r6.getResources()
            r4.<init>(r5, r6)
            r2.mResources = r3
            r2 = r0
            r3 = r0
            android.content.res.Resources r3 = r3.mResources
            android.content.res.Resources$Theme r3 = r3.newTheme()
            r2.mTheme = r3
            r2 = r0
            android.content.res.Resources$Theme r2 = r2.mTheme
            r3 = r1
            android.content.res.Resources$Theme r3 = r3.getTheme()
            r2.setTo(r3)
        L_0x0033:
            return
        L_0x0034:
            r2 = r0
            android.support.v7.widget.TintResources r3 = new android.support.v7.widget.TintResources
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r6 = r1
            android.content.res.Resources r6 = r6.getResources()
            r4.<init>(r5, r6)
            r2.mResources = r3
            r2 = r0
            r3 = 0
            r2.mTheme = r3
            goto L_0x0033
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.TintContextWrapper.<init>(android.content.Context):void");
    }

    public Resources.Theme getTheme() {
        return this.mTheme == null ? super.getTheme() : this.mTheme;
    }

    public void setTheme(int i) {
        int resid = i;
        if (this.mTheme == null) {
            super.setTheme(resid);
        } else {
            this.mTheme.applyStyle(resid, true);
        }
    }

    public Resources getResources() {
        return this.mResources;
    }

    public AssetManager getAssets() {
        return this.mResources.getAssets();
    }
}
