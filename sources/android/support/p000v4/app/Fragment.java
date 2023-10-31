package android.support.p000v4.app;

import android.animation.Animator;
import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelStore;
import android.arch.lifecycle.ViewModelStoreOwner;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.p000v4.util.C1650SimpleArrayMap;
import android.support.p000v4.util.DebugUtils;
import android.support.p000v4.view.LayoutInflaterCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

/* renamed from: android.support.v4.app.Fragment */
public class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, LifecycleOwner, ViewModelStoreOwner {
    static final int ACTIVITY_CREATED = 2;
    static final int CREATED = 1;
    static final int INITIALIZING = 0;
    static final int RESUMED = 4;
    static final int STARTED = 3;
    static final Object USE_DEFAULT_TRANSITION;
    private static final C1650SimpleArrayMap<String, Class<?>> sClassMap;
    boolean mAdded;
    AnimationInfo mAnimationInfo;
    Bundle mArguments;
    int mBackStackNesting;
    boolean mCalled;
    FragmentManagerImpl mChildFragmentManager;
    FragmentManagerNonConfig mChildNonConfig;
    ViewGroup mContainer;
    int mContainerId;
    boolean mDeferStart;
    boolean mDetached;
    int mFragmentId;
    FragmentManagerImpl mFragmentManager;
    boolean mFromLayout;
    boolean mHasMenu;
    boolean mHidden;
    boolean mHiddenChanged;
    FragmentHostCallback mHost;
    boolean mInLayout;
    int mIndex = -1;
    View mInnerView;
    boolean mIsCreated;
    boolean mIsNewlyAdded;
    LayoutInflater mLayoutInflater;
    LifecycleRegistry mLifecycleRegistry;
    boolean mMenuVisible = true;
    Fragment mParentFragment;
    boolean mPerformedCreateView;
    float mPostponedAlpha;
    boolean mRemoving;
    boolean mRestored;
    boolean mRetainInstance;
    boolean mRetaining;
    Bundle mSavedFragmentState;
    @Nullable
    Boolean mSavedUserVisibleHint;
    SparseArray<Parcelable> mSavedViewState;
    int mState = 0;
    String mTag;
    Fragment mTarget;
    int mTargetIndex = -1;
    int mTargetRequestCode;
    boolean mUserVisibleHint = true;
    View mView;
    LifecycleOwner mViewLifecycleOwner;
    MutableLiveData<LifecycleOwner> mViewLifecycleOwnerLiveData;
    LifecycleRegistry mViewLifecycleRegistry;
    ViewModelStore mViewModelStore;
    String mWho;

    /* renamed from: android.support.v4.app.Fragment$OnStartEnterTransitionListener */
    interface OnStartEnterTransitionListener {
        void onStartEnterTransition();

        void startListening();
    }

    static {
        C1650SimpleArrayMap<String, Class<?>> simpleArrayMap;
        Object obj;
        new C1650SimpleArrayMap<>();
        sClassMap = simpleArrayMap;
        new Object();
        USE_DEFAULT_TRANSITION = obj;
    }

    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @MainThread
    @NonNull
    public LifecycleOwner getViewLifecycleOwner() {
        Throwable th;
        if (this.mViewLifecycleOwner != null) {
            return this.mViewLifecycleOwner;
        }
        Throwable th2 = th;
        new IllegalStateException("Can't access the Fragment View's LifecycleOwner when getView() is null i.e., before onCreateView() or after onDestroyView()");
        throw th2;
    }

    @NonNull
    public LiveData<LifecycleOwner> getViewLifecycleOwnerLiveData() {
        return this.mViewLifecycleOwnerLiveData;
    }

    @NonNull
    public ViewModelStore getViewModelStore() {
        ViewModelStore viewModelStore;
        Throwable th;
        if (getContext() == null) {
            Throwable th2 = th;
            new IllegalStateException("Can't access ViewModels from detached fragment");
            throw th2;
        }
        if (this.mViewModelStore == null) {
            new ViewModelStore();
            this.mViewModelStore = viewModelStore;
        }
        return this.mViewModelStore;
    }

    /* renamed from: android.support.v4.app.Fragment$SavedState */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR;
        final Bundle mState;

        SavedState(Bundle state) {
            this.mState = state;
        }

        SavedState(Parcel in, ClassLoader classLoader) {
            ClassLoader loader = classLoader;
            this.mState = in.readBundle();
            if (loader != null && this.mState != null) {
                this.mState.setClassLoader(loader);
            }
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int i) {
            int i2 = i;
            dest.writeBundle(this.mState);
        }

        static {
            Parcelable.Creator<SavedState> creator;
            new Parcelable.ClassLoaderCreator<SavedState>() {
                public SavedState createFromParcel(Parcel in) {
                    SavedState savedState;
                    new SavedState(in, (ClassLoader) null);
                    return savedState;
                }

                public SavedState createFromParcel(Parcel in, ClassLoader loader) {
                    SavedState savedState;
                    new SavedState(in, loader);
                    return savedState;
                }

                public SavedState[] newArray(int size) {
                    return new SavedState[size];
                }
            };
            CREATOR = creator;
        }
    }

    /* renamed from: android.support.v4.app.Fragment$InstantiationException */
    public static class InstantiationException extends RuntimeException {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public InstantiationException(String msg, Exception cause) {
            super(msg, cause);
        }
    }

    public Fragment() {
        LifecycleRegistry lifecycleRegistry;
        MutableLiveData<LifecycleOwner> mutableLiveData;
        new LifecycleRegistry(this);
        this.mLifecycleRegistry = lifecycleRegistry;
        new MutableLiveData<>();
        this.mViewLifecycleOwnerLiveData = mutableLiveData;
    }

    public static Fragment instantiate(Context context, String fname) {
        return instantiate(context, fname, (Bundle) null);
    }

    public static Fragment instantiate(Context context, String str, @Nullable Bundle bundle) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        StringBuilder sb3;
        Throwable th4;
        StringBuilder sb4;
        Throwable th5;
        StringBuilder sb5;
        Context context2 = context;
        String fname = str;
        Bundle args = bundle;
        try {
            Class<?> clazz = sClassMap.get(fname);
            if (clazz == null) {
                clazz = context2.getClassLoader().loadClass(fname);
                Class<?> put = sClassMap.put(fname, clazz);
            }
            Fragment f = (Fragment) clazz.getConstructor(new Class[0]).newInstance(new Object[0]);
            if (args != null) {
                args.setClassLoader(f.getClass().getClassLoader());
                f.setArguments(args);
            }
            return f;
        } catch (ClassNotFoundException e) {
            ClassNotFoundException e2 = e;
            Throwable th6 = th5;
            new StringBuilder();
            new InstantiationException(sb5.append("Unable to instantiate fragment ").append(fname).append(": make sure class name exists, is public, and has an").append(" empty constructor that is public").toString(), e2);
            throw th6;
        } catch (InstantiationException e3) {
            InstantiationException e4 = e3;
            Throwable th7 = th4;
            new StringBuilder();
            new InstantiationException(sb4.append("Unable to instantiate fragment ").append(fname).append(": make sure class name exists, is public, and has an").append(" empty constructor that is public").toString(), e4);
            throw th7;
        } catch (IllegalAccessException e5) {
            IllegalAccessException e6 = e5;
            Throwable th8 = th3;
            new StringBuilder();
            new InstantiationException(sb3.append("Unable to instantiate fragment ").append(fname).append(": make sure class name exists, is public, and has an").append(" empty constructor that is public").toString(), e6);
            throw th8;
        } catch (NoSuchMethodException e7) {
            NoSuchMethodException e8 = e7;
            Throwable th9 = th2;
            new StringBuilder();
            new InstantiationException(sb2.append("Unable to instantiate fragment ").append(fname).append(": could not find Fragment constructor").toString(), e8);
            throw th9;
        } catch (InvocationTargetException e9) {
            InvocationTargetException e10 = e9;
            Throwable th10 = th;
            new StringBuilder();
            new InstantiationException(sb.append("Unable to instantiate fragment ").append(fname).append(": calling Fragment constructor caused an exception").toString(), e10);
            throw th10;
        }
    }

    static boolean isSupportFragmentClass(Context context, String str) {
        Context context2 = context;
        String fname = str;
        try {
            Class<?> clazz = sClassMap.get(fname);
            if (clazz == null) {
                clazz = context2.getClassLoader().loadClass(fname);
                Class<?> put = sClassMap.put(fname, clazz);
            }
            return Fragment.class.isAssignableFrom(clazz);
        } catch (ClassNotFoundException e) {
            ClassNotFoundException classNotFoundException = e;
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public final void restoreViewState(Bundle bundle) {
        Throwable th;
        StringBuilder sb;
        Bundle savedInstanceState = bundle;
        if (this.mSavedViewState != null) {
            this.mInnerView.restoreHierarchyState(this.mSavedViewState);
            this.mSavedViewState = null;
        }
        this.mCalled = false;
        onViewStateRestored(savedInstanceState);
        if (!this.mCalled) {
            Throwable th2 = th;
            new StringBuilder();
            new SuperNotCalledException(sb.append("Fragment ").append(this).append(" did not call through to super.onViewStateRestored()").toString());
            throw th2;
        } else if (this.mView != null) {
            this.mViewLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        }
    }

    /* access modifiers changed from: package-private */
    public final void setIndex(int index, Fragment fragment) {
        StringBuilder sb;
        StringBuilder sb2;
        Fragment parent = fragment;
        this.mIndex = index;
        if (parent != null) {
            new StringBuilder();
            this.mWho = sb2.append(parent.mWho).append(":").append(this.mIndex).toString();
            return;
        }
        new StringBuilder();
        this.mWho = sb.append("android:fragment:").append(this.mIndex).toString();
    }

    /* access modifiers changed from: package-private */
    public final boolean isInBackStack() {
        return this.mBackStackNesting > 0;
    }

    public final boolean equals(Object o) {
        return super.equals(o);
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder(128);
        StringBuilder sb2 = sb;
        DebugUtils.buildShortClassTag(this, sb2);
        if (this.mIndex >= 0) {
            StringBuilder append = sb2.append(" #");
            StringBuilder append2 = sb2.append(this.mIndex);
        }
        if (this.mFragmentId != 0) {
            StringBuilder append3 = sb2.append(" id=0x");
            StringBuilder append4 = sb2.append(Integer.toHexString(this.mFragmentId));
        }
        if (this.mTag != null) {
            StringBuilder append5 = sb2.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            StringBuilder append6 = sb2.append(this.mTag);
        }
        StringBuilder append7 = sb2.append('}');
        return sb2.toString();
    }

    public final int getId() {
        return this.mFragmentId;
    }

    @Nullable
    public final String getTag() {
        return this.mTag;
    }

    public void setArguments(@Nullable Bundle bundle) {
        Throwable th;
        Bundle args = bundle;
        if (this.mIndex < 0 || !isStateSaved()) {
            this.mArguments = args;
            return;
        }
        Throwable th2 = th;
        new IllegalStateException("Fragment already active and state has been saved");
        throw th2;
    }

    @Nullable
    public final Bundle getArguments() {
        return this.mArguments;
    }

    public final boolean isStateSaved() {
        if (this.mFragmentManager == null) {
            return false;
        }
        return this.mFragmentManager.isStateSaved();
    }

    public void setInitialSavedState(@Nullable SavedState savedState) {
        Throwable th;
        SavedState state = savedState;
        if (this.mIndex >= 0) {
            Throwable th2 = th;
            new IllegalStateException("Fragment already active");
            throw th2;
        }
        this.mSavedFragmentState = (state == null || state.mState == null) ? null : state.mState;
    }

    public void setTargetFragment(@Nullable Fragment fragment, int i) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Fragment fragment2 = fragment;
        int requestCode = i;
        FragmentManager mine = getFragmentManager();
        FragmentManager theirs = fragment2 != null ? fragment2.getFragmentManager() : null;
        if (mine == null || theirs == null || mine == theirs) {
            Fragment fragment3 = fragment2;
            while (true) {
                Fragment check = fragment3;
                if (check == null) {
                    this.mTarget = fragment2;
                    this.mTargetRequestCode = requestCode;
                    return;
                } else if (check == this) {
                    Throwable th3 = th;
                    new StringBuilder();
                    new IllegalArgumentException(sb.append("Setting ").append(fragment2).append(" as the target of ").append(this).append(" would create a target cycle").toString());
                    throw th3;
                } else {
                    fragment3 = check.getTargetFragment();
                }
            }
        } else {
            Throwable th4 = th2;
            new StringBuilder();
            new IllegalArgumentException(sb2.append("Fragment ").append(fragment2).append(" must share the same FragmentManager to be set as a target fragment").toString());
            throw th4;
        }
    }

    @Nullable
    public final Fragment getTargetFragment() {
        return this.mTarget;
    }

    public final int getTargetRequestCode() {
        return this.mTargetRequestCode;
    }

    @Nullable
    public Context getContext() {
        return this.mHost == null ? null : this.mHost.getContext();
    }

    @NonNull
    public final Context requireContext() {
        Throwable th;
        StringBuilder sb;
        Context context = getContext();
        if (context != null) {
            return context;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Fragment ").append(this).append(" not attached to a context.").toString());
        throw th2;
    }

    @Nullable
    public final FragmentActivity getActivity() {
        return this.mHost == null ? null : (FragmentActivity) this.mHost.getActivity();
    }

    @NonNull
    public final FragmentActivity requireActivity() {
        Throwable th;
        StringBuilder sb;
        FragmentActivity activity = getActivity();
        if (activity != null) {
            return activity;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Fragment ").append(this).append(" not attached to an activity.").toString());
        throw th2;
    }

    @Nullable
    public final Object getHost() {
        return this.mHost == null ? null : this.mHost.onGetHost();
    }

    @NonNull
    public final Object requireHost() {
        Throwable th;
        StringBuilder sb;
        Object host = getHost();
        if (host != null) {
            return host;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Fragment ").append(this).append(" not attached to a host.").toString());
        throw th2;
    }

    @NonNull
    public final Resources getResources() {
        return requireContext().getResources();
    }

    @NonNull
    public final CharSequence getText(@StringRes int resId) {
        return getResources().getText(resId);
    }

    @NonNull
    public final String getString(@StringRes int resId) {
        return getResources().getString(resId);
    }

    @NonNull
    public final String getString(@StringRes int resId, Object... formatArgs) {
        return getResources().getString(resId, formatArgs);
    }

    @Nullable
    public final FragmentManager getFragmentManager() {
        return this.mFragmentManager;
    }

    @NonNull
    public final FragmentManager requireFragmentManager() {
        Throwable th;
        StringBuilder sb;
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            return fragmentManager;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Fragment ").append(this).append(" not associated with a fragment manager.").toString());
        throw th2;
    }

    @NonNull
    public final FragmentManager getChildFragmentManager() {
        if (this.mChildFragmentManager == null) {
            instantiateChildFragmentManager();
            if (this.mState >= 4) {
                this.mChildFragmentManager.dispatchResume();
            } else if (this.mState >= 3) {
                this.mChildFragmentManager.dispatchStart();
            } else if (this.mState >= 2) {
                this.mChildFragmentManager.dispatchActivityCreated();
            } else if (this.mState >= 1) {
                this.mChildFragmentManager.dispatchCreate();
            }
        }
        return this.mChildFragmentManager;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public FragmentManager peekChildFragmentManager() {
        return this.mChildFragmentManager;
    }

    @Nullable
    public final Fragment getParentFragment() {
        return this.mParentFragment;
    }

    public final boolean isAdded() {
        return this.mHost != null && this.mAdded;
    }

    public final boolean isDetached() {
        return this.mDetached;
    }

    public final boolean isRemoving() {
        return this.mRemoving;
    }

    public final boolean isInLayout() {
        return this.mInLayout;
    }

    public final boolean isResumed() {
        return this.mState >= 4;
    }

    public final boolean isVisible() {
        return isAdded() && !isHidden() && this.mView != null && this.mView.getWindowToken() != null && this.mView.getVisibility() == 0;
    }

    public final boolean isHidden() {
        return this.mHidden;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final boolean hasOptionsMenu() {
        return this.mHasMenu;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final boolean isMenuVisible() {
        return this.mMenuVisible;
    }

    public void onHiddenChanged(boolean hidden) {
    }

    public void setRetainInstance(boolean retain) {
        boolean z = retain;
        this.mRetainInstance = z;
    }

    public final boolean getRetainInstance() {
        return this.mRetainInstance;
    }

    public void setHasOptionsMenu(boolean z) {
        boolean hasMenu = z;
        if (this.mHasMenu != hasMenu) {
            this.mHasMenu = hasMenu;
            if (isAdded() && !isHidden()) {
                this.mHost.onSupportInvalidateOptionsMenu();
            }
        }
    }

    public void setMenuVisibility(boolean z) {
        boolean menuVisible = z;
        if (this.mMenuVisible != menuVisible) {
            this.mMenuVisible = menuVisible;
            if (this.mHasMenu && isAdded() && !isHidden()) {
                this.mHost.onSupportInvalidateOptionsMenu();
            }
        }
    }

    public void setUserVisibleHint(boolean z) {
        boolean isVisibleToUser = z;
        if (!this.mUserVisibleHint && isVisibleToUser && this.mState < 3 && this.mFragmentManager != null && isAdded() && this.mIsCreated) {
            this.mFragmentManager.performPendingDeferredStart(this);
        }
        this.mUserVisibleHint = isVisibleToUser;
        this.mDeferStart = this.mState < 3 && !isVisibleToUser;
        if (this.mSavedFragmentState != null) {
            this.mSavedUserVisibleHint = Boolean.valueOf(isVisibleToUser);
        }
    }

    public boolean getUserVisibleHint() {
        return this.mUserVisibleHint;
    }

    @Deprecated
    public LoaderManager getLoaderManager() {
        return LoaderManager.getInstance(this);
    }

    public void startActivity(Intent intent) {
        startActivity(intent, (Bundle) null);
    }

    public void startActivity(Intent intent, @Nullable Bundle bundle) {
        Throwable th;
        StringBuilder sb;
        Intent intent2 = intent;
        Bundle options = bundle;
        if (this.mHost == null) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Fragment ").append(this).append(" not attached to Activity").toString());
            throw th2;
        }
        this.mHost.onStartActivityFromFragment(this, intent2, -1, options);
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode, (Bundle) null);
    }

    public void startActivityForResult(Intent intent, int i, @Nullable Bundle bundle) {
        Throwable th;
        StringBuilder sb;
        Intent intent2 = intent;
        int requestCode = i;
        Bundle options = bundle;
        if (this.mHost == null) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Fragment ").append(this).append(" not attached to Activity").toString());
            throw th2;
        }
        this.mHost.onStartActivityFromFragment(this, intent2, requestCode, options);
    }

    public void startIntentSenderForResult(IntentSender intentSender, int i, @Nullable Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
        Throwable th;
        StringBuilder sb;
        IntentSender intent2 = intentSender;
        int requestCode = i;
        Intent fillInIntent = intent;
        int flagsMask = i2;
        int flagsValues = i3;
        int extraFlags = i4;
        Bundle options = bundle;
        if (this.mHost == null) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Fragment ").append(this).append(" not attached to Activity").toString());
            throw th2;
        }
        this.mHost.onStartIntentSenderFromFragment(this, intent2, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    public final void requestPermissions(@NonNull String[] strArr, int i) {
        Throwable th;
        StringBuilder sb;
        String[] permissions = strArr;
        int requestCode = i;
        if (this.mHost == null) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Fragment ").append(this).append(" not attached to Activity").toString());
            throw th2;
        }
        this.mHost.onRequestPermissionsFromFragment(this, permissions, requestCode);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    }

    public boolean shouldShowRequestPermissionRationale(@NonNull String str) {
        String permission = str;
        if (this.mHost != null) {
            return this.mHost.onShouldShowRequestPermissionRationale(permission);
        }
        return false;
    }

    @NonNull
    public LayoutInflater onGetLayoutInflater(@Nullable Bundle savedInstanceState) {
        return getLayoutInflater(savedInstanceState);
    }

    public final LayoutInflater getLayoutInflater() {
        if (this.mLayoutInflater == null) {
            return performGetLayoutInflater((Bundle) null);
        }
        return this.mLayoutInflater;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public LayoutInflater performGetLayoutInflater(@Nullable Bundle savedInstanceState) {
        this.mLayoutInflater = onGetLayoutInflater(savedInstanceState);
        return this.mLayoutInflater;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Deprecated
    @NonNull
    public LayoutInflater getLayoutInflater(@Nullable Bundle bundle) {
        Throwable th;
        Bundle bundle2 = bundle;
        if (this.mHost == null) {
            Throwable th2 = th;
            new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
            throw th2;
        }
        LayoutInflater result = this.mHost.onGetLayoutInflater();
        FragmentManager childFragmentManager = getChildFragmentManager();
        LayoutInflaterCompat.setFactory2(result, this.mChildFragmentManager.getLayoutInflaterFactory());
        return result;
    }

    @CallSuper
    public void onInflate(Context context, AttributeSet attributeSet, Bundle bundle) {
        Context context2 = context;
        AttributeSet attrs = attributeSet;
        Bundle savedInstanceState = bundle;
        this.mCalled = true;
        Activity hostActivity = this.mHost == null ? null : this.mHost.getActivity();
        if (hostActivity != null) {
            this.mCalled = false;
            onInflate(hostActivity, attrs, savedInstanceState);
        }
    }

    @Deprecated
    @CallSuper
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        Activity activity2 = activity;
        AttributeSet attributeSet2 = attributeSet;
        Bundle bundle2 = bundle;
        this.mCalled = true;
    }

    public void onAttachFragment(Fragment childFragment) {
    }

    @CallSuper
    public void onAttach(Context context) {
        Context context2 = context;
        this.mCalled = true;
        Activity hostActivity = this.mHost == null ? null : this.mHost.getActivity();
        if (hostActivity != null) {
            this.mCalled = false;
            onAttach(hostActivity);
        }
    }

    @Deprecated
    @CallSuper
    public void onAttach(Activity activity) {
        Activity activity2 = activity;
        this.mCalled = true;
    }

    public Animation onCreateAnimation(int i, boolean z, int i2) {
        int i3 = i;
        boolean z2 = z;
        int i4 = i2;
        return null;
    }

    public Animator onCreateAnimator(int i, boolean z, int i2) {
        int i3 = i;
        boolean z2 = z;
        int i4 = i2;
        return null;
    }

    @CallSuper
    public void onCreate(@Nullable Bundle savedInstanceState) {
        this.mCalled = true;
        restoreChildFragmentState(savedInstanceState);
        if (this.mChildFragmentManager != null && !this.mChildFragmentManager.isStateAtLeast(1)) {
            this.mChildFragmentManager.dispatchCreate();
        }
    }

    /* access modifiers changed from: package-private */
    public void restoreChildFragmentState(@Nullable Bundle bundle) {
        Parcelable p;
        Bundle savedInstanceState = bundle;
        if (savedInstanceState != null && (p = savedInstanceState.getParcelable("android:support:fragments")) != null) {
            if (this.mChildFragmentManager == null) {
                instantiateChildFragmentManager();
            }
            this.mChildFragmentManager.restoreAllState(p, this.mChildNonConfig);
            this.mChildNonConfig = null;
            this.mChildFragmentManager.dispatchCreate();
        }
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        LayoutInflater layoutInflater2 = layoutInflater;
        ViewGroup viewGroup2 = viewGroup;
        Bundle bundle2 = bundle;
        return null;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    }

    @Nullable
    public View getView() {
        return this.mView;
    }

    @CallSuper
    public void onActivityCreated(@Nullable Bundle bundle) {
        Bundle bundle2 = bundle;
        this.mCalled = true;
    }

    @CallSuper
    public void onViewStateRestored(@Nullable Bundle bundle) {
        Bundle bundle2 = bundle;
        this.mCalled = true;
    }

    @CallSuper
    public void onStart() {
        this.mCalled = true;
    }

    @CallSuper
    public void onResume() {
        this.mCalled = true;
    }

    public void onSaveInstanceState(@NonNull Bundle outState) {
    }

    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
    }

    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
    }

    @CallSuper
    public void onConfigurationChanged(Configuration configuration) {
        Configuration configuration2 = configuration;
        this.mCalled = true;
    }

    @CallSuper
    public void onPause() {
        this.mCalled = true;
    }

    @CallSuper
    public void onStop() {
        this.mCalled = true;
    }

    @CallSuper
    public void onLowMemory() {
        this.mCalled = true;
    }

    @CallSuper
    public void onDestroyView() {
        this.mCalled = true;
    }

    @CallSuper
    public void onDestroy() {
        this.mCalled = true;
        FragmentActivity activity = getActivity();
        boolean isChangingConfigurations = activity != null && activity.isChangingConfigurations();
        if (this.mViewModelStore != null && !isChangingConfigurations) {
            this.mViewModelStore.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public void initState() {
        this.mIndex = -1;
        this.mWho = null;
        this.mAdded = false;
        this.mRemoving = false;
        this.mFromLayout = false;
        this.mInLayout = false;
        this.mRestored = false;
        this.mBackStackNesting = 0;
        this.mFragmentManager = null;
        this.mChildFragmentManager = null;
        this.mHost = null;
        this.mFragmentId = 0;
        this.mContainerId = 0;
        this.mTag = null;
        this.mHidden = false;
        this.mDetached = false;
        this.mRetaining = false;
    }

    @CallSuper
    public void onDetach() {
        this.mCalled = true;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    }

    public void onPrepareOptionsMenu(Menu menu) {
    }

    public void onDestroyOptionsMenu() {
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        MenuItem menuItem2 = menuItem;
        return false;
    }

    public void onOptionsMenuClosed(Menu menu) {
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().onCreateContextMenu(menu, v, menuInfo);
    }

    public void registerForContextMenu(View view) {
        view.setOnCreateContextMenuListener(this);
    }

    public void unregisterForContextMenu(View view) {
        view.setOnCreateContextMenuListener((View.OnCreateContextMenuListener) null);
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        MenuItem menuItem2 = menuItem;
        return false;
    }

    public void setEnterSharedElementCallback(SharedElementCallback callback) {
        SharedElementCallback sharedElementCallback = callback;
        ensureAnimationInfo().mEnterTransitionCallback = sharedElementCallback;
    }

    public void setExitSharedElementCallback(SharedElementCallback callback) {
        SharedElementCallback sharedElementCallback = callback;
        ensureAnimationInfo().mExitTransitionCallback = sharedElementCallback;
    }

    public void setEnterTransition(@Nullable Object transition) {
        Object obj = transition;
        ensureAnimationInfo().mEnterTransition = obj;
    }

    @Nullable
    public Object getEnterTransition() {
        if (this.mAnimationInfo == null) {
            return null;
        }
        return this.mAnimationInfo.mEnterTransition;
    }

    public void setReturnTransition(@Nullable Object transition) {
        Object obj = transition;
        ensureAnimationInfo().mReturnTransition = obj;
    }

    @Nullable
    public Object getReturnTransition() {
        if (this.mAnimationInfo == null) {
            return null;
        }
        return this.mAnimationInfo.mReturnTransition == USE_DEFAULT_TRANSITION ? getEnterTransition() : this.mAnimationInfo.mReturnTransition;
    }

    public void setExitTransition(@Nullable Object transition) {
        Object obj = transition;
        ensureAnimationInfo().mExitTransition = obj;
    }

    @Nullable
    public Object getExitTransition() {
        if (this.mAnimationInfo == null) {
            return null;
        }
        return this.mAnimationInfo.mExitTransition;
    }

    public void setReenterTransition(@Nullable Object transition) {
        Object obj = transition;
        ensureAnimationInfo().mReenterTransition = obj;
    }

    public Object getReenterTransition() {
        if (this.mAnimationInfo == null) {
            return null;
        }
        return this.mAnimationInfo.mReenterTransition == USE_DEFAULT_TRANSITION ? getExitTransition() : this.mAnimationInfo.mReenterTransition;
    }

    public void setSharedElementEnterTransition(@Nullable Object transition) {
        Object obj = transition;
        ensureAnimationInfo().mSharedElementEnterTransition = obj;
    }

    @Nullable
    public Object getSharedElementEnterTransition() {
        if (this.mAnimationInfo == null) {
            return null;
        }
        return this.mAnimationInfo.mSharedElementEnterTransition;
    }

    public void setSharedElementReturnTransition(@Nullable Object transition) {
        Object obj = transition;
        ensureAnimationInfo().mSharedElementReturnTransition = obj;
    }

    @Nullable
    public Object getSharedElementReturnTransition() {
        if (this.mAnimationInfo == null) {
            return null;
        }
        return this.mAnimationInfo.mSharedElementReturnTransition == USE_DEFAULT_TRANSITION ? getSharedElementEnterTransition() : this.mAnimationInfo.mSharedElementReturnTransition;
    }

    public void setAllowEnterTransitionOverlap(boolean allow) {
        ensureAnimationInfo().mAllowEnterTransitionOverlap = Boolean.valueOf(allow);
    }

    public boolean getAllowEnterTransitionOverlap() {
        boolean z;
        if (this.mAnimationInfo == null || this.mAnimationInfo.mAllowEnterTransitionOverlap == null) {
            z = true;
        } else {
            z = this.mAnimationInfo.mAllowEnterTransitionOverlap.booleanValue();
        }
        return z;
    }

    public void setAllowReturnTransitionOverlap(boolean allow) {
        ensureAnimationInfo().mAllowReturnTransitionOverlap = Boolean.valueOf(allow);
    }

    public boolean getAllowReturnTransitionOverlap() {
        boolean z;
        if (this.mAnimationInfo == null || this.mAnimationInfo.mAllowReturnTransitionOverlap == null) {
            z = true;
        } else {
            z = this.mAnimationInfo.mAllowReturnTransitionOverlap.booleanValue();
        }
        return z;
    }

    public void postponeEnterTransition() {
        ensureAnimationInfo().mEnterTransitionPostponed = true;
    }

    public void startPostponedEnterTransition() {
        Runnable runnable;
        if (this.mFragmentManager == null || this.mFragmentManager.mHost == null) {
            ensureAnimationInfo().mEnterTransitionPostponed = false;
        } else if (Looper.myLooper() != this.mFragmentManager.mHost.getHandler().getLooper()) {
            new Runnable(this) {
                final /* synthetic */ Fragment this$0;

                {
                    this.this$0 = this$0;
                }

                public void run() {
                    this.this$0.callStartTransitionListener();
                }
            };
            boolean postAtFrontOfQueue = this.mFragmentManager.mHost.getHandler().postAtFrontOfQueue(runnable);
        } else {
            callStartTransitionListener();
        }
    }

    /* access modifiers changed from: package-private */
    public void callStartTransitionListener() {
        OnStartEnterTransitionListener listener;
        if (this.mAnimationInfo == null) {
            listener = null;
        } else {
            this.mAnimationInfo.mEnterTransitionPostponed = false;
            listener = this.mAnimationInfo.mStartEnterTransitionListener;
            this.mAnimationInfo.mStartEnterTransitionListener = null;
        }
        if (listener != null) {
            listener.onStartEnterTransition();
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        StringBuilder sb;
        StringBuilder sb2;
        String prefix = str;
        FileDescriptor fd = fileDescriptor;
        PrintWriter writer = printWriter;
        String[] args = strArr;
        writer.print(prefix);
        writer.print("mFragmentId=#");
        writer.print(Integer.toHexString(this.mFragmentId));
        writer.print(" mContainerId=#");
        writer.print(Integer.toHexString(this.mContainerId));
        writer.print(" mTag=");
        writer.println(this.mTag);
        writer.print(prefix);
        writer.print("mState=");
        writer.print(this.mState);
        writer.print(" mIndex=");
        writer.print(this.mIndex);
        writer.print(" mWho=");
        writer.print(this.mWho);
        writer.print(" mBackStackNesting=");
        writer.println(this.mBackStackNesting);
        writer.print(prefix);
        writer.print("mAdded=");
        writer.print(this.mAdded);
        writer.print(" mRemoving=");
        writer.print(this.mRemoving);
        writer.print(" mFromLayout=");
        writer.print(this.mFromLayout);
        writer.print(" mInLayout=");
        writer.println(this.mInLayout);
        writer.print(prefix);
        writer.print("mHidden=");
        writer.print(this.mHidden);
        writer.print(" mDetached=");
        writer.print(this.mDetached);
        writer.print(" mMenuVisible=");
        writer.print(this.mMenuVisible);
        writer.print(" mHasMenu=");
        writer.println(this.mHasMenu);
        writer.print(prefix);
        writer.print("mRetainInstance=");
        writer.print(this.mRetainInstance);
        writer.print(" mRetaining=");
        writer.print(this.mRetaining);
        writer.print(" mUserVisibleHint=");
        writer.println(this.mUserVisibleHint);
        if (this.mFragmentManager != null) {
            writer.print(prefix);
            writer.print("mFragmentManager=");
            writer.println(this.mFragmentManager);
        }
        if (this.mHost != null) {
            writer.print(prefix);
            writer.print("mHost=");
            writer.println(this.mHost);
        }
        if (this.mParentFragment != null) {
            writer.print(prefix);
            writer.print("mParentFragment=");
            writer.println(this.mParentFragment);
        }
        if (this.mArguments != null) {
            writer.print(prefix);
            writer.print("mArguments=");
            writer.println(this.mArguments);
        }
        if (this.mSavedFragmentState != null) {
            writer.print(prefix);
            writer.print("mSavedFragmentState=");
            writer.println(this.mSavedFragmentState);
        }
        if (this.mSavedViewState != null) {
            writer.print(prefix);
            writer.print("mSavedViewState=");
            writer.println(this.mSavedViewState);
        }
        if (this.mTarget != null) {
            writer.print(prefix);
            writer.print("mTarget=");
            writer.print(this.mTarget);
            writer.print(" mTargetRequestCode=");
            writer.println(this.mTargetRequestCode);
        }
        if (getNextAnim() != 0) {
            writer.print(prefix);
            writer.print("mNextAnim=");
            writer.println(getNextAnim());
        }
        if (this.mContainer != null) {
            writer.print(prefix);
            writer.print("mContainer=");
            writer.println(this.mContainer);
        }
        if (this.mView != null) {
            writer.print(prefix);
            writer.print("mView=");
            writer.println(this.mView);
        }
        if (this.mInnerView != null) {
            writer.print(prefix);
            writer.print("mInnerView=");
            writer.println(this.mView);
        }
        if (getAnimatingAway() != null) {
            writer.print(prefix);
            writer.print("mAnimatingAway=");
            writer.println(getAnimatingAway());
            writer.print(prefix);
            writer.print("mStateAfterAnimating=");
            writer.println(getStateAfterAnimating());
        }
        if (getContext() != null) {
            LoaderManager.getInstance(this).dump(prefix, fd, writer, args);
        }
        if (this.mChildFragmentManager != null) {
            writer.print(prefix);
            new StringBuilder();
            writer.println(sb.append("Child ").append(this.mChildFragmentManager).append(":").toString());
            FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
            new StringBuilder();
            fragmentManagerImpl.dump(sb2.append(prefix).append("  ").toString(), fd, writer, args);
        }
    }

    /* access modifiers changed from: package-private */
    public Fragment findFragmentByWho(String str) {
        String who = str;
        if (who.equals(this.mWho)) {
            return this;
        } else if (this.mChildFragmentManager != null) {
            return this.mChildFragmentManager.findFragmentByWho(who);
        } else {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void instantiateChildFragmentManager() {
        FragmentManagerImpl fragmentManagerImpl;
        FragmentContainer fragmentContainer;
        Throwable th;
        if (this.mHost == null) {
            Throwable th2 = th;
            new IllegalStateException("Fragment has not been attached yet.");
            throw th2;
        }
        new FragmentManagerImpl();
        this.mChildFragmentManager = fragmentManagerImpl;
        new FragmentContainer(this) {
            final /* synthetic */ Fragment this$0;

            {
                this.this$0 = this$0;
            }

            @Nullable
            public View onFindViewById(int i) {
                Throwable th;
                int id = i;
                if (this.this$0.mView != null) {
                    return this.this$0.mView.findViewById(id);
                }
                Throwable th2 = th;
                new IllegalStateException("Fragment does not have a view");
                throw th2;
            }

            public boolean onHasView() {
                return this.this$0.mView != null;
            }

            public Fragment instantiate(Context context, String className, Bundle arguments) {
                return this.this$0.mHost.instantiate(context, className, arguments);
            }
        };
        this.mChildFragmentManager.attachController(this.mHost, fragmentContainer, this);
    }

    /* access modifiers changed from: package-private */
    public void performCreate(Bundle bundle) {
        Throwable th;
        StringBuilder sb;
        Bundle savedInstanceState = bundle;
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.noteStateNotSaved();
        }
        this.mState = 1;
        this.mCalled = false;
        onCreate(savedInstanceState);
        this.mIsCreated = true;
        if (!this.mCalled) {
            Throwable th2 = th;
            new StringBuilder();
            new SuperNotCalledException(sb.append("Fragment ").append(this).append(" did not call through to super.onCreate()").toString());
            throw th2;
        }
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
    }

    /* access modifiers changed from: package-private */
    public void performCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        LifecycleOwner lifecycleOwner;
        Throwable th;
        LayoutInflater inflater = layoutInflater;
        ViewGroup container = viewGroup;
        Bundle savedInstanceState = bundle;
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.noteStateNotSaved();
        }
        this.mPerformedCreateView = true;
        new LifecycleOwner(this) {
            final /* synthetic */ Fragment this$0;

            {
                this.this$0 = this$0;
            }

            public Lifecycle getLifecycle() {
                LifecycleRegistry lifecycleRegistry;
                if (this.this$0.mViewLifecycleRegistry == null) {
                    Fragment fragment = this.this$0;
                    new LifecycleRegistry(this.this$0.mViewLifecycleOwner);
                    fragment.mViewLifecycleRegistry = lifecycleRegistry;
                }
                return this.this$0.mViewLifecycleRegistry;
            }
        };
        this.mViewLifecycleOwner = lifecycleOwner;
        this.mViewLifecycleRegistry = null;
        this.mView = onCreateView(inflater, container, savedInstanceState);
        if (this.mView != null) {
            Lifecycle lifecycle = this.mViewLifecycleOwner.getLifecycle();
            this.mViewLifecycleOwnerLiveData.setValue(this.mViewLifecycleOwner);
        } else if (this.mViewLifecycleRegistry != null) {
            Throwable th2 = th;
            new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
            throw th2;
        } else {
            this.mViewLifecycleOwner = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void performActivityCreated(Bundle bundle) {
        Throwable th;
        StringBuilder sb;
        Bundle savedInstanceState = bundle;
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.noteStateNotSaved();
        }
        this.mState = 2;
        this.mCalled = false;
        onActivityCreated(savedInstanceState);
        if (!this.mCalled) {
            Throwable th2 = th;
            new StringBuilder();
            new SuperNotCalledException(sb.append("Fragment ").append(this).append(" did not call through to super.onActivityCreated()").toString());
            throw th2;
        } else if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchActivityCreated();
        }
    }

    /* access modifiers changed from: package-private */
    public void performStart() {
        Throwable th;
        StringBuilder sb;
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.noteStateNotSaved();
            boolean execPendingActions = this.mChildFragmentManager.execPendingActions();
        }
        this.mState = 3;
        this.mCalled = false;
        onStart();
        if (!this.mCalled) {
            Throwable th2 = th;
            new StringBuilder();
            new SuperNotCalledException(sb.append("Fragment ").append(this).append(" did not call through to super.onStart()").toString());
            throw th2;
        }
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchStart();
        }
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
        if (this.mView != null) {
            this.mViewLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
        }
    }

    /* access modifiers changed from: package-private */
    public void performResume() {
        Throwable th;
        StringBuilder sb;
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.noteStateNotSaved();
            boolean execPendingActions = this.mChildFragmentManager.execPendingActions();
        }
        this.mState = 4;
        this.mCalled = false;
        onResume();
        if (!this.mCalled) {
            Throwable th2 = th;
            new StringBuilder();
            new SuperNotCalledException(sb.append("Fragment ").append(this).append(" did not call through to super.onResume()").toString());
            throw th2;
        }
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchResume();
            boolean execPendingActions2 = this.mChildFragmentManager.execPendingActions();
        }
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        if (this.mView != null) {
            this.mViewLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        }
    }

    /* access modifiers changed from: package-private */
    public void noteStateNotSaved() {
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.noteStateNotSaved();
        }
    }

    /* access modifiers changed from: package-private */
    public void performMultiWindowModeChanged(boolean z) {
        boolean isInMultiWindowMode = z;
        onMultiWindowModeChanged(isInMultiWindowMode);
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchMultiWindowModeChanged(isInMultiWindowMode);
        }
    }

    /* access modifiers changed from: package-private */
    public void performPictureInPictureModeChanged(boolean z) {
        boolean isInPictureInPictureMode = z;
        onPictureInPictureModeChanged(isInPictureInPictureMode);
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchPictureInPictureModeChanged(isInPictureInPictureMode);
        }
    }

    /* access modifiers changed from: package-private */
    public void performConfigurationChanged(Configuration configuration) {
        Configuration newConfig = configuration;
        onConfigurationChanged(newConfig);
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchConfigurationChanged(newConfig);
        }
    }

    /* access modifiers changed from: package-private */
    public void performLowMemory() {
        onLowMemory();
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchLowMemory();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean performCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Menu menu2 = menu;
        MenuInflater inflater = menuInflater;
        boolean show = false;
        if (!this.mHidden) {
            if (this.mHasMenu && this.mMenuVisible) {
                show = true;
                onCreateOptionsMenu(menu2, inflater);
            }
            if (this.mChildFragmentManager != null) {
                show |= this.mChildFragmentManager.dispatchCreateOptionsMenu(menu2, inflater);
            }
        }
        return show;
    }

    /* access modifiers changed from: package-private */
    public boolean performPrepareOptionsMenu(Menu menu) {
        Menu menu2 = menu;
        boolean show = false;
        if (!this.mHidden) {
            if (this.mHasMenu && this.mMenuVisible) {
                show = true;
                onPrepareOptionsMenu(menu2);
            }
            if (this.mChildFragmentManager != null) {
                show |= this.mChildFragmentManager.dispatchPrepareOptionsMenu(menu2);
            }
        }
        return show;
    }

    /* access modifiers changed from: package-private */
    public boolean performOptionsItemSelected(MenuItem menuItem) {
        MenuItem item = menuItem;
        if (!this.mHidden) {
            if (this.mHasMenu && this.mMenuVisible && onOptionsItemSelected(item)) {
                return true;
            }
            if (this.mChildFragmentManager != null && this.mChildFragmentManager.dispatchOptionsItemSelected(item)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean performContextItemSelected(MenuItem menuItem) {
        MenuItem item = menuItem;
        if (!this.mHidden) {
            if (onContextItemSelected(item)) {
                return true;
            }
            if (this.mChildFragmentManager != null && this.mChildFragmentManager.dispatchContextItemSelected(item)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void performOptionsMenuClosed(Menu menu) {
        Menu menu2 = menu;
        if (!this.mHidden) {
            if (this.mHasMenu && this.mMenuVisible) {
                onOptionsMenuClosed(menu2);
            }
            if (this.mChildFragmentManager != null) {
                this.mChildFragmentManager.dispatchOptionsMenuClosed(menu2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void performSaveInstanceState(Bundle bundle) {
        Parcelable p;
        Bundle outState = bundle;
        onSaveInstanceState(outState);
        if (this.mChildFragmentManager != null && (p = this.mChildFragmentManager.saveAllState()) != null) {
            outState.putParcelable("android:support:fragments", p);
        }
    }

    /* access modifiers changed from: package-private */
    public void performPause() {
        Throwable th;
        StringBuilder sb;
        if (this.mView != null) {
            this.mViewLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        }
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchPause();
        }
        this.mState = 3;
        this.mCalled = false;
        onPause();
        if (!this.mCalled) {
            Throwable th2 = th;
            new StringBuilder();
            new SuperNotCalledException(sb.append("Fragment ").append(this).append(" did not call through to super.onPause()").toString());
            throw th2;
        }
    }

    /* access modifiers changed from: package-private */
    public void performStop() {
        Throwable th;
        StringBuilder sb;
        if (this.mView != null) {
            this.mViewLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        }
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchStop();
        }
        this.mState = 2;
        this.mCalled = false;
        onStop();
        if (!this.mCalled) {
            Throwable th2 = th;
            new StringBuilder();
            new SuperNotCalledException(sb.append("Fragment ").append(this).append(" did not call through to super.onStop()").toString());
            throw th2;
        }
    }

    /* access modifiers changed from: package-private */
    public void performDestroyView() {
        Throwable th;
        StringBuilder sb;
        if (this.mView != null) {
            this.mViewLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
        }
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchDestroyView();
        }
        this.mState = 1;
        this.mCalled = false;
        onDestroyView();
        if (!this.mCalled) {
            Throwable th2 = th;
            new StringBuilder();
            new SuperNotCalledException(sb.append("Fragment ").append(this).append(" did not call through to super.onDestroyView()").toString());
            throw th2;
        }
        LoaderManager.getInstance(this).markForRedelivery();
        this.mPerformedCreateView = false;
    }

    /* access modifiers changed from: package-private */
    public void performDestroy() {
        Throwable th;
        StringBuilder sb;
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchDestroy();
        }
        this.mState = 0;
        this.mCalled = false;
        this.mIsCreated = false;
        onDestroy();
        if (!this.mCalled) {
            Throwable th2 = th;
            new StringBuilder();
            new SuperNotCalledException(sb.append("Fragment ").append(this).append(" did not call through to super.onDestroy()").toString());
            throw th2;
        }
        this.mChildFragmentManager = null;
    }

    /* access modifiers changed from: package-private */
    public void performDetach() {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        this.mCalled = false;
        onDetach();
        this.mLayoutInflater = null;
        if (!this.mCalled) {
            Throwable th3 = th2;
            new StringBuilder();
            new SuperNotCalledException(sb2.append("Fragment ").append(this).append(" did not call through to super.onDetach()").toString());
            throw th3;
        } else if (this.mChildFragmentManager == null) {
        } else {
            if (!this.mRetaining) {
                Throwable th4 = th;
                new StringBuilder();
                new IllegalStateException(sb.append("Child FragmentManager of ").append(this).append(" was not ").append(" destroyed and this fragment is not retaining instance").toString());
                throw th4;
            }
            this.mChildFragmentManager.dispatchDestroy();
            this.mChildFragmentManager = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void setOnStartEnterTransitionListener(OnStartEnterTransitionListener onStartEnterTransitionListener) {
        Throwable th;
        StringBuilder sb;
        OnStartEnterTransitionListener listener = onStartEnterTransitionListener;
        AnimationInfo ensureAnimationInfo = ensureAnimationInfo();
        if (listener != this.mAnimationInfo.mStartEnterTransitionListener) {
            if (listener == null || this.mAnimationInfo.mStartEnterTransitionListener == null) {
                if (this.mAnimationInfo.mEnterTransitionPostponed) {
                    this.mAnimationInfo.mStartEnterTransitionListener = listener;
                }
                if (listener != null) {
                    listener.startListening();
                    return;
                }
                return;
            }
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Trying to set a replacement startPostponedEnterTransition on ").append(this).toString());
            throw th2;
        }
    }

    private AnimationInfo ensureAnimationInfo() {
        AnimationInfo animationInfo;
        if (this.mAnimationInfo == null) {
            new AnimationInfo();
            this.mAnimationInfo = animationInfo;
        }
        return this.mAnimationInfo;
    }

    /* access modifiers changed from: package-private */
    public int getNextAnim() {
        if (this.mAnimationInfo == null) {
            return 0;
        }
        return this.mAnimationInfo.mNextAnim;
    }

    /* access modifiers changed from: package-private */
    public void setNextAnim(int i) {
        int animResourceId = i;
        if (this.mAnimationInfo != null || animResourceId != 0) {
            ensureAnimationInfo().mNextAnim = animResourceId;
        }
    }

    /* access modifiers changed from: package-private */
    public int getNextTransition() {
        if (this.mAnimationInfo == null) {
            return 0;
        }
        return this.mAnimationInfo.mNextTransition;
    }

    /* access modifiers changed from: package-private */
    public void setNextTransition(int i, int i2) {
        int nextTransition = i;
        int nextTransitionStyle = i2;
        if (this.mAnimationInfo != null || nextTransition != 0 || nextTransitionStyle != 0) {
            AnimationInfo ensureAnimationInfo = ensureAnimationInfo();
            this.mAnimationInfo.mNextTransition = nextTransition;
            this.mAnimationInfo.mNextTransitionStyle = nextTransitionStyle;
        }
    }

    /* access modifiers changed from: package-private */
    public int getNextTransitionStyle() {
        if (this.mAnimationInfo == null) {
            return 0;
        }
        return this.mAnimationInfo.mNextTransitionStyle;
    }

    /* access modifiers changed from: package-private */
    public SharedElementCallback getEnterTransitionCallback() {
        if (this.mAnimationInfo == null) {
            return null;
        }
        return this.mAnimationInfo.mEnterTransitionCallback;
    }

    /* access modifiers changed from: package-private */
    public SharedElementCallback getExitTransitionCallback() {
        if (this.mAnimationInfo == null) {
            return null;
        }
        return this.mAnimationInfo.mExitTransitionCallback;
    }

    /* access modifiers changed from: package-private */
    public View getAnimatingAway() {
        if (this.mAnimationInfo == null) {
            return null;
        }
        return this.mAnimationInfo.mAnimatingAway;
    }

    /* access modifiers changed from: package-private */
    public void setAnimatingAway(View view) {
        View view2 = view;
        ensureAnimationInfo().mAnimatingAway = view2;
    }

    /* access modifiers changed from: package-private */
    public void setAnimator(Animator animator) {
        Animator animator2 = animator;
        ensureAnimationInfo().mAnimator = animator2;
    }

    /* access modifiers changed from: package-private */
    public Animator getAnimator() {
        if (this.mAnimationInfo == null) {
            return null;
        }
        return this.mAnimationInfo.mAnimator;
    }

    /* access modifiers changed from: package-private */
    public int getStateAfterAnimating() {
        if (this.mAnimationInfo == null) {
            return 0;
        }
        return this.mAnimationInfo.mStateAfterAnimating;
    }

    /* access modifiers changed from: package-private */
    public void setStateAfterAnimating(int state) {
        int i = state;
        ensureAnimationInfo().mStateAfterAnimating = i;
    }

    /* access modifiers changed from: package-private */
    public boolean isPostponed() {
        if (this.mAnimationInfo == null) {
            return false;
        }
        return this.mAnimationInfo.mEnterTransitionPostponed;
    }

    /* access modifiers changed from: package-private */
    public boolean isHideReplaced() {
        if (this.mAnimationInfo == null) {
            return false;
        }
        return this.mAnimationInfo.mIsHideReplaced;
    }

    /* access modifiers changed from: package-private */
    public void setHideReplaced(boolean replaced) {
        boolean z = replaced;
        ensureAnimationInfo().mIsHideReplaced = z;
    }

    /* renamed from: android.support.v4.app.Fragment$AnimationInfo */
    static class AnimationInfo {
        Boolean mAllowEnterTransitionOverlap;
        Boolean mAllowReturnTransitionOverlap;
        View mAnimatingAway;
        Animator mAnimator;
        Object mEnterTransition = null;
        SharedElementCallback mEnterTransitionCallback = null;
        boolean mEnterTransitionPostponed;
        Object mExitTransition = null;
        SharedElementCallback mExitTransitionCallback = null;
        boolean mIsHideReplaced;
        int mNextAnim;
        int mNextTransition;
        int mNextTransitionStyle;
        Object mReenterTransition = Fragment.USE_DEFAULT_TRANSITION;
        Object mReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
        Object mSharedElementEnterTransition = null;
        Object mSharedElementReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
        OnStartEnterTransitionListener mStartEnterTransitionListener;
        int mStateAfterAnimating;

        AnimationInfo() {
        }
    }
}
