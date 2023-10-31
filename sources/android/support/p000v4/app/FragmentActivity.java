package android.support.p000v4.app;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModelStore;
import android.arch.lifecycle.ViewModelStoreOwner;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.app.ActivityCompat;
import android.support.p000v4.internal.view.SupportMenu;
import android.support.p000v4.util.C1651SparseArrayCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* renamed from: android.support.v4.app.FragmentActivity */
public class FragmentActivity extends SupportActivity implements ViewModelStoreOwner, ActivityCompat.OnRequestPermissionsResultCallback, ActivityCompat.RequestPermissionsRequestCodeValidator {
    static final String ALLOCATED_REQUEST_INDICIES_TAG = "android:support:request_indicies";
    static final String FRAGMENTS_TAG = "android:support:fragments";
    static final int MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS = 65534;
    static final int MSG_RESUME_PENDING = 2;
    static final String NEXT_CANDIDATE_REQUEST_INDEX_TAG = "android:support:next_request_index";
    static final String REQUEST_FRAGMENT_WHO_TAG = "android:support:request_fragment_who";
    private static final String TAG = "FragmentActivity";
    boolean mCreated;
    final FragmentController mFragments;
    final Handler mHandler;
    int mNextCandidateRequestIndex;
    C1651SparseArrayCompat<String> mPendingFragmentActivityResults;
    boolean mRequestedPermissionsFromFragment;
    boolean mResumed;
    boolean mStartedActivityFromFragment;
    boolean mStartedIntentSenderFromFragment;
    boolean mStopped = true;
    private ViewModelStore mViewModelStore;

    public FragmentActivity() {
        Handler handler;
        FragmentHostCallback fragmentHostCallback;
        new Handler(this) {
            final /* synthetic */ FragmentActivity this$0;

            {
                this.this$0 = this$0;
            }

            public void handleMessage(Message message) {
                Message msg = message;
                switch (msg.what) {
                    case 2:
                        this.this$0.onResumeFragments();
                        boolean execPendingActions = this.this$0.mFragments.execPendingActions();
                        return;
                    default:
                        super.handleMessage(msg);
                        return;
                }
            }
        };
        this.mHandler = handler;
        new HostCallbacks(this);
        this.mFragments = FragmentController.createController(fragmentHostCallback);
    }

    /* renamed from: android.support.v4.app.FragmentActivity$NonConfigurationInstances */
    static final class NonConfigurationInstances {
        Object custom;
        FragmentManagerNonConfig fragments;
        ViewModelStore viewModelStore;

        NonConfigurationInstances() {
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        StringBuilder sb;
        int requestCode = i;
        int resultCode = i2;
        Intent data = intent;
        this.mFragments.noteStateNotSaved();
        int requestIndex = requestCode >> 16;
        if (requestIndex != 0) {
            int requestIndex2 = requestIndex - 1;
            String who = this.mPendingFragmentActivityResults.get(requestIndex2);
            this.mPendingFragmentActivityResults.remove(requestIndex2);
            if (who == null) {
                int w = Log.w(TAG, "Activity result delivered for unknown Fragment.");
                return;
            }
            Fragment targetFragment = this.mFragments.findFragmentByWho(who);
            if (targetFragment == null) {
                new StringBuilder();
                int w2 = Log.w(TAG, sb.append("Activity result no fragment exists for who: ").append(who).toString());
                return;
            }
            targetFragment.onActivityResult(requestCode & 65535, resultCode, data);
            return;
        }
        ActivityCompat.PermissionCompatDelegate delegate = ActivityCompat.getPermissionCompatDelegate();
        if (delegate == null || !delegate.onActivityResult(this, requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onBackPressed() {
        FragmentManager fragmentManager = this.mFragments.getSupportFragmentManager();
        boolean isStateSaved = fragmentManager.isStateSaved();
        if (isStateSaved && Build.VERSION.SDK_INT <= 25) {
            return;
        }
        if (isStateSaved || !fragmentManager.popBackStackImmediate()) {
            super.onBackPressed();
        }
    }

    public void supportFinishAfterTransition() {
        ActivityCompat.finishAfterTransition(this);
    }

    public void setEnterSharedElementCallback(SharedElementCallback callback) {
        ActivityCompat.setEnterSharedElementCallback(this, callback);
    }

    public void setExitSharedElementCallback(SharedElementCallback listener) {
        ActivityCompat.setExitSharedElementCallback(this, listener);
    }

    public void supportPostponeEnterTransition() {
        ActivityCompat.postponeEnterTransition(this);
    }

    public void supportStartPostponedEnterTransition() {
        ActivityCompat.startPostponedEnterTransition(this);
    }

    @CallSuper
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        this.mFragments.dispatchMultiWindowModeChanged(isInMultiWindowMode);
    }

    @CallSuper
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        this.mFragments.dispatchPictureInPictureModeChanged(isInPictureInPictureMode);
    }

    public void onConfigurationChanged(Configuration configuration) {
        Configuration newConfig = configuration;
        super.onConfigurationChanged(newConfig);
        this.mFragments.noteStateNotSaved();
        this.mFragments.dispatchConfigurationChanged(newConfig);
    }

    @NonNull
    public ViewModelStore getViewModelStore() {
        ViewModelStore viewModelStore;
        Throwable th;
        if (getApplication() == null) {
            Throwable th2 = th;
            new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
            throw th2;
        }
        if (this.mViewModelStore == null) {
            NonConfigurationInstances nc = (NonConfigurationInstances) getLastNonConfigurationInstance();
            if (nc != null) {
                this.mViewModelStore = nc.viewModelStore;
            }
            if (this.mViewModelStore == null) {
                new ViewModelStore();
                this.mViewModelStore = viewModelStore;
            }
        }
        return this.mViewModelStore;
    }

    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        C1651SparseArrayCompat<String> sparseArrayCompat;
        C1651SparseArrayCompat<String> sparseArrayCompat2;
        Bundle savedInstanceState = bundle;
        this.mFragments.attachHost((Fragment) null);
        super.onCreate(savedInstanceState);
        NonConfigurationInstances nc = (NonConfigurationInstances) getLastNonConfigurationInstance();
        if (!(nc == null || nc.viewModelStore == null || this.mViewModelStore != null)) {
            this.mViewModelStore = nc.viewModelStore;
        }
        if (savedInstanceState != null) {
            this.mFragments.restoreAllState(savedInstanceState.getParcelable(FRAGMENTS_TAG), nc != null ? nc.fragments : null);
            if (savedInstanceState.containsKey(NEXT_CANDIDATE_REQUEST_INDEX_TAG)) {
                this.mNextCandidateRequestIndex = savedInstanceState.getInt(NEXT_CANDIDATE_REQUEST_INDEX_TAG);
                int[] requestCodes = savedInstanceState.getIntArray(ALLOCATED_REQUEST_INDICIES_TAG);
                String[] fragmentWhos = savedInstanceState.getStringArray(REQUEST_FRAGMENT_WHO_TAG);
                if (requestCodes == null || fragmentWhos == null || requestCodes.length != fragmentWhos.length) {
                    int w = Log.w(TAG, "Invalid requestCode mapping in savedInstanceState.");
                } else {
                    new C1651SparseArrayCompat<>(requestCodes.length);
                    this.mPendingFragmentActivityResults = sparseArrayCompat2;
                    for (int i = 0; i < requestCodes.length; i++) {
                        this.mPendingFragmentActivityResults.put(requestCodes[i], fragmentWhos[i]);
                    }
                }
            }
        }
        if (this.mPendingFragmentActivityResults == null) {
            new C1651SparseArrayCompat<>();
            this.mPendingFragmentActivityResults = sparseArrayCompat;
            this.mNextCandidateRequestIndex = 0;
        }
        this.mFragments.dispatchCreate();
    }

    public boolean onCreatePanelMenu(int i, Menu menu) {
        int featureId = i;
        Menu menu2 = menu;
        if (featureId == 0) {
            return super.onCreatePanelMenu(featureId, menu2) | this.mFragments.dispatchCreateOptionsMenu(menu2, getMenuInflater());
        }
        return super.onCreatePanelMenu(featureId, menu2);
    }

    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View parent = view;
        String name = str;
        Context context2 = context;
        AttributeSet attrs = attributeSet;
        View v = dispatchFragmentsOnCreateView(parent, name, context2, attrs);
        if (v == null) {
            return super.onCreateView(parent, name, context2, attrs);
        }
        return v;
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        String name = str;
        Context context2 = context;
        AttributeSet attrs = attributeSet;
        View v = dispatchFragmentsOnCreateView((View) null, name, context2, attrs);
        if (v == null) {
            return super.onCreateView(name, context2, attrs);
        }
        return v;
    }

    /* access modifiers changed from: package-private */
    public final View dispatchFragmentsOnCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return this.mFragments.onCreateView(parent, name, context, attrs);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.mViewModelStore != null && !isChangingConfigurations()) {
            this.mViewModelStore.clear();
        }
        this.mFragments.dispatchDestroy();
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.mFragments.dispatchLowMemory();
    }

    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        int featureId = i;
        MenuItem item = menuItem;
        if (super.onMenuItemSelected(featureId, item)) {
            return true;
        }
        switch (featureId) {
            case 0:
                return this.mFragments.dispatchOptionsItemSelected(item);
            case 6:
                return this.mFragments.dispatchContextItemSelected(item);
            default:
                return false;
        }
    }

    public void onPanelClosed(int i, Menu menu) {
        int featureId = i;
        Menu menu2 = menu;
        switch (featureId) {
            case 0:
                this.mFragments.dispatchOptionsMenuClosed(menu2);
                break;
        }
        super.onPanelClosed(featureId, menu2);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mResumed = false;
        if (this.mHandler.hasMessages(2)) {
            this.mHandler.removeMessages(2);
            onResumeFragments();
        }
        this.mFragments.dispatchPause();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mFragments.noteStateNotSaved();
    }

    public void onStateNotSaved() {
        this.mFragments.noteStateNotSaved();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        boolean sendEmptyMessage = this.mHandler.sendEmptyMessage(2);
        this.mResumed = true;
        boolean execPendingActions = this.mFragments.execPendingActions();
    }

    /* access modifiers changed from: protected */
    public void onPostResume() {
        super.onPostResume();
        this.mHandler.removeMessages(2);
        onResumeFragments();
        boolean execPendingActions = this.mFragments.execPendingActions();
    }

    /* access modifiers changed from: protected */
    public void onResumeFragments() {
        this.mFragments.dispatchResume();
    }

    public boolean onPreparePanel(int i, View view, Menu menu) {
        int featureId = i;
        View view2 = view;
        Menu menu2 = menu;
        if (featureId != 0 || menu2 == null) {
            return super.onPreparePanel(featureId, view2, menu2);
        }
        return onPrepareOptionsPanel(view2, menu2) | this.mFragments.dispatchPrepareOptionsMenu(menu2);
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean onPrepareOptionsPanel(View view, Menu menu) {
        return super.onPreparePanel(0, view, menu);
    }

    public final Object onRetainNonConfigurationInstance() {
        NonConfigurationInstances nonConfigurationInstances;
        Object custom = onRetainCustomNonConfigurationInstance();
        FragmentManagerNonConfig fragments = this.mFragments.retainNestedNonConfig();
        if (fragments == null && this.mViewModelStore == null && custom == null) {
            return null;
        }
        new NonConfigurationInstances();
        NonConfigurationInstances nci = nonConfigurationInstances;
        nci.custom = custom;
        nci.viewModelStore = this.mViewModelStore;
        nci.fragments = fragments;
        return nci;
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        Bundle outState = bundle;
        super.onSaveInstanceState(outState);
        markFragmentsCreated();
        Parcelable p = this.mFragments.saveAllState();
        if (p != null) {
            outState.putParcelable(FRAGMENTS_TAG, p);
        }
        if (this.mPendingFragmentActivityResults.size() > 0) {
            outState.putInt(NEXT_CANDIDATE_REQUEST_INDEX_TAG, this.mNextCandidateRequestIndex);
            int[] requestCodes = new int[this.mPendingFragmentActivityResults.size()];
            String[] fragmentWhos = new String[this.mPendingFragmentActivityResults.size()];
            for (int i = 0; i < this.mPendingFragmentActivityResults.size(); i++) {
                requestCodes[i] = this.mPendingFragmentActivityResults.keyAt(i);
                fragmentWhos[i] = this.mPendingFragmentActivityResults.valueAt(i);
            }
            outState.putIntArray(ALLOCATED_REQUEST_INDICIES_TAG, requestCodes);
            outState.putStringArray(REQUEST_FRAGMENT_WHO_TAG, fragmentWhos);
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.mStopped = false;
        if (!this.mCreated) {
            this.mCreated = true;
            this.mFragments.dispatchActivityCreated();
        }
        this.mFragments.noteStateNotSaved();
        boolean execPendingActions = this.mFragments.execPendingActions();
        this.mFragments.dispatchStart();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.mStopped = true;
        markFragmentsCreated();
        this.mFragments.dispatchStop();
    }

    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    public Object getLastCustomNonConfigurationInstance() {
        NonConfigurationInstances nc = (NonConfigurationInstances) getLastNonConfigurationInstance();
        return nc != null ? nc.custom : null;
    }

    @Deprecated
    public void supportInvalidateOptionsMenu() {
        invalidateOptionsMenu();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        StringBuilder sb;
        String prefix = str;
        FileDescriptor fd = fileDescriptor;
        PrintWriter writer = printWriter;
        String[] args = strArr;
        super.dump(prefix, fd, writer, args);
        writer.print(prefix);
        writer.print("Local FragmentActivity ");
        writer.print(Integer.toHexString(System.identityHashCode(this)));
        writer.println(" State:");
        new StringBuilder();
        String innerPrefix = sb.append(prefix).append("  ").toString();
        writer.print(innerPrefix);
        writer.print("mCreated=");
        writer.print(this.mCreated);
        writer.print(" mResumed=");
        writer.print(this.mResumed);
        writer.print(" mStopped=");
        writer.print(this.mStopped);
        if (getApplication() != null) {
            LoaderManager.getInstance(this).dump(innerPrefix, fd, writer, args);
        }
        this.mFragments.getSupportFragmentManager().dump(prefix, fd, writer, args);
    }

    public void onAttachFragment(Fragment fragment) {
    }

    public FragmentManager getSupportFragmentManager() {
        return this.mFragments.getSupportFragmentManager();
    }

    @Deprecated
    public LoaderManager getSupportLoaderManager() {
        return LoaderManager.getInstance(this);
    }

    public void startActivityForResult(Intent intent, int i) {
        Intent intent2 = intent;
        int requestCode = i;
        if (!this.mStartedActivityFromFragment && requestCode != -1) {
            checkForValidRequestCode(requestCode);
        }
        super.startActivityForResult(intent2, requestCode);
    }

    public void startActivityForResult(Intent intent, int i, @Nullable Bundle bundle) {
        Intent intent2 = intent;
        int requestCode = i;
        Bundle options = bundle;
        if (!this.mStartedActivityFromFragment && requestCode != -1) {
            checkForValidRequestCode(requestCode);
        }
        super.startActivityForResult(intent2, requestCode, options);
    }

    public void startIntentSenderForResult(IntentSender intentSender, int i, @Nullable Intent intent, int i2, int i3, int i4) throws IntentSender.SendIntentException {
        IntentSender intent2 = intentSender;
        int requestCode = i;
        Intent fillInIntent = intent;
        int flagsMask = i2;
        int flagsValues = i3;
        int extraFlags = i4;
        if (!this.mStartedIntentSenderFromFragment && requestCode != -1) {
            checkForValidRequestCode(requestCode);
        }
        super.startIntentSenderForResult(intent2, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags);
    }

    public void startIntentSenderForResult(IntentSender intentSender, int i, @Nullable Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
        IntentSender intent2 = intentSender;
        int requestCode = i;
        Intent fillInIntent = intent;
        int flagsMask = i2;
        int flagsValues = i3;
        int extraFlags = i4;
        Bundle options = bundle;
        if (!this.mStartedIntentSenderFromFragment && requestCode != -1) {
            checkForValidRequestCode(requestCode);
        }
        super.startIntentSenderForResult(intent2, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
    }

    static void checkForValidRequestCode(int requestCode) {
        Throwable th;
        if ((requestCode & SupportMenu.CATEGORY_MASK) != 0) {
            Throwable th2 = th;
            new IllegalArgumentException("Can only use lower 16 bits for requestCode");
            throw th2;
        }
    }

    public final void validateRequestPermissionsRequestCode(int i) {
        int requestCode = i;
        if (!this.mRequestedPermissionsFromFragment && requestCode != -1) {
            checkForValidRequestCode(requestCode);
        }
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        StringBuilder sb;
        int requestCode = i;
        String[] permissions = strArr;
        int[] grantResults = iArr;
        this.mFragments.noteStateNotSaved();
        int index = (requestCode >> 16) & 65535;
        if (index != 0) {
            int index2 = index - 1;
            String who = this.mPendingFragmentActivityResults.get(index2);
            this.mPendingFragmentActivityResults.remove(index2);
            if (who == null) {
                int w = Log.w(TAG, "Activity result delivered for unknown Fragment.");
                return;
            }
            Fragment frag = this.mFragments.findFragmentByWho(who);
            if (frag == null) {
                new StringBuilder();
                int w2 = Log.w(TAG, sb.append("Activity result no fragment exists for who: ").append(who).toString());
                return;
            }
            frag.onRequestPermissionsResult(requestCode & 65535, permissions, grantResults);
        }
    }

    public void startActivityFromFragment(Fragment fragment, Intent intent, int requestCode) {
        startActivityFromFragment(fragment, intent, requestCode, (Bundle) null);
    }

    public void startActivityFromFragment(Fragment fragment, Intent intent, int i, @Nullable Bundle bundle) {
        Fragment fragment2 = fragment;
        Intent intent2 = intent;
        int requestCode = i;
        Bundle options = bundle;
        this.mStartedActivityFromFragment = true;
        if (requestCode == -1) {
            try {
                ActivityCompat.startActivityForResult(this, intent2, -1, options);
                this.mStartedActivityFromFragment = false;
            } catch (Throwable th) {
                Throwable th2 = th;
                this.mStartedActivityFromFragment = false;
                throw th2;
            }
        } else {
            checkForValidRequestCode(requestCode);
            ActivityCompat.startActivityForResult(this, intent2, ((allocateRequestIndex(fragment2) + 1) << 16) + (requestCode & 65535), options);
            this.mStartedActivityFromFragment = false;
        }
    }

    public void startIntentSenderFromFragment(Fragment fragment, IntentSender intentSender, int i, @Nullable Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
        Fragment fragment2 = fragment;
        IntentSender intent2 = intentSender;
        int requestCode = i;
        Intent fillInIntent = intent;
        int flagsMask = i2;
        int flagsValues = i3;
        int extraFlags = i4;
        Bundle options = bundle;
        this.mStartedIntentSenderFromFragment = true;
        if (requestCode == -1) {
            try {
                ActivityCompat.startIntentSenderForResult(this, intent2, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
                this.mStartedIntentSenderFromFragment = false;
            } catch (Throwable th) {
                Throwable th2 = th;
                this.mStartedIntentSenderFromFragment = false;
                throw th2;
            }
        } else {
            checkForValidRequestCode(requestCode);
            ActivityCompat.startIntentSenderForResult(this, intent2, ((allocateRequestIndex(fragment2) + 1) << 16) + (requestCode & 65535), fillInIntent, flagsMask, flagsValues, extraFlags, options);
            this.mStartedIntentSenderFromFragment = false;
        }
    }

    private int allocateRequestIndex(Fragment fragment) {
        Throwable th;
        Fragment fragment2 = fragment;
        if (this.mPendingFragmentActivityResults.size() >= MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS) {
            Throwable th2 = th;
            new IllegalStateException("Too many pending Fragment activity results.");
            throw th2;
        }
        while (this.mPendingFragmentActivityResults.indexOfKey(this.mNextCandidateRequestIndex) >= 0) {
            this.mNextCandidateRequestIndex = (this.mNextCandidateRequestIndex + 1) % MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS;
        }
        int requestIndex = this.mNextCandidateRequestIndex;
        this.mPendingFragmentActivityResults.put(requestIndex, fragment2.mWho);
        this.mNextCandidateRequestIndex = (this.mNextCandidateRequestIndex + 1) % MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS;
        return requestIndex;
    }

    /* access modifiers changed from: package-private */
    public void requestPermissionsFromFragment(Fragment fragment, String[] strArr, int i) {
        Fragment fragment2 = fragment;
        String[] permissions = strArr;
        int requestCode = i;
        if (requestCode == -1) {
            ActivityCompat.requestPermissions(this, permissions, requestCode);
            return;
        }
        checkForValidRequestCode(requestCode);
        try {
            this.mRequestedPermissionsFromFragment = true;
            ActivityCompat.requestPermissions(this, permissions, ((allocateRequestIndex(fragment2) + 1) << 16) + (requestCode & 65535));
            this.mRequestedPermissionsFromFragment = false;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.mRequestedPermissionsFromFragment = false;
            throw th2;
        }
    }

    /* renamed from: android.support.v4.app.FragmentActivity$HostCallbacks */
    class HostCallbacks extends FragmentHostCallback<FragmentActivity> {
        final /* synthetic */ FragmentActivity this$0;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public HostCallbacks(android.support.p000v4.app.FragmentActivity r5) {
            /*
                r4 = this;
                r0 = r4
                r1 = r5
                r2 = r0
                r3 = r1
                r2.this$0 = r3
                r2 = r0
                r3 = r1
                r2.<init>(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentActivity.HostCallbacks.<init>(android.support.v4.app.FragmentActivity):void");
        }

        public void onDump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
            this.this$0.dump(prefix, fd, writer, args);
        }

        public boolean onShouldSaveFragmentState(Fragment fragment) {
            Fragment fragment2 = fragment;
            return !this.this$0.isFinishing();
        }

        public LayoutInflater onGetLayoutInflater() {
            return this.this$0.getLayoutInflater().cloneInContext(this.this$0);
        }

        public FragmentActivity onGetHost() {
            return this.this$0;
        }

        public void onSupportInvalidateOptionsMenu() {
            this.this$0.supportInvalidateOptionsMenu();
        }

        public void onStartActivityFromFragment(Fragment fragment, Intent intent, int requestCode) {
            this.this$0.startActivityFromFragment(fragment, intent, requestCode);
        }

        public void onStartActivityFromFragment(Fragment fragment, Intent intent, int requestCode, @Nullable Bundle options) {
            this.this$0.startActivityFromFragment(fragment, intent, requestCode, options);
        }

        public void onStartIntentSenderFromFragment(Fragment fragment, IntentSender intent, int requestCode, @Nullable Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) throws IntentSender.SendIntentException {
            this.this$0.startIntentSenderFromFragment(fragment, intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
        }

        public void onRequestPermissionsFromFragment(@NonNull Fragment fragment, @NonNull String[] permissions, int requestCode) {
            this.this$0.requestPermissionsFromFragment(fragment, permissions, requestCode);
        }

        public boolean onShouldShowRequestPermissionRationale(@NonNull String permission) {
            return ActivityCompat.shouldShowRequestPermissionRationale(this.this$0, permission);
        }

        public boolean onHasWindowAnimations() {
            return this.this$0.getWindow() != null;
        }

        public int onGetWindowAnimations() {
            Window w = this.this$0.getWindow();
            return w == null ? 0 : w.getAttributes().windowAnimations;
        }

        public void onAttachFragment(Fragment fragment) {
            this.this$0.onAttachFragment(fragment);
        }

        @Nullable
        public View onFindViewById(int id) {
            return this.this$0.findViewById(id);
        }

        public boolean onHasView() {
            Window w = this.this$0.getWindow();
            return (w == null || w.peekDecorView() == null) ? false : true;
        }
    }

    private void markFragmentsCreated() {
        do {
        } while (markState(getSupportFragmentManager(), Lifecycle.State.CREATED));
    }

    private static boolean markState(FragmentManager manager, Lifecycle.State state) {
        Lifecycle.State state2 = state;
        boolean hadNotMarked = false;
        for (Fragment fragment : manager.getFragments()) {
            if (fragment != null) {
                if (fragment.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                    fragment.mLifecycleRegistry.markState(state2);
                    hadNotMarked = true;
                }
                FragmentManager childFragmentManager = fragment.peekChildFragmentManager();
                if (childFragmentManager != null) {
                    hadNotMarked |= markState(childFragmentManager, state2);
                }
            }
        }
        return hadNotMarked;
    }
}
