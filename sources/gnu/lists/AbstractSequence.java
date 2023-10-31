package gnu.lists;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public abstract class AbstractSequence {
    public abstract int createPos(int i, boolean z);

    public abstract Object get(int i);

    public abstract int size();

    public AbstractSequence() {
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int rank() {
        return 1;
    }

    public int getEffectiveIndex(int[] indexes) {
        return indexes[0];
    }

    public Object get(int[] indexes) {
        return get(indexes[0]);
    }

    public Object set(int[] indexes, Object value) {
        return set(indexes[0], value);
    }

    public int getLowBound(int i) {
        int i2 = i;
        return 0;
    }

    public int getSize(int dim) {
        return dim == 0 ? size() : 1;
    }

    /* access modifiers changed from: protected */
    public RuntimeException unsupported(String text) {
        StringBuilder sb;
        new StringBuilder();
        return unsupportedException(sb.append(getClass().getName()).append(" does not implement ").append(text).toString());
    }

    public static RuntimeException unsupportedException(String text) {
        RuntimeException runtimeException;
        new UnsupportedOperationException(text);
        return runtimeException;
    }

    public Object set(int i, Object obj) {
        int i2 = i;
        Object obj2 = obj;
        throw unsupported("set");
    }

    public void fill(Object obj) {
        Object value = obj;
        int i = startPos();
        while (true) {
            int nextPos = nextPos(i);
            i = nextPos;
            if (nextPos != 0) {
                setPosPrevious(i, value);
            } else {
                return;
            }
        }
    }

    public void fillPosRange(int fromPos, int i, Object obj) {
        int toPos = i;
        Object value = obj;
        int copyPos = copyPos(fromPos);
        while (true) {
            int i2 = copyPos;
            if (compare(i2, toPos) < 0) {
                setPosNext(i2, value);
                copyPos = nextPos(i2);
            } else {
                releasePos(i2);
                return;
            }
        }
    }

    public void fill(int fromIndex, int toIndex, Object obj) {
        Object value = obj;
        int i = createPos(fromIndex, false);
        int limit = createPos(toIndex, true);
        while (compare(i, limit) < 0) {
            setPosNext(i, value);
            i = nextPos(i);
        }
        releasePos(i);
        releasePos(limit);
    }

    public int indexOf(Object obj) {
        Object o = obj;
        int i = 0;
        int iter = startPos();
        while (true) {
            int nextPos = nextPos(iter);
            iter = nextPos;
            if (nextPos != 0) {
                Object v = getPosPrevious(iter);
                if (o != null) {
                    if (o.equals(v)) {
                        break;
                    }
                    i++;
                } else if (v == null) {
                    break;
                } else {
                    i++;
                }
            } else {
                return -1;
            }
        }
        releasePos(iter);
        return i;
    }

    public int lastIndexOf(Object obj) {
        Object o = obj;
        int n = size();
        while (true) {
            n--;
            if (n >= 0) {
                Object e = get(n);
                if (o != null) {
                    if (o.equals(e)) {
                        break;
                    }
                } else if (e == null) {
                    break;
                }
            } else {
                return -1;
            }
        }
        return n;
    }

    public int nextMatching(int i, ItemPredicate itemPredicate, int i2, boolean descend) {
        int startPos = i;
        ItemPredicate type = itemPredicate;
        int endPos = i2;
        if (descend) {
            throw unsupported("nextMatching with descend");
        }
        int ipos = startPos;
        while (compare(ipos, endPos) < 0) {
            ipos = nextPos(ipos);
            if (type.isInstancePos(this, ipos)) {
                return ipos;
            }
        }
        return 0;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public boolean containsAll(Collection c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    public final Enumeration elements() {
        return getIterator();
    }

    public final SeqPosition getIterator() {
        return getIterator(0);
    }

    public SeqPosition getIterator(int index) {
        SeqPosition seqPosition;
        new SeqPosition(this, index, false);
        return seqPosition;
    }

    public SeqPosition getIteratorAtPos(int ipos) {
        SeqPosition seqPosition;
        new SeqPosition(this, copyPos(ipos));
        return seqPosition;
    }

    public final Iterator iterator() {
        return getIterator();
    }

    public final ListIterator listIterator() {
        return getIterator(0);
    }

    public final ListIterator listIterator(int index) {
        return getIterator(index);
    }

    /* access modifiers changed from: protected */
    public int addPos(int i, Object obj) {
        int i2 = i;
        Object obj2 = obj;
        throw unsupported("addPos");
    }

    public boolean add(Object o) {
        int addPos = addPos(endPos(), o);
        return true;
    }

    public void add(int index, Object o) {
        int pos = createPos(index, false);
        int addPos = addPos(pos, o);
        releasePos(pos);
    }

    public boolean addAll(Collection c) {
        return addAll(size(), c);
    }

    public boolean addAll(int index, Collection c) {
        boolean changed = false;
        int pos = createPos(index, false);
        for (Object addPos : c) {
            pos = addPos(pos, addPos);
            changed = true;
        }
        releasePos(pos);
        return changed;
    }

    public void removePos(int i, int i2) {
        int ipos = i;
        int count = i2;
        int rpos = createRelativePos(ipos, count, false);
        if (count >= 0) {
            removePosRange(ipos, rpos);
        } else {
            removePosRange(rpos, ipos);
        }
        releasePos(rpos);
    }

    /* access modifiers changed from: protected */
    public void removePosRange(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        throw unsupported("removePosRange");
    }

    public Object remove(int i) {
        Throwable th;
        int index = i;
        if (index < 0 || index >= size()) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        }
        int ipos = createPos(index, false);
        Object result = getPosNext(ipos);
        removePos(ipos, 1);
        releasePos(ipos);
        return result;
    }

    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index < 0) {
            return false;
        }
        int ipos = createPos(index, false);
        removePos(ipos, 1);
        releasePos(ipos);
        return true;
    }

    public boolean removeAll(Collection collection) {
        Collection c = collection;
        boolean changed = false;
        int iter = startPos();
        while (true) {
            int nextPos = nextPos(iter);
            iter = nextPos;
            if (nextPos == 0) {
                return changed;
            }
            if (c.contains(getPosPrevious(iter))) {
                removePos(iter, -1);
                changed = true;
            }
        }
    }

    public boolean retainAll(Collection collection) {
        Collection c = collection;
        boolean changed = false;
        int iter = startPos();
        while (true) {
            int nextPos = nextPos(iter);
            iter = nextPos;
            if (nextPos == 0) {
                return changed;
            }
            if (!c.contains(getPosPrevious(iter))) {
                removePos(iter, -1);
                changed = true;
            }
        }
    }

    public void clear() {
        removePos(startPos(), endPos());
    }

    /* access modifiers changed from: protected */
    public boolean isAfterPos(int ipos) {
        return (ipos & 1) != 0;
    }

    public int createRelativePos(int pos, int delta, boolean isAfter) {
        return createPos(nextIndex(pos) + delta, isAfter);
    }

    public int startPos() {
        return 0;
    }

    public int endPos() {
        return -1;
    }

    /* access modifiers changed from: protected */
    public void releasePos(int ipos) {
    }

    public int copyPos(int ipos) {
        return ipos;
    }

    /* access modifiers changed from: protected */
    public int getIndexDifference(int ipos1, int ipos0) {
        return nextIndex(ipos1) - nextIndex(ipos0);
    }

    /* access modifiers changed from: protected */
    public int nextIndex(int ipos) {
        return getIndexDifference(ipos, startPos());
    }

    /* access modifiers changed from: protected */
    public int fromEndIndex(int ipos) {
        return size() - nextIndex(ipos);
    }

    /* access modifiers changed from: protected */
    public int getContainingSequenceSize(int i) {
        int i2 = i;
        return size();
    }

    public boolean hasNext(int ipos) {
        return nextIndex(ipos) != size();
    }

    public int getNextKind(int ipos) {
        return hasNext(ipos) ? 32 : 0;
    }

    public String getNextTypeName(int i) {
        int i2 = i;
        return null;
    }

    public Object getNextTypeObject(int i) {
        int i2 = i;
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean hasPrevious(int ipos) {
        return nextIndex(ipos) != 0;
    }

    public int nextPos(int i) {
        int ipos = i;
        if (!hasNext(ipos)) {
            return 0;
        }
        int next = createRelativePos(ipos, 1, true);
        releasePos(ipos);
        return next;
    }

    public int previousPos(int i) {
        int ipos = i;
        if (!hasPrevious(ipos)) {
            return 0;
        }
        int next = createRelativePos(ipos, -1, false);
        releasePos(ipos);
        return next;
    }

    public final boolean gotoChildrenStart(TreePosition treePosition) {
        TreePosition pos = treePosition;
        int ipos = firstChildPos(pos.getPos());
        if (ipos == 0) {
            return false;
        }
        pos.push(this, ipos);
        return true;
    }

    public int firstChildPos(int i) {
        int i2 = i;
        return 0;
    }

    public int firstChildPos(int ipos, ItemPredicate itemPredicate) {
        ItemPredicate predicate = itemPredicate;
        int child = firstChildPos(ipos);
        if (child == 0) {
            return 0;
        }
        if (predicate.isInstancePos(this, child)) {
            return child;
        }
        return nextMatching(child, predicate, endPos(), false);
    }

    public int firstAttributePos(int i) {
        int i2 = i;
        return 0;
    }

    public int parentPos(int i) {
        int i2 = i;
        return endPos();
    }

    /* access modifiers changed from: protected */
    public boolean gotoParent(TreePosition treePosition) {
        TreePosition pos = treePosition;
        if (pos.depth < 0) {
            return false;
        }
        pos.pop();
        return true;
    }

    public int getAttributeLength() {
        return 0;
    }

    public Object getAttribute(int i) {
        int i2 = i;
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean gotoAttributesStart(TreePosition treePosition) {
        TreePosition treePosition2 = treePosition;
        return false;
    }

    public Object getPosNext(int i) {
        int ipos = i;
        if (!hasNext(ipos)) {
            return Sequence.eofValue;
        }
        return get(nextIndex(ipos));
    }

    public Object getPosPrevious(int ipos) {
        int index = nextIndex(ipos);
        if (index <= 0) {
            return Sequence.eofValue;
        }
        return get(index - 1);
    }

    /* access modifiers changed from: protected */
    public void setPosNext(int ipos, Object obj) {
        Throwable th;
        Object value = obj;
        int index = nextIndex(ipos);
        if (index >= size()) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        }
        Object obj2 = set(index, value);
    }

    /* access modifiers changed from: protected */
    public void setPosPrevious(int ipos, Object obj) {
        Throwable th;
        Object value = obj;
        int index = nextIndex(ipos);
        if (index == 0) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        }
        Object obj2 = set(index - 1, value);
    }

    public final int nextIndex(SeqPosition pos) {
        return nextIndex(pos.ipos);
    }

    public boolean equals(int ipos1, int ipos2) {
        return compare(ipos1, ipos2) == 0;
    }

    public int compare(int ipos1, int ipos2) {
        int i1 = nextIndex(ipos1);
        int i2 = nextIndex(ipos2);
        return i1 < i2 ? -1 : i1 > i2 ? 1 : 0;
    }

    public final int compare(SeqPosition i1, SeqPosition i2) {
        return compare(i1.ipos, i2.ipos);
    }

    public Object[] toArray() {
        Object[] arr = new Object[size()];
        int it = startPos();
        int i = 0;
        while (true) {
            int nextPos = nextPos(it);
            it = nextPos;
            if (nextPos == 0) {
                return arr;
            }
            int i2 = i;
            i++;
            arr[i2] = getPosPrevious(it);
        }
    }

    public Object[] toArray(Object[] objArr) {
        Object[] arr = objArr;
        int alen = arr.length;
        int len = size();
        if (len > alen) {
            arr = (Object[]) Array.newInstance(arr.getClass().getComponentType(), len);
            alen = len;
        }
        int it = startPos();
        int i = 0;
        while (true) {
            int nextPos = nextPos(it);
            it = nextPos;
            if (nextPos == 0) {
                break;
            }
            arr[i] = getPosPrevious(it);
            i++;
        }
        if (len < alen) {
            arr[len] = null;
        }
        return arr;
    }

    public int stableCompare(AbstractSequence other) {
        int id1 = System.identityHashCode(this);
        int id2 = System.identityHashCode(other);
        return id1 < id2 ? -1 : id1 > id2 ? 1 : 0;
    }

    public static int compare(AbstractSequence abstractSequence, int i, AbstractSequence abstractSequence2, int i2) {
        AbstractSequence seq1 = abstractSequence;
        int pos1 = i;
        AbstractSequence seq2 = abstractSequence2;
        int pos2 = i2;
        if (seq1 == seq2) {
            return seq1.compare(pos1, pos2);
        }
        return seq1.stableCompare(seq2);
    }

    public int hashCode() {
        int hash = 1;
        int i = startPos();
        while (true) {
            int nextPos = nextPos(i);
            i = nextPos;
            if (nextPos == 0) {
                return hash;
            }
            Object obj = getPosPrevious(i);
            hash = (31 * hash) + (obj == null ? 0 : obj.hashCode());
        }
    }

    public boolean equals(Object obj) {
        boolean z;
        Object o = obj;
        if (!(this instanceof List) || !(o instanceof List)) {
            if (this == o) {
                z = true;
            } else {
                z = false;
            }
            return z;
        }
        Iterator it1 = iterator();
        Iterator it2 = ((List) o).iterator();
        while (true) {
            boolean more1 = it1.hasNext();
            if (more1 != it2.hasNext()) {
                return false;
            }
            if (!more1) {
                return true;
            }
            Object e1 = it1.next();
            Object e2 = it2.next();
            if (e1 == null) {
                if (e2 != null) {
                    return false;
                }
            } else if (!e1.equals(e2)) {
                return false;
            }
        }
    }

    public Sequence subSequence(SeqPosition start, SeqPosition end) {
        return subSequencePos(start.ipos, end.ipos);
    }

    /* access modifiers changed from: protected */
    public Sequence subSequencePos(int ipos0, int ipos1) {
        Sequence sequence;
        new SubSequence(this, ipos0, ipos1);
        return sequence;
    }

    public List subList(int fromIx, int toIx) {
        return subSequencePos(createPos(fromIx, false), createPos(toIx, true));
    }

    public boolean consumeNext(int i, Consumer consumer) {
        int ipos = i;
        Consumer out = consumer;
        int next = nextPos(ipos);
        if (next == 0) {
            return false;
        }
        consumePosRange(ipos, next, out);
        return true;
    }

    public void consumePosRange(int i, int i2, Consumer consumer) {
        Throwable th;
        int iposStart = i;
        int iposEnd = i2;
        Consumer out = consumer;
        if (!out.ignoring()) {
            int copyPos = copyPos(iposStart);
            while (true) {
                int it = copyPos;
                if (equals(it, iposEnd)) {
                    releasePos(it);
                    return;
                } else if (!hasNext(it)) {
                    Throwable th2 = th;
                    new RuntimeException();
                    throw th2;
                } else {
                    out.writeObject(getPosNext(it));
                    copyPos = nextPos(it);
                }
            }
        }
    }

    public void consume(Consumer consumer) {
        Consumer out = consumer;
        boolean isSequence = this instanceof Sequence;
        if (isSequence) {
            out.startElement("#sequence");
        }
        consumePosRange(startPos(), endPos(), out);
        if (isSequence) {
            out.endElement();
        }
    }

    public void toString(String str, StringBuffer stringBuffer) {
        String sep = str;
        StringBuffer sbuf = stringBuffer;
        boolean seen = false;
        int i = startPos();
        while (true) {
            int nextPos = nextPos(i);
            i = nextPos;
            if (nextPos != 0) {
                if (seen) {
                    StringBuffer append = sbuf.append(sep);
                } else {
                    seen = true;
                }
                StringBuffer append2 = sbuf.append(getPosPrevious(i));
            } else {
                return;
            }
        }
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer(100);
        StringBuffer sbuf = stringBuffer;
        if (this instanceof Sequence) {
            StringBuffer append = sbuf.append('[');
        }
        toString(", ", sbuf);
        if (this instanceof Sequence) {
            StringBuffer append2 = sbuf.append(']');
        }
        return sbuf.toString();
    }
}
