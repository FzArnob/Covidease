package com.bumptech.glide;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawableTransformation;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.provider.LoadProvider;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.DrawableCrossFadeFactory;
import com.bumptech.glide.request.animation.GlideAnimationFactory;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import java.io.File;
import java.io.InputStream;

public class GifRequestBuilder<ModelType> extends GenericRequestBuilder<ModelType, InputStream, GifDrawable, GifDrawable> implements BitmapOptions, DrawableOptions {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    GifRequestBuilder(LoadProvider<ModelType, InputStream, GifDrawable, GifDrawable> loadProvider, Class<GifDrawable> transcodeClass, GenericRequestBuilder<ModelType, ?, ?, ?> other) {
        super(loadProvider, transcodeClass, other);
    }

    public GifRequestBuilder<ModelType> thumbnail(GenericRequestBuilder<?, ?, ?, GifDrawable> thumbnailRequest) {
        GenericRequestBuilder<?, ?, ?, GifDrawable> thumbnail = super.thumbnail(thumbnailRequest);
        return this;
    }

    public GifRequestBuilder<ModelType> thumbnail(GifRequestBuilder<?> thumbnailRequest) {
        GenericRequestBuilder<?, DataType, ResourceType, TranscodeType> thumbnail = super.thumbnail(thumbnailRequest);
        return this;
    }

    public GifRequestBuilder<ModelType> thumbnail(float sizeMultiplier) {
        GenericRequestBuilder thumbnail = super.thumbnail(sizeMultiplier);
        return this;
    }

    public GifRequestBuilder<ModelType> sizeMultiplier(float sizeMultiplier) {
        GenericRequestBuilder sizeMultiplier2 = super.sizeMultiplier(sizeMultiplier);
        return this;
    }

    public GifRequestBuilder<ModelType> decoder(ResourceDecoder<InputStream, GifDrawable> decoder) {
        GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> decoder2 = super.decoder(decoder);
        return this;
    }

    public GifRequestBuilder<ModelType> cacheDecoder(ResourceDecoder<File, GifDrawable> cacheDecoder) {
        GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> cacheDecoder2 = super.cacheDecoder(cacheDecoder);
        return this;
    }

    public GifRequestBuilder<ModelType> encoder(ResourceEncoder<GifDrawable> encoder) {
        GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> encoder2 = super.encoder(encoder);
        return this;
    }

    public GifRequestBuilder<ModelType> priority(Priority priority) {
        GenericRequestBuilder priority2 = super.priority(priority);
        return this;
    }

    public GifRequestBuilder<ModelType> centerCrop() {
        return transformFrame(this.glide.getBitmapCenterCrop());
    }

    public GifRequestBuilder<ModelType> fitCenter() {
        return transformFrame(this.glide.getBitmapFitCenter());
    }

    public GifRequestBuilder<ModelType> transformFrame(BitmapTransformation... bitmapTransformations) {
        return transform((Transformation[]) toGifTransformations(bitmapTransformations));
    }

    public GifRequestBuilder<ModelType> transformFrame(Transformation<Bitmap>... bitmapTransformations) {
        return transform((Transformation[]) toGifTransformations(bitmapTransformations));
    }

    private GifDrawableTransformation[] toGifTransformations(Transformation<Bitmap>[] transformationArr) {
        GifDrawableTransformation gifDrawableTransformation;
        Transformation<Bitmap>[] bitmapTransformations = transformationArr;
        GifDrawableTransformation[] transformations = new GifDrawableTransformation[bitmapTransformations.length];
        for (int i = 0; i < bitmapTransformations.length; i++) {
            new GifDrawableTransformation(bitmapTransformations[i], this.glide.getBitmapPool());
            transformations[i] = gifDrawableTransformation;
        }
        return transformations;
    }

    public GifRequestBuilder<ModelType> transform(Transformation<GifDrawable>... transformations) {
        GenericRequestBuilder transform = super.transform(transformations);
        return this;
    }

    public GifRequestBuilder<ModelType> transcoder(ResourceTranscoder<GifDrawable, GifDrawable> transcoder) {
        GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> transcoder2 = super.transcoder(transcoder);
        return this;
    }

    public GifRequestBuilder<ModelType> crossFade() {
        GlideAnimationFactory glideAnimationFactory;
        new DrawableCrossFadeFactory();
        GenericRequestBuilder animate = super.animate(glideAnimationFactory);
        return this;
    }

    public GifRequestBuilder<ModelType> crossFade(int duration) {
        GlideAnimationFactory glideAnimationFactory;
        new DrawableCrossFadeFactory(duration);
        GenericRequestBuilder animate = super.animate(glideAnimationFactory);
        return this;
    }

    @Deprecated
    public GifRequestBuilder<ModelType> crossFade(Animation animation, int duration) {
        GlideAnimationFactory glideAnimationFactory;
        new DrawableCrossFadeFactory(animation, duration);
        GenericRequestBuilder animate = super.animate(glideAnimationFactory);
        return this;
    }

    public GifRequestBuilder<ModelType> crossFade(int animationId, int duration) {
        GlideAnimationFactory glideAnimationFactory;
        new DrawableCrossFadeFactory(this.context, animationId, duration);
        GenericRequestBuilder animate = super.animate(glideAnimationFactory);
        return this;
    }

    public GifRequestBuilder<ModelType> dontAnimate() {
        GenericRequestBuilder dontAnimate = super.dontAnimate();
        return this;
    }

    public GifRequestBuilder<ModelType> animate(int animationId) {
        GenericRequestBuilder animate = super.animate(animationId);
        return this;
    }

    @Deprecated
    public GifRequestBuilder<ModelType> animate(Animation animation) {
        GenericRequestBuilder animate = super.animate(animation);
        return this;
    }

    public GifRequestBuilder<ModelType> animate(ViewPropertyAnimation.Animator animator) {
        GenericRequestBuilder animate = super.animate(animator);
        return this;
    }

    public GifRequestBuilder<ModelType> animate(GlideAnimationFactory<GifDrawable> animationFactory) {
        GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> animate = super.animate(animationFactory);
        return this;
    }

    public GifRequestBuilder<ModelType> placeholder(int resourceId) {
        GenericRequestBuilder placeholder = super.placeholder(resourceId);
        return this;
    }

    public GifRequestBuilder<ModelType> placeholder(Drawable drawable) {
        GenericRequestBuilder placeholder = super.placeholder(drawable);
        return this;
    }

    public GifRequestBuilder<ModelType> fallback(Drawable drawable) {
        GenericRequestBuilder fallback = super.fallback(drawable);
        return this;
    }

    public GifRequestBuilder<ModelType> fallback(int resourceId) {
        GenericRequestBuilder fallback = super.fallback(resourceId);
        return this;
    }

    public GifRequestBuilder<ModelType> error(int resourceId) {
        GenericRequestBuilder error = super.error(resourceId);
        return this;
    }

    public GifRequestBuilder<ModelType> error(Drawable drawable) {
        GenericRequestBuilder error = super.error(drawable);
        return this;
    }

    public GifRequestBuilder<ModelType> listener(RequestListener<? super ModelType, GifDrawable> requestListener) {
        GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> listener = super.listener(requestListener);
        return this;
    }

    public GifRequestBuilder<ModelType> skipMemoryCache(boolean skip) {
        GenericRequestBuilder skipMemoryCache = super.skipMemoryCache(skip);
        return this;
    }

    public GifRequestBuilder<ModelType> diskCacheStrategy(DiskCacheStrategy strategy) {
        GenericRequestBuilder diskCacheStrategy = super.diskCacheStrategy(strategy);
        return this;
    }

    public GifRequestBuilder<ModelType> override(int width, int height) {
        GenericRequestBuilder override = super.override(width, height);
        return this;
    }

    public GifRequestBuilder<ModelType> sourceEncoder(Encoder<InputStream> sourceEncoder) {
        GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> sourceEncoder2 = super.sourceEncoder(sourceEncoder);
        return this;
    }

    public GifRequestBuilder<ModelType> dontTransform() {
        GenericRequestBuilder dontTransform = super.dontTransform();
        return this;
    }

    public GifRequestBuilder<ModelType> signature(Key signature) {
        GenericRequestBuilder signature2 = super.signature(signature);
        return this;
    }

    public GifRequestBuilder<ModelType> load(ModelType model) {
        GenericRequestBuilder load = super.load(model);
        return this;
    }

    public GifRequestBuilder<ModelType> clone() {
        return (GifRequestBuilder) super.clone();
    }

    /* access modifiers changed from: package-private */
    public void applyFitCenter() {
        GifRequestBuilder fitCenter = fitCenter();
    }

    /* access modifiers changed from: package-private */
    public void applyCenterCrop() {
        GifRequestBuilder centerCrop = centerCrop();
    }
}
