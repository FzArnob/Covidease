package com.firebase.client;

import com.firebase.client.Firebase;
import com.firebase.client.core.Path;
import com.firebase.client.core.Repo;
import com.firebase.client.core.ValidationPath;
import com.firebase.client.snapshot.Node;
import com.firebase.client.snapshot.NodeUtilities;
import com.firebase.client.snapshot.PriorityUtilities;
import com.firebase.client.utilities.Validation;
import com.firebase.client.utilities.encoding.JsonHelpers;
import java.util.Map;

public class OnDisconnect {
    /* access modifiers changed from: private */
    public Path path;
    /* access modifiers changed from: private */
    public Repo repo;

    OnDisconnect(Repo repo2, Path path2) {
        this.repo = repo2;
        this.path = path2;
    }

    public void setValue(Object value) {
        onDisconnectSetInternal(value, PriorityUtilities.NullPriority(), (Firebase.CompletionListener) null);
    }

    public void setValue(Object value, String priority) {
        onDisconnectSetInternal(value, PriorityUtilities.parsePriority(priority), (Firebase.CompletionListener) null);
    }

    public void setValue(Object value, double priority) {
        onDisconnectSetInternal(value, PriorityUtilities.parsePriority(Double.valueOf(priority)), (Firebase.CompletionListener) null);
    }

    public void setValue(Object value, Firebase.CompletionListener listener) {
        onDisconnectSetInternal(value, PriorityUtilities.NullPriority(), listener);
    }

    public void setValue(Object value, String priority, Firebase.CompletionListener listener) {
        onDisconnectSetInternal(value, PriorityUtilities.parsePriority(priority), listener);
    }

    public void setValue(Object value, double priority, Firebase.CompletionListener listener) {
        onDisconnectSetInternal(value, PriorityUtilities.parsePriority(Double.valueOf(priority)), listener);
    }

    public void setValue(Object value, Map priority, Firebase.CompletionListener listener) {
        onDisconnectSetInternal(value, PriorityUtilities.parsePriority(priority), listener);
    }

    private void onDisconnectSetInternal(Object obj, Node node, Firebase.CompletionListener completionListener) {
        Throwable th;
        Runnable runnable;
        Object value = obj;
        Node priority = node;
        Firebase.CompletionListener onComplete = completionListener;
        Validation.validateWritablePath(this.path);
        ValidationPath.validateWithObject(this.path, value);
        try {
            Object bouncedValue = JsonHelpers.getMapper().convertValue(value, Object.class);
            Validation.validateWritableObject(bouncedValue);
            final Node NodeFromJSON = NodeUtilities.NodeFromJSON(bouncedValue, priority);
            final Firebase.CompletionListener completionListener2 = onComplete;
            new Runnable(this) {
                final /* synthetic */ OnDisconnect this$0;

                {
                    this.this$0 = r7;
                }

                public void run() {
                    this.this$0.repo.onDisconnectSetValue(this.this$0.path, NodeFromJSON, completionListener2);
                }
            };
            this.repo.scheduleNow(runnable);
        } catch (IllegalArgumentException e) {
            IllegalArgumentException e2 = e;
            Throwable th2 = th;
            new FirebaseException("Failed to parse to snapshot", e2);
            throw th2;
        }
    }

    public void updateChildren(Map<String, Object> update) {
        updateChildren(update, (Firebase.CompletionListener) null);
    }

    public void updateChildren(Map<String, Object> map, Firebase.CompletionListener listener) {
        Runnable runnable;
        Map<String, Object> update = map;
        final Map<Path, Node> parseAndValidateUpdate = Validation.parseAndValidateUpdate(this.path, update);
        final Firebase.CompletionListener completionListener = listener;
        final Map<String, Object> map2 = update;
        new Runnable(this) {
            final /* synthetic */ OnDisconnect this$0;

            {
                this.this$0 = r8;
            }

            public void run() {
                this.this$0.repo.onDisconnectUpdate(this.this$0.path, parseAndValidateUpdate, completionListener, map2);
            }
        };
        this.repo.scheduleNow(runnable);
    }

    public void removeValue() {
        setValue((Object) null);
    }

    public void removeValue(Firebase.CompletionListener listener) {
        setValue((Object) null, listener);
    }

    public void cancel() {
        cancel((Firebase.CompletionListener) null);
    }

    public void cancel(Firebase.CompletionListener listener) {
        Runnable runnable;
        final Firebase.CompletionListener completionListener = listener;
        new Runnable(this) {
            final /* synthetic */ OnDisconnect this$0;

            {
                this.this$0 = r6;
            }

            public void run() {
                this.this$0.repo.onDisconnectCancel(this.this$0.path, completionListener);
            }
        };
        this.repo.scheduleNow(runnable);
    }
}
