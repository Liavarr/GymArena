package com.example.gymarena.model;

import com.google.firebase.ai.FirebaseAI;
import com.google.firebase.ai.GenerativeModel;
import com.google.firebase.ai.java.GenerativeModelFutures;
import com.google.firebase.ai.type.Content;
import com.google.firebase.ai.type.GenerateContentResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chatbot {

    private final String promptInterno = "Eres un asistente virtual especializado en entrenamiento y rutinas de gimnasio. "
            + "Sigue estas reglas estrictamente: "
            + "1. Mantén un tono motivador y profesional. "
            + "2. No des consejos médicos ni diagnósticos. En caso de que te lo pidan recomienda acudir a un especialista "
            + "3. Siempre sugiere ejercicios seguros para principiantes, intermedios o avanzados según la solicitud. "
            + "4. Limita la respuesta a máximo 200 palabras. "
            + "5. Nunca uses lenguaje ofensivo. "
            + "6. Si no hay suficiente información, responde con 'No se puede generar una recomendación específica'. "
            + "7. Si alguien solicita información que no corresponde a entrenamiento o cosas relacionadas al deporte responde con 'Lo lamento pero no tengo información para esa solicitud' y recomiéndale alguna función que puedas hacer."
            + "Prompt Usuario:\n";

    private static Chatbot instance;
    private final GenerativeModelFutures model;
    private final Executor executor;

    private Chatbot() {
        GenerativeModel ai = FirebaseAI.getInstance( /* bypass de import */ )
                .generativeModel("gemini-2.5-flash");
        this.model = GenerativeModelFutures.from(ai);
        this.executor = Executors.newSingleThreadExecutor();
    }

    public static synchronized Chatbot getInstance() {
        if (instance == null) {
            instance = new Chatbot();
        }
        return instance;
    }

    // Método unificado para chat: consejos o creación de rutinas
    public void chat(String promptUsuario, String idUsuario, ArrayList<Ejercicio> ejerciciosReferencia, ChatbotRutinaCallback callback) {
        // Convertimos la lista de Ejercicio a nombres
        StringBuilder ejerciciosTexto = new StringBuilder();
        for (Ejercicio e : ejerciciosReferencia) {
            ejerciciosTexto.append("- ").append(e.getNombre()).append("\n");
        }

        String promptFinal = promptInterno + "\n\nUsuario: " + promptUsuario
                + "\n\nLista de ejercicios permitidos:\n" + ejerciciosTexto.toString()
                + "\nInstrucciones: "
                + "Si el usuario pide crear una rutina, utiliza solo ejercicios de la lista anterior. "
                + "Devuelve un JSON con los campos: {\"nombre\":..., \"descripcion\":..., \"ejercicios\":[\\\"E001\\\",\\\"E002\\\",...]]}. "
                + "Si solo pide consejo, responde con texto libre. "
                + "No agregues información extra fuera del JSON.";

        generateText(promptFinal, new ResultCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    Rutina rutina = null;

                    Pattern jsonPattern = Pattern.compile("\\{.*\"nombre\".*\\}", Pattern.DOTALL);
                    Matcher matcher = jsonPattern.matcher(result);

                    if (matcher.find()) {
                        String jsonString = matcher.group();
                        org.json.JSONObject obj = new org.json.JSONObject(jsonString);

                        rutina = new Rutina();
                        rutina.setIdUsuario(idUsuario);
                        rutina.setNombre(obj.getString("nombre"));
                        rutina.setDescripcion(obj.getString("descripcion"));

                        org.json.JSONArray arr = obj.getJSONArray("ejercicios");
                        List<String> ejerciciosId = new ArrayList<>();

                        for (int i = 0; i < arr.length(); i++) {
                            String nombreEjercicio = arr.getString(i);
                            for (Ejercicio e : ejerciciosReferencia) {
                                if (e.getNombre().equalsIgnoreCase(nombreEjercicio)) {
                                    ejerciciosId.add(e.getIdEjercicio());
                                    break;
                                }
                            }
                        }

                        rutina.setEjerciciosId(ejerciciosId);
                    }

                    callback.onSuccess(result, rutina); // rutina será null si solo es consejo

                } catch (Exception e) {
                    callback.onError(e);
                }
            }

            @Override
            public void onError(Throwable t) {
                callback.onError(t);
            }
        });
    }

    // Callback unificado
    public interface ChatbotRutinaCallback {
        void onSuccess(String texto, Rutina rutina); // rutina puede ser null
        void onError(Throwable t);
    }

    // Método original para generar texto
    public void generateText(String promptTexto, ResultCallback callback) {
        Content prompt = new Content.Builder()
                .addText(promptTexto)
                .build();

        model.generateContent(prompt)
                .addListener(() -> {
                    try {
                        GenerateContentResponse response = model.generateContent(prompt).get();
                        String result = response.getText();
                        callback.onSuccess(result);
                    } catch (Exception e) {
                        callback.onError(e);
                    }
                }, executor);
    }

    public interface ResultCallback {
        void onSuccess(String result);
        void onError(Throwable t);
    }
}
