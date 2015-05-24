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
public class CoverInfo {

    private String leftImageOffset;

    private String topImageOffset;

    public String getLeftImageOffset() {
        return leftImageOffset;
    }

    public void setLeftImageOffset(String leftImageOffset) {
        this.leftImageOffset = leftImageOffset;
    }

    public String getTopImageOffset() {
        return topImageOffset;
    }

    public void setTopImageOffset(String topImageOffset) {
        this.topImageOffset = topImageOffset;
    }

    @Override
    public String toString() {
        return "CoverInfo [leftImageOffset = " + leftImageOffset + ", topImageOffset = " + topImageOffset + "]";
    }
}
