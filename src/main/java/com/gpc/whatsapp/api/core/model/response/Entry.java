/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpc.whatsapp.api.core.model.response;

import java.util.List;

/**
 *
 * @author ljyepes
 */
public class Entry {
      private String id;
    private List<Change> changes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Change> getChanges() {
        return changes;
    }

    public void setChanges(List<Change> changes) {
        this.changes = changes;
    }

    @Override
    public String toString() {
        return "Entry{" + "id=" + id + ", changes=" + changes + '}';
    }
    
    
}
