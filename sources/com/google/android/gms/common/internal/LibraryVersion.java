package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import org.shaded.apache.http.cookie.ClientCookie;

@KeepForSdk
public class LibraryVersion {
    private static final GmsLogger zzel;
    private static LibraryVersion zzem;
    private ConcurrentHashMap<String, String> zzen;

    @KeepForSdk
    public static LibraryVersion getInstance() {
        return zzem;
    }

    @VisibleForTesting
    protected LibraryVersion() {
        ConcurrentHashMap<String, String> concurrentHashMap;
        new ConcurrentHashMap<>();
        this.zzen = concurrentHashMap;
    }

    @KeepForSdk
    public String getVersion(@NonNull String str) {
        Properties properties;
        String str2;
        String str3;
        String str4;
        String str5;
        StringBuilder sb;
        String str6 = str;
        String checkNotEmpty = Preconditions.checkNotEmpty(str6, "Please provide a valid libraryName");
        if (this.zzen.containsKey(str6)) {
            return this.zzen.get(str6);
        }
        String str7 = null;
        new Properties();
        Properties properties2 = properties;
        try {
            InputStream resourceAsStream = LibraryVersion.class.getResourceAsStream(String.format("/%s.properties", new Object[]{str6}));
            InputStream inputStream = resourceAsStream;
            if (resourceAsStream != null) {
                properties2.load(inputStream);
                str7 = properties2.getProperty(ClientCookie.VERSION_ATTR, (String) null);
                GmsLogger gmsLogger = zzel;
                String str8 = str7;
                new StringBuilder(12 + String.valueOf(str6).length() + String.valueOf(str8).length());
                gmsLogger.mo25221v("LibraryVersion", sb.append(str6).append(" version is ").append(str8).toString());
            } else {
                GmsLogger gmsLogger2 = zzel;
                String valueOf = String.valueOf(str6);
                String str9 = valueOf;
                if (valueOf.length() != 0) {
                    str5 = "Failed to get app version for libraryName: ".concat(str9);
                } else {
                    str5 = str4;
                    new String("Failed to get app version for libraryName: ");
                }
                gmsLogger2.mo25214e("LibraryVersion", str5);
            }
        } catch (IOException e) {
            IOException iOException = e;
            GmsLogger gmsLogger3 = zzel;
            String valueOf2 = String.valueOf(str6);
            String str10 = valueOf2;
            if (valueOf2.length() != 0) {
                str3 = "Failed to get app version for libraryName: ".concat(str10);
            } else {
                str3 = str2;
                new String("Failed to get app version for libraryName: ");
            }
            gmsLogger3.mo25215e("LibraryVersion", str3, iOException);
        }
        if (str7 == null) {
            str7 = "UNKNOWN";
            zzel.mo25212d("LibraryVersion", ".properties file is dropped during release process. Failure to read app version isexpected druing Google internal testing where locally-built libraries are used");
        }
        String put = this.zzen.put(str6, str7);
        return str7;
    }

    static {
        GmsLogger gmsLogger;
        LibraryVersion libraryVersion;
        new GmsLogger("LibraryVersion", "");
        zzel = gmsLogger;
        new LibraryVersion();
        zzem = libraryVersion;
    }
}
