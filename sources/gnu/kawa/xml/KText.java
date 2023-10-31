package gnu.kawa.xml;

import gnu.lists.Consumer;
import gnu.xml.NodeTree;
import org.w3c.dom.DOMException;
import org.w3c.dom.Text;

public class KText extends KCharacterData implements Text {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KText(NodeTree seq, int ipos) {
        super(seq, ipos);
    }

    public static KText make(String text) {
        NodeTree nodeTree;
        KText kText;
        new NodeTree();
        NodeTree tree = nodeTree;
        Consumer append = tree.append((CharSequence) text);
        new KText(tree, 0);
        return kText;
    }

    public short getNodeType() {
        return 3;
    }

    public String getNodeName() {
        return "#text";
    }

    public Text splitText(int i) throws DOMException {
        Throwable th;
        int i2 = i;
        Throwable th2 = th;
        new DOMException(7, "splitText not supported");
        throw th2;
    }

    public String getWholeText() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException("getWholeText not implemented yet");
        throw th2;
    }

    public Text replaceWholeText(String str) throws DOMException {
        Throwable th;
        String str2 = str;
        Throwable th2 = th;
        new DOMException(7, "splitText not supported");
        throw th2;
    }

    public boolean hasAttributes() {
        return false;
    }

    public boolean isElementContentWhitespace() {
        return false;
    }
}
