package gnu.kawa.xml;

import android.support.p000v4.app.FragmentTransaction;
import gnu.lists.FString;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.Procedure1;
import gnu.xml.XMLPrinter;
import java.io.Writer;

public class OutputAsXML extends Procedure1 {
    public OutputAsXML() {
    }

    public int numArgs() {
        return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
    }

    public Object apply1(Object arg) {
        CharArrayOutPort charArrayOutPort;
        XMLPrinter xMLPrinter;
        Object obj;
        new CharArrayOutPort();
        CharArrayOutPort port = charArrayOutPort;
        new XMLPrinter((Writer) port);
        XMLPrinter out = xMLPrinter;
        out.writeObject(arg);
        out.flush();
        new FString(port.toCharArray());
        return obj;
    }
}
