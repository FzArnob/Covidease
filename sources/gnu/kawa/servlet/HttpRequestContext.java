package gnu.kawa.servlet;

import gnu.mapping.InPort;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;
import org.shaded.apache.http.HttpHost;
import org.shaded.apache.http.protocol.HTTP;

public abstract class HttpRequestContext {
    public static final int HTTP_NOT_FOUND = 404;
    public static final int HTTP_OK = 200;
    static final int STATUS_SENT = -999;
    public static int importServletDefinitions;
    protected static final ThreadLocal<HttpRequestContext> instance;
    ServletPrinter consumer;
    String localPath = "";
    String scriptPath = "";
    public int statusCode = 200;
    public String statusReasonPhrase = null;

    public abstract Object getAttribute(String str);

    public abstract String getContextPath();

    public abstract int getLocalPort();

    public abstract String getPathTranslated();

    public abstract String getQueryString();

    public abstract InetAddress getRemoteHost();

    public abstract String getRemoteIPAddress();

    public abstract int getRemotePort();

    public abstract String getRequestHeader(String str);

    public abstract List<String> getRequestHeaders(String str);

    public abstract Map<String, List<String>> getRequestHeaders();

    public abstract String getRequestMethod();

    public abstract Map<String, List<String>> getRequestParameters();

    public abstract InputStream getRequestStream();

    public abstract URI getRequestURI();

    public abstract URL getResourceURL(String str);

    public abstract OutputStream getResponseStream();

    public abstract void log(String str);

    public abstract void log(String str, Throwable th);

    public abstract boolean reset(boolean z);

    public abstract void sendResponseHeaders(int i, String str, long j) throws IOException;

    public abstract void setAttribute(String str, Object obj);

    public abstract void setResponseHeader(String str, String str2);

    public HttpRequestContext() {
    }

    static {
        ThreadLocal<HttpRequestContext> threadLocal;
        new ThreadLocal<>();
        instance = threadLocal;
    }

    public static HttpRequestContext getInstance() {
        Throwable th;
        HttpRequestContext hctx = instance.get();
        if (hctx != null) {
            return hctx;
        }
        Throwable th2 = th;
        new UnsupportedOperationException("can only be called by http-server");
        throw th2;
    }

    public static HttpRequestContext getInstance(String str) {
        Throwable th;
        StringBuilder sb;
        String command = str;
        HttpRequestContext hctx = instance.get();
        if (hctx != null) {
            return hctx;
        }
        Throwable th2 = th;
        new StringBuilder();
        new UnsupportedOperationException(sb.append(command).append(" can only be called within http-server").toString());
        throw th2;
    }

    public static void setInstance(HttpRequestContext ctx) {
        instance.set(ctx);
    }

    public InPort getRequestPort() {
        InPort inPort;
        new InPort(getRequestStream());
        return inPort;
    }

    public String getRequestBodyChars() throws IOException {
        Reader reader;
        String str;
        new InputStreamReader(getRequestStream());
        Reader reader2 = reader;
        int buflen = 1024;
        char[] buf = new char[1024];
        int i = 0;
        while (true) {
            int pos = i;
            int avail = buflen - pos;
            if (avail <= 0) {
                char[] tmp = new char[(2 * buflen)];
                System.arraycopy(buf, 0, tmp, 0, buflen);
                buf = tmp;
                buflen += buflen;
            }
            int count = reader2.read(buf, pos, avail);
            if (count < 0) {
                reader2.close();
                new String(buf, 0, pos);
                return str;
            }
            i = pos + count;
        }
    }

    public ServletPrinter getConsumer() throws IOException {
        ServletPrinter servletPrinter;
        if (this.consumer == null) {
            new ServletPrinter(this, 8192);
            this.consumer = servletPrinter;
        }
        return this.consumer;
    }

    public String getRequestParameter(String name) {
        List<String> p = getRequestParameters().get(name);
        return (p == null || p.isEmpty()) ? null : p.get(0);
    }

    public String getScriptPath() {
        return this.scriptPath;
    }

    public String getLocalPath() {
        return this.localPath;
    }

    public void setScriptAndLocalPath(String scriptPath2, String localPath2) {
        this.scriptPath = scriptPath2;
        this.localPath = localPath2;
    }

    public String getRequestPath() {
        return getRequestURI().getPath();
    }

    public String getRequestScheme() {
        return HttpHost.DEFAULT_SCHEME_NAME;
    }

    public InetSocketAddress getLocalSocketAddress() {
        InetSocketAddress inetSocketAddress;
        new InetSocketAddress(getLocalHost(), getLocalPort());
        return inetSocketAddress;
    }

    public String getLocalIPAddress() {
        return getLocalHost().getHostAddress();
    }

    public InetAddress getLocalHost() {
        Throwable th;
        try {
            return InetAddress.getLocalHost();
        } catch (Throwable th2) {
            Throwable ex = th2;
            Throwable th3 = th;
            new RuntimeException(ex);
            throw th3;
        }
    }

    public InetSocketAddress getRemoteSocketAddress() {
        InetSocketAddress inetSocketAddress;
        new InetSocketAddress(getRemoteHost(), getRemotePort());
        return inetSocketAddress;
    }

    public StringBuffer getRequestURLBuffer() {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        StringBuffer append = sbuf.append(getRequestScheme());
        StringBuffer append2 = sbuf.append("://");
        String host = getRequestHeader(HTTP.TARGET_HOST);
        if (host != null) {
            StringBuffer append3 = sbuf.append(host);
        } else {
            StringBuffer append4 = sbuf.append(getLocalIPAddress());
            StringBuffer append5 = sbuf.append(':');
            StringBuffer append6 = sbuf.append(getLocalPort());
        }
        StringBuffer append7 = sbuf.append(getRequestPath());
        return sbuf;
    }

    public void setContentType(String type) {
        setResponseHeader(HTTP.CONTENT_TYPE, type);
    }

    /* access modifiers changed from: protected */
    public String normalizeToContext(String str) {
        StringBuilder sb;
        String path;
        String path2 = str;
        if (path2.length() <= 0 || path2.charAt(0) != '/') {
            new StringBuilder();
            path = sb.append(getScriptPath()).append(path2).toString();
        } else {
            path = path2.substring(1);
        }
        if (path.indexOf("..") >= 0) {
            path = URI.create(path).normalize().toString();
            if (path.startsWith("../")) {
                return null;
            }
        }
        return path;
    }

    public void sendNotFound(String path) throws IOException {
        StringBuilder sb;
        Throwable th;
        new StringBuilder();
        byte[] bmsg = sb.append("The requested URL ").append(path).append(" was not found on this server.\r\n").toString().getBytes();
        sendResponseHeaders(404, (String) null, (long) bmsg.length);
        try {
            getResponseStream().write(bmsg);
        } catch (IOException e) {
            IOException ex = e;
            Throwable th2 = th;
            new RuntimeException(ex);
            throw th2;
        }
    }
}
