package com.example.gymarena;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.gymarena.auth.SessionManager;
import com.example.gymarena.model.Usuario;
import com.example.gymarena.repository.DAOInterface;
import com.example.gymarena.repository.UsuarioDAO;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@RunWith(AndroidJUnit4.class)
public class UsuarioDAOUnitTest {
    SessionManager sessionManager;
    private FirebaseFirestore db;
    private UsuarioDAO usuarioDAO;
    private String idUsuarioActualizarObtener = "7a9VEH2UnH29AS2zdmCe";
    private String idUsuarioEliminar= "qfnobaF7UvyGhJ4XAAEb";

    // Test crear usuario
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

        usuarioDAO = new UsuarioDAO(db);
    }

    // Obtener un usuario
    @Test
    public void testObtenerUsuario() throws InterruptedException {

        String idExistente = idUsuarioActualizarObtener;

        CountDownLatch latch = new CountDownLatch(1);

        usuarioDAO.obtenerUno(idExistente, new DAOInterface.OnObtenido<Usuario>() {
            @Override
            public void onSuccess(Usuario usuario) {
                assertNotNull(usuario);
                assertEquals(idExistente, usuario.getIdUsuario());
                latch.countDown();
            }

            @Override
            public void onFailure(Exception e) {
                fail("No se encontró el usuario");
                latch.countDown();
            }
        });

        latch.await();
    }

    // Obtener todos usuarios
    @Test
    public void testObtenerTodos() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        usuarioDAO.obtenerTodo(new DAOInterface.OnTodosObtenidos<Usuario>() {
            @Override
            public void onSuccess(List<Usuario> lista) {
                assertNotNull(lista);
                assertTrue(lista.size() > 0);
                latch.countDown();
            }

            @Override
            public void onFailure(Exception e) {
                fail("Error al obtener usuarios");
                latch.countDown();
            }
        });

        latch.await();
    }

    // Test actualizar usuario
    @Test
    public void testActualizarUsuario() throws InterruptedException {

        String idExistente = idUsuarioActualizarObtener;
        Usuario actualizado = new Usuario("NuevoNombre", "nuevo@email.com", 82, 1.75);

        CountDownLatch latch = new CountDownLatch(1);

        usuarioDAO.actualizar(idExistente, actualizado, new DAOInterface.OnActualizado() {
            @Override
            public void onSuccess() {
                assertTrue(true);
                latch.countDown();
            }

            @Override
            public void onFailure(Exception e) {
                fail("Error al actualizar");
                latch.countDown();
            }
        });

        latch.await();
    }

    // Test eliminar usuario
    @Test
    public void testEliminarUsuario() throws InterruptedException {

        String idAEliminar = idUsuarioEliminar;

        CountDownLatch latch = new CountDownLatch(1);

        usuarioDAO.eliminar(idAEliminar, new DAOInterface.OnEliminado() {
            @Override
            public void onSuccess() {
                assertTrue(true);
                latch.countDown();
            }

            @Override
            public void onFailure(Exception e) {
                fail("Error al eliminar");
                latch.countDown();
            }
        });

        latch.await();
    }
}
