/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gpc.whatsapp.api.core.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
 
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
    public String recibirMensaje(@RequestBody String payload) {
       
        String estado = "INICIO";//ConversacionManager.getEstado(telefono);
       
 
        switch (estado) {
            case "INICIO":
//                ConversacionManager.setEstado(telefono, "MENU");
                return "? Hola! ¿Desea *Registrar turno* o *Consultar turno*?";
// 
//            case "MENU":
//                if (body.contains("registrar")) {
//                    ConversacionManager.setEstado(telefono, "ROL");
//                    return "Seleccione el rol que hizo hoy: [Operador, Supervisor]";
//                } else if (body.contains("consultar")) {
//                    var ultimo = registroRepo.findTopByTelefonoOrderByFechaDesc(telefono);
//                    String turnoInfo = (ultimo != null)
//                            ? "Su último turno fue: " + ultimo.getTurno() + " en rol " + ultimo.getRol()
//                            : "No tiene turnos registrados.";
//                    ConversacionManager.setEstado(telefono, "FINAL");
//                    return turnoInfo + "\n¿Desea registrar otro turno o finalizar?";
//                } else {
//                    return "Por favor escriba *Registrar* o *Consultar*.";
//                }
// 
//            case "ROL":
//                ConversacionManager.setEstado(telefono, "TURNO");
//                return "Seleccione el turno: [Mañana, Tarde, Noche]";
// 
//            case "TURNO":
//                RegistroTurno reg = new RegistroTurno();
//                reg.setTelefono(telefono);
//                reg.setRol("Operador"); // ⚠️ aquí deberías guardar el rol real elegido
//                reg.setTurno(body);
//                registroRepo.save(reg);
// 
//                ConversacionManager.setEstado(telefono, "FINAL");
//                return "✅ Registro guardado. ¿Desea registrar otro turno o finalizar?";
// 
//            case "FINAL":
//                if (body.contains("otro")) {
//                    ConversacionManager.setEstado(telefono, "MENU");
//                    return "¿Desea *Registrar turno* o *Consultar turno*?";
//                } else {
//                    ConversacionManager.reset(telefono);
//                    return "Gracias 🙌, la conversación ha finalizado. Escriba *Hola* para empezar de nuevo.";
//                }
 
            default:
//                ConversacionManager.reset(telefono);
                return "No entendí , escriba *Hola* para comenzar.";
        }
    }
}
