package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class AttributeAxis extends TreeScanner {
    public AttributeAxis() {
    }

    public static AttributeAxis make(NodePredicate type) {
        AttributeAxis attributeAxis;
        new AttributeAxis();
        AttributeAxis axis = attributeAxis;
        axis.type = type;
        return axis;
    }

    public void scan(AbstractSequence abstractSequence, int ipos, PositionConsumer positionConsumer) {
        AbstractSequence seq = abstractSequence;
        PositionConsumer out = positionConsumer;
        int firstAttributePos = seq.firstAttributePos(ipos);
        while (true) {
            int ipos2 = firstAttributePos;
            if (ipos2 != 0 && seq.getNextKind(ipos2) == 35) {
                if (this.type.isInstancePos(seq, ipos2)) {
                    out.writePosition(seq, ipos2);
                } else if (seq.getNextKind(ipos2) != 35) {
                    return;
                }
                firstAttributePos = seq.nextPos(ipos2);
            } else {
                return;
            }
        }
    }
}
