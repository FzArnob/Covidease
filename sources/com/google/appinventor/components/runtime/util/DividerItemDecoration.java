package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.p000v4.content.res.ResourcesCompat;
import android.support.p003v7.widget.RecyclerView;
import android.view.View;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = {16843284};
    private Drawable vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;

    public DividerItemDecoration(Context context, int i) {
        Drawable drawable;
        Context context2 = context;
        int i2 = i;
        if (i2 == 0) {
            TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq);
            this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
        } else if (i2 == 16777215) {
            this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = ResourcesCompat.getDrawable(context2.getResources(), context2.getResources().getIdentifier("divider_transparent", "drawable", context2.getPackageName()), (Resources.Theme) null);
        } else {
            new ColorDrawable(i2);
            this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = drawable;
        }
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        Canvas canvas2 = canvas;
        RecyclerView recyclerView2 = recyclerView;
        RecyclerView.State state2 = state;
        int paddingLeft = recyclerView2.getPaddingLeft();
        int width = recyclerView2.getWidth() - recyclerView2.getPaddingRight();
        int childCount = recyclerView2.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView2.getChildAt(i);
            int bottom = childAt.getBottom() + ((RecyclerView.LayoutParams) childAt.getLayoutParams()).bottomMargin;
            this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.setBounds(paddingLeft, bottom, width, bottom + this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.getIntrinsicHeight());
            this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.draw(canvas2);
        }
    }
}
