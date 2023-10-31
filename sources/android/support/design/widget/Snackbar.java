package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.design.C0064R;
import android.support.design.widget.BaseTransientBottomBar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import gnu.expr.Declaration;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class Snackbar extends BaseTransientBottomBar<Snackbar> {
    public static final int LENGTH_INDEFINITE = -2;
    public static final int LENGTH_LONG = 0;
    public static final int LENGTH_SHORT = -1;
    private static final int[] SNACKBAR_BUTTON_STYLE_ATTR = {C0064R.attr.snackbarButtonStyle};
    private final AccessibilityManager accessibilityManager;
    @Nullable
    private BaseTransientBottomBar.BaseCallback<Snackbar> callback;
    private boolean hasAction;

    @IntRange(from = 1)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    public static class Callback extends BaseTransientBottomBar.BaseCallback<Snackbar> {
        public static final int DISMISS_EVENT_ACTION = 1;
        public static final int DISMISS_EVENT_CONSECUTIVE = 4;
        public static final int DISMISS_EVENT_MANUAL = 3;
        public static final int DISMISS_EVENT_SWIPE = 0;
        public static final int DISMISS_EVENT_TIMEOUT = 2;

        public Callback() {
        }

        public void onShown(Snackbar sb) {
        }

        public void onDismissed(Snackbar transientBottomBar, int event) {
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private Snackbar(android.view.ViewGroup r9, android.view.View r10, android.support.design.snackbar.ContentViewCallback r11) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r11
            r4 = r0
            r5 = r1
            r6 = r2
            r7 = r3
            r4.<init>(r5, r6, r7)
            r4 = r0
            r5 = r1
            android.content.Context r5 = r5.getContext()
            java.lang.String r6 = "accessibility"
            java.lang.Object r5 = r5.getSystemService(r6)
            android.view.accessibility.AccessibilityManager r5 = (android.view.accessibility.AccessibilityManager) r5
            r4.accessibilityManager = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.Snackbar.<init>(android.view.ViewGroup, android.view.View, android.support.design.snackbar.ContentViewCallback):void");
    }

    public void show() {
        super.show();
    }

    public void dismiss() {
        super.dismiss();
    }

    public boolean isShown() {
        return super.isShown();
    }

    @NonNull
    public static Snackbar make(@NonNull View view, @NonNull CharSequence charSequence, int i) {
        Snackbar snackbar;
        Throwable th;
        CharSequence text = charSequence;
        int duration = i;
        ViewGroup parent = findSuitableParent(view);
        if (parent == null) {
            Throwable th2 = th;
            new IllegalArgumentException("No suitable parent found from the given view. Please provide a valid view.");
            throw th2;
        }
        SnackbarContentLayout content = (SnackbarContentLayout) LayoutInflater.from(parent.getContext()).inflate(hasSnackbarButtonStyleAttr(parent.getContext()) ? C0064R.layout.mtrl_layout_snackbar_include : C0064R.layout.design_layout_snackbar_include, parent, false);
        new Snackbar(parent, content, content);
        Snackbar snackbar2 = snackbar;
        Snackbar text2 = snackbar2.setText(text);
        BaseTransientBottomBar duration2 = snackbar2.setDuration(duration);
        return snackbar2;
    }

    protected static boolean hasSnackbarButtonStyleAttr(Context context) {
        TypedArray a = context.obtainStyledAttributes(SNACKBAR_BUTTON_STYLE_ATTR);
        int snackbarButtonStyleResId = a.getResourceId(0, -1);
        a.recycle();
        return snackbarButtonStyleResId != -1;
    }

    @NonNull
    public static Snackbar make(@NonNull View view, @StringRes int resId, int duration) {
        View view2 = view;
        return make(view2, view2.getResources().getText(resId), duration);
    }

    private static ViewGroup findSuitableParent(View view) {
        View view2 = view;
        ViewGroup fallback = null;
        while (!(view2 instanceof CoordinatorLayout)) {
            if (view2 instanceof FrameLayout) {
                if (view2.getId() == 16908290) {
                    return (ViewGroup) view2;
                }
                fallback = (ViewGroup) view2;
            }
            if (view2 != null) {
                ViewParent parent = view2.getParent();
                view2 = parent instanceof View ? (View) parent : null;
            }
            if (view2 == null) {
                return fallback;
            }
        }
        return (ViewGroup) view2;
    }

    @NonNull
    public Snackbar setText(@NonNull CharSequence message) {
        ((SnackbarContentLayout) this.view.getChildAt(0)).getMessageView().setText(message);
        return this;
    }

    @NonNull
    public Snackbar setText(@StringRes int resId) {
        return setText(getContext().getText(resId));
    }

    @NonNull
    public Snackbar setAction(@StringRes int resId, View.OnClickListener listener) {
        return setAction(getContext().getText(resId), listener);
    }

    @NonNull
    public Snackbar setAction(CharSequence charSequence, View.OnClickListener onClickListener) {
        View.OnClickListener onClickListener2;
        CharSequence text = charSequence;
        View.OnClickListener listener = onClickListener;
        TextView tv = ((SnackbarContentLayout) this.view.getChildAt(0)).getActionView();
        if (TextUtils.isEmpty(text) || listener == null) {
            tv.setVisibility(8);
            tv.setOnClickListener((View.OnClickListener) null);
            this.hasAction = false;
        } else {
            this.hasAction = true;
            tv.setVisibility(0);
            tv.setText(text);
            final View.OnClickListener onClickListener3 = listener;
            new View.OnClickListener(this) {
                final /* synthetic */ Snackbar this$0;

                {
                    this.this$0 = this$0;
                }

                public void onClick(View view) {
                    onClickListener3.onClick(view);
                    this.this$0.dispatchDismiss(1);
                }
            };
            tv.setOnClickListener(onClickListener2);
        }
        return this;
    }

    public int getDuration() {
        int duration;
        if (!this.hasAction || !this.accessibilityManager.isTouchExplorationEnabled()) {
            duration = super.getDuration();
        } else {
            duration = -2;
        }
        return duration;
    }

    @NonNull
    public Snackbar setActionTextColor(ColorStateList colors) {
        ((SnackbarContentLayout) this.view.getChildAt(0)).getActionView().setTextColor(colors);
        return this;
    }

    @NonNull
    public Snackbar setActionTextColor(@ColorInt int color) {
        ((SnackbarContentLayout) this.view.getChildAt(0)).getActionView().setTextColor(color);
        return this;
    }

    @Deprecated
    @NonNull
    public Snackbar setCallback(Callback callback2) {
        Callback callback3 = callback2;
        if (this.callback != null) {
            BaseTransientBottomBar removeCallback = removeCallback(this.callback);
        }
        if (callback3 != null) {
            BaseTransientBottomBar addCallback = addCallback(callback3);
        }
        this.callback = callback3;
        return this;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final class SnackbarLayout extends BaseTransientBottomBar.SnackbarBaseLayout {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SnackbarLayout(Context context) {
            super(context);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SnackbarLayout(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        /* access modifiers changed from: protected */
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            int childCount = getChildCount();
            int availableWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                if (child.getLayoutParams().width == -1) {
                    child.measure(View.MeasureSpec.makeMeasureSpec(availableWidth, Declaration.MODULE_REFERENCE), View.MeasureSpec.makeMeasureSpec(child.getMeasuredHeight(), Declaration.MODULE_REFERENCE));
                }
            }
        }
    }
}
