package android.support.p003v7.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.support.annotation.LayoutRes;
import android.support.annotation.RestrictTo;
import android.support.p000v4.internal.view.SupportMenu;
import android.support.p000v4.view.ActionProvider;
import android.support.p000v4.view.MenuItemCompat;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.view.menu.MenuItemImpl;
import android.support.p003v7.view.menu.MenuItemWrapperICS;
import android.support.p003v7.widget.DrawableUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.view.SupportMenuInflater */
public class SupportMenuInflater extends MenuInflater {
    static final Class<?>[] ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE = ACTION_VIEW_CONSTRUCTOR_SIGNATURE;
    static final Class<?>[] ACTION_VIEW_CONSTRUCTOR_SIGNATURE = {Context.class};
    static final String LOG_TAG = "SupportMenuInflater";
    static final int NO_ID = 0;
    private static final String XML_GROUP = "group";
    private static final String XML_ITEM = "item";
    private static final String XML_MENU = "menu";
    final Object[] mActionProviderConstructorArguments = this.mActionViewConstructorArguments;
    final Object[] mActionViewConstructorArguments;
    Context mContext;
    private Object mRealOwner;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SupportMenuInflater(android.content.Context r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            r2.mContext = r3
            r2 = r0
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 0
            r6 = r1
            r4[r5] = r6
            r2.mActionViewConstructorArguments = r3
            r2 = r0
            r3 = r0
            java.lang.Object[] r3 = r3.mActionViewConstructorArguments
            r2.mActionProviderConstructorArguments = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.view.SupportMenuInflater.<init>(android.content.Context):void");
    }

    public void inflate(@LayoutRes int i, Menu menu) {
        Throwable th;
        Throwable th2;
        int menuRes = i;
        Menu menu2 = menu;
        if (!(menu2 instanceof SupportMenu)) {
            super.inflate(menuRes, menu2);
            return;
        }
        XmlResourceParser parser = null;
        try {
            parser = this.mContext.getResources().getLayout(menuRes);
            parseMenu(parser, Xml.asAttributeSet(parser), menu2);
            if (parser != null) {
                parser.close();
            }
        } catch (XmlPullParserException e) {
            XmlPullParserException e2 = e;
            Throwable th3 = th2;
            new InflateException("Error inflating menu XML", e2);
            throw th3;
        } catch (IOException e3) {
            IOException e4 = e3;
            Throwable th4 = th;
            new InflateException("Error inflating menu XML", e4);
            throw th4;
        } catch (Throwable th5) {
            Throwable th6 = th5;
            if (parser != null) {
                parser.close();
            }
            throw th6;
        }
    }

    private void parseMenu(XmlPullParser xmlPullParser, AttributeSet attributeSet, Menu menu) throws XmlPullParserException, IOException {
        MenuState menuState;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        XmlPullParser parser = xmlPullParser;
        AttributeSet attrs = attributeSet;
        new MenuState(this, menu);
        MenuState menuState2 = menuState;
        int eventType = parser.getEventType();
        boolean lookingForEndOfUnknownTag = false;
        String unknownTagName = null;
        while (true) {
            if (eventType != 2) {
                eventType = parser.next();
                if (eventType == 1) {
                    break;
                }
            } else {
                String tagName = parser.getName();
                if (tagName.equals(XML_MENU)) {
                    eventType = parser.next();
                } else {
                    Throwable th3 = th;
                    new StringBuilder();
                    new RuntimeException(sb.append("Expecting menu, got ").append(tagName).toString());
                    throw th3;
                }
            }
        }
        boolean reachedEndOfMenu = false;
        while (!reachedEndOfMenu) {
            switch (eventType) {
                case 1:
                    Throwable th4 = th2;
                    new RuntimeException("Unexpected end of document");
                    throw th4;
                case 2:
                    if (!lookingForEndOfUnknownTag) {
                        String tagName2 = parser.getName();
                        if (!tagName2.equals(XML_GROUP)) {
                            if (!tagName2.equals(XML_ITEM)) {
                                if (!tagName2.equals(XML_MENU)) {
                                    lookingForEndOfUnknownTag = true;
                                    unknownTagName = tagName2;
                                    break;
                                } else {
                                    parseMenu(parser, attrs, menuState2.addSubMenuItem());
                                    break;
                                }
                            } else {
                                menuState2.readItem(attrs);
                                break;
                            }
                        } else {
                            menuState2.readGroup(attrs);
                            break;
                        }
                    } else {
                        break;
                    }
                case 3:
                    String tagName3 = parser.getName();
                    if (!lookingForEndOfUnknownTag || !tagName3.equals(unknownTagName)) {
                        if (!tagName3.equals(XML_GROUP)) {
                            if (!tagName3.equals(XML_ITEM)) {
                                if (!tagName3.equals(XML_MENU)) {
                                    break;
                                } else {
                                    reachedEndOfMenu = true;
                                    break;
                                }
                            } else if (!menuState2.hasAddedItem()) {
                                if (menuState2.itemActionProvider != null && menuState2.itemActionProvider.hasSubMenu()) {
                                    SubMenu addSubMenuItem = menuState2.addSubMenuItem();
                                    break;
                                } else {
                                    menuState2.addItem();
                                    break;
                                }
                            } else {
                                break;
                            }
                        } else {
                            menuState2.resetGroup();
                            break;
                        }
                    } else {
                        lookingForEndOfUnknownTag = false;
                        unknownTagName = null;
                        break;
                    }
            }
            eventType = parser.next();
        }
    }

    /* access modifiers changed from: package-private */
    public Object getRealOwner() {
        if (this.mRealOwner == null) {
            this.mRealOwner = findRealOwner(this.mContext);
        }
        return this.mRealOwner;
    }

    private Object findRealOwner(Object obj) {
        Object owner = obj;
        if (owner instanceof Activity) {
            return owner;
        }
        if (owner instanceof ContextWrapper) {
            return findRealOwner(((ContextWrapper) owner).getBaseContext());
        }
        return owner;
    }

    /* renamed from: android.support.v7.view.SupportMenuInflater$InflatedOnMenuItemClickListener */
    private static class InflatedOnMenuItemClickListener implements MenuItem.OnMenuItemClickListener {
        private static final Class<?>[] PARAM_TYPES = {MenuItem.class};
        private Method mMethod;
        private Object mRealOwner;

        public InflatedOnMenuItemClickListener(Object obj, String str) {
            InflateException inflateException;
            StringBuilder sb;
            Object realOwner = obj;
            String methodName = str;
            this.mRealOwner = realOwner;
            Class<?> c = realOwner.getClass();
            try {
                this.mMethod = c.getMethod(methodName, PARAM_TYPES);
            } catch (Exception e) {
                new StringBuilder();
                new InflateException(sb.append("Couldn't resolve menu item onClick handler ").append(methodName).append(" in class ").append(c.getName()).toString());
                InflateException ex = inflateException;
                Throwable initCause = ex.initCause(e);
                throw ex;
            }
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            Throwable th;
            MenuItem item = menuItem;
            try {
                if (this.mMethod.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.mMethod.invoke(this.mRealOwner, new Object[]{item})).booleanValue();
                }
                Object invoke = this.mMethod.invoke(this.mRealOwner, new Object[]{item});
                return true;
            } catch (Exception e) {
                Exception e2 = e;
                Throwable th2 = th;
                new RuntimeException(e2);
                throw th2;
            }
        }
    }

    /* renamed from: android.support.v7.view.SupportMenuInflater$MenuState */
    private class MenuState {
        private static final int defaultGroupId = 0;
        private static final int defaultItemCategory = 0;
        private static final int defaultItemCheckable = 0;
        private static final boolean defaultItemChecked = false;
        private static final boolean defaultItemEnabled = true;
        private static final int defaultItemId = 0;
        private static final int defaultItemOrder = 0;
        private static final boolean defaultItemVisible = true;
        private int groupCategory;
        private int groupCheckable;
        private boolean groupEnabled;
        private int groupId;
        private int groupOrder;
        private boolean groupVisible;
        ActionProvider itemActionProvider;
        private String itemActionProviderClassName;
        private String itemActionViewClassName;
        private int itemActionViewLayout;
        private boolean itemAdded;
        private int itemAlphabeticModifiers;
        private char itemAlphabeticShortcut;
        private int itemCategoryOrder;
        private int itemCheckable;
        private boolean itemChecked;
        private CharSequence itemContentDescription;
        private boolean itemEnabled;
        private int itemIconResId;
        private ColorStateList itemIconTintList = null;
        private PorterDuff.Mode itemIconTintMode = null;
        private int itemId;
        private String itemListenerMethodName;
        private int itemNumericModifiers;
        private char itemNumericShortcut;
        private int itemShowAsAction;
        private CharSequence itemTitle;
        private CharSequence itemTitleCondensed;
        private CharSequence itemTooltipText;
        private boolean itemVisible;
        private Menu menu;
        final /* synthetic */ SupportMenuInflater this$0;

        public MenuState(SupportMenuInflater supportMenuInflater, Menu menu2) {
            this.this$0 = supportMenuInflater;
            this.menu = menu2;
            resetGroup();
        }

        public void resetGroup() {
            this.groupId = 0;
            this.groupCategory = 0;
            this.groupOrder = 0;
            this.groupCheckable = 0;
            this.groupVisible = true;
            this.groupEnabled = true;
        }

        public void readGroup(AttributeSet attrs) {
            TypedArray a = this.this$0.mContext.obtainStyledAttributes(attrs, C0425R.styleable.MenuGroup);
            this.groupId = a.getResourceId(C0425R.styleable.MenuGroup_android_id, 0);
            this.groupCategory = a.getInt(C0425R.styleable.MenuGroup_android_menuCategory, 0);
            this.groupOrder = a.getInt(C0425R.styleable.MenuGroup_android_orderInCategory, 0);
            this.groupCheckable = a.getInt(C0425R.styleable.MenuGroup_android_checkableBehavior, 0);
            this.groupVisible = a.getBoolean(C0425R.styleable.MenuGroup_android_visible, true);
            this.groupEnabled = a.getBoolean(C0425R.styleable.MenuGroup_android_enabled, true);
            a.recycle();
        }

        public void readItem(AttributeSet attrs) {
            TypedArray a = this.this$0.mContext.obtainStyledAttributes(attrs, C0425R.styleable.MenuItem);
            this.itemId = a.getResourceId(C0425R.styleable.MenuItem_android_id, 0);
            this.itemCategoryOrder = (a.getInt(C0425R.styleable.MenuItem_android_menuCategory, this.groupCategory) & SupportMenu.CATEGORY_MASK) | (a.getInt(C0425R.styleable.MenuItem_android_orderInCategory, this.groupOrder) & 65535);
            this.itemTitle = a.getText(C0425R.styleable.MenuItem_android_title);
            this.itemTitleCondensed = a.getText(C0425R.styleable.MenuItem_android_titleCondensed);
            this.itemIconResId = a.getResourceId(C0425R.styleable.MenuItem_android_icon, 0);
            this.itemAlphabeticShortcut = getShortcut(a.getString(C0425R.styleable.MenuItem_android_alphabeticShortcut));
            this.itemAlphabeticModifiers = a.getInt(C0425R.styleable.MenuItem_alphabeticModifiers, 4096);
            this.itemNumericShortcut = getShortcut(a.getString(C0425R.styleable.MenuItem_android_numericShortcut));
            this.itemNumericModifiers = a.getInt(C0425R.styleable.MenuItem_numericModifiers, 4096);
            if (a.hasValue(C0425R.styleable.MenuItem_android_checkable)) {
                this.itemCheckable = a.getBoolean(C0425R.styleable.MenuItem_android_checkable, false) ? 1 : 0;
            } else {
                this.itemCheckable = this.groupCheckable;
            }
            this.itemChecked = a.getBoolean(C0425R.styleable.MenuItem_android_checked, false);
            this.itemVisible = a.getBoolean(C0425R.styleable.MenuItem_android_visible, this.groupVisible);
            this.itemEnabled = a.getBoolean(C0425R.styleable.MenuItem_android_enabled, this.groupEnabled);
            this.itemShowAsAction = a.getInt(C0425R.styleable.MenuItem_showAsAction, -1);
            this.itemListenerMethodName = a.getString(C0425R.styleable.MenuItem_android_onClick);
            this.itemActionViewLayout = a.getResourceId(C0425R.styleable.MenuItem_actionLayout, 0);
            this.itemActionViewClassName = a.getString(C0425R.styleable.MenuItem_actionViewClass);
            this.itemActionProviderClassName = a.getString(C0425R.styleable.MenuItem_actionProviderClass);
            boolean hasActionProvider = this.itemActionProviderClassName != null;
            if (hasActionProvider && this.itemActionViewLayout == 0 && this.itemActionViewClassName == null) {
                this.itemActionProvider = (ActionProvider) newInstance(this.itemActionProviderClassName, SupportMenuInflater.ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE, this.this$0.mActionProviderConstructorArguments);
            } else {
                if (hasActionProvider) {
                    int w = Log.w(SupportMenuInflater.LOG_TAG, "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.itemActionProvider = null;
            }
            this.itemContentDescription = a.getText(C0425R.styleable.MenuItem_contentDescription);
            this.itemTooltipText = a.getText(C0425R.styleable.MenuItem_tooltipText);
            if (a.hasValue(C0425R.styleable.MenuItem_iconTintMode)) {
                this.itemIconTintMode = DrawableUtils.parseTintMode(a.getInt(C0425R.styleable.MenuItem_iconTintMode, -1), this.itemIconTintMode);
            } else {
                this.itemIconTintMode = null;
            }
            if (a.hasValue(C0425R.styleable.MenuItem_iconTint)) {
                this.itemIconTintList = a.getColorStateList(C0425R.styleable.MenuItem_iconTint);
            } else {
                this.itemIconTintList = null;
            }
            a.recycle();
            this.itemAdded = false;
        }

        private char getShortcut(String str) {
            String shortcutString = str;
            if (shortcutString == null) {
                return 0;
            }
            return shortcutString.charAt(0);
        }

        private void setItem(MenuItem menuItem) {
            MenuItem.OnMenuItemClickListener onMenuItemClickListener;
            Throwable th;
            MenuItem item = menuItem;
            MenuItem icon = item.setChecked(this.itemChecked).setVisible(this.itemVisible).setEnabled(this.itemEnabled).setCheckable(this.itemCheckable >= 1).setTitleCondensed(this.itemTitleCondensed).setIcon(this.itemIconResId);
            if (this.itemShowAsAction >= 0) {
                item.setShowAsAction(this.itemShowAsAction);
            }
            if (this.itemListenerMethodName != null) {
                if (this.this$0.mContext.isRestricted()) {
                    Throwable th2 = th;
                    new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                    throw th2;
                }
                new InflatedOnMenuItemClickListener(this.this$0.getRealOwner(), this.itemListenerMethodName);
                MenuItem onMenuItemClickListener2 = item.setOnMenuItemClickListener(onMenuItemClickListener);
            }
            MenuItemImpl menuItemImpl = item instanceof MenuItemImpl ? (MenuItemImpl) item : null;
            if (this.itemCheckable >= 2) {
                if (item instanceof MenuItemImpl) {
                    ((MenuItemImpl) item).setExclusiveCheckable(true);
                } else if (item instanceof MenuItemWrapperICS) {
                    ((MenuItemWrapperICS) item).setExclusiveCheckable(true);
                }
            }
            boolean actionViewSpecified = false;
            if (this.itemActionViewClassName != null) {
                MenuItem actionView = item.setActionView((View) newInstance(this.itemActionViewClassName, SupportMenuInflater.ACTION_VIEW_CONSTRUCTOR_SIGNATURE, this.this$0.mActionViewConstructorArguments));
                actionViewSpecified = true;
            }
            if (this.itemActionViewLayout > 0) {
                if (!actionViewSpecified) {
                    MenuItem actionView2 = item.setActionView(this.itemActionViewLayout);
                } else {
                    int w = Log.w(SupportMenuInflater.LOG_TAG, "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                }
            }
            if (this.itemActionProvider != null) {
                MenuItem actionProvider = MenuItemCompat.setActionProvider(item, this.itemActionProvider);
            }
            MenuItemCompat.setContentDescription(item, this.itemContentDescription);
            MenuItemCompat.setTooltipText(item, this.itemTooltipText);
            MenuItemCompat.setAlphabeticShortcut(item, this.itemAlphabeticShortcut, this.itemAlphabeticModifiers);
            MenuItemCompat.setNumericShortcut(item, this.itemNumericShortcut, this.itemNumericModifiers);
            if (this.itemIconTintMode != null) {
                MenuItemCompat.setIconTintMode(item, this.itemIconTintMode);
            }
            if (this.itemIconTintList != null) {
                MenuItemCompat.setIconTintList(item, this.itemIconTintList);
            }
        }

        public void addItem() {
            this.itemAdded = true;
            setItem(this.menu.add(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle));
        }

        public SubMenu addSubMenuItem() {
            this.itemAdded = true;
            SubMenu subMenu = this.menu.addSubMenu(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle);
            setItem(subMenu.getItem());
            return subMenu;
        }

        public boolean hasAddedItem() {
            return this.itemAdded;
        }

        private <T> T newInstance(String str, Class<?>[] constructorSignature, Object[] objArr) {
            StringBuilder sb;
            String className = str;
            Object[] arguments = objArr;
            try {
                Constructor<?> constructor = this.this$0.mContext.getClassLoader().loadClass(className).getConstructor(constructorSignature);
                constructor.setAccessible(true);
                return constructor.newInstance(arguments);
            } catch (Exception e) {
                new StringBuilder();
                int w = Log.w(SupportMenuInflater.LOG_TAG, sb.append("Cannot instantiate class: ").append(className).toString(), e);
                return null;
            }
        }
    }
}
