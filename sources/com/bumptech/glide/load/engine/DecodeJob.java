package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.provider.DataLoadProvider;
import com.bumptech.glide.util.LogTime;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class DecodeJob<A, T, Z> {
    private static final FileOpener DEFAULT_FILE_OPENER;
    private static final String TAG = "DecodeJob";
    private final DiskCacheProvider diskCacheProvider;
    private final DiskCacheStrategy diskCacheStrategy;
    private final DataFetcher<A> fetcher;
    /* access modifiers changed from: private */
    public final FileOpener fileOpener;
    private final int height;
    private volatile boolean isCancelled;
    private final DataLoadProvider<A, T> loadProvider;
    private final Priority priority;
    private final EngineKey resultKey;
    private final ResourceTranscoder<T, Z> transcoder;
    private final Transformation<T> transformation;
    private final int width;

    interface DiskCacheProvider {
        DiskCache getDiskCache();
    }

    static {
        FileOpener fileOpener2;
        new FileOpener();
        DEFAULT_FILE_OPENER = fileOpener2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DecodeJob(EngineKey resultKey2, int width2, int height2, DataFetcher<A> fetcher2, DataLoadProvider<A, T> loadProvider2, Transformation<T> transformation2, ResourceTranscoder<T, Z> transcoder2, DiskCacheProvider diskCacheProvider2, DiskCacheStrategy diskCacheStrategy2, Priority priority2) {
        this(resultKey2, width2, height2, fetcher2, loadProvider2, transformation2, transcoder2, diskCacheProvider2, diskCacheStrategy2, priority2, DEFAULT_FILE_OPENER);
    }

    DecodeJob(EngineKey resultKey2, int width2, int height2, DataFetcher<A> fetcher2, DataLoadProvider<A, T> loadProvider2, Transformation<T> transformation2, ResourceTranscoder<T, Z> transcoder2, DiskCacheProvider diskCacheProvider2, DiskCacheStrategy diskCacheStrategy2, Priority priority2, FileOpener fileOpener2) {
        this.resultKey = resultKey2;
        this.width = width2;
        this.height = height2;
        this.fetcher = fetcher2;
        this.loadProvider = loadProvider2;
        this.transformation = transformation2;
        this.transcoder = transcoder2;
        this.diskCacheProvider = diskCacheProvider2;
        this.diskCacheStrategy = diskCacheStrategy2;
        this.priority = priority2;
        this.fileOpener = fileOpener2;
    }

    public Resource<Z> decodeResultFromCache() throws Exception {
        if (!this.diskCacheStrategy.cacheResult()) {
            return null;
        }
        long startTime = LogTime.getLogTime();
        Resource<T> transformed = loadFromCache(this.resultKey);
        if (Log.isLoggable(TAG, 2)) {
            logWithTimeAndKey("Decoded transformed from cache", startTime);
        }
        long startTime2 = LogTime.getLogTime();
        Resource<Z> result = transcode(transformed);
        if (Log.isLoggable(TAG, 2)) {
            logWithTimeAndKey("Transcoded transformed from cache", startTime2);
        }
        return result;
    }

    public Resource<Z> decodeSourceFromCache() throws Exception {
        if (!this.diskCacheStrategy.cacheSource()) {
            return null;
        }
        long startTime = LogTime.getLogTime();
        Resource<T> decoded = loadFromCache(this.resultKey.getOriginalKey());
        if (Log.isLoggable(TAG, 2)) {
            logWithTimeAndKey("Decoded source from cache", startTime);
        }
        return transformEncodeAndTranscode(decoded);
    }

    public Resource<Z> decodeFromSource() throws Exception {
        return transformEncodeAndTranscode(decodeSource());
    }

    public void cancel() {
        this.isCancelled = true;
        this.fetcher.cancel();
    }

    private Resource<Z> transformEncodeAndTranscode(Resource<T> decoded) {
        long startTime = LogTime.getLogTime();
        Resource<T> transformed = transform(decoded);
        if (Log.isLoggable(TAG, 2)) {
            logWithTimeAndKey("Transformed resource from source", startTime);
        }
        writeTransformedToCache(transformed);
        long startTime2 = LogTime.getLogTime();
        Resource<Z> result = transcode(transformed);
        if (Log.isLoggable(TAG, 2)) {
            logWithTimeAndKey("Transcoded transformed from source", startTime2);
        }
        return result;
    }

    private void writeTransformedToCache(Resource<T> resource) {
        DiskCache.Writer writer;
        Resource<T> transformed = resource;
        if (transformed != null && this.diskCacheStrategy.cacheResult()) {
            long startTime = LogTime.getLogTime();
            new SourceWriter(this, this.loadProvider.getEncoder(), transformed);
            this.diskCacheProvider.getDiskCache().put(this.resultKey, writer);
            if (Log.isLoggable(TAG, 2)) {
                logWithTimeAndKey("Wrote transformed from source to cache", startTime);
            }
        }
    }

    /* JADX INFO: finally extract failed */
    private Resource<T> decodeSource() throws Exception {
        try {
            long startTime = LogTime.getLogTime();
            A data = this.fetcher.loadData(this.priority);
            if (Log.isLoggable(TAG, 2)) {
                logWithTimeAndKey("Fetched data", startTime);
            }
            if (this.isCancelled) {
                this.fetcher.cleanup();
                return null;
            }
            Resource<T> decoded = decodeFromSourceData(data);
            this.fetcher.cleanup();
            return decoded;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.fetcher.cleanup();
            throw th2;
        }
    }

    private Resource<T> decodeFromSourceData(A a) throws IOException {
        Resource<T> decoded;
        A data = a;
        if (this.diskCacheStrategy.cacheSource()) {
            decoded = cacheAndDecodeSourceData(data);
        } else {
            long startTime = LogTime.getLogTime();
            decoded = this.loadProvider.getSourceDecoder().decode(data, this.width, this.height);
            if (Log.isLoggable(TAG, 2)) {
                logWithTimeAndKey("Decoded from source", startTime);
            }
        }
        return decoded;
    }

    private Resource<T> cacheAndDecodeSourceData(A data) throws IOException {
        DiskCache.Writer writer;
        long startTime = LogTime.getLogTime();
        new SourceWriter(this, this.loadProvider.getSourceEncoder(), data);
        this.diskCacheProvider.getDiskCache().put(this.resultKey.getOriginalKey(), writer);
        if (Log.isLoggable(TAG, 2)) {
            logWithTimeAndKey("Wrote source to cache", startTime);
        }
        long startTime2 = LogTime.getLogTime();
        Resource<T> result = loadFromCache(this.resultKey.getOriginalKey());
        if (Log.isLoggable(TAG, 2) && result != null) {
            logWithTimeAndKey("Decoded source from cache", startTime2);
        }
        return result;
    }

    /* JADX INFO: finally extract failed */
    private Resource<T> loadFromCache(Key key) throws IOException {
        Key key2 = key;
        File cacheFile = this.diskCacheProvider.getDiskCache().get(key2);
        if (cacheFile == null) {
            return null;
        }
        try {
            Resource<T> result = this.loadProvider.getCacheDecoder().decode(cacheFile, this.width, this.height);
            if (result == null) {
                this.diskCacheProvider.getDiskCache().delete(key2);
            }
            return result;
        } catch (Throwable th) {
            Throwable th2 = th;
            if (0 == 0) {
                this.diskCacheProvider.getDiskCache().delete(key2);
            }
            throw th2;
        }
    }

    private Resource<T> transform(Resource<T> resource) {
        Resource<T> decoded = resource;
        if (decoded == null) {
            return null;
        }
        Resource<T> transformed = this.transformation.transform(decoded, this.width, this.height);
        if (!decoded.equals(transformed)) {
            decoded.recycle();
        }
        return transformed;
    }

    private Resource<Z> transcode(Resource<T> resource) {
        Resource<T> transformed = resource;
        if (transformed == null) {
            return null;
        }
        return this.transcoder.transcode(transformed);
    }

    private void logWithTimeAndKey(String message, long startTime) {
        StringBuilder sb;
        new StringBuilder();
        int v = Log.v(TAG, sb.append(message).append(" in ").append(LogTime.getElapsedMillis(startTime)).append(", key: ").append(this.resultKey).toString());
    }

    class SourceWriter<DataType> implements DiskCache.Writer {
        private final DataType data;
        private final Encoder<DataType> encoder;
        final /* synthetic */ DecodeJob this$0;

        public SourceWriter(DecodeJob decodeJob, Encoder<DataType> encoder2, DataType data2) {
            this.this$0 = decodeJob;
            this.encoder = encoder2;
            this.data = data2;
        }

        public boolean write(File file) {
            boolean success = false;
            OutputStream os = null;
            try {
                os = this.this$0.fileOpener.open(file);
                success = this.encoder.encode(this.data, os);
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        IOException iOException = e;
                    }
                }
            } catch (FileNotFoundException e2) {
                FileNotFoundException e3 = e2;
                if (Log.isLoggable(DecodeJob.TAG, 3)) {
                    int d = Log.d(DecodeJob.TAG, "Failed to find file to write to disk cache", e3);
                }
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e4) {
                        IOException iOException2 = e4;
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e5) {
                        IOException iOException3 = e5;
                    }
                }
                throw th2;
            }
            return success;
        }
    }

    static class FileOpener {
        FileOpener() {
        }

        public OutputStream open(File file) throws FileNotFoundException {
            OutputStream outputStream;
            OutputStream outputStream2;
            new FileOutputStream(file);
            new BufferedOutputStream(outputStream2);
            return outputStream;
        }
    }
}
