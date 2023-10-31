package android.support.p000v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.media.MediaDescriptionCompat;
import android.support.p000v4.media.session.MediaSessionCompat;
import android.support.p000v4.util.C1642ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Set;

/* renamed from: android.support.v4.media.MediaMetadataCompat */
public final class MediaMetadataCompat implements Parcelable {
    public static final Parcelable.Creator<MediaMetadataCompat> CREATOR;
    static final C1642ArrayMap<String, Integer> METADATA_KEYS_TYPE;
    public static final String METADATA_KEY_ADVERTISEMENT = "android.media.metadata.ADVERTISEMENT";
    public static final String METADATA_KEY_ALBUM = "android.media.metadata.ALBUM";
    public static final String METADATA_KEY_ALBUM_ART = "android.media.metadata.ALBUM_ART";
    public static final String METADATA_KEY_ALBUM_ARTIST = "android.media.metadata.ALBUM_ARTIST";
    public static final String METADATA_KEY_ALBUM_ART_URI = "android.media.metadata.ALBUM_ART_URI";
    public static final String METADATA_KEY_ART = "android.media.metadata.ART";
    public static final String METADATA_KEY_ARTIST = "android.media.metadata.ARTIST";
    public static final String METADATA_KEY_ART_URI = "android.media.metadata.ART_URI";
    public static final String METADATA_KEY_AUTHOR = "android.media.metadata.AUTHOR";
    public static final String METADATA_KEY_BT_FOLDER_TYPE = "android.media.metadata.BT_FOLDER_TYPE";
    public static final String METADATA_KEY_COMPILATION = "android.media.metadata.COMPILATION";
    public static final String METADATA_KEY_COMPOSER = "android.media.metadata.COMPOSER";
    public static final String METADATA_KEY_DATE = "android.media.metadata.DATE";
    public static final String METADATA_KEY_DISC_NUMBER = "android.media.metadata.DISC_NUMBER";
    public static final String METADATA_KEY_DISPLAY_DESCRIPTION = "android.media.metadata.DISPLAY_DESCRIPTION";
    public static final String METADATA_KEY_DISPLAY_ICON = "android.media.metadata.DISPLAY_ICON";
    public static final String METADATA_KEY_DISPLAY_ICON_URI = "android.media.metadata.DISPLAY_ICON_URI";
    public static final String METADATA_KEY_DISPLAY_SUBTITLE = "android.media.metadata.DISPLAY_SUBTITLE";
    public static final String METADATA_KEY_DISPLAY_TITLE = "android.media.metadata.DISPLAY_TITLE";
    public static final String METADATA_KEY_DOWNLOAD_STATUS = "android.media.metadata.DOWNLOAD_STATUS";
    public static final String METADATA_KEY_DURATION = "android.media.metadata.DURATION";
    public static final String METADATA_KEY_GENRE = "android.media.metadata.GENRE";
    public static final String METADATA_KEY_MEDIA_ID = "android.media.metadata.MEDIA_ID";
    public static final String METADATA_KEY_MEDIA_URI = "android.media.metadata.MEDIA_URI";
    public static final String METADATA_KEY_NUM_TRACKS = "android.media.metadata.NUM_TRACKS";
    public static final String METADATA_KEY_RATING = "android.media.metadata.RATING";
    public static final String METADATA_KEY_TITLE = "android.media.metadata.TITLE";
    public static final String METADATA_KEY_TRACK_NUMBER = "android.media.metadata.TRACK_NUMBER";
    public static final String METADATA_KEY_USER_RATING = "android.media.metadata.USER_RATING";
    public static final String METADATA_KEY_WRITER = "android.media.metadata.WRITER";
    public static final String METADATA_KEY_YEAR = "android.media.metadata.YEAR";
    static final int METADATA_TYPE_BITMAP = 2;
    static final int METADATA_TYPE_LONG = 0;
    static final int METADATA_TYPE_RATING = 3;
    static final int METADATA_TYPE_TEXT = 1;
    private static final String[] PREFERRED_BITMAP_ORDER;
    private static final String[] PREFERRED_DESCRIPTION_ORDER;
    private static final String[] PREFERRED_URI_ORDER;
    private static final String TAG = "MediaMetadata";
    final Bundle mBundle;
    private MediaDescriptionCompat mDescription;
    private Object mMetadataObj;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.media.MediaMetadataCompat$BitmapKey */
    public @interface BitmapKey {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.media.MediaMetadataCompat$LongKey */
    public @interface LongKey {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.media.MediaMetadataCompat$RatingKey */
    public @interface RatingKey {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.media.MediaMetadataCompat$TextKey */
    public @interface TextKey {
    }

    static {
        C1642ArrayMap<String, Integer> arrayMap;
        Parcelable.Creator<MediaMetadataCompat> creator;
        new C1642ArrayMap<>();
        METADATA_KEYS_TYPE = arrayMap;
        Integer put = METADATA_KEYS_TYPE.put(METADATA_KEY_TITLE, 1);
        Integer put2 = METADATA_KEYS_TYPE.put(METADATA_KEY_ARTIST, 1);
        Integer put3 = METADATA_KEYS_TYPE.put(METADATA_KEY_DURATION, 0);
        Integer put4 = METADATA_KEYS_TYPE.put(METADATA_KEY_ALBUM, 1);
        Integer put5 = METADATA_KEYS_TYPE.put(METADATA_KEY_AUTHOR, 1);
        Integer put6 = METADATA_KEYS_TYPE.put(METADATA_KEY_WRITER, 1);
        Integer put7 = METADATA_KEYS_TYPE.put(METADATA_KEY_COMPOSER, 1);
        Integer put8 = METADATA_KEYS_TYPE.put(METADATA_KEY_COMPILATION, 1);
        Integer put9 = METADATA_KEYS_TYPE.put(METADATA_KEY_DATE, 1);
        Integer put10 = METADATA_KEYS_TYPE.put(METADATA_KEY_YEAR, 0);
        Integer put11 = METADATA_KEYS_TYPE.put(METADATA_KEY_GENRE, 1);
        Integer put12 = METADATA_KEYS_TYPE.put(METADATA_KEY_TRACK_NUMBER, 0);
        Integer put13 = METADATA_KEYS_TYPE.put(METADATA_KEY_NUM_TRACKS, 0);
        Integer put14 = METADATA_KEYS_TYPE.put(METADATA_KEY_DISC_NUMBER, 0);
        Integer put15 = METADATA_KEYS_TYPE.put(METADATA_KEY_ALBUM_ARTIST, 1);
        Integer put16 = METADATA_KEYS_TYPE.put(METADATA_KEY_ART, 2);
        Integer put17 = METADATA_KEYS_TYPE.put(METADATA_KEY_ART_URI, 1);
        Integer put18 = METADATA_KEYS_TYPE.put(METADATA_KEY_ALBUM_ART, 2);
        Integer put19 = METADATA_KEYS_TYPE.put(METADATA_KEY_ALBUM_ART_URI, 1);
        Integer put20 = METADATA_KEYS_TYPE.put(METADATA_KEY_USER_RATING, 3);
        Integer put21 = METADATA_KEYS_TYPE.put(METADATA_KEY_RATING, 3);
        Integer put22 = METADATA_KEYS_TYPE.put(METADATA_KEY_DISPLAY_TITLE, 1);
        Integer put23 = METADATA_KEYS_TYPE.put(METADATA_KEY_DISPLAY_SUBTITLE, 1);
        Integer put24 = METADATA_KEYS_TYPE.put(METADATA_KEY_DISPLAY_DESCRIPTION, 1);
        Integer put25 = METADATA_KEYS_TYPE.put(METADATA_KEY_DISPLAY_ICON, 2);
        Integer put26 = METADATA_KEYS_TYPE.put(METADATA_KEY_DISPLAY_ICON_URI, 1);
        Integer put27 = METADATA_KEYS_TYPE.put(METADATA_KEY_MEDIA_ID, 1);
        Integer put28 = METADATA_KEYS_TYPE.put(METADATA_KEY_BT_FOLDER_TYPE, 0);
        Integer put29 = METADATA_KEYS_TYPE.put(METADATA_KEY_MEDIA_URI, 1);
        Integer put30 = METADATA_KEYS_TYPE.put(METADATA_KEY_ADVERTISEMENT, 0);
        Integer put31 = METADATA_KEYS_TYPE.put(METADATA_KEY_DOWNLOAD_STATUS, 0);
        String[] strArr = new String[7];
        strArr[0] = METADATA_KEY_TITLE;
        String[] strArr2 = strArr;
        strArr2[1] = METADATA_KEY_ARTIST;
        String[] strArr3 = strArr2;
        strArr3[2] = METADATA_KEY_ALBUM;
        String[] strArr4 = strArr3;
        strArr4[3] = METADATA_KEY_ALBUM_ARTIST;
        String[] strArr5 = strArr4;
        strArr5[4] = METADATA_KEY_WRITER;
        String[] strArr6 = strArr5;
        strArr6[5] = METADATA_KEY_AUTHOR;
        String[] strArr7 = strArr6;
        strArr7[6] = METADATA_KEY_COMPOSER;
        PREFERRED_DESCRIPTION_ORDER = strArr7;
        String[] strArr8 = new String[3];
        strArr8[0] = METADATA_KEY_DISPLAY_ICON;
        String[] strArr9 = strArr8;
        strArr9[1] = METADATA_KEY_ART;
        String[] strArr10 = strArr9;
        strArr10[2] = METADATA_KEY_ALBUM_ART;
        PREFERRED_BITMAP_ORDER = strArr10;
        String[] strArr11 = new String[3];
        strArr11[0] = METADATA_KEY_DISPLAY_ICON_URI;
        String[] strArr12 = strArr11;
        strArr12[1] = METADATA_KEY_ART_URI;
        String[] strArr13 = strArr12;
        strArr13[2] = METADATA_KEY_ALBUM_ART_URI;
        PREFERRED_URI_ORDER = strArr13;
        new Parcelable.Creator<MediaMetadataCompat>() {
            public MediaMetadataCompat createFromParcel(Parcel in) {
                MediaMetadataCompat mediaMetadataCompat;
                new MediaMetadataCompat(in);
                return mediaMetadataCompat;
            }

            public MediaMetadataCompat[] newArray(int size) {
                return new MediaMetadataCompat[size];
            }
        };
        CREATOR = creator;
    }

    MediaMetadataCompat(Bundle bundle) {
        Bundle bundle2;
        new Bundle(bundle);
        this.mBundle = bundle2;
        MediaSessionCompat.ensureClassLoader(this.mBundle);
    }

    MediaMetadataCompat(Parcel in) {
        this.mBundle = in.readBundle(MediaSessionCompat.class.getClassLoader());
    }

    public boolean containsKey(String key) {
        return this.mBundle.containsKey(key);
    }

    public CharSequence getText(String key) {
        return this.mBundle.getCharSequence(key);
    }

    public String getString(String key) {
        CharSequence text = this.mBundle.getCharSequence(key);
        if (text != null) {
            return text.toString();
        }
        return null;
    }

    public long getLong(String key) {
        return this.mBundle.getLong(key, 0);
    }

    public RatingCompat getRating(String str) {
        String key = str;
        RatingCompat rating = null;
        try {
            if (Build.VERSION.SDK_INT >= 19) {
                rating = RatingCompat.fromRating(this.mBundle.getParcelable(key));
            } else {
                rating = (RatingCompat) this.mBundle.getParcelable(key);
            }
        } catch (Exception e) {
            int w = Log.w(TAG, "Failed to retrieve a key as Rating.", e);
        }
        return rating;
    }

    public Bitmap getBitmap(String key) {
        Bitmap bmp = null;
        try {
            bmp = (Bitmap) this.mBundle.getParcelable(key);
        } catch (Exception e) {
            int w = Log.w(TAG, "Failed to retrieve a key as Bitmap.", e);
        }
        return bmp;
    }

    public MediaDescriptionCompat getDescription() {
        MediaDescriptionCompat.Builder builder;
        Bundle bundle;
        if (this.mDescription != null) {
            return this.mDescription;
        }
        String mediaId = getString(METADATA_KEY_MEDIA_ID);
        CharSequence[] text = new CharSequence[3];
        Bitmap icon = null;
        Uri iconUri = null;
        CharSequence displayText = getText(METADATA_KEY_DISPLAY_TITLE);
        if (!TextUtils.isEmpty(displayText)) {
            text[0] = displayText;
            text[1] = getText(METADATA_KEY_DISPLAY_SUBTITLE);
            text[2] = getText(METADATA_KEY_DISPLAY_DESCRIPTION);
        } else {
            int textIndex = 0;
            int keyIndex = 0;
            while (textIndex < text.length && keyIndex < PREFERRED_DESCRIPTION_ORDER.length) {
                int i = keyIndex;
                keyIndex++;
                CharSequence next = getText(PREFERRED_DESCRIPTION_ORDER[i]);
                if (!TextUtils.isEmpty(next)) {
                    int i2 = textIndex;
                    textIndex++;
                    text[i2] = next;
                }
            }
        }
        int i3 = 0;
        while (true) {
            if (i3 >= PREFERRED_BITMAP_ORDER.length) {
                break;
            }
            Bitmap next2 = getBitmap(PREFERRED_BITMAP_ORDER[i3]);
            if (next2 != null) {
                icon = next2;
                break;
            }
            i3++;
        }
        int i4 = 0;
        while (true) {
            if (i4 >= PREFERRED_URI_ORDER.length) {
                break;
            }
            String next3 = getString(PREFERRED_URI_ORDER[i4]);
            if (!TextUtils.isEmpty(next3)) {
                iconUri = Uri.parse(next3);
                break;
            }
            i4++;
        }
        Uri mediaUri = null;
        String mediaUriStr = getString(METADATA_KEY_MEDIA_URI);
        if (!TextUtils.isEmpty(mediaUriStr)) {
            mediaUri = Uri.parse(mediaUriStr);
        }
        new MediaDescriptionCompat.Builder();
        MediaDescriptionCompat.Builder bob = builder;
        MediaDescriptionCompat.Builder mediaId2 = bob.setMediaId(mediaId);
        MediaDescriptionCompat.Builder title = bob.setTitle(text[0]);
        MediaDescriptionCompat.Builder subtitle = bob.setSubtitle(text[1]);
        MediaDescriptionCompat.Builder description = bob.setDescription(text[2]);
        MediaDescriptionCompat.Builder iconBitmap = bob.setIconBitmap(icon);
        MediaDescriptionCompat.Builder iconUri2 = bob.setIconUri(iconUri);
        MediaDescriptionCompat.Builder mediaUri2 = bob.setMediaUri(mediaUri);
        new Bundle();
        Bundle bundle2 = bundle;
        if (this.mBundle.containsKey(METADATA_KEY_BT_FOLDER_TYPE)) {
            bundle2.putLong(MediaDescriptionCompat.EXTRA_BT_FOLDER_TYPE, getLong(METADATA_KEY_BT_FOLDER_TYPE));
        }
        if (this.mBundle.containsKey(METADATA_KEY_DOWNLOAD_STATUS)) {
            bundle2.putLong(MediaDescriptionCompat.EXTRA_DOWNLOAD_STATUS, getLong(METADATA_KEY_DOWNLOAD_STATUS));
        }
        if (!bundle2.isEmpty()) {
            MediaDescriptionCompat.Builder extras = bob.setExtras(bundle2);
        }
        this.mDescription = bob.build();
        return this.mDescription;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int i) {
        int i2 = i;
        dest.writeBundle(this.mBundle);
    }

    public int size() {
        return this.mBundle.size();
    }

    public Set<String> keySet() {
        return this.mBundle.keySet();
    }

    public Bundle getBundle() {
        Bundle bundle;
        new Bundle(this.mBundle);
        return bundle;
    }

    public static MediaMetadataCompat fromMediaMetadata(Object obj) {
        Object metadataObj = obj;
        if (metadataObj == null || Build.VERSION.SDK_INT < 21) {
            return null;
        }
        Parcel p = Parcel.obtain();
        MediaMetadataCompatApi21.writeToParcel(metadataObj, p, 0);
        p.setDataPosition(0);
        MediaMetadataCompat metadata = CREATOR.createFromParcel(p);
        p.recycle();
        metadata.mMetadataObj = metadataObj;
        return metadata;
    }

    public Object getMediaMetadata() {
        if (this.mMetadataObj == null && Build.VERSION.SDK_INT >= 21) {
            Parcel p = Parcel.obtain();
            writeToParcel(p, 0);
            p.setDataPosition(0);
            this.mMetadataObj = MediaMetadataCompatApi21.createFromParcel(p);
            p.recycle();
        }
        return this.mMetadataObj;
    }

    /* renamed from: android.support.v4.media.MediaMetadataCompat$Builder */
    public static final class Builder {
        private final Bundle mBundle;

        public Builder() {
            Bundle bundle;
            new Bundle();
            this.mBundle = bundle;
        }

        public Builder(MediaMetadataCompat source) {
            Bundle bundle;
            new Bundle(source.mBundle);
            this.mBundle = bundle;
            MediaSessionCompat.ensureClassLoader(this.mBundle);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder(MediaMetadataCompat source, int i) {
            this(source);
            int maxBitmapSize = i;
            for (String key : this.mBundle.keySet()) {
                Object value = this.mBundle.get(key);
                if (value instanceof Bitmap) {
                    Bitmap bmp = (Bitmap) value;
                    if (bmp.getHeight() > maxBitmapSize || bmp.getWidth() > maxBitmapSize) {
                        Builder putBitmap = putBitmap(key, scaleBitmap(bmp, maxBitmapSize));
                    }
                }
            }
        }

        public Builder putText(String str, CharSequence charSequence) {
            Throwable th;
            StringBuilder sb;
            String key = str;
            CharSequence value = charSequence;
            if (!MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(key) || MediaMetadataCompat.METADATA_KEYS_TYPE.get(key).intValue() == 1) {
                this.mBundle.putCharSequence(key, value);
                return this;
            }
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("The ").append(key).append(" key cannot be used to put a CharSequence").toString());
            throw th2;
        }

        public Builder putString(String str, String str2) {
            Throwable th;
            StringBuilder sb;
            String key = str;
            String value = str2;
            if (!MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(key) || MediaMetadataCompat.METADATA_KEYS_TYPE.get(key).intValue() == 1) {
                this.mBundle.putCharSequence(key, value);
                return this;
            }
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("The ").append(key).append(" key cannot be used to put a String").toString());
            throw th2;
        }

        public Builder putLong(String str, long j) {
            Throwable th;
            StringBuilder sb;
            String key = str;
            long value = j;
            if (!MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(key) || MediaMetadataCompat.METADATA_KEYS_TYPE.get(key).intValue() == 0) {
                this.mBundle.putLong(key, value);
                return this;
            }
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("The ").append(key).append(" key cannot be used to put a long").toString());
            throw th2;
        }

        public Builder putRating(String str, RatingCompat ratingCompat) {
            Throwable th;
            StringBuilder sb;
            String key = str;
            RatingCompat value = ratingCompat;
            if (!MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(key) || MediaMetadataCompat.METADATA_KEYS_TYPE.get(key).intValue() == 3) {
                if (Build.VERSION.SDK_INT >= 19) {
                    this.mBundle.putParcelable(key, (Parcelable) value.getRating());
                } else {
                    this.mBundle.putParcelable(key, value);
                }
                return this;
            }
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("The ").append(key).append(" key cannot be used to put a Rating").toString());
            throw th2;
        }

        public Builder putBitmap(String str, Bitmap bitmap) {
            Throwable th;
            StringBuilder sb;
            String key = str;
            Bitmap value = bitmap;
            if (!MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(key) || MediaMetadataCompat.METADATA_KEYS_TYPE.get(key).intValue() == 2) {
                this.mBundle.putParcelable(key, value);
                return this;
            }
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("The ").append(key).append(" key cannot be used to put a Bitmap").toString());
            throw th2;
        }

        public MediaMetadataCompat build() {
            MediaMetadataCompat mediaMetadataCompat;
            new MediaMetadataCompat(this.mBundle);
            return mediaMetadataCompat;
        }

        private Bitmap scaleBitmap(Bitmap bitmap, int maxSize) {
            Bitmap bmp = bitmap;
            float maxSizeF = (float) maxSize;
            float scale = Math.min(maxSizeF / ((float) bmp.getWidth()), maxSizeF / ((float) bmp.getHeight()));
            int height = (int) (((float) bmp.getHeight()) * scale);
            return Bitmap.createScaledBitmap(bmp, (int) (((float) bmp.getWidth()) * scale), height, true);
        }
    }
}
