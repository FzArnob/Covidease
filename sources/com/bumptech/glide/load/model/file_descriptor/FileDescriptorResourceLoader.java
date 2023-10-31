package com.bumptech.glide.load.model.file_descriptor;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.ResourceLoader;

public class FileDescriptorResourceLoader extends ResourceLoader<ParcelFileDescriptor> implements FileDescriptorModelLoader<Integer> {

    public static class Factory implements ModelLoaderFactory<Integer, ParcelFileDescriptor> {
        public Factory() {
        }

        public ModelLoader<Integer, ParcelFileDescriptor> build(Context context, GenericLoaderFactory factories) {
            ModelLoader<Integer, ParcelFileDescriptor> modelLoader;
            new FileDescriptorResourceLoader(context, factories.buildModelLoader(Uri.class, ParcelFileDescriptor.class));
            return modelLoader;
        }

        public void teardown() {
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FileDescriptorResourceLoader(android.content.Context r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            java.lang.Class<android.net.Uri> r4 = android.net.Uri.class
            r5 = r1
            com.bumptech.glide.load.model.ModelLoader r4 = com.bumptech.glide.Glide.buildFileDescriptorModelLoader(r4, (android.content.Context) r5)
            r2.<init>(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.model.file_descriptor.FileDescriptorResourceLoader.<init>(android.content.Context):void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileDescriptorResourceLoader(Context context, ModelLoader<Uri, ParcelFileDescriptor> uriLoader) {
        super(context, uriLoader);
    }
}
