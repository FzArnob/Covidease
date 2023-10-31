package android.support.p000v4.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.compat.C0020R;
import android.util.AttributeSet;
import android.util.Xml;
import java.io.IOException;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.content.res.GradientColorInflaterCompat */
final class GradientColorInflaterCompat {
    private static final int TILE_MODE_CLAMP = 0;
    private static final int TILE_MODE_MIRROR = 2;
    private static final int TILE_MODE_REPEAT = 1;

    private GradientColorInflaterCompat() {
    }

    static Shader createFromXml(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        int type;
        Throwable th;
        Resources resources2 = resources;
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
            return createFromXmlInner(resources2, parser, attrs, theme2);
        }
        Throwable th2 = th;
        new XmlPullParserException("No start tag found");
        throw th2;
    }

    static Shader createFromXmlInner(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws IOException, XmlPullParserException {
        Shader shader;
        Shader shader2;
        Throwable th;
        Shader shader3;
        Throwable th2;
        StringBuilder sb;
        Resources resources2 = resources;
        XmlPullParser parser = xmlPullParser;
        AttributeSet attrs = attributeSet;
        Resources.Theme theme2 = theme;
        String name = parser.getName();
        if (!name.equals("gradient")) {
            Throwable th3 = th2;
            new StringBuilder();
            new XmlPullParserException(sb.append(parser.getPositionDescription()).append(": invalid gradient color tag ").append(name).toString());
            throw th3;
        }
        TypedArray a = TypedArrayUtils.obtainAttributes(resources2, theme2, attrs, C0020R.styleable.GradientColor);
        float startX = TypedArrayUtils.getNamedFloat(a, parser, "startX", C0020R.styleable.GradientColor_android_startX, 0.0f);
        float startY = TypedArrayUtils.getNamedFloat(a, parser, "startY", C0020R.styleable.GradientColor_android_startY, 0.0f);
        float endX = TypedArrayUtils.getNamedFloat(a, parser, "endX", C0020R.styleable.GradientColor_android_endX, 0.0f);
        float endY = TypedArrayUtils.getNamedFloat(a, parser, "endY", C0020R.styleable.GradientColor_android_endY, 0.0f);
        float centerX = TypedArrayUtils.getNamedFloat(a, parser, "centerX", C0020R.styleable.GradientColor_android_centerX, 0.0f);
        float centerY = TypedArrayUtils.getNamedFloat(a, parser, "centerY", C0020R.styleable.GradientColor_android_centerY, 0.0f);
        int type = TypedArrayUtils.getNamedInt(a, parser, "type", C0020R.styleable.GradientColor_android_type, 0);
        int startColor = TypedArrayUtils.getNamedColor(a, parser, "startColor", C0020R.styleable.GradientColor_android_startColor, 0);
        boolean hasCenterColor = TypedArrayUtils.hasAttribute(parser, "centerColor");
        int centerColor = TypedArrayUtils.getNamedColor(a, parser, "centerColor", C0020R.styleable.GradientColor_android_centerColor, 0);
        int endColor = TypedArrayUtils.getNamedColor(a, parser, "endColor", C0020R.styleable.GradientColor_android_endColor, 0);
        int tileMode = TypedArrayUtils.getNamedInt(a, parser, "tileMode", C0020R.styleable.GradientColor_android_tileMode, 0);
        float gradientRadius = TypedArrayUtils.getNamedFloat(a, parser, "gradientRadius", C0020R.styleable.GradientColor_android_gradientRadius, 0.0f);
        a.recycle();
        ColorStops colorStops = checkColors(inflateChildElements(resources2, parser, attrs, theme2), startColor, endColor, hasCenterColor, centerColor);
        switch (type) {
            case 1:
                if (gradientRadius <= 0.0f) {
                    Throwable th4 = th;
                    new XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
                    throw th4;
                }
                new RadialGradient(centerX, centerY, gradientRadius, colorStops.mColors, colorStops.mOffsets, parseTileMode(tileMode));
                return shader2;
            case 2:
                new SweepGradient(centerX, centerY, colorStops.mColors, colorStops.mOffsets);
                return shader;
            default:
                new LinearGradient(startX, startY, endX, endY, colorStops.mColors, colorStops.mOffsets, parseTileMode(tileMode));
                return shader3;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00ba, code lost:
        throw r14;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.support.p000v4.content.res.GradientColorInflaterCompat.ColorStops inflateChildElements(@android.support.annotation.NonNull android.content.res.Resources r19, @android.support.annotation.NonNull org.xmlpull.v1.XmlPullParser r20, @android.support.annotation.NonNull android.util.AttributeSet r21, @android.support.annotation.Nullable android.content.res.Resources.Theme r22) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r22
            r14 = r1
            int r14 = r14.getDepth()
            r15 = 1
            int r14 = r14 + 1
            r4 = r14
            java.util.ArrayList r14 = new java.util.ArrayList
            r18 = r14
            r14 = r18
            r15 = r18
            r16 = 20
            r15.<init>(r16)
            r7 = r14
            java.util.ArrayList r14 = new java.util.ArrayList
            r18 = r14
            r14 = r18
            r15 = r18
            r16 = 20
            r15.<init>(r16)
            r8 = r14
        L_0x002d:
            r14 = r1
            int r14 = r14.next()
            r18 = r14
            r14 = r18
            r15 = r18
            r5 = r15
            r15 = 1
            if (r14 == r15) goto L_0x00e9
            r14 = r1
            int r14 = r14.getDepth()
            r18 = r14
            r14 = r18
            r15 = r18
            r6 = r15
            r15 = r4
            if (r14 >= r15) goto L_0x004f
            r14 = r5
            r15 = 3
            if (r14 == r15) goto L_0x00e9
        L_0x004f:
            r14 = r5
            r15 = 2
            if (r14 == r15) goto L_0x0054
            goto L_0x002d
        L_0x0054:
            r14 = r6
            r15 = r4
            if (r14 > r15) goto L_0x002d
            r14 = r1
            java.lang.String r14 = r14.getName()
            java.lang.String r15 = "item"
            boolean r14 = r14.equals(r15)
            if (r14 != 0) goto L_0x0067
            goto L_0x002d
        L_0x0067:
            r14 = r0
            r15 = r3
            r16 = r2
            int[] r17 = android.support.compat.C0020R.styleable.GradientColorItem
            android.content.res.TypedArray r14 = android.support.p000v4.content.res.TypedArrayUtils.obtainAttributes(r14, r15, r16, r17)
            r9 = r14
            r14 = r9
            int r15 = android.support.compat.C0020R.styleable.GradientColorItem_android_color
            boolean r14 = r14.hasValue(r15)
            r10 = r14
            r14 = r9
            int r15 = android.support.compat.C0020R.styleable.GradientColorItem_android_offset
            boolean r14 = r14.hasValue(r15)
            r11 = r14
            r14 = r10
            if (r14 == 0) goto L_0x0088
            r14 = r11
            if (r14 != 0) goto L_0x00bb
        L_0x0088:
            org.xmlpull.v1.XmlPullParserException r14 = new org.xmlpull.v1.XmlPullParserException
            r18 = r14
            r14 = r18
            r15 = r18
            java.lang.StringBuilder r16 = new java.lang.StringBuilder
            r18 = r16
            r16 = r18
            r17 = r18
            r17.<init>()
            r17 = r1
            java.lang.String r17 = r17.getPositionDescription()
            java.lang.StringBuilder r16 = r16.append(r17)
            java.lang.String r17 = ": <item> tag requires a 'color' attribute and a 'offset' "
            java.lang.StringBuilder r16 = r16.append(r17)
            java.lang.String r17 = "attribute!"
            java.lang.StringBuilder r16 = r16.append(r17)
            java.lang.String r16 = r16.toString()
            r15.<init>(r16)
            throw r14
        L_0x00bb:
            r14 = r9
            int r15 = android.support.compat.C0020R.styleable.GradientColorItem_android_color
            r16 = 0
            int r14 = r14.getColor(r15, r16)
            r12 = r14
            r14 = r9
            int r15 = android.support.compat.C0020R.styleable.GradientColorItem_android_offset
            r16 = 0
            float r14 = r14.getFloat(r15, r16)
            r13 = r14
            r14 = r9
            r14.recycle()
            r14 = r8
            r15 = r12
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)
            boolean r14 = r14.add(r15)
            r14 = r7
            r15 = r13
            java.lang.Float r15 = java.lang.Float.valueOf(r15)
            boolean r14 = r14.add(r15)
            goto L_0x002d
        L_0x00e9:
            r14 = r8
            int r14 = r14.size()
            if (r14 <= 0) goto L_0x0101
            android.support.v4.content.res.GradientColorInflaterCompat$ColorStops r14 = new android.support.v4.content.res.GradientColorInflaterCompat$ColorStops
            r18 = r14
            r14 = r18
            r15 = r18
            r16 = r8
            r17 = r7
            r15.<init>((java.util.List<java.lang.Integer>) r16, (java.util.List<java.lang.Float>) r17)
            r0 = r14
        L_0x0100:
            return r0
        L_0x0101:
            r14 = 0
            r0 = r14
            goto L_0x0100
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.content.res.GradientColorInflaterCompat.inflateChildElements(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):android.support.v4.content.res.GradientColorInflaterCompat$ColorStops");
    }

    private static ColorStops checkColors(@Nullable ColorStops colorStops, @ColorInt int i, @ColorInt int i2, boolean z, @ColorInt int i3) {
        ColorStops colorItems;
        ColorStops colorItems2;
        ColorStops colorItems3 = colorStops;
        int startColor = i;
        int endColor = i2;
        boolean hasCenterColor = z;
        int centerColor = i3;
        if (colorItems3 != null) {
            return colorItems3;
        }
        if (hasCenterColor) {
            new ColorStops(startColor, centerColor, endColor);
            return colorItems2;
        }
        new ColorStops(startColor, endColor);
        return colorItems;
    }

    private static Shader.TileMode parseTileMode(int tileMode) {
        switch (tileMode) {
            case 1:
                return Shader.TileMode.REPEAT;
            case 2:
                return Shader.TileMode.MIRROR;
            default:
                return Shader.TileMode.CLAMP;
        }
    }

    /* renamed from: android.support.v4.content.res.GradientColorInflaterCompat$ColorStops */
    static final class ColorStops {
        final int[] mColors;
        final float[] mOffsets;

        ColorStops(@NonNull List<Integer> list, @NonNull List<Float> list2) {
            List<Integer> colorsList = list;
            List<Float> offsetsList = list2;
            int size = colorsList.size();
            this.mColors = new int[size];
            this.mOffsets = new float[size];
            for (int i = 0; i < size; i++) {
                this.mColors[i] = colorsList.get(i).intValue();
                this.mOffsets[i] = offsetsList.get(i).floatValue();
            }
        }

        ColorStops(@ColorInt int startColor, @ColorInt int endColor) {
            int[] iArr = new int[2];
            iArr[0] = startColor;
            int[] iArr2 = iArr;
            iArr2[1] = endColor;
            this.mColors = iArr2;
            this.mOffsets = new float[]{0.0f, 1.0f};
        }

        ColorStops(@ColorInt int startColor, @ColorInt int centerColor, @ColorInt int endColor) {
            int[] iArr = new int[3];
            iArr[0] = startColor;
            int[] iArr2 = iArr;
            iArr2[1] = centerColor;
            int[] iArr3 = iArr2;
            iArr3[2] = endColor;
            this.mColors = iArr3;
            this.mOffsets = new float[]{0.0f, 0.5f, 1.0f};
        }
    }
}
