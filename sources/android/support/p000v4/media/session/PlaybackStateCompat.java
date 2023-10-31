package android.support.p000v4.media.session;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.media.session.PlaybackStateCompatApi21;
import android.text.TextUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/* renamed from: android.support.v4.media.session.PlaybackStateCompat */
public final class PlaybackStateCompat implements Parcelable {
    public static final long ACTION_FAST_FORWARD = 64;
    public static final long ACTION_PAUSE = 2;
    public static final long ACTION_PLAY = 4;
    public static final long ACTION_PLAY_FROM_MEDIA_ID = 1024;
    public static final long ACTION_PLAY_FROM_SEARCH = 2048;
    public static final long ACTION_PLAY_FROM_URI = 8192;
    public static final long ACTION_PLAY_PAUSE = 512;
    public static final long ACTION_PREPARE = 16384;
    public static final long ACTION_PREPARE_FROM_MEDIA_ID = 32768;
    public static final long ACTION_PREPARE_FROM_SEARCH = 65536;
    public static final long ACTION_PREPARE_FROM_URI = 131072;
    public static final long ACTION_REWIND = 8;
    public static final long ACTION_SEEK_TO = 256;
    public static final long ACTION_SET_CAPTIONING_ENABLED = 1048576;
    public static final long ACTION_SET_RATING = 128;
    public static final long ACTION_SET_REPEAT_MODE = 262144;
    public static final long ACTION_SET_SHUFFLE_MODE = 2097152;
    @Deprecated
    public static final long ACTION_SET_SHUFFLE_MODE_ENABLED = 524288;
    public static final long ACTION_SKIP_TO_NEXT = 32;
    public static final long ACTION_SKIP_TO_PREVIOUS = 16;
    public static final long ACTION_SKIP_TO_QUEUE_ITEM = 4096;
    public static final long ACTION_STOP = 1;
    public static final Parcelable.Creator<PlaybackStateCompat> CREATOR;
    public static final int ERROR_CODE_ACTION_ABORTED = 10;
    public static final int ERROR_CODE_APP_ERROR = 1;
    public static final int ERROR_CODE_AUTHENTICATION_EXPIRED = 3;
    public static final int ERROR_CODE_CONCURRENT_STREAM_LIMIT = 5;
    public static final int ERROR_CODE_CONTENT_ALREADY_PLAYING = 8;
    public static final int ERROR_CODE_END_OF_QUEUE = 11;
    public static final int ERROR_CODE_NOT_AVAILABLE_IN_REGION = 7;
    public static final int ERROR_CODE_NOT_SUPPORTED = 2;
    public static final int ERROR_CODE_PARENTAL_CONTROL_RESTRICTED = 6;
    public static final int ERROR_CODE_PREMIUM_ACCOUNT_REQUIRED = 4;
    public static final int ERROR_CODE_SKIP_LIMIT_REACHED = 9;
    public static final int ERROR_CODE_UNKNOWN_ERROR = 0;
    private static final int KEYCODE_MEDIA_PAUSE = 127;
    private static final int KEYCODE_MEDIA_PLAY = 126;
    public static final long PLAYBACK_POSITION_UNKNOWN = -1;
    public static final int REPEAT_MODE_ALL = 2;
    public static final int REPEAT_MODE_GROUP = 3;
    public static final int REPEAT_MODE_INVALID = -1;
    public static final int REPEAT_MODE_NONE = 0;
    public static final int REPEAT_MODE_ONE = 1;
    public static final int SHUFFLE_MODE_ALL = 1;
    public static final int SHUFFLE_MODE_GROUP = 2;
    public static final int SHUFFLE_MODE_INVALID = -1;
    public static final int SHUFFLE_MODE_NONE = 0;
    public static final int STATE_BUFFERING = 6;
    public static final int STATE_CONNECTING = 8;
    public static final int STATE_ERROR = 7;
    public static final int STATE_FAST_FORWARDING = 4;
    public static final int STATE_NONE = 0;
    public static final int STATE_PAUSED = 2;
    public static final int STATE_PLAYING = 3;
    public static final int STATE_REWINDING = 5;
    public static final int STATE_SKIPPING_TO_NEXT = 10;
    public static final int STATE_SKIPPING_TO_PREVIOUS = 9;
    public static final int STATE_SKIPPING_TO_QUEUE_ITEM = 11;
    public static final int STATE_STOPPED = 1;
    final long mActions;
    final long mActiveItemId;
    final long mBufferedPosition;
    List<CustomAction> mCustomActions;
    final int mErrorCode;
    final CharSequence mErrorMessage;
    final Bundle mExtras;
    final long mPosition;
    final float mSpeed;
    final int mState;
    private Object mStateObj;
    final long mUpdateTime;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.media.session.PlaybackStateCompat$Actions */
    public @interface Actions {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.media.session.PlaybackStateCompat$ErrorCode */
    public @interface ErrorCode {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.media.session.PlaybackStateCompat$MediaKeyAction */
    public @interface MediaKeyAction {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.media.session.PlaybackStateCompat$RepeatMode */
    public @interface RepeatMode {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.media.session.PlaybackStateCompat$ShuffleMode */
    public @interface ShuffleMode {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.media.session.PlaybackStateCompat$State */
    public @interface State {
    }

    public static int toKeyCode(long j) {
        long action = j;
        if (action == 4) {
            return KEYCODE_MEDIA_PLAY;
        }
        if (action == 2) {
            return KEYCODE_MEDIA_PAUSE;
        }
        if (action == 32) {
            return 87;
        }
        if (action == 16) {
            return 88;
        }
        if (action == 1) {
            return 86;
        }
        if (action == 64) {
            return 90;
        }
        if (action == 8) {
            return 89;
        }
        if (action == 512) {
            return 85;
        }
        return 0;
    }

    PlaybackStateCompat(int state, long position, long bufferedPosition, float rate, long actions, int errorCode, CharSequence errorMessage, long updateTime, List<CustomAction> customActions, long activeItemId, Bundle extras) {
        List<CustomAction> list;
        this.mState = state;
        this.mPosition = position;
        this.mBufferedPosition = bufferedPosition;
        this.mSpeed = rate;
        this.mActions = actions;
        this.mErrorCode = errorCode;
        this.mErrorMessage = errorMessage;
        this.mUpdateTime = updateTime;
        new ArrayList(customActions);
        this.mCustomActions = list;
        this.mActiveItemId = activeItemId;
        this.mExtras = extras;
    }

    PlaybackStateCompat(Parcel parcel) {
        Parcel in = parcel;
        this.mState = in.readInt();
        this.mPosition = in.readLong();
        this.mSpeed = in.readFloat();
        this.mUpdateTime = in.readLong();
        this.mBufferedPosition = in.readLong();
        this.mActions = in.readLong();
        this.mErrorMessage = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        this.mCustomActions = in.createTypedArrayList(CustomAction.CREATOR);
        this.mActiveItemId = in.readLong();
        this.mExtras = in.readBundle(MediaSessionCompat.class.getClassLoader());
        this.mErrorCode = in.readInt();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder("PlaybackState {");
        StringBuilder bob = sb;
        StringBuilder append = bob.append("state=").append(this.mState);
        StringBuilder append2 = bob.append(", position=").append(this.mPosition);
        StringBuilder append3 = bob.append(", buffered position=").append(this.mBufferedPosition);
        StringBuilder append4 = bob.append(", speed=").append(this.mSpeed);
        StringBuilder append5 = bob.append(", updated=").append(this.mUpdateTime);
        StringBuilder append6 = bob.append(", actions=").append(this.mActions);
        StringBuilder append7 = bob.append(", error code=").append(this.mErrorCode);
        StringBuilder append8 = bob.append(", error message=").append(this.mErrorMessage);
        StringBuilder append9 = bob.append(", custom actions=").append(this.mCustomActions);
        StringBuilder append10 = bob.append(", active item id=").append(this.mActiveItemId);
        StringBuilder append11 = bob.append("}");
        return bob.toString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        Parcel dest = parcel;
        dest.writeInt(this.mState);
        dest.writeLong(this.mPosition);
        dest.writeFloat(this.mSpeed);
        dest.writeLong(this.mUpdateTime);
        dest.writeLong(this.mBufferedPosition);
        dest.writeLong(this.mActions);
        TextUtils.writeToParcel(this.mErrorMessage, dest, flags);
        dest.writeTypedList(this.mCustomActions);
        dest.writeLong(this.mActiveItemId);
        dest.writeBundle(this.mExtras);
        dest.writeInt(this.mErrorCode);
    }

    public int getState() {
        return this.mState;
    }

    public long getPosition() {
        return this.mPosition;
    }

    public long getLastPositionUpdateTime() {
        return this.mUpdateTime;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public long getCurrentPosition(Long l) {
        Long timeDiff = l;
        return Math.max(0, this.mPosition + ((long) (this.mSpeed * ((float) (timeDiff != null ? timeDiff.longValue() : SystemClock.elapsedRealtime() - this.mUpdateTime)))));
    }

    public long getBufferedPosition() {
        return this.mBufferedPosition;
    }

    public float getPlaybackSpeed() {
        return this.mSpeed;
    }

    public long getActions() {
        return this.mActions;
    }

    public List<CustomAction> getCustomActions() {
        return this.mCustomActions;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public CharSequence getErrorMessage() {
        return this.mErrorMessage;
    }

    public long getActiveQueueItemId() {
        return this.mActiveItemId;
    }

    @Nullable
    public Bundle getExtras() {
        return this.mExtras;
    }

    public static PlaybackStateCompat fromPlaybackState(Object obj) {
        Bundle extras;
        PlaybackStateCompat playbackStateCompat;
        List<CustomAction> list;
        Object stateObj = obj;
        if (stateObj == null || Build.VERSION.SDK_INT < 21) {
            return null;
        }
        List<Object> customActionObjs = PlaybackStateCompatApi21.getCustomActions(stateObj);
        List<CustomAction> customActions = null;
        if (customActionObjs != null) {
            new ArrayList<>(customActionObjs.size());
            customActions = list;
            for (Object customActionObj : customActionObjs) {
                boolean add = customActions.add(CustomAction.fromCustomAction(customActionObj));
            }
        }
        if (Build.VERSION.SDK_INT >= 22) {
            extras = PlaybackStateCompatApi22.getExtras(stateObj);
        } else {
            extras = null;
        }
        new PlaybackStateCompat(PlaybackStateCompatApi21.getState(stateObj), PlaybackStateCompatApi21.getPosition(stateObj), PlaybackStateCompatApi21.getBufferedPosition(stateObj), PlaybackStateCompatApi21.getPlaybackSpeed(stateObj), PlaybackStateCompatApi21.getActions(stateObj), 0, PlaybackStateCompatApi21.getErrorMessage(stateObj), PlaybackStateCompatApi21.getLastPositionUpdateTime(stateObj), customActions, PlaybackStateCompatApi21.getActiveQueueItemId(stateObj), extras);
        PlaybackStateCompat state = playbackStateCompat;
        state.mStateObj = stateObj;
        return state;
    }

    public Object getPlaybackState() {
        List<Object> list;
        if (this.mStateObj == null && Build.VERSION.SDK_INT >= 21) {
            List<Object> customActions = null;
            if (this.mCustomActions != null) {
                new ArrayList<>(this.mCustomActions.size());
                customActions = list;
                for (CustomAction customAction : this.mCustomActions) {
                    boolean add = customActions.add(customAction.getCustomAction());
                }
            }
            if (Build.VERSION.SDK_INT >= 22) {
                this.mStateObj = PlaybackStateCompatApi22.newInstance(this.mState, this.mPosition, this.mBufferedPosition, this.mSpeed, this.mActions, this.mErrorMessage, this.mUpdateTime, customActions, this.mActiveItemId, this.mExtras);
            } else {
                this.mStateObj = PlaybackStateCompatApi21.newInstance(this.mState, this.mPosition, this.mBufferedPosition, this.mSpeed, this.mActions, this.mErrorMessage, this.mUpdateTime, customActions, this.mActiveItemId);
            }
        }
        return this.mStateObj;
    }

    static {
        Parcelable.Creator<PlaybackStateCompat> creator;
        new Parcelable.Creator<PlaybackStateCompat>() {
            public PlaybackStateCompat createFromParcel(Parcel in) {
                PlaybackStateCompat playbackStateCompat;
                new PlaybackStateCompat(in);
                return playbackStateCompat;
            }

            public PlaybackStateCompat[] newArray(int size) {
                return new PlaybackStateCompat[size];
            }
        };
        CREATOR = creator;
    }

    /* renamed from: android.support.v4.media.session.PlaybackStateCompat$CustomAction */
    public static final class CustomAction implements Parcelable {
        public static final Parcelable.Creator<CustomAction> CREATOR;
        private final String mAction;
        private Object mCustomActionObj;
        private final Bundle mExtras;
        private final int mIcon;
        private final CharSequence mName;

        CustomAction(String action, CharSequence name, int icon, Bundle extras) {
            this.mAction = action;
            this.mName = name;
            this.mIcon = icon;
            this.mExtras = extras;
        }

        CustomAction(Parcel parcel) {
            Parcel in = parcel;
            this.mAction = in.readString();
            this.mName = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
            this.mIcon = in.readInt();
            this.mExtras = in.readBundle(MediaSessionCompat.class.getClassLoader());
        }

        public void writeToParcel(Parcel parcel, int flags) {
            Parcel dest = parcel;
            dest.writeString(this.mAction);
            TextUtils.writeToParcel(this.mName, dest, flags);
            dest.writeInt(this.mIcon);
            dest.writeBundle(this.mExtras);
        }

        public int describeContents() {
            return 0;
        }

        public static CustomAction fromCustomAction(Object obj) {
            CustomAction customAction;
            Object customActionObj = obj;
            if (customActionObj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            new CustomAction(PlaybackStateCompatApi21.CustomAction.getAction(customActionObj), PlaybackStateCompatApi21.CustomAction.getName(customActionObj), PlaybackStateCompatApi21.CustomAction.getIcon(customActionObj), PlaybackStateCompatApi21.CustomAction.getExtras(customActionObj));
            CustomAction customAction2 = customAction;
            customAction2.mCustomActionObj = customActionObj;
            return customAction2;
        }

        public Object getCustomAction() {
            if (this.mCustomActionObj != null || Build.VERSION.SDK_INT < 21) {
                return this.mCustomActionObj;
            }
            this.mCustomActionObj = PlaybackStateCompatApi21.CustomAction.newInstance(this.mAction, this.mName, this.mIcon, this.mExtras);
            return this.mCustomActionObj;
        }

        static {
            Parcelable.Creator<CustomAction> creator;
            new Parcelable.Creator<CustomAction>() {
                public CustomAction createFromParcel(Parcel p) {
                    CustomAction customAction;
                    new CustomAction(p);
                    return customAction;
                }

                public CustomAction[] newArray(int size) {
                    return new CustomAction[size];
                }
            };
            CREATOR = creator;
        }

        public String getAction() {
            return this.mAction;
        }

        public CharSequence getName() {
            return this.mName;
        }

        public int getIcon() {
            return this.mIcon;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder();
            return sb.append("Action:mName='").append(this.mName).append(", mIcon=").append(this.mIcon).append(", mExtras=").append(this.mExtras).toString();
        }

        /* renamed from: android.support.v4.media.session.PlaybackStateCompat$CustomAction$Builder */
        public static final class Builder {
            private final String mAction;
            private Bundle mExtras;
            private final int mIcon;
            private final CharSequence mName;

            public Builder(String str, CharSequence charSequence, int i) {
                Throwable th;
                Throwable th2;
                Throwable th3;
                String action = str;
                CharSequence name = charSequence;
                int icon = i;
                if (TextUtils.isEmpty(action)) {
                    Throwable th4 = th3;
                    new IllegalArgumentException("You must specify an action to build a CustomAction.");
                    throw th4;
                } else if (TextUtils.isEmpty(name)) {
                    Throwable th5 = th2;
                    new IllegalArgumentException("You must specify a name to build a CustomAction.");
                    throw th5;
                } else if (icon == 0) {
                    Throwable th6 = th;
                    new IllegalArgumentException("You must specify an icon resource id to build a CustomAction.");
                    throw th6;
                } else {
                    this.mAction = action;
                    this.mName = name;
                    this.mIcon = icon;
                }
            }

            public Builder setExtras(Bundle extras) {
                this.mExtras = extras;
                return this;
            }

            public CustomAction build() {
                CustomAction customAction;
                new CustomAction(this.mAction, this.mName, this.mIcon, this.mExtras);
                return customAction;
            }
        }
    }

    /* renamed from: android.support.v4.media.session.PlaybackStateCompat$Builder */
    public static final class Builder {
        private long mActions;
        private long mActiveItemId = -1;
        private long mBufferedPosition;
        private final List<CustomAction> mCustomActions;
        private int mErrorCode;
        private CharSequence mErrorMessage;
        private Bundle mExtras;
        private long mPosition;
        private float mRate;
        private int mState;
        private long mUpdateTime;

        public Builder() {
            List<CustomAction> list;
            new ArrayList();
            this.mCustomActions = list;
        }

        public Builder(PlaybackStateCompat playbackStateCompat) {
            List<CustomAction> list;
            PlaybackStateCompat source = playbackStateCompat;
            new ArrayList();
            this.mCustomActions = list;
            this.mState = source.mState;
            this.mPosition = source.mPosition;
            this.mRate = source.mSpeed;
            this.mUpdateTime = source.mUpdateTime;
            this.mBufferedPosition = source.mBufferedPosition;
            this.mActions = source.mActions;
            this.mErrorCode = source.mErrorCode;
            this.mErrorMessage = source.mErrorMessage;
            if (source.mCustomActions != null) {
                boolean addAll = this.mCustomActions.addAll(source.mCustomActions);
            }
            this.mActiveItemId = source.mActiveItemId;
            this.mExtras = source.mExtras;
        }

        public Builder setState(int state, long position, float playbackSpeed) {
            return setState(state, position, playbackSpeed, SystemClock.elapsedRealtime());
        }

        public Builder setState(int state, long position, float playbackSpeed, long updateTime) {
            this.mState = state;
            this.mPosition = position;
            this.mUpdateTime = updateTime;
            this.mRate = playbackSpeed;
            return this;
        }

        public Builder setBufferedPosition(long bufferPosition) {
            this.mBufferedPosition = bufferPosition;
            return this;
        }

        public Builder setActions(long capabilities) {
            this.mActions = capabilities;
            return this;
        }

        public Builder addCustomAction(String action, String name, int icon) {
            CustomAction customAction;
            new CustomAction(action, name, icon, (Bundle) null);
            return addCustomAction(customAction);
        }

        public Builder addCustomAction(CustomAction customAction) {
            Throwable th;
            CustomAction customAction2 = customAction;
            if (customAction2 == null) {
                Throwable th2 = th;
                new IllegalArgumentException("You may not add a null CustomAction to PlaybackStateCompat.");
                throw th2;
            }
            boolean add = this.mCustomActions.add(customAction2);
            return this;
        }

        public Builder setActiveQueueItemId(long id) {
            this.mActiveItemId = id;
            return this;
        }

        public Builder setErrorMessage(CharSequence errorMessage) {
            this.mErrorMessage = errorMessage;
            return this;
        }

        public Builder setErrorMessage(int errorCode, CharSequence errorMessage) {
            this.mErrorCode = errorCode;
            this.mErrorMessage = errorMessage;
            return this;
        }

        public Builder setExtras(Bundle extras) {
            this.mExtras = extras;
            return this;
        }

        public PlaybackStateCompat build() {
            PlaybackStateCompat playbackStateCompat;
            new PlaybackStateCompat(this.mState, this.mPosition, this.mBufferedPosition, this.mRate, this.mActions, this.mErrorCode, this.mErrorMessage, this.mUpdateTime, this.mCustomActions, this.mActiveItemId, this.mExtras);
            return playbackStateCompat;
        }
    }
}
