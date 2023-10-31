package com.firebase.client.core.operation;

import com.firebase.client.core.Path;
import com.firebase.client.core.operation.Operation;
import com.firebase.client.core.utilities.ImmutableTree;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.utilities.Utilities;

public class AckUserWrite extends Operation {
    private final ImmutableTree<Boolean> affectedTree;
    private final boolean revert;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AckUserWrite(Path path, ImmutableTree<Boolean> affectedTree2, boolean revert2) {
        super(Operation.OperationType.AckUserWrite, OperationSource.USER, path);
        this.affectedTree = affectedTree2;
        this.revert = revert2;
    }

    public ImmutableTree<Boolean> getAffectedTree() {
        return this.affectedTree;
    }

    public boolean isRevert() {
        return this.revert;
    }

    public Operation operationForChild(ChildKey childKey) {
        Path path;
        AckUserWrite ackUserWrite;
        AckUserWrite ackUserWrite2;
        ChildKey childKey2 = childKey;
        if (!this.path.isEmpty()) {
            Utilities.hardAssert(this.path.getFront().equals(childKey2), "operationForChild called for unrelated child.");
            new AckUserWrite(this.path.popFront(), this.affectedTree, this.revert);
            return ackUserWrite2;
        } else if (this.affectedTree.getValue() != null) {
            Utilities.hardAssert(this.affectedTree.getChildren().isEmpty(), "affectedTree should not have overlapping affected paths.");
            return this;
        } else {
            ImmutableTree<Boolean> immutableTree = this.affectedTree;
            Path path2 = path;
            new Path(childKey2);
            new AckUserWrite(Path.getEmptyPath(), immutableTree.subtree(path2), this.revert);
            return ackUserWrite;
        }
    }

    public String toString() {
        Object[] objArr = new Object[3];
        objArr[0] = getPath();
        Object[] objArr2 = objArr;
        objArr2[1] = Boolean.valueOf(this.revert);
        Object[] objArr3 = objArr2;
        objArr3[2] = this.affectedTree;
        return String.format("AckUserWrite { path=%s, revert=%s, affectedTree=%s }", objArr3);
    }
}
