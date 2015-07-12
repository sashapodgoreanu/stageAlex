/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author SashaAlexandru
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SemTElem {
    @Expose
    private String name;
    @Expose
    private String url;
    @Expose
    private List<ElemProperty> properties;

    public SemTElem(String name, String url, List<ElemProperty> properties) {
        this.name = name;
        this.url = url;
        this.properties = new ArrayList<>();
    }

    public SemTElem() {
        this.properties = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ElemProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<ElemProperty> properties) {
        this.properties = properties;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SemTElem other = (SemTElem) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "SemTElem{" + "name=" + name + ", url=" + url + '}';
    }

    
    
    
    
    
}
