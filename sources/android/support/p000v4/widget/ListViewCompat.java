package android.support.p000v4.widget;

import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListView;

/* renamed from: android.support.v4.widget.ListViewCompat */
public final class ListViewCompat {
    public static void scrollListBy(@NonNull ListView listView, int i) {
        View firstView;
        ListView listView2 = listView;
        int y = i;
        if (Build.VERSION.SDK_INT >= 19) {
            listView2.scrollListBy(y);
            return;
        }
        int firstPosition = listView2.getFirstVisiblePosition();
        if (firstPosition != -1 && (firstView = listView2.getChildAt(0)) != null) {
            listView2.setSelectionFromTop(firstPosition, firstView.getTop() - y);
        }
    }

    public static boolean canScrollList(@NonNull ListView listView, int i) {
        ListView listView2 = listView;
        int direction = i;
        if (Build.VERSION.SDK_INT >= 19) {
            return listView2.canScrollList(direction);
        }
        int childCount = listView2.getChildCount();
        if (childCount == 0) {
            return false;
        }
        int firstPosition = listView2.getFirstVisiblePosition();
        if (direction > 0) {
            return firstPosition + childCount < listView2.getCount() || listView2.getChildAt(childCount + -1).getBottom() > listView2.getHeight() - listView2.getListPaddingBottom();
        }
        return firstPosition > 0 || listView2.getChildAt(0).getTop() < listView2.getListPaddingTop();
    }

    private ListViewCompat() {
    }
}
