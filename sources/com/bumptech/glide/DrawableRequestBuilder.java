package com.bumptech.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.widget.ImageView;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.ImageVideoWrapper;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gifbitmap.GifBitmapWrapper;
import com.bumptech.glide.load.resource.gifbitmap.GifBitmapWrapperTransformation;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestTracker;
import com.bumptech.glide.provider.LoadProvider;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.DrawableCrossFadeFactory;
import com.bumptech.glide.request.animation.GlideAnimationFactory;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.Target;
import java.io.File;

public class DrawableRequestBuilder<ModelType> extends GenericRequestBuilder<ModelType, ImageVideoWrapper, GifBitmapWrapper, GlideDrawable> implements BitmapOptions, DrawableOptions {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DrawableRequestBuilder(Context context, Class<ModelType> modelClass, LoadProvider<ModelType, ImageVideoWrapper, GifBitmapWrapper, GlideDrawable> loadProvider, Glide glide, RequestTracker requestTracker, Lifecycle lifecycle) {
        super(context, modelClass, loadProvider, GlideDrawable.class, glide, requestTracker, lifecycle);
        DrawableRequestBuilder crossFade = crossFade();
    }

    public DrawableRequestBuilder<ModelType> thumbnail(DrawableRequestBuilder<?> thumbnailRequest) {
        GenericRequestBuilder<?, DataType, ResourceType, TranscodeType> thumbnail = super.thumbnail(thumbnailRequest);
        return this;
    }

    public DrawableRequestBuilder<ModelType> thumbnail(GenericRequestBuilder<?, ?, ?, GlideDrawable> thumbnailRequest) {
        GenericRequestBuilder<?, ?, ?, GlideDrawable> thumbnail = super.thumbnail(thumbnailRequest);
        return this;
    }

    public DrawableRequestBuilder<ModelType> thumbnail(float sizeMultiplier) {
        GenericRequestBuilder thumbnail = super.thumbnail(sizeMultiplier);
        return this;
    }

    public DrawableRequestBuilder<ModelType> sizeMultiplier(float sizeMultiplier) {
        GenericRequestBuilder sizeMultiplier2 = super.sizeMultiplier(sizeMultiplier);
        return this;
    }

    public DrawableRequestBuilder<ModelType> decoder(ResourceDecoder<ImageVideoWrapper, GifBitmapWrapper> decoder) {
        GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> decoder2 = super.decoder(decoder);
        return this;
    }

    public DrawableRequestBuilder<ModelType> cacheDecoder(ResourceDecoder<File, GifBitmapWrapper> cacheDecoder) {
        GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> cacheDecoder2 = super.cacheDecoder(cacheDecoder);
        return this;
    }

    public DrawableRequestBuilder<ModelType> encoder(ResourceEncoder<GifBitmapWrapper> encoder) {
        GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> encoder2 = super.encoder(encoder);
        return this;
    }

    public DrawableRequestBuilder<ModelType> priority(Priority priority) {
        GenericRequestBuilder priority2 = super.priority(priority);
        return this;
    }

    public DrawableRequestBuilder<ModelType> transform(BitmapTransformation... transformations) {
        return bitmapTransform(transformations);
    }

    public DrawableRequestBuilder<ModelType> centerCrop() {
        return transform((Transformation<GifBitmapWrapper>[]) new Transformation[]{this.glide.getDrawableCenterCrop()});
    }

    public DrawableRequestBuilder<ModelType> fitCenter() {
        return transform((Transformation<GifBitmapWrapper>[]) new Transformation[]{this.glide.getDrawableFitCenter()});
    }

    public DrawableRequestBuilder<ModelType> bitmapTransform(Transformation<Bitmap>... transformationArr) {
        GifBitmapWrapperTransformation gifBitmapWrapperTransformation;
        Transformation<Bitmap>[] bitmapTransformations = transformationArr;
        GifBitmapWrapperTransformation[] transformations = new GifBitmapWrapperTransformation[bitmapTransformations.length];
        for (int i = 0; i < bitmapTransformations.length; i++) {
            new GifBitmapWrapperTransformation(this.glide.getBitmapPool(), bitmapTransformations[i]);
            transformations[i] = gifBitmapWrapperTransformation;
        }
        return transform((Transformation<GifBitmapWrapper>[]) transformations);
    }

    public DrawableRequestBuilder<ModelType> transform(Transformation<GifBitmapWrapper>... transformation) {
        GenericRequestBuilder transform = super.transform(transformation);
        return this;
    }

    public DrawableRequestBuilder<ModelType> transcoder(ResourceTranscoder<GifBitmapWrapper, GlideDrawable> transcoder) {
        GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> transcoder2 = super.transcoder(transcoder);
        return this;
    }

    public final DrawableRequestBuilder<ModelType> crossFade() {
        GlideAnimationFactory glideAnimationFactory;
        new DrawableCrossFadeFactory();
        GenericRequestBuilder animate = super.animate(glideAnimationFactory);
        return this;
    }

    public DrawableRequestBuilder<ModelType> crossFade(int duration) {
        GlideAnimationFactory glideAnimationFactory;
        new DrawableCrossFadeFactory(duration);
        GenericRequestBuilder animate = super.animate(glideAnimationFactory);
        return this;
    }

    @Deprecated
    public DrawableRequestBuilder<ModelType> crossFade(Animation animation, int duration) {
        GlideAnimationFactory glideAnimationFactory;
        new DrawableCrossFadeFactory(animation, duration);
        GenericRequestBuilder animate = super.animate(glideAnimationFactory);
        return this;
    }

    public DrawableRequestBuilder<ModelType> crossFade(int animationId, int duration) {
        GlideAnimationFactory glideAnimationFactory;
        new DrawableCrossFadeFactory(this.context, animationId, duration);
        GenericRequestBuilder animate = super.animate(glideAnimationFactory);
        return this;
    }

    public DrawableRequestBuilder<ModelType> dontAnimate() {
        GenericRequestBuilder dontAnimate = super.dontAnimate();
        return this;
    }

    public DrawableRequestBuilder<ModelType> animate(ViewPropertyAnimation.Animator animator) {
        GenericRequestBuilder animate = super.animate(animator);
        return this;
    }

    public DrawableRequestBuilder<ModelType> animate(GlideAnimationFactory<GlideDrawable> animationFactory) {
        GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> animate = super.animate(animationFactory);
        return this;
    }

    public DrawableRequestBuilder<ModelType> animate(int animationId) {
        GenericRequestBuilder animate = super.animate(animationId);
        return this;
    }

    @Deprecated
    public DrawableRequestBuilder<ModelType> animate(Animation animation) {
        GenericRequestBuilder animate = super.animate(animation);
        return this;
    }

    public DrawableRequestBuilder<ModelType> placeholder(int resourceId) {
        GenericRequestBuilder placeholder = super.placeholder(resourceId);
        return this;
    }

    public DrawableRequestBuilder<ModelType> placeholder(Drawable drawable) {
        GenericRequestBuilder placeholder = super.placeholder(drawable);
        return this;
    }

    public DrawableRequestBuilder<ModelType> fallback(Drawable drawable) {
        GenericRequestBuilder fallback = super.fallback(drawable);
        return this;
    }

    public DrawableRequestBuilder<ModelType> fallback(int resourceId) {
        GenericRequestBuilder fallback = super.fallback(resourceId);
        return this;
    }

    public DrawableRequestBuilder<ModelType> error(int resourceId) {
        GenericRequestBuilder error = super.error(resourceId);
        return this;
    }

    public DrawableRequestBuilder<ModelType> error(Drawable drawable) {
        GenericRequestBuilder error = super.error(drawable);
        return this;
    }

    public DrawableRequestBuilder<ModelType> listener(RequestListener<? super ModelType, GlideDrawable> requestListener) {
        GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> listener = super.listener(requestListener);
        return this;
    }

    public DrawableRequestBuilder<ModelType> diskCacheStrategy(DiskCacheStrategy strategy) {
        GenericRequestBuilder diskCacheStrategy = super.diskCacheStrategy(strategy);
        return this;
    }

    public DrawableRequestBuilder<ModelType> skipMemoryCache(boolean skip) {
        GenericRequestBuilder skipMemoryCache = super.skipMemoryCache(skip);
        return this;
    }

    public DrawableRequestBuilder<ModelType> override(int width, int height) {
        GenericRequestBuilder override = super.override(width, height);
        return this;
    }

    public DrawableRequestBuilder<ModelType> sourceEncoder(Encoder<ImageVideoWrapper> sourceEncoder) {
        GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> sourceEncoder2 = super.sourceEncoder(sourceEncoder);
        return this;
    }

    public DrawableRequestBuilder<ModelType> dontTransform() {
        GenericRequestBuilder dontTransform = super.dontTransform();
        return this;
    }

    public DrawableRequestBuilder<ModelType> signature(Key signature) {
        GenericRequestBuilder signature2 = super.signature(signature);
        return this;
    }

    public DrawableRequestBuilder<ModelType> load(ModelType model) {
        GenericRequestBuilder load = super.load(model);
        return this;
    }

    public DrawableRequestBuilder<ModelType> clone() {
        return (DrawableRequestBuilder) super.clone();
    }

    public Target<GlideDrawable> into(ImageView view) {
        return super.into(view);
    }

    /* access modifiers changed from: package-private */
    public void applyFitCenter() {
        DrawableRequestBuilder fitCenter = fitCenter();
    }

    /* access modifiers changed from: package-private */
    public void applyCenterCrop() {
        DrawableRequestBuilder centerCrop = centerCrop();
    }
}
