package com.example.gymarena.repository;

import android.util.Log;

import com.example.gymarena.model.Usuario;
import com.google.firebase.firestore.FirebaseFirestore;

public class UsuarioRepository {
    public UsuarioRepository(){
    }
    public void anadirAmigo(UsuarioDAO usuarioDAO, Usuario currentUser, String idAdmigo){
        currentUser.getListaAmigos().add(idAdmigo);
        usuarioDAO.actualizar(currentUser.getIdUsuario(), currentUser, new DAOInterface.OnActualizado(){
            @Override
            public void onSuccess() {
                System.out.println(currentUser.getListaAmigos());
                Log.d("UsuarioRepository - anadirAmigo", "onSuccess: Amigo añadido");
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("UsuarioRepository - anadirAmigo", "onSuccess: ha ocurrido un error al añadir el amigo " + e);
            }
        });
    }

    public void eliminarAmigo(UsuarioDAO usuarioDAO, Usuario currentUser, String idAmigo){
        currentUser.getListaAmigos().remove(idAmigo);
        usuarioDAO.actualizar(currentUser.getIdUsuario(), currentUser, new DAOInterface.OnActualizado(){
            @Override
            public void onSuccess() {
                Log.d("UsuarioRepository - anadirAmigo", "onSuccess: Amigo eliminado");
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("UsuarioRepository - anadirAmigo", "onSuccess: ha ocurrido un error al añadir el amigo " + e);
            }
        });
    }
}
