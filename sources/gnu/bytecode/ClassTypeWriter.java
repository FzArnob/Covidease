package gnu.bytecode;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;

public class ClassTypeWriter extends PrintWriter {
    public static final int PRINT_CONSTANT_POOL = 1;
    public static final int PRINT_CONSTANT_POOL_INDEXES = 2;
    public static final int PRINT_EXTRAS = 8;
    public static final int PRINT_VERBOSE = 15;
    public static final int PRINT_VERSION = 4;
    ClassType ctype;
    int flags;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ClassTypeWriter(ClassType ctype2, Writer stream, int flags2) {
        super(stream);
        this.ctype = ctype2;
        this.flags = flags2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ClassTypeWriter(ClassType ctype2, OutputStream stream, int flags2) {
        super(stream);
        this.ctype = ctype2;
        this.flags = flags2;
    }

    public void setFlags(int flags2) {
        int i = flags2;
        this.flags = i;
    }

    public static void print(ClassType ctype2, PrintWriter stream, int flags2) {
        ClassTypeWriter classTypeWriter;
        new ClassTypeWriter(ctype2, (Writer) stream, flags2);
        ClassTypeWriter writer = classTypeWriter;
        writer.print();
        writer.flush();
    }

    public static void print(ClassType ctype2, PrintStream stream, int flags2) {
        ClassTypeWriter classTypeWriter;
        new ClassTypeWriter(ctype2, (OutputStream) stream, flags2);
        ClassTypeWriter writer = classTypeWriter;
        writer.print();
        writer.flush();
    }

    public void print() {
        if ((this.flags & 4) != 0) {
            print("Classfile format major version: ");
            print(this.ctype.getClassfileMajorVersion());
            print(", minor version: ");
            print(this.ctype.getClassfileMinorVersion());
            println('.');
        }
        if ((this.flags & 1) != 0) {
            printConstantPool();
        }
        printClassInfo();
        printFields();
        printMethods();
        printAttributes();
    }

    public void setClass(ClassType ctype2) {
        ClassType classType = ctype2;
        this.ctype = classType;
    }

    public void print(ClassType ctype2) {
        this.ctype = ctype2;
        print();
    }

    public void printAttributes() {
        AttrContainer attrs = this.ctype;
        println();
        print("Attributes (count: ");
        print(Attribute.count(attrs));
        println("):");
        printAttributes(attrs);
    }

    public void printAttributes(AttrContainer container) {
        Attribute attributes = container.getAttributes();
        while (true) {
            Attribute attr = attributes;
            if (attr != null) {
                attr.print(this);
                attributes = attr.next;
            } else {
                return;
            }
        }
    }

    public void printClassInfo() {
        int length;
        println();
        print("Access flags:");
        print(Access.toString(this.ctype.getModifiers(), Access.CLASS_CONTEXT));
        println();
        print("This class: ");
        printOptionalIndex(this.ctype.thisClassIndex);
        printConstantTersely(this.ctype.thisClassIndex, 7);
        print(" super: ");
        if (this.ctype.superClassIndex == -1) {
            print("<unknown>");
        } else if (this.ctype.superClassIndex == 0) {
            print("0");
        } else {
            printOptionalIndex(this.ctype.superClassIndex);
            printConstantTersely(this.ctype.superClassIndex, 7);
        }
        println();
        print("Interfaces (count: ");
        int[] interfaces = this.ctype.interfaceIndexes;
        if (interfaces == null) {
            length = 0;
        } else {
            length = interfaces.length;
        }
        int n_interfaces = length;
        print(n_interfaces);
        print("):");
        println();
        for (int i = 0; i < n_interfaces; i++) {
            print("- Implements: ");
            int index = interfaces[i];
            printOptionalIndex(index);
            printConstantTersely(index, 7);
            println();
        }
    }

    public void printFields() {
        println();
        print("Fields (count: ");
        print(this.ctype.fields_count);
        print("):");
        println();
        int ifield = 0;
        Field field = this.ctype.fields;
        while (true) {
            Field field2 = field;
            if (field2 != null) {
                print("Field name: ");
                if (field2.name_index != 0) {
                    printOptionalIndex(field2.name_index);
                }
                print(field2.getName());
                print(Access.toString(field2.flags, Access.FIELD_CONTEXT));
                print(" Signature: ");
                if (field2.signature_index != 0) {
                    printOptionalIndex(field2.signature_index);
                }
                printSignature(field2.type);
                println();
                printAttributes(field2);
                ifield++;
                field = field2.next;
            } else {
                return;
            }
        }
    }

    public void printMethods() {
        println();
        print("Methods (count: ");
        print(this.ctype.methods_count);
        print("):");
        println();
        Method method = this.ctype.methods;
        while (true) {
            Method method2 = method;
            if (method2 != null) {
                printMethod(method2);
                method = method2.next;
            } else {
                return;
            }
        }
    }

    public void printMethod(Method method) {
        Method method2 = method;
        println();
        print("Method name:");
        if (method2.name_index != 0) {
            printOptionalIndex(method2.name_index);
        }
        print('\"');
        print(method2.getName());
        print('\"');
        print(Access.toString(method2.access_flags, Access.METHOD_CONTEXT));
        print(" Signature: ");
        if (method2.signature_index != 0) {
            printOptionalIndex(method2.signature_index);
        }
        print('(');
        for (int i = 0; i < method2.arg_types.length; i++) {
            if (i > 0) {
                print(',');
            }
            printSignature(method2.arg_types[i]);
        }
        print(')');
        printSignature(method2.return_type);
        println();
        printAttributes(method2);
    }

    /* access modifiers changed from: package-private */
    public CpoolEntry getCpoolEntry(int i) {
        int index = i;
        CpoolEntry[] pool = this.ctype.constants.pool;
        if (pool == null || index < 0 || index >= pool.length) {
            return null;
        }
        return pool[index];
    }

    /* access modifiers changed from: package-private */
    public final void printConstantTersely(CpoolEntry cpoolEntry, int i) {
        CpoolEntry entry = cpoolEntry;
        int expected_tag = i;
        if (entry == null) {
            print("<invalid constant index>");
        } else if (entry.getTag() != expected_tag) {
            print("<unexpected constant type ");
            entry.print(this, 1);
            print('>');
        } else {
            entry.print(this, 0);
        }
    }

    /* access modifiers changed from: package-private */
    public final void printConstantTersely(int index, int expected_tag) {
        printConstantTersely(getCpoolEntry(index), expected_tag);
    }

    /* access modifiers changed from: package-private */
    public final void printContantUtf8AsClass(int i) {
        int type_index = i;
        CpoolEntry entry = getCpoolEntry(type_index);
        if (entry == null || entry.getTag() != 1) {
            printConstantTersely(type_index, 1);
            return;
        }
        String name = ((CpoolUtf8) entry).string;
        Type.printSignature(name, 0, name.length(), this);
    }

    /* access modifiers changed from: package-private */
    public final void printConstantOperand(int i) {
        int index = i;
        print(' ');
        printOptionalIndex(index);
        CpoolEntry[] pool = this.ctype.constants.pool;
        if (pool != null && index >= 0 && index < pool.length) {
            CpoolEntry cpoolEntry = pool[index];
            CpoolEntry entry = cpoolEntry;
            if (cpoolEntry != null) {
                print('<');
                entry.print(this, 1);
                print('>');
                return;
            }
        }
        print("<invalid constant index>");
    }

    public final void printQuotedString(String str) {
        String string = str;
        print('\"');
        int len = string.length();
        for (int i = 0; i < len; i++) {
            char ch = string.charAt(i);
            if (ch == '\"') {
                print("\\\"");
            } else if (ch < ' ' || ch >= 127) {
                if (ch != 10) {
                    print("\\u");
                    int j = 4;
                    while (true) {
                        j--;
                        if (j < 0) {
                            break;
                        }
                        print(Character.forDigit((ch >> (j * 4)) & 15, 16));
                    }
                } else {
                    print("\\n");
                }
            } else {
                print(ch);
            }
        }
        print('\"');
    }

    public void printConstantPool() {
        CpoolEntry[] pool = this.ctype.constants.pool;
        int length = this.ctype.constants.count;
        for (int i = 1; i <= length; i++) {
            CpoolEntry entry = pool[i];
            if (entry != null) {
                print('#');
                print(entry.index);
                print(": ");
                entry.print(this, 2);
                println();
            }
        }
    }

    public final void printOptionalIndex(int i) {
        int index = i;
        if ((this.flags & 2) != 0) {
            print('#');
            print(index);
            print('=');
        }
    }

    public final void printOptionalIndex(CpoolEntry entry) {
        printOptionalIndex(entry.index);
    }

    /* access modifiers changed from: package-private */
    public void printName(String name) {
        print(name);
    }

    public final int printSignature(String str, int i) {
        String name;
        String sig = str;
        int pos = i;
        int len = sig.length();
        if (pos >= len) {
            print("<empty signature>");
            return pos;
        }
        int sig_length = Type.signatureLength(sig, pos);
        if (sig_length <= 0 || (name = Type.signatureToName(sig.substring(pos, pos + sig_length))) == null) {
            char c = sig.charAt(pos);
            if (c != '(') {
                print(c);
                return pos + 1;
            }
            int nargs = 0;
            int pos2 = pos + 1;
            print(c);
            while (pos2 < len) {
                char c2 = sig.charAt(pos2);
                if (c2 == ')') {
                    print(c2);
                    return printSignature(sig, pos2 + 1);
                }
                int i2 = nargs;
                nargs++;
                if (i2 > 0) {
                    print(',');
                }
                pos2 = printSignature(sig, pos2);
            }
            print("<truncated method signature>");
            return pos2;
        }
        print(name);
        return pos + sig_length;
    }

    public final void printSignature(String str) {
        String sig = str;
        int pos = printSignature(sig, 0);
        if (pos < sig.length()) {
            print("<trailing junk:");
            print(sig.substring(pos));
            print('>');
        }
    }

    public final void printSignature(Type type) {
        Type type2 = type;
        if (type2 == null) {
            print("<unknown type>");
        } else {
            printSignature(type2.getSignature());
        }
    }

    public void printSpaces(int i) {
        int count = i;
        while (true) {
            count--;
            if (count >= 0) {
                print(' ');
            } else {
                return;
            }
        }
    }
}
