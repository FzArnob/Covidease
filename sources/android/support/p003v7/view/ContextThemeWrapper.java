package android.support.p003v7.view;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.StyleRes;
import android.support.p003v7.appcompat.C0425R;
import android.view.LayoutInflater;

/* renamed from: android.support.v7.view.ContextThemeWrapper */
public class ContextThemeWrapper extends ContextWrapper {
    private LayoutInflater mInflater;
    private Configuration mOverrideConfiguration;
    private Resources mResources;
    private Resources.Theme mTheme;
    private int mThemeResource;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ContextThemeWrapper() {
        super((Context) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ContextThemeWrapper(Context base, @StyleRes int themeResId) {
        super(base);
        this.mThemeResource = themeResId;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ContextThemeWrapper(Context base, Resources.Theme theme) {
        super(base);
        this.mTheme = theme;
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    public void applyOverrideConfiguration(Configuration configuration) {
        Configuration configuration2;
        Throwable th;
        Throwable th2;
        Configuration overrideConfiguration = configuration;
        if (this.mResources != null) {
            Throwable th3 = th2;
            new IllegalStateException("getResources() or getAssets() has already been called");
            throw th3;
        } else if (this.mOverrideConfiguration != null) {
            Throwable th4 = th;
            new IllegalStateException("Override configuration has already been set");
            throw th4;
        } else {
            new Configuration(overrideConfiguration);
            this.mOverrideConfiguration = configuration2;
        }
    }

    public Resources getResources() {
        return getResourcesInternal();
    }

    private Resources getResourcesInternal() {
        if (this.mResources == null) {
            if (this.mOverrideConfiguration == null) {
                this.mResources = super.getResources();
            } else if (Build.VERSION.SDK_INT >= 17) {
                this.mResources = createConfigurationContext(this.mOverrideConfiguration).getResources();
            }
        }
        return this.mResources;
    }

    public void setTheme(int i) {
        int resid = i;
        if (this.mThemeResource != resid) {
            this.mThemeResource = resid;
            initializeTheme();
        }
    }

    public int getThemeResId() {
        return this.mThemeResource;
    }

    public Resources.Theme getTheme() {
        if (this.mTheme != null) {
            return this.mTheme;
        }
        if (this.mThemeResource == 0) {
            this.mThemeResource = C0425R.C0428style.Theme_AppCompat_Light;
        }
        initializeTheme();
        return this.mTheme;
    }

    public Object getSystemService(String str) {
        String name = str;
        if (!"layout_inflater".equals(name)) {
            return getBaseContext().getSystemService(name);
        }
        if (this.mInflater == null) {
            this.mInflater = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.mInflater;
    }

    /* access modifiers changed from: protected */
    public void onApplyThemeResource(Resources.Theme theme, int resid, boolean z) {
        boolean z2 = z;
        theme.applyStyle(resid, true);
    }

    private void initializeTheme() {
        boolean first = this.mTheme == null;
        if (first) {
            this.mTheme = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.mTheme.setTo(theme);
            }
        }
        onApplyThemeResource(this.mTheme, this.mThemeResource, first);
    }

    public AssetManager getAssets() {
        return getResources().getAssets();
    }
}
