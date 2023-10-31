package com.firebase.client.core.operation;

import com.firebase.client.core.Path;
import com.firebase.client.snapshot.ChildKey;

public abstract class Operation {
    protected final Path path;
    protected final OperationSource source;
    protected final OperationType type;

    public enum OperationType {
    }

    public abstract Operation operationForChild(ChildKey childKey);

    protected Operation(OperationType type2, OperationSource source2, Path path2) {
        this.type = type2;
        this.source = source2;
        this.path = path2;
    }

    public Path getPath() {
        return this.path;
    }

    public OperationSource getSource() {
        return this.source;
    }

    public OperationType getType() {
        return this.type;
    }
}
