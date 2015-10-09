/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

import com.unito.model.UserDetails.UserDetails;
import com.unito.model.repository.SemTElemRepository;
import com.unito.model.repository.TableRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;



@Component
@Scope(value="session",
proxyMode=ScopedProxyMode.TARGET_CLASS)
public class UserSession implements Serializable{
    
    TableRepository tableRepository;
    SemTElemRepository semTElemRepository;
    private UserDetails userDetails;
    private List<Table> tables;
    private List<Table> openTables;

    @Autowired
    public UserSession(TableRepository tableRepository, SemTElemRepository semTElemRepository) {
        this.tableRepository = tableRepository;
        this.semTElemRepository = semTElemRepository;
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
    
    public List<Table> getOpenedTables(){
        this.tables = tableRepository.getAllTables(userDetails);
        this.openTables = tableRepository.getAllTables(userDetails);
        List<Table> retVal = new ArrayList<>();
        for(Table t : this.tables){
            if (t.isOpened())
                retVal.add(t);
        }
        return retVal;
    }
    
    //TODO
    public List<SemTElem> getElementsOnTable(int idTable){
        List<SemTElem> retVal = semTElemRepository.getElementsOnTable(idTable);
        return retVal;
    }
    //TODO
    public boolean addElement(SemTElem semTElem, Table table){
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
        if (newtableId != -1){
            newTable.setID(newtableId);
            newTable.setOpened(true);
            //todo insert check control from db
            tableRepository.addUserOnTable(newTable, userDetails);
        }
        this.tables.add(newTable);
        return newtableId;
    }

    public boolean closeTable(Table closeTable) {
        return tableRepository.closeTable(userDetails,closeTable);
        
    }
    
    

    
    
    
}
