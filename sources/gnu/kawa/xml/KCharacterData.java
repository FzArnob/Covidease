package gnu.kawa.xml;

import gnu.xml.NodeTree;
import org.w3c.dom.CharacterData;
import org.w3c.dom.DOMException;

public abstract class KCharacterData extends KNode implements CharacterData {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KCharacterData(NodeTree seq, int ipos) {
        super(seq, ipos);
    }

    public int getLength() {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        NodeTree tlist = (NodeTree) this.sequence;
        int stringValue = tlist.stringValue(tlist.posToDataIndex(this.ipos), sbuf);
        return sbuf.length();
    }

    public String getData() {
        return getNodeValue();
    }

    public void setData(String str) throws DOMException {
        Throwable th;
        String str2 = str;
        Throwable th2 = th;
        new DOMException(7, "setData not supported");
        throw th2;
    }

    public String substringData(int i, int i2) throws DOMException {
        Throwable th;
        int offset = i;
        int count = i2;
        String data = getData();
        if (offset >= 0 && count >= 0 && offset + count < data.length()) {
            return data.substring(offset, count);
        }
        Throwable th2 = th;
        new DOMException(1, "invalid index to substringData");
        throw th2;
    }

    public void appendData(String str) throws DOMException {
        Throwable th;
        String str2 = str;
        Throwable th2 = th;
        new DOMException(7, "appendData not supported");
        throw th2;
    }

    public void insertData(int offset, String data) throws DOMException {
        replaceData(offset, 0, data);
    }

    public void deleteData(int offset, int count) throws DOMException {
        replaceData(offset, count, "");
    }

    public void replaceData(int i, int i2, String str) throws DOMException {
        Throwable th;
        int i3 = i;
        int i4 = i2;
        String str2 = str;
        Throwable th2 = th;
        new DOMException(7, "replaceData not supported");
        throw th2;
    }
}
