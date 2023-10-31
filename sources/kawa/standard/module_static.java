package kawa.standard;

import android.support.p000v4.media.session.PlaybackStateCompat;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class module_static extends Syntax {
    public static final module_static module_static;

    public module_static() {
    }

    static {
        module_static module_static2;
        new module_static();
        module_static = module_static2;
        module_static.setName("module-static");
    }

    public boolean scanForDefinitions(Pair st, Vector vector, ScopeExp scopeExp, Translator translator) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        Vector vector2 = vector;
        ScopeExp defs = scopeExp;
        Translator tr = translator;
        Object list = st.getCdr();
        if (!(defs instanceof ModuleExp)) {
            new StringBuilder();
            tr.error('e', sb3.append("'").append(getName()).append("' not at module level").toString());
            return true;
        }
        ModuleExp mexp = (ModuleExp) defs;
        if (list instanceof Pair) {
            Pair pair = (Pair) list;
            Pair st2 = pair;
            if (pair.getCdr() == LList.Empty && (st2.getCar() instanceof Boolean)) {
                if (st2.getCar() == Boolean.FALSE) {
                    mexp.setFlag(65536);
                } else {
                    mexp.setFlag(32768);
                }
                if (mexp.getFlag(65536) && mexp.getFlag(32768)) {
                    tr.error('e', "inconsistent module-static specifiers");
                }
                return true;
            }
        }
        if (list instanceof Pair) {
            Pair pair2 = (Pair) list;
            Pair st3 = pair2;
            if (pair2.getCdr() == LList.Empty && (st3.getCar() instanceof Pair)) {
                Pair pair3 = (Pair) st3.getCar();
                Pair st4 = pair3;
                if (tr.matches(pair3.getCar(), LispLanguage.quote_sym)) {
                    Pair pair4 = (Pair) st4.getCdr();
                    Pair st5 = pair4;
                    if (pair4 == LList.Empty || !(st5.getCar() instanceof SimpleSymbol) || st5.getCar().toString() != "init-run") {
                        new StringBuilder();
                        tr.error('e', sb2.append("invalid quoted symbol for '").append(getName()).append('\'').toString());
                        return false;
                    }
                    mexp.setFlag(32768);
                    mexp.setFlag(262144);
                    tr.error('e', "inconsistent module-static specifiers");
                    return true;
                }
            }
        }
        mexp.setFlag(65536);
        while (list != LList.Empty) {
            if (list instanceof Pair) {
                Pair pair5 = (Pair) list;
                Pair st6 = pair5;
                if (pair5.getCar() instanceof Symbol) {
                    Declaration decl = defs.getNoDefine((Symbol) st6.getCar());
                    if (decl.getFlag(512)) {
                        Translator.setLine(decl, (Object) st6);
                    }
                    decl.setFlag(PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH);
                    list = st6.getCdr();
                }
            }
            new StringBuilder();
            tr.error('e', sb.append("invalid syntax in '").append(getName()).append('\'').toString());
            return false;
        }
        tr.error('e', "inconsistent module-static specifiers");
        return true;
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        Pair pair2 = pair;
        Translator translator2 = translator;
        return null;
    }
}
