package android.support.v13.view.inputmethod;

import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.inputmethod.InputContentInfo;

public final class InputContentInfoCompat {
    private final InputContentInfoCompatImpl mImpl;

    private interface InputContentInfoCompatImpl {
        @NonNull
        Uri getContentUri();

        @NonNull
        ClipDescription getDescription();

        @Nullable
        Object getInputContentInfo();

        @Nullable
        Uri getLinkUri();

        void releasePermission();

        void requestPermission();
    }

    private static final class InputContentInfoCompatBaseImpl implements InputContentInfoCompatImpl {
        @NonNull
        private final Uri mContentUri;
        @NonNull
        private final ClipDescription mDescription;
        @Nullable
        private final Uri mLinkUri;

        InputContentInfoCompatBaseImpl(@NonNull Uri contentUri, @NonNull ClipDescription description, @Nullable Uri linkUri) {
            this.mContentUri = contentUri;
            this.mDescription = description;
            this.mLinkUri = linkUri;
        }

        @NonNull
        public Uri getContentUri() {
            return this.mContentUri;
        }

        @NonNull
        public ClipDescription getDescription() {
            return this.mDescription;
        }

        @Nullable
        public Uri getLinkUri() {
            return this.mLinkUri;
        }

        @Nullable
        public Object getInputContentInfo() {
            return null;
        }

        public void requestPermission() {
        }

        public void releasePermission() {
        }
    }

    @RequiresApi(25)
    private static final class InputContentInfoCompatApi25Impl implements InputContentInfoCompatImpl {
        @NonNull
        final InputContentInfo mObject;

        InputContentInfoCompatApi25Impl(@NonNull Object inputContentInfo) {
            this.mObject = (InputContentInfo) inputContentInfo;
        }

        InputContentInfoCompatApi25Impl(@NonNull Uri contentUri, @NonNull ClipDescription description, @Nullable Uri linkUri) {
            InputContentInfo inputContentInfo;
            new InputContentInfo(contentUri, description, linkUri);
            this.mObject = inputContentInfo;
        }

        @NonNull
        public Uri getContentUri() {
            return this.mObject.getContentUri();
        }

        @NonNull
        public ClipDescription getDescription() {
            return this.mObject.getDescription();
        }

        @Nullable
        public Uri getLinkUri() {
            return this.mObject.getLinkUri();
        }

        @Nullable
        public Object getInputContentInfo() {
            return this.mObject;
        }

        public void requestPermission() {
            this.mObject.requestPermission();
        }

        public void releasePermission() {
            this.mObject.releasePermission();
        }
    }

    public InputContentInfoCompat(@NonNull Uri uri, @NonNull ClipDescription clipDescription, @Nullable Uri uri2) {
        InputContentInfoCompatImpl inputContentInfoCompatImpl;
        InputContentInfoCompatImpl inputContentInfoCompatImpl2;
        Uri contentUri = uri;
        ClipDescription description = clipDescription;
        Uri linkUri = uri2;
        if (Build.VERSION.SDK_INT >= 25) {
            new InputContentInfoCompatApi25Impl(contentUri, description, linkUri);
            this.mImpl = inputContentInfoCompatImpl2;
            return;
        }
        new InputContentInfoCompatBaseImpl(contentUri, description, linkUri);
        this.mImpl = inputContentInfoCompatImpl;
    }

    private InputContentInfoCompat(@NonNull InputContentInfoCompatImpl impl) {
        this.mImpl = impl;
    }

    @NonNull
    public Uri getContentUri() {
        return this.mImpl.getContentUri();
    }

    @NonNull
    public ClipDescription getDescription() {
        return this.mImpl.getDescription();
    }

    @Nullable
    public Uri getLinkUri() {
        return this.mImpl.getLinkUri();
    }

    @Nullable
    public static InputContentInfoCompat wrap(@Nullable Object obj) {
        InputContentInfoCompat inputContentInfoCompat;
        InputContentInfoCompatImpl inputContentInfoCompatImpl;
        Object inputContentInfo = obj;
        if (inputContentInfo == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 25) {
            return null;
        }
        new InputContentInfoCompatApi25Impl(inputContentInfo);
        new InputContentInfoCompat(inputContentInfoCompatImpl);
        return inputContentInfoCompat;
    }

    @Nullable
    public Object unwrap() {
        return this.mImpl.getInputContentInfo();
    }

    public void requestPermission() {
        this.mImpl.requestPermission();
    }

    public void releasePermission() {
        this.mImpl.releasePermission();
    }
}
