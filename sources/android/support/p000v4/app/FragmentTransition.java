package android.support.p000v4.app;

import android.graphics.Rect;
import android.os.Build;
import android.support.p000v4.app.BackStackRecord;
import android.support.p000v4.util.C1642ArrayMap;
import android.support.p000v4.view.ViewCompat;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* renamed from: android.support.v4.app.FragmentTransition */
class FragmentTransition {
    private static final int[] INVERSE_OPS = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8};
    private static final FragmentTransitionImpl PLATFORM_IMPL;
    private static final FragmentTransitionImpl SUPPORT_IMPL = resolveSupportImpl();

    static {
        FragmentTransitionImpl fragmentTransitionImpl;
        FragmentTransitionImpl fragmentTransitionImpl2;
        if (Build.VERSION.SDK_INT >= 21) {
            fragmentTransitionImpl = fragmentTransitionImpl2;
            new FragmentTransitionCompat21();
        } else {
            fragmentTransitionImpl = null;
        }
        PLATFORM_IMPL = fragmentTransitionImpl;
    }

    private static FragmentTransitionImpl resolveSupportImpl() {
        try {
            return (FragmentTransitionImpl) Class.forName("android.support.transition.FragmentTransitionSupport").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            Exception exc = e;
            return null;
        }
    }

    static void startTransitions(FragmentManagerImpl fragmentManagerImpl, ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, int i, int i2, boolean z) {
        SparseArray sparseArray;
        View view;
        FragmentManagerImpl fragmentManager = fragmentManagerImpl;
        ArrayList<BackStackRecord> records = arrayList;
        ArrayList<Boolean> isRecordPop = arrayList2;
        int startIndex = i;
        int endIndex = i2;
        boolean isReordered = z;
        if (fragmentManager.mCurState >= 1) {
            new SparseArray();
            SparseArray sparseArray2 = sparseArray;
            for (int i3 = startIndex; i3 < endIndex; i3++) {
                BackStackRecord record = records.get(i3);
                if (isRecordPop.get(i3).booleanValue()) {
                    calculatePopFragments(record, sparseArray2, isReordered);
                } else {
                    calculateFragments(record, sparseArray2, isReordered);
                }
            }
            if (sparseArray2.size() != 0) {
                new View(fragmentManager.mHost.getContext());
                View nonExistentView = view;
                int numContainers = sparseArray2.size();
                for (int i4 = 0; i4 < numContainers; i4++) {
                    int containerId = sparseArray2.keyAt(i4);
                    C1642ArrayMap<String, String> nameOverrides = calculateNameOverrides(containerId, records, isRecordPop, startIndex, endIndex);
                    FragmentContainerTransition containerTransition = (FragmentContainerTransition) sparseArray2.valueAt(i4);
                    if (isReordered) {
                        configureTransitionsReordered(fragmentManager, containerId, containerTransition, nonExistentView, nameOverrides);
                    } else {
                        configureTransitionsOrdered(fragmentManager, containerId, containerTransition, nonExistentView, nameOverrides);
                    }
                }
            }
        }
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.support.p000v4.util.C1642ArrayMap<java.lang.String, java.lang.String> calculateNameOverrides(int r22, java.util.ArrayList<android.support.p000v4.app.BackStackRecord> r23, java.util.ArrayList<java.lang.Boolean> r24, int r25, int r26) {
        /*
            r2 = r22
            r3 = r23
            r4 = r24
            r5 = r25
            r6 = r26
            android.support.v4.util.ArrayMap r18 = new android.support.v4.util.ArrayMap
            r21 = r18
            r18 = r21
            r19 = r21
            r19.<init>()
            r7 = r18
            r18 = r6
            r19 = 1
            int r18 = r18 + -1
            r8 = r18
        L_0x001f:
            r18 = r8
            r19 = r5
            r0 = r18
            r1 = r19
            if (r0 < r1) goto L_0x00e5
            r18 = r3
            r19 = r8
            java.lang.Object r18 = r18.get(r19)
            android.support.v4.app.BackStackRecord r18 = (android.support.p000v4.app.BackStackRecord) r18
            r9 = r18
            r18 = r9
            r19 = r2
            boolean r18 = r18.interactsWith(r19)
            if (r18 != 0) goto L_0x0042
        L_0x003f:
            int r8 = r8 + -1
            goto L_0x001f
        L_0x0042:
            r18 = r4
            r19 = r8
            java.lang.Object r18 = r18.get(r19)
            java.lang.Boolean r18 = (java.lang.Boolean) r18
            boolean r18 = r18.booleanValue()
            r10 = r18
            r18 = r9
            r0 = r18
            java.util.ArrayList<java.lang.String> r0 = r0.mSharedElementSourceNames
            r18 = r0
            if (r18 == 0) goto L_0x003f
            r18 = r9
            r0 = r18
            java.util.ArrayList<java.lang.String> r0 = r0.mSharedElementSourceNames
            r18 = r0
            int r18 = r18.size()
            r11 = r18
            r18 = r10
            if (r18 == 0) goto L_0x00c5
            r18 = r9
            r0 = r18
            java.util.ArrayList<java.lang.String> r0 = r0.mSharedElementSourceNames
            r18 = r0
            r13 = r18
            r18 = r9
            r0 = r18
            java.util.ArrayList<java.lang.String> r0 = r0.mSharedElementTargetNames
            r18 = r0
            r12 = r18
        L_0x0082:
            r18 = 0
            r14 = r18
        L_0x0086:
            r18 = r14
            r19 = r11
            r0 = r18
            r1 = r19
            if (r0 >= r1) goto L_0x003f
            r18 = r12
            r19 = r14
            java.lang.Object r18 = r18.get(r19)
            java.lang.String r18 = (java.lang.String) r18
            r15 = r18
            r18 = r13
            r19 = r14
            java.lang.Object r18 = r18.get(r19)
            java.lang.String r18 = (java.lang.String) r18
            r16 = r18
            r18 = r7
            r19 = r16
            java.lang.Object r18 = r18.remove(r19)
            java.lang.String r18 = (java.lang.String) r18
            r17 = r18
            r18 = r17
            if (r18 == 0) goto L_0x00da
            r18 = r7
            r19 = r15
            r20 = r17
            java.lang.Object r18 = r18.put(r19, r20)
        L_0x00c2:
            int r14 = r14 + 1
            goto L_0x0086
        L_0x00c5:
            r18 = r9
            r0 = r18
            java.util.ArrayList<java.lang.String> r0 = r0.mSharedElementSourceNames
            r18 = r0
            r12 = r18
            r18 = r9
            r0 = r18
            java.util.ArrayList<java.lang.String> r0 = r0.mSharedElementTargetNames
            r18 = r0
            r13 = r18
            goto L_0x0082
        L_0x00da:
            r18 = r7
            r19 = r15
            r20 = r16
            java.lang.Object r18 = r18.put(r19, r20)
            goto L_0x00c2
        L_0x00e5:
            r18 = r7
            r2 = r18
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentTransition.calculateNameOverrides(int, java.util.ArrayList, java.util.ArrayList, int, int):android.support.v4.util.ArrayMap");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0033, code lost:
        r7 = r3.lastIn;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void configureTransitionsReordered(android.support.p000v4.app.FragmentManagerImpl r31, int r32, android.support.p000v4.app.FragmentTransition.FragmentContainerTransition r33, android.view.View r34, android.support.p000v4.util.C1642ArrayMap<java.lang.String, java.lang.String> r35) {
        /*
            r1 = r31
            r2 = r32
            r3 = r33
            r4 = r34
            r5 = r35
            r21 = 0
            r6 = r21
            r21 = r1
            r0 = r21
            android.support.v4.app.FragmentContainer r0 = r0.mContainer
            r21 = r0
            boolean r21 = r21.onHasView()
            if (r21 == 0) goto L_0x002e
            r21 = r1
            r0 = r21
            android.support.v4.app.FragmentContainer r0 = r0.mContainer
            r21 = r0
            r22 = r2
            android.view.View r21 = r21.onFindViewById(r22)
            android.view.ViewGroup r21 = (android.view.ViewGroup) r21
            r6 = r21
        L_0x002e:
            r21 = r6
            if (r21 != 0) goto L_0x0033
        L_0x0032:
            return
        L_0x0033:
            r21 = r3
            r0 = r21
            android.support.v4.app.Fragment r0 = r0.lastIn
            r21 = r0
            r7 = r21
            r21 = r3
            r0 = r21
            android.support.v4.app.Fragment r0 = r0.firstOut
            r21 = r0
            r8 = r21
            r21 = r8
            r22 = r7
            android.support.v4.app.FragmentTransitionImpl r21 = chooseImpl(r21, r22)
            r9 = r21
            r21 = r9
            if (r21 != 0) goto L_0x0056
            goto L_0x0032
        L_0x0056:
            r21 = r3
            r0 = r21
            boolean r0 = r0.lastInIsPop
            r21 = r0
            r10 = r21
            r21 = r3
            r0 = r21
            boolean r0 = r0.firstOutIsPop
            r21 = r0
            r11 = r21
            java.util.ArrayList r21 = new java.util.ArrayList
            r30 = r21
            r21 = r30
            r22 = r30
            r22.<init>()
            r12 = r21
            java.util.ArrayList r21 = new java.util.ArrayList
            r30 = r21
            r21 = r30
            r22 = r30
            r22.<init>()
            r13 = r21
            r21 = r9
            r22 = r7
            r23 = r10
            java.lang.Object r21 = getEnterTransition(r21, r22, r23)
            r14 = r21
            r21 = r9
            r22 = r8
            r23 = r11
            java.lang.Object r21 = getExitTransition(r21, r22, r23)
            r15 = r21
            r21 = r9
            r22 = r6
            r23 = r4
            r24 = r5
            r25 = r3
            r26 = r13
            r27 = r12
            r28 = r14
            r29 = r15
            java.lang.Object r21 = configureSharedElementsReordered(r21, r22, r23, r24, r25, r26, r27, r28, r29)
            r16 = r21
            r21 = r14
            if (r21 != 0) goto L_0x00c2
            r21 = r16
            if (r21 != 0) goto L_0x00c2
            r21 = r15
            if (r21 != 0) goto L_0x00c2
            goto L_0x0032
        L_0x00c2:
            r21 = r9
            r22 = r15
            r23 = r8
            r24 = r13
            r25 = r4
            java.util.ArrayList r21 = configureEnteringExitingViews(r21, r22, r23, r24, r25)
            r17 = r21
            r21 = r9
            r22 = r14
            r23 = r7
            r24 = r12
            r25 = r4
            java.util.ArrayList r21 = configureEnteringExitingViews(r21, r22, r23, r24, r25)
            r18 = r21
            r21 = r18
            r22 = 4
            setViewVisibility(r21, r22)
            r21 = r9
            r22 = r14
            r23 = r15
            r24 = r16
            r25 = r7
            r26 = r10
            java.lang.Object r21 = mergeTransitions(r21, r22, r23, r24, r25, r26)
            r19 = r21
            r21 = r19
            if (r21 == 0) goto L_0x0151
            r21 = r9
            r22 = r15
            r23 = r8
            r24 = r17
            replaceHide(r21, r22, r23, r24)
            r21 = r9
            r22 = r12
            java.util.ArrayList r21 = r21.prepareSetNameOverridesReordered(r22)
            r20 = r21
            r21 = r9
            r22 = r19
            r23 = r14
            r24 = r18
            r25 = r15
            r26 = r17
            r27 = r16
            r28 = r12
            r21.scheduleRemoveTargets(r22, r23, r24, r25, r26, r27, r28)
            r21 = r9
            r22 = r6
            r23 = r19
            r21.beginDelayedTransition(r22, r23)
            r21 = r9
            r22 = r6
            r23 = r13
            r24 = r12
            r25 = r20
            r26 = r5
            r21.setNameOverridesReordered(r22, r23, r24, r25, r26)
            r21 = r18
            r22 = 0
            setViewVisibility(r21, r22)
            r21 = r9
            r22 = r16
            r23 = r13
            r24 = r12
            r21.swapSharedElementTargets(r22, r23, r24)
        L_0x0151:
            goto L_0x0032
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentTransition.configureTransitionsReordered(android.support.v4.app.FragmentManagerImpl, int, android.support.v4.app.FragmentTransition$FragmentContainerTransition, android.view.View, android.support.v4.util.ArrayMap):void");
    }

    private static void replaceHide(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Fragment fragment, ArrayList<View> arrayList) {
        Runnable runnable;
        FragmentTransitionImpl impl = fragmentTransitionImpl;
        Object exitTransition = obj;
        Fragment exitingFragment = fragment;
        ArrayList<View> exitingViews = arrayList;
        if (exitingFragment != null && exitTransition != null && exitingFragment.mAdded && exitingFragment.mHidden && exitingFragment.mHiddenChanged) {
            exitingFragment.setHideReplaced(true);
            impl.scheduleHideFragmentView(exitTransition, exitingFragment.getView(), exitingViews);
            final ArrayList<View> arrayList2 = exitingViews;
            new Runnable() {
                public void run() {
                    FragmentTransition.setViewVisibility(arrayList2, 4);
                }
            };
            OneShotPreDrawListener add = OneShotPreDrawListener.add(exitingFragment.mContainer, runnable);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0033, code lost:
        r7 = r3.lastIn;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void configureTransitionsOrdered(android.support.p000v4.app.FragmentManagerImpl r30, int r31, android.support.p000v4.app.FragmentTransition.FragmentContainerTransition r32, android.view.View r33, android.support.p000v4.util.C1642ArrayMap<java.lang.String, java.lang.String> r34) {
        /*
            r1 = r30
            r2 = r31
            r3 = r32
            r4 = r33
            r5 = r34
            r20 = 0
            r6 = r20
            r20 = r1
            r0 = r20
            android.support.v4.app.FragmentContainer r0 = r0.mContainer
            r20 = r0
            boolean r20 = r20.onHasView()
            if (r20 == 0) goto L_0x002e
            r20 = r1
            r0 = r20
            android.support.v4.app.FragmentContainer r0 = r0.mContainer
            r20 = r0
            r21 = r2
            android.view.View r20 = r20.onFindViewById(r21)
            android.view.ViewGroup r20 = (android.view.ViewGroup) r20
            r6 = r20
        L_0x002e:
            r20 = r6
            if (r20 != 0) goto L_0x0033
        L_0x0032:
            return
        L_0x0033:
            r20 = r3
            r0 = r20
            android.support.v4.app.Fragment r0 = r0.lastIn
            r20 = r0
            r7 = r20
            r20 = r3
            r0 = r20
            android.support.v4.app.Fragment r0 = r0.firstOut
            r20 = r0
            r8 = r20
            r20 = r8
            r21 = r7
            android.support.v4.app.FragmentTransitionImpl r20 = chooseImpl(r20, r21)
            r9 = r20
            r20 = r9
            if (r20 != 0) goto L_0x0056
            goto L_0x0032
        L_0x0056:
            r20 = r3
            r0 = r20
            boolean r0 = r0.lastInIsPop
            r20 = r0
            r10 = r20
            r20 = r3
            r0 = r20
            boolean r0 = r0.firstOutIsPop
            r20 = r0
            r11 = r20
            r20 = r9
            r21 = r7
            r22 = r10
            java.lang.Object r20 = getEnterTransition(r20, r21, r22)
            r12 = r20
            r20 = r9
            r21 = r8
            r22 = r11
            java.lang.Object r20 = getExitTransition(r20, r21, r22)
            r13 = r20
            java.util.ArrayList r20 = new java.util.ArrayList
            r29 = r20
            r20 = r29
            r21 = r29
            r21.<init>()
            r14 = r20
            java.util.ArrayList r20 = new java.util.ArrayList
            r29 = r20
            r20 = r29
            r21 = r29
            r21.<init>()
            r15 = r20
            r20 = r9
            r21 = r6
            r22 = r4
            r23 = r5
            r24 = r3
            r25 = r14
            r26 = r15
            r27 = r12
            r28 = r13
            java.lang.Object r20 = configureSharedElementsOrdered(r20, r21, r22, r23, r24, r25, r26, r27, r28)
            r16 = r20
            r20 = r12
            if (r20 != 0) goto L_0x00c2
            r20 = r16
            if (r20 != 0) goto L_0x00c2
            r20 = r13
            if (r20 != 0) goto L_0x00c2
            goto L_0x0032
        L_0x00c2:
            r20 = r9
            r21 = r13
            r22 = r8
            r23 = r14
            r24 = r4
            java.util.ArrayList r20 = configureEnteringExitingViews(r20, r21, r22, r23, r24)
            r17 = r20
            r20 = r17
            if (r20 == 0) goto L_0x00de
            r20 = r17
            boolean r20 = r20.isEmpty()
            if (r20 == 0) goto L_0x00e2
        L_0x00de:
            r20 = 0
            r13 = r20
        L_0x00e2:
            r20 = r9
            r21 = r12
            r22 = r4
            r20.addTarget(r21, r22)
            r20 = r9
            r21 = r12
            r22 = r13
            r23 = r16
            r24 = r7
            r25 = r3
            r0 = r25
            boolean r0 = r0.lastInIsPop
            r25 = r0
            java.lang.Object r20 = mergeTransitions(r20, r21, r22, r23, r24, r25)
            r18 = r20
            r20 = r18
            if (r20 == 0) goto L_0x015b
            java.util.ArrayList r20 = new java.util.ArrayList
            r29 = r20
            r20 = r29
            r21 = r29
            r21.<init>()
            r19 = r20
            r20 = r9
            r21 = r18
            r22 = r12
            r23 = r19
            r24 = r13
            r25 = r17
            r26 = r16
            r27 = r15
            r20.scheduleRemoveTargets(r21, r22, r23, r24, r25, r26, r27)
            r20 = r9
            r21 = r6
            r22 = r7
            r23 = r4
            r24 = r15
            r25 = r12
            r26 = r19
            r27 = r13
            r28 = r17
            scheduleTargetChange(r20, r21, r22, r23, r24, r25, r26, r27, r28)
            r20 = r9
            r21 = r6
            r22 = r15
            r23 = r5
            r20.setNameOverridesOrdered(r21, r22, r23)
            r20 = r9
            r21 = r6
            r22 = r18
            r20.beginDelayedTransition(r21, r22)
            r20 = r9
            r21 = r6
            r22 = r15
            r23 = r5
            r20.scheduleNameReset(r21, r22, r23)
        L_0x015b:
            goto L_0x0032
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentTransition.configureTransitionsOrdered(android.support.v4.app.FragmentManagerImpl, int, android.support.v4.app.FragmentTransition$FragmentContainerTransition, android.view.View, android.support.v4.util.ArrayMap):void");
    }

    private static void scheduleTargetChange(FragmentTransitionImpl impl, ViewGroup sceneRoot, Fragment inFragment, View nonExistentView, ArrayList<View> sharedElementsIn, Object enterTransition, ArrayList<View> enteringViews, Object exitTransition, ArrayList<View> exitingViews) {
        Runnable runnable;
        final Object obj = enterTransition;
        final FragmentTransitionImpl fragmentTransitionImpl = impl;
        final View view = nonExistentView;
        final Fragment fragment = inFragment;
        final ArrayList<View> arrayList = sharedElementsIn;
        final ArrayList<View> arrayList2 = enteringViews;
        final ArrayList<View> arrayList3 = exitingViews;
        final Object obj2 = exitTransition;
        new Runnable() {
            public void run() {
                ArrayList arrayList;
                if (obj != null) {
                    fragmentTransitionImpl.removeTarget(obj, view);
                    boolean addAll = arrayList2.addAll(FragmentTransition.configureEnteringExitingViews(fragmentTransitionImpl, obj, fragment, arrayList, view));
                }
                if (arrayList3 != null) {
                    if (obj2 != null) {
                        new ArrayList();
                        ArrayList arrayList2 = arrayList;
                        boolean add = arrayList2.add(view);
                        fragmentTransitionImpl.replaceTargets(obj2, arrayList3, arrayList2);
                    }
                    arrayList3.clear();
                    boolean add2 = arrayList3.add(view);
                }
            }
        };
        OneShotPreDrawListener add = OneShotPreDrawListener.add(sceneRoot, runnable);
    }

    private static FragmentTransitionImpl chooseImpl(Fragment fragment, Fragment fragment2) {
        ArrayList arrayList;
        Throwable th;
        Fragment outFragment = fragment;
        Fragment inFragment = fragment2;
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        if (outFragment != null) {
            Object exitTransition = outFragment.getExitTransition();
            if (exitTransition != null) {
                boolean add = arrayList2.add(exitTransition);
            }
            Object returnTransition = outFragment.getReturnTransition();
            if (returnTransition != null) {
                boolean add2 = arrayList2.add(returnTransition);
            }
            Object sharedReturnTransition = outFragment.getSharedElementReturnTransition();
            if (sharedReturnTransition != null) {
                boolean add3 = arrayList2.add(sharedReturnTransition);
            }
        }
        if (inFragment != null) {
            Object enterTransition = inFragment.getEnterTransition();
            if (enterTransition != null) {
                boolean add4 = arrayList2.add(enterTransition);
            }
            Object reenterTransition = inFragment.getReenterTransition();
            if (reenterTransition != null) {
                boolean add5 = arrayList2.add(reenterTransition);
            }
            Object sharedEnterTransition = inFragment.getSharedElementEnterTransition();
            if (sharedEnterTransition != null) {
                boolean add6 = arrayList2.add(sharedEnterTransition);
            }
        }
        if (arrayList2.isEmpty()) {
            return null;
        }
        if (PLATFORM_IMPL != null && canHandleAll(PLATFORM_IMPL, arrayList2)) {
            return PLATFORM_IMPL;
        }
        if (SUPPORT_IMPL != null && canHandleAll(SUPPORT_IMPL, arrayList2)) {
            return SUPPORT_IMPL;
        }
        if (PLATFORM_IMPL == null && SUPPORT_IMPL == null) {
            return null;
        }
        Throwable th2 = th;
        new IllegalArgumentException("Invalid Transition types");
        throw th2;
    }

    private static boolean canHandleAll(FragmentTransitionImpl fragmentTransitionImpl, List<Object> list) {
        FragmentTransitionImpl impl = fragmentTransitionImpl;
        List<Object> transitions = list;
        int size = transitions.size();
        for (int i = 0; i < size; i++) {
            if (!impl.canHandle(transitions.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static Object getSharedElementTransition(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, Fragment fragment2, boolean z) {
        Object sharedElementEnterTransition;
        FragmentTransitionImpl impl = fragmentTransitionImpl;
        Fragment inFragment = fragment;
        Fragment outFragment = fragment2;
        boolean isPop = z;
        if (inFragment == null || outFragment == null) {
            return null;
        }
        FragmentTransitionImpl fragmentTransitionImpl2 = impl;
        if (isPop) {
            sharedElementEnterTransition = outFragment.getSharedElementReturnTransition();
        } else {
            sharedElementEnterTransition = inFragment.getSharedElementEnterTransition();
        }
        return impl.wrapTransitionInSet(fragmentTransitionImpl2.cloneTransition(sharedElementEnterTransition));
    }

    private static Object getEnterTransition(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, boolean z) {
        Object enterTransition;
        FragmentTransitionImpl impl = fragmentTransitionImpl;
        Fragment inFragment = fragment;
        boolean isPop = z;
        if (inFragment == null) {
            return null;
        }
        FragmentTransitionImpl fragmentTransitionImpl2 = impl;
        if (isPop) {
            enterTransition = inFragment.getReenterTransition();
        } else {
            enterTransition = inFragment.getEnterTransition();
        }
        return fragmentTransitionImpl2.cloneTransition(enterTransition);
    }

    private static Object getExitTransition(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, boolean z) {
        Object exitTransition;
        FragmentTransitionImpl impl = fragmentTransitionImpl;
        Fragment outFragment = fragment;
        boolean isPop = z;
        if (outFragment == null) {
            return null;
        }
        FragmentTransitionImpl fragmentTransitionImpl2 = impl;
        if (isPop) {
            exitTransition = outFragment.getReturnTransition();
        } else {
            exitTransition = outFragment.getExitTransition();
        }
        return fragmentTransitionImpl2.cloneTransition(exitTransition);
    }

    private static Object configureSharedElementsReordered(FragmentTransitionImpl fragmentTransitionImpl, ViewGroup viewGroup, View view, C1642ArrayMap<String, String> arrayMap, FragmentContainerTransition fragmentContainerTransition, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object sharedElementTransition;
        Rect epicenter;
        View epicenterView;
        Runnable runnable;
        Rect rect;
        FragmentTransitionImpl impl = fragmentTransitionImpl;
        ViewGroup sceneRoot = viewGroup;
        View nonExistentView = view;
        C1642ArrayMap<String, String> nameOverrides = arrayMap;
        FragmentContainerTransition fragments = fragmentContainerTransition;
        ArrayList<View> sharedElementsOut = arrayList;
        ArrayList<View> sharedElementsIn = arrayList2;
        Object enterTransition = obj;
        Object exitTransition = obj2;
        Fragment inFragment = fragments.lastIn;
        Fragment outFragment = fragments.firstOut;
        if (inFragment != null) {
            inFragment.getView().setVisibility(0);
        }
        if (inFragment == null || outFragment == null) {
            return null;
        }
        boolean inIsPop = fragments.lastInIsPop;
        if (nameOverrides.isEmpty()) {
            sharedElementTransition = null;
        } else {
            sharedElementTransition = getSharedElementTransition(impl, inFragment, outFragment, inIsPop);
        }
        Object sharedElementTransition2 = sharedElementTransition;
        C1642ArrayMap<String, View> outSharedElements = captureOutSharedElements(impl, nameOverrides, sharedElementTransition2, fragments);
        C1642ArrayMap<String, View> inSharedElements = captureInSharedElements(impl, nameOverrides, sharedElementTransition2, fragments);
        if (nameOverrides.isEmpty()) {
            sharedElementTransition2 = null;
            if (outSharedElements != null) {
                outSharedElements.clear();
            }
            if (inSharedElements != null) {
                inSharedElements.clear();
            }
        } else {
            addSharedElementsWithMatchingNames(sharedElementsOut, outSharedElements, nameOverrides.keySet());
            addSharedElementsWithMatchingNames(sharedElementsIn, inSharedElements, nameOverrides.values());
        }
        if (enterTransition == null && exitTransition == null && sharedElementTransition2 == null) {
            return null;
        }
        callSharedElementStartEnd(inFragment, outFragment, inIsPop, outSharedElements, true);
        if (sharedElementTransition2 != null) {
            boolean add = sharedElementsIn.add(nonExistentView);
            impl.setSharedElementTargets(sharedElementTransition2, nonExistentView, sharedElementsOut);
            setOutEpicenter(impl, sharedElementTransition2, exitTransition, outSharedElements, fragments.firstOutIsPop, fragments.firstOutTransaction);
            new Rect();
            epicenter = rect;
            epicenterView = getInEpicenterView(inSharedElements, fragments, enterTransition, inIsPop);
            if (epicenterView != null) {
                impl.setEpicenter(enterTransition, epicenter);
            }
        } else {
            epicenter = null;
            epicenterView = null;
        }
        final Fragment fragment = inFragment;
        final Fragment fragment2 = outFragment;
        final boolean z = inIsPop;
        final C1642ArrayMap<String, View> arrayMap2 = inSharedElements;
        final View view2 = epicenterView;
        final FragmentTransitionImpl fragmentTransitionImpl2 = impl;
        final Rect rect2 = epicenter;
        new Runnable() {
            public void run() {
                FragmentTransition.callSharedElementStartEnd(fragment, fragment2, z, arrayMap2, false);
                if (view2 != null) {
                    fragmentTransitionImpl2.getBoundsOnScreen(view2, rect2);
                }
            }
        };
        OneShotPreDrawListener add2 = OneShotPreDrawListener.add(sceneRoot, runnable);
        return sharedElementTransition2;
    }

    private static void addSharedElementsWithMatchingNames(ArrayList<View> arrayList, C1642ArrayMap<String, View> arrayMap, Collection<String> collection) {
        ArrayList<View> views = arrayList;
        C1642ArrayMap<String, View> sharedElements = arrayMap;
        Collection<String> nameOverridesSet = collection;
        for (int i = sharedElements.size() - 1; i >= 0; i--) {
            View view = sharedElements.valueAt(i);
            if (nameOverridesSet.contains(ViewCompat.getTransitionName(view))) {
                boolean add = views.add(view);
            }
        }
    }

    private static Object configureSharedElementsOrdered(FragmentTransitionImpl fragmentTransitionImpl, ViewGroup viewGroup, View view, C1642ArrayMap<String, String> arrayMap, FragmentContainerTransition fragmentContainerTransition, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object sharedElementTransition;
        Rect inEpicenter;
        Runnable runnable;
        Rect rect;
        FragmentTransitionImpl impl = fragmentTransitionImpl;
        ViewGroup sceneRoot = viewGroup;
        View nonExistentView = view;
        C1642ArrayMap<String, String> nameOverrides = arrayMap;
        FragmentContainerTransition fragments = fragmentContainerTransition;
        ArrayList<View> sharedElementsOut = arrayList;
        ArrayList<View> sharedElementsIn = arrayList2;
        Object enterTransition = obj;
        Object exitTransition = obj2;
        Fragment inFragment = fragments.lastIn;
        Fragment outFragment = fragments.firstOut;
        if (inFragment == null || outFragment == null) {
            return null;
        }
        boolean inIsPop = fragments.lastInIsPop;
        if (nameOverrides.isEmpty()) {
            sharedElementTransition = null;
        } else {
            sharedElementTransition = getSharedElementTransition(impl, inFragment, outFragment, inIsPop);
        }
        Object sharedElementTransition2 = sharedElementTransition;
        C1642ArrayMap<String, View> outSharedElements = captureOutSharedElements(impl, nameOverrides, sharedElementTransition2, fragments);
        if (nameOverrides.isEmpty()) {
            sharedElementTransition2 = null;
        } else {
            boolean addAll = sharedElementsOut.addAll(outSharedElements.values());
        }
        if (enterTransition == null && exitTransition == null && sharedElementTransition2 == null) {
            return null;
        }
        callSharedElementStartEnd(inFragment, outFragment, inIsPop, outSharedElements, true);
        if (sharedElementTransition2 != null) {
            new Rect();
            inEpicenter = rect;
            impl.setSharedElementTargets(sharedElementTransition2, nonExistentView, sharedElementsOut);
            setOutEpicenter(impl, sharedElementTransition2, exitTransition, outSharedElements, fragments.firstOutIsPop, fragments.firstOutTransaction);
            if (enterTransition != null) {
                impl.setEpicenter(enterTransition, inEpicenter);
            }
        } else {
            inEpicenter = null;
        }
        final FragmentTransitionImpl fragmentTransitionImpl2 = impl;
        final C1642ArrayMap<String, String> arrayMap2 = nameOverrides;
        final Object obj3 = sharedElementTransition2;
        final FragmentContainerTransition fragmentContainerTransition2 = fragments;
        final ArrayList<View> arrayList3 = sharedElementsIn;
        final View view2 = nonExistentView;
        final Fragment fragment = inFragment;
        final Fragment fragment2 = outFragment;
        final boolean z = inIsPop;
        final ArrayList<View> arrayList4 = sharedElementsOut;
        final Object obj4 = enterTransition;
        final Rect rect2 = inEpicenter;
        new Runnable() {
            public void run() {
                C1642ArrayMap<String, View> inSharedElements = FragmentTransition.captureInSharedElements(fragmentTransitionImpl2, arrayMap2, obj3, fragmentContainerTransition2);
                if (inSharedElements != null) {
                    boolean addAll = arrayList3.addAll(inSharedElements.values());
                    boolean add = arrayList3.add(view2);
                }
                FragmentTransition.callSharedElementStartEnd(fragment, fragment2, z, inSharedElements, false);
                if (obj3 != null) {
                    fragmentTransitionImpl2.swapSharedElementTargets(obj3, arrayList4, arrayList3);
                    View inEpicenterView = FragmentTransition.getInEpicenterView(inSharedElements, fragmentContainerTransition2, obj4, z);
                    if (inEpicenterView != null) {
                        fragmentTransitionImpl2.getBoundsOnScreen(inEpicenterView, rect2);
                    }
                }
            }
        };
        OneShotPreDrawListener add = OneShotPreDrawListener.add(sceneRoot, runnable);
        return sharedElementTransition2;
    }

    private static C1642ArrayMap<String, View> captureOutSharedElements(FragmentTransitionImpl fragmentTransitionImpl, C1642ArrayMap<String, String> arrayMap, Object obj, FragmentContainerTransition fragmentContainerTransition) {
        C1642ArrayMap arrayMap2;
        SharedElementCallback sharedElementCallback;
        ArrayList<String> names;
        FragmentTransitionImpl impl = fragmentTransitionImpl;
        C1642ArrayMap<String, String> nameOverrides = arrayMap;
        Object sharedElementTransition = obj;
        FragmentContainerTransition fragments = fragmentContainerTransition;
        if (nameOverrides.isEmpty() || sharedElementTransition == null) {
            nameOverrides.clear();
            return null;
        }
        Fragment outFragment = fragments.firstOut;
        new C1642ArrayMap();
        C1642ArrayMap arrayMap3 = arrayMap2;
        impl.findNamedViews(arrayMap3, outFragment.getView());
        BackStackRecord outTransaction = fragments.firstOutTransaction;
        if (fragments.firstOutIsPop) {
            sharedElementCallback = outFragment.getEnterTransitionCallback();
            names = outTransaction.mSharedElementTargetNames;
        } else {
            sharedElementCallback = outFragment.getExitTransitionCallback();
            names = outTransaction.mSharedElementSourceNames;
        }
        boolean retainAll = arrayMap3.retainAll(names);
        if (sharedElementCallback != null) {
            sharedElementCallback.onMapSharedElements(names, arrayMap3);
            for (int i = names.size() - 1; i >= 0; i--) {
                String name = names.get(i);
                View view = (View) arrayMap3.get(name);
                if (view == null) {
                    String remove = nameOverrides.remove(name);
                } else if (!name.equals(ViewCompat.getTransitionName(view))) {
                    String put = nameOverrides.put(ViewCompat.getTransitionName(view), nameOverrides.remove(name));
                }
            }
        } else {
            boolean retainAll2 = nameOverrides.retainAll(arrayMap3.keySet());
        }
        return arrayMap3;
    }

    static C1642ArrayMap<String, View> captureInSharedElements(FragmentTransitionImpl fragmentTransitionImpl, C1642ArrayMap<String, String> arrayMap, Object obj, FragmentContainerTransition fragmentContainerTransition) {
        C1642ArrayMap arrayMap2;
        SharedElementCallback sharedElementCallback;
        ArrayList<String> names;
        String key;
        FragmentTransitionImpl impl = fragmentTransitionImpl;
        C1642ArrayMap<String, String> nameOverrides = arrayMap;
        Object sharedElementTransition = obj;
        FragmentContainerTransition fragments = fragmentContainerTransition;
        Fragment inFragment = fragments.lastIn;
        View fragmentView = inFragment.getView();
        if (nameOverrides.isEmpty() || sharedElementTransition == null || fragmentView == null) {
            nameOverrides.clear();
            return null;
        }
        new C1642ArrayMap();
        C1642ArrayMap arrayMap3 = arrayMap2;
        impl.findNamedViews(arrayMap3, fragmentView);
        BackStackRecord inTransaction = fragments.lastInTransaction;
        if (fragments.lastInIsPop) {
            sharedElementCallback = inFragment.getExitTransitionCallback();
            names = inTransaction.mSharedElementSourceNames;
        } else {
            sharedElementCallback = inFragment.getEnterTransitionCallback();
            names = inTransaction.mSharedElementTargetNames;
        }
        if (names != null) {
            boolean retainAll = arrayMap3.retainAll(names);
            boolean retainAll2 = arrayMap3.retainAll(nameOverrides.values());
        }
        if (sharedElementCallback != null) {
            sharedElementCallback.onMapSharedElements(names, arrayMap3);
            for (int i = names.size() - 1; i >= 0; i--) {
                String name = names.get(i);
                View view = (View) arrayMap3.get(name);
                if (view == null) {
                    String key2 = findKeyForValue(nameOverrides, name);
                    if (key2 != null) {
                        String remove = nameOverrides.remove(key2);
                    }
                } else if (!name.equals(ViewCompat.getTransitionName(view)) && (key = findKeyForValue(nameOverrides, name)) != null) {
                    String put = nameOverrides.put(key, ViewCompat.getTransitionName(view));
                }
            }
        } else {
            retainValues(nameOverrides, arrayMap3);
        }
        return arrayMap3;
    }

    private static String findKeyForValue(C1642ArrayMap<String, String> arrayMap, String str) {
        C1642ArrayMap<String, String> map = arrayMap;
        String value = str;
        int numElements = map.size();
        for (int i = 0; i < numElements; i++) {
            if (value.equals(map.valueAt(i))) {
                return map.keyAt(i);
            }
        }
        return null;
    }

    static View getInEpicenterView(C1642ArrayMap<String, View> arrayMap, FragmentContainerTransition fragments, Object enterTransition, boolean z) {
        String targetName;
        C1642ArrayMap<String, View> inSharedElements = arrayMap;
        boolean inIsPop = z;
        BackStackRecord inTransaction = fragments.lastInTransaction;
        if (enterTransition == null || inSharedElements == null || inTransaction.mSharedElementSourceNames == null || inTransaction.mSharedElementSourceNames.isEmpty()) {
            return null;
        }
        if (inIsPop) {
            targetName = inTransaction.mSharedElementSourceNames.get(0);
        } else {
            targetName = inTransaction.mSharedElementTargetNames.get(0);
        }
        return inSharedElements.get(targetName);
    }

    private static void setOutEpicenter(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Object obj2, C1642ArrayMap<String, View> arrayMap, boolean z, BackStackRecord backStackRecord) {
        String sourceName;
        FragmentTransitionImpl impl = fragmentTransitionImpl;
        Object sharedElementTransition = obj;
        Object exitTransition = obj2;
        C1642ArrayMap<String, View> outSharedElements = arrayMap;
        boolean outIsPop = z;
        BackStackRecord outTransaction = backStackRecord;
        if (outTransaction.mSharedElementSourceNames != null && !outTransaction.mSharedElementSourceNames.isEmpty()) {
            if (outIsPop) {
                sourceName = outTransaction.mSharedElementTargetNames.get(0);
            } else {
                sourceName = outTransaction.mSharedElementSourceNames.get(0);
            }
            View outEpicenterView = outSharedElements.get(sourceName);
            impl.setEpicenter(sharedElementTransition, outEpicenterView);
            if (exitTransition != null) {
                impl.setEpicenter(exitTransition, outEpicenterView);
            }
        }
    }

    private static void retainValues(C1642ArrayMap<String, String> arrayMap, C1642ArrayMap<String, View> arrayMap2) {
        C1642ArrayMap<String, String> nameOverrides = arrayMap;
        C1642ArrayMap<String, View> namedViews = arrayMap2;
        for (int i = nameOverrides.size() - 1; i >= 0; i--) {
            if (!namedViews.containsKey(nameOverrides.valueAt(i))) {
                String removeAt = nameOverrides.removeAt(i);
            }
        }
    }

    static void callSharedElementStartEnd(Fragment fragment, Fragment fragment2, boolean isPop, C1642ArrayMap<String, View> arrayMap, boolean z) {
        SharedElementCallback enterTransitionCallback;
        ArrayList arrayList;
        ArrayList arrayList2;
        Fragment inFragment = fragment;
        Fragment outFragment = fragment2;
        C1642ArrayMap<String, View> sharedElements = arrayMap;
        boolean isStart = z;
        if (isPop) {
            enterTransitionCallback = outFragment.getEnterTransitionCallback();
        } else {
            enterTransitionCallback = inFragment.getEnterTransitionCallback();
        }
        SharedElementCallback sharedElementCallback = enterTransitionCallback;
        if (sharedElementCallback != null) {
            new ArrayList();
            ArrayList arrayList3 = arrayList;
            new ArrayList();
            ArrayList arrayList4 = arrayList2;
            int count = sharedElements == null ? 0 : sharedElements.size();
            for (int i = 0; i < count; i++) {
                boolean add = arrayList4.add(sharedElements.keyAt(i));
                boolean add2 = arrayList3.add(sharedElements.valueAt(i));
            }
            if (isStart) {
                sharedElementCallback.onSharedElementStart(arrayList4, arrayList3, (List<View>) null);
            } else {
                sharedElementCallback.onSharedElementEnd(arrayList4, arrayList3, (List<View>) null);
            }
        }
    }

    static ArrayList<View> configureEnteringExitingViews(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        ArrayList<View> arrayList2;
        FragmentTransitionImpl impl = fragmentTransitionImpl;
        Object transition = obj;
        Fragment fragment2 = fragment;
        ArrayList<View> sharedElements = arrayList;
        View nonExistentView = view;
        ArrayList<View> viewList = null;
        if (transition != null) {
            new ArrayList<>();
            viewList = arrayList2;
            View root = fragment2.getView();
            if (root != null) {
                impl.captureTransitioningViews(viewList, root);
            }
            if (sharedElements != null) {
                boolean removeAll = viewList.removeAll(sharedElements);
            }
            if (!viewList.isEmpty()) {
                boolean add = viewList.add(nonExistentView);
                impl.addTargets(transition, viewList);
            }
        }
        return viewList;
    }

    static void setViewVisibility(ArrayList<View> arrayList, int i) {
        ArrayList<View> views = arrayList;
        int visibility = i;
        if (views != null) {
            for (int i2 = views.size() - 1; i2 >= 0; i2--) {
                views.get(i2).setVisibility(visibility);
            }
        }
    }

    private static Object mergeTransitions(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z) {
        Object transition;
        boolean allowEnterTransitionOverlap;
        FragmentTransitionImpl impl = fragmentTransitionImpl;
        Object enterTransition = obj;
        Object exitTransition = obj2;
        Object sharedElementTransition = obj3;
        Fragment inFragment = fragment;
        boolean isPop = z;
        boolean overlap = true;
        if (!(enterTransition == null || exitTransition == null || inFragment == null)) {
            if (isPop) {
                allowEnterTransitionOverlap = inFragment.getAllowReturnTransitionOverlap();
            } else {
                allowEnterTransitionOverlap = inFragment.getAllowEnterTransitionOverlap();
            }
            overlap = allowEnterTransitionOverlap;
        }
        if (overlap) {
            transition = impl.mergeTransitionsTogether(exitTransition, enterTransition, sharedElementTransition);
        } else {
            transition = impl.mergeTransitionsInSequence(exitTransition, enterTransition, sharedElementTransition);
        }
        return transition;
    }

    public static void calculateFragments(BackStackRecord backStackRecord, SparseArray<FragmentContainerTransition> sparseArray, boolean z) {
        BackStackRecord transaction = backStackRecord;
        SparseArray<FragmentContainerTransition> transitioningFragments = sparseArray;
        boolean isReordered = z;
        int numOps = transaction.mOps.size();
        for (int opNum = 0; opNum < numOps; opNum++) {
            addToFirstInLastOut(transaction, transaction.mOps.get(opNum), transitioningFragments, false, isReordered);
        }
    }

    public static void calculatePopFragments(BackStackRecord backStackRecord, SparseArray<FragmentContainerTransition> sparseArray, boolean z) {
        BackStackRecord transaction = backStackRecord;
        SparseArray<FragmentContainerTransition> transitioningFragments = sparseArray;
        boolean isReordered = z;
        if (transaction.mManager.mContainer.onHasView()) {
            for (int opNum = transaction.mOps.size() - 1; opNum >= 0; opNum--) {
                addToFirstInLastOut(transaction, transaction.mOps.get(opNum), transitioningFragments, true, isReordered);
            }
        }
    }

    static boolean supportsTransition() {
        return (PLATFORM_IMPL == null && SUPPORT_IMPL == null) ? false : true;
    }

    private static void addToFirstInLastOut(BackStackRecord backStackRecord, BackStackRecord.C0240Op op, SparseArray<FragmentContainerTransition> sparseArray, boolean z, boolean z2) {
        int containerId;
        boolean setFirstOut;
        boolean setLastIn;
        BackStackRecord transaction = backStackRecord;
        BackStackRecord.C0240Op op2 = op;
        SparseArray<FragmentContainerTransition> transitioningFragments = sparseArray;
        boolean isPop = z;
        boolean isReorderedTransaction = z2;
        Fragment fragment = op2.fragment;
        if (fragment != null && (containerId = fragment.mContainerId) != 0) {
            boolean setLastIn2 = false;
            boolean wasRemoved = false;
            boolean setFirstOut2 = false;
            boolean wasAdded = false;
            switch (isPop ? INVERSE_OPS[op2.cmd] : op2.cmd) {
                case 1:
                case 7:
                    if (isReorderedTransaction) {
                        setLastIn = fragment.mIsNewlyAdded;
                    } else {
                        setLastIn = !fragment.mAdded && !fragment.mHidden;
                    }
                    wasAdded = true;
                    break;
                case 3:
                case 6:
                    if (isReorderedTransaction) {
                        setFirstOut = !fragment.mAdded && fragment.mView != null && fragment.mView.getVisibility() == 0 && fragment.mPostponedAlpha >= 0.0f;
                    } else {
                        setFirstOut = fragment.mAdded && !fragment.mHidden;
                    }
                    wasRemoved = true;
                    break;
                case 4:
                    if (isReorderedTransaction) {
                        setFirstOut2 = fragment.mHiddenChanged && fragment.mAdded && fragment.mHidden;
                    } else {
                        setFirstOut2 = fragment.mAdded && !fragment.mHidden;
                    }
                    wasRemoved = true;
                    break;
                case 5:
                    if (isReorderedTransaction) {
                        setLastIn2 = fragment.mHiddenChanged && !fragment.mHidden && fragment.mAdded;
                    } else {
                        setLastIn2 = fragment.mHidden;
                    }
                    wasAdded = true;
                    break;
            }
            FragmentContainerTransition containerTransition = transitioningFragments.get(containerId);
            if (setLastIn2) {
                containerTransition = ensureContainer(containerTransition, transitioningFragments, containerId);
                containerTransition.lastIn = fragment;
                containerTransition.lastInIsPop = isPop;
                containerTransition.lastInTransaction = transaction;
            }
            if (!isReorderedTransaction && wasAdded) {
                if (containerTransition != null && containerTransition.firstOut == fragment) {
                    containerTransition.firstOut = null;
                }
                FragmentManagerImpl manager = transaction.mManager;
                if (fragment.mState < 1 && manager.mCurState >= 1 && !transaction.mReorderingAllowed) {
                    manager.makeActive(fragment);
                    manager.moveToState(fragment, 1, 0, 0, false);
                }
            }
            if (setFirstOut2 && (containerTransition == null || containerTransition.firstOut == null)) {
                containerTransition = ensureContainer(containerTransition, transitioningFragments, containerId);
                containerTransition.firstOut = fragment;
                containerTransition.firstOutIsPop = isPop;
                containerTransition.firstOutTransaction = transaction;
            }
            if (!isReorderedTransaction && wasRemoved && containerTransition != null && containerTransition.lastIn == fragment) {
                containerTransition.lastIn = null;
            }
        }
    }

    private static FragmentContainerTransition ensureContainer(FragmentContainerTransition fragmentContainerTransition, SparseArray<FragmentContainerTransition> sparseArray, int i) {
        FragmentContainerTransition fragmentContainerTransition2;
        FragmentContainerTransition containerTransition = fragmentContainerTransition;
        SparseArray<FragmentContainerTransition> transitioningFragments = sparseArray;
        int containerId = i;
        if (containerTransition == null) {
            new FragmentContainerTransition();
            containerTransition = fragmentContainerTransition2;
            transitioningFragments.put(containerId, containerTransition);
        }
        return containerTransition;
    }

    /* renamed from: android.support.v4.app.FragmentTransition$FragmentContainerTransition */
    static class FragmentContainerTransition {
        public Fragment firstOut;
        public boolean firstOutIsPop;
        public BackStackRecord firstOutTransaction;
        public Fragment lastIn;
        public boolean lastInIsPop;
        public BackStackRecord lastInTransaction;

        FragmentContainerTransition() {
        }
    }

    private FragmentTransition() {
    }
}
