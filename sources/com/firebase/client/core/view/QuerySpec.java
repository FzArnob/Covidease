package com.firebase.client.core.view;

import com.firebase.client.core.Path;
import com.firebase.client.snapshot.Index;
import java.util.Map;

public class QuerySpec {
    private final QueryParams params;
    private final Path path;

    public static QuerySpec defaultQueryAtPath(Path path2) {
        QuerySpec querySpec;
        new QuerySpec(path2, QueryParams.DEFAULT_PARAMS);
        return querySpec;
    }

    public QuerySpec(Path path2, QueryParams params2) {
        this.path = path2;
        this.params = params2;
    }

    public Path getPath() {
        return this.path;
    }

    public QueryParams getParams() {
        return this.params;
    }

    public static QuerySpec fromPathAndQueryObject(Path path2, Map<String, Object> map) {
        QuerySpec querySpec;
        new QuerySpec(path2, QueryParams.fromQueryObject(map));
        return querySpec;
    }

    public Index getIndex() {
        return this.params.getIndex();
    }

    public boolean isDefault() {
        return this.params.isDefault();
    }

    public boolean loadsAllData() {
        return this.params.loadsAllData();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(this.path).append(":").append(this.params).toString();
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuerySpec that = (QuerySpec) o;
        if (!this.path.equals(that.path)) {
            return false;
        }
        if (!this.params.equals(that.params)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (31 * this.path.hashCode()) + this.params.hashCode();
    }
}
