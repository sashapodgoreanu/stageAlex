/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author SashaAlexandru
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenValidateResponse {

    private String issued_at;

    private String audience;

    private String issuer;

    private String issued_to;

    private String expires_in;

    private String user_id;

    public String getIssued_at() {
        return issued_at;
    }

    public void setIssued_at(String issued_at) {
        this.issued_at = issued_at;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getIssued_to() {
        return issued_to;
    }

    public void setIssued_to(String issued_to) {
        this.issued_to = issued_to;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "TokenValidateResponse [issued_at = " + issued_at + ", audience = " + audience + ", issuer = " + issuer + ", issued_to = " + issued_to + ", expires_in = " + expires_in + ", user_id = " + user_id + "]";
    }
}
