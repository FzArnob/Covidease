package android.support.p003v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p003v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.widget.SpinnerAdapter;

/* renamed from: android.support.v7.widget.ThemedSpinnerAdapter */
public interface ThemedSpinnerAdapter extends SpinnerAdapter {
    @Nullable
    Resources.Theme getDropDownViewTheme();

    void setDropDownViewTheme(@Nullable Resources.Theme theme);

    /* renamed from: android.support.v7.widget.ThemedSpinnerAdapter$Helper */
    public static final class Helper {
        private final Context mContext;
        private LayoutInflater mDropDownInflater;
        private final LayoutInflater mInflater;

        public Helper(@NonNull Context context) {
            Context context2 = context;
            this.mContext = context2;
            this.mInflater = LayoutInflater.from(context2);
        }

        public void setDropDownViewTheme(@Nullable Resources.Theme theme) {
            Context context;
            Resources.Theme theme2 = theme;
            if (theme2 == null) {
                this.mDropDownInflater = null;
            } else if (theme2 == this.mContext.getTheme()) {
                this.mDropDownInflater = this.mInflater;
            } else {
                new ContextThemeWrapper(this.mContext, theme2);
                this.mDropDownInflater = LayoutInflater.from(context);
            }
        }

        @Nullable
        public Resources.Theme getDropDownViewTheme() {
            return this.mDropDownInflater == null ? null : this.mDropDownInflater.getContext().getTheme();
        }

        @NonNull
        public LayoutInflater getDropDownViewInflater() {
            return this.mDropDownInflater != null ? this.mDropDownInflater : this.mInflater;
        }
    }
}
