package kawa.standard;

import android.support.p000v4.media.session.PlaybackStateCompat;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class export extends Syntax {
    public static final export export;
    public static final export module_export;

    public export() {
    }

    static {
        export export2;
        export export3;
        new export();
        module_export = export2;
        module_export.setName("module-export");
        new export();
        export = export3;
        module_export.setName("export");
    }

    /* JADX INFO: finally extract failed */
    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeExp, Translator translator) {
        StringBuilder sb;
        StringBuilder sb2;
        Pair st = pair;
        Vector vector2 = vector;
        ScopeExp defs = scopeExp;
        Translator tr = translator;
        Object list = st.getCdr();
        Object savePos = tr.pushPositionOf(st);
        try {
            if (defs instanceof ModuleExp) {
                ((ModuleExp) defs).setFlag(16384);
                SyntaxForm restSyntax = null;
                while (list != LList.Empty) {
                    Object pushPositionOf = tr.pushPositionOf(list);
                    while (list instanceof SyntaxForm) {
                        restSyntax = (SyntaxForm) list;
                        list = restSyntax.getDatum();
                    }
                    SyntaxForm nameSyntax = restSyntax;
                    if (list instanceof Pair) {
                        Pair st2 = (Pair) list;
                        Object symbol = st2.getCar();
                        while (symbol instanceof SyntaxForm) {
                            nameSyntax = (SyntaxForm) symbol;
                            symbol = nameSyntax.getDatum();
                        }
                        if (symbol instanceof String) {
                            String str = (String) symbol;
                            if (str.startsWith("namespace:")) {
                                tr.error('w', "'namespace:' prefix ignored");
                                symbol = str.substring(10).intern();
                            }
                        }
                        if ((symbol instanceof String) || (symbol instanceof Symbol)) {
                            if (nameSyntax != null) {
                            }
                            Declaration decl = defs.getNoDefine(symbol);
                            if (decl.getFlag(512)) {
                                Translator.setLine(decl, (Object) st2);
                            }
                            decl.setFlag(PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID);
                            list = st2.getCdr();
                        }
                    }
                    new StringBuilder();
                    tr.error('e', sb2.append("invalid syntax in '").append(getName()).append('\'').toString());
                    tr.popPositionOf(savePos);
                    return false;
                }
                tr.popPositionOf(savePos);
                return true;
            }
            new StringBuilder();
            tr.error('e', sb.append("'").append(getName()).append("' not at module level").toString());
            tr.popPositionOf(savePos);
            return true;
        } catch (Throwable th) {
            Throwable th2 = th;
            tr.popPositionOf(savePos);
            throw th2;
        }
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        Pair pair2 = pair;
        Translator translator2 = translator;
        return null;
    }
}
