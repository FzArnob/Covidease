package com.bumptech.glide.disklrucache;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.Charset;

final class Util {
    static final Charset US_ASCII = Charset.forName("US-ASCII");
    static final Charset UTF_8 = Charset.forName("UTF-8");

    private Util() {
    }

    /* JADX INFO: finally extract failed */
    static String readFully(Reader reader) throws IOException {
        StringWriter stringWriter;
        Reader reader2 = reader;
        try {
            new StringWriter();
            StringWriter writer = stringWriter;
            char[] buffer = new char[1024];
            while (true) {
                int read = reader2.read(buffer);
                int count = read;
                if (read != -1) {
                    writer.write(buffer, 0, count);
                } else {
                    String stringWriter2 = writer.toString();
                    reader2.close();
                    return stringWriter2;
                }
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            reader2.close();
            throw th2;
        }
    }

    static void deleteContents(File file) throws IOException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        File dir = file;
        File[] files = dir.listFiles();
        if (files == null) {
            Throwable th3 = th2;
            new StringBuilder();
            new IOException(sb2.append("not a readable directory: ").append(dir).toString());
            throw th3;
        }
        File[] arr$ = files;
        int len$ = arr$.length;
        for (int i$ = 0; i$ < len$; i$++) {
            File file2 = arr$[i$];
            if (file2.isDirectory()) {
                deleteContents(file2);
            }
            if (!file2.delete()) {
                Throwable th4 = th;
                new StringBuilder();
                new IOException(sb.append("failed to delete file: ").append(file2).toString());
                throw th4;
            }
        }
    }

    static void closeQuietly(Closeable closeable) {
        Closeable closeable2 = closeable;
        if (closeable2 != null) {
            try {
                closeable2.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                Exception exc = e2;
            }
        }
    }
}
