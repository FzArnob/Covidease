package android.support.p000v4.view;

import android.graphics.Rect;
import android.os.Build;
import android.view.DisplayCutout;
import java.util.List;

/* renamed from: android.support.v4.view.DisplayCutoutCompat */
public final class DisplayCutoutCompat {
    private final Object mDisplayCutout;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DisplayCutoutCompat(android.graphics.Rect r10, java.util.List<android.graphics.Rect> r11) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r0
            int r4 = android.os.Build.VERSION.SDK_INT
            r5 = 28
            if (r4 < r5) goto L_0x0018
            android.view.DisplayCutout r4 = new android.view.DisplayCutout
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r1
            r7 = r2
            r5.<init>(r6, r7)
        L_0x0014:
            r3.<init>(r4)
            return
        L_0x0018:
            r4 = 0
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.view.DisplayCutoutCompat.<init>(android.graphics.Rect, java.util.List):void");
    }

    private DisplayCutoutCompat(Object displayCutout) {
        this.mDisplayCutout = displayCutout;
    }

    public int getSafeInsetTop() {
        if (Build.VERSION.SDK_INT >= 28) {
            return ((DisplayCutout) this.mDisplayCutout).getSafeInsetTop();
        }
        return 0;
    }

    public int getSafeInsetBottom() {
        if (Build.VERSION.SDK_INT >= 28) {
            return ((DisplayCutout) this.mDisplayCutout).getSafeInsetBottom();
        }
        return 0;
    }

    public int getSafeInsetLeft() {
        if (Build.VERSION.SDK_INT >= 28) {
            return ((DisplayCutout) this.mDisplayCutout).getSafeInsetLeft();
        }
        return 0;
    }

    public int getSafeInsetRight() {
        if (Build.VERSION.SDK_INT >= 28) {
            return ((DisplayCutout) this.mDisplayCutout).getSafeInsetRight();
        }
        return 0;
    }

    public List<Rect> getBoundingRects() {
        if (Build.VERSION.SDK_INT >= 28) {
            return ((DisplayCutout) this.mDisplayCutout).getBoundingRects();
        }
        return null;
    }

    public boolean equals(Object obj) {
        boolean equals;
        Object o = obj;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DisplayCutoutCompat other = (DisplayCutoutCompat) o;
        if (this.mDisplayCutout == null) {
            equals = other.mDisplayCutout == null;
        } else {
            equals = this.mDisplayCutout.equals(other.mDisplayCutout);
        }
        return equals;
    }

    public int hashCode() {
        return this.mDisplayCutout == null ? 0 : this.mDisplayCutout.hashCode();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("DisplayCutoutCompat{").append(this.mDisplayCutout).append("}").toString();
    }

    static DisplayCutoutCompat wrap(Object obj) {
        DisplayCutoutCompat displayCutoutCompat;
        DisplayCutoutCompat displayCutoutCompat2;
        Object displayCutout = obj;
        if (displayCutout == null) {
            displayCutoutCompat2 = null;
        } else {
            displayCutoutCompat2 = displayCutoutCompat;
            new DisplayCutoutCompat(displayCutout);
        }
        return displayCutoutCompat2;
    }
}
