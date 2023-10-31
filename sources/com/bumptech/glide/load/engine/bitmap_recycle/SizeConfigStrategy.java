package com.bumptech.glide.load.engine.bitmap_recycle;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import com.bumptech.glide.util.Util;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

@TargetApi(19)
public class SizeConfigStrategy implements LruPoolStrategy {
    private static final Bitmap.Config[] ALPHA_8_IN_CONFIGS = {Bitmap.Config.ALPHA_8};
    private static final Bitmap.Config[] ARGB_4444_IN_CONFIGS = {Bitmap.Config.ARGB_4444};
    private static final Bitmap.Config[] ARGB_8888_IN_CONFIGS;
    private static final int MAX_SIZE_MULTIPLE = 8;
    private static final Bitmap.Config[] RGB_565_IN_CONFIGS = {Bitmap.Config.RGB_565};
    private final GroupedLinkedMap<Key, Bitmap> groupedMap;
    private final KeyPool keyPool;
    private final Map<Bitmap.Config, NavigableMap<Integer, Integer>> sortedSizes;

    public SizeConfigStrategy() {
        KeyPool keyPool2;
        GroupedLinkedMap<Key, Bitmap> groupedLinkedMap;
        Map<Bitmap.Config, NavigableMap<Integer, Integer>> map;
        new KeyPool();
        this.keyPool = keyPool2;
        new GroupedLinkedMap<>();
        this.groupedMap = groupedLinkedMap;
        new HashMap();
        this.sortedSizes = map;
    }

    static {
        Bitmap.Config[] configArr = new Bitmap.Config[2];
        configArr[0] = Bitmap.Config.ARGB_8888;
        Bitmap.Config[] configArr2 = configArr;
        configArr2[1] = null;
        ARGB_8888_IN_CONFIGS = configArr2;
    }

    public void put(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        Key key = this.keyPool.get(Util.getBitmapByteSize(bitmap2), bitmap2.getConfig());
        this.groupedMap.put(key, bitmap2);
        NavigableMap<Integer, Integer> sizes = getSizesForConfig(bitmap2.getConfig());
        Integer current = (Integer) sizes.get(Integer.valueOf(key.size));
        Object put = sizes.put(Integer.valueOf(key.size), Integer.valueOf(current == null ? 1 : current.intValue() + 1));
    }

    public Bitmap get(int i, int i2, Bitmap.Config config) {
        int width = i;
        int height = i2;
        Bitmap.Config config2 = config;
        int size = Util.getBitmapByteSize(width, height, config2);
        Bitmap result = this.groupedMap.get(findBestKey(this.keyPool.get(size, config2), size, config2));
        if (result != null) {
            decrementBitmapOfSize(Integer.valueOf(Util.getBitmapByteSize(result)), result.getConfig());
            result.reconfigure(width, height, result.getConfig() != null ? result.getConfig() : Bitmap.Config.ARGB_8888);
        }
        return result;
    }

    private Key findBestKey(Key key, int i, Bitmap.Config config) {
        Key key2 = key;
        int size = i;
        Bitmap.Config config2 = config;
        Key result = key2;
        Bitmap.Config[] arr$ = getInConfigs(config2);
        int len$ = arr$.length;
        int i$ = 0;
        while (true) {
            if (i$ >= len$) {
                break;
            }
            Bitmap.Config possibleConfig = arr$[i$];
            Integer possibleSize = getSizesForConfig(possibleConfig).ceilingKey(Integer.valueOf(size));
            if (possibleSize == null || possibleSize.intValue() > size * 8) {
                i$++;
            } else if (possibleSize.intValue() != size || (possibleConfig != null ? !possibleConfig.equals(config2) : config2 != null)) {
                this.keyPool.offer(key2);
                result = this.keyPool.get(possibleSize.intValue(), possibleConfig);
            }
        }
        return result;
    }

    public Bitmap removeLast() {
        Bitmap removed = this.groupedMap.removeLast();
        if (removed != null) {
            decrementBitmapOfSize(Integer.valueOf(Util.getBitmapByteSize(removed)), removed.getConfig());
        }
        return removed;
    }

    private void decrementBitmapOfSize(Integer num, Bitmap.Config config) {
        Integer size = num;
        NavigableMap<Integer, Integer> sizes = getSizesForConfig(config);
        Integer current = (Integer) sizes.get(size);
        if (current.intValue() == 1) {
            Object remove = sizes.remove(size);
        } else {
            Object put = sizes.put(size, Integer.valueOf(current.intValue() - 1));
        }
    }

    private NavigableMap<Integer, Integer> getSizesForConfig(Bitmap.Config config) {
        NavigableMap<Integer, Integer> navigableMap;
        Bitmap.Config config2 = config;
        NavigableMap<Integer, Integer> sizes = this.sortedSizes.get(config2);
        if (sizes == null) {
            new TreeMap<>();
            sizes = navigableMap;
            NavigableMap<Integer, Integer> put = this.sortedSizes.put(config2, sizes);
        }
        return sizes;
    }

    public String logBitmap(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        return getBitmapString(Util.getBitmapByteSize(bitmap2), bitmap2.getConfig());
    }

    public String logBitmap(int width, int height, Bitmap.Config config) {
        Bitmap.Config config2 = config;
        return getBitmapString(Util.getBitmapByteSize(width, height, config2), config2);
    }

    public int getSize(Bitmap bitmap) {
        return Util.getBitmapByteSize(bitmap);
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder sb2 = sb.append("SizeConfigStrategy{groupedMap=").append(this.groupedMap).append(", sortedSizes=(");
        for (Map.Entry<Bitmap.Config, NavigableMap<Integer, Integer>> entry : this.sortedSizes.entrySet()) {
            StringBuilder append = sb2.append(entry.getKey()).append('[').append(entry.getValue()).append("], ");
        }
        if (!this.sortedSizes.isEmpty()) {
            StringBuilder replace = sb2.replace(sb2.length() - 2, sb2.length(), "");
        }
        return sb2.append(")}").toString();
    }

    static class KeyPool extends BaseKeyPool<Key> {
        KeyPool() {
        }

        public Key get(int size, Bitmap.Config config) {
            Key result = (Key) get();
            result.init(size, config);
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
        private Bitmap.Config config;
        private final KeyPool pool;
        /* access modifiers changed from: private */
        public int size;

        public Key(KeyPool pool2) {
            this.pool = pool2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        Key(KeyPool pool2, int size2, Bitmap.Config config2) {
            this(pool2);
            init(size2, config2);
        }

        public void init(int size2, Bitmap.Config config2) {
            this.size = size2;
            this.config = config2;
        }

        public void offer() {
            this.pool.offer(this);
        }

        public String toString() {
            return SizeConfigStrategy.getBitmapString(this.size, this.config);
        }

        public boolean equals(Object obj) {
            Object o = obj;
            if (!(o instanceof Key)) {
                return false;
            }
            Key other = (Key) o;
            return this.size == other.size && (this.config != null ? this.config.equals(other.config) : other.config == null);
        }

        public int hashCode() {
            return (31 * this.size) + (this.config != null ? this.config.hashCode() : 0);
        }
    }

    /* access modifiers changed from: private */
    public static String getBitmapString(int size, Bitmap.Config config) {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("[").append(size).append("](").append(config).append(")").toString();
    }

    /* renamed from: com.bumptech.glide.load.engine.bitmap_recycle.SizeConfigStrategy$1 */
    static /* synthetic */ class C15141 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$Config = new int[Bitmap.Config.values().length];

        static {
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ARGB_8888.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
                NoSuchFieldError noSuchFieldError = e;
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.RGB_565.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
                NoSuchFieldError noSuchFieldError2 = e2;
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ARGB_4444.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
                NoSuchFieldError noSuchFieldError3 = e3;
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ALPHA_8.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
                NoSuchFieldError noSuchFieldError4 = e4;
            }
        }
    }

    private static Bitmap.Config[] getInConfigs(Bitmap.Config config) {
        Bitmap.Config requested = config;
        switch (C15141.$SwitchMap$android$graphics$Bitmap$Config[requested.ordinal()]) {
            case 1:
                return ARGB_8888_IN_CONFIGS;
            case 2:
                return RGB_565_IN_CONFIGS;
            case 3:
                return ARGB_4444_IN_CONFIGS;
            case 4:
                return ALPHA_8_IN_CONFIGS;
            default:
                return new Bitmap.Config[]{requested};
        }
    }
}
