package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.StrictMode;
import android.support.p000v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzap;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

public class SupportMapFragment extends Fragment {
    private final zzb zzch;

    public static SupportMapFragment newInstance() {
        SupportMapFragment supportMapFragment;
        SupportMapFragment supportMapFragment2 = supportMapFragment;
        new SupportMapFragment();
        return supportMapFragment2;
    }

    public static SupportMapFragment newInstance(GoogleMapOptions googleMapOptions) {
        SupportMapFragment supportMapFragment;
        Bundle bundle;
        new SupportMapFragment();
        SupportMapFragment supportMapFragment2 = supportMapFragment;
        new Bundle();
        Bundle bundle2 = bundle;
        bundle2.putParcelable("MapOptions", googleMapOptions);
        supportMapFragment2.setArguments(bundle2);
        return supportMapFragment2;
    }

    @VisibleForTesting
    static class zza implements MapLifecycleDelegate {
        private final Fragment fragment;
        private final IMapFragmentDelegate zzbb;

        public zza(Fragment fragment2, IMapFragmentDelegate iMapFragmentDelegate) {
            this.zzbb = (IMapFragmentDelegate) Preconditions.checkNotNull(iMapFragmentDelegate);
            this.fragment = (Fragment) Preconditions.checkNotNull(fragment2);
        }

        public final void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            Throwable th;
            Bundle bundle3;
            Activity activity2 = activity;
            Bundle bundle4 = bundle2;
            GoogleMapOptions googleMapOptions = (GoogleMapOptions) bundle.getParcelable("MapOptions");
            try {
                new Bundle();
                Bundle bundle5 = bundle3;
                zzby.zza(bundle4, bundle5);
                this.zzbb.onInflate(ObjectWrapper.wrap(activity2), googleMapOptions, bundle5);
                zzby.zza(bundle5, bundle4);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        }

        public final void onCreate(Bundle bundle) {
            Throwable th;
            Bundle bundle2;
            Bundle bundle3 = bundle;
            try {
                new Bundle();
                Bundle bundle4 = bundle2;
                zzby.zza(bundle3, bundle4);
                Bundle arguments = this.fragment.getArguments();
                Bundle bundle5 = arguments;
                if (arguments != null && bundle5.containsKey("MapOptions")) {
                    zzby.zza(bundle4, "MapOptions", bundle5.getParcelable("MapOptions"));
                }
                this.zzbb.onCreate(bundle4);
                zzby.zza(bundle4, bundle3);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        }

        public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            Throwable th;
            Bundle bundle2;
            LayoutInflater layoutInflater2 = layoutInflater;
            ViewGroup viewGroup2 = viewGroup;
            Bundle bundle3 = bundle;
            try {
                new Bundle();
                Bundle bundle4 = bundle2;
                zzby.zza(bundle3, bundle4);
                IObjectWrapper onCreateView = this.zzbb.onCreateView(ObjectWrapper.wrap(layoutInflater2), ObjectWrapper.wrap(viewGroup2), bundle4);
                zzby.zza(bundle4, bundle3);
                return (View) ObjectWrapper.unwrap(onCreateView);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        }

        public final void onStart() {
            Throwable th;
            try {
                this.zzbb.onStart();
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
                this.zzbb.onResume();
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
                this.zzbb.onPause();
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
                this.zzbb.onStop();
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        }

        public final void onDestroyView() {
            Throwable th;
            try {
                this.zzbb.onDestroyView();
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        }

        public final void onDestroy() {
            Throwable th;
            try {
                this.zzbb.onDestroy();
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
                this.zzbb.onLowMemory();
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
                this.zzbb.onSaveInstanceState(bundle4);
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
                new zzak(this, onMapReadyCallback);
                this.zzbb.getMapAsync(zzap);
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
                this.zzbb.onEnterAmbient(bundle4);
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
                this.zzbb.onExitAmbient();
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
        private final Fragment fragment;
        private OnDelegateCreatedListener<zza> zzbd;
        private Activity zzbe;
        private final List<OnMapReadyCallback> zzbf;

        @VisibleForTesting
        zzb(Fragment fragment2) {
            List<OnMapReadyCallback> list;
            new ArrayList();
            this.zzbf = list;
            this.fragment = fragment2;
        }

        /* access modifiers changed from: protected */
        public final void createDelegate(OnDelegateCreatedListener<zza> onDelegateCreatedListener) {
            this.zzbd = onDelegateCreatedListener;
            zzd();
        }

        private final void zzd() {
            Throwable th;
            LifecycleDelegate lifecycleDelegate;
            if (this.zzbe != null && this.zzbd != null && getDelegate() == null) {
                try {
                    int initialize = MapsInitializer.initialize(this.zzbe);
                    IMapFragmentDelegate zzc = zzbz.zza((Context) this.zzbe).zzc(ObjectWrapper.wrap(this.zzbe));
                    IMapFragmentDelegate iMapFragmentDelegate = zzc;
                    if (zzc != null) {
                        new zza(this.fragment, iMapFragmentDelegate);
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

        /* access modifiers changed from: private */
        public final void setActivity(Activity activity) {
            this.zzbe = activity;
            zzd();
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

    public SupportMapFragment() {
        zzb zzb2;
        new zzb(this);
        this.zzch = zzb2;
    }

    public void onAttach(Activity activity) {
        Activity activity2 = activity;
        super.onAttach(activity2);
        this.zzch.setActivity(activity2);
    }

    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        StrictMode.ThreadPolicy.Builder builder;
        Bundle bundle2;
        Activity activity2 = activity;
        AttributeSet attributeSet2 = attributeSet;
        Bundle bundle3 = bundle;
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        new StrictMode.ThreadPolicy.Builder(threadPolicy);
        StrictMode.setThreadPolicy(builder.permitAll().build());
        try {
            super.onInflate(activity2, attributeSet2, bundle3);
            this.zzch.setActivity(activity2);
            GoogleMapOptions createFromAttributes = GoogleMapOptions.createFromAttributes(activity2, attributeSet2);
            new Bundle();
            Bundle bundle4 = bundle2;
            bundle4.putParcelable("MapOptions", createFromAttributes);
            this.zzch.onInflate(activity2, bundle4, bundle3);
            StrictMode.setThreadPolicy(threadPolicy);
        } catch (Throwable th) {
            Throwable th2 = th;
            StrictMode.setThreadPolicy(threadPolicy);
            throw th2;
        }
    }

    public void onCreate(Bundle bundle) {
        Bundle bundle2 = bundle;
        super.onCreate(bundle2);
        this.zzch.onCreate(bundle2);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = this.zzch.onCreateView(layoutInflater, viewGroup, bundle);
        onCreateView.setClickable(true);
        return onCreateView;
    }

    public void onResume() {
        super.onResume();
        this.zzch.onResume();
    }

    public void onPause() {
        this.zzch.onPause();
        super.onPause();
    }

    public void onStart() {
        super.onStart();
        this.zzch.onStart();
    }

    public void onStop() {
        this.zzch.onStop();
        super.onStop();
    }

    public void onDestroyView() {
        this.zzch.onDestroyView();
        super.onDestroyView();
    }

    public void onDestroy() {
        this.zzch.onDestroy();
        super.onDestroy();
    }

    public void onLowMemory() {
        this.zzch.onLowMemory();
        super.onLowMemory();
    }

    public void onActivityCreated(Bundle bundle) {
        Bundle bundle2 = bundle;
        if (bundle2 != null) {
            bundle2.setClassLoader(SupportMapFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle2);
    }

    public void onSaveInstanceState(Bundle bundle) {
        Bundle bundle2 = bundle;
        if (bundle2 != null) {
            bundle2.setClassLoader(SupportMapFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle2);
        this.zzch.onSaveInstanceState(bundle2);
    }

    public final void onEnterAmbient(Bundle bundle) {
        Preconditions.checkMainThread("onEnterAmbient must be called on the main thread.");
        Bundle bundle2 = bundle;
        zzb zzb2 = this.zzch;
        zzb zzb3 = zzb2;
        if (zzb2.getDelegate() != null) {
            ((zza) zzb3.getDelegate()).onEnterAmbient(bundle2);
        }
    }

    public final void onExitAmbient() {
        Preconditions.checkMainThread("onExitAmbient must be called on the main thread.");
        zzb zzb2 = this.zzch;
        zzb zzb3 = zzb2;
        if (zzb2.getDelegate() != null) {
            ((zza) zzb3.getDelegate()).onExitAmbient();
        }
    }

    public void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
        Preconditions.checkMainThread("getMapAsync must be called on the main thread.");
        this.zzch.getMapAsync(onMapReadyCallback);
    }

    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }
}
