package com.example.gymarena.view;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gymarena.R;
import com.example.gymarena.auth.SessionManager;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class BaseActivity extends AppCompatActivity {
    SessionManager sesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_base);

    }

    protected void afterLayoutLoaded() {
        // vacío – será sobrescrito por cada Activity hija
    }
    protected void validarLogin(SessionManager sesion, int layoutId) {

        sesion.login("xxx@gmail.com", "xxxx", new SessionManager.LoginCallback() {
            @Override
            public void onLoginSuccess(FirebaseUser user) {
                cargarVista(layoutId);
                afterLayoutLoaded();  // <-- Aqui avisamos a la activity hija para que cargue antes de ejecutar codigo
            }

            @Override
            public void onLoginFailed(Exception e) {
                cargarVista(R.layout.activity_login);
                afterLayoutLoaded();
            }
        });
    }


    protected void cargarVista(int layoutId){
        setContentView(layoutId);
        System.out.println("Vista cargada");
    }

}
