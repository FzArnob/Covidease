package kawa.lang;

import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongArguments;

public class RecordConstructor extends ProcedureN {
    Field[] fields;
    ClassType type;

    public RecordConstructor(ClassType type2, Field[] fields2) {
        this.type = type2;
        this.fields = fields2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public RecordConstructor(Class clas, Field[] fields2) {
        this((ClassType) Type.make(clas), fields2);
    }

    public RecordConstructor(Class clas) {
        init((ClassType) Type.make(clas));
    }

    public RecordConstructor(ClassType type2) {
        init(type2);
    }

    private void init(ClassType classType) {
        ClassType type2 = classType;
        this.type = type2;
        Field list = type2.getFields();
        int count = 0;
        Field field = list;
        while (true) {
            Field fld = field;
            if (fld == null) {
                break;
            }
            if ((fld.getModifiers() & 9) == 1) {
                count++;
            }
            field = fld.getNext();
        }
        this.fields = new Field[count];
        int i = 0;
        Field field2 = list;
        while (true) {
            Field fld2 = field2;
            if (fld2 != null) {
                if ((fld2.getModifiers() & 9) == 1) {
                    int i2 = i;
                    i++;
                    this.fields[i2] = fld2;
                }
                field2 = fld2.getNext();
            } else {
                return;
            }
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public RecordConstructor(Class clas, Object fieldsList) {
        this((ClassType) Type.make(clas), fieldsList);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x007a, code lost:
        r0.fields[r5] = r8;
        r2 = r6.getCdr();
        r5 = r5 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public RecordConstructor(gnu.bytecode.ClassType r15, java.lang.Object r16) {
        /*
            r14 = this;
            r0 = r14
            r1 = r15
            r2 = r16
            r9 = r0
            r9.<init>()
            r9 = r0
            r10 = r1
            r9.type = r10
            r9 = r2
            if (r9 != 0) goto L_0x0015
            r9 = r0
            r10 = r1
            r9.init(r10)
        L_0x0014:
            return
        L_0x0015:
            r9 = r2
            r10 = 0
            int r9 = gnu.lists.LList.listLength(r9, r10)
            r3 = r9
            r9 = r0
            r10 = r3
            gnu.bytecode.Field[] r10 = new gnu.bytecode.Field[r10]
            r9.fields = r10
            r9 = r1
            gnu.bytecode.Field r9 = r9.getFields()
            r4 = r9
            r9 = 0
            r5 = r9
        L_0x002a:
            r9 = r5
            r10 = r3
            if (r9 >= r10) goto L_0x0014
            r9 = r2
            gnu.lists.Pair r9 = (gnu.lists.Pair) r9
            r6 = r9
            r9 = r6
            java.lang.Object r9 = r9.getCar()
            java.lang.String r9 = r9.toString()
            r7 = r9
            r9 = r4
            r8 = r9
        L_0x003e:
            r9 = r8
            if (r9 != 0) goto L_0x0072
            java.lang.RuntimeException r9 = new java.lang.RuntimeException
            r13 = r9
            r9 = r13
            r10 = r13
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r13 = r11
            r11 = r13
            r12 = r13
            r12.<init>()
            java.lang.String r12 = "no such field "
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = r7
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r12 = " in "
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = r1
            java.lang.String r12 = r12.getName()
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r11 = r11.toString()
            r10.<init>(r11)
            throw r9
        L_0x0072:
            r9 = r8
            java.lang.String r9 = r9.getSourceName()
            r10 = r7
            if (r9 != r10) goto L_0x008a
            r9 = r0
            gnu.bytecode.Field[] r9 = r9.fields
            r10 = r5
            r11 = r8
            r9[r10] = r11
            r9 = r6
            java.lang.Object r9 = r9.getCdr()
            r2 = r9
            int r5 = r5 + 1
            goto L_0x002a
        L_0x008a:
            r9 = r8
            gnu.bytecode.Field r9 = r9.getNext()
            r8 = r9
            goto L_0x003e
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lang.RecordConstructor.<init>(gnu.bytecode.ClassType, java.lang.Object):void");
    }

    public int numArgs() {
        int nargs = this.fields.length;
        return (nargs << 12) | nargs;
    }

    public String getName() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(this.type.getName()).append(" constructor").toString();
    }

    public Object applyN(Object[] objArr) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        StringBuilder sb;
        Throwable th4;
        Object[] args = objArr;
        try {
            Object obj = this.type.getReflectClass().newInstance();
            if (args.length != this.fields.length) {
                Throwable th5 = th4;
                new WrongArguments(this, args.length);
                throw th5;
            }
            int i = 0;
            while (i < args.length) {
                Field fld = this.fields[i];
                try {
                    fld.getReflectField().set(obj, args[i]);
                    i++;
                } catch (Exception e) {
                    Exception ex = e;
                    Throwable th6 = th3;
                    new StringBuilder();
                    new WrappedException(sb.append("illegal access for field ").append(fld.getName()).toString(), ex);
                    throw th6;
                }
            }
            return obj;
        } catch (InstantiationException e2) {
            InstantiationException ex2 = e2;
            Throwable th7 = th2;
            new GenericError(ex2.toString());
            throw th7;
        } catch (IllegalAccessException e3) {
            IllegalAccessException ex3 = e3;
            Throwable th8 = th;
            new GenericError(ex3.toString());
            throw th8;
        }
    }
}
