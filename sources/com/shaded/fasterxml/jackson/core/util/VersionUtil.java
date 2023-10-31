package com.shaded.fasterxml.jackson.core.util;

import com.shaded.fasterxml.jackson.core.Version;
import com.shaded.fasterxml.jackson.core.Versioned;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.util.Properties;
import java.util.regex.Pattern;
import org.shaded.apache.http.cookie.ClientCookie;

public class VersionUtil {
    public static final String PACKAGE_VERSION_CLASS_NAME = "PackageVersion";
    @Deprecated
    public static final String VERSION_FILE = "VERSION.txt";
    private static final Pattern VERSION_SEPARATOR = Pattern.compile("[-_./;:]");
    private final Version _version;

    protected VersionUtil() {
        StringBuilder sb;
        Version version = null;
        try {
            version = versionFor(getClass());
        } catch (Exception e) {
            Exception exc = e;
            PrintStream printStream = System.err;
            new StringBuilder();
            printStream.println(sb.append("ERROR: Failed to load Version information for bundle (via ").append(getClass().getName()).append(").").toString());
        }
        this._version = version == null ? Version.unknownVersion() : version;
    }

    public Version version() {
        return this._version;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: type inference failed for: r10v23, types: [java.io.InputStreamReader] */
    /* JADX WARNING: type inference failed for: r10v27, types: [java.io.InputStreamReader] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.shaded.fasterxml.jackson.core.Version versionFor(java.lang.Class<?> r15) {
        /*
            r0 = r15
            r10 = r0
            com.shaded.fasterxml.jackson.core.Version r10 = packageVersionFor(r10)
            r1 = r10
            r10 = r1
            if (r10 == 0) goto L_0x000d
            r10 = r1
            r0 = r10
        L_0x000c:
            return r0
        L_0x000d:
            r10 = r0
            java.lang.String r11 = "VERSION.txt"
            java.io.InputStream r10 = r10.getResourceAsStream(r11)
            r2 = r10
            r10 = r2
            if (r10 != 0) goto L_0x001f
            com.shaded.fasterxml.jackson.core.Version r10 = com.shaded.fasterxml.jackson.core.Version.unknownVersion()
            r0 = r10
            goto L_0x000c
        L_0x001f:
            java.io.InputStreamReader r10 = new java.io.InputStreamReader     // Catch:{ UnsupportedEncodingException -> 0x0054 }
            r14 = r10
            r10 = r14
            r11 = r14
            r12 = r2
            java.lang.String r13 = "UTF-8"
            r11.<init>(r12, r13)     // Catch:{ UnsupportedEncodingException -> 0x0054 }
            r3 = r10
            r10 = r3
            com.shaded.fasterxml.jackson.core.Version r10 = doReadVersion(r10)     // Catch:{ all -> 0x004c }
            r4 = r10
            r10 = r3
            r10.close()     // Catch:{ IOException -> 0x003d }
        L_0x0036:
            r10 = r2
            r10.close()     // Catch:{ IOException -> 0x0040 }
            r10 = r4
            r0 = r10
            goto L_0x000c
        L_0x003d:
            r10 = move-exception
            r5 = r10
            goto L_0x0036
        L_0x0040:
            r10 = move-exception
            r5 = r10
            java.lang.RuntimeException r10 = new java.lang.RuntimeException
            r14 = r10
            r10 = r14
            r11 = r14
            r12 = r5
            r11.<init>(r12)
            throw r10
        L_0x004c:
            r10 = move-exception
            r6 = r10
            r10 = r3
            r10.close()     // Catch:{ IOException -> 0x0062 }
        L_0x0052:
            r10 = r6
            throw r10     // Catch:{ UnsupportedEncodingException -> 0x0054 }
        L_0x0054:
            r10 = move-exception
            r3 = r10
            com.shaded.fasterxml.jackson.core.Version r10 = com.shaded.fasterxml.jackson.core.Version.unknownVersion()     // Catch:{ all -> 0x0071 }
            r4 = r10
            r10 = r2
            r10.close()     // Catch:{ IOException -> 0x0065 }
            r10 = r4
            r0 = r10
            goto L_0x000c
        L_0x0062:
            r10 = move-exception
            r7 = r10
            goto L_0x0052
        L_0x0065:
            r10 = move-exception
            r5 = r10
            java.lang.RuntimeException r10 = new java.lang.RuntimeException
            r14 = r10
            r10 = r14
            r11 = r14
            r12 = r5
            r11.<init>(r12)
            throw r10
        L_0x0071:
            r10 = move-exception
            r8 = r10
            r10 = r2
            r10.close()     // Catch:{ IOException -> 0x0079 }
            r10 = r8
            throw r10
        L_0x0079:
            r10 = move-exception
            r9 = r10
            java.lang.RuntimeException r10 = new java.lang.RuntimeException
            r14 = r10
            r10 = r14
            r11 = r14
            r12 = r9
            r11.<init>(r12)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.util.VersionUtil.versionFor(java.lang.Class):com.shaded.fasterxml.jackson.core.Version");
    }

    public static Version packageVersionFor(Class<?> cls) {
        StringBuilder sb;
        Throwable th;
        StringBuilder sb2;
        Throwable th2;
        StringBuilder sb3;
        Class<?> cls2 = cls;
        try {
            new StringBuilder(cls2.getPackage().getName());
            Class<?> cls3 = Class.forName(sb.append(".").append(PACKAGE_VERSION_CLASS_NAME).toString(), true, cls2.getClassLoader());
            if (cls3 == null) {
                return null;
            }
            try {
                Object newInstance = cls3.newInstance();
                if (newInstance instanceof Versioned) {
                    return ((Versioned) newInstance).version();
                }
                Throwable th3 = th2;
                new StringBuilder();
                new IllegalArgumentException(sb3.append("Bad version class ").append(cls3.getName()).append(": does not implement ").append(Versioned.class.getName()).toString());
                throw th3;
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                Exception exc = e2;
                Throwable th4 = th;
                new StringBuilder();
                new IllegalArgumentException(sb2.append("Failed to instantiate ").append(cls3.getName()).append(" to find version information, problem: ").append(exc.getMessage()).toString(), exc);
                throw th4;
            }
        } catch (Exception e3) {
            Exception exc2 = e3;
            return null;
        }
    }

    private static Version doReadVersion(Reader reader) {
        BufferedReader bufferedReader;
        String str = null;
        String str2 = null;
        String str3 = null;
        new BufferedReader(reader);
        BufferedReader bufferedReader2 = bufferedReader;
        try {
            str = bufferedReader2.readLine();
            if (str != null) {
                str2 = bufferedReader2.readLine();
                if (str2 != null) {
                    str3 = bufferedReader2.readLine();
                }
            }
            try {
                bufferedReader2.close();
            } catch (IOException e) {
                IOException iOException = e;
            }
        } catch (IOException e2) {
            IOException iOException2 = e2;
            try {
                bufferedReader2.close();
            } catch (IOException e3) {
                IOException iOException3 = e3;
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            try {
                bufferedReader2.close();
            } catch (IOException e4) {
                IOException iOException4 = e4;
            }
            throw th2;
        }
        if (str2 != null) {
            str2 = str2.trim();
        }
        if (str3 != null) {
            str3 = str3.trim();
        }
        return parseVersion(str, str2, str3);
    }

    public static Version mavenVersionFor(ClassLoader classLoader, String str, String str2) {
        StringBuilder sb;
        Properties properties;
        new StringBuilder();
        InputStream resourceAsStream = classLoader.getResourceAsStream(sb.append("META-INF/maven/").append(str.replaceAll("\\.", "/")).append("/").append(str2).append("/pom.properties").toString());
        if (resourceAsStream != null) {
            try {
                new Properties();
                Properties properties2 = properties;
                properties2.load(resourceAsStream);
                String property = properties2.getProperty(ClientCookie.VERSION_ATTR);
                String property2 = properties2.getProperty("artifactId");
                Version parseVersion = parseVersion(property, properties2.getProperty("groupId"), property2);
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    IOException iOException = e;
                }
                return parseVersion;
            } catch (IOException e2) {
                IOException iOException2 = e2;
                try {
                    resourceAsStream.close();
                } catch (IOException e3) {
                    IOException iOException3 = e3;
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                try {
                    resourceAsStream.close();
                } catch (IOException e4) {
                    IOException iOException4 = e4;
                }
                throw th2;
            }
        }
        return Version.unknownVersion();
    }

    @Deprecated
    public static Version parseVersion(String str) {
        return parseVersion(str, (String) null, (String) null);
    }

    public static Version parseVersion(String str, String str2, String str3) {
        Version version;
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        if (str4 == null) {
            return null;
        }
        String trim = str4.trim();
        if (trim.length() == 0) {
            return null;
        }
        String[] split = VERSION_SEPARATOR.split(trim);
        new Version(parseVersionPart(split[0]), split.length > 1 ? parseVersionPart(split[1]) : 0, split.length > 2 ? parseVersionPart(split[2]) : 0, split.length > 3 ? split[3] : null, str5, str6);
        return version;
    }

    protected static int parseVersionPart(String str) {
        char charAt;
        String str2 = str.toString();
        int length = str2.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && (charAt = str2.charAt(i2)) <= '9' && charAt >= '0') {
            i = (i * 10) + (charAt - '0');
            i2++;
        }
        return i;
    }

    public static final void throwInternal() {
        Throwable th;
        Throwable th2 = th;
        new RuntimeException("Internal error: this code path should never get executed");
        throw th2;
    }
}
