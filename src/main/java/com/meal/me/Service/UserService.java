package com.meal.me.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.meal.me.entity.User;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserService {
    // Get all users
    public User getUser(String documentId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("users").document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        User user;

        if(document.exists()) {
            user = document.toObject(User.class);
            return user;
        }
        return null;
    }

    // Create User Service
    public String createUser(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection("users").document(user.getName()).set(user);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    // Update User Service
    public String updateUser(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection("users").document(user.getName()).set(user);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    // Delete User Service
    public String deleteUser(String documentId) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("users").document(documentId).delete();
        return "Successfully Delete!" + documentId;
    }
}
