package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public final class zaa extends ActivityLifecycleObserver {
    private final WeakReference<C1641zaa> zacl;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public zaa(Activity activity) {
        this(C1641zaa.zaa(activity));
    }

    @VisibleForTesting(otherwise = 2)
    private zaa(C1641zaa zaa) {
        WeakReference<C1641zaa> weakReference;
        new WeakReference<>(zaa);
        this.zacl = weakReference;
    }

    public final ActivityLifecycleObserver onStopCallOnce(Runnable runnable) {
        Throwable th;
        Runnable runnable2 = runnable;
        C1641zaa zaa = (C1641zaa) this.zacl.get();
        C1641zaa zaa2 = zaa;
        if (zaa == null) {
            Throwable th2 = th;
            new IllegalStateException("The target activity has already been GC'd");
            throw th2;
        }
        zaa2.zaa(runnable2);
        return this;
    }

    @VisibleForTesting(otherwise = 2)
    /* renamed from: com.google.android.gms.common.api.internal.zaa$zaa  reason: collision with other inner class name */
    static class C1641zaa extends LifecycleCallback {
        private List<Runnable> zacm;

        /* access modifiers changed from: private */
        public static C1641zaa zaa(Activity activity) {
            C1641zaa zaa;
            Activity activity2 = activity;
            Activity activity3 = activity2;
            Activity activity4 = activity3;
            synchronized (activity3) {
                try {
                    LifecycleFragment fragment = getFragment(activity2);
                    LifecycleFragment lifecycleFragment = fragment;
                    C1641zaa zaa2 = (C1641zaa) fragment.getCallbackOrNull("LifecycleObserverOnStop", C1641zaa.class);
                    C1641zaa zaa3 = zaa2;
                    if (zaa2 == null) {
                        new C1641zaa(lifecycleFragment);
                        zaa3 = zaa;
                    }
                    C1641zaa zaa4 = zaa3;
                    return zaa4;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Activity activity5 = activity4;
                    throw th2;
                }
            }
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        private C1641zaa(LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            List<Runnable> list;
            new ArrayList();
            this.zacm = list;
            this.mLifecycleFragment.addCallback("LifecycleObserverOnStop", this);
        }

        /* access modifiers changed from: private */
        public final synchronized void zaa(Runnable runnable) {
            Runnable runnable2 = runnable;
            synchronized (this) {
                boolean add = this.zacm.add(runnable2);
            }
        }

        /* JADX INFO: finally extract failed */
        @MainThread
        public void onStop() {
            List<Runnable> list;
            synchronized (this) {
                try {
                    List<Runnable> list2 = this.zacm;
                    new ArrayList();
                    this.zacm = list;
                    for (Runnable run : list2) {
                        run.run();
                    }
                } catch (Throwable th) {
                    while (true) {
                        Throwable th2 = th;
                        throw th2;
                    }
                }
            }
        }
    }
}
