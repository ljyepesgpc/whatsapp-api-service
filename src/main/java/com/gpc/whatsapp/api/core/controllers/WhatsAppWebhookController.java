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
        enviarTexto(from, "hola mundo"+ body);

        return "OK";
    }

    public void enviarTexto(String to, String mensaje) {
        try {
            String url = "https://graph.facebook.com/v23.0/728695723670491/messages";

            RestTemplate restTemplate = new RestTemplate();

            Map<String, Object> body = new HashMap<>();
            body.put("messaging_product", "whatsapp");
            body.put("to", to);

            Map<String, String> text = new HashMap<>();
            text.put("body", mensaje);

            body.put("text", text);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth("EAFcSIOLmYFUBPT2D9MzDT4gu4xwCfk4j6XDT7xnZBiwYymweboKbZBK5A707CGm8yD5ZCSdIObIFrF23u1JZChMwVb9p39Nw0PH2gSx0DNixzgpUJHVTFegkF2zIZB7ltHRvx8fZAP5mkLZAK5T2ZClctZAfD0UavXLWZAlWXAkNy6qzNCrUI3tBKxowg3Lnef4ycvr48s65MBeZCoFyZB6tQ6EmC2PvG57ouETX2gh0ZAbmuRbeKDmsZD");

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class
            );

            System.out.println("Respuesta de Meta: " + response.getBody());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
