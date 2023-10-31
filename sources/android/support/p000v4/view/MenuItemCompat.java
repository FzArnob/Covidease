package android.support.p000v4.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.p000v4.internal.view.SupportMenuItem;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

/* renamed from: android.support.v4.view.MenuItemCompat */
public final class MenuItemCompat {
    @Deprecated
    public static final int SHOW_AS_ACTION_ALWAYS = 2;
    @Deprecated
    public static final int SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;
    @Deprecated
    public static final int SHOW_AS_ACTION_IF_ROOM = 1;
    @Deprecated
    public static final int SHOW_AS_ACTION_NEVER = 0;
    @Deprecated
    public static final int SHOW_AS_ACTION_WITH_TEXT = 4;
    private static final String TAG = "MenuItemCompat";

    @Deprecated
    /* renamed from: android.support.v4.view.MenuItemCompat$OnActionExpandListener */
    public interface OnActionExpandListener {
        boolean onMenuItemActionCollapse(MenuItem menuItem);

        boolean onMenuItemActionExpand(MenuItem menuItem);
    }

    @Deprecated
    public static void setShowAsAction(MenuItem item, int actionEnum) {
        item.setShowAsAction(actionEnum);
    }

    @Deprecated
    public static MenuItem setActionView(MenuItem item, View view) {
        return item.setActionView(view);
    }

    @Deprecated
    public static MenuItem setActionView(MenuItem item, int resId) {
        return item.setActionView(resId);
    }

    @Deprecated
    public static View getActionView(MenuItem item) {
        return item.getActionView();
    }

    public static MenuItem setActionProvider(MenuItem menuItem, ActionProvider actionProvider) {
        MenuItem item = menuItem;
        ActionProvider provider = actionProvider;
        if (item instanceof SupportMenuItem) {
            return ((SupportMenuItem) item).setSupportActionProvider(provider);
        }
        int w = Log.w(TAG, "setActionProvider: item does not implement SupportMenuItem; ignoring");
        return item;
    }

    public static ActionProvider getActionProvider(MenuItem menuItem) {
        MenuItem item = menuItem;
        if (item instanceof SupportMenuItem) {
            return ((SupportMenuItem) item).getSupportActionProvider();
        }
        int w = Log.w(TAG, "getActionProvider: item does not implement SupportMenuItem; returning null");
        return null;
    }

    @Deprecated
    public static boolean expandActionView(MenuItem item) {
        return item.expandActionView();
    }

    @Deprecated
    public static boolean collapseActionView(MenuItem item) {
        return item.collapseActionView();
    }

    @Deprecated
    public static boolean isActionViewExpanded(MenuItem item) {
        return item.isActionViewExpanded();
    }

    @Deprecated
    public static MenuItem setOnActionExpandListener(MenuItem item, OnActionExpandListener listener) {
        MenuItem.OnActionExpandListener onActionExpandListener;
        final OnActionExpandListener onActionExpandListener2 = listener;
        new MenuItem.OnActionExpandListener() {
            public boolean onMenuItemActionExpand(MenuItem item) {
                return onActionExpandListener2.onMenuItemActionExpand(item);
            }

            public boolean onMenuItemActionCollapse(MenuItem item) {
                return onActionExpandListener2.onMenuItemActionCollapse(item);
            }
        };
        return item.setOnActionExpandListener(onActionExpandListener);
    }

    public static void setContentDescription(MenuItem menuItem, CharSequence charSequence) {
        MenuItem item = menuItem;
        CharSequence contentDescription = charSequence;
        if (item instanceof SupportMenuItem) {
            SupportMenuItem contentDescription2 = ((SupportMenuItem) item).setContentDescription(contentDescription);
        } else if (Build.VERSION.SDK_INT >= 26) {
            MenuItem contentDescription3 = item.setContentDescription(contentDescription);
        }
    }

    public static CharSequence getContentDescription(MenuItem menuItem) {
        MenuItem item = menuItem;
        if (item instanceof SupportMenuItem) {
            return ((SupportMenuItem) item).getContentDescription();
        }
        if (Build.VERSION.SDK_INT >= 26) {
            return item.getContentDescription();
        }
        return null;
    }

    public static void setTooltipText(MenuItem menuItem, CharSequence charSequence) {
        MenuItem item = menuItem;
        CharSequence tooltipText = charSequence;
        if (item instanceof SupportMenuItem) {
            SupportMenuItem tooltipText2 = ((SupportMenuItem) item).setTooltipText(tooltipText);
        } else if (Build.VERSION.SDK_INT >= 26) {
            MenuItem tooltipText3 = item.setTooltipText(tooltipText);
        }
    }

    public static CharSequence getTooltipText(MenuItem menuItem) {
        MenuItem item = menuItem;
        if (item instanceof SupportMenuItem) {
            return ((SupportMenuItem) item).getTooltipText();
        }
        if (Build.VERSION.SDK_INT >= 26) {
            return item.getTooltipText();
        }
        return null;
    }

    public static void setShortcut(MenuItem menuItem, char c, char c2, int i, int i2) {
        MenuItem item = menuItem;
        char numericChar = c;
        char alphaChar = c2;
        int numericModifiers = i;
        int alphaModifiers = i2;
        if (item instanceof SupportMenuItem) {
            MenuItem shortcut = ((SupportMenuItem) item).setShortcut(numericChar, alphaChar, numericModifiers, alphaModifiers);
        } else if (Build.VERSION.SDK_INT >= 26) {
            MenuItem shortcut2 = item.setShortcut(numericChar, alphaChar, numericModifiers, alphaModifiers);
        }
    }

    public static void setNumericShortcut(MenuItem menuItem, char c, int i) {
        MenuItem item = menuItem;
        char numericChar = c;
        int numericModifiers = i;
        if (item instanceof SupportMenuItem) {
            MenuItem numericShortcut = ((SupportMenuItem) item).setNumericShortcut(numericChar, numericModifiers);
        } else if (Build.VERSION.SDK_INT >= 26) {
            MenuItem numericShortcut2 = item.setNumericShortcut(numericChar, numericModifiers);
        }
    }

    public static int getNumericModifiers(MenuItem menuItem) {
        MenuItem item = menuItem;
        if (item instanceof SupportMenuItem) {
            return ((SupportMenuItem) item).getNumericModifiers();
        }
        if (Build.VERSION.SDK_INT >= 26) {
            return item.getNumericModifiers();
        }
        return 0;
    }

    public static void setAlphabeticShortcut(MenuItem menuItem, char c, int i) {
        MenuItem item = menuItem;
        char alphaChar = c;
        int alphaModifiers = i;
        if (item instanceof SupportMenuItem) {
            MenuItem alphabeticShortcut = ((SupportMenuItem) item).setAlphabeticShortcut(alphaChar, alphaModifiers);
        } else if (Build.VERSION.SDK_INT >= 26) {
            MenuItem alphabeticShortcut2 = item.setAlphabeticShortcut(alphaChar, alphaModifiers);
        }
    }

    public static int getAlphabeticModifiers(MenuItem menuItem) {
        MenuItem item = menuItem;
        if (item instanceof SupportMenuItem) {
            return ((SupportMenuItem) item).getAlphabeticModifiers();
        }
        if (Build.VERSION.SDK_INT >= 26) {
            return item.getAlphabeticModifiers();
        }
        return 0;
    }

    public static void setIconTintList(MenuItem menuItem, ColorStateList colorStateList) {
        MenuItem item = menuItem;
        ColorStateList tint = colorStateList;
        if (item instanceof SupportMenuItem) {
            MenuItem iconTintList = ((SupportMenuItem) item).setIconTintList(tint);
        } else if (Build.VERSION.SDK_INT >= 26) {
            MenuItem iconTintList2 = item.setIconTintList(tint);
        }
    }

    public static ColorStateList getIconTintList(MenuItem menuItem) {
        MenuItem item = menuItem;
        if (item instanceof SupportMenuItem) {
            return ((SupportMenuItem) item).getIconTintList();
        }
        if (Build.VERSION.SDK_INT >= 26) {
            return item.getIconTintList();
        }
        return null;
    }

    public static void setIconTintMode(MenuItem menuItem, PorterDuff.Mode mode) {
        MenuItem item = menuItem;
        PorterDuff.Mode tintMode = mode;
        if (item instanceof SupportMenuItem) {
            MenuItem iconTintMode = ((SupportMenuItem) item).setIconTintMode(tintMode);
        } else if (Build.VERSION.SDK_INT >= 26) {
            MenuItem iconTintMode2 = item.setIconTintMode(tintMode);
        }
    }

    public static PorterDuff.Mode getIconTintMode(MenuItem menuItem) {
        MenuItem item = menuItem;
        if (item instanceof SupportMenuItem) {
            return ((SupportMenuItem) item).getIconTintMode();
        }
        if (Build.VERSION.SDK_INT >= 26) {
            return item.getIconTintMode();
        }
        return null;
    }

    private MenuItemCompat() {
    }
}
