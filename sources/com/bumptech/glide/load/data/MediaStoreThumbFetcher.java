package com.bumptech.glide.load.data;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.resource.bitmap.ImageHeaderParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MediaStoreThumbFetcher implements DataFetcher<InputStream> {
    private static final ThumbnailStreamOpenerFactory DEFAULT_FACTORY;
    private static final int MINI_HEIGHT = 384;
    private static final int MINI_WIDTH = 512;
    private static final String TAG = "MediaStoreThumbFetcher";
    private final Context context;
    private final DataFetcher<InputStream> defaultFetcher;
    private final ThumbnailStreamOpenerFactory factory;
    private final int height;
    private InputStream inputStream;
    private final Uri mediaStoreUri;
    private final int width;

    interface ThumbnailQuery {
        Cursor queryPath(Context context, Uri uri);
    }

    static {
        ThumbnailStreamOpenerFactory thumbnailStreamOpenerFactory;
        new ThumbnailStreamOpenerFactory();
        DEFAULT_FACTORY = thumbnailStreamOpenerFactory;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MediaStoreThumbFetcher(Context context2, Uri mediaStoreUri2, DataFetcher<InputStream> defaultFetcher2, int width2, int height2) {
        this(context2, mediaStoreUri2, defaultFetcher2, width2, height2, DEFAULT_FACTORY);
    }

    MediaStoreThumbFetcher(Context context2, Uri mediaStoreUri2, DataFetcher<InputStream> defaultFetcher2, int width2, int height2, ThumbnailStreamOpenerFactory factory2) {
        this.context = context2;
        this.mediaStoreUri = mediaStoreUri2;
        this.defaultFetcher = defaultFetcher2;
        this.width = width2;
        this.height = height2;
        this.factory = factory2;
    }

    public InputStream loadData(Priority priority) throws Exception {
        Priority priority2 = priority;
        ThumbnailStreamOpener fetcher = this.factory.build(this.mediaStoreUri, this.width, this.height);
        if (fetcher != null) {
            this.inputStream = openThumbInputStream(fetcher);
        }
        if (this.inputStream == null) {
            this.inputStream = this.defaultFetcher.loadData(priority2);
        }
        return this.inputStream;
    }

    private InputStream openThumbInputStream(ThumbnailStreamOpener thumbnailStreamOpener) {
        InputStream inputStream2;
        ThumbnailStreamOpener fetcher = thumbnailStreamOpener;
        InputStream result = null;
        try {
            result = fetcher.open(this.context, this.mediaStoreUri);
        } catch (FileNotFoundException e) {
            FileNotFoundException e2 = e;
            if (Log.isLoggable(TAG, 3)) {
                int d = Log.d(TAG, "Failed to find thumbnail file", e2);
            }
        }
        int orientation = -1;
        if (result != null) {
            orientation = fetcher.getOrientation(this.context, this.mediaStoreUri);
        }
        if (orientation != -1) {
            new ExifOrientationStream(result, orientation);
            result = inputStream2;
        }
        return result;
    }

    public void cleanup() {
        if (this.inputStream != null) {
            try {
                this.inputStream.close();
            } catch (IOException e) {
                IOException iOException = e;
            }
        }
        this.defaultFetcher.cleanup();
    }

    public String getId() {
        return this.mediaStoreUri.toString();
    }

    public void cancel() {
    }

    /* access modifiers changed from: private */
    public static boolean isMediaStoreUri(Uri uri) {
        Uri uri2 = uri;
        return uri2 != null && "content".equals(uri2.getScheme()) && "media".equals(uri2.getAuthority());
    }

    /* access modifiers changed from: private */
    public static boolean isMediaStoreVideo(Uri uri) {
        Uri uri2 = uri;
        return isMediaStoreUri(uri2) && uri2.getPathSegments().contains("video");
    }

    static class FileService {
        FileService() {
        }

        public boolean exists(File file) {
            return file.exists();
        }

        public long length(File file) {
            return file.length();
        }

        public File get(String path) {
            File file;
            new File(path);
            return file;
        }
    }

    static class ThumbnailStreamOpener {
        private static final FileService DEFAULT_SERVICE;
        private ThumbnailQuery query;
        private final FileService service;

        static {
            FileService fileService;
            new FileService();
            DEFAULT_SERVICE = fileService;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public ThumbnailStreamOpener(ThumbnailQuery query2) {
            this(DEFAULT_SERVICE, query2);
        }

        public ThumbnailStreamOpener(FileService service2, ThumbnailQuery query2) {
            this.service = service2;
            this.query = query2;
        }

        public int getOrientation(Context context, Uri uri) {
            StringBuilder sb;
            ImageHeaderParser imageHeaderParser;
            Uri uri2 = uri;
            int orientation = -1;
            InputStream is = null;
            try {
                is = context.getContentResolver().openInputStream(uri2);
                new ImageHeaderParser(is);
                orientation = imageHeaderParser.getOrientation();
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        IOException iOException = e;
                    }
                }
            } catch (IOException e2) {
                IOException e3 = e2;
                if (Log.isLoggable(MediaStoreThumbFetcher.TAG, 3)) {
                    new StringBuilder();
                    int d = Log.d(MediaStoreThumbFetcher.TAG, sb.append("Failed to open uri: ").append(uri2).toString(), e3);
                }
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e4) {
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e5) {
                        IOException iOException2 = e5;
                    }
                }
                throw th2;
            }
            return orientation;
        }

        /* JADX INFO: finally extract failed */
        public InputStream open(Context context, Uri uri) throws FileNotFoundException {
            Context context2 = context;
            Uri thumbnailUri = null;
            InputStream inputStream = null;
            Cursor cursor = this.query.queryPath(context2, uri);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        thumbnailUri = parseThumbUri(cursor);
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th2;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            if (thumbnailUri != null) {
                inputStream = context2.getContentResolver().openInputStream(thumbnailUri);
            }
            return inputStream;
        }

        private Uri parseThumbUri(Cursor cursor) {
            Uri result = null;
            String path = cursor.getString(0);
            if (!TextUtils.isEmpty(path)) {
                File file = this.service.get(path);
                if (this.service.exists(file) && this.service.length(file) > 0) {
                    result = Uri.fromFile(file);
                }
            }
            return result;
        }
    }

    static class ImageThumbnailQuery implements ThumbnailQuery {
        private static final String[] PATH_PROJECTION = {"_data"};
        private static final String PATH_SELECTION = "kind = 1 AND image_id = ?";

        ImageThumbnailQuery() {
        }

        public Cursor queryPath(Context context, Uri uri) {
            return context.getContentResolver().query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, PATH_PROJECTION, PATH_SELECTION, new String[]{uri.getLastPathSegment()}, (String) null);
        }
    }

    static class VideoThumbnailQuery implements ThumbnailQuery {
        private static final String[] PATH_PROJECTION = {"_data"};
        private static final String PATH_SELECTION = "kind = 1 AND video_id = ?";

        VideoThumbnailQuery() {
        }

        public Cursor queryPath(Context context, Uri uri) {
            return context.getContentResolver().query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, PATH_PROJECTION, PATH_SELECTION, new String[]{uri.getLastPathSegment()}, (String) null);
        }
    }

    static class ThumbnailStreamOpenerFactory {
        ThumbnailStreamOpenerFactory() {
        }

        public ThumbnailStreamOpener build(Uri uri, int i, int i2) {
            ThumbnailStreamOpener thumbnailStreamOpener;
            ThumbnailQuery thumbnailQuery;
            ThumbnailStreamOpener thumbnailStreamOpener2;
            ThumbnailQuery thumbnailQuery2;
            Uri uri2 = uri;
            int width = i;
            int height = i2;
            if (!MediaStoreThumbFetcher.isMediaStoreUri(uri2) || width > 512 || height > MediaStoreThumbFetcher.MINI_HEIGHT) {
                return null;
            }
            if (MediaStoreThumbFetcher.isMediaStoreVideo(uri2)) {
                new VideoThumbnailQuery();
                new ThumbnailStreamOpener(thumbnailQuery2);
                return thumbnailStreamOpener2;
            }
            new ImageThumbnailQuery();
            new ThumbnailStreamOpener(thumbnailQuery);
            return thumbnailStreamOpener;
        }
    }
}
