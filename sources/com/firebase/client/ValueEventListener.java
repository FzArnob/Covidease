package com.firebase.client;

public interface ValueEventListener {
    void onCancelled(FirebaseError firebaseError);

    void onDataChange(DataSnapshot dataSnapshot);
}
