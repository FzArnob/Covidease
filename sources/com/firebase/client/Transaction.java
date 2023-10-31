package com.firebase.client;

import com.firebase.client.snapshot.Node;

public class Transaction {

    public interface Handler {
        Result doTransaction(MutableData mutableData);

        void onComplete(FirebaseError firebaseError, boolean z, DataSnapshot dataSnapshot);
    }

    public Transaction() {
    }

    public static class Result {
        private Node data;
        private boolean success;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ Result(boolean x0, Node x1, C12731 r10) {
            this(x0, x1);
            C12731 r3 = r10;
        }

        private Result(boolean success2, Node data2) {
            this.success = success2;
            this.data = data2;
        }

        public boolean isSuccess() {
            return this.success;
        }

        public Node getNode() {
            return this.data;
        }
    }

    public static Result abort() {
        Result result;
        Result result2 = result;
        new Result(false, (Node) null, (C12731) null);
        return result2;
    }

    public static Result success(MutableData resultData) {
        Result result;
        new Result(true, resultData.getNode(), (C12731) null);
        return result;
    }
}
