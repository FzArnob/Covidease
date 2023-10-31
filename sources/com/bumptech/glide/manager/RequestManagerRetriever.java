package com.bumptech.glide.manager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentActivity;
import android.util.Log;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.util.Util;
import java.util.HashMap;
import java.util.Map;

public class RequestManagerRetriever implements Handler.Callback {
    static final String FRAGMENT_TAG = "com.bumptech.glide.manager";
    private static final int ID_REMOVE_FRAGMENT_MANAGER = 1;
    private static final int ID_REMOVE_SUPPORT_FRAGMENT_MANAGER = 2;
    private static final RequestManagerRetriever INSTANCE;
    private static final String TAG = "RMRetriever";
    private volatile RequestManager applicationManager;
    private final Handler handler;
    final Map<FragmentManager, RequestManagerFragment> pendingRequestManagerFragments;
    final Map<android.support.p000v4.app.FragmentManager, SupportRequestManagerFragment> pendingSupportRequestManagerFragments;

    static {
        RequestManagerRetriever requestManagerRetriever;
        new RequestManagerRetriever();
        INSTANCE = requestManagerRetriever;
    }

    public static RequestManagerRetriever get() {
        return INSTANCE;
    }

    RequestManagerRetriever() {
        Map<FragmentManager, RequestManagerFragment> map;
        Map<android.support.p000v4.app.FragmentManager, SupportRequestManagerFragment> map2;
        Handler handler2;
        new HashMap();
        this.pendingRequestManagerFragments = map;
        new HashMap();
        this.pendingSupportRequestManagerFragments = map2;
        new Handler(Looper.getMainLooper(), this);
        this.handler = handler2;
    }

    private RequestManager getApplicationManager(Context context) {
        RequestManager requestManager;
        Lifecycle lifecycle;
        RequestManagerTreeNode requestManagerTreeNode;
        Context context2 = context;
        if (this.applicationManager == null) {
            synchronized (this) {
                try {
                    if (this.applicationManager == null) {
                        new ApplicationLifecycle();
                        new EmptyRequestManagerTreeNode();
                        new RequestManager(context2.getApplicationContext(), lifecycle, requestManagerTreeNode);
                        this.applicationManager = requestManager;
                    }
                } catch (Throwable th) {
                    while (true) {
                        Throwable th2 = th;
                        throw th2;
                    }
                }
            }
        }
        return this.applicationManager;
    }

    public RequestManager get(Context context) {
        Throwable th;
        Context context2 = context;
        if (context2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("You cannot start a load on a null Context");
            throw th2;
        }
        if (Util.isOnMainThread() && !(context2 instanceof Application)) {
            if (context2 instanceof FragmentActivity) {
                return get((FragmentActivity) context2);
            }
            if (context2 instanceof Activity) {
                return get((Activity) context2);
            }
            if (context2 instanceof ContextWrapper) {
                return get(((ContextWrapper) context2).getBaseContext());
            }
        }
        return getApplicationManager(context2);
    }

    public RequestManager get(FragmentActivity fragmentActivity) {
        FragmentActivity activity = fragmentActivity;
        if (Util.isOnBackgroundThread()) {
            return get(activity.getApplicationContext());
        }
        assertNotDestroyed(activity);
        return supportFragmentGet(activity, activity.getSupportFragmentManager());
    }

    public RequestManager get(Fragment fragment) {
        Throwable th;
        Fragment fragment2 = fragment;
        if (fragment2.getActivity() == null) {
            Throwable th2 = th;
            new IllegalArgumentException("You cannot start a load on a fragment before it is attached");
            throw th2;
        } else if (Util.isOnBackgroundThread()) {
            return get(fragment2.getActivity().getApplicationContext());
        } else {
            return supportFragmentGet(fragment2.getActivity(), fragment2.getChildFragmentManager());
        }
    }

    @TargetApi(11)
    public RequestManager get(Activity activity) {
        Activity activity2 = activity;
        if (Util.isOnBackgroundThread() || Build.VERSION.SDK_INT < 11) {
            return get(activity2.getApplicationContext());
        }
        assertNotDestroyed(activity2);
        return fragmentGet(activity2, activity2.getFragmentManager());
    }

    @TargetApi(17)
    private static void assertNotDestroyed(Activity activity) {
        Throwable th;
        Activity activity2 = activity;
        if (Build.VERSION.SDK_INT >= 17 && activity2.isDestroyed()) {
            Throwable th2 = th;
            new IllegalArgumentException("You cannot start a load for a destroyed activity");
            throw th2;
        }
    }

    @TargetApi(17)
    public RequestManager get(android.app.Fragment fragment) {
        Throwable th;
        android.app.Fragment fragment2 = fragment;
        if (fragment2.getActivity() == null) {
            Throwable th2 = th;
            new IllegalArgumentException("You cannot start a load on a fragment before it is attached");
            throw th2;
        } else if (Util.isOnBackgroundThread() || Build.VERSION.SDK_INT < 17) {
            return get(fragment2.getActivity().getApplicationContext());
        } else {
            return fragmentGet(fragment2.getActivity(), fragment2.getChildFragmentManager());
        }
    }

    /* access modifiers changed from: package-private */
    @TargetApi(17)
    public RequestManagerFragment getRequestManagerFragment(FragmentManager fragmentManager) {
        RequestManagerFragment requestManagerFragment;
        FragmentManager fm = fragmentManager;
        RequestManagerFragment current = (RequestManagerFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (current == null) {
            current = this.pendingRequestManagerFragments.get(fm);
            if (current == null) {
                new RequestManagerFragment();
                current = requestManagerFragment;
                RequestManagerFragment put = this.pendingRequestManagerFragments.put(fm, current);
                int commitAllowingStateLoss = fm.beginTransaction().add(current, FRAGMENT_TAG).commitAllowingStateLoss();
                this.handler.obtainMessage(1, fm).sendToTarget();
            }
        }
        return current;
    }

    /* access modifiers changed from: package-private */
    @TargetApi(11)
    public RequestManager fragmentGet(Context context, FragmentManager fm) {
        RequestManager requestManager;
        Context context2 = context;
        RequestManagerFragment current = getRequestManagerFragment(fm);
        RequestManager requestManager2 = current.getRequestManager();
        if (requestManager2 == null) {
            new RequestManager(context2, current.getLifecycle(), current.getRequestManagerTreeNode());
            requestManager2 = requestManager;
            current.setRequestManager(requestManager2);
        }
        return requestManager2;
    }

    /* access modifiers changed from: package-private */
    public SupportRequestManagerFragment getSupportRequestManagerFragment(android.support.p000v4.app.FragmentManager fragmentManager) {
        SupportRequestManagerFragment supportRequestManagerFragment;
        android.support.p000v4.app.FragmentManager fm = fragmentManager;
        SupportRequestManagerFragment current = (SupportRequestManagerFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (current == null) {
            current = this.pendingSupportRequestManagerFragments.get(fm);
            if (current == null) {
                new SupportRequestManagerFragment();
                current = supportRequestManagerFragment;
                SupportRequestManagerFragment put = this.pendingSupportRequestManagerFragments.put(fm, current);
                int commitAllowingStateLoss = fm.beginTransaction().add((Fragment) current, FRAGMENT_TAG).commitAllowingStateLoss();
                this.handler.obtainMessage(2, fm).sendToTarget();
            }
        }
        return current;
    }

    /* access modifiers changed from: package-private */
    public RequestManager supportFragmentGet(Context context, android.support.p000v4.app.FragmentManager fm) {
        RequestManager requestManager;
        Context context2 = context;
        SupportRequestManagerFragment current = getSupportRequestManagerFragment(fm);
        RequestManager requestManager2 = current.getRequestManager();
        if (requestManager2 == null) {
            new RequestManager(context2, current.getLifecycle(), current.getRequestManagerTreeNode());
            requestManager2 = requestManager;
            current.setRequestManager(requestManager2);
        }
        return requestManager2;
    }

    public boolean handleMessage(Message message) {
        StringBuilder sb;
        Message message2 = message;
        boolean handled = true;
        Object removed = null;
        Object key = null;
        switch (message2.what) {
            case 1:
                Object fm = (FragmentManager) message2.obj;
                key = fm;
                removed = this.pendingRequestManagerFragments.remove(fm);
                break;
            case 2:
                Object supportFm = (android.support.p000v4.app.FragmentManager) message2.obj;
                key = supportFm;
                removed = this.pendingSupportRequestManagerFragments.remove(supportFm);
                break;
            default:
                handled = false;
                break;
        }
        if (handled && removed == null && Log.isLoggable(TAG, 5)) {
            new StringBuilder();
            int w = Log.w(TAG, sb.append("Failed to remove expected request manager fragment, manager: ").append(key).toString());
        }
        return handled;
    }
}
