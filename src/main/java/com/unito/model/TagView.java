/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

import com.unito.model.Propertie;
import com.unito.model.TableManager;
import com.unito.model.repository.PropertieRepository;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Alexandru Podgoreanu
 */
public abstract class TagView {

    @Autowired
    public TableManager tm;
    @Autowired
    public PropertieRepository propertyRepository;
    public String objId;//Object ID
    public String forUserId;//UserDetails ID
    public int tableId;//table ID

    abstract List<Propertie> getTagsForObj();

    abstract List<Propertie> getTagsForTable(String candidate);

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getForUserId() {
        return forUserId;
    }

    public void setForUserId(String forUserId) {
        this.forUserId = forUserId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    @PostConstruct
    public void post() {
        LOG.log(Level.INFO, "Setting up {0} ...", this.getClass().getSimpleName());
        this.tableId = tm.getOpenTableId();
        this.forUserId = tm.getUserdetails().getId();
        this.objId = tm.getLastObjectOpened();
    }
    private static final Logger LOG = Logger.getLogger(TagView.class.getName());

}
