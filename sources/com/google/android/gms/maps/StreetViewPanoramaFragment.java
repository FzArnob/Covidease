package com.google.android.gms.maps;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.StrictMode;
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
import com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate;
import com.google.android.gms.maps.internal.StreetViewLifecycleDelegate;
import com.google.android.gms.maps.internal.zzbp;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

@TargetApi(11)
public class StreetViewPanoramaFragment extends Fragment {
    private final zzb zzbt;

    public static StreetViewPanoramaFragment newInstance() {
        StreetViewPanoramaFragment streetViewPanoramaFragment;
        StreetViewPanoramaFragment streetViewPanoramaFragment2 = streetViewPanoramaFragment;
        new StreetViewPanoramaFragment();
        return streetViewPanoramaFragment2;
    }

    public static StreetViewPanoramaFragment newInstance(StreetViewPanoramaOptions streetViewPanoramaOptions) {
        StreetViewPanoramaFragment streetViewPanoramaFragment;
        Bundle bundle;
        new StreetViewPanoramaFragment();
        StreetViewPanoramaFragment streetViewPanoramaFragment2 = streetViewPanoramaFragment;
        new Bundle();
        Bundle bundle2 = bundle;
        bundle2.putParcelable("StreetViewPanoramaOptions", streetViewPanoramaOptions);
        streetViewPanoramaFragment2.setArguments(bundle2);
        return streetViewPanoramaFragment2;
    }

    @VisibleForTesting
    static class zza implements StreetViewLifecycleDelegate {
        private final Fragment zzba;
        private final IStreetViewPanoramaFragmentDelegate zzbu;

        public zza(Fragment fragment, IStreetViewPanoramaFragmentDelegate iStreetViewPanoramaFragmentDelegate) {
            this.zzbu = (IStreetViewPanoramaFragmentDelegate) Preconditions.checkNotNull(iStreetViewPanoramaFragmentDelegate);
            this.zzba = (Fragment) Preconditions.checkNotNull(fragment);
        }

        public final void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            Throwable th;
            Bundle bundle3;
            Activity activity2 = activity;
            Bundle bundle4 = bundle;
            Bundle bundle5 = bundle2;
            try {
                new Bundle();
                Bundle bundle6 = bundle3;
                zzby.zza(bundle5, bundle6);
                this.zzbu.onInflate(ObjectWrapper.wrap(activity2), (StreetViewPanoramaOptions) null, bundle6);
                zzby.zza(bundle6, bundle5);
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
                Bundle arguments = this.zzba.getArguments();
                Bundle bundle5 = arguments;
                if (arguments != null && bundle5.containsKey("StreetViewPanoramaOptions")) {
                    zzby.zza(bundle4, "StreetViewPanoramaOptions", bundle5.getParcelable("StreetViewPanoramaOptions"));
                }
                this.zzbu.onCreate(bundle4);
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
                IObjectWrapper onCreateView = this.zzbu.onCreateView(ObjectWrapper.wrap(layoutInflater2), ObjectWrapper.wrap(viewGroup2), bundle4);
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
                this.zzbu.onStart();
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
                this.zzbu.onResume();
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
                this.zzbu.onPause();
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
                this.zzbu.onStop();
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
                this.zzbu.onDestroyView();
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
                this.zzbu.onDestroy();
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
                this.zzbu.onLowMemory();
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
                this.zzbu.onSaveInstanceState(bundle4);
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
                new zzah(this, onStreetViewPanoramaReadyCallback);
                this.zzbu.getStreetViewPanoramaAsync(zzbp);
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
        private final Fragment zzba;
        private OnDelegateCreatedListener<zza> zzbd;
        private Activity zzbe;
        private final List<OnStreetViewPanoramaReadyCallback> zzbw;

        @VisibleForTesting
        zzb(Fragment fragment) {
            List<OnStreetViewPanoramaReadyCallback> list;
            new ArrayList();
            this.zzbw = list;
            this.zzba = fragment;
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
                    new zza(this.zzba, zzbz.zza((Context) this.zzbe).zzd(ObjectWrapper.wrap(this.zzbe)));
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

        /* access modifiers changed from: private */
        public final void setActivity(Activity activity) {
            this.zzbe = activity;
            zzd();
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

    public StreetViewPanoramaFragment() {
        zzb zzb2;
        new zzb(this);
        this.zzbt = zzb2;
    }

    public void onAttach(Activity activity) {
        Activity activity2 = activity;
        super.onAttach(activity2);
        this.zzbt.setActivity(activity2);
    }

    @SuppressLint({"NewApi"})
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        StrictMode.ThreadPolicy.Builder builder;
        Bundle bundle2;
        Activity activity2 = activity;
        Bundle bundle3 = bundle;
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        new StrictMode.ThreadPolicy.Builder(threadPolicy);
        StrictMode.setThreadPolicy(builder.permitAll().build());
        try {
            super.onInflate(activity2, attributeSet, bundle3);
            this.zzbt.setActivity(activity2);
            new Bundle();
            this.zzbt.onInflate(activity2, bundle2, bundle3);
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
        this.zzbt.onCreate(bundle2);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.zzbt.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onStart() {
        super.onStart();
        this.zzbt.onStart();
    }

    public void onResume() {
        super.onResume();
        this.zzbt.onResume();
    }

    public void onPause() {
        this.zzbt.onPause();
        super.onPause();
    }

    public void onStop() {
        this.zzbt.onStop();
        super.onStop();
    }

    public void onDestroyView() {
        this.zzbt.onDestroyView();
        super.onDestroyView();
    }

    public void onDestroy() {
        this.zzbt.onDestroy();
        super.onDestroy();
    }

    public void onLowMemory() {
        this.zzbt.onLowMemory();
        super.onLowMemory();
    }

    public void onActivityCreated(Bundle bundle) {
        Bundle bundle2 = bundle;
        if (bundle2 != null) {
            bundle2.setClassLoader(StreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle2);
    }

    public void onSaveInstanceState(Bundle bundle) {
        Bundle bundle2 = bundle;
        if (bundle2 != null) {
            bundle2.setClassLoader(StreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle2);
        this.zzbt.onSaveInstanceState(bundle2);
    }

    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }

    public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        Preconditions.checkMainThread("getStreetViewPanoramaAsync() must be called on the main thread");
        this.zzbt.getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
    }
}
