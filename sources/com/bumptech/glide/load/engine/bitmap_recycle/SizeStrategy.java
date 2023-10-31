package com.bumptech.glide.load.engine.bitmap_recycle;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import com.bumptech.glide.util.Util;
import java.util.TreeMap;

@TargetApi(19)
class SizeStrategy implements LruPoolStrategy {
    private static final int MAX_SIZE_MULTIPLE = 8;
    private final GroupedLinkedMap<Key, Bitmap> groupedMap;
    private final KeyPool keyPool;
    private final TreeMap<Integer, Integer> sortedSizes;

    SizeStrategy() {
        KeyPool keyPool2;
        GroupedLinkedMap<Key, Bitmap> groupedLinkedMap;
        TreeMap<Integer, Integer> treeMap;
        new KeyPool();
        this.keyPool = keyPool2;
        new GroupedLinkedMap<>();
        this.groupedMap = groupedLinkedMap;
        new PrettyPrintTreeMap();
        this.sortedSizes = treeMap;
    }

    public void put(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        Key key = this.keyPool.get(Util.getBitmapByteSize(bitmap2));
        this.groupedMap.put(key, bitmap2);
        Integer current = this.sortedSizes.get(Integer.valueOf(key.size));
        Integer put = this.sortedSizes.put(Integer.valueOf(key.size), Integer.valueOf(current == null ? 1 : current.intValue() + 1));
    }

    public Bitmap get(int i, int i2, Bitmap.Config config) {
        int width = i;
        int height = i2;
        Bitmap.Config config2 = config;
        int size = Util.getBitmapByteSize(width, height, config2);
        Key key = this.keyPool.get(size);
        Integer possibleSize = this.sortedSizes.ceilingKey(Integer.valueOf(size));
        if (!(possibleSize == null || possibleSize.intValue() == size || possibleSize.intValue() > size * 8)) {
            this.keyPool.offer(key);
            key = this.keyPool.get(possibleSize.intValue());
        }
        Bitmap result = this.groupedMap.get(key);
        if (result != null) {
            result.reconfigure(width, height, config2);
            decrementBitmapOfSize(possibleSize);
        }
        return result;
    }

    public Bitmap removeLast() {
        Bitmap removed = this.groupedMap.removeLast();
        if (removed != null) {
            decrementBitmapOfSize(Integer.valueOf(Util.getBitmapByteSize(removed)));
        }
        return removed;
    }

    private void decrementBitmapOfSize(Integer num) {
        Integer size = num;
        Integer current = this.sortedSizes.get(size);
        if (current.intValue() == 1) {
            Integer remove = this.sortedSizes.remove(size);
        } else {
            Integer put = this.sortedSizes.put(size, Integer.valueOf(current.intValue() - 1));
        }
    }

    public String logBitmap(Bitmap bitmap) {
        return getBitmapString(bitmap);
    }

    public String logBitmap(int width, int height, Bitmap.Config config) {
        return getBitmapString(Util.getBitmapByteSize(width, height, config));
    }

    public int getSize(Bitmap bitmap) {
        return Util.getBitmapByteSize(bitmap);
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("SizeStrategy:\n  ").append(this.groupedMap).append("\n").append("  SortedSizes").append(this.sortedSizes).toString();
    }

    private static String getBitmapString(Bitmap bitmap) {
        return getBitmapString(Util.getBitmapByteSize(bitmap));
    }

    /* access modifiers changed from: private */
    public static String getBitmapString(int size) {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("[").append(size).append("]").toString();
    }

    static class KeyPool extends BaseKeyPool<Key> {
        KeyPool() {
        }

        public Key get(int size) {
            Key result = (Key) get();
            result.init(size);
            return result;
        }

        /* access modifiers changed from: protected */
        public Key create() {
            Key key;
            new Key(this);
            return key;
        }
    }

    static final class Key implements Poolable {
        private final KeyPool pool;
        /* access modifiers changed from: private */
        public int size;

        Key(KeyPool pool2) {
            this.pool = pool2;
        }

        public void init(int size2) {
            int i = size2;
            this.size = i;
        }

        public boolean equals(Object obj) {
            Object o = obj;
            if (!(o instanceof Key)) {
                return false;
            }
            return this.size == ((Key) o).size;
        }

        public int hashCode() {
            return this.size;
        }

        public String toString() {
            return SizeStrategy.getBitmapString(this.size);
        }

        public void offer() {
            this.pool.offer(this);
        }
    }
}
