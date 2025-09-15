package com.gpc.whatsapp.api.core.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusRequest {
    private String id;
    private String status;
    private String timestamp;

    @JsonProperty("recipient_id")
    private String recipientId;
}
