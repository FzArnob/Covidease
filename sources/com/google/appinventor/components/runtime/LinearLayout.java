package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.google.appinventor.components.annotations.SimpleObject;

@SimpleObject
public final class LinearLayout implements Layout {
    private final android.widget.LinearLayout B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    LinearLayout(Context context) {
        this(context, 1, (Integer) null, (Integer) null);
    }

    LinearLayout(Context context, int i, Integer num, Integer num2) {
        Throwable th;
        android.widget.LinearLayout linearLayout;
        Context context2 = context;
        int i2 = i;
        Integer num3 = num;
        Integer num4 = num2;
        if ((num3 != null || num4 == null) && (num3 == null || num4 != null)) {
            final Integer num5 = num3;
            final Integer num6 = num4;
            new android.widget.LinearLayout(this, context2) {
                private /* synthetic */ LinearLayout hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r8;
                }

                /* access modifiers changed from: protected */
                public final void onMeasure(int i, int i2) {
                    int i3 = i;
                    int i4 = i2;
                    if (num5 == null || num6 == null) {
                        super.onMeasure(i3, i4);
                    } else if (getChildCount() != 0) {
                        super.onMeasure(i3, i4);
                    } else {
                        setMeasuredDimension(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i3, num5.intValue()), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i4, num6.intValue()));
                    }
                }

                private static int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(int i, int i2) {
                    int i3;
                    int i4 = i;
                    int i5 = i2;
                    int mode = View.MeasureSpec.getMode(i4);
                    int size = View.MeasureSpec.getSize(i4);
                    if (mode == 1073741824) {
                        i3 = size;
                    } else {
                        i3 = i5;
                        if (mode == Integer.MIN_VALUE) {
                            i3 = Math.min(i3, size);
                        }
                    }
                    return i3;
                }
            };
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = linearLayout;
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setOrientation(i2 == 0 ? 0 : 1);
            return;
        }
        Throwable th2 = th;
        new IllegalArgumentException("LinearLayout - preferredEmptyWidth and preferredEmptyHeight must be either both null or both not null");
        throw th2;
    }

    public final ViewGroup getLayoutManager() {
        return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    }

    public final void add(AndroidViewComponent androidViewComponent) {
        StringBuilder sb;
        ViewGroup.LayoutParams layoutParams;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        new StringBuilder("I am adding: ");
        int i = Log.i("LinearLayout", sb.append(androidViewComponent2.getView().toString()).toString());
        new LinearLayout.LayoutParams(-2, -2, 0.0f);
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.addView(androidViewComponent2.getView(), layoutParams);
    }

    public final void setHorizontalGravity(int i) {
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setHorizontalGravity(i);
    }

    public final void setVerticalGravity(int i) {
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setVerticalGravity(i);
    }

    public final void setBaselineAligned(boolean z) {
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setBaselineAligned(z);
    }
}
