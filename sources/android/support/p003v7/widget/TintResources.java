package android.support.p003v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import java.lang.ref.WeakReference;

/* renamed from: android.support.v7.widget.TintResources */
class TintResources extends ResourcesWrapper {
    private final WeakReference<Context> mContextRef;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TintResources(@NonNull Context context, @NonNull Resources res) {
        super(res);
        WeakReference<Context> weakReference;
        new WeakReference<>(context);
        this.mContextRef = weakReference;
    }

    public Drawable getDrawable(int i) throws Resources.NotFoundException {
        int id = i;
        Drawable d = super.getDrawable(id);
        Context context = (Context) this.mContextRef.get();
        if (!(d == null || context == null)) {
            AppCompatDrawableManager appCompatDrawableManager = AppCompatDrawableManager.get();
            boolean tintDrawableUsingColorFilter = AppCompatDrawableManager.tintDrawableUsingColorFilter(context, id, d);
        }
        return d;
    }
}
