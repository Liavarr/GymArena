package com.example.gymarena.repository;

import android.util.Log;

import com.example.gymarena.model.Usuario;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class UsuarioDAO implements DAOInterface<Usuario> {
    private final FirebaseFirestore db;

    public UsuarioDAO(FirebaseFirestore instancia) {
        Log.d("UsuarioDAO", "Instancia obtenida y conexion lista");
        this.db = instancia;
    }

    // CREAR
    @Override
    public void crear(Usuario usuario, OnCreado<Usuario> listener) {
        db.collection("usuarios")
                .add(usuario)
                .addOnSuccessListener(docRef -> listener.onSuccess(docRef.getId()))
                .addOnFailureListener(listener::onFailure);
    }

    // OBTENER UNO
    @Override
    public void obtenerUno(String id, OnObtenido<Usuario> listener) {
        db.collection("usuarios")
                .document(id)
                .get()
                .addOnSuccessListener(doc -> {
                    if (doc.exists()) {
                        Usuario usuario = doc.toObject(Usuario.class);
                        usuario.setIdUsuario(doc.getId());
                        listener.onSuccess(usuario);
                    } else {
                        listener.onFailure(new Exception("Usuario no encontrado"));
                    }
                })
                .addOnFailureListener(listener::onFailure);
    }

    // OBTENER TODO
    @Override
    public void obtenerTodo(OnTodosObtenidos<Usuario> listener) {
        db.collection("usuarios")
                .get()
                .addOnSuccessListener(query -> {
                    List<Usuario> lista = query.toObjects(Usuario.class);
                    // Asignar IDs de Firebase
                    for (int i = 0; i < lista.size(); i++) {
                        lista.get(i).setIdUsuario(query.getDocuments().get(i).getId());
                    }

                    listener.onSuccess(lista);
                })
                .addOnFailureListener(listener::onFailure);
    }

    // ACTUALIZAR
    @Override
    public void actualizar(String id, Usuario usuario, OnActualizado listener) {
        db.collection("usuarios")
                .document(id)
                .set(usuario)
                .addOnSuccessListener(unused -> listener.onSuccess())
                .addOnFailureListener(listener::onFailure);
    }

    // ELIMINAR
    @Override
    public void eliminar(String id, OnEliminado listener) {
        db.collection("usuarios")
                .document(id)
                .delete()
                .addOnSuccessListener(unused -> listener.onSuccess())
                .addOnFailureListener(listener::onFailure);
    }
}
