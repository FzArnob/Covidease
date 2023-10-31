package android.support.p000v4.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Shader;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.content.res.ComplexColorCompat */
public final class ComplexColorCompat {
    private static final String LOG_TAG = "ComplexColorCompat";
    private int mColor;
    private final ColorStateList mColorStateList;
    private final Shader mShader;

    private ComplexColorCompat(Shader shader, ColorStateList colorStateList, @ColorInt int color) {
        this.mShader = shader;
        this.mColorStateList = colorStateList;
        this.mColor = color;
    }

    static ComplexColorCompat from(@NonNull Shader shader) {
        ComplexColorCompat complexColorCompat;
        new ComplexColorCompat(shader, (ColorStateList) null, 0);
        return complexColorCompat;
    }

    static ComplexColorCompat from(@NonNull ColorStateList colorStateList) {
        ComplexColorCompat complexColorCompat;
        ColorStateList colorStateList2 = colorStateList;
        new ComplexColorCompat((Shader) null, colorStateList2, colorStateList2.getDefaultColor());
        return complexColorCompat;
    }

    static ComplexColorCompat from(@ColorInt int color) {
        ComplexColorCompat complexColorCompat;
        new ComplexColorCompat((Shader) null, (ColorStateList) null, color);
        return complexColorCompat;
    }

    @Nullable
    public Shader getShader() {
        return this.mShader;
    }

    @ColorInt
    public int getColor() {
        return this.mColor;
    }

    public void setColor(@ColorInt int color) {
        int i = color;
        this.mColor = i;
    }

    public boolean isGradient() {
        return this.mShader != null;
    }

    public boolean isStateful() {
        return this.mShader == null && this.mColorStateList != null && this.mColorStateList.isStateful();
    }

    public boolean onStateChanged(int[] iArr) {
        int[] stateSet = iArr;
        boolean changed = false;
        if (isStateful()) {
            int colorForState = this.mColorStateList.getColorForState(stateSet, this.mColorStateList.getDefaultColor());
            if (colorForState != this.mColor) {
                changed = true;
                this.mColor = colorForState;
            }
        }
        return changed;
    }

    public boolean willDraw() {
        return isGradient() || this.mColor != 0;
    }

    @Nullable
    public static ComplexColorCompat inflate(@NonNull Resources resources, @ColorRes int resId, @Nullable Resources.Theme theme) {
        try {
            return createFromXml(resources, resId, theme);
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, "Failed to inflate ComplexColor.", e);
            return null;
        }
    }

    @NonNull
    private static ComplexColorCompat createFromXml(@NonNull Resources resources, @ColorRes int resId, @Nullable Resources.Theme theme) throws IOException, XmlPullParserException {
        int type;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        Resources resources2 = resources;
        Resources.Theme theme2 = theme;
        XmlPullParser parser = resources2.getXml(resId);
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
        if (type != 2) {
            Throwable th3 = th2;
            new XmlPullParserException("No start tag found");
            throw th3;
        }
        String name = parser.getName();
        String str = name;
        boolean z = true;
        switch (str.hashCode()) {
            case 89650992:
                if (str.equals("gradient")) {
                    z = true;
                    break;
                }
                break;
            case 1191572447:
                if (str.equals("selector")) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                return from(ColorStateListInflaterCompat.createFromXmlInner(resources2, parser, attrs, theme2));
            case true:
                return from(GradientColorInflaterCompat.createFromXmlInner(resources2, parser, attrs, theme2));
            default:
                Throwable th4 = th;
                new StringBuilder();
                new XmlPullParserException(sb.append(parser.getPositionDescription()).append(": unsupported complex color tag ").append(name).toString());
                throw th4;
        }
    }
}
