package android.support.p000v4.app;

import android.graphics.Rect;
import android.support.annotation.RequiresApi;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(21)
/* renamed from: android.support.v4.app.FragmentTransitionCompat21 */
class FragmentTransitionCompat21 extends FragmentTransitionImpl {
    FragmentTransitionCompat21() {
    }

    public boolean canHandle(Object transition) {
        return transition instanceof Transition;
    }

    public Object cloneTransition(Object obj) {
        Object transition = obj;
        Transition copy = null;
        if (transition != null) {
            copy = ((Transition) transition).clone();
        }
        return copy;
    }

    public Object wrapTransitionInSet(Object obj) {
        TransitionSet transitionSet;
        Object transition = obj;
        if (transition == null) {
            return null;
        }
        new TransitionSet();
        TransitionSet transitionSet2 = transitionSet;
        TransitionSet addTransition = transitionSet2.addTransition((Transition) transition);
        return transitionSet2;
    }

    public void setSharedElementTargets(Object transitionObj, View view, ArrayList<View> arrayList) {
        View nonExistentView = view;
        ArrayList<View> sharedViews = arrayList;
        TransitionSet transition = (TransitionSet) transitionObj;
        List<View> views = transition.getTargets();
        views.clear();
        int count = sharedViews.size();
        for (int i = 0; i < count; i++) {
            bfsAddViewChildren(views, sharedViews.get(i));
        }
        boolean add = views.add(nonExistentView);
        boolean add2 = sharedViews.add(nonExistentView);
        addTargets(transition, sharedViews);
    }

    public void setEpicenter(Object obj, View view) {
        Rect rect;
        Transition.EpicenterCallback epicenterCallback;
        Object transitionObj = obj;
        View view2 = view;
        if (view2 != null) {
            new Rect();
            Rect epicenter = rect;
            getBoundsOnScreen(view2, epicenter);
            final Rect rect2 = epicenter;
            new Transition.EpicenterCallback(this) {
                final /* synthetic */ FragmentTransitionCompat21 this$0;

                {
                    this.this$0 = this$0;
                }

                public Rect onGetEpicenter(Transition transition) {
                    Transition transition2 = transition;
                    return rect2;
                }
            };
            ((Transition) transitionObj).setEpicenterCallback(epicenterCallback);
        }
    }

    public void addTargets(Object transitionObj, ArrayList<View> arrayList) {
        ArrayList<View> views = arrayList;
        Transition transition = (Transition) transitionObj;
        if (transition != null) {
            if (transition instanceof TransitionSet) {
                TransitionSet set = (TransitionSet) transition;
                int numTransitions = set.getTransitionCount();
                for (int i = 0; i < numTransitions; i++) {
                    addTargets(set.getTransitionAt(i), views);
                }
            } else if (!hasSimpleTarget(transition) && isNullOrEmpty(transition.getTargets())) {
                int numViews = views.size();
                for (int i2 = 0; i2 < numViews; i2++) {
                    Transition addTarget = transition.addTarget(views.get(i2));
                }
            }
        }
    }

    private static boolean hasSimpleTarget(Transition transition) {
        Transition transition2 = transition;
        return !isNullOrEmpty(transition2.getTargetIds()) || !isNullOrEmpty(transition2.getTargetNames()) || !isNullOrEmpty(transition2.getTargetTypes());
    }

    public Object mergeTransitionsTogether(Object obj, Object obj2, Object obj3) {
        TransitionSet transitionSet;
        Object transition1 = obj;
        Object transition2 = obj2;
        Object transition3 = obj3;
        new TransitionSet();
        TransitionSet transitionSet2 = transitionSet;
        if (transition1 != null) {
            TransitionSet addTransition = transitionSet2.addTransition((Transition) transition1);
        }
        if (transition2 != null) {
            TransitionSet addTransition2 = transitionSet2.addTransition((Transition) transition2);
        }
        if (transition3 != null) {
            TransitionSet addTransition3 = transitionSet2.addTransition((Transition) transition3);
        }
        return transitionSet2;
    }

    public void scheduleHideFragmentView(Object exitTransitionObj, View fragmentView, ArrayList<View> exitingViews) {
        Transition.TransitionListener transitionListener;
        final View view = fragmentView;
        final ArrayList<View> arrayList = exitingViews;
        new Transition.TransitionListener(this) {
            final /* synthetic */ FragmentTransitionCompat21 this$0;

            {
                this.this$0 = this$0;
            }

            public void onTransitionStart(Transition transition) {
            }

            public void onTransitionEnd(Transition transition) {
                Transition removeListener = transition.removeListener(this);
                view.setVisibility(8);
                int numViews = arrayList.size();
                for (int i = 0; i < numViews; i++) {
                    ((View) arrayList.get(i)).setVisibility(0);
                }
            }

            public void onTransitionCancel(Transition transition) {
            }

            public void onTransitionPause(Transition transition) {
            }

            public void onTransitionResume(Transition transition) {
            }
        };
        Transition addListener = ((Transition) exitTransitionObj).addListener(transitionListener);
    }

    public Object mergeTransitionsInSequence(Object exitTransitionObj, Object enterTransitionObj, Object sharedElementTransitionObj) {
        TransitionSet transitionSet;
        TransitionSet transitionSet2;
        Transition staggered = null;
        Transition exitTransition = (Transition) exitTransitionObj;
        Transition enterTransition = (Transition) enterTransitionObj;
        Transition sharedElementTransition = (Transition) sharedElementTransitionObj;
        if (exitTransition != null && enterTransition != null) {
            new TransitionSet();
            staggered = transitionSet2.addTransition(exitTransition).addTransition(enterTransition).setOrdering(1);
        } else if (exitTransition != null) {
            staggered = exitTransition;
        } else if (enterTransition != null) {
            staggered = enterTransition;
        }
        if (sharedElementTransition == null) {
            return staggered;
        }
        new TransitionSet();
        TransitionSet together = transitionSet;
        if (staggered != null) {
            TransitionSet addTransition = together.addTransition(staggered);
        }
        TransitionSet addTransition2 = together.addTransition(sharedElementTransition);
        return together;
    }

    public void beginDelayedTransition(ViewGroup sceneRoot, Object transition) {
        TransitionManager.beginDelayedTransition(sceneRoot, (Transition) transition);
    }

    public void scheduleRemoveTargets(Object overallTransitionObj, Object enterTransition, ArrayList<View> enteringViews, Object exitTransition, ArrayList<View> exitingViews, Object sharedElementTransition, ArrayList<View> sharedElementsIn) {
        Transition.TransitionListener transitionListener;
        final Object obj = enterTransition;
        final ArrayList<View> arrayList = enteringViews;
        final Object obj2 = exitTransition;
        final ArrayList<View> arrayList2 = exitingViews;
        final Object obj3 = sharedElementTransition;
        final ArrayList<View> arrayList3 = sharedElementsIn;
        new Transition.TransitionListener(this) {
            final /* synthetic */ FragmentTransitionCompat21 this$0;

            {
                this.this$0 = this$0;
            }

            public void onTransitionStart(Transition transition) {
                Transition transition2 = transition;
                if (obj != null) {
                    this.this$0.replaceTargets(obj, arrayList, (ArrayList<View>) null);
                }
                if (obj2 != null) {
                    this.this$0.replaceTargets(obj2, arrayList2, (ArrayList<View>) null);
                }
                if (obj3 != null) {
                    this.this$0.replaceTargets(obj3, arrayList3, (ArrayList<View>) null);
                }
            }

            public void onTransitionEnd(Transition transition) {
            }

            public void onTransitionCancel(Transition transition) {
            }

            public void onTransitionPause(Transition transition) {
            }

            public void onTransitionResume(Transition transition) {
            }
        };
        Transition addListener = ((Transition) overallTransitionObj).addListener(transitionListener);
    }

    public void swapSharedElementTargets(Object sharedElementTransitionObj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        ArrayList<View> sharedElementsOut = arrayList;
        ArrayList<View> sharedElementsIn = arrayList2;
        TransitionSet sharedElementTransition = (TransitionSet) sharedElementTransitionObj;
        if (sharedElementTransition != null) {
            sharedElementTransition.getTargets().clear();
            boolean addAll = sharedElementTransition.getTargets().addAll(sharedElementsIn);
            replaceTargets(sharedElementTransition, sharedElementsOut, sharedElementsIn);
        }
    }

    public void replaceTargets(Object transitionObj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        List<View> targets;
        ArrayList<View> oldTargets = arrayList;
        ArrayList<View> newTargets = arrayList2;
        Transition transition = (Transition) transitionObj;
        if (transition instanceof TransitionSet) {
            TransitionSet set = (TransitionSet) transition;
            int numTransitions = set.getTransitionCount();
            for (int i = 0; i < numTransitions; i++) {
                replaceTargets(set.getTransitionAt(i), oldTargets, newTargets);
            }
        } else if (!hasSimpleTarget(transition) && (targets = transition.getTargets()) != null && targets.size() == oldTargets.size() && targets.containsAll(oldTargets)) {
            int targetCount = newTargets == null ? 0 : newTargets.size();
            for (int i2 = 0; i2 < targetCount; i2++) {
                Transition addTarget = transition.addTarget(newTargets.get(i2));
            }
            for (int i3 = oldTargets.size() - 1; i3 >= 0; i3--) {
                Transition removeTarget = transition.removeTarget(oldTargets.get(i3));
            }
        }
    }

    public void addTarget(Object obj, View view) {
        Object transitionObj = obj;
        View view2 = view;
        if (transitionObj != null) {
            Transition addTarget = ((Transition) transitionObj).addTarget(view2);
        }
    }

    public void removeTarget(Object obj, View view) {
        Object transitionObj = obj;
        View view2 = view;
        if (transitionObj != null) {
            Transition removeTarget = ((Transition) transitionObj).removeTarget(view2);
        }
    }

    public void setEpicenter(Object obj, Rect rect) {
        Transition.EpicenterCallback epicenterCallback;
        Object transitionObj = obj;
        Rect epicenter = rect;
        if (transitionObj != null) {
            final Rect rect2 = epicenter;
            new Transition.EpicenterCallback(this) {
                final /* synthetic */ FragmentTransitionCompat21 this$0;

                {
                    this.this$0 = this$0;
                }

                public Rect onGetEpicenter(Transition transition) {
                    Transition transition2 = transition;
                    if (rect2 == null || rect2.isEmpty()) {
                        return null;
                    }
                    return rect2;
                }
            };
            ((Transition) transitionObj).setEpicenterCallback(epicenterCallback);
        }
    }
}
