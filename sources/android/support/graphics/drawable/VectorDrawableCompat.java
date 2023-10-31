package android.support.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.p000v4.content.res.ComplexColorCompat;
import android.support.p000v4.content.res.ResourcesCompat;
import android.support.p000v4.content.res.TypedArrayUtils;
import android.support.p000v4.graphics.PathParser;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p000v4.util.C1642ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class VectorDrawableCompat extends VectorDrawableCommon {
    private static final boolean DBG_VECTOR_DRAWABLE = false;
    static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
    private static final int LINECAP_BUTT = 0;
    private static final int LINECAP_ROUND = 1;
    private static final int LINECAP_SQUARE = 2;
    private static final int LINEJOIN_BEVEL = 2;
    private static final int LINEJOIN_MITER = 0;
    private static final int LINEJOIN_ROUND = 1;
    static final String LOGTAG = "VectorDrawableCompat";
    private static final int MAX_CACHED_BITMAP_SIZE = 2048;
    private static final String SHAPE_CLIP_PATH = "clip-path";
    private static final String SHAPE_GROUP = "group";
    private static final String SHAPE_PATH = "path";
    private static final String SHAPE_VECTOR = "vector";
    private boolean mAllowCaching = true;
    private Drawable.ConstantState mCachedConstantStateDelegate;
    private ColorFilter mColorFilter;
    private boolean mMutated;
    private PorterDuffColorFilter mTintFilter;
    private final Rect mTmpBounds;
    private final float[] mTmpFloats = new float[9];
    private final Matrix mTmpMatrix;
    private VectorDrawableCompatState mVectorState;

    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    public /* bridge */ /* synthetic */ ColorFilter getColorFilter() {
        return super.getColorFilter();
    }

    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i) {
        super.setChangingConfigurations(i);
    }

    public /* bridge */ /* synthetic */ void setColorFilter(int i, PorterDuff.Mode mode) {
        super.setColorFilter(i, mode);
    }

    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    public /* bridge */ /* synthetic */ void setHotspot(float f, float f2) {
        super.setHotspot(f, f2);
    }

    public /* bridge */ /* synthetic */ void setHotspotBounds(int i, int i2, int i3, int i4) {
        super.setHotspotBounds(i, i2, i3, i4);
    }

    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    VectorDrawableCompat() {
        Matrix matrix;
        Rect rect;
        VectorDrawableCompatState vectorDrawableCompatState;
        new Matrix();
        this.mTmpMatrix = matrix;
        new Rect();
        this.mTmpBounds = rect;
        new VectorDrawableCompatState();
        this.mVectorState = vectorDrawableCompatState;
    }

    VectorDrawableCompat(@NonNull VectorDrawableCompatState vectorDrawableCompatState) {
        Matrix matrix;
        Rect rect;
        VectorDrawableCompatState state = vectorDrawableCompatState;
        new Matrix();
        this.mTmpMatrix = matrix;
        new Rect();
        this.mTmpBounds = rect;
        this.mVectorState = state;
        this.mTintFilter = updateTintFilter(this.mTintFilter, state.mTint, state.mTintMode);
    }

    public Drawable mutate() {
        VectorDrawableCompatState vectorDrawableCompatState;
        if (this.mDelegateDrawable != null) {
            Drawable mutate = this.mDelegateDrawable.mutate();
            return this;
        }
        if (!this.mMutated && super.mutate() == this) {
            new VectorDrawableCompatState(this.mVectorState);
            this.mVectorState = vectorDrawableCompatState;
            this.mMutated = true;
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public Object getTargetByName(String name) {
        return this.mVectorState.mVPathRenderer.mVGTargetsMap.get(name);
    }

    public Drawable.ConstantState getConstantState() {
        Drawable.ConstantState constantState;
        if (this.mDelegateDrawable == null || Build.VERSION.SDK_INT < 24) {
            this.mVectorState.mChangingConfigurations = getChangingConfigurations();
            return this.mVectorState;
        }
        new VectorDrawableDelegateState(this.mDelegateDrawable.getConstantState());
        return constantState;
    }

    public void draw(Canvas canvas) {
        ColorFilter colorFilter;
        Canvas canvas2 = canvas;
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.draw(canvas2);
            return;
        }
        copyBounds(this.mTmpBounds);
        if (this.mTmpBounds.width() > 0 && this.mTmpBounds.height() > 0) {
            if (this.mColorFilter == null) {
                colorFilter = this.mTintFilter;
            } else {
                colorFilter = this.mColorFilter;
            }
            ColorFilter colorFilter2 = colorFilter;
            canvas2.getMatrix(this.mTmpMatrix);
            this.mTmpMatrix.getValues(this.mTmpFloats);
            float canvasScaleX = Math.abs(this.mTmpFloats[0]);
            float canvasScaleY = Math.abs(this.mTmpFloats[4]);
            float canvasSkewX = Math.abs(this.mTmpFloats[1]);
            float canvasSkewY = Math.abs(this.mTmpFloats[3]);
            if (!(canvasSkewX == 0.0f && canvasSkewY == 0.0f)) {
                canvasScaleX = 1.0f;
                canvasScaleY = 1.0f;
            }
            int scaledWidth = (int) (((float) this.mTmpBounds.width()) * canvasScaleX);
            int scaledHeight = (int) (((float) this.mTmpBounds.height()) * canvasScaleY);
            int scaledWidth2 = Math.min(2048, scaledWidth);
            int scaledHeight2 = Math.min(2048, scaledHeight);
            if (scaledWidth2 > 0 && scaledHeight2 > 0) {
                int saveCount = canvas2.save();
                canvas2.translate((float) this.mTmpBounds.left, (float) this.mTmpBounds.top);
                if (needMirroring()) {
                    canvas2.translate((float) this.mTmpBounds.width(), 0.0f);
                    canvas2.scale(-1.0f, 1.0f);
                }
                this.mTmpBounds.offsetTo(0, 0);
                this.mVectorState.createCachedBitmapIfNeeded(scaledWidth2, scaledHeight2);
                if (!this.mAllowCaching) {
                    this.mVectorState.updateCachedBitmap(scaledWidth2, scaledHeight2);
                } else if (!this.mVectorState.canReuseCache()) {
                    this.mVectorState.updateCachedBitmap(scaledWidth2, scaledHeight2);
                    this.mVectorState.updateCacheStates();
                }
                this.mVectorState.drawCachedBitmapWithRootAlpha(canvas2, colorFilter2, this.mTmpBounds);
                canvas2.restoreToCount(saveCount);
            }
        }
    }

    public int getAlpha() {
        if (this.mDelegateDrawable != null) {
            return DrawableCompat.getAlpha(this.mDelegateDrawable);
        }
        return this.mVectorState.mVPathRenderer.getRootAlpha();
    }

    public void setAlpha(int i) {
        int alpha = i;
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setAlpha(alpha);
        } else if (this.mVectorState.mVPathRenderer.getRootAlpha() != alpha) {
            this.mVectorState.mVPathRenderer.setRootAlpha(alpha);
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        ColorFilter colorFilter2 = colorFilter;
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setColorFilter(colorFilter2);
            return;
        }
        this.mColorFilter = colorFilter2;
        invalidateSelf();
    }

    /* access modifiers changed from: package-private */
    public PorterDuffColorFilter updateTintFilter(PorterDuffColorFilter porterDuffColorFilter, ColorStateList colorStateList, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter2;
        PorterDuffColorFilter porterDuffColorFilter3 = porterDuffColorFilter;
        ColorStateList tint = colorStateList;
        PorterDuff.Mode tintMode = mode;
        if (tint == null || tintMode == null) {
            return null;
        }
        new PorterDuffColorFilter(tint.getColorForState(getState(), 0), tintMode);
        return porterDuffColorFilter2;
    }

    public void setTint(int i) {
        int tint = i;
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setTint(this.mDelegateDrawable, tint);
        } else {
            setTintList(ColorStateList.valueOf(tint));
        }
    }

    public void setTintList(ColorStateList colorStateList) {
        ColorStateList tint = colorStateList;
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setTintList(this.mDelegateDrawable, tint);
            return;
        }
        VectorDrawableCompatState state = this.mVectorState;
        if (state.mTint != tint) {
            state.mTint = tint;
            this.mTintFilter = updateTintFilter(this.mTintFilter, tint, state.mTintMode);
            invalidateSelf();
        }
    }

    public void setTintMode(PorterDuff.Mode mode) {
        PorterDuff.Mode tintMode = mode;
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setTintMode(this.mDelegateDrawable, tintMode);
            return;
        }
        VectorDrawableCompatState state = this.mVectorState;
        if (state.mTintMode != tintMode) {
            state.mTintMode = tintMode;
            this.mTintFilter = updateTintFilter(this.mTintFilter, state.mTint, tintMode);
            invalidateSelf();
        }
    }

    public boolean isStateful() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.isStateful();
        }
        return super.isStateful() || (this.mVectorState != null && (this.mVectorState.isStateful() || (this.mVectorState.mTint != null && this.mVectorState.mTint.isStateful())));
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        int[] stateSet = iArr;
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.setState(stateSet);
        }
        boolean changed = false;
        VectorDrawableCompatState state = this.mVectorState;
        if (!(state.mTint == null || state.mTintMode == null)) {
            this.mTintFilter = updateTintFilter(this.mTintFilter, state.mTint, state.mTintMode);
            invalidateSelf();
            changed = true;
        }
        if (state.isStateful() && state.onStateChanged(stateSet)) {
            invalidateSelf();
            changed = true;
        }
        return changed;
    }

    public int getOpacity() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getOpacity();
        }
        return -3;
    }

    public int getIntrinsicWidth() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getIntrinsicWidth();
        }
        return (int) this.mVectorState.mVPathRenderer.mBaseWidth;
    }

    public int getIntrinsicHeight() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getIntrinsicHeight();
        }
        return (int) this.mVectorState.mVPathRenderer.mBaseHeight;
    }

    public boolean canApplyTheme() {
        if (this.mDelegateDrawable != null) {
            boolean canApplyTheme = DrawableCompat.canApplyTheme(this.mDelegateDrawable);
        }
        return false;
    }

    public boolean isAutoMirrored() {
        if (this.mDelegateDrawable != null) {
            return DrawableCompat.isAutoMirrored(this.mDelegateDrawable);
        }
        return this.mVectorState.mAutoMirrored;
    }

    public void setAutoMirrored(boolean z) {
        boolean mirrored = z;
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setAutoMirrored(this.mDelegateDrawable, mirrored);
            return;
        }
        this.mVectorState.mAutoMirrored = mirrored;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public float getPixelSize() {
        if (this.mVectorState == null || this.mVectorState.mVPathRenderer == null || this.mVectorState.mVPathRenderer.mBaseWidth == 0.0f || this.mVectorState.mVPathRenderer.mBaseHeight == 0.0f || this.mVectorState.mVPathRenderer.mViewportHeight == 0.0f || this.mVectorState.mVPathRenderer.mViewportWidth == 0.0f) {
            return 1.0f;
        }
        float intrinsicWidth = this.mVectorState.mVPathRenderer.mBaseWidth;
        float intrinsicHeight = this.mVectorState.mVPathRenderer.mBaseHeight;
        float viewportWidth = this.mVectorState.mVPathRenderer.mViewportWidth;
        return Math.min(viewportWidth / intrinsicWidth, this.mVectorState.mVPathRenderer.mViewportHeight / intrinsicHeight);
    }

    @Nullable
    public static VectorDrawableCompat create(@NonNull Resources resources, @DrawableRes int i, @Nullable Resources.Theme theme) {
        int type;
        Throwable th;
        VectorDrawableCompat vectorDrawableCompat;
        Drawable.ConstantState constantState;
        Resources res = resources;
        int resId = i;
        Resources.Theme theme2 = theme;
        if (Build.VERSION.SDK_INT >= 24) {
            new VectorDrawableCompat();
            VectorDrawableCompat drawable = vectorDrawableCompat;
            drawable.mDelegateDrawable = ResourcesCompat.getDrawable(res, resId, theme2);
            new VectorDrawableDelegateState(drawable.mDelegateDrawable.getConstantState());
            drawable.mCachedConstantStateDelegate = constantState;
            return drawable;
        }
        try {
            XmlPullParser parser = res.getXml(resId);
            AttributeSet attrs = Xml.asAttributeSet(parser);
            while (true) {
                int next = parser.next();
                type = next;
                if (next == 2 || type == 1) {
                }
            }
            if (type == 2) {
                return createFromXmlInner(res, parser, attrs, theme2);
            }
            Throwable th2 = th;
            new XmlPullParserException("No start tag found");
            throw th2;
        } catch (XmlPullParserException e) {
            int e2 = Log.e(LOGTAG, "parser error", e);
            return null;
        } catch (IOException e3) {
            int e4 = Log.e(LOGTAG, "parser error", e3);
            return null;
        }
    }

    public static VectorDrawableCompat createFromXmlInner(Resources r, XmlPullParser parser, AttributeSet attrs, Resources.Theme theme) throws XmlPullParserException, IOException {
        VectorDrawableCompat vectorDrawableCompat;
        new VectorDrawableCompat();
        VectorDrawableCompat drawable = vectorDrawableCompat;
        drawable.inflate(r, parser, attrs, theme);
        return drawable;
    }

    static int applyAlpha(int i, float alpha) {
        int color = i;
        return (color & 16777215) | (((int) (((float) Color.alpha(color)) * alpha)) << 24);
    }

    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        Resources res = resources;
        XmlPullParser parser = xmlPullParser;
        AttributeSet attrs = attributeSet;
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.inflate(res, parser, attrs);
        } else {
            inflate(res, parser, attrs, (Resources.Theme) null);
        }
    }

    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        VPathRenderer pathRenderer;
        Resources res = resources;
        XmlPullParser parser = xmlPullParser;
        AttributeSet attrs = attributeSet;
        Resources.Theme theme2 = theme;
        if (this.mDelegateDrawable != null) {
            DrawableCompat.inflate(this.mDelegateDrawable, res, parser, attrs, theme2);
            return;
        }
        VectorDrawableCompatState state = this.mVectorState;
        new VPathRenderer();
        state.mVPathRenderer = pathRenderer;
        TypedArray a = TypedArrayUtils.obtainAttributes(res, theme2, attrs, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_TYPE_ARRAY);
        updateStateFromTypedArray(a, parser);
        a.recycle();
        state.mChangingConfigurations = getChangingConfigurations();
        state.mCacheDirty = true;
        inflateInternal(res, parser, attrs, theme2);
        this.mTintFilter = updateTintFilter(this.mTintFilter, state.mTint, state.mTintMode);
    }

    private static PorterDuff.Mode parseTintModeCompat(int value, PorterDuff.Mode mode) {
        PorterDuff.Mode defaultMode = mode;
        switch (value) {
            case 3:
                return PorterDuff.Mode.SRC_OVER;
            case 5:
                return PorterDuff.Mode.SRC_IN;
            case 9:
                return PorterDuff.Mode.SRC_ATOP;
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return defaultMode;
        }
    }

    private void updateStateFromTypedArray(TypedArray typedArray, XmlPullParser xmlPullParser) throws XmlPullParserException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        StringBuilder sb3;
        Throwable th4;
        StringBuilder sb4;
        TypedArray a = typedArray;
        XmlPullParser parser = xmlPullParser;
        VectorDrawableCompatState state = this.mVectorState;
        VPathRenderer pathRenderer = state.mVPathRenderer;
        state.mTintMode = parseTintModeCompat(TypedArrayUtils.getNamedInt(a, parser, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
        ColorStateList tint = a.getColorStateList(1);
        if (tint != null) {
            state.mTint = tint;
        }
        state.mAutoMirrored = TypedArrayUtils.getNamedBoolean(a, parser, "autoMirrored", 5, state.mAutoMirrored);
        pathRenderer.mViewportWidth = TypedArrayUtils.getNamedFloat(a, parser, "viewportWidth", 7, pathRenderer.mViewportWidth);
        pathRenderer.mViewportHeight = TypedArrayUtils.getNamedFloat(a, parser, "viewportHeight", 8, pathRenderer.mViewportHeight);
        if (pathRenderer.mViewportWidth <= 0.0f) {
            Throwable th5 = th4;
            new StringBuilder();
            new XmlPullParserException(sb4.append(a.getPositionDescription()).append("<vector> tag requires viewportWidth > 0").toString());
            throw th5;
        } else if (pathRenderer.mViewportHeight <= 0.0f) {
            Throwable th6 = th3;
            new StringBuilder();
            new XmlPullParserException(sb3.append(a.getPositionDescription()).append("<vector> tag requires viewportHeight > 0").toString());
            throw th6;
        } else {
            pathRenderer.mBaseWidth = a.getDimension(3, pathRenderer.mBaseWidth);
            pathRenderer.mBaseHeight = a.getDimension(2, pathRenderer.mBaseHeight);
            if (pathRenderer.mBaseWidth <= 0.0f) {
                Throwable th7 = th2;
                new StringBuilder();
                new XmlPullParserException(sb2.append(a.getPositionDescription()).append("<vector> tag requires width > 0").toString());
                throw th7;
            } else if (pathRenderer.mBaseHeight <= 0.0f) {
                Throwable th8 = th;
                new StringBuilder();
                new XmlPullParserException(sb.append(a.getPositionDescription()).append("<vector> tag requires height > 0").toString());
                throw th8;
            } else {
                pathRenderer.setAlpha(TypedArrayUtils.getNamedFloat(a, parser, "alpha", 4, pathRenderer.getAlpha()));
                String name = a.getString(0);
                if (name != null) {
                    pathRenderer.mRootName = name;
                    Object put = pathRenderer.mVGTargetsMap.put(name, pathRenderer);
                }
            }
        }
    }

    private void inflateInternal(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        ArrayDeque arrayDeque;
        Throwable th;
        VGroup vGroup;
        VClipPath vClipPath;
        VFullPath vFullPath;
        Resources res = resources;
        XmlPullParser parser = xmlPullParser;
        AttributeSet attrs = attributeSet;
        Resources.Theme theme2 = theme;
        VectorDrawableCompatState state = this.mVectorState;
        VPathRenderer pathRenderer = state.mVPathRenderer;
        boolean noPathTag = true;
        new ArrayDeque();
        ArrayDeque arrayDeque2 = arrayDeque;
        arrayDeque2.push(pathRenderer.mRootGroup);
        int eventType = parser.getEventType();
        int innerDepth = parser.getDepth() + 1;
        while (eventType != 1 && (parser.getDepth() >= innerDepth || eventType != 3)) {
            if (eventType == 2) {
                String tagName = parser.getName();
                VGroup currentGroup = (VGroup) arrayDeque2.peek();
                if ("path".equals(tagName)) {
                    new VFullPath();
                    VFullPath path = vFullPath;
                    path.inflate(res, attrs, theme2, parser);
                    boolean add = currentGroup.mChildren.add(path);
                    if (path.getPathName() != null) {
                        Object put = pathRenderer.mVGTargetsMap.put(path.getPathName(), path);
                    }
                    noPathTag = false;
                    state.mChangingConfigurations |= path.mChangingConfigurations;
                } else if (SHAPE_CLIP_PATH.equals(tagName)) {
                    new VClipPath();
                    VClipPath path2 = vClipPath;
                    path2.inflate(res, attrs, theme2, parser);
                    boolean add2 = currentGroup.mChildren.add(path2);
                    if (path2.getPathName() != null) {
                        Object put2 = pathRenderer.mVGTargetsMap.put(path2.getPathName(), path2);
                    }
                    state.mChangingConfigurations |= path2.mChangingConfigurations;
                } else if (SHAPE_GROUP.equals(tagName)) {
                    new VGroup();
                    VGroup newChildGroup = vGroup;
                    newChildGroup.inflate(res, attrs, theme2, parser);
                    boolean add3 = currentGroup.mChildren.add(newChildGroup);
                    arrayDeque2.push(newChildGroup);
                    if (newChildGroup.getGroupName() != null) {
                        Object put3 = pathRenderer.mVGTargetsMap.put(newChildGroup.getGroupName(), newChildGroup);
                    }
                    state.mChangingConfigurations |= newChildGroup.mChangingConfigurations;
                }
            } else if (eventType == 3 && SHAPE_GROUP.equals(parser.getName())) {
                Object pop = arrayDeque2.pop();
            }
            eventType = parser.next();
        }
        if (noPathTag) {
            Throwable th2 = th;
            new XmlPullParserException("no path defined");
            throw th2;
        }
    }

    private void printGroupTree(VGroup vGroup, int i) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        VGroup currentGroup = vGroup;
        int level = i;
        String indent = "";
        for (int i2 = 0; i2 < level; i2++) {
            new StringBuilder();
            indent = sb3.append(indent).append("    ").toString();
        }
        new StringBuilder();
        int v = Log.v(LOGTAG, sb.append(indent).append("current group is :").append(currentGroup.getGroupName()).append(" rotation is ").append(currentGroup.mRotate).toString());
        new StringBuilder();
        int v2 = Log.v(LOGTAG, sb2.append(indent).append("matrix is :").append(currentGroup.getLocalMatrix().toString()).toString());
        for (int i3 = 0; i3 < currentGroup.mChildren.size(); i3++) {
            VObject child = currentGroup.mChildren.get(i3);
            if (child instanceof VGroup) {
                printGroupTree((VGroup) child, level + 1);
            } else {
                ((VPath) child).printVPath(level + 1);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setAllowCaching(boolean allowCaching) {
        boolean z = allowCaching;
        this.mAllowCaching = z;
    }

    private boolean needMirroring() {
        if (Build.VERSION.SDK_INT < 17) {
            return false;
        }
        return isAutoMirrored() && DrawableCompat.getLayoutDirection(this) == 1;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Rect bounds = rect;
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setBounds(bounds);
        }
    }

    public int getChangingConfigurations() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getChangingConfigurations();
        }
        return super.getChangingConfigurations() | this.mVectorState.getChangingConfigurations();
    }

    public void invalidateSelf() {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    public void scheduleSelf(Runnable runnable, long j) {
        Runnable what = runnable;
        long when = j;
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.scheduleSelf(what, when);
        } else {
            super.scheduleSelf(what, when);
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = z;
        boolean restart = z2;
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.setVisible(visible, restart);
        }
        return super.setVisible(visible, restart);
    }

    public void unscheduleSelf(Runnable runnable) {
        Runnable what = runnable;
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.unscheduleSelf(what);
        } else {
            super.unscheduleSelf(what);
        }
    }

    @RequiresApi(24)
    private static class VectorDrawableDelegateState extends Drawable.ConstantState {
        private final Drawable.ConstantState mDelegateState;

        public VectorDrawableDelegateState(Drawable.ConstantState state) {
            this.mDelegateState = state;
        }

        public Drawable newDrawable() {
            VectorDrawableCompat vectorDrawableCompat;
            new VectorDrawableCompat();
            VectorDrawableCompat drawableCompat = vectorDrawableCompat;
            drawableCompat.mDelegateDrawable = (VectorDrawable) this.mDelegateState.newDrawable();
            return drawableCompat;
        }

        public Drawable newDrawable(Resources res) {
            VectorDrawableCompat vectorDrawableCompat;
            new VectorDrawableCompat();
            VectorDrawableCompat drawableCompat = vectorDrawableCompat;
            drawableCompat.mDelegateDrawable = (VectorDrawable) this.mDelegateState.newDrawable(res);
            return drawableCompat;
        }

        public Drawable newDrawable(Resources res, Resources.Theme theme) {
            VectorDrawableCompat vectorDrawableCompat;
            new VectorDrawableCompat();
            VectorDrawableCompat drawableCompat = vectorDrawableCompat;
            drawableCompat.mDelegateDrawable = (VectorDrawable) this.mDelegateState.newDrawable(res, theme);
            return drawableCompat;
        }

        public boolean canApplyTheme() {
            return this.mDelegateState.canApplyTheme();
        }

        public int getChangingConfigurations() {
            return this.mDelegateState.getChangingConfigurations();
        }
    }

    private static class VectorDrawableCompatState extends Drawable.ConstantState {
        boolean mAutoMirrored;
        boolean mCacheDirty;
        boolean mCachedAutoMirrored;
        Bitmap mCachedBitmap;
        int mCachedRootAlpha;
        int[] mCachedThemeAttrs;
        ColorStateList mCachedTint;
        PorterDuff.Mode mCachedTintMode;
        int mChangingConfigurations;
        Paint mTempPaint;
        ColorStateList mTint = null;
        PorterDuff.Mode mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
        VPathRenderer mVPathRenderer;

        public VectorDrawableCompatState(VectorDrawableCompatState vectorDrawableCompatState) {
            VPathRenderer vPathRenderer;
            Paint paint;
            Paint paint2;
            VectorDrawableCompatState copy = vectorDrawableCompatState;
            if (copy != null) {
                this.mChangingConfigurations = copy.mChangingConfigurations;
                new VPathRenderer(copy.mVPathRenderer);
                this.mVPathRenderer = vPathRenderer;
                if (copy.mVPathRenderer.mFillPaint != null) {
                    VPathRenderer vPathRenderer2 = this.mVPathRenderer;
                    new Paint(copy.mVPathRenderer.mFillPaint);
                    vPathRenderer2.mFillPaint = paint2;
                }
                if (copy.mVPathRenderer.mStrokePaint != null) {
                    VPathRenderer vPathRenderer3 = this.mVPathRenderer;
                    new Paint(copy.mVPathRenderer.mStrokePaint);
                    vPathRenderer3.mStrokePaint = paint;
                }
                this.mTint = copy.mTint;
                this.mTintMode = copy.mTintMode;
                this.mAutoMirrored = copy.mAutoMirrored;
            }
        }

        public void drawCachedBitmapWithRootAlpha(Canvas canvas, ColorFilter filter, Rect originalBounds) {
            canvas.drawBitmap(this.mCachedBitmap, (Rect) null, originalBounds, getPaint(filter));
        }

        public boolean hasTranslucentRoot() {
            return this.mVPathRenderer.getRootAlpha() < 255;
        }

        public Paint getPaint(ColorFilter colorFilter) {
            Paint paint;
            ColorFilter filter = colorFilter;
            if (!hasTranslucentRoot() && filter == null) {
                return null;
            }
            if (this.mTempPaint == null) {
                new Paint();
                this.mTempPaint = paint;
                this.mTempPaint.setFilterBitmap(true);
            }
            this.mTempPaint.setAlpha(this.mVPathRenderer.getRootAlpha());
            ColorFilter colorFilter2 = this.mTempPaint.setColorFilter(filter);
            return this.mTempPaint;
        }

        public void updateCachedBitmap(int width, int height) {
            Canvas tmpCanvas;
            this.mCachedBitmap.eraseColor(0);
            new Canvas(this.mCachedBitmap);
            this.mVPathRenderer.draw(tmpCanvas, width, height, (ColorFilter) null);
        }

        public void createCachedBitmapIfNeeded(int i, int i2) {
            int width = i;
            int height = i2;
            if (this.mCachedBitmap == null || !canReuseBitmap(width, height)) {
                this.mCachedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                this.mCacheDirty = true;
            }
        }

        public boolean canReuseBitmap(int width, int i) {
            int height = i;
            if (width == this.mCachedBitmap.getWidth() && height == this.mCachedBitmap.getHeight()) {
                return true;
            }
            return false;
        }

        public boolean canReuseCache() {
            if (!this.mCacheDirty && this.mCachedTint == this.mTint && this.mCachedTintMode == this.mTintMode && this.mCachedAutoMirrored == this.mAutoMirrored && this.mCachedRootAlpha == this.mVPathRenderer.getRootAlpha()) {
                return true;
            }
            return false;
        }

        public void updateCacheStates() {
            this.mCachedTint = this.mTint;
            this.mCachedTintMode = this.mTintMode;
            this.mCachedRootAlpha = this.mVPathRenderer.getRootAlpha();
            this.mCachedAutoMirrored = this.mAutoMirrored;
            this.mCacheDirty = false;
        }

        public VectorDrawableCompatState() {
            VPathRenderer vPathRenderer;
            new VPathRenderer();
            this.mVPathRenderer = vPathRenderer;
        }

        @NonNull
        public Drawable newDrawable() {
            Drawable drawable;
            new VectorDrawableCompat(this);
            return drawable;
        }

        @NonNull
        public Drawable newDrawable(Resources resources) {
            Drawable drawable;
            Resources resources2 = resources;
            new VectorDrawableCompat(this);
            return drawable;
        }

        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }

        public boolean isStateful() {
            return this.mVPathRenderer.isStateful();
        }

        public boolean onStateChanged(int[] stateSet) {
            boolean changed = this.mVPathRenderer.onStateChanged(stateSet);
            this.mCacheDirty |= changed;
            return changed;
        }
    }

    private static class VPathRenderer {
        private static final Matrix IDENTITY_MATRIX;
        float mBaseHeight = 0.0f;
        float mBaseWidth = 0.0f;
        private int mChangingConfigurations;
        Paint mFillPaint;
        private final Matrix mFinalPathMatrix;
        Boolean mIsStateful = null;
        private final Path mPath;
        private PathMeasure mPathMeasure;
        private final Path mRenderPath;
        int mRootAlpha = 255;
        final VGroup mRootGroup;
        String mRootName = null;
        Paint mStrokePaint;
        final C1642ArrayMap<String, Object> mVGTargetsMap;
        float mViewportHeight = 0.0f;
        float mViewportWidth = 0.0f;

        static {
            Matrix matrix;
            new Matrix();
            IDENTITY_MATRIX = matrix;
        }

        public VPathRenderer() {
            Matrix matrix;
            C1642ArrayMap<String, Object> arrayMap;
            VGroup vGroup;
            Path path;
            Path path2;
            new Matrix();
            this.mFinalPathMatrix = matrix;
            new C1642ArrayMap<>();
            this.mVGTargetsMap = arrayMap;
            new VGroup();
            this.mRootGroup = vGroup;
            new Path();
            this.mPath = path;
            new Path();
            this.mRenderPath = path2;
        }

        public void setRootAlpha(int alpha) {
            int i = alpha;
            this.mRootAlpha = i;
        }

        public int getRootAlpha() {
            return this.mRootAlpha;
        }

        public void setAlpha(float alpha) {
            setRootAlpha((int) (alpha * 255.0f));
        }

        public float getAlpha() {
            return ((float) getRootAlpha()) / 255.0f;
        }

        public VPathRenderer(VPathRenderer vPathRenderer) {
            Matrix matrix;
            C1642ArrayMap<String, Object> arrayMap;
            VGroup vGroup;
            Path path;
            Path path2;
            VPathRenderer copy = vPathRenderer;
            new Matrix();
            this.mFinalPathMatrix = matrix;
            new C1642ArrayMap<>();
            this.mVGTargetsMap = arrayMap;
            new VGroup(copy.mRootGroup, this.mVGTargetsMap);
            this.mRootGroup = vGroup;
            new Path(copy.mPath);
            this.mPath = path;
            new Path(copy.mRenderPath);
            this.mRenderPath = path2;
            this.mBaseWidth = copy.mBaseWidth;
            this.mBaseHeight = copy.mBaseHeight;
            this.mViewportWidth = copy.mViewportWidth;
            this.mViewportHeight = copy.mViewportHeight;
            this.mChangingConfigurations = copy.mChangingConfigurations;
            this.mRootAlpha = copy.mRootAlpha;
            this.mRootName = copy.mRootName;
            if (copy.mRootName != null) {
                Object put = this.mVGTargetsMap.put(copy.mRootName, this);
            }
            this.mIsStateful = copy.mIsStateful;
        }

        private void drawGroupTree(VGroup vGroup, Matrix currentMatrix, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            VGroup currentGroup = vGroup;
            Canvas canvas2 = canvas;
            int w = i;
            int h = i2;
            ColorFilter filter = colorFilter;
            currentGroup.mStackedMatrix.set(currentMatrix);
            boolean preConcat = currentGroup.mStackedMatrix.preConcat(currentGroup.mLocalMatrix);
            int save = canvas2.save();
            for (int i3 = 0; i3 < currentGroup.mChildren.size(); i3++) {
                VObject child = currentGroup.mChildren.get(i3);
                if (child instanceof VGroup) {
                    drawGroupTree((VGroup) child, currentGroup.mStackedMatrix, canvas2, w, h, filter);
                } else if (child instanceof VPath) {
                    drawPath(currentGroup, (VPath) child, canvas2, w, h, filter);
                }
            }
            canvas2.restore();
        }

        public void draw(Canvas canvas, int w, int h, ColorFilter filter) {
            drawGroupTree(this.mRootGroup, IDENTITY_MATRIX, canvas, w, h, filter);
        }

        private void drawPath(VGroup vGroup, VPath vPath, Canvas canvas, int w, int h, ColorFilter colorFilter) {
            Paint paint;
            Paint paint2;
            PathMeasure pathMeasure;
            VPath vPath2 = vPath;
            Canvas canvas2 = canvas;
            ColorFilter filter = colorFilter;
            float scaleX = ((float) w) / this.mViewportWidth;
            float scaleY = ((float) h) / this.mViewportHeight;
            float minScale = Math.min(scaleX, scaleY);
            Matrix groupStackedMatrix = vGroup.mStackedMatrix;
            this.mFinalPathMatrix.set(groupStackedMatrix);
            boolean postScale = this.mFinalPathMatrix.postScale(scaleX, scaleY);
            float matrixScale = getMatrixScale(groupStackedMatrix);
            if (matrixScale != 0.0f) {
                vPath2.toPath(this.mPath);
                Path path = this.mPath;
                this.mRenderPath.reset();
                if (vPath2.isClipPath()) {
                    this.mRenderPath.addPath(path, this.mFinalPathMatrix);
                    boolean clipPath = canvas2.clipPath(this.mRenderPath);
                    return;
                }
                VFullPath fullPath = (VFullPath) vPath2;
                if (!(fullPath.mTrimPathStart == 0.0f && fullPath.mTrimPathEnd == 1.0f)) {
                    float start = (fullPath.mTrimPathStart + fullPath.mTrimPathOffset) % 1.0f;
                    float end = (fullPath.mTrimPathEnd + fullPath.mTrimPathOffset) % 1.0f;
                    if (this.mPathMeasure == null) {
                        new PathMeasure();
                        this.mPathMeasure = pathMeasure;
                    }
                    this.mPathMeasure.setPath(this.mPath, false);
                    float len = this.mPathMeasure.getLength();
                    float start2 = start * len;
                    float end2 = end * len;
                    path.reset();
                    if (start2 > end2) {
                        boolean segment = this.mPathMeasure.getSegment(start2, len, path, true);
                        boolean segment2 = this.mPathMeasure.getSegment(0.0f, end2, path, true);
                    } else {
                        boolean segment3 = this.mPathMeasure.getSegment(start2, end2, path, true);
                    }
                    path.rLineTo(0.0f, 0.0f);
                }
                this.mRenderPath.addPath(path, this.mFinalPathMatrix);
                if (fullPath.mFillColor.willDraw()) {
                    ComplexColorCompat fill = fullPath.mFillColor;
                    if (this.mFillPaint == null) {
                        new Paint(1);
                        this.mFillPaint = paint2;
                        this.mFillPaint.setStyle(Paint.Style.FILL);
                    }
                    Paint fillPaint = this.mFillPaint;
                    if (fill.isGradient()) {
                        Shader shader = fill.getShader();
                        shader.setLocalMatrix(this.mFinalPathMatrix);
                        Shader shader2 = fillPaint.setShader(shader);
                        fillPaint.setAlpha(Math.round(fullPath.mFillAlpha * 255.0f));
                    } else {
                        fillPaint.setColor(VectorDrawableCompat.applyAlpha(fill.getColor(), fullPath.mFillAlpha));
                    }
                    ColorFilter colorFilter2 = fillPaint.setColorFilter(filter);
                    this.mRenderPath.setFillType(fullPath.mFillRule == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                    canvas2.drawPath(this.mRenderPath, fillPaint);
                }
                if (fullPath.mStrokeColor.willDraw()) {
                    ComplexColorCompat strokeColor = fullPath.mStrokeColor;
                    if (this.mStrokePaint == null) {
                        new Paint(1);
                        this.mStrokePaint = paint;
                        this.mStrokePaint.setStyle(Paint.Style.STROKE);
                    }
                    Paint strokePaint = this.mStrokePaint;
                    if (fullPath.mStrokeLineJoin != null) {
                        strokePaint.setStrokeJoin(fullPath.mStrokeLineJoin);
                    }
                    if (fullPath.mStrokeLineCap != null) {
                        strokePaint.setStrokeCap(fullPath.mStrokeLineCap);
                    }
                    strokePaint.setStrokeMiter(fullPath.mStrokeMiterlimit);
                    if (strokeColor.isGradient()) {
                        Shader shader3 = strokeColor.getShader();
                        shader3.setLocalMatrix(this.mFinalPathMatrix);
                        Shader shader4 = strokePaint.setShader(shader3);
                        strokePaint.setAlpha(Math.round(fullPath.mStrokeAlpha * 255.0f));
                    } else {
                        strokePaint.setColor(VectorDrawableCompat.applyAlpha(strokeColor.getColor(), fullPath.mStrokeAlpha));
                    }
                    ColorFilter colorFilter3 = strokePaint.setColorFilter(filter);
                    strokePaint.setStrokeWidth(fullPath.mStrokeWidth * minScale * matrixScale);
                    canvas2.drawPath(this.mRenderPath, strokePaint);
                }
            }
        }

        private static float cross(float v1x, float v1y, float v2x, float v2y) {
            return (v1x * v2y) - (v1y * v2x);
        }

        private float getMatrixScale(Matrix groupStackedMatrix) {
            float[] unitVectors = {0.0f, 1.0f, 1.0f, 0.0f};
            groupStackedMatrix.mapVectors(unitVectors);
            float scaleX = (float) Math.hypot((double) unitVectors[0], (double) unitVectors[1]);
            float scaleY = (float) Math.hypot((double) unitVectors[2], (double) unitVectors[3]);
            float crossProduct = cross(unitVectors[0], unitVectors[1], unitVectors[2], unitVectors[3]);
            float maxScale = Math.max(scaleX, scaleY);
            float matrixScale = 0.0f;
            if (maxScale > 0.0f) {
                matrixScale = Math.abs(crossProduct) / maxScale;
            }
            return matrixScale;
        }

        public boolean isStateful() {
            if (this.mIsStateful == null) {
                this.mIsStateful = Boolean.valueOf(this.mRootGroup.isStateful());
            }
            return this.mIsStateful.booleanValue();
        }

        public boolean onStateChanged(int[] stateSet) {
            return this.mRootGroup.onStateChanged(stateSet);
        }
    }

    private static abstract class VObject {
        private VObject() {
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ VObject(C01661 r4) {
            this();
            C01661 r1 = r4;
        }

        public boolean isStateful() {
            return false;
        }

        public boolean onStateChanged(int[] iArr) {
            int[] iArr2 = iArr;
            return false;
        }
    }

    private static class VGroup extends VObject {
        int mChangingConfigurations;
        final ArrayList<VObject> mChildren;
        private String mGroupName;
        final Matrix mLocalMatrix;
        private float mPivotX = 0.0f;
        private float mPivotY = 0.0f;
        float mRotate = 0.0f;
        private float mScaleX = 1.0f;
        private float mScaleY = 1.0f;
        final Matrix mStackedMatrix;
        private int[] mThemeAttrs;
        private float mTranslateX = 0.0f;
        private float mTranslateY = 0.0f;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public VGroup(VGroup vGroup, C1642ArrayMap<String, Object> arrayMap) {
            super((C01661) null);
            Matrix matrix;
            ArrayList<VObject> arrayList;
            Matrix matrix2;
            Throwable th;
            VPath vPath;
            VPath newPath;
            VPath vPath2;
            Object obj;
            VGroup copy = vGroup;
            C1642ArrayMap<String, Object> targetsMap = arrayMap;
            new Matrix();
            this.mStackedMatrix = matrix;
            new ArrayList<>();
            this.mChildren = arrayList;
            new Matrix();
            this.mLocalMatrix = matrix2;
            this.mGroupName = null;
            this.mRotate = copy.mRotate;
            this.mPivotX = copy.mPivotX;
            this.mPivotY = copy.mPivotY;
            this.mScaleX = copy.mScaleX;
            this.mScaleY = copy.mScaleY;
            this.mTranslateX = copy.mTranslateX;
            this.mTranslateY = copy.mTranslateY;
            this.mThemeAttrs = copy.mThemeAttrs;
            this.mGroupName = copy.mGroupName;
            this.mChangingConfigurations = copy.mChangingConfigurations;
            if (this.mGroupName != null) {
                Object put = targetsMap.put(this.mGroupName, this);
            }
            this.mLocalMatrix.set(copy.mLocalMatrix);
            ArrayList<VObject> children = copy.mChildren;
            for (int i = 0; i < children.size(); i++) {
                Object copyChild = children.get(i);
                if (copyChild instanceof VGroup) {
                    new VGroup((VGroup) copyChild, targetsMap);
                    boolean add = this.mChildren.add(obj);
                } else {
                    if (copyChild instanceof VFullPath) {
                        new VFullPath((VFullPath) copyChild);
                        newPath = vPath2;
                    } else if (copyChild instanceof VClipPath) {
                        new VClipPath((VClipPath) copyChild);
                        newPath = vPath;
                    } else {
                        Throwable th2 = th;
                        new IllegalStateException("Unknown object in the tree!");
                        throw th2;
                    }
                    boolean add2 = this.mChildren.add(newPath);
                    if (newPath.mPathName != null) {
                        Object put2 = targetsMap.put(newPath.mPathName, newPath);
                    }
                }
            }
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public VGroup() {
            super((C01661) null);
            Matrix matrix;
            ArrayList<VObject> arrayList;
            Matrix matrix2;
            new Matrix();
            this.mStackedMatrix = matrix;
            new ArrayList<>();
            this.mChildren = arrayList;
            new Matrix();
            this.mLocalMatrix = matrix2;
            this.mGroupName = null;
        }

        public String getGroupName() {
            return this.mGroupName;
        }

        public Matrix getLocalMatrix() {
            return this.mLocalMatrix;
        }

        public void inflate(Resources res, AttributeSet attrs, Resources.Theme theme, XmlPullParser parser) {
            TypedArray a = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_GROUP);
            updateStateFromTypedArray(a, parser);
            a.recycle();
        }

        private void updateStateFromTypedArray(TypedArray typedArray, XmlPullParser xmlPullParser) {
            TypedArray a = typedArray;
            XmlPullParser parser = xmlPullParser;
            this.mThemeAttrs = null;
            this.mRotate = TypedArrayUtils.getNamedFloat(a, parser, "rotation", 5, this.mRotate);
            this.mPivotX = a.getFloat(1, this.mPivotX);
            this.mPivotY = a.getFloat(2, this.mPivotY);
            this.mScaleX = TypedArrayUtils.getNamedFloat(a, parser, "scaleX", 3, this.mScaleX);
            this.mScaleY = TypedArrayUtils.getNamedFloat(a, parser, "scaleY", 4, this.mScaleY);
            this.mTranslateX = TypedArrayUtils.getNamedFloat(a, parser, "translateX", 6, this.mTranslateX);
            this.mTranslateY = TypedArrayUtils.getNamedFloat(a, parser, "translateY", 7, this.mTranslateY);
            String groupName = a.getString(0);
            if (groupName != null) {
                this.mGroupName = groupName;
            }
            updateLocalMatrix();
        }

        private void updateLocalMatrix() {
            this.mLocalMatrix.reset();
            boolean postTranslate = this.mLocalMatrix.postTranslate(-this.mPivotX, -this.mPivotY);
            boolean postScale = this.mLocalMatrix.postScale(this.mScaleX, this.mScaleY);
            boolean postRotate = this.mLocalMatrix.postRotate(this.mRotate, 0.0f, 0.0f);
            boolean postTranslate2 = this.mLocalMatrix.postTranslate(this.mTranslateX + this.mPivotX, this.mTranslateY + this.mPivotY);
        }

        public float getRotation() {
            return this.mRotate;
        }

        public void setRotation(float f) {
            float rotation = f;
            if (rotation != this.mRotate) {
                this.mRotate = rotation;
                updateLocalMatrix();
            }
        }

        public float getPivotX() {
            return this.mPivotX;
        }

        public void setPivotX(float f) {
            float pivotX = f;
            if (pivotX != this.mPivotX) {
                this.mPivotX = pivotX;
                updateLocalMatrix();
            }
        }

        public float getPivotY() {
            return this.mPivotY;
        }

        public void setPivotY(float f) {
            float pivotY = f;
            if (pivotY != this.mPivotY) {
                this.mPivotY = pivotY;
                updateLocalMatrix();
            }
        }

        public float getScaleX() {
            return this.mScaleX;
        }

        public void setScaleX(float f) {
            float scaleX = f;
            if (scaleX != this.mScaleX) {
                this.mScaleX = scaleX;
                updateLocalMatrix();
            }
        }

        public float getScaleY() {
            return this.mScaleY;
        }

        public void setScaleY(float f) {
            float scaleY = f;
            if (scaleY != this.mScaleY) {
                this.mScaleY = scaleY;
                updateLocalMatrix();
            }
        }

        public float getTranslateX() {
            return this.mTranslateX;
        }

        public void setTranslateX(float f) {
            float translateX = f;
            if (translateX != this.mTranslateX) {
                this.mTranslateX = translateX;
                updateLocalMatrix();
            }
        }

        public float getTranslateY() {
            return this.mTranslateY;
        }

        public void setTranslateY(float f) {
            float translateY = f;
            if (translateY != this.mTranslateY) {
                this.mTranslateY = translateY;
                updateLocalMatrix();
            }
        }

        public boolean isStateful() {
            for (int i = 0; i < this.mChildren.size(); i++) {
                if (this.mChildren.get(i).isStateful()) {
                    return true;
                }
            }
            return false;
        }

        public boolean onStateChanged(int[] iArr) {
            int[] stateSet = iArr;
            boolean changed = false;
            for (int i = 0; i < this.mChildren.size(); i++) {
                changed |= this.mChildren.get(i).onStateChanged(stateSet);
            }
            return changed;
        }
    }

    private static abstract class VPath extends VObject {
        int mChangingConfigurations;
        protected PathParser.PathDataNode[] mNodes = null;
        String mPathName;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public VPath() {
            super((C01661) null);
        }

        public void printVPath(int i) {
            StringBuilder sb;
            StringBuilder sb2;
            int level = i;
            String indent = "";
            for (int i2 = 0; i2 < level; i2++) {
                new StringBuilder();
                indent = sb2.append(indent).append("    ").toString();
            }
            new StringBuilder();
            int v = Log.v(VectorDrawableCompat.LOGTAG, sb.append(indent).append("current path is :").append(this.mPathName).append(" pathData is ").append(nodesToString(this.mNodes)).toString());
        }

        public String nodesToString(PathParser.PathDataNode[] pathDataNodeArr) {
            StringBuilder sb;
            StringBuilder sb2;
            PathParser.PathDataNode[] nodes = pathDataNodeArr;
            String result = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
            for (int i = 0; i < nodes.length; i++) {
                new StringBuilder();
                result = sb.append(result).append(nodes[i].mType).append(":").toString();
                float[] params = nodes[i].mParams;
                for (int j = 0; j < params.length; j++) {
                    new StringBuilder();
                    result = sb2.append(result).append(params[j]).append(",").toString();
                }
            }
            return result;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public VPath(VPath vPath) {
            super((C01661) null);
            VPath copy = vPath;
            this.mPathName = copy.mPathName;
            this.mChangingConfigurations = copy.mChangingConfigurations;
            this.mNodes = PathParser.deepCopyNodes(copy.mNodes);
        }

        public void toPath(Path path) {
            Path path2 = path;
            path2.reset();
            if (this.mNodes != null) {
                PathParser.PathDataNode.nodesToPath(this.mNodes, path2);
            }
        }

        public String getPathName() {
            return this.mPathName;
        }

        public boolean canApplyTheme() {
            return false;
        }

        public void applyTheme(Resources.Theme t) {
        }

        public boolean isClipPath() {
            return false;
        }

        public PathParser.PathDataNode[] getPathData() {
            return this.mNodes;
        }

        public void setPathData(PathParser.PathDataNode[] pathDataNodeArr) {
            PathParser.PathDataNode[] nodes = pathDataNodeArr;
            if (!PathParser.canMorph(this.mNodes, nodes)) {
                this.mNodes = PathParser.deepCopyNodes(nodes);
                return;
            }
            PathParser.updateNodes(this.mNodes, nodes);
        }
    }

    private static class VClipPath extends VPath {
        public VClipPath() {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public VClipPath(VClipPath copy) {
            super(copy);
        }

        public void inflate(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser parser) {
            Resources r = resources;
            AttributeSet attrs = attributeSet;
            Resources.Theme theme2 = theme;
            if (TypedArrayUtils.hasAttribute(parser, "pathData")) {
                TypedArray a = TypedArrayUtils.obtainAttributes(r, theme2, attrs, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_CLIP_PATH);
                updateStateFromTypedArray(a);
                a.recycle();
            }
        }

        private void updateStateFromTypedArray(TypedArray typedArray) {
            TypedArray a = typedArray;
            String pathName = a.getString(0);
            if (pathName != null) {
                this.mPathName = pathName;
            }
            String pathData = a.getString(1);
            if (pathData != null) {
                this.mNodes = PathParser.createNodesFromPathData(pathData);
            }
        }

        public boolean isClipPath() {
            return true;
        }
    }

    private static class VFullPath extends VPath {
        private static final int FILL_TYPE_WINDING = 0;
        float mFillAlpha = 1.0f;
        ComplexColorCompat mFillColor;
        int mFillRule = 0;
        float mStrokeAlpha = 1.0f;
        ComplexColorCompat mStrokeColor;
        Paint.Cap mStrokeLineCap = Paint.Cap.BUTT;
        Paint.Join mStrokeLineJoin = Paint.Join.MITER;
        float mStrokeMiterlimit = 4.0f;
        float mStrokeWidth = 0.0f;
        private int[] mThemeAttrs;
        float mTrimPathEnd = 1.0f;
        float mTrimPathOffset = 0.0f;
        float mTrimPathStart = 0.0f;

        public VFullPath() {
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public VFullPath(android.support.graphics.drawable.VectorDrawableCompat.VFullPath r5) {
            /*
                r4 = this;
                r0 = r4
                r1 = r5
                r2 = r0
                r3 = r1
                r2.<init>(r3)
                r2 = r0
                r3 = 0
                r2.mStrokeWidth = r3
                r2 = r0
                r3 = 1065353216(0x3f800000, float:1.0)
                r2.mStrokeAlpha = r3
                r2 = r0
                r3 = 0
                r2.mFillRule = r3
                r2 = r0
                r3 = 1065353216(0x3f800000, float:1.0)
                r2.mFillAlpha = r3
                r2 = r0
                r3 = 0
                r2.mTrimPathStart = r3
                r2 = r0
                r3 = 1065353216(0x3f800000, float:1.0)
                r2.mTrimPathEnd = r3
                r2 = r0
                r3 = 0
                r2.mTrimPathOffset = r3
                r2 = r0
                android.graphics.Paint$Cap r3 = android.graphics.Paint.Cap.BUTT
                r2.mStrokeLineCap = r3
                r2 = r0
                android.graphics.Paint$Join r3 = android.graphics.Paint.Join.MITER
                r2.mStrokeLineJoin = r3
                r2 = r0
                r3 = 1082130432(0x40800000, float:4.0)
                r2.mStrokeMiterlimit = r3
                r2 = r0
                r3 = r1
                int[] r3 = r3.mThemeAttrs
                r2.mThemeAttrs = r3
                r2 = r0
                r3 = r1
                android.support.v4.content.res.ComplexColorCompat r3 = r3.mStrokeColor
                r2.mStrokeColor = r3
                r2 = r0
                r3 = r1
                float r3 = r3.mStrokeWidth
                r2.mStrokeWidth = r3
                r2 = r0
                r3 = r1
                float r3 = r3.mStrokeAlpha
                r2.mStrokeAlpha = r3
                r2 = r0
                r3 = r1
                android.support.v4.content.res.ComplexColorCompat r3 = r3.mFillColor
                r2.mFillColor = r3
                r2 = r0
                r3 = r1
                int r3 = r3.mFillRule
                r2.mFillRule = r3
                r2 = r0
                r3 = r1
                float r3 = r3.mFillAlpha
                r2.mFillAlpha = r3
                r2 = r0
                r3 = r1
                float r3 = r3.mTrimPathStart
                r2.mTrimPathStart = r3
                r2 = r0
                r3 = r1
                float r3 = r3.mTrimPathEnd
                r2.mTrimPathEnd = r3
                r2 = r0
                r3 = r1
                float r3 = r3.mTrimPathOffset
                r2.mTrimPathOffset = r3
                r2 = r0
                r3 = r1
                android.graphics.Paint$Cap r3 = r3.mStrokeLineCap
                r2.mStrokeLineCap = r3
                r2 = r0
                r3 = r1
                android.graphics.Paint$Join r3 = r3.mStrokeLineJoin
                r2.mStrokeLineJoin = r3
                r2 = r0
                r3 = r1
                float r3 = r3.mStrokeMiterlimit
                r2.mStrokeMiterlimit = r3
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.graphics.drawable.VectorDrawableCompat.VFullPath.<init>(android.support.graphics.drawable.VectorDrawableCompat$VFullPath):void");
        }

        private Paint.Cap getStrokeLineCap(int id, Paint.Cap cap) {
            Paint.Cap defValue = cap;
            switch (id) {
                case 0:
                    return Paint.Cap.BUTT;
                case 1:
                    return Paint.Cap.ROUND;
                case 2:
                    return Paint.Cap.SQUARE;
                default:
                    return defValue;
            }
        }

        private Paint.Join getStrokeLineJoin(int id, Paint.Join join) {
            Paint.Join defValue = join;
            switch (id) {
                case 0:
                    return Paint.Join.MITER;
                case 1:
                    return Paint.Join.ROUND;
                case 2:
                    return Paint.Join.BEVEL;
                default:
                    return defValue;
            }
        }

        public boolean canApplyTheme() {
            return this.mThemeAttrs != null;
        }

        public void inflate(Resources r, AttributeSet attrs, Resources.Theme theme, XmlPullParser parser) {
            Resources.Theme theme2 = theme;
            TypedArray a = TypedArrayUtils.obtainAttributes(r, theme2, attrs, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_PATH);
            updateStateFromTypedArray(a, parser, theme2);
            a.recycle();
        }

        private void updateStateFromTypedArray(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) {
            TypedArray a = typedArray;
            XmlPullParser parser = xmlPullParser;
            Resources.Theme theme2 = theme;
            this.mThemeAttrs = null;
            if (TypedArrayUtils.hasAttribute(parser, "pathData")) {
                String pathName = a.getString(0);
                if (pathName != null) {
                    this.mPathName = pathName;
                }
                String pathData = a.getString(2);
                if (pathData != null) {
                    this.mNodes = PathParser.createNodesFromPathData(pathData);
                }
                this.mFillColor = TypedArrayUtils.getNamedComplexColor(a, parser, theme2, "fillColor", 1, 0);
                this.mFillAlpha = TypedArrayUtils.getNamedFloat(a, parser, "fillAlpha", 12, this.mFillAlpha);
                this.mStrokeLineCap = getStrokeLineCap(TypedArrayUtils.getNamedInt(a, parser, "strokeLineCap", 8, -1), this.mStrokeLineCap);
                this.mStrokeLineJoin = getStrokeLineJoin(TypedArrayUtils.getNamedInt(a, parser, "strokeLineJoin", 9, -1), this.mStrokeLineJoin);
                this.mStrokeMiterlimit = TypedArrayUtils.getNamedFloat(a, parser, "strokeMiterLimit", 10, this.mStrokeMiterlimit);
                this.mStrokeColor = TypedArrayUtils.getNamedComplexColor(a, parser, theme2, "strokeColor", 3, 0);
                this.mStrokeAlpha = TypedArrayUtils.getNamedFloat(a, parser, "strokeAlpha", 11, this.mStrokeAlpha);
                this.mStrokeWidth = TypedArrayUtils.getNamedFloat(a, parser, "strokeWidth", 4, this.mStrokeWidth);
                this.mTrimPathEnd = TypedArrayUtils.getNamedFloat(a, parser, "trimPathEnd", 6, this.mTrimPathEnd);
                this.mTrimPathOffset = TypedArrayUtils.getNamedFloat(a, parser, "trimPathOffset", 7, this.mTrimPathOffset);
                this.mTrimPathStart = TypedArrayUtils.getNamedFloat(a, parser, "trimPathStart", 5, this.mTrimPathStart);
                this.mFillRule = TypedArrayUtils.getNamedInt(a, parser, "fillType", 13, this.mFillRule);
            }
        }

        public boolean isStateful() {
            return this.mFillColor.isStateful() || this.mStrokeColor.isStateful();
        }

        public boolean onStateChanged(int[] iArr) {
            int[] stateSet = iArr;
            return this.mFillColor.onStateChanged(stateSet) | this.mStrokeColor.onStateChanged(stateSet);
        }

        public void applyTheme(Resources.Theme theme) {
            Resources.Theme theme2 = theme;
            if (this.mThemeAttrs == null) {
            }
        }

        /* access modifiers changed from: package-private */
        @ColorInt
        public int getStrokeColor() {
            return this.mStrokeColor.getColor();
        }

        /* access modifiers changed from: package-private */
        public void setStrokeColor(int strokeColor) {
            this.mStrokeColor.setColor(strokeColor);
        }

        /* access modifiers changed from: package-private */
        public float getStrokeWidth() {
            return this.mStrokeWidth;
        }

        /* access modifiers changed from: package-private */
        public void setStrokeWidth(float strokeWidth) {
            float f = strokeWidth;
            this.mStrokeWidth = f;
        }

        /* access modifiers changed from: package-private */
        public float getStrokeAlpha() {
            return this.mStrokeAlpha;
        }

        /* access modifiers changed from: package-private */
        public void setStrokeAlpha(float strokeAlpha) {
            float f = strokeAlpha;
            this.mStrokeAlpha = f;
        }

        /* access modifiers changed from: package-private */
        @ColorInt
        public int getFillColor() {
            return this.mFillColor.getColor();
        }

        /* access modifiers changed from: package-private */
        public void setFillColor(int fillColor) {
            this.mFillColor.setColor(fillColor);
        }

        /* access modifiers changed from: package-private */
        public float getFillAlpha() {
            return this.mFillAlpha;
        }

        /* access modifiers changed from: package-private */
        public void setFillAlpha(float fillAlpha) {
            float f = fillAlpha;
            this.mFillAlpha = f;
        }

        /* access modifiers changed from: package-private */
        public float getTrimPathStart() {
            return this.mTrimPathStart;
        }

        /* access modifiers changed from: package-private */
        public void setTrimPathStart(float trimPathStart) {
            float f = trimPathStart;
            this.mTrimPathStart = f;
        }

        /* access modifiers changed from: package-private */
        public float getTrimPathEnd() {
            return this.mTrimPathEnd;
        }

        /* access modifiers changed from: package-private */
        public void setTrimPathEnd(float trimPathEnd) {
            float f = trimPathEnd;
            this.mTrimPathEnd = f;
        }

        /* access modifiers changed from: package-private */
        public float getTrimPathOffset() {
            return this.mTrimPathOffset;
        }

        /* access modifiers changed from: package-private */
        public void setTrimPathOffset(float trimPathOffset) {
            float f = trimPathOffset;
            this.mTrimPathOffset = f;
        }
    }
}
