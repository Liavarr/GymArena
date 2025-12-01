package com.example.gymarena.repository;

import android.util.Log;

import com.example.gymarena.auth.FirebaseConexion;
import com.example.gymarena.model.Rutina;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class RutinaDAO implements DAOInterface<Rutina>{
    private final FirebaseFirestore db;

    public RutinaDAO(FirebaseFirestore instancia) {
        Log.d("RutinaDAO", "Instancia obtenida y conexion lista");
        this.db = instancia;
    }

    // CREAR
    @Override
    public void crear(Rutina rutina, OnCreado<Rutina> listener) {
        db.collection("rutinas")
                .add(rutina) // guarda el objeto completo
                .addOnSuccessListener(docRef -> {
                    listener.onSuccess(docRef.getId());
                })
                .addOnFailureListener(listener::onFailure);
    }

    // OBTENER UNO
    @Override
    public void obtenerUno(String id, OnObtenido<Rutina> listener) {

        db.collection("rutinas")
                .document(id)
                .get()
                .addOnSuccessListener(doc -> {
                    if (doc.exists()) {
                        Rutina rutina = doc.toObject(Rutina.class);
                        rutina.setIdRutina(doc.getId());
                        listener.onSuccess(rutina);
                    } else {
                        listener.onFailure(new Exception("Rutina no encontrada"));
                    }
                })
                .addOnFailureListener(listener::onFailure);
    }

    // OBTENER TODO
    @Override
    public void obtenerTodo(OnTodosObtenidos<Rutina> listener) {

        db.collection("rutinas")
                .get()
                .addOnSuccessListener(query -> {

                    List<Rutina> lista = query.toObjects(Rutina.class);

                    // Asignar IDs reales
                    for (int i = 0; i < lista.size(); i++) {
                        lista.get(i).setIdRutina(query.getDocuments().get(i).getId());
                    }

                    listener.onSuccess(lista);
                })
                .addOnFailureListener(listener::onFailure);
    }

    // ACTUALIZAR
    @Override
    public void actualizar(String id, Rutina rutina, OnActualizado listener) {
        // asignar el ID al objeto por si quieres usarlo dentro del documento
        db.collection("rutinas")
                .document(id)
                .set(rutina) // guardas el objeto completo
                .addOnSuccessListener(unused -> listener.onSuccess())
                .addOnFailureListener(listener::onFailure);
    }

    // ELIMINAR
    @Override
    public void eliminar(String id, OnEliminado listener) {

        db.collection("rutinas")
                .document(id)
                .delete()
                .addOnSuccessListener(unused -> listener.onSuccess())
                .addOnFailureListener(listener::onFailure);
    }
}
