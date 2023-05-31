package com.meal.me;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
public class Application {

	public static void main(String []args) throws IOException {

		// Load the Firebase Connection SDK
		ClassLoader classLoader = Application.class.getClassLoader();

		// get the connection file
		File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());
		FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());

		// paste the firebase config here
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://meal-me-v2.firebaseio.com/")
				.build();

		FirebaseApp.initializeApp(options);

		SpringApplication.run(Application.class, args);
	}

}