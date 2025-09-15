package com.gpc.whatsapp.api.core.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContactRequest {
    private ProfileRequest profile;
    @JsonProperty("wa_id")
    private String waId;
}
