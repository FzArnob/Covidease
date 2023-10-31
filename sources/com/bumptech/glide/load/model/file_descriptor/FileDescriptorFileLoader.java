package com.bumptech.glide.load.model.file_descriptor;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.FileLoader;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import java.io.File;

public class FileDescriptorFileLoader extends FileLoader<ParcelFileDescriptor> implements FileDescriptorModelLoader<File> {

    public static class Factory implements ModelLoaderFactory<File, ParcelFileDescriptor> {
        public Factory() {
        }

        public ModelLoader<File, ParcelFileDescriptor> build(Context context, GenericLoaderFactory factories) {
            ModelLoader<File, ParcelFileDescriptor> modelLoader;
            Context context2 = context;
            new FileDescriptorFileLoader((ModelLoader<Uri, ParcelFileDescriptor>) factories.buildModelLoader(Uri.class, ParcelFileDescriptor.class));
            return modelLoader;
        }

        public void teardown() {
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public FileDescriptorFileLoader(Context context) {
        this(Glide.buildFileDescriptorModelLoader(Uri.class, context));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileDescriptorFileLoader(ModelLoader<Uri, ParcelFileDescriptor> uriLoader) {
        super(uriLoader);
    }
}
