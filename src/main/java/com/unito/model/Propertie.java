/*
 * To change this license header, choose License Headers in Project Propertie.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

/**
 *
 * @author Alexandru Podgoreanu
 */
public class Propertie {
    private int id;
    private String value;
    private boolean liked;
    private boolean shared;
    private boolean deleted;
    private String ownerId;
    private String ownerActionId;

    
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getOwnerActionId() {
        return ownerActionId;
    }

    public void setOwnerActionId(String ownerActionId) {
        this.ownerActionId = ownerActionId;
    }

    
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public Propertie() {
    }

    @Override
    public String toString() {
        return "Properties{" + "id=" + id + ", value=" + value + ", in_r_bin=" + liked + ", shared=" + shared + '}';
    }
    
    
    
}
