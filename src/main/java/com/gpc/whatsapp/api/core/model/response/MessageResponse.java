package com.gpc.whatsapp.api.core.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponse {
    @JsonProperty("messaging_product")
    private String messagingProduct;

}
