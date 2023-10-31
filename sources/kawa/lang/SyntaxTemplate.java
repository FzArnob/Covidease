package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Vector;

public class SyntaxTemplate implements Externalizable {
    static final int BUILD_CONS = 1;
    static final int BUILD_DOTS = 5;
    static final int BUILD_LIST1 = 8;
    static final int BUILD_LITERAL = 4;
    static final int BUILD_MISC = 0;
    static final int BUILD_NIL = 16;
    static final int BUILD_SYNTAX = 24;
    static final int BUILD_VAR = 2;
    static final int BUILD_VAR_CAR = 3;
    static final int BUILD_VECTOR = 40;
    static final int BUILD_WIDE = 7;
    static final String dots3 = "...";
    Object[] literal_values;
    int max_nesting;
    String patternNesting;
    String template_program;

    protected SyntaxTemplate() {
    }

    public SyntaxTemplate(String patternNesting2, String template_program2, Object[] literal_values2, int max_nesting2) {
        this.patternNesting = patternNesting2;
        this.template_program = template_program2;
        this.literal_values = literal_values2;
        this.max_nesting = max_nesting2;
    }

    public SyntaxTemplate(Object obj, SyntaxForm syntaxForm, Translator translator) {
        StringBuffer stringBuffer;
        Vector vector;
        Object obj2;
        Object template = obj;
        SyntaxForm syntax = syntaxForm;
        Translator tr = translator;
        this.patternNesting = (tr == null || tr.patternScope == null) ? "" : tr.patternScope.patternNesting.toString();
        new StringBuffer();
        StringBuffer program = stringBuffer;
        new Vector();
        Vector literals_vector = vector;
        new IdentityHashMap();
        int convert_template = convert_template(template, syntax, program, 0, literals_vector, obj2, false, tr);
        this.template_program = program.toString();
        this.literal_values = new Object[literals_vector.size()];
        literals_vector.copyInto(this.literal_values);
    }

    public int convert_template(Object obj, SyntaxForm syntaxForm, StringBuffer stringBuffer, int i, Vector vector, Object obj2, boolean z, Translator translator) {
        int i2;
        StringBuilder sb;
        int i3;
        Object form = obj;
        SyntaxForm syntax = syntaxForm;
        StringBuffer template_program2 = stringBuffer;
        int nesting = i;
        Vector literals_vector = vector;
        Object seen = obj2;
        boolean isVector = z;
        Translator tr = translator;
        while (form instanceof SyntaxForm) {
            syntax = (SyntaxForm) form;
            form = syntax.getDatum();
        }
        if ((form instanceof Pair) || (form instanceof FVector)) {
            IdentityHashMap seen_map = (IdentityHashMap) seen;
            if (seen_map.containsKey(form)) {
                Expression syntaxError = tr.syntaxError("self-referential (cyclic) syntax template");
                return -2;
            }
            Object put = seen_map.put(form, form);
        }
        if (form instanceof Pair) {
            Pair pair = (Pair) form;
            int ret_cdr = -2;
            int save_pc = template_program2.length();
            Object car = pair.getCar();
            if (tr.matches(car, dots3)) {
                Object cdr = Translator.stripSyntax(pair.getCdr());
                if (cdr instanceof Pair) {
                    Pair cdr_pair = (Pair) cdr;
                    if (cdr_pair.getCar() == dots3 && cdr_pair.getCdr() == LList.Empty) {
                        form = dots3;
                    }
                }
            }
            int save_literals = literals_vector.size();
            StringBuffer append = template_program2.append(8);
            int num_dots3 = 0;
            Object rest = pair.getCdr();
            while (rest instanceof Pair) {
                Pair p = (Pair) rest;
                if (!tr.matches(p.getCar(), dots3)) {
                    break;
                }
                num_dots3++;
                rest = p.getCdr();
                StringBuffer append2 = template_program2.append(5);
            }
            int ret_car = convert_template(car, syntax, template_program2, nesting + num_dots3, literals_vector, seen, false, tr);
            if (rest != LList.Empty) {
                template_program2.setCharAt(save_pc, (char) ((((template_program2.length() - save_pc) - 1) << 3) + 1));
                ret_cdr = convert_template(rest, syntax, template_program2, nesting, literals_vector, seen, isVector, tr);
            }
            if (num_dots3 > 0) {
                if (ret_car < 0) {
                    Expression syntaxError2 = tr.syntaxError("... follows template with no suitably-nested pattern variable");
                }
                int i4 = num_dots3;
                while (true) {
                    i4--;
                    if (i4 < 0) {
                        break;
                    }
                    template_program2.setCharAt(save_pc + i4 + 1, (char) ((ret_car << 3) + 5));
                    int n = nesting + num_dots3;
                    if (n >= this.max_nesting) {
                        this.max_nesting = n;
                    }
                }
            }
            if (ret_car >= 0) {
                return ret_car;
            }
            if (ret_cdr >= 0) {
                return ret_cdr;
            }
            if (ret_car == -1 || ret_cdr == -1) {
                return -1;
            }
            if (isVector) {
                return -2;
            }
            literals_vector.setSize(save_literals);
            template_program2.setLength(save_pc);
        } else if (form instanceof FVector) {
            StringBuffer append3 = template_program2.append('(');
            return convert_template(LList.makeList((FVector) form), syntax, template_program2, nesting, literals_vector, seen, true, tr);
        } else if (form == LList.Empty) {
            StringBuffer append4 = template_program2.append(16);
            return -2;
        } else if (!(!(form instanceof Symbol) || tr == null || tr.patternScope == null)) {
            int pattern_var_num = indexOf(tr.patternScope.pattern_names, form);
            if (pattern_var_num >= 0) {
                int var_nesting = this.patternNesting.charAt(pattern_var_num);
                int op = (var_nesting & 1) != 0 ? 3 : 2;
                int var_nesting2 = var_nesting >> 1;
                if (var_nesting2 > nesting) {
                    new StringBuilder();
                    Expression syntaxError3 = tr.syntaxError(sb.append("inconsistent ... nesting of ").append(form).toString());
                }
                StringBuffer append5 = template_program2.append((char) (op + (8 * pattern_var_num)));
                if (var_nesting2 == nesting) {
                    i2 = pattern_var_num;
                } else {
                    i2 = -1;
                }
                return i2;
            }
        }
        int literals_index = indexOf(literals_vector, form);
        if (literals_index < 0) {
            literals_index = literals_vector.size();
            literals_vector.addElement(form);
        }
        if (form instanceof Symbol) {
            tr.noteAccess(form, tr.currentScope());
        }
        if (!(form instanceof SyntaxForm) && form != dots3) {
            StringBuffer append6 = template_program2.append(24);
        }
        StringBuffer append7 = template_program2.append((char) (4 + (8 * literals_index)));
        if (form == dots3) {
            i3 = -1;
        } else {
            i3 = -2;
        }
        return i3;
    }

    static int indexOf(Vector vector, Object obj) {
        Vector vec = vector;
        Object elem = obj;
        int len = vec.size();
        for (int i = 0; i < len; i++) {
            if (vec.elementAt(i) == elem) {
                return i;
            }
        }
        return -1;
    }

    private int get_count(Object obj, int i, int[] iArr) {
        Object var = obj;
        int nesting = i;
        int[] indexes = iArr;
        for (int level = 0; level < nesting; level++) {
            var = var[indexes[level]];
        }
        return var.length;
    }

    public Object execute(Object[] vars, TemplateScope templateScope) {
        return execute(0, vars, 0, new int[this.max_nesting], (Translator) Compilation.getCurrent(), templateScope);
    }

    public Object execute(Object[] vars, Translator tr, TemplateScope templateScope) {
        return execute(0, vars, 0, new int[this.max_nesting], tr, templateScope);
    }

    /* access modifiers changed from: package-private */
    public Object get_var(int i, Object[] vars, int[] iArr) {
        int var_num = i;
        int[] indexes = iArr;
        Object var = vars[var_num];
        if (var_num < this.patternNesting.length()) {
            int var_nesting = this.patternNesting.charAt(var_num) >> 1;
            for (int level = 0; level < var_nesting; level++) {
                var = var[indexes[level]];
            }
        }
        return var;
    }

    /* access modifiers changed from: package-private */
    public LList executeToList(int i, Object[] objArr, int i2, int[] iArr, Translator translator, TemplateScope templateScope) {
        int ch;
        LList lList;
        int pc = i;
        Object[] vars = objArr;
        int nesting = i2;
        int[] indexes = iArr;
        Translator tr = translator;
        TemplateScope templateScope2 = templateScope;
        int pc0 = pc;
        int charAt = this.template_program.charAt(pc);
        while (true) {
            ch = charAt;
            if ((ch & 7) != 7) {
                break;
            }
            pc++;
            charAt = ((ch - 7) << 13) | this.template_program.charAt(pc);
        }
        if ((ch & 7) == 3) {
            Pair p = (Pair) get_var(ch >> 3, vars, indexes);
            return Translator.makePair(p, p.getCar(), LList.Empty);
        } else if ((ch & 7) == 5) {
            int count = get_count(vars[ch >> 3], nesting, indexes);
            LList result = LList.Empty;
            Pair last = null;
            int pc2 = pc + 1;
            for (int j = 0; j < count; j++) {
                indexes[nesting] = j;
                LList list = executeToList(pc2, vars, nesting + 1, indexes, tr, templateScope2);
                if (last == null) {
                    result = list;
                } else {
                    last.setCdrBackdoor(list);
                }
                while (list instanceof Pair) {
                    last = (Pair) list;
                    list = (LList) last.getCdr();
                }
            }
            return result;
        } else {
            new Pair(execute(pc0, vars, nesting, indexes, tr, templateScope2), LList.Empty);
            return lList;
        }
    }

    /* access modifiers changed from: package-private */
    public Object execute(int i, Object[] objArr, int i2, int[] iArr, Translator translator, TemplateScope templateScope) {
        int ch;
        Throwable th;
        StringBuilder sb;
        Object obj;
        int pc = i;
        Object[] vars = objArr;
        int nesting = i2;
        int[] indexes = iArr;
        Translator tr = translator;
        TemplateScope templateScope2 = templateScope;
        int charAt = this.template_program.charAt(pc);
        while (true) {
            ch = charAt;
            if ((ch & 7) != 7) {
                break;
            }
            pc++;
            charAt = ((ch - 7) << 13) | this.template_program.charAt(pc);
        }
        if (ch == 8) {
            return executeToList(pc + 1, vars, nesting, indexes, tr, templateScope2);
        }
        if (ch == 16) {
            return LList.Empty;
        }
        if (ch == 24) {
            Object v = execute(pc + 1, vars, nesting, indexes, tr, templateScope2);
            return v == LList.Empty ? v : SyntaxForms.makeForm(v, templateScope2);
        } else if ((ch & 7) == 1) {
            Pair p = null;
            Object obj2 = null;
            do {
                int pc2 = pc + 1;
                Object executeToList = executeToList(pc2, vars, nesting, indexes, tr, templateScope2);
                if (p == null) {
                    obj2 = executeToList;
                } else {
                    p.setCdrBackdoor(executeToList);
                }
                while (executeToList instanceof Pair) {
                    p = (Pair) executeToList;
                    executeToList = p.getCdr();
                }
                pc = pc2 + (ch >> 3);
                ch = this.template_program.charAt(pc);
            } while ((ch & 7) == 1);
            Object cdr = execute(pc, vars, nesting, indexes, tr, templateScope2);
            if (p == null) {
                obj2 = cdr;
            } else {
                p.setCdrBackdoor(cdr);
            }
            return obj2;
        } else if (ch == 40) {
            new FVector((List) (LList) execute(pc + 1, vars, nesting, indexes, tr, templateScope2));
            return obj;
        } else if ((ch & 7) == 4) {
            return this.literal_values[ch >> 3];
        } else if ((ch & 6) == 2) {
            Object var = get_var(ch >> 3, vars, indexes);
            if ((ch & 7) == 3) {
                var = ((Pair) var).getCar();
            }
            return var;
        } else {
            Throwable th2 = th;
            new StringBuilder();
            new Error(sb.append("unknown template code: ").append(ch).append(" at ").append(pc).toString());
            throw th2;
        }
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        out.writeObject(this.patternNesting);
        out.writeObject(this.template_program);
        out.writeObject(this.literal_values);
        out.writeInt(this.max_nesting);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        this.patternNesting = (String) in.readObject();
        this.template_program = (String) in.readObject();
        this.literal_values = (Object[]) in.readObject();
        this.max_nesting = in.readInt();
    }
}
