/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpc.whatsapp.api.core.model.response;

/**
 *
 * @author ljyepes
 */
public class Change {
    private String field;
    private Value value;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Change{" + "field=" + field + ", value=" + value + '}';
    }
    
    
}
