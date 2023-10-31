package android.support.p000v4.app;

import android.app.Person;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.p000v4.graphics.drawable.IconCompat;

/* renamed from: android.support.v4.app.Person */
public class Person {
    private static final String ICON_KEY = "icon";
    private static final String IS_BOT_KEY = "isBot";
    private static final String IS_IMPORTANT_KEY = "isImportant";
    private static final String KEY_KEY = "key";
    private static final String NAME_KEY = "name";
    private static final String URI_KEY = "uri";
    @Nullable
    IconCompat mIcon;
    boolean mIsBot;
    boolean mIsImportant;
    @Nullable
    String mKey;
    @Nullable
    CharSequence mName;
    @Nullable
    String mUri;

    @NonNull
    public static Person fromBundle(@NonNull Bundle bundle) {
        Builder builder;
        Bundle bundle2 = bundle;
        Bundle iconBundle = bundle2.getBundle(ICON_KEY);
        new Builder();
        return builder.setName(bundle2.getCharSequence(NAME_KEY)).setIcon(iconBundle != null ? IconCompat.createFromBundle(iconBundle) : null).setUri(bundle2.getString(URI_KEY)).setKey(bundle2.getString(KEY_KEY)).setBot(bundle2.getBoolean(IS_BOT_KEY)).setImportant(bundle2.getBoolean(IS_IMPORTANT_KEY)).build();
    }

    @RequiresApi(28)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @NonNull
    public static Person fromAndroidPerson(@NonNull android.app.Person person) {
        Builder builder;
        android.app.Person person2 = person;
        new Builder();
        return builder.setName(person2.getName()).setIcon(person2.getIcon() != null ? IconCompat.createFromIcon(person2.getIcon()) : null).setUri(person2.getUri()).setKey(person2.getKey()).setBot(person2.isBot()).setImportant(person2.isImportant()).build();
    }

    Person(Builder builder) {
        Builder builder2 = builder;
        this.mName = builder2.mName;
        this.mIcon = builder2.mIcon;
        this.mUri = builder2.mUri;
        this.mKey = builder2.mKey;
        this.mIsBot = builder2.mIsBot;
        this.mIsImportant = builder2.mIsImportant;
    }

    @NonNull
    public Bundle toBundle() {
        Bundle bundle;
        new Bundle();
        Bundle result = bundle;
        result.putCharSequence(NAME_KEY, this.mName);
        result.putBundle(ICON_KEY, this.mIcon != null ? this.mIcon.toBundle() : null);
        result.putString(URI_KEY, this.mUri);
        result.putString(KEY_KEY, this.mKey);
        result.putBoolean(IS_BOT_KEY, this.mIsBot);
        result.putBoolean(IS_IMPORTANT_KEY, this.mIsImportant);
        return result;
    }

    @NonNull
    public Builder toBuilder() {
        Builder builder;
        new Builder(this);
        return builder;
    }

    @RequiresApi(28)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @NonNull
    public android.app.Person toAndroidPerson() {
        Person.Builder builder;
        new Person.Builder();
        return builder.setName(getName()).setIcon(getIcon() != null ? getIcon().toIcon() : null).setUri(getUri()).setKey(getKey()).setBot(isBot()).setImportant(isImportant()).build();
    }

    @Nullable
    public CharSequence getName() {
        return this.mName;
    }

    @Nullable
    public IconCompat getIcon() {
        return this.mIcon;
    }

    @Nullable
    public String getUri() {
        return this.mUri;
    }

    @Nullable
    public String getKey() {
        return this.mKey;
    }

    public boolean isBot() {
        return this.mIsBot;
    }

    public boolean isImportant() {
        return this.mIsImportant;
    }

    /* renamed from: android.support.v4.app.Person$Builder */
    public static class Builder {
        @Nullable
        IconCompat mIcon;
        boolean mIsBot;
        boolean mIsImportant;
        @Nullable
        String mKey;
        @Nullable
        CharSequence mName;
        @Nullable
        String mUri;

        public Builder() {
        }

        Builder(Person person) {
            Person person2 = person;
            this.mName = person2.mName;
            this.mIcon = person2.mIcon;
            this.mUri = person2.mUri;
            this.mKey = person2.mKey;
            this.mIsBot = person2.mIsBot;
            this.mIsImportant = person2.mIsImportant;
        }

        @NonNull
        public Builder setName(@Nullable CharSequence name) {
            this.mName = name;
            return this;
        }

        @NonNull
        public Builder setIcon(@Nullable IconCompat icon) {
            this.mIcon = icon;
            return this;
        }

        @NonNull
        public Builder setUri(@Nullable String uri) {
            this.mUri = uri;
            return this;
        }

        @NonNull
        public Builder setKey(@Nullable String key) {
            this.mKey = key;
            return this;
        }

        @NonNull
        public Builder setBot(boolean bot) {
            this.mIsBot = bot;
            return this;
        }

        @NonNull
        public Builder setImportant(boolean important) {
            this.mIsImportant = important;
            return this;
        }

        @NonNull
        public Person build() {
            Person person;
            new Person(this);
            return person;
        }
    }
}
