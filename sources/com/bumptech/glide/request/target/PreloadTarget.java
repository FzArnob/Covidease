package com.bumptech.glide.request.target;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;

public final class PreloadTarget<Z> extends SimpleTarget<Z> {
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <Z> com.bumptech.glide.request.target.PreloadTarget<Z> obtain(int r7, int r8) {
        /*
            r0 = r7
            r1 = r8
            com.bumptech.glide.request.target.PreloadTarget r2 = new com.bumptech.glide.request.target.PreloadTarget
            r6 = r2
            r2 = r6
            r3 = r6
            r4 = r0
            r5 = r1
            r3.<init>(r4, r5)
            r0 = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.target.PreloadTarget.obtain(int, int):com.bumptech.glide.request.target.PreloadTarget");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private PreloadTarget(int width, int height) {
        super(width, height);
    }

    public void onResourceReady(Z z, GlideAnimation<? super Z> glideAnimation) {
        Z z2 = z;
        GlideAnimation<? super Z> glideAnimation2 = glideAnimation;
        Glide.clear((Target<?>) this);
    }
}
