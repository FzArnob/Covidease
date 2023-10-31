package com.bumptech.glide.load.data;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class StreamLocalUriFetcher extends LocalUriFetcher<InputStream> {
    private static final int ID_CONTACT = 3;
    private static final int ID_DISPLAY_PHOTO = 4;
    private static final int ID_LOOKUP = 1;
    private static final int ID_THUMBNAIL = 2;
    private static final UriMatcher URI_MATCHER;

    static {
        UriMatcher uriMatcher;
        new UriMatcher(-1);
        URI_MATCHER = uriMatcher;
        URI_MATCHER.addURI("com.android.contacts", "contacts/lookup/*/#", 1);
        URI_MATCHER.addURI("com.android.contacts", "contacts/lookup/*", 1);
        URI_MATCHER.addURI("com.android.contacts", "contacts/#/photo", 2);
        URI_MATCHER.addURI("com.android.contacts", "contacts/#", 3);
        URI_MATCHER.addURI("com.android.contacts", "contacts/#/display_photo", 4);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StreamLocalUriFetcher(Context context, Uri uri) {
        super(context, uri);
    }

    /* access modifiers changed from: protected */
    public InputStream loadResource(Uri uri, ContentResolver contentResolver) throws FileNotFoundException {
        Uri uri2 = uri;
        return loadResourceFromUri(uri2, contentResolver, URI_MATCHER.match(uri2));
    }

    /* access modifiers changed from: protected */
    public void close(InputStream data) throws IOException {
        data.close();
    }

    private InputStream loadResourceFromUri(Uri uri, ContentResolver contentResolver, int i) throws FileNotFoundException {
        Throwable th;
        Uri uri2 = uri;
        ContentResolver contentResolver2 = contentResolver;
        int matchedUri = i;
        switch (matchedUri) {
            case 1:
            case 3:
                if (matchedUri == 1) {
                    uri2 = ContactsContract.Contacts.lookupContact(contentResolver2, uri2);
                    if (uri2 == null) {
                        Throwable th2 = th;
                        new FileNotFoundException("Contact cannot be found");
                        throw th2;
                    }
                }
                return openContactPhotoInputStream(contentResolver2, uri2);
            default:
                return contentResolver2.openInputStream(uri2);
        }
    }

    @TargetApi(14)
    private InputStream openContactPhotoInputStream(ContentResolver contentResolver, Uri uri) {
        ContentResolver contentResolver2 = contentResolver;
        Uri contactUri = uri;
        if (Build.VERSION.SDK_INT < 14) {
            return ContactsContract.Contacts.openContactPhotoInputStream(contentResolver2, contactUri);
        }
        return ContactsContract.Contacts.openContactPhotoInputStream(contentResolver2, contactUri, true);
    }
}
