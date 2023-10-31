package com.firebase.client.core.utilities;

import com.firebase.client.core.Path;
import com.firebase.client.snapshot.ChildKey;
import java.util.Map;

public class Tree<T> {
    static final /* synthetic */ boolean $assertionsDisabled = (!Tree.class.desiredAssertionStatus());
    private ChildKey name;
    private TreeNode<T> node;
    private Tree<T> parent;

    public interface TreeFilter<T> {
        boolean filterTreeNode(Tree<T> tree);
    }

    public interface TreeVisitor<T> {
        void visitTree(Tree<T> tree);
    }

    public Tree(ChildKey name2, Tree<T> parent2, TreeNode<T> node2) {
        this.name = name2;
        this.parent = parent2;
        this.node = node2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Tree() {
        /*
            r7 = this;
            r0 = r7
            r1 = r0
            r2 = 0
            r3 = 0
            com.firebase.client.core.utilities.TreeNode r4 = new com.firebase.client.core.utilities.TreeNode
            r6 = r4
            r4 = r6
            r5 = r6
            r5.<init>()
            r1.<init>(r2, r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.client.core.utilities.Tree.<init>():void");
    }

    public TreeNode<T> lastNodeOnPath(Path path) {
        Path path2 = path;
        TreeNode<T> current = this.node;
        ChildKey front = path2.getFront();
        while (true) {
            ChildKey next = front;
            if (next == null) {
                return current;
            }
            TreeNode<T> childNode = current.children.containsKey(next) ? current.children.get(next) : null;
            if (childNode == null) {
                return current;
            }
            current = childNode;
            path2 = path2.popFront();
            front = path2.getFront();
        }
    }

    public Tree<T> subTree(Path path) {
        TreeNode treeNode;
        TreeNode treeNode2;
        Tree tree;
        Path path2 = path;
        Tree tree2 = this;
        ChildKey front = path2.getFront();
        while (true) {
            ChildKey next = front;
            if (next == null) {
                return tree2;
            }
            if (tree2.node.children.containsKey(next)) {
                treeNode2 = tree2.node.children.get(next);
            } else {
                treeNode2 = treeNode;
                new TreeNode();
            }
            new Tree(next, tree2, treeNode2);
            tree2 = tree;
            path2 = path2.popFront();
            front = path2.getFront();
        }
    }

    public T getValue() {
        return this.node.value;
    }

    public void setValue(T value) {
        this.node.value = value;
        updateParents();
    }

    public Tree<T> getParent() {
        return this.parent;
    }

    public ChildKey getName() {
        return this.name;
    }

    public Path getPath() {
        Path emptyPath;
        Path path;
        Throwable th;
        if (this.parent == null) {
            if (this.name != null) {
                emptyPath = path;
                new Path(this.name);
            } else {
                emptyPath = Path.getEmptyPath();
            }
            return emptyPath;
        } else if ($assertionsDisabled || this.name != null) {
            return this.parent.getPath().child(this.name);
        } else {
            Throwable th2 = th;
            new AssertionError();
            throw th2;
        }
    }

    public boolean hasChildren() {
        return !this.node.children.isEmpty();
    }

    public boolean isEmpty() {
        return this.node.value == null && this.node.children.isEmpty();
    }

    public void forEachDescendant(TreeVisitor<T> visitor) {
        forEachDescendant(visitor, false, false);
    }

    public void forEachDescendant(TreeVisitor<T> visitor, boolean includeSelf) {
        forEachDescendant(visitor, includeSelf, false);
    }

    public void forEachDescendant(TreeVisitor<T> treeVisitor, boolean z, boolean z2) {
        TreeVisitor treeVisitor2;
        TreeVisitor<T> visitor = treeVisitor;
        boolean includeSelf = z;
        boolean childrenFirst = z2;
        if (includeSelf && !childrenFirst) {
            visitor.visitTree(this);
        }
        final TreeVisitor<T> treeVisitor3 = visitor;
        final boolean z3 = childrenFirst;
        new TreeVisitor<T>(this) {
            final /* synthetic */ Tree this$0;

            {
                this.this$0 = r7;
            }

            public void visitTree(Tree<T> tree) {
                tree.forEachDescendant(treeVisitor3, true, z3);
            }
        };
        forEachChild(treeVisitor2);
        if (includeSelf && childrenFirst) {
            visitor.visitTree(this);
        }
    }

    public boolean forEachAncestor(TreeFilter<T> filter) {
        return forEachAncestor(filter, false);
    }

    public boolean forEachAncestor(TreeFilter<T> treeFilter, boolean includeSelf) {
        TreeFilter<T> filter = treeFilter;
        Tree tree = includeSelf ? this : this.parent;
        while (true) {
            Tree tree2 = tree;
            if (tree2 == null) {
                return false;
            }
            if (filter.filterTreeNode(tree2)) {
                return true;
            }
            tree = tree2.parent;
        }
    }

    public void forEachChild(TreeVisitor<T> treeVisitor) {
        Tree tree;
        TreeVisitor<T> visitor = treeVisitor;
        Object[] entries = this.node.children.entrySet().toArray();
        for (int i = 0; i < entries.length; i++) {
            Map.Entry<ChildKey, TreeNode<T>> entry = (Map.Entry) entries[i];
            new Tree(entry.getKey(), this, entry.getValue());
            visitor.visitTree(tree);
        }
    }

    private void updateParents() {
        if (this.parent != null) {
            this.parent.updateChild(this.name, this);
        }
    }

    private void updateChild(ChildKey childKey, Tree<T> tree) {
        ChildKey name2 = childKey;
        Tree<T> child = tree;
        boolean childEmpty = child.isEmpty();
        boolean childExists = this.node.children.containsKey(name2);
        if (childEmpty && childExists) {
            TreeNode<T> remove = this.node.children.remove(name2);
            updateParents();
        } else if (!childEmpty && !childExists) {
            TreeNode<T> put = this.node.children.put(name2, child.node);
            updateParents();
        }
    }

    public String toString() {
        return toString("");
    }

    /* access modifiers changed from: package-private */
    public String toString(String str) {
        StringBuilder sb;
        StringBuilder sb2;
        String prefix = str;
        String nodeName = this.name == null ? "<anon>" : this.name.asString();
        new StringBuilder();
        StringBuilder append = sb.append(prefix).append(nodeName).append("\n");
        TreeNode<T> treeNode = this.node;
        new StringBuilder();
        return append.append(treeNode.toString(sb2.append(prefix).append("\t").toString())).toString();
    }
}
