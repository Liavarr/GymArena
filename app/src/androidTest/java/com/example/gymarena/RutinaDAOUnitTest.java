package com.example.gymarena;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.gymarena.auth.SessionManager;
import com.example.gymarena.model.Rutina;
import com.example.gymarena.repository.DAOInterface;
import com.example.gymarena.repository.RutinaDAO;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@RunWith(AndroidJUnit4.class)
public class RutinaDAOUnitTest {

    private FirebaseFirestore db;
    private RutinaDAO rutinaDAO;
    SessionManager sessionManager;
    private String idRutinaActualizarObtener = "zIwgArlYrfmWWtsWNV7e";
    private String idRutinaEliminar= "N97x8Cizrr7f4E6nUacp";

    @Before
    public void setUp() {
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
        db = FirebaseFirestore.getInstance();
        rutinaDAO = new RutinaDAO(db);
    }

    // Test crear rutina
    @Test
    public void testCrearRutina() throws InterruptedException {
        Rutina rutina = new Rutina(null, "usuario123", "Rutina Full Body", "Descripción de prueba");
        rutina.setEjerciciosId(new ArrayList<>()); // lista vacía inicial

        CountDownLatch latch = new CountDownLatch(1);

        rutinaDAO.crear(rutina, new DAOInterface.OnCreado<Rutina>() {
            @Override
            public void onSuccess(String id) {
                assertNotNull(id);
                latch.countDown();
            }

            @Override
            public void onFailure(Exception e) {
                fail("Error al crear rutina: " + e.getMessage());
                latch.countDown();
            }
        });

        latch.await();
    }

    // Test obtener una rutina por ID
    @Test
    public void testObtenerRutina() throws InterruptedException {
        String idExistente = idRutinaActualizarObtener;

        CountDownLatch latch = new CountDownLatch(1);

        rutinaDAO.obtenerUno(idExistente, new DAOInterface.OnObtenido<Rutina>() {
            @Override
            public void onSuccess(Rutina rutina) {
                assertNotNull(rutina);
                assertEquals(idExistente, rutina.getIdRutina());
                latch.countDown();
            }

            @Override
            public void onFailure(Exception e) {
                fail("No se encontró la rutina: " + e.getMessage());
                latch.countDown();
            }
        });

        latch.await();
    }

    // Test obtener todas las rutinas
    @Test
    public void testObtenerTodasRutinas() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        rutinaDAO.obtenerTodo(new DAOInterface.OnTodosObtenidos<Rutina>() {
            @Override
            public void onSuccess(List<Rutina> lista) {
                assertNotNull(lista);
                assertTrue(lista.size() > 0);
                latch.countDown();
            }

            @Override
            public void onFailure(Exception e) {
                fail("Error al obtener rutinas: " + e.getMessage());
                latch.countDown();
            }
        });

        latch.await();
    }

    // Test actualizar rutina
    @Test
    public void testActualizarRutina() throws InterruptedException {
        String idExistente = idRutinaActualizarObtener;
        Rutina actualizado = new Rutina(idExistente, "usuario123", "Rutina Full Body Avanzada", "Descripción actualizada");
        actualizado.setEjerciciosId(new ArrayList<>()); // lista vacía para test

        CountDownLatch latch = new CountDownLatch(1);

        rutinaDAO.actualizar(idExistente, actualizado, new DAOInterface.OnActualizado() {
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

    // Test eliminar rutina
    @Test
    public void testEliminarRutina() throws InterruptedException {
        String idAEliminar = idRutinaEliminar;

        CountDownLatch latch = new CountDownLatch(1);

        rutinaDAO.eliminar(idAEliminar, new DAOInterface.OnEliminado() {
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
