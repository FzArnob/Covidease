package android.support.p000v4.widget;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.util.C1651SparseArrayCompat;
import android.support.p000v4.view.AccessibilityDelegateCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.ViewParentCompat;
import android.support.p000v4.view.accessibility.AccessibilityEventCompat;
import android.support.p000v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.p000v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.support.p000v4.view.accessibility.AccessibilityRecordCompat;
import android.support.p000v4.widget.FocusStrategy;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import java.util.ArrayList;
import java.util.List;

/* renamed from: android.support.v4.widget.ExploreByTouchHelper */
public abstract class ExploreByTouchHelper extends AccessibilityDelegateCompat {
    private static final String DEFAULT_CLASS_NAME = "android.view.View";
    public static final int HOST_ID = -1;
    public static final int INVALID_ID = Integer.MIN_VALUE;
    private static final Rect INVALID_PARENT_BOUNDS;
    private static final FocusStrategy.BoundsAdapter<AccessibilityNodeInfoCompat> NODE_ADAPTER;
    private static final FocusStrategy.CollectionAdapter<C1651SparseArrayCompat<AccessibilityNodeInfoCompat>, AccessibilityNodeInfoCompat> SPARSE_VALUES_ADAPTER;
    int mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
    private final View mHost;
    private int mHoveredVirtualViewId = Integer.MIN_VALUE;
    int mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
    private final AccessibilityManager mManager;
    private MyNodeProvider mNodeProvider;
    private final int[] mTempGlobalRect = new int[2];
    private final Rect mTempParentRect;
    private final Rect mTempScreenRect;
    private final Rect mTempVisibleRect;

    /* access modifiers changed from: protected */
    public abstract int getVirtualViewAt(float f, float f2);

    /* access modifiers changed from: protected */
    public abstract void getVisibleVirtualViews(List<Integer> list);

    /* access modifiers changed from: protected */
    public abstract boolean onPerformActionForVirtualView(int i, int i2, @Nullable Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract void onPopulateNodeForVirtualView(int i, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);

    static {
        Rect rect;
        FocusStrategy.BoundsAdapter<AccessibilityNodeInfoCompat> boundsAdapter;
        FocusStrategy.CollectionAdapter<C1651SparseArrayCompat<AccessibilityNodeInfoCompat>, AccessibilityNodeInfoCompat> collectionAdapter;
        new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        INVALID_PARENT_BOUNDS = rect;
        new FocusStrategy.BoundsAdapter<AccessibilityNodeInfoCompat>() {
            public void obtainBounds(AccessibilityNodeInfoCompat node, Rect outBounds) {
                node.getBoundsInParent(outBounds);
            }
        };
        NODE_ADAPTER = boundsAdapter;
        new FocusStrategy.CollectionAdapter<C1651SparseArrayCompat<AccessibilityNodeInfoCompat>, AccessibilityNodeInfoCompat>() {
            public AccessibilityNodeInfoCompat get(C1651SparseArrayCompat<AccessibilityNodeInfoCompat> collection, int index) {
                return collection.valueAt(index);
            }

            public int size(C1651SparseArrayCompat<AccessibilityNodeInfoCompat> collection) {
                return collection.size();
            }
        };
        SPARSE_VALUES_ADAPTER = collectionAdapter;
    }

    public ExploreByTouchHelper(@NonNull View view) {
        Rect rect;
        Rect rect2;
        Rect rect3;
        Throwable th;
        View host = view;
        new Rect();
        this.mTempScreenRect = rect;
        new Rect();
        this.mTempParentRect = rect2;
        new Rect();
        this.mTempVisibleRect = rect3;
        if (host == null) {
            Throwable th2 = th;
            new IllegalArgumentException("View may not be null");
            throw th2;
        }
        this.mHost = host;
        this.mManager = (AccessibilityManager) host.getContext().getSystemService("accessibility");
        host.setFocusable(true);
        if (ViewCompat.getImportantForAccessibility(host) == 0) {
            ViewCompat.setImportantForAccessibility(host, 1);
        }
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view) {
        MyNodeProvider myNodeProvider;
        View view2 = view;
        if (this.mNodeProvider == null) {
            new MyNodeProvider(this);
            this.mNodeProvider = myNodeProvider;
        }
        return this.mNodeProvider;
    }

    public final boolean dispatchHoverEvent(@NonNull MotionEvent motionEvent) {
        MotionEvent event = motionEvent;
        if (!this.mManager.isEnabled() || !this.mManager.isTouchExplorationEnabled()) {
            return false;
        }
        switch (event.getAction()) {
            case 7:
            case 9:
                int virtualViewId = getVirtualViewAt(event.getX(), event.getY());
                updateHoveredVirtualView(virtualViewId);
                return virtualViewId != Integer.MIN_VALUE;
            case 10:
                if (this.mHoveredVirtualViewId == Integer.MIN_VALUE) {
                    return false;
                }
                updateHoveredVirtualView(Integer.MIN_VALUE);
                return true;
            default:
                return false;
        }
    }

    public final boolean dispatchKeyEvent(@NonNull KeyEvent keyEvent) {
        KeyEvent event = keyEvent;
        boolean handled = false;
        if (event.getAction() != 1) {
            int keyCode = event.getKeyCode();
            switch (keyCode) {
                case 19:
                case 20:
                case 21:
                case 22:
                    if (event.hasNoModifiers()) {
                        int direction = keyToDirection(keyCode);
                        int count = 1 + event.getRepeatCount();
                        for (int i = 0; i < count && moveFocus(direction, (Rect) null); i++) {
                            handled = true;
                        }
                    }
                    break;
                case 23:
                case 66:
                    if (event.hasNoModifiers() && event.getRepeatCount() == 0) {
                        boolean clickKeyboardFocusedVirtualView = clickKeyboardFocusedVirtualView();
                        handled = true;
                        break;
                    }
                case 61:
                    if (!event.hasNoModifiers()) {
                        if (event.hasModifiers(1)) {
                            handled = moveFocus(1, (Rect) null);
                            break;
                        }
                    } else {
                        handled = moveFocus(2, (Rect) null);
                        break;
                    }
                    break;
            }
        }
        return handled;
    }

    public final void onFocusChanged(boolean z, int i, @Nullable Rect rect) {
        boolean gainFocus = z;
        int direction = i;
        Rect previouslyFocusedRect = rect;
        if (this.mKeyboardFocusedVirtualViewId != Integer.MIN_VALUE) {
            boolean clearKeyboardFocusForVirtualView = clearKeyboardFocusForVirtualView(this.mKeyboardFocusedVirtualViewId);
        }
        if (gainFocus) {
            boolean moveFocus = moveFocus(direction, previouslyFocusedRect);
        }
    }

    public final int getAccessibilityFocusedVirtualViewId() {
        return this.mAccessibilityFocusedVirtualViewId;
    }

    public final int getKeyboardFocusedVirtualViewId() {
        return this.mKeyboardFocusedVirtualViewId;
    }

    private static int keyToDirection(int keyCode) {
        switch (keyCode) {
            case 19:
                return 33;
            case 21:
                return 17;
            case 22:
                return 66;
            default:
                return 130;
        }
    }

    private void getBoundsInParent(int virtualViewId, Rect outBounds) {
        obtainAccessibilityNodeInfo(virtualViewId).getBoundsInParent(outBounds);
    }

    private boolean moveFocus(int i, @Nullable Rect rect) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat;
        Rect rect2;
        AccessibilityNodeInfoCompat nextFocusedNode;
        int nextFocusedNodeId;
        Throwable th;
        int direction = i;
        Rect previouslyFocusedRect = rect;
        C1651SparseArrayCompat<AccessibilityNodeInfoCompat> allNodes = getAllNodes();
        int focusedNodeId = this.mKeyboardFocusedVirtualViewId;
        if (focusedNodeId == Integer.MIN_VALUE) {
            accessibilityNodeInfoCompat = null;
        } else {
            accessibilityNodeInfoCompat = allNodes.get(focusedNodeId);
        }
        AccessibilityNodeInfoCompat focusedNode = accessibilityNodeInfoCompat;
        switch (direction) {
            case 1:
            case 2:
                nextFocusedNode = (AccessibilityNodeInfoCompat) FocusStrategy.findNextFocusInRelativeDirection(allNodes, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, focusedNode, direction, ViewCompat.getLayoutDirection(this.mHost) == 1, false);
                break;
            case 17:
            case 33:
            case 66:
            case 130:
                new Rect();
                Rect selectedRect = rect2;
                if (this.mKeyboardFocusedVirtualViewId != Integer.MIN_VALUE) {
                    getBoundsInParent(this.mKeyboardFocusedVirtualViewId, selectedRect);
                } else if (previouslyFocusedRect != null) {
                    selectedRect.set(previouslyFocusedRect);
                } else {
                    Rect guessPreviouslyFocusedRect = guessPreviouslyFocusedRect(this.mHost, direction, selectedRect);
                }
                nextFocusedNode = (AccessibilityNodeInfoCompat) FocusStrategy.findNextFocusInAbsoluteDirection(allNodes, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, focusedNode, selectedRect, direction);
                break;
            default:
                Throwable th2 = th;
                new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                throw th2;
        }
        if (nextFocusedNode == null) {
            nextFocusedNodeId = Integer.MIN_VALUE;
        } else {
            nextFocusedNodeId = allNodes.keyAt(allNodes.indexOfValue(nextFocusedNode));
        }
        return requestKeyboardFocusForVirtualView(nextFocusedNodeId);
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.support.p000v4.util.C1651SparseArrayCompat<android.support.p000v4.view.accessibility.AccessibilityNodeInfoCompat> getAllNodes() {
        /*
            r9 = this;
            r0 = r9
            java.util.ArrayList r5 = new java.util.ArrayList
            r8 = r5
            r5 = r8
            r6 = r8
            r6.<init>()
            r1 = r5
            r5 = r0
            r6 = r1
            r5.getVisibleVirtualViews(r6)
            android.support.v4.util.SparseArrayCompat r5 = new android.support.v4.util.SparseArrayCompat
            r8 = r5
            r5 = r8
            r6 = r8
            r6.<init>()
            r2 = r5
            r5 = 0
            r3 = r5
        L_0x001a:
            r5 = r3
            r6 = r1
            int r6 = r6.size()
            if (r5 >= r6) goto L_0x0032
            r5 = r0
            r6 = r3
            android.support.v4.view.accessibility.AccessibilityNodeInfoCompat r5 = r5.createNodeForChild(r6)
            r4 = r5
            r5 = r2
            r6 = r3
            r7 = r4
            r5.put(r6, r7)
            int r3 = r3 + 1
            goto L_0x001a
        L_0x0032:
            r5 = r2
            r0 = r5
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.widget.ExploreByTouchHelper.getAllNodes():android.support.v4.util.SparseArrayCompat");
    }

    private static Rect guessPreviouslyFocusedRect(@NonNull View view, int direction, @NonNull Rect rect) {
        Throwable th;
        View host = view;
        Rect outBounds = rect;
        int w = host.getWidth();
        int h = host.getHeight();
        switch (direction) {
            case 17:
                outBounds.set(w, 0, w, h);
                break;
            case 33:
                outBounds.set(0, h, w, h);
                break;
            case 66:
                outBounds.set(-1, 0, -1, h);
                break;
            case 130:
                outBounds.set(0, -1, w, -1);
                break;
            default:
                Throwable th2 = th;
                new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                throw th2;
        }
        return outBounds;
    }

    private boolean clickKeyboardFocusedVirtualView() {
        return this.mKeyboardFocusedVirtualViewId != Integer.MIN_VALUE && onPerformActionForVirtualView(this.mKeyboardFocusedVirtualViewId, 16, (Bundle) null);
    }

    public final boolean sendEventForVirtualView(int i, int i2) {
        int virtualViewId = i;
        int eventType = i2;
        if (virtualViewId == Integer.MIN_VALUE || !this.mManager.isEnabled()) {
            return false;
        }
        ViewParent parent = this.mHost.getParent();
        if (parent == null) {
            return false;
        }
        return ViewParentCompat.requestSendAccessibilityEvent(parent, this.mHost, createEvent(virtualViewId, eventType));
    }

    public final void invalidateRoot() {
        invalidateVirtualView(-1, 1);
    }

    public final void invalidateVirtualView(int virtualViewId) {
        invalidateVirtualView(virtualViewId, 0);
    }

    public final void invalidateVirtualView(int i, int i2) {
        ViewParent parent;
        int virtualViewId = i;
        int changeTypes = i2;
        if (virtualViewId != Integer.MIN_VALUE && this.mManager.isEnabled() && (parent = this.mHost.getParent()) != null) {
            AccessibilityEvent event = createEvent(virtualViewId, 2048);
            AccessibilityEventCompat.setContentChangeTypes(event, changeTypes);
            boolean requestSendAccessibilityEvent = ViewParentCompat.requestSendAccessibilityEvent(parent, this.mHost, event);
        }
    }

    @Deprecated
    public int getFocusedVirtualView() {
        return getAccessibilityFocusedVirtualViewId();
    }

    /* access modifiers changed from: protected */
    public void onVirtualViewKeyboardFocusChanged(int virtualViewId, boolean hasFocus) {
    }

    private void updateHoveredVirtualView(int i) {
        int virtualViewId = i;
        if (this.mHoveredVirtualViewId != virtualViewId) {
            int previousVirtualViewId = this.mHoveredVirtualViewId;
            this.mHoveredVirtualViewId = virtualViewId;
            boolean sendEventForVirtualView = sendEventForVirtualView(virtualViewId, 128);
            boolean sendEventForVirtualView2 = sendEventForVirtualView(previousVirtualViewId, 256);
        }
    }

    private AccessibilityEvent createEvent(int i, int i2) {
        int virtualViewId = i;
        int eventType = i2;
        switch (virtualViewId) {
            case -1:
                return createEventForHost(eventType);
            default:
                return createEventForChild(virtualViewId, eventType);
        }
    }

    private AccessibilityEvent createEventForHost(int eventType) {
        AccessibilityEvent event = AccessibilityEvent.obtain(eventType);
        this.mHost.onInitializeAccessibilityEvent(event);
        return event;
    }

    public void onInitializeAccessibilityEvent(View host, AccessibilityEvent accessibilityEvent) {
        AccessibilityEvent event = accessibilityEvent;
        super.onInitializeAccessibilityEvent(host, event);
        onPopulateEventForHost(event);
    }

    private AccessibilityEvent createEventForChild(int i, int eventType) {
        Throwable th;
        int virtualViewId = i;
        AccessibilityEvent event = AccessibilityEvent.obtain(eventType);
        AccessibilityNodeInfoCompat node = obtainAccessibilityNodeInfo(virtualViewId);
        boolean add = event.getText().add(node.getText());
        event.setContentDescription(node.getContentDescription());
        event.setScrollable(node.isScrollable());
        event.setPassword(node.isPassword());
        event.setEnabled(node.isEnabled());
        event.setChecked(node.isChecked());
        onPopulateEventForVirtualView(virtualViewId, event);
        if (!event.getText().isEmpty() || event.getContentDescription() != null) {
            event.setClassName(node.getClassName());
            AccessibilityRecordCompat.setSource(event, this.mHost, virtualViewId);
            event.setPackageName(this.mHost.getContext().getPackageName());
            return event;
        }
        Throwable th2 = th;
        new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        throw th2;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo(int i) {
        int virtualViewId = i;
        if (virtualViewId == -1) {
            return createNodeForHost();
        }
        return createNodeForChild(virtualViewId);
    }

    @NonNull
    private AccessibilityNodeInfoCompat createNodeForHost() {
        ArrayList arrayList;
        Throwable th;
        AccessibilityNodeInfoCompat info = AccessibilityNodeInfoCompat.obtain(this.mHost);
        ViewCompat.onInitializeAccessibilityNodeInfo(this.mHost, info);
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        getVisibleVirtualViews(arrayList2);
        if (info.getChildCount() <= 0 || arrayList2.size() <= 0) {
            int count = arrayList2.size();
            for (int i = 0; i < count; i++) {
                info.addChild(this.mHost, ((Integer) arrayList2.get(i)).intValue());
            }
            return info;
        }
        Throwable th2 = th;
        new RuntimeException("Views cannot have both real and virtual children");
        throw th2;
    }

    public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        AccessibilityNodeInfoCompat info = accessibilityNodeInfoCompat;
        super.onInitializeAccessibilityNodeInfo(host, info);
        onPopulateNodeForHost(info);
    }

    @NonNull
    private AccessibilityNodeInfoCompat createNodeForChild(int i) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        int virtualViewId = i;
        AccessibilityNodeInfoCompat node = AccessibilityNodeInfoCompat.obtain();
        node.setEnabled(true);
        node.setFocusable(true);
        node.setClassName(DEFAULT_CLASS_NAME);
        node.setBoundsInParent(INVALID_PARENT_BOUNDS);
        node.setBoundsInScreen(INVALID_PARENT_BOUNDS);
        node.setParent(this.mHost);
        onPopulateNodeForVirtualView(virtualViewId, node);
        if (node.getText() == null && node.getContentDescription() == null) {
            Throwable th5 = th4;
            new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
            throw th5;
        }
        node.getBoundsInParent(this.mTempParentRect);
        if (this.mTempParentRect.equals(INVALID_PARENT_BOUNDS)) {
            Throwable th6 = th3;
            new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
            throw th6;
        }
        int actions = node.getActions();
        if ((actions & 64) != 0) {
            Throwable th7 = th2;
            new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
            throw th7;
        } else if ((actions & 128) != 0) {
            Throwable th8 = th;
            new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
            throw th8;
        } else {
            node.setPackageName(this.mHost.getContext().getPackageName());
            node.setSource(this.mHost, virtualViewId);
            if (this.mAccessibilityFocusedVirtualViewId == virtualViewId) {
                node.setAccessibilityFocused(true);
                node.addAction(128);
            } else {
                node.setAccessibilityFocused(false);
                node.addAction(64);
            }
            boolean isFocused = this.mKeyboardFocusedVirtualViewId == virtualViewId;
            if (isFocused) {
                node.addAction(2);
            } else if (node.isFocusable()) {
                node.addAction(1);
            }
            node.setFocused(isFocused);
            this.mHost.getLocationOnScreen(this.mTempGlobalRect);
            node.getBoundsInScreen(this.mTempScreenRect);
            if (this.mTempScreenRect.equals(INVALID_PARENT_BOUNDS)) {
                node.getBoundsInParent(this.mTempScreenRect);
                if (node.mParentVirtualDescendantId != -1) {
                    AccessibilityNodeInfoCompat parentNode = AccessibilityNodeInfoCompat.obtain();
                    int i2 = node.mParentVirtualDescendantId;
                    while (true) {
                        int virtualDescendantId = i2;
                        if (virtualDescendantId == -1) {
                            break;
                        }
                        parentNode.setParent(this.mHost, -1);
                        parentNode.setBoundsInParent(INVALID_PARENT_BOUNDS);
                        onPopulateNodeForVirtualView(virtualDescendantId, parentNode);
                        parentNode.getBoundsInParent(this.mTempParentRect);
                        this.mTempScreenRect.offset(this.mTempParentRect.left, this.mTempParentRect.top);
                        i2 = parentNode.mParentVirtualDescendantId;
                    }
                    parentNode.recycle();
                }
                this.mTempScreenRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
            }
            if (this.mHost.getLocalVisibleRect(this.mTempVisibleRect)) {
                this.mTempVisibleRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
                if (this.mTempScreenRect.intersect(this.mTempVisibleRect)) {
                    node.setBoundsInScreen(this.mTempScreenRect);
                    if (isVisibleToUser(this.mTempScreenRect)) {
                        node.setVisibleToUser(true);
                    }
                }
            }
            return node;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean performAction(int i, int i2, Bundle bundle) {
        int virtualViewId = i;
        int action = i2;
        Bundle arguments = bundle;
        switch (virtualViewId) {
            case -1:
                return performActionForHost(action, arguments);
            default:
                return performActionForChild(virtualViewId, action, arguments);
        }
    }

    private boolean performActionForHost(int action, Bundle arguments) {
        return ViewCompat.performAccessibilityAction(this.mHost, action, arguments);
    }

    private boolean performActionForChild(int i, int i2, Bundle bundle) {
        int virtualViewId = i;
        int action = i2;
        Bundle arguments = bundle;
        switch (action) {
            case 1:
                return requestKeyboardFocusForVirtualView(virtualViewId);
            case 2:
                return clearKeyboardFocusForVirtualView(virtualViewId);
            case 64:
                return requestAccessibilityFocus(virtualViewId);
            case 128:
                return clearAccessibilityFocus(virtualViewId);
            default:
                return onPerformActionForVirtualView(virtualViewId, action, arguments);
        }
    }

    private boolean isVisibleToUser(Rect rect) {
        Rect localRect = rect;
        if (localRect == null || localRect.isEmpty()) {
            return false;
        }
        if (this.mHost.getWindowVisibility() != 0) {
            return false;
        }
        ViewParent parent = this.mHost.getParent();
        while (true) {
            ViewParent viewParent = parent;
            if (viewParent instanceof View) {
                View view = (View) viewParent;
                if (view.getAlpha() > 0.0f && view.getVisibility() == 0) {
                    parent = view.getParent();
                }
            } else {
                return viewParent != null;
            }
        }
        return false;
    }

    private boolean requestAccessibilityFocus(int i) {
        int virtualViewId = i;
        if (!this.mManager.isEnabled() || !this.mManager.isTouchExplorationEnabled()) {
            return false;
        }
        if (this.mAccessibilityFocusedVirtualViewId == virtualViewId) {
            return false;
        }
        if (this.mAccessibilityFocusedVirtualViewId != Integer.MIN_VALUE) {
            boolean clearAccessibilityFocus = clearAccessibilityFocus(this.mAccessibilityFocusedVirtualViewId);
        }
        this.mAccessibilityFocusedVirtualViewId = virtualViewId;
        this.mHost.invalidate();
        boolean sendEventForVirtualView = sendEventForVirtualView(virtualViewId, 32768);
        return true;
    }

    private boolean clearAccessibilityFocus(int i) {
        int virtualViewId = i;
        if (this.mAccessibilityFocusedVirtualViewId != virtualViewId) {
            return false;
        }
        this.mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
        this.mHost.invalidate();
        boolean sendEventForVirtualView = sendEventForVirtualView(virtualViewId, 65536);
        return true;
    }

    public final boolean requestKeyboardFocusForVirtualView(int i) {
        int virtualViewId = i;
        if (!this.mHost.isFocused() && !this.mHost.requestFocus()) {
            return false;
        }
        if (this.mKeyboardFocusedVirtualViewId == virtualViewId) {
            return false;
        }
        if (this.mKeyboardFocusedVirtualViewId != Integer.MIN_VALUE) {
            boolean clearKeyboardFocusForVirtualView = clearKeyboardFocusForVirtualView(this.mKeyboardFocusedVirtualViewId);
        }
        this.mKeyboardFocusedVirtualViewId = virtualViewId;
        onVirtualViewKeyboardFocusChanged(virtualViewId, true);
        boolean sendEventForVirtualView = sendEventForVirtualView(virtualViewId, 8);
        return true;
    }

    public final boolean clearKeyboardFocusForVirtualView(int i) {
        int virtualViewId = i;
        if (this.mKeyboardFocusedVirtualViewId != virtualViewId) {
            return false;
        }
        this.mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
        onVirtualViewKeyboardFocusChanged(virtualViewId, false);
        boolean sendEventForVirtualView = sendEventForVirtualView(virtualViewId, 8);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPopulateEventForVirtualView(int virtualViewId, @NonNull AccessibilityEvent event) {
    }

    /* access modifiers changed from: protected */
    public void onPopulateEventForHost(@NonNull AccessibilityEvent event) {
    }

    /* access modifiers changed from: protected */
    public void onPopulateNodeForHost(@NonNull AccessibilityNodeInfoCompat node) {
    }

    /* renamed from: android.support.v4.widget.ExploreByTouchHelper$MyNodeProvider */
    private class MyNodeProvider extends AccessibilityNodeProviderCompat {
        final /* synthetic */ ExploreByTouchHelper this$0;

        MyNodeProvider(ExploreByTouchHelper exploreByTouchHelper) {
            this.this$0 = exploreByTouchHelper;
        }

        public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int virtualViewId) {
            return AccessibilityNodeInfoCompat.obtain(this.this$0.obtainAccessibilityNodeInfo(virtualViewId));
        }

        public boolean performAction(int virtualViewId, int action, Bundle arguments) {
            return this.this$0.performAction(virtualViewId, action, arguments);
        }

        public AccessibilityNodeInfoCompat findFocus(int focusType) {
            int focusedId = focusType == 2 ? this.this$0.mAccessibilityFocusedVirtualViewId : this.this$0.mKeyboardFocusedVirtualViewId;
            if (focusedId == Integer.MIN_VALUE) {
                return null;
            }
            return createAccessibilityNodeInfo(focusedId);
        }
    }
}
