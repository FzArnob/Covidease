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
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzap;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

public class MapView extends FrameLayout {
    private final zzb zzbg;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MapView(android.content.Context r10) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            com.google.android.gms.maps.MapView$zzb r3 = new com.google.android.gms.maps.MapView$zzb
            r8 = r3
            r3 = r8
            r4 = r8
            r5 = r0
            r6 = r1
            r7 = 0
            r4.<init>(r5, r6, r7)
            r2.zzbg = r3
            r2 = r0
            r3 = 1
            r2.setClickable(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.MapView.<init>(android.content.Context):void");
    }

    @VisibleForTesting
    static class zza implements MapLifecycleDelegate {
        private final ViewGroup parent;
        private final IMapViewDelegate zzbh;
        private View zzbi;

        public zza(ViewGroup viewGroup, IMapViewDelegate iMapViewDelegate) {
            this.zzbh = (IMapViewDelegate) Preconditions.checkNotNull(iMapViewDelegate);
            this.parent = (ViewGroup) Preconditions.checkNotNull(viewGroup);
        }

        public final void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            Throwable th;
            Activity activity2 = activity;
            Bundle bundle3 = bundle;
            Bundle bundle4 = bundle2;
            Throwable th2 = th;
            new UnsupportedOperationException("onInflate not allowed on MapViewDelegate");
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
                this.zzbh.onCreate(bundle4);
                zzby.zza(bundle4, bundle3);
                this.zzbi = (View) ObjectWrapper.unwrap(this.zzbh.getView());
                this.parent.removeAllViews();
                this.parent.addView(this.zzbi);
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
            new UnsupportedOperationException("onCreateView not allowed on MapViewDelegate");
            throw th2;
        }

        public final void onStart() {
            Throwable th;
            try {
                this.zzbh.onStart();
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
                this.zzbh.onResume();
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
                this.zzbh.onPause();
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
                this.zzbh.onStop();
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
            new UnsupportedOperationException("onDestroyView not allowed on MapViewDelegate");
            throw th2;
        }

        public final void onDestroy() {
            Throwable th;
            try {
                this.zzbh.onDestroy();
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
                this.zzbh.onLowMemory();
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
                this.zzbh.onSaveInstanceState(bundle4);
                zzby.zza(bundle4, bundle3);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        }

        public final void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
            Throwable th;
            zzap zzap;
            try {
                new zzac(this, onMapReadyCallback);
                this.zzbh.getMapAsync(zzap);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        }

        public final void onEnterAmbient(Bundle bundle) {
            Throwable th;
            Bundle bundle2;
            Bundle bundle3 = bundle;
            try {
                new Bundle();
                Bundle bundle4 = bundle2;
                zzby.zza(bundle3, bundle4);
                this.zzbh.onEnterAmbient(bundle4);
                zzby.zza(bundle4, bundle3);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        }

        public final void onExitAmbient() {
            Throwable th;
            try {
                this.zzbh.onExitAmbient();
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        }
    }

    @VisibleForTesting
    static class zzb extends DeferredLifecycleHelper<zza> {
        private OnDelegateCreatedListener<zza> zzbd;
        private final List<OnMapReadyCallback> zzbf;
        private final ViewGroup zzbj;
        private final Context zzbk;
        private final GoogleMapOptions zzbl;

        @VisibleForTesting
        zzb(ViewGroup viewGroup, Context context, GoogleMapOptions googleMapOptions) {
            List<OnMapReadyCallback> list;
            new ArrayList();
            this.zzbf = list;
            this.zzbj = viewGroup;
            this.zzbk = context;
            this.zzbl = googleMapOptions;
        }

        /* access modifiers changed from: protected */
        public final void createDelegate(OnDelegateCreatedListener<zza> onDelegateCreatedListener) {
            Throwable th;
            LifecycleDelegate lifecycleDelegate;
            this.zzbd = onDelegateCreatedListener;
            if (this.zzbd != null && getDelegate() == null) {
                try {
                    int initialize = MapsInitializer.initialize(this.zzbk);
                    IMapViewDelegate zza = zzbz.zza(this.zzbk).zza(ObjectWrapper.wrap(this.zzbk), this.zzbl);
                    IMapViewDelegate iMapViewDelegate = zza;
                    if (zza != null) {
                        new zza(this.zzbj, iMapViewDelegate);
                        this.zzbd.onDelegateCreated(lifecycleDelegate);
                        for (OnMapReadyCallback mapAsync : this.zzbf) {
                            ((zza) getDelegate()).getMapAsync(mapAsync);
                        }
                        this.zzbf.clear();
                    }
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                    Throwable th2 = th;
                    new RuntimeRemoteException(remoteException);
                    throw th2;
                } catch (GooglePlayServicesNotAvailableException e2) {
                }
            }
        }

        public final void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
            OnMapReadyCallback onMapReadyCallback2 = onMapReadyCallback;
            if (getDelegate() != null) {
                ((zza) getDelegate()).getMapAsync(onMapReadyCallback2);
            } else {
                boolean add = this.zzbf.add(onMapReadyCallback2);
            }
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MapView(android.content.Context r12, android.util.AttributeSet r13) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r3 = r0
            r4 = r1
            r5 = r2
            r3.<init>(r4, r5)
            r3 = r0
            com.google.android.gms.maps.MapView$zzb r4 = new com.google.android.gms.maps.MapView$zzb
            r10 = r4
            r4 = r10
            r5 = r10
            r6 = r0
            r7 = r1
            r8 = r1
            r9 = r2
            com.google.android.gms.maps.GoogleMapOptions r8 = com.google.android.gms.maps.GoogleMapOptions.createFromAttributes(r8, r9)
            r5.<init>(r6, r7, r8)
            r3.zzbg = r4
            r3 = r0
            r4 = 1
            r3.setClickable(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.MapView.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MapView(android.content.Context r13, android.util.AttributeSet r14, int r15) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r4 = r0
            r5 = r1
            r6 = r2
            r7 = r3
            r4.<init>(r5, r6, r7)
            r4 = r0
            com.google.android.gms.maps.MapView$zzb r5 = new com.google.android.gms.maps.MapView$zzb
            r11 = r5
            r5 = r11
            r6 = r11
            r7 = r0
            r8 = r1
            r9 = r1
            r10 = r2
            com.google.android.gms.maps.GoogleMapOptions r9 = com.google.android.gms.maps.GoogleMapOptions.createFromAttributes(r9, r10)
            r6.<init>(r7, r8, r9)
            r4.zzbg = r5
            r4 = r0
            r5 = 1
            r4.setClickable(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.MapView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MapView(android.content.Context r11, com.google.android.gms.maps.GoogleMapOptions r12) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r0
            r4 = r1
            r3.<init>(r4)
            r3 = r0
            com.google.android.gms.maps.MapView$zzb r4 = new com.google.android.gms.maps.MapView$zzb
            r9 = r4
            r4 = r9
            r5 = r9
            r6 = r0
            r7 = r1
            r8 = r2
            r5.<init>(r6, r7, r8)
            r3.zzbg = r4
            r3 = r0
            r4 = 1
            r3.setClickable(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.MapView.<init>(android.content.Context, com.google.android.gms.maps.GoogleMapOptions):void");
    }

    public final void onCreate(Bundle bundle) {
        StrictMode.ThreadPolicy.Builder builder;
        Bundle bundle2 = bundle;
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        new StrictMode.ThreadPolicy.Builder(threadPolicy);
        StrictMode.setThreadPolicy(builder.permitAll().build());
        try {
            this.zzbg.onCreate(bundle2);
            if (this.zzbg.getDelegate() == null) {
                DeferredLifecycleHelper.showGooglePlayUnavailableMessage(this);
            }
            StrictMode.setThreadPolicy(threadPolicy);
        } catch (Throwable th) {
            Throwable th2 = th;
            StrictMode.setThreadPolicy(threadPolicy);
            throw th2;
        }
    }

    public final void onResume() {
        this.zzbg.onResume();
    }

    public final void onPause() {
        this.zzbg.onPause();
    }

    public final void onStart() {
        this.zzbg.onStart();
    }

    public final void onStop() {
        this.zzbg.onStop();
    }

    public final void onDestroy() {
        this.zzbg.onDestroy();
    }

    public final void onLowMemory() {
        this.zzbg.onLowMemory();
    }

    public final void onSaveInstanceState(Bundle bundle) {
        this.zzbg.onSaveInstanceState(bundle);
    }

    public void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
        Preconditions.checkMainThread("getMapAsync() must be called on the main thread");
        this.zzbg.getMapAsync(onMapReadyCallback);
    }

    public final void onEnterAmbient(Bundle bundle) {
        Preconditions.checkMainThread("onEnterAmbient() must be called on the main thread");
        Bundle bundle2 = bundle;
        zzb zzb2 = this.zzbg;
        zzb zzb3 = zzb2;
        if (zzb2.getDelegate() != null) {
            ((zza) zzb3.getDelegate()).onEnterAmbient(bundle2);
        }
    }

    public final void onExitAmbient() {
        Preconditions.checkMainThread("onExitAmbient() must be called on the main thread");
        zzb zzb2 = this.zzbg;
        zzb zzb3 = zzb2;
        if (zzb2.getDelegate() != null) {
            ((zza) zzb3.getDelegate()).onExitAmbient();
        }
    }
}
