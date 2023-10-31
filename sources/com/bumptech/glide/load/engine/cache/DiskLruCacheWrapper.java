package com.bumptech.glide.load.engine.cache;

import android.util.Log;
import com.bumptech.glide.disklrucache.DiskLruCache;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.cache.DiskCache;
import java.io.File;
import java.io.IOException;

public class DiskLruCacheWrapper implements DiskCache {
    private static final int APP_VERSION = 1;
    private static final String TAG = "DiskLruCacheWrapper";
    private static final int VALUE_COUNT = 1;
    private static DiskLruCacheWrapper wrapper = null;
    private final File directory;
    private DiskLruCache diskLruCache;
    private final int maxSize;
    private final SafeKeyGenerator safeKeyGenerator;
    private final DiskCacheWriteLocker writeLocker;

    public static synchronized DiskCache get(File file, int i) {
        DiskLruCacheWrapper diskLruCacheWrapper;
        DiskLruCacheWrapper diskLruCacheWrapper2;
        File directory2 = file;
        int maxSize2 = i;
        synchronized (DiskLruCacheWrapper.class) {
            if (wrapper == null) {
                new DiskLruCacheWrapper(directory2, maxSize2);
                wrapper = diskLruCacheWrapper2;
            }
            diskLruCacheWrapper = wrapper;
        }
        return diskLruCacheWrapper;
    }

    protected DiskLruCacheWrapper(File directory2, int maxSize2) {
        DiskCacheWriteLocker diskCacheWriteLocker;
        SafeKeyGenerator safeKeyGenerator2;
        new DiskCacheWriteLocker();
        this.writeLocker = diskCacheWriteLocker;
        this.directory = directory2;
        this.maxSize = maxSize2;
        new SafeKeyGenerator();
        this.safeKeyGenerator = safeKeyGenerator2;
    }

    private synchronized DiskLruCache getDiskCache() throws IOException {
        DiskLruCache diskLruCache2;
        synchronized (this) {
            if (this.diskLruCache == null) {
                this.diskLruCache = DiskLruCache.open(this.directory, 1, 1, (long) this.maxSize);
            }
            diskLruCache2 = this.diskLruCache;
        }
        return diskLruCache2;
    }

    private synchronized void resetDiskCache() {
        synchronized (this) {
            this.diskLruCache = null;
        }
    }

    public File get(Key key) {
        File result = null;
        try {
            DiskLruCache.Value value = getDiskCache().get(this.safeKeyGenerator.getSafeKey(key));
            if (value != null) {
                result = value.getFile(0);
            }
        } catch (IOException e) {
            IOException e2 = e;
            if (Log.isLoggable(TAG, 5)) {
                int w = Log.w(TAG, "Unable to get from disk cache", e2);
            }
        }
        return result;
    }

    public void put(Key key, DiskCache.Writer writer) {
        DiskLruCache.Editor editor;
        Key key2 = key;
        DiskCache.Writer writer2 = writer;
        String safeKey = this.safeKeyGenerator.getSafeKey(key2);
        this.writeLocker.acquire(key2);
        try {
            editor = getDiskCache().edit(safeKey);
            if (editor != null) {
                if (writer2.write(editor.getFile(0))) {
                    editor.commit();
                }
                editor.abortUnlessCommitted();
            }
            this.writeLocker.release(key2);
        } catch (IOException e) {
            IOException e2 = e;
            try {
                if (Log.isLoggable(TAG, 5)) {
                    int w = Log.w(TAG, "Unable to put to disk cache", e2);
                }
                this.writeLocker.release(key2);
            } catch (Throwable th) {
                Throwable th2 = th;
                this.writeLocker.release(key2);
                throw th2;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            editor.abortUnlessCommitted();
            throw th4;
        }
    }

    public void delete(Key key) {
        try {
            boolean remove = getDiskCache().remove(this.safeKeyGenerator.getSafeKey(key));
        } catch (IOException e) {
            IOException e2 = e;
            if (Log.isLoggable(TAG, 5)) {
                int w = Log.w(TAG, "Unable to delete from disk cache", e2);
            }
        }
    }

    public synchronized void clear() {
        synchronized (this) {
            try {
                getDiskCache().delete();
                resetDiskCache();
            } catch (IOException e) {
                IOException e2 = e;
                if (Log.isLoggable(TAG, 5)) {
                    int w = Log.w(TAG, "Unable to clear disk cache", e2);
                }
            }
        }
    }
}
