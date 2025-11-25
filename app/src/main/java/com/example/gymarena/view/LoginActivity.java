package com.example.gymarena.view;

import android.os.Bundle;

import com.example.gymarena.R;
import com.example.gymarena.model.Ejercicio;
import com.example.gymarena.model.Rutina;
import com.example.gymarena.model.Usuario;

public class LoginActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cargarVista(R.layout.activity_main);
        test();
    }

    protected void test(){
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA");
    }
}
