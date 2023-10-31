package org.shaded.apache.http.impl.auth;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.NameValuePair;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.auth.AUTH;
import org.shaded.apache.http.auth.AuthenticationException;
import org.shaded.apache.http.auth.Credentials;
import org.shaded.apache.http.auth.MalformedChallengeException;
import org.shaded.apache.http.auth.params.AuthParams;
import org.shaded.apache.http.message.BasicHeaderValueFormatter;
import org.shaded.apache.http.message.BasicNameValuePair;
import org.shaded.apache.http.message.BufferedHeader;
import org.shaded.apache.http.util.CharArrayBuffer;
import org.shaded.apache.http.util.EncodingUtils;

@NotThreadSafe
public class DigestScheme extends RFC2617Scheme {
    private static final char[] HEXADECIMAL = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: NC */
    private static final String f290NC = "00000001";
    private static final int QOP_AUTH = 2;
    private static final int QOP_AUTH_INT = 1;
    private static final int QOP_MISSING = 0;
    private String cnonce;
    private boolean complete = false;
    private int qopVariant = 0;

    public DigestScheme() {
    }

    public void processChallenge(Header header) throws MalformedChallengeException {
        Throwable th;
        StringTokenizer stringTokenizer;
        Throwable th2;
        Throwable th3;
        super.processChallenge(header);
        if (getParameter("realm") == null) {
            Throwable th4 = th3;
            new MalformedChallengeException("missing realm in challange");
            throw th4;
        } else if (getParameter("nonce") == null) {
            Throwable th5 = th2;
            new MalformedChallengeException("missing nonce in challange");
            throw th5;
        } else {
            boolean unsupportedQop = false;
            String qop = getParameter("qop");
            if (qop != null) {
                new StringTokenizer(qop, ",");
                StringTokenizer tok = stringTokenizer;
                while (true) {
                    if (!tok.hasMoreTokens()) {
                        break;
                    }
                    String variant = tok.nextToken().trim();
                    if (variant.equals("auth")) {
                        this.qopVariant = 2;
                        break;
                    } else if (variant.equals("auth-int")) {
                        this.qopVariant = 1;
                    } else {
                        unsupportedQop = true;
                    }
                }
            }
            if (!unsupportedQop || this.qopVariant != 0) {
                this.cnonce = null;
                this.complete = true;
                return;
            }
            Throwable th6 = th;
            new MalformedChallengeException("None of the qop methods is supported");
            throw th6;
        }
    }

    public boolean isComplete() {
        if ("true".equalsIgnoreCase(getParameter("stale"))) {
            return false;
        }
        return this.complete;
    }

    public String getSchemeName() {
        return "digest";
    }

    public boolean isConnectionBased() {
        return false;
    }

    public void overrideParamter(String name, String value) {
        String put = getParameters().put(name, value);
    }

    private String getCnonce() {
        if (this.cnonce == null) {
            this.cnonce = createCnonce();
        }
        return this.cnonce;
    }

    public Header authenticate(Credentials credentials, HttpRequest httpRequest) throws AuthenticationException {
        Throwable th;
        Throwable th2;
        Credentials credentials2 = credentials;
        HttpRequest request = httpRequest;
        if (credentials2 == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Credentials may not be null");
            throw th3;
        } else if (request == null) {
            Throwable th4 = th;
            new IllegalArgumentException("HTTP request may not be null");
            throw th4;
        } else {
            String put = getParameters().put("methodname", request.getRequestLine().getMethod());
            String put2 = getParameters().put("uri", request.getRequestLine().getUri());
            if (getParameter("charset") == null) {
                String put3 = getParameters().put("charset", AuthParams.getCredentialCharset(request.getParams()));
            }
            return createDigestHeader(credentials2, createDigest(credentials2));
        }
    }

    private static MessageDigest createMessageDigest(String str) throws UnsupportedDigestAlgorithmException {
        Throwable th;
        StringBuilder sb;
        String digAlg = str;
        try {
            return MessageDigest.getInstance(digAlg);
        } catch (Exception e) {
            Exception exc = e;
            Throwable th2 = th;
            new StringBuilder();
            new UnsupportedDigestAlgorithmException(sb.append("Unsupported algorithm in HTTP Digest authentication: ").append(digAlg).toString());
            throw th2;
        }
    }

    private String createDigest(Credentials credentials) throws AuthenticationException {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        String serverDigestValue;
        StringBuilder sb4;
        StringBuilder sb5;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Credentials credentials2 = credentials;
        String uri = getParameter("uri");
        String realm = getParameter("realm");
        String nonce = getParameter("nonce");
        String method = getParameter("methodname");
        String algorithm = getParameter("algorithm");
        if (uri == null) {
            Throwable th5 = th4;
            new IllegalStateException("URI may not be null");
            throw th5;
        } else if (realm == null) {
            Throwable th6 = th3;
            new IllegalStateException("Realm may not be null");
            throw th6;
        } else if (nonce == null) {
            Throwable th7 = th2;
            new IllegalStateException("Nonce may not be null");
            throw th7;
        } else {
            if (algorithm == null) {
                algorithm = "MD5";
            }
            String charset = getParameter("charset");
            if (charset == null) {
                charset = "ISO-8859-1";
            }
            if (this.qopVariant == 1) {
                Throwable th8 = th;
                new AuthenticationException("Unsupported qop in HTTP Digest authentication");
                throw th8;
            }
            String digAlg = algorithm;
            if (digAlg.equalsIgnoreCase("MD5-sess")) {
                digAlg = "MD5";
            }
            MessageDigest digester = createMessageDigest(digAlg);
            String uname = credentials2.getUserPrincipal().getName();
            String pwd = credentials2.getPassword();
            new StringBuilder(uname.length() + realm.length() + pwd.length() + 2);
            StringBuilder tmp = sb;
            StringBuilder append = tmp.append(uname);
            StringBuilder append2 = tmp.append(':');
            StringBuilder append3 = tmp.append(realm);
            StringBuilder append4 = tmp.append(':');
            StringBuilder append5 = tmp.append(pwd);
            String a1 = tmp.toString();
            if (algorithm.equalsIgnoreCase("MD5-sess")) {
                String algorithm2 = "MD5";
                String cnonce2 = getCnonce();
                String tmp2 = encode(digester.digest(EncodingUtils.getBytes(a1, charset)));
                new StringBuilder(tmp2.length() + nonce.length() + cnonce2.length() + 2);
                StringBuilder tmp3 = sb5;
                StringBuilder append6 = tmp3.append(tmp2);
                StringBuilder append7 = tmp3.append(':');
                StringBuilder append8 = tmp3.append(nonce);
                StringBuilder append9 = tmp3.append(':');
                StringBuilder append10 = tmp3.append(cnonce2);
                a1 = tmp3.toString();
            }
            String hasha1 = encode(digester.digest(EncodingUtils.getBytes(a1, charset)));
            String a2 = null;
            if (this.qopVariant != 1) {
                new StringBuilder();
                a2 = sb2.append(method).append(':').append(uri).toString();
            }
            String hasha2 = encode(digester.digest(EncodingUtils.getAsciiBytes(a2)));
            if (this.qopVariant == 0) {
                new StringBuilder(hasha1.length() + nonce.length() + hasha1.length());
                StringBuilder tmp22 = sb4;
                StringBuilder append11 = tmp22.append(hasha1);
                StringBuilder append12 = tmp22.append(':');
                StringBuilder append13 = tmp22.append(nonce);
                StringBuilder append14 = tmp22.append(':');
                StringBuilder append15 = tmp22.append(hasha2);
                serverDigestValue = tmp22.toString();
            } else {
                String qopOption = getQopVariantString();
                String cnonce3 = getCnonce();
                new StringBuilder(hasha1.length() + nonce.length() + f290NC.length() + cnonce3.length() + qopOption.length() + hasha2.length() + 5);
                StringBuilder tmp23 = sb3;
                StringBuilder append16 = tmp23.append(hasha1);
                StringBuilder append17 = tmp23.append(':');
                StringBuilder append18 = tmp23.append(nonce);
                StringBuilder append19 = tmp23.append(':');
                StringBuilder append20 = tmp23.append(f290NC);
                StringBuilder append21 = tmp23.append(':');
                StringBuilder append22 = tmp23.append(cnonce3);
                StringBuilder append23 = tmp23.append(':');
                StringBuilder append24 = tmp23.append(qopOption);
                StringBuilder append25 = tmp23.append(':');
                StringBuilder append26 = tmp23.append(hasha2);
                serverDigestValue = tmp23.toString();
            }
            return encode(digester.digest(EncodingUtils.getAsciiBytes(serverDigestValue)));
        }
    }

    private Header createDigestHeader(Credentials credentials, String str) {
        CharArrayBuffer charArrayBuffer;
        List<BasicNameValuePair> list;
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Header header;
        boolean z;
        Object obj6;
        Object obj7;
        Object obj8;
        Object obj9;
        Object obj10;
        Credentials credentials2 = credentials;
        String digest = str;
        new CharArrayBuffer(128);
        CharArrayBuffer buffer = charArrayBuffer;
        if (isProxy()) {
            buffer.append(AUTH.PROXY_AUTH_RESP);
        } else {
            buffer.append(AUTH.WWW_AUTH_RESP);
        }
        buffer.append(": Digest ");
        String uri = getParameter("uri");
        String realm = getParameter("realm");
        String nonce = getParameter("nonce");
        String opaque = getParameter("opaque");
        String algorithm = getParameter("algorithm");
        String uname = credentials2.getUserPrincipal().getName();
        new ArrayList<>(20);
        List<BasicNameValuePair> params = list;
        new BasicNameValuePair("username", uname);
        boolean add = params.add(obj);
        new BasicNameValuePair("realm", realm);
        boolean add2 = params.add(obj2);
        new BasicNameValuePair("nonce", nonce);
        boolean add3 = params.add(obj3);
        new BasicNameValuePair("uri", uri);
        boolean add4 = params.add(obj4);
        new BasicNameValuePair("response", digest);
        boolean add5 = params.add(obj5);
        if (this.qopVariant != 0) {
            new BasicNameValuePair("qop", getQopVariantString());
            boolean add6 = params.add(obj8);
            new BasicNameValuePair("nc", f290NC);
            boolean add7 = params.add(obj9);
            new BasicNameValuePair("cnonce", getCnonce());
            boolean add8 = params.add(obj10);
        }
        if (algorithm != null) {
            new BasicNameValuePair("algorithm", algorithm);
            boolean add9 = params.add(obj7);
        }
        if (opaque != null) {
            new BasicNameValuePair("opaque", opaque);
            boolean add10 = params.add(obj6);
        }
        for (int i = 0; i < params.size(); i++) {
            BasicNameValuePair param = params.get(i);
            if (i > 0) {
                buffer.append(", ");
            }
            boolean noQuotes = "nc".equals(param.getName()) || "qop".equals(param.getName());
            BasicHeaderValueFormatter basicHeaderValueFormatter = BasicHeaderValueFormatter.DEFAULT;
            CharArrayBuffer charArrayBuffer2 = buffer;
            BasicNameValuePair basicNameValuePair = param;
            if (!noQuotes) {
                z = true;
            } else {
                z = false;
            }
            CharArrayBuffer formatNameValuePair = basicHeaderValueFormatter.formatNameValuePair(charArrayBuffer2, (NameValuePair) basicNameValuePair, z);
        }
        new BufferedHeader(buffer);
        return header;
    }

    private String getQopVariantString() {
        String qopOption;
        if (this.qopVariant == 1) {
            qopOption = "auth-int";
        } else {
            qopOption = "auth";
        }
        return qopOption;
    }

    private static String encode(byte[] bArr) {
        String str;
        byte[] binaryData = bArr;
        int n = binaryData.length;
        char[] buffer = new char[(n * 2)];
        for (int i = 0; i < n; i++) {
            int low = binaryData[i] & 15;
            buffer[i * 2] = HEXADECIMAL[(binaryData[i] & 240) >> 4];
            buffer[(i * 2) + 1] = HEXADECIMAL[low];
        }
        new String(buffer);
        return str;
    }

    public static String createCnonce() {
        return encode(createMessageDigest("MD5").digest(EncodingUtils.getAsciiBytes(Long.toString(System.currentTimeMillis()))));
    }
}
