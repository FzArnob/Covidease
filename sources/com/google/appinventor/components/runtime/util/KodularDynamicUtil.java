package com.google.appinventor.components.runtime.util;

import android.view.View;
import com.google.appinventor.components.runtime.AndroidViewComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import java.util.List;

public class KodularDynamicUtil {
    private KodularDynamicUtil() {
    }

    public static Object getObjectById(int i, List<KodularDynamicModel> list) {
        int i2 = i;
        for (KodularDynamicModel next : list) {
            KodularDynamicModel kodularDynamicModel = next;
            if (next.getId() == i2) {
                return kodularDynamicModel.getObject();
            }
        }
        return null;
    }

    public static Object getViewHolderById(int i, List<KodularDynamicModel> list) {
        int i2 = i;
        for (KodularDynamicModel next : list) {
            KodularDynamicModel kodularDynamicModel = next;
            if (next.getId() == i2) {
                return kodularDynamicModel.getViewHolder();
            }
        }
        return null;
    }

    public static Object getChildViewHolderById(int i, List<KodularDynamicModel> list) {
        int i2 = i;
        for (KodularDynamicModel next : list) {
            KodularDynamicModel kodularDynamicModel = next;
            if (next.getId() == i2) {
                return kodularDynamicModel.getChildViewHolder();
            }
        }
        return null;
    }

    public static void removeItemById(int i, List<KodularDynamicModel> list) {
        int i2 = i;
        List<KodularDynamicModel> list2 = list;
        for (KodularDynamicModel next : list2) {
            KodularDynamicModel kodularDynamicModel = next;
            if (next.getId() == i2) {
                boolean remove = list2.remove(kodularDynamicModel);
            }
        }
    }

    public static class ComponentReturnHelper extends AndroidViewComponent {
        private View B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ComponentReturnHelper(View view) {
            super((ComponentContainer) null);
            setView(view);
        }

        public void setView(View view) {
            View view2 = view;
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = view2;
        }

        public View getView() {
            return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
        }
    }
}
