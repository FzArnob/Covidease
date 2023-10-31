package android.support.p003v7.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.RequiresApi;
import android.support.p003v7.widget.RoundRectDrawableWithShadow;

@RequiresApi(17)
/* renamed from: android.support.v7.widget.CardViewApi17Impl */
class CardViewApi17Impl extends CardViewBaseImpl {
    CardViewApi17Impl() {
    }

    public void initStatic() {
        RoundRectDrawableWithShadow.RoundRectHelper roundRectHelper;
        new RoundRectDrawableWithShadow.RoundRectHelper(this) {
            final /* synthetic */ CardViewApi17Impl this$0;

            {
                this.this$0 = this$0;
            }

            public void drawRoundRect(Canvas canvas, RectF bounds, float f, Paint paint) {
                float cornerRadius = f;
                canvas.drawRoundRect(bounds, cornerRadius, cornerRadius, paint);
            }
        };
        RoundRectDrawableWithShadow.sRoundRectHelper = roundRectHelper;
    }
}
