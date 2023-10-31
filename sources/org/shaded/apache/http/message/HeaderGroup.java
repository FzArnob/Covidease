package org.shaded.apache.http.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HeaderIterator;
import org.shaded.apache.http.util.CharArrayBuffer;

public class HeaderGroup implements Cloneable {
    private List headers;

    public HeaderGroup() {
        List list;
        new ArrayList(16);
        this.headers = list;
    }

    public void clear() {
        this.headers.clear();
    }

    public void addHeader(Header header) {
        Header header2 = header;
        if (header2 != null) {
            boolean add = this.headers.add(header2);
        }
    }

    public void removeHeader(Header header) {
        Header header2 = header;
        if (header2 != null) {
            boolean remove = this.headers.remove(header2);
        }
    }

    public void updateHeader(Header header) {
        Header header2 = header;
        if (header2 != null) {
            for (int i = 0; i < this.headers.size(); i++) {
                if (((Header) this.headers.get(i)).getName().equalsIgnoreCase(header2.getName())) {
                    Object obj = this.headers.set(i, header2);
                    return;
                }
            }
            boolean add = this.headers.add(header2);
        }
    }

    public void setHeaders(Header[] headerArr) {
        Header[] headers2 = headerArr;
        clear();
        if (headers2 != null) {
            for (int i = 0; i < headers2.length; i++) {
                boolean add = this.headers.add(headers2[i]);
            }
        }
    }

    public Header getCondensedHeader(String str) {
        CharArrayBuffer charArrayBuffer;
        Header header;
        String name = str;
        Header[] headers2 = getHeaders(name);
        if (headers2.length == 0) {
            return null;
        }
        if (headers2.length == 1) {
            return headers2[0];
        }
        new CharArrayBuffer(128);
        CharArrayBuffer valueBuffer = charArrayBuffer;
        valueBuffer.append(headers2[0].getValue());
        for (int i = 1; i < headers2.length; i++) {
            valueBuffer.append(", ");
            valueBuffer.append(headers2[i].getValue());
        }
        new BasicHeader(name.toLowerCase(Locale.ENGLISH), valueBuffer.toString());
        return header;
    }

    public Header[] getHeaders(String str) {
        ArrayList arrayList;
        String name = str;
        new ArrayList();
        ArrayList headersFound = arrayList;
        for (int i = 0; i < this.headers.size(); i++) {
            Header header = (Header) this.headers.get(i);
            if (header.getName().equalsIgnoreCase(name)) {
                boolean add = headersFound.add(header);
            }
        }
        return (Header[]) headersFound.toArray(new Header[headersFound.size()]);
    }

    public Header getFirstHeader(String str) {
        String name = str;
        for (int i = 0; i < this.headers.size(); i++) {
            Header header = (Header) this.headers.get(i);
            if (header.getName().equalsIgnoreCase(name)) {
                return header;
            }
        }
        return null;
    }

    public Header getLastHeader(String str) {
        String name = str;
        for (int i = this.headers.size() - 1; i >= 0; i--) {
            Header header = (Header) this.headers.get(i);
            if (header.getName().equalsIgnoreCase(name)) {
                return header;
            }
        }
        return null;
    }

    public Header[] getAllHeaders() {
        return (Header[]) this.headers.toArray(new Header[this.headers.size()]);
    }

    public boolean containsHeader(String str) {
        String name = str;
        for (int i = 0; i < this.headers.size(); i++) {
            if (((Header) this.headers.get(i)).getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public HeaderIterator iterator() {
        HeaderIterator headerIterator;
        new BasicListHeaderIterator(this.headers, (String) null);
        return headerIterator;
    }

    public HeaderIterator iterator(String name) {
        HeaderIterator headerIterator;
        new BasicListHeaderIterator(this.headers, name);
        return headerIterator;
    }

    public HeaderGroup copy() {
        HeaderGroup headerGroup;
        new HeaderGroup();
        HeaderGroup clone = headerGroup;
        boolean addAll = clone.headers.addAll(this.headers);
        return clone;
    }

    public Object clone() throws CloneNotSupportedException {
        List list;
        HeaderGroup clone = (HeaderGroup) super.clone();
        new ArrayList(this.headers);
        clone.headers = list;
        return clone;
    }
}
