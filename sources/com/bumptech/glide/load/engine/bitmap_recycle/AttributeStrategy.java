package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import com.bumptech.glide.util.Util;

class AttributeStrategy implements LruPoolStrategy {
    private final GroupedLinkedMap<Key, Bitmap> groupedMap;
    private final KeyPool keyPool;

    AttributeStrategy() {
        KeyPool keyPool2;
        GroupedLinkedMap<Key, Bitmap> groupedLinkedMap;
        new KeyPool();
        this.keyPool = keyPool2;
        new GroupedLinkedMap<>();
        this.groupedMap = groupedLinkedMap;
    }

    public void put(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        this.groupedMap.put(this.keyPool.get(bitmap2.getWidth(), bitmap2.getHeight(), bitmap2.getConfig()), bitmap2);
    }

    public Bitmap get(int width, int height, Bitmap.Config config) {
        return this.groupedMap.get(this.keyPool.get(width, height, config));
    }

    public Bitmap removeLast() {
        return this.groupedMap.removeLast();
    }

    public String logBitmap(Bitmap bitmap) {
        return getBitmapString(bitmap);
    }

    public String logBitmap(int width, int height, Bitmap.Config config) {
        return getBitmapString(width, height, config);
    }

    public int getSize(Bitmap bitmap) {
        return Util.getBitmapByteSize(bitmap);
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("AttributeStrategy:\n  ").append(this.groupedMap).toString();
    }

    private static String getBitmapString(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        return getBitmapString(bitmap2.getWidth(), bitmap2.getHeight(), bitmap2.getConfig());
    }

    /* access modifiers changed from: private */
    public static String getBitmapString(int width, int height, Bitmap.Config config) {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("[").append(width).append("x").append(height).append("], ").append(config).toString();
    }

    static class KeyPool extends BaseKeyPool<Key> {
        KeyPool() {
        }

        public Key get(int width, int height, Bitmap.Config config) {
            Key result = (Key) get();
            result.init(width, height, config);
            return result;
        }

        /* access modifiers changed from: protected */
        public Key create() {
            Key key;
            new Key(this);
            return key;
        }
    }

    static class Key implements Poolable {
        private Bitmap.Config config;
        private int height;
        private final KeyPool pool;
        private int width;

        public Key(KeyPool pool2) {
            this.pool = pool2;
        }

        public void init(int width2, int height2, Bitmap.Config config2) {
            this.width = width2;
            this.height = height2;
            this.config = config2;
        }

        public boolean equals(Object obj) {
            Object o = obj;
            if (!(o instanceof Key)) {
                return false;
            }
            Key other = (Key) o;
            return this.width == other.width && this.height == other.height && this.config == other.config;
        }

        public int hashCode() {
            return (31 * ((31 * this.width) + this.height)) + (this.config != null ? this.config.hashCode() : 0);
        }

        public String toString() {
            return AttributeStrategy.getBitmapString(this.width, this.height, this.config);
        }

        public void offer() {
            this.pool.offer(this);
        }
    }
}
