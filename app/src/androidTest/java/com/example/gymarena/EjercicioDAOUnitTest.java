package com.example.gymarena;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.gymarena.auth.SessionManager;
import com.example.gymarena.model.Ejercicio;
import com.example.gymarena.repository.DAOInterface;
import com.example.gymarena.repository.EjercicioDAO;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
@RunWith(AndroidJUnit4.class)
public class EjercicioDAOUnitTest {
    SessionManager sessionManager;

    private FirebaseFirestore db;
    private EjercicioDAO ejercicioDAO;

    private String idEjercicioActualizarObtener = "zIwgArlYrfmWWtsWNV7e";
    private String idEjercicioEliminar= "N97x8Cizrr7f4E6nUacp";

    @Before
    public void setUp() throws InterruptedException {
        sessionManager = SessionManager.getInstancia();
        CountDownLatch latchLogin = new CountDownLatch(1);
        sessionManager.login("test@gmail.com", "test123", new SessionManager.LoginCallback() {
            @Override
            public void onLoginSuccess(FirebaseUser user) {
                latchLogin.countDown(); // indica que login ha terminado
            }
            @Override
            public void onLoginFailed(Exception e) {
                latchLogin.countDown(); // indica que login ha terminado
            }
        });
        latchLogin.await();
        db = FirebaseFirestore.getInstance();
        ejercicioDAO = new EjercicioDAO(db);
    }

    // Test crear ejercicio
    @Test
    public void testCrearEjercicio() throws InterruptedException {
        Ejercicio ejercicio = new Ejercicio("Flexiones", "Pecho y brazos", new ArrayList<>());

        CountDownLatch latch = new CountDownLatch(1);
        ejercicioDAO.crear(ejercicio, new DAOInterface.OnCreado<Ejercicio>() {
            @Override
            public void onSuccess(String id) {
                assertNotNull(id);
                latch.countDown();
            }

            @Override
            public void onFailure(Exception e) {
                fail("Error al crear ejercicio: " + e.getMessage());
                latch.countDown();
            }
        });

        latch.await();
    }

    // Test obtener un ejercicio por ID
    @Test
    public void testObtenerEjercicio() throws InterruptedException {
        String idExistente = idEjercicioActualizarObtener;

        CountDownLatch latch = new CountDownLatch(1);

        ejercicioDAO.obtenerUno(idExistente, new DAOInterface.OnObtenido<Ejercicio>() {
            @Override
            public void onSuccess(Ejercicio ejercicio) {
                assertNotNull(ejercicio);
                assertEquals(idExistente, ejercicio.getIdEjercicio());
                latch.countDown();
            }

            @Override
            public void onFailure(Exception e) {
                fail("No se encontró el ejercicio: " + e.getMessage());
                latch.countDown();
            }
        });

        latch.await();
    }

    // Test obtener todos los ejercicios
    @Test
    public void testObtenerTodosEjercicios() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        ejercicioDAO.obtenerTodo(new DAOInterface.OnTodosObtenidos<Ejercicio>() {
            @Override
            public void onSuccess(List<Ejercicio> lista) {
                assertNotNull(lista);
                assertTrue(lista.size() > 0);
                latch.countDown();
            }

            @Override
            public void onFailure(Exception e) {
                fail("Error al obtener ejercicios: " + e.getMessage());
                latch.countDown();
            }
        });

        latch.await();
    }

    // Test actualizar ejercicio
    @Test
    public void testActualizarEjercicio() throws InterruptedException {
        String idExistente = idEjercicioActualizarObtener;
        Ejercicio actualizado = new Ejercicio("Flexiones Avanzadas", "Pecho y brazos", new ArrayList<>());

        CountDownLatch latch = new CountDownLatch(1);

        ejercicioDAO.actualizar(idExistente, actualizado, new DAOInterface.OnActualizado() {
            @Override
            public void onSuccess() {
                assertTrue(true);
                latch.countDown();
            }

            @Override
            public void onFailure(Exception e) {
                fail("Error al actualizar: " + e.getMessage());
                latch.countDown();
            }
        });

        latch.await();
    }

    // Test eliminar ejercicio
    @Test
    public void testEliminarEjercicio() throws InterruptedException {
        String idAEliminar = idEjercicioEliminar;

        CountDownLatch latch = new CountDownLatch(1);

        ejercicioDAO.eliminar(idAEliminar, new DAOInterface.OnEliminado() {
            @Override
            public void onSuccess() {
                assertTrue(true);
                latch.countDown();
            }

            @Override
            public void onFailure(Exception e) {
                fail("Error al eliminar: " + e.getMessage());
                latch.countDown();
            }
        });

        latch.await();
    }
}
