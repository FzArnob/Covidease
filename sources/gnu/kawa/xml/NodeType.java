package gnu.kawa.xml;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConditionalTarget;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.kawa.reflect.InstanceOf;
import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.mapping.Procedure;
import gnu.xml.NodeTree;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.shaded.apache.http.cookie.ClientCookie;

public class NodeType extends ObjectType implements TypeValue, NodePredicate, Externalizable {
    public static final int ATTRIBUTE_OK = 4;
    public static final int COMMENT_OK = 16;
    public static final int DOCUMENT_OK = 8;
    public static final int ELEMENT_OK = 2;
    public static final int PI_OK = 32;
    public static final int TEXT_OK = 1;
    public static final NodeType anyNodeTest;
    static final Method coerceMethod = typeNodeType.getDeclaredMethod("coerceForce", 2);
    static final Method coerceOrNullMethod = typeNodeType.getDeclaredMethod("coerceOrNull", 2);
    public static final NodeType commentNodeTest;
    public static final NodeType documentNodeTest;
    public static final NodeType nodeType;
    public static final NodeType textNodeTest;
    public static final ClassType typeKNode = ClassType.make("gnu.kawa.xml.KNode");
    public static final ClassType typeNodeType = ClassType.make("gnu.kawa.xml.NodeType");
    int kinds;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NodeType(String name, int kinds2) {
        super(name);
        this.kinds = -1;
        this.kinds = kinds2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public NodeType(String name) {
        this(name, -1);
    }

    public void emitCoerceFromObject(CodeAttr codeAttr) {
        CodeAttr code = codeAttr;
        code.emitPushInt(this.kinds);
        code.emitInvokeStatic(coerceMethod);
    }

    public Expression convertValue(Expression value) {
        ApplyExp applyExp;
        ApplyExp applyExp2 = applyExp;
        new ApplyExp(coerceMethod, value);
        ApplyExp aexp = applyExp2;
        aexp.setType(this);
        return aexp;
    }

    public Object coerceFromObject(Object obj) {
        return coerceForce(obj, this.kinds);
    }

    public Type getImplementationType() {
        return typeKNode;
    }

    public int compare(Type other) {
        return getImplementationType().compare(other);
    }

    public boolean isInstance(Object obj) {
        Object obj2 = obj;
        if (!(obj2 instanceof KNode)) {
            return false;
        }
        KNode pos = (KNode) obj2;
        return isInstancePos(pos.sequence, pos.getPos());
    }

    public boolean isInstancePos(AbstractSequence seq, int ipos) {
        return isInstance(seq, ipos, this.kinds);
    }

    public static boolean isInstance(AbstractSequence seq, int ipos, int i) {
        boolean z;
        int kinds2 = i;
        int kind = seq.getNextKind(ipos);
        if (kinds2 < 0) {
            if (kind != 0) {
                z = true;
            } else {
                z = false;
            }
            return z;
        }
        switch (kind) {
            case 0:
                return false;
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 32:
                return (kinds2 & 1) != 0;
            case 33:
                return (kinds2 & 2) != 0;
            case 34:
                return (kinds2 & 8) != 0;
            case 35:
                return (kinds2 & 4) != 0;
            case 36:
                return (kinds2 & 16) != 0;
            case 37:
                return (kinds2 & 32) != 0;
            default:
                return true;
        }
    }

    public static KNode coerceForce(Object obj, int kinds2) {
        Throwable th;
        StringBuilder sb;
        Object obj2 = obj;
        KNode pos = coerceOrNull(obj2, kinds2);
        if (pos != null) {
            return pos;
        }
        Throwable th2 = th;
        new StringBuilder();
        new ClassCastException(sb.append("coerce from ").append(obj2.getClass()).toString());
        throw th2;
    }

    public static KNode coerceOrNull(Object obj, int i) {
        KNode pos;
        Object obj2 = obj;
        int kinds2 = i;
        if (obj2 instanceof NodeTree) {
            pos = KNode.make((NodeTree) obj2);
        } else if (!(obj2 instanceof KNode)) {
            return null;
        } else {
            pos = (KNode) obj2;
        }
        return isInstance(pos.sequence, pos.ipos, kinds2) ? pos : null;
    }

    /* access modifiers changed from: protected */
    public void emitCoerceOrNullMethod(Variable variable, Compilation comp) {
        Variable incoming = variable;
        CodeAttr code = comp.getCode();
        if (incoming != null) {
            code.emitLoad(incoming);
        }
        code.emitPushInt(this.kinds);
        code.emitInvokeStatic(coerceOrNullMethod);
    }

    public void emitTestIf(Variable incoming, Declaration declaration, Compilation compilation) {
        Declaration decl = declaration;
        Compilation comp = compilation;
        CodeAttr code = comp.getCode();
        emitCoerceOrNullMethod(incoming, comp);
        if (decl != null) {
            code.emitDup();
            decl.compileStore(comp);
        }
        code.emitIfNotNull();
    }

    public void emitIsInstance(Variable variable, Compilation compilation, Target target) {
        Variable incoming = variable;
        Compilation comp = compilation;
        Target target2 = target;
        if (target2 instanceof ConditionalTarget) {
            ConditionalTarget ctarget = (ConditionalTarget) target2;
            emitCoerceOrNullMethod(incoming, comp);
            CodeAttr code = comp.getCode();
            if (ctarget.trueBranchComesFirst) {
                code.emitGotoIfCompare1(ctarget.ifFalse, 198);
            } else {
                code.emitGotoIfCompare1(ctarget.ifTrue, 199);
            }
            ctarget.emitGotoFirstBranch(code);
            return;
        }
        InstanceOf.emitIsInstance(this, incoming, comp, target2);
    }

    static {
        NodeType nodeType2;
        NodeType nodeType3;
        NodeType nodeType4;
        NodeType nodeType5;
        NodeType nodeType6;
        new NodeType("gnu.kawa.xml.KNode");
        nodeType = nodeType2;
        new NodeType("document-node", 8);
        documentNodeTest = nodeType3;
        new NodeType(PropertyTypeConstants.PROPERTY_TYPE_TEXT, 1);
        textNodeTest = nodeType4;
        new NodeType(ClientCookie.COMMENT_ATTR, 16);
        commentNodeTest = nodeType5;
        new NodeType("node");
        anyNodeTest = nodeType6;
    }

    public Procedure getConstructor() {
        return null;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("NodeType ").append(getName()).toString();
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        String name = getName();
        out.writeUTF(name == null ? "" : name);
        out.writeInt(this.kinds);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        String name = in.readUTF();
        if (name.length() > 0) {
            setName(name);
        }
        this.kinds = in.readInt();
    }
}
