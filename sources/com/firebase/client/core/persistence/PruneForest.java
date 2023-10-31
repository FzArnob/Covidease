package com.firebase.client.core.persistence;

import com.firebase.client.collection.ImmutableSortedMap;
import com.firebase.client.core.Path;
import com.firebase.client.core.utilities.ImmutableTree;
import com.firebase.client.core.utilities.Predicate;
import com.firebase.client.snapshot.ChildKey;
import java.util.Set;

public class PruneForest {
    private static final Predicate<Boolean> KEEP_PREDICATE;
    private static final ImmutableTree<Boolean> KEEP_TREE;
    private static final Predicate<Boolean> PRUNE_PREDICATE;
    private static final ImmutableTree<Boolean> PRUNE_TREE;
    private final ImmutableTree<Boolean> pruneForest;

    static {
        Predicate<Boolean> predicate;
        Predicate<Boolean> predicate2;
        ImmutableTree<Boolean> immutableTree;
        ImmutableTree<Boolean> immutableTree2;
        new Predicate<Boolean>() {
            public boolean evaluate(Boolean prune) {
                return !prune.booleanValue();
            }
        };
        KEEP_PREDICATE = predicate;
        new Predicate<Boolean>() {
            public boolean evaluate(Boolean prune) {
                return prune.booleanValue();
            }
        };
        PRUNE_PREDICATE = predicate2;
        new ImmutableTree<>(true);
        PRUNE_TREE = immutableTree;
        new ImmutableTree<>(false);
        KEEP_TREE = immutableTree2;
    }

    public PruneForest() {
        this.pruneForest = ImmutableTree.emptyInstance();
    }

    private PruneForest(ImmutableTree<Boolean> pruneForest2) {
        this.pruneForest = pruneForest2;
    }

    public boolean prunesAnything() {
        return this.pruneForest.containsMatchingValue(PRUNE_PREDICATE);
    }

    public boolean shouldPruneUnkeptDescendants(Path path) {
        Boolean shouldPrune = this.pruneForest.leafMostValue(path);
        return shouldPrune != null && shouldPrune.booleanValue();
    }

    public boolean shouldKeep(Path path) {
        Boolean shouldPrune = this.pruneForest.leafMostValue(path);
        return shouldPrune != null && !shouldPrune.booleanValue();
    }

    public boolean affectsPath(Path path) {
        Path path2 = path;
        return this.pruneForest.rootMostValue(path2) != null || !this.pruneForest.subtree(path2).isEmpty();
    }

    public PruneForest child(ChildKey key) {
        PruneForest pruneForest2;
        ImmutableTree<Boolean> immutableTree;
        ImmutableTree<Boolean> childPruneTree = this.pruneForest.getChild(key);
        if (childPruneTree == null) {
            new ImmutableTree<>(this.pruneForest.getValue());
            childPruneTree = immutableTree;
        } else if (childPruneTree.getValue() == null && this.pruneForest.getValue() != null) {
            childPruneTree = childPruneTree.set(Path.getEmptyPath(), this.pruneForest.getValue());
        }
        new PruneForest(childPruneTree);
        return pruneForest2;
    }

    public PruneForest child(Path path) {
        Path path2 = path;
        if (!path2.isEmpty()) {
            return child(path2.getFront()).child(path2.popFront());
        }
        return this;
    }

    public <T> T foldKeptNodes(T startValue, ImmutableTree.TreeVisitor<Void, T> treeVisitor) {
        ImmutableTree.TreeVisitor treeVisitor2;
        final ImmutableTree.TreeVisitor<Void, T> treeVisitor3 = treeVisitor;
        new ImmutableTree.TreeVisitor<Boolean, T>(this) {
            final /* synthetic */ PruneForest this$0;

            {
                this.this$0 = r6;
            }

            public T onNodeValue(Path path, Boolean prune, T t) {
                Path relativePath = path;
                C13903 accum = t;
                if (!prune.booleanValue()) {
                    return treeVisitor3.onNodeValue(relativePath, null, accum);
                }
                return accum;
            }
        };
        return this.pruneForest.fold(startValue, treeVisitor2);
    }

    public PruneForest prune(Path path) {
        PruneForest pruneForest2;
        Throwable th;
        Path path2 = path;
        if (this.pruneForest.rootMostValueMatching(path2, KEEP_PREDICATE) != null) {
            Throwable th2 = th;
            new IllegalArgumentException("Can't prune path that was kept previously!");
            throw th2;
        } else if (this.pruneForest.rootMostValueMatching(path2, PRUNE_PREDICATE) != null) {
            return this;
        } else {
            new PruneForest(this.pruneForest.setTree(path2, PRUNE_TREE));
            return pruneForest2;
        }
    }

    public PruneForest keep(Path path) {
        PruneForest pruneForest2;
        Path path2 = path;
        if (this.pruneForest.rootMostValueMatching(path2, KEEP_PREDICATE) != null) {
            return this;
        }
        new PruneForest(this.pruneForest.setTree(path2, KEEP_TREE));
        return pruneForest2;
    }

    public PruneForest keepAll(Path path, Set<ChildKey> set) {
        Path path2 = path;
        Set<ChildKey> children = set;
        if (this.pruneForest.rootMostValueMatching(path2, KEEP_PREDICATE) == null) {
            return doAll(path2, children, KEEP_TREE);
        }
        return this;
    }

    public PruneForest pruneAll(Path path, Set<ChildKey> set) {
        Throwable th;
        Path path2 = path;
        Set<ChildKey> children = set;
        if (this.pruneForest.rootMostValueMatching(path2, KEEP_PREDICATE) != null) {
            Throwable th2 = th;
            new IllegalArgumentException("Can't prune path that was kept previously!");
            throw th2;
        } else if (this.pruneForest.rootMostValueMatching(path2, PRUNE_PREDICATE) == null) {
            return doAll(path2, children, PRUNE_TREE);
        } else {
            return this;
        }
    }

    private PruneForest doAll(Path path, Set<ChildKey> children, ImmutableTree<Boolean> immutableTree) {
        PruneForest pruneForest2;
        ImmutableTree immutableTree2;
        Path path2 = path;
        ImmutableTree<Boolean> keepOrPruneTree = immutableTree;
        ImmutableTree<Boolean> subtree = this.pruneForest.subtree(path2);
        ImmutableSortedMap<ChildKey, ImmutableTree<Boolean>> childrenMap = subtree.getChildren();
        for (ChildKey key : children) {
            childrenMap = childrenMap.insert(key, keepOrPruneTree);
        }
        new ImmutableTree(subtree.getValue(), childrenMap);
        new PruneForest(this.pruneForest.setTree(path2, immutableTree2));
        return pruneForest2;
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (this == o) {
            return true;
        }
        if (!(o instanceof PruneForest)) {
            return false;
        }
        if (!this.pruneForest.equals(((PruneForest) o).pruneForest)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.pruneForest.hashCode();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("{PruneForest:").append(this.pruneForest.toString()).append("}").toString();
    }
}
