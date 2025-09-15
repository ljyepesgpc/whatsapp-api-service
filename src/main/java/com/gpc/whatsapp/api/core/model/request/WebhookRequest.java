package com.gpc.whatsapp.api.core.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class WebhookRequest {
    private String object;
    private List<EntryRequest> entry;
}
