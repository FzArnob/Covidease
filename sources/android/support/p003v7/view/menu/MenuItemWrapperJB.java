package android.support.p003v7.view.menu;

import android.content.Context;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.p000v4.internal.view.SupportMenuItem;
import android.support.p000v4.view.ActionProvider;
import android.support.p003v7.view.menu.MenuItemWrapperICS;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;

@RequiresApi(16)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.view.menu.MenuItemWrapperJB */
class MenuItemWrapperJB extends MenuItemWrapperICS {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MenuItemWrapperJB(Context context, SupportMenuItem object) {
        super(context, object);
    }

    /* access modifiers changed from: package-private */
    public MenuItemWrapperICS.ActionProviderWrapper createActionProviderWrapper(ActionProvider provider) {
        MenuItemWrapperICS.ActionProviderWrapper actionProviderWrapper;
        new ActionProviderWrapperJB(this, this.mContext, provider);
        return actionProviderWrapper;
    }

    /* renamed from: android.support.v7.view.menu.MenuItemWrapperJB$ActionProviderWrapperJB */
    class ActionProviderWrapperJB extends MenuItemWrapperICS.ActionProviderWrapper implements ActionProvider.VisibilityListener {
        ActionProvider.VisibilityListener mListener;
        final /* synthetic */ MenuItemWrapperJB this$0;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public ActionProviderWrapperJB(android.support.p003v7.view.menu.MenuItemWrapperJB r9, android.content.Context r10, android.view.ActionProvider r11) {
            /*
                r8 = this;
                r0 = r8
                r1 = r9
                r2 = r10
                r3 = r11
                r4 = r0
                r5 = r1
                r4.this$0 = r5
                r4 = r0
                r5 = r1
                r6 = r2
                r7 = r3
                r4.<init>(r5, r6, r7)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.view.menu.MenuItemWrapperJB.ActionProviderWrapperJB.<init>(android.support.v7.view.menu.MenuItemWrapperJB, android.content.Context, android.view.ActionProvider):void");
        }

        public View onCreateActionView(MenuItem forItem) {
            return this.mInner.onCreateActionView(forItem);
        }

        public boolean overridesItemVisibility() {
            return this.mInner.overridesItemVisibility();
        }

        public boolean isVisible() {
            return this.mInner.isVisible();
        }

        public void refreshVisibility() {
            this.mInner.refreshVisibility();
        }

        public void setVisibilityListener(ActionProvider.VisibilityListener visibilityListener) {
            ActionProvider.VisibilityListener listener = visibilityListener;
            this.mListener = listener;
            this.mInner.setVisibilityListener(listener != null ? this : null);
        }

        public void onActionProviderVisibilityChanged(boolean z) {
            boolean isVisible = z;
            if (this.mListener != null) {
                this.mListener.onActionProviderVisibilityChanged(isVisible);
            }
        }
    }
}
