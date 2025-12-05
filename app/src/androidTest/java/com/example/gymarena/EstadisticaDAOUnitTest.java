package com.example.gymarena;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.gymarena.auth.SessionManager;
import com.example.gymarena.model.Estadistica;
import com.example.gymarena.repository.DAOInterface;
import com.example.gymarena.repository.EstadisticaDAO;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@RunWith(AndroidJUnit4.class)
public class EstadisticaDAOUnitTest {
    private FirebaseFirestore db;
    private EstadisticaDAO estadisticaDAO;
    SessionManager sessionManager;
    private String idEstadisticaActualizarObtener = "s5VfxphBQ8y0fO6QgON2";
    private String idUsuarioActualizarObtener = "7a9VEH2UnH29AS2zdmCe";
    private String idEjercicioActualizarObtener = "N97x8Cizrr7f4E6nUacp";
    private String idEstadisticaBorrar = "zSQfSyXgU6fydfd7vz5c";

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
        estadisticaDAO = new EstadisticaDAO(db);
    }

    // Test crear estadistica
    @Test
    public void testCrearEstadistica() throws InterruptedException {
        Estadistica estadistica = new Estadistica("usuario123", "ejercicio123", new Date(), 50.0, 15);

        CountDownLatch latch = new CountDownLatch(1);

        estadisticaDAO.crear(estadistica, new DAOInterface.OnCreado<Estadistica>() {
            @Override
            public void onSuccess(String id) {
                assertNotNull(id);
                latch.countDown();
            }

            @Override
            public void onFailure(Exception e) {
                fail("Error al crear estadística: " + e.getMessage());
                latch.countDown();
            }
        });

        latch.await();
    }

    // Test obtener una estadistica por ID
    @Test
    public void testObtenerEstadistica() throws InterruptedException {
        String idExistente = idEstadisticaActualizarObtener;

        CountDownLatch latch = new CountDownLatch(1);

        estadisticaDAO.obtenerUno(idExistente, new DAOInterface.OnObtenido<Estadistica>() {
            @Override
            public void onSuccess(Estadistica estadistica) {
                assertNotNull(estadistica);
                assertEquals(idExistente, estadistica.getIdEstadistica());
                latch.countDown();
            }

            @Override
            public void onFailure(Exception e) {
                fail("No se encontró la estadística: " + e.getMessage());
                latch.countDown();
            }
        });

        latch.await();
    }

    // Test obtener todas las estadisticas
    @Test
    public void testObtenerTodasEstadisticas() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        estadisticaDAO.obtenerTodo(new DAOInterface.OnTodosObtenidos<Estadistica>() {
            @Override
            public void onSuccess(List<Estadistica> lista) {
                assertNotNull(lista);
                assertTrue(lista.size() > 0);
                latch.countDown();
            }

            @Override
            public void onFailure(Exception e) {
                fail("Error al obtener estadísticas: " + e.getMessage());
                latch.countDown();
            }
        });

        latch.await();
    }

    // Test obtener la última estadistica de un ejercicio
    @Test
    public void testObtenerUltimaEstadisticaPorEjercicio() throws InterruptedException {
        String idUsuario = idUsuarioActualizarObtener;
        String idEjercicio = idEjercicioActualizarObtener;

        CountDownLatch latch = new CountDownLatch(1);

        estadisticaDAO.obtenerUltimaEstadisticaPorEjercicio(idUsuario, idEjercicio, new DAOInterface.OnTodosObtenidos<Estadistica>() {
            @Override
            public void onSuccess(List<Estadistica> lista) {
                assertNotNull(lista);
                if (!lista.isEmpty()) {
                    Estadistica e = lista.get(0);
                    assertEquals(idUsuario, e.getIdUsuario());
                    assertEquals(idEjercicio, e.getIdEjercicio());
                }
                latch.countDown();
            }

            @Override
            public void onFailure(Exception e) {
                fail("Error al obtener última estadística: " + e.getMessage());
                latch.countDown();
            }
        });

        latch.await();
    }

    // Test actualizar estadistica
    @Test
    public void testActualizarEstadistica() throws InterruptedException {
        String idExistente = idEstadisticaActualizarObtener;
        Estadistica actualizado = new Estadistica("usuario123", "ejercicio123", new Date(), 55.0, 20);

        CountDownLatch latch = new CountDownLatch(1);

        estadisticaDAO.actualizar(idExistente, actualizado, new DAOInterface.OnActualizado() {
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

    // Test eliminar estadistica
    @Test
    public void testEliminarEstadistica() throws InterruptedException {
        String idAEliminar = idEstadisticaBorrar;

        CountDownLatch latch = new CountDownLatch(1);

        estadisticaDAO.eliminar(idAEliminar, new DAOInterface.OnEliminado() {
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
