package com.firebase.client.core.operation;

import com.firebase.client.core.Path;
import com.firebase.client.snapshot.ChildKey;

public class ListenComplete extends Operation {
    static final /* synthetic */ boolean $assertionsDisabled = (!ListenComplete.class.desiredAssertionStatus());

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ListenComplete(com.firebase.client.core.operation.OperationSource r9, com.firebase.client.core.Path r10) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r0
            com.firebase.client.core.operation.Operation$OperationType r4 = com.firebase.client.core.operation.Operation.OperationType.ListenComplete
            r5 = r1
            r6 = r2
            r3.<init>(r4, r5, r6)
            boolean r3 = $assertionsDisabled
            if (r3 != 0) goto L_0x0022
            r3 = r1
            boolean r3 = r3.isFromUser()
            if (r3 == 0) goto L_0x0022
            java.lang.AssertionError r3 = new java.lang.AssertionError
            r7 = r3
            r3 = r7
            r4 = r7
            java.lang.String r5 = "Can't have a listen complete from a user source"
            r4.<init>(r5)
            throw r3
        L_0x0022:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.client.core.operation.ListenComplete.<init>(com.firebase.client.core.operation.OperationSource, com.firebase.client.core.Path):void");
    }

    public Operation operationForChild(ChildKey childKey) {
        ListenComplete listenComplete;
        ListenComplete listenComplete2;
        ChildKey childKey2 = childKey;
        if (this.path.isEmpty()) {
            new ListenComplete(this.source, Path.getEmptyPath());
            return listenComplete2;
        }
        new ListenComplete(this.source, this.path.popFront());
        return listenComplete;
    }

    public String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = getPath();
        Object[] objArr2 = objArr;
        objArr2[1] = getSource();
        return String.format("ListenComplete { path=%s, source=%s }", objArr2);
    }
}
