package android.support.p003v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RestrictTo;
import java.lang.ref.WeakReference;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.widget.VectorEnabledTintResources */
public class VectorEnabledTintResources extends Resources {
    public static final int MAX_SDK_WHERE_REQUIRED = 20;
    private static boolean sCompatVectorFromResourcesEnabled = false;
    private final WeakReference<Context> mContextRef;

    public static boolean shouldBeUsed() {
        return isCompatVectorFromResourcesEnabled() && Build.VERSION.SDK_INT <= 20;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public VectorEnabledTintResources(@android.support.annotation.NonNull android.content.Context r9, @android.support.annotation.NonNull android.content.res.Resources r10) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r0
            r4 = r2
            android.content.res.AssetManager r4 = r4.getAssets()
            r5 = r2
            android.util.DisplayMetrics r5 = r5.getDisplayMetrics()
            r6 = r2
            android.content.res.Configuration r6 = r6.getConfiguration()
            r3.<init>(r4, r5, r6)
            r3 = r0
            java.lang.ref.WeakReference r4 = new java.lang.ref.WeakReference
            r7 = r4
            r4 = r7
            r5 = r7
            r6 = r1
            r5.<init>(r6)
            r3.mContextRef = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.VectorEnabledTintResources.<init>(android.content.Context, android.content.res.Resources):void");
    }

    public Drawable getDrawable(int i) throws Resources.NotFoundException {
        int id = i;
        Context context = (Context) this.mContextRef.get();
        if (context != null) {
            return AppCompatDrawableManager.get().onDrawableLoadedFromResources(context, this, id);
        }
        return super.getDrawable(id);
    }

    /* access modifiers changed from: package-private */
    public final Drawable superGetDrawable(int id) {
        return super.getDrawable(id);
    }

    public static void setCompatVectorFromResourcesEnabled(boolean enabled) {
        sCompatVectorFromResourcesEnabled = enabled;
    }

    public static boolean isCompatVectorFromResourcesEnabled() {
        return sCompatVectorFromResourcesEnabled;
    }
}
