package com.bumptech.glide.load.model.file_descriptor;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.StringLoader;

public class FileDescriptorStringLoader extends StringLoader<ParcelFileDescriptor> implements FileDescriptorModelLoader<String> {

    public static class Factory implements ModelLoaderFactory<String, ParcelFileDescriptor> {
        public Factory() {
        }

        public ModelLoader<String, ParcelFileDescriptor> build(Context context, GenericLoaderFactory factories) {
            ModelLoader<String, ParcelFileDescriptor> modelLoader;
            Context context2 = context;
            new FileDescriptorStringLoader((ModelLoader<Uri, ParcelFileDescriptor>) factories.buildModelLoader(Uri.class, ParcelFileDescriptor.class));
            return modelLoader;
        }

        public void teardown() {
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public FileDescriptorStringLoader(Context context) {
        this(Glide.buildFileDescriptorModelLoader(Uri.class, context));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileDescriptorStringLoader(ModelLoader<Uri, ParcelFileDescriptor> uriLoader) {
        super(uriLoader);
    }
}
