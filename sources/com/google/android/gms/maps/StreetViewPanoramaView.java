package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate;
import com.google.android.gms.maps.internal.StreetViewLifecycleDelegate;
import com.google.android.gms.maps.internal.zzbp;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

public class StreetViewPanoramaView extends FrameLayout {
    private final zzb zzcd;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public StreetViewPanoramaView(android.content.Context r10) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            com.google.android.gms.maps.StreetViewPanoramaView$zzb r3 = new com.google.android.gms.maps.StreetViewPanoramaView$zzb
            r8 = r3
            r3 = r8
            r4 = r8
            r5 = r0
            r6 = r1
            r7 = 0
            r4.<init>(r5, r6, r7)
            r2.zzcd = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.StreetViewPanoramaView.<init>(android.content.Context):void");
    }

    @VisibleForTesting
    static class zza implements StreetViewLifecycleDelegate {
        private final ViewGroup parent;
        private final IStreetViewPanoramaViewDelegate zzce;
        private View zzcf;

        public zza(ViewGroup viewGroup, IStreetViewPanoramaViewDelegate iStreetViewPanoramaViewDelegate) {
            this.zzce = (IStreetViewPanoramaViewDelegate) Preconditions.checkNotNull(iStreetViewPanoramaViewDelegate);
            this.parent = (ViewGroup) Preconditions.checkNotNull(viewGroup);
        }

        public final void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            Throwable th;
            Activity activity2 = activity;
            Bundle bundle3 = bundle;
            Bundle bundle4 = bundle2;
            Throwable th2 = th;
            new UnsupportedOperationException("onInflate not allowed on StreetViewPanoramaViewDelegate");
            throw th2;
        }

        public final void onCreate(Bundle bundle) {
            Throwable th;
            Bundle bundle2;
            Bundle bundle3 = bundle;
            try {
                new Bundle();
                Bundle bundle4 = bundle2;
                zzby.zza(bundle3, bundle4);
                this.zzce.onCreate(bundle4);
                zzby.zza(bundle4, bundle3);
                this.zzcf = (View) ObjectWrapper.unwrap(this.zzce.getView());
                this.parent.removeAllViews();
                this.parent.addView(this.zzcf);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        }

        public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            Throwable th;
            LayoutInflater layoutInflater2 = layoutInflater;
            ViewGroup viewGroup2 = viewGroup;
            Bundle bundle2 = bundle;
            Throwable th2 = th;
            new UnsupportedOperationException("onCreateView not allowed on StreetViewPanoramaViewDelegate");
            throw th2;
        }

        public final void onStart() {
            Throwable th;
            try {
                this.zzce.onStart();
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        }

        public final void onResume() {
            Throwable th;
            try {
                this.zzce.onResume();
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        }

        public final void onPause() {
            Throwable th;
            try {
                this.zzce.onPause();
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        }

        public final void onStop() {
            Throwable th;
            try {
                this.zzce.onStop();
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        }

        public final void onDestroyView() {
            Throwable th;
            Throwable th2 = th;
            new UnsupportedOperationException("onDestroyView not allowed on StreetViewPanoramaViewDelegate");
            throw th2;
        }

        public final void onDestroy() {
            Throwable th;
            try {
                this.zzce.onDestroy();
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        }

        public final void onLowMemory() {
            Throwable th;
            try {
                this.zzce.onLowMemory();
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        }

        public final void onSaveInstanceState(Bundle bundle) {
            Throwable th;
            Bundle bundle2;
            Bundle bundle3 = bundle;
            try {
                new Bundle();
                Bundle bundle4 = bundle2;
                zzby.zza(bundle3, bundle4);
                this.zzce.onSaveInstanceState(bundle4);
                zzby.zza(bundle4, bundle3);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        }

        public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
            Throwable th;
            zzbp zzbp;
            try {
                new zzaj(this, onStreetViewPanoramaReadyCallback);
                this.zzce.getStreetViewPanoramaAsync(zzbp);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public StreetViewPanoramaView(android.content.Context r11, android.util.AttributeSet r12) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r0
            r4 = r1
            r5 = r2
            r3.<init>(r4, r5)
            r3 = r0
            com.google.android.gms.maps.StreetViewPanoramaView$zzb r4 = new com.google.android.gms.maps.StreetViewPanoramaView$zzb
            r9 = r4
            r4 = r9
            r5 = r9
            r6 = r0
            r7 = r1
            r8 = 0
            r5.<init>(r6, r7, r8)
            r3.zzcd = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.StreetViewPanoramaView.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    @VisibleForTesting
    static class zzb extends DeferredLifecycleHelper<zza> {
        private OnDelegateCreatedListener<zza> zzbd;
        private final ViewGroup zzbj;
        private final Context zzbk;
        private final List<OnStreetViewPanoramaReadyCallback> zzbw;
        private final StreetViewPanoramaOptions zzcg;

        @VisibleForTesting
        zzb(ViewGroup viewGroup, Context context, StreetViewPanoramaOptions streetViewPanoramaOptions) {
            List<OnStreetViewPanoramaReadyCallback> list;
            new ArrayList();
            this.zzbw = list;
            this.zzbj = viewGroup;
            this.zzbk = context;
            this.zzcg = streetViewPanoramaOptions;
        }

        /* access modifiers changed from: protected */
        public final void createDelegate(OnDelegateCreatedListener<zza> onDelegateCreatedListener) {
            Throwable th;
            LifecycleDelegate lifecycleDelegate;
            this.zzbd = onDelegateCreatedListener;
            if (this.zzbd != null && getDelegate() == null) {
                try {
                    int initialize = MapsInitializer.initialize(this.zzbk);
                    new zza(this.zzbj, zzbz.zza(this.zzbk).zza(ObjectWrapper.wrap(this.zzbk), this.zzcg));
                    this.zzbd.onDelegateCreated(lifecycleDelegate);
                    for (OnStreetViewPanoramaReadyCallback streetViewPanoramaAsync : this.zzbw) {
                        ((zza) getDelegate()).getStreetViewPanoramaAsync(streetViewPanoramaAsync);
                    }
                    this.zzbw.clear();
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                    Throwable th2 = th;
                    new RuntimeRemoteException(remoteException);
                    throw th2;
                } catch (GooglePlayServicesNotAvailableException e2) {
                }
            }
        }

        public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
            OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback2 = onStreetViewPanoramaReadyCallback;
            if (getDelegate() != null) {
                ((zza) getDelegate()).getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback2);
            } else {
                boolean add = this.zzbw.add(onStreetViewPanoramaReadyCallback2);
            }
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public StreetViewPanoramaView(android.content.Context r12, android.util.AttributeSet r13, int r14) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r3 = r14
            r4 = r0
            r5 = r1
            r6 = r2
            r7 = r3
            r4.<init>(r5, r6, r7)
            r4 = r0
            com.google.android.gms.maps.StreetViewPanoramaView$zzb r5 = new com.google.android.gms.maps.StreetViewPanoramaView$zzb
            r10 = r5
            r5 = r10
            r6 = r10
            r7 = r0
            r8 = r1
            r9 = 0
            r6.<init>(r7, r8, r9)
            r4.zzcd = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.StreetViewPanoramaView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public StreetViewPanoramaView(android.content.Context r11, com.google.android.gms.maps.StreetViewPanoramaOptions r12) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r0
            r4 = r1
            r3.<init>(r4)
            r3 = r0
            com.google.android.gms.maps.StreetViewPanoramaView$zzb r4 = new com.google.android.gms.maps.StreetViewPanoramaView$zzb
            r9 = r4
            r4 = r9
            r5 = r9
            r6 = r0
            r7 = r1
            r8 = r2
            r5.<init>(r6, r7, r8)
            r3.zzcd = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.StreetViewPanoramaView.<init>(android.content.Context, com.google.android.gms.maps.StreetViewPanoramaOptions):void");
    }

    public final void onCreate(Bundle bundle) {
        StrictMode.ThreadPolicy.Builder builder;
        Bundle bundle2 = bundle;
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        new StrictMode.ThreadPolicy.Builder(threadPolicy);
        StrictMode.setThreadPolicy(builder.permitAll().build());
        try {
            this.zzcd.onCreate(bundle2);
            if (this.zzcd.getDelegate() == null) {
                DeferredLifecycleHelper.showGooglePlayUnavailableMessage(this);
            }
            StrictMode.setThreadPolicy(threadPolicy);
        } catch (Throwable th) {
            Throwable th2 = th;
            StrictMode.setThreadPolicy(threadPolicy);
            throw th2;
        }
    }

    public void onStart() {
        this.zzcd.onStart();
    }

    public void onResume() {
        this.zzcd.onResume();
    }

    public final void onPause() {
        this.zzcd.onPause();
    }

    public void onStop() {
        this.zzcd.onStop();
    }

    public void onDestroy() {
        this.zzcd.onDestroy();
    }

    public final void onLowMemory() {
        this.zzcd.onLowMemory();
    }

    public final void onSaveInstanceState(Bundle bundle) {
        this.zzcd.onSaveInstanceState(bundle);
    }

    public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        Preconditions.checkMainThread("getStreetViewPanoramaAsync() must be called on the main thread");
        this.zzcd.getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
    }
}
