package com.example.gymarena.auth;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SessionManager {
    private static SessionManager instancia;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    // Constructor privado (Singleton)
    private SessionManager() {
        mAuth = FirebaseAuth.getInstance();
    }

    // Obtener instancia única
    public static synchronized SessionManager getInstancia() {
        if (instancia == null) {
            instancia = new SessionManager();
        }
        return instancia;
    }
    public static void setInstancia(SessionManager instancia) {
        SessionManager.instancia = instancia;
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    // Obtener usuario logeado
    public FirebaseUser getCurrentUser() {
        return currentUser;
    }


    // Login de usuario con email y password, el callback hace que el sistema espere, porque si no será asincrono y se saltará el login y ejecutará el resto de la aplicacion
    public void login(String email, String password, LoginCallback callback) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        currentUser = mAuth.getCurrentUser();
                        Log.d("FirebaseConexion - login()", "Usuario logeado: " + currentUser.getEmail());
                        callback.onLoginSuccess(currentUser);
                    } else {
                        Log.w("FirebaseConexion - login()", "Error al iniciar sesión", task.getException());
                        callback.onLoginFailed(task.getException());
                    }
                });
    }

    public interface LoginCallback {
        void onLoginSuccess(FirebaseUser user);
        void onLoginFailed(Exception e);
    }

    public void setCurrentUser(FirebaseUser currentUser) {
        this.currentUser = currentUser;
    }
}
