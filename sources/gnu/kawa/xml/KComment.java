package gnu.kawa.xml;

import gnu.xml.NodeTree;
import org.w3c.dom.Comment;

public class KComment extends KCharacterData implements Comment {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KComment(NodeTree seq, int ipos) {
        super(seq, ipos);
    }

    public short getNodeType() {
        return 8;
    }

    public String getNodeName() {
        return "#comment";
    }

    public static KComment valueOf(String str) {
        NodeTree nodeTree;
        KComment kComment;
        String text = str;
        new NodeTree();
        NodeTree tree = nodeTree;
        tree.writeComment(text, 0, text.length());
        new KComment(tree, 0);
        return kComment;
    }
}
