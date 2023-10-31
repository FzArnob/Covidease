package kawa.standard;

import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.kawa.lispexpr.LispReader;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.Sequence;
import gnu.mapping.InPort;
import gnu.mapping.Symbol;
import gnu.text.SyntaxException;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import kawa.lang.AutoloadProcedure;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class define_autoload extends Syntax {
    public static final define_autoload define_autoload;
    public static final define_autoload define_autoloads_from_file;
    boolean fromFile;

    static {
        define_autoload define_autoload2;
        define_autoload define_autoload3;
        new define_autoload("define-autoload", false);
        define_autoload = define_autoload2;
        new define_autoload("define-autoloads-from-file", true);
        define_autoloads_from_file = define_autoload3;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public define_autoload(String name, boolean fromFile2) {
        super(name);
        this.fromFile = fromFile2;
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeExp, Translator translator) {
        Pair st = pair;
        Vector forms = vector;
        ScopeExp defs = scopeExp;
        Translator tr = translator;
        if (!(st.getCdr() instanceof Pair)) {
            return super.scanForDefinitions(st, forms, defs, tr);
        }
        Pair p = (Pair) st.getCdr();
        if (this.fromFile) {
            while (p.getCar() instanceof CharSequence) {
                if (!scanFile(p.getCar().toString(), defs, tr)) {
                    return false;
                }
                Object rest = p.getCdr();
                if (rest == LList.Empty) {
                    return true;
                }
                if (!(rest instanceof Pair)) {
                    break;
                }
                p = (Pair) p.getCdr();
            }
            Expression syntaxError = tr.syntaxError("invalid syntax for define-autoloads-from-file");
            return false;
        }
        Object names = p.getCar();
        if (p.getCdr() instanceof Pair) {
            return process(names, ((Pair) p.getCdr()).getCar(), forms, defs, tr);
        }
        Expression syntaxError2 = tr.syntaxError("invalid syntax for define-autoload");
        return false;
    }

    public boolean scanFile(String str, ScopeExp scopeExp, Translator translator) {
        File file;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        File file2;
        File file3;
        String filespec = str;
        ScopeExp defs = scopeExp;
        Translator tr = translator;
        if (filespec.endsWith(".el")) {
        }
        new File(filespec);
        File file4 = file;
        if (!file4.isAbsolute()) {
            new File(tr.getFileName());
            new File(file3.getParent(), filespec);
            file4 = file2;
        }
        String filename = file4.getPath();
        int dot = filename.lastIndexOf(46);
        if (dot >= 0) {
            String extension = filename.substring(dot);
            Language language = Language.getInstance(extension);
            if (language == null) {
                new StringBuilder();
                Expression syntaxError = tr.syntaxError(sb4.append("unknown extension for ").append(filename).toString());
                return true;
            }
            String prefix = tr.classPrefix;
            int extlen = extension.length();
            String substring = filespec.substring(0, filespec.length() - extlen);
            while (true) {
                String cname = substring;
                if (cname.startsWith("../")) {
                    int i = prefix.lastIndexOf(46, prefix.length() - 2);
                    if (i < 0) {
                        new StringBuilder();
                        Expression syntaxError2 = tr.syntaxError(sb3.append("cannot use relative filename \"").append(filespec).append("\" with simple prefix \"").append(prefix).append("\"").toString());
                        return false;
                    }
                    prefix = prefix.substring(0, i + 1);
                    substring = cname.substring(3);
                } else {
                    new StringBuilder();
                    String classname = sb.append(prefix).append(cname).toString().replace('/', '.');
                    try {
                        findAutoloadComments((LispReader) language.getLexer(InPort.openFile(filename), tr.getMessages()), classname, defs, tr);
                        break;
                    } catch (Exception e) {
                        new StringBuilder();
                        Expression syntaxError3 = tr.syntaxError(sb2.append("error reading ").append(filename).append(": ").append(e).toString());
                        return true;
                    }
                }
            }
        }
        return true;
    }

    public static void findAutoloadComments(LispReader lispReader, String str, ScopeExp scopeExp, Translator translator) throws IOException, SyntaxException {
        String command;
        StringBuilder sb;
        Expression ex;
        Object obj;
        LispReader in = lispReader;
        String filename = str;
        ScopeExp defs = scopeExp;
        Translator tr = translator;
        boolean lineStart = true;
        String magic = ";;;###autoload";
        int magicLength = magic.length();
        while (true) {
            int ch = in.peek();
            if (ch >= 0) {
                if (ch == 10 || ch == 13) {
                    int read = in.read();
                    lineStart = true;
                } else {
                    if (lineStart && ch == 59) {
                        int i = 0;
                        while (true) {
                            if (i != magicLength) {
                                ch = in.read();
                                if (ch >= 0) {
                                    if (ch == 10 || ch == 13) {
                                        lineStart = true;
                                    } else if (i >= 0) {
                                        int i2 = i;
                                        i++;
                                        if (ch != magic.charAt(i2)) {
                                            i = -1;
                                        }
                                    }
                                } else {
                                    return;
                                }
                            } else if (i > 0) {
                                Object form = in.readObject();
                                if (form instanceof Pair) {
                                    Pair pair = (Pair) form;
                                    Object value = null;
                                    String name = null;
                                    Object car = pair.getCar();
                                    if (car instanceof String) {
                                        command = car.toString();
                                    } else {
                                        command = car instanceof Symbol ? ((Symbol) car).getName() : null;
                                    }
                                    if (command == "defun") {
                                        name = ((Pair) pair.getCdr()).getCar().toString();
                                        new AutoloadProcedure(name, filename, tr.getLanguage());
                                        value = obj;
                                    } else {
                                        new StringBuilder();
                                        tr.error('w', sb.append("unsupported ;;;###autoload followed by: ").append(pair.getCar()).toString());
                                    }
                                    if (value != null) {
                                        Declaration decl = defs.getDefine(name, 'w', tr);
                                        new QuoteExp(value);
                                        decl.setFlag(16384);
                                        decl.noteValue(ex);
                                        decl.setProcedureDecl(true);
                                        decl.setType(Compilation.typeProcedure);
                                    }
                                }
                                lineStart = false;
                            }
                        }
                    }
                    lineStart = false;
                    in.skip();
                    if (ch == 35 && in.peek() == 124) {
                        in.skip();
                        in.readNestedComment('#', '|');
                    } else if (!Character.isWhitespace((char) ch) && in.readObject(ch) == Sequence.eofValue) {
                        return;
                    }
                }
            } else {
                return;
            }
        }
    }

    public static boolean process(Object obj, Object obj2, Vector vector, ScopeExp scopeExp, Translator translator) {
        AutoloadProcedure value;
        Expression ex;
        boolean z;
        Object names = obj;
        Object filename = obj2;
        Vector forms = vector;
        ScopeExp defs = scopeExp;
        Translator tr = translator;
        if (names instanceof Pair) {
            Pair p = (Pair) names;
            if (!process(p.getCar(), filename, forms, defs, tr) || !process(p.getCdr(), filename, forms, defs, tr)) {
                z = false;
            } else {
                z = true;
            }
            return z;
        } else if (names == LList.Empty) {
            return true;
        } else {
            if (!(names instanceof String) && !(names instanceof Symbol)) {
                return false;
            }
            String name = names.toString();
            Declaration decl = defs.getDefine(name, 'w', tr);
            if (filename instanceof String) {
                String str = (String) filename;
                String fn = str;
                int length = str.length();
                int len = length;
                if (length > 2 && fn.charAt(0) == '<' && fn.charAt(len - 1) == '>') {
                    filename = fn.substring(1, len - 1);
                }
            }
            new AutoloadProcedure(name, filename.toString(), tr.getLanguage());
            new QuoteExp(value);
            decl.setFlag(16384);
            decl.noteValue(ex);
            return true;
        }
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        Pair pair2 = pair;
        Translator translator2 = translator;
        return null;
    }
}
