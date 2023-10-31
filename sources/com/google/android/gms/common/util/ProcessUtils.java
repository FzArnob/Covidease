package com.google.android.gms.common.util;

import android.os.Process;
import android.os.StrictMode;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import javax.annotation.Nullable;

@KeepForSdk
public class ProcessUtils {
    private static String zzhf = null;
    private static int zzhg = 0;

    private ProcessUtils() {
    }

    @KeepForSdk
    @Nullable
    public static String getMyProcessName() {
        if (zzhf == null) {
            if (zzhg == 0) {
                zzhg = Process.myPid();
            }
            zzhf = zzd(zzhg);
        }
        return zzhf;
    }

    @Nullable
    private static String zzd(int i) {
        StringBuilder sb;
        int i2 = i;
        if (i2 <= 0) {
            return null;
        }
        BufferedReader bufferedReader = null;
        String str = null;
        try {
            new StringBuilder(25);
            BufferedReader zzk = zzk(sb.append("/proc/").append(i2).append("/cmdline").toString());
            bufferedReader = zzk;
            str = zzk.readLine().trim();
            IOUtils.closeQuietly((Closeable) bufferedReader);
        } catch (IOException e) {
            IOUtils.closeQuietly((Closeable) bufferedReader);
        } catch (Throwable th) {
            Throwable th2 = th;
            IOUtils.closeQuietly((Closeable) bufferedReader);
            throw th2;
        }
        return str;
    }

    /* JADX INFO: finally extract failed */
    private static BufferedReader zzk(String str) throws IOException {
        BufferedReader bufferedReader;
        Reader reader;
        String str2 = str;
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            BufferedReader bufferedReader2 = bufferedReader;
            new FileReader(str2);
            new BufferedReader(reader);
            BufferedReader bufferedReader3 = bufferedReader2;
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            return bufferedReader3;
        } catch (Throwable th) {
            Throwable th2 = th;
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th2;
        }
    }
}
