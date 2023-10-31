package android.support.p000v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.graphics.TypefaceCompatUtil */
public class TypefaceCompatUtil {
    private static final String CACHE_FILE_PREFIX = ".font";
    private static final String TAG = "TypefaceCompatUtil";

    private TypefaceCompatUtil() {
    }

    @Nullable
    public static File getTempFile(Context context) {
        StringBuilder sb;
        File file;
        StringBuilder sb2;
        Context context2 = context;
        new StringBuilder();
        String prefix = sb.append(CACHE_FILE_PREFIX).append(Process.myPid()).append("-").append(Process.myTid()).append("-").toString();
        int i = 0;
        while (i < 100) {
            File cacheDir = context2.getCacheDir();
            new StringBuilder();
            new File(cacheDir, sb2.append(prefix).append(i).toString());
            File file2 = file;
            try {
                if (file2.createNewFile()) {
                    return file2;
                }
                i++;
            } catch (IOException e) {
                IOException iOException = e;
            }
        }
        return null;
    }

    @Nullable
    @RequiresApi(19)
    private static ByteBuffer mmap(File file) {
        FileInputStream fileInputStream;
        Throwable th;
        try {
            FileInputStream fileInputStream2 = fileInputStream;
            new FileInputStream(file);
            FileInputStream fis = fileInputStream2;
            try {
                FileChannel channel = fis.getChannel();
                MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
                if (fis != null) {
                    if (0 != 0) {
                        try {
                            fis.close();
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            Throwable th4 = null;
                            th4.addSuppressed(th3);
                        }
                    } else {
                        fis.close();
                    }
                }
                return map;
            } catch (Throwable th5) {
                Throwable th6 = th5;
                if (fis != null) {
                    if (th != null) {
                        fis.close();
                    } else {
                        fis.close();
                    }
                }
                throw th6;
            }
        } catch (IOException e) {
            IOException iOException = e;
            return null;
        } catch (Throwable th7) {
            th.addSuppressed(th7);
        }
    }

    @Nullable
    @RequiresApi(19)
    public static ByteBuffer mmap(Context context, CancellationSignal cancellationSignal, Uri uri) {
        Throwable th;
        FileInputStream fileInputStream;
        Throwable th2;
        MappedByteBuffer map;
        try {
            ParcelFileDescriptor pfd = context.getContentResolver().openFileDescriptor(uri, "r", cancellationSignal);
            if (pfd == null) {
                if (pfd != null) {
                    if (0 != 0) {
                        try {
                            pfd.close();
                        } catch (Throwable th3) {
                            Throwable th4 = th3;
                            Throwable th5 = null;
                            th5.addSuppressed(th4);
                        }
                    } else {
                        pfd.close();
                    }
                }
                return null;
            }
            try {
                FileInputStream fileInputStream2 = fileInputStream;
                new FileInputStream(pfd.getFileDescriptor());
                FileInputStream fis = fileInputStream2;
                try {
                    FileChannel channel = fis.getChannel();
                    map = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
                    if (fis != null) {
                        if (0 != 0) {
                            fis.close();
                        } else {
                            fis.close();
                        }
                    }
                } catch (Throwable th6) {
                    Throwable th7 = th6;
                    if (fis != null) {
                        if (th2 != null) {
                            fis.close();
                        } else {
                            fis.close();
                        }
                    }
                    throw th7;
                }
            } catch (Throwable th8) {
                Throwable th9 = th8;
                th = th9;
                try {
                    throw th9;
                } catch (Throwable th10) {
                    Throwable th11 = th10;
                    if (pfd != null) {
                        if (th != null) {
                            pfd.close();
                        } else {
                            pfd.close();
                        }
                    }
                    throw th11;
                }
            }
            if (pfd != null) {
                if (0 != 0) {
                    try {
                        pfd.close();
                    } catch (Throwable th12) {
                        Throwable th13 = th12;
                        Throwable th14 = null;
                        th14.addSuppressed(th13);
                    }
                } else {
                    pfd.close();
                }
            }
            return map;
        } catch (IOException e) {
            IOException iOException = e;
            return null;
        } catch (Throwable th15) {
            th.addSuppressed(th15);
        }
    }

    /* JADX INFO: finally extract failed */
    @Nullable
    @RequiresApi(19)
    public static ByteBuffer copyToDirectBuffer(Context context, Resources resources, int i) {
        Resources res = resources;
        int id = i;
        File tmpFile = getTempFile(context);
        if (tmpFile == null) {
            return null;
        }
        try {
            if (!copyToFile(tmpFile, res, id)) {
                boolean delete = tmpFile.delete();
                return null;
            }
            ByteBuffer mmap = mmap(tmpFile);
            boolean delete2 = tmpFile.delete();
            return mmap;
        } catch (Throwable th) {
            Throwable th2 = th;
            boolean delete3 = tmpFile.delete();
            throw th2;
        }
    }

    /* JADX WARNING: type inference failed for: r8v24, types: [java.io.FileOutputStream] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean copyToFile(java.io.File r13, java.io.InputStream r14) {
        /*
            r0 = r13
            r1 = r14
            r8 = 0
            r2 = r8
            android.os.StrictMode$ThreadPolicy r8 = android.os.StrictMode.allowThreadDiskWrites()
            r3 = r8
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x003b }
            r12 = r8
            r8 = r12
            r9 = r12
            r10 = r0
            r11 = 0
            r9.<init>(r10, r11)     // Catch:{ IOException -> 0x003b }
            r2 = r8
            r8 = 1024(0x400, float:1.435E-42)
            byte[] r8 = new byte[r8]     // Catch:{ IOException -> 0x003b }
            r4 = r8
        L_0x0019:
            r8 = r1
            r9 = r4
            int r8 = r8.read(r9)     // Catch:{ IOException -> 0x003b }
            r12 = r8
            r8 = r12
            r9 = r12
            r5 = r9
            r9 = -1
            if (r8 == r9) goto L_0x002e
            r8 = r2
            r9 = r4
            r10 = 0
            r11 = r5
            r8.write(r9, r10, r11)     // Catch:{ IOException -> 0x003b }
            goto L_0x0019
        L_0x002e:
            r8 = 1
            r6 = r8
            r8 = r2
            closeQuietly(r8)
            r8 = r3
            android.os.StrictMode.setThreadPolicy(r8)
            r8 = r6
            r0 = r8
        L_0x003a:
            return r0
        L_0x003b:
            r8 = move-exception
            r4 = r8
            java.lang.String r8 = "TypefaceCompatUtil"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x006d }
            r12 = r9
            r9 = r12
            r10 = r12
            r10.<init>()     // Catch:{ all -> 0x006d }
            java.lang.String r10 = "Error copying resource contents to temp file: "
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ all -> 0x006d }
            r10 = r4
            java.lang.String r10 = r10.getMessage()     // Catch:{ all -> 0x006d }
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ all -> 0x006d }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x006d }
            int r8 = android.util.Log.e(r8, r9)     // Catch:{ all -> 0x006d }
            r8 = 0
            r5 = r8
            r8 = r2
            closeQuietly(r8)
            r8 = r3
            android.os.StrictMode.setThreadPolicy(r8)
            r8 = r5
            r0 = r8
            goto L_0x003a
        L_0x006d:
            r8 = move-exception
            r7 = r8
            r8 = r2
            closeQuietly(r8)
            r8 = r3
            android.os.StrictMode.setThreadPolicy(r8)
            r8 = r7
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.graphics.TypefaceCompatUtil.copyToFile(java.io.File, java.io.InputStream):boolean");
    }

    /* JADX INFO: finally extract failed */
    public static boolean copyToFile(File file, Resources res, int id) {
        File file2 = file;
        InputStream is = null;
        try {
            is = res.openRawResource(id);
            boolean copyToFile = copyToFile(file2, is);
            closeQuietly(is);
            return copyToFile;
        } catch (Throwable th) {
            Throwable th2 = th;
            closeQuietly(is);
            throw th2;
        }
    }

    public static void closeQuietly(Closeable closeable) {
        Closeable c = closeable;
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                IOException iOException = e;
            }
        }
    }
}
