package com.google.appinventor.components.runtime.multidex;

import android.support.p000v4.media.session.PlaybackStateCompat;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.CRC32;
import java.util.zip.ZipException;

/* renamed from: com.google.appinventor.components.runtime.multidex.b */
final class C1115b {

    /* renamed from: com.google.appinventor.components.runtime.multidex.b$a */
    static class C1116a {
        long KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;
        long l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j;

        C1116a() {
        }
    }

    /* JADX INFO: finally extract failed */
    static long B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(File file) throws IOException {
        RandomAccessFile randomAccessFile;
        new RandomAccessFile(file, "r");
        RandomAccessFile randomAccessFile2 = randomAccessFile;
        try {
            long hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(randomAccessFile2, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(randomAccessFile2));
            randomAccessFile2.close();
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        } catch (Throwable th) {
            Throwable th2 = th;
            randomAccessFile2.close();
            throw th2;
        }
    }

    private static C1116a hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(RandomAccessFile randomAccessFile) throws IOException, ZipException {
        C1116a aVar;
        long j;
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        RandomAccessFile randomAccessFile2 = randomAccessFile;
        long length = randomAccessFile2.length() - 22;
        long j2 = length;
        if (length < 0) {
            Throwable th3 = th2;
            new StringBuilder("File too short to be a zip file: ");
            new ZipException(sb.append(randomAccessFile2.length()).toString());
            throw th3;
        }
        long j3 = j2 - PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        long j4 = j3;
        if (j3 < 0) {
            j4 = 0;
        }
        int reverseBytes = Integer.reverseBytes(101010256);
        do {
            randomAccessFile2.seek(j2);
            if (randomAccessFile2.readInt() != reverseBytes) {
                j = j2 - 1;
                j2 = j;
            } else {
                int skipBytes = randomAccessFile2.skipBytes(2);
                int skipBytes2 = randomAccessFile2.skipBytes(2);
                int skipBytes3 = randomAccessFile2.skipBytes(2);
                int skipBytes4 = randomAccessFile2.skipBytes(2);
                new C1116a();
                C1116a aVar2 = aVar;
                C1116a aVar3 = aVar2;
                aVar2.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = ((long) Integer.reverseBytes(randomAccessFile2.readInt())) & 4294967295L;
                aVar3.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j = ((long) Integer.reverseBytes(randomAccessFile2.readInt())) & 4294967295L;
                return aVar3;
            }
        } while (j >= j4);
        Throwable th4 = th;
        new ZipException("End Of Central Directory signature not found");
        throw th4;
    }

    private static long hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(RandomAccessFile randomAccessFile, C1116a aVar) throws IOException {
        CRC32 crc32;
        RandomAccessFile randomAccessFile2 = randomAccessFile;
        C1116a aVar2 = aVar;
        new CRC32();
        CRC32 crc322 = crc32;
        long j = aVar2.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;
        randomAccessFile2.seek(aVar2.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j);
        byte[] bArr = new byte[16384];
        int read = randomAccessFile2.read(bArr, 0, (int) Math.min(16384, j));
        while (true) {
            int i = read;
            if (i == -1) {
                break;
            }
            crc322.update(bArr, 0, i);
            long j2 = j - ((long) i);
            j = j2;
            if (j2 == 0) {
                break;
            }
            read = randomAccessFile2.read(bArr, 0, (int) Math.min(16384, j));
        }
        return crc322.getValue();
    }
}
