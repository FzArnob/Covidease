package gnu.kawa.xml;

import gnu.mapping.Namespace;
import gnu.xml.NamespaceBinding;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.Hashtable;

public class XmlNamespace extends Namespace implements Externalizable {
    public static final XmlNamespace HTML = valueOf("http://www.w3.org/1999/xhtml", "");
    public static final NamespaceBinding HTML_BINDINGS;
    public static final String XHTML_NAMESPACE = "http://www.w3.org/1999/xhtml";

    static {
        NamespaceBinding namespaceBinding;
        new NamespaceBinding((String) null, "http://www.w3.org/1999/xhtml", NamespaceBinding.predefinedXML);
        HTML_BINDINGS = namespaceBinding;
    }

    /* JADX INFO: finally extract failed */
    public static XmlNamespace getInstance(String str, String str2) {
        StringBuilder sb;
        XmlNamespace xmlNamespace;
        String prefix = str;
        String uri = str2;
        new StringBuilder();
        String xname = sb.append(prefix).append(" [xml] -> ").append(uri).toString();
        Hashtable hashtable = nsTable;
        Hashtable hashtable2 = hashtable;
        synchronized (hashtable) {
            try {
                Object old = nsTable.get(xname);
                if (old instanceof XmlNamespace) {
                    XmlNamespace xmlNamespace2 = (XmlNamespace) old;
                    return xmlNamespace2;
                }
                new XmlNamespace();
                XmlNamespace ns = xmlNamespace;
                ns.setName(uri.intern());
                ns.prefix = prefix.intern();
                Object put = nsTable.put(xname, ns);
                XmlNamespace xmlNamespace3 = ns;
                return xmlNamespace3;
            } catch (Throwable th) {
                Throwable th2 = th;
                Hashtable hashtable3 = hashtable2;
                throw th2;
            }
        }
    }

    public static XmlNamespace valueOf(String name, String prefix) {
        return getInstance(prefix, name);
    }

    public XmlNamespace() {
    }

    public Object get(String name) {
        ElementType type = ElementType.make(getSymbol(name));
        if (this == HTML) {
            type.setNamespaceNodes(HTML_BINDINGS);
        }
        return type;
    }

    public boolean isConstant(String str) {
        String str2 = str;
        return true;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        out.writeObject(getName());
        out.writeObject(this.prefix);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        setName((String) in.readObject());
        this.prefix = (String) in.readObject();
    }

    public Object readResolve() throws ObjectStreamException {
        StringBuilder sb;
        new StringBuilder();
        String xname = sb.append(this.prefix).append(" -> ").append(getName()).toString();
        Namespace ns = (Namespace) nsTable.get(xname);
        if (ns instanceof XmlNamespace) {
            return ns;
        }
        Object put = nsTable.put(xname, this);
        return this;
    }
}
