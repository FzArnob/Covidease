package android.support.p000v4.provider;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.File;

/* renamed from: android.support.v4.provider.DocumentFile */
public abstract class DocumentFile {
    static final String TAG = "DocumentFile";
    @Nullable
    private final DocumentFile mParent;

    public abstract boolean canRead();

    public abstract boolean canWrite();

    @Nullable
    public abstract DocumentFile createDirectory(@NonNull String str);

    @Nullable
    public abstract DocumentFile createFile(@NonNull String str, @NonNull String str2);

    public abstract boolean delete();

    public abstract boolean exists();

    @Nullable
    public abstract String getName();

    @Nullable
    public abstract String getType();

    @NonNull
    public abstract Uri getUri();

    public abstract boolean isDirectory();

    public abstract boolean isFile();

    public abstract boolean isVirtual();

    public abstract long lastModified();

    public abstract long length();

    @NonNull
    public abstract DocumentFile[] listFiles();

    public abstract boolean renameTo(@NonNull String str);

    DocumentFile(@Nullable DocumentFile parent) {
        this.mParent = parent;
    }

    @NonNull
    public static DocumentFile fromFile(@NonNull File file) {
        DocumentFile documentFile;
        new RawDocumentFile((DocumentFile) null, file);
        return documentFile;
    }

    @Nullable
    public static DocumentFile fromSingleUri(@NonNull Context context, @NonNull Uri uri) {
        DocumentFile documentFile;
        Context context2 = context;
        Uri singleUri = uri;
        if (Build.VERSION.SDK_INT < 19) {
            return null;
        }
        new SingleDocumentFile((DocumentFile) null, context2, singleUri);
        return documentFile;
    }

    @Nullable
    public static DocumentFile fromTreeUri(@NonNull Context context, @NonNull Uri uri) {
        DocumentFile documentFile;
        Context context2 = context;
        Uri treeUri = uri;
        if (Build.VERSION.SDK_INT < 21) {
            return null;
        }
        new TreeDocumentFile((DocumentFile) null, context2, DocumentsContract.buildDocumentUriUsingTree(treeUri, DocumentsContract.getTreeDocumentId(treeUri)));
        return documentFile;
    }

    public static boolean isDocumentUri(@NonNull Context context, @Nullable Uri uri) {
        Context context2 = context;
        Uri uri2 = uri;
        if (Build.VERSION.SDK_INT >= 19) {
            return DocumentsContract.isDocumentUri(context2, uri2);
        }
        return false;
    }

    @Nullable
    public DocumentFile getParentFile() {
        return this.mParent;
    }

    @Nullable
    public DocumentFile findFile(@NonNull String str) {
        String displayName = str;
        DocumentFile[] listFiles = listFiles();
        int length = listFiles.length;
        for (int i = 0; i < length; i++) {
            DocumentFile doc = listFiles[i];
            if (displayName.equals(doc.getName())) {
                return doc;
            }
        }
        return null;
    }
}
