package com.firebase.client.snapshot;

import com.firebase.client.FirebaseException;
import com.firebase.client.collection.ImmutableSortedMap;
import com.firebase.client.core.ServerValues;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeUtilities {
    public NodeUtilities() {
    }

    public static Node NodeFromJSON(Object value) throws FirebaseException {
        return NodeFromJSON(value, PriorityUtilities.NullPriority());
    }

    public static Node NodeFromJSON(Object obj, Node node) throws FirebaseException {
        Throwable th;
        Map map;
        Map map2;
        StringBuilder sb;
        Node node2;
        Map map3;
        Node node3;
        Throwable th2;
        StringBuilder sb2;
        Node node4;
        Node node5;
        Node node6;
        Node node7;
        Node node8;
        Object value = obj;
        Node priority = node;
        try {
            if (value instanceof Map) {
                Map mapValue = (Map) value;
                if (mapValue.containsKey(".priority")) {
                    priority = PriorityUtilities.parsePriority(mapValue.get(".priority"));
                }
                if (mapValue.containsKey(".value")) {
                    value = mapValue.get(".value");
                }
            }
            if (value == null) {
                return EmptyNode.Empty();
            }
            if (value instanceof String) {
                new StringNode((String) value, priority);
                return node8;
            } else if (value instanceof Long) {
                new LongNode((Long) value, priority);
                return node7;
            } else if (value instanceof Integer) {
                new LongNode(Long.valueOf((long) ((Integer) value).intValue()), priority);
                return node6;
            } else if (value instanceof Double) {
                new DoubleNode((Double) value, priority);
                return node5;
            } else if (value instanceof Boolean) {
                new BooleanNode((Boolean) value, priority);
                return node4;
            } else if ((value instanceof Map) || (value instanceof List)) {
                if (value instanceof Map) {
                    Map mapValue2 = (Map) value;
                    if (mapValue2.containsKey(ServerValues.NAME_SUBKEY_SERVERVALUE)) {
                        new DeferredValueNode(mapValue2, priority);
                        return node3;
                    }
                    new HashMap(mapValue2.size());
                    map2 = map3;
                    for (String key : mapValue2.keySet()) {
                        if (!key.startsWith(".")) {
                            Node childNode = NodeFromJSON(mapValue2.get(key));
                            if (!childNode.isEmpty()) {
                                Object put = map2.put(ChildKey.fromString(key), childNode);
                            }
                        }
                    }
                } else {
                    List listValue = (List) value;
                    new HashMap(listValue.size());
                    map2 = map;
                    for (int i = 0; i < listValue.size(); i++) {
                        new StringBuilder();
                        String key2 = sb.append("").append(i).toString();
                        Node childNode2 = NodeFromJSON(listValue.get(i));
                        if (!childNode2.isEmpty()) {
                            Object put2 = map2.put(ChildKey.fromString(key2), childNode2);
                        }
                    }
                }
                if (map2.isEmpty()) {
                    return EmptyNode.Empty();
                }
                new ChildrenNode(ImmutableSortedMap.Builder.fromMap(map2, ChildrenNode.NAME_ONLY_COMPARATOR), priority);
                return node2;
            } else {
                Throwable th3 = th2;
                new StringBuilder();
                new FirebaseException(sb2.append("Failed to parse node with class ").append(value.getClass().toString()).toString());
                throw th3;
            }
        } catch (ClassCastException e) {
            ClassCastException e2 = e;
            Throwable th4 = th;
            new FirebaseException("Failed to parse node", e2);
            throw th4;
        }
    }

    public static int nameAndPriorityCompare(ChildKey childKey, Node aPriority, ChildKey childKey2, Node bPriority) {
        ChildKey aKey = childKey;
        ChildKey bKey = childKey2;
        int priCmp = aPriority.compareTo(bPriority);
        if (priCmp != 0) {
            return priCmp;
        }
        return aKey.compareTo(bKey);
    }
}
