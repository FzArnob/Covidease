package com.firebase.client.utilities.tuple;

import com.firebase.client.core.Path;

public class PathAndId {

    /* renamed from: id */
    private long f281id;
    private Path path;

    public PathAndId(Path path2, long id) {
        this.path = path2;
        this.f281id = id;
    }

    public Path getPath() {
        return this.path;
    }

    public long getId() {
        return this.f281id;
    }
}
