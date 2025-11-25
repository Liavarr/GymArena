package com.example.gymarena.view;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gymarena.R;
import com.example.gymarena.auth.SessionManager;

public class BaseActivity extends AppCompatActivity {
    SessionManager usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuario = new SessionManager("a");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_base);
        //validarLogin();

    }

    protected void validarLogin(){
        if (usuario.getUsuarioActivo().equals("")){
            cargarVista(R.layout.activity_login);
        } else {
            cargarVista(R.layout.activity_main);
            System.out.println("Usuario logeado");
        }
    }

    protected void cargarVista(int layoutId){
        setContentView(layoutId);
        System.out.println("Vista cargada");
    }
}
