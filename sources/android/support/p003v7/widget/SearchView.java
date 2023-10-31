package android.support.p003v7.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.view.AbsSavedState;
import android.support.p000v4.widget.CursorAdapter;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.view.CollapsibleActionView;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.actions.SearchIntents;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import gnu.expr.Declaration;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* renamed from: android.support.v7.widget.SearchView */
public class SearchView extends LinearLayoutCompat implements CollapsibleActionView {
    static final boolean DBG = false;
    static final AutoCompleteTextViewReflector HIDDEN_METHOD_INVOKER;
    private static final String IME_OPTION_NO_MICROPHONE = "nm";
    static final String LOG_TAG = "SearchView";
    private Bundle mAppSearchData;
    private boolean mClearingFocus;
    final ImageView mCloseButton;
    private final ImageView mCollapsedIcon;
    private int mCollapsedImeOptions;
    private final CharSequence mDefaultQueryHint;
    private final View mDropDownAnchor;
    private boolean mExpandedInActionView;
    final ImageView mGoButton;
    private boolean mIconified;
    private boolean mIconifiedByDefault;
    private int mMaxWidth;
    private CharSequence mOldQueryText;
    private final View.OnClickListener mOnClickListener;
    private OnCloseListener mOnCloseListener;
    private final TextView.OnEditorActionListener mOnEditorActionListener;
    private final AdapterView.OnItemClickListener mOnItemClickListener;
    private final AdapterView.OnItemSelectedListener mOnItemSelectedListener;
    private OnQueryTextListener mOnQueryChangeListener;
    View.OnFocusChangeListener mOnQueryTextFocusChangeListener;
    private View.OnClickListener mOnSearchClickListener;
    private OnSuggestionListener mOnSuggestionListener;
    private final WeakHashMap<String, Drawable.ConstantState> mOutsideDrawablesCache;
    private CharSequence mQueryHint;
    private boolean mQueryRefinement;
    private Runnable mReleaseCursorRunnable;
    final ImageView mSearchButton;
    private final View mSearchEditFrame;
    private final Drawable mSearchHintIcon;
    private final View mSearchPlate;
    final SearchAutoComplete mSearchSrcTextView;
    private Rect mSearchSrcTextViewBounds;
    private Rect mSearchSrtTextViewBoundsExpanded;
    SearchableInfo mSearchable;
    private final View mSubmitArea;
    private boolean mSubmitButtonEnabled;
    private final int mSuggestionCommitIconResId;
    private final int mSuggestionRowLayout;
    CursorAdapter mSuggestionsAdapter;
    private int[] mTemp;
    private int[] mTemp2;
    View.OnKeyListener mTextKeyListener;
    private TextWatcher mTextWatcher;
    private UpdatableTouchDelegate mTouchDelegate;
    private final Runnable mUpdateDrawableStateRunnable;
    private CharSequence mUserQuery;
    private final Intent mVoiceAppSearchIntent;
    final ImageView mVoiceButton;
    private boolean mVoiceButtonEnabled;
    private final Intent mVoiceWebSearchIntent;

    /* renamed from: android.support.v7.widget.SearchView$OnCloseListener */
    public interface OnCloseListener {
        boolean onClose();
    }

    /* renamed from: android.support.v7.widget.SearchView$OnQueryTextListener */
    public interface OnQueryTextListener {
        boolean onQueryTextChange(String str);

        boolean onQueryTextSubmit(String str);
    }

    /* renamed from: android.support.v7.widget.SearchView$OnSuggestionListener */
    public interface OnSuggestionListener {
        boolean onSuggestionClick(int i);

        boolean onSuggestionSelect(int i);
    }

    static {
        AutoCompleteTextViewReflector autoCompleteTextViewReflector;
        new AutoCompleteTextViewReflector();
        HIDDEN_METHOD_INVOKER = autoCompleteTextViewReflector;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SearchView(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SearchView(Context context, AttributeSet attrs) {
        this(context, attrs, C0425R.attr.searchViewStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SearchView(android.content.Context r18, android.util.AttributeSet r19, int r20) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r11 = r0
            r12 = r1
            r13 = r2
            r14 = r3
            r11.<init>(r12, r13, r14)
            r11 = r0
            android.graphics.Rect r12 = new android.graphics.Rect
            r16 = r12
            r12 = r16
            r13 = r16
            r13.<init>()
            r11.mSearchSrcTextViewBounds = r12
            r11 = r0
            android.graphics.Rect r12 = new android.graphics.Rect
            r16 = r12
            r12 = r16
            r13 = r16
            r13.<init>()
            r11.mSearchSrtTextViewBoundsExpanded = r12
            r11 = r0
            r12 = 2
            int[] r12 = new int[r12]
            r11.mTemp = r12
            r11 = r0
            r12 = 2
            int[] r12 = new int[r12]
            r11.mTemp2 = r12
            r11 = r0
            android.support.v7.widget.SearchView$1 r12 = new android.support.v7.widget.SearchView$1
            r16 = r12
            r12 = r16
            r13 = r16
            r14 = r0
            r13.<init>(r14)
            r11.mUpdateDrawableStateRunnable = r12
            r11 = r0
            android.support.v7.widget.SearchView$2 r12 = new android.support.v7.widget.SearchView$2
            r16 = r12
            r12 = r16
            r13 = r16
            r14 = r0
            r13.<init>(r14)
            r11.mReleaseCursorRunnable = r12
            r11 = r0
            java.util.WeakHashMap r12 = new java.util.WeakHashMap
            r16 = r12
            r12 = r16
            r13 = r16
            r13.<init>()
            r11.mOutsideDrawablesCache = r12
            r11 = r0
            android.support.v7.widget.SearchView$5 r12 = new android.support.v7.widget.SearchView$5
            r16 = r12
            r12 = r16
            r13 = r16
            r14 = r0
            r13.<init>(r14)
            r11.mOnClickListener = r12
            r11 = r0
            android.support.v7.widget.SearchView$6 r12 = new android.support.v7.widget.SearchView$6
            r16 = r12
            r12 = r16
            r13 = r16
            r14 = r0
            r13.<init>(r14)
            r11.mTextKeyListener = r12
            r11 = r0
            android.support.v7.widget.SearchView$7 r12 = new android.support.v7.widget.SearchView$7
            r16 = r12
            r12 = r16
            r13 = r16
            r14 = r0
            r13.<init>(r14)
            r11.mOnEditorActionListener = r12
            r11 = r0
            android.support.v7.widget.SearchView$8 r12 = new android.support.v7.widget.SearchView$8
            r16 = r12
            r12 = r16
            r13 = r16
            r14 = r0
            r13.<init>(r14)
            r11.mOnItemClickListener = r12
            r11 = r0
            android.support.v7.widget.SearchView$9 r12 = new android.support.v7.widget.SearchView$9
            r16 = r12
            r12 = r16
            r13 = r16
            r14 = r0
            r13.<init>(r14)
            r11.mOnItemSelectedListener = r12
            r11 = r0
            android.support.v7.widget.SearchView$10 r12 = new android.support.v7.widget.SearchView$10
            r16 = r12
            r12 = r16
            r13 = r16
            r14 = r0
            r13.<init>(r14)
            r11.mTextWatcher = r12
            r11 = r1
            r12 = r2
            int[] r13 = android.support.p003v7.appcompat.C0425R.styleable.SearchView
            r14 = r3
            r15 = 0
            android.support.v7.widget.TintTypedArray r11 = android.support.p003v7.widget.TintTypedArray.obtainStyledAttributes(r11, r12, r13, r14, r15)
            r4 = r11
            r11 = r1
            android.view.LayoutInflater r11 = android.view.LayoutInflater.from(r11)
            r5 = r11
            r11 = r4
            int r12 = android.support.p003v7.appcompat.C0425R.styleable.SearchView_layout
            int r13 = android.support.p003v7.appcompat.C0425R.layout.abc_search_view
            int r11 = r11.getResourceId(r12, r13)
            r6 = r11
            r11 = r5
            r12 = r6
            r13 = r0
            r14 = 1
            android.view.View r11 = r11.inflate(r12, r13, r14)
            r11 = r0
            r12 = r0
            int r13 = android.support.p003v7.appcompat.C0425R.C0427id.search_src_text
            android.view.View r12 = r12.findViewById(r13)
            android.support.v7.widget.SearchView$SearchAutoComplete r12 = (android.support.p003v7.widget.SearchView.SearchAutoComplete) r12
            r11.mSearchSrcTextView = r12
            r11 = r0
            android.support.v7.widget.SearchView$SearchAutoComplete r11 = r11.mSearchSrcTextView
            r12 = r0
            r11.setSearchView(r12)
            r11 = r0
            r12 = r0
            int r13 = android.support.p003v7.appcompat.C0425R.C0427id.search_edit_frame
            android.view.View r12 = r12.findViewById(r13)
            r11.mSearchEditFrame = r12
            r11 = r0
            r12 = r0
            int r13 = android.support.p003v7.appcompat.C0425R.C0427id.search_plate
            android.view.View r12 = r12.findViewById(r13)
            r11.mSearchPlate = r12
            r11 = r0
            r12 = r0
            int r13 = android.support.p003v7.appcompat.C0425R.C0427id.submit_area
            android.view.View r12 = r12.findViewById(r13)
            r11.mSubmitArea = r12
            r11 = r0
            r12 = r0
            int r13 = android.support.p003v7.appcompat.C0425R.C0427id.search_button
            android.view.View r12 = r12.findViewById(r13)
            android.widget.ImageView r12 = (android.widget.ImageView) r12
            r11.mSearchButton = r12
            r11 = r0
            r12 = r0
            int r13 = android.support.p003v7.appcompat.C0425R.C0427id.search_go_btn
            android.view.View r12 = r12.findViewById(r13)
            android.widget.ImageView r12 = (android.widget.ImageView) r12
            r11.mGoButton = r12
            r11 = r0
            r12 = r0
            int r13 = android.support.p003v7.appcompat.C0425R.C0427id.search_close_btn
            android.view.View r12 = r12.findViewById(r13)
            android.widget.ImageView r12 = (android.widget.ImageView) r12
            r11.mCloseButton = r12
            r11 = r0
            r12 = r0
            int r13 = android.support.p003v7.appcompat.C0425R.C0427id.search_voice_btn
            android.view.View r12 = r12.findViewById(r13)
            android.widget.ImageView r12 = (android.widget.ImageView) r12
            r11.mVoiceButton = r12
            r11 = r0
            r12 = r0
            int r13 = android.support.p003v7.appcompat.C0425R.C0427id.search_mag_icon
            android.view.View r12 = r12.findViewById(r13)
            android.widget.ImageView r12 = (android.widget.ImageView) r12
            r11.mCollapsedIcon = r12
            r11 = r0
            android.view.View r11 = r11.mSearchPlate
            r12 = r4
            int r13 = android.support.p003v7.appcompat.C0425R.styleable.SearchView_queryBackground
            android.graphics.drawable.Drawable r12 = r12.getDrawable(r13)
            android.support.p000v4.view.ViewCompat.setBackground(r11, r12)
            r11 = r0
            android.view.View r11 = r11.mSubmitArea
            r12 = r4
            int r13 = android.support.p003v7.appcompat.C0425R.styleable.SearchView_submitBackground
            android.graphics.drawable.Drawable r12 = r12.getDrawable(r13)
            android.support.p000v4.view.ViewCompat.setBackground(r11, r12)
            r11 = r0
            android.widget.ImageView r11 = r11.mSearchButton
            r12 = r4
            int r13 = android.support.p003v7.appcompat.C0425R.styleable.SearchView_searchIcon
            android.graphics.drawable.Drawable r12 = r12.getDrawable(r13)
            r11.setImageDrawable(r12)
            r11 = r0
            android.widget.ImageView r11 = r11.mGoButton
            r12 = r4
            int r13 = android.support.p003v7.appcompat.C0425R.styleable.SearchView_goIcon
            android.graphics.drawable.Drawable r12 = r12.getDrawable(r13)
            r11.setImageDrawable(r12)
            r11 = r0
            android.widget.ImageView r11 = r11.mCloseButton
            r12 = r4
            int r13 = android.support.p003v7.appcompat.C0425R.styleable.SearchView_closeIcon
            android.graphics.drawable.Drawable r12 = r12.getDrawable(r13)
            r11.setImageDrawable(r12)
            r11 = r0
            android.widget.ImageView r11 = r11.mVoiceButton
            r12 = r4
            int r13 = android.support.p003v7.appcompat.C0425R.styleable.SearchView_voiceIcon
            android.graphics.drawable.Drawable r12 = r12.getDrawable(r13)
            r11.setImageDrawable(r12)
            r11 = r0
            android.widget.ImageView r11 = r11.mCollapsedIcon
            r12 = r4
            int r13 = android.support.p003v7.appcompat.C0425R.styleable.SearchView_searchIcon
            android.graphics.drawable.Drawable r12 = r12.getDrawable(r13)
            r11.setImageDrawable(r12)
            r11 = r0
            r12 = r4
            int r13 = android.support.p003v7.appcompat.C0425R.styleable.SearchView_searchHintIcon
            android.graphics.drawable.Drawable r12 = r12.getDrawable(r13)
            r11.mSearchHintIcon = r12
            r11 = r0
            android.widget.ImageView r11 = r11.mSearchButton
            r12 = r0
            android.content.res.Resources r12 = r12.getResources()
            int r13 = android.support.p003v7.appcompat.C0425R.string.abc_searchview_description_search
            java.lang.String r12 = r12.getString(r13)
            android.support.p003v7.widget.TooltipCompat.setTooltipText(r11, r12)
            r11 = r0
            r12 = r4
            int r13 = android.support.p003v7.appcompat.C0425R.styleable.SearchView_suggestionRowLayout
            int r14 = android.support.p003v7.appcompat.C0425R.layout.abc_search_dropdown_item_icons_2line
            int r12 = r12.getResourceId(r13, r14)
            r11.mSuggestionRowLayout = r12
            r11 = r0
            r12 = r4
            int r13 = android.support.p003v7.appcompat.C0425R.styleable.SearchView_commitIcon
            r14 = 0
            int r12 = r12.getResourceId(r13, r14)
            r11.mSuggestionCommitIconResId = r12
            r11 = r0
            android.widget.ImageView r11 = r11.mSearchButton
            r12 = r0
            android.view.View$OnClickListener r12 = r12.mOnClickListener
            r11.setOnClickListener(r12)
            r11 = r0
            android.widget.ImageView r11 = r11.mCloseButton
            r12 = r0
            android.view.View$OnClickListener r12 = r12.mOnClickListener
            r11.setOnClickListener(r12)
            r11 = r0
            android.widget.ImageView r11 = r11.mGoButton
            r12 = r0
            android.view.View$OnClickListener r12 = r12.mOnClickListener
            r11.setOnClickListener(r12)
            r11 = r0
            android.widget.ImageView r11 = r11.mVoiceButton
            r12 = r0
            android.view.View$OnClickListener r12 = r12.mOnClickListener
            r11.setOnClickListener(r12)
            r11 = r0
            android.support.v7.widget.SearchView$SearchAutoComplete r11 = r11.mSearchSrcTextView
            r12 = r0
            android.view.View$OnClickListener r12 = r12.mOnClickListener
            r11.setOnClickListener(r12)
            r11 = r0
            android.support.v7.widget.SearchView$SearchAutoComplete r11 = r11.mSearchSrcTextView
            r12 = r0
            android.text.TextWatcher r12 = r12.mTextWatcher
            r11.addTextChangedListener(r12)
            r11 = r0
            android.support.v7.widget.SearchView$SearchAutoComplete r11 = r11.mSearchSrcTextView
            r12 = r0
            android.widget.TextView$OnEditorActionListener r12 = r12.mOnEditorActionListener
            r11.setOnEditorActionListener(r12)
            r11 = r0
            android.support.v7.widget.SearchView$SearchAutoComplete r11 = r11.mSearchSrcTextView
            r12 = r0
            android.widget.AdapterView$OnItemClickListener r12 = r12.mOnItemClickListener
            r11.setOnItemClickListener(r12)
            r11 = r0
            android.support.v7.widget.SearchView$SearchAutoComplete r11 = r11.mSearchSrcTextView
            r12 = r0
            android.widget.AdapterView$OnItemSelectedListener r12 = r12.mOnItemSelectedListener
            r11.setOnItemSelectedListener(r12)
            r11 = r0
            android.support.v7.widget.SearchView$SearchAutoComplete r11 = r11.mSearchSrcTextView
            r12 = r0
            android.view.View$OnKeyListener r12 = r12.mTextKeyListener
            r11.setOnKeyListener(r12)
            r11 = r0
            android.support.v7.widget.SearchView$SearchAutoComplete r11 = r11.mSearchSrcTextView
            android.support.v7.widget.SearchView$3 r12 = new android.support.v7.widget.SearchView$3
            r16 = r12
            r12 = r16
            r13 = r16
            r14 = r0
            r13.<init>(r14)
            r11.setOnFocusChangeListener(r12)
            r11 = r0
            r12 = r4
            int r13 = android.support.p003v7.appcompat.C0425R.styleable.SearchView_iconifiedByDefault
            r14 = 1
            boolean r12 = r12.getBoolean(r13, r14)
            r11.setIconifiedByDefault(r12)
            r11 = r4
            int r12 = android.support.p003v7.appcompat.C0425R.styleable.SearchView_android_maxWidth
            r13 = -1
            int r11 = r11.getDimensionPixelSize(r12, r13)
            r7 = r11
            r11 = r7
            r12 = -1
            if (r11 == r12) goto L_0x0264
            r11 = r0
            r12 = r7
            r11.setMaxWidth(r12)
        L_0x0264:
            r11 = r0
            r12 = r4
            int r13 = android.support.p003v7.appcompat.C0425R.styleable.SearchView_defaultQueryHint
            java.lang.CharSequence r12 = r12.getText(r13)
            r11.mDefaultQueryHint = r12
            r11 = r0
            r12 = r4
            int r13 = android.support.p003v7.appcompat.C0425R.styleable.SearchView_queryHint
            java.lang.CharSequence r12 = r12.getText(r13)
            r11.mQueryHint = r12
            r11 = r4
            int r12 = android.support.p003v7.appcompat.C0425R.styleable.SearchView_android_imeOptions
            r13 = -1
            int r11 = r11.getInt(r12, r13)
            r8 = r11
            r11 = r8
            r12 = -1
            if (r11 == r12) goto L_0x028a
            r11 = r0
            r12 = r8
            r11.setImeOptions(r12)
        L_0x028a:
            r11 = r4
            int r12 = android.support.p003v7.appcompat.C0425R.styleable.SearchView_android_inputType
            r13 = -1
            int r11 = r11.getInt(r12, r13)
            r9 = r11
            r11 = r9
            r12 = -1
            if (r11 == r12) goto L_0x029c
            r11 = r0
            r12 = r9
            r11.setInputType(r12)
        L_0x029c:
            r11 = 1
            r10 = r11
            r11 = r4
            int r12 = android.support.p003v7.appcompat.C0425R.styleable.SearchView_android_focusable
            r13 = r10
            boolean r11 = r11.getBoolean(r12, r13)
            r10 = r11
            r11 = r0
            r12 = r10
            r11.setFocusable(r12)
            r11 = r4
            r11.recycle()
            r11 = r0
            android.content.Intent r12 = new android.content.Intent
            r16 = r12
            r12 = r16
            r13 = r16
            java.lang.String r14 = "android.speech.action.WEB_SEARCH"
            r13.<init>(r14)
            r11.mVoiceWebSearchIntent = r12
            r11 = r0
            android.content.Intent r11 = r11.mVoiceWebSearchIntent
            r12 = 268435456(0x10000000, float:2.5243549E-29)
            android.content.Intent r11 = r11.addFlags(r12)
            r11 = r0
            android.content.Intent r11 = r11.mVoiceWebSearchIntent
            java.lang.String r12 = "android.speech.extra.LANGUAGE_MODEL"
            java.lang.String r13 = "web_search"
            android.content.Intent r11 = r11.putExtra(r12, r13)
            r11 = r0
            android.content.Intent r12 = new android.content.Intent
            r16 = r12
            r12 = r16
            r13 = r16
            java.lang.String r14 = "android.speech.action.RECOGNIZE_SPEECH"
            r13.<init>(r14)
            r11.mVoiceAppSearchIntent = r12
            r11 = r0
            android.content.Intent r11 = r11.mVoiceAppSearchIntent
            r12 = 268435456(0x10000000, float:2.5243549E-29)
            android.content.Intent r11 = r11.addFlags(r12)
            r11 = r0
            r12 = r0
            r13 = r0
            android.support.v7.widget.SearchView$SearchAutoComplete r13 = r13.mSearchSrcTextView
            int r13 = r13.getDropDownAnchor()
            android.view.View r12 = r12.findViewById(r13)
            r11.mDropDownAnchor = r12
            r11 = r0
            android.view.View r11 = r11.mDropDownAnchor
            if (r11 == 0) goto L_0x0317
            r11 = r0
            android.view.View r11 = r11.mDropDownAnchor
            android.support.v7.widget.SearchView$4 r12 = new android.support.v7.widget.SearchView$4
            r16 = r12
            r12 = r16
            r13 = r16
            r14 = r0
            r13.<init>(r14)
            r11.addOnLayoutChangeListener(r12)
        L_0x0317:
            r11 = r0
            r12 = r0
            boolean r12 = r12.mIconifiedByDefault
            r11.updateViewsVisibility(r12)
            r11 = r0
            r11.updateQueryHint()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.SearchView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    /* access modifiers changed from: package-private */
    public int getSuggestionRowLayout() {
        return this.mSuggestionRowLayout;
    }

    /* access modifiers changed from: package-private */
    public int getSuggestionCommitIconResId() {
        return this.mSuggestionCommitIconResId;
    }

    public void setSearchableInfo(SearchableInfo searchable) {
        this.mSearchable = searchable;
        if (this.mSearchable != null) {
            updateSearchAutoComplete();
            updateQueryHint();
        }
        this.mVoiceButtonEnabled = hasVoiceSearch();
        if (this.mVoiceButtonEnabled) {
            this.mSearchSrcTextView.setPrivateImeOptions(IME_OPTION_NO_MICROPHONE);
        }
        updateViewsVisibility(isIconified());
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setAppSearchData(Bundle appSearchData) {
        Bundle bundle = appSearchData;
        this.mAppSearchData = bundle;
    }

    public void setImeOptions(int imeOptions) {
        this.mSearchSrcTextView.setImeOptions(imeOptions);
    }

    public int getImeOptions() {
        return this.mSearchSrcTextView.getImeOptions();
    }

    public void setInputType(int inputType) {
        this.mSearchSrcTextView.setInputType(inputType);
    }

    public int getInputType() {
        return this.mSearchSrcTextView.getInputType();
    }

    public boolean requestFocus(int i, Rect rect) {
        int direction = i;
        Rect previouslyFocusedRect = rect;
        if (this.mClearingFocus) {
            return false;
        }
        if (!isFocusable()) {
            return false;
        }
        if (isIconified()) {
            return super.requestFocus(direction, previouslyFocusedRect);
        }
        boolean result = this.mSearchSrcTextView.requestFocus(direction, previouslyFocusedRect);
        if (result) {
            updateViewsVisibility(false);
        }
        return result;
    }

    public void clearFocus() {
        this.mClearingFocus = true;
        super.clearFocus();
        this.mSearchSrcTextView.clearFocus();
        this.mSearchSrcTextView.setImeVisibility(false);
        this.mClearingFocus = false;
    }

    public void setOnQueryTextListener(OnQueryTextListener listener) {
        OnQueryTextListener onQueryTextListener = listener;
        this.mOnQueryChangeListener = onQueryTextListener;
    }

    public void setOnCloseListener(OnCloseListener listener) {
        OnCloseListener onCloseListener = listener;
        this.mOnCloseListener = onCloseListener;
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener listener) {
        View.OnFocusChangeListener onFocusChangeListener = listener;
        this.mOnQueryTextFocusChangeListener = onFocusChangeListener;
    }

    public void setOnSuggestionListener(OnSuggestionListener listener) {
        OnSuggestionListener onSuggestionListener = listener;
        this.mOnSuggestionListener = onSuggestionListener;
    }

    public void setOnSearchClickListener(View.OnClickListener listener) {
        View.OnClickListener onClickListener = listener;
        this.mOnSearchClickListener = onClickListener;
    }

    public CharSequence getQuery() {
        return this.mSearchSrcTextView.getText();
    }

    public void setQuery(CharSequence charSequence, boolean z) {
        CharSequence query = charSequence;
        boolean submit = z;
        this.mSearchSrcTextView.setText(query);
        if (query != null) {
            this.mSearchSrcTextView.setSelection(this.mSearchSrcTextView.length());
            this.mUserQuery = query;
        }
        if (submit && !TextUtils.isEmpty(query)) {
            onSubmitQuery();
        }
    }

    public void setQueryHint(@Nullable CharSequence hint) {
        this.mQueryHint = hint;
        updateQueryHint();
    }

    @Nullable
    public CharSequence getQueryHint() {
        CharSequence hint;
        if (this.mQueryHint != null) {
            hint = this.mQueryHint;
        } else if (this.mSearchable == null || this.mSearchable.getHintId() == 0) {
            hint = this.mDefaultQueryHint;
        } else {
            hint = getContext().getText(this.mSearchable.getHintId());
        }
        return hint;
    }

    public void setIconifiedByDefault(boolean z) {
        boolean iconified = z;
        if (this.mIconifiedByDefault != iconified) {
            this.mIconifiedByDefault = iconified;
            updateViewsVisibility(iconified);
            updateQueryHint();
        }
    }

    public boolean isIconfiedByDefault() {
        return this.mIconifiedByDefault;
    }

    public void setIconified(boolean iconify) {
        if (iconify) {
            onCloseClicked();
        } else {
            onSearchClicked();
        }
    }

    public boolean isIconified() {
        return this.mIconified;
    }

    public void setSubmitButtonEnabled(boolean enabled) {
        this.mSubmitButtonEnabled = enabled;
        updateViewsVisibility(isIconified());
    }

    public boolean isSubmitButtonEnabled() {
        return this.mSubmitButtonEnabled;
    }

    public void setQueryRefinementEnabled(boolean z) {
        boolean enable = z;
        this.mQueryRefinement = enable;
        if (this.mSuggestionsAdapter instanceof SuggestionsAdapter) {
            ((SuggestionsAdapter) this.mSuggestionsAdapter).setQueryRefinement(enable ? 2 : 1);
        }
    }

    public boolean isQueryRefinementEnabled() {
        return this.mQueryRefinement;
    }

    public void setSuggestionsAdapter(CursorAdapter adapter) {
        this.mSuggestionsAdapter = adapter;
        this.mSearchSrcTextView.setAdapter(this.mSuggestionsAdapter);
    }

    public CursorAdapter getSuggestionsAdapter() {
        return this.mSuggestionsAdapter;
    }

    public void setMaxWidth(int maxpixels) {
        this.mMaxWidth = maxpixels;
        requestLayout();
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int widthMeasureSpec = i;
        int heightMeasureSpec = i2;
        if (isIconified()) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        switch (widthMode) {
            case Integer.MIN_VALUE:
                if (this.mMaxWidth <= 0) {
                    width = Math.min(getPreferredWidth(), width);
                    break;
                } else {
                    width = Math.min(this.mMaxWidth, width);
                    break;
                }
            case 0:
                width = this.mMaxWidth > 0 ? this.mMaxWidth : getPreferredWidth();
                break;
            case Declaration.MODULE_REFERENCE /*1073741824*/:
                if (this.mMaxWidth > 0) {
                    width = Math.min(this.mMaxWidth, width);
                    break;
                }
                break;
        }
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int height = View.MeasureSpec.getSize(heightMeasureSpec);
        switch (heightMode) {
            case Integer.MIN_VALUE:
                height = Math.min(getPreferredHeight(), height);
                break;
            case 0:
                height = getPreferredHeight();
                break;
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(width, Declaration.MODULE_REFERENCE), View.MeasureSpec.makeMeasureSpec(height, Declaration.MODULE_REFERENCE));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int left, int i, int right, int i2) {
        UpdatableTouchDelegate updatableTouchDelegate;
        boolean changed = z;
        int top = i;
        int bottom = i2;
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            getChildBoundsWithinSearchView(this.mSearchSrcTextView, this.mSearchSrcTextViewBounds);
            this.mSearchSrtTextViewBoundsExpanded.set(this.mSearchSrcTextViewBounds.left, 0, this.mSearchSrcTextViewBounds.right, bottom - top);
            if (this.mTouchDelegate == null) {
                new UpdatableTouchDelegate(this.mSearchSrtTextViewBoundsExpanded, this.mSearchSrcTextViewBounds, this.mSearchSrcTextView);
                this.mTouchDelegate = updatableTouchDelegate;
                setTouchDelegate(this.mTouchDelegate);
                return;
            }
            this.mTouchDelegate.setBounds(this.mSearchSrtTextViewBoundsExpanded, this.mSearchSrcTextViewBounds);
        }
    }

    private void getChildBoundsWithinSearchView(View view, Rect rect) {
        View view2 = view;
        view2.getLocationInWindow(this.mTemp);
        getLocationInWindow(this.mTemp2);
        int top = this.mTemp[1] - this.mTemp2[1];
        int left = this.mTemp[0] - this.mTemp2[0];
        rect.set(left, top, left + view2.getWidth(), top + view2.getHeight());
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(C0425R.dimen.abc_search_view_preferred_width);
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(C0425R.dimen.abc_search_view_preferred_height);
    }

    private void updateViewsVisibility(boolean z) {
        int iconVisibility;
        boolean collapsed = z;
        this.mIconified = collapsed;
        int visCollapsed = collapsed ? 0 : 8;
        boolean hasText = !TextUtils.isEmpty(this.mSearchSrcTextView.getText());
        this.mSearchButton.setVisibility(visCollapsed);
        updateSubmitButton(hasText);
        this.mSearchEditFrame.setVisibility(collapsed ? 8 : 0);
        if (this.mCollapsedIcon.getDrawable() == null || this.mIconifiedByDefault) {
            iconVisibility = 8;
        } else {
            iconVisibility = 0;
        }
        this.mCollapsedIcon.setVisibility(iconVisibility);
        updateCloseButton();
        updateVoiceButton(!hasText);
        updateSubmitArea();
    }

    private boolean hasVoiceSearch() {
        if (this.mSearchable != null && this.mSearchable.getVoiceSearchEnabled()) {
            Intent testIntent = null;
            if (this.mSearchable.getVoiceSearchLaunchWebSearch()) {
                testIntent = this.mVoiceWebSearchIntent;
            } else if (this.mSearchable.getVoiceSearchLaunchRecognizer()) {
                testIntent = this.mVoiceAppSearchIntent;
            }
            if (testIntent != null) {
                return getContext().getPackageManager().resolveActivity(testIntent, 65536) != null;
            }
        }
        return false;
    }

    private boolean isSubmitAreaEnabled() {
        return (this.mSubmitButtonEnabled || this.mVoiceButtonEnabled) && !isIconified();
    }

    private void updateSubmitButton(boolean z) {
        boolean hasText = z;
        int visibility = 8;
        if (this.mSubmitButtonEnabled && isSubmitAreaEnabled() && hasFocus() && (hasText || !this.mVoiceButtonEnabled)) {
            visibility = 0;
        }
        this.mGoButton.setVisibility(visibility);
    }

    private void updateSubmitArea() {
        int visibility = 8;
        if (isSubmitAreaEnabled() && (this.mGoButton.getVisibility() == 0 || this.mVoiceButton.getVisibility() == 0)) {
            visibility = 0;
        }
        this.mSubmitArea.setVisibility(visibility);
    }

    private void updateCloseButton() {
        boolean hasText = !TextUtils.isEmpty(this.mSearchSrcTextView.getText());
        this.mCloseButton.setVisibility(hasText || (this.mIconifiedByDefault && !this.mExpandedInActionView) ? 0 : 8);
        Drawable closeButtonImg = this.mCloseButton.getDrawable();
        if (closeButtonImg != null) {
            boolean state = closeButtonImg.setState(hasText ? ENABLED_STATE_SET : EMPTY_STATE_SET);
        }
    }

    private void postUpdateFocusedState() {
        boolean post = post(this.mUpdateDrawableStateRunnable);
    }

    /* access modifiers changed from: package-private */
    public void updateFocusedState() {
        int[] stateSet = this.mSearchSrcTextView.hasFocus() ? FOCUSED_STATE_SET : EMPTY_STATE_SET;
        Drawable searchPlateBg = this.mSearchPlate.getBackground();
        if (searchPlateBg != null) {
            boolean state = searchPlateBg.setState(stateSet);
        }
        Drawable submitAreaBg = this.mSubmitArea.getBackground();
        if (submitAreaBg != null) {
            boolean state2 = submitAreaBg.setState(stateSet);
        }
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        boolean removeCallbacks = removeCallbacks(this.mUpdateDrawableStateRunnable);
        boolean post = post(this.mReleaseCursorRunnable);
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: package-private */
    public void onQueryRefine(CharSequence queryText) {
        setQuery(queryText);
    }

    /* access modifiers changed from: package-private */
    public boolean onSuggestionsKey(View view, int i, KeyEvent keyEvent) {
        int selPoint;
        View view2 = view;
        int keyCode = i;
        KeyEvent event = keyEvent;
        if (this.mSearchable == null) {
            return false;
        }
        if (this.mSuggestionsAdapter == null) {
            return false;
        }
        if (event.getAction() == 0 && event.hasNoModifiers()) {
            if (keyCode == 66 || keyCode == 84 || keyCode == 61) {
                return onItemClicked(this.mSearchSrcTextView.getListSelection(), 0, (String) null);
            } else if (keyCode == 21 || keyCode == 22) {
                if (keyCode == 21) {
                    selPoint = 0;
                } else {
                    selPoint = this.mSearchSrcTextView.length();
                }
                this.mSearchSrcTextView.setSelection(selPoint);
                this.mSearchSrcTextView.setListSelection(0);
                this.mSearchSrcTextView.clearListSelection();
                HIDDEN_METHOD_INVOKER.ensureImeVisible(this.mSearchSrcTextView, true);
                return true;
            } else if (keyCode == 19 && 0 == this.mSearchSrcTextView.getListSelection()) {
                return false;
            }
        }
        return false;
    }

    private CharSequence getDecoratedHint(CharSequence charSequence) {
        SpannableStringBuilder spannableStringBuilder;
        Object obj;
        CharSequence hintText = charSequence;
        if (!this.mIconifiedByDefault || this.mSearchHintIcon == null) {
            return hintText;
        }
        int textSize = (int) (((double) this.mSearchSrcTextView.getTextSize()) * 1.25d);
        this.mSearchHintIcon.setBounds(0, 0, textSize, textSize);
        new SpannableStringBuilder("   ");
        SpannableStringBuilder ssb = spannableStringBuilder;
        new ImageSpan(this.mSearchHintIcon);
        ssb.setSpan(obj, 1, 2, 33);
        SpannableStringBuilder append = ssb.append(hintText);
        return ssb;
    }

    private void updateQueryHint() {
        CharSequence hint = getQueryHint();
        this.mSearchSrcTextView.setHint(getDecoratedHint(hint == null ? "" : hint));
    }

    private void updateSearchAutoComplete() {
        CursorAdapter cursorAdapter;
        this.mSearchSrcTextView.setThreshold(this.mSearchable.getSuggestThreshold());
        this.mSearchSrcTextView.setImeOptions(this.mSearchable.getImeOptions());
        int inputType = this.mSearchable.getInputType();
        if ((inputType & 15) == 1) {
            inputType &= -65537;
            if (this.mSearchable.getSuggestAuthority() != null) {
                inputType = inputType | 65536 | 524288;
            }
        }
        this.mSearchSrcTextView.setInputType(inputType);
        if (this.mSuggestionsAdapter != null) {
            this.mSuggestionsAdapter.changeCursor((Cursor) null);
        }
        if (this.mSearchable.getSuggestAuthority() != null) {
            new SuggestionsAdapter(getContext(), this, this.mSearchable, this.mOutsideDrawablesCache);
            this.mSuggestionsAdapter = cursorAdapter;
            this.mSearchSrcTextView.setAdapter(this.mSuggestionsAdapter);
            ((SuggestionsAdapter) this.mSuggestionsAdapter).setQueryRefinement(this.mQueryRefinement ? 2 : 1);
        }
    }

    private void updateVoiceButton(boolean z) {
        boolean empty = z;
        int visibility = 8;
        if (this.mVoiceButtonEnabled && !isIconified() && empty) {
            visibility = 0;
            this.mGoButton.setVisibility(8);
        }
        this.mVoiceButton.setVisibility(visibility);
    }

    /* access modifiers changed from: package-private */
    public void onTextChanged(CharSequence charSequence) {
        CharSequence newText = charSequence;
        CharSequence text = this.mSearchSrcTextView.getText();
        this.mUserQuery = text;
        boolean hasText = !TextUtils.isEmpty(text);
        updateSubmitButton(hasText);
        updateVoiceButton(!hasText);
        updateCloseButton();
        updateSubmitArea();
        if (this.mOnQueryChangeListener != null && !TextUtils.equals(newText, this.mOldQueryText)) {
            boolean onQueryTextChange = this.mOnQueryChangeListener.onQueryTextChange(newText.toString());
        }
        this.mOldQueryText = newText.toString();
    }

    /* access modifiers changed from: package-private */
    public void onSubmitQuery() {
        CharSequence query = this.mSearchSrcTextView.getText();
        if (query != null && TextUtils.getTrimmedLength(query) > 0) {
            if (this.mOnQueryChangeListener == null || !this.mOnQueryChangeListener.onQueryTextSubmit(query.toString())) {
                if (this.mSearchable != null) {
                    launchQuerySearch(0, (String) null, query.toString());
                }
                this.mSearchSrcTextView.setImeVisibility(false);
                dismissSuggestions();
            }
        }
    }

    private void dismissSuggestions() {
        this.mSearchSrcTextView.dismissDropDown();
    }

    /* access modifiers changed from: package-private */
    public void onCloseClicked() {
        if (!TextUtils.isEmpty(this.mSearchSrcTextView.getText())) {
            this.mSearchSrcTextView.setText("");
            boolean requestFocus = this.mSearchSrcTextView.requestFocus();
            this.mSearchSrcTextView.setImeVisibility(true);
        } else if (!this.mIconifiedByDefault) {
        } else {
            if (this.mOnCloseListener == null || !this.mOnCloseListener.onClose()) {
                clearFocus();
                updateViewsVisibility(true);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onSearchClicked() {
        updateViewsVisibility(false);
        boolean requestFocus = this.mSearchSrcTextView.requestFocus();
        this.mSearchSrcTextView.setImeVisibility(true);
        if (this.mOnSearchClickListener != null) {
            this.mOnSearchClickListener.onClick(this);
        }
    }

    /* access modifiers changed from: package-private */
    public void onVoiceClicked() {
        if (this.mSearchable != null) {
            SearchableInfo searchable = this.mSearchable;
            try {
                if (searchable.getVoiceSearchLaunchWebSearch()) {
                    getContext().startActivity(createVoiceWebSearchIntent(this.mVoiceWebSearchIntent, searchable));
                } else if (searchable.getVoiceSearchLaunchRecognizer()) {
                    getContext().startActivity(createVoiceAppSearchIntent(this.mVoiceAppSearchIntent, searchable));
                }
            } catch (ActivityNotFoundException e) {
                ActivityNotFoundException activityNotFoundException = e;
                int w = Log.w(LOG_TAG, "Could not find voice search activity");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onTextFocusChanged() {
        updateViewsVisibility(isIconified());
        postUpdateFocusedState();
        if (this.mSearchSrcTextView.hasFocus()) {
            forceSuggestionQuery();
        }
    }

    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        postUpdateFocusedState();
    }

    public void onActionViewCollapsed() {
        setQuery("", false);
        clearFocus();
        updateViewsVisibility(true);
        this.mSearchSrcTextView.setImeOptions(this.mCollapsedImeOptions);
        this.mExpandedInActionView = false;
    }

    public void onActionViewExpanded() {
        if (!this.mExpandedInActionView) {
            this.mExpandedInActionView = true;
            this.mCollapsedImeOptions = this.mSearchSrcTextView.getImeOptions();
            this.mSearchSrcTextView.setImeOptions(this.mCollapsedImeOptions | Declaration.PROTECTED_ACCESS);
            this.mSearchSrcTextView.setText("");
            setIconified(false);
        }
    }

    /* renamed from: android.support.v7.widget.SearchView$SavedState */
    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR;
        boolean isIconified;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        SavedState(Parcelable superState) {
            super(superState);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public SavedState(android.os.Parcel r7, java.lang.ClassLoader r8) {
            /*
                r6 = this;
                r0 = r6
                r1 = r7
                r2 = r8
                r3 = r0
                r4 = r1
                r5 = r2
                r3.<init>(r4, r5)
                r3 = r0
                r4 = r1
                r5 = 0
                java.lang.Object r4 = r4.readValue(r5)
                java.lang.Boolean r4 = (java.lang.Boolean) r4
                boolean r4 = r4.booleanValue()
                r3.isIconified = r4
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.SearchView.SavedState.<init>(android.os.Parcel, java.lang.ClassLoader):void");
        }

        public void writeToParcel(Parcel parcel, int flags) {
            Parcel dest = parcel;
            super.writeToParcel(dest, flags);
            dest.writeValue(Boolean.valueOf(this.isIconified));
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder();
            return sb.append("SearchView.SavedState{").append(Integer.toHexString(System.identityHashCode(this))).append(" isIconified=").append(this.isIconified).append("}").toString();
        }

        static {
            Parcelable.Creator<SavedState> creator;
            new Parcelable.ClassLoaderCreator<SavedState>() {
                public SavedState createFromParcel(Parcel in, ClassLoader loader) {
                    SavedState savedState;
                    new SavedState(in, loader);
                    return savedState;
                }

                public SavedState createFromParcel(Parcel in) {
                    SavedState savedState;
                    new SavedState(in, (ClassLoader) null);
                    return savedState;
                }

                public SavedState[] newArray(int size) {
                    return new SavedState[size];
                }
            };
            CREATOR = creator;
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState;
        new SavedState(super.onSaveInstanceState());
        SavedState ss = savedState;
        ss.isIconified = isIconified();
        return ss;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable state = parcelable;
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        updateViewsVisibility(ss.isIconified);
        requestLayout();
    }

    /* access modifiers changed from: package-private */
    public void adjustDropDownSizeAndPosition() {
        Rect rect;
        int offset;
        if (this.mDropDownAnchor.getWidth() > 1) {
            Resources res = getContext().getResources();
            int anchorPadding = this.mSearchPlate.getPaddingLeft();
            new Rect();
            Rect dropDownPadding = rect;
            boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
            int iconOffset = this.mIconifiedByDefault ? res.getDimensionPixelSize(C0425R.dimen.abc_dropdownitem_icon_width) + res.getDimensionPixelSize(C0425R.dimen.abc_dropdownitem_text_padding_left) : 0;
            boolean padding = this.mSearchSrcTextView.getDropDownBackground().getPadding(dropDownPadding);
            if (isLayoutRtl) {
                offset = -dropDownPadding.left;
            } else {
                offset = anchorPadding - (dropDownPadding.left + iconOffset);
            }
            this.mSearchSrcTextView.setDropDownHorizontalOffset(offset);
            this.mSearchSrcTextView.setDropDownWidth((((this.mDropDownAnchor.getWidth() + dropDownPadding.left) + dropDownPadding.right) + iconOffset) - anchorPadding);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean onItemClicked(int i, int i2, String str) {
        int position = i;
        int i3 = i2;
        String str2 = str;
        if (this.mOnSuggestionListener != null && this.mOnSuggestionListener.onSuggestionClick(position)) {
            return false;
        }
        boolean launchSuggestion = launchSuggestion(position, 0, (String) null);
        this.mSearchSrcTextView.setImeVisibility(false);
        dismissSuggestions();
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean onItemSelected(int i) {
        int position = i;
        if (this.mOnSuggestionListener != null && this.mOnSuggestionListener.onSuggestionSelect(position)) {
            return false;
        }
        rewriteQueryFromSuggestion(position);
        return true;
    }

    private void rewriteQueryFromSuggestion(int i) {
        int position = i;
        CharSequence oldQuery = this.mSearchSrcTextView.getText();
        Cursor c = this.mSuggestionsAdapter.getCursor();
        if (c != null) {
            if (c.moveToPosition(position)) {
                CharSequence newQuery = this.mSuggestionsAdapter.convertToString(c);
                if (newQuery != null) {
                    setQuery(newQuery);
                } else {
                    setQuery(oldQuery);
                }
            } else {
                setQuery(oldQuery);
            }
        }
    }

    private boolean launchSuggestion(int i, int i2, String str) {
        int position = i;
        int actionKey = i2;
        String actionMsg = str;
        Cursor c = this.mSuggestionsAdapter.getCursor();
        if (c == null || !c.moveToPosition(position)) {
            return false;
        }
        launchIntent(createIntentFromSuggestion(c, actionKey, actionMsg));
        return true;
    }

    private void launchIntent(Intent intent) {
        StringBuilder sb;
        Intent intent2 = intent;
        if (intent2 != null) {
            try {
                getContext().startActivity(intent2);
            } catch (RuntimeException e) {
                new StringBuilder();
                int e2 = Log.e(LOG_TAG, sb.append("Failed launch activity: ").append(intent2).toString(), e);
            }
        }
    }

    private void setQuery(CharSequence charSequence) {
        CharSequence query = charSequence;
        this.mSearchSrcTextView.setText(query);
        this.mSearchSrcTextView.setSelection(TextUtils.isEmpty(query) ? 0 : query.length());
    }

    /*  JADX ERROR: NullPointerException in pass: CodeShrinkVisitor
        java.lang.NullPointerException
        	at jadx.core.dex.instructions.args.InsnArg.wrapInstruction(InsnArg.java:118)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.inline(CodeShrinkVisitor.java:146)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkBlock(CodeShrinkVisitor.java:71)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkMethod(CodeShrinkVisitor.java:43)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.visit(CodeShrinkVisitor.java:35)
        */
    void launchQuerySearch(int r14, java.lang.String r15, java.lang.String r16) {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r2 = r15
            r3 = r16
            java.lang.String r6 = "android.intent.action.SEARCH"
            r4 = r6
            r6 = r0
            r7 = r4
            r8 = 0
            r9 = 0
            r10 = r3
            r11 = r1
            r12 = r2
            android.content.Intent r6 = r6.createIntent(r7, r8, r9, r10, r11, r12)
            r5 = r6
            r6 = r0
            android.content.Context r6 = r6.getContext()
            r7 = r5
            r6.startActivity(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.SearchView.launchQuerySearch(int, java.lang.String, java.lang.String):void");
    }

    private Intent createIntent(String action, Uri uri, String str, String str2, int i, String str3) {
        Intent intent;
        Uri data = uri;
        String extraData = str;
        String query = str2;
        int actionKey = i;
        String actionMsg = str3;
        new Intent(action);
        Intent intent2 = intent;
        Intent addFlags = intent2.addFlags(Declaration.IS_DYNAMIC);
        if (data != null) {
            Intent data2 = intent2.setData(data);
        }
        Intent putExtra = intent2.putExtra("user_query", this.mUserQuery);
        if (query != null) {
            Intent putExtra2 = intent2.putExtra(SearchIntents.EXTRA_QUERY, query);
        }
        if (extraData != null) {
            Intent putExtra3 = intent2.putExtra("intent_extra_data_key", extraData);
        }
        if (this.mAppSearchData != null) {
            Intent putExtra4 = intent2.putExtra("app_data", this.mAppSearchData);
        }
        if (actionKey != 0) {
            Intent putExtra5 = intent2.putExtra("action_key", actionKey);
            Intent putExtra6 = intent2.putExtra("action_msg", actionMsg);
        }
        Intent component = intent2.setComponent(this.mSearchable.getSearchActivity());
        return intent2;
    }

    private Intent createVoiceWebSearchIntent(Intent baseIntent, SearchableInfo searchable) {
        Intent intent;
        String flattenToShortString;
        new Intent(baseIntent);
        Intent voiceIntent = intent;
        ComponentName searchActivity = searchable.getSearchActivity();
        Intent intent2 = voiceIntent;
        if (searchActivity == null) {
            flattenToShortString = null;
        } else {
            flattenToShortString = searchActivity.flattenToShortString();
        }
        Intent putExtra = intent2.putExtra("calling_package", flattenToShortString);
        return voiceIntent;
    }

    private Intent createVoiceAppSearchIntent(Intent intent, SearchableInfo searchableInfo) {
        Intent intent2;
        Bundle bundle;
        Intent intent3;
        String flattenToShortString;
        Intent baseIntent = intent;
        SearchableInfo searchable = searchableInfo;
        ComponentName searchActivity = searchable.getSearchActivity();
        new Intent("android.intent.action.SEARCH");
        Intent queryIntent = intent2;
        Intent component = queryIntent.setComponent(searchActivity);
        PendingIntent pending = PendingIntent.getActivity(getContext(), 0, queryIntent, Declaration.MODULE_REFERENCE);
        new Bundle();
        Bundle queryExtras = bundle;
        if (this.mAppSearchData != null) {
            queryExtras.putParcelable("app_data", this.mAppSearchData);
        }
        new Intent(baseIntent);
        Intent voiceIntent = intent3;
        String languageModel = "free_form";
        String prompt = null;
        String language = null;
        int maxResults = 1;
        Resources resources = getResources();
        if (searchable.getVoiceLanguageModeId() != 0) {
            languageModel = resources.getString(searchable.getVoiceLanguageModeId());
        }
        if (searchable.getVoicePromptTextId() != 0) {
            prompt = resources.getString(searchable.getVoicePromptTextId());
        }
        if (searchable.getVoiceLanguageId() != 0) {
            language = resources.getString(searchable.getVoiceLanguageId());
        }
        if (searchable.getVoiceMaxResults() != 0) {
            maxResults = searchable.getVoiceMaxResults();
        }
        Intent putExtra = voiceIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", languageModel);
        Intent putExtra2 = voiceIntent.putExtra("android.speech.extra.PROMPT", prompt);
        Intent putExtra3 = voiceIntent.putExtra("android.speech.extra.LANGUAGE", language);
        Intent putExtra4 = voiceIntent.putExtra("android.speech.extra.MAX_RESULTS", maxResults);
        Intent intent4 = voiceIntent;
        if (searchActivity == null) {
            flattenToShortString = null;
        } else {
            flattenToShortString = searchActivity.flattenToShortString();
        }
        Intent putExtra5 = intent4.putExtra("calling_package", flattenToShortString);
        Intent putExtra6 = voiceIntent.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", pending);
        Intent putExtra7 = voiceIntent.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", queryExtras);
        return voiceIntent;
    }

    private Intent createIntentFromSuggestion(Cursor cursor, int i, String str) {
        int rowNum;
        StringBuilder sb;
        String id;
        StringBuilder sb2;
        Cursor c = cursor;
        int actionKey = i;
        String actionMsg = str;
        try {
            String action = SuggestionsAdapter.getColumnString(c, "suggest_intent_action");
            if (action == null) {
                action = this.mSearchable.getSuggestIntentAction();
            }
            if (action == null) {
                action = "android.intent.action.SEARCH";
            }
            String data = SuggestionsAdapter.getColumnString(c, "suggest_intent_data");
            if (data == null) {
                data = this.mSearchable.getSuggestIntentData();
            }
            if (!(data == null || (id = SuggestionsAdapter.getColumnString(c, "suggest_intent_data_id")) == null)) {
                new StringBuilder();
                data = sb2.append(data).append("/").append(Uri.encode(id)).toString();
            }
            return createIntent(action, data == null ? null : Uri.parse(data), SuggestionsAdapter.getColumnString(c, "suggest_intent_extra_data"), SuggestionsAdapter.getColumnString(c, "suggest_intent_query"), actionKey, actionMsg);
        } catch (RuntimeException e) {
            RuntimeException e2 = e;
            try {
                rowNum = c.getPosition();
            } catch (RuntimeException e3) {
                RuntimeException runtimeException = e3;
                rowNum = -1;
            }
            new StringBuilder();
            int w = Log.w(LOG_TAG, sb.append("Search suggestions cursor at row ").append(rowNum).append(" returned exception.").toString(), e2);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void forceSuggestionQuery() {
        HIDDEN_METHOD_INVOKER.doBeforeTextChanged(this.mSearchSrcTextView);
        HIDDEN_METHOD_INVOKER.doAfterTextChanged(this.mSearchSrcTextView);
    }

    static boolean isLandscapeMode(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    /* renamed from: android.support.v7.widget.SearchView$UpdatableTouchDelegate */
    private static class UpdatableTouchDelegate extends TouchDelegate {
        private final Rect mActualBounds;
        private boolean mDelegateTargeted;
        private final View mDelegateView;
        private final int mSlop;
        private final Rect mSlopBounds;
        private final Rect mTargetBounds;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public UpdatableTouchDelegate(android.graphics.Rect r9, android.graphics.Rect r10, android.view.View r11) {
            /*
                r8 = this;
                r0 = r8
                r1 = r9
                r2 = r10
                r3 = r11
                r4 = r0
                r5 = r1
                r6 = r3
                r4.<init>(r5, r6)
                r4 = r0
                r5 = r3
                android.content.Context r5 = r5.getContext()
                android.view.ViewConfiguration r5 = android.view.ViewConfiguration.get(r5)
                int r5 = r5.getScaledTouchSlop()
                r4.mSlop = r5
                r4 = r0
                android.graphics.Rect r5 = new android.graphics.Rect
                r7 = r5
                r5 = r7
                r6 = r7
                r6.<init>()
                r4.mTargetBounds = r5
                r4 = r0
                android.graphics.Rect r5 = new android.graphics.Rect
                r7 = r5
                r5 = r7
                r6 = r7
                r6.<init>()
                r4.mSlopBounds = r5
                r4 = r0
                android.graphics.Rect r5 = new android.graphics.Rect
                r7 = r5
                r5 = r7
                r6 = r7
                r6.<init>()
                r4.mActualBounds = r5
                r4 = r0
                r5 = r1
                r6 = r2
                r4.setBounds(r5, r6)
                r4 = r0
                r5 = r3
                r4.mDelegateView = r5
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.SearchView.UpdatableTouchDelegate.<init>(android.graphics.Rect, android.graphics.Rect, android.view.View):void");
        }

        public void setBounds(Rect rect, Rect actualBounds) {
            Rect desiredBounds = rect;
            this.mTargetBounds.set(desiredBounds);
            this.mSlopBounds.set(desiredBounds);
            this.mSlopBounds.inset(-this.mSlop, -this.mSlop);
            this.mActualBounds.set(actualBounds);
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            MotionEvent event = motionEvent;
            int x = (int) event.getX();
            int y = (int) event.getY();
            boolean sendToDelegate = false;
            boolean hit = true;
            boolean handled = false;
            switch (event.getAction()) {
                case 0:
                    if (this.mTargetBounds.contains(x, y)) {
                        this.mDelegateTargeted = true;
                        sendToDelegate = true;
                        break;
                    }
                    break;
                case 1:
                case 2:
                    sendToDelegate = this.mDelegateTargeted;
                    if (sendToDelegate && !this.mSlopBounds.contains(x, y)) {
                        hit = false;
                        break;
                    }
                case 3:
                    sendToDelegate = this.mDelegateTargeted;
                    this.mDelegateTargeted = false;
                    break;
            }
            if (sendToDelegate) {
                if (!hit || this.mActualBounds.contains(x, y)) {
                    event.setLocation((float) (x - this.mActualBounds.left), (float) (y - this.mActualBounds.top));
                } else {
                    event.setLocation((float) (this.mDelegateView.getWidth() / 2), (float) (this.mDelegateView.getHeight() / 2));
                }
                handled = this.mDelegateView.dispatchTouchEvent(event);
            }
            return handled;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* renamed from: android.support.v7.widget.SearchView$SearchAutoComplete */
    public static class SearchAutoComplete extends AppCompatAutoCompleteTextView {
        private boolean mHasPendingShowSoftInputRequest;
        final Runnable mRunShowSoftInputIfNecessary;
        private SearchView mSearchView;
        private int mThreshold;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public SearchAutoComplete(Context context) {
            this(context, (AttributeSet) null);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public SearchAutoComplete(Context context, AttributeSet attrs) {
            this(context, attrs, C0425R.attr.autoCompleteTextViewStyle);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SearchAutoComplete(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            Runnable runnable;
            new Runnable(this) {
                final /* synthetic */ SearchAutoComplete this$0;

                {
                    this.this$0 = this$0;
                }

                public void run() {
                    this.this$0.showSoftInputIfNecessary();
                }
            };
            this.mRunShowSoftInputIfNecessary = runnable;
            this.mThreshold = getThreshold();
        }

        /* access modifiers changed from: protected */
        public void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, (float) getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        /* access modifiers changed from: package-private */
        public void setSearchView(SearchView searchView) {
            SearchView searchView2 = searchView;
            this.mSearchView = searchView2;
        }

        public void setThreshold(int i) {
            int threshold = i;
            super.setThreshold(threshold);
            this.mThreshold = threshold;
        }

        /* access modifiers changed from: package-private */
        public boolean isEmpty() {
            return TextUtils.getTrimmedLength(getText()) == 0;
        }

        /* access modifiers changed from: protected */
        public void replaceText(CharSequence text) {
        }

        public void performCompletion() {
        }

        public void onWindowFocusChanged(boolean z) {
            boolean hasWindowFocus = z;
            super.onWindowFocusChanged(hasWindowFocus);
            if (hasWindowFocus && this.mSearchView.hasFocus() && getVisibility() == 0) {
                this.mHasPendingShowSoftInputRequest = true;
                if (SearchView.isLandscapeMode(getContext())) {
                    SearchView.HIDDEN_METHOD_INVOKER.ensureImeVisible(this, true);
                }
            }
        }

        /* access modifiers changed from: protected */
        public void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
            super.onFocusChanged(focused, direction, previouslyFocusedRect);
            this.mSearchView.onTextFocusChanged();
        }

        public boolean enoughToFilter() {
            return this.mThreshold <= 0 || super.enoughToFilter();
        }

        public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
            int keyCode = i;
            KeyEvent event = keyEvent;
            if (keyCode == 4) {
                if (event.getAction() == 0 && event.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState state = getKeyDispatcherState();
                    if (state != null) {
                        state.startTracking(event, this);
                    }
                    return true;
                } else if (event.getAction() == 1) {
                    KeyEvent.DispatcherState state2 = getKeyDispatcherState();
                    if (state2 != null) {
                        state2.handleUpEvent(event);
                    }
                    if (event.isTracking() && !event.isCanceled()) {
                        this.mSearchView.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(keyCode, event);
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration config = getResources().getConfiguration();
            int widthDp = config.screenWidthDp;
            int heightDp = config.screenHeightDp;
            if (widthDp >= 960 && heightDp >= 720 && config.orientation == 2) {
                return 256;
            }
            if (widthDp >= 600 || (widthDp >= 640 && heightDp >= 480)) {
                return FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE;
            }
            return ComponentConstants.TEXTBOX_PREFERRED_WIDTH;
        }

        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection ic = super.onCreateInputConnection(editorInfo);
            if (this.mHasPendingShowSoftInputRequest) {
                boolean removeCallbacks = removeCallbacks(this.mRunShowSoftInputIfNecessary);
                boolean post = post(this.mRunShowSoftInputIfNecessary);
            }
            return ic;
        }

        /* access modifiers changed from: package-private */
        public void showSoftInputIfNecessary() {
            if (this.mHasPendingShowSoftInputRequest) {
                boolean showSoftInput = ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
                this.mHasPendingShowSoftInputRequest = false;
            }
        }

        /* access modifiers changed from: package-private */
        public void setImeVisibility(boolean visible) {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService("input_method");
            if (!visible) {
                this.mHasPendingShowSoftInputRequest = false;
                boolean removeCallbacks = removeCallbacks(this.mRunShowSoftInputIfNecessary);
                boolean hideSoftInputFromWindow = imm.hideSoftInputFromWindow(getWindowToken(), 0);
            } else if (imm.isActive(this)) {
                this.mHasPendingShowSoftInputRequest = false;
                boolean removeCallbacks2 = removeCallbacks(this.mRunShowSoftInputIfNecessary);
                boolean showSoftInput = imm.showSoftInput(this, 0);
            } else {
                this.mHasPendingShowSoftInputRequest = true;
            }
        }
    }

    /* renamed from: android.support.v7.widget.SearchView$AutoCompleteTextViewReflector */
    private static class AutoCompleteTextViewReflector {
        private Method doAfterTextChanged;
        private Method doBeforeTextChanged;
        private Method ensureImeVisible;
        private Method showSoftInputUnchecked;

        AutoCompleteTextViewReflector() {
            try {
                this.doBeforeTextChanged = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.doBeforeTextChanged.setAccessible(true);
            } catch (NoSuchMethodException e) {
                NoSuchMethodException noSuchMethodException = e;
            }
            try {
                this.doAfterTextChanged = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.doAfterTextChanged.setAccessible(true);
            } catch (NoSuchMethodException e2) {
                NoSuchMethodException noSuchMethodException2 = e2;
            }
            Class<AutoCompleteTextView> cls = AutoCompleteTextView.class;
            try {
                this.ensureImeVisible = cls.getMethod("ensureImeVisible", new Class[]{Boolean.TYPE});
                this.ensureImeVisible.setAccessible(true);
            } catch (NoSuchMethodException e3) {
                NoSuchMethodException noSuchMethodException3 = e3;
            }
        }

        /* access modifiers changed from: package-private */
        public void doBeforeTextChanged(AutoCompleteTextView autoCompleteTextView) {
            AutoCompleteTextView view = autoCompleteTextView;
            if (this.doBeforeTextChanged != null) {
                try {
                    Object invoke = this.doBeforeTextChanged.invoke(view, new Object[0]);
                } catch (Exception e) {
                    Exception exc = e;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void doAfterTextChanged(AutoCompleteTextView autoCompleteTextView) {
            AutoCompleteTextView view = autoCompleteTextView;
            if (this.doAfterTextChanged != null) {
                try {
                    Object invoke = this.doAfterTextChanged.invoke(view, new Object[0]);
                } catch (Exception e) {
                    Exception exc = e;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void ensureImeVisible(AutoCompleteTextView autoCompleteTextView, boolean z) {
            AutoCompleteTextView view = autoCompleteTextView;
            boolean visible = z;
            if (this.ensureImeVisible != null) {
                try {
                    Object invoke = this.ensureImeVisible.invoke(view, new Object[]{Boolean.valueOf(visible)});
                } catch (Exception e) {
                    Exception exc = e;
                }
            }
        }
    }
}
