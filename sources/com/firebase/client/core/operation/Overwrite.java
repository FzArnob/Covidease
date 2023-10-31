package com.firebase.client.core.operation;

import com.firebase.client.core.Path;
import com.firebase.client.core.operation.Operation;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.Node;

public class Overwrite extends Operation {
    private final Node snapshot;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Overwrite(OperationSource source, Path path, Node snapshot2) {
        super(Operation.OperationType.Overwrite, source, path);
        this.snapshot = snapshot2;
    }

    public Node getSnapshot() {
        return this.snapshot;
    }

    public Operation operationForChild(ChildKey childKey) {
        Overwrite overwrite;
        Overwrite overwrite2;
        ChildKey childKey2 = childKey;
        if (this.path.isEmpty()) {
            new Overwrite(this.source, Path.getEmptyPath(), this.snapshot.getImmediateChild(childKey2));
            return overwrite2;
        }
        new Overwrite(this.source, this.path.popFront(), this.snapshot);
        return overwrite;
    }

    public String toString() {
        Object[] objArr = new Object[3];
        objArr[0] = getPath();
        Object[] objArr2 = objArr;
        objArr2[1] = getSource();
        Object[] objArr3 = objArr2;
        objArr3[2] = this.snapshot;
        return String.format("Overwrite { path=%s, source=%s, snapshot=%s }", objArr3);
    }
}
