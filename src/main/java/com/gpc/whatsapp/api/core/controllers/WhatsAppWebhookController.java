/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gpc.whatsapp.api.core.controllers;

import com.gpc.whatsapp.api.core.model.response.Change;
import com.gpc.whatsapp.api.core.model.response.Message;
import com.gpc.whatsapp.api.core.model.response.Value;
import com.gpc.whatsapp.api.core.model.response.WebhookEvent;
import com.gpc.whatsapp.api.core.model.response.WhatsAppWebhook;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/webhook/whatsapp")
public class WhatsAppWebhookController {

//    @Autowired
//    private RegistroTurnoRepository registroRepo;
    private static final String VERIFY_TOKEN = "misuperclave"; // Defínelo igual al de Meta

    @GetMapping
    public String verifyWebhook(
            @RequestParam(name = "hub.mode", required = false) String mode,
            @RequestParam(name = "hub.verify_token", required = false) String token,
            @RequestParam(name = "hub.challenge", required = false) String challenge) {

        if ("subscribe".equals(mode) && VERIFY_TOKEN.equals(token)) {
            return challenge; // ✅ Responder challenge si el token es correcto
        } else {
            return "Error: token inválido";
        }
    }

    @PostMapping
    public String recibirMensaje(@RequestBody WebhookEvent webhook) {
        System.out.println("Mensaje recibido" + webhook.toString());
        if (webhook.getEntry() == null || webhook.getEntry().isEmpty()) {
            return "XX";
        }
        List<Change> changes = webhook.getEntry().get(0).getChanges();
        if (changes == null || changes.isEmpty()) {
            return "XX";
        }
        List<Message> messages = changes.get(0).getValue().getMessages();
        if (messages == null || messages.isEmpty()) {
            return "XX";
        }

        Message message = messages.get(0);
        String from = message.getFrom();
        String body = message.getText().getBody();

        System.out.println("Mensaje recibido de " + from + ": " + body);
        enviarTexto(from, "hola mundo" + body);

        return "OK";
    }

    public void enviarTexto(String to, String mensaje) {
        try {
            String url = "https://graph.facebook.com/v23.0/728695723670491/messages";

            // === Construcción del menú con botones ===
            Map<String, Object> interactive = new HashMap<>();
            interactive.put("type", "button");

            Map<String, String> body = new HashMap<>();
            body.put("text", "¿Qué deseas hacer?");
            interactive.put("body", body);

            Map<String, Object> action = new HashMap<>();
            List<Map<String, Object>> buttons = new ArrayList<>();

            Map<String, Object> btn1 = new HashMap<>();
            btn1.put("type", "reply");
            btn1.put("reply", new HashMap<String, String>() {
                {
                    put("id", "registrar_turno");
                    put("title", "Registrar turno");
                }
            });

            Map<String, Object> btn2 = new HashMap<>();
            btn2.put("type", "reply");
            btn2.put("reply", new HashMap<String, String>() {
                {
                    put("id", "consultar_turno");
                    put("title", "Consultar turno");
                }
            });

            Map<String, Object> btn3 = new HashMap<>();
            btn3.put("type", "reply");
            btn3.put("reply", new HashMap<String, String>() {
                {
                    put("id", "salir");
                    put("title", "Salir");
                }
            });

            buttons.add(btn1);
            buttons.add(btn2);
            buttons.add(btn3);

            action.put("buttons", buttons);
            interactive.put("action", action);

            Map<String, Object> message = new HashMap<>();
            message.put("messaging_product", "whatsapp");
            message.put("to", to);
            message.put("type", "interactive");
            message.put("interactive", interactive);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth("EAFcSIOLmYFUBPUMZC8ZBI0KDVGocDr2joB51dkdZCdRbavpPT0s7ZCxD5CmTxBozoWBa0VxCD7zXy5vViK8KeZCoslfgLjeMIGA0M8DzXZABzyp6WFKBqMSVRSZB7ZBz0qZCZCglZAWibiXv5L4ZAZBVeFrinpaAupJEoFuKT9Ky8ww3HGvXzwTAdZA5MYePGDshnyv2bHcZBS0FnCZAbePIwhs5QppudOAZBLnmGr63QzDf4ZCHvUYZBpmKNoZD");

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(message, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            System.out.println("Respuesta de Meta: " + response.getBody());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
