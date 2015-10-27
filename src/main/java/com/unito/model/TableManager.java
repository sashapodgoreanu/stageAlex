/*
 * To change this license header, choose License Headers in Project Propertie.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

import com.unito.model.UserDetails.UserDetails;
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
public class TableManager implements Serializable {

    @Autowired
    private UserDetailsRepositoryJDBC userDetailsRepositoryJDBC;
    @Autowired
    private TableRepository tableRepository;
    @Autowired
    private SemTElemRepository semTElemRepository;
    @Autowired
    private TagLiked tl;
    @Autowired
    private TagUnLiked tul;
    @Autowired
    private TagPersonal tp;
    @Autowired
    private TagShared ts;
    @Autowired
    private UserDetails userDetails;
    //set on open table
    private int openTableId;
    private String lastObjectOpened;

    private List<Table> tables;
    private List<Table> openTables;

    public TableManager() {
    }

    public String getLastObjectOpened() {
        return lastObjectOpened;
    }

    public void setLastObjectOpened(String lastObjectOpened) {
        this.lastObjectOpened = lastObjectOpened;
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
    public List<SemTElem> getElementsOnTable() {
        List<SemTElem> retVal = semTElemRepository.getElementsOnTable(openTableId);
        return retVal;
    }

    //TODO
    public boolean addElement(SemTElem semTElem, Table table) {
        semTElemRepository.addelementOnTable(semTElem, table);
        return true;
    }

    /*@Override
     public String toString() {
     return "TableManager{" + "userdetails=" + userDetails.toString() + '}';
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
        this.setLastObjectOpened(idObj);
        List<Propertie> retVal = tp.getTagsForObj();
        return retVal;
    }
    
    public List<Propertie> getSharedTagsForObj(String idObj) {
        this.setLastObjectOpened(idObj);
        //List<UserDetails> users = userDetailsRepositoryJDBC.getUsersOnTable(openTableId);
        List<Propertie> retVal = ts.getTagsForObj();
        return retVal;
    }
    
    private void setupTags(String idObj){
        tp.setObjId(idObj);
        tp.setForUserId(userDetails.getId());
        ts.setObjId(idObj);
        ts.setForUserId(userDetails.getId());
        tl.setObjId(idObj);
        tl.setForUserId(userDetails.getId());
        tul.setObjId(idObj);
        tul.setForUserId(userDetails.getId());
    }

    public List<UserDetails> getUsersOnTable() {
        return userDetailsRepositoryJDBC.getUsersOnTable(openTableId);
    }

}
