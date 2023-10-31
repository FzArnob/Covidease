package gnu.kawa.xslt;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.LambdaExp;
import gnu.expr.ModuleExp;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.kawa.functions.AppendValues;
import gnu.kawa.xml.MakeAttribute;
import gnu.kawa.xml.MakeElement;
import gnu.lists.Consumer;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SourceMessages;
import gnu.xml.XMLParser;
import gnu.xml.XName;
import gnu.xquery.lang.XQParser;
import java.io.IOException;
import java.util.Stack;
import java.util.Vector;
import org.shaded.apache.commons.logging.LogFactory;
import org.shaded.apache.http.cookie.ClientCookie;

public class XslTranslator extends Lexer implements Consumer {
    static final String XSL_TRANSFORM_URI = "http://www.w3.org/1999/XSL/Transform";
    static final Method applyTemplatesMethod = typeXSLT.getDeclaredMethod("applyTemplates", 2);
    static final PrimProcedure applyTemplatesProc;
    static final Method defineTemplateMethod = typeXSLT.getDeclaredMethod("defineTemplate", 5);
    static final PrimProcedure defineTemplateProc;
    static final Method runStylesheetMethod = typeXSLT.getDeclaredMethod("runStylesheet", 0);
    static final PrimProcedure runStylesheetProc;
    static final ClassType typeTemplateTable = ClassType.make("gnu.kawa.xslt.TemplateTable");
    static final ClassType typeXSLT = ClassType.make("gnu.kawa.xslt.XSLT");
    Object attributeType;
    StringBuffer attributeValue;
    Compilation comp;
    Declaration consumerDecl;

    /* renamed from: in */
    InPort f238in;
    boolean inAttribute;
    boolean inTemplate;
    XSLT interpreter;
    ModuleExp mexp;
    StringBuffer nesting;
    boolean preserveSpace;
    LambdaExp templateLambda;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    XslTranslator(gnu.mapping.InPort r10, gnu.text.SourceMessages r11, gnu.kawa.xslt.XSLT r12) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r12
            r4 = r0
            r5 = r1
            r6 = r2
            r4.<init>(r5, r6)
            r4 = r0
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r8 = r5
            r5 = r8
            r6 = r8
            r7 = 100
            r6.<init>(r7)
            r4.nesting = r5
            r4 = r0
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r8 = r5
            r5 = r8
            r6 = r8
            r7 = 100
            r6.<init>(r7)
            r4.attributeValue = r5
            r4 = r0
            r5 = r3
            r4.interpreter = r5
            r4 = r0
            r5 = r1
            r4.f238in = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.xslt.XslTranslator.<init>(gnu.mapping.InPort, gnu.text.SourceMessages, gnu.kawa.xslt.XSLT):void");
    }

    /* access modifiers changed from: package-private */
    public void maybeSkipWhitespace() {
        if (!this.preserveSpace) {
            int size = this.comp.exprStack.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                Expression expr = (Expression) this.comp.exprStack.elementAt(size);
                if (!(expr instanceof QuoteExp)) {
                    break;
                }
                Object value = ((QuoteExp) expr).getValue();
                String str = value == null ? "" : value.toString();
                int j = str.length();
                while (true) {
                    j--;
                    if (j >= 0) {
                        char ch = str.charAt(j);
                        if (ch != ' ' && ch != 9 && ch != 13 && ch != 10) {
                            return;
                        }
                    }
                }
            }
            this.comp.exprStack.setSize(size + 1);
        }
    }

    public String popMatchingAttribute(String str, String str2, int i) {
        String ns = str;
        String name = str2;
        int start = i;
        int size = this.comp.exprStack.size();
        for (int i2 = start; i2 < size; i2++) {
            Object el = this.comp.exprStack.elementAt(start);
            if (!(el instanceof ApplyExp)) {
                return null;
            }
            ApplyExp aexp = (ApplyExp) el;
            Expression function = aexp.getFunction();
            if (aexp.getFunction() != MakeAttribute.makeAttributeExp) {
                return null;
            }
            Expression[] args = aexp.getArgs();
            if (args.length != 2) {
                return null;
            }
            Expression arg0 = args[0];
            if (!(arg0 instanceof QuoteExp)) {
                return null;
            }
            Object tag = ((QuoteExp) arg0).getValue();
            if (!(tag instanceof Symbol)) {
                return null;
            }
            Symbol stag = (Symbol) tag;
            if (stag.getLocalPart() == name && stag.getNamespaceURI() == ns) {
                this.comp.exprStack.removeElementAt(i2);
                return (String) ((QuoteExp) args[1]).getValue();
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Expression popTemplateBody(int start) {
        Expression expression;
        int i = this.comp.exprStack.size() - start;
        Expression[] args = new Expression[i];
        while (true) {
            i--;
            if (i >= 0) {
                args[i] = this.comp.exprStack.pop();
            } else {
                new ApplyExp((Procedure) AppendValues.appendValues, args);
                return expression;
            }
        }
    }

    public static String isXslTag(Object obj) {
        Object type = obj;
        if (type instanceof QuoteExp) {
            type = ((QuoteExp) type).getValue();
        }
        if (!(type instanceof Symbol)) {
            return null;
        }
        Symbol qname = (Symbol) type;
        if (qname.getNamespaceURI() != XSL_TRANSFORM_URI) {
            return null;
        }
        return qname.getLocalName();
    }

    /* access modifiers changed from: package-private */
    public void append(Expression expr) {
    }

    public void startElement(Object obj) {
        LambdaExp lambdaExp;
        Object type = obj;
        maybeSkipWhitespace();
        String xslTag = isXslTag(type);
        if (xslTag == "template") {
            if (this.templateLambda != null) {
                error("nested xsl:template");
            }
            new LambdaExp();
            this.templateLambda = lambdaExp;
        } else if (xslTag == PropertyTypeConstants.PROPERTY_TYPE_TEXT) {
            this.preserveSpace = false;
        }
        if (type instanceof XName) {
            XName xn = (XName) type;
            type = Symbol.make(xn.getNamespaceURI(), xn.getLocalPart(), xn.getPrefix());
        }
        StringBuffer append = this.nesting.append((char) this.comp.exprStack.size());
        push(type);
    }

    public void startAttribute(Object obj) {
        Object attrType = obj;
        if (this.inAttribute) {
            error('f', "internal error - attribute inside attribute");
        }
        this.attributeType = attrType;
        this.attributeValue.setLength(0);
        StringBuffer append = this.nesting.append((char) this.comp.exprStack.size());
        this.inAttribute = true;
    }

    public void endAttribute() {
        Expression expression;
        Expression expression2;
        Expression expression3;
        new QuoteExp(this.attributeType);
        new QuoteExp(this.attributeValue.toString());
        new ApplyExp((Expression) MakeAttribute.makeAttributeExp, expression, expression2);
        push(expression3);
        this.nesting.setLength(this.nesting.length() - 1);
        this.inAttribute = false;
    }

    public void endElement() {
        Expression expression;
        Expression expression2;
        MakeElement mkElement;
        Expression expression3;
        Expression expression4;
        Expression expression5;
        Expression expression6;
        Expression expression7;
        Expression expression8;
        Expression expression9;
        Expression expression10;
        Expression expression11;
        Expression expression12;
        Expression expression13;
        Expression expression14;
        maybeSkipWhitespace();
        int nlen = this.nesting.length() - 1;
        int start = this.nesting.charAt(nlen);
        this.nesting.setLength(nlen);
        String xslTag = isXslTag((Expression) this.comp.exprStack.elementAt(start));
        if (xslTag == "value-of") {
            String select = popMatchingAttribute("", "select", start + 1);
            if (select != null) {
                new ApplyExp(XQParser.makeText, parseXPath(select));
                Expression exp = expression14;
                Expression pop = this.comp.exprStack.pop();
                push(exp);
            }
        } else if (xslTag == "copy-of") {
            String select2 = popMatchingAttribute("", "select", start + 1);
            if (select2 != null) {
                Expression exp2 = parseXPath(select2);
                Expression pop2 = this.comp.exprStack.pop();
                push(exp2);
            }
        } else if (xslTag == "apply-templates") {
            String select3 = popMatchingAttribute("", "select", start + 1);
            String mode = popMatchingAttribute("", "mode", start + 1);
            Expression[] expressionArr = new Expression[2];
            new QuoteExp(select3);
            expressionArr[0] = expression11;
            Expression[] args = expressionArr;
            args[1] = resolveQNameExpression(mode);
            Expression pop3 = this.comp.exprStack.pop();
            new QuoteExp(applyTemplatesProc);
            new ApplyExp(expression13, args);
            push(expression12);
        } else if (xslTag == "if") {
            Expression test = XQParser.booleanValue(parseXPath(popMatchingAttribute("", "test", start + 1)));
            Expression clause = popTemplateBody(start + 1);
            Expression pop4 = this.comp.exprStack.pop();
            new IfExp(test, clause, QuoteExp.voidExp);
            push(expression10);
        } else if (xslTag == "stylesheet" || xslTag == "transform") {
            String popMatchingAttribute = popMatchingAttribute("", ClientCookie.VERSION_ATTR, start + 1);
            new QuoteExp(runStylesheetProc);
            new ApplyExp(expression2, Expression.noExpressions);
            push(expression);
            Expression body = popTemplateBody(start + 1);
            push(body);
            this.mexp.body = body;
        } else if (xslTag == "template") {
            String match = popMatchingAttribute("", "match", start + 1);
            String name = popMatchingAttribute("", "name", start + 1);
            String popMatchingAttribute2 = popMatchingAttribute("", LogFactory.PRIORITY_KEY, start + 1);
            String mode2 = popMatchingAttribute("", "mode", start + 1);
            this.templateLambda.body = popTemplateBody(start + 1);
            Expression pop5 = this.comp.exprStack.pop();
            new QuoteExp(match);
            new QuoteExp(DFloNum.make(0.0d));
            Expression[] args2 = {resolveQNameExpression(name), expression6, expression7, resolveQNameExpression(mode2), this.templateLambda};
            new QuoteExp(defineTemplateProc);
            new ApplyExp(expression9, args2);
            push(expression8);
            this.templateLambda = null;
        } else if (xslTag == PropertyTypeConstants.PROPERTY_TYPE_TEXT) {
            this.preserveSpace = false;
            Expression[] args3 = new Expression[((this.comp.exprStack.size() - start) - 1)];
            int i = args3.length;
            while (true) {
                i--;
                if (i >= 0) {
                    args3[i] = this.comp.exprStack.pop();
                } else {
                    Expression pop6 = this.comp.exprStack.pop();
                    new ApplyExp(XQParser.makeText, args3);
                    Expression exp3 = expression5;
                    push(exp3);
                    this.mexp.body = exp3;
                    return;
                }
            }
        } else {
            Expression[] args4 = new Expression[(this.comp.exprStack.size() - start)];
            int i2 = args4.length;
            while (true) {
                i2--;
                if (i2 >= 0) {
                    args4[i2] = this.comp.exprStack.pop();
                } else {
                    new MakeElement();
                    new QuoteExp(mkElement);
                    new ApplyExp(expression4, args4);
                    Expression exp4 = expression3;
                    push(exp4);
                    this.mexp.body = exp4;
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Expression parseXPath(String str) {
        Throwable th;
        StringBuilder sb;
        XQParser xQParser;
        InPort inPort;
        Vector vector;
        Throwable th2;
        String string = str;
        SourceMessages messages = this.comp.getMessages();
        try {
            new CharArrayInPort(string);
            new XQParser(inPort, messages, this.interpreter);
            XQParser parser = xQParser;
            new Vector(20);
            Vector exps = vector;
            while (true) {
                Expression sexp = parser.parse(this.comp);
                if (sexp == null) {
                    break;
                }
                exps.addElement(sexp);
            }
            int nexps = exps.size();
            if (nexps == 0) {
                return QuoteExp.voidExp;
            }
            if (nexps == 1) {
                return (Expression) exps.elementAt(0);
            }
            Throwable th3 = th2;
            new InternalError("too many xpath expressions");
            throw th3;
        } catch (Throwable th4) {
            Throwable ex = th4;
            ex.printStackTrace();
            Throwable th5 = th;
            new StringBuilder();
            new InternalError(sb.append("caught ").append(ex).toString());
            throw th5;
        }
    }

    public void write(int i) {
        String str;
        String str2;
        int v = i;
        if (this.inAttribute) {
            StringBuffer appendCodePoint = this.attributeValue.appendCodePoint(v);
            return;
        }
        if (v < 65536) {
            str2 = String.valueOf(v);
        } else {
            char[] cArr = new char[2];
            cArr[0] = (char) (((v - 65536) >> 10) + 55296);
            char[] c2 = cArr;
            c2[1] = (char) ((v & 1023) + 56320);
            new String(c2);
            str2 = str;
        }
        push((Object) str2);
    }

    public Consumer append(char c) {
        char v = c;
        if (this.inAttribute) {
            StringBuffer append = this.attributeValue.append(v);
        } else {
            push((Object) String.valueOf(v));
        }
        return this;
    }

    public Consumer append(CharSequence charSequence) {
        CharSequence csq = charSequence;
        if (this.inAttribute) {
            StringBuffer append = this.attributeValue.append(csq);
        } else {
            push((Object) csq.toString());
        }
        return this;
    }

    public Consumer append(CharSequence csq, int start, int end) {
        return append(csq.subSequence(start, end));
    }

    /* access modifiers changed from: package-private */
    public void push(Expression exp) {
        Expression push = this.comp.exprStack.push(exp);
    }

    /* access modifiers changed from: package-private */
    public void push(Object value) {
        Expression expression;
        new QuoteExp(value);
        push(expression);
    }

    public void writeBoolean(boolean z) {
        boolean v = z;
        if (this.inAttribute) {
            StringBuffer append = this.attributeValue.append(v);
        } else {
            push((Expression) v ? QuoteExp.trueExp : QuoteExp.falseExp);
        }
    }

    public void writeFloat(float f) {
        float v = f;
        if (this.inAttribute) {
            StringBuffer append = this.attributeValue.append(v);
        } else {
            push((Object) DFloNum.make((double) v));
        }
    }

    public void writeDouble(double d) {
        double v = d;
        if (this.inAttribute) {
            StringBuffer append = this.attributeValue.append(v);
        } else {
            push((Object) DFloNum.make(v));
        }
    }

    public void writeInt(int i) {
        int v = i;
        if (this.inAttribute) {
            StringBuffer append = this.attributeValue.append(v);
        } else {
            push((Object) IntNum.make(v));
        }
    }

    public void writeLong(long j) {
        long v = j;
        if (this.inAttribute) {
            StringBuffer append = this.attributeValue.append(v);
        } else {
            push((Object) IntNum.make(v));
        }
    }

    public void startDocument() {
    }

    public void startDocument(ModuleExp mexp2) {
        this.mexp = mexp2;
        startDocument();
    }

    public void endDocument() {
    }

    public void writeObject(Object obj) {
        Object v = obj;
        if (this.inAttribute) {
            StringBuffer append = this.attributeValue.append(v);
        } else {
            push(v);
        }
    }

    public void write(char[] cArr, int i, int i2) {
        Object obj;
        char[] buf = cArr;
        int off = i;
        int len = i2;
        if (this.inAttribute) {
            StringBuffer append = this.attributeValue.append(buf, off, len);
            return;
        }
        new String(buf, off, len);
        push(obj);
    }

    public void write(String str) {
        String str2 = str;
        if (this.inAttribute) {
            StringBuffer append = this.attributeValue.append(str2);
        } else {
            push((Object) str2);
        }
    }

    public void write(CharSequence str, int start, int length) {
        write(str.subSequence(start, length).toString());
    }

    public boolean ignoring() {
        return false;
    }

    public Expression getExpression() {
        return this.comp.exprStack.pop();
    }

    public void error(char kind, String message) {
        getMessages().error(kind, message);
    }

    /* access modifiers changed from: package-private */
    public Expression resolveQNameExpression(String str) {
        Expression expression;
        String name = str;
        if (name == null) {
            return QuoteExp.nullExp;
        }
        new QuoteExp(Symbol.make((Object) null, name));
        return expression;
    }

    public void parse(Compilation compilation) throws IOException {
        Stack<Expression> stack;
        Compilation comp2 = compilation;
        this.comp = comp2;
        if (comp2.exprStack == null) {
            new Stack<>();
            comp2.exprStack = stack;
        }
        ModuleExp mexp2 = comp2.pushNewModule((Lexer) this);
        comp2.mustCompileHere();
        startDocument(mexp2);
        XMLParser.parse((LineBufferedReader) this.f238in, getMessages(), (Consumer) this);
        endDocument();
        comp2.pop(mexp2);
    }

    static {
        PrimProcedure primProcedure;
        PrimProcedure primProcedure2;
        PrimProcedure primProcedure3;
        new PrimProcedure(defineTemplateMethod);
        defineTemplateProc = primProcedure;
        new PrimProcedure(runStylesheetMethod);
        runStylesheetProc = primProcedure2;
        new PrimProcedure(applyTemplatesMethod);
        applyTemplatesProc = primProcedure3;
    }
}
