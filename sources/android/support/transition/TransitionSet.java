package android.support.transition;

import android.animation.TimeInterpolator;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.transition.Transition;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

public class TransitionSet extends Transition {
    private static final int FLAG_CHANGE_EPICENTER = 8;
    private static final int FLAG_CHANGE_INTERPOLATOR = 1;
    private static final int FLAG_CHANGE_PATH_MOTION = 4;
    private static final int FLAG_CHANGE_PROPAGATION = 2;
    public static final int ORDERING_SEQUENTIAL = 1;
    public static final int ORDERING_TOGETHER = 0;
    private int mChangeFlags = 0;
    int mCurrentListeners;
    private boolean mPlayTogether = true;
    boolean mStarted = false;
    private ArrayList<Transition> mTransitions;

    public TransitionSet() {
        ArrayList<Transition> arrayList;
        new ArrayList<>();
        this.mTransitions = arrayList;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TransitionSet(android.content.Context r12, android.util.AttributeSet r13) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r5 = r0
            r6 = r1
            r7 = r2
            r5.<init>(r6, r7)
            r5 = r0
            java.util.ArrayList r6 = new java.util.ArrayList
            r10 = r6
            r6 = r10
            r7 = r10
            r7.<init>()
            r5.mTransitions = r6
            r5 = r0
            r6 = 1
            r5.mPlayTogether = r6
            r5 = r0
            r6 = 0
            r5.mStarted = r6
            r5 = r0
            r6 = 0
            r5.mChangeFlags = r6
            r5 = r1
            r6 = r2
            int[] r7 = android.support.transition.Styleable.TRANSITION_SET
            android.content.res.TypedArray r5 = r5.obtainStyledAttributes(r6, r7)
            r3 = r5
            r5 = r3
            r6 = r2
            android.content.res.XmlResourceParser r6 = (android.content.res.XmlResourceParser) r6
            java.lang.String r7 = "transitionOrdering"
            r8 = 0
            r9 = 0
            int r5 = android.support.p000v4.content.res.TypedArrayUtils.getNamedInt(r5, r6, r7, r8, r9)
            r4 = r5
            r5 = r0
            r6 = r4
            android.support.transition.TransitionSet r5 = r5.setOrdering(r6)
            r5 = r3
            r5.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.TransitionSet.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    @NonNull
    public TransitionSet setOrdering(int i) {
        Throwable th;
        StringBuilder sb;
        int ordering = i;
        switch (ordering) {
            case 0:
                this.mPlayTogether = true;
                break;
            case 1:
                this.mPlayTogether = false;
                break;
            default:
                Throwable th2 = th;
                new StringBuilder();
                new AndroidRuntimeException(sb.append("Invalid parameter for TransitionSet ordering: ").append(ordering).toString());
                throw th2;
        }
        return this;
    }

    public int getOrdering() {
        return this.mPlayTogether ? 0 : 1;
    }

    @NonNull
    public TransitionSet addTransition(@NonNull Transition transition) {
        Transition transition2 = transition;
        boolean add = this.mTransitions.add(transition2);
        transition2.mParent = this;
        if (this.mDuration >= 0) {
            Transition duration = transition2.setDuration(this.mDuration);
        }
        if ((this.mChangeFlags & 1) != 0) {
            Transition interpolator = transition2.setInterpolator(getInterpolator());
        }
        if ((this.mChangeFlags & 2) != 0) {
            transition2.setPropagation(getPropagation());
        }
        if ((this.mChangeFlags & 4) != 0) {
            transition2.setPathMotion(getPathMotion());
        }
        if ((this.mChangeFlags & 8) != 0) {
            transition2.setEpicenterCallback(getEpicenterCallback());
        }
        return this;
    }

    public int getTransitionCount() {
        return this.mTransitions.size();
    }

    public Transition getTransitionAt(int i) {
        int index = i;
        if (index < 0 || index >= this.mTransitions.size()) {
            return null;
        }
        return this.mTransitions.get(index);
    }

    @NonNull
    public TransitionSet setDuration(long j) {
        long duration = j;
        Transition duration2 = super.setDuration(duration);
        if (this.mDuration >= 0) {
            int numTransitions = this.mTransitions.size();
            for (int i = 0; i < numTransitions; i++) {
                Transition duration3 = this.mTransitions.get(i).setDuration(duration);
            }
        }
        return this;
    }

    @NonNull
    public TransitionSet setStartDelay(long startDelay) {
        return (TransitionSet) super.setStartDelay(startDelay);
    }

    @NonNull
    public TransitionSet setInterpolator(@Nullable TimeInterpolator timeInterpolator) {
        TimeInterpolator interpolator = timeInterpolator;
        this.mChangeFlags |= 1;
        if (this.mTransitions != null) {
            int numTransitions = this.mTransitions.size();
            for (int i = 0; i < numTransitions; i++) {
                Transition interpolator2 = this.mTransitions.get(i).setInterpolator(interpolator);
            }
        }
        return (TransitionSet) super.setInterpolator(interpolator);
    }

    @NonNull
    public TransitionSet addTarget(@NonNull View view) {
        View target = view;
        for (int i = 0; i < this.mTransitions.size(); i++) {
            Transition addTarget = this.mTransitions.get(i).addTarget(target);
        }
        return (TransitionSet) super.addTarget(target);
    }

    @NonNull
    public TransitionSet addTarget(@IdRes int i) {
        int targetId = i;
        for (int i2 = 0; i2 < this.mTransitions.size(); i2++) {
            Transition addTarget = this.mTransitions.get(i2).addTarget(targetId);
        }
        return (TransitionSet) super.addTarget(targetId);
    }

    @NonNull
    public TransitionSet addTarget(@NonNull String str) {
        String targetName = str;
        for (int i = 0; i < this.mTransitions.size(); i++) {
            Transition addTarget = this.mTransitions.get(i).addTarget(targetName);
        }
        return (TransitionSet) super.addTarget(targetName);
    }

    @NonNull
    public TransitionSet addTarget(@NonNull Class cls) {
        Class targetType = cls;
        for (int i = 0; i < this.mTransitions.size(); i++) {
            Transition addTarget = this.mTransitions.get(i).addTarget(targetType);
        }
        return (TransitionSet) super.addTarget(targetType);
    }

    @NonNull
    public TransitionSet addListener(@NonNull Transition.TransitionListener listener) {
        return (TransitionSet) super.addListener(listener);
    }

    @NonNull
    public TransitionSet removeTarget(@IdRes int i) {
        int targetId = i;
        for (int i2 = 0; i2 < this.mTransitions.size(); i2++) {
            Transition removeTarget = this.mTransitions.get(i2).removeTarget(targetId);
        }
        return (TransitionSet) super.removeTarget(targetId);
    }

    @NonNull
    public TransitionSet removeTarget(@NonNull View view) {
        View target = view;
        for (int i = 0; i < this.mTransitions.size(); i++) {
            Transition removeTarget = this.mTransitions.get(i).removeTarget(target);
        }
        return (TransitionSet) super.removeTarget(target);
    }

    @NonNull
    public TransitionSet removeTarget(@NonNull Class cls) {
        Class target = cls;
        for (int i = 0; i < this.mTransitions.size(); i++) {
            Transition removeTarget = this.mTransitions.get(i).removeTarget(target);
        }
        return (TransitionSet) super.removeTarget(target);
    }

    @NonNull
    public TransitionSet removeTarget(@NonNull String str) {
        String target = str;
        for (int i = 0; i < this.mTransitions.size(); i++) {
            Transition removeTarget = this.mTransitions.get(i).removeTarget(target);
        }
        return (TransitionSet) super.removeTarget(target);
    }

    @NonNull
    public Transition excludeTarget(@NonNull View view, boolean z) {
        View target = view;
        boolean exclude = z;
        for (int i = 0; i < this.mTransitions.size(); i++) {
            Transition excludeTarget = this.mTransitions.get(i).excludeTarget(target, exclude);
        }
        return super.excludeTarget(target, exclude);
    }

    @NonNull
    public Transition excludeTarget(@NonNull String str, boolean z) {
        String targetName = str;
        boolean exclude = z;
        for (int i = 0; i < this.mTransitions.size(); i++) {
            Transition excludeTarget = this.mTransitions.get(i).excludeTarget(targetName, exclude);
        }
        return super.excludeTarget(targetName, exclude);
    }

    @NonNull
    public Transition excludeTarget(int i, boolean z) {
        int targetId = i;
        boolean exclude = z;
        for (int i2 = 0; i2 < this.mTransitions.size(); i2++) {
            Transition excludeTarget = this.mTransitions.get(i2).excludeTarget(targetId, exclude);
        }
        return super.excludeTarget(targetId, exclude);
    }

    @NonNull
    public Transition excludeTarget(@NonNull Class cls, boolean z) {
        Class type = cls;
        boolean exclude = z;
        for (int i = 0; i < this.mTransitions.size(); i++) {
            Transition excludeTarget = this.mTransitions.get(i).excludeTarget(type, exclude);
        }
        return super.excludeTarget(type, exclude);
    }

    @NonNull
    public TransitionSet removeListener(@NonNull Transition.TransitionListener listener) {
        return (TransitionSet) super.removeListener(listener);
    }

    public void setPathMotion(PathMotion pathMotion) {
        PathMotion pathMotion2 = pathMotion;
        super.setPathMotion(pathMotion2);
        this.mChangeFlags |= 4;
        for (int i = 0; i < this.mTransitions.size(); i++) {
            this.mTransitions.get(i).setPathMotion(pathMotion2);
        }
    }

    @NonNull
    public TransitionSet removeTransition(@NonNull Transition transition) {
        Transition transition2 = transition;
        boolean remove = this.mTransitions.remove(transition2);
        transition2.mParent = null;
        return this;
    }

    private void setupStartEndListeners() {
        Transition.TransitionListener transitionListener;
        new TransitionSetListener(this);
        Transition.TransitionListener transitionListener2 = transitionListener;
        Iterator<Transition> it = this.mTransitions.iterator();
        while (it.hasNext()) {
            Transition addListener = it.next().addListener(transitionListener2);
        }
        this.mCurrentListeners = this.mTransitions.size();
    }

    static class TransitionSetListener extends TransitionListenerAdapter {
        TransitionSet mTransitionSet;

        TransitionSetListener(TransitionSet transitionSet) {
            this.mTransitionSet = transitionSet;
        }

        public void onTransitionStart(@NonNull Transition transition) {
            Transition transition2 = transition;
            if (!this.mTransitionSet.mStarted) {
                this.mTransitionSet.start();
                this.mTransitionSet.mStarted = true;
            }
        }

        public void onTransitionEnd(@NonNull Transition transition) {
            Transition transition2 = transition;
            TransitionSet transitionSet = this.mTransitionSet;
            transitionSet.mCurrentListeners--;
            if (this.mTransitionSet.mCurrentListeners == 0) {
                this.mTransitionSet.mStarted = false;
                this.mTransitionSet.end();
            }
            Transition removeListener = transition2.removeListener(this);
        }
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void createAnimators(ViewGroup viewGroup, TransitionValuesMaps transitionValuesMaps, TransitionValuesMaps transitionValuesMaps2, ArrayList<TransitionValues> arrayList, ArrayList<TransitionValues> arrayList2) {
        ViewGroup sceneRoot = viewGroup;
        TransitionValuesMaps startValues = transitionValuesMaps;
        TransitionValuesMaps endValues = transitionValuesMaps2;
        ArrayList<TransitionValues> startValuesList = arrayList;
        ArrayList<TransitionValues> endValuesList = arrayList2;
        long startDelay = getStartDelay();
        int numTransitions = this.mTransitions.size();
        for (int i = 0; i < numTransitions; i++) {
            Transition childTransition = this.mTransitions.get(i);
            if (startDelay > 0 && (this.mPlayTogether || i == 0)) {
                long childStartDelay = childTransition.getStartDelay();
                if (childStartDelay > 0) {
                    Transition startDelay2 = childTransition.setStartDelay(startDelay + childStartDelay);
                } else {
                    Transition startDelay3 = childTransition.setStartDelay(startDelay);
                }
            }
            childTransition.createAnimators(sceneRoot, startValues, endValues, startValuesList, endValuesList);
        }
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void runAnimators() {
        Transition.TransitionListener transitionListener;
        if (this.mTransitions.isEmpty()) {
            start();
            end();
            return;
        }
        setupStartEndListeners();
        if (!this.mPlayTogether) {
            for (int i = 1; i < this.mTransitions.size(); i++) {
                Transition previousTransition = this.mTransitions.get(i - 1);
                final Transition transition = this.mTransitions.get(i);
                new TransitionListenerAdapter(this) {
                    final /* synthetic */ TransitionSet this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void onTransitionEnd(@NonNull Transition transition) {
                        transition.runAnimators();
                        Transition removeListener = transition.removeListener(this);
                    }
                };
                Transition addListener = previousTransition.addListener(transitionListener);
            }
            Transition firstTransition = this.mTransitions.get(0);
            if (firstTransition != null) {
                firstTransition.runAnimators();
                return;
            }
            return;
        }
        Iterator<Transition> it = this.mTransitions.iterator();
        while (it.hasNext()) {
            it.next().runAnimators();
        }
    }

    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        TransitionValues transitionValues2 = transitionValues;
        if (isValidTarget(transitionValues2.view)) {
            Iterator<Transition> it = this.mTransitions.iterator();
            while (it.hasNext()) {
                Transition childTransition = it.next();
                if (childTransition.isValidTarget(transitionValues2.view)) {
                    childTransition.captureStartValues(transitionValues2);
                    boolean add = transitionValues2.mTargetedTransitions.add(childTransition);
                }
            }
        }
    }

    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        TransitionValues transitionValues2 = transitionValues;
        if (isValidTarget(transitionValues2.view)) {
            Iterator<Transition> it = this.mTransitions.iterator();
            while (it.hasNext()) {
                Transition childTransition = it.next();
                if (childTransition.isValidTarget(transitionValues2.view)) {
                    childTransition.captureEndValues(transitionValues2);
                    boolean add = transitionValues2.mTargetedTransitions.add(childTransition);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void capturePropagationValues(TransitionValues transitionValues) {
        TransitionValues transitionValues2 = transitionValues;
        super.capturePropagationValues(transitionValues2);
        int numTransitions = this.mTransitions.size();
        for (int i = 0; i < numTransitions; i++) {
            this.mTransitions.get(i).capturePropagationValues(transitionValues2);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void pause(View view) {
        View sceneRoot = view;
        super.pause(sceneRoot);
        int numTransitions = this.mTransitions.size();
        for (int i = 0; i < numTransitions; i++) {
            this.mTransitions.get(i).pause(sceneRoot);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void resume(View view) {
        View sceneRoot = view;
        super.resume(sceneRoot);
        int numTransitions = this.mTransitions.size();
        for (int i = 0; i < numTransitions; i++) {
            this.mTransitions.get(i).resume(sceneRoot);
        }
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void cancel() {
        super.cancel();
        int numTransitions = this.mTransitions.size();
        for (int i = 0; i < numTransitions; i++) {
            this.mTransitions.get(i).cancel();
        }
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void forceToEnd(ViewGroup viewGroup) {
        ViewGroup sceneRoot = viewGroup;
        super.forceToEnd(sceneRoot);
        int numTransitions = this.mTransitions.size();
        for (int i = 0; i < numTransitions; i++) {
            this.mTransitions.get(i).forceToEnd(sceneRoot);
        }
    }

    /* access modifiers changed from: package-private */
    public TransitionSet setSceneRoot(ViewGroup viewGroup) {
        ViewGroup sceneRoot = viewGroup;
        Transition sceneRoot2 = super.setSceneRoot(sceneRoot);
        int numTransitions = this.mTransitions.size();
        for (int i = 0; i < numTransitions; i++) {
            Transition sceneRoot3 = this.mTransitions.get(i).setSceneRoot(sceneRoot);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public void setCanRemoveViews(boolean z) {
        boolean canRemoveViews = z;
        super.setCanRemoveViews(canRemoveViews);
        int numTransitions = this.mTransitions.size();
        for (int i = 0; i < numTransitions; i++) {
            this.mTransitions.get(i).setCanRemoveViews(canRemoveViews);
        }
    }

    public void setPropagation(TransitionPropagation transitionPropagation) {
        TransitionPropagation propagation = transitionPropagation;
        super.setPropagation(propagation);
        this.mChangeFlags |= 2;
        int numTransitions = this.mTransitions.size();
        for (int i = 0; i < numTransitions; i++) {
            this.mTransitions.get(i).setPropagation(propagation);
        }
    }

    public void setEpicenterCallback(Transition.EpicenterCallback epicenterCallback) {
        Transition.EpicenterCallback epicenterCallback2 = epicenterCallback;
        super.setEpicenterCallback(epicenterCallback2);
        this.mChangeFlags |= 8;
        int numTransitions = this.mTransitions.size();
        for (int i = 0; i < numTransitions; i++) {
            this.mTransitions.get(i).setEpicenterCallback(epicenterCallback2);
        }
    }

    /* access modifiers changed from: package-private */
    public String toString(String str) {
        StringBuilder sb;
        StringBuilder sb2;
        String indent = str;
        String result = super.toString(indent);
        for (int i = 0; i < this.mTransitions.size(); i++) {
            new StringBuilder();
            StringBuilder append = sb.append(result).append("\n");
            new StringBuilder();
            result = append.append(this.mTransitions.get(i).toString(sb2.append(indent).append("  ").toString())).toString();
        }
        return result;
    }

    public Transition clone() {
        ArrayList<Transition> arrayList;
        TransitionSet clone = (TransitionSet) super.clone();
        new ArrayList<>();
        clone.mTransitions = arrayList;
        int numTransitions = this.mTransitions.size();
        for (int i = 0; i < numTransitions; i++) {
            TransitionSet addTransition = clone.addTransition(this.mTransitions.get(i).clone());
        }
        return clone;
    }
}
