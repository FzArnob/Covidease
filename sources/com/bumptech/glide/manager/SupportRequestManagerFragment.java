package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.p000v4.app.Fragment;
import android.util.Log;
import com.bumptech.glide.RequestManager;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SupportRequestManagerFragment extends Fragment {
    private static final String TAG = "SupportRMFragment";
    private final HashSet<SupportRequestManagerFragment> childRequestManagerFragments;
    private final ActivityFragmentLifecycle lifecycle;
    private RequestManager requestManager;
    private final RequestManagerTreeNode requestManagerTreeNode;
    private SupportRequestManagerFragment rootRequestManagerFragment;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SupportRequestManagerFragment() {
        /*
            r5 = this;
            r0 = r5
            r1 = r0
            com.bumptech.glide.manager.ActivityFragmentLifecycle r2 = new com.bumptech.glide.manager.ActivityFragmentLifecycle
            r4 = r2
            r2 = r4
            r3 = r4
            r3.<init>()
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.manager.SupportRequestManagerFragment.<init>():void");
    }

    @SuppressLint({"ValidFragment"})
    public SupportRequestManagerFragment(ActivityFragmentLifecycle lifecycle2) {
        RequestManagerTreeNode requestManagerTreeNode2;
        HashSet<SupportRequestManagerFragment> hashSet;
        new SupportFragmentRequestManagerTreeNode(this, (C15351) null);
        this.requestManagerTreeNode = requestManagerTreeNode2;
        new HashSet<>();
        this.childRequestManagerFragments = hashSet;
        this.lifecycle = lifecycle2;
    }

    public void setRequestManager(RequestManager requestManager2) {
        RequestManager requestManager3 = requestManager2;
        this.requestManager = requestManager3;
    }

    /* access modifiers changed from: package-private */
    public ActivityFragmentLifecycle getLifecycle() {
        return this.lifecycle;
    }

    public RequestManager getRequestManager() {
        return this.requestManager;
    }

    public RequestManagerTreeNode getRequestManagerTreeNode() {
        return this.requestManagerTreeNode;
    }

    private void addChildRequestManagerFragment(SupportRequestManagerFragment child) {
        boolean add = this.childRequestManagerFragments.add(child);
    }

    private void removeChildRequestManagerFragment(SupportRequestManagerFragment child) {
        boolean remove = this.childRequestManagerFragments.remove(child);
    }

    public Set<SupportRequestManagerFragment> getDescendantRequestManagerFragments() {
        HashSet hashSet;
        if (this.rootRequestManagerFragment == null) {
            return Collections.emptySet();
        }
        if (this.rootRequestManagerFragment == this) {
            return Collections.unmodifiableSet(this.childRequestManagerFragments);
        }
        new HashSet();
        HashSet hashSet2 = hashSet;
        for (SupportRequestManagerFragment fragment : this.rootRequestManagerFragment.getDescendantRequestManagerFragments()) {
            if (isDescendant(fragment.getParentFragment())) {
                boolean add = hashSet2.add(fragment);
            }
        }
        return Collections.unmodifiableSet(hashSet2);
    }

    private boolean isDescendant(Fragment fragment) {
        Fragment root = getParentFragment();
        for (Fragment fragment2 = fragment; fragment2.getParentFragment() != null; fragment2 = fragment2.getParentFragment()) {
            if (fragment2.getParentFragment() == root) {
                return true;
            }
        }
        return false;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.rootRequestManagerFragment = RequestManagerRetriever.get().getSupportRequestManagerFragment(getActivity().getSupportFragmentManager());
            if (this.rootRequestManagerFragment != this) {
                this.rootRequestManagerFragment.addChildRequestManagerFragment(this);
            }
        } catch (IllegalStateException e) {
            IllegalStateException e2 = e;
            if (Log.isLoggable(TAG, 5)) {
                int w = Log.w(TAG, "Unable to register fragment with root", e2);
            }
        }
    }

    public void onDetach() {
        super.onDetach();
        if (this.rootRequestManagerFragment != null) {
            this.rootRequestManagerFragment.removeChildRequestManagerFragment(this);
            this.rootRequestManagerFragment = null;
        }
    }

    public void onStart() {
        super.onStart();
        this.lifecycle.onStart();
    }

    public void onStop() {
        super.onStop();
        this.lifecycle.onStop();
    }

    public void onDestroy() {
        super.onDestroy();
        this.lifecycle.onDestroy();
    }

    public void onLowMemory() {
        super.onLowMemory();
        if (this.requestManager != null) {
            this.requestManager.onLowMemory();
        }
    }

    private class SupportFragmentRequestManagerTreeNode implements RequestManagerTreeNode {
        final /* synthetic */ SupportRequestManagerFragment this$0;

        private SupportFragmentRequestManagerTreeNode(SupportRequestManagerFragment supportRequestManagerFragment) {
            this.this$0 = supportRequestManagerFragment;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ SupportFragmentRequestManagerTreeNode(SupportRequestManagerFragment x0, C15351 r7) {
            this(x0);
            C15351 r2 = r7;
        }

        public Set<RequestManager> getDescendants() {
            HashSet hashSet;
            Set<SupportRequestManagerFragment> descendantFragments = this.this$0.getDescendantRequestManagerFragments();
            new HashSet(descendantFragments.size());
            HashSet hashSet2 = hashSet;
            for (SupportRequestManagerFragment fragment : descendantFragments) {
                if (fragment.getRequestManager() != null) {
                    boolean add = hashSet2.add(fragment.getRequestManager());
                }
            }
            return hashSet2;
        }
    }
}
