package com.google.appinventor.components.runtime;

import android.util.Log;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class FrameLayout implements Layout {
    private final android.widget.FrameLayout B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    public ViewGroup getLayoutManager() {
        int i = Log.i("FrameLayout", "some one just get my framelayout");
        return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    }

    public void add(AndroidViewComponent androidViewComponent) {
        ViewGroup.LayoutParams layoutParams;
        int i = Log.i("FrameLayout", "adding component..");
        new ViewGroup.LayoutParams(-1, -2);
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.addView(androidViewComponent.getView(), layoutParams);
    }

    public List<Object> getChildren() {
        ArrayList arrayList;
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        int childCount = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.getChildCount();
        for (int i = 0; i < childCount; i++) {
            boolean add = arrayList2.add(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.getChildAt(i));
        }
        return arrayList2;
    }
}
