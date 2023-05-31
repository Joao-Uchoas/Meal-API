package com.meal.me.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.meal.me.entity.Goals;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class GoalsService {
    public Goals getGoals(String documentId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("goals").document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        Goals goals;

        if(document.exists()) {
            goals = document.toObject(Goals.class);
            return goals;
        }
        System.out.println(document.toString());
        return null;
    }

    public String createGoals(Goals goals) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection("goals").document().set(goals);

        return collectionApiFuture.get().getUpdateTime().toString();
    }


    public String updateGoals(Goals goals) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection("goals").document().set(goals);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String deleteGoals(String documentId) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("goals").document(documentId).delete();
        return "Successfully Delete!" + documentId;
    }
}
