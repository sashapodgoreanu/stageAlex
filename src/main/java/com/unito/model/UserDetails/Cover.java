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
public class Cover {

    private CoverInfo coverInfo;

    private CoverPhoto coverPhoto;

    private String layout;

    public CoverInfo getCoverInfo() {
        return coverInfo;
    }

    public void setCoverInfo(CoverInfo coverInfo) {
        this.coverInfo = coverInfo;
    }

    public CoverPhoto getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(CoverPhoto coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    @Override
    public String toString() {
        return "Cover [coverInfo = " + coverInfo + ", coverPhoto = " + coverPhoto + ", layout = " + layout + "]";
    }
}
