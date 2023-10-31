package android.support.p000v4.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.arch.lifecycle.ViewModelStore;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.util.C1643ArraySet;
import android.support.p000v4.util.DebugUtils;
import android.support.p000v4.util.LogWriter;
import android.support.p000v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: android.support.v4.app.FragmentManagerImpl */
/* compiled from: FragmentManager */
final class FragmentManagerImpl extends FragmentManager implements LayoutInflater.Factory2 {
    static final Interpolator ACCELERATE_CUBIC;
    static final Interpolator ACCELERATE_QUINT;
    static final int ANIM_DUR = 220;
    public static final int ANIM_STYLE_CLOSE_ENTER = 3;
    public static final int ANIM_STYLE_CLOSE_EXIT = 4;
    public static final int ANIM_STYLE_FADE_ENTER = 5;
    public static final int ANIM_STYLE_FADE_EXIT = 6;
    public static final int ANIM_STYLE_OPEN_ENTER = 1;
    public static final int ANIM_STYLE_OPEN_EXIT = 2;
    static boolean DEBUG = false;
    static final Interpolator DECELERATE_CUBIC;
    static final Interpolator DECELERATE_QUINT;
    static final String TAG = "FragmentManager";
    static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
    static final String TARGET_STATE_TAG = "android:target_state";
    static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
    static final String VIEW_STATE_TAG = "android:view_state";
    static Field sAnimationListenerField = null;
    SparseArray<Fragment> mActive;
    final ArrayList<Fragment> mAdded;
    ArrayList<Integer> mAvailBackStackIndices;
    ArrayList<BackStackRecord> mBackStack;
    ArrayList<FragmentManager.OnBackStackChangedListener> mBackStackChangeListeners;
    ArrayList<BackStackRecord> mBackStackIndices;
    FragmentContainer mContainer;
    ArrayList<Fragment> mCreatedMenus;
    int mCurState;
    boolean mDestroyed;
    Runnable mExecCommit;
    boolean mExecutingActions;
    boolean mHavePendingDeferredStart;
    FragmentHostCallback mHost;
    private final CopyOnWriteArrayList<FragmentLifecycleCallbacksHolder> mLifecycleCallbacks;
    boolean mNeedMenuInvalidate;
    int mNextFragmentIndex = 0;
    String mNoTransactionsBecause;
    Fragment mParent;
    ArrayList<OpGenerator> mPendingActions;
    ArrayList<StartEnterTransitionListener> mPostponedTransactions;
    @Nullable
    Fragment mPrimaryNav;
    FragmentManagerNonConfig mSavedNonConfig;
    SparseArray<Parcelable> mStateArray;
    Bundle mStateBundle;
    boolean mStateSaved;
    boolean mStopped;
    ArrayList<Fragment> mTmpAddedFragments;
    ArrayList<Boolean> mTmpIsPop;
    ArrayList<BackStackRecord> mTmpRecords;

    /* renamed from: android.support.v4.app.FragmentManagerImpl$OpGenerator */
    /* compiled from: FragmentManager */
    interface OpGenerator {
        boolean generateOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2);
    }

    FragmentManagerImpl() {
        ArrayList<Fragment> arrayList;
        CopyOnWriteArrayList<FragmentLifecycleCallbacksHolder> copyOnWriteArrayList;
        Runnable runnable;
        new ArrayList<>();
        this.mAdded = arrayList;
        new CopyOnWriteArrayList<>();
        this.mLifecycleCallbacks = copyOnWriteArrayList;
        this.mCurState = 0;
        this.mStateBundle = null;
        this.mStateArray = null;
        new Runnable(this) {
            final /* synthetic */ FragmentManagerImpl this$0;

            {
                this.this$0 = this$0;
            }

            public void run() {
                boolean execPendingActions = this.this$0.execPendingActions();
            }
        };
        this.mExecCommit = runnable;
    }

    static {
        Interpolator interpolator;
        Interpolator interpolator2;
        Interpolator interpolator3;
        Interpolator interpolator4;
        new DecelerateInterpolator(2.5f);
        DECELERATE_QUINT = interpolator;
        new DecelerateInterpolator(1.5f);
        DECELERATE_CUBIC = interpolator2;
        new AccelerateInterpolator(2.5f);
        ACCELERATE_QUINT = interpolator3;
        new AccelerateInterpolator(1.5f);
        ACCELERATE_CUBIC = interpolator4;
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder */
    /* compiled from: FragmentManager */
    private static final class FragmentLifecycleCallbacksHolder {
        final FragmentManager.FragmentLifecycleCallbacks mCallback;
        final boolean mRecursive;

        FragmentLifecycleCallbacksHolder(FragmentManager.FragmentLifecycleCallbacks callback, boolean recursive) {
            this.mCallback = callback;
            this.mRecursive = recursive;
        }
    }

    static boolean modifiesAlpha(AnimationOrAnimator animationOrAnimator) {
        AnimationOrAnimator anim = animationOrAnimator;
        if (anim.animation instanceof AlphaAnimation) {
            return true;
        }
        if (!(anim.animation instanceof AnimationSet)) {
            return modifiesAlpha(anim.animator);
        }
        List<Animation> anims = ((AnimationSet) anim.animation).getAnimations();
        for (int i = 0; i < anims.size(); i++) {
            if (anims.get(i) instanceof AlphaAnimation) {
                return true;
            }
        }
        return false;
    }

    static boolean modifiesAlpha(Animator animator) {
        Animator anim = animator;
        if (anim == null) {
            return false;
        }
        if (anim instanceof ValueAnimator) {
            PropertyValuesHolder[] values = ((ValueAnimator) anim).getValues();
            for (int i = 0; i < values.length; i++) {
                if ("alpha".equals(values[i].getPropertyName())) {
                    return true;
                }
            }
        } else if (anim instanceof AnimatorSet) {
            List<Animator> animList = ((AnimatorSet) anim).getChildAnimations();
            for (int i2 = 0; i2 < animList.size(); i2++) {
                if (modifiesAlpha(animList.get(i2))) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean shouldRunOnHWLayer(View view, AnimationOrAnimator animationOrAnimator) {
        View v = view;
        AnimationOrAnimator anim = animationOrAnimator;
        if (v == null || anim == null) {
            return false;
        }
        return Build.VERSION.SDK_INT >= 19 && v.getLayerType() == 0 && ViewCompat.hasOverlappingRendering(v) && modifiesAlpha(anim);
    }

    private void throwException(RuntimeException runtimeException) {
        LogWriter logw;
        PrintWriter printWriter;
        RuntimeException ex = runtimeException;
        int e = Log.e(TAG, ex.getMessage());
        int e2 = Log.e(TAG, "Activity state:");
        new LogWriter(TAG);
        new PrintWriter(logw);
        PrintWriter pw = printWriter;
        if (this.mHost != null) {
            try {
                this.mHost.onDump("  ", (FileDescriptor) null, pw, new String[0]);
            } catch (Exception e3) {
                int e4 = Log.e(TAG, "Failed dumping state", e3);
            }
        } else {
            try {
                dump("  ", (FileDescriptor) null, pw, new String[0]);
            } catch (Exception e5) {
                int e6 = Log.e(TAG, "Failed dumping state", e5);
            }
        }
        throw ex;
    }

    public FragmentTransaction beginTransaction() {
        FragmentTransaction fragmentTransaction;
        new BackStackRecord(this);
        return fragmentTransaction;
    }

    public boolean executePendingTransactions() {
        boolean updates = execPendingActions();
        forcePostponedTransactions();
        return updates;
    }

    public void popBackStack() {
        OpGenerator opGenerator;
        new PopBackStackState(this, (String) null, -1, 0);
        enqueueAction(opGenerator, false);
    }

    public boolean popBackStackImmediate() {
        checkStateLoss();
        return popBackStackImmediate((String) null, -1, 0);
    }

    public void popBackStack(@Nullable String name, int flags) {
        OpGenerator opGenerator;
        new PopBackStackState(this, name, -1, flags);
        enqueueAction(opGenerator, false);
    }

    public boolean popBackStackImmediate(@Nullable String name, int flags) {
        checkStateLoss();
        return popBackStackImmediate(name, -1, flags);
    }

    public void popBackStack(int i, int i2) {
        OpGenerator opGenerator;
        Throwable th;
        StringBuilder sb;
        int id = i;
        int flags = i2;
        if (id < 0) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Bad id: ").append(id).toString());
            throw th2;
        }
        new PopBackStackState(this, (String) null, id, flags);
        enqueueAction(opGenerator, false);
    }

    public boolean popBackStackImmediate(int i, int i2) {
        Throwable th;
        StringBuilder sb;
        int id = i;
        int flags = i2;
        checkStateLoss();
        boolean execPendingActions = execPendingActions();
        if (id >= 0) {
            return popBackStackImmediate((String) null, id, flags);
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Bad id: ").append(id).toString());
        throw th2;
    }

    private boolean popBackStackImmediate(String str, int i, int i2) {
        FragmentManager childManager;
        String name = str;
        int id = i;
        int flags = i2;
        boolean execPendingActions = execPendingActions();
        ensureExecReady(true);
        if (this.mPrimaryNav != null && id < 0 && name == null && (childManager = this.mPrimaryNav.peekChildFragmentManager()) != null && childManager.popBackStackImmediate()) {
            return true;
        }
        boolean executePop = popBackStackState(this.mTmpRecords, this.mTmpIsPop, name, id, flags);
        if (executePop) {
            this.mExecutingActions = true;
            try {
                removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
                cleanupExec();
            } catch (Throwable th) {
                Throwable th2 = th;
                cleanupExec();
                throw th2;
            }
        }
        doPendingDeferredStart();
        burpActive();
        return executePop;
    }

    public int getBackStackEntryCount() {
        return this.mBackStack != null ? this.mBackStack.size() : 0;
    }

    public FragmentManager.BackStackEntry getBackStackEntryAt(int index) {
        return this.mBackStack.get(index);
    }

    public void addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener onBackStackChangedListener) {
        ArrayList<FragmentManager.OnBackStackChangedListener> arrayList;
        FragmentManager.OnBackStackChangedListener listener = onBackStackChangedListener;
        if (this.mBackStackChangeListeners == null) {
            new ArrayList<>();
            this.mBackStackChangeListeners = arrayList;
        }
        boolean add = this.mBackStackChangeListeners.add(listener);
    }

    public void removeOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener onBackStackChangedListener) {
        FragmentManager.OnBackStackChangedListener listener = onBackStackChangedListener;
        if (this.mBackStackChangeListeners != null) {
            boolean remove = this.mBackStackChangeListeners.remove(listener);
        }
    }

    public void putFragment(Bundle bundle, String str, Fragment fragment) {
        RuntimeException runtimeException;
        StringBuilder sb;
        Bundle bundle2 = bundle;
        String key = str;
        Fragment fragment2 = fragment;
        if (fragment2.mIndex < 0) {
            new StringBuilder();
            new IllegalStateException(sb.append("Fragment ").append(fragment2).append(" is not currently in the FragmentManager").toString());
            throwException(runtimeException);
        }
        bundle2.putInt(key, fragment2.mIndex);
    }

    @Nullable
    public Fragment getFragment(Bundle bundle, String str) {
        RuntimeException runtimeException;
        StringBuilder sb;
        String key = str;
        int index = bundle.getInt(key, -1);
        if (index == -1) {
            return null;
        }
        Fragment f = this.mActive.get(index);
        if (f == null) {
            new StringBuilder();
            new IllegalStateException(sb.append("Fragment no longer exists for key ").append(key).append(": index ").append(index).toString());
            throwException(runtimeException);
        }
        return f;
    }

    public List<Fragment> getFragments() {
        if (this.mAdded.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList<Fragment> arrayList = this.mAdded;
        ArrayList<Fragment> arrayList2 = arrayList;
        synchronized (arrayList) {
            try {
                List<Fragment> list = (List) this.mAdded.clone();
                return list;
            } catch (Throwable th) {
                Throwable th2 = th;
                ArrayList<Fragment> arrayList3 = arrayList2;
                throw th2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public List<Fragment> getActiveFragments() {
        ArrayList arrayList;
        if (this.mActive == null) {
            return null;
        }
        int count = this.mActive.size();
        new ArrayList(count);
        ArrayList arrayList2 = arrayList;
        for (int i = 0; i < count; i++) {
            boolean add = arrayList2.add(this.mActive.valueAt(i));
        }
        return arrayList2;
    }

    /* access modifiers changed from: package-private */
    public int getActiveFragmentCount() {
        if (this.mActive == null) {
            return 0;
        }
        return this.mActive.size();
    }

    @Nullable
    public Fragment.SavedState saveFragmentInstanceState(Fragment fragment) {
        Fragment.SavedState savedState;
        Fragment.SavedState savedState2;
        RuntimeException runtimeException;
        StringBuilder sb;
        Fragment fragment2 = fragment;
        if (fragment2.mIndex < 0) {
            new StringBuilder();
            new IllegalStateException(sb.append("Fragment ").append(fragment2).append(" is not currently in the FragmentManager").toString());
            throwException(runtimeException);
        }
        if (fragment2.mState <= 0) {
            return null;
        }
        Bundle result = saveFragmentBasicState(fragment2);
        if (result != null) {
            savedState = savedState2;
            new Fragment.SavedState(result);
        } else {
            savedState = null;
        }
        return savedState;
    }

    public boolean isDestroyed() {
        return this.mDestroyed;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder(128);
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append("FragmentManager{");
        StringBuilder append2 = sb2.append(Integer.toHexString(System.identityHashCode(this)));
        StringBuilder append3 = sb2.append(" in ");
        if (this.mParent != null) {
            DebugUtils.buildShortClassTag(this.mParent, sb2);
        } else {
            DebugUtils.buildShortClassTag(this.mHost, sb2);
        }
        StringBuilder append4 = sb2.append("}}");
        return sb2.toString();
    }

    /* JADX INFO: finally extract failed */
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        StringBuilder sb;
        int N;
        int N2;
        int N3;
        int N4;
        int N5;
        String prefix = str;
        FileDescriptor fd = fileDescriptor;
        PrintWriter writer = printWriter;
        String[] args = strArr;
        new StringBuilder();
        String innerPrefix = sb.append(prefix).append("    ").toString();
        if (this.mActive != null && (N5 = this.mActive.size()) > 0) {
            writer.print(prefix);
            writer.print("Active Fragments in ");
            writer.print(Integer.toHexString(System.identityHashCode(this)));
            writer.println(":");
            for (int i = 0; i < N5; i++) {
                Fragment f = this.mActive.valueAt(i);
                writer.print(prefix);
                writer.print("  #");
                writer.print(i);
                writer.print(": ");
                writer.println(f);
                if (f != null) {
                    f.dump(innerPrefix, fd, writer, args);
                }
            }
        }
        int N6 = this.mAdded.size();
        if (N6 > 0) {
            writer.print(prefix);
            writer.println("Added Fragments:");
            for (int i2 = 0; i2 < N6; i2++) {
                Fragment f2 = this.mAdded.get(i2);
                writer.print(prefix);
                writer.print("  #");
                writer.print(i2);
                writer.print(": ");
                writer.println(f2.toString());
            }
        }
        if (this.mCreatedMenus != null && (N4 = this.mCreatedMenus.size()) > 0) {
            writer.print(prefix);
            writer.println("Fragments Created Menus:");
            for (int i3 = 0; i3 < N4; i3++) {
                Fragment f3 = this.mCreatedMenus.get(i3);
                writer.print(prefix);
                writer.print("  #");
                writer.print(i3);
                writer.print(": ");
                writer.println(f3.toString());
            }
        }
        if (this.mBackStack != null && (N3 = this.mBackStack.size()) > 0) {
            writer.print(prefix);
            writer.println("Back Stack:");
            for (int i4 = 0; i4 < N3; i4++) {
                BackStackRecord bs = this.mBackStack.get(i4);
                writer.print(prefix);
                writer.print("  #");
                writer.print(i4);
                writer.print(": ");
                writer.println(bs.toString());
                bs.dump(innerPrefix, fd, writer, args);
            }
        }
        synchronized (this) {
            try {
                if (this.mBackStackIndices != null && (N2 = this.mBackStackIndices.size()) > 0) {
                    writer.print(prefix);
                    writer.println("Back Stack Indices:");
                    for (int i5 = 0; i5 < N2; i5++) {
                        BackStackRecord bs2 = this.mBackStackIndices.get(i5);
                        writer.print(prefix);
                        writer.print("  #");
                        writer.print(i5);
                        writer.print(": ");
                        writer.println(bs2);
                    }
                }
                if (this.mAvailBackStackIndices != null && this.mAvailBackStackIndices.size() > 0) {
                    writer.print(prefix);
                    writer.print("mAvailBackStackIndices: ");
                    writer.println(Arrays.toString(this.mAvailBackStackIndices.toArray()));
                }
                if (this.mPendingActions != null && (N = this.mPendingActions.size()) > 0) {
                    writer.print(prefix);
                    writer.println("Pending Actions:");
                    for (int i6 = 0; i6 < N; i6++) {
                        OpGenerator r = this.mPendingActions.get(i6);
                        writer.print(prefix);
                        writer.print("  #");
                        writer.print(i6);
                        writer.print(": ");
                        writer.println(r);
                    }
                }
                writer.print(prefix);
                writer.println("FragmentManager misc state:");
                writer.print(prefix);
                writer.print("  mHost=");
                writer.println(this.mHost);
                writer.print(prefix);
                writer.print("  mContainer=");
                writer.println(this.mContainer);
                if (this.mParent != null) {
                    writer.print(prefix);
                    writer.print("  mParent=");
                    writer.println(this.mParent);
                }
                writer.print(prefix);
                writer.print("  mCurState=");
                writer.print(this.mCurState);
                writer.print(" mStateSaved=");
                writer.print(this.mStateSaved);
                writer.print(" mStopped=");
                writer.print(this.mStopped);
                writer.print(" mDestroyed=");
                writer.println(this.mDestroyed);
                if (this.mNeedMenuInvalidate) {
                    writer.print(prefix);
                    writer.print("  mNeedMenuInvalidate=");
                    writer.println(this.mNeedMenuInvalidate);
                }
                if (this.mNoTransactionsBecause != null) {
                    writer.print(prefix);
                    writer.print("  mNoTransactionsBecause=");
                    writer.println(this.mNoTransactionsBecause);
                }
            } catch (Throwable th) {
                while (true) {
                    Throwable th2 = th;
                    throw th2;
                }
            }
        }
    }

    static AnimationOrAnimator makeOpenCloseAnimation(Context context, float f, float f2, float startAlpha, float endAlpha) {
        AnimationSet animationSet;
        ScaleAnimation scaleAnimation;
        AlphaAnimation alphaAnimation;
        AnimationOrAnimator animationOrAnimator;
        Context context2 = context;
        float startScale = f;
        float endScale = f2;
        new AnimationSet(false);
        AnimationSet set = animationSet;
        new ScaleAnimation(startScale, endScale, startScale, endScale, 1, 0.5f, 1, 0.5f);
        ScaleAnimation scale = scaleAnimation;
        scale.setInterpolator(DECELERATE_QUINT);
        scale.setDuration(220);
        set.addAnimation(scale);
        new AlphaAnimation(startAlpha, endAlpha);
        AlphaAnimation alpha = alphaAnimation;
        alpha.setInterpolator(DECELERATE_CUBIC);
        alpha.setDuration(220);
        set.addAnimation(alpha);
        new AnimationOrAnimator((Animation) set);
        return animationOrAnimator;
    }

    static AnimationOrAnimator makeFadeAnimation(Context context, float start, float end) {
        AlphaAnimation alphaAnimation;
        AnimationOrAnimator animationOrAnimator;
        Context context2 = context;
        new AlphaAnimation(start, end);
        AlphaAnimation anim = alphaAnimation;
        anim.setInterpolator(DECELERATE_CUBIC);
        anim.setDuration(220);
        new AnimationOrAnimator((Animation) anim);
        return animationOrAnimator;
    }

    /* access modifiers changed from: package-private */
    public AnimationOrAnimator loadAnimation(Fragment fragment, int i, boolean z, int i2) {
        AnimationOrAnimator animationOrAnimator;
        AnimationOrAnimator animationOrAnimator2;
        AnimationOrAnimator animationOrAnimator3;
        AnimationOrAnimator animationOrAnimator4;
        AnimationOrAnimator animationOrAnimator5;
        Fragment fragment2 = fragment;
        int transit = i;
        boolean enter = z;
        int transitionStyle = i2;
        int nextAnim = fragment2.getNextAnim();
        Animation animation = fragment2.onCreateAnimation(transit, enter, nextAnim);
        if (animation != null) {
            new AnimationOrAnimator(animation);
            return animationOrAnimator5;
        }
        Animator animator = fragment2.onCreateAnimator(transit, enter, nextAnim);
        if (animator != null) {
            new AnimationOrAnimator(animator);
            return animationOrAnimator4;
        }
        if (nextAnim != 0) {
            boolean isAnim = "anim".equals(this.mHost.getContext().getResources().getResourceTypeName(nextAnim));
            boolean successfulLoad = false;
            if (isAnim) {
                try {
                    Animation animation2 = AnimationUtils.loadAnimation(this.mHost.getContext(), nextAnim);
                    if (animation2 != null) {
                        AnimationOrAnimator animationOrAnimator6 = animationOrAnimator3;
                        new AnimationOrAnimator(animation2);
                        return animationOrAnimator6;
                    }
                    successfulLoad = true;
                } catch (Resources.NotFoundException e) {
                    throw e;
                } catch (RuntimeException e2) {
                    RuntimeException runtimeException = e2;
                }
            }
            if (!successfulLoad) {
                try {
                    Animator animator2 = AnimatorInflater.loadAnimator(this.mHost.getContext(), nextAnim);
                    if (animator2 != null) {
                        AnimationOrAnimator animationOrAnimator7 = animationOrAnimator2;
                        new AnimationOrAnimator(animator2);
                        return animationOrAnimator7;
                    }
                } catch (RuntimeException e3) {
                    RuntimeException e4 = e3;
                    if (isAnim) {
                        throw e4;
                    }
                    Animation animation3 = AnimationUtils.loadAnimation(this.mHost.getContext(), nextAnim);
                    if (animation3 != null) {
                        new AnimationOrAnimator(animation3);
                        return animationOrAnimator;
                    }
                }
            }
        }
        if (transit == 0) {
            return null;
        }
        int styleIndex = transitToStyleIndex(transit, enter);
        if (styleIndex < 0) {
            return null;
        }
        switch (styleIndex) {
            case 1:
                return makeOpenCloseAnimation(this.mHost.getContext(), 1.125f, 1.0f, 0.0f, 1.0f);
            case 2:
                return makeOpenCloseAnimation(this.mHost.getContext(), 1.0f, 0.975f, 1.0f, 0.0f);
            case 3:
                return makeOpenCloseAnimation(this.mHost.getContext(), 0.975f, 1.0f, 0.0f, 1.0f);
            case 4:
                return makeOpenCloseAnimation(this.mHost.getContext(), 1.0f, 1.075f, 1.0f, 0.0f);
            case 5:
                return makeFadeAnimation(this.mHost.getContext(), 0.0f, 1.0f);
            case 6:
                return makeFadeAnimation(this.mHost.getContext(), 1.0f, 0.0f);
            default:
                if (transitionStyle == 0 && this.mHost.onHasWindowAnimations()) {
                    transitionStyle = this.mHost.onGetWindowAnimations();
                }
                if (transitionStyle == 0) {
                    return null;
                }
                return null;
        }
    }

    public void performPendingDeferredStart(Fragment fragment) {
        Fragment f = fragment;
        if (!f.mDeferStart) {
            return;
        }
        if (this.mExecutingActions) {
            this.mHavePendingDeferredStart = true;
            return;
        }
        f.mDeferStart = false;
        moveToState(f, this.mCurState, 0, 0, false);
    }

    private static void setHWLayerAnimListenerIfAlpha(View view, AnimationOrAnimator animationOrAnimator) {
        Animation.AnimationListener animationListener;
        Animator.AnimatorListener animatorListener;
        View v = view;
        AnimationOrAnimator anim = animationOrAnimator;
        if (v != null && anim != null && shouldRunOnHWLayer(v, anim)) {
            if (anim.animator != null) {
                new AnimatorOnHWLayerIfNeededListener(v);
                anim.animator.addListener(animatorListener);
                return;
            }
            Animation.AnimationListener originalListener = getAnimationListener(anim.animation);
            v.setLayerType(2, (Paint) null);
            new AnimateOnHWLayerIfNeededListener(v, originalListener);
            anim.animation.setAnimationListener(animationListener);
        }
    }

    private static Animation.AnimationListener getAnimationListener(Animation animation) {
        Animation animation2 = animation;
        Animation.AnimationListener originalListener = null;
        try {
            if (sAnimationListenerField == null) {
                sAnimationListenerField = Animation.class.getDeclaredField("mListener");
                sAnimationListenerField.setAccessible(true);
            }
            originalListener = (Animation.AnimationListener) sAnimationListenerField.get(animation2);
        } catch (NoSuchFieldException e) {
            int e2 = Log.e(TAG, "No field with the name mListener is found in Animation class", e);
        } catch (IllegalAccessException e3) {
            int e4 = Log.e(TAG, "Cannot access Animation's mListener field", e3);
        }
        return originalListener;
    }

    /* access modifiers changed from: package-private */
    public boolean isStateAtLeast(int state) {
        return this.mCurState >= state;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x02fb, code lost:
        r7 = r1.getResources().getResourceName(r1.mContainerId);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x0446, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0447, code lost:
        r8 = r9;
        r7 = android.support.p000v4.p002os.EnvironmentCompat.MEDIA_UNKNOWN;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x0467, code lost:
        if (r2 >= 1) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x046c, code lost:
        if (r0.mDestroyed == false) goto L_0x0484;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x0473, code lost:
        if (r1.getAnimatingAway() == null) goto L_0x05bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0475, code lost:
        r6 = r1.getAnimatingAway();
        r1.setAnimatingAway((android.view.View) null);
        r6.clearAnimation();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x0489, code lost:
        if (r1.getAnimatingAway() != null) goto L_0x0492;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0490, code lost:
        if (r1.getAnimator() == null) goto L_0x05d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0492, code lost:
        r1.setStateAfterAnimating(r2);
        r2 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x04ce, code lost:
        if (r2 >= 3) goto L_0x04fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x04d2, code lost:
        if (DEBUG == false) goto L_0x04f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x04d4, code lost:
        new java.lang.StringBuilder();
        r9 = android.util.Log.v(TAG, r15.append("movefrom STARTED: ").append(r1).toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x04f3, code lost:
        r1.performStop();
        dispatchOnFragmentStopped(r1, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x04ff, code lost:
        if (r2 >= 2) goto L_0x0465;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x0503, code lost:
        if (DEBUG == false) goto L_0x0524;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x0505, code lost:
        new java.lang.StringBuilder();
        r9 = android.util.Log.v(TAG, r15.append("movefrom ACTIVITY_CREATED: ").append(r1).toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x0527, code lost:
        if (r1.mView == null) goto L_0x053d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x0531, code lost:
        if (r0.mHost.onShouldSaveFragmentState(r1) == false) goto L_0x053d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x0536, code lost:
        if (r1.mSavedViewState != null) goto L_0x053d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x0538, code lost:
        saveFragmentViewState(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x053d, code lost:
        r1.performDestroyView();
        dispatchOnFragmentViewDestroyed(r1, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x054a, code lost:
        if (r1.mView == null) goto L_0x059e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x054f, code lost:
        if (r1.mContainer == null) goto L_0x059e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x0551, code lost:
        r1.mContainer.endViewTransition(r1.mView);
        r1.mView.clearAnimation();
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x0565, code lost:
        if (r0.mCurState <= 0) goto L_0x0587;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x056a, code lost:
        if (r0.mDestroyed != false) goto L_0x0587;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x0573, code lost:
        if (r1.mView.getVisibility() != 0) goto L_0x0587;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x057b, code lost:
        if (r1.mPostponedAlpha < 0.0f) goto L_0x0587;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x057d, code lost:
        r6 = loadAnimation(r1, r3, false, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x0587, code lost:
        r1.mPostponedAlpha = 0.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x058c, code lost:
        if (r6 == null) goto L_0x0595;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x058e, code lost:
        animateRemoveFragment(r1, r6, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x0595, code lost:
        r1.mContainer.removeView(r1.mView);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x059e, code lost:
        r1.mContainer = null;
        r1.mView = null;
        r1.mViewLifecycleOwner = null;
        r1.mViewLifecycleOwnerLiveData.setValue(null);
        r1.mInnerView = null;
        r1.mInLayout = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x05c0, code lost:
        if (r1.getAnimator() == null) goto L_0x0484;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x05c2, code lost:
        r6 = r1.getAnimator();
        r1.setAnimator((android.animation.Animator) null);
        r6.cancel();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x05d5, code lost:
        if (DEBUG == false) goto L_0x05f6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x05d7, code lost:
        new java.lang.StringBuilder();
        r9 = android.util.Log.v(TAG, r15.append("movefrom CREATED: ").append(r1).toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x05f9, code lost:
        if (r1.mRetaining != false) goto L_0x061e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x05fb, code lost:
        r1.performDestroy();
        dispatchOnFragmentDestroyed(r1, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x0605, code lost:
        r1.performDetach();
        dispatchOnFragmentDetached(r1, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x0610, code lost:
        if (r5 != false) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x0615, code lost:
        if (r1.mRetaining != false) goto L_0x0623;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x0617, code lost:
        makeInactive(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x061e, code lost:
        r1.mState = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x0623, code lost:
        r1.mHost = null;
        r1.mParentFragment = null;
        r1.mFragmentManager = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0272, code lost:
        ensureInflatedFragmentView(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0279, code lost:
        if (r2 <= 1) goto L_0x03c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x027d, code lost:
        if (DEBUG == false) goto L_0x029e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x027f, code lost:
        new java.lang.StringBuilder();
        r9 = android.util.Log.v(TAG, r15.append("moveto ACTIVITY_CREATED: ").append(r1).toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x02a1, code lost:
        if (r1.mFromLayout != false) goto L_0x03a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x02a3, code lost:
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x02a8, code lost:
        if (r1.mContainerId == 0) goto L_0x033f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x02ae, code lost:
        if (r1.mContainerId != -1) goto L_0x02db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x02b0, code lost:
        new java.lang.StringBuilder();
        new java.lang.IllegalArgumentException(r15.append("Cannot create fragment ").append(r1).append(" for a container view with no id").toString());
        throwException(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x02db, code lost:
        r6 = (android.view.ViewGroup) r0.mContainer.onFindViewById(r1.mContainerId);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x02e9, code lost:
        if (r6 != null) goto L_0x033f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x02ee, code lost:
        if (r1.mRestored != false) goto L_0x033f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:216:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x008d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void moveToState(android.support.p000v4.app.Fragment r17, int r18, int r19, int r20, boolean r21) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r19
            r4 = r20
            r5 = r21
            r9 = r1
            boolean r9 = r9.mAdded
            if (r9 == 0) goto L_0x0016
            r9 = r1
            boolean r9 = r9.mDetached
            if (r9 == 0) goto L_0x001c
        L_0x0016:
            r9 = r2
            r10 = 1
            if (r9 <= r10) goto L_0x001c
            r9 = 1
            r2 = r9
        L_0x001c:
            r9 = r1
            boolean r9 = r9.mRemoving
            if (r9 == 0) goto L_0x0035
            r9 = r2
            r10 = r1
            int r10 = r10.mState
            if (r9 <= r10) goto L_0x0035
            r9 = r1
            int r9 = r9.mState
            if (r9 != 0) goto L_0x0057
            r9 = r1
            boolean r9 = r9.isInBackStack()
            if (r9 == 0) goto L_0x0057
            r9 = 1
            r2 = r9
        L_0x0035:
            r9 = r1
            boolean r9 = r9.mDeferStart
            if (r9 == 0) goto L_0x0046
            r9 = r1
            int r9 = r9.mState
            r10 = 3
            if (r9 >= r10) goto L_0x0046
            r9 = r2
            r10 = 2
            if (r9 <= r10) goto L_0x0046
            r9 = 2
            r2 = r9
        L_0x0046:
            r9 = r1
            int r9 = r9.mState
            r10 = r2
            if (r9 > r10) goto L_0x0457
            r9 = r1
            boolean r9 = r9.mFromLayout
            if (r9 == 0) goto L_0x005c
            r9 = r1
            boolean r9 = r9.mInLayout
            if (r9 != 0) goto L_0x005c
        L_0x0056:
            return
        L_0x0057:
            r9 = r1
            int r9 = r9.mState
            r2 = r9
            goto L_0x0035
        L_0x005c:
            r9 = r1
            android.view.View r9 = r9.getAnimatingAway()
            if (r9 != 0) goto L_0x006a
            r9 = r1
            android.animation.Animator r9 = r9.getAnimator()
            if (r9 == 0) goto L_0x0081
        L_0x006a:
            r9 = r1
            r10 = 0
            r9.setAnimatingAway(r10)
            r9 = r1
            r10 = 0
            r9.setAnimator(r10)
            r9 = r0
            r10 = r1
            r11 = r1
            int r11 = r11.getStateAfterAnimating()
            r12 = 0
            r13 = 0
            r14 = 1
            r9.moveToState(r10, r11, r12, r13, r14)
        L_0x0081:
            r9 = r1
            int r9 = r9.mState
            switch(r9) {
                case 0: goto L_0x00d2;
                case 1: goto L_0x0272;
                case 2: goto L_0x03c4;
                case 3: goto L_0x03f5;
                default: goto L_0x0087;
            }
        L_0x0087:
            r9 = r1
            int r9 = r9.mState
            r10 = r2
            if (r9 == r10) goto L_0x00d1
            java.lang.String r9 = "FragmentManager"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r15 = r10
            r10 = r15
            r11 = r15
            r11.<init>()
            java.lang.String r11 = "moveToState: Fragment state for "
            java.lang.StringBuilder r10 = r10.append(r11)
            r11 = r1
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r11 = " not updated inline; "
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r11 = "expected state "
            java.lang.StringBuilder r10 = r10.append(r11)
            r11 = r2
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r11 = " found "
            java.lang.StringBuilder r10 = r10.append(r11)
            r11 = r1
            int r11 = r11.mState
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            int r9 = android.util.Log.w(r9, r10)
            r9 = r1
            r10 = r2
            r9.mState = r10
        L_0x00d1:
            goto L_0x0056
        L_0x00d2:
            r9 = r2
            if (r9 <= 0) goto L_0x0272
            boolean r9 = DEBUG
            if (r9 == 0) goto L_0x00f8
            java.lang.String r9 = "FragmentManager"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r15 = r10
            r10 = r15
            r11 = r15
            r11.<init>()
            java.lang.String r11 = "moveto CREATED: "
            java.lang.StringBuilder r10 = r10.append(r11)
            r11 = r1
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            int r9 = android.util.Log.v(r9, r10)
        L_0x00f8:
            r9 = r1
            android.os.Bundle r9 = r9.mSavedFragmentState
            if (r9 == 0) goto L_0x015e
            r9 = r1
            android.os.Bundle r9 = r9.mSavedFragmentState
            r10 = r0
            android.support.v4.app.FragmentHostCallback r10 = r10.mHost
            android.content.Context r10 = r10.getContext()
            java.lang.ClassLoader r10 = r10.getClassLoader()
            r9.setClassLoader(r10)
            r9 = r1
            r10 = r1
            android.os.Bundle r10 = r10.mSavedFragmentState
            java.lang.String r11 = "android:view_state"
            android.util.SparseArray r10 = r10.getSparseParcelableArray(r11)
            r9.mSavedViewState = r10
            r9 = r1
            r10 = r0
            r11 = r1
            android.os.Bundle r11 = r11.mSavedFragmentState
            java.lang.String r12 = "android:target_state"
            android.support.v4.app.Fragment r10 = r10.getFragment(r11, r12)
            r9.mTarget = r10
            r9 = r1
            android.support.v4.app.Fragment r9 = r9.mTarget
            if (r9 == 0) goto L_0x013c
            r9 = r1
            r10 = r1
            android.os.Bundle r10 = r10.mSavedFragmentState
            java.lang.String r11 = "android:target_req_state"
            r12 = 0
            int r10 = r10.getInt(r11, r12)
            r9.mTargetRequestCode = r10
        L_0x013c:
            r9 = r1
            java.lang.Boolean r9 = r9.mSavedUserVisibleHint
            if (r9 == 0) goto L_0x01c3
            r9 = r1
            r10 = r1
            java.lang.Boolean r10 = r10.mSavedUserVisibleHint
            boolean r10 = r10.booleanValue()
            r9.mUserVisibleHint = r10
            r9 = r1
            r10 = 0
            r9.mSavedUserVisibleHint = r10
        L_0x014f:
            r9 = r1
            boolean r9 = r9.mUserVisibleHint
            if (r9 != 0) goto L_0x015e
            r9 = r1
            r10 = 1
            r9.mDeferStart = r10
            r9 = r2
            r10 = 2
            if (r9 <= r10) goto L_0x015e
            r9 = 2
            r2 = r9
        L_0x015e:
            r9 = r1
            r10 = r0
            android.support.v4.app.FragmentHostCallback r10 = r10.mHost
            r9.mHost = r10
            r9 = r1
            r10 = r0
            android.support.v4.app.Fragment r10 = r10.mParent
            r9.mParentFragment = r10
            r9 = r1
            r10 = r0
            android.support.v4.app.Fragment r10 = r10.mParent
            if (r10 == 0) goto L_0x01d3
            r10 = r0
            android.support.v4.app.Fragment r10 = r10.mParent
            android.support.v4.app.FragmentManagerImpl r10 = r10.mChildFragmentManager
        L_0x0175:
            r9.mFragmentManager = r10
            r9 = r1
            android.support.v4.app.Fragment r9 = r9.mTarget
            if (r9 == 0) goto L_0x01ee
            r9 = r0
            android.util.SparseArray<android.support.v4.app.Fragment> r9 = r9.mActive
            r10 = r1
            android.support.v4.app.Fragment r10 = r10.mTarget
            int r10 = r10.mIndex
            java.lang.Object r9 = r9.get(r10)
            r10 = r1
            android.support.v4.app.Fragment r10 = r10.mTarget
            if (r9 == r10) goto L_0x01db
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            r15 = r9
            r9 = r15
            r10 = r15
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r15 = r11
            r11 = r15
            r12 = r15
            r12.<init>()
            java.lang.String r12 = "Fragment "
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = r1
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r12 = " declared target fragment "
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = r1
            android.support.v4.app.Fragment r12 = r12.mTarget
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r12 = " that does not belong to this FragmentManager!"
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r11 = r11.toString()
            r10.<init>(r11)
            throw r9
        L_0x01c3:
            r9 = r1
            r10 = r1
            android.os.Bundle r10 = r10.mSavedFragmentState
            java.lang.String r11 = "android:user_visible_hint"
            r12 = 1
            boolean r10 = r10.getBoolean(r11, r12)
            r9.mUserVisibleHint = r10
            goto L_0x014f
        L_0x01d3:
            r10 = r0
            android.support.v4.app.FragmentHostCallback r10 = r10.mHost
            android.support.v4.app.FragmentManagerImpl r10 = r10.getFragmentManagerImpl()
            goto L_0x0175
        L_0x01db:
            r9 = r1
            android.support.v4.app.Fragment r9 = r9.mTarget
            int r9 = r9.mState
            r10 = 1
            if (r9 >= r10) goto L_0x01ee
            r9 = r0
            r10 = r1
            android.support.v4.app.Fragment r10 = r10.mTarget
            r11 = 1
            r12 = 0
            r13 = 0
            r14 = 1
            r9.moveToState(r10, r11, r12, r13, r14)
        L_0x01ee:
            r9 = r0
            r10 = r1
            r11 = r0
            android.support.v4.app.FragmentHostCallback r11 = r11.mHost
            android.content.Context r11 = r11.getContext()
            r12 = 0
            r9.dispatchOnFragmentPreAttached(r10, r11, r12)
            r9 = r1
            r10 = 0
            r9.mCalled = r10
            r9 = r1
            r10 = r0
            android.support.v4.app.FragmentHostCallback r10 = r10.mHost
            android.content.Context r10 = r10.getContext()
            r9.onAttach((android.content.Context) r10)
            r9 = r1
            boolean r9 = r9.mCalled
            if (r9 != 0) goto L_0x0237
            android.support.v4.app.SuperNotCalledException r9 = new android.support.v4.app.SuperNotCalledException
            r15 = r9
            r9 = r15
            r10 = r15
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r15 = r11
            r11 = r15
            r12 = r15
            r12.<init>()
            java.lang.String r12 = "Fragment "
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = r1
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r12 = " did not call through to super.onAttach()"
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r11 = r11.toString()
            r10.<init>(r11)
            throw r9
        L_0x0237:
            r9 = r1
            android.support.v4.app.Fragment r9 = r9.mParentFragment
            if (r9 != 0) goto L_0x0430
            r9 = r0
            android.support.v4.app.FragmentHostCallback r9 = r9.mHost
            r10 = r1
            r9.onAttachFragment(r10)
        L_0x0243:
            r9 = r0
            r10 = r1
            r11 = r0
            android.support.v4.app.FragmentHostCallback r11 = r11.mHost
            android.content.Context r11 = r11.getContext()
            r12 = 0
            r9.dispatchOnFragmentAttached(r10, r11, r12)
            r9 = r1
            boolean r9 = r9.mIsCreated
            if (r9 != 0) goto L_0x0439
            r9 = r0
            r10 = r1
            r11 = r1
            android.os.Bundle r11 = r11.mSavedFragmentState
            r12 = 0
            r9.dispatchOnFragmentPreCreated(r10, r11, r12)
            r9 = r1
            r10 = r1
            android.os.Bundle r10 = r10.mSavedFragmentState
            r9.performCreate(r10)
            r9 = r0
            r10 = r1
            r11 = r1
            android.os.Bundle r11 = r11.mSavedFragmentState
            r12 = 0
            r9.dispatchOnFragmentCreated(r10, r11, r12)
        L_0x026e:
            r9 = r1
            r10 = 0
            r9.mRetaining = r10
        L_0x0272:
            r9 = r0
            r10 = r1
            r9.ensureInflatedFragmentView(r10)
            r9 = r2
            r10 = 1
            if (r9 <= r10) goto L_0x03c4
            boolean r9 = DEBUG
            if (r9 == 0) goto L_0x029e
            java.lang.String r9 = "FragmentManager"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r15 = r10
            r10 = r15
            r11 = r15
            r11.<init>()
            java.lang.String r11 = "moveto ACTIVITY_CREATED: "
            java.lang.StringBuilder r10 = r10.append(r11)
            r11 = r1
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            int r9 = android.util.Log.v(r9, r10)
        L_0x029e:
            r9 = r1
            boolean r9 = r9.mFromLayout
            if (r9 != 0) goto L_0x03a4
            r9 = 0
            r6 = r9
            r9 = r1
            int r9 = r9.mContainerId
            if (r9 == 0) goto L_0x033f
            r9 = r1
            int r9 = r9.mContainerId
            r10 = -1
            if (r9 != r10) goto L_0x02db
            r9 = r0
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            r15 = r10
            r10 = r15
            r11 = r15
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r15 = r12
            r12 = r15
            r13 = r15
            r13.<init>()
            java.lang.String r13 = "Cannot create fragment "
            java.lang.StringBuilder r12 = r12.append(r13)
            r13 = r1
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r13 = " for a container view with no id"
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r12 = r12.toString()
            r11.<init>(r12)
            r9.throwException(r10)
        L_0x02db:
            r9 = r0
            android.support.v4.app.FragmentContainer r9 = r9.mContainer
            r10 = r1
            int r10 = r10.mContainerId
            android.view.View r9 = r9.onFindViewById(r10)
            android.view.ViewGroup r9 = (android.view.ViewGroup) r9
            r6 = r9
            r9 = r6
            if (r9 != 0) goto L_0x033f
            r9 = r1
            boolean r9 = r9.mRestored
            if (r9 != 0) goto L_0x033f
            r9 = r1
            android.content.res.Resources r9 = r9.getResources()     // Catch:{ NotFoundException -> 0x0446 }
            r10 = r1
            int r10 = r10.mContainerId     // Catch:{ NotFoundException -> 0x0446 }
            java.lang.String r9 = r9.getResourceName(r10)     // Catch:{ NotFoundException -> 0x0446 }
            r7 = r9
        L_0x02fd:
            r9 = r0
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            r15 = r10
            r10 = r15
            r11 = r15
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r15 = r12
            r12 = r15
            r13 = r15
            r13.<init>()
            java.lang.String r13 = "No view found for id 0x"
            java.lang.StringBuilder r12 = r12.append(r13)
            r13 = r1
            int r13 = r13.mContainerId
            java.lang.String r13 = java.lang.Integer.toHexString(r13)
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r13 = " ("
            java.lang.StringBuilder r12 = r12.append(r13)
            r13 = r7
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r13 = ") for fragment "
            java.lang.StringBuilder r12 = r12.append(r13)
            r13 = r1
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r12 = r12.toString()
            r11.<init>(r12)
            r9.throwException(r10)
        L_0x033f:
            r9 = r1
            r10 = r6
            r9.mContainer = r10
            r9 = r1
            r10 = r1
            r11 = r1
            android.os.Bundle r11 = r11.mSavedFragmentState
            android.view.LayoutInflater r10 = r10.performGetLayoutInflater(r11)
            r11 = r6
            r12 = r1
            android.os.Bundle r12 = r12.mSavedFragmentState
            r9.performCreateView(r10, r11, r12)
            r9 = r1
            android.view.View r9 = r9.mView
            if (r9 == 0) goto L_0x0451
            r9 = r1
            r10 = r1
            android.view.View r10 = r10.mView
            r9.mInnerView = r10
            r9 = r1
            android.view.View r9 = r9.mView
            r10 = 0
            r9.setSaveFromParentEnabled(r10)
            r9 = r6
            if (r9 == 0) goto L_0x036f
            r9 = r6
            r10 = r1
            android.view.View r10 = r10.mView
            r9.addView(r10)
        L_0x036f:
            r9 = r1
            boolean r9 = r9.mHidden
            if (r9 == 0) goto L_0x037c
            r9 = r1
            android.view.View r9 = r9.mView
            r10 = 8
            r9.setVisibility(r10)
        L_0x037c:
            r9 = r1
            r10 = r1
            android.view.View r10 = r10.mView
            r11 = r1
            android.os.Bundle r11 = r11.mSavedFragmentState
            r9.onViewCreated(r10, r11)
            r9 = r0
            r10 = r1
            r11 = r1
            android.view.View r11 = r11.mView
            r12 = r1
            android.os.Bundle r12 = r12.mSavedFragmentState
            r13 = 0
            r9.dispatchOnFragmentViewCreated(r10, r11, r12, r13)
            r9 = r1
            r10 = r1
            android.view.View r10 = r10.mView
            int r10 = r10.getVisibility()
            if (r10 != 0) goto L_0x044e
            r10 = r1
            android.view.ViewGroup r10 = r10.mContainer
            if (r10 == 0) goto L_0x044e
            r10 = 1
        L_0x03a2:
            r9.mIsNewlyAdded = r10
        L_0x03a4:
            r9 = r1
            r10 = r1
            android.os.Bundle r10 = r10.mSavedFragmentState
            r9.performActivityCreated(r10)
            r9 = r0
            r10 = r1
            r11 = r1
            android.os.Bundle r11 = r11.mSavedFragmentState
            r12 = 0
            r9.dispatchOnFragmentActivityCreated(r10, r11, r12)
            r9 = r1
            android.view.View r9 = r9.mView
            if (r9 == 0) goto L_0x03c0
            r9 = r1
            r10 = r1
            android.os.Bundle r10 = r10.mSavedFragmentState
            r9.restoreViewState(r10)
        L_0x03c0:
            r9 = r1
            r10 = 0
            r9.mSavedFragmentState = r10
        L_0x03c4:
            r9 = r2
            r10 = 2
            if (r9 <= r10) goto L_0x03f5
            boolean r9 = DEBUG
            if (r9 == 0) goto L_0x03eb
            java.lang.String r9 = "FragmentManager"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r15 = r10
            r10 = r15
            r11 = r15
            r11.<init>()
            java.lang.String r11 = "moveto STARTED: "
            java.lang.StringBuilder r10 = r10.append(r11)
            r11 = r1
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            int r9 = android.util.Log.v(r9, r10)
        L_0x03eb:
            r9 = r1
            r9.performStart()
            r9 = r0
            r10 = r1
            r11 = 0
            r9.dispatchOnFragmentStarted(r10, r11)
        L_0x03f5:
            r9 = r2
            r10 = 3
            if (r9 <= r10) goto L_0x0087
            boolean r9 = DEBUG
            if (r9 == 0) goto L_0x041c
            java.lang.String r9 = "FragmentManager"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r15 = r10
            r10 = r15
            r11 = r15
            r11.<init>()
            java.lang.String r11 = "moveto RESUMED: "
            java.lang.StringBuilder r10 = r10.append(r11)
            r11 = r1
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            int r9 = android.util.Log.v(r9, r10)
        L_0x041c:
            r9 = r1
            r9.performResume()
            r9 = r0
            r10 = r1
            r11 = 0
            r9.dispatchOnFragmentResumed(r10, r11)
            r9 = r1
            r10 = 0
            r9.mSavedFragmentState = r10
            r9 = r1
            r10 = 0
            r9.mSavedViewState = r10
            goto L_0x0087
        L_0x0430:
            r9 = r1
            android.support.v4.app.Fragment r9 = r9.mParentFragment
            r10 = r1
            r9.onAttachFragment(r10)
            goto L_0x0243
        L_0x0439:
            r9 = r1
            r10 = r1
            android.os.Bundle r10 = r10.mSavedFragmentState
            r9.restoreChildFragmentState(r10)
            r9 = r1
            r10 = 1
            r9.mState = r10
            goto L_0x026e
        L_0x0446:
            r9 = move-exception
            r8 = r9
            java.lang.String r9 = "unknown"
            r7 = r9
            goto L_0x02fd
        L_0x044e:
            r10 = 0
            goto L_0x03a2
        L_0x0451:
            r9 = r1
            r10 = 0
            r9.mInnerView = r10
            goto L_0x03a4
        L_0x0457:
            r9 = r1
            int r9 = r9.mState
            r10 = r2
            if (r9 <= r10) goto L_0x0087
            r9 = r1
            int r9 = r9.mState
            switch(r9) {
                case 1: goto L_0x0465;
                case 2: goto L_0x04fd;
                case 3: goto L_0x04cc;
                case 4: goto L_0x049b;
                default: goto L_0x0463;
            }
        L_0x0463:
            goto L_0x0087
        L_0x0465:
            r9 = r2
            r10 = 1
            if (r9 >= r10) goto L_0x0087
            r9 = r0
            boolean r9 = r9.mDestroyed
            if (r9 == 0) goto L_0x0484
            r9 = r1
            android.view.View r9 = r9.getAnimatingAway()
            if (r9 == 0) goto L_0x05bb
            r9 = r1
            android.view.View r9 = r9.getAnimatingAway()
            r6 = r9
            r9 = r1
            r10 = 0
            r9.setAnimatingAway(r10)
            r9 = r6
            r9.clearAnimation()
        L_0x0484:
            r9 = r1
            android.view.View r9 = r9.getAnimatingAway()
            if (r9 != 0) goto L_0x0492
            r9 = r1
            android.animation.Animator r9 = r9.getAnimator()
            if (r9 == 0) goto L_0x05d3
        L_0x0492:
            r9 = r1
            r10 = r2
            r9.setStateAfterAnimating(r10)
            r9 = 1
            r2 = r9
            goto L_0x0087
        L_0x049b:
            r9 = r2
            r10 = 4
            if (r9 >= r10) goto L_0x04cc
            boolean r9 = DEBUG
            if (r9 == 0) goto L_0x04c2
            java.lang.String r9 = "FragmentManager"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r15 = r10
            r10 = r15
            r11 = r15
            r11.<init>()
            java.lang.String r11 = "movefrom RESUMED: "
            java.lang.StringBuilder r10 = r10.append(r11)
            r11 = r1
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            int r9 = android.util.Log.v(r9, r10)
        L_0x04c2:
            r9 = r1
            r9.performPause()
            r9 = r0
            r10 = r1
            r11 = 0
            r9.dispatchOnFragmentPaused(r10, r11)
        L_0x04cc:
            r9 = r2
            r10 = 3
            if (r9 >= r10) goto L_0x04fd
            boolean r9 = DEBUG
            if (r9 == 0) goto L_0x04f3
            java.lang.String r9 = "FragmentManager"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r15 = r10
            r10 = r15
            r11 = r15
            r11.<init>()
            java.lang.String r11 = "movefrom STARTED: "
            java.lang.StringBuilder r10 = r10.append(r11)
            r11 = r1
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            int r9 = android.util.Log.v(r9, r10)
        L_0x04f3:
            r9 = r1
            r9.performStop()
            r9 = r0
            r10 = r1
            r11 = 0
            r9.dispatchOnFragmentStopped(r10, r11)
        L_0x04fd:
            r9 = r2
            r10 = 2
            if (r9 >= r10) goto L_0x0465
            boolean r9 = DEBUG
            if (r9 == 0) goto L_0x0524
            java.lang.String r9 = "FragmentManager"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r15 = r10
            r10 = r15
            r11 = r15
            r11.<init>()
            java.lang.String r11 = "movefrom ACTIVITY_CREATED: "
            java.lang.StringBuilder r10 = r10.append(r11)
            r11 = r1
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            int r9 = android.util.Log.v(r9, r10)
        L_0x0524:
            r9 = r1
            android.view.View r9 = r9.mView
            if (r9 == 0) goto L_0x053d
            r9 = r0
            android.support.v4.app.FragmentHostCallback r9 = r9.mHost
            r10 = r1
            boolean r9 = r9.onShouldSaveFragmentState(r10)
            if (r9 == 0) goto L_0x053d
            r9 = r1
            android.util.SparseArray<android.os.Parcelable> r9 = r9.mSavedViewState
            if (r9 != 0) goto L_0x053d
            r9 = r0
            r10 = r1
            r9.saveFragmentViewState(r10)
        L_0x053d:
            r9 = r1
            r9.performDestroyView()
            r9 = r0
            r10 = r1
            r11 = 0
            r9.dispatchOnFragmentViewDestroyed(r10, r11)
            r9 = r1
            android.view.View r9 = r9.mView
            if (r9 == 0) goto L_0x059e
            r9 = r1
            android.view.ViewGroup r9 = r9.mContainer
            if (r9 == 0) goto L_0x059e
            r9 = r1
            android.view.ViewGroup r9 = r9.mContainer
            r10 = r1
            android.view.View r10 = r10.mView
            r9.endViewTransition(r10)
            r9 = r1
            android.view.View r9 = r9.mView
            r9.clearAnimation()
            r9 = 0
            r6 = r9
            r9 = r0
            int r9 = r9.mCurState
            if (r9 <= 0) goto L_0x0587
            r9 = r0
            boolean r9 = r9.mDestroyed
            if (r9 != 0) goto L_0x0587
            r9 = r1
            android.view.View r9 = r9.mView
            int r9 = r9.getVisibility()
            if (r9 != 0) goto L_0x0587
            r9 = r1
            float r9 = r9.mPostponedAlpha
            r10 = 0
            int r9 = (r9 > r10 ? 1 : (r9 == r10 ? 0 : -1))
            if (r9 < 0) goto L_0x0587
            r9 = r0
            r10 = r1
            r11 = r3
            r12 = 0
            r13 = r4
            android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator r9 = r9.loadAnimation(r10, r11, r12, r13)
            r6 = r9
        L_0x0587:
            r9 = r1
            r10 = 0
            r9.mPostponedAlpha = r10
            r9 = r6
            if (r9 == 0) goto L_0x0595
            r9 = r0
            r10 = r1
            r11 = r6
            r12 = r2
            r9.animateRemoveFragment(r10, r11, r12)
        L_0x0595:
            r9 = r1
            android.view.ViewGroup r9 = r9.mContainer
            r10 = r1
            android.view.View r10 = r10.mView
            r9.removeView(r10)
        L_0x059e:
            r9 = r1
            r10 = 0
            r9.mContainer = r10
            r9 = r1
            r10 = 0
            r9.mView = r10
            r9 = r1
            r10 = 0
            r9.mViewLifecycleOwner = r10
            r9 = r1
            android.arch.lifecycle.MutableLiveData<android.arch.lifecycle.LifecycleOwner> r9 = r9.mViewLifecycleOwnerLiveData
            r10 = 0
            r9.setValue(r10)
            r9 = r1
            r10 = 0
            r9.mInnerView = r10
            r9 = r1
            r10 = 0
            r9.mInLayout = r10
            goto L_0x0465
        L_0x05bb:
            r9 = r1
            android.animation.Animator r9 = r9.getAnimator()
            if (r9 == 0) goto L_0x0484
            r9 = r1
            android.animation.Animator r9 = r9.getAnimator()
            r6 = r9
            r9 = r1
            r10 = 0
            r9.setAnimator(r10)
            r9 = r6
            r9.cancel()
            goto L_0x0484
        L_0x05d3:
            boolean r9 = DEBUG
            if (r9 == 0) goto L_0x05f6
            java.lang.String r9 = "FragmentManager"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r15 = r10
            r10 = r15
            r11 = r15
            r11.<init>()
            java.lang.String r11 = "movefrom CREATED: "
            java.lang.StringBuilder r10 = r10.append(r11)
            r11 = r1
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            int r9 = android.util.Log.v(r9, r10)
        L_0x05f6:
            r9 = r1
            boolean r9 = r9.mRetaining
            if (r9 != 0) goto L_0x061e
            r9 = r1
            r9.performDestroy()
            r9 = r0
            r10 = r1
            r11 = 0
            r9.dispatchOnFragmentDestroyed(r10, r11)
        L_0x0605:
            r9 = r1
            r9.performDetach()
            r9 = r0
            r10 = r1
            r11 = 0
            r9.dispatchOnFragmentDetached(r10, r11)
            r9 = r5
            if (r9 != 0) goto L_0x0087
            r9 = r1
            boolean r9 = r9.mRetaining
            if (r9 != 0) goto L_0x0623
            r9 = r0
            r10 = r1
            r9.makeInactive(r10)
            goto L_0x0087
        L_0x061e:
            r9 = r1
            r10 = 0
            r9.mState = r10
            goto L_0x0605
        L_0x0623:
            r9 = r1
            r10 = 0
            r9.mHost = r10
            r9 = r1
            r10 = 0
            r9.mParentFragment = r10
            r9 = r1
            r10 = 0
            r9.mFragmentManager = r10
            goto L_0x0087
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentManagerImpl.moveToState(android.support.v4.app.Fragment, int, int, int, boolean):void");
    }

    private void animateRemoveFragment(@NonNull Fragment fragment, @NonNull AnimationOrAnimator animationOrAnimator, int newState) {
        Animator.AnimatorListener animatorListener;
        Animation animation;
        Animation.AnimationListener animationListener;
        Fragment fragment2 = fragment;
        AnimationOrAnimator anim = animationOrAnimator;
        View viewToAnimate = fragment2.mView;
        ViewGroup container = fragment2.mContainer;
        container.startViewTransition(viewToAnimate);
        fragment2.setStateAfterAnimating(newState);
        if (anim.animation != null) {
            new EndViewTransitionAnimator(anim.animation, container, viewToAnimate);
            Animation animation2 = animation;
            fragment2.setAnimatingAway(fragment2.mView);
            final ViewGroup viewGroup = container;
            final Fragment fragment3 = fragment2;
            new AnimationListenerWrapper(this, getAnimationListener(animation2)) {
                final /* synthetic */ FragmentManagerImpl this$0;

                {
                    this.this$0 = this$0;
                }

                public void onAnimationEnd(Animation animation) {
                    Runnable runnable;
                    super.onAnimationEnd(animation);
                    new Runnable(this) {
                        final /* synthetic */ C02482 this$1;

                        {
                            this.this$1 = this$1;
                        }

                        public void run() {
                            if (fragment3.getAnimatingAway() != null) {
                                fragment3.setAnimatingAway((View) null);
                                this.this$1.this$0.moveToState(fragment3, fragment3.getStateAfterAnimating(), 0, 0, false);
                            }
                        }
                    };
                    boolean post = viewGroup.post(runnable);
                }
            };
            animation2.setAnimationListener(animationListener);
            setHWLayerAnimListenerIfAlpha(viewToAnimate, anim);
            fragment2.mView.startAnimation(animation2);
            return;
        }
        Animator animator = anim.animator;
        fragment2.setAnimator(anim.animator);
        final ViewGroup viewGroup2 = container;
        final View view = viewToAnimate;
        final Fragment fragment4 = fragment2;
        new AnimatorListenerAdapter(this) {
            final /* synthetic */ FragmentManagerImpl this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationEnd(Animator animator) {
                Animator animator2 = animator;
                viewGroup2.endViewTransition(view);
                Animator animator3 = fragment4.getAnimator();
                fragment4.setAnimator((Animator) null);
                if (animator3 != null && viewGroup2.indexOfChild(view) < 0) {
                    this.this$0.moveToState(fragment4, fragment4.getStateAfterAnimating(), 0, 0, false);
                }
            }
        };
        animator.addListener(animatorListener);
        animator.setTarget(fragment2.mView);
        setHWLayerAnimListenerIfAlpha(fragment2.mView, anim);
        animator.start();
    }

    /* access modifiers changed from: package-private */
    public void moveToState(Fragment f) {
        moveToState(f, this.mCurState, 0, 0, false);
    }

    /* access modifiers changed from: package-private */
    public void ensureInflatedFragmentView(Fragment fragment) {
        Fragment f = fragment;
        if (f.mFromLayout && !f.mPerformedCreateView) {
            f.performCreateView(f.performGetLayoutInflater(f.mSavedFragmentState), (ViewGroup) null, f.mSavedFragmentState);
            if (f.mView != null) {
                f.mInnerView = f.mView;
                f.mView.setSaveFromParentEnabled(false);
                if (f.mHidden) {
                    f.mView.setVisibility(8);
                }
                f.onViewCreated(f.mView, f.mSavedFragmentState);
                dispatchOnFragmentViewCreated(f, f.mView, f.mSavedFragmentState, false);
                return;
            }
            f.mInnerView = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void completeShowHideFragment(Fragment fragment) {
        Animator.AnimatorListener animatorListener;
        Fragment fragment2 = fragment;
        if (fragment2.mView != null) {
            AnimationOrAnimator anim = loadAnimation(fragment2, fragment2.getNextTransition(), !fragment2.mHidden, fragment2.getNextTransitionStyle());
            if (anim == null || anim.animator == null) {
                if (anim != null) {
                    setHWLayerAnimListenerIfAlpha(fragment2.mView, anim);
                    fragment2.mView.startAnimation(anim.animation);
                    anim.animation.start();
                }
                fragment2.mView.setVisibility((!fragment2.mHidden || fragment2.isHideReplaced()) ? 0 : 8);
                if (fragment2.isHideReplaced()) {
                    fragment2.setHideReplaced(false);
                }
            } else {
                anim.animator.setTarget(fragment2.mView);
                if (!fragment2.mHidden) {
                    fragment2.mView.setVisibility(0);
                } else if (fragment2.isHideReplaced()) {
                    fragment2.setHideReplaced(false);
                } else {
                    ViewGroup container = fragment2.mContainer;
                    View animatingView = fragment2.mView;
                    container.startViewTransition(animatingView);
                    final ViewGroup viewGroup = container;
                    final View view = animatingView;
                    final Fragment fragment3 = fragment2;
                    new AnimatorListenerAdapter(this) {
                        final /* synthetic */ FragmentManagerImpl this$0;

                        {
                            this.this$0 = this$0;
                        }

                        public void onAnimationEnd(Animator animation) {
                            viewGroup.endViewTransition(view);
                            animation.removeListener(this);
                            if (fragment3.mView != null) {
                                fragment3.mView.setVisibility(8);
                            }
                        }
                    };
                    anim.animator.addListener(animatorListener);
                }
                setHWLayerAnimListenerIfAlpha(fragment2.mView, anim);
                anim.animator.start();
            }
        }
        if (fragment2.mAdded && fragment2.mHasMenu && fragment2.mMenuVisible) {
            this.mNeedMenuInvalidate = true;
        }
        fragment2.mHiddenChanged = false;
        fragment2.onHiddenChanged(fragment2.mHidden);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003d, code lost:
        r4 = r3.mView;
        r5 = r1.mContainer;
        r6 = r5.indexOfChild(r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void moveFragmentToExpectedState(android.support.p000v4.app.Fragment r15) {
        /*
            r14 = this;
            r0 = r14
            r1 = r15
            r8 = r1
            if (r8 != 0) goto L_0x0006
        L_0x0005:
            return
        L_0x0006:
            r8 = r0
            int r8 = r8.mCurState
            r2 = r8
            r8 = r1
            boolean r8 = r8.mRemoving
            if (r8 == 0) goto L_0x001d
            r8 = r1
            boolean r8 = r8.isInBackStack()
            if (r8 == 0) goto L_0x00bf
            r8 = r2
            r9 = 1
            int r8 = java.lang.Math.min(r8, r9)
            r2 = r8
        L_0x001d:
            r8 = r0
            r9 = r1
            r10 = r2
            r11 = r1
            int r11 = r11.getNextTransition()
            r12 = r1
            int r12 = r12.getNextTransitionStyle()
            r13 = 0
            r8.moveToState(r9, r10, r11, r12, r13)
            r8 = r1
            android.view.View r8 = r8.mView
            if (r8 == 0) goto L_0x00b3
            r8 = r0
            r9 = r1
            android.support.v4.app.Fragment r8 = r8.findFragmentUnder(r9)
            r3 = r8
            r8 = r3
            if (r8 == 0) goto L_0x0066
            r8 = r3
            android.view.View r8 = r8.mView
            r4 = r8
            r8 = r1
            android.view.ViewGroup r8 = r8.mContainer
            r5 = r8
            r8 = r5
            r9 = r4
            int r8 = r8.indexOfChild(r9)
            r6 = r8
            r8 = r5
            r9 = r1
            android.view.View r9 = r9.mView
            int r8 = r8.indexOfChild(r9)
            r7 = r8
            r8 = r7
            r9 = r6
            if (r8 >= r9) goto L_0x0066
            r8 = r5
            r9 = r7
            r8.removeViewAt(r9)
            r8 = r5
            r9 = r1
            android.view.View r9 = r9.mView
            r10 = r6
            r8.addView(r9, r10)
        L_0x0066:
            r8 = r1
            boolean r8 = r8.mIsNewlyAdded
            if (r8 == 0) goto L_0x00b3
            r8 = r1
            android.view.ViewGroup r8 = r8.mContainer
            if (r8 == 0) goto L_0x00b3
            r8 = r1
            float r8 = r8.mPostponedAlpha
            r9 = 0
            int r8 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
            if (r8 <= 0) goto L_0x0081
            r8 = r1
            android.view.View r8 = r8.mView
            r9 = r1
            float r9 = r9.mPostponedAlpha
            r8.setAlpha(r9)
        L_0x0081:
            r8 = r1
            r9 = 0
            r8.mPostponedAlpha = r9
            r8 = r1
            r9 = 0
            r8.mIsNewlyAdded = r9
            r8 = r0
            r9 = r1
            r10 = r1
            int r10 = r10.getNextTransition()
            r11 = 1
            r12 = r1
            int r12 = r12.getNextTransitionStyle()
            android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator r8 = r8.loadAnimation(r9, r10, r11, r12)
            r4 = r8
            r8 = r4
            if (r8 == 0) goto L_0x00b3
            r8 = r1
            android.view.View r8 = r8.mView
            r9 = r4
            setHWLayerAnimListenerIfAlpha(r8, r9)
            r8 = r4
            android.view.animation.Animation r8 = r8.animation
            if (r8 == 0) goto L_0x00c8
            r8 = r1
            android.view.View r8 = r8.mView
            r9 = r4
            android.view.animation.Animation r9 = r9.animation
            r8.startAnimation(r9)
        L_0x00b3:
            r8 = r1
            boolean r8 = r8.mHiddenChanged
            if (r8 == 0) goto L_0x00bd
            r8 = r0
            r9 = r1
            r8.completeShowHideFragment(r9)
        L_0x00bd:
            goto L_0x0005
        L_0x00bf:
            r8 = r2
            r9 = 0
            int r8 = java.lang.Math.min(r8, r9)
            r2 = r8
            goto L_0x001d
        L_0x00c8:
            r8 = r4
            android.animation.Animator r8 = r8.animator
            r9 = r1
            android.view.View r9 = r9.mView
            r8.setTarget(r9)
            r8 = r4
            android.animation.Animator r8 = r8.animator
            r8.start()
            goto L_0x00b3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentManagerImpl.moveFragmentToExpectedState(android.support.v4.app.Fragment):void");
    }

    /* access modifiers changed from: package-private */
    public void moveToState(int i, boolean z) {
        Throwable th;
        int newState = i;
        boolean always = z;
        if (this.mHost == null && newState != 0) {
            Throwable th2 = th;
            new IllegalStateException("No activity");
            throw th2;
        } else if (always || newState != this.mCurState) {
            this.mCurState = newState;
            if (this.mActive != null) {
                int numAdded = this.mAdded.size();
                for (int i2 = 0; i2 < numAdded; i2++) {
                    moveFragmentToExpectedState(this.mAdded.get(i2));
                }
                int numActive = this.mActive.size();
                for (int i3 = 0; i3 < numActive; i3++) {
                    Fragment f = this.mActive.valueAt(i3);
                    if (f != null && ((f.mRemoving || f.mDetached) && !f.mIsNewlyAdded)) {
                        moveFragmentToExpectedState(f);
                    }
                }
                startPendingDeferredFragments();
                if (this.mNeedMenuInvalidate && this.mHost != null && this.mCurState == 4) {
                    this.mHost.onSupportInvalidateOptionsMenu();
                    this.mNeedMenuInvalidate = false;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void startPendingDeferredFragments() {
        if (this.mActive != null) {
            for (int i = 0; i < this.mActive.size(); i++) {
                Fragment f = this.mActive.valueAt(i);
                if (f != null) {
                    performPendingDeferredStart(f);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void makeActive(Fragment fragment) {
        StringBuilder sb;
        SparseArray<Fragment> sparseArray;
        Fragment f = fragment;
        if (f.mIndex < 0) {
            int i = this.mNextFragmentIndex;
            int i2 = i + 1;
            this.mNextFragmentIndex = i2;
            f.setIndex(i, this.mParent);
            if (this.mActive == null) {
                new SparseArray<>();
                this.mActive = sparseArray;
            }
            this.mActive.put(f.mIndex, f);
            if (DEBUG) {
                new StringBuilder();
                int v = Log.v(TAG, sb.append("Allocated fragment index ").append(f).toString());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void makeInactive(Fragment fragment) {
        StringBuilder sb;
        Fragment f = fragment;
        if (f.mIndex >= 0) {
            if (DEBUG) {
                new StringBuilder();
                int v = Log.v(TAG, sb.append("Freeing fragment index ").append(f).toString());
            }
            this.mActive.put(f.mIndex, (Object) null);
            f.initState();
        }
    }

    /* JADX INFO: finally extract failed */
    public void addFragment(Fragment fragment, boolean z) {
        Throwable th;
        StringBuilder sb;
        StringBuilder sb2;
        Fragment fragment2 = fragment;
        boolean moveToStateNow = z;
        if (DEBUG) {
            new StringBuilder();
            int v = Log.v(TAG, sb2.append("add: ").append(fragment2).toString());
        }
        makeActive(fragment2);
        if (fragment2.mDetached) {
            return;
        }
        if (this.mAdded.contains(fragment2)) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Fragment already added: ").append(fragment2).toString());
            throw th2;
        }
        ArrayList<Fragment> arrayList = this.mAdded;
        ArrayList<Fragment> arrayList2 = arrayList;
        synchronized (arrayList) {
            try {
                boolean add = this.mAdded.add(fragment2);
                fragment2.mAdded = true;
                fragment2.mRemoving = false;
                if (fragment2.mView == null) {
                    fragment2.mHiddenChanged = false;
                }
                if (fragment2.mHasMenu && fragment2.mMenuVisible) {
                    this.mNeedMenuInvalidate = true;
                }
                if (moveToStateNow) {
                    moveToState(fragment2);
                }
            } catch (Throwable th3) {
                while (true) {
                    Throwable th4 = th3;
                    ArrayList<Fragment> arrayList3 = arrayList2;
                    throw th4;
                }
            }
        }
    }

    public void removeFragment(Fragment fragment) {
        StringBuilder sb;
        Fragment fragment2 = fragment;
        if (DEBUG) {
            new StringBuilder();
            int v = Log.v(TAG, sb.append("remove: ").append(fragment2).append(" nesting=").append(fragment2.mBackStackNesting).toString());
        }
        boolean inactive = !fragment2.isInBackStack();
        if (!fragment2.mDetached || inactive) {
            ArrayList<Fragment> arrayList = this.mAdded;
            ArrayList<Fragment> arrayList2 = arrayList;
            synchronized (arrayList) {
                try {
                    boolean remove = this.mAdded.remove(fragment2);
                    if (fragment2.mHasMenu && fragment2.mMenuVisible) {
                        this.mNeedMenuInvalidate = true;
                    }
                    fragment2.mAdded = false;
                    fragment2.mRemoving = true;
                } catch (Throwable th) {
                    while (true) {
                        Throwable th2 = th;
                        ArrayList<Fragment> arrayList3 = arrayList2;
                        throw th2;
                    }
                }
            }
        }
    }

    public void hideFragment(Fragment fragment) {
        StringBuilder sb;
        Fragment fragment2 = fragment;
        if (DEBUG) {
            new StringBuilder();
            int v = Log.v(TAG, sb.append("hide: ").append(fragment2).toString());
        }
        if (!fragment2.mHidden) {
            fragment2.mHidden = true;
            fragment2.mHiddenChanged = !fragment2.mHiddenChanged;
        }
    }

    public void showFragment(Fragment fragment) {
        StringBuilder sb;
        Fragment fragment2 = fragment;
        if (DEBUG) {
            new StringBuilder();
            int v = Log.v(TAG, sb.append("show: ").append(fragment2).toString());
        }
        if (fragment2.mHidden) {
            fragment2.mHidden = false;
            fragment2.mHiddenChanged = !fragment2.mHiddenChanged;
        }
    }

    public void detachFragment(Fragment fragment) {
        StringBuilder sb;
        StringBuilder sb2;
        Fragment fragment2 = fragment;
        if (DEBUG) {
            new StringBuilder();
            int v = Log.v(TAG, sb2.append("detach: ").append(fragment2).toString());
        }
        if (!fragment2.mDetached) {
            fragment2.mDetached = true;
            if (fragment2.mAdded) {
                if (DEBUG) {
                    new StringBuilder();
                    int v2 = Log.v(TAG, sb.append("remove from detach: ").append(fragment2).toString());
                }
                ArrayList<Fragment> arrayList = this.mAdded;
                ArrayList<Fragment> arrayList2 = arrayList;
                synchronized (arrayList) {
                    try {
                        boolean remove = this.mAdded.remove(fragment2);
                        if (fragment2.mHasMenu && fragment2.mMenuVisible) {
                            this.mNeedMenuInvalidate = true;
                        }
                        fragment2.mAdded = false;
                    } catch (Throwable th) {
                        while (true) {
                            Throwable th2 = th;
                            ArrayList<Fragment> arrayList3 = arrayList2;
                            throw th2;
                        }
                    }
                }
            }
        }
    }

    public void attachFragment(Fragment fragment) {
        StringBuilder sb;
        Throwable th;
        StringBuilder sb2;
        StringBuilder sb3;
        Fragment fragment2 = fragment;
        if (DEBUG) {
            new StringBuilder();
            int v = Log.v(TAG, sb3.append("attach: ").append(fragment2).toString());
        }
        if (fragment2.mDetached) {
            fragment2.mDetached = false;
            if (fragment2.mAdded) {
                return;
            }
            if (this.mAdded.contains(fragment2)) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalStateException(sb2.append("Fragment already added: ").append(fragment2).toString());
                throw th2;
            }
            if (DEBUG) {
                new StringBuilder();
                int v2 = Log.v(TAG, sb.append("add from attach: ").append(fragment2).toString());
            }
            ArrayList<Fragment> arrayList = this.mAdded;
            ArrayList<Fragment> arrayList2 = arrayList;
            synchronized (arrayList) {
                try {
                    boolean add = this.mAdded.add(fragment2);
                    fragment2.mAdded = true;
                    if (fragment2.mHasMenu && fragment2.mMenuVisible) {
                        this.mNeedMenuInvalidate = true;
                    }
                } catch (Throwable th3) {
                    while (true) {
                        Throwable th4 = th3;
                        ArrayList<Fragment> arrayList3 = arrayList2;
                        throw th4;
                    }
                }
            }
        }
    }

    @Nullable
    public Fragment findFragmentById(int i) {
        int id = i;
        for (int i2 = this.mAdded.size() - 1; i2 >= 0; i2--) {
            Fragment f = this.mAdded.get(i2);
            if (f != null && f.mFragmentId == id) {
                return f;
            }
        }
        if (this.mActive != null) {
            for (int i3 = this.mActive.size() - 1; i3 >= 0; i3--) {
                Fragment f2 = this.mActive.valueAt(i3);
                if (f2 != null && f2.mFragmentId == id) {
                    return f2;
                }
            }
        }
        return null;
    }

    @Nullable
    public Fragment findFragmentByTag(@Nullable String str) {
        String tag = str;
        if (tag != null) {
            for (int i = this.mAdded.size() - 1; i >= 0; i--) {
                Fragment f = this.mAdded.get(i);
                if (f != null && tag.equals(f.mTag)) {
                    return f;
                }
            }
        }
        if (!(this.mActive == null || tag == null)) {
            for (int i2 = this.mActive.size() - 1; i2 >= 0; i2--) {
                Fragment f2 = this.mActive.valueAt(i2);
                if (f2 != null && tag.equals(f2.mTag)) {
                    return f2;
                }
            }
        }
        return null;
    }

    public Fragment findFragmentByWho(String str) {
        String who = str;
        if (!(this.mActive == null || who == null)) {
            for (int i = this.mActive.size() - 1; i >= 0; i--) {
                Fragment f = this.mActive.valueAt(i);
                if (f != null) {
                    Fragment findFragmentByWho = f.findFragmentByWho(who);
                    Fragment f2 = findFragmentByWho;
                    if (findFragmentByWho != null) {
                        return f2;
                    }
                }
            }
        }
        return null;
    }

    private void checkStateLoss() {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        if (isStateSaved()) {
            Throwable th3 = th2;
            new IllegalStateException("Can not perform this action after onSaveInstanceState");
            throw th3;
        } else if (this.mNoTransactionsBecause != null) {
            Throwable th4 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Can not perform this action inside of ").append(this.mNoTransactionsBecause).toString());
            throw th4;
        }
    }

    public boolean isStateSaved() {
        return this.mStateSaved || this.mStopped;
    }

    public void enqueueAction(OpGenerator opGenerator, boolean z) {
        Throwable th;
        ArrayList<OpGenerator> arrayList;
        OpGenerator action = opGenerator;
        boolean allowStateLoss = z;
        if (!allowStateLoss) {
            checkStateLoss();
        }
        synchronized (this) {
            try {
                if (!this.mDestroyed && this.mHost != null) {
                    if (this.mPendingActions == null) {
                        new ArrayList();
                        this.mPendingActions = arrayList;
                    }
                    boolean add = this.mPendingActions.add(action);
                    scheduleCommit();
                } else if (allowStateLoss) {
                } else {
                    Throwable th2 = th;
                    new IllegalStateException("Activity has been destroyed");
                    throw th2;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                throw th4;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public void scheduleCommit() {
        synchronized (this) {
            try {
                boolean postponeReady = this.mPostponedTransactions != null && !this.mPostponedTransactions.isEmpty();
                boolean pendingReady = this.mPendingActions != null && this.mPendingActions.size() == 1;
                if (postponeReady || pendingReady) {
                    this.mHost.getHandler().removeCallbacks(this.mExecCommit);
                    boolean post = this.mHost.getHandler().post(this.mExecCommit);
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    public int allocBackStackIndex(BackStackRecord backStackRecord) {
        StringBuilder sb;
        ArrayList<BackStackRecord> arrayList;
        StringBuilder sb2;
        BackStackRecord bse = backStackRecord;
        synchronized (this) {
            try {
                if (this.mAvailBackStackIndices == null || this.mAvailBackStackIndices.size() <= 0) {
                    if (this.mBackStackIndices == null) {
                        new ArrayList();
                        this.mBackStackIndices = arrayList;
                    }
                    int index = this.mBackStackIndices.size();
                    if (DEBUG) {
                        new StringBuilder();
                        int v = Log.v(TAG, sb.append("Setting back stack index ").append(index).append(" to ").append(bse).toString());
                    }
                    boolean add = this.mBackStackIndices.add(bse);
                    int i = index;
                    return i;
                }
                int index2 = this.mAvailBackStackIndices.remove(this.mAvailBackStackIndices.size() - 1).intValue();
                if (DEBUG) {
                    new StringBuilder();
                    int v2 = Log.v(TAG, sb2.append("Adding back stack index ").append(index2).append(" with ").append(bse).toString());
                }
                BackStackRecord backStackRecord2 = this.mBackStackIndices.set(index2, bse);
                int i2 = index2;
                return i2;
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public void setBackStackIndex(int i, BackStackRecord backStackRecord) {
        StringBuilder sb;
        StringBuilder sb2;
        ArrayList<Integer> arrayList;
        StringBuilder sb3;
        ArrayList<BackStackRecord> arrayList2;
        int index = i;
        BackStackRecord bse = backStackRecord;
        synchronized (this) {
            try {
                if (this.mBackStackIndices == null) {
                    new ArrayList();
                    this.mBackStackIndices = arrayList2;
                }
                int N = this.mBackStackIndices.size();
                if (index < N) {
                    if (DEBUG) {
                        new StringBuilder();
                        int v = Log.v(TAG, sb3.append("Setting back stack index ").append(index).append(" to ").append(bse).toString());
                    }
                    BackStackRecord backStackRecord2 = this.mBackStackIndices.set(index, bse);
                } else {
                    while (N < index) {
                        boolean add = this.mBackStackIndices.add((Object) null);
                        if (this.mAvailBackStackIndices == null) {
                            new ArrayList();
                            this.mAvailBackStackIndices = arrayList;
                        }
                        if (DEBUG) {
                            new StringBuilder();
                            int v2 = Log.v(TAG, sb2.append("Adding available back stack index ").append(N).toString());
                        }
                        boolean add2 = this.mAvailBackStackIndices.add(Integer.valueOf(N));
                        N++;
                    }
                    if (DEBUG) {
                        new StringBuilder();
                        int v3 = Log.v(TAG, sb.append("Adding back stack index ").append(index).append(" with ").append(bse).toString());
                    }
                    boolean add3 = this.mBackStackIndices.add(bse);
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    public void freeBackStackIndex(int i) {
        StringBuilder sb;
        ArrayList<Integer> arrayList;
        int index = i;
        synchronized (this) {
            try {
                BackStackRecord backStackRecord = this.mBackStackIndices.set(index, (Object) null);
                if (this.mAvailBackStackIndices == null) {
                    new ArrayList();
                    this.mAvailBackStackIndices = arrayList;
                }
                if (DEBUG) {
                    new StringBuilder();
                    int v = Log.v(TAG, sb.append("Freeing back stack index ").append(index).toString());
                }
                boolean add = this.mAvailBackStackIndices.add(Integer.valueOf(index));
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    private void ensureExecReady(boolean z) {
        ArrayList<BackStackRecord> arrayList;
        ArrayList<Boolean> arrayList2;
        Throwable th;
        Throwable th2;
        Throwable th3;
        boolean allowStateLoss = z;
        if (this.mExecutingActions) {
            Throwable th4 = th3;
            new IllegalStateException("FragmentManager is already executing transactions");
            throw th4;
        } else if (this.mHost == null) {
            Throwable th5 = th2;
            new IllegalStateException("Fragment host has been destroyed");
            throw th5;
        } else if (Looper.myLooper() != this.mHost.getHandler().getLooper()) {
            Throwable th6 = th;
            new IllegalStateException("Must be called from main thread of fragment host");
            throw th6;
        } else {
            if (!allowStateLoss) {
                checkStateLoss();
            }
            if (this.mTmpRecords == null) {
                new ArrayList<>();
                this.mTmpRecords = arrayList;
                new ArrayList<>();
                this.mTmpIsPop = arrayList2;
            }
            this.mExecutingActions = true;
            try {
                executePostponedTransaction((ArrayList<BackStackRecord>) null, (ArrayList<Boolean>) null);
                this.mExecutingActions = false;
            } catch (Throwable th7) {
                Throwable th8 = th7;
                this.mExecutingActions = false;
                throw th8;
            }
        }
    }

    public void execSingleAction(OpGenerator opGenerator, boolean z) {
        OpGenerator action = opGenerator;
        boolean allowStateLoss = z;
        if (!allowStateLoss || (this.mHost != null && !this.mDestroyed)) {
            ensureExecReady(allowStateLoss);
            if (action.generateOps(this.mTmpRecords, this.mTmpIsPop)) {
                this.mExecutingActions = true;
                try {
                    removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
                    cleanupExec();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    cleanupExec();
                    throw th2;
                }
            }
            doPendingDeferredStart();
            burpActive();
        }
    }

    private void cleanupExec() {
        this.mExecutingActions = false;
        this.mTmpIsPop.clear();
        this.mTmpRecords.clear();
    }

    public boolean execPendingActions() {
        ensureExecReady(true);
        boolean z = false;
        while (true) {
            boolean didSomething = z;
            if (generateOpsForPendingActions(this.mTmpRecords, this.mTmpIsPop)) {
                this.mExecutingActions = true;
                try {
                    removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
                    cleanupExec();
                    z = true;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    cleanupExec();
                    throw th2;
                }
            } else {
                doPendingDeferredStart();
                burpActive();
                return didSomething;
            }
        }
    }

    private void executePostponedTransaction(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
        int index;
        ArrayList<BackStackRecord> records = arrayList;
        ArrayList<Boolean> isRecordPop = arrayList2;
        int numPostponed = this.mPostponedTransactions == null ? 0 : this.mPostponedTransactions.size();
        int i = 0;
        while (i < numPostponed) {
            StartEnterTransitionListener listener = this.mPostponedTransactions.get(i);
            if (records != null && !listener.mIsBack && (index = records.indexOf(listener.mRecord)) != -1 && isRecordPop.get(index).booleanValue()) {
                listener.cancelTransaction();
            } else if (listener.isReady() || (records != null && listener.mRecord.interactsWith(records, 0, records.size()))) {
                StartEnterTransitionListener remove = this.mPostponedTransactions.remove(i);
                i--;
                numPostponed--;
                if (records != null && !listener.mIsBack) {
                    int indexOf = records.indexOf(listener.mRecord);
                    int index2 = indexOf;
                    if (indexOf != -1 && isRecordPop.get(index2).booleanValue()) {
                        listener.cancelTransaction();
                    }
                }
                listener.completeTransaction();
            }
            i++;
        }
    }

    private void removeRedundantOperationsAndExecute(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
        Throwable th;
        ArrayList<BackStackRecord> records = arrayList;
        ArrayList<Boolean> isRecordPop = arrayList2;
        if (records != null && !records.isEmpty()) {
            if (isRecordPop == null || records.size() != isRecordPop.size()) {
                Throwable th2 = th;
                new IllegalStateException("Internal error with the back stack records");
                throw th2;
            }
            executePostponedTransaction(records, isRecordPop);
            int numRecords = records.size();
            int startIndex = 0;
            int recordNum = 0;
            while (recordNum < numRecords) {
                if (!records.get(recordNum).mReorderingAllowed) {
                    if (startIndex != recordNum) {
                        executeOpsTogether(records, isRecordPop, startIndex, recordNum);
                    }
                    int reorderingEnd = recordNum + 1;
                    if (isRecordPop.get(recordNum).booleanValue()) {
                        while (reorderingEnd < numRecords && isRecordPop.get(reorderingEnd).booleanValue() && !records.get(reorderingEnd).mReorderingAllowed) {
                            reorderingEnd++;
                        }
                    }
                    executeOpsTogether(records, isRecordPop, recordNum, reorderingEnd);
                    startIndex = reorderingEnd;
                    recordNum = reorderingEnd - 1;
                }
                recordNum++;
            }
            if (startIndex != numRecords) {
                executeOpsTogether(records, isRecordPop, startIndex, numRecords);
            }
        }
    }

    private void executeOpsTogether(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, int i, int i2) {
        C1643ArraySet arraySet;
        boolean z;
        ArrayList<Fragment> arrayList3;
        ArrayList<BackStackRecord> records = arrayList;
        ArrayList<Boolean> isRecordPop = arrayList2;
        int startIndex = i;
        int endIndex = i2;
        boolean allowReordering = records.get(startIndex).mReorderingAllowed;
        boolean addToBackStack = false;
        if (this.mTmpAddedFragments == null) {
            new ArrayList<>();
            this.mTmpAddedFragments = arrayList3;
        } else {
            this.mTmpAddedFragments.clear();
        }
        boolean addAll = this.mTmpAddedFragments.addAll(this.mAdded);
        Fragment oldPrimaryNav = getPrimaryNavigationFragment();
        for (int recordNum = startIndex; recordNum < endIndex; recordNum++) {
            BackStackRecord record = records.get(recordNum);
            if (!isRecordPop.get(recordNum).booleanValue()) {
                oldPrimaryNav = record.expandOps(this.mTmpAddedFragments, oldPrimaryNav);
            } else {
                oldPrimaryNav = record.trackAddedFragmentsInPop(this.mTmpAddedFragments, oldPrimaryNav);
            }
            if (addToBackStack || record.mAddToBackStack) {
                z = true;
            } else {
                z = false;
            }
            addToBackStack = z;
        }
        this.mTmpAddedFragments.clear();
        if (!allowReordering) {
            FragmentTransition.startTransitions(this, records, isRecordPop, startIndex, endIndex, false);
        }
        executeOps(records, isRecordPop, startIndex, endIndex);
        int postponeIndex = endIndex;
        if (allowReordering) {
            new C1643ArraySet();
            C1643ArraySet arraySet2 = arraySet;
            addAddedFragments(arraySet2);
            postponeIndex = postponePostponableTransactions(records, isRecordPop, startIndex, endIndex, arraySet2);
            makeRemovedFragmentsInvisible(arraySet2);
        }
        if (postponeIndex != startIndex && allowReordering) {
            FragmentTransition.startTransitions(this, records, isRecordPop, startIndex, postponeIndex, true);
            moveToState(this.mCurState, true);
        }
        for (int recordNum2 = startIndex; recordNum2 < endIndex; recordNum2++) {
            BackStackRecord record2 = records.get(recordNum2);
            if (isRecordPop.get(recordNum2).booleanValue() && record2.mIndex >= 0) {
                freeBackStackIndex(record2.mIndex);
                record2.mIndex = -1;
            }
            record2.runOnCommitRunnables();
        }
        if (addToBackStack) {
            reportBackStackChanged();
        }
    }

    private void makeRemovedFragmentsInvisible(C1643ArraySet<Fragment> arraySet) {
        C1643ArraySet<Fragment> fragments = arraySet;
        int numAdded = fragments.size();
        for (int i = 0; i < numAdded; i++) {
            Fragment fragment = fragments.valueAt(i);
            if (!fragment.mAdded) {
                View view = fragment.getView();
                fragment.mPostponedAlpha = view.getAlpha();
                view.setAlpha(0.0f);
            }
        }
    }

    private int postponePostponableTransactions(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, int i, int i2, C1643ArraySet<Fragment> arraySet) {
        StartEnterTransitionListener startEnterTransitionListener;
        ArrayList<StartEnterTransitionListener> arrayList3;
        ArrayList<BackStackRecord> records = arrayList;
        ArrayList<Boolean> isRecordPop = arrayList2;
        int startIndex = i;
        int endIndex = i2;
        C1643ArraySet<Fragment> added = arraySet;
        int postponeIndex = endIndex;
        for (int i3 = endIndex - 1; i3 >= startIndex; i3--) {
            BackStackRecord record = records.get(i3);
            boolean isPop = isRecordPop.get(i3).booleanValue();
            if (record.isPostponed() && !record.interactsWith(records, i3 + 1, endIndex)) {
                if (this.mPostponedTransactions == null) {
                    new ArrayList<>();
                    this.mPostponedTransactions = arrayList3;
                }
                new StartEnterTransitionListener(record, isPop);
                StartEnterTransitionListener listener = startEnterTransitionListener;
                boolean add = this.mPostponedTransactions.add(listener);
                record.setOnStartPostponedListener(listener);
                if (isPop) {
                    record.executeOps();
                } else {
                    record.executePopOps(false);
                }
                postponeIndex--;
                if (i3 != postponeIndex) {
                    BackStackRecord remove = records.remove(i3);
                    records.add(postponeIndex, record);
                }
                addAddedFragments(added);
            }
        }
        return postponeIndex;
    }

    /* access modifiers changed from: package-private */
    public void completeExecute(BackStackRecord backStackRecord, boolean z, boolean z2, boolean z3) {
        ArrayList arrayList;
        ArrayList arrayList2;
        BackStackRecord record = backStackRecord;
        boolean isPop = z;
        boolean runTransitions = z2;
        boolean moveToState = z3;
        if (isPop) {
            record.executePopOps(moveToState);
        } else {
            record.executeOps();
        }
        new ArrayList(1);
        ArrayList arrayList3 = arrayList;
        new ArrayList(1);
        ArrayList arrayList4 = arrayList2;
        boolean add = arrayList3.add(record);
        boolean add2 = arrayList4.add(Boolean.valueOf(isPop));
        if (runTransitions) {
            FragmentTransition.startTransitions(this, arrayList3, arrayList4, 0, 1, true);
        }
        if (moveToState) {
            moveToState(this.mCurState, true);
        }
        if (this.mActive != null) {
            int numActive = this.mActive.size();
            for (int i = 0; i < numActive; i++) {
                Fragment fragment = this.mActive.valueAt(i);
                if (fragment != null && fragment.mView != null && fragment.mIsNewlyAdded && record.interactsWith(fragment.mContainerId)) {
                    if (fragment.mPostponedAlpha > 0.0f) {
                        fragment.mView.setAlpha(fragment.mPostponedAlpha);
                    }
                    if (moveToState) {
                        fragment.mPostponedAlpha = 0.0f;
                    } else {
                        fragment.mPostponedAlpha = -1.0f;
                        fragment.mIsNewlyAdded = false;
                    }
                }
            }
        }
    }

    private Fragment findFragmentUnder(Fragment fragment) {
        Fragment f = fragment;
        ViewGroup container = f.mContainer;
        View view = f.mView;
        if (container == null || view == null) {
            return null;
        }
        for (int i = this.mAdded.indexOf(f) - 1; i >= 0; i--) {
            Fragment underFragment = this.mAdded.get(i);
            if (underFragment.mContainer == container && underFragment.mView != null) {
                return underFragment;
            }
        }
        return null;
    }

    private static void executeOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, int startIndex, int i) {
        ArrayList<BackStackRecord> records = arrayList;
        ArrayList<Boolean> isRecordPop = arrayList2;
        int endIndex = i;
        int i2 = startIndex;
        while (i2 < endIndex) {
            BackStackRecord record = records.get(i2);
            if (isRecordPop.get(i2).booleanValue()) {
                record.bumpBackStackNesting(-1);
                record.executePopOps(i2 == endIndex + -1);
            } else {
                record.bumpBackStackNesting(1);
                record.executeOps();
            }
            i2++;
        }
    }

    private void addAddedFragments(C1643ArraySet<Fragment> arraySet) {
        C1643ArraySet<Fragment> added = arraySet;
        if (this.mCurState >= 1) {
            int state = Math.min(this.mCurState, 3);
            int numAdded = this.mAdded.size();
            for (int i = 0; i < numAdded; i++) {
                Fragment fragment = this.mAdded.get(i);
                if (fragment.mState < state) {
                    moveToState(fragment, state, fragment.getNextAnim(), fragment.getNextTransition(), false);
                    if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded) {
                        boolean add = added.add(fragment);
                    }
                }
            }
        }
    }

    private void forcePostponedTransactions() {
        if (this.mPostponedTransactions != null) {
            while (!this.mPostponedTransactions.isEmpty()) {
                this.mPostponedTransactions.remove(0).completeTransaction();
            }
        }
    }

    private void endAnimatingAwayFragments() {
        int size;
        if (this.mActive == null) {
            size = 0;
        } else {
            size = this.mActive.size();
        }
        int numFragments = size;
        for (int i = 0; i < numFragments; i++) {
            Fragment fragment = this.mActive.valueAt(i);
            if (fragment != null) {
                if (fragment.getAnimatingAway() != null) {
                    int stateAfterAnimating = fragment.getStateAfterAnimating();
                    View animatingAway = fragment.getAnimatingAway();
                    Animation animation = animatingAway.getAnimation();
                    if (animation != null) {
                        animation.cancel();
                        animatingAway.clearAnimation();
                    }
                    fragment.setAnimatingAway((View) null);
                    moveToState(fragment, stateAfterAnimating, 0, 0, false);
                } else if (fragment.getAnimator() != null) {
                    fragment.getAnimator().end();
                }
            }
        }
    }

    /* JADX INFO: finally extract failed */
    private boolean generateOpsForPendingActions(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
        ArrayList<BackStackRecord> records = arrayList;
        ArrayList<Boolean> isPop = arrayList2;
        boolean didSomething = false;
        synchronized (this) {
            try {
                if (this.mPendingActions == null || this.mPendingActions.size() == 0) {
                    return false;
                }
                int numActions = this.mPendingActions.size();
                for (int i = 0; i < numActions; i++) {
                    didSomething |= this.mPendingActions.get(i).generateOps(records, isPop);
                }
                this.mPendingActions.clear();
                this.mHost.getHandler().removeCallbacks(this.mExecCommit);
                return didSomething;
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void doPendingDeferredStart() {
        if (this.mHavePendingDeferredStart) {
            this.mHavePendingDeferredStart = false;
            startPendingDeferredFragments();
        }
    }

    /* access modifiers changed from: package-private */
    public void reportBackStackChanged() {
        if (this.mBackStackChangeListeners != null) {
            for (int i = 0; i < this.mBackStackChangeListeners.size(); i++) {
                this.mBackStackChangeListeners.get(i).onBackStackChanged();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void addBackStackState(BackStackRecord backStackRecord) {
        ArrayList<BackStackRecord> arrayList;
        BackStackRecord state = backStackRecord;
        if (this.mBackStack == null) {
            new ArrayList<>();
            this.mBackStack = arrayList;
        }
        boolean add = this.mBackStack.add(state);
    }

    /* access modifiers changed from: package-private */
    public boolean popBackStackState(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, String str, int i, int i2) {
        ArrayList<BackStackRecord> records = arrayList;
        ArrayList<Boolean> isRecordPop = arrayList2;
        String name = str;
        int id = i;
        int flags = i2;
        if (this.mBackStack == null) {
            return false;
        }
        if (name == null && id < 0 && (flags & 1) == 0) {
            int last = this.mBackStack.size() - 1;
            if (last < 0) {
                return false;
            }
            boolean add = records.add(this.mBackStack.remove(last));
            boolean add2 = isRecordPop.add(true);
        } else {
            int index = -1;
            if (name != null || id >= 0) {
                index = this.mBackStack.size() - 1;
                while (index >= 0) {
                    BackStackRecord bss = this.mBackStack.get(index);
                    if ((name != null && name.equals(bss.getName())) || (id >= 0 && id == bss.mIndex)) {
                        break;
                    }
                    index--;
                }
                if (index < 0) {
                    return false;
                }
                if ((flags & 1) != 0) {
                    while (true) {
                        index--;
                        if (index < 0) {
                            break;
                        }
                        BackStackRecord bss2 = this.mBackStack.get(index);
                        if ((name == null || !name.equals(bss2.getName())) && (id < 0 || id != bss2.mIndex)) {
                            break;
                        }
                    }
                }
            }
            if (index == this.mBackStack.size() - 1) {
                return false;
            }
            for (int i3 = this.mBackStack.size() - 1; i3 > index; i3--) {
                boolean add3 = records.add(this.mBackStack.remove(i3));
                boolean add4 = isRecordPop.add(true);
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public FragmentManagerNonConfig retainNonConfig() {
        setRetaining(this.mSavedNonConfig);
        return this.mSavedNonConfig;
    }

    private static void setRetaining(FragmentManagerNonConfig fragmentManagerNonConfig) {
        FragmentManagerNonConfig nonConfig = fragmentManagerNonConfig;
        if (nonConfig != null) {
            List<Fragment> fragments = nonConfig.getFragments();
            if (fragments != null) {
                for (Fragment fragment : fragments) {
                    fragment.mRetaining = true;
                }
            }
            List<FragmentManagerNonConfig> children = nonConfig.getChildNonConfigs();
            if (children != null) {
                for (FragmentManagerNonConfig child : children) {
                    setRetaining(child);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void saveNonConfig() {
        FragmentManagerNonConfig fragmentManagerNonConfig;
        FragmentManagerNonConfig child;
        ArrayList arrayList;
        ArrayList arrayList2;
        StringBuilder sb;
        ArrayList arrayList3;
        ArrayList arrayList4 = null;
        ArrayList arrayList5 = null;
        ArrayList arrayList6 = null;
        if (this.mActive != null) {
            for (int i = 0; i < this.mActive.size(); i++) {
                Fragment f = this.mActive.valueAt(i);
                if (f != null) {
                    if (f.mRetainInstance) {
                        if (arrayList4 == null) {
                            new ArrayList();
                            arrayList4 = arrayList3;
                        }
                        boolean add = arrayList4.add(f);
                        f.mTargetIndex = f.mTarget != null ? f.mTarget.mIndex : -1;
                        if (DEBUG) {
                            new StringBuilder();
                            int v = Log.v(TAG, sb.append("retainNonConfig: keeping retained ").append(f).toString());
                        }
                    }
                    if (f.mChildFragmentManager != null) {
                        f.mChildFragmentManager.saveNonConfig();
                        child = f.mChildFragmentManager.mSavedNonConfig;
                    } else {
                        child = f.mChildNonConfig;
                    }
                    if (arrayList5 == null && child != null) {
                        new ArrayList(this.mActive.size());
                        arrayList5 = arrayList2;
                        for (int j = 0; j < i; j++) {
                            boolean add2 = arrayList5.add((Object) null);
                        }
                    }
                    if (arrayList5 != null) {
                        boolean add3 = arrayList5.add(child);
                    }
                    if (arrayList6 == null && f.mViewModelStore != null) {
                        new ArrayList(this.mActive.size());
                        arrayList6 = arrayList;
                        for (int j2 = 0; j2 < i; j2++) {
                            boolean add4 = arrayList6.add((Object) null);
                        }
                    }
                    if (arrayList6 != null) {
                        boolean add5 = arrayList6.add(f.mViewModelStore);
                    }
                }
            }
        }
        if (arrayList4 == null && arrayList5 == null && arrayList6 == null) {
            this.mSavedNonConfig = null;
            return;
        }
        new FragmentManagerNonConfig(arrayList4, arrayList5, arrayList6);
        this.mSavedNonConfig = fragmentManagerNonConfig;
    }

    /* access modifiers changed from: package-private */
    public void saveFragmentViewState(Fragment fragment) {
        SparseArray<Parcelable> sparseArray;
        Fragment f = fragment;
        if (f.mInnerView != null) {
            if (this.mStateArray == null) {
                new SparseArray<>();
                this.mStateArray = sparseArray;
            } else {
                this.mStateArray.clear();
            }
            f.mInnerView.saveHierarchyState(this.mStateArray);
            if (this.mStateArray.size() > 0) {
                f.mSavedViewState = this.mStateArray;
                this.mStateArray = null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Bundle saveFragmentBasicState(Fragment fragment) {
        Bundle bundle;
        Bundle bundle2;
        Bundle bundle3;
        Fragment f = fragment;
        Bundle result = null;
        if (this.mStateBundle == null) {
            new Bundle();
            this.mStateBundle = bundle3;
        }
        f.performSaveInstanceState(this.mStateBundle);
        dispatchOnFragmentSaveInstanceState(f, this.mStateBundle, false);
        if (!this.mStateBundle.isEmpty()) {
            result = this.mStateBundle;
            this.mStateBundle = null;
        }
        if (f.mView != null) {
            saveFragmentViewState(f);
        }
        if (f.mSavedViewState != null) {
            if (result == null) {
                new Bundle();
                result = bundle2;
            }
            result.putSparseParcelableArray(VIEW_STATE_TAG, f.mSavedViewState);
        }
        if (!f.mUserVisibleHint) {
            if (result == null) {
                new Bundle();
                result = bundle;
            }
            result.putBoolean(USER_VISIBLE_HINT_TAG, f.mUserVisibleHint);
        }
        return result;
    }

    /* access modifiers changed from: package-private */
    public Parcelable saveAllState() {
        FragmentManagerState fragmentManagerState;
        int N;
        BackStackState backStackState;
        StringBuilder sb;
        StringBuilder sb2;
        RuntimeException runtimeException;
        StringBuilder sb3;
        FragmentState fragmentState;
        StringBuilder sb4;
        Bundle bundle;
        RuntimeException runtimeException2;
        StringBuilder sb5;
        RuntimeException runtimeException3;
        StringBuilder sb6;
        forcePostponedTransactions();
        endAnimatingAwayFragments();
        boolean execPendingActions = execPendingActions();
        this.mStateSaved = true;
        this.mSavedNonConfig = null;
        if (this.mActive == null || this.mActive.size() <= 0) {
            return null;
        }
        int N2 = this.mActive.size();
        FragmentState[] active = new FragmentState[N2];
        boolean haveFragments = false;
        for (int i = 0; i < N2; i++) {
            Fragment f = this.mActive.valueAt(i);
            if (f != null) {
                if (f.mIndex < 0) {
                    new StringBuilder();
                    new IllegalStateException(sb6.append("Failure saving state: active ").append(f).append(" has cleared index: ").append(f.mIndex).toString());
                    throwException(runtimeException3);
                }
                haveFragments = true;
                new FragmentState(f);
                FragmentState fs = fragmentState;
                active[i] = fs;
                if (f.mState <= 0 || fs.mSavedFragmentState != null) {
                    fs.mSavedFragmentState = f.mSavedFragmentState;
                } else {
                    fs.mSavedFragmentState = saveFragmentBasicState(f);
                    if (f.mTarget != null) {
                        if (f.mTarget.mIndex < 0) {
                            new StringBuilder();
                            new IllegalStateException(sb5.append("Failure saving state: ").append(f).append(" has target not in fragment manager: ").append(f.mTarget).toString());
                            throwException(runtimeException2);
                        }
                        if (fs.mSavedFragmentState == null) {
                            new Bundle();
                            fs.mSavedFragmentState = bundle;
                        }
                        putFragment(fs.mSavedFragmentState, TARGET_STATE_TAG, f.mTarget);
                        if (f.mTargetRequestCode != 0) {
                            fs.mSavedFragmentState.putInt(TARGET_REQUEST_CODE_STATE_TAG, f.mTargetRequestCode);
                        }
                    }
                }
                if (DEBUG) {
                    new StringBuilder();
                    int v = Log.v(TAG, sb4.append("Saved state of ").append(f).append(": ").append(fs.mSavedFragmentState).toString());
                }
            }
        }
        if (!haveFragments) {
            if (DEBUG) {
                int v2 = Log.v(TAG, "saveAllState: no fragments!");
            }
            return null;
        }
        int[] added = null;
        BackStackState[] backStack = null;
        int N3 = this.mAdded.size();
        if (N3 > 0) {
            added = new int[N3];
            for (int i2 = 0; i2 < N3; i2++) {
                added[i2] = this.mAdded.get(i2).mIndex;
                if (added[i2] < 0) {
                    new StringBuilder();
                    new IllegalStateException(sb3.append("Failure saving state: active ").append(this.mAdded.get(i2)).append(" has cleared index: ").append(added[i2]).toString());
                    throwException(runtimeException);
                }
                if (DEBUG) {
                    new StringBuilder();
                    int v3 = Log.v(TAG, sb2.append("saveAllState: adding fragment #").append(i2).append(": ").append(this.mAdded.get(i2)).toString());
                }
            }
        }
        if (this.mBackStack != null && (N = this.mBackStack.size()) > 0) {
            backStack = new BackStackState[N];
            for (int i3 = 0; i3 < N; i3++) {
                new BackStackState(this.mBackStack.get(i3));
                backStack[i3] = backStackState;
                if (DEBUG) {
                    new StringBuilder();
                    int v4 = Log.v(TAG, sb.append("saveAllState: adding back stack #").append(i3).append(": ").append(this.mBackStack.get(i3)).toString());
                }
            }
        }
        new FragmentManagerState();
        FragmentManagerState fms = fragmentManagerState;
        fms.mActive = active;
        fms.mAdded = added;
        fms.mBackStack = backStack;
        if (this.mPrimaryNav != null) {
            fms.mPrimaryNavActiveIndex = this.mPrimaryNav.mIndex;
        }
        fms.mNextFragmentIndex = this.mNextFragmentIndex;
        saveNonConfig();
        return fms;
    }

    /* access modifiers changed from: package-private */
    public void restoreAllState(Parcelable parcelable, FragmentManagerNonConfig fragmentManagerNonConfig) {
        SparseArray<Fragment> sparseArray;
        ArrayList<BackStackRecord> arrayList;
        StringBuilder sb;
        LogWriter logw;
        PrintWriter printWriter;
        Throwable th;
        StringBuilder sb2;
        RuntimeException runtimeException;
        StringBuilder sb3;
        StringBuilder sb4;
        StringBuilder sb5;
        RuntimeException runtimeException2;
        StringBuilder sb6;
        StringBuilder sb7;
        Parcelable state = parcelable;
        FragmentManagerNonConfig nonConfig = fragmentManagerNonConfig;
        if (state != null) {
            FragmentManagerState fms = (FragmentManagerState) state;
            if (fms.mActive != null) {
                List<FragmentManagerNonConfig> childNonConfigs = null;
                List<ViewModelStore> viewModelStores = null;
                if (nonConfig != null) {
                    List<Fragment> nonConfigFragments = nonConfig.getFragments();
                    childNonConfigs = nonConfig.getChildNonConfigs();
                    viewModelStores = nonConfig.getViewModelStores();
                    int count = nonConfigFragments != null ? nonConfigFragments.size() : 0;
                    for (int i = 0; i < count; i++) {
                        Fragment f = nonConfigFragments.get(i);
                        if (DEBUG) {
                            new StringBuilder();
                            int v = Log.v(TAG, sb7.append("restoreAllState: re-attaching retained ").append(f).toString());
                        }
                        int index = 0;
                        while (index < fms.mActive.length && fms.mActive[index].mIndex != f.mIndex) {
                            index++;
                        }
                        if (index == fms.mActive.length) {
                            new StringBuilder();
                            new IllegalStateException(sb6.append("Could not find active fragment with index ").append(f.mIndex).toString());
                            throwException(runtimeException2);
                        }
                        FragmentState fs = fms.mActive[index];
                        fs.mInstance = f;
                        f.mSavedViewState = null;
                        f.mBackStackNesting = 0;
                        f.mInLayout = false;
                        f.mAdded = false;
                        f.mTarget = null;
                        if (fs.mSavedFragmentState != null) {
                            fs.mSavedFragmentState.setClassLoader(this.mHost.getContext().getClassLoader());
                            f.mSavedViewState = fs.mSavedFragmentState.getSparseParcelableArray(VIEW_STATE_TAG);
                            f.mSavedFragmentState = fs.mSavedFragmentState;
                        }
                    }
                }
                new SparseArray<>(fms.mActive.length);
                this.mActive = sparseArray;
                for (int i2 = 0; i2 < fms.mActive.length; i2++) {
                    FragmentState fs2 = fms.mActive[i2];
                    if (fs2 != null) {
                        FragmentManagerNonConfig childNonConfig = null;
                        if (childNonConfigs != null && i2 < childNonConfigs.size()) {
                            childNonConfig = childNonConfigs.get(i2);
                        }
                        ViewModelStore viewModelStore = null;
                        if (viewModelStores != null && i2 < viewModelStores.size()) {
                            viewModelStore = viewModelStores.get(i2);
                        }
                        Fragment f2 = fs2.instantiate(this.mHost, this.mContainer, this.mParent, childNonConfig, viewModelStore);
                        if (DEBUG) {
                            new StringBuilder();
                            int v2 = Log.v(TAG, sb5.append("restoreAllState: active #").append(i2).append(": ").append(f2).toString());
                        }
                        this.mActive.put(f2.mIndex, f2);
                        fs2.mInstance = null;
                    }
                }
                if (nonConfig != null) {
                    List<Fragment> nonConfigFragments2 = nonConfig.getFragments();
                    int count2 = nonConfigFragments2 != null ? nonConfigFragments2.size() : 0;
                    for (int i3 = 0; i3 < count2; i3++) {
                        Fragment f3 = nonConfigFragments2.get(i3);
                        if (f3.mTargetIndex >= 0) {
                            f3.mTarget = this.mActive.get(f3.mTargetIndex);
                            if (f3.mTarget == null) {
                                new StringBuilder();
                                int w = Log.w(TAG, sb4.append("Re-attaching retained fragment ").append(f3).append(" target no longer exists: ").append(f3.mTargetIndex).toString());
                            }
                        }
                    }
                }
                this.mAdded.clear();
                if (fms.mAdded != null) {
                    int i4 = 0;
                    while (i4 < fms.mAdded.length) {
                        Fragment f4 = this.mActive.get(fms.mAdded[i4]);
                        if (f4 == null) {
                            new StringBuilder();
                            new IllegalStateException(sb3.append("No instantiated fragment for index #").append(fms.mAdded[i4]).toString());
                            throwException(runtimeException);
                        }
                        f4.mAdded = true;
                        if (DEBUG) {
                            new StringBuilder();
                            int v3 = Log.v(TAG, sb2.append("restoreAllState: added #").append(i4).append(": ").append(f4).toString());
                        }
                        if (this.mAdded.contains(f4)) {
                            Throwable th2 = th;
                            new IllegalStateException("Already added!");
                            throw th2;
                        }
                        ArrayList<Fragment> arrayList2 = this.mAdded;
                        ArrayList<Fragment> arrayList3 = arrayList2;
                        synchronized (arrayList2) {
                            try {
                                boolean add = this.mAdded.add(f4);
                                i4++;
                            } catch (Throwable th3) {
                                Throwable th4 = th3;
                                ArrayList<Fragment> arrayList4 = arrayList3;
                                throw th4;
                            }
                        }
                    }
                }
                if (fms.mBackStack != null) {
                    new ArrayList<>(fms.mBackStack.length);
                    this.mBackStack = arrayList;
                    for (int i5 = 0; i5 < fms.mBackStack.length; i5++) {
                        BackStackRecord bse = fms.mBackStack[i5].instantiate(this);
                        if (DEBUG) {
                            new StringBuilder();
                            int v4 = Log.v(TAG, sb.append("restoreAllState: back stack #").append(i5).append(" (index ").append(bse.mIndex).append("): ").append(bse).toString());
                            new LogWriter(TAG);
                            new PrintWriter(logw);
                            PrintWriter pw = printWriter;
                            bse.dump("  ", pw, false);
                            pw.close();
                        }
                        boolean add2 = this.mBackStack.add(bse);
                        if (bse.mIndex >= 0) {
                            setBackStackIndex(bse.mIndex, bse);
                        }
                    }
                } else {
                    this.mBackStack = null;
                }
                if (fms.mPrimaryNavActiveIndex >= 0) {
                    this.mPrimaryNav = this.mActive.get(fms.mPrimaryNavActiveIndex);
                }
                this.mNextFragmentIndex = fms.mNextFragmentIndex;
            }
        }
    }

    private void burpActive() {
        if (this.mActive != null) {
            for (int i = this.mActive.size() - 1; i >= 0; i--) {
                if (this.mActive.valueAt(i) == null) {
                    this.mActive.delete(this.mActive.keyAt(i));
                }
            }
        }
    }

    public void attachController(FragmentHostCallback fragmentHostCallback, FragmentContainer fragmentContainer, Fragment fragment) {
        Throwable th;
        FragmentHostCallback host = fragmentHostCallback;
        FragmentContainer container = fragmentContainer;
        Fragment parent = fragment;
        if (this.mHost != null) {
            Throwable th2 = th;
            new IllegalStateException("Already attached");
            throw th2;
        }
        this.mHost = host;
        this.mContainer = container;
        this.mParent = parent;
    }

    public void noteStateNotSaved() {
        this.mSavedNonConfig = null;
        this.mStateSaved = false;
        this.mStopped = false;
        int addedCount = this.mAdded.size();
        for (int i = 0; i < addedCount; i++) {
            Fragment fragment = this.mAdded.get(i);
            if (fragment != null) {
                fragment.noteStateNotSaved();
            }
        }
    }

    public void dispatchCreate() {
        this.mStateSaved = false;
        this.mStopped = false;
        dispatchStateChange(1);
    }

    public void dispatchActivityCreated() {
        this.mStateSaved = false;
        this.mStopped = false;
        dispatchStateChange(2);
    }

    public void dispatchStart() {
        this.mStateSaved = false;
        this.mStopped = false;
        dispatchStateChange(3);
    }

    public void dispatchResume() {
        this.mStateSaved = false;
        this.mStopped = false;
        dispatchStateChange(4);
    }

    public void dispatchPause() {
        dispatchStateChange(3);
    }

    public void dispatchStop() {
        this.mStopped = true;
        dispatchStateChange(2);
    }

    public void dispatchDestroyView() {
        dispatchStateChange(1);
    }

    public void dispatchDestroy() {
        this.mDestroyed = true;
        boolean execPendingActions = execPendingActions();
        dispatchStateChange(0);
        this.mHost = null;
        this.mContainer = null;
        this.mParent = null;
    }

    private void dispatchStateChange(int i) {
        int nextState = i;
        try {
            this.mExecutingActions = true;
            moveToState(nextState, false);
            this.mExecutingActions = false;
            boolean execPendingActions = execPendingActions();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.mExecutingActions = false;
            throw th2;
        }
    }

    public void dispatchMultiWindowModeChanged(boolean z) {
        boolean isInMultiWindowMode = z;
        for (int i = this.mAdded.size() - 1; i >= 0; i--) {
            Fragment f = this.mAdded.get(i);
            if (f != null) {
                f.performMultiWindowModeChanged(isInMultiWindowMode);
            }
        }
    }

    public void dispatchPictureInPictureModeChanged(boolean z) {
        boolean isInPictureInPictureMode = z;
        for (int i = this.mAdded.size() - 1; i >= 0; i--) {
            Fragment f = this.mAdded.get(i);
            if (f != null) {
                f.performPictureInPictureModeChanged(isInPictureInPictureMode);
            }
        }
    }

    public void dispatchConfigurationChanged(Configuration configuration) {
        Configuration newConfig = configuration;
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment f = this.mAdded.get(i);
            if (f != null) {
                f.performConfigurationChanged(newConfig);
            }
        }
    }

    public void dispatchLowMemory() {
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment f = this.mAdded.get(i);
            if (f != null) {
                f.performLowMemory();
            }
        }
    }

    public boolean dispatchCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        ArrayList<Fragment> arrayList;
        Menu menu2 = menu;
        MenuInflater inflater = menuInflater;
        if (this.mCurState < 1) {
            return false;
        }
        boolean show = false;
        ArrayList<Fragment> newMenus = null;
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment f = this.mAdded.get(i);
            if (f != null && f.performCreateOptionsMenu(menu2, inflater)) {
                show = true;
                if (newMenus == null) {
                    new ArrayList<>();
                    newMenus = arrayList;
                }
                boolean add = newMenus.add(f);
            }
        }
        if (this.mCreatedMenus != null) {
            for (int i2 = 0; i2 < this.mCreatedMenus.size(); i2++) {
                Fragment f2 = this.mCreatedMenus.get(i2);
                if (newMenus == null || !newMenus.contains(f2)) {
                    f2.onDestroyOptionsMenu();
                }
            }
        }
        this.mCreatedMenus = newMenus;
        return show;
    }

    public boolean dispatchPrepareOptionsMenu(Menu menu) {
        Menu menu2 = menu;
        if (this.mCurState < 1) {
            return false;
        }
        boolean show = false;
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment f = this.mAdded.get(i);
            if (f != null && f.performPrepareOptionsMenu(menu2)) {
                show = true;
            }
        }
        return show;
    }

    public boolean dispatchOptionsItemSelected(MenuItem menuItem) {
        MenuItem item = menuItem;
        if (this.mCurState < 1) {
            return false;
        }
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment f = this.mAdded.get(i);
            if (f != null && f.performOptionsItemSelected(item)) {
                return true;
            }
        }
        return false;
    }

    public boolean dispatchContextItemSelected(MenuItem menuItem) {
        MenuItem item = menuItem;
        if (this.mCurState < 1) {
            return false;
        }
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment f = this.mAdded.get(i);
            if (f != null && f.performContextItemSelected(item)) {
                return true;
            }
        }
        return false;
    }

    public void dispatchOptionsMenuClosed(Menu menu) {
        Menu menu2 = menu;
        if (this.mCurState >= 1) {
            for (int i = 0; i < this.mAdded.size(); i++) {
                Fragment f = this.mAdded.get(i);
                if (f != null) {
                    f.performOptionsMenuClosed(menu2);
                }
            }
        }
    }

    public void setPrimaryNavigationFragment(Fragment fragment) {
        Throwable th;
        StringBuilder sb;
        Fragment f = fragment;
        if (f == null || (this.mActive.get(f.mIndex) == f && (f.mHost == null || f.getFragmentManager() == this))) {
            this.mPrimaryNav = f;
            return;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Fragment ").append(f).append(" is not an active fragment of FragmentManager ").append(this).toString());
        throw th2;
    }

    @Nullable
    public Fragment getPrimaryNavigationFragment() {
        return this.mPrimaryNav;
    }

    public void registerFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks cb, boolean recursive) {
        Object obj;
        new FragmentLifecycleCallbacksHolder(cb, recursive);
        boolean add = this.mLifecycleCallbacks.add(obj);
    }

    public void unregisterFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks) {
        FragmentManager.FragmentLifecycleCallbacks cb = fragmentLifecycleCallbacks;
        CopyOnWriteArrayList<FragmentLifecycleCallbacksHolder> copyOnWriteArrayList = this.mLifecycleCallbacks;
        CopyOnWriteArrayList<FragmentLifecycleCallbacksHolder> copyOnWriteArrayList2 = copyOnWriteArrayList;
        synchronized (copyOnWriteArrayList) {
            int i = 0;
            try {
                int N = this.mLifecycleCallbacks.size();
                while (true) {
                    if (i >= N) {
                        break;
                    } else if (this.mLifecycleCallbacks.get(i).mCallback == cb) {
                        FragmentLifecycleCallbacksHolder remove = this.mLifecycleCallbacks.remove(i);
                        break;
                    } else {
                        i++;
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                CopyOnWriteArrayList<FragmentLifecycleCallbacksHolder> copyOnWriteArrayList3 = copyOnWriteArrayList2;
                throw th2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentPreAttached(@NonNull Fragment fragment, @NonNull Context context, boolean z) {
        Fragment f = fragment;
        Context context2 = context;
        boolean onlyRecursive = z;
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentPreAttached(f, context2, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentPreAttached(this, f, context2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentAttached(@NonNull Fragment fragment, @NonNull Context context, boolean z) {
        Fragment f = fragment;
        Context context2 = context;
        boolean onlyRecursive = z;
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentAttached(f, context2, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentAttached(this, f, context2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentPreCreated(@NonNull Fragment fragment, @Nullable Bundle bundle, boolean z) {
        Fragment f = fragment;
        Bundle savedInstanceState = bundle;
        boolean onlyRecursive = z;
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentPreCreated(f, savedInstanceState, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentPreCreated(this, f, savedInstanceState);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentCreated(@NonNull Fragment fragment, @Nullable Bundle bundle, boolean z) {
        Fragment f = fragment;
        Bundle savedInstanceState = bundle;
        boolean onlyRecursive = z;
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentCreated(f, savedInstanceState, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentCreated(this, f, savedInstanceState);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentActivityCreated(@NonNull Fragment fragment, @Nullable Bundle bundle, boolean z) {
        Fragment f = fragment;
        Bundle savedInstanceState = bundle;
        boolean onlyRecursive = z;
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentActivityCreated(f, savedInstanceState, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentActivityCreated(this, f, savedInstanceState);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentViewCreated(@NonNull Fragment fragment, @NonNull View view, @Nullable Bundle bundle, boolean z) {
        Fragment f = fragment;
        View v = view;
        Bundle savedInstanceState = bundle;
        boolean onlyRecursive = z;
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentViewCreated(f, v, savedInstanceState, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentViewCreated(this, f, v, savedInstanceState);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentStarted(@NonNull Fragment fragment, boolean z) {
        Fragment f = fragment;
        boolean onlyRecursive = z;
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentStarted(f, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentStarted(this, f);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentResumed(@NonNull Fragment fragment, boolean z) {
        Fragment f = fragment;
        boolean onlyRecursive = z;
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentResumed(f, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentResumed(this, f);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentPaused(@NonNull Fragment fragment, boolean z) {
        Fragment f = fragment;
        boolean onlyRecursive = z;
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentPaused(f, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentPaused(this, f);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentStopped(@NonNull Fragment fragment, boolean z) {
        Fragment f = fragment;
        boolean onlyRecursive = z;
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentStopped(f, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentStopped(this, f);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentSaveInstanceState(@NonNull Fragment fragment, @NonNull Bundle bundle, boolean z) {
        Fragment f = fragment;
        Bundle outState = bundle;
        boolean onlyRecursive = z;
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentSaveInstanceState(f, outState, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentSaveInstanceState(this, f, outState);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentViewDestroyed(@NonNull Fragment fragment, boolean z) {
        Fragment f = fragment;
        boolean onlyRecursive = z;
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentViewDestroyed(f, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentViewDestroyed(this, f);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentDestroyed(@NonNull Fragment fragment, boolean z) {
        Fragment f = fragment;
        boolean onlyRecursive = z;
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentDestroyed(f, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentDestroyed(this, f);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentDetached(@NonNull Fragment fragment, boolean z) {
        Fragment f = fragment;
        boolean onlyRecursive = z;
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentDetached(f, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentDetached(this, f);
            }
        }
    }

    public static int reverseTransit(int transit) {
        int rev = 0;
        switch (transit) {
            case FragmentTransaction.TRANSIT_FRAGMENT_OPEN:
                rev = 8194;
                break;
            case FragmentTransaction.TRANSIT_FRAGMENT_FADE:
                rev = 4099;
                break;
            case 8194:
                rev = 4097;
                break;
        }
        return rev;
    }

    public static int transitToStyleIndex(int transit, boolean z) {
        boolean enter = z;
        int animAttr = -1;
        switch (transit) {
            case FragmentTransaction.TRANSIT_FRAGMENT_OPEN:
                animAttr = enter ? 1 : 2;
                break;
            case FragmentTransaction.TRANSIT_FRAGMENT_FADE:
                animAttr = enter ? 5 : 6;
                break;
            case 8194:
                animAttr = enter ? 3 : 4;
                break;
        }
        return animAttr;
    }

    public View onCreateView(View view, String name, Context context, AttributeSet attributeSet) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        StringBuilder sb3;
        Throwable th3;
        StringBuilder sb4;
        View parent = view;
        Context context2 = context;
        AttributeSet attrs = attributeSet;
        if (!"fragment".equals(name)) {
            return null;
        }
        String fname = attrs.getAttributeValue((String) null, "class");
        TypedArray a = context2.obtainStyledAttributes(attrs, FragmentTag.Fragment);
        if (fname == null) {
            fname = a.getString(0);
        }
        int id = a.getResourceId(1, -1);
        String tag = a.getString(2);
        a.recycle();
        if (!Fragment.isSupportFragmentClass(this.mHost.getContext(), fname)) {
            return null;
        }
        int containerId = parent != null ? parent.getId() : 0;
        if (containerId == -1 && id == -1 && tag == null) {
            Throwable th4 = th3;
            new StringBuilder();
            new IllegalArgumentException(sb4.append(attrs.getPositionDescription()).append(": Must specify unique android:id, android:tag, or have a parent with an id for ").append(fname).toString());
            throw th4;
        }
        Fragment fragment = id != -1 ? findFragmentById(id) : null;
        if (fragment == null && tag != null) {
            fragment = findFragmentByTag(tag);
        }
        if (fragment == null && containerId != -1) {
            fragment = findFragmentById(containerId);
        }
        if (DEBUG) {
            new StringBuilder();
            int v = Log.v(TAG, sb3.append("onCreateView: id=0x").append(Integer.toHexString(id)).append(" fname=").append(fname).append(" existing=").append(fragment).toString());
        }
        if (fragment == null) {
            fragment = this.mContainer.instantiate(context2, fname, (Bundle) null);
            fragment.mFromLayout = true;
            fragment.mFragmentId = id != 0 ? id : containerId;
            fragment.mContainerId = containerId;
            fragment.mTag = tag;
            fragment.mInLayout = true;
            fragment.mFragmentManager = this;
            fragment.mHost = this.mHost;
            fragment.onInflate(this.mHost.getContext(), attrs, fragment.mSavedFragmentState);
            addFragment(fragment, true);
        } else if (fragment.mInLayout) {
            Throwable th5 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append(attrs.getPositionDescription()).append(": Duplicate id 0x").append(Integer.toHexString(id)).append(", tag ").append(tag).append(", or parent id 0x").append(Integer.toHexString(containerId)).append(" with another fragment for ").append(fname).toString());
            throw th5;
        } else {
            fragment.mInLayout = true;
            fragment.mHost = this.mHost;
            if (!fragment.mRetaining) {
                fragment.onInflate(this.mHost.getContext(), attrs, fragment.mSavedFragmentState);
            }
        }
        if (this.mCurState >= 1 || !fragment.mFromLayout) {
            moveToState(fragment);
        } else {
            moveToState(fragment, 1, 0, 0, false);
        }
        if (fragment.mView == null) {
            Throwable th6 = th2;
            new StringBuilder();
            new IllegalStateException(sb2.append("Fragment ").append(fname).append(" did not create a view.").toString());
            throw th6;
        }
        if (id != 0) {
            fragment.mView.setId(id);
        }
        if (fragment.mView.getTag() == null) {
            fragment.mView.setTag(tag);
        }
        return fragment.mView;
    }

    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return onCreateView((View) null, name, context, attrs);
    }

    /* access modifiers changed from: package-private */
    public LayoutInflater.Factory2 getLayoutInflaterFactory() {
        return this;
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl$FragmentTag */
    /* compiled from: FragmentManager */
    static class FragmentTag {
        public static final int[] Fragment = {16842755, 16842960, 16842961};
        public static final int Fragment_id = 1;
        public static final int Fragment_name = 0;
        public static final int Fragment_tag = 2;

        private FragmentTag() {
        }
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl$PopBackStackState */
    /* compiled from: FragmentManager */
    private class PopBackStackState implements OpGenerator {
        final int mFlags;
        final int mId;
        final String mName;
        final /* synthetic */ FragmentManagerImpl this$0;

        PopBackStackState(FragmentManagerImpl fragmentManagerImpl, String name, int id, int flags) {
            this.this$0 = fragmentManagerImpl;
            this.mName = name;
            this.mId = id;
            this.mFlags = flags;
        }

        public boolean generateOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
            FragmentManager childManager;
            ArrayList<BackStackRecord> records = arrayList;
            ArrayList<Boolean> isRecordPop = arrayList2;
            if (this.this$0.mPrimaryNav == null || this.mId >= 0 || this.mName != null || (childManager = this.this$0.mPrimaryNav.peekChildFragmentManager()) == null || !childManager.popBackStackImmediate()) {
                return this.this$0.popBackStackState(records, isRecordPop, this.mName, this.mId, this.mFlags);
            }
            return false;
        }
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl$StartEnterTransitionListener */
    /* compiled from: FragmentManager */
    static class StartEnterTransitionListener implements Fragment.OnStartEnterTransitionListener {
        final boolean mIsBack;
        private int mNumPostponed;
        final BackStackRecord mRecord;

        StartEnterTransitionListener(BackStackRecord record, boolean isBack) {
            this.mIsBack = isBack;
            this.mRecord = record;
        }

        public void onStartEnterTransition() {
            this.mNumPostponed--;
            if (this.mNumPostponed == 0) {
                this.mRecord.mManager.scheduleCommit();
            }
        }

        public void startListening() {
            this.mNumPostponed++;
        }

        public boolean isReady() {
            return this.mNumPostponed == 0;
        }

        public void completeTransaction() {
            boolean canceled = this.mNumPostponed > 0;
            FragmentManagerImpl manager = this.mRecord.mManager;
            int numAdded = manager.mAdded.size();
            for (int i = 0; i < numAdded; i++) {
                Fragment fragment = manager.mAdded.get(i);
                fragment.setOnStartEnterTransitionListener((Fragment.OnStartEnterTransitionListener) null);
                if (canceled && fragment.isPostponed()) {
                    fragment.startPostponedEnterTransition();
                }
            }
            this.mRecord.mManager.completeExecute(this.mRecord, this.mIsBack, !canceled, true);
        }

        public void cancelTransaction() {
            this.mRecord.mManager.completeExecute(this.mRecord, this.mIsBack, false, false);
        }
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator */
    /* compiled from: FragmentManager */
    private static class AnimationOrAnimator {
        public final Animation animation;
        public final Animator animator;

        AnimationOrAnimator(Animation animation2) {
            Throwable th;
            Animation animation3 = animation2;
            this.animation = animation3;
            this.animator = null;
            if (animation3 == null) {
                Throwable th2 = th;
                new IllegalStateException("Animation cannot be null");
                throw th2;
            }
        }

        AnimationOrAnimator(Animator animator2) {
            Throwable th;
            Animator animator3 = animator2;
            this.animation = null;
            this.animator = animator3;
            if (animator3 == null) {
                Throwable th2 = th;
                new IllegalStateException("Animator cannot be null");
                throw th2;
            }
        }
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl$AnimationListenerWrapper */
    /* compiled from: FragmentManager */
    private static class AnimationListenerWrapper implements Animation.AnimationListener {
        private final Animation.AnimationListener mWrapped;

        AnimationListenerWrapper(Animation.AnimationListener wrapped) {
            this.mWrapped = wrapped;
        }

        @CallSuper
        public void onAnimationStart(Animation animation) {
            Animation animation2 = animation;
            if (this.mWrapped != null) {
                this.mWrapped.onAnimationStart(animation2);
            }
        }

        @CallSuper
        public void onAnimationEnd(Animation animation) {
            Animation animation2 = animation;
            if (this.mWrapped != null) {
                this.mWrapped.onAnimationEnd(animation2);
            }
        }

        @CallSuper
        public void onAnimationRepeat(Animation animation) {
            Animation animation2 = animation;
            if (this.mWrapped != null) {
                this.mWrapped.onAnimationRepeat(animation2);
            }
        }
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl$AnimateOnHWLayerIfNeededListener */
    /* compiled from: FragmentManager */
    private static class AnimateOnHWLayerIfNeededListener extends AnimationListenerWrapper {
        View mView;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnimateOnHWLayerIfNeededListener(View v, Animation.AnimationListener listener) {
            super(listener);
            this.mView = v;
        }

        @CallSuper
        public void onAnimationEnd(Animation animation) {
            Runnable runnable;
            Animation animation2 = animation;
            if (ViewCompat.isAttachedToWindow(this.mView) || Build.VERSION.SDK_INT >= 24) {
                new Runnable(this) {
                    final /* synthetic */ AnimateOnHWLayerIfNeededListener this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void run() {
                        this.this$0.mView.setLayerType(0, (Paint) null);
                    }
                };
                boolean post = this.mView.post(runnable);
            } else {
                this.mView.setLayerType(0, (Paint) null);
            }
            super.onAnimationEnd(animation2);
        }
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl$AnimatorOnHWLayerIfNeededListener */
    /* compiled from: FragmentManager */
    private static class AnimatorOnHWLayerIfNeededListener extends AnimatorListenerAdapter {
        View mView;

        AnimatorOnHWLayerIfNeededListener(View v) {
            this.mView = v;
        }

        public void onAnimationStart(Animator animator) {
            Animator animator2 = animator;
            this.mView.setLayerType(2, (Paint) null);
        }

        public void onAnimationEnd(Animator animation) {
            this.mView.setLayerType(0, (Paint) null);
            animation.removeListener(this);
        }
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl$EndViewTransitionAnimator */
    /* compiled from: FragmentManager */
    private static class EndViewTransitionAnimator extends AnimationSet implements Runnable {
        private boolean mAnimating = true;
        private final View mChild;
        private boolean mEnded;
        private final ViewGroup mParent;
        private boolean mTransitionEnded;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        EndViewTransitionAnimator(@NonNull Animation animation, @NonNull ViewGroup parent, @NonNull View child) {
            super(false);
            this.mParent = parent;
            this.mChild = child;
            addAnimation(animation);
            boolean post = this.mParent.post(this);
        }

        public boolean getTransformation(long j, Transformation transformation) {
            long currentTime = j;
            Transformation t = transformation;
            this.mAnimating = true;
            if (this.mEnded) {
                return !this.mTransitionEnded;
            }
            if (!super.getTransformation(currentTime, t)) {
                this.mEnded = true;
                OneShotPreDrawListener add = OneShotPreDrawListener.add(this.mParent, this);
            }
            return true;
        }

        public boolean getTransformation(long j, Transformation transformation, float f) {
            long currentTime = j;
            Transformation outTransformation = transformation;
            float scale = f;
            this.mAnimating = true;
            if (this.mEnded) {
                return !this.mTransitionEnded;
            }
            if (!super.getTransformation(currentTime, outTransformation, scale)) {
                this.mEnded = true;
                OneShotPreDrawListener add = OneShotPreDrawListener.add(this.mParent, this);
            }
            return true;
        }

        public void run() {
            if (this.mEnded || !this.mAnimating) {
                this.mParent.endViewTransition(this.mChild);
                this.mTransitionEnded = true;
                return;
            }
            this.mAnimating = false;
            boolean post = this.mParent.post(this);
        }
    }
}
