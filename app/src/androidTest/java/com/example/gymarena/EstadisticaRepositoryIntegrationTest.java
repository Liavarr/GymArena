package com.example.gymarena;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.gymarena.auth.SessionManager;
import com.example.gymarena.model.Ejercicio;
import com.example.gymarena.model.Estadistica;
import com.example.gymarena.model.Usuario;
import com.example.gymarena.repository.DAOInterface;
import com.example.gymarena.repository.EstadisticaDAO;
import com.example.gymarena.repository.EstadisticaRepository;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
@RunWith(AndroidJUnit4.class)
public class EstadisticaRepositoryIntegrationTest {
    SessionManager sessionManager;
    private EstadisticaRepository repository;
    private EstadisticaDAO estadisticaDAO;

    private Usuario usuario;
    private Usuario amigo;
    private Ejercicio ejercicio;

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
        repository = new EstadisticaRepository();
        estadisticaDAO = new EstadisticaDAO(FirebaseFirestore.getInstance());

        usuario = new Usuario("User", "user@email.com", 70, 1.75);
        usuario.setIdUsuario("user123");

        amigo = new Usuario("Friend", "friend@email.com", 80, 1.80);
        amigo.setIdUsuario("friend123");

        ejercicio = new Ejercicio("ejercicio123", "Flexiones", Arrays.asList());
    }

    @Test
    public void testCompararEstadisticaUserLevantaMas() throws InterruptedException {
        // Primero creamos estadísticas reales en Firebase
        CountDownLatch latch = new CountDownLatch(1);

        Estadistica usuarioEstadistica = new Estadistica(usuario.getIdUsuario(), ejercicio.getIdEjercicio(), new Date(), 50.0, 15);
        Estadistica amigoEstadistica = new Estadistica(amigo.getIdUsuario(), ejercicio.getIdEjercicio(), new Date(), 40.0, 10);

        // Crear estadísticas
        estadisticaDAO.crear(usuarioEstadistica, new DAOInterface.OnCreado<Estadistica>() {
            @Override
            public void onSuccess(String idUserEst) {
                estadisticaDAO.crear(amigoEstadistica, new DAOInterface.OnCreado<Estadistica>() {
                    @Override
                    public void onSuccess(String idFriendEst) {
                        // Comparación real
                        repository.compararEstadistica(estadisticaDAO, usuario, amigo, ejercicio, new EstadisticaRepository.ComparacionCallback() {
                            @Override
                            public void onResultado(String resultado) {
                                try {
                                    assertEquals("Tú levantas más peso.\nTú haces más repeticiones.", resultado);
                                } finally {
                                    latch.countDown();
                                }
                            }

                            @Override
                            public void onError(Exception e) {
                                latch.countDown();
                                fail("No debía fallar: " + e.getMessage());
                            }
                        });
                    }

                    @Override
                    public void onFailure(Exception e) {
                        latch.countDown();
                        fail("Error al crear estadística del amigo: " + e.getMessage());
                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
                latch.countDown();
                fail("Error al crear estadística del usuario: " + e.getMessage());
            }
        });

        // Espera máxima 10 segundos
        /*if (!latch.await(10, TimeUnit.SECONDS)) {
            fail("Timeout: la operación no finalizó a tiempo");
        }*/
    }
}
