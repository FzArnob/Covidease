package com.firebase.client.core;

public class Tag {
    private final long tagNumber;

    public Tag(long tagNumber2) {
        this.tagNumber = tagNumber2;
    }

    public long getTagNumber() {
        return this.tagNumber;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("Tag{tagNumber=").append(this.tagNumber).append('}').toString();
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this.tagNumber != ((Tag) o).tagNumber) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (this.tagNumber ^ (this.tagNumber >>> 32));
    }
}
