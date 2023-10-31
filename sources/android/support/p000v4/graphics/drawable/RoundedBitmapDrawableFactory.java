package android.support.p000v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.graphics.BitmapCompat;
import android.support.p000v4.view.GravityCompat;
import android.util.Log;
import java.io.InputStream;

/* renamed from: android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory */
public final class RoundedBitmapDrawableFactory {
    private static final String TAG = "RoundedBitmapDrawableFa";

    /* renamed from: android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory$DefaultRoundedBitmapDrawable */
    private static class DefaultRoundedBitmapDrawable extends RoundedBitmapDrawable {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        DefaultRoundedBitmapDrawable(Resources res, Bitmap bitmap) {
            super(res, bitmap);
        }

        public void setMipMap(boolean z) {
            boolean mipMap = z;
            if (this.mBitmap != null) {
                BitmapCompat.setHasMipMap(this.mBitmap, mipMap);
                invalidateSelf();
            }
        }

        public boolean hasMipMap() {
            return this.mBitmap != null && BitmapCompat.hasMipMap(this.mBitmap);
        }

        /* access modifiers changed from: package-private */
        public void gravityCompatApply(int gravity, int bitmapWidth, int bitmapHeight, Rect bounds, Rect outRect) {
            GravityCompat.apply(gravity, bitmapWidth, bitmapHeight, bounds, outRect, 0);
        }
    }

    @NonNull
    public static RoundedBitmapDrawable create(@NonNull Resources resources, @Nullable Bitmap bitmap) {
        RoundedBitmapDrawable roundedBitmapDrawable;
        RoundedBitmapDrawable roundedBitmapDrawable2;
        Resources res = resources;
        Bitmap bitmap2 = bitmap;
        if (Build.VERSION.SDK_INT >= 21) {
            new RoundedBitmapDrawable21(res, bitmap2);
            return roundedBitmapDrawable2;
        }
        new DefaultRoundedBitmapDrawable(res, bitmap2);
        return roundedBitmapDrawable;
    }

    @NonNull
    public static RoundedBitmapDrawable create(@NonNull Resources res, @NonNull String str) {
        StringBuilder sb;
        String filepath = str;
        RoundedBitmapDrawable drawable = create(res, BitmapFactory.decodeFile(filepath));
        if (drawable.getBitmap() == null) {
            new StringBuilder();
            int w = Log.w(TAG, sb.append("RoundedBitmapDrawable cannot decode ").append(filepath).toString());
        }
        return drawable;
    }

    @NonNull
    public static RoundedBitmapDrawable create(@NonNull Resources res, @NonNull InputStream inputStream) {
        StringBuilder sb;
        InputStream is = inputStream;
        RoundedBitmapDrawable drawable = create(res, BitmapFactory.decodeStream(is));
        if (drawable.getBitmap() == null) {
            new StringBuilder();
            int w = Log.w(TAG, sb.append("RoundedBitmapDrawable cannot decode ").append(is).toString());
        }
        return drawable;
    }

    private RoundedBitmapDrawableFactory() {
    }
}
