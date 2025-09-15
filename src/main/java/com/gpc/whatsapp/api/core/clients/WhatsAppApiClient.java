package com.gpc.whatsapp.api.core.clients;

import com.gpc.whatsapp.api.core.model.request.MessageRequest;
import com.gpc.whatsapp.api.core.model.response.MessageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${whatsapp.name}", url = "${whatsapp.url}")
public interface WhatsAppApiClient {

    @RequestMapping(method = RequestMethod.POST , value = "/111128371707333/messages")
    MessageResponse messages(@RequestHeader("Authorization") String token, MessageRequest message);

}
