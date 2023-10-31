package org.shaded.apache.http.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import org.shaded.apache.http.HeaderElement;
import org.shaded.apache.http.HttpEntity;
import org.shaded.apache.http.NameValuePair;
import org.shaded.apache.http.ParseException;

public final class EntityUtils {
    private EntityUtils() {
    }

    public static byte[] toByteArray(HttpEntity httpEntity) throws IOException {
        ByteArrayBuffer byteArrayBuffer;
        Throwable th;
        Throwable th2;
        HttpEntity entity = httpEntity;
        if (entity == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("HTTP entity may not be null");
            throw th3;
        }
        InputStream instream = entity.getContent();
        if (instream == null) {
            return new byte[0];
        }
        if (entity.getContentLength() > 2147483647L) {
            Throwable th4 = th;
            new IllegalArgumentException("HTTP entity too large to be buffered in memory");
            throw th4;
        }
        int i = (int) entity.getContentLength();
        if (i < 0) {
            i = 4096;
        }
        new ByteArrayBuffer(i);
        ByteArrayBuffer buffer = byteArrayBuffer;
        try {
            byte[] tmp = new byte[4096];
            while (true) {
                int read = instream.read(tmp);
                int l = read;
                if (read != -1) {
                    buffer.append(tmp, 0, l);
                } else {
                    instream.close();
                    return buffer.toByteArray();
                }
            }
        } catch (Throwable th5) {
            Throwable th6 = th5;
            instream.close();
            throw th6;
        }
    }

    public static String getContentCharSet(HttpEntity httpEntity) throws ParseException {
        NameValuePair param;
        Throwable th;
        HttpEntity entity = httpEntity;
        if (entity == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP entity may not be null");
            throw th2;
        }
        String charset = null;
        if (entity.getContentType() != null) {
            HeaderElement[] values = entity.getContentType().getElements();
            if (values.length > 0 && (param = values[0].getParameterByName("charset")) != null) {
                charset = param.getValue();
            }
        }
        return charset;
    }

    public static String toString(HttpEntity httpEntity, String str) throws IOException, ParseException {
        Reader reader;
        CharArrayBuffer charArrayBuffer;
        Throwable th;
        Throwable th2;
        HttpEntity entity = httpEntity;
        String defaultCharset = str;
        if (entity == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("HTTP entity may not be null");
            throw th3;
        }
        InputStream instream = entity.getContent();
        if (instream == null) {
            return "";
        }
        if (entity.getContentLength() > 2147483647L) {
            Throwable th4 = th;
            new IllegalArgumentException("HTTP entity too large to be buffered in memory");
            throw th4;
        }
        int i = (int) entity.getContentLength();
        if (i < 0) {
            i = 4096;
        }
        String charset = getContentCharSet(entity);
        if (charset == null) {
            charset = defaultCharset;
        }
        if (charset == null) {
            charset = "ISO-8859-1";
        }
        new InputStreamReader(instream, charset);
        Reader reader2 = reader;
        new CharArrayBuffer(i);
        CharArrayBuffer buffer = charArrayBuffer;
        try {
            char[] tmp = new char[1024];
            while (true) {
                int read = reader2.read(tmp);
                int l = read;
                if (read != -1) {
                    buffer.append(tmp, 0, l);
                } else {
                    reader2.close();
                    return buffer.toString();
                }
            }
        } catch (Throwable th5) {
            Throwable th6 = th5;
            reader2.close();
            throw th6;
        }
    }

    public static String toString(HttpEntity entity) throws IOException, ParseException {
        return toString(entity, (String) null);
    }
}
