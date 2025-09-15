/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpc.whatsapp.api.core.controllers;


import com.gpc.whatsapp.api.core.model.request.WebhookRequest;
import com.gpc.whatsapp.api.core.model.response.MessageResponse;
import com.gpc.whatsapp.api.core.services.WhatsAppApiService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author racarrascal
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/webhook1")
@RestController
@Slf4j
public class WhatsAppApiController {

    private final WhatsAppApiService whatsAppApiService;

    public WhatsAppApiController(WhatsAppApiService whatsAppApiService) {
        this.whatsAppApiService = whatsAppApiService;
    }

    @Operation(summary = "Obtener el conductor por cedula ")
    @GetMapping("/test")
    public MessageResponse test() {
        log.info("Enviando mensaje {}");
        return whatsAppApiService.sendMessage("test");
    }


    @Operation(summary = "Recibir mensaje WebHook ")
    @PutMapping("/receive")
    public void receive(@RequestBody  WebhookRequest request) {
        log.info("Recibiendo mensaje de whatsapp {}",request);
        whatsAppApiService.receiveMessage(request);
    }




}
