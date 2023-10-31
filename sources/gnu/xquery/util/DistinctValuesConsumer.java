package gnu.xquery.util;

import gnu.kawa.xml.KNode;
import gnu.lists.AbstractSequence;
import gnu.lists.Consumer;
import gnu.lists.FilterConsumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.mapping.Values;
import gnu.xml.NodeTree;

/* compiled from: DistinctValues */
class DistinctValuesConsumer extends FilterConsumer implements PositionConsumer {
    DistinctValuesHashTable table;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DistinctValuesConsumer(NamedCollator collator, Consumer out) {
        super(out);
        DistinctValuesHashTable distinctValuesHashTable;
        new DistinctValuesHashTable(collator);
        this.table = distinctValuesHashTable;
    }

    public void consume(SeqPosition position) {
        writeObject(position);
    }

    public void writePosition(AbstractSequence seq, int ipos) {
        writeObject(((NodeTree) seq).typedValue(ipos));
    }

    public void writeBoolean(boolean v) {
        writeObject(v ? Boolean.TRUE : Boolean.FALSE);
    }

    public void writeObject(Object obj) {
        Object value = obj;
        if (value instanceof Values) {
            Values.writeValues(value, this);
        } else if (value instanceof KNode) {
            KNode node = (KNode) value;
            writeObject(((NodeTree) node.sequence).typedValue(node.ipos));
        } else if (this.table.get(value, null) == null) {
            Object put = this.table.put(value, value);
            this.base.writeObject(value);
        }
    }
}
