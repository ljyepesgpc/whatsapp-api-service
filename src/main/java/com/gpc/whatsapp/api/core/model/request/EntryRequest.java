package com.gpc.whatsapp.api.core.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class EntryRequest {
    private String id;
    private String field;
    private List<ChangeRequest> changes;
}
