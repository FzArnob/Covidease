package android.support.p000v4.content;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

@Deprecated
/* renamed from: android.support.v4.content.SharedPreferencesCompat */
public final class SharedPreferencesCompat {

    @Deprecated
    /* renamed from: android.support.v4.content.SharedPreferencesCompat$EditorCompat */
    public static final class EditorCompat {
        private static EditorCompat sInstance;
        private final Helper mHelper;

        /* renamed from: android.support.v4.content.SharedPreferencesCompat$EditorCompat$Helper */
        private static class Helper {
            Helper() {
            }

            public void apply(@NonNull SharedPreferences.Editor editor) {
                SharedPreferences.Editor editor2 = editor;
                try {
                    editor2.apply();
                } catch (AbstractMethodError e) {
                    AbstractMethodError abstractMethodError = e;
                    boolean commit = editor2.commit();
                }
            }
        }

        private EditorCompat() {
            Helper helper;
            new Helper();
            this.mHelper = helper;
        }

        @Deprecated
        public static EditorCompat getInstance() {
            EditorCompat editorCompat;
            if (sInstance == null) {
                new EditorCompat();
                sInstance = editorCompat;
            }
            return sInstance;
        }

        @Deprecated
        public void apply(@NonNull SharedPreferences.Editor editor) {
            this.mHelper.apply(editor);
        }
    }

    private SharedPreferencesCompat() {
    }
}
