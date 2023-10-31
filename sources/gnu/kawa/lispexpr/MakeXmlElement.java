package gnu.kawa.lispexpr;

import gnu.bytecode.ClassType;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.kawa.xml.MakeElement;
import gnu.kawa.xml.MakeText;
import gnu.kawa.xml.XmlNamespace;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import gnu.xml.NamespaceBinding;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class MakeXmlElement extends Syntax {
    public static final MakeXmlElement makeXml;
    static final ClassType typeNamespace = ClassType.make("gnu.mapping.Namespace");

    public MakeXmlElement() {
    }

    static {
        MakeXmlElement makeXmlElement;
        new MakeXmlElement();
        makeXml = makeXmlElement;
        makeXml.setName("$make-xml$");
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        MakeElement makeElement;
        String intern;
        StringBuilder sb;
        Namespace namespace;
        Expression expression;
        Object value;
        Pair form = pair;
        Translator tr = translator;
        Pair pair1 = (Pair) form.getCdr();
        Object namespaceList = pair1.getCar();
        Object obj = pair1.getCdr();
        boolean nsSeen = false;
        NamespaceBinding saveBindings = tr.xmlElementNamespaces;
        NamespaceBinding nsBindings = saveBindings;
        while (namespaceList instanceof Pair) {
            if (!nsSeen) {
                tr.letStart();
                nsSeen = true;
            }
            Pair namespacePair = (Pair) namespaceList;
            Pair namespaceNode = (Pair) namespacePair.getCar();
            String nsPrefix = (String) namespaceNode.getCar();
            if (nsPrefix.length() == 0) {
                intern = null;
            } else {
                intern = nsPrefix.intern();
            }
            String nsPrefix2 = intern;
            Object valueList = namespaceNode.getCdr();
            new StringBuilder();
            StringBuilder sbuf = sb;
            while (valueList instanceof Pair) {
                Pair valuePair = (Pair) valueList;
                Object valueForm = valuePair.getCar();
                if (LList.listLength(valueForm, false) == 2 && (valueForm instanceof Pair) && ((Pair) valueForm).getCar() == MakeText.makeText) {
                    value = ((Pair) ((Pair) valueForm).getCdr()).getCar();
                } else {
                    value = tr.rewrite_car(valuePair, false).valueIfConstant();
                }
                if (value == null) {
                    Object savePos = tr.pushPositionOf(valuePair);
                    tr.error('e', "namespace URI must be literal");
                    tr.popPositionOf(savePos);
                } else {
                    StringBuilder append = sbuf.append(value);
                }
                valueList = valuePair.getCdr();
            }
            String nsUri = sbuf.toString().intern();
            NamespaceBinding namespaceBinding = r27;
            NamespaceBinding namespaceBinding2 = new NamespaceBinding(nsPrefix2, nsUri == "" ? null : nsUri, nsBindings);
            nsBindings = namespaceBinding;
            if (nsPrefix2 == null) {
                namespace = Namespace.valueOf(nsUri);
                nsPrefix2 = "[default-element-namespace]";
            } else {
                namespace = XmlNamespace.getInstance(nsPrefix2, nsUri);
            }
            Symbol nsSymbol = Namespace.EmptyNamespace.getSymbol(nsPrefix2);
            new QuoteExp(namespace);
            tr.letVariable(nsSymbol, typeNamespace, expression).setFlag(2121728);
            namespaceList = namespacePair.getCdr();
        }
        new MakeElement();
        MakeElement mkElement = makeElement;
        mkElement.setNamespaceNodes(nsBindings);
        tr.xmlElementNamespaces = nsBindings;
        if (nsSeen) {
            try {
                tr.letEnter();
            } catch (Throwable th) {
                Throwable th2 = th;
                tr.xmlElementNamespaces = saveBindings;
                throw th2;
            }
        }
        Expression result = tr.rewrite(Translator.makePair(form, mkElement, obj));
        Expression letDone = nsSeen ? tr.letDone(result) : result;
        tr.xmlElementNamespaces = saveBindings;
        return letDone;
    }
}
