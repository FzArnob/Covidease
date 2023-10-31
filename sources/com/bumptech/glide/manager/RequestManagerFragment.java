package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.util.Log;
import com.bumptech.glide.RequestManager;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@TargetApi(11)
public class RequestManagerFragment extends Fragment {
    private static final String TAG = "RMFragment";
    private final HashSet<RequestManagerFragment> childRequestManagerFragments;
    private final ActivityFragmentLifecycle lifecycle;
    private RequestManager requestManager;
    private final RequestManagerTreeNode requestManagerTreeNode;
    private RequestManagerFragment rootRequestManagerFragment;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public RequestManagerFragment() {
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
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.manager.RequestManagerFragment.<init>():void");
    }

    @SuppressLint({"ValidFragment"})
    RequestManagerFragment(ActivityFragmentLifecycle lifecycle2) {
        RequestManagerTreeNode requestManagerTreeNode2;
        HashSet<RequestManagerFragment> hashSet;
        new FragmentRequestManagerTreeNode(this, (C15341) null);
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

    private void addChildRequestManagerFragment(RequestManagerFragment child) {
        boolean add = this.childRequestManagerFragments.add(child);
    }

    private void removeChildRequestManagerFragment(RequestManagerFragment child) {
        boolean remove = this.childRequestManagerFragments.remove(child);
    }

    @TargetApi(17)
    public Set<RequestManagerFragment> getDescendantRequestManagerFragments() {
        HashSet hashSet;
        if (this.rootRequestManagerFragment == this) {
            return Collections.unmodifiableSet(this.childRequestManagerFragments);
        }
        if (this.rootRequestManagerFragment == null || Build.VERSION.SDK_INT < 17) {
            return Collections.emptySet();
        }
        new HashSet();
        HashSet hashSet2 = hashSet;
        for (RequestManagerFragment fragment : this.rootRequestManagerFragment.getDescendantRequestManagerFragments()) {
            if (isDescendant(fragment.getParentFragment())) {
                boolean add = hashSet2.add(fragment);
            }
        }
        return Collections.unmodifiableSet(hashSet2);
    }

    @TargetApi(17)
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
            this.rootRequestManagerFragment = RequestManagerRetriever.get().getRequestManagerFragment(getActivity().getFragmentManager());
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

    public void onTrimMemory(int i) {
        int level = i;
        if (this.requestManager != null) {
            this.requestManager.onTrimMemory(level);
        }
    }

    public void onLowMemory() {
        if (this.requestManager != null) {
            this.requestManager.onLowMemory();
        }
    }

    private class FragmentRequestManagerTreeNode implements RequestManagerTreeNode {
        final /* synthetic */ RequestManagerFragment this$0;

        private FragmentRequestManagerTreeNode(RequestManagerFragment requestManagerFragment) {
            this.this$0 = requestManagerFragment;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ FragmentRequestManagerTreeNode(RequestManagerFragment x0, C15341 r7) {
            this(x0);
            C15341 r2 = r7;
        }

        public Set<RequestManager> getDescendants() {
            HashSet hashSet;
            Set<RequestManagerFragment> descendantFragments = this.this$0.getDescendantRequestManagerFragments();
            new HashSet(descendantFragments.size());
            HashSet hashSet2 = hashSet;
            for (RequestManagerFragment fragment : descendantFragments) {
                if (fragment.getRequestManager() != null) {
                    boolean add = hashSet2.add(fragment.getRequestManager());
                }
            }
            return hashSet2;
        }
    }
}
