package android.support.p000v4.app;

import android.support.annotation.Nullable;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentManagerImpl;
import android.support.p000v4.util.LogWriter;
import android.support.p000v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* renamed from: android.support.v4.app.BackStackRecord */
final class BackStackRecord extends FragmentTransaction implements FragmentManager.BackStackEntry, FragmentManagerImpl.OpGenerator {
    static final int OP_ADD = 1;
    static final int OP_ATTACH = 7;
    static final int OP_DETACH = 6;
    static final int OP_HIDE = 4;
    static final int OP_NULL = 0;
    static final int OP_REMOVE = 3;
    static final int OP_REPLACE = 2;
    static final int OP_SET_PRIMARY_NAV = 8;
    static final int OP_SHOW = 5;
    static final int OP_UNSET_PRIMARY_NAV = 9;
    static final String TAG = "FragmentManager";
    boolean mAddToBackStack;
    boolean mAllowAddToBackStack = true;
    int mBreadCrumbShortTitleRes;
    CharSequence mBreadCrumbShortTitleText;
    int mBreadCrumbTitleRes;
    CharSequence mBreadCrumbTitleText;
    ArrayList<Runnable> mCommitRunnables;
    boolean mCommitted;
    int mEnterAnim;
    int mExitAnim;
    int mIndex = -1;
    final FragmentManagerImpl mManager;
    @Nullable
    String mName;
    ArrayList<C0240Op> mOps;
    int mPopEnterAnim;
    int mPopExitAnim;
    boolean mReorderingAllowed = false;
    ArrayList<String> mSharedElementSourceNames;
    ArrayList<String> mSharedElementTargetNames;
    int mTransition;
    int mTransitionStyle;

    /* renamed from: android.support.v4.app.BackStackRecord$Op */
    static final class C0240Op {
        int cmd;
        int enterAnim;
        int exitAnim;
        Fragment fragment;
        int popEnterAnim;
        int popExitAnim;

        C0240Op() {
        }

        C0240Op(int cmd2, Fragment fragment2) {
            this.cmd = cmd2;
            this.fragment = fragment2;
        }
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder(128);
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append("BackStackEntry{");
        StringBuilder append2 = sb2.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.mIndex >= 0) {
            StringBuilder append3 = sb2.append(" #");
            StringBuilder append4 = sb2.append(this.mIndex);
        }
        if (this.mName != null) {
            StringBuilder append5 = sb2.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            StringBuilder append6 = sb2.append(this.mName);
        }
        StringBuilder append7 = sb2.append("}");
        return sb2.toString();
    }

    public void dump(String prefix, FileDescriptor fileDescriptor, PrintWriter writer, String[] strArr) {
        FileDescriptor fileDescriptor2 = fileDescriptor;
        String[] strArr2 = strArr;
        dump(prefix, writer, true);
    }

    public void dump(String str, PrintWriter printWriter, boolean z) {
        StringBuilder sb;
        String cmdStr;
        StringBuilder sb2;
        String prefix = str;
        PrintWriter writer = printWriter;
        boolean full = z;
        if (full) {
            writer.print(prefix);
            writer.print("mName=");
            writer.print(this.mName);
            writer.print(" mIndex=");
            writer.print(this.mIndex);
            writer.print(" mCommitted=");
            writer.println(this.mCommitted);
            if (this.mTransition != 0) {
                writer.print(prefix);
                writer.print("mTransition=#");
                writer.print(Integer.toHexString(this.mTransition));
                writer.print(" mTransitionStyle=#");
                writer.println(Integer.toHexString(this.mTransitionStyle));
            }
            if (!(this.mEnterAnim == 0 && this.mExitAnim == 0)) {
                writer.print(prefix);
                writer.print("mEnterAnim=#");
                writer.print(Integer.toHexString(this.mEnterAnim));
                writer.print(" mExitAnim=#");
                writer.println(Integer.toHexString(this.mExitAnim));
            }
            if (!(this.mPopEnterAnim == 0 && this.mPopExitAnim == 0)) {
                writer.print(prefix);
                writer.print("mPopEnterAnim=#");
                writer.print(Integer.toHexString(this.mPopEnterAnim));
                writer.print(" mPopExitAnim=#");
                writer.println(Integer.toHexString(this.mPopExitAnim));
            }
            if (!(this.mBreadCrumbTitleRes == 0 && this.mBreadCrumbTitleText == null)) {
                writer.print(prefix);
                writer.print("mBreadCrumbTitleRes=#");
                writer.print(Integer.toHexString(this.mBreadCrumbTitleRes));
                writer.print(" mBreadCrumbTitleText=");
                writer.println(this.mBreadCrumbTitleText);
            }
            if (!(this.mBreadCrumbShortTitleRes == 0 && this.mBreadCrumbShortTitleText == null)) {
                writer.print(prefix);
                writer.print("mBreadCrumbShortTitleRes=#");
                writer.print(Integer.toHexString(this.mBreadCrumbShortTitleRes));
                writer.print(" mBreadCrumbShortTitleText=");
                writer.println(this.mBreadCrumbShortTitleText);
            }
        }
        if (!this.mOps.isEmpty()) {
            writer.print(prefix);
            writer.println("Operations:");
            new StringBuilder();
            String sb3 = sb.append(prefix).append("    ").toString();
            int numOps = this.mOps.size();
            for (int opNum = 0; opNum < numOps; opNum++) {
                C0240Op op = this.mOps.get(opNum);
                switch (op.cmd) {
                    case 0:
                        cmdStr = "NULL";
                        break;
                    case 1:
                        cmdStr = "ADD";
                        break;
                    case 2:
                        cmdStr = "REPLACE";
                        break;
                    case 3:
                        cmdStr = "REMOVE";
                        break;
                    case 4:
                        cmdStr = "HIDE";
                        break;
                    case 5:
                        cmdStr = "SHOW";
                        break;
                    case 6:
                        cmdStr = "DETACH";
                        break;
                    case 7:
                        cmdStr = "ATTACH";
                        break;
                    case 8:
                        cmdStr = "SET_PRIMARY_NAV";
                        break;
                    case 9:
                        cmdStr = "UNSET_PRIMARY_NAV";
                        break;
                    default:
                        new StringBuilder();
                        cmdStr = sb2.append("cmd=").append(op.cmd).toString();
                        break;
                }
                writer.print(prefix);
                writer.print("  Op #");
                writer.print(opNum);
                writer.print(": ");
                writer.print(cmdStr);
                writer.print(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                writer.println(op.fragment);
                if (full) {
                    if (!(op.enterAnim == 0 && op.exitAnim == 0)) {
                        writer.print(prefix);
                        writer.print("enterAnim=#");
                        writer.print(Integer.toHexString(op.enterAnim));
                        writer.print(" exitAnim=#");
                        writer.println(Integer.toHexString(op.exitAnim));
                    }
                    if (op.popEnterAnim != 0 || op.popExitAnim != 0) {
                        writer.print(prefix);
                        writer.print("popEnterAnim=#");
                        writer.print(Integer.toHexString(op.popEnterAnim));
                        writer.print(" popExitAnim=#");
                        writer.println(Integer.toHexString(op.popExitAnim));
                    }
                }
            }
        }
    }

    public BackStackRecord(FragmentManagerImpl manager) {
        ArrayList<C0240Op> arrayList;
        new ArrayList<>();
        this.mOps = arrayList;
        this.mManager = manager;
    }

    public int getId() {
        return this.mIndex;
    }

    public int getBreadCrumbTitleRes() {
        return this.mBreadCrumbTitleRes;
    }

    public int getBreadCrumbShortTitleRes() {
        return this.mBreadCrumbShortTitleRes;
    }

    @Nullable
    public CharSequence getBreadCrumbTitle() {
        if (this.mBreadCrumbTitleRes != 0) {
            return this.mManager.mHost.getContext().getText(this.mBreadCrumbTitleRes);
        }
        return this.mBreadCrumbTitleText;
    }

    @Nullable
    public CharSequence getBreadCrumbShortTitle() {
        if (this.mBreadCrumbShortTitleRes != 0) {
            return this.mManager.mHost.getContext().getText(this.mBreadCrumbShortTitleRes);
        }
        return this.mBreadCrumbShortTitleText;
    }

    /* access modifiers changed from: package-private */
    public void addOp(C0240Op op) {
        C0240Op op2 = op;
        boolean add = this.mOps.add(op2);
        op2.enterAnim = this.mEnterAnim;
        op2.exitAnim = this.mExitAnim;
        op2.popEnterAnim = this.mPopEnterAnim;
        op2.popExitAnim = this.mPopExitAnim;
    }

    public FragmentTransaction add(Fragment fragment, @Nullable String tag) {
        doAddOp(0, fragment, tag, 1);
        return this;
    }

    public FragmentTransaction add(int containerViewId, Fragment fragment) {
        doAddOp(containerViewId, fragment, (String) null, 1);
        return this;
    }

    public FragmentTransaction add(int containerViewId, Fragment fragment, @Nullable String tag) {
        doAddOp(containerViewId, fragment, tag, 1);
        return this;
    }

    private void doAddOp(int i, Fragment fragment, @Nullable String str, int i2) {
        Throwable th;
        StringBuilder sb;
        C0240Op op;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        StringBuilder sb3;
        Throwable th4;
        StringBuilder sb4;
        int containerViewId = i;
        Fragment fragment2 = fragment;
        String tag = str;
        int opcmd = i2;
        Class fragmentClass = fragment2.getClass();
        int modifiers = fragmentClass.getModifiers();
        if (fragmentClass.isAnonymousClass() || !Modifier.isPublic(modifiers) || (fragmentClass.isMemberClass() && !Modifier.isStatic(modifiers))) {
            Throwable th5 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Fragment ").append(fragmentClass.getCanonicalName()).append(" must be a public static class to be  properly recreated from").append(" instance state.").toString());
            throw th5;
        }
        fragment2.mFragmentManager = this.mManager;
        if (tag != null) {
            if (fragment2.mTag == null || tag.equals(fragment2.mTag)) {
                fragment2.mTag = tag;
            } else {
                Throwable th6 = th4;
                new StringBuilder();
                new IllegalStateException(sb4.append("Can't change tag of fragment ").append(fragment2).append(": was ").append(fragment2.mTag).append(" now ").append(tag).toString());
                throw th6;
            }
        }
        if (containerViewId != 0) {
            if (containerViewId == -1) {
                Throwable th7 = th3;
                new StringBuilder();
                new IllegalArgumentException(sb3.append("Can't add fragment ").append(fragment2).append(" with tag ").append(tag).append(" to container view with no id").toString());
                throw th7;
            } else if (fragment2.mFragmentId == 0 || fragment2.mFragmentId == containerViewId) {
                int i3 = containerViewId;
                int i4 = i3;
                fragment2.mFragmentId = i4;
                fragment2.mContainerId = i3;
            } else {
                Throwable th8 = th2;
                new StringBuilder();
                new IllegalStateException(sb2.append("Can't change container ID of fragment ").append(fragment2).append(": was ").append(fragment2.mFragmentId).append(" now ").append(containerViewId).toString());
                throw th8;
            }
        }
        new C0240Op(opcmd, fragment2);
        addOp(op);
    }

    public FragmentTransaction replace(int containerViewId, Fragment fragment) {
        return replace(containerViewId, fragment, (String) null);
    }

    public FragmentTransaction replace(int i, Fragment fragment, @Nullable String str) {
        Throwable th;
        int containerViewId = i;
        Fragment fragment2 = fragment;
        String tag = str;
        if (containerViewId == 0) {
            Throwable th2 = th;
            new IllegalArgumentException("Must use non-zero containerViewId");
            throw th2;
        }
        doAddOp(containerViewId, fragment2, tag, 2);
        return this;
    }

    public FragmentTransaction remove(Fragment fragment) {
        C0240Op op;
        new C0240Op(3, fragment);
        addOp(op);
        return this;
    }

    public FragmentTransaction hide(Fragment fragment) {
        C0240Op op;
        new C0240Op(4, fragment);
        addOp(op);
        return this;
    }

    public FragmentTransaction show(Fragment fragment) {
        C0240Op op;
        new C0240Op(5, fragment);
        addOp(op);
        return this;
    }

    public FragmentTransaction detach(Fragment fragment) {
        C0240Op op;
        new C0240Op(6, fragment);
        addOp(op);
        return this;
    }

    public FragmentTransaction attach(Fragment fragment) {
        C0240Op op;
        new C0240Op(7, fragment);
        addOp(op);
        return this;
    }

    public FragmentTransaction setPrimaryNavigationFragment(@Nullable Fragment fragment) {
        C0240Op op;
        new C0240Op(8, fragment);
        addOp(op);
        return this;
    }

    public FragmentTransaction setCustomAnimations(int enter, int exit) {
        return setCustomAnimations(enter, exit, 0, 0);
    }

    public FragmentTransaction setCustomAnimations(int enter, int exit, int popEnter, int popExit) {
        this.mEnterAnim = enter;
        this.mExitAnim = exit;
        this.mPopEnterAnim = popEnter;
        this.mPopExitAnim = popExit;
        return this;
    }

    public FragmentTransaction setTransition(int transition) {
        this.mTransition = transition;
        return this;
    }

    public FragmentTransaction addSharedElement(View view, String str) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        ArrayList<String> arrayList;
        ArrayList<String> arrayList2;
        Throwable th3;
        View sharedElement = view;
        String name = str;
        if (FragmentTransition.supportsTransition()) {
            String transitionName = ViewCompat.getTransitionName(sharedElement);
            if (transitionName == null) {
                Throwable th4 = th3;
                new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
                throw th4;
            }
            if (this.mSharedElementSourceNames == null) {
                new ArrayList<>();
                this.mSharedElementSourceNames = arrayList;
                new ArrayList<>();
                this.mSharedElementTargetNames = arrayList2;
            } else if (this.mSharedElementTargetNames.contains(name)) {
                Throwable th5 = th2;
                new StringBuilder();
                new IllegalArgumentException(sb2.append("A shared element with the target name '").append(name).append("' has already been added to the transaction.").toString());
                throw th5;
            } else if (this.mSharedElementSourceNames.contains(transitionName)) {
                Throwable th6 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("A shared element with the source name '").append(transitionName).append(" has already been added to the transaction.").toString());
                throw th6;
            }
            boolean add = this.mSharedElementSourceNames.add(transitionName);
            boolean add2 = this.mSharedElementTargetNames.add(name);
        }
        return this;
    }

    public FragmentTransaction setTransitionStyle(int styleRes) {
        this.mTransitionStyle = styleRes;
        return this;
    }

    public FragmentTransaction addToBackStack(@Nullable String str) {
        Throwable th;
        String name = str;
        if (!this.mAllowAddToBackStack) {
            Throwable th2 = th;
            new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
            throw th2;
        }
        this.mAddToBackStack = true;
        this.mName = name;
        return this;
    }

    public boolean isAddToBackStackAllowed() {
        return this.mAllowAddToBackStack;
    }

    public FragmentTransaction disallowAddToBackStack() {
        Throwable th;
        if (this.mAddToBackStack) {
            Throwable th2 = th;
            new IllegalStateException("This transaction is already being added to the back stack");
            throw th2;
        }
        this.mAllowAddToBackStack = false;
        return this;
    }

    public FragmentTransaction setBreadCrumbTitle(int res) {
        this.mBreadCrumbTitleRes = res;
        this.mBreadCrumbTitleText = null;
        return this;
    }

    public FragmentTransaction setBreadCrumbTitle(@Nullable CharSequence text) {
        this.mBreadCrumbTitleRes = 0;
        this.mBreadCrumbTitleText = text;
        return this;
    }

    public FragmentTransaction setBreadCrumbShortTitle(int res) {
        this.mBreadCrumbShortTitleRes = res;
        this.mBreadCrumbShortTitleText = null;
        return this;
    }

    public FragmentTransaction setBreadCrumbShortTitle(@Nullable CharSequence text) {
        this.mBreadCrumbShortTitleRes = 0;
        this.mBreadCrumbShortTitleText = text;
        return this;
    }

    /* access modifiers changed from: package-private */
    public void bumpBackStackNesting(int i) {
        StringBuilder sb;
        StringBuilder sb2;
        int amt = i;
        if (this.mAddToBackStack) {
            if (FragmentManagerImpl.DEBUG) {
                new StringBuilder();
                int v = Log.v(TAG, sb2.append("Bump nesting in ").append(this).append(" by ").append(amt).toString());
            }
            int numOps = this.mOps.size();
            for (int opNum = 0; opNum < numOps; opNum++) {
                C0240Op op = this.mOps.get(opNum);
                if (op.fragment != null) {
                    op.fragment.mBackStackNesting += amt;
                    if (FragmentManagerImpl.DEBUG) {
                        new StringBuilder();
                        int v2 = Log.v(TAG, sb.append("Bump nesting of ").append(op.fragment).append(" to ").append(op.fragment.mBackStackNesting).toString());
                    }
                }
            }
        }
    }

    public FragmentTransaction runOnCommit(Runnable runnable) {
        ArrayList<Runnable> arrayList;
        Throwable th;
        Runnable runnable2 = runnable;
        if (runnable2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("runnable cannot be null");
            throw th2;
        }
        FragmentTransaction disallowAddToBackStack = disallowAddToBackStack();
        if (this.mCommitRunnables == null) {
            new ArrayList<>();
            this.mCommitRunnables = arrayList;
        }
        boolean add = this.mCommitRunnables.add(runnable2);
        return this;
    }

    public void runOnCommitRunnables() {
        if (this.mCommitRunnables != null) {
            int N = this.mCommitRunnables.size();
            for (int i = 0; i < N; i++) {
                this.mCommitRunnables.get(i).run();
            }
            this.mCommitRunnables = null;
        }
    }

    public int commit() {
        return commitInternal(false);
    }

    public int commitAllowingStateLoss() {
        return commitInternal(true);
    }

    public void commitNow() {
        FragmentTransaction disallowAddToBackStack = disallowAddToBackStack();
        this.mManager.execSingleAction(this, false);
    }

    public void commitNowAllowingStateLoss() {
        FragmentTransaction disallowAddToBackStack = disallowAddToBackStack();
        this.mManager.execSingleAction(this, true);
    }

    public FragmentTransaction setReorderingAllowed(boolean reorderingAllowed) {
        this.mReorderingAllowed = reorderingAllowed;
        return this;
    }

    public FragmentTransaction setAllowOptimization(boolean allowOptimization) {
        return setReorderingAllowed(allowOptimization);
    }

    /* access modifiers changed from: package-private */
    public int commitInternal(boolean z) {
        StringBuilder sb;
        LogWriter logw;
        PrintWriter printWriter;
        Throwable th;
        boolean allowStateLoss = z;
        if (this.mCommitted) {
            Throwable th2 = th;
            new IllegalStateException("commit already called");
            throw th2;
        }
        if (FragmentManagerImpl.DEBUG) {
            new StringBuilder();
            int v = Log.v(TAG, sb.append("Commit: ").append(this).toString());
            new LogWriter(TAG);
            new PrintWriter(logw);
            PrintWriter pw = printWriter;
            dump("  ", (FileDescriptor) null, pw, (String[]) null);
            pw.close();
        }
        this.mCommitted = true;
        if (this.mAddToBackStack) {
            this.mIndex = this.mManager.allocBackStackIndex(this);
        } else {
            this.mIndex = -1;
        }
        this.mManager.enqueueAction(this, allowStateLoss);
        return this.mIndex;
    }

    public boolean generateOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
        StringBuilder sb;
        ArrayList<BackStackRecord> records = arrayList;
        ArrayList<Boolean> isRecordPop = arrayList2;
        if (FragmentManagerImpl.DEBUG) {
            new StringBuilder();
            int v = Log.v(TAG, sb.append("Run: ").append(this).toString());
        }
        boolean add = records.add(this);
        boolean add2 = isRecordPop.add(false);
        if (this.mAddToBackStack) {
            this.mManager.addBackStackState(this);
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean interactsWith(int i) {
        int containerId = i;
        int numOps = this.mOps.size();
        for (int opNum = 0; opNum < numOps; opNum++) {
            C0240Op op = this.mOps.get(opNum);
            int fragContainer = op.fragment != null ? op.fragment.mContainerId : 0;
            if (fragContainer != 0 && fragContainer == containerId) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean interactsWith(ArrayList<BackStackRecord> arrayList, int i, int i2) {
        ArrayList<BackStackRecord> records = arrayList;
        int startIndex = i;
        int endIndex = i2;
        if (endIndex == startIndex) {
            return false;
        }
        int numOps = this.mOps.size();
        int lastContainer = -1;
        for (int opNum = 0; opNum < numOps; opNum++) {
            C0240Op op = this.mOps.get(opNum);
            int container = op.fragment != null ? op.fragment.mContainerId : 0;
            if (!(container == 0 || container == lastContainer)) {
                lastContainer = container;
                for (int i3 = startIndex; i3 < endIndex; i3++) {
                    BackStackRecord record = records.get(i3);
                    int numThoseOps = record.mOps.size();
                    for (int thoseOpIndex = 0; thoseOpIndex < numThoseOps; thoseOpIndex++) {
                        C0240Op thatOp = record.mOps.get(thoseOpIndex);
                        if ((thatOp.fragment != null ? thatOp.fragment.mContainerId : 0) == container) {
                            return true;
                        }
                    }
                }
                continue;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void executeOps() {
        Throwable th;
        StringBuilder sb;
        int numOps = this.mOps.size();
        for (int opNum = 0; opNum < numOps; opNum++) {
            C0240Op op = this.mOps.get(opNum);
            Fragment f = op.fragment;
            if (f != null) {
                f.setNextTransition(this.mTransition, this.mTransitionStyle);
            }
            switch (op.cmd) {
                case 1:
                    f.setNextAnim(op.enterAnim);
                    this.mManager.addFragment(f, false);
                    break;
                case 3:
                    f.setNextAnim(op.exitAnim);
                    this.mManager.removeFragment(f);
                    break;
                case 4:
                    f.setNextAnim(op.exitAnim);
                    this.mManager.hideFragment(f);
                    break;
                case 5:
                    f.setNextAnim(op.enterAnim);
                    this.mManager.showFragment(f);
                    break;
                case 6:
                    f.setNextAnim(op.exitAnim);
                    this.mManager.detachFragment(f);
                    break;
                case 7:
                    f.setNextAnim(op.enterAnim);
                    this.mManager.attachFragment(f);
                    break;
                case 8:
                    this.mManager.setPrimaryNavigationFragment(f);
                    break;
                case 9:
                    this.mManager.setPrimaryNavigationFragment((Fragment) null);
                    break;
                default:
                    Throwable th2 = th;
                    new StringBuilder();
                    new IllegalArgumentException(sb.append("Unknown cmd: ").append(op.cmd).toString());
                    throw th2;
            }
            if (!(this.mReorderingAllowed || op.cmd == 1 || f == null)) {
                this.mManager.moveFragmentToExpectedState(f);
            }
        }
        if (!this.mReorderingAllowed) {
            this.mManager.moveToState(this.mManager.mCurState, true);
        }
    }

    /* access modifiers changed from: package-private */
    public void executePopOps(boolean z) {
        Throwable th;
        StringBuilder sb;
        boolean moveToState = z;
        for (int opNum = this.mOps.size() - 1; opNum >= 0; opNum--) {
            C0240Op op = this.mOps.get(opNum);
            Fragment f = op.fragment;
            if (f != null) {
                f.setNextTransition(FragmentManagerImpl.reverseTransit(this.mTransition), this.mTransitionStyle);
            }
            switch (op.cmd) {
                case 1:
                    f.setNextAnim(op.popExitAnim);
                    this.mManager.removeFragment(f);
                    break;
                case 3:
                    f.setNextAnim(op.popEnterAnim);
                    this.mManager.addFragment(f, false);
                    break;
                case 4:
                    f.setNextAnim(op.popEnterAnim);
                    this.mManager.showFragment(f);
                    break;
                case 5:
                    f.setNextAnim(op.popExitAnim);
                    this.mManager.hideFragment(f);
                    break;
                case 6:
                    f.setNextAnim(op.popEnterAnim);
                    this.mManager.attachFragment(f);
                    break;
                case 7:
                    f.setNextAnim(op.popExitAnim);
                    this.mManager.detachFragment(f);
                    break;
                case 8:
                    this.mManager.setPrimaryNavigationFragment((Fragment) null);
                    break;
                case 9:
                    this.mManager.setPrimaryNavigationFragment(f);
                    break;
                default:
                    Throwable th2 = th;
                    new StringBuilder();
                    new IllegalArgumentException(sb.append("Unknown cmd: ").append(op.cmd).toString());
                    throw th2;
            }
            if (!(this.mReorderingAllowed || op.cmd == 3 || f == null)) {
                this.mManager.moveFragmentToExpectedState(f);
            }
        }
        if (!this.mReorderingAllowed && moveToState) {
            this.mManager.moveToState(this.mManager.mCurState, true);
        }
    }

    /* access modifiers changed from: package-private */
    public Fragment expandOps(ArrayList<Fragment> arrayList, Fragment fragment) {
        Object obj;
        C0240Op op;
        Object obj2;
        Object obj3;
        ArrayList<Fragment> added = arrayList;
        Fragment oldPrimaryNav = fragment;
        int opNum = 0;
        while (opNum < this.mOps.size()) {
            C0240Op op2 = this.mOps.get(opNum);
            switch (op2.cmd) {
                case 1:
                case 7:
                    boolean add = added.add(op2.fragment);
                    break;
                case 2:
                    Fragment f = op2.fragment;
                    int containerId = f.mContainerId;
                    boolean alreadyAdded = false;
                    for (int i = added.size() - 1; i >= 0; i--) {
                        Fragment old = added.get(i);
                        if (old.mContainerId == containerId) {
                            if (old == f) {
                                alreadyAdded = true;
                            } else {
                                if (old == oldPrimaryNav) {
                                    new C0240Op(9, old);
                                    this.mOps.add(opNum, obj2);
                                    opNum++;
                                    oldPrimaryNav = null;
                                }
                                new C0240Op(3, old);
                                C0240Op removeOp = op;
                                removeOp.enterAnim = op2.enterAnim;
                                removeOp.popEnterAnim = op2.popEnterAnim;
                                removeOp.exitAnim = op2.exitAnim;
                                removeOp.popExitAnim = op2.popExitAnim;
                                this.mOps.add(opNum, removeOp);
                                boolean remove = added.remove(old);
                                opNum++;
                            }
                        }
                    }
                    if (!alreadyAdded) {
                        op2.cmd = 1;
                        boolean add2 = added.add(f);
                        break;
                    } else {
                        C0240Op remove2 = this.mOps.remove(opNum);
                        opNum--;
                        break;
                    }
                case 3:
                case 6:
                    boolean remove3 = added.remove(op2.fragment);
                    if (op2.fragment != oldPrimaryNav) {
                        break;
                    } else {
                        new C0240Op(9, op2.fragment);
                        this.mOps.add(opNum, obj3);
                        opNum++;
                        oldPrimaryNav = null;
                        break;
                    }
                case 8:
                    new C0240Op(9, oldPrimaryNav);
                    this.mOps.add(opNum, obj);
                    opNum++;
                    oldPrimaryNav = op2.fragment;
                    break;
            }
            opNum++;
        }
        return oldPrimaryNav;
    }

    /* access modifiers changed from: package-private */
    public Fragment trackAddedFragmentsInPop(ArrayList<Fragment> arrayList, Fragment fragment) {
        ArrayList<Fragment> added = arrayList;
        Fragment oldPrimaryNav = fragment;
        for (int opNum = 0; opNum < this.mOps.size(); opNum++) {
            C0240Op op = this.mOps.get(opNum);
            switch (op.cmd) {
                case 1:
                case 7:
                    boolean remove = added.remove(op.fragment);
                    break;
                case 3:
                case 6:
                    boolean add = added.add(op.fragment);
                    break;
                case 8:
                    oldPrimaryNav = null;
                    break;
                case 9:
                    oldPrimaryNav = op.fragment;
                    break;
            }
        }
        return oldPrimaryNav;
    }

    /* access modifiers changed from: package-private */
    public boolean isPostponed() {
        for (int opNum = 0; opNum < this.mOps.size(); opNum++) {
            if (isFragmentPostponed(this.mOps.get(opNum))) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void setOnStartPostponedListener(Fragment.OnStartEnterTransitionListener onStartEnterTransitionListener) {
        Fragment.OnStartEnterTransitionListener listener = onStartEnterTransitionListener;
        for (int opNum = 0; opNum < this.mOps.size(); opNum++) {
            C0240Op op = this.mOps.get(opNum);
            if (isFragmentPostponed(op)) {
                op.fragment.setOnStartEnterTransitionListener(listener);
            }
        }
    }

    private static boolean isFragmentPostponed(C0240Op op) {
        Fragment fragment = op.fragment;
        return fragment != null && fragment.mAdded && fragment.mView != null && !fragment.mDetached && !fragment.mHidden && fragment.isPostponed();
    }

    @Nullable
    public String getName() {
        return this.mName;
    }

    public int getTransition() {
        return this.mTransition;
    }

    public int getTransitionStyle() {
        return this.mTransitionStyle;
    }

    public boolean isEmpty() {
        return this.mOps.isEmpty();
    }
}
