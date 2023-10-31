package gnu.text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class ResourceStreamHandler extends URLStreamHandler {
    public static final String CLASS_RESOURCE_URI_PREFIX = "class-resource:/";
    public static final int CLASS_RESOURCE_URI_PREFIX_LENGTH = 16;
    ClassLoader cloader;

    public ResourceStreamHandler(ClassLoader cloader2) {
        this.cloader = cloader2;
    }

    public static URL makeURL(Class cls) throws MalformedURLException {
        StringBuilder sb;
        URL url;
        URLStreamHandler uRLStreamHandler;
        Class clas = cls;
        String cname = clas.getName();
        int dot = cname.lastIndexOf(46);
        new StringBuilder();
        StringBuilder sbuf = sb;
        StringBuilder append = sbuf.append(CLASS_RESOURCE_URI_PREFIX);
        if (dot >= 0) {
            StringBuilder append2 = sbuf.append(cname.substring(0, dot));
            StringBuilder append3 = sbuf.append('/');
            cname = cname.substring(dot + 1);
        }
        StringBuilder append4 = sbuf.append(cname);
        String str = sbuf.toString();
        new ResourceStreamHandler(clas.getClassLoader());
        new URL((URL) null, str, uRLStreamHandler);
        return url;
    }

    public URLConnection openConnection(URL u) throws IOException {
        Throwable th;
        StringBuilder sb;
        String ustr = u.toString();
        String rstr = ustr.substring(16);
        int sl = rstr.indexOf(47);
        if (sl > 0) {
            new StringBuilder();
            rstr = sb.append(rstr.substring(0, sl).replace('.', '/')).append(rstr.substring(sl)).toString();
        }
        URL url = this.cloader.getResource(rstr);
        if (url != null) {
            return url.openConnection();
        }
        Throwable th2 = th;
        new FileNotFoundException(ustr);
        throw th2;
    }
}
