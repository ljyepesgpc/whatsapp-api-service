package com.gpc.whatsapp.api.core.model.request;

import com.gpc.whatsapp.api.core.model.request.MessageRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ChangeRequest {
    private List<MessageRequest> messages;
    private List<StatusRequest> statuses;
    private List<ContactRequest> contacts;
}
