package com.firebase.client.core.view;

import com.firebase.client.core.view.filter.IndexedFilter;
import com.firebase.client.core.view.filter.LimitedFilter;
import com.firebase.client.core.view.filter.NodeFilter;
import com.firebase.client.core.view.filter.RangedFilter;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.Index;
import com.firebase.client.snapshot.Node;
import com.firebase.client.snapshot.NodeUtilities;
import com.firebase.client.snapshot.PriorityIndex;
import com.shaded.fasterxml.jackson.databind.ObjectMapper;
import com.shaded.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QueryParams {
    static final /* synthetic */ boolean $assertionsDisabled = (!QueryParams.class.desiredAssertionStatus());
    public static final QueryParams DEFAULT_PARAMS;
    private static final String INDEX = "i";
    private static final String INDEX_END_NAME = "en";
    private static final String INDEX_END_VALUE = "ep";
    private static final String INDEX_START_NAME = "sn";
    private static final String INDEX_START_VALUE = "sp";
    private static final String LIMIT = "l";
    private static final String VIEW_FROM = "vf";
    private static final ObjectMapper mapperInstance;
    private Index index = PriorityIndex.getInstance();
    private ChildKey indexEndName = null;
    private Node indexEndValue = null;
    private ChildKey indexStartName = null;
    private Node indexStartValue = null;
    private String jsonSerialization = null;
    private Integer limit;
    private ViewFrom viewFrom;

    private enum ViewFrom {
    }

    static {
        QueryParams queryParams;
        ObjectMapper objectMapper;
        new QueryParams();
        DEFAULT_PARAMS = queryParams;
        new ObjectMapper();
        mapperInstance = objectMapper;
        ObjectMapper configure = mapperInstance.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
    }

    public QueryParams() {
    }

    public boolean hasStart() {
        return this.indexStartValue != null;
    }

    public Node getIndexStartValue() {
        Throwable th;
        if (hasStart()) {
            return this.indexStartValue;
        }
        Throwable th2 = th;
        new IllegalArgumentException("Cannot get index start value if start has not been set");
        throw th2;
    }

    public ChildKey getIndexStartName() {
        Throwable th;
        if (!hasStart()) {
            Throwable th2 = th;
            new IllegalArgumentException("Cannot get index start name if start has not been set");
            throw th2;
        } else if (this.indexStartName != null) {
            return this.indexStartName;
        } else {
            return ChildKey.getMinName();
        }
    }

    public boolean hasEnd() {
        return this.indexEndValue != null;
    }

    public Node getIndexEndValue() {
        Throwable th;
        if (hasEnd()) {
            return this.indexEndValue;
        }
        Throwable th2 = th;
        new IllegalArgumentException("Cannot get index end value if start has not been set");
        throw th2;
    }

    public ChildKey getIndexEndName() {
        Throwable th;
        if (!hasEnd()) {
            Throwable th2 = th;
            new IllegalArgumentException("Cannot get index end name if start has not been set");
            throw th2;
        } else if (this.indexEndName != null) {
            return this.indexEndName;
        } else {
            return ChildKey.getMaxName();
        }
    }

    public boolean hasLimit() {
        return this.limit != null;
    }

    public boolean hasAnchoredLimit() {
        return hasLimit() && this.viewFrom != null;
    }

    public int getLimit() {
        Throwable th;
        if (hasLimit()) {
            return this.limit.intValue();
        }
        Throwable th2 = th;
        new IllegalArgumentException("Cannot get limit if limit has not been set");
        throw th2;
    }

    public Index getIndex() {
        return this.index;
    }

    private QueryParams copy() {
        QueryParams queryParams;
        new QueryParams();
        QueryParams params = queryParams;
        params.limit = this.limit;
        params.indexStartValue = this.indexStartValue;
        params.indexStartName = this.indexStartName;
        params.indexEndValue = this.indexEndValue;
        params.indexEndName = this.indexEndName;
        params.viewFrom = this.viewFrom;
        params.index = this.index;
        return params;
    }

    public QueryParams limit(int limit2) {
        QueryParams copy = copy();
        copy.limit = Integer.valueOf(limit2);
        copy.viewFrom = null;
        return copy;
    }

    public QueryParams limitToFirst(int limit2) {
        QueryParams copy = copy();
        copy.limit = Integer.valueOf(limit2);
        copy.viewFrom = ViewFrom.LEFT;
        return copy;
    }

    public QueryParams limitToLast(int limit2) {
        QueryParams copy = copy();
        copy.limit = Integer.valueOf(limit2);
        copy.viewFrom = ViewFrom.RIGHT;
        return copy;
    }

    public QueryParams startAt(Node node, ChildKey childKey) {
        Throwable th;
        Node indexStartValue2 = node;
        ChildKey indexStartName2 = childKey;
        if ($assertionsDisabled || indexStartValue2.isLeafNode() || indexStartValue2.isEmpty()) {
            QueryParams copy = copy();
            copy.indexStartValue = indexStartValue2;
            copy.indexStartName = indexStartName2;
            return copy;
        }
        Throwable th2 = th;
        new AssertionError();
        throw th2;
    }

    public QueryParams endAt(Node node, ChildKey childKey) {
        Throwable th;
        Node indexEndValue2 = node;
        ChildKey indexEndName2 = childKey;
        if ($assertionsDisabled || indexEndValue2.isLeafNode() || indexEndValue2.isEmpty()) {
            QueryParams copy = copy();
            copy.indexEndValue = indexEndValue2;
            copy.indexEndName = indexEndName2;
            return copy;
        }
        Throwable th2 = th;
        new AssertionError();
        throw th2;
    }

    public QueryParams orderBy(Index index2) {
        QueryParams copy = copy();
        copy.index = index2;
        return copy;
    }

    public boolean isViewFromLeft() {
        return this.viewFrom != null ? this.viewFrom == ViewFrom.LEFT : hasStart();
    }

    public Map<String, Object> getWireProtocolParams() {
        Map<String, Object> map;
        new HashMap();
        Map<String, Object> queryObject = map;
        if (hasStart()) {
            Object put = queryObject.put(INDEX_START_VALUE, this.indexStartValue.getValue());
            if (this.indexStartName != null) {
                Object put2 = queryObject.put(INDEX_START_NAME, this.indexStartName.asString());
            }
        }
        if (hasEnd()) {
            Object put3 = queryObject.put(INDEX_END_VALUE, this.indexEndValue.getValue());
            if (this.indexEndName != null) {
                Object put4 = queryObject.put(INDEX_END_NAME, this.indexEndName.asString());
            }
        }
        if (this.limit != null) {
            Object put5 = queryObject.put(LIMIT, this.limit);
            ViewFrom viewFromToAdd = this.viewFrom;
            if (viewFromToAdd == null) {
                if (hasStart()) {
                    viewFromToAdd = ViewFrom.LEFT;
                } else {
                    viewFromToAdd = ViewFrom.RIGHT;
                }
            }
            switch (viewFromToAdd) {
                case LEFT:
                    Object put6 = queryObject.put(VIEW_FROM, LIMIT);
                    break;
                case RIGHT:
                    Object put7 = queryObject.put(VIEW_FROM, "r");
                    break;
            }
        }
        if (!this.index.equals(PriorityIndex.getInstance())) {
            Object put8 = queryObject.put(INDEX, this.index.getQueryDefinition());
        }
        return queryObject;
    }

    public boolean loadsAllData() {
        return !hasStart() && !hasEnd() && !hasLimit();
    }

    public boolean isDefault() {
        return loadsAllData() && this.index.equals(PriorityIndex.getInstance());
    }

    public boolean isValid() {
        return !hasStart() || !hasEnd() || !hasLimit() || hasAnchoredLimit();
    }

    public String toJSON() {
        Throwable th;
        if (this.jsonSerialization == null) {
            try {
                this.jsonSerialization = mapperInstance.writeValueAsString(getWireProtocolParams());
            } catch (IOException e) {
                IOException e2 = e;
                Throwable th2 = th;
                new RuntimeException(e2);
                throw th2;
            }
        }
        return this.jsonSerialization;
    }

    public static QueryParams fromQueryObject(Map<String, Object> map) {
        QueryParams queryParams;
        Map<String, Object> map2 = map;
        new QueryParams();
        QueryParams params = queryParams;
        params.limit = (Integer) map2.get(LIMIT);
        if (map2.containsKey(INDEX_START_VALUE)) {
            params.indexStartValue = NodeUtilities.NodeFromJSON(map2.get(INDEX_START_VALUE));
            String indexStartName2 = (String) map2.get(INDEX_START_NAME);
            if (indexStartName2 != null) {
                params.indexStartName = ChildKey.fromString(indexStartName2);
            }
        }
        if (map2.containsKey(INDEX_END_VALUE)) {
            params.indexEndValue = NodeUtilities.NodeFromJSON(map2.get(INDEX_END_VALUE));
            String indexEndName2 = (String) map2.get(INDEX_END_NAME);
            if (indexEndName2 != null) {
                params.indexEndName = ChildKey.fromString(indexEndName2);
            }
        }
        String viewFrom2 = (String) map2.get(VIEW_FROM);
        if (viewFrom2 != null) {
            params.viewFrom = viewFrom2.equals(LIMIT) ? ViewFrom.LEFT : ViewFrom.RIGHT;
        }
        String indexStr = (String) map2.get(INDEX);
        if (indexStr != null) {
            params.index = Index.fromQueryDefinition(indexStr);
        }
        return params;
    }

    public NodeFilter getNodeFilter() {
        NodeFilter nodeFilter;
        NodeFilter nodeFilter2;
        NodeFilter nodeFilter3;
        if (loadsAllData()) {
            new IndexedFilter(getIndex());
            return nodeFilter3;
        } else if (hasLimit()) {
            new LimitedFilter(this);
            return nodeFilter2;
        } else {
            new RangedFilter(this);
            return nodeFilter;
        }
    }

    public String toString() {
        return getWireProtocolParams().toString();
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QueryParams that = (QueryParams) o;
        if (this.limit == null ? that.limit != null : !this.limit.equals(that.limit)) {
            return false;
        }
        if (this.index == null ? that.index != null : !this.index.equals(that.index)) {
            return false;
        }
        if (this.indexEndName == null ? that.indexEndName != null : !this.indexEndName.equals(that.indexEndName)) {
            return false;
        }
        if (this.indexEndValue == null ? that.indexEndValue != null : !this.indexEndValue.equals(that.indexEndValue)) {
            return false;
        }
        if (this.indexStartName == null ? that.indexStartName != null : !this.indexStartName.equals(that.indexStartName)) {
            return false;
        }
        if (this.indexStartValue == null ? that.indexStartValue != null : !this.indexStartValue.equals(that.indexStartValue)) {
            return false;
        }
        if (isViewFromLeft() != that.isViewFromLeft()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (31 * ((31 * ((31 * ((31 * ((31 * ((31 * (this.limit != null ? this.limit.intValue() : 0)) + (isViewFromLeft() ? 1231 : 1237))) + (this.indexStartValue != null ? this.indexStartValue.hashCode() : 0))) + (this.indexStartName != null ? this.indexStartName.hashCode() : 0))) + (this.indexEndValue != null ? this.indexEndValue.hashCode() : 0))) + (this.indexEndName != null ? this.indexEndName.hashCode() : 0))) + (this.index != null ? this.index.hashCode() : 0);
    }
}
