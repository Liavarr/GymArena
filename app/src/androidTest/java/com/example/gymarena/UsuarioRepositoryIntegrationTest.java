package com.example.gymarena;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.example.gymarena.auth.SessionManager;
import com.example.gymarena.model.Usuario;
import com.example.gymarena.repository.DAOInterface;
import com.example.gymarena.repository.UsuarioDAO;
import com.example.gymarena.repository.UsuarioRepository;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class UsuarioRepositoryIntegrationTest {
    SessionManager sessionManager;
    private UsuarioDAO usuarioDAO;
    private UsuarioRepository usuarioRepository;
    private Usuario currentUser;

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
        usuarioDAO = new UsuarioDAO(FirebaseFirestore.getInstance());
        usuarioRepository = new UsuarioRepository();

        currentUser = new Usuario();
        currentUser.setIdUsuario("user123");
        currentUser.setListaAmigos(new ArrayList<>());
    }

    @Test
    public void testAnadirAmigo() throws InterruptedException {
        String idAmigo = "amigo456";
        CountDownLatch latch = new CountDownLatch(1);

        // Llamada real al repository que actualiza Firebase
        usuarioRepository.anadirAmigo(usuarioDAO, currentUser, idAmigo);

        // Esperamos a que el callback del DAO termine
        usuarioDAO.actualizar(currentUser.getIdUsuario(), currentUser, new DAOInterface.OnActualizado() {
            @Override
            public void onSuccess() {
                try {
                    assertTrue(currentUser.getListaAmigos().contains(idAmigo));
                } finally {
                    latch.countDown();
                }
            }

            @Override
            public void onFailure(Exception e) {
                latch.countDown();
                fail("Error al añadir amigo: " + e.getMessage());
            }
        });

        /*if (!latch.await(5, TimeUnit.SECONDS)) {
            fail("Timeout: la operación no finalizó a tiempo");
        }*/
    }

    @Test
    public void testEliminarAmigo() throws InterruptedException {
        String idAmigo = "amigo456";
        currentUser.getListaAmigos().add(idAmigo);
        CountDownLatch latch = new CountDownLatch(1);

        usuarioRepository.eliminarAmigo(usuarioDAO, currentUser, idAmigo);

        usuarioDAO.actualizar(currentUser.getIdUsuario(), currentUser, new DAOInterface.OnActualizado() {
            @Override
            public void onSuccess() {
                try {
                    assertTrue(!currentUser.getListaAmigos().contains(idAmigo));
                } finally {
                    latch.countDown();
                }
            }

            @Override
            public void onFailure(Exception e) {
                latch.countDown();
                fail("Error al eliminar amigo: " + e.getMessage());
            }
        });

        /*if (!latch.await(5, TimeUnit.SECONDS)) {
            fail("Timeout: la operación no finalizó a tiempo");
        }*/
    }
}
