package com.firebase.client.core;

import com.firebase.client.core.SparseSnapshotTree;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.ChildrenNode;
import com.firebase.client.snapshot.Node;
import com.firebase.client.snapshot.NodeUtilities;
import com.firebase.client.snapshot.PriorityUtilities;
import com.firebase.client.utilities.Clock;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ServerValues {
    public static final String NAME_SUBKEY_SERVERVALUE = ".sv";

    public ServerValues() {
    }

    public static Map<String, Object> generateServerValues(Clock clock) {
        Map<String, Object> map;
        new HashMap();
        Map<String, Object> values = map;
        Object put = values.put("timestamp", Long.valueOf(clock.millis()));
        return values;
    }

    public static Object resolveDeferredValue(Object obj, Map<String, Object> map) {
        Object value = obj;
        Map<String, Object> serverValues = map;
        if (value instanceof Map) {
            Map mapValue = (Map) value;
            if (mapValue.containsKey(NAME_SUBKEY_SERVERVALUE)) {
                String serverValueKey = (String) mapValue.get(NAME_SUBKEY_SERVERVALUE);
                if (serverValues.containsKey(serverValueKey)) {
                    return serverValues.get(serverValueKey);
                }
            }
        }
        return value;
    }

    public static SparseSnapshotTree resolveDeferredValueTree(SparseSnapshotTree tree, Map<String, Object> serverValues) {
        SparseSnapshotTree sparseSnapshotTree;
        Path path;
        SparseSnapshotTree.SparseSnapshotTreeVisitor sparseSnapshotTreeVisitor;
        new SparseSnapshotTree();
        SparseSnapshotTree resolvedTree = sparseSnapshotTree;
        new Path("");
        final SparseSnapshotTree sparseSnapshotTree2 = resolvedTree;
        final Map<String, Object> map = serverValues;
        new SparseSnapshotTree.SparseSnapshotTreeVisitor() {
            public void visitTree(Path prefixPath, Node tree) {
                sparseSnapshotTree2.remember(prefixPath, ServerValues.resolveDeferredValueSnapshot(tree, map));
            }
        };
        tree.forEachTree(path, sparseSnapshotTreeVisitor);
        return resolvedTree;
    }

    public static Node resolveDeferredValueSnapshot(Node node, Map<String, Object> map) {
        SnapshotHolder snapshotHolder;
        ChildrenNode.ChildVisitor childVisitor;
        Node data = node;
        Map<String, Object> serverValues = map;
        Object priorityVal = data.getPriority().getValue();
        if (priorityVal instanceof Map) {
            Map priorityMapValue = (Map) priorityVal;
            if (priorityMapValue.containsKey(NAME_SUBKEY_SERVERVALUE)) {
                priorityVal = serverValues.get((String) priorityMapValue.get(NAME_SUBKEY_SERVERVALUE));
            }
        }
        Node priority = PriorityUtilities.parsePriority(priorityVal);
        if (data.isLeafNode()) {
            Object value = resolveDeferredValue(data.getValue(), serverValues);
            if (!value.equals(data.getValue()) || !priority.equals(data.getPriority())) {
                return NodeUtilities.NodeFromJSON(value, priority);
            }
            return data;
        } else if (data.isEmpty()) {
            return data;
        } else {
            ChildrenNode childNode = (ChildrenNode) data;
            new SnapshotHolder(childNode);
            SnapshotHolder holder = snapshotHolder;
            final Map<String, Object> map2 = serverValues;
            final SnapshotHolder snapshotHolder2 = holder;
            new ChildrenNode.ChildVisitor() {
                public void visitChild(ChildKey childKey, Node node) {
                    Path path;
                    ChildKey name = childKey;
                    Node child = node;
                    Node newChildNode = ServerValues.resolveDeferredValueSnapshot(child, map2);
                    if (newChildNode != child) {
                        new Path(name.asString());
                        snapshotHolder2.update(path, newChildNode);
                    }
                }
            };
            childNode.forEachChild(childVisitor);
            if (!holder.getRootNode().getPriority().equals(priority)) {
                return holder.getRootNode().updatePriority(priority);
            }
            return holder.getRootNode();
        }
    }

    public static CompoundWrite resolveDeferredValueMerge(CompoundWrite merge, Map<String, Object> map) {
        Map<String, Object> serverValues = map;
        CompoundWrite write = CompoundWrite.emptyWrite();
        Iterator i$ = merge.iterator();
        while (i$.hasNext()) {
            Map.Entry<Path, Node> entry = i$.next();
            write = write.addWrite(entry.getKey(), resolveDeferredValueSnapshot(entry.getValue(), serverValues));
        }
        return write;
    }
}
