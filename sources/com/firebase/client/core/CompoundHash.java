package com.firebase.client.core;

import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.ChildrenNode;
import com.firebase.client.snapshot.LeafNode;
import com.firebase.client.snapshot.Node;
import com.firebase.client.utilities.NodeSizeEstimator;
import com.firebase.client.utilities.Utilities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class CompoundHash {
    private final List<String> hashes;
    private final List<Path> posts;

    public interface SplitStrategy {
        boolean shouldSplit(CompoundHashBuilder compoundHashBuilder);
    }

    private CompoundHash(List<Path> list, List<String> list2) {
        Throwable th;
        List<Path> posts2 = list;
        List<String> hashes2 = list2;
        if (posts2.size() != hashes2.size() - 1) {
            Throwable th2 = th;
            new IllegalArgumentException("Number of posts need to be n-1 for n hashes in CompoundHash");
            throw th2;
        }
        this.posts = posts2;
        this.hashes = hashes2;
    }

    public List<Path> getPosts() {
        return Collections.unmodifiableList(this.posts);
    }

    public List<String> getHashes() {
        return Collections.unmodifiableList(this.hashes);
    }

    private static class SimpleSizeSplitStrategy implements SplitStrategy {
        private final long splitThreshold;

        public SimpleSizeSplitStrategy(Node node) {
            this.splitThreshold = Math.max(512, (long) Math.sqrt((double) (NodeSizeEstimator.estimateSerializedNodeSize(node) * 100)));
        }

        public boolean shouldSplit(CompoundHashBuilder compoundHashBuilder) {
            CompoundHashBuilder state = compoundHashBuilder;
            return ((long) state.currentHashLength()) > this.splitThreshold && !state.currentPath().getBack().equals(ChildKey.getPriorityKey());
        }
    }

    static class CompoundHashBuilder {
        /* access modifiers changed from: private */
        public final List<String> currentHashes;
        private Stack<ChildKey> currentPath;
        private int currentPathDepth;
        /* access modifiers changed from: private */
        public final List<Path> currentPaths;
        private int lastLeafDepth;
        private boolean needsComma;
        private StringBuilder optHashValueBuilder = null;
        private final SplitStrategy splitStrategy;

        public CompoundHashBuilder(SplitStrategy strategy) {
            Stack<ChildKey> stack;
            List<Path> list;
            List<String> list2;
            new Stack<>();
            this.currentPath = stack;
            this.lastLeafDepth = -1;
            this.needsComma = true;
            new ArrayList();
            this.currentPaths = list;
            new ArrayList();
            this.currentHashes = list2;
            this.splitStrategy = strategy;
        }

        public boolean buildingRange() {
            return this.optHashValueBuilder != null;
        }

        public int currentHashLength() {
            return this.optHashValueBuilder.length();
        }

        public Path currentPath() {
            return currentPath(this.currentPathDepth);
        }

        private Path currentPath(int i) {
            Path path;
            int depth = i;
            ChildKey[] segments = new ChildKey[depth];
            for (int i2 = 0; i2 < depth; i2++) {
                segments[i2] = (ChildKey) this.currentPath.get(i2);
            }
            new Path(segments);
            return path;
        }

        private void ensureRange() {
            StringBuilder sb;
            if (!buildingRange()) {
                new StringBuilder();
                this.optHashValueBuilder = sb;
                StringBuilder append = this.optHashValueBuilder.append("(");
                Iterator i$ = currentPath(this.currentPathDepth).iterator();
                while (i$.hasNext()) {
                    appendKey(this.optHashValueBuilder, i$.next());
                    StringBuilder append2 = this.optHashValueBuilder.append(":(");
                }
                this.needsComma = false;
            }
        }

        private void appendKey(StringBuilder builder, ChildKey key) {
            StringBuilder append = builder.append(Utilities.stringHashV2Representation(key.asString()));
        }

        /* access modifiers changed from: private */
        public void processLeaf(LeafNode<?> node) {
            ensureRange();
            this.lastLeafDepth = this.currentPathDepth;
            StringBuilder append = this.optHashValueBuilder.append(node.getHashRepresentation(Node.HashVersion.f278V2));
            this.needsComma = true;
            if (this.splitStrategy.shouldSplit(this)) {
                endRange();
            }
        }

        /* access modifiers changed from: private */
        public void startChild(ChildKey childKey) {
            ChildKey key = childKey;
            ensureRange();
            if (this.needsComma) {
                StringBuilder append = this.optHashValueBuilder.append(",");
            }
            appendKey(this.optHashValueBuilder, key);
            StringBuilder append2 = this.optHashValueBuilder.append(":(");
            if (this.currentPathDepth == this.currentPath.size()) {
                boolean add = this.currentPath.add(key);
            } else {
                Object obj = this.currentPath.set(this.currentPathDepth, key);
            }
            this.currentPathDepth++;
            this.needsComma = false;
        }

        /* access modifiers changed from: private */
        public void endChild() {
            this.currentPathDepth--;
            if (buildingRange()) {
                StringBuilder append = this.optHashValueBuilder.append(")");
            }
            this.needsComma = true;
        }

        /* access modifiers changed from: private */
        public void finishHashing() {
            Utilities.hardAssert(this.currentPathDepth == 0, "Can't finish hashing in the middle processing a child");
            if (buildingRange()) {
                endRange();
            }
            boolean add = this.currentHashes.add("");
        }

        private void endRange() {
            Utilities.hardAssert(buildingRange(), "Can't end range without starting a range!");
            for (int i = 0; i < this.currentPathDepth; i++) {
                StringBuilder append = this.optHashValueBuilder.append(")");
            }
            StringBuilder append2 = this.optHashValueBuilder.append(")");
            Path lastLeafPath = currentPath(this.lastLeafDepth);
            boolean add = this.currentHashes.add(Utilities.sha1HexDigest(this.optHashValueBuilder.toString()));
            boolean add2 = this.currentPaths.add(lastLeafPath);
            this.optHashValueBuilder = null;
        }
    }

    public static CompoundHash fromNode(Node node) {
        SplitStrategy splitStrategy;
        Node node2 = node;
        new SimpleSizeSplitStrategy(node2);
        return fromNode(node2, splitStrategy);
    }

    public static CompoundHash fromNode(Node node, SplitStrategy splitStrategy) {
        CompoundHashBuilder compoundHashBuilder;
        CompoundHash compoundHash;
        CompoundHash compoundHash2;
        Node node2 = node;
        SplitStrategy strategy = splitStrategy;
        if (node2.isEmpty()) {
            new CompoundHash(Collections.emptyList(), Collections.singletonList(""));
            return compoundHash2;
        }
        new CompoundHashBuilder(strategy);
        CompoundHashBuilder state = compoundHashBuilder;
        processNode(node2, state);
        state.finishHashing();
        new CompoundHash(state.currentPaths, state.currentHashes);
        return compoundHash;
    }

    /* access modifiers changed from: private */
    public static void processNode(Node node, CompoundHashBuilder compoundHashBuilder) {
        ChildrenNode.ChildVisitor visitor;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        Node node2 = node;
        CompoundHashBuilder state = compoundHashBuilder;
        if (node2.isLeafNode()) {
            state.processLeaf((LeafNode) node2);
        } else if (node2.isEmpty()) {
            Throwable th3 = th2;
            new IllegalArgumentException("Can't calculate hash on empty node!");
            throw th3;
        } else if (!(node2 instanceof ChildrenNode)) {
            Throwable th4 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Expected children node, but got: ").append(node2).toString());
            throw th4;
        } else {
            final CompoundHashBuilder compoundHashBuilder2 = state;
            new ChildrenNode.ChildVisitor() {
                public void visitChild(ChildKey name, Node child) {
                    compoundHashBuilder2.startChild(name);
                    CompoundHash.processNode(child, compoundHashBuilder2);
                    compoundHashBuilder2.endChild();
                }
            };
            ((ChildrenNode) node2).forEachChild(visitor, true);
        }
    }
}
