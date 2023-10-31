package android.support.p000v4.view;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.C0015Px;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.UiThread;
import android.support.compat.C0020R;
import android.support.p000v4.util.C1642ArrayMap;
import android.support.p000v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.p000v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.KeyEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeProvider;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: android.support.v4.view.ViewCompat */
public class ViewCompat {
    public static final int ACCESSIBILITY_LIVE_REGION_ASSERTIVE = 2;
    public static final int ACCESSIBILITY_LIVE_REGION_NONE = 0;
    public static final int ACCESSIBILITY_LIVE_REGION_POLITE = 1;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_AUTO = 0;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO = 2;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS = 4;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_YES = 1;
    @Deprecated
    public static final int LAYER_TYPE_HARDWARE = 2;
    @Deprecated
    public static final int LAYER_TYPE_NONE = 0;
    @Deprecated
    public static final int LAYER_TYPE_SOFTWARE = 1;
    public static final int LAYOUT_DIRECTION_INHERIT = 2;
    public static final int LAYOUT_DIRECTION_LOCALE = 3;
    public static final int LAYOUT_DIRECTION_LTR = 0;
    public static final int LAYOUT_DIRECTION_RTL = 1;
    @Deprecated
    public static final int MEASURED_HEIGHT_STATE_SHIFT = 16;
    @Deprecated
    public static final int MEASURED_SIZE_MASK = 16777215;
    @Deprecated
    public static final int MEASURED_STATE_MASK = -16777216;
    @Deprecated
    public static final int MEASURED_STATE_TOO_SMALL = 16777216;
    @Deprecated
    public static final int OVER_SCROLL_ALWAYS = 0;
    @Deprecated
    public static final int OVER_SCROLL_IF_CONTENT_SCROLLS = 1;
    @Deprecated
    public static final int OVER_SCROLL_NEVER = 2;
    public static final int SCROLL_AXIS_HORIZONTAL = 1;
    public static final int SCROLL_AXIS_NONE = 0;
    public static final int SCROLL_AXIS_VERTICAL = 2;
    public static final int SCROLL_INDICATOR_BOTTOM = 2;
    public static final int SCROLL_INDICATOR_END = 32;
    public static final int SCROLL_INDICATOR_LEFT = 4;
    public static final int SCROLL_INDICATOR_RIGHT = 8;
    public static final int SCROLL_INDICATOR_START = 16;
    public static final int SCROLL_INDICATOR_TOP = 1;
    private static final String TAG = "ViewCompat";
    public static final int TYPE_NON_TOUCH = 1;
    public static final int TYPE_TOUCH = 0;
    private static boolean sAccessibilityDelegateCheckFailed = false;
    private static Field sAccessibilityDelegateField;
    private static Method sChildrenDrawingOrderMethod;
    private static Method sDispatchFinishTemporaryDetach;
    private static Method sDispatchStartTemporaryDetach;
    private static Field sMinHeightField;
    private static boolean sMinHeightFieldFetched;
    private static Field sMinWidthField;
    private static boolean sMinWidthFieldFetched;
    private static final AtomicInteger sNextGeneratedId;
    private static boolean sTempDetachBound;
    private static ThreadLocal<Rect> sThreadLocalRect;
    private static WeakHashMap<View, String> sTransitionNameMap;
    private static WeakHashMap<View, ViewPropertyAnimatorCompat> sViewPropertyAnimatorMap = null;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.view.ViewCompat$FocusDirection */
    public @interface FocusDirection {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.view.ViewCompat$FocusRealDirection */
    public @interface FocusRealDirection {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.view.ViewCompat$FocusRelativeDirection */
    public @interface FocusRelativeDirection {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.view.ViewCompat$NestedScrollType */
    public @interface NestedScrollType {
    }

    /* renamed from: android.support.v4.view.ViewCompat$OnUnhandledKeyEventListenerCompat */
    public interface OnUnhandledKeyEventListenerCompat {
        boolean onUnhandledKeyEvent(View view, KeyEvent keyEvent);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.view.ViewCompat$ScrollAxis */
    public @interface ScrollAxis {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.view.ViewCompat$ScrollIndicators */
    public @interface ScrollIndicators {
    }

    static {
        AtomicInteger atomicInteger;
        new AtomicInteger(1);
        sNextGeneratedId = atomicInteger;
    }

    private static Rect getEmptyTempRect() {
        Rect rect;
        ThreadLocal<Rect> threadLocal;
        if (sThreadLocalRect == null) {
            new ThreadLocal<>();
            sThreadLocalRect = threadLocal;
        }
        Rect rect2 = sThreadLocalRect.get();
        if (rect2 == null) {
            new Rect();
            rect2 = rect;
            sThreadLocalRect.set(rect2);
        }
        rect2.setEmpty();
        return rect2;
    }

    @Deprecated
    public static boolean canScrollHorizontally(View view, int direction) {
        return view.canScrollHorizontally(direction);
    }

    @Deprecated
    public static boolean canScrollVertically(View view, int direction) {
        return view.canScrollVertically(direction);
    }

    @Deprecated
    public static int getOverScrollMode(View v) {
        return v.getOverScrollMode();
    }

    @Deprecated
    public static void setOverScrollMode(View v, int overScrollMode) {
        v.setOverScrollMode(overScrollMode);
    }

    @Deprecated
    public static void onPopulateAccessibilityEvent(View v, AccessibilityEvent event) {
        v.onPopulateAccessibilityEvent(event);
    }

    @Deprecated
    public static void onInitializeAccessibilityEvent(View v, AccessibilityEvent event) {
        v.onInitializeAccessibilityEvent(event);
    }

    public static void onInitializeAccessibilityNodeInfo(@NonNull View v, AccessibilityNodeInfoCompat info) {
        v.onInitializeAccessibilityNodeInfo(info.unwrap());
    }

    public static void setAccessibilityDelegate(@NonNull View v, AccessibilityDelegateCompat accessibilityDelegateCompat) {
        AccessibilityDelegateCompat delegate = accessibilityDelegateCompat;
        v.setAccessibilityDelegate(delegate == null ? null : delegate.getBridge());
    }

    public static void setAutofillHints(@NonNull View view, @Nullable String... strArr) {
        View v = view;
        String[] autofillHints = strArr;
        if (Build.VERSION.SDK_INT >= 26) {
            v.setAutofillHints(autofillHints);
        }
    }

    @SuppressLint({"InlinedApi"})
    public static int getImportantForAutofill(@NonNull View view) {
        View v = view;
        if (Build.VERSION.SDK_INT >= 26) {
            return v.getImportantForAutofill();
        }
        return 0;
    }

    public static void setImportantForAutofill(@NonNull View view, int i) {
        View v = view;
        int mode = i;
        if (Build.VERSION.SDK_INT >= 26) {
            v.setImportantForAutofill(mode);
        }
    }

    public static boolean isImportantForAutofill(@NonNull View view) {
        View v = view;
        if (Build.VERSION.SDK_INT >= 26) {
            return v.isImportantForAutofill();
        }
        return true;
    }

    public static boolean hasAccessibilityDelegate(@NonNull View view) {
        boolean z;
        View v = view;
        if (sAccessibilityDelegateCheckFailed) {
            return false;
        }
        if (sAccessibilityDelegateField == null) {
            try {
                sAccessibilityDelegateField = View.class.getDeclaredField("mAccessibilityDelegate");
                sAccessibilityDelegateField.setAccessible(true);
            } catch (Throwable th) {
                Throwable th2 = th;
                sAccessibilityDelegateCheckFailed = true;
                return false;
            }
        }
        try {
            if (sAccessibilityDelegateField.get(v) != null) {
                z = true;
            } else {
                z = false;
            }
            return z;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            sAccessibilityDelegateCheckFailed = true;
            return false;
        }
    }

    public static boolean hasTransientState(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 16) {
            return view2.hasTransientState();
        }
        return false;
    }

    public static void setHasTransientState(@NonNull View view, boolean z) {
        View view2 = view;
        boolean hasTransientState = z;
        if (Build.VERSION.SDK_INT >= 16) {
            view2.setHasTransientState(hasTransientState);
        }
    }

    public static void postInvalidateOnAnimation(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 16) {
            view2.postInvalidateOnAnimation();
        } else {
            view2.postInvalidate();
        }
    }

    public static void postInvalidateOnAnimation(@NonNull View view, int i, int i2, int i3, int i4) {
        View view2 = view;
        int left = i;
        int top = i2;
        int right = i3;
        int bottom = i4;
        if (Build.VERSION.SDK_INT >= 16) {
            view2.postInvalidateOnAnimation(left, top, right, bottom);
        } else {
            view2.postInvalidate(left, top, right, bottom);
        }
    }

    public static void postOnAnimation(@NonNull View view, Runnable runnable) {
        View view2 = view;
        Runnable action = runnable;
        if (Build.VERSION.SDK_INT >= 16) {
            view2.postOnAnimation(action);
        } else {
            boolean postDelayed = view2.postDelayed(action, ValueAnimator.getFrameDelay());
        }
    }

    public static void postOnAnimationDelayed(@NonNull View view, Runnable runnable, long j) {
        View view2 = view;
        Runnable action = runnable;
        long delayMillis = j;
        if (Build.VERSION.SDK_INT >= 16) {
            view2.postOnAnimationDelayed(action, delayMillis);
        } else {
            boolean postDelayed = view2.postDelayed(action, ValueAnimator.getFrameDelay() + delayMillis);
        }
    }

    public static int getImportantForAccessibility(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 16) {
            return view2.getImportantForAccessibility();
        }
        return 0;
    }

    public static void setImportantForAccessibility(@NonNull View view, int i) {
        View view2 = view;
        int mode = i;
        if (Build.VERSION.SDK_INT >= 19) {
            view2.setImportantForAccessibility(mode);
        } else if (Build.VERSION.SDK_INT >= 16) {
            if (mode == 4) {
                mode = 2;
            }
            view2.setImportantForAccessibility(mode);
        }
    }

    public static boolean isImportantForAccessibility(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 21) {
            return view2.isImportantForAccessibility();
        }
        return true;
    }

    public static boolean performAccessibilityAction(@NonNull View view, int i, Bundle bundle) {
        View view2 = view;
        int action = i;
        Bundle arguments = bundle;
        if (Build.VERSION.SDK_INT >= 16) {
            return view2.performAccessibilityAction(action, arguments);
        }
        return false;
    }

    public static AccessibilityNodeProviderCompat getAccessibilityNodeProvider(@NonNull View view) {
        AccessibilityNodeProvider provider;
        AccessibilityNodeProviderCompat accessibilityNodeProviderCompat;
        View view2 = view;
        if (Build.VERSION.SDK_INT < 16 || (provider = view2.getAccessibilityNodeProvider()) == null) {
            return null;
        }
        new AccessibilityNodeProviderCompat(provider);
        return accessibilityNodeProviderCompat;
    }

    @Deprecated
    public static float getAlpha(View view) {
        return view.getAlpha();
    }

    @Deprecated
    public static void setLayerType(View view, int layerType, Paint paint) {
        view.setLayerType(layerType, paint);
    }

    @Deprecated
    public static int getLayerType(View view) {
        return view.getLayerType();
    }

    public static int getLabelFor(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 17) {
            return view2.getLabelFor();
        }
        return 0;
    }

    public static void setLabelFor(@NonNull View view, @IdRes int i) {
        View view2 = view;
        int labeledId = i;
        if (Build.VERSION.SDK_INT >= 17) {
            view2.setLabelFor(labeledId);
        }
    }

    public static void setLayerPaint(@NonNull View view, Paint paint) {
        View view2 = view;
        Paint paint2 = paint;
        if (Build.VERSION.SDK_INT >= 17) {
            view2.setLayerPaint(paint2);
            return;
        }
        view2.setLayerType(view2.getLayerType(), paint2);
        view2.invalidate();
    }

    public static int getLayoutDirection(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 17) {
            return view2.getLayoutDirection();
        }
        return 0;
    }

    public static void setLayoutDirection(@NonNull View view, int i) {
        View view2 = view;
        int layoutDirection = i;
        if (Build.VERSION.SDK_INT >= 17) {
            view2.setLayoutDirection(layoutDirection);
        }
    }

    public static ViewParent getParentForAccessibility(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 16) {
            return view2.getParentForAccessibility();
        }
        return view2.getParent();
    }

    @NonNull
    public static <T extends View> T requireViewById(@NonNull View view, @IdRes int i) {
        Throwable th;
        View view2 = view;
        int id = i;
        if (Build.VERSION.SDK_INT >= 28) {
            return view2.requireViewById(id);
        }
        View targetView = view2.findViewById(id);
        if (targetView != null) {
            return targetView;
        }
        Throwable th2 = th;
        new IllegalArgumentException("ID does not reference a View inside this View");
        throw th2;
    }

    @Deprecated
    public static boolean isOpaque(View view) {
        return view.isOpaque();
    }

    @Deprecated
    public static int resolveSizeAndState(int size, int measureSpec, int childMeasuredState) {
        return View.resolveSizeAndState(size, measureSpec, childMeasuredState);
    }

    @Deprecated
    public static int getMeasuredWidthAndState(View view) {
        return view.getMeasuredWidthAndState();
    }

    @Deprecated
    public static int getMeasuredHeightAndState(View view) {
        return view.getMeasuredHeightAndState();
    }

    @Deprecated
    public static int getMeasuredState(View view) {
        return view.getMeasuredState();
    }

    @Deprecated
    public static int combineMeasuredStates(int curState, int newState) {
        return View.combineMeasuredStates(curState, newState);
    }

    public static int getAccessibilityLiveRegion(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 19) {
            return view2.getAccessibilityLiveRegion();
        }
        return 0;
    }

    public static void setAccessibilityLiveRegion(@NonNull View view, int i) {
        View view2 = view;
        int mode = i;
        if (Build.VERSION.SDK_INT >= 19) {
            view2.setAccessibilityLiveRegion(mode);
        }
    }

    @C0015Px
    public static int getPaddingStart(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 17) {
            return view2.getPaddingStart();
        }
        return view2.getPaddingLeft();
    }

    @C0015Px
    public static int getPaddingEnd(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 17) {
            return view2.getPaddingEnd();
        }
        return view2.getPaddingRight();
    }

    public static void setPaddingRelative(@NonNull View view, @C0015Px int i, @C0015Px int i2, @C0015Px int i3, @C0015Px int i4) {
        View view2 = view;
        int start = i;
        int top = i2;
        int end = i3;
        int bottom = i4;
        if (Build.VERSION.SDK_INT >= 17) {
            view2.setPaddingRelative(start, top, end, bottom);
        } else {
            view2.setPadding(start, top, end, bottom);
        }
    }

    private static void bindTempDetach() {
        try {
            sDispatchStartTemporaryDetach = View.class.getDeclaredMethod("dispatchStartTemporaryDetach", new Class[0]);
            sDispatchFinishTemporaryDetach = View.class.getDeclaredMethod("dispatchFinishTemporaryDetach", new Class[0]);
        } catch (NoSuchMethodException e) {
            int e2 = Log.e(TAG, "Couldn't find method", e);
        }
        sTempDetachBound = true;
    }

    public static void dispatchStartTemporaryDetach(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 24) {
            view2.dispatchStartTemporaryDetach();
            return;
        }
        if (!sTempDetachBound) {
            bindTempDetach();
        }
        if (sDispatchStartTemporaryDetach != null) {
            try {
                Object invoke = sDispatchStartTemporaryDetach.invoke(view2, new Object[0]);
            } catch (Exception e) {
                int d = Log.d(TAG, "Error calling dispatchStartTemporaryDetach", e);
            }
        } else {
            view2.onStartTemporaryDetach();
        }
    }

    public static void dispatchFinishTemporaryDetach(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 24) {
            view2.dispatchFinishTemporaryDetach();
            return;
        }
        if (!sTempDetachBound) {
            bindTempDetach();
        }
        if (sDispatchFinishTemporaryDetach != null) {
            try {
                Object invoke = sDispatchFinishTemporaryDetach.invoke(view2, new Object[0]);
            } catch (Exception e) {
                int d = Log.d(TAG, "Error calling dispatchFinishTemporaryDetach", e);
            }
        } else {
            view2.onFinishTemporaryDetach();
        }
    }

    @Deprecated
    public static float getTranslationX(View view) {
        return view.getTranslationX();
    }

    @Deprecated
    public static float getTranslationY(View view) {
        return view.getTranslationY();
    }

    @Nullable
    @Deprecated
    public static Matrix getMatrix(View view) {
        return view.getMatrix();
    }

    public static int getMinimumWidth(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 16) {
            return view2.getMinimumWidth();
        }
        if (!sMinWidthFieldFetched) {
            try {
                sMinWidthField = View.class.getDeclaredField("mMinWidth");
                sMinWidthField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                NoSuchFieldException noSuchFieldException = e;
            }
            sMinWidthFieldFetched = true;
        }
        if (sMinWidthField != null) {
            try {
                return ((Integer) sMinWidthField.get(view2)).intValue();
            } catch (Exception e2) {
                Exception exc = e2;
            }
        }
        return 0;
    }

    public static int getMinimumHeight(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 16) {
            return view2.getMinimumHeight();
        }
        if (!sMinHeightFieldFetched) {
            try {
                sMinHeightField = View.class.getDeclaredField("mMinHeight");
                sMinHeightField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                NoSuchFieldException noSuchFieldException = e;
            }
            sMinHeightFieldFetched = true;
        }
        if (sMinHeightField != null) {
            try {
                return ((Integer) sMinHeightField.get(view2)).intValue();
            } catch (Exception e2) {
                Exception exc = e2;
            }
        }
        return 0;
    }

    @NonNull
    public static ViewPropertyAnimatorCompat animate(@NonNull View view) {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat;
        WeakHashMap<View, ViewPropertyAnimatorCompat> weakHashMap;
        View view2 = view;
        if (sViewPropertyAnimatorMap == null) {
            new WeakHashMap<>();
            sViewPropertyAnimatorMap = weakHashMap;
        }
        ViewPropertyAnimatorCompat vpa = sViewPropertyAnimatorMap.get(view2);
        if (vpa == null) {
            new ViewPropertyAnimatorCompat(view2);
            vpa = viewPropertyAnimatorCompat;
            ViewPropertyAnimatorCompat put = sViewPropertyAnimatorMap.put(view2, vpa);
        }
        return vpa;
    }

    @Deprecated
    public static void setTranslationX(View view, float value) {
        view.setTranslationX(value);
    }

    @Deprecated
    public static void setTranslationY(View view, float value) {
        view.setTranslationY(value);
    }

    @Deprecated
    public static void setAlpha(View view, @FloatRange(from = 0.0d, mo103to = 1.0d) float value) {
        view.setAlpha(value);
    }

    @Deprecated
    public static void setX(View view, float value) {
        view.setX(value);
    }

    @Deprecated
    public static void setY(View view, float value) {
        view.setY(value);
    }

    @Deprecated
    public static void setRotation(View view, float value) {
        view.setRotation(value);
    }

    @Deprecated
    public static void setRotationX(View view, float value) {
        view.setRotationX(value);
    }

    @Deprecated
    public static void setRotationY(View view, float value) {
        view.setRotationY(value);
    }

    @Deprecated
    public static void setScaleX(View view, float value) {
        view.setScaleX(value);
    }

    @Deprecated
    public static void setScaleY(View view, float value) {
        view.setScaleY(value);
    }

    @Deprecated
    public static float getPivotX(View view) {
        return view.getPivotX();
    }

    @Deprecated
    public static void setPivotX(View view, float value) {
        view.setPivotX(value);
    }

    @Deprecated
    public static float getPivotY(View view) {
        return view.getPivotY();
    }

    @Deprecated
    public static void setPivotY(View view, float value) {
        view.setPivotY(value);
    }

    @Deprecated
    public static float getRotation(View view) {
        return view.getRotation();
    }

    @Deprecated
    public static float getRotationX(View view) {
        return view.getRotationX();
    }

    @Deprecated
    public static float getRotationY(View view) {
        return view.getRotationY();
    }

    @Deprecated
    public static float getScaleX(View view) {
        return view.getScaleX();
    }

    @Deprecated
    public static float getScaleY(View view) {
        return view.getScaleY();
    }

    @Deprecated
    public static float getX(View view) {
        return view.getX();
    }

    @Deprecated
    public static float getY(View view) {
        return view.getY();
    }

    public static void setElevation(@NonNull View view, float f) {
        View view2 = view;
        float elevation = f;
        if (Build.VERSION.SDK_INT >= 21) {
            view2.setElevation(elevation);
        }
    }

    public static float getElevation(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 21) {
            return view2.getElevation();
        }
        return 0.0f;
    }

    public static void setTranslationZ(@NonNull View view, float f) {
        View view2 = view;
        float translationZ = f;
        if (Build.VERSION.SDK_INT >= 21) {
            view2.setTranslationZ(translationZ);
        }
    }

    public static float getTranslationZ(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 21) {
            return view2.getTranslationZ();
        }
        return 0.0f;
    }

    public static void setTransitionName(@NonNull View view, String str) {
        WeakHashMap<View, String> weakHashMap;
        View view2 = view;
        String transitionName = str;
        if (Build.VERSION.SDK_INT >= 21) {
            view2.setTransitionName(transitionName);
            return;
        }
        if (sTransitionNameMap == null) {
            new WeakHashMap<>();
            sTransitionNameMap = weakHashMap;
        }
        String put = sTransitionNameMap.put(view2, transitionName);
    }

    @Nullable
    public static String getTransitionName(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 21) {
            return view2.getTransitionName();
        }
        if (sTransitionNameMap == null) {
            return null;
        }
        return sTransitionNameMap.get(view2);
    }

    public static int getWindowSystemUiVisibility(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 16) {
            return view2.getWindowSystemUiVisibility();
        }
        return 0;
    }

    public static void requestApplyInsets(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 20) {
            view2.requestApplyInsets();
        } else if (Build.VERSION.SDK_INT >= 16) {
            view2.requestFitSystemWindows();
        }
    }

    @Deprecated
    public static void setChildrenDrawingOrderEnabled(ViewGroup viewGroup, boolean z) {
        ViewGroup viewGroup2 = viewGroup;
        boolean enabled = z;
        if (sChildrenDrawingOrderMethod == null) {
            Class<ViewGroup> cls = ViewGroup.class;
            try {
                sChildrenDrawingOrderMethod = cls.getDeclaredMethod("setChildrenDrawingOrderEnabled", new Class[]{Boolean.TYPE});
            } catch (NoSuchMethodException e) {
                int e2 = Log.e(TAG, "Unable to find childrenDrawingOrderEnabled", e);
            }
            sChildrenDrawingOrderMethod.setAccessible(true);
        }
        try {
            Object invoke = sChildrenDrawingOrderMethod.invoke(viewGroup2, new Object[]{Boolean.valueOf(enabled)});
        } catch (IllegalAccessException e3) {
            int e4 = Log.e(TAG, "Unable to invoke childrenDrawingOrderEnabled", e3);
        } catch (IllegalArgumentException e5) {
            int e6 = Log.e(TAG, "Unable to invoke childrenDrawingOrderEnabled", e5);
        } catch (InvocationTargetException e7) {
            int e8 = Log.e(TAG, "Unable to invoke childrenDrawingOrderEnabled", e7);
        }
    }

    public static boolean getFitsSystemWindows(@NonNull View view) {
        View v = view;
        if (Build.VERSION.SDK_INT >= 16) {
            return v.getFitsSystemWindows();
        }
        return false;
    }

    @Deprecated
    public static void setFitsSystemWindows(View view, boolean fitSystemWindows) {
        view.setFitsSystemWindows(fitSystemWindows);
    }

    @Deprecated
    public static void jumpDrawablesToCurrentState(View v) {
        v.jumpDrawablesToCurrentState();
    }

    public static void setOnApplyWindowInsetsListener(@NonNull View view, OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        View.OnApplyWindowInsetsListener onApplyWindowInsetsListener2;
        View v = view;
        OnApplyWindowInsetsListener listener = onApplyWindowInsetsListener;
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        if (listener == null) {
            v.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) null);
            return;
        }
        final OnApplyWindowInsetsListener onApplyWindowInsetsListener3 = listener;
        new View.OnApplyWindowInsetsListener() {
            public WindowInsets onApplyWindowInsets(View view, WindowInsets insets) {
                return (WindowInsets) WindowInsetsCompat.unwrap(onApplyWindowInsetsListener3.onApplyWindowInsets(view, WindowInsetsCompat.wrap(insets)));
            }
        };
        v.setOnApplyWindowInsetsListener(onApplyWindowInsetsListener2);
    }

    public static WindowInsetsCompat onApplyWindowInsets(@NonNull View view, WindowInsetsCompat windowInsetsCompat) {
        WindowInsets windowInsets;
        View view2 = view;
        WindowInsetsCompat insets = windowInsetsCompat;
        if (Build.VERSION.SDK_INT < 21) {
            return insets;
        }
        WindowInsets unwrapped = (WindowInsets) WindowInsetsCompat.unwrap(insets);
        WindowInsets result = view2.onApplyWindowInsets(unwrapped);
        if (result != unwrapped) {
            new WindowInsets(result);
            unwrapped = windowInsets;
        }
        return WindowInsetsCompat.wrap(unwrapped);
    }

    public static WindowInsetsCompat dispatchApplyWindowInsets(@NonNull View view, WindowInsetsCompat windowInsetsCompat) {
        WindowInsets windowInsets;
        View view2 = view;
        WindowInsetsCompat insets = windowInsetsCompat;
        if (Build.VERSION.SDK_INT < 21) {
            return insets;
        }
        WindowInsets unwrapped = (WindowInsets) WindowInsetsCompat.unwrap(insets);
        WindowInsets result = view2.dispatchApplyWindowInsets(unwrapped);
        if (result != unwrapped) {
            new WindowInsets(result);
            unwrapped = windowInsets;
        }
        return WindowInsetsCompat.wrap(unwrapped);
    }

    @Deprecated
    public static void setSaveFromParentEnabled(View v, boolean enabled) {
        v.setSaveFromParentEnabled(enabled);
    }

    @Deprecated
    public static void setActivated(View view, boolean activated) {
        view.setActivated(activated);
    }

    public static boolean hasOverlappingRendering(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 16) {
            return view2.hasOverlappingRendering();
        }
        return true;
    }

    public static boolean isPaddingRelative(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 17) {
            return view2.isPaddingRelative();
        }
        return false;
    }

    public static void setBackground(@NonNull View view, @Nullable Drawable drawable) {
        View view2 = view;
        Drawable background = drawable;
        if (Build.VERSION.SDK_INT >= 16) {
            view2.setBackground(background);
        } else {
            view2.setBackgroundDrawable(background);
        }
    }

    public static ColorStateList getBackgroundTintList(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 21) {
            return view2.getBackgroundTintList();
        }
        return view2 instanceof TintableBackgroundView ? ((TintableBackgroundView) view2).getSupportBackgroundTintList() : null;
    }

    public static void setBackgroundTintList(@NonNull View view, ColorStateList colorStateList) {
        View view2 = view;
        ColorStateList tintList = colorStateList;
        if (Build.VERSION.SDK_INT >= 21) {
            view2.setBackgroundTintList(tintList);
            if (Build.VERSION.SDK_INT == 21) {
                Drawable background = view2.getBackground();
                boolean hasTint = (view2.getBackgroundTintList() == null && view2.getBackgroundTintMode() == null) ? false : true;
                if (background != null && hasTint) {
                    if (background.isStateful()) {
                        boolean state = background.setState(view2.getDrawableState());
                    }
                    view2.setBackground(background);
                }
            }
        } else if (view2 instanceof TintableBackgroundView) {
            ((TintableBackgroundView) view2).setSupportBackgroundTintList(tintList);
        }
    }

    public static PorterDuff.Mode getBackgroundTintMode(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 21) {
            return view2.getBackgroundTintMode();
        }
        return view2 instanceof TintableBackgroundView ? ((TintableBackgroundView) view2).getSupportBackgroundTintMode() : null;
    }

    public static void setBackgroundTintMode(@NonNull View view, PorterDuff.Mode mode) {
        View view2 = view;
        PorterDuff.Mode mode2 = mode;
        if (Build.VERSION.SDK_INT >= 21) {
            view2.setBackgroundTintMode(mode2);
            if (Build.VERSION.SDK_INT == 21) {
                Drawable background = view2.getBackground();
                boolean hasTint = (view2.getBackgroundTintList() == null && view2.getBackgroundTintMode() == null) ? false : true;
                if (background != null && hasTint) {
                    if (background.isStateful()) {
                        boolean state = background.setState(view2.getDrawableState());
                    }
                    view2.setBackground(background);
                }
            }
        } else if (view2 instanceof TintableBackgroundView) {
            ((TintableBackgroundView) view2).setSupportBackgroundTintMode(mode2);
        }
    }

    public static void setNestedScrollingEnabled(@NonNull View view, boolean z) {
        View view2 = view;
        boolean enabled = z;
        if (Build.VERSION.SDK_INT >= 21) {
            view2.setNestedScrollingEnabled(enabled);
        } else if (view2 instanceof NestedScrollingChild) {
            ((NestedScrollingChild) view2).setNestedScrollingEnabled(enabled);
        }
    }

    public static boolean isNestedScrollingEnabled(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 21) {
            return view2.isNestedScrollingEnabled();
        }
        if (view2 instanceof NestedScrollingChild) {
            return ((NestedScrollingChild) view2).isNestedScrollingEnabled();
        }
        return false;
    }

    public static boolean startNestedScroll(@NonNull View view, int i) {
        View view2 = view;
        int axes = i;
        if (Build.VERSION.SDK_INT >= 21) {
            return view2.startNestedScroll(axes);
        }
        if (view2 instanceof NestedScrollingChild) {
            return ((NestedScrollingChild) view2).startNestedScroll(axes);
        }
        return false;
    }

    public static void stopNestedScroll(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 21) {
            view2.stopNestedScroll();
        } else if (view2 instanceof NestedScrollingChild) {
            ((NestedScrollingChild) view2).stopNestedScroll();
        }
    }

    public static boolean hasNestedScrollingParent(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 21) {
            return view2.hasNestedScrollingParent();
        }
        if (view2 instanceof NestedScrollingChild) {
            return ((NestedScrollingChild) view2).hasNestedScrollingParent();
        }
        return false;
    }

    public static boolean dispatchNestedScroll(@NonNull View view, int i, int i2, int i3, int i4, @Nullable int[] iArr) {
        View view2 = view;
        int dxConsumed = i;
        int dyConsumed = i2;
        int dxUnconsumed = i3;
        int dyUnconsumed = i4;
        int[] offsetInWindow = iArr;
        if (Build.VERSION.SDK_INT >= 21) {
            return view2.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
        }
        if (view2 instanceof NestedScrollingChild) {
            return ((NestedScrollingChild) view2).dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
        }
        return false;
    }

    public static boolean dispatchNestedPreScroll(@NonNull View view, int i, int i2, @Nullable int[] iArr, @Nullable int[] iArr2) {
        View view2 = view;
        int dx = i;
        int dy = i2;
        int[] consumed = iArr;
        int[] offsetInWindow = iArr2;
        if (Build.VERSION.SDK_INT >= 21) {
            return view2.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
        }
        if (view2 instanceof NestedScrollingChild) {
            return ((NestedScrollingChild) view2).dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
        }
        return false;
    }

    public static boolean startNestedScroll(@NonNull View view, int i, int i2) {
        View view2 = view;
        int axes = i;
        int type = i2;
        if (view2 instanceof NestedScrollingChild2) {
            return ((NestedScrollingChild2) view2).startNestedScroll(axes, type);
        }
        if (type == 0) {
            return startNestedScroll(view2, axes);
        }
        return false;
    }

    public static void stopNestedScroll(@NonNull View view, int i) {
        View view2 = view;
        int type = i;
        if (view2 instanceof NestedScrollingChild2) {
            ((NestedScrollingChild2) view2).stopNestedScroll(type);
        } else if (type == 0) {
            stopNestedScroll(view2);
        }
    }

    public static boolean hasNestedScrollingParent(@NonNull View view, int i) {
        View view2 = view;
        int type = i;
        if (view2 instanceof NestedScrollingChild2) {
            boolean hasNestedScrollingParent = ((NestedScrollingChild2) view2).hasNestedScrollingParent(type);
        } else if (type == 0) {
            return hasNestedScrollingParent(view2);
        }
        return false;
    }

    public static boolean dispatchNestedScroll(@NonNull View view, int i, int i2, int i3, int i4, @Nullable int[] iArr, int i5) {
        View view2 = view;
        int dxConsumed = i;
        int dyConsumed = i2;
        int dxUnconsumed = i3;
        int dyUnconsumed = i4;
        int[] offsetInWindow = iArr;
        int type = i5;
        if (view2 instanceof NestedScrollingChild2) {
            return ((NestedScrollingChild2) view2).dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type);
        }
        if (type == 0) {
            return dispatchNestedScroll(view2, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
        }
        return false;
    }

    public static boolean dispatchNestedPreScroll(@NonNull View view, int i, int i2, @Nullable int[] iArr, @Nullable int[] iArr2, int i3) {
        View view2 = view;
        int dx = i;
        int dy = i2;
        int[] consumed = iArr;
        int[] offsetInWindow = iArr2;
        int type = i3;
        if (view2 instanceof NestedScrollingChild2) {
            return ((NestedScrollingChild2) view2).dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type);
        }
        if (type == 0) {
            return dispatchNestedPreScroll(view2, dx, dy, consumed, offsetInWindow);
        }
        return false;
    }

    public static boolean dispatchNestedFling(@NonNull View view, float f, float f2, boolean z) {
        View view2 = view;
        float velocityX = f;
        float velocityY = f2;
        boolean consumed = z;
        if (Build.VERSION.SDK_INT >= 21) {
            return view2.dispatchNestedFling(velocityX, velocityY, consumed);
        }
        if (view2 instanceof NestedScrollingChild) {
            return ((NestedScrollingChild) view2).dispatchNestedFling(velocityX, velocityY, consumed);
        }
        return false;
    }

    public static boolean dispatchNestedPreFling(@NonNull View view, float f, float f2) {
        View view2 = view;
        float velocityX = f;
        float velocityY = f2;
        if (Build.VERSION.SDK_INT >= 21) {
            return view2.dispatchNestedPreFling(velocityX, velocityY);
        }
        if (view2 instanceof NestedScrollingChild) {
            return ((NestedScrollingChild) view2).dispatchNestedPreFling(velocityX, velocityY);
        }
        return false;
    }

    public static boolean isInLayout(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 18) {
            return view2.isInLayout();
        }
        return false;
    }

    public static boolean isLaidOut(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 19) {
            return view2.isLaidOut();
        }
        return view2.getWidth() > 0 && view2.getHeight() > 0;
    }

    public static boolean isLayoutDirectionResolved(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 19) {
            return view2.isLayoutDirectionResolved();
        }
        return false;
    }

    public static float getZ(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 21) {
            return view2.getZ();
        }
        return 0.0f;
    }

    public static void setZ(@NonNull View view, float f) {
        View view2 = view;
        float z = f;
        if (Build.VERSION.SDK_INT >= 21) {
            view2.setZ(z);
        }
    }

    public static void offsetTopAndBottom(@NonNull View view, int i) {
        View view2 = view;
        int offset = i;
        if (Build.VERSION.SDK_INT >= 23) {
            view2.offsetTopAndBottom(offset);
        } else if (Build.VERSION.SDK_INT >= 21) {
            Rect parentRect = getEmptyTempRect();
            boolean needInvalidateWorkaround = false;
            ViewParent parent = view2.getParent();
            if (parent instanceof View) {
                View p = (View) parent;
                parentRect.set(p.getLeft(), p.getTop(), p.getRight(), p.getBottom());
                needInvalidateWorkaround = !parentRect.intersects(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            }
            compatOffsetTopAndBottom(view2, offset);
            if (needInvalidateWorkaround && parentRect.intersect(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom())) {
                ((View) parent).invalidate(parentRect);
            }
        } else {
            compatOffsetTopAndBottom(view2, offset);
        }
    }

    private static void compatOffsetTopAndBottom(View view, int offset) {
        View view2 = view;
        view2.offsetTopAndBottom(offset);
        if (view2.getVisibility() == 0) {
            tickleInvalidationFlag(view2);
            ViewParent parent = view2.getParent();
            if (parent instanceof View) {
                tickleInvalidationFlag((View) parent);
            }
        }
    }

    public static void offsetLeftAndRight(@NonNull View view, int i) {
        View view2 = view;
        int offset = i;
        if (Build.VERSION.SDK_INT >= 23) {
            view2.offsetLeftAndRight(offset);
        } else if (Build.VERSION.SDK_INT >= 21) {
            Rect parentRect = getEmptyTempRect();
            boolean needInvalidateWorkaround = false;
            ViewParent parent = view2.getParent();
            if (parent instanceof View) {
                View p = (View) parent;
                parentRect.set(p.getLeft(), p.getTop(), p.getRight(), p.getBottom());
                needInvalidateWorkaround = !parentRect.intersects(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            }
            compatOffsetLeftAndRight(view2, offset);
            if (needInvalidateWorkaround && parentRect.intersect(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom())) {
                ((View) parent).invalidate(parentRect);
            }
        } else {
            compatOffsetLeftAndRight(view2, offset);
        }
    }

    private static void compatOffsetLeftAndRight(View view, int offset) {
        View view2 = view;
        view2.offsetLeftAndRight(offset);
        if (view2.getVisibility() == 0) {
            tickleInvalidationFlag(view2);
            ViewParent parent = view2.getParent();
            if (parent instanceof View) {
                tickleInvalidationFlag((View) parent);
            }
        }
    }

    private static void tickleInvalidationFlag(View view) {
        View view2 = view;
        float y = view2.getTranslationY();
        view2.setTranslationY(y + 1.0f);
        view2.setTranslationY(y);
    }

    public static void setClipBounds(@NonNull View view, Rect rect) {
        View view2 = view;
        Rect clipBounds = rect;
        if (Build.VERSION.SDK_INT >= 18) {
            view2.setClipBounds(clipBounds);
        }
    }

    @Nullable
    public static Rect getClipBounds(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 18) {
            return view2.getClipBounds();
        }
        return null;
    }

    public static boolean isAttachedToWindow(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 19) {
            return view2.isAttachedToWindow();
        }
        return view2.getWindowToken() != null;
    }

    public static boolean hasOnClickListeners(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 15) {
            return view2.hasOnClickListeners();
        }
        return false;
    }

    public static void setScrollIndicators(@NonNull View view, int i) {
        View view2 = view;
        int indicators = i;
        if (Build.VERSION.SDK_INT >= 23) {
            view2.setScrollIndicators(indicators);
        }
    }

    public static void setScrollIndicators(@NonNull View view, int i, int i2) {
        View view2 = view;
        int indicators = i;
        int mask = i2;
        if (Build.VERSION.SDK_INT >= 23) {
            view2.setScrollIndicators(indicators, mask);
        }
    }

    public static int getScrollIndicators(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 23) {
            return view2.getScrollIndicators();
        }
        return 0;
    }

    public static void setPointerIcon(@NonNull View view, PointerIconCompat pointerIconCompat) {
        View view2 = view;
        PointerIconCompat pointerIcon = pointerIconCompat;
        if (Build.VERSION.SDK_INT >= 24) {
            view2.setPointerIcon((PointerIcon) (pointerIcon != null ? pointerIcon.getPointerIcon() : null));
        }
    }

    @Nullable
    public static Display getDisplay(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 17) {
            return view2.getDisplay();
        }
        if (isAttachedToWindow(view2)) {
            return ((WindowManager) view2.getContext().getSystemService("window")).getDefaultDisplay();
        }
        return null;
    }

    public static void setTooltipText(@NonNull View view, @Nullable CharSequence charSequence) {
        View view2 = view;
        CharSequence tooltipText = charSequence;
        if (Build.VERSION.SDK_INT >= 26) {
            view2.setTooltipText(tooltipText);
        }
    }

    public static boolean startDragAndDrop(@NonNull View view, ClipData clipData, View.DragShadowBuilder dragShadowBuilder, Object obj, int i) {
        View v = view;
        ClipData data = clipData;
        View.DragShadowBuilder shadowBuilder = dragShadowBuilder;
        Object localState = obj;
        int flags = i;
        if (Build.VERSION.SDK_INT >= 24) {
            return v.startDragAndDrop(data, shadowBuilder, localState, flags);
        }
        return v.startDrag(data, shadowBuilder, localState, flags);
    }

    public static void cancelDragAndDrop(@NonNull View view) {
        View v = view;
        if (Build.VERSION.SDK_INT >= 24) {
            v.cancelDragAndDrop();
        }
    }

    public static void updateDragShadow(@NonNull View view, View.DragShadowBuilder dragShadowBuilder) {
        View v = view;
        View.DragShadowBuilder shadowBuilder = dragShadowBuilder;
        if (Build.VERSION.SDK_INT >= 24) {
            v.updateDragShadow(shadowBuilder);
        }
    }

    public static int getNextClusterForwardId(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 26) {
            return view2.getNextClusterForwardId();
        }
        return -1;
    }

    public static void setNextClusterForwardId(@NonNull View view, int i) {
        View view2 = view;
        int nextClusterForwardId = i;
        if (Build.VERSION.SDK_INT >= 26) {
            view2.setNextClusterForwardId(nextClusterForwardId);
        }
    }

    public static boolean isKeyboardNavigationCluster(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 26) {
            return view2.isKeyboardNavigationCluster();
        }
        return false;
    }

    public static void setKeyboardNavigationCluster(@NonNull View view, boolean z) {
        View view2 = view;
        boolean isCluster = z;
        if (Build.VERSION.SDK_INT >= 26) {
            view2.setKeyboardNavigationCluster(isCluster);
        }
    }

    public static boolean isFocusedByDefault(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 26) {
            return view2.isFocusedByDefault();
        }
        return false;
    }

    public static void setFocusedByDefault(@NonNull View view, boolean z) {
        View view2 = view;
        boolean isFocusedByDefault = z;
        if (Build.VERSION.SDK_INT >= 26) {
            view2.setFocusedByDefault(isFocusedByDefault);
        }
    }

    public static View keyboardNavigationClusterSearch(@NonNull View view, View view2, int i) {
        View view3 = view;
        View currentCluster = view2;
        int direction = i;
        if (Build.VERSION.SDK_INT >= 26) {
            return view3.keyboardNavigationClusterSearch(currentCluster, direction);
        }
        return null;
    }

    public static void addKeyboardNavigationClusters(@NonNull View view, @NonNull Collection<View> collection, int i) {
        View view2 = view;
        Collection<View> views = collection;
        int direction = i;
        if (Build.VERSION.SDK_INT >= 26) {
            view2.addKeyboardNavigationClusters(views, direction);
        }
    }

    public static boolean restoreDefaultFocus(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 26) {
            return view2.restoreDefaultFocus();
        }
        return view2.requestFocus();
    }

    public static boolean hasExplicitFocusable(@NonNull View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 26) {
            return view2.hasExplicitFocusable();
        }
        return view2.hasFocusable();
    }

    public static int generateViewId() {
        int result;
        int newValue;
        if (Build.VERSION.SDK_INT >= 17) {
            return View.generateViewId();
        }
        do {
            result = sNextGeneratedId.get();
            newValue = result + 1;
            if (newValue > 16777215) {
                newValue = 1;
            }
        } while (!sNextGeneratedId.compareAndSet(result, newValue));
        return result;
    }

    public static void addOnUnhandledKeyEventListener(@NonNull View view, @NonNull OnUnhandledKeyEventListenerCompat onUnhandledKeyEventListenerCompat) {
        ArrayList arrayList;
        View.OnUnhandledKeyEventListener onUnhandledKeyEventListener;
        Map<OnUnhandledKeyEventListenerCompat, View.OnUnhandledKeyEventListener> map;
        View v = view;
        OnUnhandledKeyEventListenerCompat listener = onUnhandledKeyEventListenerCompat;
        if (Build.VERSION.SDK_INT >= 28) {
            Map<OnUnhandledKeyEventListenerCompat, View.OnUnhandledKeyEventListener> viewListeners = (Map) v.getTag(C0020R.C0022id.tag_unhandled_key_listeners);
            if (viewListeners == null) {
                new C1642ArrayMap<>();
                viewListeners = map;
                v.setTag(C0020R.C0022id.tag_unhandled_key_listeners, viewListeners);
            }
            new OnUnhandledKeyEventListenerWrapper(listener);
            View.OnUnhandledKeyEventListener fwListener = onUnhandledKeyEventListener;
            View.OnUnhandledKeyEventListener put = viewListeners.put(listener, fwListener);
            v.addOnUnhandledKeyEventListener(fwListener);
            return;
        }
        ArrayList arrayList2 = (ArrayList) v.getTag(C0020R.C0022id.tag_unhandled_key_listeners);
        if (arrayList2 == null) {
            new ArrayList();
            arrayList2 = arrayList;
            v.setTag(C0020R.C0022id.tag_unhandled_key_listeners, arrayList2);
        }
        boolean add = arrayList2.add(listener);
        if (arrayList2.size() == 1) {
            UnhandledKeyEventManager.registerListeningView(v);
        }
    }

    public static void removeOnUnhandledKeyEventListener(@NonNull View view, @NonNull OnUnhandledKeyEventListenerCompat onUnhandledKeyEventListenerCompat) {
        View.OnUnhandledKeyEventListener fwListener;
        View v = view;
        OnUnhandledKeyEventListenerCompat listener = onUnhandledKeyEventListenerCompat;
        if (Build.VERSION.SDK_INT >= 28) {
            Map<OnUnhandledKeyEventListenerCompat, View.OnUnhandledKeyEventListener> viewListeners = (Map) v.getTag(C0020R.C0022id.tag_unhandled_key_listeners);
            if (viewListeners != null && (fwListener = viewListeners.get(listener)) != null) {
                v.removeOnUnhandledKeyEventListener(fwListener);
                return;
            }
            return;
        }
        ArrayList<OnUnhandledKeyEventListenerCompat> viewListeners2 = (ArrayList) v.getTag(C0020R.C0022id.tag_unhandled_key_listeners);
        if (viewListeners2 != null) {
            boolean remove = viewListeners2.remove(listener);
            if (viewListeners2.size() == 0) {
                UnhandledKeyEventManager.unregisterListeningView(v);
            }
        }
    }

    protected ViewCompat() {
    }

    @RequiresApi(28)
    /* renamed from: android.support.v4.view.ViewCompat$OnUnhandledKeyEventListenerWrapper */
    private static class OnUnhandledKeyEventListenerWrapper implements View.OnUnhandledKeyEventListener {
        private OnUnhandledKeyEventListenerCompat mCompatListener;

        OnUnhandledKeyEventListenerWrapper(OnUnhandledKeyEventListenerCompat listener) {
            this.mCompatListener = listener;
        }

        public boolean onUnhandledKeyEvent(View v, KeyEvent event) {
            return this.mCompatListener.onUnhandledKeyEvent(v, event);
        }
    }

    @UiThread
    static boolean dispatchUnhandledKeyEventBeforeHierarchy(View view, KeyEvent keyEvent) {
        View root = view;
        KeyEvent evt = keyEvent;
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return UnhandledKeyEventManager.m6at(root).preDispatch(evt);
    }

    @UiThread
    static boolean dispatchUnhandledKeyEventBeforeCallback(View view, KeyEvent keyEvent) {
        View root = view;
        KeyEvent evt = keyEvent;
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return UnhandledKeyEventManager.m6at(root).dispatch(root, evt);
    }

    /* renamed from: android.support.v4.view.ViewCompat$UnhandledKeyEventManager */
    static class UnhandledKeyEventManager {
        private static final ArrayList<WeakReference<View>> sViewsWithListeners;
        private SparseArray<WeakReference<View>> mCapturedKeys = null;
        private WeakReference<KeyEvent> mLastDispatchedPreViewKeyEvent = null;
        @Nullable
        private WeakHashMap<View, Boolean> mViewsContainingListeners = null;

        UnhandledKeyEventManager() {
        }

        static {
            ArrayList<WeakReference<View>> arrayList;
            new ArrayList<>();
            sViewsWithListeners = arrayList;
        }

        private SparseArray<WeakReference<View>> getCapturedKeys() {
            SparseArray<WeakReference<View>> sparseArray;
            if (this.mCapturedKeys == null) {
                new SparseArray<>();
                this.mCapturedKeys = sparseArray;
            }
            return this.mCapturedKeys;
        }

        /* renamed from: at */
        static UnhandledKeyEventManager m6at(View view) {
            UnhandledKeyEventManager unhandledKeyEventManager;
            View root = view;
            UnhandledKeyEventManager manager = (UnhandledKeyEventManager) root.getTag(C0020R.C0022id.tag_unhandled_key_event_manager);
            if (manager == null) {
                new UnhandledKeyEventManager();
                manager = unhandledKeyEventManager;
                root.setTag(C0020R.C0022id.tag_unhandled_key_event_manager, manager);
            }
            return manager;
        }

        /* access modifiers changed from: package-private */
        public boolean dispatch(View view, KeyEvent keyEvent) {
            Object obj;
            View root = view;
            KeyEvent event = keyEvent;
            if (event.getAction() == 0) {
                recalcViewsWithUnhandled();
            }
            View consumer = dispatchInOrder(root, event);
            if (event.getAction() == 0) {
                int keycode = event.getKeyCode();
                if (consumer != null && !KeyEvent.isModifierKey(keycode)) {
                    new WeakReference(consumer);
                    getCapturedKeys().put(keycode, obj);
                }
            }
            return consumer != null;
        }

        @Nullable
        private View dispatchInOrder(View view, KeyEvent keyEvent) {
            View view2 = view;
            KeyEvent event = keyEvent;
            if (this.mViewsContainingListeners == null || !this.mViewsContainingListeners.containsKey(view2)) {
                return null;
            }
            if (view2 instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) view2;
                for (int i = vg.getChildCount() - 1; i >= 0; i--) {
                    View consumer = dispatchInOrder(vg.getChildAt(i), event);
                    if (consumer != null) {
                        return consumer;
                    }
                }
            }
            if (onUnhandledKeyEvent(view2, event)) {
                return view2;
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public boolean preDispatch(KeyEvent keyEvent) {
            WeakReference<KeyEvent> weakReference;
            int idx;
            KeyEvent event = keyEvent;
            if (this.mLastDispatchedPreViewKeyEvent != null && this.mLastDispatchedPreViewKeyEvent.get() == event) {
                return false;
            }
            new WeakReference<>(event);
            this.mLastDispatchedPreViewKeyEvent = weakReference;
            WeakReference<View> currentReceiver = null;
            SparseArray<WeakReference<View>> capturedKeys = getCapturedKeys();
            if (event.getAction() == 1 && (idx = capturedKeys.indexOfKey(event.getKeyCode())) >= 0) {
                currentReceiver = capturedKeys.valueAt(idx);
                capturedKeys.removeAt(idx);
            }
            if (currentReceiver == null) {
                currentReceiver = capturedKeys.get(event.getKeyCode());
            }
            if (currentReceiver == null) {
                return false;
            }
            View target = (View) currentReceiver.get();
            if (target != null && ViewCompat.isAttachedToWindow(target)) {
                boolean onUnhandledKeyEvent = onUnhandledKeyEvent(target, event);
            }
            return true;
        }

        private boolean onUnhandledKeyEvent(@NonNull View view, @NonNull KeyEvent keyEvent) {
            View v = view;
            KeyEvent event = keyEvent;
            ArrayList<OnUnhandledKeyEventListenerCompat> viewListeners = (ArrayList) v.getTag(C0020R.C0022id.tag_unhandled_key_listeners);
            if (viewListeners != null) {
                for (int i = viewListeners.size() - 1; i >= 0; i--) {
                    if (viewListeners.get(i).onUnhandledKeyEvent(v, event)) {
                        return true;
                    }
                }
            }
            return false;
        }

        /* JADX INFO: finally extract failed */
        static void registerListeningView(View view) {
            Object obj;
            View v = view;
            ArrayList<WeakReference<View>> arrayList = sViewsWithListeners;
            ArrayList<WeakReference<View>> arrayList2 = arrayList;
            synchronized (arrayList) {
                try {
                    Iterator<WeakReference<View>> it = sViewsWithListeners.iterator();
                    while (it.hasNext()) {
                        if (it.next().get() == v) {
                            return;
                        }
                    }
                    new WeakReference(v);
                    boolean add = sViewsWithListeners.add(obj);
                } catch (Throwable th) {
                    Throwable th2 = th;
                    ArrayList<WeakReference<View>> arrayList3 = arrayList2;
                    throw th2;
                }
            }
        }

        static void unregisterListeningView(View view) {
            View v = view;
            ArrayList<WeakReference<View>> arrayList = sViewsWithListeners;
            ArrayList<WeakReference<View>> arrayList2 = arrayList;
            synchronized (arrayList) {
                int i = 0;
                while (i < sViewsWithListeners.size()) {
                    try {
                        if (sViewsWithListeners.get(i).get() == v) {
                            WeakReference<View> remove = sViewsWithListeners.remove(i);
                            return;
                        }
                        i++;
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        ArrayList<WeakReference<View>> arrayList3 = arrayList2;
                        throw th2;
                    }
                }
            }
        }

        private void recalcViewsWithUnhandled() {
            WeakHashMap<View, Boolean> weakHashMap;
            if (this.mViewsContainingListeners != null) {
                this.mViewsContainingListeners.clear();
            }
            if (!sViewsWithListeners.isEmpty()) {
                ArrayList<WeakReference<View>> arrayList = sViewsWithListeners;
                ArrayList<WeakReference<View>> arrayList2 = arrayList;
                synchronized (arrayList) {
                    try {
                        if (this.mViewsContainingListeners == null) {
                            new WeakHashMap();
                            this.mViewsContainingListeners = weakHashMap;
                        }
                        for (int i = sViewsWithListeners.size() - 1; i >= 0; i--) {
                            View v = (View) sViewsWithListeners.get(i).get();
                            if (v == null) {
                                WeakReference<View> remove = sViewsWithListeners.remove(i);
                            } else {
                                Boolean put = this.mViewsContainingListeners.put(v, Boolean.TRUE);
                                for (ViewParent nxt = v.getParent(); nxt instanceof View; nxt = nxt.getParent()) {
                                    Boolean put2 = this.mViewsContainingListeners.put((View) nxt, Boolean.TRUE);
                                }
                            }
                        }
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        ArrayList<WeakReference<View>> arrayList3 = arrayList2;
                        throw th2;
                    }
                }
            }
        }
    }
}
