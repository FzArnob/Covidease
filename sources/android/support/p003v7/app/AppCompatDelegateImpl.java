package android.support.p003v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.VisibleForTesting;
import android.support.p000v4.app.NavUtils;
import android.support.p000v4.view.KeyEventDispatcher;
import android.support.p000v4.view.LayoutInflaterCompat;
import android.support.p000v4.view.OnApplyWindowInsetsListener;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.ViewPropertyAnimatorCompat;
import android.support.p000v4.view.ViewPropertyAnimatorListener;
import android.support.p000v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.p000v4.view.WindowInsetsCompat;
import android.support.p000v4.widget.PopupWindowCompat;
import android.support.p003v7.app.ActionBarDrawerToggle;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.content.res.AppCompatResources;
import android.support.p003v7.view.ActionMode;
import android.support.p003v7.view.ContextThemeWrapper;
import android.support.p003v7.view.StandaloneActionMode;
import android.support.p003v7.view.SupportActionModeWrapper;
import android.support.p003v7.view.SupportMenuInflater;
import android.support.p003v7.view.WindowCallbackWrapper;
import android.support.p003v7.view.menu.ListMenuPresenter;
import android.support.p003v7.view.menu.MenuBuilder;
import android.support.p003v7.view.menu.MenuPresenter;
import android.support.p003v7.view.menu.MenuView;
import android.support.p003v7.widget.ActionBarContextView;
import android.support.p003v7.widget.AppCompatDrawableManager;
import android.support.p003v7.widget.ContentFrameLayout;
import android.support.p003v7.widget.DecorContentParent;
import android.support.p003v7.widget.FitWindowsViewGroup;
import android.support.p003v7.widget.TintTypedArray;
import android.support.p003v7.widget.Toolbar;
import android.support.p003v7.widget.VectorEnabledTintResources;
import android.support.p003v7.widget.ViewStubCompat;
import android.support.p003v7.widget.ViewUtils;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.lang.Thread;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* renamed from: android.support.v7.app.AppCompatDelegateImpl */
class AppCompatDelegateImpl extends AppCompatDelegate implements MenuBuilder.Callback, LayoutInflater.Factory2 {
    private static final boolean DEBUG = false;
    static final String EXCEPTION_HANDLER_MESSAGE_SUFFIX = ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.";
    private static final boolean IS_PRE_LOLLIPOP = (Build.VERSION.SDK_INT < 21);
    private static final String KEY_LOCAL_NIGHT_MODE = "appcompat:local_night_mode";
    private static boolean sInstalledExceptionHandler = true;
    private static final int[] sWindowBackgroundStyleable = {16842836};
    ActionBar mActionBar;
    private ActionMenuPresenterCallback mActionMenuPresenterCallback;
    ActionMode mActionMode;
    PopupWindow mActionModePopup;
    ActionBarContextView mActionModeView;
    final AppCompatCallback mAppCompatCallback;
    private AppCompatViewInflater mAppCompatViewInflater;
    final Window.Callback mAppCompatWindowCallback;
    private boolean mApplyDayNightCalled;
    private AutoNightModeManager mAutoNightModeManager;
    private boolean mClosingActionMenu;
    final Context mContext;
    private DecorContentParent mDecorContentParent;
    private boolean mEnableDefaultActionBarUp;
    ViewPropertyAnimatorCompat mFadeAnim = null;
    private boolean mFeatureIndeterminateProgress;
    private boolean mFeatureProgress;
    private boolean mHandleNativeActionModes = true;
    boolean mHasActionBar;
    int mInvalidatePanelMenuFeatures;
    boolean mInvalidatePanelMenuPosted;
    private final Runnable mInvalidatePanelMenuRunnable;
    boolean mIsDestroyed;
    boolean mIsFloating;
    private int mLocalNightMode = -100;
    private boolean mLongPressBackDown;
    MenuInflater mMenuInflater;
    final Window.Callback mOriginalWindowCallback;
    boolean mOverlayActionBar;
    boolean mOverlayActionMode;
    private PanelMenuPresenterCallback mPanelMenuPresenterCallback;
    private PanelFeatureState[] mPanels;
    private PanelFeatureState mPreparedPanel;
    Runnable mShowActionModePopup;
    private View mStatusGuard;
    private ViewGroup mSubDecor;
    private boolean mSubDecorInstalled;
    private Rect mTempRect1;
    private Rect mTempRect2;
    private CharSequence mTitle;
    private TextView mTitleView;
    final Window mWindow;
    boolean mWindowNoTitle;

    static {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
        if (IS_PRE_LOLLIPOP && !sInstalledExceptionHandler) {
            final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            new Thread.UncaughtExceptionHandler() {
                public void uncaughtException(Thread thread, Throwable th) {
                    Throwable th2;
                    StringBuilder sb;
                    Thread thread2 = thread;
                    Throwable thowable = th;
                    if (shouldWrapException(thowable)) {
                        new StringBuilder();
                        new Resources.NotFoundException(sb.append(thowable.getMessage()).append(AppCompatDelegateImpl.EXCEPTION_HANDLER_MESSAGE_SUFFIX).toString());
                        Throwable wrapped = th2;
                        Throwable initCause = wrapped.initCause(thowable.getCause());
                        wrapped.setStackTrace(thowable.getStackTrace());
                        defaultUncaughtExceptionHandler.uncaughtException(thread2, wrapped);
                        return;
                    }
                    defaultUncaughtExceptionHandler.uncaughtException(thread2, thowable);
                }

                private boolean shouldWrapException(Throwable th) {
                    Throwable throwable = th;
                    if (!(throwable instanceof Resources.NotFoundException)) {
                        return false;
                    }
                    String message = throwable.getMessage();
                    return message != null && (message.contains("drawable") || message.contains("Drawable"));
                }
            };
            Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
        }
    }

    AppCompatDelegateImpl(Context context, Window window, AppCompatCallback callback) {
        Runnable runnable;
        Window.Callback callback2;
        Throwable th;
        Context context2 = context;
        new Runnable(this) {
            final /* synthetic */ AppCompatDelegateImpl this$0;

            {
                this.this$0 = this$0;
            }

            public void run() {
                if ((this.this$0.mInvalidatePanelMenuFeatures & 1) != 0) {
                    this.this$0.doInvalidatePanelMenu(0);
                }
                if ((this.this$0.mInvalidatePanelMenuFeatures & 4096) != 0) {
                    this.this$0.doInvalidatePanelMenu(108);
                }
                this.this$0.mInvalidatePanelMenuPosted = false;
                this.this$0.mInvalidatePanelMenuFeatures = 0;
            }
        };
        this.mInvalidatePanelMenuRunnable = runnable;
        this.mContext = context2;
        this.mWindow = window;
        this.mAppCompatCallback = callback;
        this.mOriginalWindowCallback = this.mWindow.getCallback();
        if (this.mOriginalWindowCallback instanceof AppCompatWindowCallback) {
            Throwable th2 = th;
            new IllegalStateException("AppCompat has already installed itself into the Window");
            throw th2;
        }
        new AppCompatWindowCallback(this, this.mOriginalWindowCallback);
        this.mAppCompatWindowCallback = callback2;
        this.mWindow.setCallback(this.mAppCompatWindowCallback);
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(context2, (AttributeSet) null, sWindowBackgroundStyleable);
        Drawable winBg = a.getDrawableIfKnown(0);
        if (winBg != null) {
            this.mWindow.setBackgroundDrawable(winBg);
        }
        a.recycle();
    }

    public void onCreate(Bundle bundle) {
        Bundle savedInstanceState = bundle;
        if (this.mOriginalWindowCallback instanceof Activity) {
            String parentActivityName = null;
            try {
                parentActivityName = NavUtils.getParentActivityName((Activity) this.mOriginalWindowCallback);
            } catch (IllegalArgumentException e) {
                IllegalArgumentException illegalArgumentException = e;
            }
            if (parentActivityName != null) {
                ActionBar ab = peekSupportActionBar();
                if (ab == null) {
                    this.mEnableDefaultActionBarUp = true;
                } else {
                    ab.setDefaultDisplayHomeAsUpEnabled(true);
                }
            }
        }
        if (savedInstanceState != null && this.mLocalNightMode == -100) {
            this.mLocalNightMode = savedInstanceState.getInt(KEY_LOCAL_NIGHT_MODE, -100);
        }
    }

    public void onPostCreate(Bundle bundle) {
        Bundle bundle2 = bundle;
        ensureSubDecor();
    }

    public ActionBar getSupportActionBar() {
        initWindowDecorActionBar();
        return this.mActionBar;
    }

    /* access modifiers changed from: package-private */
    public final ActionBar peekSupportActionBar() {
        return this.mActionBar;
    }

    /* access modifiers changed from: package-private */
    public final Window.Callback getWindowCallback() {
        return this.mWindow.getCallback();
    }

    private void initWindowDecorActionBar() {
        ActionBar actionBar;
        ActionBar actionBar2;
        ensureSubDecor();
        if (this.mHasActionBar && this.mActionBar == null) {
            if (this.mOriginalWindowCallback instanceof Activity) {
                new WindowDecorActionBar((Activity) this.mOriginalWindowCallback, this.mOverlayActionBar);
                this.mActionBar = actionBar2;
            } else if (this.mOriginalWindowCallback instanceof Dialog) {
                new WindowDecorActionBar((Dialog) this.mOriginalWindowCallback);
                this.mActionBar = actionBar;
            }
            if (this.mActionBar != null) {
                this.mActionBar.setDefaultDisplayHomeAsUpEnabled(this.mEnableDefaultActionBarUp);
            }
        }
    }

    public void setSupportActionBar(Toolbar toolbar) {
        ToolbarActionBar toolbarActionBar;
        Throwable th;
        Toolbar toolbar2 = toolbar;
        if (this.mOriginalWindowCallback instanceof Activity) {
            ActionBar ab = getSupportActionBar();
            if (ab instanceof WindowDecorActionBar) {
                Throwable th2 = th;
                new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
                throw th2;
            }
            this.mMenuInflater = null;
            if (ab != null) {
                ab.onDestroy();
            }
            if (toolbar2 != null) {
                new ToolbarActionBar(toolbar2, ((Activity) this.mOriginalWindowCallback).getTitle(), this.mAppCompatWindowCallback);
                ToolbarActionBar tbab = toolbarActionBar;
                this.mActionBar = tbab;
                this.mWindow.setCallback(tbab.getWrappedWindowCallback());
            } else {
                this.mActionBar = null;
                this.mWindow.setCallback(this.mAppCompatWindowCallback);
            }
            invalidateOptionsMenu();
        }
    }

    /* access modifiers changed from: package-private */
    public final Context getActionBarThemedContext() {
        Context context = null;
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            context = ab.getThemedContext();
        }
        if (context == null) {
            context = this.mContext;
        }
        return context;
    }

    public MenuInflater getMenuInflater() {
        if (this.mMenuInflater == null) {
            initWindowDecorActionBar();
            SupportMenuInflater supportMenuInflater = r5;
            SupportMenuInflater supportMenuInflater2 = new SupportMenuInflater(this.mActionBar != null ? this.mActionBar.getThemedContext() : this.mContext);
            this.mMenuInflater = supportMenuInflater;
        }
        return this.mMenuInflater;
    }

    @Nullable
    public <T extends View> T findViewById(@IdRes int id) {
        ensureSubDecor();
        return this.mWindow.findViewById(id);
    }

    public void onConfigurationChanged(Configuration configuration) {
        ActionBar ab;
        Configuration newConfig = configuration;
        if (this.mHasActionBar && this.mSubDecorInstalled && (ab = getSupportActionBar()) != null) {
            ab.onConfigurationChanged(newConfig);
        }
        AppCompatDrawableManager.get().onConfigurationChanged(this.mContext);
        boolean applyDayNight = applyDayNight();
    }

    public void onStart() {
        boolean applyDayNight = applyDayNight();
    }

    public void onStop() {
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setShowHideAnimationEnabled(false);
        }
        if (this.mAutoNightModeManager != null) {
            this.mAutoNightModeManager.cleanup();
        }
    }

    public void onPostResume() {
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setShowHideAnimationEnabled(true);
        }
    }

    public void setContentView(View v) {
        ensureSubDecor();
        ViewGroup contentParent = (ViewGroup) this.mSubDecor.findViewById(16908290);
        contentParent.removeAllViews();
        contentParent.addView(v);
        this.mOriginalWindowCallback.onContentChanged();
    }

    public void setContentView(int resId) {
        ensureSubDecor();
        ViewGroup contentParent = (ViewGroup) this.mSubDecor.findViewById(16908290);
        contentParent.removeAllViews();
        View inflate = LayoutInflater.from(this.mContext).inflate(resId, contentParent);
        this.mOriginalWindowCallback.onContentChanged();
    }

    public void setContentView(View v, ViewGroup.LayoutParams lp) {
        ensureSubDecor();
        ViewGroup contentParent = (ViewGroup) this.mSubDecor.findViewById(16908290);
        contentParent.removeAllViews();
        contentParent.addView(v, lp);
        this.mOriginalWindowCallback.onContentChanged();
    }

    public void addContentView(View v, ViewGroup.LayoutParams lp) {
        ensureSubDecor();
        ((ViewGroup) this.mSubDecor.findViewById(16908290)).addView(v, lp);
        this.mOriginalWindowCallback.onContentChanged();
    }

    public void onSaveInstanceState(Bundle bundle) {
        Bundle outState = bundle;
        if (this.mLocalNightMode != -100) {
            outState.putInt(KEY_LOCAL_NIGHT_MODE, this.mLocalNightMode);
        }
    }

    public void onDestroy() {
        if (this.mInvalidatePanelMenuPosted) {
            boolean removeCallbacks = this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
        }
        this.mIsDestroyed = true;
        if (this.mActionBar != null) {
            this.mActionBar.onDestroy();
        }
        if (this.mAutoNightModeManager != null) {
            this.mAutoNightModeManager.cleanup();
        }
    }

    private void ensureSubDecor() {
        if (!this.mSubDecorInstalled) {
            this.mSubDecor = createSubDecor();
            CharSequence title = getTitle();
            if (!TextUtils.isEmpty(title)) {
                if (this.mDecorContentParent != null) {
                    this.mDecorContentParent.setWindowTitle(title);
                } else if (peekSupportActionBar() != null) {
                    peekSupportActionBar().setWindowTitle(title);
                } else if (this.mTitleView != null) {
                    this.mTitleView.setText(title);
                }
            }
            applyFixedSizeWindow();
            onSubDecorInstalled(this.mSubDecor);
            this.mSubDecorInstalled = true;
            PanelFeatureState st = getPanelState(0, false);
            if (this.mIsDestroyed) {
                return;
            }
            if (st == null || st.menu == null) {
                invalidatePanelMenu(108);
            }
        }
    }

    private ViewGroup createSubDecor() {
        FitWindowsViewGroup.OnFitSystemWindowsListener onFitSystemWindowsListener;
        OnApplyWindowInsetsListener onApplyWindowInsetsListener;
        ContentFrameLayout.OnAttachListener onAttachListener;
        Throwable th;
        StringBuilder sb;
        TypedValue typedValue;
        Context themedContext;
        Context context;
        Throwable th2;
        TypedArray a = this.mContext.obtainStyledAttributes(C0425R.styleable.AppCompatTheme);
        if (!a.hasValue(C0425R.styleable.AppCompatTheme_windowActionBar)) {
            a.recycle();
            Throwable th3 = th2;
            new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
            throw th3;
        }
        if (a.getBoolean(C0425R.styleable.AppCompatTheme_windowNoTitle, false)) {
            boolean requestWindowFeature = requestWindowFeature(1);
        } else if (a.getBoolean(C0425R.styleable.AppCompatTheme_windowActionBar, false)) {
            boolean requestWindowFeature2 = requestWindowFeature(108);
        }
        if (a.getBoolean(C0425R.styleable.AppCompatTheme_windowActionBarOverlay, false)) {
            boolean requestWindowFeature3 = requestWindowFeature(109);
        }
        if (a.getBoolean(C0425R.styleable.AppCompatTheme_windowActionModeOverlay, false)) {
            boolean requestWindowFeature4 = requestWindowFeature(10);
        }
        this.mIsFloating = a.getBoolean(C0425R.styleable.AppCompatTheme_android_windowIsFloating, false);
        a.recycle();
        View decorView = this.mWindow.getDecorView();
        LayoutInflater inflater = LayoutInflater.from(this.mContext);
        ViewGroup subDecor = null;
        if (this.mWindowNoTitle) {
            if (this.mOverlayActionMode) {
                subDecor = (ViewGroup) inflater.inflate(C0425R.layout.abc_screen_simple_overlay_action_mode, (ViewGroup) null);
            } else {
                subDecor = (ViewGroup) inflater.inflate(C0425R.layout.abc_screen_simple, (ViewGroup) null);
            }
            if (Build.VERSION.SDK_INT >= 21) {
                new OnApplyWindowInsetsListener(this) {
                    final /* synthetic */ AppCompatDelegateImpl this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                        View v = view;
                        WindowInsetsCompat insets = windowInsetsCompat;
                        int top = insets.getSystemWindowInsetTop();
                        int newTop = this.this$0.updateStatusGuard(top);
                        if (top != newTop) {
                            insets = insets.replaceSystemWindowInsets(insets.getSystemWindowInsetLeft(), newTop, insets.getSystemWindowInsetRight(), insets.getSystemWindowInsetBottom());
                        }
                        return ViewCompat.onApplyWindowInsets(v, insets);
                    }
                };
                ViewCompat.setOnApplyWindowInsetsListener(subDecor, onApplyWindowInsetsListener);
            } else {
                new FitWindowsViewGroup.OnFitSystemWindowsListener(this) {
                    final /* synthetic */ AppCompatDelegateImpl this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void onFitSystemWindows(Rect rect) {
                        Rect insets = rect;
                        insets.top = this.this$0.updateStatusGuard(insets.top);
                    }
                };
                ((FitWindowsViewGroup) subDecor).setOnFitSystemWindowsListener(onFitSystemWindowsListener);
            }
        } else if (this.mIsFloating) {
            subDecor = (ViewGroup) inflater.inflate(C0425R.layout.abc_dialog_title_material, (ViewGroup) null);
            this.mOverlayActionBar = false;
            this.mHasActionBar = false;
        } else if (this.mHasActionBar) {
            new TypedValue();
            TypedValue outValue = typedValue;
            boolean resolveAttribute = this.mContext.getTheme().resolveAttribute(C0425R.attr.actionBarTheme, outValue, true);
            if (outValue.resourceId != 0) {
                new ContextThemeWrapper(this.mContext, outValue.resourceId);
                themedContext = context;
            } else {
                themedContext = this.mContext;
            }
            subDecor = (ViewGroup) LayoutInflater.from(themedContext).inflate(C0425R.layout.abc_screen_toolbar, (ViewGroup) null);
            this.mDecorContentParent = (DecorContentParent) subDecor.findViewById(C0425R.C0427id.decor_content_parent);
            this.mDecorContentParent.setWindowCallback(getWindowCallback());
            if (this.mOverlayActionBar) {
                this.mDecorContentParent.initFeature(109);
            }
            if (this.mFeatureProgress) {
                this.mDecorContentParent.initFeature(2);
            }
            if (this.mFeatureIndeterminateProgress) {
                this.mDecorContentParent.initFeature(5);
            }
        }
        if (subDecor == null) {
            Throwable th4 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("AppCompat does not support the current theme features: { windowActionBar: ").append(this.mHasActionBar).append(", windowActionBarOverlay: ").append(this.mOverlayActionBar).append(", android:windowIsFloating: ").append(this.mIsFloating).append(", windowActionModeOverlay: ").append(this.mOverlayActionMode).append(", windowNoTitle: ").append(this.mWindowNoTitle).append(" }").toString());
            throw th4;
        }
        if (this.mDecorContentParent == null) {
            this.mTitleView = (TextView) subDecor.findViewById(C0425R.C0427id.title);
        }
        ViewUtils.makeOptionalFitsSystemWindows(subDecor);
        ContentFrameLayout contentView = (ContentFrameLayout) subDecor.findViewById(C0425R.C0427id.action_bar_activity_content);
        ViewGroup windowContentView = (ViewGroup) this.mWindow.findViewById(16908290);
        if (windowContentView != null) {
            while (windowContentView.getChildCount() > 0) {
                View child = windowContentView.getChildAt(0);
                windowContentView.removeViewAt(0);
                contentView.addView(child);
            }
            windowContentView.setId(-1);
            contentView.setId(16908290);
            if (windowContentView instanceof FrameLayout) {
                ((FrameLayout) windowContentView).setForeground((Drawable) null);
            }
        }
        this.mWindow.setContentView(subDecor);
        new ContentFrameLayout.OnAttachListener(this) {
            final /* synthetic */ AppCompatDelegateImpl this$0;

            {
                this.this$0 = this$0;
            }

            public void onAttachedFromWindow() {
            }

            public void onDetachedFromWindow() {
                this.this$0.dismissPopups();
            }
        };
        contentView.setAttachListener(onAttachListener);
        return subDecor;
    }

    /* access modifiers changed from: package-private */
    public void onSubDecorInstalled(ViewGroup subDecor) {
    }

    private void applyFixedSizeWindow() {
        ContentFrameLayout cfl = (ContentFrameLayout) this.mSubDecor.findViewById(16908290);
        View windowDecor = this.mWindow.getDecorView();
        cfl.setDecorPadding(windowDecor.getPaddingLeft(), windowDecor.getPaddingTop(), windowDecor.getPaddingRight(), windowDecor.getPaddingBottom());
        TypedArray a = this.mContext.obtainStyledAttributes(C0425R.styleable.AppCompatTheme);
        boolean value = a.getValue(C0425R.styleable.AppCompatTheme_windowMinWidthMajor, cfl.getMinWidthMajor());
        boolean value2 = a.getValue(C0425R.styleable.AppCompatTheme_windowMinWidthMinor, cfl.getMinWidthMinor());
        if (a.hasValue(C0425R.styleable.AppCompatTheme_windowFixedWidthMajor)) {
            boolean value3 = a.getValue(C0425R.styleable.AppCompatTheme_windowFixedWidthMajor, cfl.getFixedWidthMajor());
        }
        if (a.hasValue(C0425R.styleable.AppCompatTheme_windowFixedWidthMinor)) {
            boolean value4 = a.getValue(C0425R.styleable.AppCompatTheme_windowFixedWidthMinor, cfl.getFixedWidthMinor());
        }
        if (a.hasValue(C0425R.styleable.AppCompatTheme_windowFixedHeightMajor)) {
            boolean value5 = a.getValue(C0425R.styleable.AppCompatTheme_windowFixedHeightMajor, cfl.getFixedHeightMajor());
        }
        if (a.hasValue(C0425R.styleable.AppCompatTheme_windowFixedHeightMinor)) {
            boolean value6 = a.getValue(C0425R.styleable.AppCompatTheme_windowFixedHeightMinor, cfl.getFixedHeightMinor());
        }
        a.recycle();
        cfl.requestLayout();
    }

    public boolean requestWindowFeature(int featureId) {
        int featureId2 = sanitizeWindowFeatureId(featureId);
        if (this.mWindowNoTitle && featureId2 == 108) {
            return false;
        }
        if (this.mHasActionBar && featureId2 == 1) {
            this.mHasActionBar = false;
        }
        switch (featureId2) {
            case 1:
                throwFeatureRequestIfSubDecorInstalled();
                this.mWindowNoTitle = true;
                return true;
            case 2:
                throwFeatureRequestIfSubDecorInstalled();
                this.mFeatureProgress = true;
                return true;
            case 5:
                throwFeatureRequestIfSubDecorInstalled();
                this.mFeatureIndeterminateProgress = true;
                return true;
            case 10:
                throwFeatureRequestIfSubDecorInstalled();
                this.mOverlayActionMode = true;
                return true;
            case 108:
                throwFeatureRequestIfSubDecorInstalled();
                this.mHasActionBar = true;
                return true;
            case 109:
                throwFeatureRequestIfSubDecorInstalled();
                this.mOverlayActionBar = true;
                return true;
            default:
                return this.mWindow.requestFeature(featureId2);
        }
    }

    public boolean hasWindowFeature(int i) {
        int featureId = i;
        boolean result = false;
        switch (sanitizeWindowFeatureId(featureId)) {
            case 1:
                result = this.mWindowNoTitle;
                break;
            case 2:
                result = this.mFeatureProgress;
                break;
            case 5:
                result = this.mFeatureIndeterminateProgress;
                break;
            case 10:
                result = this.mOverlayActionMode;
                break;
            case 108:
                result = this.mHasActionBar;
                break;
            case 109:
                result = this.mOverlayActionBar;
                break;
        }
        return result || this.mWindow.hasFeature(featureId);
    }

    public final void setTitle(CharSequence charSequence) {
        CharSequence title = charSequence;
        this.mTitle = title;
        if (this.mDecorContentParent != null) {
            this.mDecorContentParent.setWindowTitle(title);
        } else if (peekSupportActionBar() != null) {
            peekSupportActionBar().setWindowTitle(title);
        } else if (this.mTitleView != null) {
            this.mTitleView.setText(title);
        }
    }

    /* access modifiers changed from: package-private */
    public final CharSequence getTitle() {
        if (this.mOriginalWindowCallback instanceof Activity) {
            return ((Activity) this.mOriginalWindowCallback).getTitle();
        }
        return this.mTitle;
    }

    /* access modifiers changed from: package-private */
    public void onPanelClosed(int i) {
        int featureId = i;
        if (featureId == 108) {
            ActionBar ab = getSupportActionBar();
            if (ab != null) {
                ab.dispatchMenuVisibilityChanged(false);
            }
        } else if (featureId == 0) {
            PanelFeatureState st = getPanelState(featureId, true);
            if (st.isOpen) {
                closePanel(st, false);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onMenuOpened(int featureId) {
        ActionBar ab;
        if (featureId == 108 && (ab = getSupportActionBar()) != null) {
            ab.dispatchMenuVisibilityChanged(true);
        }
    }

    public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        PanelFeatureState panel;
        MenuBuilder menu = menuBuilder;
        MenuItem item = menuItem;
        Window.Callback cb = getWindowCallback();
        if (cb == null || this.mIsDestroyed || (panel = findMenuPanel(menu.getRootMenu())) == null) {
            return false;
        }
        return cb.onMenuItemSelected(panel.featureId, item);
    }

    public void onMenuModeChange(MenuBuilder menu) {
        reopenMenu(menu, true);
    }

    public ActionMode startSupportActionMode(@NonNull ActionMode.Callback callback) {
        ActionMode.Callback callback2;
        Throwable th;
        ActionMode.Callback callback3 = callback;
        if (callback3 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("ActionMode callback can not be null.");
            throw th2;
        }
        if (this.mActionMode != null) {
            this.mActionMode.finish();
        }
        new ActionModeCallbackWrapperV9(this, callback3);
        ActionMode.Callback wrappedCallback = callback2;
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            this.mActionMode = ab.startActionMode(wrappedCallback);
            if (!(this.mActionMode == null || this.mAppCompatCallback == null)) {
                this.mAppCompatCallback.onSupportActionModeStarted(this.mActionMode);
            }
        }
        if (this.mActionMode == null) {
            this.mActionMode = startSupportActionModeFromWindow(wrappedCallback);
        }
        return this.mActionMode;
    }

    public void invalidateOptionsMenu() {
        ActionBar ab = getSupportActionBar();
        if (ab == null || !ab.invalidateOptionsMenu()) {
            invalidatePanelMenu(0);
        }
    }

    /* access modifiers changed from: package-private */
    public ActionMode startSupportActionModeFromWindow(@NonNull ActionMode.Callback callback) {
        ViewPropertyAnimatorListener viewPropertyAnimatorListener;
        TypedValue typedValue;
        Context actionBarContext;
        ActionBarContextView actionBarContextView;
        PopupWindow popupWindow;
        Runnable runnable;
        Context context;
        ActionMode.Callback callback2;
        ActionMode.Callback callback3 = callback;
        endOnGoingFadeAnimation();
        if (this.mActionMode != null) {
            this.mActionMode.finish();
        }
        if (!(callback3 instanceof ActionModeCallbackWrapperV9)) {
            new ActionModeCallbackWrapperV9(this, callback3);
            callback3 = callback2;
        }
        ActionMode mode = null;
        if (this.mAppCompatCallback != null && !this.mIsDestroyed) {
            try {
                mode = this.mAppCompatCallback.onWindowStartingSupportActionMode(callback3);
            } catch (AbstractMethodError e) {
                AbstractMethodError abstractMethodError = e;
            }
        }
        if (mode != null) {
            this.mActionMode = mode;
        } else {
            if (this.mActionModeView == null) {
                if (this.mIsFloating) {
                    new TypedValue();
                    TypedValue outValue = typedValue;
                    Resources.Theme baseTheme = this.mContext.getTheme();
                    boolean resolveAttribute = baseTheme.resolveAttribute(C0425R.attr.actionBarTheme, outValue, true);
                    if (outValue.resourceId != 0) {
                        Resources.Theme actionBarTheme = this.mContext.getResources().newTheme();
                        actionBarTheme.setTo(baseTheme);
                        actionBarTheme.applyStyle(outValue.resourceId, true);
                        new ContextThemeWrapper(this.mContext, 0);
                        actionBarContext = context;
                        actionBarContext.getTheme().setTo(actionBarTheme);
                    } else {
                        actionBarContext = this.mContext;
                    }
                    new ActionBarContextView(actionBarContext);
                    this.mActionModeView = actionBarContextView;
                    new PopupWindow(actionBarContext, (AttributeSet) null, C0425R.attr.actionModePopupWindowStyle);
                    this.mActionModePopup = popupWindow;
                    PopupWindowCompat.setWindowLayoutType(this.mActionModePopup, 2);
                    this.mActionModePopup.setContentView(this.mActionModeView);
                    this.mActionModePopup.setWidth(-1);
                    boolean resolveAttribute2 = actionBarContext.getTheme().resolveAttribute(C0425R.attr.actionBarSize, outValue, true);
                    this.mActionModeView.setContentHeight(TypedValue.complexToDimensionPixelSize(outValue.data, actionBarContext.getResources().getDisplayMetrics()));
                    this.mActionModePopup.setHeight(-2);
                    new Runnable(this) {
                        final /* synthetic */ AppCompatDelegateImpl this$0;

                        {
                            this.this$0 = this$0;
                        }

                        public void run() {
                            ViewPropertyAnimatorListener viewPropertyAnimatorListener;
                            this.this$0.mActionModePopup.showAtLocation(this.this$0.mActionModeView, 55, 0, 0);
                            this.this$0.endOnGoingFadeAnimation();
                            if (this.this$0.shouldAnimateActionModeView()) {
                                this.this$0.mActionModeView.setAlpha(0.0f);
                                this.this$0.mFadeAnim = ViewCompat.animate(this.this$0.mActionModeView).alpha(1.0f);
                                new ViewPropertyAnimatorListenerAdapter(this) {
                                    final /* synthetic */ C04136 this$1;

                                    {
                                        this.this$1 = this$1;
                                    }

                                    public void onAnimationStart(View view) {
                                        View view2 = view;
                                        this.this$1.this$0.mActionModeView.setVisibility(0);
                                    }

                                    public void onAnimationEnd(View view) {
                                        View view2 = view;
                                        this.this$1.this$0.mActionModeView.setAlpha(1.0f);
                                        ViewPropertyAnimatorCompat listener = this.this$1.this$0.mFadeAnim.setListener((ViewPropertyAnimatorListener) null);
                                        this.this$1.this$0.mFadeAnim = null;
                                    }
                                };
                                ViewPropertyAnimatorCompat listener = this.this$0.mFadeAnim.setListener(viewPropertyAnimatorListener);
                                return;
                            }
                            this.this$0.mActionModeView.setAlpha(1.0f);
                            this.this$0.mActionModeView.setVisibility(0);
                        }
                    };
                    this.mShowActionModePopup = runnable;
                } else {
                    ViewStubCompat stub = (ViewStubCompat) this.mSubDecor.findViewById(C0425R.C0427id.action_mode_bar_stub);
                    if (stub != null) {
                        stub.setLayoutInflater(LayoutInflater.from(getActionBarThemedContext()));
                        this.mActionModeView = (ActionBarContextView) stub.inflate();
                    }
                }
            }
            if (this.mActionModeView != null) {
                endOnGoingFadeAnimation();
                this.mActionModeView.killMode();
                ActionMode actionMode = r13;
                StandaloneActionMode standaloneActionMode = new StandaloneActionMode(this.mActionModeView.getContext(), this.mActionModeView, callback3, this.mActionModePopup == null);
                ActionMode mode2 = actionMode;
                if (callback3.onCreateActionMode(mode2, mode2.getMenu())) {
                    mode2.invalidate();
                    this.mActionModeView.initForMode(mode2);
                    this.mActionMode = mode2;
                    if (shouldAnimateActionModeView()) {
                        this.mActionModeView.setAlpha(0.0f);
                        this.mFadeAnim = ViewCompat.animate(this.mActionModeView).alpha(1.0f);
                        new ViewPropertyAnimatorListenerAdapter(this) {
                            final /* synthetic */ AppCompatDelegateImpl this$0;

                            {
                                this.this$0 = this$0;
                            }

                            public void onAnimationStart(View view) {
                                View view2 = view;
                                this.this$0.mActionModeView.setVisibility(0);
                                this.this$0.mActionModeView.sendAccessibilityEvent(32);
                                if (this.this$0.mActionModeView.getParent() instanceof View) {
                                    ViewCompat.requestApplyInsets((View) this.this$0.mActionModeView.getParent());
                                }
                            }

                            public void onAnimationEnd(View view) {
                                View view2 = view;
                                this.this$0.mActionModeView.setAlpha(1.0f);
                                ViewPropertyAnimatorCompat listener = this.this$0.mFadeAnim.setListener((ViewPropertyAnimatorListener) null);
                                this.this$0.mFadeAnim = null;
                            }
                        };
                        ViewPropertyAnimatorCompat listener = this.mFadeAnim.setListener(viewPropertyAnimatorListener);
                    } else {
                        this.mActionModeView.setAlpha(1.0f);
                        this.mActionModeView.setVisibility(0);
                        this.mActionModeView.sendAccessibilityEvent(32);
                        if (this.mActionModeView.getParent() instanceof View) {
                            ViewCompat.requestApplyInsets((View) this.mActionModeView.getParent());
                        }
                    }
                    if (this.mActionModePopup != null) {
                        boolean post = this.mWindow.getDecorView().post(this.mShowActionModePopup);
                    }
                } else {
                    this.mActionMode = null;
                }
            }
        }
        if (!(this.mActionMode == null || this.mAppCompatCallback == null)) {
            this.mAppCompatCallback.onSupportActionModeStarted(this.mActionMode);
        }
        return this.mActionMode;
    }

    /* access modifiers changed from: package-private */
    public final boolean shouldAnimateActionModeView() {
        return this.mSubDecorInstalled && this.mSubDecor != null && ViewCompat.isLaidOut(this.mSubDecor);
    }

    public void setHandleNativeActionModesEnabled(boolean enabled) {
        boolean z = enabled;
        this.mHandleNativeActionModes = z;
    }

    public boolean isHandleNativeActionModesEnabled() {
        return this.mHandleNativeActionModes;
    }

    /* access modifiers changed from: package-private */
    public void endOnGoingFadeAnimation() {
        if (this.mFadeAnim != null) {
            this.mFadeAnim.cancel();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean onBackPressed() {
        if (this.mActionMode != null) {
            this.mActionMode.finish();
            return true;
        }
        ActionBar ab = getSupportActionBar();
        if (ab == null || !ab.collapseActionView()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean onKeyShortcut(int i, KeyEvent keyEvent) {
        int keyCode = i;
        KeyEvent ev = keyEvent;
        ActionBar ab = getSupportActionBar();
        if (ab != null && ab.onKeyShortcut(keyCode, ev)) {
            return true;
        }
        if (this.mPreparedPanel == null || !performPanelShortcut(this.mPreparedPanel, ev.getKeyCode(), ev, 1)) {
            if (this.mPreparedPanel == null) {
                PanelFeatureState st = getPanelState(0, true);
                boolean preparePanel = preparePanel(st, ev);
                boolean handled = performPanelShortcut(st, ev.getKeyCode(), ev, 1);
                st.isPrepared = false;
                if (handled) {
                    return true;
                }
            }
            return false;
        }
        if (this.mPreparedPanel != null) {
            this.mPreparedPanel.isHandled = true;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View root;
        KeyEvent event = keyEvent;
        if (((this.mOriginalWindowCallback instanceof KeyEventDispatcher.Component) || (this.mOriginalWindowCallback instanceof AppCompatDialog)) && (root = this.mWindow.getDecorView()) != null && KeyEventDispatcher.dispatchBeforeHierarchy(root, event)) {
            return true;
        }
        if (event.getKeyCode() == 82 && this.mOriginalWindowCallback.dispatchKeyEvent(event)) {
            return true;
        }
        int keyCode = event.getKeyCode();
        return event.getAction() == 0 ? onKeyDown(keyCode, event) : onKeyUp(keyCode, event);
    }

    /* access modifiers changed from: package-private */
    public boolean onKeyUp(int keyCode, KeyEvent keyEvent) {
        KeyEvent event = keyEvent;
        switch (keyCode) {
            case 4:
                boolean wasLongPressBackDown = this.mLongPressBackDown;
                this.mLongPressBackDown = false;
                PanelFeatureState st = getPanelState(0, false);
                if (st != null && st.isOpen) {
                    if (!wasLongPressBackDown) {
                        closePanel(st, true);
                    }
                    return true;
                } else if (onBackPressed()) {
                    return true;
                }
                break;
            case 82:
                boolean onKeyUpPanel = onKeyUpPanel(0, event);
                return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        KeyEvent event = keyEvent;
        switch (keyCode) {
            case 4:
                this.mLongPressBackDown = (event.getFlags() & 128) != 0;
                break;
            case 82:
                boolean onKeyDownPanel = onKeyDownPanel(0, event);
                return true;
        }
        return false;
    }

    public View createView(View view, String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        boolean shouldInheritContext;
        AppCompatViewInflater appCompatViewInflater;
        StringBuilder sb;
        AppCompatViewInflater appCompatViewInflater2;
        View parent = view;
        String name = str;
        Context context2 = context;
        AttributeSet attrs = attributeSet;
        if (this.mAppCompatViewInflater == null) {
            String viewInflaterClassName = this.mContext.obtainStyledAttributes(C0425R.styleable.AppCompatTheme).getString(C0425R.styleable.AppCompatTheme_viewInflaterClass);
            if (viewInflaterClassName == null || AppCompatViewInflater.class.getName().equals(viewInflaterClassName)) {
                new AppCompatViewInflater();
                this.mAppCompatViewInflater = appCompatViewInflater;
            } else {
                try {
                    this.mAppCompatViewInflater = (AppCompatViewInflater) Class.forName(viewInflaterClassName).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    new StringBuilder();
                    int i = Log.i("AppCompatDelegate", sb.append("Failed to instantiate custom view inflater ").append(viewInflaterClassName).append(". Falling back to default.").toString(), th);
                    new AppCompatViewInflater();
                    this.mAppCompatViewInflater = appCompatViewInflater2;
                }
            }
        }
        boolean inheritContext = false;
        if (IS_PRE_LOLLIPOP) {
            if (attrs instanceof XmlPullParser) {
                shouldInheritContext = ((XmlPullParser) attrs).getDepth() > 1;
            } else {
                shouldInheritContext = shouldInheritContext((ViewParent) parent);
            }
            inheritContext = shouldInheritContext;
        }
        return this.mAppCompatViewInflater.createView(parent, name, context2, attrs, inheritContext, IS_PRE_LOLLIPOP, true, VectorEnabledTintResources.shouldBeUsed());
    }

    private boolean shouldInheritContext(ViewParent viewParent) {
        ViewParent parent = viewParent;
        if (parent == null) {
            return false;
        }
        Object decorView = this.mWindow.getDecorView();
        while (parent != null) {
            if (parent == decorView || !(parent instanceof View) || ViewCompat.isAttachedToWindow((View) parent)) {
                return false;
            }
            parent = parent.getParent();
        }
        return true;
    }

    public void installViewFactory() {
        LayoutInflater layoutInflater = LayoutInflater.from(this.mContext);
        if (layoutInflater.getFactory() == null) {
            LayoutInflaterCompat.setFactory2(layoutInflater, this);
        } else if (!(layoutInflater.getFactory2() instanceof AppCompatDelegateImpl)) {
            int i = Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    public final View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return createView(parent, name, context, attrs);
    }

    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return onCreateView((View) null, name, context, attrs);
    }

    private void openPanel(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        WindowManager.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2;
        ViewGroup.LayoutParams lp;
        PanelFeatureState st = panelFeatureState;
        KeyEvent event = keyEvent;
        if (!st.isOpen && !this.mIsDestroyed) {
            if (st.featureId == 0) {
                if ((this.mContext.getResources().getConfiguration().screenLayout & 15) == 4) {
                    return;
                }
            }
            Window.Callback cb = getWindowCallback();
            if (cb == null || cb.onMenuOpened(st.featureId, st.menu)) {
                WindowManager wm = (WindowManager) this.mContext.getSystemService("window");
                if (wm != null && preparePanel(st, event)) {
                    int width = -2;
                    if (st.decorView == null || st.refreshDecorView) {
                        if (st.decorView == null) {
                            if (!initializePanelDecor(st) || st.decorView == null) {
                                return;
                            }
                        } else if (st.refreshDecorView && st.decorView.getChildCount() > 0) {
                            st.decorView.removeAllViews();
                        }
                        if (initializePanelContent(st) && st.hasPanelItems()) {
                            ViewGroup.LayoutParams lp2 = st.shownPanelView.getLayoutParams();
                            if (lp2 == null) {
                                new ViewGroup.LayoutParams(-2, -2);
                                lp2 = layoutParams2;
                            }
                            st.decorView.setBackgroundResource(st.background);
                            ViewParent shownPanelParent = st.shownPanelView.getParent();
                            if (shownPanelParent != null && (shownPanelParent instanceof ViewGroup)) {
                                ((ViewGroup) shownPanelParent).removeView(st.shownPanelView);
                            }
                            st.decorView.addView(st.shownPanelView, lp2);
                            if (!st.shownPanelView.hasFocus()) {
                                boolean requestFocus = st.shownPanelView.requestFocus();
                            }
                        } else {
                            return;
                        }
                    } else if (!(st.createdPanelView == null || (lp = st.createdPanelView.getLayoutParams()) == null || lp.width != -1)) {
                        width = -1;
                    }
                    st.isHandled = false;
                    new WindowManager.LayoutParams(width, -2, st.f32x, st.f33y, 1002, 8519680, -3);
                    WindowManager.LayoutParams lp3 = layoutParams;
                    lp3.gravity = st.gravity;
                    lp3.windowAnimations = st.windowAnimations;
                    wm.addView(st.decorView, lp3);
                    st.isOpen = true;
                    return;
                }
                return;
            }
            closePanel(st, true);
        }
    }

    private boolean initializePanelDecor(PanelFeatureState panelFeatureState) {
        ViewGroup viewGroup;
        PanelFeatureState st = panelFeatureState;
        st.setStyle(getActionBarThemedContext());
        new ListMenuDecorView(this, st.listPresenterContext);
        st.decorView = viewGroup;
        st.gravity = 81;
        return true;
    }

    private void reopenMenu(MenuBuilder menuBuilder, boolean z) {
        MenuBuilder menuBuilder2 = menuBuilder;
        boolean toggleMenuMode = z;
        if (this.mDecorContentParent == null || !this.mDecorContentParent.canShowOverflowMenu() || (ViewConfiguration.get(this.mContext).hasPermanentMenuKey() && !this.mDecorContentParent.isOverflowMenuShowPending())) {
            PanelFeatureState st = getPanelState(0, true);
            st.refreshDecorView = true;
            closePanel(st, false);
            openPanel(st, (KeyEvent) null);
            return;
        }
        Window.Callback cb = getWindowCallback();
        if (this.mDecorContentParent.isOverflowMenuShowing() && toggleMenuMode) {
            boolean hideOverflowMenu = this.mDecorContentParent.hideOverflowMenu();
            if (!this.mIsDestroyed) {
                cb.onPanelClosed(108, getPanelState(0, true).menu);
            }
        } else if (cb != null && !this.mIsDestroyed) {
            if (this.mInvalidatePanelMenuPosted && (this.mInvalidatePanelMenuFeatures & 1) != 0) {
                boolean removeCallbacks = this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
                this.mInvalidatePanelMenuRunnable.run();
            }
            PanelFeatureState st2 = getPanelState(0, true);
            if (st2.menu != null && !st2.refreshMenuContent && cb.onPreparePanel(0, st2.createdPanelView, st2.menu)) {
                boolean onMenuOpened = cb.onMenuOpened(108, st2.menu);
                boolean showOverflowMenu = this.mDecorContentParent.showOverflowMenu();
            }
        }
    }

    private boolean initializePanelMenu(PanelFeatureState panelFeatureState) {
        MenuBuilder menuBuilder;
        TypedValue typedValue;
        Context context;
        PanelFeatureState st = panelFeatureState;
        Context context2 = this.mContext;
        if ((st.featureId == 0 || st.featureId == 108) && this.mDecorContentParent != null) {
            new TypedValue();
            TypedValue outValue = typedValue;
            Resources.Theme baseTheme = context2.getTheme();
            boolean resolveAttribute = baseTheme.resolveAttribute(C0425R.attr.actionBarTheme, outValue, true);
            Resources.Theme widgetTheme = null;
            if (outValue.resourceId != 0) {
                widgetTheme = context2.getResources().newTheme();
                widgetTheme.setTo(baseTheme);
                widgetTheme.applyStyle(outValue.resourceId, true);
                boolean resolveAttribute2 = widgetTheme.resolveAttribute(C0425R.attr.actionBarWidgetTheme, outValue, true);
            } else {
                boolean resolveAttribute3 = baseTheme.resolveAttribute(C0425R.attr.actionBarWidgetTheme, outValue, true);
            }
            if (outValue.resourceId != 0) {
                if (widgetTheme == null) {
                    widgetTheme = context2.getResources().newTheme();
                    widgetTheme.setTo(baseTheme);
                }
                widgetTheme.applyStyle(outValue.resourceId, true);
            }
            if (widgetTheme != null) {
                new ContextThemeWrapper(context2, 0);
                context2 = context;
                context2.getTheme().setTo(widgetTheme);
            }
        }
        new MenuBuilder(context2);
        MenuBuilder menu = menuBuilder;
        menu.setCallback(this);
        st.setMenu(menu);
        return true;
    }

    private boolean initializePanelContent(PanelFeatureState panelFeatureState) {
        PanelMenuPresenterCallback panelMenuPresenterCallback;
        PanelFeatureState st = panelFeatureState;
        if (st.createdPanelView != null) {
            st.shownPanelView = st.createdPanelView;
            return true;
        } else if (st.menu == null) {
            return false;
        } else {
            if (this.mPanelMenuPresenterCallback == null) {
                new PanelMenuPresenterCallback(this);
                this.mPanelMenuPresenterCallback = panelMenuPresenterCallback;
            }
            st.shownPanelView = (View) st.getListMenuView(this.mPanelMenuPresenterCallback);
            return st.shownPanelView != null;
        }
    }

    private boolean preparePanel(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        ActionMenuPresenterCallback actionMenuPresenterCallback;
        PanelFeatureState st = panelFeatureState;
        KeyEvent event = keyEvent;
        if (this.mIsDestroyed) {
            return false;
        }
        if (st.isPrepared) {
            return true;
        }
        if (!(this.mPreparedPanel == null || this.mPreparedPanel == st)) {
            closePanel(this.mPreparedPanel, false);
        }
        Window.Callback cb = getWindowCallback();
        if (cb != null) {
            st.createdPanelView = cb.onCreatePanelView(st.featureId);
        }
        boolean isActionBarMenu = st.featureId == 0 || st.featureId == 108;
        if (isActionBarMenu && this.mDecorContentParent != null) {
            this.mDecorContentParent.setMenuPrepared();
        }
        if (st.createdPanelView == null && (!isActionBarMenu || !(peekSupportActionBar() instanceof ToolbarActionBar))) {
            if (st.menu == null || st.refreshMenuContent) {
                if (st.menu == null && (!initializePanelMenu(st) || st.menu == null)) {
                    return false;
                }
                if (isActionBarMenu && this.mDecorContentParent != null) {
                    if (this.mActionMenuPresenterCallback == null) {
                        new ActionMenuPresenterCallback(this);
                        this.mActionMenuPresenterCallback = actionMenuPresenterCallback;
                    }
                    this.mDecorContentParent.setMenu(st.menu, this.mActionMenuPresenterCallback);
                }
                st.menu.stopDispatchingItemsChanged();
                if (!cb.onCreatePanelMenu(st.featureId, st.menu)) {
                    st.setMenu((MenuBuilder) null);
                    if (isActionBarMenu && this.mDecorContentParent != null) {
                        this.mDecorContentParent.setMenu((Menu) null, this.mActionMenuPresenterCallback);
                    }
                    return false;
                }
                st.refreshMenuContent = false;
            }
            st.menu.stopDispatchingItemsChanged();
            if (st.frozenActionViewState != null) {
                st.menu.restoreActionViewStates(st.frozenActionViewState);
                st.frozenActionViewState = null;
            }
            if (!cb.onPreparePanel(0, st.createdPanelView, st.menu)) {
                if (isActionBarMenu && this.mDecorContentParent != null) {
                    this.mDecorContentParent.setMenu((Menu) null, this.mActionMenuPresenterCallback);
                }
                st.menu.startDispatchingItemsChanged();
                return false;
            }
            st.qwertyMode = KeyCharacterMap.load(event != null ? event.getDeviceId() : -1).getKeyboardType() != 1;
            st.menu.setQwertyMode(st.qwertyMode);
            st.menu.startDispatchingItemsChanged();
        }
        st.isPrepared = true;
        st.isHandled = false;
        this.mPreparedPanel = st;
        return true;
    }

    /* access modifiers changed from: package-private */
    public void checkCloseActionMenu(MenuBuilder menuBuilder) {
        MenuBuilder menu = menuBuilder;
        if (!this.mClosingActionMenu) {
            this.mClosingActionMenu = true;
            this.mDecorContentParent.dismissPopups();
            Window.Callback cb = getWindowCallback();
            if (cb != null && !this.mIsDestroyed) {
                cb.onPanelClosed(108, menu);
            }
            this.mClosingActionMenu = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void closePanel(int featureId) {
        closePanel(getPanelState(featureId, true), true);
    }

    /* access modifiers changed from: package-private */
    public void closePanel(PanelFeatureState panelFeatureState, boolean z) {
        PanelFeatureState st = panelFeatureState;
        boolean doCallback = z;
        if (!doCallback || st.featureId != 0 || this.mDecorContentParent == null || !this.mDecorContentParent.isOverflowMenuShowing()) {
            WindowManager wm = (WindowManager) this.mContext.getSystemService("window");
            if (!(wm == null || !st.isOpen || st.decorView == null)) {
                wm.removeView(st.decorView);
                if (doCallback) {
                    callOnPanelClosed(st.featureId, st, (Menu) null);
                }
            }
            st.isPrepared = false;
            st.isHandled = false;
            st.isOpen = false;
            st.shownPanelView = null;
            st.refreshDecorView = true;
            if (this.mPreparedPanel == st) {
                this.mPreparedPanel = null;
                return;
            }
            return;
        }
        checkCloseActionMenu(st.menu);
    }

    private boolean onKeyDownPanel(int i, KeyEvent keyEvent) {
        int featureId = i;
        KeyEvent event = keyEvent;
        if (event.getRepeatCount() == 0) {
            PanelFeatureState st = getPanelState(featureId, true);
            if (!st.isOpen) {
                return preparePanel(st, event);
            }
        }
        return false;
    }

    private boolean onKeyUpPanel(int i, KeyEvent keyEvent) {
        int featureId = i;
        KeyEvent event = keyEvent;
        if (this.mActionMode != null) {
            return false;
        }
        boolean handled = false;
        PanelFeatureState st = getPanelState(featureId, true);
        if (featureId != 0 || this.mDecorContentParent == null || !this.mDecorContentParent.canShowOverflowMenu() || ViewConfiguration.get(this.mContext).hasPermanentMenuKey()) {
            if (st.isOpen || st.isHandled) {
                handled = st.isOpen;
                closePanel(st, true);
            } else if (st.isPrepared) {
                boolean show = true;
                if (st.refreshMenuContent) {
                    st.isPrepared = false;
                    show = preparePanel(st, event);
                }
                if (show) {
                    openPanel(st, event);
                    handled = true;
                }
            }
        } else if (this.mDecorContentParent.isOverflowMenuShowing()) {
            handled = this.mDecorContentParent.hideOverflowMenu();
        } else if (!this.mIsDestroyed && preparePanel(st, event)) {
            handled = this.mDecorContentParent.showOverflowMenu();
        }
        if (handled) {
            AudioManager audioManager = (AudioManager) this.mContext.getSystemService("audio");
            if (audioManager != null) {
                audioManager.playSoundEffect(0);
            } else {
                int w = Log.w("AppCompatDelegate", "Couldn't get audio manager");
            }
        }
        return handled;
    }

    /* access modifiers changed from: package-private */
    public void callOnPanelClosed(int i, PanelFeatureState panelFeatureState, Menu menu) {
        int featureId = i;
        PanelFeatureState panel = panelFeatureState;
        Menu menu2 = menu;
        if (menu2 == null) {
            if (panel == null && featureId >= 0 && featureId < this.mPanels.length) {
                panel = this.mPanels[featureId];
            }
            if (panel != null) {
                menu2 = panel.menu;
            }
        }
        if ((panel == null || panel.isOpen) && !this.mIsDestroyed) {
            this.mOriginalWindowCallback.onPanelClosed(featureId, menu2);
        }
    }

    /* access modifiers changed from: package-private */
    public PanelFeatureState findMenuPanel(Menu menu) {
        Menu menu2 = menu;
        PanelFeatureState[] panels = this.mPanels;
        int N = panels != null ? panels.length : 0;
        for (int i = 0; i < N; i++) {
            PanelFeatureState panel = panels[i];
            if (panel != null && panel.menu == menu2) {
                return panel;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public PanelFeatureState getPanelState(int i, boolean z) {
        PanelFeatureState panelFeatureState;
        int featureId = i;
        boolean z2 = z;
        PanelFeatureState[] panelFeatureStateArr = this.mPanels;
        PanelFeatureState[] ar = panelFeatureStateArr;
        if (panelFeatureStateArr == null || ar.length <= featureId) {
            PanelFeatureState[] nar = new PanelFeatureState[(featureId + 1)];
            if (ar != null) {
                System.arraycopy(ar, 0, nar, 0, ar.length);
            }
            PanelFeatureState[] panelFeatureStateArr2 = nar;
            ar = panelFeatureStateArr2;
            this.mPanels = panelFeatureStateArr2;
        }
        PanelFeatureState st = ar[featureId];
        if (st == null) {
            new PanelFeatureState(featureId);
            PanelFeatureState panelFeatureState2 = panelFeatureState;
            st = panelFeatureState2;
            ar[featureId] = panelFeatureState2;
        }
        return st;
    }

    private boolean performPanelShortcut(PanelFeatureState panelFeatureState, int i, KeyEvent keyEvent, int i2) {
        PanelFeatureState st = panelFeatureState;
        int keyCode = i;
        KeyEvent event = keyEvent;
        int flags = i2;
        if (event.isSystem()) {
            return false;
        }
        boolean handled = false;
        if ((st.isPrepared || preparePanel(st, event)) && st.menu != null) {
            handled = st.menu.performShortcut(keyCode, event, flags);
        }
        if (handled && (flags & 1) == 0 && this.mDecorContentParent == null) {
            closePanel(st, true);
        }
        return handled;
    }

    private void invalidatePanelMenu(int featureId) {
        this.mInvalidatePanelMenuFeatures |= 1 << featureId;
        if (!this.mInvalidatePanelMenuPosted) {
            ViewCompat.postOnAnimation(this.mWindow.getDecorView(), this.mInvalidatePanelMenuRunnable);
            this.mInvalidatePanelMenuPosted = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void doInvalidatePanelMenu(int i) {
        PanelFeatureState st;
        Bundle bundle;
        int featureId = i;
        PanelFeatureState st2 = getPanelState(featureId, true);
        if (st2.menu != null) {
            new Bundle();
            Bundle savedActionViewStates = bundle;
            st2.menu.saveActionViewStates(savedActionViewStates);
            if (savedActionViewStates.size() > 0) {
                st2.frozenActionViewState = savedActionViewStates;
            }
            st2.menu.stopDispatchingItemsChanged();
            st2.menu.clear();
        }
        st2.refreshMenuContent = true;
        st2.refreshDecorView = true;
        if ((featureId == 108 || featureId == 0) && this.mDecorContentParent != null && (st = getPanelState(0, false)) != null) {
            st.isPrepared = false;
            boolean preparePanel = preparePanel(st, (KeyEvent) null);
        }
    }

    /* access modifiers changed from: package-private */
    public int updateStatusGuard(int i) {
        View view;
        ViewGroup.LayoutParams layoutParams;
        Rect rect;
        Rect rect2;
        int insetTop = i;
        boolean showStatusGuard = false;
        if (this.mActionModeView != null && (this.mActionModeView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) this.mActionModeView.getLayoutParams();
            boolean mlpChanged = false;
            if (this.mActionModeView.isShown()) {
                if (this.mTempRect1 == null) {
                    new Rect();
                    this.mTempRect1 = rect;
                    new Rect();
                    this.mTempRect2 = rect2;
                }
                Rect insets = this.mTempRect1;
                Rect localInsets = this.mTempRect2;
                insets.set(0, insetTop, 0, 0);
                ViewUtils.computeFitSystemWindows(this.mSubDecor, insets, localInsets);
                if (mlp.topMargin != (localInsets.top == 0 ? insetTop : 0)) {
                    mlpChanged = true;
                    mlp.topMargin = insetTop;
                    if (this.mStatusGuard == null) {
                        new View(this.mContext);
                        this.mStatusGuard = view;
                        this.mStatusGuard.setBackgroundColor(this.mContext.getResources().getColor(C0425R.color.abc_input_method_navigation_guard));
                        new ViewGroup.LayoutParams(-1, insetTop);
                        this.mSubDecor.addView(this.mStatusGuard, -1, layoutParams);
                    } else {
                        ViewGroup.LayoutParams lp = this.mStatusGuard.getLayoutParams();
                        if (lp.height != insetTop) {
                            lp.height = insetTop;
                            this.mStatusGuard.setLayoutParams(lp);
                        }
                    }
                }
                showStatusGuard = this.mStatusGuard != null;
                if (!this.mOverlayActionMode && showStatusGuard) {
                    insetTop = 0;
                }
            } else if (mlp.topMargin != 0) {
                mlpChanged = true;
                mlp.topMargin = 0;
            }
            if (mlpChanged) {
                this.mActionModeView.setLayoutParams(mlp);
            }
        }
        if (this.mStatusGuard != null) {
            this.mStatusGuard.setVisibility(showStatusGuard ? 0 : 8);
        }
        return insetTop;
    }

    private void throwFeatureRequestIfSubDecorInstalled() {
        Throwable th;
        if (this.mSubDecorInstalled) {
            Throwable th2 = th;
            new AndroidRuntimeException("Window feature must be requested before adding content");
            throw th2;
        }
    }

    private int sanitizeWindowFeatureId(int i) {
        int featureId = i;
        if (featureId == 8) {
            int i2 = Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return 108;
        } else if (featureId != 9) {
            return featureId;
        } else {
            int i3 = Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            return 109;
        }
    }

    /* access modifiers changed from: package-private */
    public ViewGroup getSubDecor() {
        return this.mSubDecor;
    }

    /* access modifiers changed from: package-private */
    public void dismissPopups() {
        if (this.mDecorContentParent != null) {
            this.mDecorContentParent.dismissPopups();
        }
        if (this.mActionModePopup != null) {
            boolean removeCallbacks = this.mWindow.getDecorView().removeCallbacks(this.mShowActionModePopup);
            if (this.mActionModePopup.isShowing()) {
                try {
                    this.mActionModePopup.dismiss();
                } catch (IllegalArgumentException e) {
                    IllegalArgumentException illegalArgumentException = e;
                }
            }
            this.mActionModePopup = null;
        }
        endOnGoingFadeAnimation();
        PanelFeatureState st = getPanelState(0, false);
        if (st != null && st.menu != null) {
            st.menu.close();
        }
    }

    public boolean applyDayNight() {
        boolean applied = false;
        int nightMode = getNightMode();
        int modeToApply = mapNightMode(nightMode);
        if (modeToApply != -1) {
            applied = updateForNightMode(modeToApply);
        }
        if (nightMode == 0) {
            ensureAutoNightModeManager();
            this.mAutoNightModeManager.setup();
        }
        this.mApplyDayNightCalled = true;
        return applied;
    }

    public void setLocalNightMode(int i) {
        int mode = i;
        switch (mode) {
            case -1:
            case 0:
            case 1:
            case 2:
                if (this.mLocalNightMode != mode) {
                    this.mLocalNightMode = mode;
                    if (this.mApplyDayNightCalled) {
                        boolean applyDayNight = applyDayNight();
                        return;
                    }
                    return;
                }
                return;
            default:
                int i2 = Log.i("AppCompatDelegate", "setLocalNightMode() called with an unknown mode");
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public int mapNightMode(int i) {
        int mode = i;
        switch (mode) {
            case -100:
                return -1;
            case 0:
                if (Build.VERSION.SDK_INT >= 23 && ((UiModeManager) this.mContext.getSystemService(UiModeManager.class)).getNightMode() == 0) {
                    return -1;
                }
                ensureAutoNightModeManager();
                return this.mAutoNightModeManager.getApplyableNightMode();
            default:
                return mode;
        }
    }

    private int getNightMode() {
        return this.mLocalNightMode != -100 ? this.mLocalNightMode : getDefaultNightMode();
    }

    private boolean updateForNightMode(int mode) {
        Configuration configuration;
        Resources res = this.mContext.getResources();
        Configuration conf = res.getConfiguration();
        int currentNightMode = conf.uiMode & 48;
        int newNightMode = mode == 2 ? 32 : 16;
        if (currentNightMode == newNightMode) {
            return false;
        }
        if (shouldRecreateOnNightModeChange()) {
            ((Activity) this.mContext).recreate();
        } else {
            new Configuration(conf);
            Configuration config = configuration;
            DisplayMetrics metrics = res.getDisplayMetrics();
            config.uiMode = newNightMode | (config.uiMode & -49);
            res.updateConfiguration(config, metrics);
            if (Build.VERSION.SDK_INT < 26) {
                ResourcesFlusher.flush(res);
            }
        }
        return true;
    }

    private void ensureAutoNightModeManager() {
        AutoNightModeManager autoNightModeManager;
        if (this.mAutoNightModeManager == null) {
            new AutoNightModeManager(this, TwilightManager.getInstance(this.mContext));
            this.mAutoNightModeManager = autoNightModeManager;
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final AutoNightModeManager getAutoNightModeManager() {
        ensureAutoNightModeManager();
        return this.mAutoNightModeManager;
    }

    private boolean shouldRecreateOnNightModeChange() {
        ComponentName componentName;
        if (!this.mApplyDayNightCalled || !(this.mContext instanceof Activity)) {
            return false;
        }
        PackageManager packageManager = this.mContext.getPackageManager();
        try {
            new ComponentName(this.mContext, this.mContext.getClass());
            return (packageManager.getActivityInfo(componentName, 0).configChanges & 512) == 0;
        } catch (PackageManager.NameNotFoundException e) {
            int d = Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e);
            return true;
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$ActionModeCallbackWrapperV9 */
    class ActionModeCallbackWrapperV9 implements ActionMode.Callback {
        private ActionMode.Callback mWrapped;
        final /* synthetic */ AppCompatDelegateImpl this$0;

        public ActionModeCallbackWrapperV9(AppCompatDelegateImpl this$02, ActionMode.Callback wrapped) {
            this.this$0 = this$02;
            this.mWrapped = wrapped;
        }

        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            return this.mWrapped.onCreateActionMode(mode, menu);
        }

        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return this.mWrapped.onPrepareActionMode(mode, menu);
        }

        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return this.mWrapped.onActionItemClicked(mode, item);
        }

        public void onDestroyActionMode(ActionMode mode) {
            ViewPropertyAnimatorListener viewPropertyAnimatorListener;
            this.mWrapped.onDestroyActionMode(mode);
            if (this.this$0.mActionModePopup != null) {
                boolean removeCallbacks = this.this$0.mWindow.getDecorView().removeCallbacks(this.this$0.mShowActionModePopup);
            }
            if (this.this$0.mActionModeView != null) {
                this.this$0.endOnGoingFadeAnimation();
                this.this$0.mFadeAnim = ViewCompat.animate(this.this$0.mActionModeView).alpha(0.0f);
                new ViewPropertyAnimatorListenerAdapter(this) {
                    final /* synthetic */ ActionModeCallbackWrapperV9 this$1;

                    {
                        this.this$1 = this$1;
                    }

                    public void onAnimationEnd(View view) {
                        View view2 = view;
                        this.this$1.this$0.mActionModeView.setVisibility(8);
                        if (this.this$1.this$0.mActionModePopup != null) {
                            this.this$1.this$0.mActionModePopup.dismiss();
                        } else if (this.this$1.this$0.mActionModeView.getParent() instanceof View) {
                            ViewCompat.requestApplyInsets((View) this.this$1.this$0.mActionModeView.getParent());
                        }
                        this.this$1.this$0.mActionModeView.removeAllViews();
                        ViewPropertyAnimatorCompat listener = this.this$1.this$0.mFadeAnim.setListener((ViewPropertyAnimatorListener) null);
                        this.this$1.this$0.mFadeAnim = null;
                    }
                };
                ViewPropertyAnimatorCompat listener = this.this$0.mFadeAnim.setListener(viewPropertyAnimatorListener);
            }
            if (this.this$0.mAppCompatCallback != null) {
                this.this$0.mAppCompatCallback.onSupportActionModeFinished(this.this$0.mActionMode);
            }
            this.this$0.mActionMode = null;
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$PanelMenuPresenterCallback */
    private final class PanelMenuPresenterCallback implements MenuPresenter.Callback {
        final /* synthetic */ AppCompatDelegateImpl this$0;

        PanelMenuPresenterCallback(AppCompatDelegateImpl appCompatDelegateImpl) {
            this.this$0 = appCompatDelegateImpl;
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            Menu menu = menuBuilder;
            boolean allMenusAreClosing = z;
            Menu parentMenu = menu.getRootMenu();
            boolean isSubMenu = parentMenu != menu;
            PanelFeatureState panel = this.this$0.findMenuPanel(isSubMenu ? parentMenu : menu);
            if (panel == null) {
                return;
            }
            if (isSubMenu) {
                this.this$0.callOnPanelClosed(panel.featureId, panel, parentMenu);
                this.this$0.closePanel(panel, true);
                return;
            }
            this.this$0.closePanel(panel, allMenusAreClosing);
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            Window.Callback cb;
            MenuBuilder subMenu = menuBuilder;
            if (subMenu == null && this.this$0.mHasActionBar && (cb = this.this$0.getWindowCallback()) != null && !this.this$0.mIsDestroyed) {
                boolean onMenuOpened = cb.onMenuOpened(108, subMenu);
            }
            return true;
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$ActionMenuPresenterCallback */
    private final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        final /* synthetic */ AppCompatDelegateImpl this$0;

        ActionMenuPresenterCallback(AppCompatDelegateImpl appCompatDelegateImpl) {
            this.this$0 = appCompatDelegateImpl;
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            MenuBuilder subMenu = menuBuilder;
            Window.Callback cb = this.this$0.getWindowCallback();
            if (cb != null) {
                boolean onMenuOpened = cb.onMenuOpened(108, subMenu);
            }
            return true;
        }

        public void onCloseMenu(MenuBuilder menu, boolean z) {
            boolean z2 = z;
            this.this$0.checkCloseActionMenu(menu);
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$PanelFeatureState */
    protected static final class PanelFeatureState {
        int background;
        View createdPanelView;
        ViewGroup decorView;
        int featureId;
        Bundle frozenActionViewState;
        Bundle frozenMenuState;
        int gravity;
        boolean isHandled;
        boolean isOpen;
        boolean isPrepared;
        ListMenuPresenter listMenuPresenter;
        Context listPresenterContext;
        MenuBuilder menu;
        public boolean qwertyMode;
        boolean refreshDecorView = false;
        boolean refreshMenuContent;
        View shownPanelView;
        boolean wasLastOpen;
        int windowAnimations;

        /* renamed from: x */
        int f32x;

        /* renamed from: y */
        int f33y;

        PanelFeatureState(int featureId2) {
            this.featureId = featureId2;
        }

        public boolean hasPanelItems() {
            if (this.shownPanelView == null) {
                return false;
            }
            if (this.createdPanelView != null) {
                return true;
            }
            return this.listMenuPresenter.getAdapter().getCount() > 0;
        }

        public void clearMenuPresenters() {
            if (this.menu != null) {
                this.menu.removeMenuPresenter(this.listMenuPresenter);
            }
            this.listMenuPresenter = null;
        }

        /* access modifiers changed from: package-private */
        public void setStyle(Context context) {
            TypedValue typedValue;
            Context context2;
            Context context3 = context;
            new TypedValue();
            TypedValue outValue = typedValue;
            Resources.Theme widgetTheme = context3.getResources().newTheme();
            widgetTheme.setTo(context3.getTheme());
            boolean resolveAttribute = widgetTheme.resolveAttribute(C0425R.attr.actionBarPopupTheme, outValue, true);
            if (outValue.resourceId != 0) {
                widgetTheme.applyStyle(outValue.resourceId, true);
            }
            boolean resolveAttribute2 = widgetTheme.resolveAttribute(C0425R.attr.panelMenuListTheme, outValue, true);
            if (outValue.resourceId != 0) {
                widgetTheme.applyStyle(outValue.resourceId, true);
            } else {
                widgetTheme.applyStyle(C0425R.C0428style.Theme_AppCompat_CompactMenu, true);
            }
            new ContextThemeWrapper(context3, 0);
            Context context4 = context2;
            context4.getTheme().setTo(widgetTheme);
            this.listPresenterContext = context4;
            TypedArray a = context4.obtainStyledAttributes(C0425R.styleable.AppCompatTheme);
            this.background = a.getResourceId(C0425R.styleable.AppCompatTheme_panelBackground, 0);
            this.windowAnimations = a.getResourceId(C0425R.styleable.AppCompatTheme_android_windowAnimationStyle, 0);
            a.recycle();
        }

        /* access modifiers changed from: package-private */
        public void setMenu(MenuBuilder menuBuilder) {
            MenuBuilder menu2 = menuBuilder;
            if (menu2 != this.menu) {
                if (this.menu != null) {
                    this.menu.removeMenuPresenter(this.listMenuPresenter);
                }
                this.menu = menu2;
                if (menu2 != null && this.listMenuPresenter != null) {
                    menu2.addMenuPresenter(this.listMenuPresenter);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public MenuView getListMenuView(MenuPresenter.Callback callback) {
            ListMenuPresenter listMenuPresenter2;
            MenuPresenter.Callback cb = callback;
            if (this.menu == null) {
                return null;
            }
            if (this.listMenuPresenter == null) {
                new ListMenuPresenter(this.listPresenterContext, C0425R.layout.abc_list_menu_item_layout);
                this.listMenuPresenter = listMenuPresenter2;
                this.listMenuPresenter.setCallback(cb);
                this.menu.addMenuPresenter(this.listMenuPresenter);
            }
            return this.listMenuPresenter.getMenuView(this.decorView);
        }

        /* access modifiers changed from: package-private */
        public Parcelable onSaveInstanceState() {
            SavedState savedState;
            Bundle bundle;
            new SavedState();
            SavedState savedState2 = savedState;
            savedState2.featureId = this.featureId;
            savedState2.isOpen = this.isOpen;
            if (this.menu != null) {
                new Bundle();
                savedState2.menuState = bundle;
                this.menu.savePresenterStates(savedState2.menuState);
            }
            return savedState2;
        }

        /* access modifiers changed from: package-private */
        public void onRestoreInstanceState(Parcelable state) {
            SavedState savedState = (SavedState) state;
            this.featureId = savedState.featureId;
            this.wasLastOpen = savedState.isOpen;
            this.frozenMenuState = savedState.menuState;
            this.shownPanelView = null;
            this.decorView = null;
        }

        /* access modifiers changed from: package-private */
        public void applyFrozenState() {
            if (this.menu != null && this.frozenMenuState != null) {
                this.menu.restorePresenterStates(this.frozenMenuState);
                this.frozenMenuState = null;
            }
        }

        /* renamed from: android.support.v7.app.AppCompatDelegateImpl$PanelFeatureState$SavedState */
        private static class SavedState implements Parcelable {
            public static final Parcelable.Creator<SavedState> CREATOR;
            int featureId;
            boolean isOpen;
            Bundle menuState;

            SavedState() {
            }

            public int describeContents() {
                return 0;
            }

            public void writeToParcel(Parcel parcel, int i) {
                Parcel dest = parcel;
                int i2 = i;
                dest.writeInt(this.featureId);
                dest.writeInt(this.isOpen ? 1 : 0);
                if (this.isOpen) {
                    dest.writeBundle(this.menuState);
                }
            }

            static SavedState readFromParcel(Parcel parcel, ClassLoader classLoader) {
                SavedState savedState;
                Parcel source = parcel;
                ClassLoader loader = classLoader;
                new SavedState();
                SavedState savedState2 = savedState;
                savedState2.featureId = source.readInt();
                savedState2.isOpen = source.readInt() == 1;
                if (savedState2.isOpen) {
                    savedState2.menuState = source.readBundle(loader);
                }
                return savedState2;
            }

            static {
                Parcelable.Creator<SavedState> creator;
                new Parcelable.ClassLoaderCreator<SavedState>() {
                    public SavedState createFromParcel(Parcel in, ClassLoader loader) {
                        return SavedState.readFromParcel(in, loader);
                    }

                    public SavedState createFromParcel(Parcel in) {
                        return SavedState.readFromParcel(in, (ClassLoader) null);
                    }

                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
                CREATOR = creator;
            }
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$ListMenuDecorView */
    private class ListMenuDecorView extends ContentFrameLayout {
        final /* synthetic */ AppCompatDelegateImpl this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ListMenuDecorView(AppCompatDelegateImpl appCompatDelegateImpl, Context context) {
            super(context);
            this.this$0 = appCompatDelegateImpl;
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            KeyEvent event = keyEvent;
            return this.this$0.dispatchKeyEvent(event) || super.dispatchKeyEvent(event);
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            MotionEvent event = motionEvent;
            if (event.getAction() == 0) {
                if (isOutOfBounds((int) event.getX(), (int) event.getY())) {
                    this.this$0.closePanel(0);
                    return true;
                }
            }
            return super.onInterceptTouchEvent(event);
        }

        public void setBackgroundResource(int resid) {
            setBackgroundDrawable(AppCompatResources.getDrawable(getContext(), resid));
        }

        private boolean isOutOfBounds(int i, int i2) {
            int x = i;
            int y = i2;
            return x < -5 || y < -5 || x > getWidth() + 5 || y > getHeight() + 5;
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$AppCompatWindowCallback */
    class AppCompatWindowCallback extends WindowCallbackWrapper {
        final /* synthetic */ AppCompatDelegateImpl this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AppCompatWindowCallback(AppCompatDelegateImpl this$02, Window.Callback callback) {
            super(callback);
            this.this$0 = this$02;
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            KeyEvent event = keyEvent;
            return this.this$0.dispatchKeyEvent(event) || super.dispatchKeyEvent(event);
        }

        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            KeyEvent event = keyEvent;
            return super.dispatchKeyShortcutEvent(event) || this.this$0.onKeyShortcut(event.getKeyCode(), event);
        }

        public boolean onCreatePanelMenu(int i, Menu menu) {
            int featureId = i;
            Menu menu2 = menu;
            if (featureId != 0 || (menu2 instanceof MenuBuilder)) {
                return super.onCreatePanelMenu(featureId, menu2);
            }
            return false;
        }

        public void onContentChanged() {
        }

        public boolean onPreparePanel(int i, View view, Menu menu) {
            int featureId = i;
            View view2 = view;
            Menu menu2 = menu;
            MenuBuilder mb = menu2 instanceof MenuBuilder ? (MenuBuilder) menu2 : null;
            if (featureId == 0 && mb == null) {
                return false;
            }
            if (mb != null) {
                mb.setOverrideVisibleItems(true);
            }
            boolean handled = super.onPreparePanel(featureId, view2, menu2);
            if (mb != null) {
                mb.setOverrideVisibleItems(false);
            }
            return handled;
        }

        public boolean onMenuOpened(int i, Menu menu) {
            int featureId = i;
            boolean onMenuOpened = super.onMenuOpened(featureId, menu);
            this.this$0.onMenuOpened(featureId);
            return true;
        }

        public void onPanelClosed(int i, Menu menu) {
            int featureId = i;
            super.onPanelClosed(featureId, menu);
            this.this$0.onPanelClosed(featureId);
        }

        public android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            ActionMode.Callback callback2 = callback;
            if (Build.VERSION.SDK_INT >= 23) {
                return null;
            }
            if (this.this$0.isHandleNativeActionModesEnabled()) {
                return startAsSupportActionMode(callback2);
            }
            return super.onWindowStartingActionMode(callback2);
        }

        /* access modifiers changed from: package-private */
        public final android.view.ActionMode startAsSupportActionMode(ActionMode.Callback callback) {
            SupportActionModeWrapper.CallbackWrapper callbackWrapper;
            new SupportActionModeWrapper.CallbackWrapper(this.this$0.mContext, callback);
            SupportActionModeWrapper.CallbackWrapper callbackWrapper2 = callbackWrapper;
            android.support.p003v7.view.ActionMode supportActionMode = this.this$0.startSupportActionMode(callbackWrapper2);
            if (supportActionMode != null) {
                return callbackWrapper2.getActionModeWrapper(supportActionMode);
            }
            return null;
        }

        @RequiresApi(23)
        public android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            ActionMode.Callback callback2 = callback;
            int type = i;
            if (this.this$0.isHandleNativeActionModesEnabled()) {
                switch (type) {
                    case 0:
                        return startAsSupportActionMode(callback2);
                }
            }
            return super.onWindowStartingActionMode(callback2, type);
        }

        @RequiresApi(24)
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
            List<KeyboardShortcutGroup> data = list;
            Menu menu2 = menu;
            int deviceId = i;
            PanelFeatureState panel = this.this$0.getPanelState(0, true);
            if (panel == null || panel.menu == null) {
                super.onProvideKeyboardShortcuts(data, menu2, deviceId);
            } else {
                super.onProvideKeyboardShortcuts(data, panel.menu, deviceId);
            }
        }
    }

    @VisibleForTesting
    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$AutoNightModeManager */
    final class AutoNightModeManager {
        private BroadcastReceiver mAutoTimeChangeReceiver;
        private IntentFilter mAutoTimeChangeReceiverFilter;
        private boolean mIsNight;
        private TwilightManager mTwilightManager;
        final /* synthetic */ AppCompatDelegateImpl this$0;

        AutoNightModeManager(@NonNull AppCompatDelegateImpl this$02, TwilightManager twilightManager) {
            TwilightManager twilightManager2 = twilightManager;
            this.this$0 = this$02;
            this.mTwilightManager = twilightManager2;
            this.mIsNight = twilightManager2.isNight();
        }

        /* access modifiers changed from: package-private */
        public int getApplyableNightMode() {
            this.mIsNight = this.mTwilightManager.isNight();
            return this.mIsNight ? 2 : 1;
        }

        /* access modifiers changed from: package-private */
        public void dispatchTimeChanged() {
            boolean isNight = this.mTwilightManager.isNight();
            if (isNight != this.mIsNight) {
                this.mIsNight = isNight;
                boolean applyDayNight = this.this$0.applyDayNight();
            }
        }

        /* access modifiers changed from: package-private */
        public void setup() {
            IntentFilter intentFilter;
            BroadcastReceiver broadcastReceiver;
            cleanup();
            if (this.mAutoTimeChangeReceiver == null) {
                new BroadcastReceiver(this) {
                    final /* synthetic */ AutoNightModeManager this$1;

                    {
                        this.this$1 = this$1;
                    }

                    public void onReceive(Context context, Intent intent) {
                        Context context2 = context;
                        Intent intent2 = intent;
                        this.this$1.dispatchTimeChanged();
                    }
                };
                this.mAutoTimeChangeReceiver = broadcastReceiver;
            }
            if (this.mAutoTimeChangeReceiverFilter == null) {
                new IntentFilter();
                this.mAutoTimeChangeReceiverFilter = intentFilter;
                this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_SET");
                this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
                this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_TICK");
            }
            Intent registerReceiver = this.this$0.mContext.registerReceiver(this.mAutoTimeChangeReceiver, this.mAutoTimeChangeReceiverFilter);
        }

        /* access modifiers changed from: package-private */
        public void cleanup() {
            if (this.mAutoTimeChangeReceiver != null) {
                this.this$0.mContext.unregisterReceiver(this.mAutoTimeChangeReceiver);
                this.mAutoTimeChangeReceiver = null;
            }
        }
    }

    public final ActionBarDrawerToggle.Delegate getDrawerToggleDelegate() {
        ActionBarDrawerToggle.Delegate delegate;
        new ActionBarDrawableToggleImpl(this);
        return delegate;
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$ActionBarDrawableToggleImpl */
    private class ActionBarDrawableToggleImpl implements ActionBarDrawerToggle.Delegate {
        final /* synthetic */ AppCompatDelegateImpl this$0;

        ActionBarDrawableToggleImpl(AppCompatDelegateImpl appCompatDelegateImpl) {
            this.this$0 = appCompatDelegateImpl;
        }

        public Drawable getThemeUpIndicator() {
            int[] iArr = {C0425R.attr.homeAsUpIndicator};
            TintTypedArray a = TintTypedArray.obtainStyledAttributes(getActionBarThemedContext(), (AttributeSet) null, iArr);
            Drawable result = a.getDrawable(0);
            a.recycle();
            return result;
        }

        public Context getActionBarThemedContext() {
            return this.this$0.getActionBarThemedContext();
        }

        public boolean isNavigationVisible() {
            ActionBar ab = this.this$0.getSupportActionBar();
            return (ab == null || (ab.getDisplayOptions() & 4) == 0) ? false : true;
        }

        public void setActionBarUpIndicator(Drawable drawable, int i) {
            Drawable upDrawable = drawable;
            int contentDescRes = i;
            ActionBar ab = this.this$0.getSupportActionBar();
            if (ab != null) {
                ab.setHomeAsUpIndicator(upDrawable);
                ab.setHomeActionContentDescription(contentDescRes);
            }
        }

        public void setActionBarDescription(int i) {
            int contentDescRes = i;
            ActionBar ab = this.this$0.getSupportActionBar();
            if (ab != null) {
                ab.setHomeActionContentDescription(contentDescRes);
            }
        }
    }
}
