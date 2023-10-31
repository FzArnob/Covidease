package org.shaded.apache.http.conn.ssl;

import java.io.IOException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.conn.util.InetAddressUtils;

@Immutable
public abstract class AbstractVerifier implements X509HostnameVerifier {
    private static final String[] BAD_COUNTRY_2LDS;

    static {
        String[] strArr = new String[14];
        strArr[0] = "ac";
        String[] strArr2 = strArr;
        strArr2[1] = "co";
        String[] strArr3 = strArr2;
        strArr3[2] = "com";
        String[] strArr4 = strArr3;
        strArr4[3] = "ed";
        String[] strArr5 = strArr4;
        strArr5[4] = "edu";
        String[] strArr6 = strArr5;
        strArr6[5] = "go";
        String[] strArr7 = strArr6;
        strArr7[6] = "gouv";
        String[] strArr8 = strArr7;
        strArr8[7] = "gov";
        String[] strArr9 = strArr8;
        strArr9[8] = "info";
        String[] strArr10 = strArr9;
        strArr10[9] = "lg";
        String[] strArr11 = strArr10;
        strArr11[10] = "ne";
        String[] strArr12 = strArr11;
        strArr12[11] = "net";
        String[] strArr13 = strArr12;
        strArr13[12] = "or";
        String[] strArr14 = strArr13;
        strArr14[13] = "org";
        BAD_COUNTRY_2LDS = strArr14;
        Arrays.sort(BAD_COUNTRY_2LDS);
    }

    public AbstractVerifier() {
    }

    public final void verify(String str, SSLSocket sSLSocket) throws IOException {
        Throwable th;
        String host = str;
        SSLSocket ssl = sSLSocket;
        if (host == null) {
            Throwable th2 = th;
            new NullPointerException("host to verify is null");
            throw th2;
        }
        SSLSession session = ssl.getSession();
        if (session == null) {
            int available = ssl.getInputStream().available();
            session = ssl.getSession();
            if (session == null) {
                ssl.startHandshake();
                session = ssl.getSession();
            }
        }
        verify(host, (X509Certificate) session.getPeerCertificates()[0]);
    }

    public final boolean verify(String str, SSLSession session) {
        String host = str;
        try {
            verify(host, (X509Certificate) session.getPeerCertificates()[0]);
            return true;
        } catch (SSLException e) {
            SSLException sSLException = e;
            return false;
        }
    }

    public final void verify(String str, X509Certificate x509Certificate) throws SSLException {
        String host = str;
        X509Certificate cert = x509Certificate;
        verify(host, getCNs(cert), getSubjectAlts(cert, host));
    }

    public final void verify(String str, String[] strArr, String[] strArr2, boolean z) throws SSLException {
        LinkedList linkedList;
        StringBuffer stringBuffer;
        Throwable th;
        StringBuilder sb;
        StringBuilder sb2;
        Throwable th2;
        String host = str;
        String[] cns = strArr;
        String[] subjectAlts = strArr2;
        boolean strictWithSubDomains = z;
        new LinkedList();
        LinkedList linkedList2 = linkedList;
        if (!(cns == null || cns.length <= 0 || cns[0] == null)) {
            boolean add = linkedList2.add(cns[0]);
        }
        if (subjectAlts != null) {
            String[] arr$ = subjectAlts;
            int len$ = arr$.length;
            for (int i$ = 0; i$ < len$; i$++) {
                String subjectAlt = arr$[i$];
                if (subjectAlt != null) {
                    boolean add2 = linkedList2.add(subjectAlt);
                }
            }
        }
        if (linkedList2.isEmpty()) {
            new StringBuilder();
            Throwable th3 = th2;
            new SSLException(sb2.append("Certificate for <").append(host).append("> doesn't contain CN or DNS subjectAlt").toString());
            throw th3;
        }
        new StringBuffer();
        StringBuffer buf = stringBuffer;
        String hostName = host.trim().toLowerCase(Locale.ENGLISH);
        boolean match = false;
        Iterator<String> it = linkedList2.iterator();
        while (it.hasNext()) {
            String cn = it.next().toLowerCase(Locale.ENGLISH);
            StringBuffer append = buf.append(" <");
            StringBuffer append2 = buf.append(cn);
            StringBuffer append3 = buf.append('>');
            if (it.hasNext()) {
                StringBuffer append4 = buf.append(" OR");
            }
            if (cn.startsWith("*.") && cn.lastIndexOf(46) >= 0 && acceptableCountryWildcard(cn) && !isIPAddress(host)) {
                match = hostName.endsWith(cn.substring(1));
                if (match && strictWithSubDomains) {
                    match = countDots(hostName) == countDots(cn);
                }
            } else {
                match = hostName.equals(cn);
            }
            if (match) {
                break;
            }
        }
        if (!match) {
            Throwable th4 = th;
            new StringBuilder();
            new SSLException(sb.append("hostname in certificate didn't match: <").append(host).append("> !=").append(buf).toString());
            throw th4;
        }
    }

    public static boolean acceptableCountryWildcard(String str) {
        String cn = str;
        int cnLen = cn.length();
        if (cnLen < 7 || cnLen > 9 || cn.charAt(cnLen - 3) != '.') {
            return true;
        }
        return Arrays.binarySearch(BAD_COUNTRY_2LDS, cn.substring(2, cnLen + -3)) < 0;
    }

    public static String[] getCNs(X509Certificate cert) {
        LinkedList linkedList;
        StringTokenizer stringTokenizer;
        new LinkedList();
        LinkedList linkedList2 = linkedList;
        new StringTokenizer(cert.getSubjectX500Principal().toString(), ",");
        StringTokenizer st = stringTokenizer;
        while (st.hasMoreTokens()) {
            String tok = st.nextToken();
            int x = tok.indexOf("CN=");
            if (x >= 0) {
                boolean add = linkedList2.add(tok.substring(x + 3));
            }
        }
        if (linkedList2.isEmpty()) {
            return null;
        }
        String[] cns = new String[linkedList2.size()];
        Object[] array = linkedList2.toArray(cns);
        return cns;
    }

    private static String[] getSubjectAlts(X509Certificate x509Certificate, String hostname) {
        int subjectType;
        LinkedList linkedList;
        X509Certificate cert = x509Certificate;
        if (isIPAddress(hostname)) {
            subjectType = 7;
        } else {
            subjectType = 2;
        }
        new LinkedList();
        LinkedList linkedList2 = linkedList;
        Collection<List<?>> c = null;
        try {
            c = cert.getSubjectAlternativeNames();
        } catch (CertificateParsingException e) {
            Logger.getLogger(AbstractVerifier.class.getName()).log(Level.FINE, "Error parsing certificate.", e);
        }
        if (c != null) {
            for (List<?> list : c) {
                if (((Integer) list.get(0)).intValue() == subjectType) {
                    boolean add = linkedList2.add((String) list.get(1));
                }
            }
        }
        if (linkedList2.isEmpty()) {
            return null;
        }
        String[] subjectAlts = new String[linkedList2.size()];
        Object[] array = linkedList2.toArray(subjectAlts);
        return subjectAlts;
    }

    public static String[] getDNSSubjectAlts(X509Certificate cert) {
        return getSubjectAlts(cert, (String) null);
    }

    public static int countDots(String str) {
        String s = str;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                count++;
            }
        }
        return count;
    }

    private static boolean isIPAddress(String str) {
        String hostname = str;
        return hostname != null && (InetAddressUtils.isIPv4Address(hostname) || InetAddressUtils.isIPv6Address(hostname));
    }
}
