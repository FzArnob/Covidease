package com.firebase.client.core.operation;

import com.firebase.client.core.CompoundWrite;
import com.firebase.client.core.Path;
import com.firebase.client.core.operation.Operation;
import com.firebase.client.snapshot.ChildKey;

public class Merge extends Operation {
    private final CompoundWrite children;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Merge(OperationSource source, Path path, CompoundWrite children2) {
        super(Operation.OperationType.Merge, source, path);
        this.children = children2;
    }

    public CompoundWrite getChildren() {
        return this.children;
    }

    public Operation operationForChild(ChildKey childKey) {
        Merge merge;
        Path path;
        Merge merge2;
        Operation operation;
        ChildKey childKey2 = childKey;
        if (this.path.isEmpty()) {
            CompoundWrite compoundWrite = this.children;
            Path path2 = path;
            new Path(childKey2);
            CompoundWrite childTree = compoundWrite.childCompoundWrite(path2);
            if (childTree.isEmpty()) {
                return null;
            }
            if (childTree.rootWrite() != null) {
                new Overwrite(this.source, Path.getEmptyPath(), childTree.rootWrite());
                return operation;
            }
            new Merge(this.source, Path.getEmptyPath(), childTree);
            return merge2;
        } else if (!this.path.getFront().equals(childKey2)) {
            return null;
        } else {
            new Merge(this.source, this.path.popFront(), this.children);
            return merge;
        }
    }

    public String toString() {
        Object[] objArr = new Object[3];
        objArr[0] = getPath();
        Object[] objArr2 = objArr;
        objArr2[1] = getSource();
        Object[] objArr3 = objArr2;
        objArr3[2] = this.children;
        return String.format("Merge { path=%s, source=%s, children=%s }", objArr3);
    }
}
