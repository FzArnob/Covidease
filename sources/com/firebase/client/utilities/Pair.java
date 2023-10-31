package com.firebase.client.utilities;

public class Pair<T, U> {
    private final T first;
    private final U second;

    public Pair(T first2, U second2) {
        this.first = first2;
        this.second = second2;
    }

    public T getFirst() {
        return this.first;
    }

    public U getSecond() {
        return this.second;
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair pair = (Pair) o;
        if (this.first == null ? pair.first != null : !this.first.equals(pair.first)) {
            return false;
        }
        if (this.second == null ? pair.second != null : !this.second.equals(pair.second)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (31 * (this.first != null ? this.first.hashCode() : 0)) + (this.second != null ? this.second.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("Pair(").append(this.first).append(",").append(this.second).append(")").toString();
    }
}
