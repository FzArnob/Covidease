package gnu.xquery.util;

import gnu.kawa.xml.KElement;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.kawa.xml.XStringType;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.text.Path;
import gnu.text.URIPath;
import gnu.xml.NamespaceBinding;
import gnu.xml.TextUtils;
import gnu.xml.XName;
import java.net.URISyntaxException;

public class QNameUtils {
    public QNameUtils() {
    }

    public static Object resolveQNameUsingElement(Object qname, KElement kElement) {
        Throwable th;
        String prefix;
        String localPart;
        Throwable th2;
        StringBuilder sb;
        Throwable th3;
        StringBuilder sb2;
        KElement node = kElement;
        Object qname2 = KNode.atomicValue(qname);
        if (qname2 == Values.empty || qname2 == null) {
            return qname2;
        }
        if ((qname2 instanceof Values) || (!(qname2 instanceof String) && !(qname2 instanceof UntypedAtomic))) {
            Throwable th4 = th;
            new RuntimeException("bad argument to QName");
            throw th4;
        }
        String name = TextUtils.replaceWhitespace(qname2.toString(), true);
        int colon = name.indexOf(58);
        if (colon < 0) {
            prefix = null;
            localPart = name;
        } else {
            prefix = name.substring(0, colon).intern();
            localPart = name.substring(colon + 1);
        }
        String uri = node.lookupNamespaceURI(prefix);
        if (uri == null) {
            if (prefix == null) {
                uri = "";
            } else {
                Throwable th5 = th3;
                new StringBuilder();
                new RuntimeException(sb2.append("unknown namespace for '").append(name).append("'").toString());
                throw th5;
            }
        }
        if (!validNCName(localPart) || (prefix != null && !validNCName(prefix))) {
            Throwable th6 = th2;
            new StringBuilder();
            new RuntimeException(sb.append("invalid QName syntax '").append(name).append("'").toString());
            throw th6;
        }
        return Symbol.make(uri, localPart, prefix == null ? "" : prefix);
    }

    public static Object resolveQName(Object qname, NamespaceBinding namespaceBinding, NamespaceBinding namespaceBinding2) {
        Throwable th;
        String prefix;
        String localPart;
        Throwable th2;
        StringBuilder sb;
        NamespaceBinding constructorNamespaces = namespaceBinding;
        NamespaceBinding prologNamespaces = namespaceBinding2;
        Object qname2 = KNode.atomicValue(qname);
        if (qname2 instanceof Symbol) {
            return qname2;
        }
        if ((qname2 instanceof Values) || (!(qname2 instanceof String) && !(qname2 instanceof UntypedAtomic))) {
            Throwable th3 = th;
            new RuntimeException("bad argument to QName");
            throw th3;
        }
        String name = TextUtils.replaceWhitespace(qname2.toString(), true);
        int colon = name.indexOf(58);
        if (colon < 0) {
            localPart = name;
            prefix = null;
        } else {
            prefix = name.substring(0, colon).intern();
            localPart = name.substring(colon + 1);
        }
        if (!validNCName(localPart) || (prefix != null && !validNCName(prefix))) {
            Throwable th4 = th2;
            new StringBuilder();
            new RuntimeException(sb.append("invalid QName syntax '").append(name).append("'").toString());
            throw th4;
        }
        return Symbol.make(resolvePrefix(prefix, constructorNamespaces, prologNamespaces), localPart, prefix == null ? "" : prefix);
    }

    public static String lookupPrefix(String str, NamespaceBinding constructorNamespaces, NamespaceBinding namespaceBinding) {
        String uri;
        String prefix = str;
        NamespaceBinding prologNamespaces = namespaceBinding;
        NamespaceBinding namespaceBinding2 = constructorNamespaces;
        while (true) {
            NamespaceBinding ns = namespaceBinding2;
            if (ns == null) {
                uri = prologNamespaces.resolve(prefix);
                break;
            } else if (ns.getPrefix() == prefix) {
                uri = ns.getUri();
                break;
            } else {
                namespaceBinding2 = ns.getNext();
            }
        }
        if (uri == null && prefix == null) {
            uri = "";
        }
        return uri;
    }

    public static String resolvePrefix(String str, NamespaceBinding constructorNamespaces, NamespaceBinding prologNamespaces) {
        Throwable th;
        StringBuilder sb;
        String prefix = str;
        String uri = lookupPrefix(prefix, constructorNamespaces, prologNamespaces);
        if (uri != null) {
            return uri;
        }
        Throwable th2 = th;
        new StringBuilder();
        new RuntimeException(sb.append("unknown namespace prefix '").append(prefix).append("'").toString());
        throw th2;
    }

    public static boolean validNCName(String name) {
        return XName.isName(name);
    }

    public static Symbol makeQName(Object obj, String str) {
        String localPart;
        String prefix;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Object paramURI = obj;
        String paramQName = str;
        if (paramURI == null || paramURI == Values.empty) {
            paramURI = "";
        }
        int colon = paramQName.indexOf(58);
        String namespaceURI = (String) paramURI;
        if (colon < 0) {
            localPart = paramQName;
            prefix = "";
        } else {
            localPart = paramQName.substring(colon + 1);
            prefix = paramQName.substring(0, colon).intern();
        }
        if (!validNCName(localPart) || (colon >= 0 && !validNCName(prefix))) {
            Throwable th3 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("invalid QName syntax '").append(paramQName).append("'").toString());
            throw th3;
        } else if (colon < 0 || namespaceURI.length() != 0) {
            return Symbol.make(namespaceURI, localPart, prefix);
        } else {
            Throwable th4 = th2;
            new StringBuilder();
            new IllegalArgumentException(sb2.append("empty uri for '").append(paramQName).append("'").toString());
            throw th4;
        }
    }

    public static Object localNameFromQName(Object obj) {
        Throwable th;
        Object name = obj;
        if (name == Values.empty || name == null) {
            return name;
        }
        if (name instanceof Symbol) {
            return XStringType.makeNCName(((Symbol) name).getName());
        }
        Throwable th2 = th;
        new WrongType("local-name-from-QName", 1, name, "xs:QName");
        throw th2;
    }

    public static Object prefixFromQName(Object obj) {
        Throwable th;
        Object name = obj;
        if (name == Values.empty || name == null) {
            return name;
        }
        if (name instanceof Symbol) {
            String prefix = ((Symbol) name).getPrefix();
            if (prefix == null || prefix.length() == 0) {
                return Values.empty;
            }
            return XStringType.makeNCName(prefix);
        }
        Throwable th2 = th;
        new WrongType("prefix-from-QName", 1, name, "xs:QName");
        throw th2;
    }

    public static Object namespaceURIFromQName(Object obj) {
        Throwable th;
        Object name = obj;
        if (name == Values.empty || name == null) {
            return name;
        }
        try {
            return URIPath.makeURI(((Symbol) name).getNamespaceURI());
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType("namespace-uri", 1, name, "xs:QName");
            throw th2;
        }
    }

    public static Object namespaceURIForPrefix(Object obj, Object obj2) {
        String str;
        Throwable th;
        Throwable th2;
        Object prefix = obj;
        Object element = obj2;
        KNode el = KNode.coerce(element);
        if (el == null) {
            Throwable th3 = th2;
            new WrongType("namespace-uri-for-prefix", 2, element, "node()");
            throw th3;
        }
        if (prefix == null || prefix == Values.empty) {
            str = null;
        } else if ((prefix instanceof String) || (prefix instanceof UntypedAtomic)) {
            str = prefix.toString().intern();
            if (str == "") {
                str = null;
            }
        } else {
            Throwable th4 = th;
            new WrongType("namespace-uri-for-prefix", 1, element, "xs:string");
            throw th4;
        }
        Object uri = el.lookupNamespaceURI(str);
        if (uri == null) {
            return Values.empty;
        }
        return uri;
    }

    public static Object resolveURI(Object obj, Object obj2) throws URISyntaxException {
        Object relative = obj;
        Object base = obj2;
        if (relative instanceof KNode) {
            relative = KNode.atomicValue(relative);
        }
        if (base instanceof KNode) {
            base = KNode.atomicValue(base);
        }
        if (relative == Values.empty || relative == null) {
            return relative;
        }
        if (relative instanceof UntypedAtomic) {
            relative = relative.toString();
        }
        if (base instanceof UntypedAtomic) {
            base = base.toString();
        }
        Path baseP = base instanceof Path ? (Path) base : URIPath.makeURI(base);
        if (relative instanceof Path) {
            return baseP.resolve((Path) relative);
        }
        return baseP.resolve(relative.toString());
    }
}
