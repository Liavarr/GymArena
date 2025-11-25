package com.example.gymarena.view;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gymarena.R;
import com.example.gymarena.model.Ejercicio;
import com.example.gymarena.model.Rutina;
import com.example.gymarena.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        validarLogin();
        test();
    }

    protected void test(){
        Usuario usuario1 = new Usuario("1","Pablo","email@gmail.com",90.00,1.75);
        Usuario usuario2 = new Usuario("2","Alejandro","correo@gmail.com",80.00,1.75);

        Rutina rutina1 = new Rutina();
        Rutina rutina2 = new Rutina();

        Ejercicio ejercicio1 = new Ejercicio();
        Ejercicio ejercicio2 = new Ejercicio();
        Ejercicio ejercicio3 = new Ejercicio();

        rutina1.getEjercicios().add(ejercicio1);
        rutina1.getEjercicios().add(ejercicio2);

        rutina2.getEjercicios().add(ejercicio3);
        rutina2.getEjercicios().add(ejercicio2);

        usuario1.getListaAmigos().add(usuario2.getId());

        System.out.println("============================");
        System.out.println(usuario2);
        System.out.println(usuario1);


        System.out.println("============================");
        System.out.println(rutina1);
        System.out.println(rutina2);
    }
}