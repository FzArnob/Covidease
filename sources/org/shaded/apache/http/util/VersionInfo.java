package org.shaded.apache.http.util;

import java.util.ArrayList;
import java.util.Map;

public class VersionInfo {
    public static final String PROPERTY_MODULE = "info.module";
    public static final String PROPERTY_RELEASE = "info.release";
    public static final String PROPERTY_TIMESTAMP = "info.timestamp";
    public static final String UNAVAILABLE = "UNAVAILABLE";
    public static final String VERSION_PROPERTY_FILE = "version.properties";
    private final String infoClassloader;
    private final String infoModule;
    private final String infoPackage;
    private final String infoRelease;
    private final String infoTimestamp;

    protected VersionInfo(String str, String str2, String str3, String str4, String str5) {
        Throwable th;
        String pckg = str;
        String module = str2;
        String release = str3;
        String time = str4;
        String clsldr = str5;
        if (pckg == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Package identifier must not be null.");
            throw th2;
        }
        this.infoPackage = pckg;
        this.infoModule = module != null ? module : UNAVAILABLE;
        this.infoRelease = release != null ? release : UNAVAILABLE;
        this.infoTimestamp = time != null ? time : UNAVAILABLE;
        this.infoClassloader = clsldr != null ? clsldr : UNAVAILABLE;
    }

    public final String getPackage() {
        return this.infoPackage;
    }

    public final String getModule() {
        return this.infoModule;
    }

    public final String getRelease() {
        return this.infoRelease;
    }

    public final String getTimestamp() {
        return this.infoTimestamp;
    }

    public final String getClassloader() {
        return this.infoClassloader;
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer(20 + this.infoPackage.length() + this.infoModule.length() + this.infoRelease.length() + this.infoTimestamp.length() + this.infoClassloader.length());
        StringBuffer sb = stringBuffer;
        StringBuffer append = sb.append("VersionInfo(").append(this.infoPackage).append(':').append(this.infoModule);
        if (!UNAVAILABLE.equals(this.infoRelease)) {
            StringBuffer append2 = sb.append(':').append(this.infoRelease);
        }
        if (!UNAVAILABLE.equals(this.infoTimestamp)) {
            StringBuffer append3 = sb.append(':').append(this.infoTimestamp);
        }
        StringBuffer append4 = sb.append(')');
        if (!UNAVAILABLE.equals(this.infoClassloader)) {
            StringBuffer append5 = sb.append('@').append(this.infoClassloader);
        }
        return sb.toString();
    }

    public static final VersionInfo[] loadVersionInfo(String[] strArr, ClassLoader classLoader) {
        ArrayList arrayList;
        Throwable th;
        String[] pckgs = strArr;
        ClassLoader clsldr = classLoader;
        if (pckgs == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Package identifier list must not be null.");
            throw th2;
        }
        new ArrayList(pckgs.length);
        ArrayList vil = arrayList;
        for (int i = 0; i < pckgs.length; i++) {
            VersionInfo vi = loadVersionInfo(pckgs[i], clsldr);
            if (vi != null) {
                boolean add = vil.add(vi);
            }
        }
        return (VersionInfo[]) vil.toArray(new VersionInfo[vil.size()]);
    }

    /* JADX WARNING: type inference failed for: r6v17, types: [java.util.Properties] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final org.shaded.apache.http.util.VersionInfo loadVersionInfo(java.lang.String r12, java.lang.ClassLoader r13) {
        /*
            r0 = r12
            r1 = r13
            r6 = r0
            if (r6 != 0) goto L_0x0011
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r11 = r6
            r6 = r11
            r7 = r11
            java.lang.String r8 = "Package identifier must not be null."
            r7.<init>(r8)
            throw r6
        L_0x0011:
            r6 = r1
            if (r6 != 0) goto L_0x001d
            java.lang.Thread r6 = java.lang.Thread.currentThread()
            java.lang.ClassLoader r6 = r6.getContextClassLoader()
            r1 = r6
        L_0x001d:
            r6 = 0
            r2 = r6
            r6 = r1
            java.lang.StringBuffer r7 = new java.lang.StringBuffer     // Catch:{ IOException -> 0x007b }
            r11 = r7
            r7 = r11
            r8 = r11
            r8.<init>()     // Catch:{ IOException -> 0x007b }
            r8 = r0
            r9 = 46
            r10 = 47
            java.lang.String r8 = r8.replace(r9, r10)     // Catch:{ IOException -> 0x007b }
            java.lang.StringBuffer r7 = r7.append(r8)     // Catch:{ IOException -> 0x007b }
            java.lang.String r8 = "/"
            java.lang.StringBuffer r7 = r7.append(r8)     // Catch:{ IOException -> 0x007b }
            java.lang.String r8 = "version.properties"
            java.lang.StringBuffer r7 = r7.append(r8)     // Catch:{ IOException -> 0x007b }
            java.lang.String r7 = r7.toString()     // Catch:{ IOException -> 0x007b }
            java.io.InputStream r6 = r6.getResourceAsStream(r7)     // Catch:{ IOException -> 0x007b }
            r3 = r6
            r6 = r3
            if (r6 == 0) goto L_0x0063
            java.util.Properties r6 = new java.util.Properties     // Catch:{ all -> 0x0073 }
            r11 = r6
            r6 = r11
            r7 = r11
            r7.<init>()     // Catch:{ all -> 0x0073 }
            r4 = r6
            r6 = r4
            r7 = r3
            r6.load(r7)     // Catch:{ all -> 0x0073 }
            r6 = r4
            r2 = r6
            r6 = r3
            r6.close()     // Catch:{ IOException -> 0x007b }
        L_0x0063:
            r6 = 0
            r3 = r6
            r6 = r2
            if (r6 == 0) goto L_0x0070
            r6 = r0
            r7 = r2
            r8 = r1
            org.shaded.apache.http.util.VersionInfo r6 = fromMap(r6, r7, r8)
            r3 = r6
        L_0x0070:
            r6 = r3
            r0 = r6
            return r0
        L_0x0073:
            r6 = move-exception
            r5 = r6
            r6 = r3
            r6.close()     // Catch:{ IOException -> 0x007b }
            r6 = r5
            throw r6     // Catch:{ IOException -> 0x007b }
        L_0x007b:
            r6 = move-exception
            r3 = r6
            goto L_0x0063
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.util.VersionInfo.loadVersionInfo(java.lang.String, java.lang.ClassLoader):org.shaded.apache.http.util.VersionInfo");
    }

    protected static final VersionInfo fromMap(String str, Map map, ClassLoader classLoader) {
        VersionInfo versionInfo;
        Throwable th;
        String pckg = str;
        Map info = map;
        ClassLoader clsldr = classLoader;
        if (pckg == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Package identifier must not be null.");
            throw th2;
        }
        String module = null;
        String release = null;
        String timestamp = null;
        if (info != null) {
            module = (String) info.get(PROPERTY_MODULE);
            if (module != null && module.length() < 1) {
                module = null;
            }
            release = (String) info.get(PROPERTY_RELEASE);
            if (release != null && (release.length() < 1 || release.equals("${pom.version}"))) {
                release = null;
            }
            timestamp = (String) info.get(PROPERTY_TIMESTAMP);
            if (timestamp != null && (timestamp.length() < 1 || timestamp.equals("${mvn.timestamp}"))) {
                timestamp = null;
            }
        }
        String clsldrstr = null;
        if (clsldr != null) {
            clsldrstr = clsldr.toString();
        }
        new VersionInfo(pckg, module, release, timestamp, clsldrstr);
        return versionInfo;
    }
}
