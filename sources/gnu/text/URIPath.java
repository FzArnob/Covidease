package gnu.text;

import gnu.bytecode.Access;
import gnu.lists.FString;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class URIPath extends Path implements Comparable<URIPath> {
    final URI uri;

    URIPath(URI uri2) {
        this.uri = uri2;
    }

    public static URIPath coerceToURIPathOrNull(Object obj) {
        String str;
        Object path = obj;
        if (path instanceof URIPath) {
            return (URIPath) path;
        }
        if (path instanceof URL) {
            return URLPath.valueOf((URL) path);
        }
        if (path instanceof URI) {
            return valueOf((URI) path);
        }
        if ((path instanceof File) || (path instanceof Path) || (path instanceof FString)) {
            str = path.toString();
        } else if (!(path instanceof String)) {
            return null;
        } else {
            str = (String) path;
        }
        return valueOf(str);
    }

    public static URIPath makeURI(Object obj) {
        Throwable th;
        Object arg = obj;
        URIPath path = coerceToURIPathOrNull(arg);
        if (path != null) {
            return path;
        }
        Throwable th2 = th;
        new WrongType((String) null, -4, arg, "URI");
        throw th2;
    }

    public static URIPath valueOf(URI uri2) {
        URIPath uRIPath;
        new URIPath(uri2);
        return uRIPath;
    }

    public static URIPath valueOf(String str) {
        URIPath uRIPath;
        URI uri2;
        String uri3 = str;
        try {
            URIPath uRIPath2 = uRIPath;
            new URI(encodeForUri(uri3, Access.INNERCLASS_CONTEXT));
            new URIStringPath(uri2, uri3);
            return uRIPath2;
        } catch (Throwable th) {
            throw WrappedException.wrapIfNeeded(th);
        }
    }

    public boolean isAbsolute() {
        return this.uri.isAbsolute();
    }

    public boolean exists() {
        try {
            URLConnection conn = toURL().openConnection();
            if (conn instanceof HttpURLConnection) {
                return ((HttpURLConnection) conn).getResponseCode() == 200;
            }
            return conn.getLastModified() != 0;
        } catch (Throwable th) {
            Throwable th2 = th;
            return false;
        }
    }

    public long getLastModified() {
        return URLPath.getLastModified(toURL());
    }

    public long getContentLength() {
        return (long) URLPath.getContentLength(toURL());
    }

    public URI toUri() {
        return this.uri;
    }

    public String toURIString() {
        return this.uri.toString();
    }

    public Path resolve(String str) {
        URI uri2;
        File file;
        String rstr = str;
        if (Path.uriSchemeSpecified(rstr)) {
            return valueOf(rstr);
        }
        char fileSep = File.separatorChar;
        if (fileSep != '/') {
            if (rstr.length() < 2 || ((rstr.charAt(1) != ':' || !Character.isLetter(rstr.charAt(0))) && !(rstr.charAt(0) == fileSep && rstr.charAt(1) == fileSep))) {
                rstr = rstr.replace(fileSep, '/');
            } else {
                new File(rstr);
                return FilePath.valueOf(file);
            }
        }
        try {
            new URI((String) null, rstr, (String) null);
            return valueOf(this.uri.resolve(uri2));
        } catch (Throwable th) {
            throw WrappedException.wrapIfNeeded(th);
        }
    }

    public int compareTo(URIPath path) {
        return this.uri.compareTo(path.uri);
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        return (obj2 instanceof URIPath) && this.uri.equals(((URIPath) obj2).uri);
    }

    public int hashCode() {
        return this.uri.hashCode();
    }

    public String toString() {
        return toURIString();
    }

    public URL toURL() {
        return Path.toURL(this.uri.toString());
    }

    public InputStream openInputStream() throws IOException {
        return URLPath.openInputStream(toURL());
    }

    public OutputStream openOutputStream() throws IOException {
        return URLPath.openOutputStream(toURL());
    }

    public String getScheme() {
        return this.uri.getScheme();
    }

    public String getHost() {
        return this.uri.getHost();
    }

    public String getAuthority() {
        return this.uri.getAuthority();
    }

    public String getUserInfo() {
        return this.uri.getUserInfo();
    }

    public int getPort() {
        return this.uri.getPort();
    }

    public String getPath() {
        return this.uri.getPath();
    }

    public String getQuery() {
        return this.uri.getQuery();
    }

    public String getFragment() {
        return this.uri.getFragment();
    }

    public Path getCanonical() {
        if (!isAbsolute()) {
            return getAbsolute().getCanonical();
        }
        URI norm = this.uri.normalize();
        if (norm != this.uri) {
            return valueOf(norm);
        }
        return this;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v15, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v16, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v17, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v26, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v11, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v1, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v2, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v35, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v36, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v40, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v42, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v4, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v45, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v6, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v49, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v50, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v51, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v52, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v53, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v54, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v55, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v57, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v58, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v59, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v60, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v61, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v62, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v63, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v64, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v65, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v66, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v67, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v68, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v69, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v70, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v71, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v72, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v73, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v74, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v75, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v76, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v77, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v78, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v52, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v43, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v81, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v82, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v83, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v85, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String encodeForUri(java.lang.String r20, char r21) {
        /*
            r2 = r20
            r3 = r21
            java.lang.StringBuffer r15 = new java.lang.StringBuffer
            r19 = r15
            r15 = r19
            r16 = r19
            r16.<init>()
            r4 = r15
            r15 = r2
            int r15 = r15.length()
            r5 = r15
            r15 = 0
            r6 = r15
        L_0x0018:
            r15 = r6
            r16 = r5
            r0 = r16
            if (r15 >= r0) goto L_0x020a
            r15 = r2
            r16 = r6
            int r6 = r6 + 1
            char r15 = r15.charAt(r16)
            r7 = r15
            r15 = r7
            r16 = 55296(0xd800, float:7.7486E-41)
            r0 = r16
            if (r15 < r0) goto L_0x0060
            r15 = r7
            r16 = 56320(0xdc00, float:7.8921E-41)
            r0 = r16
            if (r15 >= r0) goto L_0x0060
            r15 = r6
            r16 = r5
            r0 = r16
            if (r15 >= r0) goto L_0x0060
            r15 = r7
            r16 = 55296(0xd800, float:7.7486E-41)
            int r15 = r15 - r16
            r16 = 1024(0x400, float:1.435E-42)
            int r15 = r15 * 1024
            r16 = r2
            r17 = r6
            int r6 = r6 + 1
            char r16 = r16.charAt(r17)
            r17 = 56320(0xdc00, float:7.8921E-41)
            int r16 = r16 - r17
            int r15 = r15 + r16
            r16 = 65536(0x10000, float:9.18355E-41)
            int r15 = r15 + r16
            r7 = r15
        L_0x0060:
            r15 = r3
            r16 = 72
            r0 = r16
            if (r15 != r0) goto L_0x0082
            r15 = r7
            r16 = 32
            r0 = r16
            if (r15 < r0) goto L_0x0154
            r15 = r7
            r16 = 126(0x7e, float:1.77E-43)
            r0 = r16
            if (r15 > r0) goto L_0x0154
        L_0x0075:
            r15 = r4
            r16 = r7
            r0 = r16
            char r0 = (char) r0
            r16 = r0
            java.lang.StringBuffer r15 = r15.append(r16)
        L_0x0081:
            goto L_0x0018
        L_0x0082:
            r15 = r7
            r16 = 97
            r0 = r16
            if (r15 < r0) goto L_0x0090
            r15 = r7
            r16 = 122(0x7a, float:1.71E-43)
            r0 = r16
            if (r15 <= r0) goto L_0x0075
        L_0x0090:
            r15 = r7
            r16 = 65
            r0 = r16
            if (r15 < r0) goto L_0x009e
            r15 = r7
            r16 = 90
            r0 = r16
            if (r15 <= r0) goto L_0x0075
        L_0x009e:
            r15 = r7
            r16 = 48
            r0 = r16
            if (r15 < r0) goto L_0x00ac
            r15 = r7
            r16 = 57
            r0 = r16
            if (r15 <= r0) goto L_0x0075
        L_0x00ac:
            r15 = r7
            r16 = 45
            r0 = r16
            if (r15 == r0) goto L_0x0075
            r15 = r7
            r16 = 95
            r0 = r16
            if (r15 == r0) goto L_0x0075
            r15 = r7
            r16 = 46
            r0 = r16
            if (r15 == r0) goto L_0x0075
            r15 = r7
            r16 = 126(0x7e, float:1.77E-43)
            r0 = r16
            if (r15 == r0) goto L_0x0075
            r15 = r3
            r16 = 73
            r0 = r16
            if (r15 != r0) goto L_0x0154
            r15 = r7
            r16 = 59
            r0 = r16
            if (r15 == r0) goto L_0x0075
            r15 = r7
            r16 = 47
            r0 = r16
            if (r15 == r0) goto L_0x0075
            r15 = r7
            r16 = 63
            r0 = r16
            if (r15 == r0) goto L_0x0075
            r15 = r7
            r16 = 58
            r0 = r16
            if (r15 == r0) goto L_0x0075
            r15 = r7
            r16 = 42
            r0 = r16
            if (r15 == r0) goto L_0x0075
            r15 = r7
            r16 = 39
            r0 = r16
            if (r15 == r0) goto L_0x0075
            r15 = r7
            r16 = 40
            r0 = r16
            if (r15 == r0) goto L_0x0075
            r15 = r7
            r16 = 41
            r0 = r16
            if (r15 == r0) goto L_0x0075
            r15 = r7
            r16 = 64
            r0 = r16
            if (r15 == r0) goto L_0x0075
            r15 = r7
            r16 = 38
            r0 = r16
            if (r15 == r0) goto L_0x0075
            r15 = r7
            r16 = 61
            r0 = r16
            if (r15 == r0) goto L_0x0075
            r15 = r7
            r16 = 43
            r0 = r16
            if (r15 == r0) goto L_0x0075
            r15 = r7
            r16 = 36
            r0 = r16
            if (r15 == r0) goto L_0x0075
            r15 = r7
            r16 = 44
            r0 = r16
            if (r15 == r0) goto L_0x0075
            r15 = r7
            r16 = 91
            r0 = r16
            if (r15 == r0) goto L_0x0075
            r15 = r7
            r16 = 93
            r0 = r16
            if (r15 == r0) goto L_0x0075
            r15 = r7
            r16 = 35
            r0 = r16
            if (r15 == r0) goto L_0x0075
            r15 = r7
            r16 = 33
            r0 = r16
            if (r15 == r0) goto L_0x0075
            r15 = r7
            r16 = 37
            r0 = r16
            if (r15 == r0) goto L_0x0075
        L_0x0154:
            r15 = r4
            int r15 = r15.length()
            r8 = r15
            r15 = 0
            r9 = r15
            r15 = r7
            r16 = 128(0x80, float:1.794E-43)
            r0 = r16
            if (r15 >= r0) goto L_0x01c5
            r15 = 1
        L_0x0164:
            r10 = r15
        L_0x0165:
            r15 = r9
            if (r15 != 0) goto L_0x01d9
            r15 = 7
        L_0x0169:
            r11 = r15
            r15 = r7
            r16 = 1
            r17 = r11
            int r16 = r16 << r17
            r0 = r16
            if (r15 >= r0) goto L_0x01df
            r15 = r7
            r12 = r15
            r15 = r9
            if (r15 <= 0) goto L_0x018d
            r15 = r12
            r16 = 65408(0xff80, float:9.1656E-41)
            r17 = r9
            int r16 = r16 >> r17
            r17 = 255(0xff, float:3.57E-43)
            r0 = r16
            r0 = r0 & 255(0xff, float:3.57E-43)
            r16 = r0
            r15 = r15 | r16
            r12 = r15
        L_0x018d:
            r15 = 0
            r7 = r15
        L_0x018f:
            int r9 = r9 + 1
            r15 = 0
            r13 = r15
        L_0x0193:
            r15 = r13
            r16 = 1
            r0 = r16
            if (r15 > r0) goto L_0x01fc
            r15 = r12
            r16 = 15
            r15 = r15 & 15
            r14 = r15
            r15 = r4
            r16 = r8
            r17 = r14
            r18 = 9
            r0 = r17
            r1 = r18
            if (r0 > r1) goto L_0x01f1
            r17 = r14
            r18 = 48
            int r17 = r17 + 48
        L_0x01b3:
            r0 = r17
            char r0 = (char) r0
            r17 = r0
            java.lang.StringBuffer r15 = r15.insert(r16, r17)
            r15 = r12
            r16 = 4
            int r15 = r15 >> 4
            r12 = r15
            int r13 = r13 + 1
            goto L_0x0193
        L_0x01c5:
            r15 = r7
            r16 = 2048(0x800, float:2.87E-42)
            r0 = r16
            if (r15 >= r0) goto L_0x01ce
            r15 = 2
            goto L_0x0164
        L_0x01ce:
            r15 = r7
            r16 = 65536(0x10000, float:9.18355E-41)
            r0 = r16
            if (r15 >= r0) goto L_0x01d7
            r15 = 3
            goto L_0x0164
        L_0x01d7:
            r15 = 4
            goto L_0x0164
        L_0x01d9:
            r15 = 6
            r16 = r9
            int r15 = 6 - r16
            goto L_0x0169
        L_0x01df:
            r15 = 128(0x80, float:1.794E-43)
            r16 = r7
            r17 = 63
            r16 = r16 & 63
            r15 = r15 | r16
            r12 = r15
            r15 = r7
            r16 = 6
            int r15 = r15 >> 6
            r7 = r15
            goto L_0x018f
        L_0x01f1:
            r17 = r14
            r18 = 10
            int r17 = r17 + -10
            r18 = 65
            int r17 = r17 + 65
            goto L_0x01b3
        L_0x01fc:
            r15 = r4
            r16 = r8
            r17 = 37
            java.lang.StringBuffer r15 = r15.insert(r16, r17)
            r15 = r7
            if (r15 != 0) goto L_0x0165
            goto L_0x0081
        L_0x020a:
            r15 = r4
            java.lang.String r15 = r15.toString()
            r2 = r15
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.URIPath.encodeForUri(java.lang.String, char):java.lang.String");
    }
}
