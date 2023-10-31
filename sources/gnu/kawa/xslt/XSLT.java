package gnu.kawa.xslt;

import gnu.expr.ApplicationMainSupport;
import gnu.expr.Compilation;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.kawa.xml.Document;
import gnu.kawa.xml.Focus;
import gnu.kawa.xml.KDocument;
import gnu.lists.TreeList;
import gnu.mapping.CallContext;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.text.Lexer;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import gnu.xquery.lang.XQParser;
import gnu.xquery.lang.XQResolveNames;
import gnu.xquery.lang.XQuery;
import java.io.IOException;

public class XSLT extends XQuery {
    public static XSLT instance;
    public static Symbol nullMode = Symbol.make((Object) null, "");

    public String getName() {
        return "XSLT";
    }

    public XSLT() {
        instance = this;
        ModuleBody.setMainPrintValues(true);
    }

    public static XSLT getXsltInstance() {
        Object obj;
        if (instance == null) {
            Object obj2 = obj;
            new XSLT();
        }
        return instance;
    }

    public Lexer getLexer(InPort inp, SourceMessages messages) {
        Lexer lexer;
        new XslTranslator(inp, messages, this);
        return lexer;
    }

    public boolean parse(Compilation compilation, int i) throws IOException, SyntaxException {
        XQParser xQParser;
        XQResolveNames xQResolveNames;
        Compilation comp = compilation;
        int i2 = i;
        Compilation.defaultCallConvention = 2;
        ((XslTranslator) comp.lexer).parse(comp);
        comp.setState(4);
        new XQParser((InPort) null, comp.getMessages(), this);
        XQParser xqparser = xQParser;
        new XQResolveNames(comp);
        XQResolveNames resolver = xQResolveNames;
        resolver.functionNamespacePath = xqparser.functionNamespacePath;
        resolver.parser = xqparser;
        resolver.resolveModule(comp.mainLambda);
        return true;
    }

    public static void registerEnvironment() {
        Language language;
        new XSLT();
        Language.setDefaults(language);
    }

    public static void defineCallTemplate(Symbol name, double priority, Procedure template) {
    }

    public static void defineApplyTemplate(String str, double d, Symbol symbol, Procedure procedure) {
        String pattern = str;
        double priority = d;
        Symbol mode = symbol;
        Procedure template = procedure;
        if (mode == null) {
            mode = nullMode;
        }
        TemplateTable.getTemplateTable(mode).enter(pattern, priority, template);
    }

    public static void defineTemplate(Symbol symbol, String str, double d, Symbol symbol2, Procedure procedure) {
        Symbol name = symbol;
        String pattern = str;
        double priority = d;
        Symbol mode = symbol2;
        Procedure template = procedure;
        if (name != null) {
            defineCallTemplate(name, priority, template);
        }
        if (pattern != null) {
            defineApplyTemplate(pattern, priority, mode, template);
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void process(gnu.lists.TreeList r16, gnu.kawa.xml.Focus r17, gnu.mapping.CallContext r18) throws java.lang.Throwable {
        /*
            r0 = r16
            r1 = r17
            r2 = r18
            r11 = r2
            gnu.lists.Consumer r11 = r11.consumer
            r3 = r11
        L_0x000a:
            r11 = r1
            int r11 = r11.ipos
            r4 = r11
            r11 = r0
            r12 = r4
            int r11 = r11.getNextKind(r12)
            r5 = r11
            r11 = r5
            switch(r11) {
                case 29: goto L_0x00bd;
                case 30: goto L_0x0019;
                case 31: goto L_0x0019;
                case 32: goto L_0x0019;
                case 33: goto L_0x0026;
                case 34: goto L_0x001a;
                case 35: goto L_0x0084;
                case 36: goto L_0x00e6;
                case 37: goto L_0x00e6;
                default: goto L_0x0019;
            }
        L_0x0019:
            return
        L_0x001a:
            r11 = r0
            r12 = r4
            int r11 = r11.firstChildPos(r12)
            r4 = r11
        L_0x0021:
            r11 = r1
            r12 = r4
            r11.ipos = r12
            goto L_0x000a
        L_0x0026:
            r11 = r1
            java.lang.Object r11 = r11.getNextTypeObject()
            r6 = r11
            r11 = r1
            java.lang.String r11 = r11.getNextTypeName()
            r7 = r11
            gnu.kawa.xslt.TemplateTable r11 = gnu.kawa.xslt.TemplateTable.nullModeTable
            r12 = r7
            gnu.mapping.Procedure r11 = r11.find(r12)
            r8 = r11
            r11 = r8
            if (r11 == 0) goto L_0x0059
            r11 = r8
            r12 = r2
            r11.check0(r12)
            r11 = r2
            r11.runUntilDone()
        L_0x0046:
            r11 = r0
            r12 = r4
            r13 = 1
            int r12 = r12 >>> 1
            int r11 = r11.nextDataIndex(r12)
            r12 = 1
            int r11 = r11 << 1
            r4 = r11
            r11 = r1
            boolean r11 = r11.gotoNext()
            goto L_0x0021
        L_0x0059:
            r11 = r3
            r12 = r6
            r11.startElement(r12)
            r11 = r0
            r12 = r4
            int r11 = r11.firstAttributePos(r12)
            r9 = r11
            r11 = r9
            if (r11 != 0) goto L_0x006f
            r11 = r0
            r12 = r4
            int r11 = r11.firstChildPos(r12)
            r9 = r11
        L_0x006f:
            r11 = r1
            r12 = r0
            r13 = r9
            r11.push(r12, r13)
            r11 = r0
            r12 = r1
            r13 = r2
            process(r11, r12, r13)
            r11 = r1
            r11.pop()
            r11 = r3
            r11.endElement()
            goto L_0x0046
        L_0x0084:
            r11 = r1
            java.lang.Object r11 = r11.getNextTypeObject()
            r6 = r11
            r11 = r1
            java.lang.String r11 = r11.getNextTypeName()
            r7 = r11
            gnu.kawa.xslt.TemplateTable r11 = gnu.kawa.xslt.TemplateTable.nullModeTable
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r15 = r12
            r12 = r15
            r13 = r15
            r13.<init>()
            java.lang.String r13 = "@"
            java.lang.StringBuilder r12 = r12.append(r13)
            r13 = r7
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r12 = r12.toString()
            gnu.mapping.Procedure r11 = r11.find(r12)
            r8 = r11
            r11 = r8
            if (r11 == 0) goto L_0x00bd
            r11 = r8
            r12 = r2
            r11.check0(r12)
            r11 = r2
            r11.runUntilDone()
            goto L_0x0021
        L_0x00bd:
            r11 = r4
            r12 = 1
            int r11 = r11 >>> 1
            r9 = r11
            r11 = r0
            r12 = r9
            r13 = 2147483647(0x7fffffff, float:NaN)
            int r11 = r11.nextNodeIndex(r12, r13)
            r10 = r11
            r11 = r9
            r12 = r10
            if (r11 != r12) goto L_0x00d7
            r11 = r0
            r12 = r9
            int r11 = r11.nextDataIndex(r12)
            r10 = r11
        L_0x00d7:
            r11 = r0
            r12 = r9
            r13 = r10
            r14 = r3
            int r11 = r11.consumeIRange(r12, r13, r14)
            r11 = r10
            r12 = 1
            int r11 = r11 << 1
            r4 = r11
            goto L_0x0021
        L_0x00e6:
            r11 = r0
            r12 = r4
            r13 = 1
            int r12 = r12 >>> 1
            int r11 = r11.nextDataIndex(r12)
            r12 = 1
            int r11 = r11 << 1
            r4 = r11
            goto L_0x0021
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.xslt.XSLT.process(gnu.lists.TreeList, gnu.kawa.xml.Focus, gnu.mapping.CallContext):void");
    }

    public static void runStylesheet() throws Throwable {
        CallContext ctx = CallContext.getInstance();
        ApplicationMainSupport.processSetProperties();
        String[] args = ApplicationMainSupport.commandLineArgArray;
        for (int i = 0; i < args.length; i++) {
            KDocument doc = Document.parse(args[i]);
            Focus pos = Focus.getCurrent();
            pos.push(doc.sequence, doc.ipos);
            process((TreeList) doc.sequence, pos, ctx);
        }
    }

    public static void applyTemplates(String str, Symbol symbol) throws Throwable {
        String str2 = str;
        Symbol mode = symbol;
        if (mode == null) {
            mode = nullMode;
        }
        TemplateTable templateTable = TemplateTable.getTemplateTable(mode);
        CallContext ctx = CallContext.getInstance();
        Focus pos = Focus.getCurrent();
        TreeList doc = (TreeList) pos.sequence;
        pos.push(doc, doc.firstChildPos(pos.ipos));
        process(doc, pos, ctx);
        pos.pop();
    }
}
