package com.firebase.client.core.persistence;

import com.firebase.client.core.view.QuerySpec;

public class TrackedQuery {
    public final boolean active;
    public final boolean complete;

    /* renamed from: id */
    public final long f274id;
    public final long lastUse;
    public final QuerySpec querySpec;

    public TrackedQuery(long id, QuerySpec querySpec2, long j, boolean z, boolean z2) {
        Throwable th;
        QuerySpec querySpec3 = querySpec2;
        long lastUse2 = j;
        boolean complete2 = z;
        boolean active2 = z2;
        this.f274id = id;
        if (!querySpec3.loadsAllData() || querySpec3.isDefault()) {
            this.querySpec = querySpec3;
            this.lastUse = lastUse2;
            this.complete = complete2;
            this.active = active2;
            return;
        }
        Throwable th2 = th;
        new IllegalArgumentException("Can't create TrackedQuery for a non-default query that loads all data");
        throw th2;
    }

    public TrackedQuery updateLastUse(long lastUse2) {
        TrackedQuery trackedQuery;
        new TrackedQuery(this.f274id, this.querySpec, lastUse2, this.complete, this.active);
        return trackedQuery;
    }

    public TrackedQuery setComplete() {
        TrackedQuery trackedQuery;
        new TrackedQuery(this.f274id, this.querySpec, this.lastUse, true, this.active);
        return trackedQuery;
    }

    public TrackedQuery setActiveState(boolean isActive) {
        TrackedQuery trackedQuery;
        new TrackedQuery(this.f274id, this.querySpec, this.lastUse, this.complete, isActive);
        return trackedQuery;
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        TrackedQuery query = (TrackedQuery) o;
        return this.f274id == query.f274id && this.querySpec.equals(query.querySpec) && this.lastUse == query.lastUse && this.complete == query.complete && this.active == query.active;
    }

    public int hashCode() {
        return (31 * ((31 * ((31 * ((31 * Long.valueOf(this.f274id).hashCode()) + this.querySpec.hashCode())) + Long.valueOf(this.lastUse).hashCode())) + Boolean.valueOf(this.complete).hashCode())) + Boolean.valueOf(this.active).hashCode();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("TrackedQuery{id=").append(this.f274id).append(", querySpec=").append(this.querySpec).append(", lastUse=").append(this.lastUse).append(", complete=").append(this.complete).append(", active=").append(this.active).append("}").toString();
    }
}
