package com.gpc.whatsapp.api.core.services.impl;

import com.gpc.whatsapp.api.core.clients.WhatsAppApiClient;
import com.gpc.whatsapp.api.core.model.request.MessageRequest;
import com.gpc.whatsapp.api.core.model.request.TextRequest;
import com.gpc.whatsapp.api.core.model.request.WebhookRequest;
import com.gpc.whatsapp.api.core.model.response.MessageResponse;
import com.gpc.whatsapp.api.core.services.WhatsAppApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WhatsAppApiServiceImpl implements WhatsAppApiService {

    private final WhatsAppApiClient whatsAppApiClient;

    public WhatsAppApiServiceImpl(WhatsAppApiClient whatsAppApiClient) {
        this.whatsAppApiClient = whatsAppApiClient;
    }

    @Override
    public MessageResponse sendMessage(String message) {
        log.info("Enviando mensaje {}", message);
        MessageRequest request= new MessageRequest();
        request.setMessagingProduct("whatsapp");
        request.setPreviewUrl(false);
        request.setRecipientType("individual");
        request.setTo("573145170379");
        request.setType("text");
        request.setText(new TextRequest(message));
        String token="Bearer EAAPjfZCss2JUBAEEH2XMZBVZCUDtWjV5w8LgeMQSptTt4obfNhc9M1cQyZAzAVlKzjOc0Yuim5aZA0JJcMq8cT6ZCTvHUoVnBrDFZCRvBnDeencPW4y5FZChZCn3AUCeMf0JROybNMJ49ZAOev5aG75iNhZCDSdsSFZCPywYMiZCSdGH6ZAajo2sSP6vugyk533ywLam3JMri3YJJimcFcHmXNtG12";
        return whatsAppApiClient.messages(token,request);
    }

    @Override
    public void receiveMessage(WebhookRequest webhookRequest) {
        log.info("recibimos mensaje {}", webhookRequest);


    }
}
