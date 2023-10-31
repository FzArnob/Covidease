package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.ScopeExp;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure1;
import gnu.text.Printable;
import gnu.text.ReportFormat;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class SyntaxRules extends Procedure1 implements Printable, Externalizable {
    Object[] literal_identifiers;
    int maxVars = 0;
    SyntaxRule[] rules;

    public SyntaxRules() {
    }

    public SyntaxRules(Object[] literal_identifiers2, SyntaxRule[] rules2, int maxVars2) {
        this.literal_identifiers = literal_identifiers2;
        this.rules = rules2;
        this.maxVars = maxVars2;
    }

    public SyntaxRules(Object[] objArr, Object obj, Translator translator) {
        Object syntax_rule;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuffer stringBuffer;
        SyntaxPattern spattern;
        SyntaxRule syntaxRule;
        Object[] literal_identifiers2 = objArr;
        Object srules = obj;
        Translator tr = translator;
        this.literal_identifiers = literal_identifiers2;
        int rules_count = Translator.listLength(srules);
        if (rules_count < 0) {
            rules_count = 0;
            Expression syntaxError = tr.syntaxError("missing or invalid syntax-rules");
        }
        this.rules = new SyntaxRule[rules_count];
        SyntaxForm rules_syntax = null;
        int i = 0;
        while (i < rules_count) {
            while (srules instanceof SyntaxForm) {
                rules_syntax = (SyntaxForm) srules;
                srules = rules_syntax.getDatum();
            }
            Pair rules_pair = (Pair) srules;
            SyntaxForm rule_syntax = rules_syntax;
            Object car = rules_pair.getCar();
            while (true) {
                syntax_rule = car;
                if (!(syntax_rule instanceof SyntaxForm)) {
                    break;
                }
                rule_syntax = (SyntaxForm) syntax_rule;
                car = rule_syntax.getDatum();
            }
            if (!(syntax_rule instanceof Pair)) {
                new StringBuilder();
                Expression syntaxError2 = tr.syntaxError(sb.append("missing pattern in ").append(i).append("'th syntax rule").toString());
                return;
            }
            SyntaxForm pattern_syntax = rule_syntax;
            Pair syntax_rule_pair = (Pair) syntax_rule;
            Object pattern = syntax_rule_pair.getCar();
            String save_filename = tr.getFileName();
            int save_line = tr.getLineNumber();
            int save_column = tr.getColumnNumber();
            SyntaxForm template_syntax = rule_syntax;
            try {
                tr.setLine((Object) syntax_rule_pair);
                Object syntax_rule2 = syntax_rule_pair.getCdr();
                while (syntax_rule2 instanceof SyntaxForm) {
                    template_syntax = (SyntaxForm) syntax_rule2;
                    syntax_rule2 = template_syntax.getDatum();
                }
                if (!(syntax_rule2 instanceof Pair)) {
                    new StringBuilder();
                    Expression syntaxError3 = tr.syntaxError(sb2.append("missing template in ").append(i).append("'th syntax rule").toString());
                    tr.setLine(save_filename, save_line, save_column);
                    return;
                }
                Pair syntax_rule_pair2 = (Pair) syntax_rule2;
                if (syntax_rule_pair2.getCdr() != LList.Empty) {
                    new StringBuilder();
                    Expression syntaxError4 = tr.syntaxError(sb3.append("junk after ").append(i).append("'th syntax rule").toString());
                    tr.setLine(save_filename, save_line, save_column);
                    return;
                }
                Object template = syntax_rule_pair2.getCar();
                tr.push((ScopeExp) PatternScope.push(tr));
                while (pattern instanceof SyntaxForm) {
                    pattern_syntax = (SyntaxForm) pattern;
                    pattern = pattern_syntax.getDatum();
                }
                new StringBuffer();
                StringBuffer programbuf = stringBuffer;
                if (pattern instanceof Pair) {
                    literal_identifiers2[0] = ((Pair) pattern).getCar();
                    StringBuffer append = programbuf.append(12);
                    StringBuffer append2 = programbuf.append(24);
                    new SyntaxPattern(programbuf, ((Pair) pattern).getCdr(), pattern_syntax, literal_identifiers2, tr);
                    new SyntaxRule(spattern, template, template_syntax, tr);
                    this.rules[i] = syntaxRule;
                    PatternScope.pop(tr);
                    tr.pop();
                    tr.setLine(save_filename, save_line, save_column);
                    i++;
                    srules = rules_pair.getCdr();
                } else {
                    Expression syntaxError5 = tr.syntaxError("pattern does not start with name");
                    tr.setLine(save_filename, save_line, save_column);
                    return;
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                tr.setLine(save_filename, save_line, save_column);
                throw th2;
            }
        }
        int i2 = this.rules.length;
        while (true) {
            i2--;
            if (i2 >= 0) {
                int size = this.rules[i2].patternNesting.length();
                if (size > this.maxVars) {
                    this.maxVars = size;
                }
            } else {
                return;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public Object apply1(Object obj) {
        Object arg = obj;
        if (!(arg instanceof SyntaxForm)) {
            return expand(arg, (Translator) Compilation.getCurrent());
        }
        SyntaxForm sf = (SyntaxForm) arg;
        Translator tr = (Translator) Compilation.getCurrent();
        ScopeExp save_scope = tr.currentScope();
        tr.setCurrentScope(sf.getScope());
        try {
            Object expand = expand(sf, tr);
            tr.setCurrentScope(save_scope);
            return expand;
        } catch (Throwable th) {
            Throwable th2 = th;
            tr.setCurrentScope(save_scope);
            throw th2;
        }
    }

    public Object expand(Object obj, Translator translator) {
        StringBuilder sb;
        Object obj2;
        StringBuilder sb2;
        Object obj3 = obj;
        Translator tr = translator;
        Object[] vars = new Object[this.maxVars];
        Macro macro = (Macro) tr.getCurrentSyntax();
        int i = 0;
        while (i < this.rules.length) {
            SyntaxRule rule = this.rules[i];
            if (rule == null) {
                new StringBuilder();
                new ErrorExp(sb2.append("error defining ").append(macro).toString());
                return obj2;
            } else if (rule.pattern.match(obj3, vars, 0)) {
                return rule.execute(vars, tr, TemplateScope.make(tr));
            } else {
                i++;
            }
        }
        new StringBuilder();
        return tr.syntaxError(sb.append("no matching syntax-rule for ").append(this.literal_identifiers[0]).toString());
    }

    public void print(Consumer consumer) {
        Consumer out = consumer;
        out.write("#<macro ");
        ReportFormat.print(this.literal_identifiers[0], out);
        out.write(62);
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        out.writeObject(this.literal_identifiers);
        out.writeObject(this.rules);
        out.writeInt(this.maxVars);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        this.literal_identifiers = (Object[]) in.readObject();
        this.rules = (SyntaxRule[]) in.readObject();
        this.maxVars = in.readInt();
    }
}
