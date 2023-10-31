package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ConnectionErrorMessages;
import com.google.android.gms.dynamic.LifecycleDelegate;
import java.util.LinkedList;

@KeepForSdk
public abstract class DeferredLifecycleHelper<T extends LifecycleDelegate> {
    /* access modifiers changed from: private */
    public T zarf;
    private Bundle zarg;
    /* access modifiers changed from: private */
    public LinkedList<zaa> zarh;
    private final OnDelegateCreatedListener<T> zari;

    private interface zaa {
        int getState();

        void zaa(LifecycleDelegate lifecycleDelegate);
    }

    @KeepForSdk
    public DeferredLifecycleHelper() {
        OnDelegateCreatedListener<T> onDelegateCreatedListener;
        new zaa(this);
        this.zari = onDelegateCreatedListener;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract void createDelegate(OnDelegateCreatedListener<T> onDelegateCreatedListener);

    @KeepForSdk
    public T getDelegate() {
        return this.zarf;
    }

    private final void zal(int i) {
        int i2 = i;
        while (!this.zarh.isEmpty() && this.zarh.getLast().getState() >= i2) {
            zaa removeLast = this.zarh.removeLast();
        }
    }

    private final void zaa(Bundle bundle, zaa zaa2) {
        LinkedList<zaa> linkedList;
        Bundle bundle2 = bundle;
        zaa zaa3 = zaa2;
        if (this.zarf != null) {
            zaa3.zaa(this.zarf);
            return;
        }
        if (this.zarh == null) {
            new LinkedList<>();
            this.zarh = linkedList;
        }
        boolean add = this.zarh.add(zaa3);
        if (bundle2 != null) {
            if (this.zarg == null) {
                this.zarg = (Bundle) bundle2.clone();
            } else {
                this.zarg.putAll(bundle2);
            }
        }
        createDelegate(this.zari);
    }

    @KeepForSdk
    public void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
        zaa zaa2;
        Bundle bundle3 = bundle2;
        new zab(this, activity, bundle, bundle3);
        zaa(bundle3, zaa2);
    }

    @KeepForSdk
    public void onCreate(Bundle bundle) {
        zaa zaa2;
        Bundle bundle2 = bundle;
        new zac(this, bundle2);
        zaa(bundle2, zaa2);
    }

    @KeepForSdk
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FrameLayout frameLayout;
        zaa zaa2;
        LayoutInflater layoutInflater2 = layoutInflater;
        Bundle bundle2 = bundle;
        new FrameLayout(layoutInflater2.getContext());
        FrameLayout frameLayout2 = frameLayout;
        new zad(this, frameLayout2, layoutInflater2, viewGroup, bundle2);
        zaa(bundle2, zaa2);
        if (this.zarf == null) {
            handleGooglePlayUnavailable(frameLayout2);
        }
        return frameLayout2;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void handleGooglePlayUnavailable(FrameLayout frameLayout) {
        showGooglePlayUnavailableMessage(frameLayout);
    }

    @KeepForSdk
    public static void showGooglePlayUnavailableMessage(FrameLayout frameLayout) {
        LinearLayout linearLayout;
        ViewGroup.LayoutParams layoutParams;
        TextView textView;
        ViewGroup.LayoutParams layoutParams2;
        Button button;
        ViewGroup.LayoutParams layoutParams3;
        View.OnClickListener onClickListener;
        GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        FrameLayout frameLayout2 = frameLayout;
        FrameLayout frameLayout3 = frameLayout2;
        Context context = frameLayout2.getContext();
        int isGooglePlayServicesAvailable = instance.isGooglePlayServicesAvailable(context);
        String errorMessage = ConnectionErrorMessages.getErrorMessage(context, isGooglePlayServicesAvailable);
        String errorDialogButtonMessage = ConnectionErrorMessages.getErrorDialogButtonMessage(context, isGooglePlayServicesAvailable);
        new LinearLayout(frameLayout3.getContext());
        LinearLayout linearLayout2 = linearLayout;
        LinearLayout linearLayout3 = linearLayout2;
        linearLayout2.setOrientation(1);
        new FrameLayout.LayoutParams(-2, -2);
        linearLayout3.setLayoutParams(layoutParams);
        frameLayout3.addView(linearLayout3);
        new TextView(frameLayout3.getContext());
        TextView textView2 = textView;
        TextView textView3 = textView2;
        new FrameLayout.LayoutParams(-2, -2);
        textView2.setLayoutParams(layoutParams2);
        textView3.setText(errorMessage);
        linearLayout3.addView(textView3);
        Intent errorResolutionIntent = instance.getErrorResolutionIntent(context, isGooglePlayServicesAvailable, (String) null);
        Intent intent = errorResolutionIntent;
        if (errorResolutionIntent != null) {
            new Button(context);
            Button button2 = button;
            Button button3 = button2;
            button2.setId(16908313);
            new FrameLayout.LayoutParams(-2, -2);
            button3.setLayoutParams(layoutParams3);
            button3.setText(errorDialogButtonMessage);
            linearLayout3.addView(button3);
            new zae(context, intent);
            button3.setOnClickListener(onClickListener);
        }
    }

    @KeepForSdk
    public void onStart() {
        zaa zaa2;
        new zaf(this);
        zaa((Bundle) null, zaa2);
    }

    @KeepForSdk
    public void onResume() {
        zaa zaa2;
        new zag(this);
        zaa((Bundle) null, zaa2);
    }

    @KeepForSdk
    public void onPause() {
        if (this.zarf != null) {
            this.zarf.onPause();
        } else {
            zal(5);
        }
    }

    @KeepForSdk
    public void onStop() {
        if (this.zarf != null) {
            this.zarf.onStop();
        } else {
            zal(4);
        }
    }

    @KeepForSdk
    public void onDestroyView() {
        if (this.zarf != null) {
            this.zarf.onDestroyView();
        } else {
            zal(2);
        }
    }

    @KeepForSdk
    public void onDestroy() {
        if (this.zarf != null) {
            this.zarf.onDestroy();
        } else {
            zal(1);
        }
    }

    @KeepForSdk
    public void onSaveInstanceState(Bundle bundle) {
        Bundle bundle2 = bundle;
        if (this.zarf != null) {
            this.zarf.onSaveInstanceState(bundle2);
        } else if (this.zarg != null) {
            bundle2.putAll(this.zarg);
        }
    }

    @KeepForSdk
    public void onLowMemory() {
        if (this.zarf != null) {
            this.zarf.onLowMemory();
        }
    }

    static /* synthetic */ LifecycleDelegate zaa(DeferredLifecycleHelper deferredLifecycleHelper, LifecycleDelegate lifecycleDelegate) {
        LifecycleDelegate lifecycleDelegate2 = lifecycleDelegate;
        LifecycleDelegate lifecycleDelegate3 = lifecycleDelegate2;
        deferredLifecycleHelper.zarf = lifecycleDelegate3;
        return lifecycleDelegate2;
    }

    static /* synthetic */ Bundle zaa(DeferredLifecycleHelper deferredLifecycleHelper, Bundle bundle) {
        Bundle bundle2 = bundle;
        deferredLifecycleHelper.zarg = null;
        return null;
    }
}
