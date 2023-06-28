package com.DataBase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author marlo
 */
public class Firebase {

    public Firestore getConexionFirebase() {
        try {
            FileInputStream serviceAccount
                    = new FileInputStream("src/com/DataBase/proyectoagiles.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://proyectoagiles-a0340-default-rtdb.firebaseio.com")
                    .build();
            FirebaseApp.initializeApp(options);
            return FirestoreClient.getFirestore();
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado");
            return null;
        } catch (IOException ex) {
            System.out.println("Conexion fallida");
            return null;
        }
    }
}