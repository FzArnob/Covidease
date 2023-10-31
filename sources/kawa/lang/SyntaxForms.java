package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.lists.ImmutablePair;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;

public class SyntaxForms {
    public static final boolean DEBUGGING = true;

    public SyntaxForms() {
    }

    public static Object makeForm(Object obj, TemplateScope templateScope) {
        Object datum;
        Object datum2;
        Object datum3 = obj;
        TemplateScope scope = templateScope;
        if (datum3 instanceof Pair) {
            new PairSyntaxForm((Pair) datum3, scope);
            return datum2;
        } else if (datum3 == LList.Empty) {
            return datum3;
        } else {
            new SimpleSyntaxForm(datum3, scope);
            return datum;
        }
    }

    public static Object makeWithTemplate(Object obj, Object obj2) {
        Object template = obj;
        Object form = obj2;
        if (form instanceof SyntaxForm) {
            return (SyntaxForm) form;
        }
        if (!(template instanceof SyntaxForm)) {
            return form;
        }
        Object sform = (SyntaxForm) template;
        if (form == sform.getDatum()) {
            return sform;
        }
        return fromDatum(form, sform);
    }

    public static boolean freeIdentifierEquals(SyntaxForm id1, SyntaxForm id2) {
        Translator tr = (Translator) Compilation.getCurrent();
        return tr.lexical.lookup(id1.getDatum(), -1) == tr.lexical.lookup(id2.getDatum(), -1);
    }

    public static boolean isIdentifier(SyntaxForm form) {
        return form.getDatum() instanceof Symbol;
    }

    public static Object fromDatum(Object datum, SyntaxForm template) {
        return makeForm(datum, template.getScope());
    }

    public static Object fromDatumIfNeeded(Object obj, SyntaxForm syntaxForm) {
        Object datum = obj;
        Object template = syntaxForm;
        if (datum == template.getDatum()) {
            return template;
        }
        if (datum instanceof SyntaxForm) {
            return (SyntaxForm) datum;
        }
        return fromDatum(datum, template);
    }

    public static Expression rewrite(Object x) {
        return ((Translator) Compilation.getCurrent()).rewrite(x);
    }

    public static Expression rewriteBody(Object x) {
        return ((Translator) Compilation.getCurrent()).rewrite_body(x);
    }

    public static String toString(SyntaxForm syntaxForm, String str) {
        StringBuilder sb;
        SyntaxForm sform = syntaxForm;
        String id = str;
        new StringBuilder("#<syntax");
        StringBuilder sbuf = sb;
        if (id != null) {
            StringBuilder append = sbuf.append('#');
            StringBuilder append2 = sbuf.append(id);
        }
        StringBuilder append3 = sbuf.append(' ');
        StringBuilder append4 = sbuf.append(sform.getDatum());
        TemplateScope scope = sform.getScope();
        if (scope == null) {
            StringBuilder append5 = sbuf.append(" in null");
        } else {
            StringBuilder append6 = sbuf.append(" in #");
            StringBuilder append7 = sbuf.append(scope.f60id);
        }
        StringBuilder append8 = sbuf.append(">");
        return sbuf.toString();
    }

    static class SimpleSyntaxForm implements SyntaxForm {
        static int counter;
        private Object datum;

        /* renamed from: id */
        int f268id;
        private TemplateScope scope;

        SimpleSyntaxForm(Object datum2, TemplateScope scope2) {
            int i = counter + 1;
            counter = i;
            this.f268id = i;
            this.datum = datum2;
            this.scope = scope2;
        }

        public Object getDatum() {
            return this.datum;
        }

        public TemplateScope getScope() {
            return this.scope;
        }

        public String toString() {
            return SyntaxForms.toString(this, Integer.toString(this.f268id));
        }
    }

    static class PairSyntaxForm extends ImmutablePair implements SyntaxForm {
        private Pair datum;
        private TemplateScope scope;

        public PairSyntaxForm(Pair datum2, TemplateScope scope2) {
            this.datum = datum2;
            this.scope = scope2;
        }

        public Object getDatum() {
            return this.datum;
        }

        public TemplateScope getScope() {
            return this.scope;
        }

        public Object getCar() {
            if (this.car == null) {
                this.car = SyntaxForms.makeForm(this.datum.getCar(), this.scope);
            }
            return this.car;
        }

        public Object getCdr() {
            if (this.cdr == null) {
                this.cdr = SyntaxForms.makeForm(this.datum.getCdr(), this.scope);
            }
            return this.cdr;
        }

        public String toString() {
            return SyntaxForms.toString(this, (String) null);
        }
    }
}
