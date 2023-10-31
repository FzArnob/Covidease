package com.google.appinventor.components.runtime.util;

import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.Log;
import com.google.appinventor.components.runtime.repackaged.org.json.HTTP;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.shaded.apache.http.client.methods.HttpPost;
import org.shaded.apache.http.client.methods.HttpPut;

public class NanoHTTPD {
    public static final String HTTP_BADREQUEST = "400 Bad Request";
    public static final String HTTP_FORBIDDEN = "403 Forbidden";
    public static final String HTTP_INTERNALERROR = "500 Internal Server Error";
    public static final String HTTP_NOTFOUND = "404 Not Found";
    public static final String HTTP_NOTIMPLEMENTED = "501 Not Implemented";
    public static final String HTTP_NOTMODIFIED = "304 Not Modified";
    public static final String HTTP_OK = "200 OK";
    public static final String HTTP_PARTIALCONTENT = "206 Partial Content";
    public static final String HTTP_RANGE_NOT_SATISFIABLE = "416 Requested Range Not Satisfiable";
    public static final String HTTP_REDIRECT = "301 Moved Permanently";
    public static final String MIME_DEFAULT_BINARY = "application/octet-stream";
    public static final String MIME_HTML = "text/html";
    public static final String MIME_PLAINTEXT = "text/plain";
    public static final String MIME_XML = "text/xml";
    /* access modifiers changed from: private */
    public static int QGAZOXcdjHKQSTylJ1PoRPU6GviAfBkJokC9i5wWel7BvaAua8hNDN1OOldV05g5 = 16384;
    /* access modifiers changed from: private */
    public static SimpleDateFormat hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private static Hashtable f568hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    protected static PrintStream myErr = System.err;
    protected static PrintStream myOut = System.out;
    private int VihNyRIYpiuVGhDxhZD9FEeZY0Q1YjvMZGuz6ngatU6E3Tfj3PPYt3ibIHEBq90c;
    private File hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private Thread f569hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final ServerSocket f570hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private ThreadPoolExecutor f571hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    public Response serve(String str, String str2, Properties properties, Properties properties2, Properties properties3, Socket socket) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        String str3 = str;
        Properties properties4 = properties;
        Properties properties5 = properties2;
        Properties properties6 = properties3;
        Socket socket2 = socket;
        PrintStream printStream = myOut;
        new StringBuilder();
        printStream.println(sb.append(str2).append(" '").append(str3).append("' ").toString());
        Enumeration<?> propertyNames = properties4.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String str4 = (String) propertyNames.nextElement();
            PrintStream printStream2 = myOut;
            new StringBuilder("  HDR: '");
            printStream2.println(sb4.append(str4).append("' = '").append(properties4.getProperty(str4)).append("'").toString());
        }
        Enumeration<?> propertyNames2 = properties5.propertyNames();
        while (propertyNames2.hasMoreElements()) {
            String str5 = (String) propertyNames2.nextElement();
            PrintStream printStream3 = myOut;
            new StringBuilder("  PRM: '");
            printStream3.println(sb3.append(str5).append("' = '").append(properties5.getProperty(str5)).append("'").toString());
        }
        Enumeration<?> propertyNames3 = properties6.propertyNames();
        while (propertyNames3.hasMoreElements()) {
            String str6 = (String) propertyNames3.nextElement();
            PrintStream printStream4 = myOut;
            new StringBuilder("  UPLOADED: '");
            printStream4.println(sb2.append(str6).append("' = '").append(properties6.getProperty(str6)).append("'").toString());
        }
        return serveFile(str3, properties4, this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO, true);
    }

    public class Response {
        public InputStream data;
        public Properties header;
        private /* synthetic */ NanoHTTPD hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        public String mimeType;
        public String status;

        public Response(NanoHTTPD nanoHTTPD) {
            Properties properties;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = nanoHTTPD;
            new Properties();
            this.header = properties;
            this.status = NanoHTTPD.HTTP_OK;
        }

        public Response(NanoHTTPD nanoHTTPD, String str, String str2, InputStream inputStream) {
            Properties properties;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = nanoHTTPD;
            new Properties();
            this.header = properties;
            this.status = str;
            this.mimeType = str2;
            this.data = inputStream;
        }

        public Response(NanoHTTPD nanoHTTPD, String str, String str2, String str3) {
            Properties properties;
            InputStream inputStream;
            String str4 = str3;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = nanoHTTPD;
            new Properties();
            this.header = properties;
            this.status = str;
            this.mimeType = str2;
            try {
                new ByteArrayInputStream(str4.getBytes("UTF-8"));
                this.data = inputStream;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        public void addHeader(String str, String str2) {
            Object put = this.header.put(str, str2);
        }
    }

    public NanoHTTPD(int i, File file) throws IOException {
        ThreadPoolExecutor threadPoolExecutor;
        BlockingQueue blockingQueue;
        ThreadFactory threadFactory;
        ServerSocket serverSocket;
        Thread thread;
        Runnable runnable;
        new SynchronousQueue();
        new C1183b(this, (byte) 0);
        new ThreadPoolExecutor(2, 10, 5, TimeUnit.SECONDS, blockingQueue, threadFactory);
        this.f571hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = threadPoolExecutor;
        this.VihNyRIYpiuVGhDxhZD9FEeZY0Q1YjvMZGuz6ngatU6E3Tfj3PPYt3ibIHEBq90c = i;
        this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = file;
        new ServerSocket(this.VihNyRIYpiuVGhDxhZD9FEeZY0Q1YjvMZGuz6ngatU6E3Tfj3PPYt3ibIHEBq90c);
        this.f570hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = serverSocket;
        new Runnable(this) {
            private /* synthetic */ NanoHTTPD hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void run() {
                while (true) {
                    try {
                        new C1182a(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, NanoHTTPD.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).accept());
                    } catch (IOException e) {
                        return;
                    }
                }
            }
        };
        new Thread(runnable);
        this.f569hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = thread;
        this.f569hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setDaemon(true);
        this.f569hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.start();
    }

    public void stop() {
        try {
            this.f570hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.close();
            this.f569hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.join();
        } catch (IOException | InterruptedException e) {
        }
    }

    public static void main(String[] strArr) {
        File file;
        StringBuilder sb;
        File file2;
        String[] strArr2 = strArr;
        myOut.println("NanoHTTPD 1.25 (C) 2001,2005-2011 Jarno Elonen and (C) 2010 Konstantinos Togias\n(Command line options: [-p port] [-d root-dir] [--licence])\n");
        int i = 80;
        new File(".");
        File absoluteFile = file.getAbsoluteFile();
        int i2 = 0;
        while (true) {
            if (i2 < strArr2.length) {
                if (strArr2[i2].equalsIgnoreCase("-p")) {
                    i = Integer.parseInt(strArr2[i2 + 1]);
                } else if (strArr2[i2].equalsIgnoreCase("-d")) {
                    new File(strArr2[i2 + 1]);
                    absoluteFile = file2.getAbsoluteFile();
                } else if (strArr2[i2].toLowerCase().endsWith("licence")) {
                    myOut.println("Copyright (C) 2001,2005-2011 by Jarno Elonen <elonen@iki.fi>\nand Copyright (C) 2010 by Konstantinos Togias <info@ktogias.gr>\n\nRedistribution and use in source and binary forms, with or without\nmodification, are permitted provided that the following conditions\nare met:\n\nRedistributions of source code must retain the above copyright notice,\nthis list of conditions and the following disclaimer. Redistributions in\nbinary form must reproduce the above copyright notice, this list of\nconditions and the following disclaimer in the documentation and/or other\nmaterials provided with the distribution. The name of the author may not\nbe used to endorse or promote products derived from this software without\nspecific prior written permission. \n \nTHIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR\nIMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES\nOF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.\nIN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,\nINCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT\nNOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,\nDATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY\nTHEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT\n(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE\nOF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.\n");
                    break;
                }
                i2++;
            }
        }
        try {
            new NanoHTTPD(i, absoluteFile);
        } catch (IOException e) {
            myErr.println("Couldn't start server:\n".concat(String.valueOf(e)));
            System.exit(-1);
        }
        PrintStream printStream = myOut;
        new StringBuilder("Now serving files in port ");
        printStream.println(sb.append(i).append(" from \"").append(absoluteFile).append("\"").toString());
        myOut.println("Hit Enter to stop.\n");
        try {
            int read = System.in.read();
        } catch (Throwable th) {
        }
    }

    /* renamed from: com.google.appinventor.components.runtime.util.NanoHTTPD$b */
    class C1183b implements ThreadFactory {
        private /* synthetic */ NanoHTTPD hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        private C1183b(NanoHTTPD nanoHTTPD) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = nanoHTTPD;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C1183b(NanoHTTPD nanoHTTPD, byte b) {
            this(nanoHTTPD);
            byte b2 = b;
        }

        public final Thread newThread(Runnable runnable) {
            Thread thread;
            ThreadGroup threadGroup;
            new ThreadGroup("biggerstack");
            new Thread(threadGroup, runnable, "HTTPD Session", PlaybackStateCompat.ACTION_SET_REPEAT_MODE);
            Thread thread2 = thread;
            thread2.setDaemon(true);
            return thread2;
        }
    }

    /* renamed from: com.google.appinventor.components.runtime.util.NanoHTTPD$a */
    class C1182a implements Runnable {
        private /* synthetic */ NanoHTTPD hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
        private Socket f572hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        public C1182a(NanoHTTPD nanoHTTPD, Socket socket) {
            StringBuilder sb;
            NanoHTTPD nanoHTTPD2 = nanoHTTPD;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = nanoHTTPD2;
            this.f572hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = socket;
            new StringBuilder("NanoHTTPD: getPoolSize() = ");
            int d = Log.d("AppInvHTTPD", sb.append(NanoHTTPD.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(nanoHTTPD2).getPoolSize()).toString());
            NanoHTTPD.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(nanoHTTPD2).execute(this);
        }

        public final void run() {
            StringBuilder sb;
            InputStream inputStream;
            BufferedReader bufferedReader;
            Reader reader;
            Properties properties;
            Properties properties2;
            Properties properties3;
            Properties properties4;
            OutputStream outputStream;
            OutputStream outputStream2;
            InputStream inputStream2;
            BufferedReader bufferedReader2;
            Reader reader2;
            StringTokenizer stringTokenizer;
            StringBuilder sb2;
            StringTokenizer stringTokenizer2;
            OutputStream outputStream3;
            try {
                InputStream inputStream3 = this.f572hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getInputStream();
                InputStream inputStream4 = inputStream3;
                if (inputStream3 != null) {
                    byte[] bArr = new byte[8192];
                    int read = inputStream4.read(bArr, 0, 8192);
                    int i = read;
                    if (read > 0) {
                        new ByteArrayInputStream(bArr, 0, i);
                        new InputStreamReader(inputStream);
                        new BufferedReader(reader);
                        new Properties();
                        Properties properties5 = properties;
                        new Properties();
                        Properties properties6 = properties2;
                        new Properties();
                        Properties properties7 = properties3;
                        new Properties();
                        Properties properties8 = properties4;
                        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(bufferedReader, properties5, properties6, properties7);
                        String property = properties5.getProperty("method");
                        String property2 = properties5.getProperty("uri");
                        long j = Long.MAX_VALUE;
                        String property3 = properties7.getProperty("content-length");
                        String str = property3;
                        if (property3 != null) {
                            try {
                                j = (long) Integer.parseInt(str);
                            } catch (NumberFormatException e) {
                            }
                        }
                        int i2 = 0;
                        boolean z = false;
                        while (true) {
                            if (i2 >= i) {
                                break;
                            }
                            if (bArr[i2] == 13) {
                                i2++;
                                if (bArr[i2] == 10) {
                                    i2++;
                                    if (bArr[i2] == 13) {
                                        i2++;
                                        if (bArr[i2] == 10) {
                                            z = true;
                                            break;
                                        }
                                    } else {
                                        continue;
                                    }
                                } else {
                                    continue;
                                }
                            }
                            i2++;
                        }
                        int i3 = i2 + 1;
                        if (property.equalsIgnoreCase(HttpPut.METHOD_NAME)) {
                            File createTempFile = File.createTempFile("upload", "bin");
                            File file = createTempFile;
                            createTempFile.deleteOnExit();
                            new FileOutputStream(file);
                            outputStream2 = outputStream3;
                            Object put = properties8.put("content", file.getAbsolutePath());
                        } else {
                            new ByteArrayOutputStream();
                            outputStream2 = outputStream;
                        }
                        if (i3 < i) {
                            outputStream2.write(bArr, i3, i - i3);
                        }
                        if (i3 < i) {
                            j -= (long) ((i - i3) + 1);
                        } else if (!z || j == Long.MAX_VALUE) {
                            j = 0;
                        }
                        byte[] bArr2 = new byte[512];
                        while (i >= 0 && j > 0) {
                            i = inputStream4.read(bArr2, 0, 512);
                            j -= (long) i;
                            if (i > 0) {
                                outputStream2.write(bArr2, 0, i);
                            }
                        }
                        if (property.equalsIgnoreCase(HttpPost.METHOD_NAME)) {
                            byte[] byteArray = ((ByteArrayOutputStream) outputStream2).toByteArray();
                            new ByteArrayInputStream(byteArray);
                            new InputStreamReader(inputStream2);
                            new BufferedReader(reader2);
                            BufferedReader bufferedReader3 = bufferedReader2;
                            String str2 = "";
                            new StringTokenizer(properties7.getProperty("content-type"), "; ");
                            StringTokenizer stringTokenizer3 = stringTokenizer;
                            StringTokenizer stringTokenizer4 = stringTokenizer3;
                            if (stringTokenizer3.hasMoreTokens()) {
                                str2 = stringTokenizer4.nextToken();
                            }
                            if (str2.equalsIgnoreCase("multipart/form-data")) {
                                if (!stringTokenizer4.hasMoreTokens()) {
                                    wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(NanoHTTPD.HTTP_BADREQUEST, "BAD REQUEST: Content type is multipart/form-data but boundary missing. Usage: GET /example/file.html");
                                }
                                new StringTokenizer(stringTokenizer4.nextToken(), "=");
                                StringTokenizer stringTokenizer5 = stringTokenizer2;
                                StringTokenizer stringTokenizer6 = stringTokenizer5;
                                if (stringTokenizer5.countTokens() != 2) {
                                    wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(NanoHTTPD.HTTP_BADREQUEST, "BAD REQUEST: Content type is multipart/form-data but boundary syntax error. Usage: GET /example/file.html");
                                }
                                String nextToken = stringTokenizer6.nextToken();
                                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(stringTokenizer6.nextToken(), byteArray, bufferedReader3, properties6, properties8);
                            } else {
                                String str3 = "";
                                char[] cArr = new char[512];
                                for (int read2 = bufferedReader3.read(cArr); read2 >= 0 && !str3.endsWith(HTTP.CRLF); read2 = bufferedReader3.read(cArr)) {
                                    new StringBuilder();
                                    str3 = sb2.append(str3).append(String.valueOf(cArr, 0, read2)).toString();
                                }
                                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str3.trim(), properties6);
                            }
                            bufferedReader3.close();
                        } else if (property.equalsIgnoreCase("PUT ")) {
                            outputStream2.close();
                        }
                        Response serve = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.serve(property2, property, properties7, properties6, properties8, this.f572hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                        Response response = serve;
                        if (serve == null) {
                            wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(NanoHTTPD.HTTP_INTERNALERROR, "SERVER INTERNAL ERROR: Serve() returned a null response.");
                        } else {
                            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(response.status, response.mimeType, response.header, response.data);
                        }
                        inputStream4.close();
                    }
                }
            } catch (IOException e2) {
                IOException iOException = e2;
                new StringBuilder("SERVER INTERNAL ERROR: IOException: ");
                wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(NanoHTTPD.HTTP_INTERNALERROR, sb.append(iOException.getMessage()).toString());
            } catch (InterruptedException e3) {
            } catch (Throwable th) {
            }
        }

        private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BufferedReader bufferedReader, Properties properties, Properties properties2, Properties properties3) throws InterruptedException {
            StringBuilder sb;
            StringTokenizer stringTokenizer;
            String KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;
            BufferedReader bufferedReader2 = bufferedReader;
            Properties properties4 = properties;
            Properties properties5 = properties2;
            Properties properties6 = properties3;
            try {
                String readLine = bufferedReader2.readLine();
                String str = readLine;
                if (readLine != null) {
                    new StringTokenizer(str);
                    StringTokenizer stringTokenizer2 = stringTokenizer;
                    StringTokenizer stringTokenizer3 = stringTokenizer2;
                    if (!stringTokenizer2.hasMoreTokens()) {
                        wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(NanoHTTPD.HTTP_BADREQUEST, "BAD REQUEST: Syntax error. Usage: GET /example/file.html");
                    }
                    Object put = properties4.put("method", stringTokenizer3.nextToken());
                    if (!stringTokenizer3.hasMoreTokens()) {
                        wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(NanoHTTPD.HTTP_BADREQUEST, "BAD REQUEST: Missing URI. Usage: GET /example/file.html");
                    }
                    String nextToken = stringTokenizer3.nextToken();
                    String str2 = nextToken;
                    int indexOf = nextToken.indexOf(63);
                    int i = indexOf;
                    if (indexOf >= 0) {
                        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str2.substring(i + 1), properties5);
                        KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH(str2.substring(0, i));
                    } else {
                        KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH(str2);
                    }
                    if (stringTokenizer3.hasMoreTokens()) {
                        String readLine2 = bufferedReader2.readLine();
                        while (readLine2 != null && readLine2.trim().length() > 0) {
                            int indexOf2 = readLine2.indexOf(58);
                            int i2 = indexOf2;
                            if (indexOf2 >= 0) {
                                Object put2 = properties6.put(readLine2.substring(0, i2).trim().toLowerCase(), readLine2.substring(i2 + 1).trim());
                            }
                            readLine2 = bufferedReader2.readLine();
                        }
                    }
                    Object put3 = properties4.put("uri", KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH);
                }
            } catch (IOException e) {
                new StringBuilder("SERVER INTERNAL ERROR: IOException: ");
                wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(NanoHTTPD.HTTP_INTERNALERROR, sb.append(e.getMessage()).toString());
            }
        }

        private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, byte[] bArr, BufferedReader bufferedReader, Properties properties, Properties properties2) throws InterruptedException {
            StringBuilder sb;
            Vector vector;
            Properties properties3;
            StringTokenizer stringTokenizer;
            Properties properties4;
            StringBuilder sb2;
            StringBuilder sb3;
            String str2 = str;
            byte[] bArr2 = bArr;
            BufferedReader bufferedReader2 = bufferedReader;
            Properties properties5 = properties;
            Properties properties6 = properties2;
            byte[] bArr3 = bArr2;
            try {
                byte[] bytes = str2.getBytes();
                byte[] bArr4 = bArr3;
                int i = 0;
                int i2 = -1;
                new Vector();
                Vector vector2 = vector;
                int i3 = 0;
                while (i3 < bArr4.length) {
                    if (bArr4[i3] == bytes[i]) {
                        if (i == 0) {
                            i2 = i3;
                        }
                        i++;
                        if (i == bytes.length) {
                            vector2.addElement(Integer.valueOf(i2));
                            i = 0;
                            i2 = -1;
                        }
                    } else {
                        i3 -= i;
                        i = 0;
                        i2 = -1;
                    }
                    i3++;
                }
                int[] iArr = new int[vector2.size()];
                for (int i4 = 0; i4 < iArr.length; i4++) {
                    iArr[i4] = ((Integer) vector2.elementAt(i4)).intValue();
                }
                int[] iArr2 = iArr;
                int i5 = 1;
                String readLine = bufferedReader2.readLine();
                while (readLine != null) {
                    if (readLine.indexOf(str2) == -1) {
                        wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(NanoHTTPD.HTTP_BADREQUEST, "BAD REQUEST: Content type is multipart/form-data but next chunk does not start with boundary. Usage: GET /example/file.html");
                    }
                    i5++;
                    new Properties();
                    Properties properties7 = properties3;
                    readLine = bufferedReader2.readLine();
                    while (readLine != null && readLine.trim().length() > 0) {
                        int indexOf = readLine.indexOf(58);
                        int i6 = indexOf;
                        if (indexOf != -1) {
                            Object put = properties7.put(readLine.substring(0, i6).trim().toLowerCase(), readLine.substring(i6 + 1).trim());
                        }
                        readLine = bufferedReader2.readLine();
                    }
                    if (readLine != null) {
                        String property = properties7.getProperty("content-disposition");
                        String str3 = property;
                        if (property == null) {
                            wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(NanoHTTPD.HTTP_BADREQUEST, "BAD REQUEST: Content type is multipart/form-data but no content-disposition info found. Usage: GET /example/file.html");
                        }
                        new StringTokenizer(str3, "; ");
                        StringTokenizer stringTokenizer2 = stringTokenizer;
                        new Properties();
                        Properties properties8 = properties4;
                        while (stringTokenizer2.hasMoreTokens()) {
                            String nextToken = stringTokenizer2.nextToken();
                            String str4 = nextToken;
                            int indexOf2 = nextToken.indexOf(61);
                            int i7 = indexOf2;
                            if (indexOf2 != -1) {
                                Object put2 = properties8.put(str4.substring(0, i7).trim().toLowerCase(), str4.substring(i7 + 1).trim());
                            }
                        }
                        String property2 = properties8.getProperty("name");
                        String substring = property2.substring(1, property2.length() - 1);
                        String str5 = "";
                        if (properties7.getProperty("content-type") == null) {
                            while (readLine != null && readLine.indexOf(str2) == -1) {
                                String readLine2 = bufferedReader2.readLine();
                                readLine = readLine2;
                                if (readLine2 != null) {
                                    int indexOf3 = readLine.indexOf(str2);
                                    int i8 = indexOf3;
                                    if (indexOf3 == -1) {
                                        new StringBuilder();
                                        str5 = sb3.append(str5).append(readLine).toString();
                                    } else {
                                        new StringBuilder();
                                        str5 = sb2.append(str5).append(readLine.substring(0, i8 - 2)).toString();
                                    }
                                }
                            }
                        } else {
                            if (i5 > iArr2.length) {
                                wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(NanoHTTPD.HTTP_INTERNALERROR, "Error processing request");
                            }
                            int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(bArr2, iArr2[i5 - 2]);
                            Object put3 = properties6.put(substring, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(bArr2, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2, (iArr2[i5 - 1] - hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2) - 4));
                            String property3 = properties8.getProperty("filename");
                            str5 = property3.substring(1, property3.length() - 1);
                            do {
                                String readLine3 = bufferedReader2.readLine();
                                readLine = readLine3;
                                if (readLine3 == null) {
                                    break;
                                }
                            } while (readLine.indexOf(str2) != -1);
                        }
                        Object put4 = properties5.put(substring, str5);
                    }
                }
            } catch (IOException e) {
                new StringBuilder("SERVER INTERNAL ERROR: IOException: ");
                wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(NanoHTTPD.HTTP_INTERNALERROR, sb.append(e.getMessage()).toString());
            }
        }

        private static String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(byte[] bArr, int i, int i2) {
            StringBuilder sb;
            File file;
            OutputStream outputStream;
            byte[] bArr2 = bArr;
            int i3 = i;
            int i4 = i2;
            String str = "";
            if (i4 > 0) {
                try {
                    new File(System.getProperty("java.io.tmpdir"));
                    File createTempFile = File.createTempFile("NanoHTTPD", "", file);
                    new FileOutputStream(createTempFile);
                    OutputStream outputStream2 = outputStream;
                    outputStream2.write(bArr2, i3, i4);
                    outputStream2.close();
                    str = createTempFile.getAbsolutePath();
                } catch (Exception e) {
                    Exception exc = e;
                    PrintStream printStream = NanoHTTPD.myErr;
                    new StringBuilder("Error: ");
                    printStream.println(sb.append(exc.getMessage()).toString());
                }
            }
            return str;
        }

        private static int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(byte[] bArr, int i) {
            byte[] bArr2 = bArr;
            int i2 = i;
            while (i2 < bArr2.length) {
                if (bArr2[i2] == 13) {
                    i2++;
                    if (bArr2[i2] == 10) {
                        i2++;
                        if (bArr2[i2] == 13) {
                            i2++;
                            if (bArr2[i2] == 10) {
                                break;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
                i2++;
            }
            return i2 + 1;
        }

        private String KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH(String str) throws InterruptedException {
            StringBuffer stringBuffer;
            String str2 = str;
            try {
                new StringBuffer();
                StringBuffer stringBuffer2 = stringBuffer;
                int i = 0;
                while (i < str2.length()) {
                    char charAt = str2.charAt(i);
                    char c = charAt;
                    switch (charAt) {
                        case '%':
                            StringBuffer append = stringBuffer2.append((char) Integer.parseInt(str2.substring(i + 1, i + 3), 16));
                            i += 2;
                            break;
                        case '+':
                            StringBuffer append2 = stringBuffer2.append(' ');
                            break;
                        default:
                            StringBuffer append3 = stringBuffer2.append(c);
                            break;
                    }
                    i++;
                }
                return stringBuffer2.toString();
            } catch (Exception e) {
                wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(NanoHTTPD.HTTP_BADREQUEST, "BAD REQUEST: Bad percent-encoding.");
                return null;
            }
        }

        private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, Properties properties) throws InterruptedException {
            StringTokenizer stringTokenizer;
            String str2 = str;
            Properties properties2 = properties;
            if (str2 != null) {
                new StringTokenizer(str2, "&");
                StringTokenizer stringTokenizer2 = stringTokenizer;
                while (stringTokenizer2.hasMoreTokens()) {
                    String nextToken = stringTokenizer2.nextToken();
                    String str3 = nextToken;
                    int indexOf = nextToken.indexOf(61);
                    int i = indexOf;
                    if (indexOf >= 0) {
                        Object put = properties2.put(KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH(str3.substring(0, i)).trim(), KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH(str3.substring(i + 1)));
                    }
                }
            }
        }

        private void wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(String str, String str2) throws InterruptedException {
            InputStream inputStream;
            Throwable th;
            new ByteArrayInputStream(str2.getBytes());
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, "text/plain", (Properties) null, inputStream);
            Throwable th2 = th;
            new InterruptedException();
            throw th2;
        }

        private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, String str2, Properties properties, InputStream inputStream) {
            PrintWriter printWriter;
            StringBuilder sb;
            StringBuilder sb2;
            StringBuilder sb3;
            Date date;
            StringBuilder sb4;
            Throwable th;
            String str3 = str;
            String str4 = str2;
            Properties properties2 = properties;
            InputStream inputStream2 = inputStream;
            if (str3 == null) {
                try {
                    Throwable th2 = th;
                    new Error("sendResponse(): Status can't be null.");
                    throw th2;
                } catch (IOException e) {
                    this.f572hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.close();
                } catch (Throwable th3) {
                }
            } else {
                OutputStream outputStream = this.f572hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getOutputStream();
                new PrintWriter(outputStream);
                PrintWriter printWriter2 = printWriter;
                PrintWriter printWriter3 = printWriter2;
                new StringBuilder("HTTP/1.0 ");
                printWriter2.print(sb.append(str3).append(" \r\n").toString());
                if (str4 != null) {
                    new StringBuilder("Content-Type: ");
                    printWriter3.print(sb4.append(str4).append(HTTP.CRLF).toString());
                }
                if (properties2 == null || properties2.getProperty(org.shaded.apache.http.protocol.HTTP.DATE_HEADER) == null) {
                    new StringBuilder("Date: ");
                    new Date();
                    printWriter3.print(sb3.append(NanoHTTPD.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.format(date)).append(HTTP.CRLF).toString());
                }
                if (properties2 != null) {
                    Enumeration keys = properties2.keys();
                    while (keys.hasMoreElements()) {
                        String str5 = (String) keys.nextElement();
                        String property = properties2.getProperty(str5);
                        new StringBuilder();
                        printWriter3.print(sb2.append(str5).append(": ").append(property).append(HTTP.CRLF).toString());
                    }
                }
                printWriter3.print(HTTP.CRLF);
                printWriter3.flush();
                if (inputStream2 != null) {
                    int available = inputStream2.available();
                    byte[] bArr = new byte[NanoHTTPD.QGAZOXcdjHKQSTylJ1PoRPU6GviAfBkJokC9i5wWel7BvaAua8hNDN1OOldV05g5];
                    while (available > 0) {
                        int read = inputStream2.read(bArr, 0, available > NanoHTTPD.QGAZOXcdjHKQSTylJ1PoRPU6GviAfBkJokC9i5wWel7BvaAua8hNDN1OOldV05g5 ? NanoHTTPD.QGAZOXcdjHKQSTylJ1PoRPU6GviAfBkJokC9i5wWel7BvaAua8hNDN1OOldV05g5 : available);
                        int i = read;
                        if (read <= 0) {
                            break;
                        }
                        outputStream.write(bArr, 0, i);
                        available -= i;
                    }
                }
                outputStream.flush();
                outputStream.close();
                if (inputStream2 != null) {
                    inputStream2.close();
                }
            }
        }
    }

    private static String l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j(String str) {
        StringTokenizer stringTokenizer;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        String str2 = "";
        new StringTokenizer(str, "/ ", true);
        StringTokenizer stringTokenizer2 = stringTokenizer;
        while (stringTokenizer2.hasMoreTokens()) {
            String nextToken = stringTokenizer2.nextToken();
            String str3 = nextToken;
            if (nextToken.equals("/")) {
                new StringBuilder();
                str2 = sb3.append(str2).append("/").toString();
            } else if (str3.equals(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)) {
                new StringBuilder();
                str2 = sb2.append(str2).append("%20").toString();
            } else {
                new StringBuilder();
                str2 = sb.append(str2).append(URLEncoder.encode(str3)).toString();
            }
        }
        return str2;
    }

    public Response serveFile(String str, Properties properties, File file, boolean z) {
        File file2;
        Response response;
        StringBuilder sb;
        Response response2;
        InputStream inputStream;
        Response response3;
        FileInputStream fileInputStream;
        Response response4;
        StringBuilder sb2;
        Response response5;
        File file3;
        File file4;
        Response response6;
        StringBuilder sb3;
        String str2;
        StringBuilder sb4;
        Response response7;
        File file5;
        StringBuilder sb5;
        StringBuilder sb6;
        StringBuilder sb7;
        StringBuilder sb8;
        StringBuilder sb9;
        StringBuilder sb10;
        String sb11;
        StringBuilder sb12;
        StringBuilder sb13;
        StringBuilder sb14;
        StringBuilder sb15;
        StringBuilder sb16;
        StringBuilder sb17;
        File file6;
        StringBuilder sb18;
        File file7;
        StringBuilder sb19;
        StringBuilder sb20;
        Response response8;
        StringBuilder sb21;
        Response response9;
        Response response10;
        Response response11;
        String str3 = str;
        Properties properties2 = properties;
        File file8 = file;
        boolean z2 = z;
        Response response12 = null;
        if (!file8.isDirectory()) {
            new Response(this, HTTP_INTERNALERROR, "text/plain", "INTERNAL ERRROR: serveFile(): given homeDir is not a directory.");
            response12 = response11;
        }
        if (response12 == null) {
            String replace = str3.trim().replace(File.separatorChar, '/');
            str3 = replace;
            if (replace.indexOf(63) >= 0) {
                str3 = str3.substring(0, str3.indexOf(63));
            }
            if (str3.startsWith("..") || str3.endsWith("..") || str3.indexOf("../") >= 0) {
                new Response(this, HTTP_FORBIDDEN, "text/plain", "FORBIDDEN: Won't serve ../ for security reasons.");
                response12 = response10;
            }
        }
        new File(file8, str3);
        File file9 = file2;
        if (response12 == null && !file9.exists()) {
            new Response(this, HTTP_NOTFOUND, "text/plain", "Error 404, file not found.");
            response12 = response9;
        }
        if (response12 == null && file9.isDirectory()) {
            if (!str3.endsWith("/")) {
                new StringBuilder();
                str3 = sb20.append(str3).append("/").toString();
                new StringBuilder("<html><body>Redirected: <a href=\"");
                new Response(this, HTTP_REDIRECT, MIME_HTML, sb21.append(str3).append("\">").append(str3).append("</a></body></html>").toString());
                Response response13 = response8;
                response12 = response13;
                response13.addHeader("Location", str3);
            }
            if (response12 == null) {
                new File(file9, "index.html");
                if (file3.exists()) {
                    new StringBuilder();
                    new File(file8, sb19.append(str3).append("/index.html").toString());
                    file9 = file7;
                } else {
                    new File(file9, "index.htm");
                    if (file4.exists()) {
                        new StringBuilder();
                        new File(file8, sb18.append(str3).append("/index.htm").toString());
                        file9 = file6;
                    } else if (!z2 || !file9.canRead()) {
                        new Response(this, HTTP_FORBIDDEN, "text/plain", "FORBIDDEN: No directory listing.");
                        response12 = response6;
                    } else {
                        String[] list = file9.list();
                        new StringBuilder("<html><body><h1>Directory ");
                        String sb22 = sb3.append(str3).append("</h1><br/>").toString();
                        if (str3.length() > 1) {
                            String substring = str3.substring(0, str3.length() - 1);
                            String str4 = substring;
                            int lastIndexOf = substring.lastIndexOf(47);
                            int i = lastIndexOf;
                            if (lastIndexOf >= 0 && i < str4.length()) {
                                new StringBuilder();
                                sb22 = sb17.append(sb22).append("<b><a href=\"").append(str3.substring(0, i + 1)).append("\">..</a></b><br/>").toString();
                            }
                        }
                        if (list != null) {
                            for (int i2 = 0; i2 < list.length; i2++) {
                                new File(file9, list[i2]);
                                File file10 = file5;
                                File file11 = file10;
                                boolean isDirectory = file10.isDirectory();
                                boolean z3 = isDirectory;
                                if (isDirectory) {
                                    new StringBuilder();
                                    str2 = sb15.append(str2).append("<b>").toString();
                                    new StringBuilder();
                                    String[] strArr = list;
                                    int i3 = i2;
                                    strArr[i3] = sb16.append(strArr[i3]).append("/").toString();
                                }
                                new StringBuilder();
                                StringBuilder append = sb5.append(str2).append("<a href=\"");
                                new StringBuilder();
                                String sb23 = append.append(l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j(sb6.append(str3).append(list[i2]).toString())).append("\">").append(list[i2]).append("</a>").toString();
                                if (file11.isFile()) {
                                    long length = file11.length();
                                    new StringBuilder();
                                    String sb24 = sb9.append(sb23).append(" &nbsp;<font size=2>(").toString();
                                    if (length < PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
                                        new StringBuilder();
                                        sb11 = sb14.append(sb24).append(length).append(" bytes").toString();
                                    } else if (length < PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
                                        new StringBuilder();
                                        sb11 = sb12.append(sb24).append(length / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID).append(".").append(((length % PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) / 10) % 100).append(" KB").toString();
                                    } else {
                                        new StringBuilder();
                                        sb11 = sb10.append(sb24).append(length / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED).append(".").append(((length % PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) / 10) % 100).append(" MB").toString();
                                    }
                                    new StringBuilder();
                                    sb23 = sb13.append(sb11).append(")</font>").toString();
                                }
                                new StringBuilder();
                                str2 = sb7.append(sb23).append("<br/>").toString();
                                if (z3) {
                                    new StringBuilder();
                                    str2 = sb8.append(str2).append("</b>").toString();
                                }
                            }
                        }
                        new StringBuilder();
                        new Response(this, HTTP_OK, MIME_HTML, sb4.append(str2).append("</body></html>").toString());
                        response12 = response7;
                    }
                }
            }
        }
        if (response12 == null) {
            String str5 = null;
            try {
                int lastIndexOf2 = file9.getCanonicalPath().lastIndexOf(46);
                int i4 = lastIndexOf2;
                if (lastIndexOf2 >= 0) {
                    str5 = (String) f568hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.get(file9.getCanonicalPath().substring(i4 + 1).toLowerCase());
                }
                if (str5 == null) {
                    str5 = "application/octet-stream";
                }
                new StringBuilder();
                String hexString = Integer.toHexString(sb.append(file9.getAbsolutePath()).append(file9.lastModified()).append(file9.length()).toString().hashCode());
                long j = 0;
                long j2 = -1;
                String property = properties2.getProperty("range");
                String str6 = property;
                if (property != null && str6.startsWith("bytes=")) {
                    String substring2 = str6.substring(6);
                    str6 = substring2;
                    int indexOf = substring2.indexOf(45);
                    if (indexOf > 0) {
                        try {
                            j = Long.parseLong(str6.substring(0, indexOf));
                            j2 = Long.parseLong(str6.substring(indexOf + 1));
                        } catch (NumberFormatException e) {
                        }
                    }
                }
                long length2 = file9.length();
                if (str6 == null || j < 0) {
                    if (hexString.equals(properties2.getProperty("if-none-match"))) {
                        new Response(this, HTTP_NOTMODIFIED, str5, "");
                        response12 = response3;
                    } else {
                        new FileInputStream(file9);
                        new Response(this, HTTP_OK, str5, inputStream);
                        Response response14 = response2;
                        response12 = response14;
                        response14.addHeader(org.shaded.apache.http.protocol.HTTP.CONTENT_LEN, String.valueOf(length2));
                        response12.addHeader("ETag", hexString);
                    }
                } else if (j >= length2) {
                    new Response(this, HTTP_RANGE_NOT_SATISFIABLE, "text/plain", "");
                    Response response15 = response5;
                    response12 = response15;
                    response15.addHeader("Content-Range", "bytes 0-0/".concat(String.valueOf(length2)));
                    response12.addHeader("ETag", hexString);
                } else {
                    if (j2 < 0) {
                        j2 = length2 - 1;
                    }
                    long j3 = (j2 - j) + 1;
                    long j4 = j3;
                    if (j3 < 0) {
                        j4 = 0;
                    }
                    long j5 = j4;
                    final long j6 = j5;
                    new FileInputStream(this, file9) {
                        private /* synthetic */ NanoHTTPD hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r10;
                        }

                        public final int available() throws IOException {
                            return (int) j6;
                        }
                    };
                    FileInputStream fileInputStream2 = fileInputStream;
                    long skip = fileInputStream2.skip(j);
                    new Response(this, HTTP_PARTIALCONTENT, str5, (InputStream) fileInputStream2);
                    Response response16 = response4;
                    response12 = response16;
                    response16.addHeader(org.shaded.apache.http.protocol.HTTP.CONTENT_LEN, String.valueOf(j5));
                    new StringBuilder("bytes ");
                    response12.addHeader("Content-Range", sb2.append(j).append("-").append(j2).append("/").append(length2).toString());
                    response12.addHeader("ETag", hexString);
                }
            } catch (IOException e2) {
                new Response(this, HTTP_FORBIDDEN, "text/plain", "FORBIDDEN: Reading file failed.");
                response12 = response;
            }
        }
        response12.addHeader("Accept-Ranges", "bytes");
        return response12;
    }

    static {
        Hashtable hashtable;
        StringTokenizer stringTokenizer;
        SimpleDateFormat simpleDateFormat;
        new Hashtable();
        f568hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hashtable;
        new StringTokenizer("css            text/css htm            text/html html           text/html xml            text/xml txt            text/plain asc            text/plain gif            image/gif jpg            image/jpeg jpeg           image/jpeg png            image/png mp3            audio/mpeg m3u            audio/mpeg-url mp4            video/mp4 ogv            video/ogg flv            video/x-flv mov            video/quicktime swf            application/x-shockwave-flash js                     application/javascript pdf            application/pdf doc            application/msword ogg            application/x-ogg zip            application/octet-stream exe            application/octet-stream class          application/octet-stream ");
        StringTokenizer stringTokenizer2 = stringTokenizer;
        while (stringTokenizer2.hasMoreTokens()) {
            Object put = f568hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.put(stringTokenizer2.nextToken(), stringTokenizer2.nextToken());
        }
        new SimpleDateFormat("E, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        SimpleDateFormat simpleDateFormat2 = simpleDateFormat;
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = simpleDateFormat2;
        simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("GMT"));
    }
}
