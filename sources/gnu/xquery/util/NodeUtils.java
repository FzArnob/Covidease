package gnu.xquery.util;

import gnu.bytecode.ClassType;
import gnu.kawa.reflect.ClassMethods;
import gnu.kawa.xml.Document;
import gnu.kawa.xml.KDocument;
import gnu.kawa.xml.KElement;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.Nodes;
import gnu.kawa.xml.SortedNodes;
import gnu.kawa.xml.UntypedAtomic;
import gnu.lists.Consumer;
import gnu.lists.PositionConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.text.Path;
import gnu.xml.NamespaceBinding;
import gnu.xml.NodeTree;
import gnu.xml.TextUtils;
import gnu.xml.XName;
import gnu.xquery.lang.XQuery;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Stack;

public class NodeUtils {
    static String collectionNamespace = "http://gnu.org/kawa/cached-collections";
    public static final Symbol collectionResolverSymbol = Symbol.make(XQuery.LOCAL_NAMESPACE, "collection-resolver", "qexo");

    public NodeUtils() {
    }

    public static Object nodeName(Object obj) {
        Throwable th;
        Object node = obj;
        if (node == Values.empty || node == null) {
            return node;
        }
        if (!(node instanceof KNode)) {
            Throwable th2 = th;
            new WrongType("node-name", 1, node, "node()?");
            throw th2;
        }
        Object sym = ((KNode) node).getNodeSymbol();
        if (sym == null) {
            return Values.empty;
        }
        return sym;
    }

    public static String name(Object obj) {
        Object node = obj;
        if (node == Values.empty || node == null) {
            return "";
        }
        Object name = ((KNode) node).getNodeNameObject();
        if (name == null || name == Values.empty) {
            return "";
        }
        return name.toString();
    }

    public static String localName(Object obj) {
        Throwable th;
        Object node = obj;
        if (node == Values.empty || node == null) {
            return "";
        }
        if (!(node instanceof KNode)) {
            Throwable th2 = th;
            new WrongType("local-name", 1, node, "node()?");
            throw th2;
        }
        Object name = ((KNode) node).getNodeNameObject();
        if (name == null || name == Values.empty) {
            return "";
        }
        if (name instanceof Symbol) {
            return ((Symbol) name).getName();
        }
        return name.toString();
    }

    public static Object namespaceURI(Object obj) {
        Throwable th;
        Object node = obj;
        if (!(node == Values.empty || node == null)) {
            if (!(node instanceof KNode)) {
                Throwable th2 = th;
                new WrongType("namespace-uri", 1, node, "node()?");
                throw th2;
            }
            Object name = ((KNode) node).getNodeNameObject();
            if (name instanceof Symbol) {
                return QNameUtils.namespaceURIFromQName(name);
            }
        }
        return "";
    }

    public static void prefixesFromNodetype(XName name, Consumer consumer) {
        Consumer out = consumer;
        NamespaceBinding bindings = name.getNamespaceNodes();
        NamespaceBinding namespaceBinding = bindings;
        while (true) {
            NamespaceBinding ns = namespaceBinding;
            if (ns != null) {
                if (ns.getUri() != null) {
                    String prefix = ns.getPrefix();
                    NamespaceBinding namespaceBinding2 = bindings;
                    while (true) {
                        NamespaceBinding ns2 = namespaceBinding2;
                        if (ns2 == ns) {
                            out.writeObject(prefix == null ? "" : prefix);
                        } else if (ns2.getPrefix() == prefix) {
                            break;
                        } else {
                            namespaceBinding2 = ns2.getNext();
                        }
                    }
                }
                namespaceBinding = ns.getNext();
            } else {
                return;
            }
        }
    }

    public static void inScopePrefixes$X(Object node, CallContext callContext) {
        CallContext ctx = callContext;
        Object type = ((KElement) node).getNodeNameObject();
        if (type instanceof XName) {
            prefixesFromNodetype((XName) type, ctx.consumer);
        } else {
            ctx.consumer.writeObject("xml");
        }
    }

    public static void data$X(Object obj, CallContext ctx) {
        Object arg = obj;
        Consumer out = ctx.consumer;
        if (arg instanceof Values) {
            Values vals = (Values) arg;
            int ipos = vals.startPos();
            while (true) {
                int nextPos = vals.nextPos(ipos);
                ipos = nextPos;
                if (nextPos != 0) {
                    out.writeObject(KNode.atomicValue(vals.getPosPrevious(ipos)));
                } else {
                    return;
                }
            }
        } else {
            out.writeObject(KNode.atomicValue(arg));
        }
    }

    public static Object root(Object obj) {
        Throwable th;
        Object arg = obj;
        if (arg == null || arg == Values.empty) {
            return arg;
        }
        if (!(arg instanceof KNode)) {
            Throwable th2 = th;
            new WrongType("root", 1, arg, "node()?");
            throw th2;
        }
        KNode node = (KNode) arg;
        return Nodes.root((NodeTree) node.sequence, node.getPos());
    }

    public static KDocument rootDocument(Object obj) {
        Throwable th;
        Throwable th2;
        Object arg = obj;
        if (!(arg instanceof KNode)) {
            Throwable th3 = th2;
            new WrongType("root-document", 1, arg, "node()?");
            throw th3;
        }
        KNode node = (KNode) arg;
        KNode node2 = Nodes.root((NodeTree) node.sequence, node.getPos());
        if (node2 instanceof KDocument) {
            return (KDocument) node2;
        }
        Throwable th4 = th;
        new WrongType("root-document", 1, arg, "document()");
        throw th4;
    }

    public static String getLang(KNode kNode) {
        KNode node = kNode;
        NodeTree seq = (NodeTree) node.sequence;
        int attr = seq.ancestorAttribute(node.ipos, NamespaceBinding.XML_NAMESPACE, "lang");
        if (attr == 0) {
            return null;
        }
        return KNode.getNodeValue(seq, attr);
    }

    public static boolean lang(Object obj, Object obj2) {
        String teststr;
        Object testlang = obj;
        Object node = obj2;
        if (testlang == null || testlang == Values.empty) {
            teststr = "";
        } else {
            teststr = TextUtils.stringValue(testlang);
        }
        String lang = getLang((KNode) node);
        if (lang == null) {
            return false;
        }
        int langlen = lang.length();
        int testlen = teststr.length();
        if (langlen > testlen && lang.charAt(testlen) == '-') {
            lang = lang.substring(0, testlen);
        }
        return lang.equalsIgnoreCase(teststr);
    }

    public static Object documentUri(Object obj) {
        Throwable th;
        Object arg = obj;
        if (arg == null || arg == Values.empty) {
            return arg;
        }
        if (!(arg instanceof KNode)) {
            Throwable th2 = th;
            new WrongType("xs:document-uri", 1, arg, "node()?");
            throw th2;
        }
        KNode node = (KNode) arg;
        Object uri = ((NodeTree) node.sequence).documentUriOfPos(node.ipos);
        return uri == null ? Values.empty : uri;
    }

    public static Object nilled(Object obj) {
        Throwable th;
        Object arg = obj;
        if (arg == null || arg == Values.empty) {
            return arg;
        }
        if (!(arg instanceof KNode)) {
            Throwable th2 = th;
            new WrongType("nilled", 1, arg, "node()?");
            throw th2;
        } else if (!(arg instanceof KElement)) {
            return Values.empty;
        } else {
            return Boolean.FALSE;
        }
    }

    public static Object baseUri(Object obj) {
        Throwable th;
        Object arg = obj;
        if (arg == null || arg == Values.empty) {
            return arg;
        }
        if (!(arg instanceof KNode)) {
            Throwable th2 = th;
            new WrongType("base-uri", 1, arg, "node()?");
            throw th2;
        }
        Object uri = ((KNode) arg).baseURI();
        if (uri == null) {
            return Values.empty;
        }
        return uri;
    }

    static Object getIDs(Object obj, Object obj2) {
        Stack stack;
        Stack st;
        Object arg = obj;
        String collector = obj2;
        if (arg instanceof KNode) {
            arg = KNode.atomicValue(arg);
        }
        if (arg instanceof Values) {
            Object[] ar = ((Values) arg).getValues();
            int i = ar.length;
            while (true) {
                i--;
                if (i < 0) {
                    break;
                }
                collector = getIDs(ar[i], collector);
            }
        } else {
            String str = StringUtils.coerceToString(arg, "fn:id", 1, "");
            int len = str.length();
            int i2 = 0;
            while (i2 < len) {
                int i3 = i2;
                i2++;
                char ch = str.charAt(i3);
                if (!Character.isWhitespace(ch)) {
                    int start = XName.isNameStart(ch) ? i2 - 1 : len;
                    while (i2 < len) {
                        char ch2 = str.charAt(i2);
                        if (Character.isWhitespace(ch2)) {
                            break;
                        }
                        i2++;
                        if (start < len && !XName.isNamePart(ch2)) {
                            start = len;
                        }
                    }
                    if (start < len) {
                        String ref = str.substring(start, i2);
                        if (collector == null) {
                            collector = ref;
                        } else {
                            if (collector instanceof Stack) {
                                st = (Stack) collector;
                            } else {
                                new Stack();
                                st = stack;
                                Object push = st.push(collector);
                                collector = st;
                            }
                            Object push2 = st.push(ref);
                        }
                    }
                    i2++;
                }
            }
        }
        return collector;
    }

    public static void id$X(Object arg1, Object arg2, CallContext ctx) {
        SortedNodes sortedNodes;
        KNode node = (KNode) arg2;
        NodeTree ntree = (NodeTree) node.sequence;
        KDocument kDocument = (KDocument) Nodes.root(ntree, node.ipos);
        Consumer out = ctx.consumer;
        Object idrefs = getIDs(arg1, (Object) null);
        if (idrefs != null) {
            ntree.makeIDtableIfNeeded();
            if ((out instanceof PositionConsumer) && ((idrefs instanceof String) || (out instanceof SortedNodes))) {
                idScan(idrefs, ntree, (PositionConsumer) out);
            } else if (idrefs instanceof String) {
                int pos = ntree.lookupID((String) idrefs);
                if (pos != -1) {
                    out.writeObject(KNode.make(ntree, pos));
                }
            } else {
                new SortedNodes();
                SortedNodes nodes = sortedNodes;
                idScan(idrefs, ntree, nodes);
                Values.writeValues(nodes, out);
            }
        }
    }

    private static void idScan(Object obj, NodeTree nodeTree, PositionConsumer positionConsumer) {
        Object ids = obj;
        NodeTree seq = nodeTree;
        PositionConsumer out = positionConsumer;
        if (ids instanceof String) {
            int pos = seq.lookupID((String) ids);
            if (pos != -1) {
                out.writePosition(seq, pos);
            }
        } else if (ids instanceof Stack) {
            Stack st = (Stack) ids;
            int n = st.size();
            for (int i = 0; i < n; i++) {
                idScan(st.elementAt(i), seq, out);
            }
        }
    }

    public static Object idref(Object obj, Object arg2) {
        Object obj2 = obj;
        KNode node = (KNode) arg2;
        KDocument kDocument = (KDocument) Nodes.root((NodeTree) node.sequence, node.getPos());
        return Values.empty;
    }

    public static void setSavedCollection(Object obj, Object obj2, Environment environment) {
        Object uri = obj;
        Object value = obj2;
        Environment env = environment;
        if (uri == null) {
            uri = "#default";
        }
        env.put(Symbol.make(collectionNamespace, uri.toString()), (Object) null, value);
    }

    public static void setSavedCollection(Object uri, Object value) {
        setSavedCollection(uri, value, Environment.getCurrent());
    }

    public static Object getSavedCollection(Object obj, Environment environment) {
        Throwable th;
        StringBuilder sb;
        Object uri = obj;
        Environment env = environment;
        if (uri == null) {
            uri = "#default";
        }
        Object coll = env.get(Symbol.make(collectionNamespace, uri.toString()), (Object) null, (Object) null);
        if (coll != null) {
            return coll;
        }
        Throwable th2 = th;
        new StringBuilder();
        new RuntimeException(sb.append("collection '").append(uri).append("' not found").toString());
        throw th2;
    }

    public static Object getSavedCollection(Object uri) {
        return getSavedCollection(uri, Environment.getCurrent());
    }

    public static Object collection(Object uri, Object base) throws Throwable {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        StringBuilder sb3;
        Throwable th4;
        StringBuilder sb4;
        Object uri2 = resolve(uri, base, "collection");
        Environment env = Environment.getCurrent();
        Symbol rsym = collectionResolverSymbol;
        Object rvalue = env.get(rsym, (Object) null, (Object) null);
        if (rvalue == null) {
            rvalue = env.get(Symbol.makeWithUnknownNamespace(rsym.getLocalName(), rsym.getPrefix()), (Object) null, (Object) null);
        }
        if (rvalue == null) {
            return getSavedCollection(uri2);
        }
        if ((rvalue instanceof String) || (rvalue instanceof UntypedAtomic)) {
            String obj = rvalue.toString();
            String str = obj;
            int indexOf = obj.indexOf(58);
            int colon = indexOf;
            if (indexOf > 0) {
                String cname = str.substring(0, colon);
                String mname = str.substring(colon + 1);
                try {
                    rvalue = ClassMethods.apply((ClassType) ClassType.make(Class.forName(cname)), mname, 0, XQuery.instance);
                    if (rvalue == null) {
                        Throwable th5 = th4;
                        new StringBuilder();
                        new RuntimeException(sb4.append("invalid collection-resolver: no method ").append(mname).append(" in ").append(cname).toString());
                        throw th5;
                    }
                } catch (ClassNotFoundException e) {
                    ClassNotFoundException classNotFoundException = e;
                    Throwable th6 = th3;
                    new StringBuilder();
                    new RuntimeException(sb3.append("invalid collection-resolver: class ").append(cname).append(" not found").toString());
                    throw th6;
                } catch (Throwable th7) {
                    Throwable ex = th7;
                    Throwable th8 = th2;
                    new StringBuilder();
                    new RuntimeException(sb2.append("invalid collection-resolver: ").append(ex).toString());
                    throw th8;
                }
            }
        }
        if (rvalue instanceof Procedure) {
            return ((Procedure) rvalue).apply1(uri2);
        }
        Throwable th9 = th;
        new StringBuilder();
        new RuntimeException(sb.append("invalid collection-resolver: ").append(rvalue).toString());
        throw th9;
    }

    static Object resolve(Object obj, Object obj2, String str) throws Throwable {
        Object uri = obj;
        Object obj3 = obj2;
        String fname = str;
        if (!(uri instanceof File) && !(uri instanceof Path) && !(uri instanceof URI) && !(uri instanceof URL)) {
            uri = StringUtils.coerceToString(uri, fname, 1, (String) null);
        }
        if (uri == Values.empty || uri == null) {
            return null;
        }
        return Path.currentPath().resolve(Path.valueOf(uri));
    }

    public static Object docCached(Object uri, Object base) throws Throwable {
        Object uri2 = resolve(uri, base, "doc");
        if (uri2 == null) {
            return Values.empty;
        }
        return Document.parseCached(uri2);
    }

    public static boolean availableCached(Object uri, Object base) throws Throwable {
        Object uri2 = resolve(uri, base, "doc-available");
        if (uri2 == null) {
            return false;
        }
        try {
            KDocument parseCached = Document.parseCached(uri2);
            return true;
        } catch (Throwable th) {
            Throwable th2 = th;
            return false;
        }
    }
}
