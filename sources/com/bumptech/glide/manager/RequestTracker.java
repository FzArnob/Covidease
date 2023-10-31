package com.bumptech.glide.manager;

import com.bumptech.glide.request.Request;
import com.bumptech.glide.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class RequestTracker {
    private boolean isPaused;
    private final List<Request> pendingRequests;
    private final Set<Request> requests;

    public RequestTracker() {
        Map map;
        List<Request> list;
        new WeakHashMap();
        this.requests = Collections.newSetFromMap(map);
        new ArrayList();
        this.pendingRequests = list;
    }

    public void runRequest(Request request) {
        Request request2 = request;
        boolean add = this.requests.add(request2);
        if (!this.isPaused) {
            request2.begin();
        } else {
            boolean add2 = this.pendingRequests.add(request2);
        }
    }

    /* access modifiers changed from: package-private */
    public void addRequest(Request request) {
        boolean add = this.requests.add(request);
    }

    public void removeRequest(Request request) {
        Request request2 = request;
        boolean remove = this.requests.remove(request2);
        boolean remove2 = this.pendingRequests.remove(request2);
    }

    public boolean isPaused() {
        return this.isPaused;
    }

    public void pauseRequests() {
        this.isPaused = true;
        for (T request : Util.getSnapshot(this.requests)) {
            if (request.isRunning()) {
                request.pause();
                boolean add = this.pendingRequests.add(request);
            }
        }
    }

    public void resumeRequests() {
        this.isPaused = false;
        for (T request : Util.getSnapshot(this.requests)) {
            if (!request.isComplete() && !request.isCancelled() && !request.isRunning()) {
                request.begin();
            }
        }
        this.pendingRequests.clear();
    }

    public void clearRequests() {
        for (T request : Util.getSnapshot(this.requests)) {
            request.clear();
        }
        this.pendingRequests.clear();
    }

    public void restartRequests() {
        for (T request : Util.getSnapshot(this.requests)) {
            if (!request.isComplete() && !request.isCancelled()) {
                request.pause();
                if (!this.isPaused) {
                    request.begin();
                } else {
                    boolean add = this.pendingRequests.add(request);
                }
            }
        }
    }
}
