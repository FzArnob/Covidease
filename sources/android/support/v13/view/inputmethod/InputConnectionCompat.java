package android.support.v13.view.inputmethod;

import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;

public final class InputConnectionCompat {
    private static final String COMMIT_CONTENT_ACTION = "android.support.v13.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT";
    private static final String COMMIT_CONTENT_CONTENT_URI_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_URI";
    private static final String COMMIT_CONTENT_DESCRIPTION_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION";
    private static final String COMMIT_CONTENT_FLAGS_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS";
    private static final String COMMIT_CONTENT_LINK_URI_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI";
    private static final String COMMIT_CONTENT_OPTS_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_OPTS";
    private static final String COMMIT_CONTENT_RESULT_RECEIVER = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER";
    public static final int INPUT_CONTENT_GRANT_READ_URI_PERMISSION = 1;

    public interface OnCommitContentListener {
        boolean onCommitContent(InputContentInfoCompat inputContentInfoCompat, int i, Bundle bundle);
    }

    /* JADX INFO: finally extract failed */
    static boolean handlePerformPrivateCommand(@Nullable String action, @NonNull Bundle bundle, @NonNull OnCommitContentListener onCommitContentListener) {
        InputContentInfoCompat inputContentInfo;
        Bundle data = bundle;
        OnCommitContentListener onCommitContentListener2 = onCommitContentListener;
        if (!TextUtils.equals(COMMIT_CONTENT_ACTION, action)) {
            return false;
        }
        if (data == null) {
            return false;
        }
        ResultReceiver resultReceiver = null;
        try {
            resultReceiver = (ResultReceiver) data.getParcelable(COMMIT_CONTENT_RESULT_RECEIVER);
            Uri contentUri = (Uri) data.getParcelable(COMMIT_CONTENT_CONTENT_URI_KEY);
            ClipDescription description = (ClipDescription) data.getParcelable(COMMIT_CONTENT_DESCRIPTION_KEY);
            Uri linkUri = (Uri) data.getParcelable(COMMIT_CONTENT_LINK_URI_KEY);
            int flags = data.getInt(COMMIT_CONTENT_FLAGS_KEY);
            Bundle opts = (Bundle) data.getParcelable(COMMIT_CONTENT_OPTS_KEY);
            new InputContentInfoCompat(contentUri, description, linkUri);
            boolean result = onCommitContentListener2.onCommitContent(inputContentInfo, flags, opts);
            if (resultReceiver != null) {
                resultReceiver.send(result ? 1 : 0, (Bundle) null);
            }
            return result;
        } catch (Throwable th) {
            Throwable th2 = th;
            if (resultReceiver != null) {
                resultReceiver.send(0 != 0 ? 1 : 0, (Bundle) null);
            }
            throw th2;
        }
    }

    public static boolean commitContent(@NonNull InputConnection inputConnection, @NonNull EditorInfo editorInfo, @NonNull InputContentInfoCompat inputContentInfoCompat, int i, @Nullable Bundle bundle) {
        Bundle bundle2;
        InputConnection inputConnection2 = inputConnection;
        InputContentInfoCompat inputContentInfo = inputContentInfoCompat;
        int flags = i;
        Bundle opts = bundle;
        ClipDescription description = inputContentInfo.getDescription();
        boolean supported = false;
        String[] contentMimeTypes = EditorInfoCompat.getContentMimeTypes(editorInfo);
        int length = contentMimeTypes.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            if (description.hasMimeType(contentMimeTypes[i2])) {
                supported = true;
                break;
            }
            i2++;
        }
        if (!supported) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 25) {
            return inputConnection2.commitContent((InputContentInfo) inputContentInfo.unwrap(), flags, opts);
        }
        new Bundle();
        Bundle params = bundle2;
        params.putParcelable(COMMIT_CONTENT_CONTENT_URI_KEY, inputContentInfo.getContentUri());
        params.putParcelable(COMMIT_CONTENT_DESCRIPTION_KEY, inputContentInfo.getDescription());
        params.putParcelable(COMMIT_CONTENT_LINK_URI_KEY, inputContentInfo.getLinkUri());
        params.putInt(COMMIT_CONTENT_FLAGS_KEY, flags);
        params.putParcelable(COMMIT_CONTENT_OPTS_KEY, opts);
        return inputConnection2.performPrivateCommand(COMMIT_CONTENT_ACTION, params);
    }

    @NonNull
    public static InputConnection createWrapper(@NonNull InputConnection inputConnection, @NonNull EditorInfo editorInfo, @NonNull OnCommitContentListener onCommitContentListener) {
        InputConnection inputConnection2;
        InputConnection inputConnection3;
        Throwable th;
        Throwable th2;
        Throwable th3;
        InputConnection inputConnection4 = inputConnection;
        EditorInfo editorInfo2 = editorInfo;
        OnCommitContentListener onCommitContentListener2 = onCommitContentListener;
        if (inputConnection4 == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("inputConnection must be non-null");
            throw th4;
        } else if (editorInfo2 == null) {
            Throwable th5 = th2;
            new IllegalArgumentException("editorInfo must be non-null");
            throw th5;
        } else if (onCommitContentListener2 == null) {
            Throwable th6 = th;
            new IllegalArgumentException("onCommitContentListener must be non-null");
            throw th6;
        } else if (Build.VERSION.SDK_INT >= 25) {
            final OnCommitContentListener onCommitContentListener3 = onCommitContentListener2;
            new InputConnectionWrapper(inputConnection4, false) {
                public boolean commitContent(InputContentInfo inputContentInfo, int i, Bundle bundle) {
                    InputContentInfo inputContentInfo2 = inputContentInfo;
                    int flags = i;
                    Bundle opts = bundle;
                    if (onCommitContentListener3.onCommitContent(InputContentInfoCompat.wrap(inputContentInfo2), flags, opts)) {
                        return true;
                    }
                    return super.commitContent(inputContentInfo2, flags, opts);
                }
            };
            return inputConnection3;
        } else if (EditorInfoCompat.getContentMimeTypes(editorInfo2).length == 0) {
            return inputConnection4;
        } else {
            final OnCommitContentListener onCommitContentListener4 = onCommitContentListener2;
            new InputConnectionWrapper(inputConnection4, false) {
                public boolean performPrivateCommand(String str, Bundle bundle) {
                    String action = str;
                    Bundle data = bundle;
                    if (InputConnectionCompat.handlePerformPrivateCommand(action, data, onCommitContentListener4)) {
                        return true;
                    }
                    return super.performPrivateCommand(action, data);
                }
            };
            return inputConnection2;
        }
    }

    @Deprecated
    public InputConnectionCompat() {
    }
}
