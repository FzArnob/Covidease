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
import com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate;
import com.google.android.gms.maps.internal.StreetViewLifecycleDelegate;
import com.google.android.gms.maps.internal.zzbp;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

public class SupportStreetViewPanoramaFragment extends Fragment {
    private final zzb zzci;

    public static SupportStreetViewPanoramaFragment newInstance() {
        SupportStreetViewPanoramaFragment supportStreetViewPanoramaFragment;
        SupportStreetViewPanoramaFragment supportStreetViewPanoramaFragment2 = supportStreetViewPanoramaFragment;
        new SupportStreetViewPanoramaFragment();
        return supportStreetViewPanoramaFragment2;
    }

    public static SupportStreetViewPanoramaFragment newInstance(StreetViewPanoramaOptions streetViewPanoramaOptions) {
        SupportStreetViewPanoramaFragment supportStreetViewPanoramaFragment;
        Bundle bundle;
        new SupportStreetViewPanoramaFragment();
        SupportStreetViewPanoramaFragment supportStreetViewPanoramaFragment2 = supportStreetViewPanoramaFragment;
        new Bundle();
        Bundle bundle2 = bundle;
        bundle2.putParcelable("StreetViewPanoramaOptions", streetViewPanoramaOptions);
        supportStreetViewPanoramaFragment2.setArguments(bundle2);
        return supportStreetViewPanoramaFragment2;
    }

    @VisibleForTesting
    static class zza implements StreetViewLifecycleDelegate {
        private final Fragment fragment;
        private final IStreetViewPanoramaFragmentDelegate zzbu;

        public zza(Fragment fragment2, IStreetViewPanoramaFragmentDelegate iStreetViewPanoramaFragmentDelegate) {
            this.zzbu = (IStreetViewPanoramaFragmentDelegate) Preconditions.checkNotNull(iStreetViewPanoramaFragmentDelegate);
            this.fragment = (Fragment) Preconditions.checkNotNull(fragment2);
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
                Bundle arguments = this.fragment.getArguments();
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
                new zzal(this, onStreetViewPanoramaReadyCallback);
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
        private final Fragment fragment;
        private OnDelegateCreatedListener<zza> zzbd;
        private Activity zzbe;
        private final List<OnStreetViewPanoramaReadyCallback> zzbw;

        @VisibleForTesting
        zzb(Fragment fragment2) {
            List<OnStreetViewPanoramaReadyCallback> list;
            new ArrayList();
            this.zzbw = list;
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
                    new zza(this.fragment, zzbz.zza((Context) this.zzbe).zzd(ObjectWrapper.wrap(this.zzbe)));
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

    public SupportStreetViewPanoramaFragment() {
        zzb zzb2;
        new zzb(this);
        this.zzci = zzb2;
    }

    public void onAttach(Activity activity) {
        Activity activity2 = activity;
        super.onAttach(activity2);
        this.zzci.setActivity(activity2);
    }

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
            this.zzci.setActivity(activity2);
            new Bundle();
            this.zzci.onInflate(activity2, bundle2, bundle3);
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
        this.zzci.onCreate(bundle2);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.zzci.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onStart() {
        super.onStart();
        this.zzci.onStart();
    }

    public void onResume() {
        super.onResume();
        this.zzci.onResume();
    }

    public void onPause() {
        this.zzci.onPause();
        super.onPause();
    }

    public void onStop() {
        this.zzci.onStop();
        super.onStop();
    }

    public void onDestroyView() {
        this.zzci.onDestroyView();
        super.onDestroyView();
    }

    public void onDestroy() {
        this.zzci.onDestroy();
        super.onDestroy();
    }

    public void onLowMemory() {
        this.zzci.onLowMemory();
        super.onLowMemory();
    }

    public void onActivityCreated(Bundle bundle) {
        Bundle bundle2 = bundle;
        if (bundle2 != null) {
            bundle2.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle2);
    }

    public void onSaveInstanceState(Bundle bundle) {
        Bundle bundle2 = bundle;
        if (bundle2 != null) {
            bundle2.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle2);
        this.zzci.onSaveInstanceState(bundle2);
    }

    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }

    public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        Preconditions.checkMainThread("getStreetViewPanoramaAsync() must be called on the main thread");
        this.zzci.getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
    }
}
