package gnu.kawa.xml;

import gnu.lists.UnescapedData;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure1;

public class MakeUnescapedData extends Procedure1 {
    public static final MakeUnescapedData unescapedData;

    static {
        MakeUnescapedData makeUnescapedData;
        new MakeUnescapedData();
        unescapedData = makeUnescapedData;
    }

    public MakeUnescapedData() {
        setProperty(Procedure.validateApplyKey, "gnu.kawa.xml.CompileXmlFunctions:validateApplyMakeUnescapedData");
    }

    public Object apply1(Object obj) {
        Object arg = obj;
        UnescapedData unescapedData2 = r5;
        UnescapedData unescapedData3 = new UnescapedData(arg == null ? "" : arg.toString());
        return unescapedData2;
    }
}
