package com.google.appinventor.components.runtime;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.p000v4.app.ActivityCompat;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.view.GravityCompat;
import android.support.p000v4.widget.DrawerLayout;
import android.support.p003v7.app.ActionBar;
import android.support.p003v7.app.ActionBarDrawerToggle;
import android.support.p003v7.app.AppCompatActivity;
import android.support.p003v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesAssets;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.runtime.collect.Lists;
import com.google.appinventor.components.runtime.collect.Maps;
import com.google.appinventor.components.runtime.collect.Sets;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.multidex.MultiDex;
import com.google.appinventor.components.runtime.util.AlignmentUtil;
import com.google.appinventor.components.runtime.util.AnimationUtil;
import com.google.appinventor.components.runtime.util.BulkPermissionRequest;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.KodularBilling;
import com.google.appinventor.components.runtime.util.KodularCompanionUtil;
import com.google.appinventor.components.runtime.util.KodularHelper;
import com.google.appinventor.components.runtime.util.KodularResourcesUtil;
import com.google.appinventor.components.runtime.util.KodularUnitUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.OnInitializeListener;
import com.google.appinventor.components.runtime.util.PaintUtil;
import com.google.appinventor.components.runtime.util.PermissionUtil;
import com.google.appinventor.components.runtime.util.ScreenDensityUtil;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.json.JSONException;

@DesignerComponent(androidMinSdk = 19, category = ComponentCategory.LAYOUT_GENERAL, description = "Top-level component containing all other components in the program", showOnPalette = false, version = 42)
@UsesLibraries(libraries = "animated-vector-drawable.aar,appcompat-v7.aar,asynclayoutinflater.aar,cardview-v7.aar,collections.jar,constraint-layout.aar,coordinatorlayout.aar,cursoradapter.aar,customtabs.aar,customview.aar,design.aar,documentfile.aar,drawerlayout.aar,interpolator.aar,livedata.aar,livedata-core.aar,loader.aar,localbroadcastmanager.aar,print.aar,recyclerview-v7.aar,runtime.aar,slidingpanelayout.aar,support-compat.aar,support-core-ui.aar,support-core-utils.aar,support-fragment.aar,support-media-compat.aar,support-v4.aar, support-vector-drawable.aar,swiperefreshlayout.aar,transition.aar,versionedparcelable.aar,viewmodel.aar,viewpager.aar,arch-core-runtime.jar, arch-core-runtime.aar")
@SimpleObject
@UsesAssets(fileNames = "Roboto-Thin.ttf, Roboto-Regular.ttf, fontawesome-webfont.ttf, fa-regular-400.ttf, fa-solid-900.ttf, fa-brands-400.ttf, MaterialIcons-Regular.ttf")
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.ACCESS_WIFI_STATE, android.permission.ACCESS_NETWORK_STATE")
public class Form extends AppCompatActivity implements ViewTreeObserver.OnGlobalLayoutListener, Component, ComponentContainer, HandlesEventDispatching {
    static final /* synthetic */ boolean $assertionsDisabled;
    public static final String APPINVENTOR_URL_SCHEME = "appinventor";
    private static final String ARGUMENT_NAME = "APP_INVENTOR_START";
    public static final String ASSETS_PREFIX = "file:///android_asset/";
    private static final boolean DEBUG = false;
    private static final int DEFAULT_ACCENT_COLOR = PaintUtil.hexStringToInt(ComponentConstants.DEFAULT_ACCENT_COLOR);
    private static final int DEFAULT_PRIMARY_COLOR = PaintUtil.hexStringToInt("&HFF3F51B5");
    private static final int DEFAULT_PRIMARY_COLOR_DARK = PaintUtil.hexStringToInt(ComponentConstants.DEFAULT_PRIMARY_DARK_COLOR);
    private static final String LOG_TAG = "Form";
    public static final int MAX_PERMISSION_NONCE = 65535;
    private static final String RESULT_NAME = "APP_INVENTOR_RESULT";
    private static final int SWITCH_FORM_REQUEST_CODE = 1;
    private static boolean _initialized = false;
    protected static Form activeForm;
    private static boolean applicationIsBeingClosed;
    private static long minimumToastWait = 10000000000L;
    private static int nextRequestCode = 2;
    /* access modifiers changed from: private */
    public static boolean sCompatibilityMode;
    private static boolean screenInitialized;
    private static boolean showListsAsJson = false;
    private String aboutScreen;
    private String aboutScreenTitle;
    private int aboutThisAppBackgroundColor;
    private boolean aboutThisAppLightTheme;
    private int accentColor;
    /* access modifiers changed from: private */
    public ActionBarDrawerToggle actionBarDrawerToggle;
    private final HashMap<Integer, ActivityResultListener> activityResultMap = Maps.newHashMap();
    private AlignmentUtil alignmentSetter;
    protected final Handler androidUIHandler;
    private AppBarLayout appBarLayout;
    private String appId = "";
    private int backgroundColor;
    private Drawable backgroundDrawable;
    private String backgroundImagePath = "";
    /* access modifiers changed from: private */
    public KodularBilling billing;
    private String closeAnimType;
    private float compatScalingFactor;
    public CoordinatorLayout coordinatorLayout;
    private Menu customActionBarIcon;
    private Menu customMenu;
    private float deviceDensity;
    private ArrayList<PercentStorageRecord> dimChanges;
    private int drawerArrowIconColor = 0;
    /* access modifiers changed from: private */
    public DrawerLayout drawerLayout;
    private int fontTypeface;
    private int formHeight;
    protected String formName;
    private int formWidth;
    /* access modifiers changed from: private */
    public FrameLayout frameLayout;
    private FullScreenVideoUtil fullScreenVideoUtil;
    public boolean highQuality;
    private int horizontalAlignment;
    /* access modifiers changed from: private */
    public boolean isCompanion;
    /* access modifiers changed from: private */
    public boolean isDrawerOpenBackup;
    private boolean keepScreenOn;
    private boolean keyboardReallyShown;
    private boolean keyboardShown;
    private long lastToastTime;
    private Object layoutBackup;
    /* access modifiers changed from: private */
    public boolean lockedMenu;

    /* renamed from: lp */
    private DrawerLayout.LayoutParams f49lp;
    private boolean navigationBarLight;
    private int navigationIconColor;
    private String nextFormName;
    private final Set<OnClearListener> onClearListeners = Sets.newHashSet();
    private Bundle onCreateBundle;
    private final Set<OnCreateListener> onCreateListeners = Sets.newHashSet();
    private final Set<OnCreateOptionsMenuListener> onCreateOptionsMenuListeners = Sets.newHashSet();
    private final Set<OnDestroyListener> onDestroyListeners = Sets.newHashSet();
    /* access modifiers changed from: private */
    public final Set<OnInitializeListener> onInitializeListeners = Sets.newHashSet();
    private final Set<OnNewIntentListener> onNewIntentListeners = Sets.newHashSet();
    private final Set<OnOptionsItemSelectedListener> onOptionsItemSelectedListeners = Sets.newHashSet();
    private final Set<OnOrientationChangeListener> onOrientationChangeListeners = Sets.newHashSet();
    private final Set<OnPauseListener> onPauseListeners = Sets.newHashSet();
    private final Set<OnResumeListener> onResumeListeners = Sets.newHashSet();
    private final Set<OnStopListener> onStopListeners = Sets.newHashSet();
    private String openAnimType;
    private int optionsMenuIconColor;
    /* access modifiers changed from: private */
    public final HashMap<Integer, PermissionResultHandler> permissionHandlers = Maps.newHashMap();
    /* access modifiers changed from: private */
    public final Random permissionRandom;
    private final Set<String> permissions;
    private int primaryColor;
    private int primaryColorDark;
    private ProgressDialog progress;
    protected String receiveSharedValue;
    /* access modifiers changed from: private */
    public int receiveSharedValueType;
    private ScaledFrameLayout scaleLayout;
    private boolean scrollable;
    private boolean showNavBar;
    private boolean showOptionsMenu;
    public boolean showStatusBar;
    public boolean showTitle = true;
    private boolean splashEnabled;
    protected String startupValue;
    private boolean stateBackButton;
    private int statusbarColor;
    private boolean statusbarLight;
    /* access modifiers changed from: private */
    public int titleBarColor;
    private int titleBarTextColor;
    /* access modifiers changed from: private */
    public Toolbar toolbar;
    private int toolbarIconColor;
    /* access modifiers changed from: private */
    public String toolbarSubTitle;
    /* access modifiers changed from: private */
    public String toolbarTitle;
    private int verticalAlignment;
    private LinearLayout viewLayout;
    private String yandexTranslateTagline;

    static {
        boolean z;
        if (!Form.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
    }

    public Form() {
        Handler handler;
        Random random;
        Set<String> set;
        ArrayList<PercentStorageRecord> arrayList;
        new Handler();
        this.androidUIHandler = handler;
        new Random();
        this.permissionRandom = random;
        this.receiveSharedValue = "";
        this.receiveSharedValueType = 0;
        this.lastToastTime = System.nanoTime() - minimumToastWait;
        this.onCreateBundle = null;
        this.keyboardReallyShown = false;
        new HashSet();
        this.permissions = set;
        this.highQuality = true;
        this.splashEnabled = true;
        this.keepScreenOn = false;
        this.layoutBackup = null;
        this.aboutThisAppBackgroundColor = Component.COLOR_DARK_GRAY;
        this.primaryColor = DEFAULT_PRIMARY_COLOR;
        this.primaryColorDark = DEFAULT_PRIMARY_COLOR_DARK;
        this.accentColor = DEFAULT_ACCENT_COLOR;
        this.fontTypeface = 0;
        this.toolbarSubTitle = "";
        new ArrayList<>();
        this.dimChanges = arrayList;
        this.yandexTranslateTagline = "";
    }

    static /* synthetic */ KodularBilling access$1002(Form form, KodularBilling kodularBilling) {
        KodularBilling kodularBilling2 = kodularBilling;
        KodularBilling kodularBilling3 = kodularBilling2;
        form.billing = kodularBilling3;
        return kodularBilling2;
    }

    static /* synthetic */ boolean access$1202(boolean z) {
        boolean z2 = z;
        screenInitialized = z2;
        return z2;
    }

    static /* synthetic */ boolean access$302(Form form, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        form.isDrawerOpenBackup = z3;
        return z2;
    }

    static /* synthetic */ boolean access$802(Form form, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        form.isCompanion = z3;
        return z2;
    }

    public static class PercentStorageRecord {
        AndroidViewComponent B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
        Dim hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        int tj0MDuMmBVyFcp8vwXpkfd0RnoyqL9aUR0zh2QG1qIbcD4cqzuxOkXiR3Ef5Sjag;

        public enum Dim {
        }

        public PercentStorageRecord(AndroidViewComponent androidViewComponent, int i, Dim dim) {
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = androidViewComponent;
            this.tj0MDuMmBVyFcp8vwXpkfd0RnoyqL9aUR0zh2QG1qIbcD4cqzuxOkXiR3Ef5Sjag = i;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = dim;
        }
    }

    /* renamed from: com.google.appinventor.components.runtime.Form$a */
    static class C0711a extends AsyncTask<Form, Void, Boolean> {
        private Form wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

        private C0711a() {
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C0711a(byte b) {
            this();
            byte b2 = b;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = ((Form[]) objArr)[0];
            int d = Log.d(Form.LOG_TAG, "Doing Full MultiDex Install");
            boolean install = MultiDex.install(this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou, true);
            return Boolean.TRUE;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            Object obj2 = obj;
            this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.onCreateFinish();
        }
    }

    public void onCreate(Bundle bundle) {
        LinearLayout linearLayout;
        AlignmentUtil alignmentUtil;
        StringBuilder sb;
        StringBuilder sb2;
        C0711a aVar;
        Bundle bundle2 = bundle;
        super.onCreate(bundle2);
        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }
        this.onCreateBundle = bundle2;
        for (OnCreateListener onCreate : this.onCreateListeners) {
            onCreate.onCreate();
        }
        String name = getClass().getName();
        this.formName = name.substring(name.lastIndexOf(46) + 1);
        this.onCreateBundle = bundle2;
        activeForm = this;
        if (this instanceof ReplForm) {
            this.isCompanion = true;
        }
        this.deviceDensity = getResources().getDisplayMetrics().density;
        this.compatScalingFactor = ScreenDensityUtil.computeCompatibleScaling(this);
        new LinearLayout(this);
        this.viewLayout = linearLayout;
        new AlignmentUtil(this.viewLayout);
        this.alignmentSetter = alignmentUtil;
        this.progress = null;
        if (_initialized || !this.formName.equals("Screen1")) {
            new StringBuilder("NO MULTI: _initialized = ");
            int d = Log.d(LOG_TAG, sb.append(_initialized).append(" formName = ").append(this.formName).toString());
            _initialized = true;
            onCreateFinish();
            return;
        }
        new StringBuilder("MULTI: _initialized = ");
        int d2 = Log.d(LOG_TAG, sb2.append(_initialized).append(" formName = ").append(this.formName).toString());
        _initialized = true;
        if (ReplApplication.installed) {
            int d3 = Log.d(LOG_TAG, "MultiDex already installed.");
            onCreateFinish();
            return;
        }
        this.progress = ProgressDialog.show(this, "Please Wait...", "Installation Finishing");
        this.progress.show();
        new C0711a((byte) 0);
        AsyncTask execute = aVar.execute(new Form[]{this});
    }

    /* access modifiers changed from: package-private */
    public void onCreateFinish() {
        StringBuilder sb;
        PermissionResultHandler permissionResultHandler;
        new StringBuilder("onCreateFinish called ");
        int d = Log.d(LOG_TAG, sb.append(System.currentTimeMillis()).toString());
        if (this.progress != null) {
            this.progress.dismiss();
        }
        populatePermissions();
        if (doesAppDeclarePermission("android.permission.WRITE_EXTERNAL_STORAGE") && IsCompanion()) {
            new PermissionResultHandler(this) {
                final /* synthetic */ Form hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void HandlePermissionResponse(String str, boolean z) {
                    Runnable runnable;
                    String str2 = str;
                    if (z) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.onCreateFinish2();
                        return;
                    }
                    int i = Log.i(Form.LOG_TAG, "WRITE_EXTERNAL_STORAGE Permission denied by user");
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.onCreateFinish2();
                    new Runnable(this) {
                        private /* synthetic */ C06861 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                        }

                        public final void run() {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.PermissionDenied(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Initialize", "android.permission.WRITE_EXTERNAL_STORAGE");
                        }
                    };
                    boolean post = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.post(runnable);
                }
            };
            askPermission("android.permission.WRITE_EXTERNAL_STORAGE", permissionResultHandler);
            return;
        }
        onCreateFinish2();
    }

    /* access modifiers changed from: package-private */
    public void onCreateFinish2() {
        FullScreenVideoUtil fullScreenVideoUtil2;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        defaultPropertyValues();
        Intent intent = null;
        try {
            intent = getIntent();
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
        if (intent != null && intent.hasExtra(ARGUMENT_NAME)) {
            this.startupValue = intent.getStringExtra(ARGUMENT_NAME);
        }
        if (!(intent == null || intent.getType() == null)) {
            if (intent.getType().contains("audio/")) {
                Uri uri = (Uri) intent.getParcelableExtra("android.intent.extra.STREAM");
                Uri uri2 = uri;
                if (uri != null) {
                    new StringBuilder();
                    this.receiveSharedValue = sb3.append(uri2.getPath()).toString();
                    this.receiveSharedValueType = 1;
                }
            } else if (intent.getType().contains("image/")) {
                Uri uri3 = (Uri) intent.getParcelableExtra("android.intent.extra.STREAM");
                Uri uri4 = uri3;
                if (uri3 != null) {
                    new StringBuilder();
                    this.receiveSharedValue = sb2.append(uri4.getPath()).toString();
                    this.receiveSharedValueType = 2;
                }
            } else if (intent.getType().contains("text/")) {
                String stringExtra = intent.getStringExtra("android.intent.extra.TEXT");
                String str = stringExtra;
                if (stringExtra != null) {
                    this.receiveSharedValue = String.valueOf(str);
                    this.receiveSharedValueType = 3;
                }
            } else if (intent.getType().contains("video/")) {
                Uri uri5 = (Uri) intent.getParcelableExtra("android.intent.extra.STREAM");
                Uri uri6 = uri5;
                if (uri5 != null) {
                    new StringBuilder();
                    this.receiveSharedValue = sb.append(uri6.getPath()).toString();
                    this.receiveSharedValueType = 4;
                }
            }
        }
        FullScreenVideoUtil fullScreenVideoUtil3 = fullScreenVideoUtil2;
        new FullScreenVideoUtil(this, this.androidUIHandler);
        this.fullScreenVideoUtil = fullScreenVideoUtil3;
        getWindow().setSoftInputMode(getWindow().getAttributes().softInputMode | 16);
        $define();
        Initialize();
    }

    private void populatePermissions() {
        try {
            boolean addAll = Collections.addAll(this.permissions, getPackageManager().getPackageInfo(getPackageName(), 4096).requestedPermissions);
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, "Exception while attempting to learn permissions.", e);
        }
    }

    public Bundle getOnCreateBundle() {
        return this.onCreateBundle;
    }

    private void defaultPropertyValues() {
        Scrollable(false);
        Sizing("Responsive");
        AboutScreen("");
        AboutScreenTitle("About this application");
        AboutScreenBackgroundColor(this.aboutThisAppBackgroundColor);
        AboutScreenLightTheme(this.aboutThisAppLightTheme);
        BackgroundImage("");
        BackgroundColor(-1);
        AlignHorizontal(1);
        AlignVertical(1);
        Title("");
        TitleBarSubTitle("");
        ShowStatusBar(true);
        ShowNavBar(true);
        TitleVisible(true);
        ShowListsAsJson(false);
        NavigationBarColor(-16777216);
        ShowOptionsMenu(true);
        ScreenOrientation("unspecified");
        SplashEnabled(true);
        AccentColor(DEFAULT_ACCENT_COLOR);
        PrimaryColor(DEFAULT_PRIMARY_COLOR);
        PrimaryColorDark(DEFAULT_PRIMARY_COLOR_DARK);
        Theme(ComponentConstants.DEFAULT_THEME);
        TitleBarTypefaceImport("");
        HighQuality(false);
        KeepScreenOn(false);
        RTLSupport(false);
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        if (this.actionBarDrawerToggle != null) {
            this.actionBarDrawerToggle.syncState();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        Runnable runnable;
        Configuration configuration2 = configuration;
        super.onConfigurationChanged(configuration2);
        if (this.actionBarDrawerToggle != null) {
            this.actionBarDrawerToggle.onConfigurationChanged(configuration2);
        }
        int d = Log.d(LOG_TAG, "onConfigurationChanged() called");
        int i = configuration2.orientation;
        int i2 = i;
        if (i == 2 || i2 == 1) {
            final int i3 = i2;
            new Runnable(this) {
                private /* synthetic */ Form hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final void run() {
                    Runnable runnable;
                    boolean z = false;
                    if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.frameLayout != null) {
                        if (i3 == 2) {
                            if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.frameLayout.getWidth() >= this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.frameLayout.getHeight()) {
                                z = true;
                            }
                        } else if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.frameLayout.getHeight() >= this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.frameLayout.getWidth()) {
                            z = true;
                        }
                    }
                    if (z) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.recomputeLayout();
                        final FrameLayout access$100 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.frameLayout;
                        new Runnable(this) {

                            /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
                            private /* synthetic */ C069012 f398hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                            {
                                this.f398hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                            }

                            public final void run() {
                                if (access$100 != null) {
                                    access$100.invalidate();
                                }
                            }
                        };
                        boolean postDelayed = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.postDelayed(runnable, 66);
                        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isDrawerOpenBackup) {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.SideMenuOpen();
                        }
                        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.lockedMenu) {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.LockSideMenu();
                        }
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.TitleVisible(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.showTitle);
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Title(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.toolbarTitle);
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.TitleBarSubTitle(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.toolbarSubTitle);
                        KodularCompanionUtil.toolbarColor(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.toolbar, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isCompanion, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.titleBarColor);
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.ScreenOrientationChanged();
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidateOptionsMenu();
                        return;
                    }
                    boolean post = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.post(this);
                }
            };
            boolean post = this.androidUIHandler.post(runnable);
        }
    }

    public void onGlobalLayout() {
        Rect rect;
        int height = this.scaleLayout.getRootView().getHeight();
        float height2 = ((float) (height - this.scaleLayout.getHeight())) / ((float) height);
        int d = Log.d(LOG_TAG, "onGlobalLayout(): diffPercent = ".concat(String.valueOf(height2)));
        if (((double) height2) < 0.25d) {
            int d2 = Log.d(LOG_TAG, "keyboard hidden!");
            if (this.keyboardShown) {
                this.keyboardShown = false;
                if (sCompatibilityMode) {
                    this.scaleLayout.setScale(this.compatScalingFactor);
                    this.scaleLayout.invalidate();
                }
            }
        } else {
            int d3 = Log.d(LOG_TAG, "keyboard shown!");
            this.keyboardShown = true;
            if (this.scaleLayout != null) {
                this.scaleLayout.setScale(1.0f);
                this.scaleLayout.invalidate();
            }
        }
        new Rect();
        Rect rect2 = rect;
        this.frameLayout.getWindowVisibleDisplayFrame(rect2);
        int height3 = this.frameLayout.getRootView().getHeight();
        this.keyboardReallyShown = ((double) (height3 - rect2.bottom)) > ((double) height3) * 0.15d;
        KeyboardVisiblityChanged(this.keyboardReallyShown);
    }

    @SimpleEvent(description = "Event will be invoked if the keyboard was visible or invisible.")
    public void KeyboardVisiblityChanged(boolean z) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "KeyboardVisiblityChanged", Boolean.valueOf(z));
    }

    public void onBackPressed() {
        if (this.drawerLayout != null && this.drawerLayout.isDrawerOpen((int) GravityCompat.START)) {
            this.drawerLayout.closeDrawer((int) GravityCompat.START);
        } else if (!BackPressed()) {
            AnimationUtil.ApplyCloseScreenAnimation(this, this.closeAnimType);
            super.onBackPressed();
        }
    }

    @SimpleEvent(description = "Device back button pressed.")
    public boolean BackPressed() {
        return EventDispatcher.dispatchEvent(this, "BackPressed", new Object[0]);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        StringBuilder sb;
        String str;
        int i3 = i;
        int i4 = i2;
        Intent intent2 = intent;
        super.onActivityResult(i3, i4, intent2);
        new StringBuilder("Form ");
        int i5 = Log.i(LOG_TAG, sb.append(this.formName).append(" got onActivityResult, requestCode = ").append(i3).append(", resultCode = ").append(i4).toString());
        if (i3 == 1) {
            if (intent2 == null || !intent2.hasExtra(RESULT_NAME)) {
                str = "";
            } else {
                str = intent2.getStringExtra(RESULT_NAME);
            }
            OtherScreenClosed(this.nextFormName, decodeJSONStringForForm(str, "other screen closed"));
            return;
        }
        ActivityResultListener activityResultListener = this.activityResultMap.get(Integer.valueOf(i3));
        ActivityResultListener activityResultListener2 = activityResultListener;
        if (activityResultListener != null) {
            activityResultListener2.resultReturned(i3, i4, intent2);
        }
    }

    private static Object decodeJSONStringForForm(String str, String str2) {
        StringBuilder sb;
        String str3 = str;
        String str4 = str2;
        int i = Log.i(LOG_TAG, "decodeJSONStringForForm -- decoding JSON representation:".concat(String.valueOf(str3)));
        Object obj = "";
        try {
            obj = JsonUtil.getObjectFromJson(str3, true);
            new StringBuilder("decodeJSONStringForForm -- got decoded JSON:");
            int i2 = Log.i(LOG_TAG, sb.append(obj.toString()).toString());
        } catch (JSONException e) {
            Form form = activeForm;
            form.dispatchErrorOccurredEvent(form, str4, ErrorMessages.ERROR_SCREEN_BAD_VALUE_RECEIVED, str3);
        }
        return obj;
    }

    public int registerForActivityResult(ActivityResultListener activityResultListener) {
        int generateNewRequestCode = generateNewRequestCode();
        ActivityResultListener put = this.activityResultMap.put(Integer.valueOf(generateNewRequestCode), activityResultListener);
        return generateNewRequestCode;
    }

    public void registerForActivityResult(ActivityResultListener activityResultListener, int i) {
        ActivityResultListener put = this.activityResultMap.put(Integer.valueOf(i), activityResultListener);
    }

    public void unregisterForActivityResult(ActivityResultListener activityResultListener) {
        ActivityResultListener activityResultListener2 = activityResultListener;
        ArrayList<Integer> newArrayList = Lists.newArrayList();
        for (Map.Entry next : this.activityResultMap.entrySet()) {
            if (activityResultListener2.equals(next.getValue())) {
                boolean add = newArrayList.add(next.getKey());
            }
        }
        for (Integer remove : newArrayList) {
            ActivityResultListener remove2 = this.activityResultMap.remove(remove);
        }
    }

    /* access modifiers changed from: package-private */
    public void ReplayFormOrientation() {
        int d = Log.d(LOG_TAG, "ReplayFormOrientation()");
        ArrayList arrayList = (ArrayList) this.dimChanges.clone();
        this.dimChanges.clear();
        for (int i = 0; i < arrayList.size(); i++) {
            PercentStorageRecord percentStorageRecord = (PercentStorageRecord) arrayList.get(i);
            PercentStorageRecord percentStorageRecord2 = percentStorageRecord;
            if (percentStorageRecord.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == PercentStorageRecord.Dim.HEIGHT) {
                percentStorageRecord2.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.Height(percentStorageRecord2.tj0MDuMmBVyFcp8vwXpkfd0RnoyqL9aUR0zh2QG1qIbcD4cqzuxOkXiR3Ef5Sjag);
            } else {
                percentStorageRecord2.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.Width(percentStorageRecord2.tj0MDuMmBVyFcp8vwXpkfd0RnoyqL9aUR0zh2QG1qIbcD4cqzuxOkXiR3Ef5Sjag);
            }
        }
    }

    public void registerPercentLength(AndroidViewComponent androidViewComponent, int i, PercentStorageRecord.Dim dim) {
        Object obj;
        new PercentStorageRecord(androidViewComponent, i, dim);
        boolean add = this.dimChanges.add(obj);
    }

    private static int generateNewRequestCode() {
        int i = nextRequestCode;
        int i2 = i;
        nextRequestCode = i + 1;
        return i2;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        StringBuilder sb;
        super.onResume();
        new StringBuilder("Form ");
        int i = Log.i(LOG_TAG, sb.append(this.formName).append(" got onResume").toString());
        activeForm = this;
        if (applicationIsBeingClosed) {
            closeApplication();
            return;
        }
        OnAppResume();
        for (OnResumeListener onResume : this.onResumeListeners) {
            onResume.onResume();
        }
    }

    public void registerForOnResume(OnResumeListener onResumeListener) {
        boolean add = this.onResumeListeners.add(onResumeListener);
    }

    public void registerForOnInitialize(OnInitializeListener onInitializeListener) {
        boolean add = this.onInitializeListeners.add(onInitializeListener);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        StringBuilder sb;
        Intent intent2 = intent;
        super.onNewIntent(intent2);
        new StringBuilder("Form ");
        int d = Log.d(LOG_TAG, sb.append(this.formName).append(" got onNewIntent ").append(intent2).toString());
        for (OnNewIntentListener onNewIntent : this.onNewIntentListeners) {
            onNewIntent.onNewIntent(intent2);
        }
    }

    public void registerForOnNewIntent(OnNewIntentListener onNewIntentListener) {
        boolean add = this.onNewIntentListeners.add(onNewIntentListener);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        StringBuilder sb;
        super.onPause();
        new StringBuilder("Form ");
        int i = Log.i(LOG_TAG, sb.append(this.formName).append(" got onPause").toString());
        OnAppPause();
        for (OnPauseListener onPause : this.onPauseListeners) {
            onPause.onPause();
        }
    }

    public void registerForOnPause(OnPauseListener onPauseListener) {
        boolean add = this.onPauseListeners.add(onPauseListener);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        StringBuilder sb;
        super.onStop();
        new StringBuilder("Form ");
        int i = Log.i(LOG_TAG, sb.append(this.formName).append(" got onStop").toString());
        OnAppStop();
        for (OnStopListener onStop : this.onStopListeners) {
            onStop.onStop();
        }
    }

    public void registerForOnStop(OnStopListener onStopListener) {
        boolean add = this.onStopListeners.add(onStopListener);
    }

    public void registerForOnClear(OnClearListener onClearListener) {
        boolean add = this.onClearListeners.add(onClearListener);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        StringBuilder sb;
        new StringBuilder("Form ");
        int i = Log.i(LOG_TAG, sb.append(this.formName).append(" got onDestroy").toString());
        EventDispatcher.removeDispatchDelegate(this);
        for (OnDestroyListener onDestroy : this.onDestroyListeners) {
            onDestroy.onDestroy();
        }
        super.onDestroy();
    }

    public void registerForOnDestroy(OnDestroyListener onDestroyListener) {
        boolean add = this.onDestroyListeners.add(onDestroyListener);
    }

    public void registerForOnCreateOptionsMenu(OnCreateOptionsMenuListener onCreateOptionsMenuListener) {
        boolean add = this.onCreateOptionsMenuListeners.add(onCreateOptionsMenuListener);
    }

    public void registerForOnOptionsItemSelected(OnOptionsItemSelectedListener onOptionsItemSelectedListener) {
        boolean add = this.onOptionsItemSelectedListeners.add(onOptionsItemSelectedListener);
    }

    public void registerForOnOrientationChangeListener(OnOrientationChangeListener onOrientationChangeListener) {
        boolean add = this.onOrientationChangeListeners.add(onOrientationChangeListener);
    }

    public void registerForOnCreateListener(OnCreateListener onCreateListener) {
        boolean add = this.onCreateListeners.add(onCreateListener);
    }

    public Dialog onCreateDialog(int i) {
        int i2 = i;
        switch (i2) {
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_DIALOG_FLAG /*189*/:
                return this.fullScreenVideoUtil.createFullScreenVideoDialog();
            default:
                return super.onCreateDialog(i2);
        }
    }

    public void onPrepareDialog(int i, Dialog dialog) {
        int i2 = i;
        Dialog dialog2 = dialog;
        switch (i2) {
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_DIALOG_FLAG /*189*/:
                this.fullScreenVideoUtil.prepareFullScreenVideoDialog(dialog2);
                return;
            default:
                super.onPrepareDialog(i2, dialog2);
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void $define() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [com.google.appinventor.components.runtime.Component] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean canDispatchEvent(com.google.appinventor.components.runtime.Component r7, java.lang.String r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            boolean r3 = screenInitialized
            if (r3 != 0) goto L_0x0015
            r3 = r1
            r4 = r0
            if (r3 != r4) goto L_0x0022
            r3 = r2
            java.lang.String r4 = "Initialize"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0022
        L_0x0015:
            r3 = 1
        L_0x0016:
            r5 = r3
            r3 = r5
            r4 = r5
            r1 = r4
            if (r3 == 0) goto L_0x001f
            r3 = r0
            activeForm = r3
        L_0x001f:
            r3 = r1
            r0 = r3
            return r0
        L_0x0022:
            r3 = 0
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Form.canDispatchEvent(com.google.appinventor.components.runtime.Component, java.lang.String):boolean");
    }

    public boolean dispatchEvent(Component component, String str, String str2, Object[] objArr) {
        Throwable th;
        Component component2 = component;
        String str3 = str;
        String str4 = str2;
        Object[] objArr2 = objArr;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    public void dispatchGenericEvent(Component component, String str, boolean z, Object[] objArr) {
        Throwable th;
        Component component2 = component;
        String str2 = str;
        boolean z2 = z;
        Object[] objArr2 = objArr;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    @SimpleEvent(description = "Screen starting")
    public void Initialize() {
        Runnable runnable;
        new Runnable(this) {
            private /* synthetic */ Form hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void run() {
                KodularBilling kodularBilling;
                StringBuilder sb;
                StringBuilder sb2;
                StringBuilder sb3;
                if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.frameLayout == null || this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.frameLayout.getWidth() == 0 || this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.frameLayout.getHeight() == 0) {
                    boolean post = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.post(this);
                    return;
                }
                new KodularBilling(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.$context(), this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                KodularBilling access$1002 = Form.access$1002(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, kodularBilling);
                if (Form.activeForm instanceof ReplForm) {
                    boolean access$802 = Form.access$802(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, true);
                }
                boolean dispatchEvent = EventDispatcher.dispatchEvent(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Initialize", new Object[0]);
                if (Form.sCompatibilityMode) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Sizing("Fixed");
                } else {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Sizing("Responsive");
                }
                boolean access$1202 = Form.access$1202(true);
                new StringBuilder("receiveSharedValueType: ");
                int i = Log.i(Form.LOG_TAG, sb.append(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.receiveSharedValueType).toString());
                new StringBuilder("receiveSharedValue: ");
                int i2 = Log.i(Form.LOG_TAG, sb2.append(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.receiveSharedValue).toString());
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotReceivedShared(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.receiveSharedValueType, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.receiveSharedValue);
                for (OnInitializeListener onInitialize : this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.onInitializeListeners) {
                    onInitialize.onInitialize();
                }
                if (Form.activeForm instanceof ReplForm) {
                    ReplForm replForm = (ReplForm) Form.activeForm;
                    String str = ReplForm.LOG_TAG;
                    new StringBuilder("HandleReturnValues() Called, replResult = ");
                    int d = Log.d(str, sb3.append(replForm.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt).toString());
                    if (replForm.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt != null) {
                        ReplForm replForm2 = replForm;
                        replForm2.OtherScreenClosed(replForm2.TDuNe2Upxoi7WX7QZc6R8eUnFPyyDFw3hP7z3w6U8jMxQwJRQM03zSa9HzWXRv1, replForm.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt);
                        int d2 = Log.d(ReplForm.LOG_TAG, "Called OtherScreenClosed");
                        replForm.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt = null;
                    }
                }
                if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.formName.equals("Screen1")) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.billing.update();
                }
            }
        };
        boolean post = this.androidUIHandler.post(runnable);
    }

    @SimpleEvent(description = "Screen orientation changed")
    public void ScreenOrientationChanged() {
        for (OnOrientationChangeListener onOrientationChange : this.onOrientationChangeListeners) {
            onOrientationChange.onOrientationChange();
        }
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "ScreenOrientationChanged", new Object[0]);
    }

    @SimpleEvent(description = "Event raised when an error occurs. Only some errors will raise this condition.  For those errors, the system will show a notification by default.  You can use this event handler to prescribe an error behavior different than the default.")
    public void ErrorOccurred(Component component, String str, int i, String str2) {
        StringBuilder sb;
        Notifier notifier;
        StringBuilder sb2;
        Component component2 = component;
        String str3 = str;
        int i2 = i;
        String str4 = str2;
        String name = component2.getClass().getName();
        new StringBuilder("Form ");
        int e = Log.e(LOG_TAG, sb.append(this.formName).append(" ErrorOccurred, errorNumber = ").append(i2).append(", componentType = ").append(name.substring(name.lastIndexOf(".") + 1)).append(", functionName = ").append(str3).append(", messages = ").append(str4).toString());
        Object[] objArr = new Object[4];
        objArr[0] = component2;
        Object[] objArr2 = objArr;
        objArr2[1] = str3;
        Object[] objArr3 = objArr2;
        objArr3[2] = Integer.valueOf(i2);
        Object[] objArr4 = objArr3;
        objArr4[3] = str4;
        if (!EventDispatcher.dispatchEvent(this, "ErrorOccurred", objArr4) && screenInitialized) {
            try {
                new Notifier(this);
                new StringBuilder("Error ");
                notifier.ShowAlert(sb2.append(i2).append(": ").append(str4).toString());
            } catch (Exception e2) {
                int e3 = Log.e(LOG_TAG, String.valueOf(e2));
            }
        }
    }

    public void ErrorOccurredDialog(Component component, String str, int i, String str2, String str3, String str4) {
        StringBuilder sb;
        Notifier notifier;
        StringBuilder sb2;
        Component component2 = component;
        String str5 = str;
        int i2 = i;
        String str6 = str2;
        String str7 = str3;
        String str8 = str4;
        String name = component2.getClass().getName();
        new StringBuilder("Form ");
        int e = Log.e(LOG_TAG, sb.append(this.formName).append(" ErrorOccurred, errorNumber = ").append(i2).append(", componentType = ").append(name.substring(name.lastIndexOf(".") + 1)).append(", functionName = ").append(str5).append(", messages = ").append(str6).toString());
        Object[] objArr = new Object[4];
        objArr[0] = component2;
        Object[] objArr2 = objArr;
        objArr2[1] = str5;
        Object[] objArr3 = objArr2;
        objArr3[2] = Integer.valueOf(i2);
        Object[] objArr4 = objArr3;
        objArr4[3] = str6;
        if (!EventDispatcher.dispatchEvent(this, "ErrorOccurred", objArr4) && screenInitialized) {
            try {
                new Notifier(this);
                new StringBuilder("Error ");
                notifier.ShowMessageDialog(sb2.append(i2).append(": ").append(str6).toString(), str7, str8);
            } catch (Exception e2) {
                int e3 = Log.e(LOG_TAG, String.valueOf(e2));
            }
        }
    }

    public void dispatchErrorOccurredEvent(Component component, String str, int i, Object... objArr) {
        Runnable runnable;
        final int i2 = i;
        final Object[] objArr2 = objArr;
        final Component component2 = component;
        final String str2 = str;
        new Runnable(this) {

            /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
            private /* synthetic */ Form f403hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.f403hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r9;
            }

            public final void run() {
                this.f403hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.ErrorOccurred(component2, str2, i2, ErrorMessages.formatMessage(i2, objArr2));
            }
        };
        runOnUiThread(runnable);
    }

    public void dispatchErrorOccurredEventDialog(Component component, String str, int i, Object... objArr) {
        Runnable runnable;
        final int i2 = i;
        final Object[] objArr2 = objArr;
        final Component component2 = component;
        final String str2 = str;
        new Runnable(this) {

            /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
            private /* synthetic */ Form f404hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.f404hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r9;
            }

            public final void run() {
                StringBuilder sb;
                String formatMessage = ErrorMessages.formatMessage(i2, objArr2);
                new StringBuilder("Error in ");
                this.f404hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.ErrorOccurredDialog(component2, str2, i2, formatMessage, sb.append(str2).toString(), "Dismiss");
            }
        };
        runOnUiThread(runnable);
    }

    public void runtimeFormErrorOccurredEvent(String str, int i, String str2) {
        String str3 = str;
        int i2 = i;
        String str4 = str2;
        int d = Log.d("FORM_RUNTIME_ERROR", "functionName is ".concat(String.valueOf(str3)));
        int d2 = Log.d("FORM_RUNTIME_ERROR", "errorNumber is ".concat(String.valueOf(i2)));
        int d3 = Log.d("FORM_RUNTIME_ERROR", "message is ".concat(String.valueOf(str4)));
        dispatchErrorOccurredEvent(activeForm, str3, i2, str4);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "When checked, there will be a vertical scrollbar on the screen, and the height of the application can exceed the physical height of the device. When unchecked, the application height is constrained to the height of the device.")
    public boolean Scrollable() {
        return this.scrollable;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void Scrollable(boolean z) {
        boolean z2 = z;
        if (this.scrollable != z2 || this.frameLayout == null) {
            this.scrollable = z2;
            recomputeLayout();
            TitleVisible(this.showTitle);
            for (OnOrientationChangeListener onOrientationChange : this.onOrientationChangeListeners) {
                onOrientationChange.onOrientationChange();
            }
        }
    }

    /* access modifiers changed from: private */
    public void recomputeLayout() {
        DrawerLayout drawerLayout2;
        DrawerLayout.DrawerListener drawerListener;
        CoordinatorLayout coordinatorLayout2;
        ViewGroup.LayoutParams layoutParams;
        LinearLayout linearLayout;
        ViewGroup.LayoutParams layoutParams2;
        AppBarLayout appBarLayout2;
        ViewGroup.LayoutParams layoutParams3;
        Toolbar toolbar2;
        ViewGroup.LayoutParams layoutParams4;
        Drawable drawable;
        View.OnClickListener onClickListener;
        FrameLayout frameLayout2;
        ViewGroup.LayoutParams layoutParams5;
        ViewGroup.LayoutParams layoutParams6;
        ViewGroup.LayoutParams layoutParams7;
        ScaledFrameLayout scaledFrameLayout;
        ViewGroup.LayoutParams layoutParams8;
        Runnable runnable;
        FrameLayout frameLayout3;
        int d = Log.d(LOG_TAG, "recomputeLayout called");
        if (this.frameLayout != null) {
            this.frameLayout.removeAllViews();
        }
        new DrawerLayout(this);
        this.drawerLayout = drawerLayout2;
        this.drawerLayout.setDrawerLockMode(1);
        this.drawerLayout.addDrawerListener((DrawerLayout.DrawerListener) null);
        new DrawerLayout.SimpleDrawerListener(this) {
            private /* synthetic */ Form hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onDrawerSlide(View view, float f) {
            }

            public final void onDrawerOpened(View view) {
                View view2 = view;
                boolean access$302 = Form.access$302(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, true);
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.SideMenuOpened();
            }

            public final void onDrawerClosed(View view) {
                View view2 = view;
                boolean access$302 = Form.access$302(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, false);
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.SideMenuClosed();
            }

            public final void onDrawerStateChanged(int i) {
            }
        };
        this.drawerLayout.addDrawerListener(drawerListener);
        new CoordinatorLayout(this);
        this.coordinatorLayout = coordinatorLayout2;
        new CoordinatorLayout.LayoutParams(-1, -1);
        this.coordinatorLayout.setLayoutParams(layoutParams);
        new LinearLayout(this);
        LinearLayout linearLayout2 = linearLayout;
        LinearLayout linearLayout3 = linearLayout2;
        new LinearLayout.LayoutParams(-1, -1);
        linearLayout2.setLayoutParams(layoutParams2);
        linearLayout3.setOrientation(1);
        new AppBarLayout(this);
        this.appBarLayout = appBarLayout2;
        new AppBarLayout.LayoutParams(-1, -2);
        this.appBarLayout.setLayoutParams(layoutParams3);
        new Toolbar(this);
        this.toolbar = toolbar2;
        TypedArray obtainStyledAttributes = getApplicationContext().getTheme().obtainStyledAttributes(new int[]{16843499});
        int dimension = (int) obtainStyledAttributes.getDimension(0, 0.0f);
        obtainStyledAttributes.recycle();
        new Toolbar.LayoutParams(-1, dimension);
        this.toolbar.setLayoutParams(layoutParams4);
        new ColorDrawable(KodularResourcesUtil.getColor(getApplicationContext(), "colorPrimary"));
        this.toolbar.setBackground(drawable);
        UpdateTitlebarItemColor(this.toolbarIconColor);
        this.appBarLayout.addView(this.toolbar);
        linearLayout3.addView(this.appBarLayout);
        setSupportActionBar(this.toolbar);
        new View.OnClickListener(this) {
            private /* synthetic */ Form hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onClick(View view) {
                View view2 = view;
                if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.drawerLayout == null || this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.actionBarDrawerToggle == null || this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.lockedMenu) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.TitlebarBackButtonClicked();
                } else if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.drawerLayout.isDrawerOpen((int) GravityCompat.START)) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.drawerLayout.closeDrawer((int) GravityCompat.START);
                } else {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.drawerLayout.openDrawer((int) GravityCompat.START);
                }
            }
        };
        this.toolbar.setNavigationOnClickListener(onClickListener);
        if (this.scrollable) {
            new ScrollView(this);
            this.frameLayout = frameLayout3;
            if (Build.VERSION.SDK_INT >= 24) {
                ((ScrollView) this.frameLayout).setFillViewport(true);
            }
        } else {
            new FrameLayout(this);
            this.frameLayout = frameLayout2;
        }
        new ViewGroup.LayoutParams(-1, -1);
        this.frameLayout.addView(this.viewLayout.getLayoutManager(), layoutParams5);
        new ViewGroup.LayoutParams(-1, -1);
        this.frameLayout.setLayoutParams(layoutParams6);
        linearLayout3.addView(this.frameLayout);
        this.coordinatorLayout.addView(linearLayout3);
        setBackground(this.frameLayout);
        new ViewGroup.LayoutParams(-1, -1);
        this.drawerLayout.addView(this.coordinatorLayout, layoutParams7);
        if (this.layoutBackup != null) {
            if (this.layoutBackup instanceof AndroidViewComponent) {
                SideMenu((AndroidViewComponent) this.layoutBackup);
            } else if (this.layoutBackup instanceof MakeroidSideMenuLayout) {
                SideMenuLayout((MakeroidSideMenuLayout) this.layoutBackup);
            }
        }
        int d2 = Log.d(LOG_TAG, "About to create a new ScaledFrameLayout");
        new ScaledFrameLayout(this);
        this.scaleLayout = scaledFrameLayout;
        new ViewGroup.LayoutParams(-1, -1);
        this.scaleLayout.addView(this.drawerLayout, layoutParams8);
        setContentView((View) this.scaleLayout);
        this.frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(this);
        this.scaleLayout.requestLayout();
        new Runnable(this) {
            private /* synthetic */ Form hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void run() {
                if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.frameLayout == null || this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.frameLayout.getWidth() == 0 || this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.frameLayout.getHeight() == 0) {
                    boolean post = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.post(this);
                    return;
                }
                if (Form.sCompatibilityMode) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Sizing("Fixed");
                } else {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Sizing("Responsive");
                }
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.ReplayFormOrientation();
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.frameLayout.requestLayout();
            }
        };
        boolean post = this.androidUIHandler.post(runnable);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public int BackgroundColor() {
        return this.backgroundColor;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty
    public void BackgroundColor(int i) {
        this.backgroundColor = i;
        setBackground(this.frameLayout);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The screen background image.")
    public String BackgroundImage() {
        return this.backgroundImagePath;
    }

    @DesignerProperty(defaultValue = "", editorType = "image_asset")
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The screen background image.")
    public void BackgroundImage(String str) {
        StringBuilder sb;
        String str2 = str;
        this.backgroundImagePath = str2 == null ? "" : str2;
        try {
            this.backgroundDrawable = MediaUtil.getBitmapDrawable(this, this.backgroundImagePath);
        } catch (Exception e) {
            new StringBuilder("Unable to load ");
            int e2 = Log.e(LOG_TAG, sb.append(this.backgroundImagePath).toString());
            this.backgroundDrawable = null;
        }
        setBackground(this.frameLayout);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The caption for the form, which apears in the title bar")
    public String Title() {
        if (getSupportActionBar() == null) {
            return getTitle().toString();
        }
        if (getSupportActionBar().getTitle() != null) {
            return getSupportActionBar().getTitle().toString();
        }
        return getTitle().toString();
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public void Title(String str) {
        String str2 = str;
        setTitle(str2);
        this.toolbarTitle = str2;
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle((CharSequence) str2);
        }
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(userVisible = false)
    public void AppId(String str) {
        String str2 = str;
        this.appId = str2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Information about the screen.  It appears when \"About this Application\" is selected from the system menu. Use it to inform people about your app.  In multiple screen apps, each screen has its own AboutScreen info.")
    public String AboutScreen() {
        return this.aboutScreen;
    }

    @DesignerProperty(defaultValue = "", editorType = "textArea")
    @SimpleProperty
    public void AboutScreen(String str) {
        String str2 = str;
        while (str2.contains("<!--")) {
            str2 = str2.replace("<!--", "");
        }
        while (str2.contains("<!-")) {
            str2 = str2.replace("<!-", "");
        }
        this.aboutScreen = str2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The title bar is the top gray bar on the screen. This property reports whether the title bar is visible.")
    public boolean TitleVisible() {
        return this.showTitle;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public void TitleVisible(boolean z) {
        boolean z2 = z;
        if (getSupportActionBar() != null) {
            if (z2) {
                this.appBarLayout.setVisibility(0);
            } else {
                this.appBarLayout.setVisibility(8);
            }
            this.showTitle = z2;
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The status bar is the topmost bar on the screen. This property reports whether the status bar is visible.")
    public boolean ShowStatusBar() {
        return this.showStatusBar;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public void ShowStatusBar(boolean z) {
        boolean z2 = z;
        if (z2 != this.showStatusBar) {
            if (z2) {
                getWindow().addFlags(2048);
                getWindow().clearFlags(1024);
            } else {
                getWindow().addFlags(1024);
                getWindow().clearFlags(2048);
            }
            this.showStatusBar = z2;
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The requested screen orientation, specified as a text value.  Commonly used values are landscape, portrait, sensor, user and unspecified.  See the Android developer documentation for ActivityInfo.Screen_Orientation for the complete list of possible settings.")
    public String ScreenOrientation() {
        switch (getRequestedOrientation()) {
            case -1:
                return "unspecified";
            case 0:
                return "landscape";
            case 1:
                return "portrait";
            case 2:
                return "user";
            case 3:
                return "behind";
            case 4:
                return "sensor";
            case 5:
                return "nosensor";
            case 6:
                return "sensorLandscape";
            case 7:
                return "sensorPortrait";
            case 8:
                return "reverseLandscape";
            case 9:
                return "reversePortrait";
            case 10:
                return "fullSensor";
            default:
                return "unspecified";
        }
    }

    @DesignerProperty(defaultValue = "unspecified", editorType = "screen_orientation")
    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public void ScreenOrientation(String str) {
        String str2 = str;
        if (str2.equalsIgnoreCase("behind")) {
            setRequestedOrientation(3);
        } else if (str2.equalsIgnoreCase("fullSensor")) {
            setRequestedOrientation(10);
        } else if (str2.equalsIgnoreCase("landscape")) {
            setRequestedOrientation(0);
        } else if (str2.equalsIgnoreCase("nosensor")) {
            setRequestedOrientation(5);
        } else if (str2.equalsIgnoreCase("portrait")) {
            setRequestedOrientation(1);
        } else if (str2.equalsIgnoreCase("reverseLandscape")) {
            setRequestedOrientation(8);
        } else if (str2.equalsIgnoreCase("reversePortrait")) {
            setRequestedOrientation(9);
        } else if (str2.equalsIgnoreCase("sensor")) {
            setRequestedOrientation(4);
        } else if (str2.equalsIgnoreCase("sensorLandscape")) {
            setRequestedOrientation(6);
        } else if (str2.equalsIgnoreCase("sensorPortrait")) {
            setRequestedOrientation(7);
        } else if (str2.equalsIgnoreCase("unspecified")) {
            setRequestedOrientation(-1);
        } else if (str2.equalsIgnoreCase("user")) {
            setRequestedOrientation(2);
        } else {
            dispatchErrorOccurredEvent(this, "ScreenOrientation", ErrorMessages.ERROR_INVALID_SCREEN_ORIENTATION, str2);
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A number that encodes how contents of the screen are aligned  horizontally. The choices are: 1 = left aligned, 2 = horizontally centered,  3 = right aligned.")
    public int AlignHorizontal() {
        return this.horizontalAlignment;
    }

    @DesignerProperty(defaultValue = "1", editorType = "horizontal_alignment")
    @SimpleProperty
    public void AlignHorizontal(int i) {
        int i2 = i;
        try {
            this.alignmentSetter.setHorizontalAlignment(i2);
            this.horizontalAlignment = i2;
        } catch (IllegalArgumentException e) {
            dispatchErrorOccurredEvent(this, "HorizontalAlignment", ErrorMessages.ERROR_BAD_VALUE_FOR_HORIZONTAL_ALIGNMENT, Integer.valueOf(i2));
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A number that encodes how the contents of the arrangement are aligned vertically. The choices are: 1 = aligned at the top, 2 = vertically centered, 3 = aligned at the bottom. Vertical alignment has no effect if the screen is scrollable.")
    public int AlignVertical() {
        return this.verticalAlignment;
    }

    @DesignerProperty(defaultValue = "1", editorType = "vertical_alignment")
    @SimpleProperty
    public void AlignVertical(int i) {
        int i2 = i;
        try {
            this.alignmentSetter.setVerticalAlignment(i2);
            this.verticalAlignment = i2;
        } catch (IllegalArgumentException e) {
            dispatchErrorOccurredEvent(this, "VerticalAlignment", ErrorMessages.ERROR_BAD_VALUE_FOR_VERTICAL_ALIGNMENT, Integer.valueOf(i2));
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The animation for switching to another screen. Valid options are default, fade, zoom, slidehorizontal, slidevertical, and none")
    public String OpenScreenAnimation() {
        return this.openAnimType;
    }

    @DesignerProperty(defaultValue = "default", editorType = "screen_animation")
    @SimpleProperty
    public void OpenScreenAnimation(String str) {
        String str2 = str;
        if (str2 == "default" || str2 == "fade" || str2 == "zoom" || str2 == "slidehorizontal" || str2 == "slidevertical" || str2 == "none") {
            this.openAnimType = str2;
            return;
        }
        dispatchErrorOccurredEvent(this, "Screen", ErrorMessages.ERROR_SCREEN_INVALID_ANIMATION, str2);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The animation for closing current screen and returning  to the previous screen. Valid options are default, fade, zoom, slidehorizontal, slidevertical, and none")
    public String CloseScreenAnimation() {
        return this.closeAnimType;
    }

    @DesignerProperty(defaultValue = "default", editorType = "screen_animation")
    @SimpleProperty
    public void CloseScreenAnimation(String str) {
        String str2 = str;
        if (str2 == "default" || str2 == "fade" || str2 == "zoom" || str2 == "slidehorizontal" || str2 == "slidevertical" || str2 == "none") {
            this.closeAnimType = str2;
            return;
        }
        dispatchErrorOccurredEvent(this, "Screen", ErrorMessages.ERROR_SCREEN_INVALID_ANIMATION, str2);
    }

    public String getOpenAnimType() {
        return this.openAnimType;
    }

    @DesignerProperty(defaultValue = "", editorType = "image_asset")
    @SimpleProperty(userVisible = false)
    public void Icon(String str) {
    }

    @DesignerProperty(defaultValue = "1", editorType = "non_negative_integer")
    @SimpleProperty(description = "An integer value which must be incremented each time a new Android Application Package File (APK) is created for the Google Play Store.", userVisible = false)
    public void VersionCode(int i) {
    }

    @DesignerProperty(defaultValue = "1.0", editorType = "string")
    @SimpleProperty(description = "A string which can be changed to allow Google Play Store users to distinguish between different versions of the App.", userVisible = false)
    public void VersionName(String str) {
    }

    @DesignerProperty(defaultValue = "", editorType = "string", propertyType = "advanced")
    @SimpleProperty(description = "Set a custom PackageName for the app", userVisible = false)
    public void PackageName(String str) {
    }

    @DesignerProperty(defaultValue = "Responsive", editorType = "sizing")
    @SimpleProperty(description = "If set to fixed,  screen layouts will be created for a single fixed-size screen and autoscaled. If set to responsive, screen layouts will use the actual resolution of the device.  See the documentation on responsive design in App Inventor for more information. This property appears on Screen1 only and controls the sizing for all screens in the app.", userVisible = false)
    public void Sizing(String str) {
        StringBuilder sb;
        StringBuilder sb2;
        String str2 = str;
        new StringBuilder("Sizing(");
        int d = Log.d(LOG_TAG, sb.append(str2).append(")").toString());
        this.formWidth = (int) (((float) getResources().getDisplayMetrics().widthPixels) / this.deviceDensity);
        this.formHeight = (int) (((float) getResources().getDisplayMetrics().heightPixels) / this.deviceDensity);
        if (str2.equals("Fixed")) {
            sCompatibilityMode = true;
            this.formWidth = (int) (((float) this.formWidth) / this.compatScalingFactor);
            this.formHeight = (int) (((float) this.formHeight) / this.compatScalingFactor);
        } else {
            sCompatibilityMode = false;
        }
        this.scaleLayout.setScale(sCompatibilityMode ? this.compatScalingFactor : 1.0f);
        if (this.frameLayout != null) {
            this.frameLayout.invalidate();
        }
        new StringBuilder("formWidth = ");
        int d2 = Log.d(LOG_TAG, sb2.append(this.formWidth).append(" formHeight = ").append(this.formHeight).toString());
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "If false, lists will be converted to strings using Lisp notation, i.e., as symbols separated by spaces, e.g., (a 1 b2 (c d). If true, lists will appear as in Json or Python, e.g.  [\"a\", 1, \"b\", 2, [\"c\", \"d\"]].  This property appears only in Screen 1, and the value for Screen 1 determines the behavior for all screens. The property defaults to \"false\" meaning that the App Inventor programmer must explicitly set it to \"true\" if JSON/Python syntax is desired. At some point in the future we will alter the system so that new projects are created with this property set to \"true\" by default. Existing projects will not be impacted. The App Inventor programmer can also set it back to \"false\" in newer projects if desired. ", userVisible = false)
    public void ShowListsAsJson(boolean z) {
        showListsAsJson = z;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public boolean ShowListsAsJson() {
        return showListsAsJson;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "This is the display name of the installed application in the phone.If the AppName is blank, it will be set to the name of the project when the project is built.", userVisible = false)
    public void AppName(String str) {
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Screen width (x-size).")
    public int Width() {
        StringBuilder sb;
        new StringBuilder("Form.Width = ");
        int d = Log.d(LOG_TAG, sb.append(this.formWidth).toString());
        return this.formWidth;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Screen height (y-size).")
    public int Height() {
        StringBuilder sb;
        new StringBuilder("Form.Height = ");
        int d = Log.d(LOG_TAG, sb.append(this.formHeight).toString());
        return this.formHeight;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "A URL to use to populate the Tutorial Sidebar while editing a project. Used as a teaching aid.", userVisible = false)
    public void TutorialURL(String str) {
    }

    public static void switchForm(String str) {
        Throwable th;
        String str2 = str;
        if (activeForm != null) {
            activeForm.startNewForm(str2, (Object) null);
            return;
        }
        Throwable th2 = th;
        new IllegalStateException("activeForm is null");
        throw th2;
    }

    public static void switchFormWithStartValue(String str, Object obj) {
        Throwable th;
        String str2 = str;
        Object obj2 = obj;
        int i = Log.i(LOG_TAG, "Open another screen with start value:".concat(String.valueOf(str2)));
        if (activeForm != null) {
            activeForm.startNewForm(str2, obj2);
            return;
        }
        Throwable th2 = th;
        new IllegalStateException("activeForm is null");
        throw th2;
    }

    /* access modifiers changed from: protected */
    public void startNewForm(String str, Object obj) {
        Intent intent;
        String str2;
        String str3 = str;
        Object obj2 = obj;
        int i = Log.i(LOG_TAG, "startNewForm:".concat(String.valueOf(str3)));
        new Intent();
        Intent intent2 = intent;
        int i2 = Log.i(LOG_TAG, "Trying to get package name...");
        String packageName = getPackageName();
        int i3 = Log.i(LOG_TAG, "Package Name is ".concat(String.valueOf(packageName)));
        PackageManager packageManager = getPackageManager();
        String replaceAll = packageManager.resolveActivity(packageManager.getLaunchIntentForPackage(packageName), 65536).activityInfo.name.replaceAll("Screen1", str3);
        int i4 = Log.i(LOG_TAG, "Setting Intent Class to ".concat(String.valueOf(replaceAll)));
        Intent className = intent2.setClassName(this, replaceAll);
        String str4 = obj2 == null ? "open another screen" : "open another screen with start value";
        if (obj2 != null) {
            int i5 = Log.i(LOG_TAG, "StartNewForm about to JSON encode:".concat(String.valueOf(obj2)));
            str2 = jsonEncodeForForm(obj2, str4);
            int i6 = Log.i(LOG_TAG, "StartNewForm got JSON encoding:".concat(String.valueOf(str2)));
        } else {
            str2 = "";
        }
        Intent putExtra = intent2.putExtra(ARGUMENT_NAME, str2);
        this.nextFormName = str3;
        int i7 = Log.i(LOG_TAG, "about to start new form".concat(String.valueOf(replaceAll)));
        try {
            int i8 = Log.i(LOG_TAG, "startNewForm starting activity:".concat(String.valueOf(intent2)));
            startActivityForResult(intent2, 1);
            AnimationUtil.ApplyOpenScreenAnimation(this, this.openAnimType);
        } catch (ActivityNotFoundException e) {
            dispatchErrorOccurredEvent(this, str4, ErrorMessages.ERROR_SCREEN_NOT_FOUND, replaceAll);
        }
    }

    public String getKodularPackageName() {
        String packageName = getPackageName();
        try {
            PackageManager packageManager = getPackageManager();
            return packageManager.resolveActivity(packageManager.getLaunchIntentForPackage(packageName), 65536).activityInfo.name.replaceAll(".Screen1", "");
        } catch (NullPointerException e) {
            return packageName;
        }
    }

    public boolean isCustomPackage() {
        return !getPackageName().equals(getKodularPackageName());
    }

    public static String getApplicationName(Context context) {
        Context context2 = context;
        ApplicationInfo applicationInfo = context2.getApplicationInfo();
        ApplicationInfo applicationInfo2 = applicationInfo;
        int i = applicationInfo.labelRes;
        return i == 0 ? applicationInfo2.nonLocalizedLabel.toString() : context2.getString(i);
    }

    public String getInstalledFrom() {
        String installerPackageName = $context().getPackageManager().getInstallerPackageName($context().getPackageName());
        boolean z = true;
        switch (installerPackageName.hashCode()) {
            case -1859733809:
                if (installerPackageName.equals("com.amazon.venezia")) {
                    z = true;
                    break;
                }
                break;
            case -1225090538:
                if (installerPackageName.equals("com.sec.android.app.samsungapps")) {
                    z = true;
                    break;
                }
                break;
            case -1046965711:
                if (installerPackageName.equals("com.android.vending")) {
                    z = false;
                    break;
                }
                break;
            case 565251532:
                if (installerPackageName.equals("com.google.android.feedback")) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
            case true:
                return "Google Play";
            case true:
                return "Amazon Appstore";
            case true:
                return "Samsung App Store";
            default:
                return "Unknown";
        }
    }

    public boolean isInstalledThruStore() {
        return !getInstalledFrom().equals("Unknown");
    }

    public String getAppId() {
        return this.appId;
    }

    protected static String jsonEncodeForForm(Object obj, String str) {
        StringBuilder sb;
        Object obj2 = obj;
        String str2 = str;
        String str3 = "";
        new StringBuilder("jsonEncodeForForm -- creating JSON representation:");
        int i = Log.i(LOG_TAG, sb.append(obj2.toString()).toString());
        try {
            str3 = JsonUtil.getJsonRepresentation(obj2);
            int i2 = Log.i(LOG_TAG, "jsonEncodeForForm -- got JSON representation:".concat(String.valueOf(str3)));
        } catch (JSONException e) {
            Form form = activeForm;
            form.dispatchErrorOccurredEvent(form, str2, ErrorMessages.ERROR_SCREEN_BAD_VALUE_FOR_SENDING, obj2.toString());
        }
        return str3;
    }

    @SimpleEvent(description = "Event raised when another screen has closed and control has returned to this screen.")
    public void OtherScreenClosed(String str, Object obj) {
        StringBuilder sb;
        String str2 = str;
        Object obj2 = obj;
        new StringBuilder("Form ");
        int i = Log.i(LOG_TAG, sb.append(this.formName).append(" OtherScreenClosed, otherScreenName = ").append(str2).append(", result = ").append(obj2.toString()).toString());
        Object[] objArr = new Object[2];
        objArr[0] = str2;
        Object[] objArr2 = objArr;
        objArr2[1] = obj2;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OtherScreenClosed", objArr2);
    }

    public HandlesEventDispatching getDispatchDelegate() {
        return this;
    }

    public Activity $context() {
        return this;
    }

    public Form $form() {
        return this;
    }

    public void $add(AndroidViewComponent androidViewComponent) {
        this.viewLayout.add(androidViewComponent);
    }

    public float deviceDensity() {
        return this.deviceDensity;
    }

    public float compatScalingFactor() {
        return this.compatScalingFactor;
    }

    public void setChildWidth(AndroidViewComponent androidViewComponent, int i) {
        StringBuilder sb;
        Runnable runnable;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        int i2 = i;
        int Width = Width();
        int i3 = Width;
        if (Width == 0) {
            final AndroidViewComponent androidViewComponent3 = androidViewComponent2;
            final int i4 = i2;
            new Runnable(this) {

                /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
                private /* synthetic */ Form f406hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.f406hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
                }

                public final void run() {
                    System.err.println("(Form)Width not stable yet... trying again");
                    this.f406hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setChildWidth(androidViewComponent3, i4);
                }
            };
            boolean postDelayed = this.androidUIHandler.postDelayed(runnable, 100);
        }
        PrintStream printStream = System.err;
        new StringBuilder("Form.setChildWidth(): width = ");
        printStream.println(sb.append(i2).append(" parent Width = ").append(i3).append(" child = ").append(androidViewComponent2).toString());
        if (i2 <= -1000) {
            i2 = (i3 * (-(i2 + 1000))) / 100;
        }
        androidViewComponent2.setLastWidth(i2);
        ViewUtil.setChildWidthForVerticalLayout(androidViewComponent2.getView(), i2);
    }

    public void setChildHeight(AndroidViewComponent androidViewComponent, int i) {
        Runnable runnable;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        int i2 = i;
        if (Height() == 0) {
            final AndroidViewComponent androidViewComponent3 = androidViewComponent2;
            final int i3 = i2;
            new Runnable(this) {

                /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
                private /* synthetic */ Form f405hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.f405hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
                }

                public final void run() {
                    System.err.println("(Form)Height not stable yet... trying again");
                    this.f405hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setChildHeight(androidViewComponent3, i3);
                }
            };
            boolean postDelayed = this.androidUIHandler.postDelayed(runnable, 100);
        }
        if (i2 <= -1000) {
            i2 = (Height() * (-(i2 + 1000))) / 100;
        }
        androidViewComponent2.setLastHeight(i2);
        ViewUtil.setChildHeightForVerticalLayout(androidViewComponent2.getView(), i2);
    }

    public static Form getActiveForm() {
        return activeForm;
    }

    public static String getStartText() {
        Throwable th;
        if (activeForm != null) {
            return activeForm.startupValue;
        }
        Throwable th2 = th;
        new IllegalStateException("activeForm is null");
        throw th2;
    }

    public static Object getStartValue() {
        Throwable th;
        if (activeForm != null) {
            return decodeJSONStringForForm(activeForm.startupValue, "get start value");
        }
        Throwable th2 = th;
        new IllegalStateException("activeForm is null");
        throw th2;
    }

    public static void finishActivity() {
        Throwable th;
        if (activeForm != null) {
            activeForm.closeForm((Intent) null);
            return;
        }
        Throwable th2 = th;
        new IllegalStateException("activeForm is null");
        throw th2;
    }

    public static void finishActivityWithResult(Object obj) {
        Throwable th;
        Intent intent;
        Object obj2 = obj;
        if (activeForm == null) {
            Throwable th2 = th;
            new IllegalStateException("activeForm is null");
            throw th2;
        } else if (activeForm instanceof ReplForm) {
            ((ReplForm) activeForm).setResult(obj2);
            activeForm.closeForm((Intent) null);
        } else {
            String jsonEncodeForForm = jsonEncodeForForm(obj2, "close screen with value");
            new Intent();
            Intent intent2 = intent;
            Intent putExtra = intent2.putExtra(RESULT_NAME, jsonEncodeForForm);
            activeForm.closeForm(intent2);
        }
    }

    public static void finishActivityWithTextResult(String str) {
        Throwable th;
        Intent intent;
        String str2 = str;
        if (activeForm != null) {
            new Intent();
            Intent intent2 = intent;
            Intent putExtra = intent2.putExtra(RESULT_NAME, str2);
            activeForm.closeForm(intent2);
            return;
        }
        Throwable th2 = th;
        new IllegalStateException("activeForm is null");
        throw th2;
    }

    /* access modifiers changed from: protected */
    public void closeForm(Intent intent) {
        Intent intent2 = intent;
        if (intent2 != null) {
            setResult(-1, intent2);
        }
        finish();
        AnimationUtil.ApplyCloseScreenAnimation(this, this.closeAnimType);
    }

    public static void finishApplication() {
        Throwable th;
        if (activeForm != null) {
            activeForm.closeApplicationFromBlocks();
            return;
        }
        Throwable th2 = th;
        new IllegalStateException("activeForm is null");
        throw th2;
    }

    /* access modifiers changed from: protected */
    public void closeApplicationFromBlocks() {
        closeApplication();
    }

    private void closeApplication() {
        applicationIsBeingClosed = true;
        finish();
        if (this.formName.equals("Screen1")) {
            System.exit(0);
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        Menu menu2 = menu;
        boolean onPrepareOptionsMenu = super.onPrepareOptionsMenu(menu2);
        menu2.clear();
        this.customMenu = menu2;
        this.customActionBarIcon = menu2;
        addAboutInfoToMenu(this.customMenu);
        MenuInitialize();
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        Menu menu2 = menu;
        return this.showOptionsMenu;
    }

    @SimpleEvent(description = "Event to detect when the menu has loaded. Set here your blocks like TitleBarIcon or AddMenuItem.")
    public void MenuInitialize() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "MenuInitialize", new Object[0]);
    }

    @DesignerProperty(defaultValue = "About this application", editorType = "string")
    @SimpleProperty(description = "Define the title of the about application option.")
    public void AboutScreenTitle(String str) {
        String str2 = str;
        if (str2 == null || str2.isEmpty()) {
            this.aboutScreenTitle = "About this application";
            return;
        }
        this.aboutScreenTitle = str2;
    }

    public void addAboutInfoToMenu(Menu menu) {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener;
        new MenuItem.OnMenuItemClickListener(this) {
            private /* synthetic */ Form hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final boolean onMenuItemClick(MenuItem menuItem) {
                MenuItem menuItem2 = menuItem;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.showAboutApplicationNotification();
                return true;
            }
        };
        MenuItem onMenuItemClickListener2 = menu.add(0, 0, 2, this.aboutScreenTitle).setOnMenuItemClickListener(onMenuItemClickListener);
    }

    @SimpleFunction(description = "Add a new item to the menu. Use the 'make a list' block.")
    public void AddMenuItem(YailList yailList) {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener;
        String[] stringArray = yailList.toStringArray();
        if (this.customMenu != null) {
            int length = stringArray.length;
            for (int i = 0; i < length; i++) {
                String str = stringArray[i];
                new MenuItem.OnMenuItemClickListener(this) {
                    private /* synthetic */ Form hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                    {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                    }

                    public final boolean onMenuItemClick(MenuItem menuItem) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.MenuItemSelected(menuItem.getTitle().toString());
                        return true;
                    }
                };
                MenuItem onMenuItemClickListener2 = this.customMenu.add(0, 0, 0, str).setOnMenuItemClickListener(onMenuItemClickListener);
            }
        }
    }

    @SimpleFunction(description = "Add a new item with a icon on the left side to the menu. This function does not use the make a list block. If you want more items with icon then use this block again.")
    public void AddMenuItemWithIcon(String str, String str2) {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener;
        String str3 = str;
        String str4 = str2;
        BitmapDrawable bitmapDrawable = null;
        if (this.customMenu != null) {
            try {
                bitmapDrawable = MediaUtil.getBitmapDrawable(this, str4);
            } catch (Exception e) {
                int e2 = Log.e(LOG_TAG, "AddMenuItemWithIcon: ".concat(String.valueOf(e)));
            }
            if (bitmapDrawable != null) {
                new MenuItem.OnMenuItemClickListener(this) {
                    private /* synthetic */ Form hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                    {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                    }

                    public final boolean onMenuItemClick(MenuItem menuItem) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.MenuItemSelected(menuItem.getTitle().toString());
                        return true;
                    }
                };
                MenuItem onMenuItemClickListener2 = this.customMenu.add(0, 0, 0, menuIconWithText(bitmapDrawable, str3)).setOnMenuItemClickListener(onMenuItemClickListener);
            }
        }
    }

    private CharSequence menuIconWithText(Drawable drawable, String str) {
        SpannableString spannableString;
        Object obj;
        Drawable drawable2 = drawable;
        drawable2.setBounds(0, 0, 75, 75);
        new SpannableString("       ".concat(String.valueOf(str)));
        SpannableString spannableString2 = spannableString;
        new ImageSpan(drawable2, 1);
        spannableString2.setSpan(obj, 0, 1, 33);
        return spannableString2;
    }

    @SimpleEvent(description = "Event to detect when a menu item has been selected.")
    public void MenuItemSelected(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "MenuItemSelected", str);
    }

    @SimpleFunction(description = "Reset the menu back to its default")
    public void ResetMenu() {
        if (this.customMenu != null) {
            this.customMenu.clear();
            addAboutInfoToMenu(this.customMenu);
            boolean onPrepareOptionsMenu = super.onPrepareOptionsMenu(this.customMenu);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        MenuItem menuItem2 = menuItem;
        if (this.actionBarDrawerToggle != null && this.actionBarDrawerToggle.onOptionsItemSelected(menuItem2)) {
            return true;
        }
        for (OnOptionsItemSelectedListener onOptionsItemSelected : this.onOptionsItemSelectedListeners) {
            if (onOptionsItemSelected.onOptionsItemSelected(menuItem2)) {
                return true;
            }
        }
        return false;
    }

    @SimpleEvent(description = "Event to detect when a menu item has been selected.")
    public void TitlebarBackButtonClicked() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "TitlebarBackButtonClicked", new Object[0]);
    }

    /* access modifiers changed from: package-private */
    public void setYandexTranslateTagline() {
        this.yandexTranslateTagline = "<p><small>Language translation powered by Yandex.Translate</small></p>";
    }

    /* access modifiers changed from: private */
    public void showAboutApplicationNotification() {
        StringBuilder sb;
        String str = "<p><small><em>Created with Kodular<br><a href=\"https://www.kodular.io\">kodular.io</a></em></small></p>";
        new StringBuilder();
        try {
            Notifier.aboutThisApp(this, sb.append(this.aboutScreen).append(this.billing.hasBrandingRemoved() ? "" : str).append(this.yandexTranslateTagline).toString().replaceAll("\\n", "<br>"), this.aboutScreenTitle, getString(17039370), this.aboutThisAppBackgroundColor, this.aboutThisAppLightTheme);
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Show the dialog which shows when pressing the \"About This Application\" button in the menu.")
    public void ShowAboutApplication() {
        showAboutApplicationNotification();
    }

    public void clear() {
        this.viewLayout.getLayoutManager().removeAllViews();
        if (this.frameLayout != null) {
            this.frameLayout.removeAllViews();
            this.frameLayout = null;
        }
        defaultPropertyValues();
        this.onStopListeners.clear();
        this.onNewIntentListeners.clear();
        this.onResumeListeners.clear();
        this.onPauseListeners.clear();
        this.onDestroyListeners.clear();
        this.onInitializeListeners.clear();
        this.onCreateOptionsMenuListeners.clear();
        this.onOptionsItemSelectedListeners.clear();
        this.onOrientationChangeListeners.clear();
        this.onCreateListeners.clear();
        screenInitialized = false;
        for (OnClearListener onClear : this.onClearListeners) {
            onClear.onClear();
        }
        this.onClearListeners.clear();
        System.err.println("Form.clear() About to do moby GC!");
        System.gc();
        this.dimChanges.clear();
    }

    public void deleteComponent(Object obj) {
        Object obj2 = obj;
        if (obj2 instanceof OnStopListener) {
            boolean remove = this.onStopListeners.remove(obj2);
        }
        if (obj2 instanceof OnNewIntentListener) {
            boolean remove2 = this.onNewIntentListeners.remove(obj2);
        }
        if (obj2 instanceof OnResumeListener) {
            boolean remove3 = this.onResumeListeners.remove(obj2);
        }
        if (obj2 instanceof OnPauseListener) {
            boolean remove4 = this.onPauseListeners.remove(obj2);
        }
        if (obj2 instanceof OnDestroyListener) {
            boolean remove5 = this.onDestroyListeners.remove(obj2);
        }
        if (obj2 instanceof OnInitializeListener) {
            boolean remove6 = this.onInitializeListeners.remove(obj2);
        }
        if (obj2 instanceof OnCreateOptionsMenuListener) {
            boolean remove7 = this.onCreateOptionsMenuListeners.remove(obj2);
        }
        if (obj2 instanceof OnOptionsItemSelectedListener) {
            boolean remove8 = this.onOptionsItemSelectedListeners.remove(obj2);
        }
        if (obj2 instanceof Deleteable) {
            ((Deleteable) obj2).onDelete();
        }
        if (obj2 instanceof OnOrientationChangeListener) {
            boolean remove9 = this.onOrientationChangeListeners.remove(obj2);
        }
        if (obj2 instanceof OnCreateListener) {
            boolean remove10 = this.onCreateListeners.remove(obj2);
        }
    }

    public void dontGrabTouchEventsForComponent() {
        this.frameLayout.requestDisallowInterceptTouchEvent(true);
    }

    /* access modifiers changed from: protected */
    public boolean toastAllowed() {
        long nanoTime = System.nanoTime();
        long j = nanoTime;
        if (nanoTime <= this.lastToastTime + minimumToastWait) {
            return false;
        }
        this.lastToastTime = j;
        return true;
    }

    public void callInitialize(Object obj) throws Throwable {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        Object obj2 = obj;
        try {
            OnCompanionRefresh();
            Method method = obj2.getClass().getMethod("Initialize", (Class[]) null);
            try {
                new StringBuilder("calling Initialize method for Object ");
                int i = Log.i(LOG_TAG, sb3.append(obj2.toString()).toString());
                Object invoke = method.invoke(obj2, (Object[]) null);
            } catch (InvocationTargetException e) {
                InvocationTargetException invocationTargetException = e;
                new StringBuilder("invoke exception: ");
                int i2 = Log.i(LOG_TAG, sb2.append(invocationTargetException.getMessage()).toString());
                throw invocationTargetException.getTargetException();
            }
        } catch (SecurityException e2) {
            new StringBuilder("Security exception ");
            int i3 = Log.i(LOG_TAG, sb.append(e2.getMessage()).toString());
        } catch (NoSuchMethodException e3) {
        }
    }

    public synchronized Bundle fullScreenVideoAction(int i, VideoPlayer videoPlayer, Object obj) {
        Bundle performAction;
        int i2 = i;
        VideoPlayer videoPlayer2 = videoPlayer;
        Object obj2 = obj;
        synchronized (this) {
            performAction = this.fullScreenVideoUtil.performAction(i2, videoPlayer2, obj2);
        }
        return performAction;
    }

    private void setBackground(View view) {
        View view2 = view;
        ColorDrawable colorDrawable = this.backgroundDrawable;
        if (this.backgroundImagePath.equals("") || colorDrawable == null) {
            ColorDrawable colorDrawable2 = r6;
            ColorDrawable colorDrawable3 = new ColorDrawable(this.backgroundColor != 0 ? this.backgroundColor : -1);
            colorDrawable = colorDrawable2;
        } else {
            if (this.backgroundDrawable.getConstantState() != null) {
                colorDrawable = this.backgroundDrawable.getConstantState().newDrawable();
            }
            colorDrawable.setColorFilter(this.backgroundColor != 0 ? this.backgroundColor : -1, PorterDuff.Mode.DST_OVER);
        }
        ViewUtil.setBackgroundImage(view2, colorDrawable);
        view2.invalidate();
    }

    public static boolean getCompatibilityMode() {
        return sCompatibilityMode;
    }

    @SimpleFunction(description = "Move task to back. That means it will minimize your current app.")
    public void MoveTaskToBack() {
        boolean moveTaskToBack = moveTaskToBack(true);
    }

    @SimpleProperty(description = "Set title bar color")
    public void TitleBarColor(int i) {
        this.titleBarColor = i;
        if (getSupportActionBar() != null) {
            ActionBar supportActionBar = getSupportActionBar();
            ColorDrawable colorDrawable = r6;
            ColorDrawable colorDrawable2 = new ColorDrawable(this.titleBarColor != 0 ? this.titleBarColor : -1);
            supportActionBar.setBackgroundDrawable(colorDrawable);
        }
    }

    @SimpleProperty
    public int TitleBarColor() {
        return this.titleBarColor;
    }

    @SimpleProperty(description = "Set status bar color. This will work starting from API Level 21 (Android Lollipop")
    public void StatusBarColor(int i) {
        int i2 = i;
        this.statusbarColor = i2;
        if (Build.VERSION.SDK_INT >= 21) {
            KodularHelper.setStatusBarColor(this, i2 == 0 ? 0 : i2);
        } else {
            int w = Log.w(LOG_TAG, "Sorry, setStatusBarColor is not available for API Level < 21");
        }
    }

    @SimpleProperty
    public int StatusBarColor() {
        return this.statusbarColor;
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty(description = "Set navigation bar color. This will work starting from API Level 21 (Android Lollipop)")
    public void NavigationBarColor(int i) {
        int i2 = i;
        if (Build.VERSION.SDK_INT >= 21) {
            KodularHelper.setNavigationBarColor(this, i2);
        } else {
            int w = Log.w(LOG_TAG, "Sorry, setNavigationBarColor is not available for API Level < 21");
        }
    }

    @SimpleProperty
    public int NavigationBarColor() {
        if (Build.VERSION.SDK_INT >= 21) {
            return getWindow().getNavigationBarColor();
        }
        return -16777216;
    }

    @SimpleFunction(description = "This block will returns the version name")
    public String VersionName() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
            return "0";
        }
    }

    @SimpleFunction(description = "This block will returns the version code")
    public int VersionCode() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
            return 0;
        }
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "Set the TitleBar's subtitle.")
    public void TitleBarSubTitle(String str) {
        String str2 = str;
        this.toolbarSubTitle = str2;
        if (getSupportActionBar() != null) {
            getSupportActionBar().setSubtitle((CharSequence) TextViewUtil.fromHtml(str2));
        }
    }

    @SimpleProperty(description = "Get the TitleBar's subtitle.")
    public String TitleBarSubTitle() {
        if (getSupportActionBar() == null || getSupportActionBar().getSubtitle() == null) {
            return this.formName != null ? this.formName : "Screen1";
        }
        return getSupportActionBar().getSubtitle().toString();
    }

    @SimpleFunction(description = "Show the keyboard")
    public void ShowKeyboard() {
        Throwable th;
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if ($assertionsDisabled || inputMethodManager != null) {
            inputMethodManager.toggleSoftInput(2, 1);
            return;
        }
        Throwable th2 = th;
        new AssertionError();
        throw th2;
    }

    @SimpleFunction(description = "Hide the keyboard.")
    public void HideKeyboard() {
        Throwable th;
        View currentFocus = getCurrentFocus();
        FrameLayout frameLayout2 = currentFocus;
        if (currentFocus == null) {
            frameLayout2 = this.frameLayout;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if ($assertionsDisabled || inputMethodManager != null) {
            boolean hideSoftInputFromWindow = inputMethodManager.hideSoftInputFromWindow(frameLayout2.getWindowToken(), 0);
            return;
        }
        Throwable th2 = th;
        new AssertionError();
        throw th2;
    }

    @SimpleProperty(description = "Returns the status of the keyboard. If the keyboard is visible then the result is true.")
    public boolean isKeyboardVisible() {
        return this.keyboardReallyShown;
    }

    @SimpleFunction(description = "Add a new action icon to the TitleBar. You will see a toast message on a long click with your choosen name. Add this block to the \"MenuInitialize\" event.")
    public void AddTitleBarIcon(String str, String str2) {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener;
        String str3 = str;
        String str4 = str2;
        BitmapDrawable bitmapDrawable = null;
        try {
            bitmapDrawable = MediaUtil.getBitmapDrawable(this, str3);
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, "AddTitleBarIcon: ".concat(String.valueOf(e)));
        }
        if (bitmapDrawable != null && this.customActionBarIcon != null && this.customActionBarIcon.size() < 3) {
            MenuItem add = this.customActionBarIcon.add(0, 24072017, 0, str4);
            MenuItem menuItem = add;
            add.setShowAsAction(2);
            MenuItem icon = menuItem.setIcon(bitmapDrawable);
            final String str5 = str3;
            final String str6 = str4;
            new MenuItem.OnMenuItemClickListener(this) {
                private /* synthetic */ Form hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
                }

                public final boolean onMenuItemClick(MenuItem menuItem) {
                    MenuItem menuItem2 = menuItem;
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.TitleBarIconSelected(str5, str6);
                    return true;
                }
            };
            MenuItem onMenuItemClickListener2 = menuItem.setOnMenuItemClickListener(onMenuItemClickListener);
        }
    }

    @SimpleEvent(description = "The event returns the 'icon' or 'name' of the selected icon.")
    public void TitleBarIconSelected(String str, String str2) {
        Object[] objArr = new Object[2];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "TitleBarIconSelected", objArr2);
    }

    @SimpleFunction(description = "Remove all added action icons from the TitleBar.")
    public void RemoveTitleBarIcons() {
        if (this.customActionBarIcon != null) {
            Menu menu = this.customActionBarIcon;
            Menu menu2 = menu;
            int size = menu.size();
            for (int i = 0; i < size; i++) {
                menu2.removeItem(24072017);
            }
        }
    }

    @SimpleProperty(description = "Set a custom color for the TitleBar text.")
    public void TitlebarTextColor(int i) {
        int i2 = i;
        this.titleBarTextColor = i2;
        titleBarTextColorHelper(i2);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Return the text color from the TitleBar.")
    public int TitlebarTextColor() {
        return this.titleBarTextColor;
    }

    @SimpleProperty(description = "If true it will show in the TitleBar a back button only if no side menu was added. If a side menu was added it will remove the ‘hamburger’-menu icon but the side menu can still be opened.")
    public void ShowTitlebarBackButton(boolean z) {
        boolean z2 = z;
        this.stateBackButton = z2;
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(z2);
        }
        UpdateTitlebarItemColor(this.toolbarIconColor);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Return the state of titlebar back button.")
    public boolean ShowTitlebarBackButton() {
        return this.stateBackButton;
    }

    @SimpleEvent(description = "When the activity enters the Resumed state, it comes to the foreground, and then the system invokes this event.")
    public void OnAppResume() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnAppResume", new Object[0]);
    }

    @SimpleEvent(description = "The system calls this method as the first indication that the user is leaving your activity (though it does not always mean the activity is being destroyed).")
    public void OnAppPause() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnAppPause", new Object[0]);
    }

    @SimpleEvent(description = "When your activity is no longer visible to the user, it has entered the Stopped state, and the system invokes this event.")
    public void OnAppStop() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnAppStop", new Object[0]);
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(userVisible = false)
    public void ShowOptionsMenu(boolean z) {
        boolean z2 = z;
        this.showOptionsMenu = z2;
    }

    @SimpleFunction(description = "Sets information describing the task with this activity for presentation inside the Recents System UI. You will see the settings if the device API is >= 21 and you minimize the app.")
    public void TaskDescription(String str, int i) {
        String str2 = str;
        int i2 = i;
        if (Build.VERSION.SDK_INT >= 21) {
            KodularHelper.setAppBackgroundTaskInfo(this, str2, i2);
        } else {
            int w = Log.w(LOG_TAG, "Sorry, setAppBackgroundTaskInfo is not available for API Level < 21");
        }
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean", propertyType = "experimental")
    @SimpleProperty
    public void ShowNavBar(boolean z) {
        View.OnSystemUiVisibilityChangeListener onSystemUiVisibilityChangeListener;
        boolean z2 = z;
        this.showNavBar = z2;
        View decorView = getWindow().getDecorView();
        View view = decorView;
        decorView.setSystemUiVisibility(VisibilityHelper(z2));
        final View view2 = view;
        final boolean z3 = z2;
        new View.OnSystemUiVisibilityChangeListener(this) {

            /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
            private /* synthetic */ Form f407hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.f407hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
            }

            public final void onSystemUiVisibilityChange(int i) {
                int i2 = i;
                view2.setSystemUiVisibility(this.f407hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.VisibilityHelper(z3));
            }
        };
        view.setOnSystemUiVisibilityChangeListener(onSystemUiVisibilityChangeListener);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Show/Hide Navigation Bar")
    public boolean ShowNavBar() {
        return this.showNavBar;
    }

    public int VisibilityHelper(boolean z) {
        int i;
        if (z) {
            i = 0;
        } else if (Build.VERSION.SDK_INT < 19) {
            i = 2;
        } else {
            i = 4098;
        }
        return i;
    }

    @SimpleFunction(description = "Remove a first created side menu. This block will be usefull if you need to update a side menu dynamically. You can use this block too to test a side menu in the companion. Add then this block above of the 'Side Menu' block.")
    public void RemoveSideMenu() {
        if (this.drawerLayout != null && this.actionBarDrawerToggle != null && getSupportActionBar() != null) {
            try {
                this.drawerLayout.removeViewAt(1);
                this.actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                this.layoutBackup = null;
                this.actionBarDrawerToggle = null;
                UpdateTitlebarItemColor(this.toolbarIconColor);
            } catch (Exception e) {
                int e2 = Log.e(LOG_TAG, String.valueOf(e));
            }
        }
    }

    @SimpleFunction(description = "Use this block to lock the side menu. This means the user can not open the side menu until the side menu  unlock block is used.")
    public void LockSideMenu() {
        if (this.drawerLayout != null && this.actionBarDrawerToggle != null && getSupportActionBar() != null) {
            this.lockedMenu = true;
            this.drawerLayout.setDrawerLockMode(1);
            this.actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    @SimpleFunction(description = "Use this block to unlock the side menu. This means the user can now open again the side menu.")
    public void UnlockSideMenu() {
        if (this.drawerLayout != null && this.actionBarDrawerToggle != null && getSupportActionBar() != null) {
            this.lockedMenu = false;
            this.drawerLayout.setDrawerLockMode(0);
            this.actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @SimpleFunction(description = "Create a Side Menu. Set to \"layout\" your layout that will be then your side menu. Use as example a vertical arrangement. Your choosen layout will be then removed from the screen and only visible in the side menu.\"Information\": This block works on companion only if you add a side menu on button click.Don’t add it in companion on \"screen initialize event\". Else the companion will crash.Do NOT use this block with the Side Menu Layout component")
    public void SideMenu(AndroidViewComponent androidViewComponent) {
        StringBuilder sb;
        DrawerLayout.LayoutParams layoutParams;
        View.OnTouchListener onTouchListener;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        ViewGroup viewGroup = (ViewGroup) androidViewComponent2.getView();
        try {
            this.layoutBackup = androidViewComponent2;
            ((ViewGroup) viewGroup.getParent()).removeView(viewGroup);
            new DrawerLayout.LayoutParams(KodularUnitUtil.DpToPixels((Context) this, 296), -1);
            this.f49lp = layoutParams;
            this.f49lp.gravity = GravityCompat.START;
            viewGroup.setLayoutParams(this.f49lp);
            new View.OnTouchListener(this) {
                private /* synthetic */ Form hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                @SuppressLint({"ClickableViewAccessibility"})
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    View view2 = view;
                    MotionEvent motionEvent2 = motionEvent;
                    return true;
                }
            };
            viewGroup.setOnTouchListener(onTouchListener);
            if (this.drawerLayout != null) {
                this.drawerLayout.addView(viewGroup);
                this.drawerLayout.setDrawerLockMode(0);
                this.drawerLayout.invalidate();
                AddDrawerSync();
            }
        } catch (Exception e) {
            new StringBuilder();
            int w = Log.w(LOG_TAG, sb.append(e.getMessage()).toString());
            this.layoutBackup = null;
        }
    }

    public void SideMenuLayout(MakeroidSideMenuLayout makeroidSideMenuLayout) {
        DrawerLayout.LayoutParams layoutParams;
        View.OnTouchListener onTouchListener;
        MakeroidSideMenuLayout makeroidSideMenuLayout2 = makeroidSideMenuLayout;
        ViewGroup viewGroup = (ViewGroup) makeroidSideMenuLayout2.getView();
        this.layoutBackup = makeroidSideMenuLayout2;
        if (viewGroup.getParent() != null) {
            ((ViewGroup) viewGroup.getParent()).removeView(viewGroup);
        }
        new DrawerLayout.LayoutParams(KodularUnitUtil.DpToPixels((Context) this, 296), -1);
        this.f49lp = layoutParams;
        this.f49lp.gravity = GravityCompat.START;
        viewGroup.setLayoutParams(this.f49lp);
        new View.OnTouchListener(this) {
            private /* synthetic */ Form hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            @SuppressLint({"ClickableViewAccessibility"})
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                View view2 = view;
                MotionEvent motionEvent2 = motionEvent;
                return true;
            }
        };
        viewGroup.setOnTouchListener(onTouchListener);
        if (this.drawerLayout != null) {
            this.drawerLayout.addView(viewGroup);
            this.drawerLayout.setDrawerLockMode(0);
            this.drawerLayout.invalidate();
            AddDrawerSync();
        }
    }

    public void AddDrawerSync() {
        ActionBarDrawerToggle actionBarDrawerToggle2;
        ActionBarDrawerToggle actionBarDrawerToggle3 = actionBarDrawerToggle2;
        new ActionBarDrawerToggle(this, this, this.drawerLayout) {
            private /* synthetic */ Form hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r10;
            }

            public final void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public final void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
            }
        };
        this.actionBarDrawerToggle = actionBarDrawerToggle3;
        this.actionBarDrawerToggle.syncState();
        this.actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        if (this.drawerLayout != null) {
            this.drawerLayout.addDrawerListener(this.actionBarDrawerToggle);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        UpdateTitlebarItemColor(this.toolbarIconColor);
    }

    @SimpleFunction(description = "If you had set your side menu then you can use this block to open it as example with a button click.")
    public void SideMenuOpen() {
        if (this.actionBarDrawerToggle != null && this.drawerLayout != null) {
            this.drawerLayout.openDrawer((int) GravityCompat.START);
        }
    }

    @SimpleFunction(description = "If you had set your side menu then you can use this block to close it as example with a button click.")
    public void SideMenuClose() {
        if (this.actionBarDrawerToggle != null && this.drawerLayout != null) {
            this.drawerLayout.closeDrawer((int) GravityCompat.START);
        }
    }

    @SimpleProperty(description = "Returns true if a side menu is current open. Else it will return false.")
    public boolean IsSideMenuOpen() {
        if (this.drawerLayout != null) {
            return this.drawerLayout.isDrawerOpen((int) GravityCompat.START);
        }
        return false;
    }

    @SimpleProperty(description = "Returns true if a side menu is added to the screen.")
    public boolean IsSideMenuAdded() {
        return (this.actionBarDrawerToggle == null || this.drawerLayout == null) ? false : true;
    }

    @SimpleEvent(description = "Event will be invoked if the side menu was opened.")
    public void SideMenuOpened() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "SideMenuOpened", new Object[0]);
    }

    @SimpleEvent(description = "Event will be invoked if the side menu was closed.")
    public void SideMenuClosed() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "SideMenuClosed", new Object[0]);
    }

    @SimpleProperty(description = "This option tells the system to use dark statusbar icons, useful for lighter colored status bars. Works only for devices with API >= 23.")
    public void StatusbarLightIcons(boolean z) {
        boolean z2 = z;
        if (Build.VERSION.SDK_INT < 23) {
            int w = Log.w(LOG_TAG, "Sorry, statusbar light icons is not available for API Level < 23");
            return;
        }
        this.statusbarLight = z2;
        if (z2 && NavigationBarLightIcons()) {
            getWindow().getDecorView().setSystemUiVisibility(-2147475440);
        } else if (z2) {
            getWindow().getDecorView().setSystemUiVisibility(8192);
        } else if (NavigationBarLightIcons()) {
            NavigationBarLightIcons(true);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(0);
        }
    }

    @SimpleProperty
    public boolean StatusbarLightIcons() {
        return this.statusbarLight;
    }

    @SimpleProperty(description = "This option tells the system to use dark navigation bar icons, useful for lighter colored navigation bars. Works only for devices with API >= 26.")
    public void NavigationBarLightIcons(boolean z) {
        boolean z2 = z;
        if (Build.VERSION.SDK_INT < 26) {
            int w = Log.w(LOG_TAG, "Sorry, navigation bar light icons is not available for API Level < 26");
            return;
        }
        this.navigationBarLight = z2;
        if (z2 && StatusbarLightIcons()) {
            getWindow().getDecorView().setSystemUiVisibility(-2147475440);
        } else if (z2) {
            getWindow().getDecorView().setSystemUiVisibility(-2147483632);
        } else if (StatusbarLightIcons()) {
            StatusbarLightIcons(true);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(0);
        }
    }

    @SimpleProperty
    public boolean NavigationBarLightIcons() {
        return this.navigationBarLight;
    }

    @DesignerProperty(defaultValue = "&HFF3F51B5", editorType = "color")
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "This is the primary color used for Material UI elements, such as the ActionBar.", userVisible = false)
    public void PrimaryColor(int i) {
        Drawable drawable;
        int i2 = i;
        ActionBar supportActionBar = getSupportActionBar();
        int i3 = i2 == 0 ? DEFAULT_PRIMARY_COLOR : i2;
        this.titleBarColor = i3;
        if (supportActionBar != null && i3 != this.primaryColor) {
            this.primaryColor = i3;
            this.titleBarColor = i2;
            new ColorDrawable(i2);
            supportActionBar.setBackgroundDrawable(drawable);
        }
    }

    @SimpleProperty
    public int PrimaryColor() {
        return this.primaryColor;
    }

    @DesignerProperty(defaultValue = "&HFF303F9F", editorType = "color")
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "This is the primary color used for darker elements in Material UI.", userVisible = false)
    public void PrimaryColorDark(int i) {
        int i2 = i;
        this.primaryColorDark = i2;
    }

    @SimpleProperty
    public int PrimaryColorDark() {
        return this.primaryColorDark;
    }

    @DesignerProperty(defaultValue = "&HFFFF4081", editorType = "color")
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "This is the accent color used for highlights and other user interface accents.", userVisible = false)
    public void AccentColor(int i) {
        int i2 = i;
        this.accentColor = i2;
    }

    @SimpleProperty
    public int AccentColor() {
        return this.accentColor;
    }

    @DesignerProperty(editorType = "image_asset", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void SplashIcon(String str) {
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "If set to true the user will see a splash screen while the app is loading the content.", userVisible = false)
    public void SplashEnabled(boolean z) {
        boolean z2 = z;
        this.splashEnabled = z2;
    }

    @SimpleProperty
    public boolean SplashEnabled() {
        return this.splashEnabled;
    }

    @DesignerProperty(defaultValue = "AppTheme", editorType = "theme")
    @SimpleProperty(description = "Sets the theme used by the application.", userVisible = false)
    public void Theme(String str) {
    }

    @DesignerProperty(defaultValue = "19", editorType = "min_sdk", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void MinSdk(int i) {
    }

    @SimpleFunction(description = "Returns true if ALL needed app permissions were granted, else false.")
    public boolean ArePermissionsGranted() {
        return PermissionUtil.arePermissionsGranted(this);
    }

    @SimpleFunction(description = "Opens the settings screen of the app. Useful if 'Are Permissions Granted' has returned false.")
    public void OpenAppSettings() {
        PermissionUtil.appSettings(this);
    }

    @SimpleFunction(description = "Opens the app's system settings page. This works only for devices with android 6+.")
    public void OpenSystemWriteSettings() {
        PermissionUtil.appSystemSettings(this);
    }

    @SimpleFunction(description = "Returns true if the app can write system settings, else it returns false. It will return true automatic for devices with android version below 6 (API 23).")
    public boolean CanWriteSystemSettings() {
        return PermissionUtil.writeSystemSettings(this);
    }

    public String getAssetPathForExtension(Component component, String str) throws FileNotFoundException {
        StringBuilder sb;
        String name = component.getClass().getPackage().getName();
        new StringBuilder(ASSETS_PREFIX);
        return sb.append(name).append("/").append(str).toString();
    }

    public InputStream openAssetForExtension(Component component, String str) throws IOException {
        return openAssetInternal(getAssetPathForExtension(component, str));
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public int AboutScreenBackgroundColor() {
        return this.aboutThisAppBackgroundColor;
    }

    @DesignerProperty(defaultValue = "&HFF444444", editorType = "color")
    @SimpleProperty
    public void AboutScreenBackgroundColor(int i) {
        int i2 = i;
        this.aboutThisAppBackgroundColor = i2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public boolean AboutScreenLightTheme() {
        return this.aboutThisAppLightTheme;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void AboutScreenLightTheme(boolean z) {
        boolean z2 = z;
        this.aboutThisAppLightTheme = z2;
    }

    public void removeElevation() {
        try {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setElevation(0.0f);
            }
            if (Build.VERSION.SDK_INT >= 21 && this.appBarLayout != null) {
                this.appBarLayout.setOutlineProvider((ViewOutlineProvider) null);
            }
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    public void addElevation() {
        try {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setElevation(5.0f);
            }
            if (Build.VERSION.SDK_INT >= 21 && this.appBarLayout != null) {
                this.appBarLayout.setOutlineProvider(ViewOutlineProvider.BACKGROUND);
            }
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public void TitleBarFontTypeface(int i) {
        int i2 = i;
        this.fontTypeface = i2;
        if (getSupportActionBar() != null) {
            TextViewUtil.setContext(this);
            titleBarFontHelper("", i2, false);
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public int TitleBarFontTypeface() {
        return this.fontTypeface;
    }

    @DesignerProperty(defaultValue = "", editorType = "font_asset", propertyType = "advanced")
    @SimpleProperty(description = "Set a custom font.")
    public void TitleBarTypefaceImport(String str) {
        String str2 = str;
        if (getSupportActionBar() != null && str2 != null && !str2.isEmpty()) {
            titleBarFontHelper(str2, 0, true);
        }
    }

    private void titleBarFontHelper(String str, int i, boolean z) {
        String str2 = str;
        int i2 = i;
        boolean z2 = z;
        TextViewUtil.setContext(this);
        for (int i3 = 0; i3 < this.toolbar.getChildCount(); i3++) {
            View childAt = this.toolbar.getChildAt(i3);
            View view = childAt;
            if (childAt instanceof TextView) {
                TextView textView = (TextView) view;
                TextView textView2 = textView;
                if (textView.getText().equals(this.toolbar.getTitle()) || textView2.getText().equals(this.toolbar.getSubtitle())) {
                    if (z2) {
                        TextViewUtil.setCustomFontTypeface($form(), textView2, str2, false, false);
                    } else {
                        TextViewUtil.setFontTypeface(textView2, i2, false, false);
                    }
                }
            }
        }
    }

    private void titleBarTextColorHelper(int i) {
        int i2 = i;
        for (int i3 = 0; i3 < this.toolbar.getChildCount(); i3++) {
            View childAt = this.toolbar.getChildAt(i3);
            View view = childAt;
            if (childAt instanceof TextView) {
                TextView textView = (TextView) view;
                TextView textView2 = textView;
                if (textView.getText().equals(this.toolbar.getTitle())) {
                    textView2.setTextColor(i2);
                }
            }
        }
    }

    private void UpdateTitlebarItemColor(int i) {
        int i2 = i;
        this.toolbarIconColor = i2;
        int color = i2 == 0 ? KodularResourcesUtil.getColor(this, "colorToolbar") : i2;
        if (this.toolbar != null) {
            if (this.toolbar.getOverflowIcon() != null) {
                this.toolbar.getOverflowIcon().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
            }
            if (this.toolbar.getNavigationIcon() != null) {
                this.toolbar.getNavigationIcon().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
            }
            if (this.actionBarDrawerToggle != null) {
                this.actionBarDrawerToggle.getDrawerArrowDrawable().setColor(color);
            }
        }
        this.optionsMenuIconColor = color;
        this.navigationIconColor = color;
        this.drawerArrowIconColor = color;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public void HighQuality(boolean z) {
        boolean z2 = z;
        this.highQuality = z2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "If set to true, pictures will be loaded in high quality.")
    public boolean HighQuality() {
        return this.highQuality;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "This block will return true, if you are running your project current in the companion application. Else it will return false.")
    public boolean IsCompanion() {
        return this.isCompanion;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "Keep the device's screen turned on and bright.")
    public void KeepScreenOn(boolean z) {
        boolean z2 = z;
        this.keepScreenOn = z2;
        if (z2) {
            getWindow().addFlags(128);
        } else {
            getWindow().clearFlags(128);
        }
    }

    @SimpleProperty(description = "Returns true if keep screen on is set to enabled, else it returns false.")
    public boolean KeepScreenOn() {
        return this.keepScreenOn;
    }

    private void OnCompanionRefresh() {
        KodularCompanionUtil.drawerLayoutFix(this.drawerLayout, IsSideMenuAdded(), this.isCompanion);
        KodularCompanionUtil.toolbarColor(this.toolbar, this.isCompanion, this.titleBarColor);
        KodularCompanionUtil.statusBarColor(this, this.isCompanion, this.primaryColorDark);
        if (this.layoutBackup != null && (this.layoutBackup instanceof MakeroidSideMenuLayout)) {
            SideMenuLayout((MakeroidSideMenuLayout) this.layoutBackup);
        }
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        StringBuilder sb;
        int i2 = i;
        String[] strArr2 = strArr;
        int[] iArr2 = iArr;
        PermissionResultHandler permissionResultHandler = this.permissionHandlers.get(Integer.valueOf(i2));
        PermissionResultHandler permissionResultHandler2 = permissionResultHandler;
        if (permissionResultHandler == null) {
            int e = Log.e(LOG_TAG, "Received permission response which we cannot match.");
            return;
        }
        if (iArr2.length <= 0) {
            new StringBuilder("onRequestPermissionsResult: grantResults.length = ");
            int d = Log.d(LOG_TAG, sb.append(iArr2.length).append(" requestCode = ").append(i2).toString());
        } else if (iArr2[0] == 0) {
            permissionResultHandler2.HandlePermissionResponse(strArr2[0], true);
        } else {
            permissionResultHandler2.HandlePermissionResponse(strArr2[0], false);
        }
        PermissionResultHandler remove = this.permissionHandlers.remove(Integer.valueOf(i2));
    }

    @SimpleEvent
    public void PermissionDenied(Component component, String str, String str2) {
        Component component2 = component;
        String str3 = str;
        String str4 = str2;
        if (str4.startsWith("android.permission.")) {
            str4 = str4.replace("android.permission.", "");
        }
        Object[] objArr = new Object[3];
        objArr[0] = component2;
        Object[] objArr2 = objArr;
        objArr2[1] = str3;
        Object[] objArr3 = objArr2;
        objArr3[2] = str4;
        if (!EventDispatcher.dispatchEvent(this, "PermissionDenied", objArr3)) {
            dispatchErrorOccurredEvent(component2, str3, ErrorMessages.ERROR_PERMISSION_DENIED, str4);
        }
    }

    @SimpleEvent
    public void PermissionGranted(String str) {
        String str2 = str;
        if (str2.startsWith("android.permission.")) {
            str2 = str2.replace("android.permission.", "");
        }
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "PermissionGranted", str2);
    }

    @SimpleFunction
    public void AskForPermission(String str) {
        PermissionResultHandler permissionResultHandler;
        String str2 = str;
        if (!str2.contains(".")) {
            str2 = "android.permission.".concat(String.valueOf(str2));
        }
        new PermissionResultHandler(this) {
            private /* synthetic */ Form hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void HandlePermissionResponse(String str, boolean z) {
                String str2 = str;
                if (z) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.PermissionGranted(str2);
                } else {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.PermissionDenied(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "RequestPermission", str2);
                }
            }
        };
        askPermission(str2, permissionResultHandler);
    }

    public boolean isDeniedPermission(String str) {
        String str2 = str;
        if (Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(this, str2) != -1) {
            return false;
        }
        return true;
    }

    public void assertPermission(String str) {
        Throwable th;
        String str2 = str;
        if (isDeniedPermission(str2)) {
            Throwable th2 = th;
            new PermissionException(str2);
            throw th2;
        }
    }

    public void askPermission(String str, PermissionResultHandler permissionResultHandler) {
        Runnable runnable;
        String str2 = str;
        PermissionResultHandler permissionResultHandler2 = permissionResultHandler;
        if (!isDeniedPermission(str2)) {
            permissionResultHandler2.HandlePermissionResponse(str2, true);
            return;
        }
        final String str3 = str2;
        final PermissionResultHandler permissionResultHandler3 = permissionResultHandler2;
        new Runnable(this) {
            private /* synthetic */ Form hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r8;
            }

            public final void run() {
                StringBuilder sb;
                int nextInt = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.permissionRandom.nextInt(65535);
                new StringBuilder("askPermission: permission = ");
                int d = Log.d(Form.LOG_TAG, sb.append(str3).append(" requestCode = ").append(nextInt).toString());
                Object put = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.permissionHandlers.put(Integer.valueOf(nextInt), permissionResultHandler3);
                ActivityCompat.requestPermissions(this, new String[]{str3}, nextInt);
            }
        };
        boolean post = this.androidUIHandler.post(runnable);
    }

    public void askPermission(BulkPermissionRequest bulkPermissionRequest) {
        Runnable runnable;
        BulkPermissionRequest bulkPermissionRequest2 = bulkPermissionRequest;
        List<String> permissions2 = bulkPermissionRequest2.getPermissions();
        List<String> list = permissions2;
        Iterator<String> it = permissions2.iterator();
        while (it.hasNext()) {
            if (!isDeniedPermission(it.next())) {
                it.remove();
            }
        }
        if (list.size() == 0) {
            bulkPermissionRequest2.onGranted();
            return;
        }
        final List<String> list2 = list;
        final BulkPermissionRequest bulkPermissionRequest3 = bulkPermissionRequest2;
        new Runnable(this) {
            final /* synthetic */ Form hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
            }

            public final void run() {
                PermissionResultHandler permissionResultHandler;
                Iterator it = list2.iterator();
                final Iterator it2 = it;
                new PermissionResultHandler(this) {
                    private /* synthetic */ C069314 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                    private List<String> mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT;

                    {
                        List<String> list;
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r8;
                        new ArrayList();
                        this.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT = list;
                    }

                    public final void HandlePermissionResponse(String str, boolean z) {
                        String str2 = str;
                        if (!z) {
                            boolean add = this.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT.add(str2);
                        }
                        if (it2.hasNext()) {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.askPermission((String) it2.next(), this);
                        } else if (this.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT.size() == 0) {
                            bulkPermissionRequest3.onGranted();
                        } else {
                            bulkPermissionRequest3.onDenied((String[]) this.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT.toArray(new String[0]));
                        }
                    }
                };
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.askPermission((String) it.next(), permissionResultHandler);
            }
        };
        boolean post = this.androidUIHandler.post(runnable);
    }

    public void dispatchPermissionDeniedEvent(Component component, String str, PermissionException permissionException) {
        PermissionException permissionException2 = permissionException;
        permissionException2.printStackTrace();
        dispatchPermissionDeniedEvent(component, str, permissionException2.getPermissionNeeded());
    }

    public void dispatchPermissionDeniedEvent(Component component, String str, String str2) {
        Runnable runnable;
        final Component component2 = component;
        final String str3 = str;
        final String str4 = str2;
        new Runnable(this) {

            /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
            private /* synthetic */ Form f402hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.f402hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r8;
            }

            public final void run() {
                this.f402hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.PermissionDenied(component2, str3, str4);
            }
        };
        runOnUiThread(runnable);
    }

    public boolean doesAppDeclarePermission(String str) {
        return this.permissions.contains(str);
    }

    public String getAssetPath(String str) {
        return ASSETS_PREFIX.concat(String.valueOf(str));
    }

    public InputStream openAsset(String str) throws IOException {
        return openAssetInternal(getAssetPath(str));
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public InputStream openAssetInternal(String str) throws IOException {
        String str2 = str;
        if (str2.startsWith(ASSETS_PREFIX)) {
            return getAssets().open(str2.substring(22));
        }
        if (str2.startsWith("file:")) {
            return FileUtil.openFile(URI.create(str2));
        }
        return FileUtil.openFile(str2);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void RTLSupport(boolean z) {
    }

    @DesignerProperty(defaultValue = "", editorType = "receive_types")
    @SimpleProperty(userVisible = false)
    public void ReceiveSharedText(String str) {
    }

    @SimpleEvent(description = "Event to detect that a user shared content to your app throw the sharing dialog of any other app. Type stand for integer. 0 = nothing shared, 1 = audio, 2 = image, 3 = text or 4 = video")
    public void GotReceivedShared(int i, String str) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotReceivedShared", objArr2);
    }

    @SimpleProperty(description = "Set the options menu icon color.")
    public void OptionsMenuIconColor(int i) {
        int i2 = i;
        if (!(this.toolbar == null || this.toolbar.getOverflowIcon() == null)) {
            try {
                this.toolbar.getOverflowIcon().setColorFilter(i2, PorterDuff.Mode.SRC_ATOP);
            } catch (Exception e) {
                int e2 = Log.e(LOG_TAG, String.valueOf(e));
            }
        }
        this.optionsMenuIconColor = i2;
    }

    @SimpleProperty(description = "Get the options menu icon color.")
    public int OptionsMenuIconColor() {
        return this.optionsMenuIconColor;
    }

    @SimpleProperty(description = "Set the navigation icon color.")
    public void NavigationIconColor(int i) {
        int i2 = i;
        if (!(this.toolbar == null || this.toolbar.getNavigationIcon() == null)) {
            try {
                this.toolbar.getNavigationIcon().setColorFilter(i2, PorterDuff.Mode.SRC_ATOP);
            } catch (Exception e) {
                int e2 = Log.e(LOG_TAG, String.valueOf(e));
            }
        }
        this.navigationIconColor = i2;
    }

    @SimpleProperty(description = "Get the navigation icon color.")
    public int NavigationIconColor() {
        return this.navigationIconColor;
    }

    @SimpleProperty(description = "Set the drawer arrow icon color.")
    public void DrawerArrowIconColor(int i) {
        int i2 = i;
        if (this.actionBarDrawerToggle != null) {
            try {
                this.actionBarDrawerToggle.getDrawerArrowDrawable().setColor(i2);
            } catch (Exception e) {
                int e2 = Log.e(LOG_TAG, String.valueOf(e));
            }
        }
        this.drawerArrowIconColor = i2;
    }

    @SimpleProperty(description = "Get the drawer arrow icon color.")
    public int DrawerArrowIconColor() {
        return this.drawerArrowIconColor;
    }
}
