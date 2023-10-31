package gnu.xml;

import gnu.mapping.Symbol;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class XName extends Symbol implements Externalizable {
    NamespaceBinding namespaceNodes;

    public XName() {
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public XName(gnu.mapping.Symbol r7, gnu.xml.NamespaceBinding r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r1
            gnu.mapping.Namespace r4 = r4.getNamespace()
            r5 = r1
            java.lang.String r5 = r5.getName()
            r3.<init>(r4, r5)
            r3 = r0
            r4 = r2
            r3.namespaceNodes = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xml.XName.<init>(gnu.mapping.Symbol, gnu.xml.NamespaceBinding):void");
    }

    public final NamespaceBinding getNamespaceNodes() {
        return this.namespaceNodes;
    }

    public final void setNamespaceNodes(NamespaceBinding nodes) {
        NamespaceBinding namespaceBinding = nodes;
        this.namespaceNodes = namespaceBinding;
    }

    /* access modifiers changed from: package-private */
    public String lookupNamespaceURI(String str) {
        String prefix = str;
        NamespaceBinding namespaceBinding = this.namespaceNodes;
        while (true) {
            NamespaceBinding ns = namespaceBinding;
            if (ns == null) {
                return null;
            }
            if (prefix == ns.prefix) {
                return ns.uri;
            }
            namespaceBinding = ns.next;
        }
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        super.writeExternal(out);
        out.writeObject(this.namespaceNodes);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        super.readExternal(in);
        this.namespaceNodes = (NamespaceBinding) in.readObject();
    }

    public static boolean isNameStart(int i) {
        int ch = i;
        return Character.isUnicodeIdentifierStart(ch) || ch == 95;
    }

    public static boolean isNamePart(int i) {
        int ch = i;
        return Character.isUnicodeIdentifierPart(ch) || ch == 45 || ch == 46;
    }

    public static boolean isNmToken(String value) {
        return checkName(value) >= 0;
    }

    public static boolean isName(String value) {
        return checkName(value) > 0;
    }

    public static boolean isNCName(String value) {
        return checkName(value) > 1;
    }

    public static int checkName(String str) {
        String value = str;
        int len = value.length();
        if (len == 0) {
            return -1;
        }
        int result = 2;
        int i = 0;
        while (i < len) {
            boolean first = i == 0;
            int i2 = i;
            i++;
            int ch = value.charAt(i2);
            if (ch >= 55296 && ch < 56320 && i < len) {
                int i3 = i;
                i++;
                ch = ((ch - 55296) * 1024) + (value.charAt(i3) - 56320) + 65536;
            }
            if (ch == 58) {
                if (result == 2) {
                    result = 1;
                }
            } else if (!isNamePart(ch)) {
                return -1;
            } else {
                if (first && !isNameStart(ch)) {
                    result = 0;
                }
            }
        }
        return result;
    }
}
