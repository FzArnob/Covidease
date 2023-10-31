package com.bumptech.glide.load.resource.gif;

import com.bumptech.glide.Priority;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.ModelLoader;

class GifFrameModelLoader implements ModelLoader<GifDecoder, GifDecoder> {
    GifFrameModelLoader() {
    }

    public DataFetcher<GifDecoder> getResourceFetcher(GifDecoder model, int i, int i2) {
        DataFetcher<GifDecoder> dataFetcher;
        int i3 = i;
        int i4 = i2;
        new GifFrameDataFetcher(model);
        return dataFetcher;
    }

    private static class GifFrameDataFetcher implements DataFetcher<GifDecoder> {
        private final GifDecoder decoder;

        public GifFrameDataFetcher(GifDecoder decoder2) {
            this.decoder = decoder2;
        }

        public GifDecoder loadData(Priority priority) {
            Priority priority2 = priority;
            return this.decoder;
        }

        public void cleanup() {
        }

        public String getId() {
            return String.valueOf(this.decoder.getCurrentFrameIndex());
        }

        public void cancel() {
        }
    }
}
