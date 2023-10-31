package gnu.kawa.xml;

import gnu.xml.NodeTree;
import org.w3c.dom.CDATASection;

public class KCDATASection extends KText implements CDATASection {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KCDATASection(NodeTree seq, int ipos) {
        super(seq, ipos);
    }

    public short getNodeType() {
        return 4;
    }

    public String getNodeName() {
        return "#cdata-section";
    }

    public String getData() {
        return getNodeValue();
    }

    public int getLength() {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        NodeTree tlist = (NodeTree) this.sequence;
        int stringValue = tlist.stringValue(tlist.posToDataIndex(this.ipos), sbuf);
        return sbuf.length();
    }
}
