package android.support.p000v4.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import java.util.ArrayList;

/* renamed from: android.support.v4.app.FragmentTabHost */
public class FragmentTabHost extends TabHost implements TabHost.OnTabChangeListener {
    private boolean mAttached;
    private int mContainerId;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private TabInfo mLastTab;
    private TabHost.OnTabChangeListener mOnTabChangeListener;
    private FrameLayout mRealTabContent;
    private final ArrayList<TabInfo> mTabs;

    /* renamed from: android.support.v4.app.FragmentTabHost$TabInfo */
    static final class TabInfo {
        @Nullable
        final Bundle args;
        @NonNull
        final Class<?> clss;
        Fragment fragment;
        @NonNull
        final String tag;

        TabInfo(@NonNull String _tag, @NonNull Class<?> _class, @Nullable Bundle _args) {
            this.tag = _tag;
            this.clss = _class;
            this.args = _args;
        }
    }

    /* renamed from: android.support.v4.app.FragmentTabHost$DummyTabFactory */
    static class DummyTabFactory implements TabHost.TabContentFactory {
        private final Context mContext;

        public DummyTabFactory(Context context) {
            this.mContext = context;
        }

        public View createTabContent(String str) {
            View view;
            String str2 = str;
            new View(this.mContext);
            View v = view;
            v.setMinimumWidth(0);
            v.setMinimumHeight(0);
            return v;
        }
    }

    /* renamed from: android.support.v4.app.FragmentTabHost$SavedState */
    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR;
        String curTab;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        SavedState(Parcelable superState) {
            super(superState);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        SavedState(android.os.Parcel r5) {
            /*
                r4 = this;
                r0 = r4
                r1 = r5
                r2 = r0
                r3 = r1
                r2.<init>(r3)
                r2 = r0
                r3 = r1
                java.lang.String r3 = r3.readString()
                r2.curTab = r3
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentTabHost.SavedState.<init>(android.os.Parcel):void");
        }

        public void writeToParcel(Parcel parcel, int flags) {
            Parcel out = parcel;
            super.writeToParcel(out, flags);
            out.writeString(this.curTab);
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder();
            return sb.append("FragmentTabHost.SavedState{").append(Integer.toHexString(System.identityHashCode(this))).append(" curTab=").append(this.curTab).append("}").toString();
        }

        static {
            Parcelable.Creator<SavedState> creator;
            new Parcelable.Creator<SavedState>() {
                public SavedState createFromParcel(Parcel in) {
                    SavedState savedState;
                    new SavedState(in);
                    return savedState;
                }

                public SavedState[] newArray(int size) {
                    return new SavedState[size];
                }
            };
            CREATOR = creator;
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FragmentTabHost(android.content.Context r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            r4 = 0
            r2.<init>(r3, r4)
            r2 = r0
            java.util.ArrayList r3 = new java.util.ArrayList
            r5 = r3
            r3 = r5
            r4 = r5
            r4.<init>()
            r2.mTabs = r3
            r2 = r0
            r3 = r1
            r4 = 0
            r2.initFragmentTabHost(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentTabHost.<init>(android.content.Context):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FragmentTabHost(android.content.Context r8, android.util.AttributeSet r9) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r0
            r4 = r1
            r5 = r2
            r3.<init>(r4, r5)
            r3 = r0
            java.util.ArrayList r4 = new java.util.ArrayList
            r6 = r4
            r4 = r6
            r5 = r6
            r5.<init>()
            r3.mTabs = r4
            r3 = r0
            r4 = r1
            r5 = r2
            r3.initFragmentTabHost(r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentTabHost.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    private void initFragmentTabHost(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, new int[]{16842995}, 0, 0);
        this.mContainerId = a.getResourceId(0, 0);
        a.recycle();
        super.setOnTabChangedListener(this);
    }

    private void ensureHierarchy(Context context) {
        LinearLayout linearLayout;
        ViewGroup.LayoutParams layoutParams;
        TabWidget tabWidget;
        ViewGroup.LayoutParams layoutParams2;
        FrameLayout frameLayout;
        ViewGroup.LayoutParams layoutParams3;
        FrameLayout frameLayout2;
        ViewGroup.LayoutParams layoutParams4;
        Context context2 = context;
        if (findViewById(16908307) == null) {
            new LinearLayout(context2);
            LinearLayout ll = linearLayout;
            ll.setOrientation(1);
            new FrameLayout.LayoutParams(-1, -1);
            addView(ll, layoutParams);
            new TabWidget(context2);
            TabWidget tw = tabWidget;
            tw.setId(16908307);
            tw.setOrientation(0);
            new LinearLayout.LayoutParams(-1, -2, 0.0f);
            ll.addView(tw, layoutParams2);
            new FrameLayout(context2);
            FrameLayout fl = frameLayout;
            fl.setId(16908305);
            new LinearLayout.LayoutParams(0, 0, 0.0f);
            ll.addView(fl, layoutParams3);
            new FrameLayout(context2);
            FrameLayout fl2 = frameLayout2;
            this.mRealTabContent = fl2;
            this.mRealTabContent.setId(this.mContainerId);
            new LinearLayout.LayoutParams(-1, 0, 1.0f);
            ll.addView(fl2, layoutParams4);
        }
    }

    @Deprecated
    public void setup() {
        Throwable th;
        Throwable th2 = th;
        new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
        throw th2;
    }

    public void setup(Context context, FragmentManager manager) {
        Context context2 = context;
        ensureHierarchy(context2);
        super.setup();
        this.mContext = context2;
        this.mFragmentManager = manager;
        ensureContent();
    }

    public void setup(Context context, FragmentManager manager, int i) {
        Context context2 = context;
        int containerId = i;
        ensureHierarchy(context2);
        super.setup();
        this.mContext = context2;
        this.mFragmentManager = manager;
        this.mContainerId = containerId;
        ensureContent();
        this.mRealTabContent.setId(containerId);
        if (getId() == -1) {
            setId(16908306);
        }
    }

    private void ensureContent() {
        Throwable th;
        StringBuilder sb;
        if (this.mRealTabContent == null) {
            this.mRealTabContent = (FrameLayout) findViewById(this.mContainerId);
            if (this.mRealTabContent == null) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalStateException(sb.append("No tab content FrameLayout found for id ").append(this.mContainerId).toString());
                throw th2;
            }
        }
    }

    public void setOnTabChangedListener(TabHost.OnTabChangeListener l) {
        TabHost.OnTabChangeListener onTabChangeListener = l;
        this.mOnTabChangeListener = onTabChangeListener;
    }

    public void addTab(@NonNull TabHost.TabSpec tabSpec, @NonNull Class<?> clss, @Nullable Bundle args) {
        TabHost.TabContentFactory tabContentFactory;
        TabInfo tabInfo;
        TabHost.TabSpec tabSpec2 = tabSpec;
        new DummyTabFactory(this.mContext);
        TabHost.TabSpec content = tabSpec2.setContent(tabContentFactory);
        String tag = tabSpec2.getTag();
        new TabInfo(tag, clss, args);
        TabInfo info = tabInfo;
        if (this.mAttached) {
            info.fragment = this.mFragmentManager.findFragmentByTag(tag);
            if (info.fragment != null && !info.fragment.isDetached()) {
                FragmentTransaction ft = this.mFragmentManager.beginTransaction();
                FragmentTransaction detach = ft.detach(info.fragment);
                int commit = ft.commit();
            }
        }
        boolean add = this.mTabs.add(info);
        addTab(tabSpec2);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTag = getCurrentTabTag();
        FragmentTransaction ft = null;
        int count = this.mTabs.size();
        for (int i = 0; i < count; i++) {
            TabInfo tab = this.mTabs.get(i);
            tab.fragment = this.mFragmentManager.findFragmentByTag(tab.tag);
            if (tab.fragment != null && !tab.fragment.isDetached()) {
                if (tab.tag.equals(currentTag)) {
                    this.mLastTab = tab;
                } else {
                    if (ft == null) {
                        ft = this.mFragmentManager.beginTransaction();
                    }
                    FragmentTransaction detach = ft.detach(tab.fragment);
                }
            }
        }
        this.mAttached = true;
        FragmentTransaction ft2 = doTabChanged(currentTag, ft);
        if (ft2 != null) {
            int commit = ft2.commit();
            boolean executePendingTransactions = this.mFragmentManager.executePendingTransactions();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAttached = false;
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState;
        new SavedState(super.onSaveInstanceState());
        SavedState ss = savedState;
        ss.curTab = getCurrentTabTag();
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
        setCurrentTabByTag(ss.curTab);
    }

    public void onTabChanged(String str) {
        FragmentTransaction ft;
        String tabId = str;
        if (this.mAttached && (ft = doTabChanged(tabId, (FragmentTransaction) null)) != null) {
            int commit = ft.commit();
        }
        if (this.mOnTabChangeListener != null) {
            this.mOnTabChangeListener.onTabChanged(tabId);
        }
    }

    @Nullable
    private FragmentTransaction doTabChanged(@Nullable String tag, @Nullable FragmentTransaction fragmentTransaction) {
        FragmentTransaction ft = fragmentTransaction;
        TabInfo newTab = getTabInfoForTag(tag);
        if (this.mLastTab != newTab) {
            if (ft == null) {
                ft = this.mFragmentManager.beginTransaction();
            }
            if (!(this.mLastTab == null || this.mLastTab.fragment == null)) {
                FragmentTransaction detach = ft.detach(this.mLastTab.fragment);
            }
            if (newTab != null) {
                if (newTab.fragment == null) {
                    newTab.fragment = Fragment.instantiate(this.mContext, newTab.clss.getName(), newTab.args);
                    FragmentTransaction add = ft.add(this.mContainerId, newTab.fragment, newTab.tag);
                } else {
                    FragmentTransaction attach = ft.attach(newTab.fragment);
                }
            }
            this.mLastTab = newTab;
        }
        return ft;
    }

    @Nullable
    private TabInfo getTabInfoForTag(String str) {
        String tabId = str;
        int count = this.mTabs.size();
        for (int i = 0; i < count; i++) {
            TabInfo tab = this.mTabs.get(i);
            if (tab.tag.equals(tabId)) {
                return tab;
            }
        }
        return null;
    }
}
