package com.gpc.whatsapp.api.core.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequest {

    private String id;

    private String timestamp;

    @JsonProperty("messaging_product")
    private String messagingProduct;

    @JsonProperty("preview_url")
    private boolean previewUrl;

    @JsonProperty("recipient_type")
    private String recipientType;

    private String to;

    private String type;

    private TextRequest text;

}
