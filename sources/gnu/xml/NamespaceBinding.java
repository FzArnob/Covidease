package gnu.xml;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public final class NamespaceBinding implements Externalizable {
    public static final String XML_NAMESPACE = "http://www.w3.org/XML/1998/namespace";
    public static final NamespaceBinding predefinedXML;
    int depth;
    NamespaceBinding next;
    String prefix;
    String uri;

    public final String getPrefix() {
        return this.prefix;
    }

    public final void setPrefix(String prefix2) {
        String str = prefix2;
        this.prefix = str;
    }

    public final String getUri() {
        return this.uri;
    }

    public final void setUri(String uri2) {
        String str = uri2;
        this.uri = str;
    }

    public final NamespaceBinding getNext() {
        return this.next;
    }

    public final void setNext(NamespaceBinding namespaceBinding) {
        NamespaceBinding next2 = namespaceBinding;
        this.next = next2;
        this.depth = next2 == null ? 0 : next2.depth + 1;
    }

    public static final NamespaceBinding nconc(NamespaceBinding namespaceBinding, NamespaceBinding namespaceBinding2) {
        NamespaceBinding list1 = namespaceBinding;
        NamespaceBinding list2 = namespaceBinding2;
        if (list1 == null) {
            return list2;
        }
        list1.setNext(nconc(list1.next, list2));
        return list1;
    }

    public NamespaceBinding(String prefix2, String uri2, NamespaceBinding next2) {
        this.prefix = prefix2;
        this.uri = uri2;
        setNext(next2);
    }

    static {
        NamespaceBinding namespaceBinding;
        new NamespaceBinding("xml", XML_NAMESPACE, (NamespaceBinding) null);
        predefinedXML = namespaceBinding;
    }

    public String resolve(String str) {
        String prefix2 = str;
        NamespaceBinding namespaceBinding = this;
        while (true) {
            NamespaceBinding ns = namespaceBinding;
            if (ns == null) {
                return null;
            }
            if (ns.prefix == prefix2) {
                return ns.uri;
            }
            namespaceBinding = ns.next;
        }
    }

    public String resolve(String str, NamespaceBinding namespaceBinding) {
        String prefix2 = str;
        NamespaceBinding fencePost = namespaceBinding;
        NamespaceBinding namespaceBinding2 = this;
        while (true) {
            NamespaceBinding ns = namespaceBinding2;
            if (ns == fencePost) {
                return null;
            }
            if (ns.prefix == prefix2) {
                return ns.uri;
            }
            namespaceBinding2 = ns.next;
        }
    }

    public static NamespaceBinding commonAncestor(NamespaceBinding namespaceBinding, NamespaceBinding namespaceBinding2) {
        NamespaceBinding ns1 = namespaceBinding;
        NamespaceBinding ns2 = namespaceBinding2;
        if (ns1.depth > ns2.depth) {
            NamespaceBinding tmp = ns1;
            ns1 = ns2;
            ns2 = tmp;
        }
        while (ns2.depth > ns1.depth) {
            ns2 = ns2.next;
        }
        while (ns1 != ns2) {
            ns1 = ns1.next;
            ns2 = ns2.next;
        }
        return ns1;
    }

    public NamespaceBinding reversePrefix(NamespaceBinding namespaceBinding) {
        NamespaceBinding fencePost = namespaceBinding;
        NamespaceBinding prev = fencePost;
        NamespaceBinding t = this;
        int depth2 = fencePost == null ? -1 : fencePost.depth;
        while (t != fencePost) {
            NamespaceBinding next2 = t.next;
            t.next = prev;
            prev = t;
            depth2++;
            t.depth = depth2;
            t = next2;
        }
        return prev;
    }

    public int count(NamespaceBinding namespaceBinding) {
        NamespaceBinding fencePost = namespaceBinding;
        int count = 0;
        NamespaceBinding namespaceBinding2 = this;
        while (true) {
            NamespaceBinding ns = namespaceBinding2;
            if (ns == fencePost) {
                return count;
            }
            count++;
            namespaceBinding2 = ns.next;
        }
    }

    public static NamespaceBinding maybeAdd(String str, String str2, NamespaceBinding namespaceBinding) {
        NamespaceBinding namespaceBinding2;
        String prefix2 = str;
        String uri2 = str2;
        NamespaceBinding bindings = namespaceBinding;
        if (bindings == null) {
            if (uri2 == null) {
                return bindings;
            }
            bindings = predefinedXML;
        }
        String found = bindings.resolve(prefix2);
        if (found != null ? found.equals(uri2) : uri2 == null) {
            return bindings;
        }
        new NamespaceBinding(prefix2, uri2, bindings);
        return namespaceBinding2;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("Namespace{").append(this.prefix).append("=").append(this.uri).append(", depth:").append(this.depth).append("}").toString();
    }

    public String toStringAll() {
        StringBuffer stringBuffer;
        String str;
        new StringBuffer("Namespaces{");
        StringBuffer sbuf = stringBuffer;
        NamespaceBinding namespaceBinding = this;
        while (true) {
            NamespaceBinding ns = namespaceBinding;
            if (ns != null) {
                StringBuffer append = sbuf.append(ns.prefix);
                StringBuffer append2 = sbuf.append("=\"");
                StringBuffer append3 = sbuf.append(ns.uri);
                StringBuffer stringBuffer2 = sbuf;
                if (ns == null) {
                    str = "\"";
                } else {
                    str = "\", ";
                }
                StringBuffer append4 = stringBuffer2.append(str);
                namespaceBinding = ns.next;
            } else {
                StringBuffer append5 = sbuf.append('}');
                return sbuf.toString();
            }
        }
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        out.writeUTF(this.prefix);
        out.writeUTF(this.uri);
        out.writeObject(this.next);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        this.prefix = in.readUTF();
        this.uri = in.readUTF();
        this.next = (NamespaceBinding) in.readObject();
    }
}
