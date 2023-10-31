package gnu.kawa.xml;

import gnu.xml.NodeTree;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

public class KDocument extends KNode implements Document {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KDocument(NodeTree seq, int ipos) {
        super(seq, ipos);
    }

    public String getNodeName() {
        return "#document";
    }

    public DOMImplementation getImplementation() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException("getImplementation not implemented");
        throw th2;
    }

    public DocumentType getDoctype() {
        return null;
    }

    public Node getParentNode() {
        return null;
    }

    public KElement getDocumentElement() {
        int posFirstChild = ((NodeTree) this.sequence).posFirstChild(this.ipos);
        while (true) {
            int child = posFirstChild;
            if (child == -1) {
                return null;
            }
            if (this.sequence.getNextKind(child) != 36) {
                return (KElement) make((NodeTree) this.sequence, child);
            }
            posFirstChild = this.sequence.nextPos(child);
        }
    }

    public short getNodeType() {
        return 9;
    }

    public String getNodeValue() {
        return null;
    }

    public String getTextContent() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void getTextContent(StringBuffer sbuf) {
    }

    public Element createElement(String str) {
        Throwable th;
        String str2 = str;
        Throwable th2 = th;
        new UnsupportedOperationException("createElement not implemented");
        throw th2;
    }

    public DocumentFragment createDocumentFragment() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException("createDocumentFragment not implemented");
        throw th2;
    }

    public Text createTextNode(String str) {
        Throwable th;
        String str2 = str;
        Throwable th2 = th;
        new UnsupportedOperationException("createTextNode not implemented");
        throw th2;
    }

    public Comment createComment(String str) {
        Throwable th;
        String str2 = str;
        Throwable th2 = th;
        new UnsupportedOperationException("createComment not implemented");
        throw th2;
    }

    public CDATASection createCDATASection(String str) {
        Throwable th;
        String str2 = str;
        Throwable th2 = th;
        new UnsupportedOperationException("createCDATASection not implemented");
        throw th2;
    }

    public ProcessingInstruction createProcessingInstruction(String str, String str2) {
        Throwable th;
        String str3 = str;
        String str4 = str2;
        Throwable th2 = th;
        new UnsupportedOperationException("createProcessingInstruction not implemented");
        throw th2;
    }

    public Attr createAttribute(String str) {
        Throwable th;
        String str2 = str;
        Throwable th2 = th;
        new UnsupportedOperationException("createAttribute not implemented");
        throw th2;
    }

    public EntityReference createEntityReference(String str) {
        Throwable th;
        String str2 = str;
        Throwable th2 = th;
        new UnsupportedOperationException("createEntityReference implemented");
        throw th2;
    }

    public Node importNode(Node node, boolean z) {
        Throwable th;
        Node node2 = node;
        boolean z2 = z;
        Throwable th2 = th;
        new UnsupportedOperationException("importNode not implemented");
        throw th2;
    }

    public Element createElementNS(String str, String str2) {
        Throwable th;
        String str3 = str;
        String str4 = str2;
        Throwable th2 = th;
        new UnsupportedOperationException("createElementNS not implemented");
        throw th2;
    }

    public Attr createAttributeNS(String str, String str2) {
        Throwable th;
        String str3 = str;
        String str4 = str2;
        Throwable th2 = th;
        new UnsupportedOperationException("createAttributeNS not implemented");
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

    public Element getElementById(String str) {
        String str2 = str;
        return null;
    }

    public boolean hasAttributes() {
        return false;
    }

    public String getInputEncoding() {
        return null;
    }

    public String getXmlEncoding() {
        return null;
    }

    public boolean getXmlStandalone() {
        return false;
    }

    public void setXmlStandalone(boolean xmlStandalone) {
    }

    public String getXmlVersion() {
        return "1.1";
    }

    public void setXmlVersion(String xmlVersion) {
    }

    public boolean getStrictErrorChecking() {
        return false;
    }

    public void setStrictErrorChecking(boolean strictErrorChecking) {
    }

    public String getDocumentURI() {
        return null;
    }

    public void setDocumentURI(String documentURI) {
    }

    public Node renameNode(Node node, String str, String str2) throws DOMException {
        Throwable th;
        Node node2 = node;
        String str3 = str;
        String str4 = str2;
        Throwable th2 = th;
        new DOMException(9, "renameNode not implemented");
        throw th2;
    }

    public Node adoptNode(Node node) throws DOMException {
        Throwable th;
        Node node2 = node;
        Throwable th2 = th;
        new DOMException(9, "adoptNode not implemented");
        throw th2;
    }

    public void normalizeDocument() {
    }

    public DOMConfiguration getDomConfig() {
        Throwable th;
        Throwable th2 = th;
        new DOMException(9, "getDomConfig not implemented");
        throw th2;
    }
}
