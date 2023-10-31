package android.support.p000v4.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.util.C1650SimpleArrayMap;
import android.support.p000v4.util.Pools;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* renamed from: android.support.v4.widget.DirectedAcyclicGraph */
public final class DirectedAcyclicGraph<T> {
    private final C1650SimpleArrayMap<T, ArrayList<T>> mGraph;
    private final Pools.Pool<ArrayList<T>> mListPool;
    private final ArrayList<T> mSortResult;
    private final HashSet<T> mSortTmpMarked;

    public DirectedAcyclicGraph() {
        Pools.Pool<ArrayList<T>> pool;
        C1650SimpleArrayMap<T, ArrayList<T>> simpleArrayMap;
        ArrayList<T> arrayList;
        HashSet<T> hashSet;
        new Pools.SimplePool(10);
        this.mListPool = pool;
        new C1650SimpleArrayMap<>();
        this.mGraph = simpleArrayMap;
        new ArrayList<>();
        this.mSortResult = arrayList;
        new HashSet<>();
        this.mSortTmpMarked = hashSet;
    }

    public void addNode(@NonNull T t) {
        T node = t;
        if (!this.mGraph.containsKey(node)) {
            ArrayList<T> put = this.mGraph.put(node, null);
        }
    }

    public boolean contains(@NonNull T node) {
        return this.mGraph.containsKey(node);
    }

    public void addEdge(@NonNull T t, @NonNull T t2) {
        Throwable th;
        T node = t;
        T incomingEdge = t2;
        if (!this.mGraph.containsKey(node) || !this.mGraph.containsKey(incomingEdge)) {
            Throwable th2 = th;
            new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
            throw th2;
        }
        ArrayList<T> edges = this.mGraph.get(node);
        if (edges == null) {
            edges = getEmptyList();
            ArrayList<T> put = this.mGraph.put(node, edges);
        }
        boolean add = edges.add(incomingEdge);
    }

    @Nullable
    public List getIncomingEdges(@NonNull T node) {
        return this.mGraph.get(node);
    }

    @Nullable
    public List<T> getOutgoingEdges(@NonNull T t) {
        ArrayList arrayList;
        T node = t;
        ArrayList arrayList2 = null;
        int size = this.mGraph.size();
        for (int i = 0; i < size; i++) {
            ArrayList<T> edges = this.mGraph.valueAt(i);
            if (edges != null && edges.contains(node)) {
                if (arrayList2 == null) {
                    new ArrayList();
                    arrayList2 = arrayList;
                }
                boolean add = arrayList2.add(this.mGraph.keyAt(i));
            }
        }
        return arrayList2;
    }

    public boolean hasOutgoingEdges(@NonNull T t) {
        T node = t;
        int size = this.mGraph.size();
        for (int i = 0; i < size; i++) {
            ArrayList<T> edges = this.mGraph.valueAt(i);
            if (edges != null && edges.contains(node)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        int size = this.mGraph.size();
        for (int i = 0; i < size; i++) {
            ArrayList<T> edges = this.mGraph.valueAt(i);
            if (edges != null) {
                poolList(edges);
            }
        }
        this.mGraph.clear();
    }

    @NonNull
    public ArrayList<T> getSortedList() {
        this.mSortResult.clear();
        this.mSortTmpMarked.clear();
        int size = this.mGraph.size();
        for (int i = 0; i < size; i++) {
            dfs(this.mGraph.keyAt(i), this.mSortResult, this.mSortTmpMarked);
        }
        return this.mSortResult;
    }

    private void dfs(T t, ArrayList<T> arrayList, HashSet<T> hashSet) {
        Throwable th;
        T node = t;
        ArrayList<T> result = arrayList;
        HashSet<T> tmpMarked = hashSet;
        if (!result.contains(node)) {
            if (tmpMarked.contains(node)) {
                Throwable th2 = th;
                new RuntimeException("This graph contains cyclic dependencies");
                throw th2;
            }
            boolean add = tmpMarked.add(node);
            ArrayList<T> edges = this.mGraph.get(node);
            if (edges != null) {
                int size = edges.size();
                for (int i = 0; i < size; i++) {
                    dfs(edges.get(i), result, tmpMarked);
                }
            }
            boolean remove = tmpMarked.remove(node);
            boolean add2 = result.add(node);
        }
    }

    /* access modifiers changed from: package-private */
    public int size() {
        return this.mGraph.size();
    }

    @NonNull
    private ArrayList<T> getEmptyList() {
        ArrayList arrayList;
        ArrayList acquire = this.mListPool.acquire();
        if (acquire == null) {
            new ArrayList();
            acquire = arrayList;
        }
        return acquire;
    }

    private void poolList(@NonNull ArrayList<T> arrayList) {
        ArrayList<T> list = arrayList;
        list.clear();
        boolean release = this.mListPool.release(list);
    }
}
