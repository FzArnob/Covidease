package android.support.p000v4.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.util.ArrayList;

@RequiresApi(21)
/* renamed from: android.support.v4.provider.TreeDocumentFile */
class TreeDocumentFile extends DocumentFile {
    private Context mContext;
    private Uri mUri;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TreeDocumentFile(@Nullable DocumentFile parent, Context context, Uri uri) {
        super(parent);
        this.mContext = context;
        this.mUri = uri;
    }

    @Nullable
    public DocumentFile createFile(String mimeType, String displayName) {
        TreeDocumentFile treeDocumentFile;
        TreeDocumentFile treeDocumentFile2;
        Uri result = createFile(this.mContext, this.mUri, mimeType, displayName);
        if (result != null) {
            treeDocumentFile = treeDocumentFile2;
            new TreeDocumentFile(this, this.mContext, result);
        } else {
            treeDocumentFile = null;
        }
        return treeDocumentFile;
    }

    @Nullable
    private static Uri createFile(Context context, Uri self, String mimeType, String displayName) {
        try {
            return DocumentsContract.createDocument(context.getContentResolver(), self, mimeType, displayName);
        } catch (Exception e) {
            Exception exc = e;
            return null;
        }
    }

    @Nullable
    public DocumentFile createDirectory(String displayName) {
        TreeDocumentFile treeDocumentFile;
        TreeDocumentFile treeDocumentFile2;
        Uri result = createFile(this.mContext, this.mUri, "vnd.android.document/directory", displayName);
        if (result != null) {
            treeDocumentFile = treeDocumentFile2;
            new TreeDocumentFile(this, this.mContext, result);
        } else {
            treeDocumentFile = null;
        }
        return treeDocumentFile;
    }

    public Uri getUri() {
        return this.mUri;
    }

    @Nullable
    public String getName() {
        return DocumentsContractApi19.getName(this.mContext, this.mUri);
    }

    @Nullable
    public String getType() {
        return DocumentsContractApi19.getType(this.mContext, this.mUri);
    }

    public boolean isDirectory() {
        return DocumentsContractApi19.isDirectory(this.mContext, this.mUri);
    }

    public boolean isFile() {
        return DocumentsContractApi19.isFile(this.mContext, this.mUri);
    }

    public boolean isVirtual() {
        return DocumentsContractApi19.isVirtual(this.mContext, this.mUri);
    }

    public long lastModified() {
        return DocumentsContractApi19.lastModified(this.mContext, this.mUri);
    }

    public long length() {
        return DocumentsContractApi19.length(this.mContext, this.mUri);
    }

    public boolean canRead() {
        return DocumentsContractApi19.canRead(this.mContext, this.mUri);
    }

    public boolean canWrite() {
        return DocumentsContractApi19.canWrite(this.mContext, this.mUri);
    }

    public boolean delete() {
        try {
            return DocumentsContract.deleteDocument(this.mContext.getContentResolver(), this.mUri);
        } catch (Exception e) {
            Exception exc = e;
            return false;
        }
    }

    public boolean exists() {
        return DocumentsContractApi19.exists(this.mContext, this.mUri);
    }

    public DocumentFile[] listFiles() {
        ArrayList arrayList;
        StringBuilder sb;
        DocumentFile documentFile;
        ContentResolver resolver = this.mContext.getContentResolver();
        Uri childrenUri = DocumentsContract.buildChildDocumentsUriUsingTree(this.mUri, DocumentsContract.getDocumentId(this.mUri));
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        try {
            Cursor c = resolver.query(childrenUri, new String[]{"document_id"}, (String) null, (String[]) null, (String) null);
            while (c.moveToNext()) {
                boolean add = arrayList2.add(DocumentsContract.buildDocumentUriUsingTree(this.mUri, c.getString(0)));
            }
            closeQuietly(c);
        } catch (Exception e) {
            Exception e2 = e;
            new StringBuilder();
            int w = Log.w("DocumentFile", sb.append("Failed query: ").append(e2).toString());
            closeQuietly((AutoCloseable) null);
        } catch (Throwable th) {
            closeQuietly((AutoCloseable) null);
            throw th;
        }
        Uri[] result = (Uri[]) arrayList2.toArray(new Uri[arrayList2.size()]);
        DocumentFile[] resultFiles = new DocumentFile[result.length];
        for (int i = 0; i < result.length; i++) {
            new TreeDocumentFile(this, this.mContext, result[i]);
            resultFiles[i] = documentFile;
        }
        return resultFiles;
    }

    private static void closeQuietly(@Nullable AutoCloseable autoCloseable) {
        AutoCloseable closeable = autoCloseable;
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                Exception exc = e2;
            }
        }
    }

    public boolean renameTo(String displayName) {
        try {
            Uri result = DocumentsContract.renameDocument(this.mContext.getContentResolver(), this.mUri, displayName);
            if (result == null) {
                return false;
            }
            this.mUri = result;
            return true;
        } catch (Exception e) {
            Exception exc = e;
            return false;
        }
    }
}
