package android.support.p000v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.util.Preconditions;
import android.view.LayoutInflater;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* renamed from: android.support.v4.app.FragmentHostCallback */
public abstract class FragmentHostCallback<E> extends FragmentContainer {
    @Nullable
    private final Activity mActivity;
    @NonNull
    private final Context mContext;
    final FragmentManagerImpl mFragmentManager;
    @NonNull
    private final Handler mHandler;
    private final int mWindowAnimations;

    @Nullable
    public abstract E onGetHost();

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FragmentHostCallback(@android.support.annotation.NonNull android.content.Context r10, @android.support.annotation.NonNull android.os.Handler r11, int r12) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r12
            r4 = r0
            r5 = r1
            boolean r5 = r5 instanceof android.app.Activity
            if (r5 == 0) goto L_0x0014
            r5 = r1
            android.app.Activity r5 = (android.app.Activity) r5
        L_0x000d:
            r6 = r1
            r7 = r2
            r8 = r3
            r4.<init>(r5, r6, r7, r8)
            return
        L_0x0014:
            r5 = 0
            goto L_0x000d
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentHostCallback.<init>(android.content.Context, android.os.Handler, int):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    FragmentHostCallback(@android.support.annotation.NonNull android.support.p000v4.app.FragmentActivity r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            r3 = r1
            r4 = r1
            r5 = r1
            android.os.Handler r5 = r5.mHandler
            r6 = 0
            r2.<init>(r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentHostCallback.<init>(android.support.v4.app.FragmentActivity):void");
    }

    FragmentHostCallback(@Nullable Activity activity, @NonNull Context context, @NonNull Handler handler, int windowAnimations) {
        FragmentManagerImpl fragmentManagerImpl;
        new FragmentManagerImpl();
        this.mFragmentManager = fragmentManagerImpl;
        this.mActivity = activity;
        this.mContext = (Context) Preconditions.checkNotNull(context, "context == null");
        this.mHandler = (Handler) Preconditions.checkNotNull(handler, "handler == null");
        this.mWindowAnimations = windowAnimations;
    }

    public void onDump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
    }

    public boolean onShouldSaveFragmentState(Fragment fragment) {
        Fragment fragment2 = fragment;
        return true;
    }

    @NonNull
    public LayoutInflater onGetLayoutInflater() {
        return LayoutInflater.from(this.mContext);
    }

    public void onSupportInvalidateOptionsMenu() {
    }

    public void onStartActivityFromFragment(Fragment fragment, Intent intent, int requestCode) {
        onStartActivityFromFragment(fragment, intent, requestCode, (Bundle) null);
    }

    public void onStartActivityFromFragment(Fragment fragment, Intent intent, int requestCode, @Nullable Bundle bundle) {
        Throwable th;
        Fragment fragment2 = fragment;
        Intent intent2 = intent;
        Bundle bundle2 = bundle;
        if (requestCode != -1) {
            Throwable th2 = th;
            new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
            throw th2;
        }
        this.mContext.startActivity(intent2);
    }

    public void onStartIntentSenderFromFragment(Fragment fragment, IntentSender intentSender, int i, @Nullable Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
        Throwable th;
        Fragment fragment2 = fragment;
        IntentSender intent2 = intentSender;
        int requestCode = i;
        Intent fillInIntent = intent;
        int flagsMask = i2;
        int flagsValues = i3;
        int extraFlags = i4;
        Bundle options = bundle;
        if (requestCode != -1) {
            Throwable th2 = th;
            new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
            throw th2;
        }
        ActivityCompat.startIntentSenderForResult(this.mActivity, intent2, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
    }

    public void onRequestPermissionsFromFragment(@NonNull Fragment fragment, @NonNull String[] permissions, int requestCode) {
    }

    public boolean onShouldShowRequestPermissionRationale(@NonNull String str) {
        String str2 = str;
        return false;
    }

    public boolean onHasWindowAnimations() {
        return true;
    }

    public int onGetWindowAnimations() {
        return this.mWindowAnimations;
    }

    @Nullable
    public View onFindViewById(int i) {
        int i2 = i;
        return null;
    }

    public boolean onHasView() {
        return true;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Activity getActivity() {
        return this.mActivity;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public Context getContext() {
        return this.mContext;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public Handler getHandler() {
        return this.mHandler;
    }

    /* access modifiers changed from: package-private */
    public FragmentManagerImpl getFragmentManagerImpl() {
        return this.mFragmentManager;
    }

    /* access modifiers changed from: package-private */
    public void onAttachFragment(Fragment fragment) {
    }
}
