package org.shaded.apache.http.message;

import java.util.NoSuchElementException;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HeaderIterator;

public class BasicHeaderIterator implements HeaderIterator {
    protected final Header[] allHeaders;
    protected int currentIndex;
    protected String headerName;

    public BasicHeaderIterator(Header[] headerArr, String str) {
        Throwable th;
        Header[] headers = headerArr;
        String name = str;
        if (headers == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Header array must not be null.");
            throw th2;
        }
        this.allHeaders = headers;
        this.headerName = name;
        this.currentIndex = findNext(-1);
    }

    /* access modifiers changed from: protected */
    public int findNext(int i) {
        boolean found;
        int from = i;
        if (from < -1) {
            return -1;
        }
        int to = this.allHeaders.length - 1;
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
    public boolean filterHeader(int index) {
        return this.headerName == null || this.headerName.equalsIgnoreCase(this.allHeaders[index].getName());
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
        this.currentIndex = findNext(current);
        return this.allHeaders[current];
    }

    public final Object next() throws NoSuchElementException {
        return nextHeader();
    }

    public void remove() throws UnsupportedOperationException {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException("Removing headers is not supported.");
        throw th2;
    }
}
