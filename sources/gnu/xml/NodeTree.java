package gnu.xml;

import gnu.kawa.xml.ElementType;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.lists.AbstractSequence;
import gnu.lists.Consumer;
import gnu.lists.SeqPosition;
import gnu.lists.TreeList;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.Symbol;
import gnu.text.Path;
import gnu.text.URIPath;
import java.io.Writer;

public class NodeTree extends TreeList {
    static int counter;

    /* renamed from: id */
    int f248id;
    int idCount;
    String[] idNames;
    int[] idOffsets;

    public NodeTree() {
    }

    public int nextPos(int i) {
        int position = i;
        boolean z = (position & 1) != 0;
        int index = posToDataIndex(position);
        int next = nextNodeIndex(index, Integer.MAX_VALUE);
        if (next != index) {
            return next << 1;
        }
        if (index == this.data.length) {
            return 0;
        }
        return (index << 1) + 3;
    }

    public static NodeTree make() {
        NodeTree nodeTree;
        NodeTree nodeTree2 = nodeTree;
        new NodeTree();
        return nodeTree2;
    }

    public int getId() {
        if (this.f248id == 0) {
            int i = counter + 1;
            counter = i;
            this.f248id = i;
        }
        return this.f248id;
    }

    public int stableCompare(AbstractSequence abstractSequence) {
        AbstractSequence other = abstractSequence;
        if (this == other) {
            return 0;
        }
        int comp = super.stableCompare(other);
        if (comp == 0 && (other instanceof NodeTree)) {
            int id1 = getId();
            int id2 = ((NodeTree) other).getId();
            comp = id1 < id2 ? -1 : id1 > id2 ? 1 : 0;
        }
        return comp;
    }

    public SeqPosition getIteratorAtPos(int ipos) {
        return KNode.make(this, ipos);
    }

    public String posNamespaceURI(int ipos) {
        Object type = getNextTypeObject(ipos);
        if (type instanceof XName) {
            return ((XName) type).getNamespaceURI();
        }
        if (type instanceof Symbol) {
            return ((Symbol) type).getNamespaceURI();
        }
        return null;
    }

    public String posPrefix(int ipos) {
        String name = getNextTypeName(ipos);
        if (name == null) {
            return null;
        }
        int colon = name.indexOf(58);
        return colon < 0 ? null : name.substring(0, colon);
    }

    public String posLocalName(int i) {
        int ipos = i;
        Object type = getNextTypeObject(ipos);
        if (type instanceof XName) {
            return ((XName) type).getLocalPart();
        }
        if (type instanceof Symbol) {
            return ((Symbol) type).getLocalName();
        }
        return getNextTypeName(ipos);
    }

    public boolean posIsDefaultNamespace(int i, String str) {
        Throwable th;
        int i2 = i;
        String str2 = str;
        Throwable th2 = th;
        new Error("posIsDefaultNamespace not implemented");
        throw th2;
    }

    public String posLookupNamespaceURI(int i, String str) {
        Throwable th;
        int ipos = i;
        String prefix = str;
        if (getNextKind(ipos) != 33) {
            Throwable th2 = th;
            new IllegalArgumentException("argument must be an element");
            throw th2;
        }
        Object type = getNextTypeObject(ipos);
        if (type instanceof XName) {
            return ((XName) type).lookupNamespaceURI(prefix);
        }
        return null;
    }

    public String posLookupPrefix(int i, String str) {
        Throwable th;
        int i2 = i;
        String str2 = str;
        Throwable th2 = th;
        new Error("posLookupPrefix not implemented");
        throw th2;
    }

    public int posFirstChild(int ipos) {
        int index = gotoChildrenStart(posToDataIndex(ipos));
        if (index < 0) {
            return -1;
        }
        char datum = this.data[index];
        if (datum == 61707 || datum == 61708 || datum == 61713) {
            return -1;
        }
        return index << 1;
    }

    public boolean posHasAttributes(int ipos) {
        int index = gotoAttributesStart(posToDataIndex(ipos));
        if (index < 0) {
            return false;
        }
        return index >= 0 && this.data[index] == 61705;
    }

    public int getAttribute(int parent, String str, String str2) {
        String namespaceURI = str;
        String localName = str2;
        return getAttributeI(parent, namespaceURI == null ? null : namespaceURI.intern(), localName == null ? null : localName.intern());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getAttributeI(int r8, java.lang.String r9, java.lang.String r10) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r10
            r5 = r0
            r6 = r1
            int r5 = r5.firstAttributePos(r6)
            r4 = r5
        L_0x000b:
            r5 = r4
            if (r5 == 0) goto L_0x0018
            r5 = r0
            r6 = r4
            int r5 = r5.getNextKind(r6)
            r6 = 35
            if (r5 == r6) goto L_0x001b
        L_0x0018:
            r5 = 0
            r0 = r5
        L_0x001a:
            return r0
        L_0x001b:
            r5 = r3
            if (r5 == 0) goto L_0x0027
            r5 = r0
            r6 = r4
            java.lang.String r5 = r5.posLocalName(r6)
            r6 = r3
            if (r5 != r6) goto L_0x0036
        L_0x0027:
            r5 = r2
            if (r5 == 0) goto L_0x0033
            r5 = r0
            r6 = r4
            java.lang.String r5 = r5.posNamespaceURI(r6)
            r6 = r2
            if (r5 != r6) goto L_0x0036
        L_0x0033:
            r5 = r4
            r0 = r5
            goto L_0x001a
        L_0x0036:
            r5 = r0
            r6 = r4
            int r5 = r5.nextPos(r6)
            r4 = r5
            goto L_0x000b
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xml.NodeTree.getAttributeI(int, java.lang.String, java.lang.String):int");
    }

    public Object typedValue(int i) {
        StringBuffer stringBuffer;
        Object obj;
        int ipos = i;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        int stringValue = stringValue(posToDataIndex(ipos), sbuf);
        String str = sbuf.toString();
        int kind = getNextKind(ipos);
        if (kind == 37 || kind == 36) {
            return str;
        }
        new UntypedAtomic(str);
        return obj;
    }

    public String posTarget(int ipos) {
        Throwable th;
        int index = posToDataIndex(ipos);
        if (this.data[index] == 61716) {
            return (String) this.objects[getIntN(index + 1)];
        }
        Throwable th2 = th;
        new ClassCastException("expected process-instruction");
        throw th2;
    }

    public int ancestorAttribute(int i, String str, String str2) {
        String namespace = str;
        String name = str2;
        for (int ipos = i; ipos != -1; ipos = parentPos(ipos)) {
            int attr = getAttributeI(ipos, namespace, name);
            if (attr != 0) {
                return attr;
            }
        }
        return 0;
    }

    public Path baseUriOfPos(int i, boolean z) {
        int attr;
        int pos = i;
        boolean resolveRelative = z;
        Path uri = null;
        int index = posToDataIndex(pos);
        while (index != this.data.length) {
            char datum = this.data[index];
            Path base = null;
            if (datum == 61714) {
                int oindex = getIntN(index + 1);
                if (oindex >= 0) {
                    base = URIPath.makeURI(this.objects[oindex]);
                }
            } else if (((datum >= 40960 && datum <= 45055) || datum == 61704) && (attr = getAttributeI(pos, NamespaceBinding.XML_NAMESPACE, "base")) != 0) {
                base = URIPath.valueOf(KNode.getNodeValue(this, attr));
            }
            if (base != null) {
                uri = (uri == null || !resolveRelative) ? base : base.resolve(uri);
                if (uri.isAbsolute() || !resolveRelative) {
                    return uri;
                }
            }
            index = parentOrEntityI(index);
            if (index == -1) {
                return uri;
            }
            pos = index << 1;
        }
        return null;
    }

    public String toString() {
        CharArrayOutPort charArrayOutPort;
        Consumer consumer;
        new CharArrayOutPort();
        CharArrayOutPort wr = charArrayOutPort;
        new XMLPrinter((Writer) wr);
        consume(consumer);
        wr.close();
        return wr.toString();
    }

    public void makeIDtableIfNeeded() {
        if (this.idNames == null) {
            this.idNames = new String[64];
            this.idOffsets = new int[64];
            int limit = endPos();
            int ipos = 0;
            while (true) {
                ipos = nextMatching(ipos, ElementType.anyElement, limit, true);
                if (ipos != 0) {
                    int attr = getAttributeI(ipos, NamespaceBinding.XML_NAMESPACE, "id");
                    if (attr != 0) {
                        enterID(KNode.getNodeValue(this, attr), ipos);
                    }
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void enterID(String str, int i) {
        int size;
        String name = str;
        int offset = i;
        String[] tmpNames = this.idNames;
        int[] tmpOffsets = this.idOffsets;
        if (tmpNames == null) {
            size = 64;
            this.idNames = new String[64];
            this.idOffsets = new int[64];
        } else {
            int i2 = 4 * this.idCount;
            int length = this.idNames.length;
            size = length;
            if (i2 >= 3 * length) {
                this.idNames = new String[(2 * size)];
                this.idOffsets = new int[(2 * size)];
                this.idCount = 0;
                int i3 = size;
                while (true) {
                    i3--;
                    if (i3 < 0) {
                        break;
                    }
                    String oldName = tmpNames[i3];
                    if (oldName != null) {
                        enterID(oldName, tmpOffsets[i3]);
                    }
                }
                tmpNames = this.idNames;
                tmpOffsets = this.idOffsets;
                size = 2 * size;
            }
        }
        int hash = name.hashCode();
        int mask = size - 1;
        int index = hash & mask;
        int step = ((hash ^ -1) << 1) | 1;
        while (true) {
            String oldName2 = tmpNames[index];
            if (oldName2 == null) {
                tmpNames[index] = name;
                tmpOffsets[index] = offset;
                this.idCount++;
                return;
            } else if (!oldName2.equals(name)) {
                index = (index + step) & mask;
            } else {
                return;
            }
        }
    }

    public int lookupID(String str) {
        String name = str;
        String[] tmpNames = this.idNames;
        int[] tmpOffsets = this.idOffsets;
        int size = this.idNames.length;
        int hash = name.hashCode();
        int mask = size - 1;
        int index = hash & mask;
        int step = ((hash ^ -1) << 1) | 1;
        while (true) {
            String oldName = tmpNames[index];
            if (oldName == null) {
                return -1;
            }
            if (oldName.equals(name)) {
                return tmpOffsets[index];
            }
            index = (index + step) & mask;
        }
    }
}
