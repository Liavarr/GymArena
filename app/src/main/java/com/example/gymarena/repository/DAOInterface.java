package com.example.gymarena.repository;

import java.util.List;

public interface DAOInterface<T> {
    void crear(T objeto, OnCreado<T> listener);

    void obtenerUno(String idDocumento, OnObtenido<T> listener);

    void obtenerTodo(OnTodosObtenidos<T> listener);

    void actualizar(String idDocumento, T objeto, OnActualizado listener);

    void eliminar(String idDocumento, OnEliminado listener);

    // CALLBACKS GENÉRICOS

    interface OnCreado<T> {
        void onSuccess(String idDocumento);
        void onFailure(Exception e);
    }

    interface OnObtenido<T> {
        void onSuccess(T objeto);
        void onFailure(Exception e);
    }

    interface OnTodosObtenidos<T> {
        void onSuccess(List<T> lista);
        void onFailure(Exception e);
    }

    interface OnActualizado {
        void onSuccess();
        void onFailure(Exception e);
    }

    interface OnEliminado {
        void onSuccess();
        void onFailure(Exception e);
    }
}
