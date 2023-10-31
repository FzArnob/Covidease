package gnu.kawa.slib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.xml.Document;
import gnu.kawa.xml.KAttr;
import gnu.kawa.xml.KComment;
import gnu.kawa.xml.KDocument;
import gnu.kawa.xml.KElement;
import gnu.kawa.xml.KProcessingInstruction;
import gnu.kawa.xml.OutputAsXML;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.WrongType;

/* compiled from: XML.scm */
public class XML extends ModuleBody {
    public static final XML $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit2;
    public static OutputAsXML as$Mnxml;
    public static final ModuleMethod attribute$Mnname;
    public static final Class comment = KComment.class;
    public static final ModuleMethod element$Mnname;
    public static final ModuleMethod parse$Mnxml$Mnfrom$Mnurl;
    public static final Class processing$Mninstruction = KProcessingInstruction.class;

    static {
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        SimpleSymbol simpleSymbol3;
        XML xml;
        ModuleMethod moduleMethod;
        ModuleMethod moduleMethod2;
        ModuleMethod moduleMethod3;
        new SimpleSymbol("attribute-name");
        Lit2 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("element-name");
        Lit1 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("parse-xml-from-url");
        Lit0 = (SimpleSymbol) simpleSymbol3.readResolve();
        new XML();
        $instance = xml;
        XML xml2 = $instance;
        new ModuleMethod(xml2, 1, Lit0, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        parse$Mnxml$Mnfrom$Mnurl = moduleMethod;
        new ModuleMethod(xml2, 2, Lit1, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        element$Mnname = moduleMethod2;
        new ModuleMethod(xml2, 3, Lit2, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        attribute$Mnname = moduleMethod3;
        $instance.run();
    }

    public XML() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext $ctx) {
        OutputAsXML outputAsXML;
        Consumer consumer = $ctx.consumer;
        new OutputAsXML();
        as$Mnxml = outputAsXML;
    }

    public static KDocument parseXmlFromUrl(Object url) {
        return Document.parse(url);
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 1:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 2:
                CallContext callContext3 = callContext2;
                Object obj3 = obj2;
                Object obj4 = obj3;
                if (!(obj3 instanceof KElement)) {
                    return -786431;
                }
                callContext3.value1 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 3:
                CallContext callContext4 = callContext2;
                Object obj5 = obj2;
                Object obj6 = obj5;
                if (!(obj5 instanceof KAttr)) {
                    return -786431;
                }
                callContext4.value1 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    public static Symbol elementName(KElement element) {
        return element.getNodeSymbol();
    }

    public static Symbol attributeName(KAttr attr) {
        return attr.getNodeSymbol();
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        Throwable th;
        Throwable th2;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 1:
                return parseXmlFromUrl(obj2);
            case 2:
                try {
                    return elementName((KElement) obj2);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th2;
                    new WrongType(classCastException, "element-name", 1, obj2);
                    throw th3;
                }
            case 3:
                try {
                    return attributeName((KAttr) obj2);
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th4 = th;
                    new WrongType(classCastException2, "attribute-name", 1, obj2);
                    throw th4;
                }
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }
}
