package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.util.C1642ArrayMap;
import com.google.android.gms.internal.common.zze;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

public final class zzc extends Fragment implements LifecycleFragment {
    private static WeakHashMap<FragmentActivity, WeakReference<zzc>> zzbe;
    private Map<String, LifecycleCallback> zzbf;
    /* access modifiers changed from: private */
    public int zzbg = 0;
    /* access modifiers changed from: private */
    public Bundle zzbh;

    public zzc() {
        Map<String, LifecycleCallback> map;
        new C1642ArrayMap();
        this.zzbf = map;
    }

    public static zzc zza(FragmentActivity fragmentActivity) {
        Throwable th;
        Object obj;
        zzc zzc;
        FragmentActivity fragmentActivity2 = fragmentActivity;
        WeakReference weakReference = zzbe.get(fragmentActivity2);
        WeakReference weakReference2 = weakReference;
        if (weakReference != null) {
            zzc zzc2 = (zzc) weakReference2.get();
            zzc zzc3 = zzc2;
            if (zzc2 != null) {
                return zzc3;
            }
        }
        try {
            zzc zzc4 = (zzc) fragmentActivity2.getSupportFragmentManager().findFragmentByTag("SupportLifecycleFragmentImpl");
            if (zzc4 == null || zzc4.isRemoving()) {
                new zzc();
                zzc4 = zzc;
                int commitAllowingStateLoss = fragmentActivity2.getSupportFragmentManager().beginTransaction().add((Fragment) zzc4, "SupportLifecycleFragmentImpl").commitAllowingStateLoss();
            }
            new WeakReference(zzc4);
            WeakReference<zzc> put = zzbe.put(fragmentActivity2, obj);
            return zzc4;
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new IllegalStateException("Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl", classCastException);
            throw th2;
        }
    }

    public final <T extends LifecycleCallback> T getCallbackOrNull(String str, Class<T> cls) {
        return (LifecycleCallback) cls.cast(this.zzbf.get(str));
    }

    public final void addCallback(String str, @NonNull LifecycleCallback lifecycleCallback) {
        Throwable th;
        StringBuilder sb;
        zze zze;
        Runnable runnable;
        String str2 = str;
        LifecycleCallback lifecycleCallback2 = lifecycleCallback;
        if (!this.zzbf.containsKey(str2)) {
            LifecycleCallback put = this.zzbf.put(str2, lifecycleCallback2);
            LifecycleCallback lifecycleCallback3 = lifecycleCallback2;
            String str3 = str2;
            if (this.zzbg > 0) {
                new zze(Looper.getMainLooper());
                new zzd(this, lifecycleCallback3, str3);
                boolean post = zze.post(runnable);
                return;
            }
            return;
        }
        Throwable th2 = th;
        new StringBuilder(59 + String.valueOf(str2).length());
        new IllegalArgumentException(sb.append("LifecycleCallback with tag ").append(str2).append(" already added to this fragment.").toString());
        throw th2;
    }

    public final boolean isCreated() {
        return this.zzbg > 0;
    }

    public final boolean isStarted() {
        return this.zzbg >= 2;
    }

    public final void onCreate(Bundle bundle) {
        Bundle bundle2 = bundle;
        super.onCreate(bundle2);
        this.zzbg = 1;
        this.zzbh = bundle2;
        for (Map.Entry next : this.zzbf.entrySet()) {
            ((LifecycleCallback) next.getValue()).onCreate(bundle2 != null ? bundle2.getBundle((String) next.getKey()) : null);
        }
    }

    public final void onStart() {
        super.onStart();
        this.zzbg = 2;
        for (LifecycleCallback onStart : this.zzbf.values()) {
            onStart.onStart();
        }
    }

    public final void onResume() {
        super.onResume();
        this.zzbg = 3;
        for (LifecycleCallback onResume : this.zzbf.values()) {
            onResume.onResume();
        }
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
        int i3 = i;
        int i4 = i2;
        Intent intent2 = intent;
        super.onActivityResult(i3, i4, intent2);
        for (LifecycleCallback onActivityResult : this.zzbf.values()) {
            onActivityResult.onActivityResult(i3, i4, intent2);
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        Bundle bundle2;
        Bundle bundle3 = bundle;
        super.onSaveInstanceState(bundle3);
        if (bundle3 != null) {
            for (Map.Entry next : this.zzbf.entrySet()) {
                new Bundle();
                Bundle bundle4 = bundle2;
                ((LifecycleCallback) next.getValue()).onSaveInstanceState(bundle4);
                bundle3.putBundle((String) next.getKey(), bundle4);
            }
        }
    }

    public final void onStop() {
        super.onStop();
        this.zzbg = 4;
        for (LifecycleCallback onStop : this.zzbf.values()) {
            onStop.onStop();
        }
    }

    public final void onDestroy() {
        super.onDestroy();
        this.zzbg = 5;
        for (LifecycleCallback onDestroy : this.zzbf.values()) {
            onDestroy.onDestroy();
        }
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str2 = str;
        FileDescriptor fileDescriptor2 = fileDescriptor;
        PrintWriter printWriter2 = printWriter;
        String[] strArr2 = strArr;
        super.dump(str2, fileDescriptor2, printWriter2, strArr2);
        for (LifecycleCallback dump : this.zzbf.values()) {
            dump.dump(str2, fileDescriptor2, printWriter2, strArr2);
        }
    }

    public final /* synthetic */ Activity getLifecycleActivity() {
        return getActivity();
    }

    static {
        WeakHashMap<FragmentActivity, WeakReference<zzc>> weakHashMap;
        new WeakHashMap<>();
        zzbe = weakHashMap;
    }
}
