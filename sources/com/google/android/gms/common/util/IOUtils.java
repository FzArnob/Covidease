package com.google.android.gms.common.util;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.annotation.Nullable;

@ShowFirstParty
@KeepForSdk
@Deprecated
public final class IOUtils {
    private IOUtils() {
    }

    @KeepForSdk
    public static void closeQuietly(@Nullable Closeable closeable) {
        Closeable closeable2 = closeable;
        if (closeable2 != null) {
            try {
                closeable2.close();
            } catch (IOException e) {
            }
        }
    }

    @KeepForSdk
    public static void closeQuietly(@Nullable ParcelFileDescriptor parcelFileDescriptor) {
        ParcelFileDescriptor parcelFileDescriptor2 = parcelFileDescriptor;
        if (parcelFileDescriptor2 != null) {
            try {
                parcelFileDescriptor2.close();
            } catch (IOException e) {
            }
        }
    }

    @KeepForSdk
    public static boolean isGzipByteBuffer(byte[] bArr) {
        byte[] bArr2 = bArr;
        return bArr2.length > 1 && ((bArr2[0] & Ev3Constants.Opcode.TST) | ((bArr2[1] & Ev3Constants.Opcode.TST) << 8)) == 35615;
    }

    @KeepForSdk
    @Deprecated
    public static long copyStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        return zza(inputStream, outputStream, false);
    }

    @Deprecated
    private static long zza(InputStream inputStream, OutputStream outputStream, boolean z) throws IOException {
        return copyStream(inputStream, outputStream, z, 1024);
    }

    /* JADX INFO: finally extract failed */
    @KeepForSdk
    @Deprecated
    public static long copyStream(InputStream inputStream, OutputStream outputStream, boolean z, int i) throws IOException {
        InputStream inputStream2 = inputStream;
        OutputStream outputStream2 = outputStream;
        boolean z2 = z;
        int i2 = i;
        byte[] bArr = new byte[i2];
        long j = 0;
        while (true) {
            try {
                int read = inputStream2.read(bArr, 0, i2);
                int i3 = read;
                if (read == -1) {
                    break;
                }
                j += (long) i3;
                outputStream2.write(bArr, 0, i3);
            } catch (Throwable th) {
                Throwable th2 = th;
                if (z2) {
                    closeQuietly((Closeable) inputStream2);
                    closeQuietly((Closeable) outputStream2);
                }
                throw th2;
            }
        }
        if (z2) {
            closeQuietly((Closeable) inputStream2);
            closeQuietly((Closeable) outputStream2);
        }
        return j;
    }

    @KeepForSdk
    @Deprecated
    public static byte[] readInputStreamFully(InputStream inputStream) throws IOException {
        return readInputStreamFully(inputStream, true);
    }

    @KeepForSdk
    @Deprecated
    public static byte[] readInputStreamFully(InputStream inputStream, boolean z) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        new ByteArrayOutputStream();
        ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
        long zza = zza(inputStream, byteArrayOutputStream2, z);
        return byteArrayOutputStream2.toByteArray();
    }

    @KeepForSdk
    @Deprecated
    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        new ByteArrayOutputStream();
        ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream3 = byteArrayOutputStream2;
        InputStream inputStream2 = inputStream;
        InputStream inputStream3 = inputStream2;
        Object checkNotNull = Preconditions.checkNotNull(inputStream2);
        Object checkNotNull2 = Preconditions.checkNotNull(byteArrayOutputStream3);
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream3.read(bArr);
            int i = read;
            if (read == -1) {
                return byteArrayOutputStream2.toByteArray();
            }
            byteArrayOutputStream3.write(bArr, 0, i);
        }
    }
}
