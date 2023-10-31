package com.bumptech.glide.load.model;

import com.bumptech.glide.util.LruCache;
import com.bumptech.glide.util.Util;
import java.util.Queue;

public class ModelCache<A, B> {
    private static final int DEFAULT_SIZE = 250;
    private final LruCache<ModelKey<A>, B> cache;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ModelCache() {
        this(250);
    }

    public ModelCache(int size) {
        LruCache<ModelKey<A>, B> lruCache;
        new LruCache<ModelKey<A>, B>(this, size) {
            final /* synthetic */ ModelCache this$0;

            {
                this.this$0 = r6;
            }

            /* access modifiers changed from: protected */
            public void onItemEvicted(ModelKey<A> key, B b) {
                B b2 = b;
                key.release();
            }
        };
        this.cache = lruCache;
    }

    public B get(A model, int width, int height) {
        ModelKey<A> key = ModelKey.get(model, width, height);
        ModelCache<A, B> result = this.cache.get(key);
        key.release();
        return result;
    }

    public void put(A model, int width, int height, B value) {
        ModelKey modelKey = ModelKey.get(model, width, height);
        B put = this.cache.put(modelKey, value);
    }

    static final class ModelKey<A> {
        private static final Queue<ModelKey<?>> KEY_QUEUE = Util.createQueue(0);
        private int height;
        private A model;
        private int width;

        static <A> ModelKey<A> get(A a, int i, int i2) {
            ModelKey modelKey;
            A model2 = a;
            int width2 = i;
            int height2 = i2;
            ModelKey poll = KEY_QUEUE.poll();
            if (poll == null) {
                new ModelKey();
                poll = modelKey;
            }
            poll.init(model2, width2, height2);
            return poll;
        }

        private ModelKey() {
        }

        private void init(A model2, int width2, int height2) {
            this.model = model2;
            this.width = width2;
            this.height = height2;
        }

        public void release() {
            boolean offer = KEY_QUEUE.offer(this);
        }

        public boolean equals(Object obj) {
            Object o = obj;
            if (!(o instanceof ModelKey)) {
                return false;
            }
            ModelKey other = (ModelKey) o;
            return this.width == other.width && this.height == other.height && this.model.equals(other.model);
        }

        public int hashCode() {
            return (31 * ((31 * this.height) + this.width)) + this.model.hashCode();
        }
    }
}
