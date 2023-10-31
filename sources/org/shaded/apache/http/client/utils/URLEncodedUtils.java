package org.shaded.apache.http.client.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HeaderElement;
import org.shaded.apache.http.HttpEntity;
import org.shaded.apache.http.NameValuePair;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.message.BasicNameValuePair;
import org.shaded.apache.http.protocol.HTTP;
import org.shaded.apache.http.util.EntityUtils;

@Immutable
public class URLEncodedUtils {
    public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    private static final String NAME_VALUE_SEPARATOR = "=";
    private static final String PARAMETER_SEPARATOR = "&";

    public URLEncodedUtils() {
    }

    public static List<NameValuePair> parse(URI uri, String str) {
        List<NameValuePair> list;
        Scanner scanner;
        String encoding = str;
        List<NameValuePair> result = Collections.emptyList();
        String query = uri.getRawQuery();
        if (query != null && query.length() > 0) {
            new ArrayList<>();
            result = list;
            new Scanner(query);
            parse(result, scanner, encoding);
        }
        return result;
    }

    public static List<NameValuePair> parse(HttpEntity httpEntity) throws IOException {
        String content;
        List<NameValuePair> list;
        Scanner scanner;
        HttpEntity entity = httpEntity;
        List<NameValuePair> result = Collections.emptyList();
        String contentType = null;
        String charset = null;
        Header h = entity.getContentType();
        if (h != null) {
            HeaderElement[] elems = h.getElements();
            if (elems.length > 0) {
                HeaderElement elem = elems[0];
                contentType = elem.getName();
                NameValuePair param = elem.getParameterByName("charset");
                if (param != null) {
                    charset = param.getValue();
                }
            }
        }
        if (contentType != null && contentType.equalsIgnoreCase(CONTENT_TYPE) && (content = EntityUtils.toString(entity, HTTP.ASCII)) != null && content.length() > 0) {
            new ArrayList<>();
            result = list;
            new Scanner(content);
            parse(result, scanner, charset);
        }
        return result;
    }

    public static boolean isEncoded(HttpEntity entity) {
        Header h = entity.getContentType();
        if (h == null) {
            return false;
        }
        HeaderElement[] elems = h.getElements();
        if (elems.length > 0) {
            return elems[0].getName().equalsIgnoreCase(CONTENT_TYPE);
        }
        return false;
    }

    public static void parse(List<NameValuePair> list, Scanner scanner, String str) {
        Throwable th;
        Object obj;
        List<NameValuePair> parameters = list;
        Scanner scanner2 = scanner;
        String encoding = str;
        Scanner useDelimiter = scanner2.useDelimiter(PARAMETER_SEPARATOR);
        while (scanner2.hasNext()) {
            String[] nameValue = scanner2.next().split(NAME_VALUE_SEPARATOR);
            if (nameValue.length == 0 || nameValue.length > 2) {
                Throwable th2 = th;
                new IllegalArgumentException("bad parameter");
                throw th2;
            }
            String name = decode(nameValue[0], encoding);
            String value = null;
            if (nameValue.length == 2) {
                value = decode(nameValue[1], encoding);
            }
            new BasicNameValuePair(name, value);
            boolean add = parameters.add(obj);
        }
    }

    public static String format(List<? extends NameValuePair> parameters, String str) {
        StringBuilder sb;
        String encoding = str;
        new StringBuilder();
        StringBuilder result = sb;
        for (NameValuePair parameter : parameters) {
            String encodedName = encode(parameter.getName(), encoding);
            String value = parameter.getValue();
            String encodedValue = value != null ? encode(value, encoding) : "";
            if (result.length() > 0) {
                StringBuilder append = result.append(PARAMETER_SEPARATOR);
            }
            StringBuilder append2 = result.append(encodedName);
            StringBuilder append3 = result.append(NAME_VALUE_SEPARATOR);
            StringBuilder append4 = result.append(encodedValue);
        }
        return result.toString();
    }

    private static String decode(String content, String str) {
        Throwable th;
        String encoding = str;
        try {
            return URLDecoder.decode(content, encoding != null ? encoding : "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            UnsupportedEncodingException problem = e;
            Throwable th2 = th;
            new IllegalArgumentException(problem);
            throw th2;
        }
    }

    private static String encode(String content, String str) {
        Throwable th;
        String encoding = str;
        try {
            return URLEncoder.encode(content, encoding != null ? encoding : "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            UnsupportedEncodingException problem = e;
            Throwable th2 = th;
            new IllegalArgumentException(problem);
            throw th2;
        }
    }
}
