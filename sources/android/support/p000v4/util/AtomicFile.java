package android.support.p000v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* renamed from: android.support.v4.util.AtomicFile */
public class AtomicFile {
    private final File mBackupName;
    private final File mBaseName;

    public AtomicFile(@NonNull File file) {
        File file2;
        StringBuilder sb;
        File baseName = file;
        this.mBaseName = baseName;
        new StringBuilder();
        new File(sb.append(baseName.getPath()).append(".bak").toString());
        this.mBackupName = file2;
    }

    @NonNull
    public File getBaseFile() {
        return this.mBaseName;
    }

    public void delete() {
        boolean delete = this.mBaseName.delete();
        boolean delete2 = this.mBackupName.delete();
    }

    @NonNull
    public FileOutputStream startWrite() throws IOException {
        Throwable th;
        StringBuilder sb;
        FileOutputStream fileOutputStream;
        FileOutputStream str;
        Throwable th2;
        StringBuilder sb2;
        FileOutputStream fileOutputStream2;
        StringBuilder sb3;
        if (this.mBaseName.exists()) {
            if (this.mBackupName.exists()) {
                boolean delete = this.mBaseName.delete();
            } else if (!this.mBaseName.renameTo(this.mBackupName)) {
                new StringBuilder();
                int w = Log.w("AtomicFile", sb3.append("Couldn't rename file ").append(this.mBaseName).append(" to backup file ").append(this.mBackupName).toString());
            }
        }
        try {
            FileOutputStream fileOutputStream3 = fileOutputStream2;
            new FileOutputStream(this.mBaseName);
            str = fileOutputStream3;
        } catch (FileNotFoundException e) {
            FileNotFoundException fileNotFoundException = e;
            if (!this.mBaseName.getParentFile().mkdirs()) {
                Throwable th3 = th2;
                new StringBuilder();
                new IOException(sb2.append("Couldn't create directory ").append(this.mBaseName).toString());
                throw th3;
            }
            try {
                FileOutputStream fileOutputStream4 = fileOutputStream;
                new FileOutputStream(this.mBaseName);
                str = fileOutputStream4;
            } catch (FileNotFoundException e2) {
                FileNotFoundException fileNotFoundException2 = e2;
                Throwable th4 = th;
                new StringBuilder();
                new IOException(sb.append("Couldn't create ").append(this.mBaseName).toString());
                throw th4;
            }
        }
        return str;
    }

    public void finishWrite(@Nullable FileOutputStream fileOutputStream) {
        FileOutputStream str = fileOutputStream;
        if (str != null) {
            boolean sync = sync(str);
            try {
                str.close();
                boolean delete = this.mBackupName.delete();
            } catch (IOException e) {
                int w = Log.w("AtomicFile", "finishWrite: Got exception:", e);
            }
        }
    }

    public void failWrite(@Nullable FileOutputStream fileOutputStream) {
        FileOutputStream str = fileOutputStream;
        if (str != null) {
            boolean sync = sync(str);
            try {
                str.close();
                boolean delete = this.mBaseName.delete();
                boolean renameTo = this.mBackupName.renameTo(this.mBaseName);
            } catch (IOException e) {
                int w = Log.w("AtomicFile", "failWrite: Got exception:", e);
            }
        }
    }

    @NonNull
    public FileInputStream openRead() throws FileNotFoundException {
        FileInputStream fileInputStream;
        if (this.mBackupName.exists()) {
            boolean delete = this.mBaseName.delete();
            boolean renameTo = this.mBackupName.renameTo(this.mBaseName);
        }
        new FileInputStream(this.mBaseName);
        return fileInputStream;
    }

    /* JADX INFO: finally extract failed */
    @NonNull
    public byte[] readFully() throws IOException {
        FileInputStream stream = openRead();
        int pos = 0;
        try {
            byte[] data = new byte[stream.available()];
            while (true) {
                int amt = stream.read(data, pos, data.length - pos);
                if (amt <= 0) {
                    byte[] bArr = data;
                    stream.close();
                    return bArr;
                }
                pos += amt;
                int avail = stream.available();
                if (avail > data.length - pos) {
                    byte[] newData = new byte[(pos + avail)];
                    System.arraycopy(data, 0, newData, 0, pos);
                    data = newData;
                }
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            stream.close();
            throw th2;
        }
    }

    private static boolean sync(@NonNull FileOutputStream stream) {
        try {
            stream.getFD().sync();
            return true;
        } catch (IOException e) {
            IOException iOException = e;
            return false;
        }
    }
}
