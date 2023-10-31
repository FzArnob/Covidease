package gnu.kawa.reflect;

import android.support.p000v4.app.FragmentTransaction;
import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.lists.ItemPredicate;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class OccurrenceType extends ObjectType implements Externalizable, TypeValue {
    public static final Type emptySequenceType = getInstance(SingletonType.instance, 0, 0);
    static final Method isInstanceMethod = typeOccurrenceType.getDeclaredMethod("isInstance", 1);
    public static final ClassType typeOccurrenceType = ClassType.make("gnu.kawa.reflect.OccurrenceType");
    Type base;
    int maxOccurs;
    int minOccurs;

    public Type getBase() {
        return this.base;
    }

    public int minOccurs() {
        return this.minOccurs;
    }

    public int maxOccurs() {
        return this.maxOccurs;
    }

    public OccurrenceType(Type base2, int minOccurs2, int maxOccurs2) {
        this.base = base2;
        this.minOccurs = minOccurs2;
        this.maxOccurs = maxOccurs2;
    }

    public static Type getInstance(Type type, int i, int i2) {
        Type base2;
        Type base3 = type;
        int minOccurs2 = i;
        int maxOccurs2 = i2;
        if (minOccurs2 == 1 && maxOccurs2 == 1) {
            return base3;
        }
        if (minOccurs2 == 0 && maxOccurs2 < 0 && (base3 == SingletonType.instance || base3 == Type.pointer_type)) {
            return Type.pointer_type;
        }
        new OccurrenceType(base3, minOccurs2, maxOccurs2);
        return base2;
    }

    public Type getImplementationType() {
        return Type.pointer_type;
    }

    public int compare(Type type) {
        Type other = type;
        if (other instanceof OccurrenceType) {
            OccurrenceType occOther = (OccurrenceType) other;
            if (this.minOccurs == occOther.minOccurs && this.maxOccurs == occOther.maxOccurs) {
                return this.base.compare(occOther.getBase());
            }
        }
        return -2;
    }

    public Object coerceFromObject(Object obj) {
        Throwable th;
        Object obj2 = obj;
        if (!(obj2 instanceof Values) && this.minOccurs <= 1 && this.maxOccurs != 0) {
            return this.base.coerceFromObject(obj2);
        }
        if (isInstance(obj2)) {
            return obj2;
        }
        Throwable th2 = th;
        new ClassCastException();
        throw th2;
    }

    public boolean isInstance(Object obj) {
        boolean z;
        Object obj2 = obj;
        if (obj2 instanceof Values) {
            Values vals = (Values) obj2;
            int pos = vals.startPos();
            int n = 0;
            if (this.base instanceof ItemPredicate) {
                ItemPredicate pred = (ItemPredicate) this.base;
                while (true) {
                    boolean matches = pred.isInstancePos(vals, pos);
                    pos = vals.nextPos(pos);
                    if (pos == 0) {
                        if (n < this.minOccurs || (this.maxOccurs >= 0 && n > this.maxOccurs)) {
                            z = false;
                        } else {
                            z = true;
                        }
                        return z;
                    } else if (!matches) {
                        return false;
                    } else {
                        n++;
                    }
                }
            } else {
                while (true) {
                    pos = vals.nextPos(pos);
                    if (pos == 0) {
                        return n >= this.minOccurs && (this.maxOccurs < 0 || n <= this.maxOccurs);
                    }
                    if (!this.base.isInstance(vals.getPosPrevious(pos))) {
                        return false;
                    }
                    n++;
                }
            }
        } else if (this.minOccurs > 1 || this.maxOccurs == 0) {
            return false;
        } else {
            return this.base.isInstance(obj2);
        }
    }

    public void emitTestIf(Variable variable, Declaration declaration, Compilation compilation) {
        Variable incoming = variable;
        Declaration decl = declaration;
        Compilation comp = compilation;
        CodeAttr code = comp.getCode();
        if (incoming != null) {
            code.emitLoad(incoming);
        }
        if (decl != null) {
            code.emitDup();
            decl.compileStore(comp);
        }
        comp.compileConstant(this);
        code.emitSwap();
        code.emitInvokeVirtual(isInstanceMethod);
        code.emitIfIntNotZero();
    }

    public void emitIsInstance(Variable incoming, Compilation comp, Target target) {
        InstanceOf.emitIsInstance(this, incoming, comp, target);
    }

    public Expression convertValue(Expression expression) {
        Expression expression2 = expression;
        return null;
    }

    public Procedure getConstructor() {
        return null;
    }

    public static int itemCountRange(Type type) {
        int max;
        Type type2 = type;
        if (type2 instanceof SingletonType) {
            return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
        }
        if (type2 instanceof OccurrenceType) {
            OccurrenceType occ = (OccurrenceType) type2;
            int min = occ.minOccurs();
            int max2 = occ.maxOccurs();
            int bnum = itemCountRange(occ.getBase());
            if ((min == 1 && max2 == 1) || bnum == 0) {
                return bnum;
            }
            if (max2 > 1048575) {
                max2 = -1;
            }
            if (max == 0) {
                return 0;
            }
            int bmin = bnum & 4095;
            int bmax = bnum >> 12;
            if (bnum != 4097) {
                if (min > 4095) {
                    min = 4095;
                }
                min *= bmin;
                if (min > 4095) {
                    min = 4095;
                }
                if (max < 0 || bmax < 0) {
                    max = -1;
                } else {
                    max *= bmax;
                }
                if (max > 1048575) {
                    max = -1;
                }
            }
            return (max << 12) | min;
        } else if (type2 instanceof PrimType) {
            return type2.isVoid() ? 0 : FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
        } else if (type2 instanceof ArrayType) {
            return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
        } else {
            if (!(type2 instanceof ObjectType) || type2.compare(Compilation.typeValues) != -3) {
                return -4096;
            }
            return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
        }
    }

    public static char itemCountCode(Type type) {
        int num = itemCountRange(type);
        int min = num & 4095;
        int max = num >> 12;
        return max == 0 ? '0' : min == 0 ? max == 1 ? '?' : '*' : (min == 1 && max == 1) ? '1' : '+';
    }

    public static boolean itemCountIsZeroOrOne(Type type) {
        return (itemCountRange(type) >> 13) == 0;
    }

    public static boolean itemCountIsOne(Type type) {
        return itemCountRange(type) == 4097;
    }

    public static Type itemPrimeType(Type type) {
        Type type2 = type;
        while (type2 instanceof OccurrenceType) {
            type2 = ((OccurrenceType) type2).getBase();
        }
        return itemCountIsOne(type2) ? type2 : SingletonType.instance;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        out.writeObject(this.base);
        out.writeInt(this.minOccurs);
        out.writeInt(this.maxOccurs);
    }

    public String toString() {
        StringBuffer stringBuffer;
        String b = this.base.toString();
        boolean parens = b == null || b.indexOf(32) >= 0;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        if (parens) {
            StringBuffer append = sbuf.append('(');
        }
        StringBuffer append2 = sbuf.append(b);
        if (parens) {
            StringBuffer append3 = sbuf.append(')');
        }
        if (!(this.minOccurs == 1 && this.maxOccurs == 1)) {
            if (this.minOccurs == 0 && this.maxOccurs == 1) {
                StringBuffer append4 = sbuf.append('?');
            } else if (this.minOccurs == 1 && this.maxOccurs == -1) {
                StringBuffer append5 = sbuf.append('+');
            } else if (this.minOccurs == 0 && this.maxOccurs == -1) {
                StringBuffer append6 = sbuf.append('*');
            } else {
                StringBuffer append7 = sbuf.append('{');
                StringBuffer append8 = sbuf.append(this.minOccurs);
                StringBuffer append9 = sbuf.append(',');
                if (this.maxOccurs >= 0) {
                    StringBuffer append10 = sbuf.append(this.maxOccurs);
                } else {
                    StringBuffer append11 = sbuf.append('*');
                }
                StringBuffer append12 = sbuf.append('}');
            }
        }
        return sbuf.toString();
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        this.base = (Type) in.readObject();
        this.minOccurs = in.readInt();
        this.maxOccurs = in.readInt();
    }
}
