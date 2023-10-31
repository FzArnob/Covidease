package android.support.p003v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.graphics.ColorUtils;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p000v4.util.C1642ArrayMap;
import android.support.p000v4.util.C1647LongSparseArray;
import android.support.p000v4.util.C1648LruCache;
import android.support.p000v4.util.C1651SparseArrayCompat;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.content.res.AppCompatResources;
import android.support.p003v7.graphics.drawable.AnimatedStateListDrawableCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.widget.AppCompatDrawableManager */
public final class AppCompatDrawableManager {
    private static final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY;
    private static final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED;
    private static final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL;
    private static final ColorFilterLruCache COLOR_FILTER_CACHE;
    private static final boolean DEBUG = false;
    private static final PorterDuff.Mode DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
    private static AppCompatDrawableManager INSTANCE = null;
    private static final String PLATFORM_VD_CLAZZ = "android.graphics.drawable.VectorDrawable";
    private static final String SKIP_DRAWABLE_TAG = "appcompat_skip_skip";
    private static final String TAG = "AppCompatDrawableManag";
    private static final int[] TINT_CHECKABLE_BUTTON_LIST;
    private static final int[] TINT_COLOR_CONTROL_NORMAL;
    private static final int[] TINT_COLOR_CONTROL_STATE_LIST;
    private C1642ArrayMap<String, InflateDelegate> mDelegates;
    private final WeakHashMap<Context, C1647LongSparseArray<WeakReference<Drawable.ConstantState>>> mDrawableCaches;
    private boolean mHasCheckedVectorDrawableSetup;
    private C1651SparseArrayCompat<String> mKnownDrawableIdTags;
    private WeakHashMap<Context, C1651SparseArrayCompat<ColorStateList>> mTintLists;
    private TypedValue mTypedValue;

    /* renamed from: android.support.v7.widget.AppCompatDrawableManager$InflateDelegate */
    private interface InflateDelegate {
        Drawable createFromXmlInner(@NonNull Context context, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme);
    }

    public AppCompatDrawableManager() {
        WeakHashMap<Context, C1647LongSparseArray<WeakReference<Drawable.ConstantState>>> weakHashMap;
        new WeakHashMap<>(0);
        this.mDrawableCaches = weakHashMap;
    }

    static {
        ColorFilterLruCache colorFilterLruCache;
        new ColorFilterLruCache(6);
        COLOR_FILTER_CACHE = colorFilterLruCache;
        int[] iArr = new int[3];
        iArr[0] = C0425R.C0426drawable.abc_textfield_search_default_mtrl_alpha;
        int[] iArr2 = iArr;
        iArr2[1] = C0425R.C0426drawable.abc_textfield_default_mtrl_alpha;
        int[] iArr3 = iArr2;
        iArr3[2] = C0425R.C0426drawable.abc_ab_share_pack_mtrl_alpha;
        COLORFILTER_TINT_COLOR_CONTROL_NORMAL = iArr3;
        int[] iArr4 = new int[7];
        iArr4[0] = C0425R.C0426drawable.abc_ic_commit_search_api_mtrl_alpha;
        int[] iArr5 = iArr4;
        iArr5[1] = C0425R.C0426drawable.abc_seekbar_tick_mark_material;
        int[] iArr6 = iArr5;
        iArr6[2] = C0425R.C0426drawable.abc_ic_menu_share_mtrl_alpha;
        int[] iArr7 = iArr6;
        iArr7[3] = C0425R.C0426drawable.abc_ic_menu_copy_mtrl_am_alpha;
        int[] iArr8 = iArr7;
        iArr8[4] = C0425R.C0426drawable.abc_ic_menu_cut_mtrl_alpha;
        int[] iArr9 = iArr8;
        iArr9[5] = C0425R.C0426drawable.abc_ic_menu_selectall_mtrl_alpha;
        int[] iArr10 = iArr9;
        iArr10[6] = C0425R.C0426drawable.abc_ic_menu_paste_mtrl_am_alpha;
        TINT_COLOR_CONTROL_NORMAL = iArr10;
        int[] iArr11 = new int[10];
        iArr11[0] = C0425R.C0426drawable.abc_textfield_activated_mtrl_alpha;
        int[] iArr12 = iArr11;
        iArr12[1] = C0425R.C0426drawable.abc_textfield_search_activated_mtrl_alpha;
        int[] iArr13 = iArr12;
        iArr13[2] = C0425R.C0426drawable.abc_cab_background_top_mtrl_alpha;
        int[] iArr14 = iArr13;
        iArr14[3] = C0425R.C0426drawable.abc_text_cursor_material;
        int[] iArr15 = iArr14;
        iArr15[4] = C0425R.C0426drawable.abc_text_select_handle_left_mtrl_dark;
        int[] iArr16 = iArr15;
        iArr16[5] = C0425R.C0426drawable.abc_text_select_handle_middle_mtrl_dark;
        int[] iArr17 = iArr16;
        iArr17[6] = C0425R.C0426drawable.abc_text_select_handle_right_mtrl_dark;
        int[] iArr18 = iArr17;
        iArr18[7] = C0425R.C0426drawable.abc_text_select_handle_left_mtrl_light;
        int[] iArr19 = iArr18;
        iArr19[8] = C0425R.C0426drawable.abc_text_select_handle_middle_mtrl_light;
        int[] iArr20 = iArr19;
        iArr20[9] = C0425R.C0426drawable.abc_text_select_handle_right_mtrl_light;
        COLORFILTER_COLOR_CONTROL_ACTIVATED = iArr20;
        int[] iArr21 = new int[3];
        iArr21[0] = C0425R.C0426drawable.abc_popup_background_mtrl_mult;
        int[] iArr22 = iArr21;
        iArr22[1] = C0425R.C0426drawable.abc_cab_background_internal_bg;
        int[] iArr23 = iArr22;
        iArr23[2] = C0425R.C0426drawable.abc_menu_hardkey_panel_mtrl_mult;
        COLORFILTER_COLOR_BACKGROUND_MULTIPLY = iArr23;
        int[] iArr24 = new int[2];
        iArr24[0] = C0425R.C0426drawable.abc_tab_indicator_material;
        int[] iArr25 = iArr24;
        iArr25[1] = C0425R.C0426drawable.abc_textfield_search_material;
        TINT_COLOR_CONTROL_STATE_LIST = iArr25;
        int[] iArr26 = new int[2];
        iArr26[0] = C0425R.C0426drawable.abc_btn_check_material;
        int[] iArr27 = iArr26;
        iArr27[1] = C0425R.C0426drawable.abc_btn_radio_material;
        TINT_CHECKABLE_BUTTON_LIST = iArr27;
    }

    public static synchronized AppCompatDrawableManager get() {
        AppCompatDrawableManager appCompatDrawableManager;
        AppCompatDrawableManager appCompatDrawableManager2;
        synchronized (AppCompatDrawableManager.class) {
            if (INSTANCE == null) {
                new AppCompatDrawableManager();
                INSTANCE = appCompatDrawableManager2;
                installDefaultInflateDelegates(INSTANCE);
            }
            appCompatDrawableManager = INSTANCE;
        }
        return appCompatDrawableManager;
    }

    private static void installDefaultInflateDelegates(@NonNull AppCompatDrawableManager appCompatDrawableManager) {
        InflateDelegate inflateDelegate;
        InflateDelegate inflateDelegate2;
        InflateDelegate inflateDelegate3;
        AppCompatDrawableManager manager = appCompatDrawableManager;
        if (Build.VERSION.SDK_INT < 24) {
            new VdcInflateDelegate();
            manager.addDelegate("vector", inflateDelegate);
            new AvdcInflateDelegate();
            manager.addDelegate("animated-vector", inflateDelegate2);
            new AsldcInflateDelegate();
            manager.addDelegate("animated-selector", inflateDelegate3);
        }
    }

    public synchronized Drawable getDrawable(@NonNull Context context, @DrawableRes int i) {
        Drawable drawable;
        Context context2 = context;
        int resId = i;
        synchronized (this) {
            drawable = getDrawable(context2, resId, false);
        }
        return drawable;
    }

    /* access modifiers changed from: package-private */
    public synchronized Drawable getDrawable(@NonNull Context context, @DrawableRes int i, boolean z) {
        Drawable drawable;
        Context context2 = context;
        int resId = i;
        boolean failIfNotKnown = z;
        synchronized (this) {
            checkVectorDrawableSetup(context2);
            Drawable drawable2 = loadDrawableFromDelegates(context2, resId);
            if (drawable2 == null) {
                drawable2 = createDrawableIfNeeded(context2, resId);
            }
            if (drawable2 == null) {
                drawable2 = ContextCompat.getDrawable(context2, resId);
            }
            if (drawable2 != null) {
                drawable2 = tintDrawable(context2, resId, failIfNotKnown, drawable2);
            }
            if (drawable2 != null) {
                DrawableUtils.fixDrawable(drawable2);
            }
            drawable = drawable2;
        }
        return drawable;
    }

    public synchronized void onConfigurationChanged(@NonNull Context context) {
        Context context2 = context;
        synchronized (this) {
            C1647LongSparseArray<WeakReference<Drawable.ConstantState>> cache = this.mDrawableCaches.get(context2);
            if (cache != null) {
                cache.clear();
            }
        }
    }

    private static long createCacheKey(TypedValue typedValue) {
        TypedValue tv = typedValue;
        return (((long) tv.assetCookie) << 32) | ((long) tv.data);
    }

    private Drawable createDrawableIfNeeded(@NonNull Context context, @DrawableRes int i) {
        Drawable drawable;
        TypedValue typedValue;
        Context context2 = context;
        int resId = i;
        if (this.mTypedValue == null) {
            new TypedValue();
            this.mTypedValue = typedValue;
        }
        TypedValue tv = this.mTypedValue;
        context2.getResources().getValue(resId, tv, true);
        long key = createCacheKey(tv);
        Drawable dr = getCachedDrawable(context2, key);
        if (dr != null) {
            return dr;
        }
        if (resId == C0425R.C0426drawable.abc_cab_background_top_material) {
            Drawable drawable2 = drawable;
            Drawable[] drawableArr = new Drawable[2];
            drawableArr[0] = getDrawable(context2, C0425R.C0426drawable.abc_cab_background_internal_bg);
            Drawable[] drawableArr2 = drawableArr;
            drawableArr2[1] = getDrawable(context2, C0425R.C0426drawable.abc_cab_background_top_mtrl_alpha);
            new LayerDrawable(drawableArr2);
            dr = drawable2;
        }
        if (dr != null) {
            dr.setChangingConfigurations(tv.changingConfigurations);
            boolean addDrawableToCache = addDrawableToCache(context2, key, dr);
        }
        return dr;
    }

    private Drawable tintDrawable(@NonNull Context context, @DrawableRes int i, boolean z, @NonNull Drawable drawable) {
        Context context2 = context;
        int resId = i;
        boolean failIfNotKnown = z;
        Drawable drawable2 = drawable;
        ColorStateList tintList = getTintList(context2, resId);
        if (tintList != null) {
            if (DrawableUtils.canSafelyMutateDrawable(drawable2)) {
                drawable2 = drawable2.mutate();
            }
            drawable2 = DrawableCompat.wrap(drawable2);
            DrawableCompat.setTintList(drawable2, tintList);
            PorterDuff.Mode tintMode = getTintMode(resId);
            if (tintMode != null) {
                DrawableCompat.setTintMode(drawable2, tintMode);
            }
        } else if (resId == C0425R.C0426drawable.abc_seekbar_track_material) {
            LayerDrawable ld = (LayerDrawable) drawable2;
            setPorterDuffColorFilter(ld.findDrawableByLayerId(16908288), ThemeUtils.getThemeAttrColor(context2, C0425R.attr.colorControlNormal), DEFAULT_MODE);
            setPorterDuffColorFilter(ld.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(context2, C0425R.attr.colorControlNormal), DEFAULT_MODE);
            setPorterDuffColorFilter(ld.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(context2, C0425R.attr.colorControlActivated), DEFAULT_MODE);
        } else if (resId == C0425R.C0426drawable.abc_ratingbar_material || resId == C0425R.C0426drawable.abc_ratingbar_indicator_material || resId == C0425R.C0426drawable.abc_ratingbar_small_material) {
            LayerDrawable ld2 = (LayerDrawable) drawable2;
            setPorterDuffColorFilter(ld2.findDrawableByLayerId(16908288), ThemeUtils.getDisabledThemeAttrColor(context2, C0425R.attr.colorControlNormal), DEFAULT_MODE);
            setPorterDuffColorFilter(ld2.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(context2, C0425R.attr.colorControlActivated), DEFAULT_MODE);
            setPorterDuffColorFilter(ld2.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(context2, C0425R.attr.colorControlActivated), DEFAULT_MODE);
        } else if (!tintDrawableUsingColorFilter(context2, resId, drawable2) && failIfNotKnown) {
            drawable2 = null;
        }
        return drawable2;
    }

    private Drawable loadDrawableFromDelegates(@NonNull Context context, @DrawableRes int i) {
        C1651SparseArrayCompat<String> sparseArrayCompat;
        int type;
        Throwable th;
        TypedValue typedValue;
        Context context2 = context;
        int resId = i;
        if (this.mDelegates == null || this.mDelegates.isEmpty()) {
            return null;
        }
        if (this.mKnownDrawableIdTags != null) {
            String cachedTagName = this.mKnownDrawableIdTags.get(resId);
            if (SKIP_DRAWABLE_TAG.equals(cachedTagName) || (cachedTagName != null && this.mDelegates.get(cachedTagName) == null)) {
                return null;
            }
        } else {
            new C1651SparseArrayCompat<>();
            this.mKnownDrawableIdTags = sparseArrayCompat;
        }
        if (this.mTypedValue == null) {
            new TypedValue();
            this.mTypedValue = typedValue;
        }
        TypedValue tv = this.mTypedValue;
        Resources res = context2.getResources();
        res.getValue(resId, tv, true);
        long key = createCacheKey(tv);
        Drawable dr = getCachedDrawable(context2, key);
        if (dr != null) {
            return dr;
        }
        if (tv.string != null && tv.string.toString().endsWith(".xml")) {
            try {
                XmlPullParser parser = res.getXml(resId);
                AttributeSet attrs = Xml.asAttributeSet(parser);
                while (true) {
                    int next = parser.next();
                    type = next;
                    if (next == 2 || type == 1) {
                    }
                }
                if (type != 2) {
                    Throwable th2 = th;
                    new XmlPullParserException("No start tag found");
                    throw th2;
                }
                String tagName = parser.getName();
                this.mKnownDrawableIdTags.append(resId, tagName);
                InflateDelegate delegate = this.mDelegates.get(tagName);
                if (delegate != null) {
                    dr = delegate.createFromXmlInner(context2, parser, attrs, context2.getTheme());
                }
                if (dr != null) {
                    dr.setChangingConfigurations(tv.changingConfigurations);
                    if (addDrawableToCache(context2, key, dr)) {
                    }
                }
            } catch (Exception e) {
                int e2 = Log.e(TAG, "Exception while inflating drawable", e);
            }
        }
        if (dr == null) {
            this.mKnownDrawableIdTags.append(resId, SKIP_DRAWABLE_TAG);
        }
        return dr;
    }

    private synchronized Drawable getCachedDrawable(@NonNull Context context, long j) {
        Drawable drawable;
        Context context2 = context;
        long key = j;
        synchronized (this) {
            C1647LongSparseArray<WeakReference<Drawable.ConstantState>> cache = this.mDrawableCaches.get(context2);
            if (cache == null) {
                drawable = null;
            } else {
                WeakReference<Drawable.ConstantState> wr = cache.get(key);
                if (wr != null) {
                    Drawable.ConstantState entry = (Drawable.ConstantState) wr.get();
                    if (entry != null) {
                        drawable = entry.newDrawable(context2.getResources());
                    } else {
                        cache.delete(key);
                    }
                }
                drawable = null;
            }
        }
        return drawable;
    }

    private synchronized boolean addDrawableToCache(@NonNull Context context, long j, @NonNull Drawable drawable) {
        boolean z;
        Object obj;
        C1647LongSparseArray longSparseArray;
        Context context2 = context;
        long key = j;
        Drawable drawable2 = drawable;
        synchronized (this) {
            Drawable.ConstantState cs = drawable2.getConstantState();
            if (cs != null) {
                C1647LongSparseArray longSparseArray2 = this.mDrawableCaches.get(context2);
                if (longSparseArray2 == null) {
                    new C1647LongSparseArray();
                    longSparseArray2 = longSparseArray;
                    C1647LongSparseArray<WeakReference<Drawable.ConstantState>> put = this.mDrawableCaches.put(context2, longSparseArray2);
                }
                new WeakReference(cs);
                longSparseArray2.put(key, obj);
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public synchronized Drawable onDrawableLoadedFromResources(@NonNull Context context, @NonNull VectorEnabledTintResources vectorEnabledTintResources, @DrawableRes int i) {
        Drawable drawable;
        Context context2 = context;
        VectorEnabledTintResources resources = vectorEnabledTintResources;
        int resId = i;
        synchronized (this) {
            Drawable drawable2 = loadDrawableFromDelegates(context2, resId);
            if (drawable2 == null) {
                drawable2 = resources.superGetDrawable(resId);
            }
            if (drawable2 != null) {
                drawable = tintDrawable(context2, resId, false, drawable2);
            } else {
                drawable = null;
            }
        }
        return drawable;
    }

    static boolean tintDrawableUsingColorFilter(@NonNull Context context, @DrawableRes int i, @NonNull Drawable drawable) {
        Context context2 = context;
        int resId = i;
        Drawable drawable2 = drawable;
        PorterDuff.Mode tintMode = DEFAULT_MODE;
        boolean colorAttrSet = false;
        int colorAttr = 0;
        int alpha = -1;
        if (arrayContains(COLORFILTER_TINT_COLOR_CONTROL_NORMAL, resId)) {
            colorAttr = C0425R.attr.colorControlNormal;
            colorAttrSet = true;
        } else if (arrayContains(COLORFILTER_COLOR_CONTROL_ACTIVATED, resId)) {
            colorAttr = C0425R.attr.colorControlActivated;
            colorAttrSet = true;
        } else if (arrayContains(COLORFILTER_COLOR_BACKGROUND_MULTIPLY, resId)) {
            colorAttr = 16842801;
            colorAttrSet = true;
            tintMode = PorterDuff.Mode.MULTIPLY;
        } else if (resId == C0425R.C0426drawable.abc_list_divider_mtrl_alpha) {
            colorAttr = 16842800;
            colorAttrSet = true;
            alpha = Math.round(40.8f);
        } else if (resId == C0425R.C0426drawable.abc_dialog_material_background) {
            colorAttr = 16842801;
            colorAttrSet = true;
        }
        if (!colorAttrSet) {
            return false;
        }
        if (DrawableUtils.canSafelyMutateDrawable(drawable2)) {
            drawable2 = drawable2.mutate();
        }
        drawable2.setColorFilter(getPorterDuffColorFilter(ThemeUtils.getThemeAttrColor(context2, colorAttr), tintMode));
        if (alpha != -1) {
            drawable2.setAlpha(alpha);
        }
        return true;
    }

    private void addDelegate(@NonNull String str, @NonNull InflateDelegate inflateDelegate) {
        C1642ArrayMap<String, InflateDelegate> arrayMap;
        String tagName = str;
        InflateDelegate delegate = inflateDelegate;
        if (this.mDelegates == null) {
            new C1642ArrayMap<>();
            this.mDelegates = arrayMap;
        }
        InflateDelegate put = this.mDelegates.put(tagName, delegate);
    }

    private void removeDelegate(@NonNull String str, @NonNull InflateDelegate inflateDelegate) {
        String tagName = str;
        InflateDelegate delegate = inflateDelegate;
        if (this.mDelegates != null && this.mDelegates.get(tagName) == delegate) {
            InflateDelegate remove = this.mDelegates.remove(tagName);
        }
    }

    private static boolean arrayContains(int[] array, int i) {
        int value = i;
        int[] iArr = array;
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (iArr[i2] == value) {
                return true;
            }
        }
        return false;
    }

    static PorterDuff.Mode getTintMode(int resId) {
        PorterDuff.Mode mode = null;
        if (resId == C0425R.C0426drawable.abc_switch_thumb_material) {
            mode = PorterDuff.Mode.MULTIPLY;
        }
        return mode;
    }

    /* access modifiers changed from: package-private */
    public synchronized ColorStateList getTintList(@NonNull Context context, @DrawableRes int i) {
        ColorStateList colorStateList;
        Context context2 = context;
        int resId = i;
        synchronized (this) {
            ColorStateList tint = getTintListFromCache(context2, resId);
            if (tint == null) {
                if (resId == C0425R.C0426drawable.abc_edit_text_material) {
                    tint = AppCompatResources.getColorStateList(context2, C0425R.color.abc_tint_edittext);
                } else if (resId == C0425R.C0426drawable.abc_switch_track_mtrl_alpha) {
                    tint = AppCompatResources.getColorStateList(context2, C0425R.color.abc_tint_switch_track);
                } else if (resId == C0425R.C0426drawable.abc_switch_thumb_material) {
                    tint = createSwitchThumbColorStateList(context2);
                } else if (resId == C0425R.C0426drawable.abc_btn_default_mtrl_shape) {
                    tint = createDefaultButtonColorStateList(context2);
                } else if (resId == C0425R.C0426drawable.abc_btn_borderless_material) {
                    tint = createBorderlessButtonColorStateList(context2);
                } else if (resId == C0425R.C0426drawable.abc_btn_colored_material) {
                    tint = createColoredButtonColorStateList(context2);
                } else if (resId == C0425R.C0426drawable.abc_spinner_mtrl_am_alpha || resId == C0425R.C0426drawable.abc_spinner_textfield_background_material) {
                    tint = AppCompatResources.getColorStateList(context2, C0425R.color.abc_tint_spinner);
                } else if (arrayContains(TINT_COLOR_CONTROL_NORMAL, resId)) {
                    tint = ThemeUtils.getThemeAttrColorStateList(context2, C0425R.attr.colorControlNormal);
                } else if (arrayContains(TINT_COLOR_CONTROL_STATE_LIST, resId)) {
                    tint = AppCompatResources.getColorStateList(context2, C0425R.color.abc_tint_default);
                } else if (arrayContains(TINT_CHECKABLE_BUTTON_LIST, resId)) {
                    tint = AppCompatResources.getColorStateList(context2, C0425R.color.abc_tint_btn_checkable);
                } else if (resId == C0425R.C0426drawable.abc_seekbar_thumb_material) {
                    tint = AppCompatResources.getColorStateList(context2, C0425R.color.abc_tint_seek_thumb);
                }
                if (tint != null) {
                    addTintListToCache(context2, resId, tint);
                }
            }
            colorStateList = tint;
        }
        return colorStateList;
    }

    private ColorStateList getTintListFromCache(@NonNull Context context, @DrawableRes int i) {
        Context context2 = context;
        int resId = i;
        if (this.mTintLists == null) {
            return null;
        }
        C1651SparseArrayCompat<ColorStateList> tints = this.mTintLists.get(context2);
        return tints != null ? tints.get(resId) : null;
    }

    private void addTintListToCache(@NonNull Context context, @DrawableRes int i, @NonNull ColorStateList colorStateList) {
        C1651SparseArrayCompat sparseArrayCompat;
        WeakHashMap<Context, C1651SparseArrayCompat<ColorStateList>> weakHashMap;
        Context context2 = context;
        int resId = i;
        ColorStateList tintList = colorStateList;
        if (this.mTintLists == null) {
            new WeakHashMap<>();
            this.mTintLists = weakHashMap;
        }
        C1651SparseArrayCompat sparseArrayCompat2 = this.mTintLists.get(context2);
        if (sparseArrayCompat2 == null) {
            new C1651SparseArrayCompat();
            sparseArrayCompat2 = sparseArrayCompat;
            C1651SparseArrayCompat<ColorStateList> put = this.mTintLists.put(context2, sparseArrayCompat2);
        }
        sparseArrayCompat2.append(resId, tintList);
    }

    private ColorStateList createDefaultButtonColorStateList(@NonNull Context context) {
        Context context2 = context;
        return createButtonColorStateList(context2, ThemeUtils.getThemeAttrColor(context2, C0425R.attr.colorButtonNormal));
    }

    private ColorStateList createBorderlessButtonColorStateList(@NonNull Context context) {
        return createButtonColorStateList(context, 0);
    }

    private ColorStateList createColoredButtonColorStateList(@NonNull Context context) {
        Context context2 = context;
        return createButtonColorStateList(context2, ThemeUtils.getThemeAttrColor(context2, C0425R.attr.colorAccent));
    }

    private ColorStateList createButtonColorStateList(@NonNull Context context, @ColorInt int i) {
        ColorStateList colorStateList;
        Context context2 = context;
        int baseColor = i;
        int[][] states = new int[4][];
        int[] colors = new int[4];
        int colorControlHighlight = ThemeUtils.getThemeAttrColor(context2, C0425R.attr.colorControlHighlight);
        int disabledColor = ThemeUtils.getDisabledThemeAttrColor(context2, C0425R.attr.colorButtonNormal);
        states[0] = ThemeUtils.DISABLED_STATE_SET;
        colors[0] = disabledColor;
        int i2 = 0 + 1;
        states[i2] = ThemeUtils.PRESSED_STATE_SET;
        colors[i2] = ColorUtils.compositeColors(colorControlHighlight, baseColor);
        int i3 = i2 + 1;
        states[i3] = ThemeUtils.FOCUSED_STATE_SET;
        colors[i3] = ColorUtils.compositeColors(colorControlHighlight, baseColor);
        int i4 = i3 + 1;
        states[i4] = ThemeUtils.EMPTY_STATE_SET;
        colors[i4] = baseColor;
        int i5 = i4 + 1;
        new ColorStateList(states, colors);
        return colorStateList;
    }

    private ColorStateList createSwitchThumbColorStateList(Context context) {
        ColorStateList colorStateList;
        Context context2 = context;
        int[][] states = new int[3][];
        int[] colors = new int[3];
        ColorStateList thumbColor = ThemeUtils.getThemeAttrColorStateList(context2, C0425R.attr.colorSwitchThumbNormal);
        if (thumbColor == null || !thumbColor.isStateful()) {
            states[0] = ThemeUtils.DISABLED_STATE_SET;
            colors[0] = ThemeUtils.getDisabledThemeAttrColor(context2, C0425R.attr.colorSwitchThumbNormal);
            int i = 0 + 1;
            states[i] = ThemeUtils.CHECKED_STATE_SET;
            colors[i] = ThemeUtils.getThemeAttrColor(context2, C0425R.attr.colorControlActivated);
            int i2 = i + 1;
            states[i2] = ThemeUtils.EMPTY_STATE_SET;
            colors[i2] = ThemeUtils.getThemeAttrColor(context2, C0425R.attr.colorSwitchThumbNormal);
            int i3 = i2 + 1;
        } else {
            states[0] = ThemeUtils.DISABLED_STATE_SET;
            colors[0] = thumbColor.getColorForState(states[0], 0);
            int i4 = 0 + 1;
            states[i4] = ThemeUtils.CHECKED_STATE_SET;
            colors[i4] = ThemeUtils.getThemeAttrColor(context2, C0425R.attr.colorControlActivated);
            int i5 = i4 + 1;
            states[i5] = ThemeUtils.EMPTY_STATE_SET;
            colors[i5] = thumbColor.getDefaultColor();
            int i6 = i5 + 1;
        }
        new ColorStateList(states, colors);
        return colorStateList;
    }

    /* renamed from: android.support.v7.widget.AppCompatDrawableManager$ColorFilterLruCache */
    private static class ColorFilterLruCache extends C1648LruCache<Integer, PorterDuffColorFilter> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ColorFilterLruCache(int maxSize) {
            super(maxSize);
        }

        /* access modifiers changed from: package-private */
        public PorterDuffColorFilter get(int color, PorterDuff.Mode mode) {
            return (PorterDuffColorFilter) get(Integer.valueOf(generateCacheKey(color, mode)));
        }

        /* access modifiers changed from: package-private */
        public PorterDuffColorFilter put(int color, PorterDuff.Mode mode, PorterDuffColorFilter filter) {
            return (PorterDuffColorFilter) put(Integer.valueOf(generateCacheKey(color, mode)), filter);
        }

        private static int generateCacheKey(int color, PorterDuff.Mode mode) {
            return (31 * ((31 * 1) + color)) + mode.hashCode();
        }
    }

    static void tintDrawable(Drawable drawable, TintInfo tintInfo, int[] iArr) {
        Drawable drawable2 = drawable;
        TintInfo tint = tintInfo;
        int[] state = iArr;
        if (!DrawableUtils.canSafelyMutateDrawable(drawable2) || drawable2.mutate() == drawable2) {
            if (tint.mHasTintList || tint.mHasTintMode) {
                drawable2.setColorFilter(createTintFilter(tint.mHasTintList ? tint.mTintList : null, tint.mHasTintMode ? tint.mTintMode : DEFAULT_MODE, state));
            } else {
                drawable2.clearColorFilter();
            }
            if (Build.VERSION.SDK_INT <= 23) {
                drawable2.invalidateSelf();
                return;
            }
            return;
        }
        int d = Log.d(TAG, "Mutated drawable is not the same instance as the input.");
    }

    private static PorterDuffColorFilter createTintFilter(ColorStateList colorStateList, PorterDuff.Mode mode, int[] iArr) {
        ColorStateList tint = colorStateList;
        PorterDuff.Mode tintMode = mode;
        int[] state = iArr;
        if (tint == null || tintMode == null) {
            return null;
        }
        return getPorterDuffColorFilter(tint.getColorForState(state, 0), tintMode);
    }

    public static synchronized PorterDuffColorFilter getPorterDuffColorFilter(int i, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter;
        PorterDuffColorFilter porterDuffColorFilter2;
        int color = i;
        PorterDuff.Mode mode2 = mode;
        synchronized (AppCompatDrawableManager.class) {
            PorterDuffColorFilter filter = COLOR_FILTER_CACHE.get(color, mode2);
            if (filter == null) {
                new PorterDuffColorFilter(color, mode2);
                filter = porterDuffColorFilter2;
                PorterDuffColorFilter put = COLOR_FILTER_CACHE.put(color, mode2, filter);
            }
            porterDuffColorFilter = filter;
        }
        return porterDuffColorFilter;
    }

    private static void setPorterDuffColorFilter(Drawable drawable, int i, PorterDuff.Mode mode) {
        Drawable d = drawable;
        int color = i;
        PorterDuff.Mode mode2 = mode;
        if (DrawableUtils.canSafelyMutateDrawable(d)) {
            d = d.mutate();
        }
        d.setColorFilter(getPorterDuffColorFilter(color, mode2 == null ? DEFAULT_MODE : mode2));
    }

    private void checkVectorDrawableSetup(@NonNull Context context) {
        Throwable th;
        Context context2 = context;
        if (!this.mHasCheckedVectorDrawableSetup) {
            this.mHasCheckedVectorDrawableSetup = true;
            Drawable d = getDrawable(context2, C0425R.C0426drawable.abc_vector_test);
            if (d == null || !isVectorDrawable(d)) {
                this.mHasCheckedVectorDrawableSetup = false;
                Throwable th2 = th;
                new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
                throw th2;
            }
        }
    }

    private static boolean isVectorDrawable(@NonNull Drawable drawable) {
        Drawable d = drawable;
        return (d instanceof VectorDrawableCompat) || PLATFORM_VD_CLAZZ.equals(d.getClass().getName());
    }

    /* renamed from: android.support.v7.widget.AppCompatDrawableManager$VdcInflateDelegate */
    private static class VdcInflateDelegate implements InflateDelegate {
        VdcInflateDelegate() {
        }

        public Drawable createFromXmlInner(@NonNull Context context, @NonNull XmlPullParser parser, @NonNull AttributeSet attrs, @Nullable Resources.Theme theme) {
            try {
                return VectorDrawableCompat.createFromXmlInner(context.getResources(), parser, attrs, theme);
            } catch (Exception e) {
                int e2 = Log.e("VdcInflateDelegate", "Exception while inflating <vector>", e);
                return null;
            }
        }
    }

    /* renamed from: android.support.v7.widget.AppCompatDrawableManager$AvdcInflateDelegate */
    private static class AvdcInflateDelegate implements InflateDelegate {
        AvdcInflateDelegate() {
        }

        public Drawable createFromXmlInner(@NonNull Context context, @NonNull XmlPullParser parser, @NonNull AttributeSet attrs, @Nullable Resources.Theme theme) {
            Context context2 = context;
            try {
                return AnimatedVectorDrawableCompat.createFromXmlInner(context2, context2.getResources(), parser, attrs, theme);
            } catch (Exception e) {
                int e2 = Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", e);
                return null;
            }
        }
    }

    @RequiresApi(11)
    /* renamed from: android.support.v7.widget.AppCompatDrawableManager$AsldcInflateDelegate */
    static class AsldcInflateDelegate implements InflateDelegate {
        AsldcInflateDelegate() {
        }

        public Drawable createFromXmlInner(@NonNull Context context, @NonNull XmlPullParser parser, @NonNull AttributeSet attrs, @Nullable Resources.Theme theme) {
            Context context2 = context;
            try {
                return AnimatedStateListDrawableCompat.createFromXmlInner(context2, context2.getResources(), parser, attrs, theme);
            } catch (Exception e) {
                int e2 = Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", e);
                return null;
            }
        }
    }
}
