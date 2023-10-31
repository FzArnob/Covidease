package android.support.p000v4.app;

import android.graphics.Rect;
import android.support.annotation.RestrictTo;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.ViewGroupCompat;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.app.FragmentTransitionImpl */
public abstract class FragmentTransitionImpl {
    public abstract void addTarget(Object obj, View view);

    public abstract void addTargets(Object obj, ArrayList<View> arrayList);

    public abstract void beginDelayedTransition(ViewGroup viewGroup, Object obj);

    public abstract boolean canHandle(Object obj);

    public abstract Object cloneTransition(Object obj);

    public abstract Object mergeTransitionsInSequence(Object obj, Object obj2, Object obj3);

    public abstract Object mergeTransitionsTogether(Object obj, Object obj2, Object obj3);

    public abstract void removeTarget(Object obj, View view);

    public abstract void replaceTargets(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2);

    public abstract void scheduleHideFragmentView(Object obj, View view, ArrayList<View> arrayList);

    public abstract void scheduleRemoveTargets(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3);

    public abstract void setEpicenter(Object obj, Rect rect);

    public abstract void setEpicenter(Object obj, View view);

    public abstract void setSharedElementTargets(Object obj, View view, ArrayList<View> arrayList);

    public abstract void swapSharedElementTargets(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2);

    public abstract Object wrapTransitionInSet(Object obj);

    public FragmentTransitionImpl() {
    }

    /* access modifiers changed from: protected */
    public void getBoundsOnScreen(View view, Rect epicenter) {
        View view2 = view;
        int[] loc = new int[2];
        view2.getLocationOnScreen(loc);
        epicenter.set(loc[0], loc[1], loc[0] + view2.getWidth(), loc[1] + view2.getHeight());
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<java.lang.String> prepareSetNameOverridesReordered(java.util.ArrayList<android.view.View> r10) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            java.util.ArrayList r6 = new java.util.ArrayList
            r8 = r6
            r6 = r8
            r7 = r8
            r7.<init>()
            r2 = r6
            r6 = r1
            int r6 = r6.size()
            r3 = r6
            r6 = 0
            r4 = r6
        L_0x0013:
            r6 = r4
            r7 = r3
            if (r6 >= r7) goto L_0x0032
            r6 = r1
            r7 = r4
            java.lang.Object r6 = r6.get(r7)
            android.view.View r6 = (android.view.View) r6
            r5 = r6
            r6 = r2
            r7 = r5
            java.lang.String r7 = android.support.p000v4.view.ViewCompat.getTransitionName(r7)
            boolean r6 = r6.add(r7)
            r6 = r5
            r7 = 0
            android.support.p000v4.view.ViewCompat.setTransitionName(r6, r7)
            int r4 = r4 + 1
            goto L_0x0013
        L_0x0032:
            r6 = r2
            r0 = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentTransitionImpl.prepareSetNameOverridesReordered(java.util.ArrayList):java.util.ArrayList");
    }

    /* access modifiers changed from: package-private */
    public void setNameOverridesReordered(View view, ArrayList<View> arrayList, ArrayList<View> arrayList2, ArrayList<String> arrayList3, Map<String, String> map) {
        ArrayList arrayList4;
        Runnable runnable;
        View sceneRoot = view;
        ArrayList<View> sharedElementsOut = arrayList;
        ArrayList<View> sharedElementsIn = arrayList2;
        ArrayList<String> inNames = arrayList3;
        Map<String, String> nameOverrides = map;
        int numSharedElements = sharedElementsIn.size();
        new ArrayList();
        ArrayList arrayList5 = arrayList4;
        for (int i = 0; i < numSharedElements; i++) {
            View view2 = sharedElementsOut.get(i);
            String name = ViewCompat.getTransitionName(view2);
            boolean add = arrayList5.add(name);
            if (name != null) {
                ViewCompat.setTransitionName(view2, (String) null);
                String inName = nameOverrides.get(name);
                int j = 0;
                while (true) {
                    if (j >= numSharedElements) {
                        break;
                    } else if (inName.equals(inNames.get(j))) {
                        ViewCompat.setTransitionName(sharedElementsIn.get(j), name);
                        break;
                    } else {
                        j++;
                    }
                }
            }
        }
        final int i2 = numSharedElements;
        final ArrayList<View> arrayList6 = sharedElementsIn;
        final ArrayList<String> arrayList7 = inNames;
        final ArrayList<View> arrayList8 = sharedElementsOut;
        final ArrayList arrayList9 = arrayList5;
        new Runnable(this) {
            final /* synthetic */ FragmentTransitionImpl this$0;

            {
                this.this$0 = this$0;
            }

            public void run() {
                for (int i = 0; i < i2; i++) {
                    ViewCompat.setTransitionName((View) arrayList6.get(i), (String) arrayList7.get(i));
                    ViewCompat.setTransitionName((View) arrayList8.get(i), (String) arrayList9.get(i));
                }
            }
        };
        OneShotPreDrawListener add2 = OneShotPreDrawListener.add(sceneRoot, runnable);
    }

    /* access modifiers changed from: package-private */
    public void captureTransitioningViews(ArrayList<View> arrayList, View view) {
        ArrayList<View> transitioningViews = arrayList;
        View view2 = view;
        if (view2.getVisibility() != 0) {
            return;
        }
        if (view2 instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view2;
            if (ViewGroupCompat.isTransitionGroup(viewGroup)) {
                boolean add = transitioningViews.add(viewGroup);
                return;
            }
            int count = viewGroup.getChildCount();
            for (int i = 0; i < count; i++) {
                captureTransitioningViews(transitioningViews, viewGroup.getChildAt(i));
            }
            return;
        }
        boolean add2 = transitioningViews.add(view2);
    }

    /* access modifiers changed from: package-private */
    public void findNamedViews(Map<String, View> map, View view) {
        Map<String, View> namedViews = map;
        View view2 = view;
        if (view2.getVisibility() == 0) {
            String transitionName = ViewCompat.getTransitionName(view2);
            if (transitionName != null) {
                View put = namedViews.put(transitionName, view2);
            }
            if (view2 instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view2;
                int count = viewGroup.getChildCount();
                for (int i = 0; i < count; i++) {
                    findNamedViews(namedViews, viewGroup.getChildAt(i));
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setNameOverridesOrdered(View sceneRoot, ArrayList<View> sharedElementsIn, Map<String, String> nameOverrides) {
        Runnable runnable;
        final ArrayList<View> arrayList = sharedElementsIn;
        final Map<String, String> map = nameOverrides;
        new Runnable(this) {
            final /* synthetic */ FragmentTransitionImpl this$0;

            {
                this.this$0 = this$0;
            }

            public void run() {
                int numSharedElements = arrayList.size();
                for (int i = 0; i < numSharedElements; i++) {
                    View view = (View) arrayList.get(i);
                    String name = ViewCompat.getTransitionName(view);
                    if (name != null) {
                        ViewCompat.setTransitionName(view, FragmentTransitionImpl.findKeyForValue(map, name));
                    }
                }
            }
        };
        OneShotPreDrawListener add = OneShotPreDrawListener.add(sceneRoot, runnable);
    }

    /* access modifiers changed from: package-private */
    public void scheduleNameReset(ViewGroup sceneRoot, ArrayList<View> sharedElementsIn, Map<String, String> nameOverrides) {
        Runnable runnable;
        final ArrayList<View> arrayList = sharedElementsIn;
        final Map<String, String> map = nameOverrides;
        new Runnable(this) {
            final /* synthetic */ FragmentTransitionImpl this$0;

            {
                this.this$0 = this$0;
            }

            public void run() {
                int numSharedElements = arrayList.size();
                for (int i = 0; i < numSharedElements; i++) {
                    View view = (View) arrayList.get(i);
                    ViewCompat.setTransitionName(view, (String) map.get(ViewCompat.getTransitionName(view)));
                }
            }
        };
        OneShotPreDrawListener add = OneShotPreDrawListener.add(sceneRoot, runnable);
    }

    protected static void bfsAddViewChildren(List<View> list, View view) {
        List<View> views = list;
        View startView = view;
        int startIndex = views.size();
        if (!containedBeforeIndex(views, startView, startIndex)) {
            boolean add = views.add(startView);
            for (int index = startIndex; index < views.size(); index++) {
                View view2 = views.get(index);
                if (view2 instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view2;
                    int childCount = viewGroup.getChildCount();
                    for (int childIndex = 0; childIndex < childCount; childIndex++) {
                        View child = viewGroup.getChildAt(childIndex);
                        if (!containedBeforeIndex(views, child, startIndex)) {
                            boolean add2 = views.add(child);
                        }
                    }
                }
            }
        }
    }

    private static boolean containedBeforeIndex(List<View> list, View view, int i) {
        List<View> views = list;
        View view2 = view;
        int maxIndex = i;
        for (int i2 = 0; i2 < maxIndex; i2++) {
            if (views.get(i2) == view2) {
                return true;
            }
        }
        return false;
    }

    protected static boolean isNullOrEmpty(List list) {
        List list2 = list;
        return list2 == null || list2.isEmpty();
    }

    static String findKeyForValue(Map<String, String> map, String str) {
        String value = str;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
