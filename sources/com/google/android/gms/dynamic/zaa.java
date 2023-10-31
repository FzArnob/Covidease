package com.google.android.gms.dynamic;

import android.os.Bundle;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import java.util.Iterator;

final class zaa implements OnDelegateCreatedListener<T> {
    private final /* synthetic */ DeferredLifecycleHelper zarj;

    zaa(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.zarj = deferredLifecycleHelper;
    }

    public final void onDelegateCreated(T t) {
        LifecycleDelegate zaa = DeferredLifecycleHelper.zaa(this.zarj, (LifecycleDelegate) t);
        Iterator it = this.zarj.zarh.iterator();
        while (it.hasNext()) {
            ((DeferredLifecycleHelper.zaa) it.next()).zaa(this.zarj.zarf);
        }
        this.zarj.zarh.clear();
        Bundle zaa2 = DeferredLifecycleHelper.zaa(this.zarj, (Bundle) null);
    }
}
