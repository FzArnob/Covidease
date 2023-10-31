package com.firebase.client.core.utilities;

import com.firebase.client.snapshot.ChildKey;
import java.util.HashMap;
import java.util.Map;

public class TreeNode<T> {
    public Map<ChildKey, TreeNode<T>> children;
    public T value;

    public TreeNode() {
        Map<ChildKey, TreeNode<T>> map;
        new HashMap();
        this.children = map;
    }

    /* access modifiers changed from: package-private */
    public String toString(String str) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        String prefix = str;
        new StringBuilder();
        String result = sb.append(prefix).append("<value>: ").append(this.value).append("\n").toString();
        if (this.children.isEmpty()) {
            new StringBuilder();
            return sb4.append(result).append(prefix).append("<empty>").toString();
        }
        for (Map.Entry<ChildKey, TreeNode<T>> entry : this.children.entrySet()) {
            new StringBuilder();
            StringBuilder append = sb2.append(result).append(prefix).append(entry.getKey()).append(":\n");
            new StringBuilder();
            result = append.append(entry.getValue().toString(sb3.append(prefix).append("\t").toString())).append("\n").toString();
        }
        return result;
    }
}
