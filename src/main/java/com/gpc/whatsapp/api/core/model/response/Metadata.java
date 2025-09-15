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
public class Metadata {

    private String display_phone_number;
    private String phone_number_id;

    public String getDisplay_phone_number() {
        return display_phone_number;
    }

    public void setDisplay_phone_number(String display_phone_number) {
        this.display_phone_number = display_phone_number;
    }

    public String getPhone_number_id() {
        return phone_number_id;
    }

    public void setPhone_number_id(String phone_number_id) {
        this.phone_number_id = phone_number_id;
    }

}
