package android.support.p000v4.graphics;

import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.p000v4.util.Pair;

/* renamed from: android.support.v4.graphics.PaintCompat */
public final class PaintCompat {
    private static final String EM_STRING = "m";
    private static final String TOFU_STRING = "󟿽";
    private static final ThreadLocal<Pair<Rect, Rect>> sRectThreadLocal;

    static {
        ThreadLocal<Pair<Rect, Rect>> threadLocal;
        new ThreadLocal<>();
        sRectThreadLocal = threadLocal;
    }

    public static boolean hasGlyph(@NonNull Paint paint, @NonNull String str) {
        Paint paint2 = paint;
        String string = str;
        if (Build.VERSION.SDK_INT >= 23) {
            return paint2.hasGlyph(string);
        }
        int length = string.length();
        if (length == 1 && Character.isWhitespace(string.charAt(0))) {
            return true;
        }
        float missingGlyphWidth = paint2.measureText(TOFU_STRING);
        float emGlyphWidth = paint2.measureText(EM_STRING);
        float width = paint2.measureText(string);
        if (width == 0.0f) {
            return false;
        }
        if (string.codePointCount(0, string.length()) > 1) {
            if (width > 2.0f * emGlyphWidth) {
                return false;
            }
            float sumWidth = 0.0f;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    break;
                }
                int charCount = Character.charCount(string.codePointAt(i2));
                sumWidth += paint2.measureText(string, i2, i2 + charCount);
                i = i2 + charCount;
            }
            if (width >= sumWidth) {
                return false;
            }
        }
        if (width != missingGlyphWidth) {
            return true;
        }
        Pair<Rect, Rect> rects = obtainEmptyRects();
        paint2.getTextBounds(TOFU_STRING, 0, TOFU_STRING.length(), (Rect) rects.first);
        paint2.getTextBounds(string, 0, length, (Rect) rects.second);
        return !((Rect) rects.first).equals(rects.second);
    }

    private static Pair<Rect, Rect> obtainEmptyRects() {
        Pair pair;
        Object obj;
        Object obj2;
        Pair pair2 = sRectThreadLocal.get();
        if (pair2 == null) {
            new Rect();
            new Rect();
            new Pair(obj, obj2);
            pair2 = pair;
            sRectThreadLocal.set(pair2);
        } else {
            ((Rect) pair2.first).setEmpty();
            ((Rect) pair2.second).setEmpty();
        }
        return pair2;
    }

    private PaintCompat() {
    }
}
