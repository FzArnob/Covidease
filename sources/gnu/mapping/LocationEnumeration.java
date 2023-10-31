package gnu.mapping;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LocationEnumeration implements Iterator<Location>, Enumeration<Location> {
    NamedLocation[] bindings;
    SimpleEnvironment env;
    int index;
    LocationEnumeration inherited;
    NamedLocation nextLoc;
    NamedLocation prevLoc;

    public LocationEnumeration(NamedLocation[] bindings2, int count) {
        this.bindings = bindings2;
        this.index = count;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LocationEnumeration(gnu.mapping.SimpleEnvironment r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            gnu.mapping.NamedLocation[] r3 = r3.table
            r4 = 1
            r5 = r1
            int r5 = r5.log2Size
            int r4 = r4 << r5
            r2.<init>(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.LocationEnumeration.<init>(gnu.mapping.SimpleEnvironment):void");
    }

    public boolean hasMoreElements() {
        return this.env.hasMoreElements(this);
    }

    public Location nextElement() {
        return nextLocation();
    }

    public Location nextLocation() {
        Throwable th;
        if (this.nextLoc != null || hasMoreElements()) {
            NamedLocation namedLocation = this.prevLoc;
            if (this.prevLoc == null) {
                NamedLocation first = this.bindings[this.index];
                if (this.nextLoc != first) {
                    this.prevLoc = first;
                }
            }
            while (this.prevLoc != null && this.prevLoc.next != this.nextLoc) {
                this.prevLoc = this.prevLoc.next;
            }
            Location r = this.nextLoc;
            this.nextLoc = this.nextLoc.next;
            return r;
        }
        Throwable th2 = th;
        new NoSuchElementException();
        throw th2;
    }

    public boolean hasNext() {
        return hasMoreElements();
    }

    public Location next() {
        return nextElement();
    }

    public void remove() {
        Throwable th;
        NamedLocation curLoc = this.prevLoc != null ? this.prevLoc.next : this.bindings[this.index];
        if (curLoc == null || curLoc.next != this.nextLoc) {
            Throwable th2 = th;
            new IllegalStateException();
            throw th2;
        }
        curLoc.next = null;
        if (this.prevLoc != null) {
            this.prevLoc.next = this.nextLoc;
        } else {
            this.bindings[this.index] = this.nextLoc;
        }
        SimpleEnvironment simpleEnvironment = this.env;
        simpleEnvironment.num_bindings--;
    }
}
