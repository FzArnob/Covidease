package com.firebase.client.core;

import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.ChildrenNode;
import com.firebase.client.snapshot.Node;
import java.util.HashMap;
import java.util.Map;

class SparseSnapshotTree {
    private Map<ChildKey, SparseSnapshotTree> children = null;
    private Node value = null;

    public interface SparseSnapshotChildVisitor {
        void visitChild(ChildKey childKey, SparseSnapshotTree sparseSnapshotTree);
    }

    public interface SparseSnapshotTreeVisitor {
        void visitTree(Path path, Node node);
    }

    public SparseSnapshotTree() {
    }

    public void remember(Path path, Node node) {
        Object obj;
        Map<ChildKey, SparseSnapshotTree> map;
        Path path2 = path;
        Node data = node;
        if (path2.isEmpty()) {
            this.value = data;
            this.children = null;
        } else if (this.value != null) {
            this.value = this.value.updateChild(path2, data);
        } else {
            if (this.children == null) {
                new HashMap();
                this.children = map;
            }
            ChildKey childKey = path2.getFront();
            if (!this.children.containsKey(childKey)) {
                new SparseSnapshotTree();
                SparseSnapshotTree put = this.children.put(childKey, obj);
            }
            this.children.get(childKey).remember(path2.popFront(), data);
        }
    }

    public boolean forget(Path path) {
        ChildrenNode.ChildVisitor childVisitor;
        Path path2 = path;
        if (path2.isEmpty()) {
            this.value = null;
            this.children = null;
            return true;
        } else if (this.value != null) {
            if (this.value.isLeafNode()) {
                return false;
            }
            ChildrenNode childrenNode = (ChildrenNode) this.value;
            this.value = null;
            final Path path3 = path2;
            new ChildrenNode.ChildVisitor(this) {
                final /* synthetic */ SparseSnapshotTree this$0;

                {
                    this.this$0 = r6;
                }

                public void visitChild(ChildKey name, Node child) {
                    this.this$0.remember(path3.child(name), child);
                }
            };
            childrenNode.forEachChild(childVisitor);
            return forget(path2);
        } else if (this.children == null) {
            return true;
        } else {
            ChildKey childKey = path2.getFront();
            Path childPath = path2.popFront();
            if (this.children.containsKey(childKey) && this.children.get(childKey).forget(childPath)) {
                SparseSnapshotTree remove = this.children.remove(childKey);
            }
            if (!this.children.isEmpty()) {
                return false;
            }
            this.children = null;
            return true;
        }
    }

    public void forEachTree(Path path, SparseSnapshotTreeVisitor sparseSnapshotTreeVisitor) {
        SparseSnapshotChildVisitor sparseSnapshotChildVisitor;
        Path prefixPath = path;
        SparseSnapshotTreeVisitor visitor = sparseSnapshotTreeVisitor;
        if (this.value != null) {
            visitor.visitTree(prefixPath, this.value);
            return;
        }
        final Path path2 = prefixPath;
        final SparseSnapshotTreeVisitor sparseSnapshotTreeVisitor2 = visitor;
        new SparseSnapshotChildVisitor(this) {
            final /* synthetic */ SparseSnapshotTree this$0;

            {
                this.this$0 = r7;
            }

            public void visitChild(ChildKey key, SparseSnapshotTree tree) {
                tree.forEachTree(path2.child(key), sparseSnapshotTreeVisitor2);
            }
        };
        forEachChild(sparseSnapshotChildVisitor);
    }

    public void forEachChild(SparseSnapshotChildVisitor sparseSnapshotChildVisitor) {
        SparseSnapshotChildVisitor visitor = sparseSnapshotChildVisitor;
        if (this.children != null) {
            for (Map.Entry<ChildKey, SparseSnapshotTree> entry : this.children.entrySet()) {
                visitor.visitChild(entry.getKey(), entry.getValue());
            }
        }
    }
}
