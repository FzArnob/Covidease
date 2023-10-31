package android.support.p000v4.media;

import android.media.Rating;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* renamed from: android.support.v4.media.RatingCompat */
public final class RatingCompat implements Parcelable {
    public static final Parcelable.Creator<RatingCompat> CREATOR;
    public static final int RATING_3_STARS = 3;
    public static final int RATING_4_STARS = 4;
    public static final int RATING_5_STARS = 5;
    public static final int RATING_HEART = 1;
    public static final int RATING_NONE = 0;
    private static final float RATING_NOT_RATED = -1.0f;
    public static final int RATING_PERCENTAGE = 6;
    public static final int RATING_THUMB_UP_DOWN = 2;
    private static final String TAG = "Rating";
    private Object mRatingObj;
    private final int mRatingStyle;
    private final float mRatingValue;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.media.RatingCompat$StarStyle */
    public @interface StarStyle {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.media.RatingCompat$Style */
    public @interface Style {
    }

    RatingCompat(int ratingStyle, float rating) {
        this.mRatingStyle = ratingStyle;
        this.mRatingValue = rating;
    }

    public String toString() {
        StringBuilder sb;
        String valueOf;
        new StringBuilder();
        StringBuilder append = sb.append("Rating:style=").append(this.mRatingStyle).append(" rating=");
        if (this.mRatingValue < 0.0f) {
            valueOf = "unrated";
        } else {
            valueOf = String.valueOf(this.mRatingValue);
        }
        return append.append(valueOf).toString();
    }

    public int describeContents() {
        return this.mRatingStyle;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Parcel dest = parcel;
        int i2 = i;
        dest.writeInt(this.mRatingStyle);
        dest.writeFloat(this.mRatingValue);
    }

    static {
        Parcelable.Creator<RatingCompat> creator;
        new Parcelable.Creator<RatingCompat>() {
            public RatingCompat createFromParcel(Parcel parcel) {
                RatingCompat ratingCompat;
                Parcel p = parcel;
                new RatingCompat(p.readInt(), p.readFloat());
                return ratingCompat;
            }

            public RatingCompat[] newArray(int size) {
                return new RatingCompat[size];
            }
        };
        CREATOR = creator;
    }

    public static RatingCompat newUnratedRating(int i) {
        RatingCompat ratingCompat;
        int ratingStyle = i;
        switch (ratingStyle) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                new RatingCompat(ratingStyle, -1.0f);
                return ratingCompat;
            default:
                return null;
        }
    }

    public static RatingCompat newHeartRating(boolean hasHeart) {
        RatingCompat ratingCompat = r5;
        RatingCompat ratingCompat2 = new RatingCompat(1, hasHeart ? 1.0f : 0.0f);
        return ratingCompat;
    }

    public static RatingCompat newThumbRating(boolean thumbIsUp) {
        RatingCompat ratingCompat = r5;
        RatingCompat ratingCompat2 = new RatingCompat(2, thumbIsUp ? 1.0f : 0.0f);
        return ratingCompat;
    }

    public static RatingCompat newStarRating(int i, float f) {
        float maxRating;
        RatingCompat ratingCompat;
        StringBuilder sb;
        int starRatingStyle = i;
        float starRating = f;
        switch (starRatingStyle) {
            case 3:
                maxRating = 3.0f;
                break;
            case 4:
                maxRating = 4.0f;
                break;
            case 5:
                maxRating = 5.0f;
                break;
            default:
                new StringBuilder();
                int e = Log.e(TAG, sb.append("Invalid rating style (").append(starRatingStyle).append(") for a star rating").toString());
                return null;
        }
        if (starRating < 0.0f || starRating > maxRating) {
            int e2 = Log.e(TAG, "Trying to set out of range star-based rating");
            return null;
        }
        new RatingCompat(starRatingStyle, starRating);
        return ratingCompat;
    }

    public static RatingCompat newPercentageRating(float f) {
        RatingCompat ratingCompat;
        float percent = f;
        if (percent < 0.0f || percent > 100.0f) {
            int e = Log.e(TAG, "Invalid percentage-based rating value");
            return null;
        }
        new RatingCompat(6, percent);
        return ratingCompat;
    }

    public boolean isRated() {
        return this.mRatingValue >= 0.0f;
    }

    public int getRatingStyle() {
        return this.mRatingStyle;
    }

    public boolean hasHeart() {
        if (this.mRatingStyle != 1) {
            return false;
        }
        return this.mRatingValue == 1.0f;
    }

    public boolean isThumbUp() {
        if (this.mRatingStyle != 2) {
            return false;
        }
        return this.mRatingValue == 1.0f;
    }

    public float getStarRating() {
        switch (this.mRatingStyle) {
            case 3:
            case 4:
            case 5:
                if (isRated()) {
                    return this.mRatingValue;
                }
                break;
        }
        return -1.0f;
    }

    public float getPercentRating() {
        if (this.mRatingStyle != 6 || !isRated()) {
            return -1.0f;
        }
        return this.mRatingValue;
    }

    public static RatingCompat fromRating(Object obj) {
        RatingCompat rating;
        Object ratingObj = obj;
        if (ratingObj == null || Build.VERSION.SDK_INT < 19) {
            return null;
        }
        int ratingStyle = ((Rating) ratingObj).getRatingStyle();
        if (((Rating) ratingObj).isRated()) {
            switch (ratingStyle) {
                case 1:
                    rating = newHeartRating(((Rating) ratingObj).hasHeart());
                    break;
                case 2:
                    rating = newThumbRating(((Rating) ratingObj).isThumbUp());
                    break;
                case 3:
                case 4:
                case 5:
                    rating = newStarRating(ratingStyle, ((Rating) ratingObj).getStarRating());
                    break;
                case 6:
                    rating = newPercentageRating(((Rating) ratingObj).getPercentRating());
                    break;
                default:
                    return null;
            }
        } else {
            rating = newUnratedRating(ratingStyle);
        }
        rating.mRatingObj = ratingObj;
        return rating;
    }

    public Object getRating() {
        if (this.mRatingObj == null && Build.VERSION.SDK_INT >= 19) {
            if (isRated()) {
                switch (this.mRatingStyle) {
                    case 1:
                        this.mRatingObj = Rating.newHeartRating(hasHeart());
                        break;
                    case 2:
                        this.mRatingObj = Rating.newThumbRating(isThumbUp());
                        break;
                    case 3:
                    case 4:
                    case 5:
                        this.mRatingObj = Rating.newStarRating(this.mRatingStyle, getStarRating());
                        break;
                    case 6:
                        this.mRatingObj = Rating.newPercentageRating(getPercentRating());
                        break;
                    default:
                        return null;
                }
            } else {
                this.mRatingObj = Rating.newUnratedRating(this.mRatingStyle);
            }
        }
        return this.mRatingObj;
    }
}
