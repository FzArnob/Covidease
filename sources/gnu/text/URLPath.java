package gnu.text;

import gnu.mapping.WrappedException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class URLPath extends URIPath {
    final URL url;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    URLPath(java.net.URL r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            java.net.URI r3 = toUri(r3)
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            r2.url = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.URLPath.<init>(java.net.URL):void");
    }

    public static URLPath valueOf(URL url2) {
        URLPath uRLPath;
        new URLPath(url2);
        return uRLPath;
    }

    public boolean isAbsolute() {
        return true;
    }

    public long getLastModified() {
        return getLastModified(this.url);
    }

    public static long getLastModified(URL url2) {
        try {
            return url2.openConnection().getLastModified();
        } catch (Throwable th) {
            Throwable th2 = th;
            return 0;
        }
    }

    public long getContentLength() {
        return (long) getContentLength(this.url);
    }

    public static int getContentLength(URL url2) {
        try {
            return url2.openConnection().getContentLength();
        } catch (Throwable th) {
            Throwable th2 = th;
            return -1;
        }
    }

    public URL toURL() {
        return this.url;
    }

    public static URI toUri(URL url2) {
        try {
            return url2.toURI();
        } catch (Throwable th) {
            throw WrappedException.wrapIfNeeded(th);
        }
    }

    public URI toUri() {
        return toUri(this.url);
    }

    public String toURIString() {
        return this.url.toString();
    }

    public Path resolve(String relative) {
        URL url2;
        try {
            new URL(this.url, relative);
            return valueOf(url2);
        } catch (Throwable th) {
            throw WrappedException.wrapIfNeeded(th);
        }
    }

    public static InputStream openInputStream(URL url2) throws IOException {
        return url2.openConnection().getInputStream();
    }

    public InputStream openInputStream() throws IOException {
        return openInputStream(this.url);
    }

    public static OutputStream openOutputStream(URL url2) throws IOException {
        OutputStream outputStream;
        File file;
        URI uri;
        URL url3 = url2;
        String str = url3.toString();
        if (str.startsWith("file:")) {
            try {
                OutputStream outputStream2 = outputStream;
                new URI(str);
                new File(uri);
                new FileOutputStream(file);
                return outputStream2;
            } catch (Throwable th) {
                Throwable th2 = th;
            }
        }
        URLConnection conn = url3.openConnection();
        conn.setDoInput(false);
        conn.setDoOutput(true);
        return conn.getOutputStream();
    }

    public OutputStream openOutputStream() throws IOException {
        return openOutputStream(this.url);
    }

    public static URLPath classResourcePath(Class cls) {
        StringBuilder sb;
        URL url2;
        Class clas = cls;
        try {
            url2 = ResourceStreamHandler.makeURL(clas);
        } catch (SecurityException e) {
            SecurityException securityException = e;
            new StringBuilder();
            url2 = clas.getClassLoader().getResource(sb.append(clas.getName().replace('.', '/')).append(".class").toString());
        } catch (Throwable th) {
            throw WrappedException.wrapIfNeeded(th);
        }
        return valueOf(url2);
    }
}
