package android.support.p003v7.widget;

import android.graphics.drawable.Drawable;
import android.view.View;

/* renamed from: android.support.v7.widget.CardViewDelegate */
interface CardViewDelegate {
    Drawable getCardBackground();

    View getCardView();

    boolean getPreventCornerOverlap();

    boolean getUseCompatPadding();

    void setCardBackground(Drawable drawable);

    void setMinWidthHeightInternal(int i, int i2);

    void setShadowPadding(int i, int i2, int i3, int i4);
}
