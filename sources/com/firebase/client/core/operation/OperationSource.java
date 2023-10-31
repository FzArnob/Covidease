package com.firebase.client.core.operation;

import com.firebase.client.core.view.QueryParams;

public class OperationSource {
    static final /* synthetic */ boolean $assertionsDisabled = (!OperationSource.class.desiredAssertionStatus());
    public static final OperationSource SERVER;
    public static final OperationSource USER;
    private final QueryParams queryParams;
    private final Source source;
    private final boolean tagged;

    private enum Source {
    }

    static {
        OperationSource operationSource;
        OperationSource operationSource2;
        new OperationSource(Source.User, (QueryParams) null, false);
        USER = operationSource;
        new OperationSource(Source.Server, (QueryParams) null, false);
        SERVER = operationSource2;
    }

    public static OperationSource forServerTaggedQuery(QueryParams queryParams2) {
        OperationSource operationSource;
        new OperationSource(Source.Server, queryParams2, true);
        return operationSource;
    }

    public OperationSource(Source source2, QueryParams queryParams2, boolean z) {
        Throwable th;
        boolean tagged2 = z;
        this.source = source2;
        this.queryParams = queryParams2;
        this.tagged = tagged2;
        if (!$assertionsDisabled && tagged2 && !isFromServer()) {
            Throwable th2 = th;
            new AssertionError();
            throw th2;
        }
    }

    public boolean isFromUser() {
        return this.source == Source.User;
    }

    public boolean isFromServer() {
        return this.source == Source.Server;
    }

    public boolean isTagged() {
        return this.tagged;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("OperationSource{source=").append(this.source).append(", queryParams=").append(this.queryParams).append(", tagged=").append(this.tagged).append('}').toString();
    }

    public QueryParams getQueryParams() {
        return this.queryParams;
    }
}
