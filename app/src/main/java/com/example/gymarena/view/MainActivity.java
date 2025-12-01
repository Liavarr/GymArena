package com.example.gymarena.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gymarena.R;
import com.example.gymarena.auth.FirebaseConexion;
import com.example.gymarena.auth.SessionManager;
import com.example.gymarena.model.Chatbot;
import com.example.gymarena.model.Ejercicio;
import com.example.gymarena.model.Estadistica;
import com.example.gymarena.model.Rutina;
import com.example.gymarena.model.Usuario;
import com.example.gymarena.repository.DAOInterface;
import com.example.gymarena.repository.EjercicioDAO;
import com.example.gymarena.repository.EstadisticaDAO;
import com.example.gymarena.repository.RutinaDAO;
import com.example.gymarena.repository.UsuarioDAO;
import com.example.gymarena.repository.UsuarioRepository;
import com.example.gymarena.util.EjerciciosList;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends BaseActivity {
    private Usuario usuarioTestAnadirAmigo;
    protected TextView notificaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Logeo basico
        SessionManager session = SessionManager.getInstancia();
        //Obtener conexion a la bbdd
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //Validar login
        validarLogin(session, R.layout.activity_main);

        System.out.println("Usuario actual:"+ session.getmAuth().getCurrentUser().getUid());
        //test();
        //testCrearUsuario(db);
        //testCrearRutina(db);
        //testCrearEjercicio(db);
        //testCrearEstadistica(db);
        //testAnadirAmigo(db);
        //testEliminarAmigo(db);
        //testChatRutina(db);
        testChat(db);
    }

    @Override
    protected void afterLayoutLoaded() {
        notificaciones = findViewById(R.id.textoNotificaciones);
    }

    private void testCrearUsuario(FirebaseFirestore db){
        UsuarioDAO usuarioDAO = new UsuarioDAO(db);
        Usuario usuario1 = new Usuario("DAOTest","email@gmail.com",90.00,1.75);

        usuario1.getListaAmigos().add("1234");
        usuario1.getListaAmigos().add("4312");
        usuario1.getListaAmigos().add("2222");
        usuario1.getListaAmigos().add("3333");
        usuarioDAO.crear(usuario1, new DAOInterface.OnCreado<Usuario>() {
            @Override
            public void onSuccess(String idDocumento) {
                Log.d("Crear Usuario", "Usuario creada");
            }
            @Override
            public void onFailure(Exception e) {
                Log.d("Crear Usuario", "onFailure: "+e);
            }
        });
    }

    private void testCrearRutina(FirebaseFirestore db){
        RutinaDAO rutinaDAO = new RutinaDAO(db);
        Rutina rutina1 = new Rutina();
        Rutina rutina2 = new Rutina();

        Ejercicio ejercicio1 = new Ejercicio();
        Ejercicio ejercicio2 = new Ejercicio();

        rutina1.getEjercicios().add("Brazos");
        rutina1.getEjercicios().add("Piernas");
        rutina1.setIdUsuario("123");


        rutinaDAO.crear(rutina1, new DAOInterface.OnCreado<Rutina>(){
            @Override
            public void onSuccess(String idDocumento) {
                Log.d("Crear Rutina", "Rutina creada");
            }
            @Override
            public void onFailure(Exception e) {
                Log.d("Crear Rutina", "onFailure: "+e);
            }
        });
    }

    private void testCrearEjercicio(FirebaseFirestore db){
        EjercicioDAO ejercicioDAO = new EjercicioDAO(db);
        Ejercicio ejercicio1 = new Ejercicio("Press Banca", "Pecho", new ArrayList<String>());
        ejercicio1.getMusculoSecundario().add("Triceps");
        ejercicio1.getMusculoSecundario().add("Hombro");

        ejercicioDAO.crear(ejercicio1, new DAOInterface.OnCreado<Ejercicio>(){
            @Override
            public void onSuccess(String idDocumento) {
                Log.d("Crear Ejercicio", "Ejercicio creada");
            }
            @Override
            public void onFailure(Exception e) {
                Log.d("Crear Ejercicio", "onFailure: "+e);
            }
        });
    }

    private void testCrearEstadistica(FirebaseFirestore db) {
        EstadisticaDAO estadisticaDAO = new EstadisticaDAO(db);

        // Crear una estadística de ejemplo
        Estadistica estadistica = new Estadistica("usuario123", "ejercicio456", new Date(), 80.0, 10
        );
        estadisticaDAO.crear(estadistica, new DAOInterface.OnCreado<Estadistica>() {
            @Override
            public void onSuccess(String idDocumento) {
                Log.d("Crear Estadistica", "Estadística creada con ID: " + idDocumento);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("Crear Estadistica", "Error al crear estadística", e);
            }
        });
    }

    private void testAnadirAmigo(FirebaseFirestore db){

        UsuarioDAO usuarioDAO = new UsuarioDAO(db);
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        usuarioDAO.obtenerUno("mFkCQymSQ69LWZkcWDtA", new DAOInterface.OnObtenido<Usuario>() {
            @Override
            public void onSuccess(Usuario objeto) {
                usuarioRepository.anadirAmigo(usuarioDAO, objeto, "123455");
            }

            @Override
            public void onFailure(Exception e) {

            }
        });



    }

    private void testEliminarAmigo(FirebaseFirestore db){
        UsuarioDAO usuarioDAO = new UsuarioDAO(db);
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        usuarioDAO.obtenerUno("mFkCQymSQ69LWZkcWDtA", new DAOInterface.OnObtenido<Usuario>() {
            @Override
            public void onSuccess(Usuario objeto) {
                usuarioRepository.eliminarAmigo(usuarioDAO, objeto, "123455");
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    private void testChat(){

    }

    private void testChatRutina(FirebaseFirestore db) {
        // Prompt de prueba
        String promptUsuario = "Crea una rutina de pecho y espalda para principiante";
        // Usuario de prueba
        Usuario usuario1 = new Usuario("DAOTest","email@gmail.com",90.00,1.75);
        // RutinaDAO para poder hacer llamadas
        RutinaDAO rutinaDAO = new RutinaDAO(db);
        // Lista de ejercicios
        ArrayList<Ejercicio> ejerciciosReferencia = EjerciciosList.getEjerciciosReferencia();

        Chatbot.getInstance().chat(promptUsuario, usuario1.getIdUsuario(), ejerciciosReferencia,
                new Chatbot.ChatbotRutinaCallback() {
                    @Override
                    public void onSuccess(String texto, Rutina rutina) {
                        runOnUiThread(() -> notificaciones.setText(texto));

                        if (rutina != null) {
                            rutinaDAO.crear(rutina, new DAOInterface.OnCreado<Rutina>() {
                                @Override
                                public void onSuccess(String idDocumento) {
                                    Log.d("Crear rutina por chatbot", "Rutina creada por chatbot: " + idDocumento);
                                }

                                @Override
                                public void onFailure(Exception e) {
                                    Log.d("Crear rutina por chatbot", "Rutina fallida: " + e);
                                }
                            });
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("Error Chatbot en main", "onError: Error al crear el chat con el bot" + t);

                    }
                }
        );
    }

    private void testChat(FirebaseFirestore db) {
        // Prompt de prueba
        String promptUsuario = "Me he lesionado y creo que me duele el hombro, que me puedo tomar?";
        // Usuario de prueba
        Usuario usuario1 = new Usuario("DAOTest","email@gmail.com",90.00,1.75);
        // RutinaDAO para poder hacer llamadas
        RutinaDAO rutinaDAO = new RutinaDAO(db);
        // Lista de ejercicios
        ArrayList<Ejercicio> ejerciciosReferencia = EjerciciosList.getEjerciciosReferencia();

        Chatbot.getInstance().chat(promptUsuario, usuario1.getIdUsuario(), ejerciciosReferencia,
                new Chatbot.ChatbotRutinaCallback() {
                    @Override
                    public void onSuccess(String texto, Rutina rutina) {
                        runOnUiThread(() -> System.out.println(texto));

                        if (rutina != null) {
                            rutinaDAO.crear(rutina, new DAOInterface.OnCreado<Rutina>() {
                                @Override
                                public void onSuccess(String idDocumento) {
                                    Log.d("Crear conversacion", "chat creado con el chatbot para textp " + idDocumento);
                                }

                                @Override
                                public void onFailure(Exception e) {
                                    Log.d("Crear conversacion", "Error al responder " + e);
                                }
                            });
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("Crear conversacion", "onError: Error al crear el chat con el bot" + t);

                    }
                }
        );
    }
}