package gnu.xquery.util;

import gnu.kawa.xml.SortedNodes;
import gnu.lists.AbstractSequence;
import gnu.lists.Consumer;
import gnu.lists.FilterConsumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;

public class RelativeStepFilter extends FilterConsumer implements PositionConsumer {
    char seen;
    SortedNodes snodes;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RelativeStepFilter(Consumer base) {
        super(base);
    }

    public void consume(SeqPosition seqPosition) {
        SeqPosition position = seqPosition;
        writePosition(position.sequence, position.ipos);
    }

    public void writeObject(Object obj) {
        Object v = obj;
        if (v instanceof SeqPosition) {
            SeqPosition n = (SeqPosition) v;
            writePosition(n.sequence, n.ipos);
            return;
        }
        super.writeObject(v);
    }

    /* access modifiers changed from: protected */
    public void beforeContent() {
        Throwable th;
        if (this.seen == 'N') {
            Throwable th2 = th;
            new Error("path returns mix of atoms and nodes");
            throw th2;
        }
        this.seen = 'A';
    }

    public void writePosition(AbstractSequence abstractSequence, int i) {
        SortedNodes sortedNodes;
        Throwable th;
        AbstractSequence seq = abstractSequence;
        int ipos = i;
        if (this.seen == 'A') {
            Throwable th2 = th;
            new Error("path returns mix of atoms and nodes");
            throw th2;
        }
        this.seen = 'N';
        if (this.snodes == null) {
            new SortedNodes();
            this.snodes = sortedNodes;
        }
        this.snodes.writePosition(seq, ipos);
    }

    public void finish() {
        if (this.snodes != null) {
            this.snodes.consume(this.base);
        }
        this.snodes = null;
    }
}
