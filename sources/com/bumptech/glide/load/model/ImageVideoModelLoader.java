package com.bumptech.glide.load.model;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import java.io.InputStream;

public class ImageVideoModelLoader<A> implements ModelLoader<A, ImageVideoWrapper> {
    private static final String TAG = "IVML";
    private final ModelLoader<A, ParcelFileDescriptor> fileDescriptorLoader;
    private final ModelLoader<A, InputStream> streamLoader;

    public ImageVideoModelLoader(ModelLoader<A, InputStream> modelLoader, ModelLoader<A, ParcelFileDescriptor> modelLoader2) {
        Throwable th;
        ModelLoader<A, InputStream> streamLoader2 = modelLoader;
        ModelLoader<A, ParcelFileDescriptor> fileDescriptorLoader2 = modelLoader2;
        if (streamLoader2 == null && fileDescriptorLoader2 == null) {
            Throwable th2 = th;
            new NullPointerException("At least one of streamLoader and fileDescriptorLoader must be non null");
            throw th2;
        }
        this.streamLoader = streamLoader2;
        this.fileDescriptorLoader = fileDescriptorLoader2;
    }

    public DataFetcher<ImageVideoWrapper> getResourceFetcher(A a, int i, int i2) {
        DataFetcher<ImageVideoWrapper> dataFetcher;
        A model = a;
        int width = i;
        int height = i2;
        DataFetcher<InputStream> streamFetcher = null;
        if (this.streamLoader != null) {
            streamFetcher = this.streamLoader.getResourceFetcher(model, width, height);
        }
        DataFetcher<ParcelFileDescriptor> fileDescriptorFetcher = null;
        if (this.fileDescriptorLoader != null) {
            fileDescriptorFetcher = this.fileDescriptorLoader.getResourceFetcher(model, width, height);
        }
        if (streamFetcher == null && fileDescriptorFetcher == null) {
            return null;
        }
        new ImageVideoFetcher(streamFetcher, fileDescriptorFetcher);
        return dataFetcher;
    }

    static class ImageVideoFetcher implements DataFetcher<ImageVideoWrapper> {
        private final DataFetcher<ParcelFileDescriptor> fileDescriptorFetcher;
        private final DataFetcher<InputStream> streamFetcher;

        public ImageVideoFetcher(DataFetcher<InputStream> streamFetcher2, DataFetcher<ParcelFileDescriptor> fileDescriptorFetcher2) {
            this.streamFetcher = streamFetcher2;
            this.fileDescriptorFetcher = fileDescriptorFetcher2;
        }

        public ImageVideoWrapper loadData(Priority priority) throws Exception {
            ImageVideoWrapper imageVideoWrapper;
            Priority priority2 = priority;
            InputStream is = null;
            if (this.streamFetcher != null) {
                try {
                    is = this.streamFetcher.loadData(priority2);
                } catch (Exception e) {
                    Exception e2 = e;
                    if (Log.isLoggable(ImageVideoModelLoader.TAG, 2)) {
                        int v = Log.v(ImageVideoModelLoader.TAG, "Exception fetching input stream, trying ParcelFileDescriptor", e2);
                    }
                    if (this.fileDescriptorFetcher == null) {
                        throw e2;
                    }
                }
            }
            ParcelFileDescriptor fileDescriptor = null;
            if (this.fileDescriptorFetcher != null) {
                try {
                    fileDescriptor = this.fileDescriptorFetcher.loadData(priority2);
                } catch (Exception e3) {
                    Exception e4 = e3;
                    if (Log.isLoggable(ImageVideoModelLoader.TAG, 2)) {
                        int v2 = Log.v(ImageVideoModelLoader.TAG, "Exception fetching ParcelFileDescriptor", e4);
                    }
                    if (is == null) {
                        throw e4;
                    }
                }
            }
            new ImageVideoWrapper(is, fileDescriptor);
            return imageVideoWrapper;
        }

        public void cleanup() {
            if (this.streamFetcher != null) {
                this.streamFetcher.cleanup();
            }
            if (this.fileDescriptorFetcher != null) {
                this.fileDescriptorFetcher.cleanup();
            }
        }

        public String getId() {
            if (this.streamFetcher != null) {
                return this.streamFetcher.getId();
            }
            return this.fileDescriptorFetcher.getId();
        }

        public void cancel() {
            if (this.streamFetcher != null) {
                this.streamFetcher.cancel();
            }
            if (this.fileDescriptorFetcher != null) {
                this.fileDescriptorFetcher.cancel();
            }
        }
    }
}
