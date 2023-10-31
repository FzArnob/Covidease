package android.support.p000v4.widget;

import android.widget.ListView;

/* renamed from: android.support.v4.widget.ListViewAutoScrollHelper */
public class ListViewAutoScrollHelper extends AutoScrollHelper {
    private final ListView mTarget;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ListViewAutoScrollHelper(@android.support.annotation.NonNull android.widget.ListView r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            r2.mTarget = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.widget.ListViewAutoScrollHelper.<init>(android.widget.ListView):void");
    }

    public void scrollTargetBy(int i, int deltaY) {
        int i2 = i;
        ListViewCompat.scrollListBy(this.mTarget, deltaY);
    }

    public boolean canTargetScrollHorizontally(int i) {
        int i2 = i;
        return false;
    }

    public boolean canTargetScrollVertically(int i) {
        int direction = i;
        ListView target = this.mTarget;
        int itemCount = target.getCount();
        if (itemCount == 0) {
            return false;
        }
        int childCount = target.getChildCount();
        int firstPosition = target.getFirstVisiblePosition();
        int lastPosition = firstPosition + childCount;
        if (direction > 0) {
            if (lastPosition >= itemCount && target.getChildAt(childCount - 1).getBottom() <= target.getHeight()) {
                return false;
            }
        } else if (direction >= 0) {
            return false;
        } else {
            if (firstPosition <= 0 && target.getChildAt(0).getTop() >= 0) {
                return false;
            }
        }
        return true;
    }
}
