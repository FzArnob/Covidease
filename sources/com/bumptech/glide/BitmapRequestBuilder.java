package com.bumptech.glide;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.ParcelFileDescriptor;
import android.view.animation.Animation;
import android.widget.ImageView;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.model.ImageVideoWrapper;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.load.resource.bitmap.FileDescriptorBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.ImageVideoBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.StreamBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.VideoBitmapDecoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.BitmapCrossFadeFactory;
import com.bumptech.glide.request.animation.DrawableCrossFadeFactory;
import com.bumptech.glide.request.animation.GlideAnimationFactory;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.Target;
import java.io.File;
import java.io.InputStream;

public class BitmapRequestBuilder<ModelType, TranscodeType> extends GenericRequestBuilder<ModelType, ImageVideoWrapper, Bitmap, TranscodeType> implements BitmapOptions, DrawableOptions {
    private final BitmapPool bitmapPool;
    private DecodeFormat decodeFormat;
    private Downsampler downsampler = Downsampler.AT_LEAST;
    private ResourceDecoder<InputStream, Bitmap> imageDecoder;
    private ResourceDecoder<ParcelFileDescriptor, Bitmap> videoDecoder;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    BitmapRequestBuilder(com.bumptech.glide.provider.LoadProvider<ModelType, com.bumptech.glide.load.model.ImageVideoWrapper, android.graphics.Bitmap, TranscodeType> r11, java.lang.Class<TranscodeType> r12, com.bumptech.glide.GenericRequestBuilder<ModelType, ?, ?, ?> r13) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r0
            r5 = r1
            r6 = r2
            r7 = r3
            r4.<init>(r5, r6, r7)
            r4 = r0
            com.bumptech.glide.load.resource.bitmap.Downsampler r5 = com.bumptech.glide.load.resource.bitmap.Downsampler.AT_LEAST
            r4.downsampler = r5
            r4 = r0
            r5 = r3
            com.bumptech.glide.Glide r5 = r5.glide
            com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r5 = r5.getBitmapPool()
            r4.bitmapPool = r5
            r4 = r0
            r5 = r3
            com.bumptech.glide.Glide r5 = r5.glide
            com.bumptech.glide.load.DecodeFormat r5 = r5.getDecodeFormat()
            r4.decodeFormat = r5
            r4 = r0
            com.bumptech.glide.load.resource.bitmap.StreamBitmapDecoder r5 = new com.bumptech.glide.load.resource.bitmap.StreamBitmapDecoder
            r9 = r5
            r5 = r9
            r6 = r9
            r7 = r0
            com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r7 = r7.bitmapPool
            r8 = r0
            com.bumptech.glide.load.DecodeFormat r8 = r8.decodeFormat
            r6.<init>((com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool) r7, (com.bumptech.glide.load.DecodeFormat) r8)
            r4.imageDecoder = r5
            r4 = r0
            com.bumptech.glide.load.resource.bitmap.FileDescriptorBitmapDecoder r5 = new com.bumptech.glide.load.resource.bitmap.FileDescriptorBitmapDecoder
            r9 = r5
            r5 = r9
            r6 = r9
            r7 = r0
            com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r7 = r7.bitmapPool
            r8 = r0
            com.bumptech.glide.load.DecodeFormat r8 = r8.decodeFormat
            r6.<init>((com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool) r7, (com.bumptech.glide.load.DecodeFormat) r8)
            r4.videoDecoder = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.BitmapRequestBuilder.<init>(com.bumptech.glide.provider.LoadProvider, java.lang.Class, com.bumptech.glide.GenericRequestBuilder):void");
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> approximate() {
        return downsample(Downsampler.AT_LEAST);
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> asIs() {
        return downsample(Downsampler.NONE);
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> atMost() {
        return downsample(Downsampler.AT_MOST);
    }

    private BitmapRequestBuilder<ModelType, TranscodeType> downsample(Downsampler downsampler2) {
        ResourceDecoder<InputStream, Bitmap> resourceDecoder;
        ResourceDecoder resourceDecoder2;
        Downsampler downsampler3 = downsampler2;
        this.downsampler = downsampler3;
        new StreamBitmapDecoder(downsampler3, this.bitmapPool, this.decodeFormat);
        this.imageDecoder = resourceDecoder;
        new ImageVideoBitmapDecoder(this.imageDecoder, this.videoDecoder);
        GenericRequestBuilder decoder = super.decoder(resourceDecoder2);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> thumbnail(float sizeMultiplier) {
        GenericRequestBuilder thumbnail = super.thumbnail(sizeMultiplier);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> thumbnail(BitmapRequestBuilder<?, TranscodeType> thumbnailRequest) {
        GenericRequestBuilder<?, DataType, ResourceType, TranscodeType> thumbnail = super.thumbnail(thumbnailRequest);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> sizeMultiplier(float sizeMultiplier) {
        GenericRequestBuilder sizeMultiplier2 = super.sizeMultiplier(sizeMultiplier);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> decoder(ResourceDecoder<ImageVideoWrapper, Bitmap> decoder) {
        GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> decoder2 = super.decoder(decoder);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> cacheDecoder(ResourceDecoder<File, Bitmap> cacheDecoder) {
        GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> cacheDecoder2 = super.cacheDecoder(cacheDecoder);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> encoder(ResourceEncoder<Bitmap> encoder) {
        GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> encoder2 = super.encoder(encoder);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> imageDecoder(ResourceDecoder<InputStream, Bitmap> resourceDecoder) {
        ResourceDecoder resourceDecoder2;
        ResourceDecoder<InputStream, Bitmap> decoder = resourceDecoder;
        this.imageDecoder = decoder;
        new ImageVideoBitmapDecoder(decoder, this.videoDecoder);
        GenericRequestBuilder decoder2 = super.decoder(resourceDecoder2);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> videoDecoder(ResourceDecoder<ParcelFileDescriptor, Bitmap> resourceDecoder) {
        ResourceDecoder resourceDecoder2;
        ResourceDecoder<ParcelFileDescriptor, Bitmap> decoder = resourceDecoder;
        this.videoDecoder = decoder;
        new ImageVideoBitmapDecoder(this.imageDecoder, decoder);
        GenericRequestBuilder decoder2 = super.decoder(resourceDecoder2);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> format(DecodeFormat decodeFormat2) {
        ResourceDecoder<InputStream, Bitmap> resourceDecoder;
        ResourceDecoder<ParcelFileDescriptor, Bitmap> resourceDecoder2;
        VideoBitmapDecoder videoBitmapDecoder;
        ResourceDecoder resourceDecoder3;
        ResourceDecoder resourceDecoder4;
        ResourceDecoder resourceDecoder5;
        DecodeFormat format = decodeFormat2;
        this.decodeFormat = format;
        new StreamBitmapDecoder(this.downsampler, this.bitmapPool, format);
        this.imageDecoder = resourceDecoder;
        new VideoBitmapDecoder();
        new FileDescriptorBitmapDecoder(videoBitmapDecoder, this.bitmapPool, format);
        this.videoDecoder = resourceDecoder2;
        new StreamBitmapDecoder(this.downsampler, this.bitmapPool, format);
        new FileToStreamDecoder(resourceDecoder4);
        GenericRequestBuilder cacheDecoder = super.cacheDecoder(resourceDecoder3);
        new ImageVideoBitmapDecoder(this.imageDecoder, this.videoDecoder);
        GenericRequestBuilder decoder = super.decoder(resourceDecoder5);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> priority(Priority priority) {
        GenericRequestBuilder priority2 = super.priority(priority);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> transform(BitmapTransformation... transformations) {
        GenericRequestBuilder transform = super.transform(transformations);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> centerCrop() {
        return transform(this.glide.getBitmapCenterCrop());
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> fitCenter() {
        return transform(this.glide.getBitmapFitCenter());
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> transform(Transformation<Bitmap>... transformations) {
        GenericRequestBuilder transform = super.transform(transformations);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> transcoder(ResourceTranscoder<Bitmap, TranscodeType> transcoder) {
        GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> transcoder2 = super.transcoder(transcoder);
        return this;
    }

    private RuntimeException crossFadeNotSupported() {
        RuntimeException runtimeException;
        StringBuilder sb;
        String className = this.transcodeClass.getCanonicalName();
        if (className == null) {
            className = this.transcodeClass.toString();
        }
        new StringBuilder();
        new UnsupportedOperationException(sb.append(".crossFade() is not supported for ").append(className).append(", use .animate() to provide a compatible animation.").toString());
        return runtimeException;
    }

    public final BitmapRequestBuilder<ModelType, TranscodeType> crossFade() {
        GlideAnimationFactory glideAnimationFactory;
        GlideAnimationFactory glideAnimationFactory2;
        if (Bitmap.class.isAssignableFrom(this.transcodeClass)) {
            new BitmapCrossFadeFactory();
            return animate(glideAnimationFactory2);
        } else if (Drawable.class.isAssignableFrom(this.transcodeClass)) {
            new DrawableCrossFadeFactory();
            return animate(glideAnimationFactory);
        } else {
            throw crossFadeNotSupported();
        }
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> crossFade(int i) {
        GlideAnimationFactory glideAnimationFactory;
        GlideAnimationFactory glideAnimationFactory2;
        int duration = i;
        if (Bitmap.class.isAssignableFrom(this.transcodeClass)) {
            new BitmapCrossFadeFactory(duration);
            return animate(glideAnimationFactory2);
        } else if (Drawable.class.isAssignableFrom(this.transcodeClass)) {
            new DrawableCrossFadeFactory(duration);
            return animate(glideAnimationFactory);
        } else {
            throw crossFadeNotSupported();
        }
    }

    @Deprecated
    public BitmapRequestBuilder<ModelType, TranscodeType> crossFade(Animation animation, int i) {
        GlideAnimationFactory glideAnimationFactory;
        GlideAnimationFactory glideAnimationFactory2;
        Animation animation2 = animation;
        int duration = i;
        if (Bitmap.class.isAssignableFrom(this.transcodeClass)) {
            new BitmapCrossFadeFactory(animation2, duration);
            return animate(glideAnimationFactory2);
        } else if (Drawable.class.isAssignableFrom(this.transcodeClass)) {
            new DrawableCrossFadeFactory(animation2, duration);
            return animate(glideAnimationFactory);
        } else {
            throw crossFadeNotSupported();
        }
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> crossFade(int i, int i2) {
        GlideAnimationFactory glideAnimationFactory;
        GlideAnimationFactory glideAnimationFactory2;
        int animationId = i;
        int duration = i2;
        if (Bitmap.class.isAssignableFrom(this.transcodeClass)) {
            new BitmapCrossFadeFactory(this.context, animationId, duration);
            return animate(glideAnimationFactory2);
        } else if (Drawable.class.isAssignableFrom(this.transcodeClass)) {
            new DrawableCrossFadeFactory(this.context, animationId, duration);
            return animate(glideAnimationFactory);
        } else {
            throw crossFadeNotSupported();
        }
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> dontAnimate() {
        GenericRequestBuilder dontAnimate = super.dontAnimate();
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> animate(int animationId) {
        GenericRequestBuilder animate = super.animate(animationId);
        return this;
    }

    @Deprecated
    public BitmapRequestBuilder<ModelType, TranscodeType> animate(Animation animation) {
        GenericRequestBuilder animate = super.animate(animation);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> animate(ViewPropertyAnimation.Animator animator) {
        GenericRequestBuilder animate = super.animate(animator);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> animate(GlideAnimationFactory<TranscodeType> animationFactory) {
        GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> animate = super.animate(animationFactory);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> placeholder(int resourceId) {
        GenericRequestBuilder placeholder = super.placeholder(resourceId);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> placeholder(Drawable drawable) {
        GenericRequestBuilder placeholder = super.placeholder(drawable);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> fallback(Drawable drawable) {
        GenericRequestBuilder fallback = super.fallback(drawable);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> fallback(int resourceId) {
        GenericRequestBuilder fallback = super.fallback(resourceId);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> error(int resourceId) {
        GenericRequestBuilder error = super.error(resourceId);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> error(Drawable drawable) {
        GenericRequestBuilder error = super.error(drawable);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> listener(RequestListener<? super ModelType, TranscodeType> requestListener) {
        GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> listener = super.listener(requestListener);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> skipMemoryCache(boolean skip) {
        GenericRequestBuilder skipMemoryCache = super.skipMemoryCache(skip);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> diskCacheStrategy(DiskCacheStrategy strategy) {
        GenericRequestBuilder diskCacheStrategy = super.diskCacheStrategy(strategy);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> override(int width, int height) {
        GenericRequestBuilder override = super.override(width, height);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> thumbnail(GenericRequestBuilder<?, ?, ?, TranscodeType> thumbnailRequest) {
        GenericRequestBuilder<?, ?, ?, TranscodeType> thumbnail = super.thumbnail(thumbnailRequest);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> sourceEncoder(Encoder<ImageVideoWrapper> sourceEncoder) {
        GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> sourceEncoder2 = super.sourceEncoder(sourceEncoder);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> dontTransform() {
        GenericRequestBuilder dontTransform = super.dontTransform();
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> signature(Key signature) {
        GenericRequestBuilder signature2 = super.signature(signature);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> load(ModelType model) {
        GenericRequestBuilder load = super.load(model);
        return this;
    }

    public BitmapRequestBuilder<ModelType, TranscodeType> clone() {
        return (BitmapRequestBuilder) super.clone();
    }

    public Target<TranscodeType> into(ImageView view) {
        return super.into(view);
    }

    /* access modifiers changed from: package-private */
    public void applyFitCenter() {
        BitmapRequestBuilder fitCenter = fitCenter();
    }

    /* access modifiers changed from: package-private */
    public void applyCenterCrop() {
        BitmapRequestBuilder centerCrop = centerCrop();
    }
}
