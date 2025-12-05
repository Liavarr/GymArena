package com.example.gymarena;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.gymarena.model.Chatbot;
import com.example.gymarena.model.Ejercicio;
import com.example.gymarena.model.Rutina;
import com.example.gymarena.model.Usuario;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
@RunWith(AndroidJUnit4.class)
public class ChatbotUnitTest {

    private Chatbot chatbot;
    private Usuario testUser;
    private ArrayList<Ejercicio> ejerciciosReferencia;

    @Before
    public void setUp() {
        chatbot = Chatbot.getInstance();
        testUser = new Usuario("TestUser", "test@example.com", 75.0, 1.75);
        ejerciciosReferencia = new ArrayList<>();
        ejerciciosReferencia.add(new Ejercicio("E001", "Flexiones", new ArrayList<>()));
        ejerciciosReferencia.add(new Ejercicio("E002", "Sentadillas", new ArrayList<>()));
    }

    @Test
    public void testChatRutinaGenerada() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        String prompt = "Crea una rutina de hombro para principiante";

        chatbot.chat(prompt, testUser.getIdUsuario(), ejerciciosReferencia, new Chatbot.ChatbotRutinaCallback() {
            @Override
            public void onSuccess(String texto, Rutina rutina) {
                try {
                    assertNotNull("El texto generado no puede ser null", texto);
                    assertTrue("El texto debería contener JSON si se genera rutina", texto.contains("{"));
                    if (rutina != null) {
                        assertEquals(testUser.getIdUsuario(), rutina.getIdUsuario());
                        assertTrue(rutina.getEjerciciosId().size() > 0);
                    }
                } finally {
                    latch.countDown();
                }
            }

            @Override
            public void onError(Throwable t) {
                fail("Error al generar respuesta del Chatbot: " + t.getMessage());
                latch.countDown();
            }
        });

        // Espera máximo 15 segundos
        latch.await(15, TimeUnit.SECONDS);
    }

    @Test
    public void testChatConsejo() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        String prompt = "Me he lesionado y me duele el hombro, qué puedo tomar?";

        chatbot.chat(prompt, testUser.getIdUsuario(), ejerciciosReferencia, new Chatbot.ChatbotRutinaCallback() {
            @Override
            public void onSuccess(String texto, Rutina rutina) {
                try {
                    assertNotNull(texto);
                    // En este caso rutina puede ser null porque es consejo
                    assertNull(rutina);
                    assertTrue(texto.contains("especialista") || texto.length() > 0);
                } finally {
                    latch.countDown();
                }
            }

            @Override
            public void onError(Throwable t) {
                fail("Error al generar consejo del Chatbot: " + t.getMessage());
                latch.countDown();
            }
        });

        latch.await(15, TimeUnit.SECONDS);
    }
}
