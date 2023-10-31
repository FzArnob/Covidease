package com.firebase.client.core;

import com.firebase.client.FirebaseException;
import com.firebase.client.snapshot.ChildKey;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Path implements Iterable<ChildKey>, Comparable<Path> {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final Path EMPTY_PATH;
    /* access modifiers changed from: private */
    public final int end;
    /* access modifiers changed from: private */
    public final ChildKey[] pieces;
    /* access modifiers changed from: private */
    public final int start;

    static {
        boolean z;
        Path path;
        if (!Path.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
        new Path("");
        EMPTY_PATH = path;
    }

    public static Path getRelative(Path path, Path path2) {
        Throwable th;
        StringBuilder sb;
        Path from = path;
        Path to = path2;
        ChildKey outerFront = from.getFront();
        ChildKey innerFront = to.getFront();
        if (outerFront == null) {
            return to;
        }
        if (outerFront.equals(innerFront)) {
            return getRelative(from.popFront(), to.popFront());
        }
        Throwable th2 = th;
        new StringBuilder();
        new FirebaseException(sb.append("INTERNAL ERROR: ").append(to).append(" is not contained in ").append(from).toString());
        throw th2;
    }

    public static Path getEmptyPath() {
        return EMPTY_PATH;
    }

    public Path(ChildKey... childKeyArr) {
        Throwable th;
        ChildKey[] segments = childKeyArr;
        this.pieces = (ChildKey[]) Arrays.copyOf(segments, segments.length);
        this.start = 0;
        this.end = segments.length;
        ChildKey[] arr$ = segments;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            ChildKey name = arr$[i$];
            if ($assertionsDisabled || name != null) {
                i$++;
            } else {
                Throwable th2 = th;
                new AssertionError("Can't construct a path with a null value!");
                throw th2;
            }
        }
    }

    public Path(String pathString) {
        String[] segments = pathString.split("/");
        int count = 0;
        String[] arr$ = segments;
        int len$ = arr$.length;
        for (int i$ = 0; i$ < len$; i$++) {
            if (arr$[i$].length() > 0) {
                count++;
            }
        }
        this.pieces = new ChildKey[count];
        int j = 0;
        String[] arr$2 = segments;
        int i$2 = arr$2.length;
        for (int i$3 = 0; i$3 < i$2; i$3++) {
            String segment = arr$2[i$3];
            if (segment.length() > 0) {
                int i = j;
                j++;
                this.pieces[i] = ChildKey.fromString(segment);
            }
        }
        this.start = 0;
        this.end = this.pieces.length;
    }

    private Path(ChildKey[] pieces2, int start2, int end2) {
        this.pieces = pieces2;
        this.start = start2;
        this.end = end2;
    }

    public Path child(Path path) {
        Path path2;
        Path path3 = path;
        int newSize = size() + path3.size();
        ChildKey[] newPieces = new ChildKey[newSize];
        System.arraycopy(this.pieces, this.start, newPieces, 0, size());
        System.arraycopy(path3.pieces, path3.start, newPieces, size(), path3.size());
        new Path(newPieces, 0, newSize);
        return path2;
    }

    public Path child(ChildKey child) {
        Path path;
        int size = size();
        ChildKey[] newPieces = new ChildKey[(size + 1)];
        System.arraycopy(this.pieces, this.start, newPieces, 0, size);
        newPieces[size] = child;
        new Path(newPieces, 0, size + 1);
        return path;
    }

    public String toString() {
        StringBuilder sb;
        if (isEmpty()) {
            return "/";
        }
        new StringBuilder();
        StringBuilder builder = sb;
        for (int i = this.start; i < this.end; i++) {
            StringBuilder append = builder.append("/");
            StringBuilder append2 = builder.append(this.pieces[i].asString());
        }
        return builder.toString();
    }

    public String wireFormat() {
        StringBuilder sb;
        if (isEmpty()) {
            return "/";
        }
        new StringBuilder();
        StringBuilder builder = sb;
        for (int i = this.start; i < this.end; i++) {
            if (i > this.start) {
                StringBuilder append = builder.append("/");
            }
            StringBuilder append2 = builder.append(this.pieces[i].asString());
        }
        return builder.toString();
    }

    public ChildKey getFront() {
        if (isEmpty()) {
            return null;
        }
        return this.pieces[this.start];
    }

    public Path popFront() {
        Path path;
        int newStart = this.start;
        if (!isEmpty()) {
            newStart++;
        }
        new Path(this.pieces, newStart, this.end);
        return path;
    }

    public Path getParent() {
        Path path;
        if (isEmpty()) {
            return null;
        }
        new Path(this.pieces, this.start, this.end - 1);
        return path;
    }

    public ChildKey getBack() {
        if (!isEmpty()) {
            return this.pieces[this.end - 1];
        }
        return null;
    }

    public boolean isEmpty() {
        return this.start >= this.end;
    }

    public int size() {
        return this.end - this.start;
    }

    public Iterator<ChildKey> iterator() {
        Iterator<ChildKey> it;
        new Iterator<ChildKey>(this) {
            int offset = this.this$0.start;
            final /* synthetic */ Path this$0;

            {
                this.this$0 = r5;
            }

            public boolean hasNext() {
                return this.offset < this.this$0.end;
            }

            public ChildKey next() {
                Throwable th;
                if (!hasNext()) {
                    Throwable th2 = th;
                    new NoSuchElementException("No more elements.");
                    throw th2;
                }
                ChildKey child = this.this$0.pieces[this.offset];
                this.offset++;
                return child;
            }

            public void remove() {
                Throwable th;
                Throwable th2 = th;
                new UnsupportedOperationException("Can't remove component from immutable Path!");
                throw th2;
            }
        };
        return it;
    }

    public boolean contains(Path path) {
        Path other = path;
        if (size() > other.size()) {
            return false;
        }
        int i = this.start;
        int j = other.start;
        while (i < this.end) {
            if (!this.pieces[i].equals(other.pieces[j])) {
                return false;
            }
            i++;
            j++;
        }
        return true;
    }

    public boolean equals(Object obj) {
        Object other = obj;
        if (!(other instanceof Path)) {
            return false;
        }
        if (this == other) {
            return true;
        }
        Path otherPath = (Path) other;
        if (size() != otherPath.size()) {
            return false;
        }
        int i = this.start;
        int j = otherPath.start;
        while (i < this.end && j < otherPath.end) {
            if (!this.pieces[i].equals(otherPath.pieces[j])) {
                return false;
            }
            i++;
            j++;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = 0;
        for (int i = this.start; i < this.end; i++) {
            hashCode = (hashCode * 37) + this.pieces[i].hashCode();
        }
        return hashCode;
    }

    public int compareTo(Path path) {
        Path other = path;
        int i = this.start;
        int j = other.start;
        while (i < this.end && j < other.end) {
            int comp = this.pieces[i].compareTo(other.pieces[j]);
            if (comp != 0) {
                return comp;
            }
            i++;
            j++;
        }
        if (i == this.end && j == other.end) {
            return 0;
        }
        if (i == this.end) {
            return -1;
        }
        return 1;
    }
}
