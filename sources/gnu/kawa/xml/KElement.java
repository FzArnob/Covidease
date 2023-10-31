package gnu.kawa.xml;

import gnu.xml.NodeTree;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;

public class KElement extends KNode implements Element {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KElement(NodeTree seq, int ipos) {
        super(seq, ipos);
    }

    public short getNodeType() {
        return 1;
    }

    public String getTagName() {
        return this.sequence.getNextTypeName(this.ipos);
    }

    public String getNodeValue() {
        return null;
    }

    public boolean hasAttributes() {
        return ((NodeTree) this.sequence).posHasAttributes(this.ipos);
    }

    public String getAttribute(String str) {
        String name = str;
        if (name == null) {
            name = "";
        }
        NodeTree nodes = (NodeTree) this.sequence;
        int attr = nodes.getAttribute(this.ipos, (String) null, name);
        if (attr == 0) {
            return "";
        }
        return KNode.getNodeValue(nodes, attr);
    }

    public void setAttribute(String str, String str2) throws DOMException {
        Throwable th;
        String str3 = str;
        String str4 = str2;
        Throwable th2 = th;
        new DOMException(7, "setAttribute not supported");
        throw th2;
    }

    public void setIdAttribute(String str, boolean z) throws DOMException {
        Throwable th;
        String str2 = str;
        boolean z2 = z;
        Throwable th2 = th;
        new DOMException(7, "setIdAttribute not supported");
        throw th2;
    }

    public void setIdAttributeNS(String str, String str2, boolean z) throws DOMException {
        Throwable th;
        String str3 = str;
        String str4 = str2;
        boolean z2 = z;
        Throwable th2 = th;
        new DOMException(7, "setIdAttributeNS not supported");
        throw th2;
    }

    public void setIdAttributeNode(Attr attr, boolean z) throws DOMException {
        Throwable th;
        Attr attr2 = attr;
        boolean z2 = z;
        Throwable th2 = th;
        new DOMException(7, "setIdAttributeNode not supported");
        throw th2;
    }

    public void removeAttribute(String str) throws DOMException {
        Throwable th;
        String str2 = str;
        Throwable th2 = th;
        new DOMException(7, "removeAttribute not supported");
        throw th2;
    }

    public KAttr getAttributeNode(String str) {
        KAttr kAttr;
        String name = str;
        if (name == null) {
            name = "";
        }
        NodeTree nodes = (NodeTree) this.sequence;
        int attr = nodes.getAttribute(this.ipos, (String) null, name);
        if (attr == 0) {
            return null;
        }
        new KAttr(nodes, attr);
        return kAttr;
    }

    public Attr setAttributeNode(Attr attr) throws DOMException {
        Throwable th;
        Attr attr2 = attr;
        Throwable th2 = th;
        new DOMException(7, "setAttributeNode not supported");
        throw th2;
    }

    public Attr removeAttributeNode(Attr attr) throws DOMException {
        Throwable th;
        Attr attr2 = attr;
        Throwable th2 = th;
        new DOMException(7, "removeAttributeNode not supported");
        throw th2;
    }

    public String getAttributeNS(String str, String str2) {
        String namespaceURI = str;
        String localName = str2;
        if (namespaceURI == null) {
            namespaceURI = "";
        }
        if (localName == null) {
            localName = "";
        }
        NodeTree nodes = (NodeTree) this.sequence;
        int attr = nodes.getAttribute(this.ipos, namespaceURI, localName);
        if (attr == 0) {
            return "";
        }
        return getNodeValue(nodes, attr);
    }

    public void setAttributeNS(String str, String str2, String str3) throws DOMException {
        Throwable th;
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        Throwable th2 = th;
        new DOMException(7, "setAttributeNS not supported");
        throw th2;
    }

    public void removeAttributeNS(String str, String str2) throws DOMException {
        Throwable th;
        String str3 = str;
        String str4 = str2;
        Throwable th2 = th;
        new DOMException(7, "removeAttributeNS not supported");
        throw th2;
    }

    public KAttr getAttributeNodeNS(String str, String str2) {
        KAttr kAttr;
        String namespaceURI = str;
        String localName = str2;
        if (namespaceURI == null) {
            namespaceURI = "";
        }
        if (localName == null) {
            localName = "";
        }
        NodeTree nodes = (NodeTree) this.sequence;
        int attr = nodes.getAttribute(this.ipos, namespaceURI, localName);
        if (attr == 0) {
            return null;
        }
        new KAttr(nodes, attr);
        return kAttr;
    }

    public Attr setAttributeNodeNS(Attr attr) throws DOMException {
        Throwable th;
        Attr attr2 = attr;
        Throwable th2 = th;
        new DOMException(7, "setAttributeNodeNS not supported");
        throw th2;
    }

    public NodeList getElementsByTagNameNS(String str, String str2) {
        Throwable th;
        String str3 = str;
        String str4 = str2;
        Throwable th2 = th;
        new UnsupportedOperationException("getElementsByTagNameNS not implemented yet");
        throw th2;
    }

    public boolean hasAttribute(String str) {
        String name = str;
        return ((NodeTree) this.sequence).getAttribute(this.ipos, (String) null, name == null ? "" : name) != 0;
    }

    public boolean hasAttributeNS(String str, String str2) {
        String namespaceURI = str;
        String localName = str2;
        if (namespaceURI == null) {
            namespaceURI = "";
        }
        if (localName == null) {
            localName = "";
        }
        return ((NodeTree) this.sequence).getAttribute(this.ipos, namespaceURI, localName) != 0;
    }

    public TypeInfo getSchemaTypeInfo() {
        return null;
    }
}
