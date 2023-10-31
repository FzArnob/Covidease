package com.bumptech.glide.request.target;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;

public class ImageViewTargetFactory {
    public ImageViewTargetFactory() {
    }

    public <Z> Target<Z> buildTarget(ImageView imageView, Class<Z> cls) {
        Throwable th;
        StringBuilder sb;
        Target<Z> target;
        Target<Z> target2;
        Target<Z> target3;
        ImageView view = imageView;
        Class<Z> clazz = cls;
        if (GlideDrawable.class.isAssignableFrom(clazz)) {
            new GlideDrawableImageViewTarget(view);
            return target3;
        } else if (Bitmap.class.equals(clazz)) {
            new BitmapImageViewTarget(view);
            return target2;
        } else if (Drawable.class.isAssignableFrom(clazz)) {
            new DrawableImageViewTarget(view);
            return target;
        } else {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Unhandled class: ").append(clazz).append(", try .as*(Class).transcode(ResourceTranscoder)").toString());
            throw th2;
        }
    }
}
