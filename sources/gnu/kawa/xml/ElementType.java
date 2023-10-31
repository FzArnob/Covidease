package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.TypeValue;
import gnu.lists.AbstractSequence;
import gnu.lists.ElementPredicate;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.xml.NamespaceBinding;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javax.xml.namespace.QName;

public class ElementType extends NodeType implements TypeValue, Externalizable, ElementPredicate {
    public static final String MATCH_ANY_LOCALNAME = "";
    public static final Symbol MATCH_ANY_QNAME;
    public static final ElementType anyElement = make((String) null, (String) null);
    static final Method coerceMethod = typeElementType.getDeclaredMethod("coerce", 3);
    static final Method coerceOrNullMethod = typeElementType.getDeclaredMethod("coerceOrNull", 3);
    public static final ClassType typeElementType = ClassType.make("gnu.kawa.xml.ElementType");
    NamespaceBinding namespaceNodes;
    Symbol qname;

    static {
        Symbol symbol;
        new Symbol((Namespace) null, "");
        MATCH_ANY_QNAME = symbol;
    }

    public static ElementType make(String str, String str2) {
        Symbol symbol;
        Symbol qname2;
        ElementType elementType;
        String namespaceURI = str;
        String localName = str2;
        if (namespaceURI != null) {
            qname2 = Symbol.make(namespaceURI, localName);
        } else if (localName == "") {
            qname2 = MATCH_ANY_QNAME;
        } else {
            new Symbol((Namespace) null, localName);
            qname2 = symbol;
        }
        new ElementType(qname2);
        return elementType;
    }

    public static ElementType make(Symbol qname2) {
        ElementType elementType;
        new ElementType(qname2);
        return elementType;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ElementType(Symbol qname2) {
        this((String) null, qname2);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ElementType(java.lang.String r8, gnu.mapping.Symbol r9) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r0
            r4 = r1
            if (r4 == 0) goto L_0x0017
            r4 = r1
            int r4 = r4.length()
            if (r4 <= 0) goto L_0x0017
            r4 = r1
        L_0x000f:
            r3.<init>(r4)
            r3 = r0
            r4 = r2
            r3.qname = r4
            return
        L_0x0017:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r6 = r4
            r4 = r6
            r5 = r6
            r5.<init>()
            java.lang.String r5 = "ELEMENT "
            java.lang.StringBuilder r4 = r4.append(r5)
            r5 = r2
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = " (*)"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            goto L_0x000f
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.xml.ElementType.<init>(java.lang.String, gnu.mapping.Symbol):void");
    }

    public Type getImplementationType() {
        return ClassType.make("gnu.kawa.xml.KElement");
    }

    public final String getNamespaceURI() {
        return this.qname.getNamespaceURI();
    }

    public final String getLocalName() {
        return this.qname.getLocalName();
    }

    public void emitCoerceFromObject(CodeAttr codeAttr) {
        CodeAttr code = codeAttr;
        code.emitPushString(this.qname.getNamespaceURI());
        code.emitPushString(this.qname.getLocalName());
        code.emitInvokeStatic(coerceMethod);
    }

    public Object coerceFromObject(Object obj) {
        return coerce(obj, this.qname.getNamespaceURI(), this.qname.getLocalName());
    }

    public boolean isInstancePos(AbstractSequence abstractSequence, int i) {
        AbstractSequence seq = abstractSequence;
        int ipos = i;
        int kind = seq.getNextKind(ipos);
        if (kind == 33) {
            return isInstance(seq, ipos, seq.getNextTypeObject(ipos));
        }
        if (kind == 32) {
            return isInstance(seq.getPosNext(ipos));
        }
        return false;
    }

    public boolean isInstance(AbstractSequence abstractSequence, int i, Object obj) {
        String curNamespaceURI;
        String curLocalName;
        AbstractSequence abstractSequence2 = abstractSequence;
        int i2 = i;
        Object elementType = obj;
        String namespaceURI = this.qname.getNamespaceURI();
        String localName = this.qname.getLocalName();
        if (elementType instanceof Symbol) {
            Symbol qname2 = (Symbol) elementType;
            curNamespaceURI = qname2.getNamespaceURI();
            curLocalName = qname2.getLocalName();
        } else if (elementType instanceof QName) {
            QName qtype = (QName) elementType;
            curNamespaceURI = qtype.getNamespaceURI();
            curLocalName = qtype.getLocalPart();
        } else {
            curNamespaceURI = "";
            curLocalName = elementType.toString().intern();
        }
        if (localName != null && localName.length() == 0) {
            localName = null;
        }
        return (localName == curLocalName || localName == null) && (namespaceURI == curNamespaceURI || namespaceURI == null);
    }

    public boolean isInstance(Object obj) {
        return coerceOrNull(obj, this.qname.getNamespaceURI(), this.qname.getLocalName()) != null;
    }

    public static KElement coerceOrNull(Object obj, String str, String str2) {
        String curNamespaceURI;
        String curLocalName;
        String namespaceURI = str;
        String localName = str2;
        KElement pos = (KElement) NodeType.coerceOrNull(obj, 2);
        if (pos == null) {
            return null;
        }
        if (localName != null && localName.length() == 0) {
            localName = null;
        }
        Object curName = pos.getNextTypeObject();
        if (curName instanceof Symbol) {
            Symbol qname2 = (Symbol) curName;
            curNamespaceURI = qname2.getNamespaceURI();
            curLocalName = qname2.getLocalName();
        } else if (curName instanceof QName) {
            QName qtype = (QName) curName;
            curNamespaceURI = qtype.getNamespaceURI();
            curLocalName = qtype.getLocalPart();
        } else {
            curNamespaceURI = "";
            curLocalName = curName.toString().intern();
        }
        if ((localName == curLocalName || localName == null) && (namespaceURI == curNamespaceURI || namespaceURI == null)) {
            return pos;
        }
        return null;
    }

    public static KElement coerce(Object obj, String namespaceURI, String localName) {
        Throwable th;
        KElement pos = coerceOrNull(obj, namespaceURI, localName);
        if (pos != null) {
            return pos;
        }
        Throwable th2 = th;
        new ClassCastException();
        throw th2;
    }

    /* access modifiers changed from: protected */
    public void emitCoerceOrNullMethod(Variable variable, Compilation comp) {
        Variable incoming = variable;
        CodeAttr code = comp.getCode();
        if (incoming != null) {
            code.emitLoad(incoming);
        }
        code.emitPushString(this.qname.getNamespaceURI());
        code.emitPushString(this.qname.getLocalName());
        code.emitInvokeStatic(coerceOrNullMethod);
    }

    public NamespaceBinding getNamespaceNodes() {
        return this.namespaceNodes;
    }

    public void setNamespaceNodes(NamespaceBinding bindings) {
        NamespaceBinding namespaceBinding = bindings;
        this.namespaceNodes = namespaceBinding;
    }

    public Procedure getConstructor() {
        MakeElement makeElement;
        new MakeElement();
        MakeElement element = makeElement;
        element.tag = this.qname;
        element.setHandlingKeywordParameters(true);
        if (this.namespaceNodes != null) {
            element.setNamespaceNodes(this.namespaceNodes);
        }
        return element;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        String name = getName();
        out.writeUTF(name == null ? "" : name);
        out.writeObject(this.qname);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        String name = in.readUTF();
        if (name.length() > 0) {
            setName(name);
        }
        this.qname = (Symbol) in.readObject();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("ElementType ").append(this.qname).toString();
    }
}
