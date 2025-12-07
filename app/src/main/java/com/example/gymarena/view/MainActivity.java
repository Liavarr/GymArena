package com.example.gymarena.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.gymarena.R;
import com.example.gymarena.auth.SessionManager;
import com.example.gymarena.model.Chatbot;
import com.example.gymarena.model.Ejercicio;
import com.example.gymarena.model.Estadistica;
import com.example.gymarena.model.Rutina;
import com.example.gymarena.model.Usuario;
import com.example.gymarena.repository.DAOInterface;
import com.example.gymarena.repository.EjercicioDAO;
import com.example.gymarena.repository.EstadisticaDAO;
import com.example.gymarena.repository.EstadisticaRepository;
import com.example.gymarena.repository.RutinaDAO;
import com.example.gymarena.repository.UsuarioDAO;
import com.example.gymarena.repository.UsuarioRepository;
import com.example.gymarena.util.EjerciciosList;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MainActivity extends BaseActivity {
    private Usuario usuarioTestAnadirAmigo;
    protected TextView notificaciones;
    protected TextView rutinas;
    Usuario currentUser;
    Usuario amigo;
    Ejercicio ejercicio;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Logeo basico
        session = SessionManager.getInstancia();
        //Obtener conexion a la bbdd
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //Validar login
        validarLogin(session, R.layout.activity_main);


        //test();
        //testCrearUsuario(db);
        //testCrearRutina(db);
        //testCrearEjercicio(db);
        //testCrearEstadistica(db);
        //testAnadirAmigo(db);
        //testEliminarAmigo(db);
        //testChatRutina(db);
        //testChat(db);
        //testCompararEstadistica(db);
        //datosMinimos(db);
    }

    @Override
    protected void afterLayoutLoaded() {
        notificaciones = findViewById(R.id.textoNotificaciones);
        rutinas = findViewById(R.id.textoRutinas);
        //System.out.println("Usuario actual:"+ session.getmAuth().getCurrentUser().getUid());
    }

    private void testCrearUsuario(FirebaseFirestore db){
        UsuarioDAO usuarioDAO = new UsuarioDAO(db);
        Usuario usuario1 = new Usuario(
                "Marcos",
                "marcos@example.com",
                82.0,        // peso corporal
                1.75         // altura
        );

        usuario1.getListaAmigos().add("1234");
        usuario1.getListaAmigos().add("4312");
        usuario1.getListaAmigos().add("2222");
        usuario1.getListaAmigos().add("3333");
        usuarioDAO.crear(usuario1, new DAOInterface.OnCreado<Usuario>() {
            @Override
            public void onSuccess(String idDocumento) {
                Log.d("Crear Usuario", "Usuario creada"+idDocumento);
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

        rutina1.getEjerciciosId().add("Brazos");
        rutina1.getEjerciciosId().add("Piernas");
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
        Ejercicio ejercicio1 = new Ejercicio(
                "Press Banca",              // nombre
                "Pecho",                    // musculo principal
                new ArrayList<String>()     // secundarios
        );
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
        Estadistica estadistica = new Estadistica(
                "U2",          // idUsuario
                "E001",        // idEjercicio
                new Date(),
                70.0,          // peso levantado
                12             // repeticiones totales
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


    private void testChatRutina(FirebaseFirestore db) {
        // Prompt de prueba
        String promptUsuario = "Crea una rutina de hombro para principiante";
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
                        runOnUiThread(() -> rutinas.setText(texto));

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

    public void testCompararEstadistica(FirebaseFirestore db) {
        UsuarioDAO usuarioDAO = new UsuarioDAO(db);
        EjercicioDAO ejercicioDAO = new EjercicioDAO(db);
        EstadisticaDAO estadisticaDAO = new EstadisticaDAO(db);
        EstadisticaRepository estadisticaRepository = new EstadisticaRepository();

        // 1️⃣ Obtener usuario
        usuarioDAO.obtenerUno("9pGyiuKNI8ONEcjXyGyZ", new DAOInterface.OnObtenido<Usuario>() {
            @Override
            public void onSuccess(Usuario user) {
                currentUser = user;

                // 2️⃣ Obtener amigo
                usuarioDAO.obtenerUno("w4FyGtwDHpvjG0bxCxqH", new DAOInterface.OnObtenido<Usuario>() {
                    @Override
                    public void onSuccess(Usuario user2) {
                        amigo = user2;

                        // 3️⃣ Obtener ejercicio
                        ejercicioDAO.obtenerUno("tWWJEIp14jXtmKcol1pL", new DAOInterface.OnObtenido<Ejercicio>() {
                            @Override
                            public void onSuccess(Ejercicio ej) {
                                ejercicio = ej;

                                // 4️⃣ Ahora sí, hacer la comparación
                                estadisticaRepository.compararEstadistica(
                                        estadisticaDAO,
                                        currentUser,
                                        amigo,
                                        ejercicio,
                                        new EstadisticaRepository.ComparacionCallback() {
                                            @Override
                                            public void onResultado(String resultado) {
                                                System.out.println("Hemos llegado a la comparacion:");
                                                System.out.println(resultado);
                                            }

                                            @Override
                                            public void onError(Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                );
                            }

                            @Override
                            public void onFailure(Exception e) {
                                e.printStackTrace();
                            }
                        });

                    }

                    @Override
                    public void onFailure(Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });

    }

    public static void datosMinimos(FirebaseFirestore db) {
        // --- Crear usuarios ---
        UsuarioDAO usuarioDAO = new UsuarioDAO(db);
        UsuarioRepository usuarioRepository = new UsuarioRepository();

        Usuario usuario1 = new Usuario("Marcos", "marcos@example.com", 82.0, 1.75);
        Usuario usuario2 = new Usuario("Luis", "luis@example.com", 75.0, 1.80);


        usuarioDAO.crear(usuario1, new DAOInterface.OnCreado<Usuario>() {
            @Override
            public void onSuccess(String idDoc1) {
                usuario1.setIdUsuario(idDoc1);
                Log.d("DatosPrueba", "Usuario1 creado con ID: " + idDoc1);

                usuarioDAO.crear(usuario2, new DAOInterface.OnCreado<Usuario>() {
                    @Override
                    public void onSuccess(String idDoc2) {
                        usuario2.setIdUsuario(idDoc2);
                        Log.d("DatosPrueba", "Usuario2 creado con ID: " + idDoc2);

                        // --- Crear ejercicios ---
                        EjercicioDAO ejercicioDAO = new EjercicioDAO(db);

                        Ejercicio ejercicio1 = new Ejercicio("Press Banca", "Pecho", new ArrayList<>(Arrays.asList("Triceps", "Hombro")));
                        Ejercicio ejercicio2 = new Ejercicio("Sentadillas", "Piernas", new ArrayList<>(Arrays.asList("Gluteos", "Espalda baja")));

                        //Los amigos se añaden entre sí
                        usuarioRepository.anadirAmigo(usuarioDAO, usuario1, usuario2.getIdUsuario());
                        usuarioRepository.anadirAmigo(usuarioDAO, usuario2, usuario1.getIdUsuario());
                        ejercicioDAO.crear(ejercicio1, new DAOInterface.OnCreado<Ejercicio>() {
                            @Override
                            public void onSuccess(String idEj1) {
                                ejercicio1.setIdEjercicio(idEj1);
                                Log.d("DatosPrueba", "Ejercicio1 creado con ID: " + idEj1);

                                ejercicioDAO.crear(ejercicio2, new DAOInterface.OnCreado<Ejercicio>() {
                                    @Override
                                    public void onSuccess(String idEj2) {
                                        ejercicio2.setIdEjercicio(idEj2);
                                        Log.d("DatosPrueba", "Ejercicio2 creado con ID: " + idEj2);

                                        // --- Crear rutinas ---
                                        RutinaDAO rutinaDAO = new RutinaDAO(db);

                                        Rutina rutina1 = new Rutina();
                                        rutina1.setIdUsuario(usuario1.getIdUsuario());
                                        rutina1.setNombre("Rutina Upper Body");
                                        rutina1.setDescripcion("Rutina para pecho y brazos");
                                        rutina1.getEjerciciosId().add(ejercicio1.getIdEjercicio());

                                        Rutina rutina2 = new Rutina();
                                        rutina2.setIdUsuario(usuario2.getIdUsuario());
                                        rutina2.setNombre("Rutina Lower Body");
                                        rutina2.setDescripcion("Rutina para piernas y gluteos");
                                        rutina2.getEjerciciosId().add(ejercicio2.getIdEjercicio());

                                        rutinaDAO.crear(rutina1, new DAOInterface.OnCreado<Rutina>() {
                                            @Override
                                            public void onSuccess(String idRut1) {
                                                rutina1.setIdRutina(idRut1);
                                                Log.d("DatosPrueba", "Rutina1 creada con ID: " + idRut1);
                                            }

                                            @Override
                                            public void onFailure(Exception e) {
                                                Log.e("DatosPrueba", "Error al crear Rutina1", e);
                                            }
                                        });

                                        rutinaDAO.crear(rutina2, new DAOInterface.OnCreado<Rutina>() {
                                            @Override
                                            public void onSuccess(String idRut2) {
                                                rutina2.setIdRutina(idRut2);
                                                Log.d("DatosPrueba", "Rutina2 creada con ID: " + idRut2);
                                            }

                                            @Override
                                            public void onFailure(Exception e) {
                                                Log.e("DatosPrueba", "Error al crear Rutina2", e);
                                            }
                                        });

                                        // --- Crear estadísticas ---
                                        EstadisticaDAO estadisticaDAO = new EstadisticaDAO(db);

                                        Estadistica estadistica1 = new Estadistica(usuario1.getIdUsuario(), ejercicio1.getIdEjercicio(), new Date(), 70.0, 12);
                                        Estadistica estadistica2 = new Estadistica(usuario2.getIdUsuario(), ejercicio1.getIdEjercicio(), new Date(), 65.0, 10);

                                        estadisticaDAO.crear(estadistica1, new DAOInterface.OnCreado<Estadistica>() {
                                            @Override
                                            public void onSuccess(String idEst1) {
                                                Log.d("DatosPrueba", "Estadística1 creada con ID: " + idEst1);
                                            }

                                            @Override
                                            public void onFailure(Exception e) {
                                                Log.e("DatosPrueba", "Error al crear Estadística1", e);
                                            }
                                        });

                                        estadisticaDAO.crear(estadistica2, new DAOInterface.OnCreado<Estadistica>() {
                                            @Override
                                            public void onSuccess(String idEst2) {
                                                Log.d("DatosPrueba", "Estadística2 creada con ID: " + idEst2);
                                            }

                                            @Override
                                            public void onFailure(Exception e) {
                                                Log.e("DatosPrueba", "Error al crear Estadística2", e);
                                            }
                                        });

                                    }

                                    @Override
                                    public void onFailure(Exception e) {
                                        Log.e("DatosPrueba", "Error al crear Ejercicio2", e);
                                    }
                                });

                            }

                            @Override
                            public void onFailure(Exception e) {
                                Log.e("DatosPrueba", "Error al crear Ejercicio1", e);
                            }
                        });

                    }

                    @Override
                    public void onFailure(Exception e) {
                        Log.e("DatosPrueba", "Error al crear Usuario2", e);
                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("DatosPrueba", "Error al crear Usuario1", e);
            }
        });
    }
}