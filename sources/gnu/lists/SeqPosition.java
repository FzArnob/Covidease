package gnu.lists;

import gnu.kawa.functions.GetNamedPart;
import java.util.Enumeration;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SeqPosition implements ListIterator, Enumeration {
    public int ipos;
    public AbstractSequence sequence;

    public SeqPosition() {
    }

    public SeqPosition(AbstractSequence seq) {
        this.sequence = seq;
    }

    public SeqPosition(AbstractSequence abstractSequence, int offset, boolean isAfter) {
        AbstractSequence seq = abstractSequence;
        this.sequence = seq;
        this.ipos = seq.createPos(offset, isAfter);
    }

    public SeqPosition(AbstractSequence seq, int ipos2) {
        this.sequence = seq;
        this.ipos = ipos2;
    }

    public static SeqPosition make(AbstractSequence abstractSequence, int ipos2) {
        SeqPosition seqPosition;
        AbstractSequence seq = abstractSequence;
        new SeqPosition(seq, seq.copyPos(ipos2));
        return seqPosition;
    }

    public SeqPosition copy() {
        SeqPosition seqPosition;
        new SeqPosition(this.sequence, this.sequence.copyPos(getPos()));
        return seqPosition;
    }

    public final void gotoStart(AbstractSequence abstractSequence) {
        AbstractSequence seq = abstractSequence;
        setPos(seq, seq.startPos());
    }

    public final void gotoEnd(AbstractSequence abstractSequence) {
        AbstractSequence seq = abstractSequence;
        setPos(seq, seq.endPos());
    }

    public boolean gotoChildrenStart() {
        int child = this.sequence.firstChildPos(getPos());
        if (child == 0) {
            return false;
        }
        this.ipos = child;
        return true;
    }

    public final boolean hasMoreElements() {
        return hasNext();
    }

    public boolean hasNext() {
        return this.sequence.hasNext(getPos());
    }

    public int getNextKind() {
        return this.sequence.getNextKind(getPos());
    }

    public String getNextTypeName() {
        return this.sequence.getNextTypeName(getPos());
    }

    public Object getNextTypeObject() {
        return this.sequence.getNextTypeObject(getPos());
    }

    public boolean hasPrevious() {
        return this.sequence.hasPrevious(getPos());
    }

    public Object next() {
        Throwable th;
        Object result = getNext();
        if (result != Sequence.eofValue && gotoNext()) {
            return result;
        }
        Throwable th2 = th;
        new NoSuchElementException();
        throw th2;
    }

    public boolean gotoNext() {
        int next = this.sequence.nextPos(this.ipos);
        if (next != 0) {
            this.ipos = next;
            return true;
        }
        this.ipos = -1;
        return false;
    }

    public boolean gotoPrevious() {
        int prev = this.sequence.previousPos(this.ipos);
        if (prev != -1) {
            this.ipos = prev;
            return true;
        }
        this.ipos = 0;
        return false;
    }

    public Object previous() {
        Throwable th;
        Object result = getPrevious();
        if (result != Sequence.eofValue && gotoPrevious()) {
            return result;
        }
        Throwable th2 = th;
        new NoSuchElementException();
        throw th2;
    }

    public final Object nextElement() {
        return next();
    }

    public Object getNext() {
        return this.sequence.getPosNext(getPos());
    }

    public Object getPrevious() {
        return this.sequence.getPosPrevious(getPos());
    }

    public int nextIndex() {
        return this.sequence.nextIndex(getPos());
    }

    public final int fromEndIndex() {
        return this.sequence.fromEndIndex(getPos());
    }

    public int getContainingSequenceSize() {
        return this.sequence.getContainingSequenceSize(getPos());
    }

    public final int previousIndex() {
        return this.sequence.nextIndex(getPos()) - 1;
    }

    public boolean isAfter() {
        return this.sequence.isAfterPos(getPos());
    }

    public final void set(Object obj) {
        Object value = obj;
        if (isAfter()) {
            setPrevious(value);
        } else {
            setNext(value);
        }
    }

    public void setNext(Object value) {
        this.sequence.setPosNext(getPos(), value);
    }

    public void setPrevious(Object value) {
        this.sequence.setPosPrevious(getPos(), value);
    }

    public void remove() {
        this.sequence.removePos(getPos(), isAfter() ? -1 : 1);
    }

    public void add(Object o) {
        setPos(this.sequence.addPos(getPos(), o));
    }

    public int getPos() {
        return this.ipos;
    }

    public void setPos(AbstractSequence abstractSequence, int i) {
        AbstractSequence seq = abstractSequence;
        int ipos2 = i;
        if (this.sequence != null) {
            this.sequence.releasePos(getPos());
        }
        this.ipos = ipos2;
        this.sequence = seq;
    }

    public void setPos(int i) {
        int ipos2 = i;
        if (this.sequence != null) {
            this.sequence.releasePos(getPos());
        }
        this.ipos = ipos2;
    }

    public void set(AbstractSequence abstractSequence, int i, boolean z) {
        AbstractSequence seq = abstractSequence;
        int index = i;
        boolean isAfter = z;
        if (this.sequence != null) {
            this.sequence.releasePos(this.ipos);
        }
        this.sequence = seq;
        this.ipos = seq.createPos(index, isAfter);
    }

    public void set(SeqPosition seqPosition) {
        SeqPosition pos = seqPosition;
        if (this.sequence != null) {
            this.sequence.releasePos(this.ipos);
        }
        this.sequence = pos.sequence;
        pos.ipos = this.sequence.copyPos(pos.ipos);
    }

    public void release() {
        if (this.sequence != null) {
            this.sequence.releasePos(getPos());
            this.sequence = null;
        }
    }

    public void finalize() {
        release();
    }

    public String toString() {
        StringBuilder sb;
        if (this.sequence == null) {
            return toInfo();
        }
        Object item = this.sequence.getPosNext(this.ipos);
        new StringBuilder();
        return sb.append(GetNamedPart.CAST_METHOD_NAME).append(nextIndex()).append(": ").append(item == null ? "(null)" : item.toString()).toString();
    }

    public String toInfo() {
        StringBuffer stringBuffer;
        new StringBuffer(60);
        StringBuffer sbuf = stringBuffer;
        StringBuffer append = sbuf.append('{');
        if (this.sequence == null) {
            StringBuffer append2 = sbuf.append("null sequence");
        } else {
            StringBuffer append3 = sbuf.append(this.sequence.getClass().getName());
            StringBuffer append4 = sbuf.append('@');
            StringBuffer append5 = sbuf.append(System.identityHashCode(this.sequence));
        }
        StringBuffer append6 = sbuf.append(" ipos: ");
        StringBuffer append7 = sbuf.append(this.ipos);
        StringBuffer append8 = sbuf.append('}');
        return sbuf.toString();
    }
}
