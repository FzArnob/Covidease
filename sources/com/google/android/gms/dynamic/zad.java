package com.google.android.gms.dynamic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;

final class zad implements DeferredLifecycleHelper.zaa {
    private final /* synthetic */ ViewGroup val$container;
    private final /* synthetic */ DeferredLifecycleHelper zarj;
    private final /* synthetic */ Bundle zarl;
    private final /* synthetic */ FrameLayout zarm;
    private final /* synthetic */ LayoutInflater zarn;

    zad(DeferredLifecycleHelper deferredLifecycleHelper, FrameLayout frameLayout, LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.zarj = deferredLifecycleHelper;
        this.zarm = frameLayout;
        this.zarn = layoutInflater;
        this.val$container = viewGroup;
        this.zarl = bundle;
    }

    public final int getState() {
        return 2;
    }

    public final void zaa(LifecycleDelegate lifecycleDelegate) {
        LifecycleDelegate lifecycleDelegate2 = lifecycleDelegate;
        this.zarm.removeAllViews();
        this.zarm.addView(this.zarj.zarf.onCreateView(this.zarn, this.val$container, this.zarl));
    }
}
