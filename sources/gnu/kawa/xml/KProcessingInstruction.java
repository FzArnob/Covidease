package gnu.kawa.xml;

import gnu.xml.NodeTree;
import org.w3c.dom.DOMException;
import org.w3c.dom.ProcessingInstruction;

public class KProcessingInstruction extends KNode implements ProcessingInstruction {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KProcessingInstruction(NodeTree seq, int ipos) {
        super(seq, ipos);
    }

    public short getNodeType() {
        return 7;
    }

    public String getNodeName() {
        return getTarget();
    }

    public String getData() {
        return getNodeValue();
    }

    public void setData(String str) throws DOMException {
        Throwable th;
        String str2 = str;
        Throwable th2 = th;
        new DOMException(7, "setData not supported");
        throw th2;
    }

    public String getTarget() {
        return ((NodeTree) this.sequence).posTarget(this.ipos);
    }

    public static KProcessingInstruction valueOf(String target, String str) {
        NodeTree nodeTree;
        KProcessingInstruction kProcessingInstruction;
        String content = str;
        new NodeTree();
        NodeTree tree = nodeTree;
        tree.writeProcessingInstruction(target, content, 0, content.length());
        new KProcessingInstruction(tree, 0);
        return kProcessingInstruction;
    }
}
