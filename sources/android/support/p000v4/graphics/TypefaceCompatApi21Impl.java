package android.support.p000v4.graphics;

import android.os.ParcelFileDescriptor;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.File;

@RequiresApi(21)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.graphics.TypefaceCompatApi21Impl */
class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl {
    private static final String TAG = "TypefaceCompatApi21Impl";

    TypefaceCompatApi21Impl() {
    }

    private File getFile(ParcelFileDescriptor parcelFileDescriptor) {
        StringBuilder sb;
        File file;
        ParcelFileDescriptor fd = parcelFileDescriptor;
        try {
            new StringBuilder();
            String path = Os.readlink(sb.append("/proc/self/fd/").append(fd.getFd()).toString());
            if (!OsConstants.S_ISREG(Os.stat(path).st_mode)) {
                return null;
            }
            File file2 = file;
            new File(path);
            return file2;
        } catch (ErrnoException e) {
            ErrnoException errnoException = e;
            return null;
        }
    }

    /* JADX WARNING: type inference failed for: r20v36, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r20v37, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r20v50, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r20v51, types: [java.io.FileInputStream] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r26, android.os.CancellationSignal r27, @android.support.annotation.NonNull android.support.p000v4.provider.FontsContractCompat.FontInfo[] r28, int r29) {
        /*
            r25 = this;
            r2 = r25
            r3 = r26
            r4 = r27
            r5 = r28
            r6 = r29
            r20 = r5
            r0 = r20
            int r0 = r0.length
            r20 = r0
            r21 = 1
            r0 = r20
            r1 = r21
            if (r0 >= r1) goto L_0x001e
            r20 = 0
            r2 = r20
        L_0x001d:
            return r2
        L_0x001e:
            r20 = r2
            r21 = r5
            r22 = r6
            android.support.v4.provider.FontsContractCompat$FontInfo r20 = r20.findBestInfo(r21, r22)
            r7 = r20
            r20 = r3
            android.content.ContentResolver r20 = r20.getContentResolver()
            r8 = r20
            r20 = r8
            r21 = r7
            android.net.Uri r21 = r21.getUri()     // Catch:{ IOException -> 0x00d0 }
            java.lang.String r22 = "r"
            r23 = r4
            android.os.ParcelFileDescriptor r20 = r20.openFileDescriptor(r21, r22, r23)     // Catch:{ IOException -> 0x00d0 }
            r9 = r20
            r20 = 0
            r10 = r20
            r20 = r2
            r21 = r9
            java.io.File r20 = r20.getFile(r21)     // Catch:{ Throwable -> 0x00b3 }
            r11 = r20
            r20 = r11
            if (r20 == 0) goto L_0x005f
            r20 = r11
            boolean r20 = r20.canRead()     // Catch:{ Throwable -> 0x00b3 }
            if (r20 != 0) goto L_0x0118
        L_0x005f:
            java.io.FileInputStream r20 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x00b3 }
            r24 = r20
            r20 = r24
            r21 = r24
            r22 = r9
            java.io.FileDescriptor r22 = r22.getFileDescriptor()     // Catch:{ Throwable -> 0x00b3 }
            r21.<init>(r22)     // Catch:{ Throwable -> 0x00b3 }
            r12 = r20
            r20 = 0
            r13 = r20
            r20 = r2
            r21 = r3
            r22 = r12
            android.graphics.Typeface r20 = super.createFromInputStream(r21, r22)     // Catch:{ Throwable -> 0x00ea }
            r14 = r20
            r20 = r12
            if (r20 == 0) goto L_0x008f
            r20 = r13
            if (r20 == 0) goto L_0x00ad
            r20 = r12
            r20.close()     // Catch:{ Throwable -> 0x00a2 }
        L_0x008f:
            r20 = r9
            if (r20 == 0) goto L_0x009c
            r20 = r10
            if (r20 == 0) goto L_0x00e4
            r20 = r9
            r20.close()     // Catch:{ Throwable -> 0x00d9 }
        L_0x009c:
            r20 = r14
            r2 = r20
            goto L_0x001d
        L_0x00a2:
            r20 = move-exception
            r15 = r20
            r20 = r13
            r21 = r15
            r20.addSuppressed(r21)     // Catch:{ Throwable -> 0x00b3 }
            goto L_0x008f
        L_0x00ad:
            r20 = r12
            r20.close()     // Catch:{ Throwable -> 0x00b3 }
            goto L_0x008f
        L_0x00b3:
            r20 = move-exception
            r11 = r20
            r20 = r11
            r10 = r20
            r20 = r11
            throw r20     // Catch:{ all -> 0x00bd }
        L_0x00bd:
            r20 = move-exception
            r18 = r20
            r20 = r9
            if (r20 == 0) goto L_0x00cd
            r20 = r10
            if (r20 == 0) goto L_0x0150
            r20 = r9
            r20.close()     // Catch:{ Throwable -> 0x0144 }
        L_0x00cd:
            r20 = r18
            throw r20     // Catch:{ IOException -> 0x00d0 }
        L_0x00d0:
            r20 = move-exception
            r9 = r20
            r20 = 0
            r2 = r20
            goto L_0x001d
        L_0x00d9:
            r20 = move-exception
            r15 = r20
            r20 = r10
            r21 = r15
            r20.addSuppressed(r21)     // Catch:{ IOException -> 0x00d0 }
            goto L_0x009c
        L_0x00e4:
            r20 = r9
            r20.close()     // Catch:{ IOException -> 0x00d0 }
            goto L_0x009c
        L_0x00ea:
            r20 = move-exception
            r14 = r20
            r20 = r14
            r13 = r20
            r20 = r14
            throw r20     // Catch:{ all -> 0x00f4 }
        L_0x00f4:
            r20 = move-exception
            r16 = r20
            r20 = r12
            if (r20 == 0) goto L_0x0104
            r20 = r13
            if (r20 == 0) goto L_0x0112
            r20 = r12
            r20.close()     // Catch:{ Throwable -> 0x0107 }
        L_0x0104:
            r20 = r16
            throw r20     // Catch:{ Throwable -> 0x00b3 }
        L_0x0107:
            r20 = move-exception
            r17 = r20
            r20 = r13
            r21 = r17
            r20.addSuppressed(r21)     // Catch:{ Throwable -> 0x00b3 }
            goto L_0x0104
        L_0x0112:
            r20 = r12
            r20.close()     // Catch:{ Throwable -> 0x00b3 }
            goto L_0x0104
        L_0x0118:
            r20 = r11
            android.graphics.Typeface r20 = android.graphics.Typeface.createFromFile(r20)     // Catch:{ Throwable -> 0x00b3 }
            r12 = r20
            r20 = r9
            if (r20 == 0) goto L_0x012d
            r20 = r10
            if (r20 == 0) goto L_0x013e
            r20 = r9
            r20.close()     // Catch:{ Throwable -> 0x0133 }
        L_0x012d:
            r20 = r12
            r2 = r20
            goto L_0x001d
        L_0x0133:
            r20 = move-exception
            r13 = r20
            r20 = r10
            r21 = r13
            r20.addSuppressed(r21)     // Catch:{ IOException -> 0x00d0 }
            goto L_0x012d
        L_0x013e:
            r20 = r9
            r20.close()     // Catch:{ IOException -> 0x00d0 }
            goto L_0x012d
        L_0x0144:
            r20 = move-exception
            r19 = r20
            r20 = r10
            r21 = r19
            r20.addSuppressed(r21)     // Catch:{ IOException -> 0x00d0 }
            goto L_0x00cd
        L_0x0150:
            r20 = r9
            r20.close()     // Catch:{ IOException -> 0x00d0 }
            goto L_0x00cd
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.graphics.TypefaceCompatApi21Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, android.support.v4.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }
}
