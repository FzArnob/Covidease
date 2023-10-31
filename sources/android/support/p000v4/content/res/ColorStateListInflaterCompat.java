package android.support.p000v4.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.compat.C0020R;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.Xml;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.content.res.ColorStateListInflaterCompat */
public final class ColorStateListInflaterCompat {
    private static final int DEFAULT_COLOR = -65536;

    private ColorStateListInflaterCompat() {
    }

    @NonNull
    public static ColorStateList createFromXml(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        int type;
        Throwable th;
        Resources r = resources;
        XmlPullParser parser = xmlPullParser;
        Resources.Theme theme2 = theme;
        AttributeSet attrs = Xml.asAttributeSet(parser);
        do {
            int next = parser.next();
            type = next;
            if (next == 2 || type == 1) {
            }
            int next2 = parser.next();
            type = next2;
            break;
        } while (type == 1);
        if (type == 2) {
            return createFromXmlInner(r, parser, attrs, theme2);
        }
        Throwable th2 = th;
        new XmlPullParserException("No start tag found");
        throw th2;
    }

    @NonNull
    public static ColorStateList createFromXmlInner(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        Throwable th;
        StringBuilder sb;
        Resources r = resources;
        XmlPullParser parser = xmlPullParser;
        AttributeSet attrs = attributeSet;
        Resources.Theme theme2 = theme;
        String name = parser.getName();
        if (name.equals("selector")) {
            return inflate(r, parser, attrs, theme2);
        }
        Throwable th2 = th;
        new StringBuilder();
        new XmlPullParserException(sb.append(parser.getPositionDescription()).append(": invalid color state list tag ").append(name).toString());
        throw th2;
    }

    private static ColorStateList inflate(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        ColorStateList colorStateList;
        int i;
        Resources r = resources;
        XmlPullParser parser = xmlPullParser;
        AttributeSet attrs = attributeSet;
        Resources.Theme theme2 = theme;
        int innerDepth = parser.getDepth() + 1;
        int[][] stateSpecList = new int[20][];
        int[] colorList = new int[stateSpecList.length];
        int listSize = 0;
        while (true) {
            int next = parser.next();
            int type = next;
            if (next == 1) {
                break;
            }
            int depth = parser.getDepth();
            int depth2 = depth;
            if (depth < innerDepth && type == 3) {
                break;
            } else if (type == 2 && depth2 <= innerDepth && parser.getName().equals("item")) {
                TypedArray a = obtainAttributes(r, theme2, attrs, C0020R.styleable.ColorStateListItem);
                int baseColor = a.getColor(C0020R.styleable.ColorStateListItem_android_color, -65281);
                float alphaMod = 1.0f;
                if (a.hasValue(C0020R.styleable.ColorStateListItem_android_alpha)) {
                    alphaMod = a.getFloat(C0020R.styleable.ColorStateListItem_android_alpha, 1.0f);
                } else if (a.hasValue(C0020R.styleable.ColorStateListItem_alpha)) {
                    alphaMod = a.getFloat(C0020R.styleable.ColorStateListItem_alpha, 1.0f);
                }
                a.recycle();
                int j = 0;
                int numAttrs = attrs.getAttributeCount();
                int[] stateSpec = new int[numAttrs];
                for (int i2 = 0; i2 < numAttrs; i2++) {
                    int stateResId = attrs.getAttributeNameResource(i2);
                    if (!(stateResId == 16843173 || stateResId == 16843551 || stateResId == C0020R.attr.alpha)) {
                        int[] iArr = stateSpec;
                        int i3 = j;
                        j++;
                        if (attrs.getAttributeBooleanValue(i2, false)) {
                            i = stateResId;
                        } else {
                            i = -stateResId;
                        }
                        iArr[i3] = i;
                    }
                }
                int[] stateSpec2 = StateSet.trimStateSet(stateSpec, j);
                int i4 = modulateColorAlpha(baseColor, alphaMod);
                if (listSize == 0 || stateSpec2.length == 0) {
                    int defaultColor = i4;
                }
                colorList = GrowingArrayUtils.append(colorList, listSize, i4);
                stateSpecList = (int[][]) GrowingArrayUtils.append((T[]) stateSpecList, listSize, stateSpec2);
                listSize++;
            }
        }
        int[] colors = new int[listSize];
        int[][] stateSpecs = new int[listSize][];
        System.arraycopy(colorList, 0, colors, 0, listSize);
        System.arraycopy(stateSpecList, 0, stateSpecs, 0, listSize);
        new ColorStateList(stateSpecs, colors);
        return colorStateList;
    }

    private static TypedArray obtainAttributes(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        TypedArray obtainStyledAttributes;
        Resources res = resources;
        Resources.Theme theme2 = theme;
        AttributeSet set = attributeSet;
        int[] attrs = iArr;
        if (theme2 == null) {
            obtainStyledAttributes = res.obtainAttributes(set, attrs);
        } else {
            obtainStyledAttributes = theme2.obtainStyledAttributes(set, attrs, 0, 0);
        }
        return obtainStyledAttributes;
    }

    @ColorInt
    private static int modulateColorAlpha(@ColorInt int i, @FloatRange(from = 0.0d, mo103to = 1.0d) float alphaMod) {
        int color = i;
        return (color & 16777215) | (Math.round(((float) Color.alpha(color)) * alphaMod) << 24);
    }
}
