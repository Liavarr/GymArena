package com.example.gymarena.auth;

import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseConexion {
    private static FirebaseConexion instancia;
    private final FirebaseFirestore db;
    public FirebaseConexion() {
        db = FirebaseFirestore.getInstance();
    }

    // Obtener conexión a Firestore
    public FirebaseFirestore getDb() {
        return db;
    }

    public static void setInstancia(FirebaseConexion instancia) {
        FirebaseConexion.instancia = instancia;
    }

    // Obtener instancia única
    public static synchronized FirebaseConexion getInstancia() {
        if (instancia == null) {
            instancia = new FirebaseConexion();
        }
        return instancia;
    }
}
