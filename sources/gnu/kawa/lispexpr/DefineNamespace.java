package gnu.kawa.lispexpr;

import android.support.p000v4.media.session.PlaybackStateCompat;
import gnu.bytecode.ClassType;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.kawa.xml.XmlNamespace;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class DefineNamespace extends Syntax {
    public static final String XML_NAMESPACE_MAGIC = "&xml&";
    public static final DefineNamespace define_namespace;
    public static final DefineNamespace define_private_namespace;
    public static final DefineNamespace define_xml_namespace;
    private boolean makePrivate;
    private boolean makeXML;

    public DefineNamespace() {
    }

    static {
        DefineNamespace defineNamespace;
        DefineNamespace defineNamespace2;
        DefineNamespace defineNamespace3;
        new DefineNamespace();
        define_namespace = defineNamespace;
        new DefineNamespace();
        define_private_namespace = defineNamespace2;
        new DefineNamespace();
        define_xml_namespace = defineNamespace3;
        define_namespace.setName("define-namespace");
        define_private_namespace.setName("define-private-namespace");
        define_private_namespace.makePrivate = true;
        define_xml_namespace.setName("define-xml-namespace");
        define_xml_namespace.makeXML = true;
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeExp, Translator translator) {
        Expression value;
        Namespace namespace;
        Expression expression;
        Pair st = pair;
        Vector forms = vector;
        ScopeExp defs = scopeExp;
        Translator tr = translator;
        if (st.getCdr() instanceof Pair) {
            Pair pair2 = (Pair) st.getCdr();
            Pair p1 = pair2;
            if ((pair2.getCar() instanceof Symbol) && (p1.getCdr() instanceof Pair)) {
                Pair pair3 = (Pair) p1.getCdr();
                Pair p2 = pair3;
                if (pair3.getCdr() == LList.Empty) {
                    Symbol name = (Symbol) p1.getCar();
                    Declaration decl = defs.getDefine(name, 'w', tr);
                    tr.push(decl);
                    decl.setFlag(2375680);
                    if (this.makePrivate) {
                        decl.setFlag(16777216);
                        decl.setPrivate(true);
                    } else if (defs instanceof ModuleExp) {
                        decl.setCanRead(true);
                    }
                    Translator.setLine(decl, (Object) p1);
                    if (p2.getCar() instanceof CharSequence) {
                        String literal = p2.getCar().toString();
                        if (literal.startsWith("class:")) {
                            namespace = ClassNamespace.getInstance(literal, ClassType.make(literal.substring(6)));
                            decl.setType(ClassType.make("gnu.kawa.lispexpr.ClassNamespace"));
                        } else if (this.makeXML) {
                            namespace = XmlNamespace.getInstance(name.getName(), literal);
                            decl.setType(ClassType.make("gnu.kawa.xml.XmlNamespace"));
                        } else {
                            namespace = Namespace.valueOf(literal);
                            decl.setType(ClassType.make("gnu.mapping.Namespace"));
                        }
                        new QuoteExp(namespace);
                        value = expression;
                        decl.setFlag(PlaybackStateCompat.ACTION_PLAY_FROM_URI);
                    } else {
                        value = tr.rewrite_car(p2, false);
                    }
                    decl.noteValue(value);
                    forms.addElement(SetExp.makeDefinition(decl, value));
                    return true;
                }
            }
        }
        tr.error('e', "invalid syntax for define-namespace");
        return false;
    }

    public Expression rewriteForm(Pair pair, Translator tr) {
        Pair pair2 = pair;
        return tr.syntaxError("define-namespace is only allowed in a <body>");
    }
}
