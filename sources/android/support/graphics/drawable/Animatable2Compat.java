package android.support.graphics.drawable;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

public interface Animatable2Compat extends Animatable {
    void clearAnimationCallbacks();

    void registerAnimationCallback(@NonNull AnimationCallback animationCallback);

    boolean unregisterAnimationCallback(@NonNull AnimationCallback animationCallback);

    public static abstract class AnimationCallback {
        Animatable2.AnimationCallback mPlatformCallback;

        public AnimationCallback() {
        }

        public void onAnimationStart(Drawable drawable) {
        }

        public void onAnimationEnd(Drawable drawable) {
        }

        /* access modifiers changed from: package-private */
        @RequiresApi(23)
        public Animatable2.AnimationCallback getPlatformCallback() {
            Animatable2.AnimationCallback animationCallback;
            if (this.mPlatformCallback == null) {
                new Animatable2.AnimationCallback(this) {
                    final /* synthetic */ AnimationCallback this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void onAnimationStart(Drawable drawable) {
                        this.this$0.onAnimationStart(drawable);
                    }

                    public void onAnimationEnd(Drawable drawable) {
                        this.this$0.onAnimationEnd(drawable);
                    }
                };
                this.mPlatformCallback = animationCallback;
            }
            return this.mPlatformCallback;
        }
    }
}
