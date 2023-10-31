package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.TreePosition;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import gnu.text.Path;
import gnu.xml.NodeTree;
import gnu.xml.XMLPrinter;
import java.io.Writer;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

public abstract class KNode extends SeqPosition implements Node, Consumable {
    public abstract short getNodeType();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KNode(NodeTree seq, int ipos) {
        super(seq, ipos);
    }

    public static Object atomicValue(Object obj) {
        Object value = obj;
        if (!(value instanceof KNode)) {
            return value;
        }
        KNode node = (KNode) value;
        return ((NodeTree) node.sequence).typedValue(node.ipos);
    }

    public static KNode coerce(Object obj) {
        Object value = obj;
        if (value instanceof KNode) {
            return (KNode) value;
        }
        if (value instanceof NodeTree) {
            NodeTree ntree = (NodeTree) value;
            return make(ntree, ntree.startPos());
        }
        if ((value instanceof SeqPosition) && !(value instanceof TreePosition)) {
            SeqPosition seqp = (SeqPosition) value;
            if (seqp.sequence instanceof NodeTree) {
                return make((NodeTree) seqp.sequence, seqp.ipos);
            }
        }
        return null;
    }

    public static KNode make(NodeTree nodeTree, int i) {
        KNode kNode;
        KNode kNode2;
        KNode kNode3;
        KNode kNode4;
        KNode kNode5;
        KNode kNode6;
        KNode kNode7;
        NodeTree seq = nodeTree;
        int ipos = i;
        int index = seq.posToDataIndex(ipos);
        while (index < seq.data.length && seq.data[index] == 61714) {
            index += 5;
            if (index == seq.gapStart) {
                index = seq.gapEnd;
            }
            ipos = index << 1;
        }
        switch (seq.getNextKindI(seq.posToDataIndex(ipos))) {
            case 0:
                if (!seq.isEmpty()) {
                    return null;
                }
                break;
            case 31:
                new KCDATASection(seq, ipos);
                return kNode3;
            case 33:
                new KElement(seq, ipos);
                return kNode6;
            case 34:
                new KDocument(seq, ipos);
                return kNode4;
            case 35:
                new KAttr(seq, ipos);
                return kNode5;
            case 36:
                new KComment(seq, ipos);
                return kNode2;
            case 37:
                new KProcessingInstruction(seq, ipos);
                return kNode;
        }
        new KText(seq, ipos);
        return kNode7;
    }

    public KNode copy() {
        return make((NodeTree) this.sequence, this.sequence.copyPos(getPos()));
    }

    public static KNode make(NodeTree seq) {
        return make(seq, 0);
    }

    public boolean isSupported(String str, String str2) {
        String str3 = str;
        String str4 = str2;
        return false;
    }

    public String getNodeName() {
        return this.sequence.getNextTypeName(this.ipos);
    }

    public Symbol getNodeSymbol() {
        Object type = ((NodeTree) this.sequence).getNextTypeObject(this.ipos);
        if (type == null) {
            return null;
        }
        if (type instanceof Symbol) {
            return (Symbol) type;
        }
        return Namespace.EmptyNamespace.getSymbol(type.toString().intern());
    }

    public Object getNodeNameObject() {
        return ((NodeTree) this.sequence).getNextTypeObject(this.ipos);
    }

    public String getNamespaceURI() {
        return ((NodeTree) this.sequence).posNamespaceURI(this.ipos);
    }

    public String getPrefix() {
        return ((NodeTree) this.sequence).posPrefix(this.ipos);
    }

    public String getLocalName() {
        return ((NodeTree) this.sequence).posLocalName(this.ipos);
    }

    public static String getNodeValue(NodeTree seq, int ipos) {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        getNodeValue(seq, ipos, sbuf);
        return sbuf.toString();
    }

    public static void getNodeValue(NodeTree nodeTree, int ipos, StringBuffer sbuf) {
        NodeTree seq = nodeTree;
        int stringValue = seq.stringValue(seq.posToDataIndex(ipos), sbuf);
    }

    public String getNodeValue() {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        getNodeValue(sbuf);
        return sbuf.toString();
    }

    public void getNodeValue(StringBuffer sbuf) {
        getNodeValue((NodeTree) this.sequence, this.ipos, sbuf);
    }

    public boolean hasChildNodes() {
        return ((NodeTree) this.sequence).posFirstChild(this.ipos) >= 0;
    }

    public String getTextContent() {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        getTextContent(sbuf);
        return sbuf.toString();
    }

    /* access modifiers changed from: protected */
    public void getTextContent(StringBuffer sbuf) {
        getNodeValue(sbuf);
    }

    public Node getParentNode() {
        int parent = this.sequence.parentPos(this.ipos);
        if (parent == -1) {
            return null;
        }
        return make((NodeTree) this.sequence, parent);
    }

    public Node getPreviousSibling() {
        int previous;
        int parent = this.sequence.parentPos(this.ipos);
        if (parent == -1) {
            parent = 0;
        }
        int index = ((NodeTree) this.sequence).posToDataIndex(this.ipos);
        int child = this.sequence.firstChildPos(parent);
        do {
            previous = child;
            child = this.sequence.nextPos(child);
            if (child == 0 || ((NodeTree) this.sequence).posToDataIndex(child) == index) {
            }
            previous = child;
            child = this.sequence.nextPos(child);
            break;
        } while (((NodeTree) this.sequence).posToDataIndex(child) == index);
        return previous == 0 ? null : make((NodeTree) this.sequence, previous);
    }

    public Node getNextSibling() {
        int next = ((NodeTree) this.sequence).nextPos(this.ipos);
        return next == 0 ? null : make((NodeTree) this.sequence, next);
    }

    public Node getFirstChild() {
        return make((NodeTree) this.sequence, ((NodeTree) this.sequence).posFirstChild(this.ipos));
    }

    public Node getLastChild() {
        int last = 0;
        int firstChildPos = this.sequence.firstChildPos(this.ipos);
        while (true) {
            int child = firstChildPos;
            if (child == 0) {
                break;
            }
            last = child;
            firstChildPos = this.sequence.nextPos(child);
        }
        return last == 0 ? null : make((NodeTree) this.sequence, last);
    }

    public NodeList getChildNodes() {
        Nodes nodes;
        new SortedNodes();
        Nodes nodes2 = nodes;
        int firstChildPos = this.sequence.firstChildPos(this.ipos);
        while (true) {
            int child = firstChildPos;
            if (child == 0) {
                return nodes2;
            }
            nodes2.writePosition(this.sequence, child);
            firstChildPos = this.sequence.nextPos(child);
        }
    }

    public NodeList getElementsByTagName(String str) {
        Throwable th;
        String str2 = str;
        Throwable th2 = th;
        new UnsupportedOperationException("getElementsByTagName not implemented yet");
        throw th2;
    }

    public void setNodeValue(String str) throws DOMException {
        Throwable th;
        String str2 = str;
        Throwable th2 = th;
        new DOMException(7, "setNodeValue not supported");
        throw th2;
    }

    public void setPrefix(String str) throws DOMException {
        Throwable th;
        String str2 = str;
        Throwable th2 = th;
        new DOMException(7, "setPrefix not supported");
        throw th2;
    }

    public Node insertBefore(Node node, Node node2) throws DOMException {
        Throwable th;
        Node node3 = node;
        Node node4 = node2;
        Throwable th2 = th;
        new DOMException(7, "insertBefore not supported");
        throw th2;
    }

    public Node replaceChild(Node node, Node node2) throws DOMException {
        Throwable th;
        Node node3 = node;
        Node node4 = node2;
        Throwable th2 = th;
        new DOMException(7, "replaceChild not supported");
        throw th2;
    }

    public Node removeChild(Node node) throws DOMException {
        Throwable th;
        Node node2 = node;
        Throwable th2 = th;
        new DOMException(7, "removeChild not supported");
        throw th2;
    }

    public Node appendChild(Node node) throws DOMException {
        Throwable th;
        Node node2 = node;
        Throwable th2 = th;
        new DOMException(7, "appendChild not supported");
        throw th2;
    }

    public void setTextContent(String str) throws DOMException {
        Throwable th;
        String str2 = str;
        Throwable th2 = th;
        new DOMException(7, "setTextContent not supported");
        throw th2;
    }

    public Node cloneNode(boolean deep) {
        NodeTree nodeTree;
        Throwable th;
        if (!deep) {
            Throwable th2 = th;
            new UnsupportedOperationException("shallow cloneNode not implemented");
            throw th2;
        }
        new NodeTree();
        NodeTree tree = nodeTree;
        boolean consumeNext = ((NodeTree) this.sequence).consumeNext(this.ipos, tree);
        return make(tree);
    }

    public Document getOwnerDocument() {
        Document document;
        if (this.sequence.getNextKind(this.ipos) != 34) {
            return null;
        }
        new KDocument((NodeTree) this.sequence, 0);
        return document;
    }

    public NamedNodeMap getAttributes() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException("getAttributes not implemented yet");
        throw th2;
    }

    public void normalize() {
    }

    public boolean hasAttributes() {
        return false;
    }

    public boolean isDefaultNamespace(String namespaceURI) {
        return ((NodeTree) this.sequence).posIsDefaultNamespace(this.ipos, namespaceURI);
    }

    public String lookupNamespaceURI(String prefix) {
        return ((NodeTree) this.sequence).posLookupNamespaceURI(this.ipos, prefix);
    }

    public String lookupPrefix(String namespaceURI) {
        return ((NodeTree) this.sequence).posLookupPrefix(this.ipos, namespaceURI);
    }

    public String getBaseURI() {
        Path uri = ((NodeTree) this.sequence).baseUriOfPos(this.ipos, true);
        return uri == null ? null : uri.toString();
    }

    public Path baseURI() {
        return ((NodeTree) this.sequence).baseUriOfPos(this.ipos, true);
    }

    public short compareDocumentPosition(Node node) throws DOMException {
        Throwable th;
        StringBuilder sb;
        Node other = node;
        if (!(other instanceof KNode)) {
            Throwable th2 = th;
            new StringBuilder();
            new DOMException(9, sb.append("other Node is a ").append(other.getClass().getName()).toString());
            throw th2;
        }
        KNode n = (KNode) other;
        AbstractSequence nseq = n.sequence;
        return (short) (this.sequence == nseq ? nseq.compare(this.ipos, n.ipos) : this.sequence.stableCompare(nseq));
    }

    public boolean isSameNode(Node node) {
        Node node2 = node;
        if (!(node2 instanceof KNode)) {
            return false;
        }
        KNode n = (KNode) node2;
        if (this.sequence != n.sequence) {
            return false;
        }
        return this.sequence.equals(this.ipos, n.ipos);
    }

    public boolean isEqualNode(Node node) {
        Throwable th;
        Node node2 = node;
        Throwable th2 = th;
        new UnsupportedOperationException("getAttributesisEqualNode not implemented yet");
        throw th2;
    }

    public String toString() {
        CharArrayOutPort charArrayOutPort;
        XMLPrinter xMLPrinter;
        new CharArrayOutPort();
        CharArrayOutPort wr = charArrayOutPort;
        new XMLPrinter((Writer) wr);
        XMLPrinter xp = xMLPrinter;
        boolean consumeNext = ((NodeTree) this.sequence).consumeNext(this.ipos, xp);
        xp.close();
        wr.close();
        return wr.toString();
    }

    public Object getFeature(String str, String str2) {
        String str3 = str;
        String str4 = str2;
        return null;
    }

    public void consume(Consumer consumer) {
        Consumer out = consumer;
        if (out instanceof PositionConsumer) {
            ((PositionConsumer) out).consume(this);
        } else {
            boolean consumeNext = ((NodeTree) this.sequence).consumeNext(this.ipos, out);
        }
    }

    public Object setUserData(String str, Object obj, UserDataHandler userDataHandler) {
        Throwable th;
        String str2 = str;
        Object obj2 = obj;
        UserDataHandler userDataHandler2 = userDataHandler;
        Throwable th2 = th;
        new UnsupportedOperationException("setUserData not implemented yet");
        throw th2;
    }

    public Object getUserData(String str) {
        String str2 = str;
        return null;
    }
}
