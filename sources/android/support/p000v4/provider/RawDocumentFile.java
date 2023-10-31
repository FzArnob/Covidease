package android.support.p000v4.provider;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/* renamed from: android.support.v4.provider.RawDocumentFile */
class RawDocumentFile extends DocumentFile {
    private File mFile;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RawDocumentFile(@Nullable DocumentFile parent, File file) {
        super(parent);
        this.mFile = file;
    }

    @Nullable
    public DocumentFile createFile(String mimeType, String str) {
        File file;
        StringBuilder sb;
        DocumentFile documentFile;
        StringBuilder sb2;
        String displayName = str;
        String extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType);
        if (extension != null) {
            new StringBuilder();
            displayName = sb2.append(displayName).append(".").append(extension).toString();
        }
        new File(this.mFile, displayName);
        File target = file;
        try {
            boolean createNewFile = target.createNewFile();
            DocumentFile documentFile2 = documentFile;
            new RawDocumentFile(this, target);
            return documentFile2;
        } catch (IOException e) {
            new StringBuilder();
            int w = Log.w("DocumentFile", sb.append("Failed to createFile: ").append(e).toString());
            return null;
        }
    }

    @Nullable
    public DocumentFile createDirectory(String displayName) {
        File file;
        RawDocumentFile rawDocumentFile;
        new File(this.mFile, displayName);
        File target = file;
        if (!target.isDirectory() && !target.mkdir()) {
            return null;
        }
        new RawDocumentFile(this, target);
        return rawDocumentFile;
    }

    public Uri getUri() {
        return Uri.fromFile(this.mFile);
    }

    public String getName() {
        return this.mFile.getName();
    }

    @Nullable
    public String getType() {
        if (this.mFile.isDirectory()) {
            return null;
        }
        return getTypeForName(this.mFile.getName());
    }

    public boolean isDirectory() {
        return this.mFile.isDirectory();
    }

    public boolean isFile() {
        return this.mFile.isFile();
    }

    public boolean isVirtual() {
        return false;
    }

    public long lastModified() {
        return this.mFile.lastModified();
    }

    public long length() {
        return this.mFile.length();
    }

    public boolean canRead() {
        return this.mFile.canRead();
    }

    public boolean canWrite() {
        return this.mFile.canWrite();
    }

    public boolean delete() {
        boolean deleteContents = deleteContents(this.mFile);
        return this.mFile.delete();
    }

    public boolean exists() {
        return this.mFile.exists();
    }

    public DocumentFile[] listFiles() {
        ArrayList arrayList;
        Object obj;
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        File[] files = this.mFile.listFiles();
        if (files != null) {
            File[] fileArr = files;
            int length = fileArr.length;
            for (int i = 0; i < length; i++) {
                new RawDocumentFile(this, fileArr[i]);
                boolean add = arrayList2.add(obj);
            }
        }
        return (DocumentFile[]) arrayList2.toArray(new DocumentFile[arrayList2.size()]);
    }

    public boolean renameTo(String displayName) {
        File file;
        new File(this.mFile.getParentFile(), displayName);
        File target = file;
        if (!this.mFile.renameTo(target)) {
            return false;
        }
        this.mFile = target;
        return true;
    }

    private static String getTypeForName(String str) {
        String mime;
        String name = str;
        int lastDot = name.lastIndexOf(46);
        if (lastDot < 0 || (mime = MimeTypeMap.getSingleton().getMimeTypeFromExtension(name.substring(lastDot + 1).toLowerCase())) == null) {
            return "application/octet-stream";
        }
        return mime;
    }

    private static boolean deleteContents(File dir) {
        StringBuilder sb;
        File[] files = dir.listFiles();
        boolean success = true;
        if (files != null) {
            File[] fileArr = files;
            int length = fileArr.length;
            for (int i = 0; i < length; i++) {
                File file = fileArr[i];
                if (file.isDirectory()) {
                    success &= deleteContents(file);
                }
                if (!file.delete()) {
                    new StringBuilder();
                    int w = Log.w("DocumentFile", sb.append("Failed to delete ").append(file).toString());
                    success = false;
                }
            }
        }
        return success;
    }
}
