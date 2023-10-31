package com.firebase.client.core;

import com.firebase.client.core.utilities.ImmutableTree;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.NamedNode;
import com.firebase.client.snapshot.Node;
import com.firebase.client.snapshot.NodeUtilities;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CompoundWrite implements Iterable<Map.Entry<Path, Node>> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CompoundWrite.class.desiredAssertionStatus());
    private static final CompoundWrite EMPTY;
    private final ImmutableTree<Node> writeTree;

    static {
        CompoundWrite compoundWrite;
        ImmutableTree immutableTree;
        new ImmutableTree(null);
        new CompoundWrite(immutableTree);
        EMPTY = compoundWrite;
    }

    private CompoundWrite(ImmutableTree<Node> writeTree2) {
        this.writeTree = writeTree2;
    }

    public static CompoundWrite emptyWrite() {
        return EMPTY;
    }

    public static CompoundWrite fromValue(Map<String, Object> merge) {
        CompoundWrite compoundWrite;
        ImmutableTree immutableTree;
        Path path;
        ImmutableTree<Node> writeTree2 = ImmutableTree.emptyInstance();
        for (Map.Entry<String, Object> entry : merge.entrySet()) {
            new ImmutableTree(NodeUtilities.NodeFromJSON(entry.getValue()));
            new Path(entry.getKey());
            writeTree2 = writeTree2.setTree(path, immutableTree);
        }
        new CompoundWrite(writeTree2);
        return compoundWrite;
    }

    public static CompoundWrite fromChildMerge(Map<ChildKey, Node> merge) {
        CompoundWrite compoundWrite;
        ImmutableTree immutableTree;
        Path path;
        ImmutableTree<Node> writeTree2 = ImmutableTree.emptyInstance();
        for (Map.Entry<ChildKey, Node> entry : merge.entrySet()) {
            new ImmutableTree(entry.getValue());
            ImmutableTree immutableTree2 = immutableTree;
            Path path2 = path;
            new Path(entry.getKey());
            writeTree2 = writeTree2.setTree(path2, immutableTree2);
        }
        new CompoundWrite(writeTree2);
        return compoundWrite;
    }

    public static CompoundWrite fromPathMerge(Map<Path, Node> merge) {
        CompoundWrite compoundWrite;
        ImmutableTree immutableTree;
        ImmutableTree<Node> writeTree2 = ImmutableTree.emptyInstance();
        for (Map.Entry<Path, Node> entry : merge.entrySet()) {
            new ImmutableTree(entry.getValue());
            writeTree2 = writeTree2.setTree(entry.getKey(), immutableTree);
        }
        new CompoundWrite(writeTree2);
        return compoundWrite;
    }

    public CompoundWrite addWrite(Path path, Node node) {
        ImmutableTree immutableTree;
        CompoundWrite compoundWrite;
        CompoundWrite compoundWrite2;
        CompoundWrite compoundWrite3;
        ImmutableTree immutableTree2;
        Path path2 = path;
        Node node2 = node;
        if (path2.isEmpty()) {
            new ImmutableTree(node2);
            new CompoundWrite(immutableTree2);
            return compoundWrite3;
        }
        Path rootMostPath = this.writeTree.findRootMostPathWithValue(path2);
        if (rootMostPath != null) {
            Path relativePath = Path.getRelative(rootMostPath, path2);
            Node value = this.writeTree.get(rootMostPath);
            ChildKey back = relativePath.getBack();
            if (back == null || !back.isPriorityChildName() || !value.getChild(relativePath.getParent()).isEmpty()) {
                new CompoundWrite(this.writeTree.set(rootMostPath, value.updateChild(relativePath, node2)));
                return compoundWrite2;
            }
            return this;
        }
        new ImmutableTree(node2);
        new CompoundWrite(this.writeTree.setTree(path2, immutableTree));
        return compoundWrite;
    }

    public CompoundWrite addWrite(ChildKey key, Node node) {
        Path path;
        Path path2 = path;
        new Path(key);
        return addWrite(path2, node);
    }

    public CompoundWrite addWrites(Path path, CompoundWrite updates) {
        ImmutableTree.TreeVisitor treeVisitor;
        final Path path2 = path;
        new ImmutableTree.TreeVisitor<Node, CompoundWrite>(this) {
            final /* synthetic */ CompoundWrite this$0;

            {
                this.this$0 = r6;
            }

            public CompoundWrite onNodeValue(Path relativePath, Node value, CompoundWrite accum) {
                return accum.addWrite(path2.child(relativePath), value);
            }
        };
        return (CompoundWrite) updates.writeTree.fold(this, treeVisitor);
    }

    public CompoundWrite removeWrite(Path path) {
        CompoundWrite compoundWrite;
        Path path2 = path;
        if (path2.isEmpty()) {
            return EMPTY;
        }
        new CompoundWrite(this.writeTree.setTree(path2, ImmutableTree.emptyInstance()));
        return compoundWrite;
    }

    public boolean hasCompleteWrite(Path path) {
        return getCompleteNode(path) != null;
    }

    public Node rootWrite() {
        return this.writeTree.getValue();
    }

    public Node getCompleteNode(Path path) {
        Path path2 = path;
        Path rootMost = this.writeTree.findRootMostPathWithValue(path2);
        if (rootMost != null) {
            return this.writeTree.get(rootMost).getChild(Path.getRelative(rootMost, path2));
        }
        return null;
    }

    public List<NamedNode> getCompleteChildren() {
        List<NamedNode> list;
        Object obj;
        Object obj2;
        new ArrayList();
        List<NamedNode> children = list;
        if (this.writeTree.getValue() != null) {
            for (NamedNode entry : this.writeTree.getValue()) {
                new NamedNode(entry.getName(), entry.getNode());
                boolean add = children.add(obj2);
            }
        } else {
            Iterator i$ = this.writeTree.getChildren().iterator();
            while (i$.hasNext()) {
                Map.Entry<ChildKey, ImmutableTree<Node>> entry2 = i$.next();
                ImmutableTree<Node> childTree = entry2.getValue();
                if (childTree.getValue() != null) {
                    new NamedNode(entry2.getKey(), childTree.getValue());
                    boolean add2 = children.add(obj);
                }
            }
        }
        return children;
    }

    public CompoundWrite childCompoundWrite(Path path) {
        CompoundWrite compoundWrite;
        CompoundWrite compoundWrite2;
        ImmutableTree immutableTree;
        Path path2 = path;
        if (path2.isEmpty()) {
            return this;
        }
        Node shadowingNode = getCompleteNode(path2);
        if (shadowingNode != null) {
            new ImmutableTree(shadowingNode);
            new CompoundWrite(immutableTree);
            return compoundWrite2;
        }
        new CompoundWrite(this.writeTree.subtree(path2));
        return compoundWrite;
    }

    public Map<ChildKey, CompoundWrite> childCompoundWrites() {
        Map<ChildKey, CompoundWrite> map;
        Object obj;
        new HashMap();
        Map<ChildKey, CompoundWrite> children = map;
        Iterator i$ = this.writeTree.getChildren().iterator();
        while (i$.hasNext()) {
            Map.Entry<ChildKey, ImmutableTree<Node>> entries = i$.next();
            new CompoundWrite(entries.getValue());
            CompoundWrite put = children.put(entries.getKey(), obj);
        }
        return children;
    }

    public boolean isEmpty() {
        return this.writeTree.isEmpty();
    }

    private Node applySubtreeWrite(Path path, ImmutableTree<Node> immutableTree, Node node) {
        Throwable th;
        Path relativePath = path;
        ImmutableTree<Node> writeTree2 = immutableTree;
        Node node2 = node;
        if (writeTree2.getValue() != null) {
            return node2.updateChild(relativePath, writeTree2.getValue());
        }
        Node priorityWrite = null;
        Iterator i$ = writeTree2.getChildren().iterator();
        while (i$.hasNext()) {
            Map.Entry<ChildKey, ImmutableTree<Node>> childTreeEntry = i$.next();
            ImmutableTree<Node> childTree = childTreeEntry.getValue();
            ChildKey childKey = childTreeEntry.getKey();
            if (!childKey.isPriorityChildName()) {
                node2 = applySubtreeWrite(relativePath.child(childKey), childTree, node2);
            } else if ($assertionsDisabled || childTree.getValue() != null) {
                priorityWrite = childTree.getValue();
            } else {
                Throwable th2 = th;
                new AssertionError("Priority writes must always be leaf nodes");
                throw th2;
            }
        }
        if (!node2.getChild(relativePath).isEmpty() && priorityWrite != null) {
            node2 = node2.updateChild(relativePath.child(ChildKey.getPriorityKey()), priorityWrite);
        }
        return node2;
    }

    public Node apply(Node node) {
        return applySubtreeWrite(Path.getEmptyPath(), this.writeTree, node);
    }

    public Map<String, Object> getValue(boolean exportFormat) {
        Map<String, Object> map;
        ImmutableTree.TreeVisitor treeVisitor;
        new HashMap();
        Map<String, Object> writes = map;
        final Map<String, Object> map2 = writes;
        final boolean z = exportFormat;
        new ImmutableTree.TreeVisitor<Node, Void>(this) {
            final /* synthetic */ CompoundWrite this$0;

            {
                this.this$0 = r7;
            }

            public Void onNodeValue(Path relativePath, Node value, Void voidR) {
                Void voidR2 = voidR;
                Object put = map2.put(relativePath.wireFormat(), value.getValue(z));
                return null;
            }
        };
        this.writeTree.foreach(treeVisitor);
        return writes;
    }

    public Iterator<Map.Entry<Path, Node>> iterator() {
        return this.writeTree.iterator();
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        return ((CompoundWrite) o).getValue(true).equals(getValue(true));
    }

    public int hashCode() {
        return getValue(true).hashCode();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("CompoundWrite{").append(getValue(true).toString()).append("}").toString();
    }
}
