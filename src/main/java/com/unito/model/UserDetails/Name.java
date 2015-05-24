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
public class Name {

    private String familyName;

    private String givenName;

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    @Override
    public String toString() {
        return "Name [familyName = " + familyName + ", givenName = " + givenName + "]";
    }
}
