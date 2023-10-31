package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.content.res.TypedArrayUtils;
import android.support.p000v4.util.C1642ArrayMap;
import android.support.p000v4.util.C1647LongSparseArray;
import android.support.p000v4.util.C1650SimpleArrayMap;
import android.support.p000v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.InflateException;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import gnu.kawa.functions.GetNamedPart;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public abstract class Transition implements Cloneable {
    static final boolean DBG = false;
    private static final int[] DEFAULT_MATCH_ORDER = {2, 1, 3, 4};
    private static final String LOG_TAG = "Transition";
    private static final int MATCH_FIRST = 1;
    public static final int MATCH_ID = 3;
    private static final String MATCH_ID_STR = "id";
    public static final int MATCH_INSTANCE = 1;
    private static final String MATCH_INSTANCE_STR = "instance";
    public static final int MATCH_ITEM_ID = 4;
    private static final String MATCH_ITEM_ID_STR = "itemId";
    private static final int MATCH_LAST = 4;
    public static final int MATCH_NAME = 2;
    private static final String MATCH_NAME_STR = "name";
    private static final PathMotion STRAIGHT_PATH_MOTION;
    private static ThreadLocal<C1642ArrayMap<Animator, AnimationInfo>> sRunningAnimators;
    private ArrayList<Animator> mAnimators;
    boolean mCanRemoveViews;
    ArrayList<Animator> mCurrentAnimators;
    long mDuration = -1;
    private TransitionValuesMaps mEndValues;
    private ArrayList<TransitionValues> mEndValuesList;
    private boolean mEnded;
    private EpicenterCallback mEpicenterCallback;
    private TimeInterpolator mInterpolator = null;
    private ArrayList<TransitionListener> mListeners;
    private int[] mMatchOrder;
    private String mName = getClass().getName();
    private C1642ArrayMap<String, String> mNameOverrides;
    private int mNumInstances;
    TransitionSet mParent;
    private PathMotion mPathMotion;
    private boolean mPaused;
    TransitionPropagation mPropagation;
    private ViewGroup mSceneRoot;
    private long mStartDelay = -1;
    private TransitionValuesMaps mStartValues;
    private ArrayList<TransitionValues> mStartValuesList;
    private ArrayList<View> mTargetChildExcludes;
    private ArrayList<View> mTargetExcludes;
    private ArrayList<Integer> mTargetIdChildExcludes;
    private ArrayList<Integer> mTargetIdExcludes;
    ArrayList<Integer> mTargetIds;
    private ArrayList<String> mTargetNameExcludes;
    private ArrayList<String> mTargetNames;
    private ArrayList<Class> mTargetTypeChildExcludes;
    private ArrayList<Class> mTargetTypeExcludes;
    private ArrayList<Class> mTargetTypes;
    ArrayList<View> mTargets;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MatchOrder {
    }

    public interface TransitionListener {
        void onTransitionCancel(@NonNull Transition transition);

        void onTransitionEnd(@NonNull Transition transition);

        void onTransitionPause(@NonNull Transition transition);

        void onTransitionResume(@NonNull Transition transition);

        void onTransitionStart(@NonNull Transition transition);
    }

    public abstract void captureEndValues(@NonNull TransitionValues transitionValues);

    public abstract void captureStartValues(@NonNull TransitionValues transitionValues);

    static {
        PathMotion pathMotion;
        ThreadLocal<C1642ArrayMap<Animator, AnimationInfo>> threadLocal;
        new PathMotion() {
            public Path getPath(float startX, float startY, float endX, float endY) {
                Path path;
                new Path();
                Path path2 = path;
                path2.moveTo(startX, startY);
                path2.lineTo(endX, endY);
                return path2;
            }
        };
        STRAIGHT_PATH_MOTION = pathMotion;
        new ThreadLocal<>();
        sRunningAnimators = threadLocal;
    }

    public Transition() {
        ArrayList<Integer> arrayList;
        ArrayList<View> arrayList2;
        TransitionValuesMaps transitionValuesMaps;
        TransitionValuesMaps transitionValuesMaps2;
        ArrayList<Animator> arrayList3;
        ArrayList<Animator> arrayList4;
        new ArrayList<>();
        this.mTargetIds = arrayList;
        new ArrayList<>();
        this.mTargets = arrayList2;
        this.mTargetNames = null;
        this.mTargetTypes = null;
        this.mTargetIdExcludes = null;
        this.mTargetExcludes = null;
        this.mTargetTypeExcludes = null;
        this.mTargetNameExcludes = null;
        this.mTargetIdChildExcludes = null;
        this.mTargetChildExcludes = null;
        this.mTargetTypeChildExcludes = null;
        new TransitionValuesMaps();
        this.mStartValues = transitionValuesMaps;
        new TransitionValuesMaps();
        this.mEndValues = transitionValuesMaps2;
        this.mParent = null;
        this.mMatchOrder = DEFAULT_MATCH_ORDER;
        this.mSceneRoot = null;
        this.mCanRemoveViews = false;
        new ArrayList<>();
        this.mCurrentAnimators = arrayList3;
        this.mNumInstances = 0;
        this.mPaused = false;
        this.mEnded = false;
        this.mListeners = null;
        new ArrayList<>();
        this.mAnimators = arrayList4;
        this.mPathMotion = STRAIGHT_PATH_MOTION;
    }

    public Transition(Context context, AttributeSet attributeSet) {
        ArrayList<Integer> arrayList;
        ArrayList<View> arrayList2;
        TransitionValuesMaps transitionValuesMaps;
        TransitionValuesMaps transitionValuesMaps2;
        ArrayList<Animator> arrayList3;
        ArrayList<Animator> arrayList4;
        Context context2 = context;
        AttributeSet attrs = attributeSet;
        new ArrayList<>();
        this.mTargetIds = arrayList;
        new ArrayList<>();
        this.mTargets = arrayList2;
        this.mTargetNames = null;
        this.mTargetTypes = null;
        this.mTargetIdExcludes = null;
        this.mTargetExcludes = null;
        this.mTargetTypeExcludes = null;
        this.mTargetNameExcludes = null;
        this.mTargetIdChildExcludes = null;
        this.mTargetChildExcludes = null;
        this.mTargetTypeChildExcludes = null;
        new TransitionValuesMaps();
        this.mStartValues = transitionValuesMaps;
        new TransitionValuesMaps();
        this.mEndValues = transitionValuesMaps2;
        this.mParent = null;
        this.mMatchOrder = DEFAULT_MATCH_ORDER;
        this.mSceneRoot = null;
        this.mCanRemoveViews = false;
        new ArrayList<>();
        this.mCurrentAnimators = arrayList3;
        this.mNumInstances = 0;
        this.mPaused = false;
        this.mEnded = false;
        this.mListeners = null;
        new ArrayList<>();
        this.mAnimators = arrayList4;
        this.mPathMotion = STRAIGHT_PATH_MOTION;
        TypedArray a = context2.obtainStyledAttributes(attrs, Styleable.TRANSITION);
        XmlResourceParser parser = (XmlResourceParser) attrs;
        long duration = (long) TypedArrayUtils.getNamedInt(a, parser, "duration", 1, -1);
        if (duration >= 0) {
            Transition duration2 = setDuration(duration);
        }
        long startDelay = (long) TypedArrayUtils.getNamedInt(a, parser, "startDelay", 2, -1);
        if (startDelay > 0) {
            Transition startDelay2 = setStartDelay(startDelay);
        }
        int resId = TypedArrayUtils.getNamedResourceId(a, parser, "interpolator", 0, 0);
        if (resId > 0) {
            Transition interpolator = setInterpolator(AnimationUtils.loadInterpolator(context2, resId));
        }
        String matchOrder = TypedArrayUtils.getNamedString(a, parser, "matchOrder", 3);
        if (matchOrder != null) {
            setMatchOrder(parseMatchOrder(matchOrder));
        }
        a.recycle();
    }

    private static int[] parseMatchOrder(String matchOrderString) {
        StringTokenizer stringTokenizer;
        Throwable th;
        StringBuilder sb;
        new StringTokenizer(matchOrderString, ",");
        StringTokenizer st = stringTokenizer;
        int[] matches = new int[st.countTokens()];
        int index = 0;
        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            if (MATCH_ID_STR.equalsIgnoreCase(token)) {
                matches[index] = 3;
            } else if (MATCH_INSTANCE_STR.equalsIgnoreCase(token)) {
                matches[index] = 1;
            } else if (MATCH_NAME_STR.equalsIgnoreCase(token)) {
                matches[index] = 2;
            } else if (MATCH_ITEM_ID_STR.equalsIgnoreCase(token)) {
                matches[index] = 4;
            } else if (token.isEmpty()) {
                int[] smallerMatches = new int[(matches.length - 1)];
                System.arraycopy(matches, 0, smallerMatches, 0, index);
                matches = smallerMatches;
                index--;
            } else {
                Throwable th2 = th;
                new StringBuilder();
                new InflateException(sb.append("Unknown match type in matchOrder: '").append(token).append("'").toString());
                throw th2;
            }
            index++;
        }
        return matches;
    }

    @NonNull
    public Transition setDuration(long duration) {
        this.mDuration = duration;
        return this;
    }

    public long getDuration() {
        return this.mDuration;
    }

    @NonNull
    public Transition setStartDelay(long startDelay) {
        this.mStartDelay = startDelay;
        return this;
    }

    public long getStartDelay() {
        return this.mStartDelay;
    }

    @NonNull
    public Transition setInterpolator(@Nullable TimeInterpolator interpolator) {
        this.mInterpolator = interpolator;
        return this;
    }

    @Nullable
    public TimeInterpolator getInterpolator() {
        return this.mInterpolator;
    }

    @Nullable
    public String[] getTransitionProperties() {
        return null;
    }

    @Nullable
    public Animator createAnimator(@NonNull ViewGroup viewGroup, @Nullable TransitionValues transitionValues, @Nullable TransitionValues transitionValues2) {
        ViewGroup viewGroup2 = viewGroup;
        TransitionValues transitionValues3 = transitionValues;
        TransitionValues transitionValues4 = transitionValues2;
        return null;
    }

    public void setMatchOrder(int... iArr) {
        Throwable th;
        Throwable th2;
        int[] matches = iArr;
        if (matches == null || matches.length == 0) {
            this.mMatchOrder = DEFAULT_MATCH_ORDER;
            return;
        }
        int i = 0;
        while (i < matches.length) {
            if (!isValidMatch(matches[i])) {
                Throwable th3 = th;
                new IllegalArgumentException("matches contains invalid value");
                throw th3;
            } else if (alreadyContains(matches, i)) {
                Throwable th4 = th2;
                new IllegalArgumentException("matches contains a duplicate value");
                throw th4;
            } else {
                i++;
            }
        }
        this.mMatchOrder = (int[]) matches.clone();
    }

    private static boolean isValidMatch(int i) {
        int match = i;
        return match >= 1 && match <= 4;
    }

    private static boolean alreadyContains(int[] iArr, int i) {
        int[] array = iArr;
        int searchIndex = i;
        int value = array[searchIndex];
        for (int i2 = 0; i2 < searchIndex; i2++) {
            if (array[i2] == value) {
                return true;
            }
        }
        return false;
    }

    private void matchInstances(C1642ArrayMap<View, TransitionValues> arrayMap, C1642ArrayMap<View, TransitionValues> arrayMap2) {
        TransitionValues end;
        C1642ArrayMap<View, TransitionValues> unmatchedStart = arrayMap;
        C1642ArrayMap<View, TransitionValues> unmatchedEnd = arrayMap2;
        for (int i = unmatchedStart.size() - 1; i >= 0; i--) {
            View view = unmatchedStart.keyAt(i);
            if (!(view == null || !isValidTarget(view) || (end = unmatchedEnd.remove(view)) == null || end.view == null || !isValidTarget(end.view))) {
                boolean add = this.mStartValuesList.add(unmatchedStart.removeAt(i));
                boolean add2 = this.mEndValuesList.add(end);
            }
        }
    }

    private void matchItemIds(C1642ArrayMap<View, TransitionValues> arrayMap, C1642ArrayMap<View, TransitionValues> arrayMap2, C1647LongSparseArray<View> longSparseArray, C1647LongSparseArray<View> longSparseArray2) {
        View endView;
        C1642ArrayMap<View, TransitionValues> unmatchedStart = arrayMap;
        C1642ArrayMap<View, TransitionValues> unmatchedEnd = arrayMap2;
        C1647LongSparseArray<View> startItemIds = longSparseArray;
        C1647LongSparseArray<View> endItemIds = longSparseArray2;
        int numStartIds = startItemIds.size();
        for (int i = 0; i < numStartIds; i++) {
            View startView = startItemIds.valueAt(i);
            if (startView != null && isValidTarget(startView) && (endView = endItemIds.get(startItemIds.keyAt(i))) != null && isValidTarget(endView)) {
                TransitionValues startValues = unmatchedStart.get(startView);
                TransitionValues endValues = unmatchedEnd.get(endView);
                if (!(startValues == null || endValues == null)) {
                    boolean add = this.mStartValuesList.add(startValues);
                    boolean add2 = this.mEndValuesList.add(endValues);
                    TransitionValues remove = unmatchedStart.remove(startView);
                    TransitionValues remove2 = unmatchedEnd.remove(endView);
                }
            }
        }
    }

    private void matchIds(C1642ArrayMap<View, TransitionValues> arrayMap, C1642ArrayMap<View, TransitionValues> arrayMap2, SparseArray<View> sparseArray, SparseArray<View> sparseArray2) {
        View endView;
        C1642ArrayMap<View, TransitionValues> unmatchedStart = arrayMap;
        C1642ArrayMap<View, TransitionValues> unmatchedEnd = arrayMap2;
        SparseArray<View> startIds = sparseArray;
        SparseArray<View> endIds = sparseArray2;
        int numStartIds = startIds.size();
        for (int i = 0; i < numStartIds; i++) {
            View startView = startIds.valueAt(i);
            if (startView != null && isValidTarget(startView) && (endView = endIds.get(startIds.keyAt(i))) != null && isValidTarget(endView)) {
                TransitionValues startValues = unmatchedStart.get(startView);
                TransitionValues endValues = unmatchedEnd.get(endView);
                if (!(startValues == null || endValues == null)) {
                    boolean add = this.mStartValuesList.add(startValues);
                    boolean add2 = this.mEndValuesList.add(endValues);
                    TransitionValues remove = unmatchedStart.remove(startView);
                    TransitionValues remove2 = unmatchedEnd.remove(endView);
                }
            }
        }
    }

    private void matchNames(C1642ArrayMap<View, TransitionValues> arrayMap, C1642ArrayMap<View, TransitionValues> arrayMap2, C1642ArrayMap<String, View> arrayMap3, C1642ArrayMap<String, View> arrayMap4) {
        View endView;
        C1642ArrayMap<View, TransitionValues> unmatchedStart = arrayMap;
        C1642ArrayMap<View, TransitionValues> unmatchedEnd = arrayMap2;
        C1642ArrayMap<String, View> startNames = arrayMap3;
        C1642ArrayMap<String, View> endNames = arrayMap4;
        int numStartNames = startNames.size();
        for (int i = 0; i < numStartNames; i++) {
            View startView = startNames.valueAt(i);
            if (startView != null && isValidTarget(startView) && (endView = endNames.get(startNames.keyAt(i))) != null && isValidTarget(endView)) {
                TransitionValues startValues = unmatchedStart.get(startView);
                TransitionValues endValues = unmatchedEnd.get(endView);
                if (!(startValues == null || endValues == null)) {
                    boolean add = this.mStartValuesList.add(startValues);
                    boolean add2 = this.mEndValuesList.add(endValues);
                    TransitionValues remove = unmatchedStart.remove(startView);
                    TransitionValues remove2 = unmatchedEnd.remove(endView);
                }
            }
        }
    }

    private void addUnmatched(C1642ArrayMap<View, TransitionValues> arrayMap, C1642ArrayMap<View, TransitionValues> arrayMap2) {
        C1642ArrayMap<View, TransitionValues> unmatchedStart = arrayMap;
        C1642ArrayMap<View, TransitionValues> unmatchedEnd = arrayMap2;
        for (int i = 0; i < unmatchedStart.size(); i++) {
            TransitionValues start = unmatchedStart.valueAt(i);
            if (isValidTarget(start.view)) {
                boolean add = this.mStartValuesList.add(start);
                boolean add2 = this.mEndValuesList.add((Object) null);
            }
        }
        for (int i2 = 0; i2 < unmatchedEnd.size(); i2++) {
            TransitionValues end = unmatchedEnd.valueAt(i2);
            if (isValidTarget(end.view)) {
                boolean add3 = this.mEndValuesList.add(end);
                boolean add4 = this.mStartValuesList.add((Object) null);
            }
        }
    }

    private void matchStartAndEnd(TransitionValuesMaps transitionValuesMaps, TransitionValuesMaps transitionValuesMaps2) {
        C1642ArrayMap arrayMap;
        C1642ArrayMap arrayMap2;
        TransitionValuesMaps startValues = transitionValuesMaps;
        TransitionValuesMaps endValues = transitionValuesMaps2;
        new C1642ArrayMap((C1650SimpleArrayMap) startValues.mViewValues);
        C1642ArrayMap arrayMap3 = arrayMap;
        new C1642ArrayMap((C1650SimpleArrayMap) endValues.mViewValues);
        C1642ArrayMap arrayMap4 = arrayMap2;
        for (int i = 0; i < this.mMatchOrder.length; i++) {
            switch (this.mMatchOrder[i]) {
                case 1:
                    matchInstances(arrayMap3, arrayMap4);
                    break;
                case 2:
                    matchNames(arrayMap3, arrayMap4, startValues.mNameValues, endValues.mNameValues);
                    break;
                case 3:
                    matchIds(arrayMap3, arrayMap4, startValues.mIdValues, endValues.mIdValues);
                    break;
                case 4:
                    matchItemIds(arrayMap3, arrayMap4, startValues.mItemIdValues, endValues.mItemIdValues);
                    break;
            }
        }
        addUnmatched(arrayMap3, arrayMap4);
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void createAnimators(ViewGroup viewGroup, TransitionValuesMaps transitionValuesMaps, TransitionValuesMaps transitionValuesMaps2, ArrayList<TransitionValues> arrayList, ArrayList<TransitionValues> arrayList2) {
        SparseIntArray sparseIntArray;
        View view;
        Object obj;
        TransitionValues transitionValues;
        ViewGroup sceneRoot = viewGroup;
        TransitionValuesMaps transitionValuesMaps3 = transitionValuesMaps;
        TransitionValuesMaps endValues = transitionValuesMaps2;
        ArrayList<TransitionValues> startValuesList = arrayList;
        ArrayList<TransitionValues> endValuesList = arrayList2;
        C1642ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
        long minStartDelay = Long.MAX_VALUE;
        new SparseIntArray();
        SparseIntArray startDelays = sparseIntArray;
        int startValuesListCount = startValuesList.size();
        for (int i = 0; i < startValuesListCount; i++) {
            TransitionValues start = startValuesList.get(i);
            TransitionValues end = endValuesList.get(i);
            if (start != null && !start.mTargetedTransitions.contains(this)) {
                start = null;
            }
            if (end != null && !end.mTargetedTransitions.contains(this)) {
                end = null;
            }
            if (start != null || end != null) {
                if (start == null || end == null || isTransitionRequired(start, end)) {
                    Animator animator = createAnimator(sceneRoot, start, end);
                    if (animator != null) {
                        TransitionValues infoValues = null;
                        if (end != null) {
                            view = end.view;
                            String[] properties = getTransitionProperties();
                            if (view != null && properties != null && properties.length > 0) {
                                new TransitionValues();
                                infoValues = transitionValues;
                                infoValues.view = view;
                                TransitionValues newValues = endValues.mViewValues.get(view);
                                if (newValues != null) {
                                    for (int j = 0; j < properties.length; j++) {
                                        Object put = infoValues.values.put(properties[j], newValues.values.get(properties[j]));
                                    }
                                }
                                int numExistingAnims = runningAnimators.size();
                                int j2 = 0;
                                while (true) {
                                    if (j2 >= numExistingAnims) {
                                        break;
                                    }
                                    AnimationInfo info = runningAnimators.get(runningAnimators.keyAt(j2));
                                    if (info.mValues != null && info.mView == view && info.mName.equals(getName()) && info.mValues.equals(infoValues)) {
                                        animator = null;
                                        break;
                                    }
                                    j2++;
                                }
                            }
                        } else {
                            view = start.view;
                        }
                        if (animator != null) {
                            if (this.mPropagation != null) {
                                long delay = this.mPropagation.getStartDelay(sceneRoot, this, start, end);
                                startDelays.put(this.mAnimators.size(), (int) delay);
                                minStartDelay = Math.min(delay, minStartDelay);
                            }
                            new AnimationInfo(view, getName(), this, ViewUtils.getWindowId(sceneRoot), infoValues);
                            AnimationInfo put2 = runningAnimators.put(animator, obj);
                            boolean add = this.mAnimators.add(animator);
                        }
                    }
                }
            }
        }
        if (minStartDelay != 0) {
            for (int i2 = 0; i2 < startDelays.size(); i2++) {
                Animator animator2 = this.mAnimators.get(startDelays.keyAt(i2));
                animator2.setStartDelay((((long) startDelays.valueAt(i2)) - minStartDelay) + animator2.getStartDelay());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isValidTarget(View view) {
        View target = view;
        int targetId = target.getId();
        if (this.mTargetIdExcludes != null && this.mTargetIdExcludes.contains(Integer.valueOf(targetId))) {
            return false;
        }
        if (this.mTargetExcludes != null && this.mTargetExcludes.contains(target)) {
            return false;
        }
        if (this.mTargetTypeExcludes != null) {
            int numTypes = this.mTargetTypeExcludes.size();
            for (int i = 0; i < numTypes; i++) {
                if (this.mTargetTypeExcludes.get(i).isInstance(target)) {
                    return false;
                }
            }
        }
        if (this.mTargetNameExcludes != null && ViewCompat.getTransitionName(target) != null && this.mTargetNameExcludes.contains(ViewCompat.getTransitionName(target))) {
            return false;
        }
        if (this.mTargetIds.size() == 0 && this.mTargets.size() == 0 && ((this.mTargetTypes == null || this.mTargetTypes.isEmpty()) && (this.mTargetNames == null || this.mTargetNames.isEmpty()))) {
            return true;
        }
        if (this.mTargetIds.contains(Integer.valueOf(targetId)) || this.mTargets.contains(target)) {
            return true;
        }
        if (this.mTargetNames != null && this.mTargetNames.contains(ViewCompat.getTransitionName(target))) {
            return true;
        }
        if (this.mTargetTypes != null) {
            for (int i2 = 0; i2 < this.mTargetTypes.size(); i2++) {
                if (this.mTargetTypes.get(i2).isInstance(target)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static C1642ArrayMap<Animator, AnimationInfo> getRunningAnimators() {
        C1642ArrayMap arrayMap;
        C1642ArrayMap arrayMap2 = sRunningAnimators.get();
        if (arrayMap2 == null) {
            new C1642ArrayMap();
            arrayMap2 = arrayMap;
            sRunningAnimators.set(arrayMap2);
        }
        return arrayMap2;
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void runAnimators() {
        start();
        C1642ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
        Iterator<Animator> it = this.mAnimators.iterator();
        while (it.hasNext()) {
            Animator anim = it.next();
            if (runningAnimators.containsKey(anim)) {
                start();
                runAnimator(anim, runningAnimators);
            }
        }
        this.mAnimators.clear();
        end();
    }

    private void runAnimator(Animator animator, C1642ArrayMap<Animator, AnimationInfo> arrayMap) {
        Animator.AnimatorListener animatorListener;
        Animator animator2 = animator;
        C1642ArrayMap<Animator, AnimationInfo> runningAnimators = arrayMap;
        if (animator2 != null) {
            final C1642ArrayMap<Animator, AnimationInfo> arrayMap2 = runningAnimators;
            new AnimatorListenerAdapter(this) {
                final /* synthetic */ Transition this$0;

                {
                    this.this$0 = this$0;
                }

                public void onAnimationStart(Animator animation) {
                    boolean add = this.this$0.mCurrentAnimators.add(animation);
                }

                public void onAnimationEnd(Animator animator) {
                    Animator animation = animator;
                    Object remove = arrayMap2.remove(animation);
                    boolean remove2 = this.this$0.mCurrentAnimators.remove(animation);
                }
            };
            animator2.addListener(animatorListener);
            animate(animator2);
        }
    }

    @NonNull
    public Transition addTarget(@NonNull View target) {
        boolean add = this.mTargets.add(target);
        return this;
    }

    @NonNull
    public Transition addTarget(@IdRes int i) {
        int targetId = i;
        if (targetId != 0) {
            boolean add = this.mTargetIds.add(Integer.valueOf(targetId));
        }
        return this;
    }

    @NonNull
    public Transition addTarget(@NonNull String str) {
        ArrayList<String> arrayList;
        String targetName = str;
        if (this.mTargetNames == null) {
            new ArrayList<>();
            this.mTargetNames = arrayList;
        }
        boolean add = this.mTargetNames.add(targetName);
        return this;
    }

    @NonNull
    public Transition addTarget(@NonNull Class cls) {
        ArrayList<Class> arrayList;
        Class targetType = cls;
        if (this.mTargetTypes == null) {
            new ArrayList<>();
            this.mTargetTypes = arrayList;
        }
        boolean add = this.mTargetTypes.add(targetType);
        return this;
    }

    @NonNull
    public Transition removeTarget(@NonNull View target) {
        boolean remove = this.mTargets.remove(target);
        return this;
    }

    @NonNull
    public Transition removeTarget(@IdRes int i) {
        int targetId = i;
        if (targetId != 0) {
            boolean remove = this.mTargetIds.remove(Integer.valueOf(targetId));
        }
        return this;
    }

    @NonNull
    public Transition removeTarget(@NonNull String str) {
        String targetName = str;
        if (this.mTargetNames != null) {
            boolean remove = this.mTargetNames.remove(targetName);
        }
        return this;
    }

    @NonNull
    public Transition removeTarget(@NonNull Class cls) {
        Class target = cls;
        if (this.mTargetTypes != null) {
            boolean remove = this.mTargetTypes.remove(target);
        }
        return this;
    }

    private static <T> ArrayList<T> excludeObject(ArrayList<T> arrayList, T t, boolean z) {
        ArrayList<T> list = arrayList;
        T target = t;
        boolean exclude = z;
        if (target != null) {
            if (exclude) {
                list = ArrayListManager.add(list, target);
            } else {
                list = ArrayListManager.remove(list, target);
            }
        }
        return list;
    }

    @NonNull
    public Transition excludeTarget(@NonNull View target, boolean exclude) {
        this.mTargetExcludes = excludeView(this.mTargetExcludes, target, exclude);
        return this;
    }

    @NonNull
    public Transition excludeTarget(@IdRes int targetId, boolean exclude) {
        this.mTargetIdExcludes = excludeId(this.mTargetIdExcludes, targetId, exclude);
        return this;
    }

    @NonNull
    public Transition excludeTarget(@NonNull String targetName, boolean exclude) {
        this.mTargetNameExcludes = excludeObject(this.mTargetNameExcludes, targetName, exclude);
        return this;
    }

    @NonNull
    public Transition excludeChildren(@NonNull View target, boolean exclude) {
        this.mTargetChildExcludes = excludeView(this.mTargetChildExcludes, target, exclude);
        return this;
    }

    @NonNull
    public Transition excludeChildren(@IdRes int targetId, boolean exclude) {
        this.mTargetIdChildExcludes = excludeId(this.mTargetIdChildExcludes, targetId, exclude);
        return this;
    }

    private ArrayList<Integer> excludeId(ArrayList<Integer> arrayList, int i, boolean z) {
        ArrayList<Integer> list = arrayList;
        int targetId = i;
        boolean exclude = z;
        if (targetId > 0) {
            if (exclude) {
                list = ArrayListManager.add(list, Integer.valueOf(targetId));
            } else {
                list = ArrayListManager.remove(list, Integer.valueOf(targetId));
            }
        }
        return list;
    }

    private ArrayList<View> excludeView(ArrayList<View> arrayList, View view, boolean z) {
        ArrayList<View> list = arrayList;
        View target = view;
        boolean exclude = z;
        if (target != null) {
            if (exclude) {
                list = ArrayListManager.add(list, target);
            } else {
                list = ArrayListManager.remove(list, target);
            }
        }
        return list;
    }

    @NonNull
    public Transition excludeTarget(@NonNull Class type, boolean exclude) {
        this.mTargetTypeExcludes = excludeType(this.mTargetTypeExcludes, type, exclude);
        return this;
    }

    @NonNull
    public Transition excludeChildren(@NonNull Class type, boolean exclude) {
        this.mTargetTypeChildExcludes = excludeType(this.mTargetTypeChildExcludes, type, exclude);
        return this;
    }

    private ArrayList<Class> excludeType(ArrayList<Class> arrayList, Class cls, boolean z) {
        ArrayList<Class> list = arrayList;
        Class type = cls;
        boolean exclude = z;
        if (type != null) {
            if (exclude) {
                list = ArrayListManager.add(list, type);
            } else {
                list = ArrayListManager.remove(list, type);
            }
        }
        return list;
    }

    @NonNull
    public List<Integer> getTargetIds() {
        return this.mTargetIds;
    }

    @NonNull
    public List<View> getTargets() {
        return this.mTargets;
    }

    @Nullable
    public List<String> getTargetNames() {
        return this.mTargetNames;
    }

    @Nullable
    public List<Class> getTargetTypes() {
        return this.mTargetTypes;
    }

    /* access modifiers changed from: package-private */
    public void captureValues(ViewGroup viewGroup, boolean z) {
        ArrayList arrayList;
        TransitionValues transitionValues;
        TransitionValues transitionValues2;
        ViewGroup sceneRoot = viewGroup;
        boolean start = z;
        clearValues(start);
        if ((this.mTargetIds.size() > 0 || this.mTargets.size() > 0) && ((this.mTargetNames == null || this.mTargetNames.isEmpty()) && (this.mTargetTypes == null || this.mTargetTypes.isEmpty()))) {
            for (int i = 0; i < this.mTargetIds.size(); i++) {
                View view = sceneRoot.findViewById(this.mTargetIds.get(i).intValue());
                if (view != null) {
                    new TransitionValues();
                    TransitionValues values = transitionValues2;
                    values.view = view;
                    if (start) {
                        captureStartValues(values);
                    } else {
                        captureEndValues(values);
                    }
                    boolean add = values.mTargetedTransitions.add(this);
                    capturePropagationValues(values);
                    if (start) {
                        addViewValues(this.mStartValues, view, values);
                    } else {
                        addViewValues(this.mEndValues, view, values);
                    }
                }
            }
            for (int i2 = 0; i2 < this.mTargets.size(); i2++) {
                View view2 = this.mTargets.get(i2);
                new TransitionValues();
                TransitionValues values2 = transitionValues;
                values2.view = view2;
                if (start) {
                    captureStartValues(values2);
                } else {
                    captureEndValues(values2);
                }
                boolean add2 = values2.mTargetedTransitions.add(this);
                capturePropagationValues(values2);
                if (start) {
                    addViewValues(this.mStartValues, view2, values2);
                } else {
                    addViewValues(this.mEndValues, view2, values2);
                }
            }
        } else {
            captureHierarchy(sceneRoot, start);
        }
        if (!start && this.mNameOverrides != null) {
            int numOverrides = this.mNameOverrides.size();
            new ArrayList(numOverrides);
            ArrayList arrayList2 = arrayList;
            for (int i3 = 0; i3 < numOverrides; i3++) {
                boolean add3 = arrayList2.add(this.mStartValues.mNameValues.remove(this.mNameOverrides.keyAt(i3)));
            }
            for (int i4 = 0; i4 < numOverrides; i4++) {
                View view3 = (View) arrayList2.get(i4);
                if (view3 != null) {
                    View put = this.mStartValues.mNameValues.put(this.mNameOverrides.valueAt(i4), view3);
                }
            }
        }
    }

    private static void addViewValues(TransitionValuesMaps transitionValuesMaps, View view, TransitionValues transitionValues) {
        TransitionValuesMaps transitionValuesMaps2 = transitionValuesMaps;
        View view2 = view;
        TransitionValues put = transitionValuesMaps2.mViewValues.put(view2, transitionValues);
        int id = view2.getId();
        if (id >= 0) {
            if (transitionValuesMaps2.mIdValues.indexOfKey(id) >= 0) {
                transitionValuesMaps2.mIdValues.put(id, (Object) null);
            } else {
                transitionValuesMaps2.mIdValues.put(id, view2);
            }
        }
        String name = ViewCompat.getTransitionName(view2);
        if (name != null) {
            if (transitionValuesMaps2.mNameValues.containsKey(name)) {
                View put2 = transitionValuesMaps2.mNameValues.put(name, null);
            } else {
                View put3 = transitionValuesMaps2.mNameValues.put(name, view2);
            }
        }
        if (view2.getParent() instanceof ListView) {
            ListView listview = (ListView) view2.getParent();
            if (listview.getAdapter().hasStableIds()) {
                long itemId = listview.getItemIdAtPosition(listview.getPositionForView(view2));
                if (transitionValuesMaps2.mItemIdValues.indexOfKey(itemId) >= 0) {
                    View alreadyMatched = transitionValuesMaps2.mItemIdValues.get(itemId);
                    if (alreadyMatched != null) {
                        ViewCompat.setHasTransientState(alreadyMatched, false);
                        transitionValuesMaps2.mItemIdValues.put(itemId, null);
                        return;
                    }
                    return;
                }
                ViewCompat.setHasTransientState(view2, true);
                transitionValuesMaps2.mItemIdValues.put(itemId, view2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void clearValues(boolean start) {
        if (start) {
            this.mStartValues.mViewValues.clear();
            this.mStartValues.mIdValues.clear();
            this.mStartValues.mItemIdValues.clear();
            return;
        }
        this.mEndValues.mViewValues.clear();
        this.mEndValues.mIdValues.clear();
        this.mEndValues.mItemIdValues.clear();
    }

    private void captureHierarchy(View view, boolean z) {
        TransitionValues transitionValues;
        View view2 = view;
        boolean start = z;
        if (view2 != null) {
            int id = view2.getId();
            if (this.mTargetIdExcludes != null && this.mTargetIdExcludes.contains(Integer.valueOf(id))) {
                return;
            }
            if (this.mTargetExcludes == null || !this.mTargetExcludes.contains(view2)) {
                if (this.mTargetTypeExcludes != null) {
                    int numTypes = this.mTargetTypeExcludes.size();
                    int i = 0;
                    while (i < numTypes) {
                        if (!this.mTargetTypeExcludes.get(i).isInstance(view2)) {
                            i++;
                        } else {
                            return;
                        }
                    }
                }
                if (view2.getParent() instanceof ViewGroup) {
                    new TransitionValues();
                    TransitionValues values = transitionValues;
                    values.view = view2;
                    if (start) {
                        captureStartValues(values);
                    } else {
                        captureEndValues(values);
                    }
                    boolean add = values.mTargetedTransitions.add(this);
                    capturePropagationValues(values);
                    if (start) {
                        addViewValues(this.mStartValues, view2, values);
                    } else {
                        addViewValues(this.mEndValues, view2, values);
                    }
                }
                if (!(view2 instanceof ViewGroup)) {
                    return;
                }
                if (this.mTargetIdChildExcludes != null && this.mTargetIdChildExcludes.contains(Integer.valueOf(id))) {
                    return;
                }
                if (this.mTargetChildExcludes == null || !this.mTargetChildExcludes.contains(view2)) {
                    if (this.mTargetTypeChildExcludes != null) {
                        int numTypes2 = this.mTargetTypeChildExcludes.size();
                        int i2 = 0;
                        while (i2 < numTypes2) {
                            if (!this.mTargetTypeChildExcludes.get(i2).isInstance(view2)) {
                                i2++;
                            } else {
                                return;
                            }
                        }
                    }
                    ViewGroup parent = (ViewGroup) view2;
                    for (int i3 = 0; i3 < parent.getChildCount(); i3++) {
                        captureHierarchy(parent.getChildAt(i3), start);
                    }
                }
            }
        }
    }

    @Nullable
    public TransitionValues getTransitionValues(@NonNull View view, boolean z) {
        View view2 = view;
        boolean start = z;
        if (this.mParent != null) {
            return this.mParent.getTransitionValues(view2, start);
        }
        return (start ? this.mStartValues : this.mEndValues).mViewValues.get(view2);
    }

    /* access modifiers changed from: package-private */
    public TransitionValues getMatchedTransitionValues(View view, boolean z) {
        View view2 = view;
        boolean viewInStart = z;
        if (this.mParent != null) {
            return this.mParent.getMatchedTransitionValues(view2, viewInStart);
        }
        ArrayList<TransitionValues> lookIn = viewInStart ? this.mStartValuesList : this.mEndValuesList;
        if (lookIn == null) {
            return null;
        }
        int count = lookIn.size();
        int index = -1;
        int i = 0;
        while (true) {
            if (i >= count) {
                break;
            }
            TransitionValues values = lookIn.get(i);
            if (values == null) {
                return null;
            }
            if (values.view == view2) {
                index = i;
                break;
            }
            i++;
        }
        TransitionValues values2 = null;
        if (index >= 0) {
            values2 = (viewInStart ? this.mEndValuesList : this.mStartValuesList).get(index);
        }
        return values2;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void pause(View view) {
        View sceneRoot = view;
        if (!this.mEnded) {
            C1642ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
            int numOldAnims = runningAnimators.size();
            WindowIdImpl windowId = ViewUtils.getWindowId(sceneRoot);
            for (int i = numOldAnims - 1; i >= 0; i--) {
                AnimationInfo info = runningAnimators.valueAt(i);
                if (info.mView != null && windowId.equals(info.mWindowId)) {
                    AnimatorUtils.pause(runningAnimators.keyAt(i));
                }
            }
            if (this.mListeners != null && this.mListeners.size() > 0) {
                ArrayList<TransitionListener> tmpListeners = (ArrayList) this.mListeners.clone();
                int numListeners = tmpListeners.size();
                for (int i2 = 0; i2 < numListeners; i2++) {
                    tmpListeners.get(i2).onTransitionPause(this);
                }
            }
            this.mPaused = true;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void resume(View view) {
        View sceneRoot = view;
        if (this.mPaused) {
            if (!this.mEnded) {
                C1642ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
                int numOldAnims = runningAnimators.size();
                WindowIdImpl windowId = ViewUtils.getWindowId(sceneRoot);
                for (int i = numOldAnims - 1; i >= 0; i--) {
                    AnimationInfo info = runningAnimators.valueAt(i);
                    if (info.mView != null && windowId.equals(info.mWindowId)) {
                        AnimatorUtils.resume(runningAnimators.keyAt(i));
                    }
                }
                if (this.mListeners != null && this.mListeners.size() > 0) {
                    ArrayList<TransitionListener> tmpListeners = (ArrayList) this.mListeners.clone();
                    int numListeners = tmpListeners.size();
                    for (int i2 = 0; i2 < numListeners; i2++) {
                        tmpListeners.get(i2).onTransitionResume(this);
                    }
                }
            }
            this.mPaused = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void playTransition(ViewGroup viewGroup) {
        ArrayList<TransitionValues> arrayList;
        ArrayList<TransitionValues> arrayList2;
        AnimationInfo oldInfo;
        ViewGroup sceneRoot = viewGroup;
        new ArrayList<>();
        this.mStartValuesList = arrayList;
        new ArrayList<>();
        this.mEndValuesList = arrayList2;
        matchStartAndEnd(this.mStartValues, this.mEndValues);
        C1642ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
        int numOldAnims = runningAnimators.size();
        WindowIdImpl windowId = ViewUtils.getWindowId(sceneRoot);
        for (int i = numOldAnims - 1; i >= 0; i--) {
            Animator anim = runningAnimators.keyAt(i);
            if (!(anim == null || (oldInfo = runningAnimators.get(anim)) == null || oldInfo.mView == null || !windowId.equals(oldInfo.mWindowId))) {
                TransitionValues oldValues = oldInfo.mValues;
                View oldView = oldInfo.mView;
                TransitionValues startValues = getTransitionValues(oldView, true);
                TransitionValues endValues = getMatchedTransitionValues(oldView, true);
                if (!(startValues == null && endValues == null) && oldInfo.mTransition.isTransitionRequired(oldValues, endValues)) {
                    if (anim.isRunning() || anim.isStarted()) {
                        anim.cancel();
                    } else {
                        AnimationInfo remove = runningAnimators.remove(anim);
                    }
                }
            }
        }
        createAnimators(sceneRoot, this.mStartValues, this.mEndValues, this.mStartValuesList, this.mEndValuesList);
        runAnimators();
    }

    public boolean isTransitionRequired(@Nullable TransitionValues transitionValues, @Nullable TransitionValues transitionValues2) {
        TransitionValues startValues = transitionValues;
        TransitionValues endValues = transitionValues2;
        boolean valuesChanged = false;
        if (startValues != null && endValues != null) {
            String[] properties = getTransitionProperties();
            if (properties == null) {
                Iterator<String> it = startValues.values.keySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (isValueChanged(startValues, endValues, it.next())) {
                        valuesChanged = true;
                        break;
                    }
                }
            } else {
                String[] strArr = properties;
                int length = strArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    if (isValueChanged(startValues, endValues, strArr[i])) {
                        valuesChanged = true;
                        break;
                    }
                    i++;
                }
            }
        }
        return valuesChanged;
    }

    private static boolean isValueChanged(TransitionValues oldValues, TransitionValues newValues, String str) {
        boolean changed;
        String key = str;
        Object oldValue = oldValues.values.get(key);
        Object newValue = newValues.values.get(key);
        if (oldValue == null && newValue == null) {
            changed = false;
        } else if (oldValue == null || newValue == null) {
            changed = true;
        } else {
            changed = !oldValue.equals(newValue);
        }
        return changed;
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void animate(Animator animator) {
        Animator.AnimatorListener animatorListener;
        Animator animator2 = animator;
        if (animator2 == null) {
            end();
            return;
        }
        if (getDuration() >= 0) {
            Animator duration = animator2.setDuration(getDuration());
        }
        if (getStartDelay() >= 0) {
            animator2.setStartDelay(getStartDelay());
        }
        if (getInterpolator() != null) {
            animator2.setInterpolator(getInterpolator());
        }
        new AnimatorListenerAdapter(this) {
            final /* synthetic */ Transition this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationEnd(Animator animation) {
                this.this$0.end();
                animation.removeListener(this);
            }
        };
        animator2.addListener(animatorListener);
        animator2.start();
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void start() {
        if (this.mNumInstances == 0) {
            if (this.mListeners != null && this.mListeners.size() > 0) {
                ArrayList<TransitionListener> tmpListeners = (ArrayList) this.mListeners.clone();
                int numListeners = tmpListeners.size();
                for (int i = 0; i < numListeners; i++) {
                    tmpListeners.get(i).onTransitionStart(this);
                }
            }
            this.mEnded = false;
        }
        this.mNumInstances++;
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void end() {
        this.mNumInstances--;
        if (this.mNumInstances == 0) {
            if (this.mListeners != null && this.mListeners.size() > 0) {
                ArrayList<TransitionListener> tmpListeners = (ArrayList) this.mListeners.clone();
                int numListeners = tmpListeners.size();
                for (int i = 0; i < numListeners; i++) {
                    tmpListeners.get(i).onTransitionEnd(this);
                }
            }
            for (int i2 = 0; i2 < this.mStartValues.mItemIdValues.size(); i2++) {
                View view = this.mStartValues.mItemIdValues.valueAt(i2);
                if (view != null) {
                    ViewCompat.setHasTransientState(view, false);
                }
            }
            for (int i3 = 0; i3 < this.mEndValues.mItemIdValues.size(); i3++) {
                View view2 = this.mEndValues.mItemIdValues.valueAt(i3);
                if (view2 != null) {
                    ViewCompat.setHasTransientState(view2, false);
                }
            }
            this.mEnded = true;
        }
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void forceToEnd(ViewGroup viewGroup) {
        ViewGroup sceneRoot = viewGroup;
        C1642ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
        int numOldAnims = runningAnimators.size();
        if (sceneRoot != null) {
            WindowIdImpl windowId = ViewUtils.getWindowId(sceneRoot);
            for (int i = numOldAnims - 1; i >= 0; i--) {
                AnimationInfo info = runningAnimators.valueAt(i);
                if (!(info.mView == null || windowId == null || !windowId.equals(info.mWindowId))) {
                    runningAnimators.keyAt(i).end();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void cancel() {
        for (int i = this.mCurrentAnimators.size() - 1; i >= 0; i--) {
            this.mCurrentAnimators.get(i).cancel();
        }
        if (this.mListeners != null && this.mListeners.size() > 0) {
            ArrayList<TransitionListener> tmpListeners = (ArrayList) this.mListeners.clone();
            int numListeners = tmpListeners.size();
            for (int i2 = 0; i2 < numListeners; i2++) {
                tmpListeners.get(i2).onTransitionCancel(this);
            }
        }
    }

    @NonNull
    public Transition addListener(@NonNull TransitionListener transitionListener) {
        ArrayList<TransitionListener> arrayList;
        TransitionListener listener = transitionListener;
        if (this.mListeners == null) {
            new ArrayList<>();
            this.mListeners = arrayList;
        }
        boolean add = this.mListeners.add(listener);
        return this;
    }

    @NonNull
    public Transition removeListener(@NonNull TransitionListener transitionListener) {
        TransitionListener listener = transitionListener;
        if (this.mListeners == null) {
            return this;
        }
        boolean remove = this.mListeners.remove(listener);
        if (this.mListeners.size() == 0) {
            this.mListeners = null;
        }
        return this;
    }

    public void setPathMotion(@Nullable PathMotion pathMotion) {
        PathMotion pathMotion2 = pathMotion;
        if (pathMotion2 == null) {
            this.mPathMotion = STRAIGHT_PATH_MOTION;
            return;
        }
        this.mPathMotion = pathMotion2;
    }

    @NonNull
    public PathMotion getPathMotion() {
        return this.mPathMotion;
    }

    public void setEpicenterCallback(@Nullable EpicenterCallback epicenterCallback) {
        EpicenterCallback epicenterCallback2 = epicenterCallback;
        this.mEpicenterCallback = epicenterCallback2;
    }

    @Nullable
    public EpicenterCallback getEpicenterCallback() {
        return this.mEpicenterCallback;
    }

    @Nullable
    public Rect getEpicenter() {
        if (this.mEpicenterCallback == null) {
            return null;
        }
        return this.mEpicenterCallback.onGetEpicenter(this);
    }

    public void setPropagation(@Nullable TransitionPropagation transitionPropagation) {
        TransitionPropagation transitionPropagation2 = transitionPropagation;
        this.mPropagation = transitionPropagation2;
    }

    @Nullable
    public TransitionPropagation getPropagation() {
        return this.mPropagation;
    }

    /* access modifiers changed from: package-private */
    public void capturePropagationValues(TransitionValues transitionValues) {
        String[] propertyNames;
        TransitionValues transitionValues2 = transitionValues;
        if (this.mPropagation != null && !transitionValues2.values.isEmpty() && (propertyNames = this.mPropagation.getPropagationProperties()) != null) {
            boolean containsAll = true;
            int i = 0;
            while (true) {
                if (i >= propertyNames.length) {
                    break;
                } else if (!transitionValues2.values.containsKey(propertyNames[i])) {
                    containsAll = false;
                    break;
                } else {
                    i++;
                }
            }
            if (!containsAll) {
                this.mPropagation.captureValues(transitionValues2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Transition setSceneRoot(ViewGroup sceneRoot) {
        this.mSceneRoot = sceneRoot;
        return this;
    }

    /* access modifiers changed from: package-private */
    public void setCanRemoveViews(boolean canRemoveViews) {
        boolean z = canRemoveViews;
        this.mCanRemoveViews = z;
    }

    public String toString() {
        return toString("");
    }

    public Transition clone() {
        ArrayList<Animator> arrayList;
        TransitionValuesMaps transitionValuesMaps;
        TransitionValuesMaps transitionValuesMaps2;
        try {
            Transition clone = (Transition) super.clone();
            new ArrayList();
            clone.mAnimators = arrayList;
            new TransitionValuesMaps();
            clone.mStartValues = transitionValuesMaps;
            new TransitionValuesMaps();
            clone.mEndValues = transitionValuesMaps2;
            clone.mStartValuesList = null;
            clone.mEndValuesList = null;
            return clone;
        } catch (CloneNotSupportedException e) {
            CloneNotSupportedException cloneNotSupportedException = e;
            return null;
        }
    }

    @NonNull
    public String getName() {
        return this.mName;
    }

    /* access modifiers changed from: package-private */
    public String toString(String indent) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        StringBuilder sb5;
        StringBuilder sb6;
        StringBuilder sb7;
        StringBuilder sb8;
        StringBuilder sb9;
        StringBuilder sb10;
        new StringBuilder();
        String result = sb.append(indent).append(getClass().getSimpleName()).append(GetNamedPart.CAST_METHOD_NAME).append(Integer.toHexString(hashCode())).append(": ").toString();
        if (this.mDuration != -1) {
            new StringBuilder();
            result = sb10.append(result).append("dur(").append(this.mDuration).append(") ").toString();
        }
        if (this.mStartDelay != -1) {
            new StringBuilder();
            result = sb9.append(result).append("dly(").append(this.mStartDelay).append(") ").toString();
        }
        if (this.mInterpolator != null) {
            new StringBuilder();
            result = sb8.append(result).append("interp(").append(this.mInterpolator).append(") ").toString();
        }
        if (this.mTargetIds.size() > 0 || this.mTargets.size() > 0) {
            new StringBuilder();
            String result2 = sb2.append(result).append("tgts(").toString();
            if (this.mTargetIds.size() > 0) {
                for (int i = 0; i < this.mTargetIds.size(); i++) {
                    if (i > 0) {
                        new StringBuilder();
                        result2 = sb7.append(result2).append(", ").toString();
                    }
                    new StringBuilder();
                    result2 = sb6.append(result2).append(this.mTargetIds.get(i)).toString();
                }
            }
            if (this.mTargets.size() > 0) {
                for (int i2 = 0; i2 < this.mTargets.size(); i2++) {
                    if (i2 > 0) {
                        new StringBuilder();
                        result2 = sb5.append(result2).append(", ").toString();
                    }
                    new StringBuilder();
                    result2 = sb4.append(result2).append(this.mTargets.get(i2)).toString();
                }
            }
            new StringBuilder();
            result = sb3.append(result2).append(")").toString();
        }
        return result;
    }

    private static class AnimationInfo {
        String mName;
        Transition mTransition;
        TransitionValues mValues;
        View mView;
        WindowIdImpl mWindowId;

        AnimationInfo(View view, String name, Transition transition, WindowIdImpl windowId, TransitionValues values) {
            this.mView = view;
            this.mName = name;
            this.mValues = values;
            this.mWindowId = windowId;
            this.mTransition = transition;
        }
    }

    private static class ArrayListManager {
        private ArrayListManager() {
        }

        static <T> ArrayList<T> add(ArrayList<T> arrayList, T t) {
            ArrayList<T> arrayList2;
            ArrayList<T> list = arrayList;
            T item = t;
            if (list == null) {
                new ArrayList<>();
                list = arrayList2;
            }
            if (!list.contains(item)) {
                boolean add = list.add(item);
            }
            return list;
        }

        static <T> ArrayList<T> remove(ArrayList<T> arrayList, T t) {
            ArrayList<T> list = arrayList;
            T item = t;
            if (list != null) {
                boolean remove = list.remove(item);
                if (list.isEmpty()) {
                    list = null;
                }
            }
            return list;
        }
    }

    public static abstract class EpicenterCallback {
        public abstract Rect onGetEpicenter(@NonNull Transition transition);

        public EpicenterCallback() {
        }
    }
}
