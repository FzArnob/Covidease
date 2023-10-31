package android.support.p003v7.app;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.view.KeyEventDispatcher;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.view.ActionMode;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: android.support.v7.app.AppCompatDialog */
public class AppCompatDialog extends Dialog implements AppCompatCallback {
    private AppCompatDelegate mDelegate;
    private final KeyEventDispatcher.Component mKeyDispatcher;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AppCompatDialog(Context context) {
        this(context, 0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AppCompatDialog(android.content.Context r9, int r10) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r0
            r4 = r1
            r5 = r1
            r6 = r2
            int r5 = getThemeResId(r5, r6)
            r3.<init>(r4, r5)
            r3 = r0
            android.support.v7.app.AppCompatDialog$1 r4 = new android.support.v7.app.AppCompatDialog$1
            r7 = r4
            r4 = r7
            r5 = r7
            r6 = r0
            r5.<init>(r6)
            r3.mKeyDispatcher = r4
            r3 = r0
            android.support.v7.app.AppCompatDelegate r3 = r3.getDelegate()
            r4 = 0
            r3.onCreate(r4)
            r3 = r0
            android.support.v7.app.AppCompatDelegate r3 = r3.getDelegate()
            boolean r3 = r3.applyDayNight()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.app.AppCompatDialog.<init>(android.content.Context, int):void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected AppCompatDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        KeyEventDispatcher.Component component;
        new KeyEventDispatcher.Component(this) {
            final /* synthetic */ AppCompatDialog this$0;

            {
                this.this$0 = this$0;
            }

            public boolean superDispatchKeyEvent(KeyEvent event) {
                return this.this$0.superDispatchKeyEvent(event);
            }
        };
        this.mKeyDispatcher = component;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Bundle savedInstanceState = bundle;
        getDelegate().installViewFactory();
        super.onCreate(savedInstanceState);
        getDelegate().onCreate(savedInstanceState);
    }

    public ActionBar getSupportActionBar() {
        return getDelegate().getSupportActionBar();
    }

    public void setContentView(@LayoutRes int layoutResID) {
        getDelegate().setContentView(layoutResID);
    }

    public void setContentView(View view) {
        getDelegate().setContentView(view);
    }

    public void setContentView(View view, ViewGroup.LayoutParams params) {
        getDelegate().setContentView(view, params);
    }

    @Nullable
    public <T extends View> T findViewById(@IdRes int id) {
        return getDelegate().findViewById(id);
    }

    public void setTitle(CharSequence charSequence) {
        CharSequence title = charSequence;
        super.setTitle(title);
        getDelegate().setTitle(title);
    }

    public void setTitle(int i) {
        int titleId = i;
        super.setTitle(titleId);
        getDelegate().setTitle(getContext().getString(titleId));
    }

    public void addContentView(View view, ViewGroup.LayoutParams params) {
        getDelegate().addContentView(view, params);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        getDelegate().onStop();
    }

    public boolean supportRequestWindowFeature(int featureId) {
        return getDelegate().requestWindowFeature(featureId);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void invalidateOptionsMenu() {
        getDelegate().invalidateOptionsMenu();
    }

    public AppCompatDelegate getDelegate() {
        if (this.mDelegate == null) {
            this.mDelegate = AppCompatDelegate.create((Dialog) this, (AppCompatCallback) this);
        }
        return this.mDelegate;
    }

    private static int getThemeResId(Context context, int i) {
        TypedValue typedValue;
        Context context2 = context;
        int themeId = i;
        if (themeId == 0) {
            new TypedValue();
            TypedValue outValue = typedValue;
            boolean resolveAttribute = context2.getTheme().resolveAttribute(C0425R.attr.dialogTheme, outValue, true);
            themeId = outValue.resourceId;
        }
        return themeId;
    }

    public void onSupportActionModeStarted(ActionMode mode) {
    }

    public void onSupportActionModeFinished(ActionMode mode) {
    }

    @Nullable
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        ActionMode.Callback callback2 = callback;
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean superDispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        return KeyEventDispatcher.dispatchKeyEvent(this.mKeyDispatcher, getWindow().getDecorView(), this, event);
    }
}
