package com.meal.me.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.meal.me.entity.Meals;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class MealsService {

    public Meals getMeals(String documentId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("meals").document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        Meals meals;

        if(document.exists()) {
            meals = document.toObject(Meals.class);
            return meals;
        }
        System.out.println(document.toString());
        return null;
    }

    public String createMeals(Meals meals) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection("meals").document().set(meals);

        return collectionApiFuture.get().getUpdateTime().toString();
    }



    public String updateMeals(Meals meals) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection("meals").document().set(meals);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String deleteMeals(String documentId) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("meals").document(documentId).delete();
        return "Successfully Delete!" + documentId;
    }
}
