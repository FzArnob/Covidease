package gnu.kawa.lispexpr;

import gnu.bytecode.ClassType;
import gnu.kawa.functions.GetNamedPart;
import gnu.mapping.Namespace;
import gnu.mapping.WrappedException;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class ClassNamespace extends Namespace implements Externalizable {
    ClassType ctype;

    public ClassType getClassType() {
        return this.ctype;
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v13, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: gnu.kawa.lispexpr.ClassNamespace} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static gnu.kawa.lispexpr.ClassNamespace getInstance(java.lang.String r10, gnu.bytecode.ClassType r11) {
        /*
            r0 = r10
            r1 = r11
            java.util.Hashtable r6 = nsTable
            r9 = r6
            r6 = r9
            r7 = r9
            r2 = r7
            monitor-enter(r6)
            java.util.Hashtable r6 = nsTable     // Catch:{ all -> 0x0034 }
            r7 = r0
            java.lang.Object r6 = r6.get(r7)     // Catch:{ all -> 0x0034 }
            r3 = r6
            r6 = r3
            boolean r6 = r6 instanceof gnu.kawa.lispexpr.ClassNamespace     // Catch:{ all -> 0x0034 }
            if (r6 == 0) goto L_0x001d
            r6 = r3
            gnu.kawa.lispexpr.ClassNamespace r6 = (gnu.kawa.lispexpr.ClassNamespace) r6     // Catch:{ all -> 0x0034 }
            r7 = r2
            monitor-exit(r7)     // Catch:{ all -> 0x0034 }
            r0 = r6
        L_0x001c:
            return r0
        L_0x001d:
            gnu.kawa.lispexpr.ClassNamespace r6 = new gnu.kawa.lispexpr.ClassNamespace     // Catch:{ all -> 0x0034 }
            r9 = r6
            r6 = r9
            r7 = r9
            r8 = r1
            r7.<init>(r8)     // Catch:{ all -> 0x0034 }
            r4 = r6
            java.util.Hashtable r6 = nsTable     // Catch:{ all -> 0x0034 }
            r7 = r0
            r8 = r4
            java.lang.Object r6 = r6.put(r7, r8)     // Catch:{ all -> 0x0034 }
            r6 = r4
            r7 = r2
            monitor-exit(r7)     // Catch:{ all -> 0x0034 }
            r0 = r6
            goto L_0x001c
        L_0x0034:
            r6 = move-exception
            r5 = r6
            r6 = r2
            monitor-exit(r6)     // Catch:{ all -> 0x0034 }
            r6 = r5
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.ClassNamespace.getInstance(java.lang.String, gnu.bytecode.ClassType):gnu.kawa.lispexpr.ClassNamespace");
    }

    public ClassNamespace() {
    }

    public ClassNamespace(ClassType classType) {
        StringBuilder sb;
        ClassType ctype2 = classType;
        new StringBuilder();
        setName(sb.append("class:").append(ctype2.getName()).toString());
        this.ctype = ctype2;
    }

    public Object get(String name) {
        try {
            return GetNamedPart.getTypePart(this.ctype, name);
        } catch (Throwable th) {
            throw WrappedException.wrapIfNeeded(th);
        }
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.ctype);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        StringBuilder sb;
        this.ctype = (ClassType) in.readObject();
        new StringBuilder();
        setName(sb.append("class:").append(this.ctype.getName()).toString());
    }

    public Object readResolve() throws ObjectStreamException {
        String name = getName();
        if (name != null) {
            Namespace ns = (Namespace) nsTable.get(name);
            if (ns instanceof ClassNamespace) {
                return ns;
            }
            Object put = nsTable.put(name, this);
        }
        return this;
    }
}
