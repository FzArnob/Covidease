package android.support.p003v7.widget;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.widget.ResourceCursorAdapter;
import android.support.p003v7.appcompat.C0425R;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

/* renamed from: android.support.v7.widget.SuggestionsAdapter */
class SuggestionsAdapter extends ResourceCursorAdapter implements View.OnClickListener {
    private static final boolean DBG = false;
    static final int INVALID_INDEX = -1;
    private static final String LOG_TAG = "SuggestionsAdapter";
    private static final int QUERY_LIMIT = 50;
    static final int REFINE_ALL = 2;
    static final int REFINE_BY_ENTRY = 1;
    static final int REFINE_NONE = 0;
    private boolean mClosed = false;
    private final int mCommitIconResId;
    private int mFlagsCol = -1;
    private int mIconName1Col = -1;
    private int mIconName2Col = -1;
    private final WeakHashMap<String, Drawable.ConstantState> mOutsideDrawablesCache;
    private final Context mProviderContext;
    private int mQueryRefinement = 1;
    private final SearchManager mSearchManager = ((SearchManager) this.mContext.getSystemService("search"));
    private final SearchView mSearchView;
    private final SearchableInfo mSearchable;
    private int mText1Col = -1;
    private int mText2Col = -1;
    private int mText2UrlCol = -1;
    private ColorStateList mUrlColor;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SuggestionsAdapter(android.content.Context r11, android.support.p003v7.widget.SearchView r12, android.app.SearchableInfo r13, java.util.WeakHashMap<java.lang.String, android.graphics.drawable.Drawable.ConstantState> r14) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r14
            r5 = r0
            r6 = r1
            r7 = r2
            int r7 = r7.getSuggestionRowLayout()
            r8 = 0
            r9 = 1
            r5.<init>((android.content.Context) r6, (int) r7, (android.database.Cursor) r8, (boolean) r9)
            r5 = r0
            r6 = 0
            r5.mClosed = r6
            r5 = r0
            r6 = 1
            r5.mQueryRefinement = r6
            r5 = r0
            r6 = -1
            r5.mText1Col = r6
            r5 = r0
            r6 = -1
            r5.mText2Col = r6
            r5 = r0
            r6 = -1
            r5.mText2UrlCol = r6
            r5 = r0
            r6 = -1
            r5.mIconName1Col = r6
            r5 = r0
            r6 = -1
            r5.mIconName2Col = r6
            r5 = r0
            r6 = -1
            r5.mFlagsCol = r6
            r5 = r0
            r6 = r0
            android.content.Context r6 = r6.mContext
            java.lang.String r7 = "search"
            java.lang.Object r6 = r6.getSystemService(r7)
            android.app.SearchManager r6 = (android.app.SearchManager) r6
            r5.mSearchManager = r6
            r5 = r0
            r6 = r2
            r5.mSearchView = r6
            r5 = r0
            r6 = r3
            r5.mSearchable = r6
            r5 = r0
            r6 = r2
            int r6 = r6.getSuggestionCommitIconResId()
            r5.mCommitIconResId = r6
            r5 = r0
            r6 = r1
            r5.mProviderContext = r6
            r5 = r0
            r6 = r4
            r5.mOutsideDrawablesCache = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.SuggestionsAdapter.<init>(android.content.Context, android.support.v7.widget.SearchView, android.app.SearchableInfo, java.util.WeakHashMap):void");
    }

    public void setQueryRefinement(int refineWhat) {
        int i = refineWhat;
        this.mQueryRefinement = i;
    }

    public int getQueryRefinement() {
        return this.mQueryRefinement;
    }

    public boolean hasStableIds() {
        return false;
    }

    public Cursor runQueryOnBackgroundThread(CharSequence charSequence) {
        String charSequence2;
        CharSequence constraint = charSequence;
        if (constraint == null) {
            charSequence2 = "";
        } else {
            charSequence2 = constraint.toString();
        }
        String query = charSequence2;
        if (this.mSearchView.getVisibility() != 0 || this.mSearchView.getWindowVisibility() != 0) {
            return null;
        }
        try {
            Cursor cursor = getSearchManagerSuggestions(this.mSearchable, query, 50);
            if (cursor != null) {
                int count = cursor.getCount();
                return cursor;
            }
        } catch (RuntimeException e) {
            int w = Log.w(LOG_TAG, "Search suggestions query threw an exception.", e);
        }
        return null;
    }

    public void close() {
        changeCursor((Cursor) null);
        this.mClosed = true;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        updateSpinnerState(getCursor());
    }

    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        updateSpinnerState(getCursor());
    }

    private void updateSpinnerState(Cursor cursor) {
        Cursor cursor2 = cursor;
        Bundle extras = cursor2 != null ? cursor2.getExtras() : null;
        if (extras == null || extras.getBoolean("in_progress")) {
        }
    }

    public void changeCursor(Cursor cursor) {
        Cursor c = cursor;
        if (this.mClosed) {
            int w = Log.w(LOG_TAG, "Tried to change cursor after adapter was closed.");
            if (c != null) {
                c.close();
                return;
            }
            return;
        }
        try {
            super.changeCursor(c);
            if (c != null) {
                this.mText1Col = c.getColumnIndex("suggest_text_1");
                this.mText2Col = c.getColumnIndex("suggest_text_2");
                this.mText2UrlCol = c.getColumnIndex("suggest_text_2_url");
                this.mIconName1Col = c.getColumnIndex("suggest_icon_1");
                this.mIconName2Col = c.getColumnIndex("suggest_icon_2");
                this.mFlagsCol = c.getColumnIndex("suggest_flags");
            }
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, "error changing cursor and caching columns", e);
        }
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        Object obj;
        View v = super.newView(context, cursor, parent);
        new ChildViewCache(v);
        v.setTag(obj);
        ((ImageView) v.findViewById(C0425R.C0427id.edit_query)).setImageResource(this.mCommitIconResId);
        return v;
    }

    /* renamed from: android.support.v7.widget.SuggestionsAdapter$ChildViewCache */
    private static final class ChildViewCache {
        public final ImageView mIcon1;
        public final ImageView mIcon2;
        public final ImageView mIconRefine;
        public final TextView mText1;
        public final TextView mText2;

        public ChildViewCache(View view) {
            View v = view;
            this.mText1 = (TextView) v.findViewById(16908308);
            this.mText2 = (TextView) v.findViewById(16908309);
            this.mIcon1 = (ImageView) v.findViewById(16908295);
            this.mIcon2 = (ImageView) v.findViewById(16908296);
            this.mIconRefine = (ImageView) v.findViewById(C0425R.C0427id.edit_query);
        }
    }

    public void bindView(View view, Context context, Cursor cursor) {
        CharSequence text2;
        Context context2 = context;
        Cursor cursor2 = cursor;
        ChildViewCache views = (ChildViewCache) view.getTag();
        int flags = 0;
        if (this.mFlagsCol != -1) {
            flags = cursor2.getInt(this.mFlagsCol);
        }
        if (views.mText1 != null) {
            setViewText(views.mText1, getStringOrNull(cursor2, this.mText1Col));
        }
        if (views.mText2 != null) {
            CharSequence text22 = getStringOrNull(cursor2, this.mText2UrlCol);
            if (text22 != null) {
                text2 = formatUrl(text22);
            } else {
                text2 = getStringOrNull(cursor2, this.mText2Col);
            }
            if (TextUtils.isEmpty(text2)) {
                if (views.mText1 != null) {
                    views.mText1.setSingleLine(false);
                    views.mText1.setMaxLines(2);
                }
            } else if (views.mText1 != null) {
                views.mText1.setSingleLine(true);
                views.mText1.setMaxLines(1);
            }
            setViewText(views.mText2, text2);
        }
        if (views.mIcon1 != null) {
            setViewDrawable(views.mIcon1, getIcon1(cursor2), 4);
        }
        if (views.mIcon2 != null) {
            setViewDrawable(views.mIcon2, getIcon2(cursor2), 8);
        }
        if (this.mQueryRefinement == 2 || (this.mQueryRefinement == 1 && (flags & 1) != 0)) {
            views.mIconRefine.setVisibility(0);
            views.mIconRefine.setTag(views.mText1.getText());
            views.mIconRefine.setOnClickListener(this);
            return;
        }
        views.mIconRefine.setVisibility(8);
    }

    public void onClick(View v) {
        Object tag = v.getTag();
        if (tag instanceof CharSequence) {
            this.mSearchView.onQueryRefine((CharSequence) tag);
        }
    }

    private CharSequence formatUrl(CharSequence charSequence) {
        SpannableString spannableString;
        Object obj;
        TypedValue typedValue;
        CharSequence url = charSequence;
        if (this.mUrlColor == null) {
            new TypedValue();
            TypedValue colorValue = typedValue;
            boolean resolveAttribute = this.mContext.getTheme().resolveAttribute(C0425R.attr.textColorSearchUrl, colorValue, true);
            this.mUrlColor = this.mContext.getResources().getColorStateList(colorValue.resourceId);
        }
        new SpannableString(url);
        SpannableString text = spannableString;
        new TextAppearanceSpan((String) null, 0, 0, this.mUrlColor, (ColorStateList) null);
        text.setSpan(obj, 0, url.length(), 33);
        return text;
    }

    private void setViewText(TextView textView, CharSequence charSequence) {
        TextView v = textView;
        CharSequence text = charSequence;
        v.setText(text);
        if (TextUtils.isEmpty(text)) {
            v.setVisibility(8);
        } else {
            v.setVisibility(0);
        }
    }

    private Drawable getIcon1(Cursor cursor) {
        Cursor cursor2 = cursor;
        if (this.mIconName1Col == -1) {
            return null;
        }
        Drawable drawable = getDrawableFromResourceValue(cursor2.getString(this.mIconName1Col));
        if (drawable != null) {
            return drawable;
        }
        return getDefaultIcon1(cursor2);
    }

    private Drawable getIcon2(Cursor cursor) {
        Cursor cursor2 = cursor;
        if (this.mIconName2Col == -1) {
            return null;
        }
        return getDrawableFromResourceValue(cursor2.getString(this.mIconName2Col));
    }

    private void setViewDrawable(ImageView imageView, Drawable drawable, int i) {
        ImageView v = imageView;
        Drawable drawable2 = drawable;
        int nullVisibility = i;
        v.setImageDrawable(drawable2);
        if (drawable2 == null) {
            v.setVisibility(nullVisibility);
            return;
        }
        v.setVisibility(0);
        boolean visible = drawable2.setVisible(false, false);
        boolean visible2 = drawable2.setVisible(true, false);
    }

    public CharSequence convertToString(Cursor cursor) {
        String text1;
        String data;
        Cursor cursor2 = cursor;
        if (cursor2 == null) {
            return null;
        }
        String query = getColumnString(cursor2, "suggest_intent_query");
        if (query != null) {
            return query;
        }
        if (this.mSearchable.shouldRewriteQueryFromData() && (data = getColumnString(cursor2, "suggest_intent_data")) != null) {
            return data;
        }
        if (!this.mSearchable.shouldRewriteQueryFromText() || (text1 = getColumnString(cursor2, "suggest_text_1")) == null) {
            return null;
        }
        return text1;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewGroup parent = viewGroup;
        try {
            return super.getView(position, convertView, parent);
        } catch (RuntimeException e) {
            RuntimeException e2 = e;
            int w = Log.w(LOG_TAG, "Search suggestions cursor threw exception.", e2);
            View v = newView(this.mContext, this.mCursor, parent);
            if (v != null) {
                ((ChildViewCache) v.getTag()).mText1.setText(e2.toString());
            }
            return v;
        }
    }

    public View getDropDownView(int position, View convertView, ViewGroup viewGroup) {
        ViewGroup parent = viewGroup;
        try {
            return super.getDropDownView(position, convertView, parent);
        } catch (RuntimeException e) {
            RuntimeException e2 = e;
            int w = Log.w(LOG_TAG, "Search suggestions cursor threw exception.", e2);
            View v = newDropDownView(this.mContext, this.mCursor, parent);
            if (v != null) {
                ((ChildViewCache) v.getTag()).mText1.setText(e2.toString());
            }
            return v;
        }
    }

    private Drawable getDrawableFromResourceValue(String str) {
        StringBuilder sb;
        StringBuilder sb2;
        String drawableId = str;
        if (drawableId == null || drawableId.isEmpty() || "0".equals(drawableId)) {
            return null;
        }
        try {
            int resourceId = Integer.parseInt(drawableId);
            new StringBuilder();
            String drawableUri = sb2.append("android.resource://").append(this.mProviderContext.getPackageName()).append("/").append(resourceId).toString();
            Drawable drawable = checkIconCache(drawableUri);
            if (drawable != null) {
                return drawable;
            }
            Drawable drawable2 = ContextCompat.getDrawable(this.mProviderContext, resourceId);
            storeInIconCache(drawableUri, drawable2);
            return drawable2;
        } catch (NumberFormatException e) {
            NumberFormatException numberFormatException = e;
            Drawable drawable3 = checkIconCache(drawableId);
            if (drawable3 != null) {
                return drawable3;
            }
            Drawable drawable4 = getDrawable(Uri.parse(drawableId));
            storeInIconCache(drawableId, drawable4);
            return drawable4;
        } catch (Resources.NotFoundException e2) {
            Resources.NotFoundException notFoundException = e2;
            new StringBuilder();
            int w = Log.w(LOG_TAG, sb.append("Icon resource not found: ").append(drawableId).toString());
            return null;
        }
    }

    private Drawable getDrawable(Uri uri) {
        StringBuilder sb;
        InputStream stream;
        StringBuilder sb2;
        StringBuilder sb3;
        Throwable th;
        StringBuilder sb4;
        Throwable th2;
        StringBuilder sb5;
        Uri uri2 = uri;
        try {
            if ("android.resource".equals(uri2.getScheme())) {
                return getDrawableFromResourceUri(uri2);
            }
            stream = this.mProviderContext.getContentResolver().openInputStream(uri2);
            if (stream == null) {
                Throwable th3 = th;
                new StringBuilder();
                new FileNotFoundException(sb4.append("Failed to open ").append(uri2).toString());
                throw th3;
            }
            Drawable createFromStream = Drawable.createFromStream(stream, (String) null);
            try {
                stream.close();
            } catch (IOException e) {
                IOException ex = e;
                new StringBuilder();
                int e2 = Log.e(LOG_TAG, sb3.append("Error closing icon stream for ").append(uri2).toString(), ex);
            }
            return createFromStream;
        } catch (Resources.NotFoundException e3) {
            Resources.NotFoundException notFoundException = e3;
            Throwable th4 = th2;
            new StringBuilder();
            new FileNotFoundException(sb5.append("Resource does not exist: ").append(uri2).toString());
            throw th4;
        } catch (FileNotFoundException e4) {
            new StringBuilder();
            int w = Log.w(LOG_TAG, sb.append("Icon not found: ").append(uri2).append(", ").append(e4.getMessage()).toString());
            return null;
        } catch (Throwable th5) {
            Throwable th6 = th5;
            try {
                stream.close();
            } catch (IOException e5) {
                new StringBuilder();
                int e6 = Log.e(LOG_TAG, sb2.append("Error closing icon stream for ").append(uri2).toString(), e5);
            }
            throw th6;
        }
    }

    private Drawable checkIconCache(String resourceUri) {
        Drawable.ConstantState cached = this.mOutsideDrawablesCache.get(resourceUri);
        if (cached == null) {
            return null;
        }
        return cached.newDrawable();
    }

    private void storeInIconCache(String str, Drawable drawable) {
        String resourceUri = str;
        Drawable drawable2 = drawable;
        if (drawable2 != null) {
            Drawable.ConstantState put = this.mOutsideDrawablesCache.put(resourceUri, drawable2.getConstantState());
        }
    }

    private Drawable getDefaultIcon1(Cursor cursor) {
        Cursor cursor2 = cursor;
        Drawable drawable = getActivityIconWithCache(this.mSearchable.getSearchActivity());
        if (drawable != null) {
            return drawable;
        }
        return this.mContext.getPackageManager().getDefaultActivityIcon();
    }

    private Drawable getActivityIconWithCache(ComponentName componentName) {
        ComponentName component = componentName;
        String componentIconKey = component.flattenToShortString();
        if (this.mOutsideDrawablesCache.containsKey(componentIconKey)) {
            Drawable.ConstantState cached = this.mOutsideDrawablesCache.get(componentIconKey);
            return cached == null ? null : cached.newDrawable(this.mProviderContext.getResources());
        }
        Drawable drawable = getActivityIcon(component);
        Drawable.ConstantState put = this.mOutsideDrawablesCache.put(componentIconKey, drawable == null ? null : drawable.getConstantState());
        return drawable;
    }

    private Drawable getActivityIcon(ComponentName componentName) {
        StringBuilder sb;
        ComponentName component = componentName;
        PackageManager pm = this.mContext.getPackageManager();
        try {
            ActivityInfo activityInfo = pm.getActivityInfo(component, 128);
            int iconId = activityInfo.getIconResource();
            if (iconId == 0) {
                return null;
            }
            Drawable drawable = pm.getDrawable(component.getPackageName(), iconId, activityInfo.applicationInfo);
            if (drawable != null) {
                return drawable;
            }
            new StringBuilder();
            int w = Log.w(LOG_TAG, sb.append("Invalid icon resource ").append(iconId).append(" for ").append(component.flattenToShortString()).toString());
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            int w2 = Log.w(LOG_TAG, e.toString());
            return null;
        }
    }

    public static String getColumnString(Cursor cursor, String columnName) {
        Cursor cursor2 = cursor;
        return getStringOrNull(cursor2, cursor2.getColumnIndex(columnName));
    }

    private static String getStringOrNull(Cursor cursor, int i) {
        Cursor cursor2 = cursor;
        int col = i;
        if (col == -1) {
            return null;
        }
        try {
            return cursor2.getString(col);
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, "unexpected error retrieving valid column from cursor, did the remote process die?", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public Drawable getDrawableFromResourceUri(Uri uri) throws FileNotFoundException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        int id;
        Throwable th3;
        StringBuilder sb3;
        Throwable th4;
        StringBuilder sb4;
        Throwable th5;
        StringBuilder sb5;
        Throwable th6;
        StringBuilder sb6;
        Uri uri2 = uri;
        String authority = uri2.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            Throwable th7 = th6;
            new StringBuilder();
            new FileNotFoundException(sb6.append("No authority: ").append(uri2).toString());
            throw th7;
        }
        try {
            Resources r = this.mContext.getPackageManager().getResourcesForApplication(authority);
            List<String> path = uri2.getPathSegments();
            if (path == null) {
                Throwable th8 = th5;
                new StringBuilder();
                new FileNotFoundException(sb5.append("No path: ").append(uri2).toString());
                throw th8;
            }
            int len = path.size();
            if (len == 1) {
                try {
                    id = Integer.parseInt(path.get(0));
                } catch (NumberFormatException e) {
                    NumberFormatException numberFormatException = e;
                    Throwable th9 = th4;
                    new StringBuilder();
                    new FileNotFoundException(sb4.append("Single path segment is not a resource ID: ").append(uri2).toString());
                    throw th9;
                }
            } else if (len == 2) {
                id = r.getIdentifier(path.get(1), path.get(0), authority);
            } else {
                Throwable th10 = th2;
                new StringBuilder();
                new FileNotFoundException(sb2.append("More than two path segments: ").append(uri2).toString());
                throw th10;
            }
            if (id != 0) {
                return r.getDrawable(id);
            }
            Throwable th11 = th3;
            new StringBuilder();
            new FileNotFoundException(sb3.append("No resource found for: ").append(uri2).toString());
            throw th11;
        } catch (PackageManager.NameNotFoundException e2) {
            PackageManager.NameNotFoundException nameNotFoundException = e2;
            Throwable th12 = th;
            new StringBuilder();
            new FileNotFoundException(sb.append("No package found for authority: ").append(uri2).toString());
            throw th12;
        }
    }

    /* access modifiers changed from: package-private */
    public Cursor getSearchManagerSuggestions(SearchableInfo searchableInfo, String str, int i) {
        Uri.Builder builder;
        SearchableInfo searchable = searchableInfo;
        String query = str;
        int limit = i;
        if (searchable == null) {
            return null;
        }
        String authority = searchable.getSuggestAuthority();
        if (authority == null) {
            return null;
        }
        new Uri.Builder();
        Uri.Builder uriBuilder = builder.scheme("content").authority(authority).query("").fragment("");
        String contentPath = searchable.getSuggestPath();
        if (contentPath != null) {
            Uri.Builder appendEncodedPath = uriBuilder.appendEncodedPath(contentPath);
        }
        Uri.Builder appendPath = uriBuilder.appendPath("search_suggest_query");
        String selection = searchable.getSuggestSelection();
        String[] selArgs = null;
        if (selection != null) {
            selArgs = new String[]{query};
        } else {
            Uri.Builder appendPath2 = uriBuilder.appendPath(query);
        }
        if (limit > 0) {
            Uri.Builder appendQueryParameter = uriBuilder.appendQueryParameter("limit", String.valueOf(limit));
        }
        return this.mContext.getContentResolver().query(uriBuilder.build(), (String[]) null, selection, selArgs, (String) null);
    }
}
