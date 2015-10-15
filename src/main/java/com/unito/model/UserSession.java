/*
 * To change this license header, choose License Headers in Project Propertie.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

import com.unito.model.UserDetails.UserDetails;
import com.unito.model.repository.PropertiesRepository;
import com.unito.model.repository.SemTElemRepository;
import com.unito.model.repository.TableRepository;
import com.unito.model.repository.UserDetailsRepositoryJDBC;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session",
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSession implements Serializable {

    @Autowired
    private UserDetailsRepositoryJDBC userDetailsRepositoryJDBC;
    @Autowired
    private TableRepository tableRepository;
    @Autowired
    private SemTElemRepository semTElemRepository;
    @Autowired
    private PropertiesRepository propertiesRepository;
    private UserDetails userDetails;
    //set on open table
    private int openTableId;

    private List<Table> tables;
    private List<Table> openTables;

    public UserSession() {
    }

    public int getOpenTableId() {
        return openTableId;
    }

    public void setOpenTableId(int openTableId) {
        this.openTableId = openTableId;
    }

    public List<Table> getOpenTables() {
        return openTables;
    }

    public void setOpenTables(List<Table> openTables) {
        this.openTables = openTables;
    }

    public UserDetails getUserdetails() {
        return userDetails;
    }

    public void setUserdetails(UserDetails userdetails) {
        this.userDetails = userdetails;
        this.tables = tableRepository.getAllTables(userdetails);
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public List<Table> getOpenedTables() {
        this.tables = tableRepository.getAllTables(userDetails);
        this.openTables = tableRepository.getAllTables(userDetails);
        List<Table> retVal = new ArrayList<>();
        for (Table t : this.tables) {
            if (t.isOpened()) {
                retVal.add(t);
            }
        }
        return retVal;
    }

    //TODO
    public List<SemTElem> getElementsOnTable(int idTable) {
        List<SemTElem> retVal = semTElemRepository.getElementsOnTable(idTable);
        return retVal;
    }

    //TODO

    public boolean addElement(SemTElem semTElem, Table table) {
        semTElemRepository.addelementOnTable(semTElem, table);
        return true;
    }

    /*@Override
     public String toString() {
     return "UserSession{" + "userdetails=" + userDetails.toString() + '}';
     }*/
    public int registerNewTable(Table newTable) {
        newTable.setOwner(this.getUserdetails().getId());
        int newtableId = tableRepository.saveTable(newTable);
        if (newtableId != -1) {
            newTable.setID(newtableId);
            newTable.setOpened(true);
            //todo insert check control from db
            tableRepository.addUserOnTable(newTable, userDetails);
        }
        this.tables.add(newTable);
        return newtableId;
    }

    public boolean closeTable(Table closeTable) {
        return tableRepository.closeTable(userDetails, closeTable);

    }

    public List<Propertie> getPersonalTagsForObj(String idObj) {
        List<Propertie> retVal;
        retVal = propertiesRepository.getPersonalPropertiesForObj(userDetails.getId(), idObj);
        return retVal;
    }

    public List<Propertie> getSharedTagsForObj(String idObj) {
        List<UserDetails> users = userDetailsRepositoryJDBC.getUsersOnTable(openTableId);
        List<Propertie> retVal = new ArrayList<>();
        for (UserDetails u : users) {
            //get all tags for this object that are not mine
            if (!u.getId().equals(userDetails.getId())) {
                retVal.addAll(propertiesRepository.getSharedPropertiesForObj(u.getId(), idObj));
            }
        }
        return retVal;
    }

}
