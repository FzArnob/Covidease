package android.support.p000v4.hardware.display;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.WindowManager;
import java.util.WeakHashMap;

/* renamed from: android.support.v4.hardware.display.DisplayManagerCompat */
public final class DisplayManagerCompat {
    public static final String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";
    private static final WeakHashMap<Context, DisplayManagerCompat> sInstances;
    private final Context mContext;

    static {
        WeakHashMap<Context, DisplayManagerCompat> weakHashMap;
        new WeakHashMap<>();
        sInstances = weakHashMap;
    }

    private DisplayManagerCompat(Context context) {
        this.mContext = context;
    }

    /* JADX INFO: finally extract failed */
    @NonNull
    public static DisplayManagerCompat getInstance(@NonNull Context context) {
        DisplayManagerCompat displayManagerCompat;
        Context context2 = context;
        WeakHashMap<Context, DisplayManagerCompat> weakHashMap = sInstances;
        WeakHashMap<Context, DisplayManagerCompat> weakHashMap2 = weakHashMap;
        synchronized (weakHashMap) {
            try {
                DisplayManagerCompat instance = sInstances.get(context2);
                if (instance == null) {
                    new DisplayManagerCompat(context2);
                    instance = displayManagerCompat;
                    DisplayManagerCompat put = sInstances.put(context2, instance);
                }
                DisplayManagerCompat displayManagerCompat2 = instance;
                return displayManagerCompat2;
            } catch (Throwable th) {
                Throwable th2 = th;
                WeakHashMap<Context, DisplayManagerCompat> weakHashMap3 = weakHashMap2;
                throw th2;
            }
        }
    }

    @Nullable
    public Display getDisplay(int i) {
        int displayId = i;
        if (Build.VERSION.SDK_INT >= 17) {
            return ((DisplayManager) this.mContext.getSystemService("display")).getDisplay(displayId);
        }
        Display display = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay();
        if (display.getDisplayId() == displayId) {
            return display;
        }
        return null;
    }

    @NonNull
    public Display[] getDisplays() {
        if (Build.VERSION.SDK_INT >= 17) {
            return ((DisplayManager) this.mContext.getSystemService("display")).getDisplays();
        }
        return new Display[]{((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay()};
    }

    @NonNull
    public Display[] getDisplays(@Nullable String str) {
        String category = str;
        if (Build.VERSION.SDK_INT >= 17) {
            return ((DisplayManager) this.mContext.getSystemService("display")).getDisplays(category);
        }
        if (category == null) {
            return new Display[0];
        }
        return new Display[]{((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay()};
    }
}
