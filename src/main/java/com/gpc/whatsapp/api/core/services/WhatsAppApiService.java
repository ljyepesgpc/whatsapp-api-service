package com.gpc.whatsapp.api.core.services;

import com.gpc.whatsapp.api.core.model.request.WebhookRequest;
import com.gpc.whatsapp.api.core.model.response.MessageResponse;

public interface WhatsAppApiService {
    MessageResponse sendMessage(String message);
    void receiveMessage(WebhookRequest webhookRequest);
}
