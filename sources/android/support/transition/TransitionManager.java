package android.support.transition;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.util.C1642ArrayMap;
import android.support.p000v4.view.ViewCompat;
import android.support.transition.Transition;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public class TransitionManager {
    private static final String LOG_TAG = "TransitionManager";
    private static Transition sDefaultTransition;
    static ArrayList<ViewGroup> sPendingTransitions;
    private static ThreadLocal<WeakReference<C1642ArrayMap<ViewGroup, ArrayList<Transition>>>> sRunningTransitions;
    private C1642ArrayMap<Scene, C1642ArrayMap<Scene, Transition>> mScenePairTransitions;
    private C1642ArrayMap<Scene, Transition> mSceneTransitions;

    public TransitionManager() {
        C1642ArrayMap<Scene, Transition> arrayMap;
        C1642ArrayMap<Scene, C1642ArrayMap<Scene, Transition>> arrayMap2;
        new C1642ArrayMap<>();
        this.mSceneTransitions = arrayMap;
        new C1642ArrayMap<>();
        this.mScenePairTransitions = arrayMap2;
    }

    static {
        Transition transition;
        ThreadLocal<WeakReference<C1642ArrayMap<ViewGroup, ArrayList<Transition>>>> threadLocal;
        ArrayList<ViewGroup> arrayList;
        new AutoTransition();
        sDefaultTransition = transition;
        new ThreadLocal<>();
        sRunningTransitions = threadLocal;
        new ArrayList<>();
        sPendingTransitions = arrayList;
    }

    public void setTransition(@NonNull Scene scene, @Nullable Transition transition) {
        Transition put = this.mSceneTransitions.put(scene, transition);
    }

    public void setTransition(@NonNull Scene scene, @NonNull Scene scene2, @Nullable Transition transition) {
        C1642ArrayMap arrayMap;
        Scene fromScene = scene;
        Scene toScene = scene2;
        Transition transition2 = transition;
        C1642ArrayMap arrayMap2 = this.mScenePairTransitions.get(toScene);
        if (arrayMap2 == null) {
            new C1642ArrayMap();
            arrayMap2 = arrayMap;
            C1642ArrayMap<Scene, Transition> put = this.mScenePairTransitions.put(toScene, arrayMap2);
        }
        Object put2 = arrayMap2.put(fromScene, transition2);
    }

    private Transition getTransition(Scene scene) {
        Scene currScene;
        C1642ArrayMap<Scene, Transition> sceneTransitionMap;
        Transition transition;
        Scene scene2 = scene;
        ViewGroup sceneRoot = scene2.getSceneRoot();
        if (sceneRoot != null && (currScene = Scene.getCurrentScene(sceneRoot)) != null && (sceneTransitionMap = this.mScenePairTransitions.get(scene2)) != null && (transition = sceneTransitionMap.get(currScene)) != null) {
            return transition;
        }
        Transition transition2 = this.mSceneTransitions.get(scene2);
        return transition2 != null ? transition2 : sDefaultTransition;
    }

    private static void changeScene(Scene scene, Transition transition) {
        Scene scene2 = scene;
        Transition transition2 = transition;
        ViewGroup sceneRoot = scene2.getSceneRoot();
        if (sPendingTransitions.contains(sceneRoot)) {
            return;
        }
        if (transition2 == null) {
            scene2.enter();
            return;
        }
        boolean add = sPendingTransitions.add(sceneRoot);
        Transition transitionClone = transition2.clone();
        Transition sceneRoot2 = transitionClone.setSceneRoot(sceneRoot);
        Scene oldScene = Scene.getCurrentScene(sceneRoot);
        if (oldScene != null && oldScene.isCreatedFromLayoutResource()) {
            transitionClone.setCanRemoveViews(true);
        }
        sceneChangeSetup(sceneRoot, transitionClone);
        scene2.enter();
        sceneChangeRunTransition(sceneRoot, transitionClone);
    }

    static C1642ArrayMap<ViewGroup, ArrayList<Transition>> getRunningTransitions() {
        C1642ArrayMap arrayMap;
        Object obj;
        C1642ArrayMap<ViewGroup, ArrayList<Transition>> transitions;
        WeakReference<C1642ArrayMap<ViewGroup, ArrayList<Transition>>> runningTransitions = sRunningTransitions.get();
        if (runningTransitions != null && (transitions = (C1642ArrayMap) runningTransitions.get()) != null) {
            return transitions;
        }
        new C1642ArrayMap();
        C1642ArrayMap arrayMap2 = arrayMap;
        new WeakReference(arrayMap2);
        sRunningTransitions.set(obj);
        return arrayMap2;
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [android.view.ViewTreeObserver$OnPreDrawListener] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void sceneChangeRunTransition(android.view.ViewGroup r8, android.support.transition.Transition r9) {
        /*
            r0 = r8
            r1 = r9
            r3 = r1
            if (r3 == 0) goto L_0x0021
            r3 = r0
            if (r3 == 0) goto L_0x0021
            android.support.transition.TransitionManager$MultiListener r3 = new android.support.transition.TransitionManager$MultiListener
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r1
            r6 = r0
            r4.<init>(r5, r6)
            r2 = r3
            r3 = r0
            r4 = r2
            r3.addOnAttachStateChangeListener(r4)
            r3 = r0
            android.view.ViewTreeObserver r3 = r3.getViewTreeObserver()
            r4 = r2
            r3.addOnPreDrawListener(r4)
        L_0x0021:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.TransitionManager.sceneChangeRunTransition(android.view.ViewGroup, android.support.transition.Transition):void");
    }

    private static class MultiListener implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
        ViewGroup mSceneRoot;
        Transition mTransition;

        MultiListener(Transition transition, ViewGroup sceneRoot) {
            this.mTransition = transition;
            this.mSceneRoot = sceneRoot;
        }

        private void removeListeners() {
            this.mSceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
            this.mSceneRoot.removeOnAttachStateChangeListener(this);
        }

        public void onViewAttachedToWindow(View v) {
        }

        public void onViewDetachedFromWindow(View view) {
            View view2 = view;
            removeListeners();
            boolean remove = TransitionManager.sPendingTransitions.remove(this.mSceneRoot);
            ArrayList<Transition> runningTransitions = TransitionManager.getRunningTransitions().get(this.mSceneRoot);
            if (runningTransitions != null && runningTransitions.size() > 0) {
                Iterator<Transition> it = runningTransitions.iterator();
                while (it.hasNext()) {
                    it.next().resume(this.mSceneRoot);
                }
            }
            this.mTransition.clearValues(true);
        }

        public boolean onPreDraw() {
            ArrayList arrayList;
            Transition.TransitionListener transitionListener;
            ArrayList arrayList2;
            removeListeners();
            if (!TransitionManager.sPendingTransitions.remove(this.mSceneRoot)) {
                return true;
            }
            C1642ArrayMap<ViewGroup, ArrayList<Transition>> runningTransitions = TransitionManager.getRunningTransitions();
            ArrayList arrayList3 = runningTransitions.get(this.mSceneRoot);
            ArrayList arrayList4 = null;
            if (arrayList3 == null) {
                new ArrayList();
                arrayList3 = arrayList2;
                ArrayList<Transition> put = runningTransitions.put(this.mSceneRoot, arrayList3);
            } else if (arrayList3.size() > 0) {
                new ArrayList(arrayList3);
                arrayList4 = arrayList;
            }
            boolean add = arrayList3.add(this.mTransition);
            final C1642ArrayMap<ViewGroup, ArrayList<Transition>> arrayMap = runningTransitions;
            new TransitionListenerAdapter(this) {
                final /* synthetic */ MultiListener this$0;

                {
                    this.this$0 = this$0;
                }

                public void onTransitionEnd(@NonNull Transition transition) {
                    boolean remove = ((ArrayList) arrayMap.get(this.this$0.mSceneRoot)).remove(transition);
                }
            };
            Transition addListener = this.mTransition.addListener(transitionListener);
            this.mTransition.captureValues(this.mSceneRoot, false);
            if (arrayList4 != null) {
                Iterator it = arrayList4.iterator();
                while (it.hasNext()) {
                    ((Transition) it.next()).resume(this.mSceneRoot);
                }
            }
            this.mTransition.playTransition(this.mSceneRoot);
            return true;
        }
    }

    private static void sceneChangeSetup(ViewGroup viewGroup, Transition transition) {
        ViewGroup sceneRoot = viewGroup;
        Transition transition2 = transition;
        ArrayList<Transition> runningTransitions = getRunningTransitions().get(sceneRoot);
        if (runningTransitions != null && runningTransitions.size() > 0) {
            Iterator<Transition> it = runningTransitions.iterator();
            while (it.hasNext()) {
                it.next().pause(sceneRoot);
            }
        }
        if (transition2 != null) {
            transition2.captureValues(sceneRoot, true);
        }
        Scene previousScene = Scene.getCurrentScene(sceneRoot);
        if (previousScene != null) {
            previousScene.exit();
        }
    }

    public void transitionTo(@NonNull Scene scene) {
        Scene scene2 = scene;
        changeScene(scene2, getTransition(scene2));
    }

    /* renamed from: go */
    public static void m2go(@NonNull Scene scene) {
        changeScene(scene, sDefaultTransition);
    }

    /* renamed from: go */
    public static void m3go(@NonNull Scene scene, @Nullable Transition transition) {
        changeScene(scene, transition);
    }

    public static void beginDelayedTransition(@NonNull ViewGroup sceneRoot) {
        beginDelayedTransition(sceneRoot, (Transition) null);
    }

    public static void beginDelayedTransition(@NonNull ViewGroup viewGroup, @Nullable Transition transition) {
        ViewGroup sceneRoot = viewGroup;
        Transition transition2 = transition;
        if (!sPendingTransitions.contains(sceneRoot) && ViewCompat.isLaidOut(sceneRoot)) {
            boolean add = sPendingTransitions.add(sceneRoot);
            if (transition2 == null) {
                transition2 = sDefaultTransition;
            }
            Transition transitionClone = transition2.clone();
            sceneChangeSetup(sceneRoot, transitionClone);
            Scene.setCurrentScene(sceneRoot, (Scene) null);
            sceneChangeRunTransition(sceneRoot, transitionClone);
        }
    }

    public static void endTransitions(ViewGroup viewGroup) {
        ArrayList arrayList;
        ViewGroup sceneRoot = viewGroup;
        boolean remove = sPendingTransitions.remove(sceneRoot);
        ArrayList<Transition> runningTransitions = getRunningTransitions().get(sceneRoot);
        if (runningTransitions != null && !runningTransitions.isEmpty()) {
            new ArrayList(runningTransitions);
            ArrayList arrayList2 = arrayList;
            for (int i = arrayList2.size() - 1; i >= 0; i--) {
                ((Transition) arrayList2.get(i)).forceToEnd(sceneRoot);
            }
        }
    }
}
