package org.shaded.apache.http.message;

import org.shaded.apache.http.HeaderElement;
import org.shaded.apache.http.NameValuePair;
import org.shaded.apache.http.util.CharArrayBuffer;

public class BasicHeaderValueFormatter implements HeaderValueFormatter {
    public static final BasicHeaderValueFormatter DEFAULT;
    public static final String SEPARATORS = " ;,:@()<>\\\"/[]?={}\t";
    public static final String UNSAFE_CHARS = "\"\\";

    public BasicHeaderValueFormatter() {
    }

    static {
        BasicHeaderValueFormatter basicHeaderValueFormatter;
        new BasicHeaderValueFormatter();
        DEFAULT = basicHeaderValueFormatter;
    }

    public static final String formatElements(HeaderElement[] headerElementArr, boolean z, HeaderValueFormatter headerValueFormatter) {
        HeaderElement[] elems = headerElementArr;
        boolean quote = z;
        HeaderValueFormatter formatter = headerValueFormatter;
        if (formatter == null) {
            formatter = DEFAULT;
        }
        return formatter.formatElements((CharArrayBuffer) null, elems, quote).toString();
    }

    public CharArrayBuffer formatElements(CharArrayBuffer charArrayBuffer, HeaderElement[] headerElementArr, boolean z) {
        CharArrayBuffer charArrayBuffer2;
        Throwable th;
        CharArrayBuffer buffer = charArrayBuffer;
        HeaderElement[] elems = headerElementArr;
        boolean quote = z;
        if (elems == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Header element array must not be null.");
            throw th2;
        }
        int len = estimateElementsLen(elems);
        if (buffer == null) {
            new CharArrayBuffer(len);
            buffer = charArrayBuffer2;
        } else {
            buffer.ensureCapacity(len);
        }
        for (int i = 0; i < elems.length; i++) {
            if (i > 0) {
                buffer.append(", ");
            }
            CharArrayBuffer formatHeaderElement = formatHeaderElement(buffer, elems[i], quote);
        }
        return buffer;
    }

    /* access modifiers changed from: protected */
    public int estimateElementsLen(HeaderElement[] headerElementArr) {
        HeaderElement[] elems = headerElementArr;
        if (elems == null || elems.length < 1) {
            return 0;
        }
        int result = (elems.length - 1) * 2;
        for (int i = 0; i < elems.length; i++) {
            result += estimateHeaderElementLen(elems[i]);
        }
        return result;
    }

    public static final String formatHeaderElement(HeaderElement headerElement, boolean z, HeaderValueFormatter headerValueFormatter) {
        HeaderElement elem = headerElement;
        boolean quote = z;
        HeaderValueFormatter formatter = headerValueFormatter;
        if (formatter == null) {
            formatter = DEFAULT;
        }
        return formatter.formatHeaderElement((CharArrayBuffer) null, elem, quote).toString();
    }

    public CharArrayBuffer formatHeaderElement(CharArrayBuffer charArrayBuffer, HeaderElement headerElement, boolean z) {
        CharArrayBuffer charArrayBuffer2;
        Throwable th;
        CharArrayBuffer buffer = charArrayBuffer;
        HeaderElement elem = headerElement;
        boolean quote = z;
        if (elem == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Header element must not be null.");
            throw th2;
        }
        int len = estimateHeaderElementLen(elem);
        if (buffer == null) {
            new CharArrayBuffer(len);
            buffer = charArrayBuffer2;
        } else {
            buffer.ensureCapacity(len);
        }
        buffer.append(elem.getName());
        String value = elem.getValue();
        if (value != null) {
            buffer.append('=');
            doFormatValue(buffer, value, quote);
        }
        int parcnt = elem.getParameterCount();
        if (parcnt > 0) {
            for (int i = 0; i < parcnt; i++) {
                buffer.append("; ");
                CharArrayBuffer formatNameValuePair = formatNameValuePair(buffer, elem.getParameter(i), quote);
            }
        }
        return buffer;
    }

    /* access modifiers changed from: protected */
    public int estimateHeaderElementLen(HeaderElement headerElement) {
        HeaderElement elem = headerElement;
        if (elem == null) {
            return 0;
        }
        int result = elem.getName().length();
        String value = elem.getValue();
        if (value != null) {
            result += 3 + value.length();
        }
        int parcnt = elem.getParameterCount();
        if (parcnt > 0) {
            for (int i = 0; i < parcnt; i++) {
                result += 2 + estimateNameValuePairLen(elem.getParameter(i));
            }
        }
        return result;
    }

    public static final String formatParameters(NameValuePair[] nameValuePairArr, boolean z, HeaderValueFormatter headerValueFormatter) {
        NameValuePair[] nvps = nameValuePairArr;
        boolean quote = z;
        HeaderValueFormatter formatter = headerValueFormatter;
        if (formatter == null) {
            formatter = DEFAULT;
        }
        return formatter.formatParameters((CharArrayBuffer) null, nvps, quote).toString();
    }

    public CharArrayBuffer formatParameters(CharArrayBuffer charArrayBuffer, NameValuePair[] nameValuePairArr, boolean z) {
        CharArrayBuffer charArrayBuffer2;
        Throwable th;
        CharArrayBuffer buffer = charArrayBuffer;
        NameValuePair[] nvps = nameValuePairArr;
        boolean quote = z;
        if (nvps == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Parameters must not be null.");
            throw th2;
        }
        int len = estimateParametersLen(nvps);
        if (buffer == null) {
            new CharArrayBuffer(len);
            buffer = charArrayBuffer2;
        } else {
            buffer.ensureCapacity(len);
        }
        for (int i = 0; i < nvps.length; i++) {
            if (i > 0) {
                buffer.append("; ");
            }
            CharArrayBuffer formatNameValuePair = formatNameValuePair(buffer, nvps[i], quote);
        }
        return buffer;
    }

    /* access modifiers changed from: protected */
    public int estimateParametersLen(NameValuePair[] nameValuePairArr) {
        NameValuePair[] nvps = nameValuePairArr;
        if (nvps == null || nvps.length < 1) {
            return 0;
        }
        int result = (nvps.length - 1) * 2;
        for (int i = 0; i < nvps.length; i++) {
            result += estimateNameValuePairLen(nvps[i]);
        }
        return result;
    }

    public static final String formatNameValuePair(NameValuePair nameValuePair, boolean z, HeaderValueFormatter headerValueFormatter) {
        NameValuePair nvp = nameValuePair;
        boolean quote = z;
        HeaderValueFormatter formatter = headerValueFormatter;
        if (formatter == null) {
            formatter = DEFAULT;
        }
        return formatter.formatNameValuePair((CharArrayBuffer) null, nvp, quote).toString();
    }

    public CharArrayBuffer formatNameValuePair(CharArrayBuffer charArrayBuffer, NameValuePair nameValuePair, boolean z) {
        CharArrayBuffer charArrayBuffer2;
        Throwable th;
        CharArrayBuffer buffer = charArrayBuffer;
        NameValuePair nvp = nameValuePair;
        boolean quote = z;
        if (nvp == null) {
            Throwable th2 = th;
            new IllegalArgumentException("NameValuePair must not be null.");
            throw th2;
        }
        int len = estimateNameValuePairLen(nvp);
        if (buffer == null) {
            new CharArrayBuffer(len);
            buffer = charArrayBuffer2;
        } else {
            buffer.ensureCapacity(len);
        }
        buffer.append(nvp.getName());
        String value = nvp.getValue();
        if (value != null) {
            buffer.append('=');
            doFormatValue(buffer, value, quote);
        }
        return buffer;
    }

    /* access modifiers changed from: protected */
    public int estimateNameValuePairLen(NameValuePair nameValuePair) {
        NameValuePair nvp = nameValuePair;
        if (nvp == null) {
            return 0;
        }
        int result = nvp.getName().length();
        String value = nvp.getValue();
        if (value != null) {
            result += 3 + value.length();
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public void doFormatValue(CharArrayBuffer charArrayBuffer, String str, boolean z) {
        CharArrayBuffer buffer = charArrayBuffer;
        String value = str;
        boolean quote = z;
        if (!quote) {
            for (int i = 0; i < value.length() && !quote; i++) {
                quote = isSeparator(value.charAt(i));
            }
        }
        if (quote) {
            buffer.append('\"');
        }
        for (int i2 = 0; i2 < value.length(); i2++) {
            char ch = value.charAt(i2);
            if (isUnsafe(ch)) {
                buffer.append('\\');
            }
            buffer.append(ch);
        }
        if (quote) {
            buffer.append('\"');
        }
    }

    /* access modifiers changed from: protected */
    public boolean isSeparator(char ch) {
        return SEPARATORS.indexOf(ch) >= 0;
    }

    /* access modifiers changed from: protected */
    public boolean isUnsafe(char ch) {
        return UNSAFE_CHARS.indexOf(ch) >= 0;
    }
}
