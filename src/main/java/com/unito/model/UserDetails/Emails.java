/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model.UserDetails;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author SashaAlexandru
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Emails {

    private String value;

    private String type;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Emails [value = " + value + ", type = " + type + "]";
    }
}
