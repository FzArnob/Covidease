package com.firebase.client.core.view;

import com.firebase.client.snapshot.IndexedNode;
import com.firebase.client.snapshot.Node;

public class ViewCache {
    private final CacheNode eventSnap;
    private final CacheNode serverSnap;

    public ViewCache(CacheNode eventSnap2, CacheNode serverSnap2) {
        this.eventSnap = eventSnap2;
        this.serverSnap = serverSnap2;
    }

    public ViewCache updateEventSnap(IndexedNode eventSnap2, boolean complete, boolean filtered) {
        ViewCache viewCache;
        CacheNode cacheNode;
        new CacheNode(eventSnap2, complete, filtered);
        new ViewCache(cacheNode, this.serverSnap);
        return viewCache;
    }

    public ViewCache updateServerSnap(IndexedNode serverSnap2, boolean complete, boolean filtered) {
        ViewCache viewCache;
        CacheNode cacheNode;
        new CacheNode(serverSnap2, complete, filtered);
        new ViewCache(this.eventSnap, cacheNode);
        return viewCache;
    }

    public CacheNode getEventCache() {
        return this.eventSnap;
    }

    public Node getCompleteEventSnap() {
        return this.eventSnap.isFullyInitialized() ? this.eventSnap.getNode() : null;
    }

    public CacheNode getServerCache() {
        return this.serverSnap;
    }

    public Node getCompleteServerSnap() {
        return this.serverSnap.isFullyInitialized() ? this.serverSnap.getNode() : null;
    }
}
