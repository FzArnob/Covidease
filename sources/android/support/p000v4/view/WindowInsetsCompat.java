package android.support.p000v4.view;

import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.view.WindowInsets;

/* renamed from: android.support.v4.view.WindowInsetsCompat */
public class WindowInsetsCompat {
    private final Object mInsets;

    private WindowInsetsCompat(Object insets) {
        this.mInsets = insets;
    }

    public WindowInsetsCompat(WindowInsetsCompat windowInsetsCompat) {
        Object obj;
        Object obj2;
        WindowInsetsCompat src = windowInsetsCompat;
        if (Build.VERSION.SDK_INT >= 20) {
            if (src == null) {
                obj2 = null;
            } else {
                obj2 = obj;
                new WindowInsets((WindowInsets) src.mInsets);
            }
            this.mInsets = obj2;
            return;
        }
        this.mInsets = null;
    }

    public int getSystemWindowInsetLeft() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.mInsets).getSystemWindowInsetLeft();
        }
        return 0;
    }

    public int getSystemWindowInsetTop() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.mInsets).getSystemWindowInsetTop();
        }
        return 0;
    }

    public int getSystemWindowInsetRight() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.mInsets).getSystemWindowInsetRight();
        }
        return 0;
    }

    public int getSystemWindowInsetBottom() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.mInsets).getSystemWindowInsetBottom();
        }
        return 0;
    }

    public boolean hasSystemWindowInsets() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.mInsets).hasSystemWindowInsets();
        }
        return false;
    }

    public boolean hasInsets() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.mInsets).hasInsets();
        }
        return false;
    }

    public boolean isConsumed() {
        if (Build.VERSION.SDK_INT >= 21) {
            return ((WindowInsets) this.mInsets).isConsumed();
        }
        return false;
    }

    public boolean isRound() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.mInsets).isRound();
        }
        return false;
    }

    public WindowInsetsCompat consumeSystemWindowInsets() {
        WindowInsetsCompat windowInsetsCompat;
        if (Build.VERSION.SDK_INT < 20) {
            return null;
        }
        new WindowInsetsCompat((Object) ((WindowInsets) this.mInsets).consumeSystemWindowInsets());
        return windowInsetsCompat;
    }

    public WindowInsetsCompat replaceSystemWindowInsets(int i, int i2, int i3, int i4) {
        WindowInsetsCompat windowInsetsCompat;
        int left = i;
        int top = i2;
        int right = i3;
        int bottom = i4;
        if (Build.VERSION.SDK_INT < 20) {
            return null;
        }
        new WindowInsetsCompat((Object) ((WindowInsets) this.mInsets).replaceSystemWindowInsets(left, top, right, bottom));
        return windowInsetsCompat;
    }

    public WindowInsetsCompat replaceSystemWindowInsets(Rect rect) {
        WindowInsetsCompat windowInsetsCompat;
        Rect systemWindowInsets = rect;
        if (Build.VERSION.SDK_INT < 21) {
            return null;
        }
        new WindowInsetsCompat((Object) ((WindowInsets) this.mInsets).replaceSystemWindowInsets(systemWindowInsets));
        return windowInsetsCompat;
    }

    public int getStableInsetTop() {
        if (Build.VERSION.SDK_INT >= 21) {
            return ((WindowInsets) this.mInsets).getStableInsetTop();
        }
        return 0;
    }

    public int getStableInsetLeft() {
        if (Build.VERSION.SDK_INT >= 21) {
            return ((WindowInsets) this.mInsets).getStableInsetLeft();
        }
        return 0;
    }

    public int getStableInsetRight() {
        if (Build.VERSION.SDK_INT >= 21) {
            return ((WindowInsets) this.mInsets).getStableInsetRight();
        }
        return 0;
    }

    public int getStableInsetBottom() {
        if (Build.VERSION.SDK_INT >= 21) {
            return ((WindowInsets) this.mInsets).getStableInsetBottom();
        }
        return 0;
    }

    public boolean hasStableInsets() {
        if (Build.VERSION.SDK_INT >= 21) {
            return ((WindowInsets) this.mInsets).hasStableInsets();
        }
        return false;
    }

    public WindowInsetsCompat consumeStableInsets() {
        WindowInsetsCompat windowInsetsCompat;
        if (Build.VERSION.SDK_INT < 21) {
            return null;
        }
        new WindowInsetsCompat((Object) ((WindowInsets) this.mInsets).consumeStableInsets());
        return windowInsetsCompat;
    }

    @Nullable
    public DisplayCutoutCompat getDisplayCutout() {
        if (Build.VERSION.SDK_INT >= 28) {
            return DisplayCutoutCompat.wrap(((WindowInsets) this.mInsets).getDisplayCutout());
        }
        return null;
    }

    public WindowInsetsCompat consumeDisplayCutout() {
        WindowInsetsCompat windowInsetsCompat;
        if (Build.VERSION.SDK_INT >= 28) {
            new WindowInsetsCompat((Object) ((WindowInsets) this.mInsets).consumeDisplayCutout());
            return windowInsetsCompat;
        }
        return this;
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WindowInsetsCompat other = (WindowInsetsCompat) o;
        return this.mInsets == null ? other.mInsets == null : this.mInsets.equals(other.mInsets);
    }

    public int hashCode() {
        return this.mInsets == null ? 0 : this.mInsets.hashCode();
    }

    static WindowInsetsCompat wrap(Object obj) {
        WindowInsetsCompat windowInsetsCompat;
        WindowInsetsCompat windowInsetsCompat2;
        Object insets = obj;
        if (insets == null) {
            windowInsetsCompat2 = null;
        } else {
            windowInsetsCompat2 = windowInsetsCompat;
            new WindowInsetsCompat(insets);
        }
        return windowInsetsCompat2;
    }

    static Object unwrap(WindowInsetsCompat windowInsetsCompat) {
        WindowInsetsCompat insets = windowInsetsCompat;
        return insets == null ? null : insets.mInsets;
    }
}
