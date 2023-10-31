package com.bumptech.glide.load.engine.prefill;

import android.graphics.Bitmap;

public final class PreFillType {
    static final Bitmap.Config DEFAULT_CONFIG = Bitmap.Config.RGB_565;
    private final Bitmap.Config config;
    private final int height;
    private final int weight;
    private final int width;

    PreFillType(int i, int i2, Bitmap.Config config2, int i3) {
        Throwable th;
        int width2 = i;
        int height2 = i2;
        Bitmap.Config config3 = config2;
        int weight2 = i3;
        if (config3 == null) {
            Throwable th2 = th;
            new NullPointerException("Config must not be null");
            throw th2;
        }
        this.width = width2;
        this.height = height2;
        this.config = config3;
        this.weight = weight2;
    }

    /* access modifiers changed from: package-private */
    public int getWidth() {
        return this.width;
    }

    /* access modifiers changed from: package-private */
    public int getHeight() {
        return this.height;
    }

    /* access modifiers changed from: package-private */
    public Bitmap.Config getConfig() {
        return this.config;
    }

    /* access modifiers changed from: package-private */
    public int getWeight() {
        return this.weight;
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (!(o instanceof PreFillType)) {
            return false;
        }
        PreFillType other = (PreFillType) o;
        return this.height == other.height && this.width == other.width && this.weight == other.weight && this.config == other.config;
    }

    public int hashCode() {
        return (31 * ((31 * ((31 * this.width) + this.height)) + this.config.hashCode())) + this.weight;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("PreFillSize{width=").append(this.width).append(", height=").append(this.height).append(", config=").append(this.config).append(", weight=").append(this.weight).append('}').toString();
    }

    public static class Builder {
        private Bitmap.Config config;
        private final int height;
        private int weight;
        private final int width;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public Builder(int r6) {
            /*
                r5 = this;
                r0 = r5
                r1 = r6
                r2 = r0
                r3 = r1
                r4 = r1
                r2.<init>(r3, r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.prefill.PreFillType.Builder.<init>(int):void");
        }

        public Builder(int i, int i2) {
            Throwable th;
            Throwable th2;
            int width2 = i;
            int height2 = i2;
            this.weight = 1;
            if (width2 <= 0) {
                Throwable th3 = th2;
                new IllegalArgumentException("Width must be > 0");
                throw th3;
            } else if (height2 <= 0) {
                Throwable th4 = th;
                new IllegalArgumentException("Height must be > 0");
                throw th4;
            } else {
                this.width = width2;
                this.height = height2;
            }
        }

        public Builder setConfig(Bitmap.Config config2) {
            this.config = config2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Bitmap.Config getConfig() {
            return this.config;
        }

        public Builder setWeight(int i) {
            Throwable th;
            int weight2 = i;
            if (weight2 <= 0) {
                Throwable th2 = th;
                new IllegalArgumentException("Weight must be > 0");
                throw th2;
            }
            this.weight = weight2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public PreFillType build() {
            PreFillType preFillType;
            new PreFillType(this.width, this.height, this.config, this.weight);
            return preFillType;
        }
    }
}
