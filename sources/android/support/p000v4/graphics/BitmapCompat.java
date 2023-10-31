package android.support.p000v4.graphics;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;

/* renamed from: android.support.v4.graphics.BitmapCompat */
public final class BitmapCompat {
    public static boolean hasMipMap(@NonNull Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        if (Build.VERSION.SDK_INT >= 18) {
            return bitmap2.hasMipMap();
        }
        return false;
    }

    public static void setHasMipMap(@NonNull Bitmap bitmap, boolean z) {
        Bitmap bitmap2 = bitmap;
        boolean hasMipMap = z;
        if (Build.VERSION.SDK_INT >= 18) {
            bitmap2.setHasMipMap(hasMipMap);
        }
    }

    public static int getAllocationByteCount(@NonNull Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        if (Build.VERSION.SDK_INT >= 19) {
            return bitmap2.getAllocationByteCount();
        }
        return bitmap2.getByteCount();
    }

    private BitmapCompat() {
    }
}
