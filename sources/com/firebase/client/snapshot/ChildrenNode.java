package com.firebase.client.snapshot;

import com.firebase.client.collection.ImmutableSortedMap;
import com.firebase.client.collection.LLRBNode;
import com.firebase.client.core.Path;
import com.firebase.client.snapshot.Node;
import com.firebase.client.utilities.Utilities;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ChildrenNode implements Node {
    static final /* synthetic */ boolean $assertionsDisabled = (!ChildrenNode.class.desiredAssertionStatus());
    public static Comparator<ChildKey> NAME_ONLY_COMPARATOR;
    private final ImmutableSortedMap<ChildKey, Node> children;
    private String lazyHash;
    private final Node priority;

    static {
        Comparator<ChildKey> comparator;
        new Comparator<ChildKey>() {
            public int compare(ChildKey o1, ChildKey o2) {
                return o1.compareTo(o2);
            }
        };
        NAME_ONLY_COMPARATOR = comparator;
    }

    private static class NamedNodeIterator implements Iterator<NamedNode> {
        private final Iterator<Map.Entry<ChildKey, Node>> iterator;

        public NamedNodeIterator(Iterator<Map.Entry<ChildKey, Node>> iterator2) {
            this.iterator = iterator2;
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        public NamedNode next() {
            NamedNode namedNode;
            Map.Entry<ChildKey, Node> entry = this.iterator.next();
            new NamedNode(entry.getKey(), entry.getValue());
            return namedNode;
        }

        public void remove() {
            this.iterator.remove();
        }
    }

    public static abstract class ChildVisitor extends LLRBNode.NodeVisitor<ChildKey, Node> {
        public abstract void visitChild(ChildKey childKey, Node node);

        public ChildVisitor() {
        }

        public void visitEntry(ChildKey key, Node value) {
            visitChild(key, value);
        }
    }

    protected ChildrenNode() {
        this.lazyHash = null;
        this.children = ImmutableSortedMap.Builder.emptyMap(NAME_ONLY_COMPARATOR);
        this.priority = PriorityUtilities.NullPriority();
    }

    protected ChildrenNode(ImmutableSortedMap<ChildKey, Node> immutableSortedMap, Node node) {
        Throwable th;
        ImmutableSortedMap<ChildKey, Node> children2 = immutableSortedMap;
        Node priority2 = node;
        this.lazyHash = null;
        if (!children2.isEmpty() || priority2.isEmpty()) {
            this.priority = priority2;
            this.children = children2;
            return;
        }
        Throwable th2 = th;
        new IllegalArgumentException("Can't create empty ChildrenNode with priority!");
        throw th2;
    }

    public boolean hasChild(ChildKey name) {
        return !getImmediateChild(name).isEmpty();
    }

    public boolean isEmpty() {
        return this.children.isEmpty();
    }

    public int getChildCount() {
        return this.children.size();
    }

    public Object getValue() {
        return getValue(false);
    }

    public Object getValue(boolean z) {
        Map<String, Object> map;
        List<Object> list;
        StringBuilder sb;
        boolean useExportFormat = z;
        if (isEmpty()) {
            return null;
        }
        int numKeys = 0;
        int maxKey = 0;
        boolean allIntegerKeys = true;
        new HashMap<>();
        Map<String, Object> result = map;
        Iterator i$ = this.children.iterator();
        while (i$.hasNext()) {
            Map.Entry<ChildKey, Node> entry = i$.next();
            String key = entry.getKey().asString();
            Object put = result.put(key, entry.getValue().getValue(useExportFormat));
            numKeys++;
            if (allIntegerKeys) {
                if (key.length() <= 1 || key.charAt(0) != '0') {
                    Integer keyAsInt = Utilities.tryParseInt(key);
                    if (keyAsInt == null || keyAsInt.intValue() < 0) {
                        allIntegerKeys = false;
                    } else if (keyAsInt.intValue() > maxKey) {
                        maxKey = keyAsInt.intValue();
                    }
                } else {
                    allIntegerKeys = false;
                }
            }
        }
        if (useExportFormat || !allIntegerKeys || maxKey >= 2 * numKeys) {
            if (useExportFormat && !this.priority.isEmpty()) {
                Object put2 = result.put(".priority", this.priority.getValue());
            }
            return result;
        }
        new ArrayList<>(maxKey + 1);
        List<Object> arrayResult = list;
        for (int i = 0; i <= maxKey; i++) {
            new StringBuilder();
            boolean add = arrayResult.add(result.get(sb.append("").append(i).toString()));
        }
        return arrayResult;
    }

    public ChildKey getPredecessorChildKey(ChildKey childKey) {
        return this.children.getPredecessorKey(childKey);
    }

    public ChildKey getSuccessorChildKey(ChildKey childKey) {
        return this.children.getSuccessorKey(childKey);
    }

    public String getHashRepresentation(Node.HashVersion version) {
        StringBuilder sb;
        List<NamedNode> list;
        Throwable th;
        if (version != Node.HashVersion.f277V1) {
            Throwable th2 = th;
            new IllegalArgumentException("Hashes on children nodes only supported for V1");
            throw th2;
        }
        new StringBuilder();
        StringBuilder toHash = sb;
        if (!this.priority.isEmpty()) {
            StringBuilder append = toHash.append("priority:");
            StringBuilder append2 = toHash.append(this.priority.getHashRepresentation(Node.HashVersion.f277V1));
            StringBuilder append3 = toHash.append(":");
        }
        new ArrayList<>();
        List<NamedNode> nodes = list;
        boolean sawPriority = false;
        Iterator i$ = iterator();
        while (i$.hasNext()) {
            NamedNode node = i$.next();
            boolean add = nodes.add(node);
            sawPriority = sawPriority || !node.getNode().getPriority().isEmpty();
        }
        if (sawPriority) {
            Collections.sort(nodes, PriorityIndex.getInstance());
        }
        for (NamedNode node2 : nodes) {
            String hashString = node2.getNode().getHash();
            if (!hashString.equals("")) {
                StringBuilder append4 = toHash.append(":");
                StringBuilder append5 = toHash.append(node2.getName().asString());
                StringBuilder append6 = toHash.append(":");
                StringBuilder append7 = toHash.append(hashString);
            }
        }
        return toHash.toString();
    }

    public String getHash() {
        if (this.lazyHash == null) {
            String hashString = getHashRepresentation(Node.HashVersion.f277V1);
            this.lazyHash = hashString.isEmpty() ? "" : Utilities.sha1HexDigest(hashString);
        }
        return this.lazyHash;
    }

    public boolean isLeafNode() {
        return false;
    }

    public Node getPriority() {
        return this.priority;
    }

    public Node updatePriority(Node node) {
        ChildrenNode childrenNode;
        Node priority2 = node;
        if (this.children.isEmpty()) {
            return EmptyNode.Empty();
        }
        new ChildrenNode(this.children, priority2);
        return childrenNode;
    }

    public Node getImmediateChild(ChildKey childKey) {
        ChildKey name = childKey;
        if (name.isPriorityChildName() && !this.priority.isEmpty()) {
            return this.priority;
        }
        if (this.children.containsKey(name)) {
            return this.children.get(name);
        }
        return EmptyNode.Empty();
    }

    public Node getChild(Path path) {
        Path path2 = path;
        ChildKey front = path2.getFront();
        if (front != null) {
            return getImmediateChild(front).getChild(path2.popFront());
        }
        return this;
    }

    public void forEachChild(ChildVisitor visitor) {
        forEachChild(visitor, false);
    }

    public void forEachChild(ChildVisitor childVisitor, boolean includePriority) {
        LLRBNode.NodeVisitor nodeVisitor;
        ChildVisitor visitor = childVisitor;
        if (!includePriority || getPriority().isEmpty()) {
            this.children.inOrderTraversal(visitor);
            return;
        }
        final ChildVisitor childVisitor2 = visitor;
        new LLRBNode.NodeVisitor<ChildKey, Node>(this) {
            boolean passedPriorityKey = false;
            final /* synthetic */ ChildrenNode this$0;

            {
                this.this$0 = r6;
            }

            public void visitEntry(ChildKey childKey, Node node) {
                ChildKey key = childKey;
                Node value = node;
                if (!this.passedPriorityKey && key.compareTo(ChildKey.getPriorityKey()) > 0) {
                    this.passedPriorityKey = true;
                    childVisitor2.visitChild(ChildKey.getPriorityKey(), this.this$0.getPriority());
                }
                childVisitor2.visitChild(key, value);
            }
        };
        this.children.inOrderTraversal(nodeVisitor);
    }

    public ChildKey getFirstChildKey() {
        return this.children.getMinKey();
    }

    public ChildKey getLastChildKey() {
        return this.children.getMaxKey();
    }

    public Node updateChild(Path path, Node node) {
        Throwable th;
        Path path2 = path;
        Node newChildNode = node;
        ChildKey front = path2.getFront();
        if (front == null) {
            return newChildNode;
        }
        if (!front.isPriorityChildName()) {
            return updateImmediateChild(front, getImmediateChild(front).updateChild(path2.popFront(), newChildNode));
        } else if ($assertionsDisabled || PriorityUtilities.isValidPriority(newChildNode)) {
            return updatePriority(newChildNode);
        } else {
            Throwable th2 = th;
            new AssertionError();
            throw th2;
        }
    }

    public Iterator<NamedNode> iterator() {
        Iterator<NamedNode> it;
        new NamedNodeIterator(this.children.iterator());
        return it;
    }

    public Iterator<NamedNode> reverseIterator() {
        Iterator<NamedNode> it;
        new NamedNodeIterator(this.children.reverseIterator());
        return it;
    }

    public Node updateImmediateChild(ChildKey childKey, Node node) {
        ChildrenNode childrenNode;
        ChildKey key = childKey;
        Node newChildNode = node;
        if (key.isPriorityChildName()) {
            return updatePriority(newChildNode);
        }
        ImmutableSortedMap<ChildKey, Node> newChildren = this.children;
        if (newChildren.containsKey(key)) {
            newChildren = newChildren.remove(key);
        }
        if (!newChildNode.isEmpty()) {
            newChildren = newChildren.insert(key, newChildNode);
        }
        if (newChildren.isEmpty()) {
            return EmptyNode.Empty();
        }
        new ChildrenNode(newChildren, this.priority);
        return childrenNode;
    }

    public int compareTo(Node node) {
        Node o = node;
        if (isEmpty()) {
            if (o.isEmpty()) {
                return 0;
            }
            return -1;
        } else if (o.isLeafNode()) {
            return 1;
        } else {
            if (o.isEmpty()) {
                return 1;
            }
            if (o == Node.MAX_NODE) {
                return -1;
            }
            return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x005f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r12) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r7 = r1
            if (r7 != 0) goto L_0x0008
            r7 = 0
            r0 = r7
        L_0x0007:
            return r0
        L_0x0008:
            r7 = r1
            r8 = r0
            if (r7 != r8) goto L_0x000f
            r7 = 1
            r0 = r7
            goto L_0x0007
        L_0x000f:
            r7 = r1
            boolean r7 = r7 instanceof com.firebase.client.snapshot.ChildrenNode
            if (r7 != 0) goto L_0x0017
            r7 = 0
            r0 = r7
            goto L_0x0007
        L_0x0017:
            r7 = r1
            com.firebase.client.snapshot.ChildrenNode r7 = (com.firebase.client.snapshot.ChildrenNode) r7
            r2 = r7
            r7 = r0
            com.firebase.client.snapshot.Node r7 = r7.getPriority()
            r8 = r2
            com.firebase.client.snapshot.Node r8 = r8.getPriority()
            boolean r7 = r7.equals(r8)
            if (r7 != 0) goto L_0x002e
            r7 = 0
            r0 = r7
            goto L_0x0007
        L_0x002e:
            r7 = r0
            com.firebase.client.collection.ImmutableSortedMap<com.firebase.client.snapshot.ChildKey, com.firebase.client.snapshot.Node> r7 = r7.children
            int r7 = r7.size()
            r8 = r2
            com.firebase.client.collection.ImmutableSortedMap<com.firebase.client.snapshot.ChildKey, com.firebase.client.snapshot.Node> r8 = r8.children
            int r8 = r8.size()
            if (r7 == r8) goto L_0x0041
            r7 = 0
            r0 = r7
            goto L_0x0007
        L_0x0041:
            r7 = r0
            com.firebase.client.collection.ImmutableSortedMap<com.firebase.client.snapshot.ChildKey, com.firebase.client.snapshot.Node> r7 = r7.children
            java.util.Iterator r7 = r7.iterator()
            r3 = r7
            r7 = r2
            com.firebase.client.collection.ImmutableSortedMap<com.firebase.client.snapshot.ChildKey, com.firebase.client.snapshot.Node> r7 = r7.children
            java.util.Iterator r7 = r7.iterator()
            r4 = r7
        L_0x0051:
            r7 = r3
            boolean r7 = r7.hasNext()
            if (r7 == 0) goto L_0x0098
            r7 = r4
            boolean r7 = r7.hasNext()
            if (r7 == 0) goto L_0x0098
            r7 = r3
            java.lang.Object r7 = r7.next()
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7
            r5 = r7
            r7 = r4
            java.lang.Object r7 = r7.next()
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7
            r6 = r7
            r7 = r5
            java.lang.Object r7 = r7.getKey()
            com.firebase.client.snapshot.ChildKey r7 = (com.firebase.client.snapshot.ChildKey) r7
            r8 = r6
            java.lang.Object r8 = r8.getKey()
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x0093
            r7 = r5
            java.lang.Object r7 = r7.getValue()
            com.firebase.client.snapshot.Node r7 = (com.firebase.client.snapshot.Node) r7
            r8 = r6
            java.lang.Object r8 = r8.getValue()
            boolean r7 = r7.equals(r8)
            if (r7 != 0) goto L_0x0097
        L_0x0093:
            r7 = 0
            r0 = r7
            goto L_0x0007
        L_0x0097:
            goto L_0x0051
        L_0x0098:
            r7 = r3
            boolean r7 = r7.hasNext()
            if (r7 != 0) goto L_0x00a6
            r7 = r4
            boolean r7 = r7.hasNext()
            if (r7 == 0) goto L_0x00b2
        L_0x00a6:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            r10 = r7
            r7 = r10
            r8 = r10
            java.lang.String r9 = "Something went wrong internally."
            r8.<init>(r9)
            throw r7
        L_0x00b2:
            r7 = 1
            r0 = r7
            goto L_0x0007
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.client.snapshot.ChildrenNode.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int hashCode = 0;
        Iterator i$ = iterator();
        while (i$.hasNext()) {
            NamedNode entry = i$.next();
            hashCode = (17 * ((31 * hashCode) + entry.getName().hashCode())) + entry.getNode().hashCode();
        }
        return hashCode;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder builder = sb;
        toString(builder, 0);
        return builder.toString();
    }

    private static void addIndentation(StringBuilder sb, int i) {
        StringBuilder builder = sb;
        int indentation = i;
        for (int i2 = 0; i2 < indentation; i2++) {
            StringBuilder append = builder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        }
    }

    private void toString(StringBuilder sb, int i) {
        StringBuilder builder = sb;
        int indentation = i;
        if (!this.children.isEmpty() || !this.priority.isEmpty()) {
            StringBuilder append = builder.append("{\n");
            Iterator i$ = this.children.iterator();
            while (i$.hasNext()) {
                Map.Entry<ChildKey, Node> childEntry = i$.next();
                addIndentation(builder, indentation + 2);
                StringBuilder append2 = builder.append(childEntry.getKey().asString());
                StringBuilder append3 = builder.append("=");
                if (childEntry.getValue() instanceof ChildrenNode) {
                    ((ChildrenNode) childEntry.getValue()).toString(builder, indentation + 2);
                } else {
                    StringBuilder append4 = builder.append(childEntry.getValue().toString());
                }
                StringBuilder append5 = builder.append("\n");
            }
            if (!this.priority.isEmpty()) {
                addIndentation(builder, indentation + 2);
                StringBuilder append6 = builder.append(".priority=");
                StringBuilder append7 = builder.append(this.priority.toString());
                StringBuilder append8 = builder.append("\n");
            }
            addIndentation(builder, indentation);
            StringBuilder append9 = builder.append("}");
            return;
        }
        StringBuilder append10 = builder.append("{ }");
    }
}
