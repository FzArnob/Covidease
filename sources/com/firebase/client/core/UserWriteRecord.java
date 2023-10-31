package com.firebase.client.core;

import com.firebase.client.snapshot.Node;

public class UserWriteRecord {
    private final CompoundWrite merge;
    private final Node overwrite;
    private final Path path;
    private final boolean visible;
    private final long writeId;

    public UserWriteRecord(long writeId2, Path path2, Node overwrite2, boolean visible2) {
        this.writeId = writeId2;
        this.path = path2;
        this.overwrite = overwrite2;
        this.merge = null;
        this.visible = visible2;
    }

    public UserWriteRecord(long writeId2, Path path2, CompoundWrite merge2) {
        this.writeId = writeId2;
        this.path = path2;
        this.overwrite = null;
        this.merge = merge2;
        this.visible = true;
    }

    public long getWriteId() {
        return this.writeId;
    }

    public Path getPath() {
        return this.path;
    }

    public Node getOverwrite() {
        Throwable th;
        if (this.overwrite != null) {
            return this.overwrite;
        }
        Throwable th2 = th;
        new IllegalArgumentException("Can't access overwrite when write is a merge!");
        throw th2;
    }

    public CompoundWrite getMerge() {
        Throwable th;
        if (this.merge != null) {
            return this.merge;
        }
        Throwable th2 = th;
        new IllegalArgumentException("Can't access merge when write is an overwrite!");
        throw th2;
    }

    public boolean isMerge() {
        return this.merge != null;
    }

    public boolean isOverwrite() {
        return this.overwrite != null;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserWriteRecord record = (UserWriteRecord) o;
        if (this.writeId != record.writeId) {
            return false;
        }
        if (!this.path.equals(record.path)) {
            return false;
        }
        if (this.visible != record.visible) {
            return false;
        }
        if (this.overwrite == null ? record.overwrite != null : !this.overwrite.equals(record.overwrite)) {
            return false;
        }
        if (this.merge == null ? record.merge == null : this.merge.equals(record.merge)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (31 * ((31 * ((31 * ((31 * Long.valueOf(this.writeId).hashCode()) + Boolean.valueOf(this.visible).hashCode())) + this.path.hashCode())) + (this.overwrite != null ? this.overwrite.hashCode() : 0))) + (this.merge != null ? this.merge.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("UserWriteRecord{id=").append(this.writeId).append(" path=").append(this.path).append(" visible=").append(this.visible).append(" overwrite=").append(this.overwrite).append(" merge=").append(this.merge).append("}").toString();
    }
}
