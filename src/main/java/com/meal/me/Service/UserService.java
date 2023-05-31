package com.meal.me.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.meal.me.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
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
        System.out.println("No such document with ID: " + documentId);
        return null;
    }
    public String createUser(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        CollectionReference mealsRef = dbFirestore.collection("meals");
        Map<String, Object> meals = new HashMap<>();
        for (String mealId : user.getMeals().keySet()) {
            DocumentReference mealDocRef = mealsRef.document(mealId);
            ApiFuture<DocumentSnapshot> future = mealDocRef.get();
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                meals.put(mealId, document.getData());
            } else {
                System.out.println("No such document with ID: " + mealId);
            }
        }
        user.setMeals(meals);

        CollectionReference goalsRef = dbFirestore.collection("goals");
        Map<String, Object> goals = new HashMap<>();

        for (String goalsId : user.getGoals().keySet()) {
            DocumentReference mealDocRef = goalsRef.document(goalsId);
            ApiFuture<DocumentSnapshot> future = mealDocRef.get();
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                goals.put(goalsId, document.getData());
            } else {
                System.out.println("No such document with ID: " + goalsId);
            }
        }
        user.setGoals(goals);

        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection("users").document().set(user);

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
