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
public class CoverPhoto {

    private String height;

    private String width;

    private String url;

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "CoverPhoto [height = " + height + ", width = " + width + ", url = " + url + "]";
    }
}
