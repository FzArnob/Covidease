package org.shaded.apache.http.message;

import java.util.List;
import java.util.NoSuchElementException;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HeaderIterator;

public class BasicListHeaderIterator implements HeaderIterator {
    protected final List allHeaders;
    protected int currentIndex;
    protected String headerName;
    protected int lastIndex;

    public BasicListHeaderIterator(List list, String str) {
        Throwable th;
        List headers = list;
        String name = str;
        if (headers == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Header list must not be null.");
            throw th2;
        }
        this.allHeaders = headers;
        this.headerName = name;
        this.currentIndex = findNext(-1);
        this.lastIndex = -1;
    }

    /* access modifiers changed from: protected */
    public int findNext(int i) {
        boolean found;
        int from = i;
        if (from < -1) {
            return -1;
        }
        int to = this.allHeaders.size() - 1;
        boolean z = false;
        while (true) {
            found = z;
            if (!found && from < to) {
                from++;
                z = filterHeader(from);
            }
        }
        return found ? from : -1;
    }

    /* access modifiers changed from: protected */
    public boolean filterHeader(int i) {
        int index = i;
        if (this.headerName == null) {
            return true;
        }
        return this.headerName.equalsIgnoreCase(((Header) this.allHeaders.get(index)).getName());
    }

    public boolean hasNext() {
        return this.currentIndex >= 0;
    }

    public Header nextHeader() throws NoSuchElementException {
        Throwable th;
        int current = this.currentIndex;
        if (current < 0) {
            Throwable th2 = th;
            new NoSuchElementException("Iteration already finished.");
            throw th2;
        }
        this.lastIndex = current;
        this.currentIndex = findNext(current);
        return (Header) this.allHeaders.get(current);
    }

    public final Object next() throws NoSuchElementException {
        return nextHeader();
    }

    public void remove() throws UnsupportedOperationException {
        Throwable th;
        if (this.lastIndex < 0) {
            Throwable th2 = th;
            new IllegalStateException("No header to remove.");
            throw th2;
        }
        Object remove = this.allHeaders.remove(this.lastIndex);
        this.lastIndex = -1;
        this.currentIndex--;
    }
}
