package com.meal.me.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.meal.me.entity.Test;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
public class TestService {
    // Get all Tests
    public Test getTest(String documentId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("test").document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        Test test;

        if(document.exists()) {
            test = document.toObject(Test.class);
            return test;
        }
        System.out.println(document.toString());
        return null;
    }

    // Create Test Service
    public String createTest(Test test) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        // Converte LocalDate para LocalDateTime
        LocalDateTime localDateTime = test.getLocalDate().toSqlTimestamp().toLocalDateTime();

        // Converte LocalDateTime para java.util.Date
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        // Converte java.util.Date para Timestamp
        Timestamp timestamp = Timestamp.of(date);

        // Define o campo localDate do objeto test como o timestamp
        test.setLocalDate(timestamp);

        // Salva o objeto test no Firestore
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection("test").document().set(test);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    // Update Test Service
    public String updateTest(Test test) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection("test").document().set(test);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    // Delete Test Service
    public String deleteTest(String documentId) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("test").document(documentId).delete();
        return "Successfully Delete!" + documentId;
    }
}

