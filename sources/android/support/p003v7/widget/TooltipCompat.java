package android.support.p003v7.widget;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/* renamed from: android.support.v7.widget.TooltipCompat */
public class TooltipCompat {
    public static void setTooltipText(@NonNull View view, @Nullable CharSequence charSequence) {
        View view2 = view;
        CharSequence tooltipText = charSequence;
        if (Build.VERSION.SDK_INT >= 26) {
            view2.setTooltipText(tooltipText);
        } else {
            TooltipCompatHandler.setTooltipText(view2, tooltipText);
        }
    }

    private TooltipCompat() {
    }
}
