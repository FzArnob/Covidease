package org.shaded.apache.http.conn.ssl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.conn.ConnectTimeoutException;
import org.shaded.apache.http.conn.scheme.HostNameResolver;
import org.shaded.apache.http.conn.scheme.LayeredSocketFactory;
import org.shaded.apache.http.params.HttpConnectionParams;
import org.shaded.apache.http.params.HttpParams;

@NotThreadSafe
public class SSLSocketFactory implements LayeredSocketFactory {
    public static final X509HostnameVerifier ALLOW_ALL_HOSTNAME_VERIFIER;
    public static final X509HostnameVerifier BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
    private static final SSLSocketFactory DEFAULT_FACTORY;
    public static final String SSL = "SSL";
    public static final String SSLV2 = "SSLv2";
    public static final X509HostnameVerifier STRICT_HOSTNAME_VERIFIER;
    public static final String TLS = "TLS";
    private volatile X509HostnameVerifier hostnameVerifier;
    private final HostNameResolver nameResolver;
    private final javax.net.ssl.SSLSocketFactory socketfactory;
    private final SSLContext sslcontext;

    static {
        X509HostnameVerifier x509HostnameVerifier;
        X509HostnameVerifier x509HostnameVerifier2;
        X509HostnameVerifier x509HostnameVerifier3;
        SSLSocketFactory sSLSocketFactory;
        new AllowAllHostnameVerifier();
        ALLOW_ALL_HOSTNAME_VERIFIER = x509HostnameVerifier;
        new BrowserCompatHostnameVerifier();
        BROWSER_COMPATIBLE_HOSTNAME_VERIFIER = x509HostnameVerifier2;
        new StrictHostnameVerifier();
        STRICT_HOSTNAME_VERIFIER = x509HostnameVerifier3;
        new SSLSocketFactory();
        DEFAULT_FACTORY = sSLSocketFactory;
    }

    public static SSLSocketFactory getSocketFactory() {
        return DEFAULT_FACTORY;
    }

    public SSLSocketFactory(String str, KeyStore keyStore, String str2, KeyStore keyStore2, SecureRandom secureRandom, HostNameResolver hostNameResolver) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        String algorithm = str;
        KeyStore keystore = keyStore;
        String keystorePassword = str2;
        KeyStore truststore = keyStore2;
        SecureRandom random = secureRandom;
        HostNameResolver nameResolver2 = hostNameResolver;
        this.hostnameVerifier = BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
        algorithm = algorithm == null ? TLS : algorithm;
        KeyManager[] keymanagers = keystore != null ? createKeyManagers(keystore, keystorePassword) : null;
        TrustManager[] trustmanagers = truststore != null ? createTrustManagers(truststore) : null;
        this.sslcontext = SSLContext.getInstance(algorithm);
        this.sslcontext.init(keymanagers, trustmanagers, random);
        this.socketfactory = this.sslcontext.getSocketFactory();
        this.nameResolver = nameResolver2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SSLSocketFactory(KeyStore keystore, String keystorePassword, KeyStore truststore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        this(TLS, keystore, keystorePassword, truststore, (SecureRandom) null, (HostNameResolver) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SSLSocketFactory(KeyStore keystore, String keystorePassword) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        this(TLS, keystore, keystorePassword, (KeyStore) null, (SecureRandom) null, (HostNameResolver) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SSLSocketFactory(KeyStore truststore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        this(TLS, (KeyStore) null, (String) null, truststore, (SecureRandom) null, (HostNameResolver) null);
    }

    public SSLSocketFactory(SSLContext sslContext, HostNameResolver nameResolver2) {
        this.hostnameVerifier = BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
        this.sslcontext = sslContext;
        this.socketfactory = this.sslcontext.getSocketFactory();
        this.nameResolver = nameResolver2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SSLSocketFactory(SSLContext sslContext) {
        this(sslContext, (HostNameResolver) null);
    }

    private SSLSocketFactory() {
        this.hostnameVerifier = BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
        this.sslcontext = null;
        this.socketfactory = HttpsURLConnection.getDefaultSSLSocketFactory();
        this.nameResolver = null;
    }

    private static KeyManager[] createKeyManagers(KeyStore keyStore, String str) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        Throwable th;
        KeyStore keystore = keyStore;
        String password = str;
        if (keystore == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Keystore may not be null");
            throw th2;
        }
        KeyManagerFactory kmfactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmfactory.init(keystore, password != null ? password.toCharArray() : null);
        return kmfactory.getKeyManagers();
    }

    private static TrustManager[] createTrustManagers(KeyStore keyStore) throws KeyStoreException, NoSuchAlgorithmException {
        Throwable th;
        KeyStore keystore = keyStore;
        if (keystore == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Keystore may not be null");
            throw th2;
        }
        TrustManagerFactory tmfactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmfactory.init(keystore);
        return tmfactory.getTrustManagers();
    }

    public Socket createSocket() throws IOException {
        return (SSLSocket) this.socketfactory.createSocket();
    }

    public Socket connectSocket(Socket socket, String str, int i, InetAddress inetAddress, int i2, HttpParams httpParams) throws IOException {
        Socket createSocket;
        InetSocketAddress inetSocketAddress;
        InetSocketAddress remoteAddress;
        Throwable th;
        StringBuilder sb;
        InetSocketAddress inetSocketAddress2;
        SocketAddress socketAddress;
        Throwable th2;
        Throwable th3;
        Socket sock = socket;
        String host = str;
        int port = i;
        InetAddress localAddress = inetAddress;
        int localPort = i2;
        HttpParams params = httpParams;
        if (host == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("Target host may not be null.");
            throw th4;
        } else if (params == null) {
            Throwable th5 = th2;
            new IllegalArgumentException("Parameters may not be null.");
            throw th5;
        } else {
            if (sock != null) {
                createSocket = sock;
            } else {
                createSocket = createSocket();
            }
            SSLSocket sslsock = (SSLSocket) createSocket;
            if (localAddress != null || localPort > 0) {
                if (localPort < 0) {
                    localPort = 0;
                }
                new InetSocketAddress(localAddress, localPort);
                sslsock.bind(socketAddress);
            }
            int connTimeout = HttpConnectionParams.getConnectionTimeout(params);
            int soTimeout = HttpConnectionParams.getSoTimeout(params);
            if (this.nameResolver != null) {
                new InetSocketAddress(this.nameResolver.resolve(host), port);
                remoteAddress = inetSocketAddress2;
            } else {
                new InetSocketAddress(host, port);
                remoteAddress = inetSocketAddress;
            }
            try {
                sslsock.connect(remoteAddress, connTimeout);
                sslsock.setSoTimeout(soTimeout);
                try {
                    this.hostnameVerifier.verify(host, sslsock);
                    return sslsock;
                } catch (IOException e) {
                    IOException iox = e;
                    try {
                        sslsock.close();
                    } catch (Exception e2) {
                        Exception exc = e2;
                    }
                    throw iox;
                }
            } catch (SocketTimeoutException e3) {
                SocketTimeoutException socketTimeoutException = e3;
                Throwable th6 = th;
                new StringBuilder();
                new ConnectTimeoutException(sb.append("Connect to ").append(remoteAddress).append(" timed out").toString());
                throw th6;
            }
        }
    }

    public boolean isSecure(Socket socket) throws IllegalArgumentException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Socket sock = socket;
        if (sock == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("Socket may not be null.");
            throw th4;
        } else if (!(sock instanceof SSLSocket)) {
            Throwable th5 = th2;
            new IllegalArgumentException("Socket not created by this factory.");
            throw th5;
        } else if (!sock.isClosed()) {
            return true;
        } else {
            Throwable th6 = th;
            new IllegalArgumentException("Socket is closed.");
            throw th6;
        }
    }

    public Socket createSocket(Socket socket, String str, int port, boolean autoClose) throws IOException, UnknownHostException {
        String host = str;
        SSLSocket sslSocket = (SSLSocket) this.socketfactory.createSocket(socket, host, port, autoClose);
        this.hostnameVerifier.verify(host, sslSocket);
        return sslSocket;
    }

    public void setHostnameVerifier(X509HostnameVerifier x509HostnameVerifier) {
        Throwable th;
        X509HostnameVerifier hostnameVerifier2 = x509HostnameVerifier;
        if (hostnameVerifier2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Hostname verifier may not be null");
            throw th2;
        }
        this.hostnameVerifier = hostnameVerifier2;
    }

    public X509HostnameVerifier getHostnameVerifier() {
        return this.hostnameVerifier;
    }
}
