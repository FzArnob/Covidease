package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.Consumer;
import gnu.lists.SeqPosition;
import gnu.lists.Sequence;
import gnu.lists.TreeList;
import gnu.mapping.Values;
import gnu.xml.NodeTree;
import gnu.xml.XMLFilter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Nodes extends Values implements NodeList {
    static final int POS_SIZE = 5;
    int count;
    XMLFilter curFragment;
    NodeTree curNode;
    boolean inAttribute;
    int nesting = 0;

    public Nodes() {
    }

    public void writePosition(AbstractSequence seq, int ipos) {
        this.count++;
        super.writePosition(seq, ipos);
    }

    public int find(Object obj) {
        Object seq = obj;
        if (this.gapStart > 0) {
            int oindex = getIntN((this.gapStart - 5) + 1);
            if (this.objects[oindex] == seq) {
                return oindex;
            }
        }
        if (this.gapEnd < this.data.length) {
            int oindex2 = getIntN(this.gapEnd + 1);
            if (this.objects[oindex2] == seq) {
                return oindex2;
            }
        }
        return super.find(seq);
    }

    public void writeObject(Object obj) {
        Object v = obj;
        if (this.curFragment != null) {
            if (this.nesting != 0 || (!(v instanceof SeqPosition) && !(v instanceof TreeList))) {
                this.curFragment.writeObject(v);
                return;
            }
            finishFragment();
        }
        if (v instanceof SeqPosition) {
            SeqPosition seq = (SeqPosition) v;
            writePosition(seq.sequence, seq.ipos);
        } else if (v instanceof TreeList) {
            writePosition((TreeList) v, 0);
        } else {
            handleNonNode();
            this.curFragment.writeObject(v);
        }
    }

    /* access modifiers changed from: package-private */
    public void maybeStartTextNode() {
        Throwable th;
        if (this.curFragment == null) {
            Throwable th2 = th;
            new IllegalArgumentException("non-node where node required");
            throw th2;
        }
    }

    /* access modifiers changed from: package-private */
    public void handleNonNode() {
        Throwable th;
        if (this.curFragment == null) {
            Throwable th2 = th;
            new ClassCastException("atomic value where node is required");
            throw th2;
        }
    }

    public void writeFloat(float v) {
        handleNonNode();
        this.curFragment.writeFloat(v);
    }

    public void writeDouble(double v) {
        handleNonNode();
        this.curFragment.writeDouble(v);
    }

    public void writeLong(long v) {
        handleNonNode();
        this.curFragment.writeLong(v);
    }

    public void writeInt(int v) {
        handleNonNode();
        this.curFragment.writeInt(v);
    }

    public void writeBoolean(boolean v) {
        handleNonNode();
        this.curFragment.writeBoolean(v);
    }

    public void write(int v) {
        maybeStartTextNode();
        this.curFragment.write(v);
    }

    public Consumer append(CharSequence csq, int start, int end) {
        maybeStartTextNode();
        this.curFragment.write(csq, start, end);
        return this;
    }

    public void write(char[] buf, int off, int len) {
        maybeStartTextNode();
        this.curFragment.write(buf, off, len);
    }

    public void write(CharSequence str, int start, int length) {
        maybeStartTextNode();
        this.curFragment.write(str, start, length);
    }

    public void write(String str) {
        maybeStartTextNode();
        this.curFragment.write(str);
    }

    private void maybeStartNonTextNode() {
        if (this.curFragment != null && this.nesting == 0) {
            finishFragment();
        }
        if (this.curFragment == null) {
            startFragment();
        }
        this.nesting++;
    }

    private void maybeEndNonTextNode() {
        int i = this.nesting - 1;
        int i2 = i;
        this.nesting = i;
        if (i2 == 0) {
            finishFragment();
        }
    }

    public void startElement(Object type) {
        maybeStartNonTextNode();
        this.curFragment.startElement(type);
    }

    public void endElement() {
        this.curFragment.endElement();
        maybeEndNonTextNode();
    }

    public void startAttribute(Object attrType) {
        maybeStartNonTextNode();
        this.curFragment.startAttribute(attrType);
        this.inAttribute = true;
    }

    public void endAttribute() {
        if (this.inAttribute) {
            this.inAttribute = false;
            this.curFragment.endAttribute();
            maybeEndNonTextNode();
        }
    }

    public void writeComment(char[] chars, int offset, int length) {
        maybeStartNonTextNode();
        this.curFragment.writeComment(chars, offset, length);
        maybeEndNonTextNode();
    }

    public void writeCDATA(char[] chars, int offset, int length) {
        maybeStartNonTextNode();
        this.curFragment.writeCDATA(chars, offset, length);
    }

    public void writeProcessingInstruction(String target, char[] content, int offset, int length) {
        maybeStartNonTextNode();
        this.curFragment.writeProcessingInstruction(target, content, offset, length);
        maybeEndNonTextNode();
    }

    public void startDocument() {
        maybeStartNonTextNode();
        this.curFragment.startDocument();
    }

    public void endDocument() {
        this.curFragment.endDocument();
        maybeEndNonTextNode();
    }

    public void beginEntity(Object base) {
        maybeStartNonTextNode();
        this.curFragment.beginEntity(base);
    }

    public void endEntity() {
        this.curFragment.endEntity();
        maybeEndNonTextNode();
    }

    /* access modifiers changed from: package-private */
    public void startFragment() {
        NodeTree nodeTree;
        XMLFilter xMLFilter;
        new NodeTree();
        this.curNode = nodeTree;
        new XMLFilter(this.curNode);
        this.curFragment = xMLFilter;
        writePosition(this.curNode, 0);
    }

    /* access modifiers changed from: package-private */
    public void finishFragment() {
        this.curNode = null;
        this.curFragment = null;
    }

    public int size() {
        return this.count;
    }

    public int getLength() {
        return this.count;
    }

    public Object get(int index) {
        Throwable th;
        Throwable th2;
        int i = 5 * index;
        if (i >= this.gapStart) {
            i += this.gapEnd - this.gapStart;
        }
        if (i < 0 || i >= this.data.length) {
            Throwable th3 = th;
            new IndexOutOfBoundsException();
            throw th3;
        } else if (this.data[i] == 61711) {
            return KNode.make((NodeTree) this.objects[getIntN(i + 1)], getIntN(i + 3));
        } else {
            Throwable th4 = th2;
            new RuntimeException("internal error - unexpected data");
            throw th4;
        }
    }

    public Node item(int i) {
        int index = i;
        if (index >= this.count) {
            return null;
        }
        return (Node) get(index);
    }

    public Object getPosNext(int ipos) {
        Throwable th;
        int index = posToDataIndex(ipos);
        if (index == this.data.length) {
            return Sequence.eofValue;
        }
        if (this.data[index] == 61711) {
            return KNode.make((NodeTree) this.objects[getIntN(index + 1)], getIntN(index + 3));
        }
        Throwable th2 = th;
        new RuntimeException("internal error - unexpected data");
        throw th2;
    }

    public AbstractSequence getSeq(int index) {
        Throwable th;
        int i = 5 * index;
        if (i >= this.gapStart) {
            i += this.gapEnd - this.gapStart;
        }
        if (i < 0 || i >= this.data.length) {
            return null;
        }
        if (this.data[i] == 61711) {
            return (AbstractSequence) this.objects[getIntN(i + 1)];
        }
        Throwable th2 = th;
        new RuntimeException("internal error - unexpected data");
        throw th2;
    }

    public int getPos(int index) {
        Throwable th;
        int i = 5 * index;
        if (i >= this.gapStart) {
            i += this.gapEnd - this.gapStart;
        }
        if (this.data[i] == 61711) {
            return getIntN(i + 3);
        }
        Throwable th2 = th;
        new RuntimeException("internal error - unexpected data");
        throw th2;
    }

    public static KNode root(NodeTree nodeTree, int i) {
        int root;
        NodeTree seq = nodeTree;
        int i2 = i;
        if (seq.gapStart <= 5 || seq.data[0] != 61714) {
            root = 0;
        } else {
            root = 10;
        }
        return KNode.make(seq, root);
    }
}
